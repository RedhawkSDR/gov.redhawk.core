/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.ui.port.nxmblocks;

import gov.redhawk.bulkio.util.AbstractBulkIOSDDSPort;
import gov.redhawk.bulkio.util.AbstractBulkIOSDDSPort.SddsStreamSession;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.sca.util.Debug;
import gov.redhawk.sca.util.OrbSession;
import gov.redhawk.ui.port.nxmplot.AbstractNxmPlotWidget;
import gov.redhawk.ui.port.nxmplot.PlotActivator;

import java.nio.ByteOrder;
import java.text.MessageFormat;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.omg.CORBA.SystemException;
import org.omg.PortableServer.Servant;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import BULKIO.SDDSDataDigraph;
import BULKIO.SDDSStreamDefinition;
import BULKIO.StreamSRI;
import BULKIO.dataSDDSPackage.AttachError;
import BULKIO.dataSDDSPackage.DetachError;
import BULKIO.dataSDDSPackage.StreamInputError;
import CF.PortPackage.InvalidPort;
import CF.PortPackage.OccupiedPort;

/**
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.4
 */
public class BulkIOSddsNxmBlock extends SddsNxmBlock {

	private static final Debug TRACE_LOG = new Debug(PlotActivator.PLUGIN_ID, BulkIOSddsNxmBlock.class.getSimpleName());

	private static OrbSession orbSession = OrbSession.createSession();

	private final ConcurrentHashMap<String, SddsStreamSession> streamIDToSSSMap = new ConcurrentHashMap<String, SddsStreamSession>();
	private final ConcurrentHashMap<String, StreamSRI> streamIDToSriMap = new ConcurrentHashMap<String, StreamSRI>();

	private ScaUsesPort scaUsesPort;
	private org.omg.CORBA.Object corbaObjRef;
	private String connectionId;
	private SddsPort sddsPort;

	class SddsPort extends AbstractBulkIOSDDSPort {

		@Override
		protected void handleAttach(SddsStreamSession sss) throws AttachError, StreamInputError {
			final String streamID;
			final String id = sss.getSddsStreamDef().id; // SDDSStreamDefinition.id matches StreamSRI.streamID
			if (id != null) {
				streamID = id;
			} else {
				streamID = sss.getAttachId(); // fall-back to attachment ID
				BulkIOSddsNxmBlock.TRACE_LOG.message("WARN: no SDDSStreamDefinition.id specified! using attachID: " + streamID);
			}
			if (BulkIOSddsNxmBlock.TRACE_LOG.enabled) {
				BulkIOSddsNxmBlock.TRACE_LOG.message("SDDSStreamDefinition.id = [{0}] attachID = [{1}] SddsStreamSession = {2}", id, sss.getAttachId(), sss);
			}
			streamIDToSSSMap.put(streamID, sss);

			StreamSRI streamSRI = streamIDToSriMap.get(streamID);
			if (streamSRI == null) {
				streamSRI = new StreamSRI();
				streamSRI.streamID = streamID;
				streamIDToSriMap.put(streamID, streamSRI);
			}
			
			int sr = sss.getSddsStreamDef().sampleRate;
			if (sr > 0) {
				streamSRI.xdelta = 1.0 / sr;
			}

			final StreamSRI sri = streamSRI;
			// run in background so we don't further block our caller
			Job job = new Job("launching BulkIO SDDS stream: " + streamID) {
				@Override
				protected IStatus run(IProgressMonitor monitor) {
					launch(streamID, sri);
					return Status.OK_STATUS;
				}
			};
			job.setUser(false);
			job.schedule(0);
		}

		@Override
		protected void handleDetach(SddsStreamSession sss) throws DetachError, StreamInputError {
			BulkIOSddsNxmBlock.TRACE_LOG.enteringMethod(sss);
			String streamID = sss.getSddsStreamDef().id;
			if (streamID == null) {
				streamID = sss.getAttachId(); // fall-back to attachment ID
			}
			shutdown(streamID);
			streamIDToSSSMap.remove(streamID);
			streamIDToSriMap.remove(streamID);
			BulkIOSddsNxmBlock.TRACE_LOG.exitingMethod();
		}

		@Override
		protected void handleStreamSRIChanged(@NonNull String streamID, @Nullable StreamSRI oldSri, @NonNull StreamSRI newSri) {
			BulkIOSddsNxmBlock.TRACE_LOG.enteringMethod(streamID, oldSri, newSri);
			// TODO: what should we do here?
		}

	} // inner class SddsPort

	public BulkIOSddsNxmBlock(@NonNull AbstractNxmPlotWidget plotWidget, @NonNull ScaUsesPort scaUsesPort) {
		this(plotWidget, scaUsesPort, null);
	}

	public BulkIOSddsNxmBlock(@NonNull AbstractNxmPlotWidget plotWidget, @NonNull ScaUsesPort scaUsesPort, @NonNull SddsNxmBlockSettings settings) {
		super(plotWidget, settings);
		this.scaUsesPort = scaUsesPort;
	}

