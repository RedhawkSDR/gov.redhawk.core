/*******************************************************************************
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.bulkio.util.internal;

import gov.redhawk.bulkio.util.AbstractUberBulkIOPort;
import gov.redhawk.bulkio.util.BulkIOUtilActivator;
import gov.redhawk.bulkio.util.IPortFactory;
import gov.redhawk.bulkio.util.PortReference;
import gov.redhawk.sca.util.Debug;
import gov.redhawk.sca.util.OrbSession;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.omg.CORBA.SystemException;

import BULKIO.PrecisionUTCTime;
import BULKIO.StreamSRI;
import BULKIO.dataCharOperations;
import BULKIO.dataDoubleOperations;
import BULKIO.dataFloatOperations;
import BULKIO.dataLongLongOperations;
import BULKIO.dataLongOperations;
import BULKIO.dataOctetOperations;
import BULKIO.dataShortOperations;
import BULKIO.dataUlongLongOperations;
import BULKIO.dataUlongOperations;
import BULKIO.dataUshortOperations;
import BULKIO.updateSRIOperations;
import CF.Port;
import CF.PortHelper;
import CF.PortPackage.InvalidPort;

/**
 * @since 1.1
 */
public class Connection extends AbstractUberBulkIOPort {
	private static final Debug DEBUG_PUSHPACKET = new Debug(BulkIOUtilActivator.PLUGIN_ID, Connection.class.getSimpleName());

	private OrbSession orbSession = OrbSession.createSession();
	private final ConnectionKey info;
	private final String connectionId;
	private Port port;
	private PortReference ref;
	private final List<updateSRIOperations> children = Collections.synchronizedList(new ArrayList<updateSRIOperations>());

	private boolean disposed;
	private boolean warnedPushPacketError = false;

	public Connection(ConnectionKey info) {
		this.info = info;
		if (info.getConnectionID() == null) {
			connectionId = createConnectionID();
		} else {
			connectionId = info.getConnectionID();
		}
	}

	public void connectPort() throws CoreException {
		if (disposed) {
			// Connection is disposed, do nothing
			return;
		}
		this.port = PortHelper.narrow(orbSession.getOrb().string_to_object(info.getIor()));
		if (port == null) {
			throw new IllegalStateException("Failed to narrow to port.");
		}
		IPortFactory factory = BulkIOUtilActivator.getDefault().getPortFactory();
		if (factory == null) {
			throw new CoreException(new Status(Status.ERROR, BulkIOUtilActivator.PLUGIN_ID, "Failed to find Port Factory", null));
		}

		ref = factory.connect(connectionId, info.getIor(), info.getType(), this);
	}

	// SRI has changed for specified streamID
	@Override
	protected void handleStreamSRIChanged(@NonNull String streamID, @Nullable StreamSRI oldSri, @NonNull final StreamSRI newSri) {
		Object[] childrenArray = children.toArray();
		for (final Object child : childrenArray) {
			SafeRunner.run(new ISafeRunnable() {

				@Override
				public void handleException(Throwable exception) {
				}

				@Override
				public void run() throws Exception {
					((updateSRIOperations) child).pushSRI(newSri);
				}
			});

		}
	}

	private static String createConnectionID() {
		return System.getProperty("user.name", "user") + "_" + System.currentTimeMillis();
	}

	public void dispose() {
		synchronized (this) {
			if (disposed) {
				return;
			}
			disposed = true;
		}
		try {
			if (port != null) {
				port.disconnectPort(connectionId);
				port = null;
			}
		} catch (InvalidPort e) {
			// PASS
		} catch (SystemException e) {
			// PASS
		}
		if (ref != null) {
			ref.dispose();
			ref = null;
		}
		children.clear();

		orbSession.dispose();
	}

