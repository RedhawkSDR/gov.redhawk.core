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
package gov.redhawk.bulkio.orb.omni.internal;

import gov.redhawk.bulkio.util.BulkIOType;
import gov.redhawk.bulkio.util.IPortFactory;
import gov.redhawk.bulkio.util.PortReference;
import gov.redhawk.sca.util.OrbSession;
import omnijni.Servant;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.omg.CORBA.SystemException;

import BULKIO.PortStatistics;
import BULKIO.PortUsageType;
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
import bulkio.InCharPort;
import bulkio.InDoublePort;
import bulkio.InFloatPort;
import bulkio.InInt16Port;
import bulkio.InInt32Port;
import bulkio.InInt64Port;
import bulkio.InUInt16Port;
import bulkio.InUInt32Port;
import bulkio.InUInt64Port;
import bulkio.InUInt8Port;

/**
 * 
 */
public class PortFactory implements IPortFactory {

	private static final OrbSession SESSION = OrbSession.createSession();

	public PortReference connect(final String connectionID, final String portIor, BulkIOType type, final updateSRIOperations handler) throws CoreException {
		org.omg.CORBA.Object portRef = SESSION.getOrb().string_to_object(portIor);
		final Port port = PortHelper.narrow(portRef);
		switch (type) {
		case CHAR:
			return connect(connectionID, port, (dataCharOperations) handler);
		case DOUBLE:
			return connect(connectionID, port, (dataDoubleOperations) handler);
		case FLOAT:
			return connect(connectionID, port, (dataFloatOperations) handler);
		case LONG:
			return connect(connectionID, port, (dataLongOperations) handler);
		case LONG_LONG:
			return connect(connectionID, port, (dataLongLongOperations) handler);
		case OCTET:
			return connect(connectionID, port, (dataOctetOperations) handler);
		case SHORT:
			return connect(connectionID, port, (dataShortOperations) handler);
		case ULONG:
			return connect(connectionID, port, (dataUlongOperations) handler);
		case ULONG_LONG:
			return connect(connectionID, port, (dataUlongLongOperations) handler);
		case USHORT:
			return connect(connectionID, port, (dataUshortOperations) handler);
		default:
			throw new IllegalStateException("Unhandled BulkIO connect type OmniOrb : " + type);
		}
	}

	/**
	 * @param connectionID
	 * @param port
	 * @param handler
	 * @return
	 */
	private PortReference connect(final String connectionID, final Port port, final dataUshortOperations handler) throws CoreException {
		final InUInt16Port inPort = new InUInt16Port(connectionID) {
			public void pushPacket(short[] data, PrecisionUTCTime time, boolean eos, String streamID) {
				handler.pushPacket(data, time, eos, streamID);
			}

			public void pushSRI(BULKIO.StreamSRI header) {
				handler.pushSRI(header);
			}

			@Override
			public StreamSRI[] activeSRIs() {
				return handler.activeSRIs();
			}

			@Override
			public PortUsageType state() {
				return handler.state();
			}

			@Override
			public PortStatistics statistics() {
				return handler.statistics();
			}
		};
		return createAndConnectHandler(connectionID, port, inPort);
	}

	/**
	 * @param connectionID
	 * @param port
	 * @param handler
	 * @return
	 */
	private PortReference connect(final String connectionID, final Port port, final dataUlongLongOperations handler) throws CoreException {
		final InUInt64Port inPort = new InUInt64Port(connectionID) {
			public void pushPacket(long[] data, PrecisionUTCTime time, boolean eos, String streamID) {
				handler.pushPacket(data, time, eos, streamID);
			}

			public void pushSRI(BULKIO.StreamSRI header) {
				handler.pushSRI(header);
			}

			@Override
			public StreamSRI[] activeSRIs() {
				return handler.activeSRIs();
			}

			@Override
			public PortUsageType state() {
				return handler.state();
			}

			@Override
			public PortStatistics statistics() {
				return handler.statistics();
			}
		};
		return createAndConnectHandler(connectionID, port, inPort);
	}

