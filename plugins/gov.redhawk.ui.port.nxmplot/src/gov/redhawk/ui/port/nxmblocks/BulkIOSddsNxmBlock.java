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
import gov.redhawk.sca.util.ORBUtil;
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
				BulkIOSddsNxmBlock.TRACE_LOG.message("handleAttach: SDDSStreamDefinition.id = [{0}] attachID = [{1}] SddsStreamSession = {2}", id, sss.getAttachId(), sss);
			}
			streamIDToSSSMap.put(streamID, sss);

			StreamSRI streamSRI = getSri(streamID);
			if (streamSRI == null) {
				streamSRI = new StreamSRI();
				streamSRI.streamID = streamID;
				putSri(streamID, streamSRI);
				BulkIOSddsNxmBlock.TRACE_LOG.message("handleAttach: creating new StreamSRI for map: {0}", streamSRI);
			} else {
				BulkIOSddsNxmBlock.TRACE_LOG.message("handleAttach: using StreamSRI found in map: {0}", streamSRI); 
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

	public BulkIOSddsNxmBlock(@NonNull AbstractNxmPlotWidget plotWidget, @NonNull ScaUsesPort scaUsesPort, @Nullable SddsNxmBlockSettings settings) {
		super(plotWidget, settings);
		this.scaUsesPort = scaUsesPort;
	}

	@Override
	@NonNull
	protected String formCmdLine(@NonNull AbstractNxmPlotWidget plotWidget, String streamID) {
		final String outputName = AbstractNxmPlotWidget.createUniqueName(true);
		putOutputNameMapping(0, streamID, outputName); // save output name mapping

		SddsStreamSession sss = streamIDToSSSMap.get(streamID);
		SDDSStreamDefinition sddsStreamDef = sss.getSddsStreamDef();
		
		final StringBuilder switches = new StringBuilder("");
		ByteOrder byteOrder = getDataByteOrder();
		if (byteOrder != null) {
			switches.append("/BYTEORDER=").append(byteOrder);
		}
		final int pipeSize = getPipeSize(); // in bytes
		if (pipeSize > 0) {
			switches.append("/PS=").append(pipeSize);
		}
		
		// use values from SDDSStreamDefinition as each attach might be from different source mcastAddr, port, vlan, format, etc. 
		String outputFormat = BulkIOSddsNxmBlock.sddsDigraph2MidasFormatType(sddsStreamDef.dataFormat);
		switches.append("/FC=").append(outputFormat);
		switches.append("/MGRP=").append(sddsStreamDef.multicastAddress);
		switches.append("/PORT=").append(sddsStreamDef.port);
		switches.append("/VLAN=").append(sddsStreamDef.vlan);

		String pattern = "SOURCENIC{0}/BG OUT={1}";
		String cmdLine = MessageFormat.format(pattern, switches, outputName);

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

	private void disconnect() {
		ScaUsesPort port = scaUsesPort;
		try {
			if (port != null && !port.isDisposed()) {
				port.disconnectPort(getConnectionID()); // disconnect from BULKIO dataSddsOut Port
			}
		} catch (InvalidPort e) {
			// PASS
		} catch (SystemException e) {
			// PASS
		}
		if (corbaObjRef != null) {
			ORBUtil.release(corbaObjRef); // release corba object reference
			corbaObjRef = null;
		}
		sddsPort = null;
	}

	@Override
	public void start() throws CoreException {
		BulkIOSddsNxmBlock.TRACE_LOG.enteringMethod();
		if (sddsPort != null) {
			throw new IllegalStateException("This block has already started! " + this);
		}

		connect();
		BulkIOSddsNxmBlock.TRACE_LOG.exitingMethod();
	}

	@Override
	public void stop() {
		BulkIOSddsNxmBlock.TRACE_LOG.enteringMethod(isStopped());

		if (isStopped()) {
			return; // It is valid to attempt to stop a block more than once, so just return
		}
		super.stop();

		disconnect();
		scaUsesPort = null;

		BulkIOSddsNxmBlock.TRACE_LOG.exitingMethod();
	}

	private static String sddsDigraph2MidasFormatType(SDDSDataDigraph sddsDataFormat) {
		switch (sddsDataFormat.value()) {
		case SDDSDataDigraph._SDDS_SF:
			return "SF";
		case SDDSDataDigraph._SDDS_SI:
			return "SI";
		case SDDSDataDigraph._SDDS_SB:
			return "SB";
		case SDDSDataDigraph._SDDS_SL:
			return "SL";
		case SDDSDataDigraph._SDDS_SX:
			return "SX";
		case SDDSDataDigraph._SDDS_SD:
			return "SD";
		case SDDSDataDigraph._SDDS_CB:
			return "CB";
		case SDDSDataDigraph._SDDS_CI:
			return "CI";
		case SDDSDataDigraph._SDDS_CL:
			return "CL";
		case SDDSDataDigraph._SDDS_CX:
			return "CX";
		case SDDSDataDigraph._SDDS_CF:
			return "CF";
		case SDDSDataDigraph._SDDS_CD:
			return "CD";
		default:
			return ""; // TODO: throw exception? or return empty string?
		}
	}

	private static String createConnectionID() {
		return System.getProperty("user.name", "user") + "_" + System.currentTimeMillis();
	}

}