	public void registerDataReceiver(@NonNull final updateSRIOperations receiver) {
		info.getType().getPortType().cast(receiver);

		children.add(receiver);

		if (activeSRIs().length > 0) {
			Job job = new Job("pushSRI to new receiver") {
				@Override
				protected IStatus run(IProgressMonitor monitor) {
					StreamSRI[] currentSri = activeSRIs();
					for (StreamSRI sri : currentSri) {
						receiver.pushSRI(sri);
					}
					return Status.OK_STATUS;
				}
			};
			job.setSystem(true); // hide from progress monitor
			job.setUser(false);
			job.schedule(0);
		}
	}

	public void deregisterDataReceiver(@NonNull updateSRIOperations receiver) {
		children.remove(receiver);
	}

	public boolean isEmpty() {
		return children.isEmpty();
	}

	@Override
	public void pushPacket(char[] data, PrecisionUTCTime time, boolean eos, String streamID) {
		if (!super.pushPacket(data.length, time, eos, streamID)) {
			return;
		}
		for (updateSRIOperations child : children) {
			try {
				((dataCharOperations) child).pushPacket(data, time, eos, streamID);
			} catch (Exception e) { // SUPPRESS CHECKSTYLE IllegalCatch
				if (!warnedPushPacketError) {
					warnedPushPacketError = true;
					log(IStatus.ERROR, "This will only be logged once per Port Connection, got exception calling pushPacket(" + data.getClass().getCanonicalName() + "...) on " + child, e);
				}
				if (DEBUG_PUSHPACKET.enabled) {
					DEBUG_PUSHPACKET.message("Got exception calling pushPacket({0}...) on {1}. Exception={2}. IOR={3}" , data.getClass().getCanonicalName(), child, e, info.getIor());
				}
			}
		}
	}

	@Override
	public void pushPacket(double[] data, PrecisionUTCTime time, boolean eos, String streamID) {
		if (!super.pushPacket(data.length, time, eos, streamID)) {
			return;
		}
		for (updateSRIOperations child : children) {
			try {
				((dataDoubleOperations) child).pushPacket(data, time, eos, streamID);
			} catch (Exception e) { // SUPPRESS CHECKSTYLE IllegalCatch
				if (!warnedPushPacketError) {
					warnedPushPacketError = true;
					log(IStatus.ERROR, "This will only be logged once per Port Connection, got exception calling pushPacket(" + data.getClass().getCanonicalName() + "...) on " + child, e);
				}
				if (DEBUG_PUSHPACKET.enabled) {
					DEBUG_PUSHPACKET.message("Got exception calling pushPacket({0}...) on {1}. Exception={2}. IOR={3}" , data.getClass().getCanonicalName(), child, e, info.getIor());
				}
			}
		}
	}

	@Override
	public void pushPacket(float[] data, PrecisionUTCTime time, boolean eos, String streamID) {
		if (!super.pushPacket(data.length, time, eos, streamID)) {
			return;
		}
		for (updateSRIOperations child : children) {
			try {
				((dataFloatOperations) child).pushPacket(data, time, eos, streamID);
			} catch (Exception e) { // SUPPRESS CHECKSTYLE IllegalCatch
				if (!warnedPushPacketError) {
					warnedPushPacketError = true;
					log(IStatus.ERROR, "This will only be logged once per Port Connection, got exception calling pushPacket(" + data.getClass().getCanonicalName() + "...) on " + child, e);
				}
				if (DEBUG_PUSHPACKET.enabled) {
					DEBUG_PUSHPACKET.message("Got exception calling pushPacket({0}...) on {1}. Exception={2}. IOR={3}" , data.getClass().getCanonicalName(), child, e, info.getIor());
				}
			}
		}
	}

	@Override
	public void pushPacket(byte[] data, PrecisionUTCTime time, boolean eos, String streamID) {
		if (!super.pushPacket(data.length, time, eos, streamID)) {
			return;
		}
		for (updateSRIOperations child : children) {
			try {
				((dataOctetOperations) child).pushPacket(data, time, eos, streamID);
			} catch (Exception e) { // SUPPRESS CHECKSTYLE IllegalCatch
				if (!warnedPushPacketError) {
					warnedPushPacketError = true;
					log(IStatus.ERROR, "This will only be logged once per Port Connection, got exception calling pushPacket(" + data.getClass().getCanonicalName() + "...) on " + child, e);
				}
				if (DEBUG_PUSHPACKET.enabled) {
					DEBUG_PUSHPACKET.message("Got exception calling pushPacket({0}...) on {1}. Exception={2}. IOR={3}" , data.getClass().getCanonicalName(), child, e, info.getIor());
				}
			}
		}
	}