	/**
	 * @param connectionID
	 * @param port
	 * @param handler
	 * @return
	 */
	private PortReference connect(final String connectionID, final Port port, final dataUlongOperations handler) throws CoreException {
		final InUInt32Port inPort = new InUInt32Port(connectionID) {
			public void pushPacket(int[] data, PrecisionUTCTime time, boolean eos, String streamID) {
				handler.pushPacket(data, time, eos, streamID);
			}

			public void pushSRI(BULKIO.StreamSRI header) {
				handler.pushSRI(header);
			}

			@Override
			public StreamSRI[] activeSRIs() {
				return handler.activeSRIs();
			}

			@Override
			public PortUsageType state() {
				return handler.state();
			}

			@Override
			public PortStatistics statistics() {
				return handler.statistics();
			}
		};
		return createAndConnectHandler(connectionID, port, inPort);
	}

	/**
	 * @param connectionID
	 * @param port
	 * @param handler
	 * @return
	 */
	private PortReference connect(final String connectionID, final Port port, final dataShortOperations handler) throws CoreException {
		final InInt16Port inPort = new InInt16Port(connectionID) {
			public void pushPacket(short[] data, PrecisionUTCTime time, boolean eos, String streamID) {
				handler.pushPacket(data, time, eos, streamID);
			}

			public void pushSRI(BULKIO.StreamSRI header) {
				handler.pushSRI(header);
			}

			@Override
			public StreamSRI[] activeSRIs() {
				return handler.activeSRIs();
			}

			@Override
			public PortUsageType state() {
				return handler.state();
			}

			@Override
			public PortStatistics statistics() {
				return handler.statistics();
			}
		};
		return createAndConnectHandler(connectionID, port, inPort);
	}

	/**
	 * @param connectionID
	 * @param port
	 * @param handler
	 * @return
	 */
	private PortReference connect(final String connectionID, final Port port, final dataOctetOperations handler) throws CoreException {
		final InUInt8Port inPort = new InUInt8Port(connectionID) {
			public void pushPacket(byte[] data, PrecisionUTCTime time, boolean eos, String streamID) {
				handler.pushPacket(data, time, eos, streamID);
			}

			public void pushSRI(BULKIO.StreamSRI header) {
				handler.pushSRI(header);
			}

			@Override
			public StreamSRI[] activeSRIs() {
				return handler.activeSRIs();
			}

			@Override
			public PortUsageType state() {
				return handler.state();
			}

			@Override
			public PortStatistics statistics() {
				return handler.statistics();
			}
		};
		return createAndConnectHandler(connectionID, port, inPort);
	}

	/**
	 * @param connectionID
	 * @param port
	 * @param handler
	 * @return
	 */
	private PortReference connect(final String connectionID, final Port port, final dataLongLongOperations handler) throws CoreException {
		final InInt64Port inPort = new InInt64Port(connectionID) {
			public void pushPacket(long[] data, PrecisionUTCTime time, boolean eos, String streamID) {
				handler.pushPacket(data, time, eos, streamID);
			}

			public void pushSRI(BULKIO.StreamSRI header) {
				handler.pushSRI(header);
			}

			@Override
			public StreamSRI[] activeSRIs() {
				return handler.activeSRIs();
			}

			@Override
			public PortUsageType state() {
				return handler.state();
			}

			@Override
			public PortStatistics statistics() {
				return handler.statistics();
			}
		};
		return createAndConnectHandler(connectionID, port, inPort);
	}

	/**
	 * @param connectionID
	 * @param port
	 * @param handler
	 * @return
	 */
	private PortReference connect(final String connectionID, final Port port, final dataLongOperations handler) throws CoreException {
		final InInt32Port inPort = new InInt32Port(connectionID) {
			public void pushPacket(int[] data, PrecisionUTCTime time, boolean eos, String streamID) {
				handler.pushPacket(data, time, eos, streamID);
			}

			public void pushSRI(BULKIO.StreamSRI header) {
				handler.pushSRI(header);
			}

			@Override
			public StreamSRI[] activeSRIs() {
				return handler.activeSRIs();
			}

			@Override
			public PortUsageType state() {
				return handler.state();
			}

			@Override
			public PortStatistics statistics() {
				return handler.statistics();
			}
		};
		return createAndConnectHandler(connectionID, port, inPort);
	}

