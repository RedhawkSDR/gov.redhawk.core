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
import gov.redhawk.bulkio.util.BulkIOType;
import gov.redhawk.bulkio.util.BulkIOUtilActivator;
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
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

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
import CF.PortPackage.OccupiedPort;

/**
 * @since 1.1
 */
public class Connection extends AbstractUberBulkIOPort {
	private static final Debug DEBUG_PUSHPACKET = new Debug(BulkIOUtilActivator.PLUGIN_ID, Connection.class.getSimpleName());

	private OrbSession orbSession = OrbSession.createSession();

	private String ior;
	private Port port;
	private org.omg.CORBA.Object ref;
	private String connectionId;
	private final List<updateSRIOperations> children = Collections.synchronizedList(new ArrayList<updateSRIOperations>());

	private boolean disposed;
	private boolean warnedPushPacketError = false;

	private final BulkIOType type;

	public Connection(@NonNull BulkIOType type) {
		this.type = type;
	}

	public void connectPort(@NonNull String ior) throws CoreException {
		if (disposed) {
			// Connection is disposed, do nothing
			return;
		}
		this.ior = ior;
		this.port = PortHelper.narrow(orbSession.getOrb().string_to_object(ior));
		if (port == null) {
			throw new IllegalStateException("Failed to narrow to port.");
		}

		try {
			ref = type.createRef(orbSession.getPOA(), this);
			connectionId = createConnectionID();
			this.port.connectPort(ref, connectionId);
		} catch (ServantNotActive e) {
			throw new CoreException(new Status(Status.ERROR, BulkIOUtilActivator.PLUGIN_ID, "Failed to register new shared connection.", e));
		} catch (WrongPolicy e) {
			throw new CoreException(new Status(Status.ERROR, BulkIOUtilActivator.PLUGIN_ID, "Failed to register new shared connection.", e));
		} catch (InvalidPort e) {
			throw new CoreException(new Status(Status.ERROR, BulkIOUtilActivator.PLUGIN_ID, "Failed to register new shared connection.", e));
		} catch (OccupiedPort e) {
			throw new CoreException(new Status(Status.ERROR, BulkIOUtilActivator.PLUGIN_ID, "Failed to register new shared connection.", e));
		} catch (SystemException e) {
			throw new CoreException(new Status(Status.ERROR, BulkIOUtilActivator.PLUGIN_ID, "Failed to register new shared connection.", e));
		}
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
			try {
				ref._release();
			} catch (SystemException e) {
				// PASS
			}
			ref = null;
		}
		children.clear();

		orbSession.dispose();
	}

	public void registerDataReceiver(@NonNull final updateSRIOperations receiver) {
		// TODO: Throw class cast exception if wrong type
		//		type.getJavaType().cast(receiver); // check later

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
					DEBUG_PUSHPACKET.message("Got exception calling pushPacket({0}...) on {1}. Exception={2}. IOR={3}" , data.getClass().getCanonicalName(), child, e, ior);
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
					DEBUG_PUSHPACKET.message("Got exception calling pushPacket({0}...) on {1}. Exception={2}. IOR={3}" , data.getClass().getCanonicalName(), child, e, ior);
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
					DEBUG_PUSHPACKET.message("Got exception calling pushPacket({0}...) on {1}. Exception={2}. IOR={3}" , data.getClass().getCanonicalName(), child, e, ior);
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
					DEBUG_PUSHPACKET.message("Got exception calling pushPacket({0}...) on {1}. Exception={2}. IOR={3}" , data.getClass().getCanonicalName(), child, e, ior);
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
				if (type.isUnsigned()) {
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
					DEBUG_PUSHPACKET.message("Got exception calling pushPacket({0}...) on {1}. Exception={2}. IOR={3}" , data.getClass().getCanonicalName(), child, e, ior);
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
				if (type.isUnsigned()) {
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
					DEBUG_PUSHPACKET.message("Got exception calling pushPacket({0}...) on {1}. Exception={2}. IOR={3}" , data.getClass().getCanonicalName(), child, e, ior);
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
				if (type.isUnsigned()) {
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
					DEBUG_PUSHPACKET.message("Got exception calling pushPacket({0}...) on {1}. Exception={2}. IOR={3}" , data.getClass().getCanonicalName(), child, e, ior);
				}
			}
		}
	}

	private void log(int severity, String msg, Throwable t) {
		BulkIOUtilActivator.getDefault().getLog().log(new Status(severity, BulkIOUtilActivator.PLUGIN_ID, msg, t));
	}
}