	@Override
	public void pushPacket(final short[] data, final PrecisionUTCTime time, final boolean eos, final String streamID) {
		if (!super.pushPacket(data.length, time, eos, streamID)) {
			return;
		}
		for (final updateSRIOperations child : children) {
			try {
				if (info.getType().isUnsigned()) {
					((dataUshortOperations) child).pushPacket(data, time, eos, streamID);
				} else {
					((dataShortOperations) child).pushPacket(data, time, eos, streamID);
				}
			} catch (Exception e) { // SUPPRESS CHECKSTYLE IllegalCatch
				if (!warnedPushPacketError) {
					warnedPushPacketError = true;
					log(IStatus.ERROR, "This will only be logged once per Port Connection, got exception calling pushPacket(" + data.getClass().getCanonicalName() + "...) on " + child, e);
				}
				if (DEBUG_PUSHPACKET.enabled) {
					DEBUG_PUSHPACKET.message("Got exception calling pushPacket({0}...) on {1}. Exception={2}. IOR={3}" , data.getClass().getCanonicalName(), child, e, info.getIor());
				}
			}
		}
	}

	@Override
	public void pushPacket(int[] data, PrecisionUTCTime time, boolean eos, String streamID) {
		if (!super.pushPacket(data.length, time, eos, streamID)) {
			return;
		}
		for (updateSRIOperations child : children) {
			try {
				if (info.getType().isUnsigned()) {
					((dataUlongOperations) child).pushPacket(data, time, eos, streamID);
				} else {
					((dataLongOperations) child).pushPacket(data, time, eos, streamID);
				}
			} catch (Exception e) { // SUPPRESS CHECKSTYLE IllegalCatch
				if (!warnedPushPacketError) {
					warnedPushPacketError = true;
					log(IStatus.ERROR, "This will only be logged once per Port Connection, got exception calling pushPacket(" + data.getClass().getCanonicalName() + "...) on " + child, e);
				}
				if (DEBUG_PUSHPACKET.enabled) {
					DEBUG_PUSHPACKET.message("Got exception calling pushPacket({0}...) on {1}. Exception={2}. IOR={3}" , data.getClass().getCanonicalName(), child, e, info.getIor());
				}
			}
		}
	}

	@Override
	public void pushPacket(long[] data, PrecisionUTCTime time, boolean eos, String streamID) {
		if (!super.pushPacket(data.length, time, eos, streamID)) {
			return;
		}
		for (updateSRIOperations child : children) {
			try {
				if (info.getType().isUnsigned()) {
					((dataUlongLongOperations) child).pushPacket(data, time, eos, streamID);
				} else {
					((dataLongLongOperations) child).pushPacket(data, time, eos, streamID);
				}
			} catch (Exception e) { // SUPPRESS CHECKSTYLE IllegalCatch
				if (!warnedPushPacketError) {
					warnedPushPacketError = true;
					log(IStatus.ERROR, "This will only be logged once per Port Connection, got exception calling pushPacket(" + data.getClass().getCanonicalName() + "...) on " + child, e);
				}
				if (DEBUG_PUSHPACKET.enabled) {
					DEBUG_PUSHPACKET.message("Got exception calling pushPacket({0}...) on {1}. Exception={2}. IOR={3}" , data.getClass().getCanonicalName(), child, e, info.getIor());
				}
			}
		}
	}

	private void log(int severity, String msg, Throwable t) {
		BulkIOUtilActivator.getDefault().getLog().log(new Status(severity, BulkIOUtilActivator.PLUGIN_ID, msg, t));
	}
}