	/**
	 * @param connectionID
	 * @param port
	 * @param handler
	 * @return
	 */
	private PortReference connect(final String connectionID, final Port port, final dataFloatOperations handler) throws CoreException {
		final InFloatPort inPort = new InFloatPort(connectionID) {
			public void pushPacket(float[] data, PrecisionUTCTime time, boolean eos, String streamID) {
				handler.pushPacket(data, time, eos, streamID);
			}

			public void pushSRI(BULKIO.StreamSRI header) {
				handler.pushSRI(header);
			}

			@Override
			public StreamSRI[] activeSRIs() {
				return handler.activeSRIs();
			}

			@Override
			public PortUsageType state() {
				return handler.state();
			}

			@Override
			public PortStatistics statistics() {
				return handler.statistics();
			}
		};
		return createAndConnectHandler(connectionID, port, inPort);
	}

	/**
	 * @param connectionID
	 * @param port
	 * @param handler
	 * @return
	 */
	private PortReference connect(final String connectionID, final Port port, final dataDoubleOperations handler) throws CoreException {
		final InDoublePort inPort = new InDoublePort(connectionID) {
			public void pushPacket(double[] data, PrecisionUTCTime time, boolean eos, String streamID) {
				handler.pushPacket(data, time, eos, streamID);
			}

			public void pushSRI(StreamSRI header) {
				handler.pushSRI(header);
			}

			@Override
			public StreamSRI[] activeSRIs() {
				return handler.activeSRIs();
			}

			@Override
			public PortUsageType state() {
				return handler.state();
			}

			@Override
			public PortStatistics statistics() {
				return handler.statistics();
			}
		};
		return createAndConnectHandler(connectionID, port, inPort);
	}

	/**
	 * @param connectionID
	 * @param port
	 * @param handler
	 * @return
	 */
	private PortReference connect(final String connectionID, final Port port, final dataCharOperations handler) throws CoreException {
		final InCharPort inPort = new InCharPort(connectionID) {
			public void pushPacket(char[] data, PrecisionUTCTime time, boolean eos, String streamID) {
				handler.pushPacket(data, time, eos, streamID);
			}

			public void pushSRI(BULKIO.StreamSRI header) {
				handler.pushSRI(header);
			}

			@Override
			public StreamSRI[] activeSRIs() {
				return handler.activeSRIs();
			}

			@Override
			public PortUsageType state() {
				return handler.state();
			}

			@Override
			public PortStatistics statistics() {
				return handler.statistics();
			}
		};
		return createAndConnectHandler(connectionID, port, inPort);
	}


	private PortReference createAndConnectHandler(final String connectionID, final Port port, final Servant inPort) throws CoreException {
		try {
			port.connectPort(inPort._this_object(SESSION.getOrb()), connectionID);
			return new PortReference() {
				@Override
				public void dispose() {
					try {
						port.disconnectPort(connectionID);
					} catch (InvalidPort e) {
						// PASS
					} catch (SystemException e) {
						// PASS
					}
					inPort._deactivate();
				}

			};
		} catch (InvalidPort e) {
			throw new CoreException(new Status(Status.ERROR, "gov.redhaw.bulkio.orb.omni", "Failed to connect BulkIO Port ", e));
		} catch (OccupiedPort e) {
			throw new CoreException(new Status(Status.ERROR, "gov.redhaw.bulkio.orb.omni", "Failed to connect BulkIO Port ", e));
		} catch (SystemException e) {
			throw new CoreException(new Status(Status.ERROR, "gov.redhaw.bulkio.orb.omni", "Failed to connect BulkIO Port ", e));
		}

	}

}