	@Override
	@NonNull
	protected String formCmdLine(@NonNull AbstractNxmPlotWidget plotWidget, String streamID) {
		final String outputName = AbstractNxmPlotWidget.createUniqueName(true);
		putOutputNameMapping(0, streamID, outputName); // save output name mapping

		SddsStreamSession sss = streamIDToSSSMap.get(streamID);
		SDDSStreamDefinition sddsSettings = sss.getSddsStreamDef();
		String outputFormat = getOutputFormat();
		if (outputFormat == null) {
			outputFormat = BulkIOSddsNxmBlock.sddsDigraph2MidasFormatType(sddsSettings.dataFormat);
		}
		final StringBuilder switches = new StringBuilder("");
		final int pipeSize = getPipeSize(); // in bytes
		if (pipeSize > 0) {
			switches.append("/PS=").append(pipeSize);
		}

		ByteOrder byteOrder = getDataByteOrder();
		String pattern = "SOURCENIC{0}/BG/BYTEORDER={1}/FC={2}/MGRP={3}/VLAN={4,number,#}/PORT={5,number,#} OUT={6}";
		String cmdLine = MessageFormat.format(pattern, switches, byteOrder, outputFormat, sddsSettings.multicastAddress, sddsSettings.vlan, sddsSettings.port,
			outputName);

		return cmdLine;
	}

	private void connect() throws CoreException {
		sddsPort = new SddsPort();
		Servant sddsPortServant = sddsPort.toServant(BulkIOSddsNxmBlock.orbSession.getPOA());

		try {
			corbaObjRef = BulkIOSddsNxmBlock.orbSession.getPOA().servant_to_reference(sddsPortServant);
			connectionId = BulkIOSddsNxmBlock.createConnectionID();

			scaUsesPort.connectPort(corbaObjRef, connectionId);
		} catch (ServantNotActive e) {
			throw new CoreException(new Status(IStatus.ERROR, PlotActivator.PLUGIN_ID, "Failed to register connection (1).", e));
		} catch (WrongPolicy e) {
			throw new CoreException(new Status(IStatus.ERROR, PlotActivator.PLUGIN_ID, "Failed to register connection (2).", e));
		} catch (InvalidPort e) {
			throw new CoreException(new Status(IStatus.ERROR, PlotActivator.PLUGIN_ID, "Failed to register connection (3).", e));
		} catch (OccupiedPort e) {
			throw new CoreException(new Status(IStatus.ERROR, PlotActivator.PLUGIN_ID, "Failed to register connection (4).", e));
		} catch (SystemException e) {
			throw new CoreException(new Status(IStatus.ERROR, PlotActivator.PLUGIN_ID, "Failed to register connection (5).", e));
		}
	}

	@Override
	public void start() throws CoreException {
		BulkIOSddsNxmBlock.TRACE_LOG.enteringMethod();
		if (sddsPort != null) {
			// PASS: TODO: disconnect from previous SDDS port impl/servant? or throw IllegalState Exception?
		}

		connect();
		BulkIOSddsNxmBlock.TRACE_LOG.exitingMethod();
	}

	@Override
	public void stop() {
		BulkIOSddsNxmBlock.TRACE_LOG.enteringMethod();
		try {
			if (scaUsesPort != null) {
				scaUsesPort.disconnectPort(connectionId); // disconnect from BULKIO dataSddsOut Port
				scaUsesPort = null;
			}
		} catch (InvalidPort e) {
			// PASS
		} catch (SystemException e) {
			// PASS
		}
		if (corbaObjRef != null) {
			corbaObjRef._release(); // release corba object reference
			corbaObjRef = null;
		}
		if (sddsPort != null) {
			sddsPort = null;
		}
		BulkIOSddsNxmBlock.TRACE_LOG.exitingMethod();
	}

	private static String sddsDigraph2MidasFormatType(SDDSDataDigraph sddsDataFormat) {
		String format = null;
		if (SDDSDataDigraph.SDDS_SF.equals(sddsDataFormat)) {
			format = "SF";
		} else if (SDDSDataDigraph.SDDS_SI.equals(sddsDataFormat)) {
			format = "SI";
		} else if (SDDSDataDigraph.SDDS_SB.equals(sddsDataFormat)) {
			format = "SB";
		} else if (SDDSDataDigraph.SDDS_SL.equals(sddsDataFormat)) {
			format = "SL";
		} else if (SDDSDataDigraph.SDDS_SX.equals(sddsDataFormat)) {
			format = "SX";
		} else if (SDDSDataDigraph.SDDS_SD.equals(sddsDataFormat)) {
			format = "SD";
		} else if (SDDSDataDigraph.SDDS_CB.equals(sddsDataFormat)) {
			format = "CB";
		} else if (SDDSDataDigraph.SDDS_CI.equals(sddsDataFormat)) {
			format = "CI";
		} else if (SDDSDataDigraph.SDDS_CL.equals(sddsDataFormat)) {
			format = "CL";
		} else if (SDDSDataDigraph.SDDS_CX.equals(sddsDataFormat)) {
			format = "CX";
		} else if (SDDSDataDigraph.SDDS_CF.equals(sddsDataFormat)) {
			format = "CF";
		} else if (SDDSDataDigraph.SDDS_CD.equals(sddsDataFormat)) {
			format = "CD";
		} else {
			format = ""; // unknown ?TODO: throw exception? or return empty string?
		}
		return format;
	}

	private static String createConnectionID() {
		return System.getProperty("user.name", "user") + "_" + System.currentTimeMillis();
	}

}
