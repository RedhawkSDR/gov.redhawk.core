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
package nxm.redhawk.prim;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import nxm.redhawk.prim.data.BaseBulkIOReceiver;
import nxm.redhawk.prim.data.BulkIOReceiverFactory;
import nxm.sys.inc.Commandable;
import nxm.sys.lib.BaseFile;
import nxm.sys.lib.Convert;
import nxm.sys.lib.DataFile;
import nxm.sys.lib.FileName;
import nxm.sys.lib.Time;

import org.eclipse.core.runtime.CoreException;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import BULKIO.PortStatistics;
import BULKIO.PortUsageType;
import BULKIO.PrecisionUTCTime;
import BULKIO.StreamSRI;

/**
 * This class connects to the specified CORBA host and receives data and writes
 * it to a file. Generics are used to receive all types of data(Scalar/Complex,
 * Char/Short/Long/Float/Double). NOTE: The class can switch between receiving
 * Scalar and Complex, but not the actual data type. This functionality has not
 * been tested(the plot may not recover).
 */
public class corbareceiver extends CorbaPrimitive { //SUPPRESS CHECKSTYLE ClassName
	/**
	 * @since 8.0
	 */
	public static final String A_IDL = "IDL";
	/**
	 * @since 8.0
	 */
	public static final String NAME = "CORBARECEIVER";

	/**
	 * Framesize attribute
	 * @since 10.0
	 */
	public static final String A_FRAMESIZE = "FRAMESIZE";

	/**
	 * OVERRIDE FRAME SIZE attribute
	 * @since 10.0
	 */
	public static final String A_OVERRIDE_SRI_SUBSIZE = "OVERRIDE_SRI_SUBSIZE";
	/**
	 * @since 10.0
	 */
	//	public static final String A_WAIT = "/WAIT";
	/**
	 * @since 8.0
	 */
	public static final String A_FILE = "FILE";
	//	private static final int SLEEP_INTERVAL = 1000;

	/** the output file to write to */
	private DataFile outputFile = null;

	/** The configured SRI */
	private StreamSRI currentSri;
	private org.omg.CORBA.Object stub;
	private BaseBulkIOReceiver receiver;

	private int frameSizeAttribute;

	private FileName fileName;
	private boolean overrideSRISubSize;

	/**
	 * @since 8.0
	 */
	public static String encodeIDL(final String idl) {
		try {
			return URLEncoder.encode(idl, "UTF-8");
		} catch (final UnsupportedEncodingException e) {
			return idl;
		}
	}

	/**
	 * @since 8.0
	 */
	public static String decodeIDL(final String idl) {
		try {
			return URLDecoder.decode(idl, "UTF-8");
		} catch (final UnsupportedEncodingException e) {
			return idl;
		}

	}

	@Override
	public synchronized int open() {
		// MA is the argument list
		this.fileName = this.MA.getFileName(corbareceiver.A_FILE);
		final String encoded_idl = this.MA.getS(corbareceiver.A_IDL, null);
		this.frameSizeAttribute = this.MA.getL(corbareceiver.A_FRAMESIZE, 0);
		this.overrideSRISubSize = this.MA.getState(corbareceiver.A_OVERRIDE_SRI_SUBSIZE, false);
		final String idl = corbareceiver.decodeIDL(encoded_idl);

		if (this.receiver == null) {
			this.receiver = BulkIOReceiverFactory.createReceiver(this, idl);
		}

		int ret = super.open();

		// This check fails if we weren't able to connect to the ORB
		if ((ret == Commandable.NORMAL)) {

			// Check if we were able to connect to the port
			if (initConnections()) {
				ret = Commandable.NORMAL;
			} else {
				ret = Commandable.ABORT;
			}
		}

		//		final double maxWaitForSRI = MA.getD(corbareceiver.A_WAIT, SLEEP_INTERVAL);
		//		int numLoops = (int) (maxWaitForSRI / SLEEP_INTERVAL); // 0 or positive values will effect timeout
		//		if (maxWaitForSRI < 0) {
		//			numLoops = Integer.MAX_VALUE; // effectively infinity
		//		}
		//		while (this.currentSri == null && (numLoops-- > 0)) {
		//			try {
		//				wait(SLEEP_INTERVAL);
		//			} catch (InterruptedException e) {
		//				// PASS
		//			}
		//		}

		this.outputFile = createOutputFile(this.currentSri, this.receiver);
		this.outputFile.open();

		return ret;
	}

	@Override
	public synchronized int close() {
		if (this.state != Commandable.RESTART) {
			//Avoid hanging the UI if the CORBA call to the component fails to return
			super.close();
			if (this.outputFile != null) {
				this.outputFile.close();
				this.outputFile = null;
			}
			if (this.stub != null) {
				this.stub._release();
			}
			this.stub = null;
			this.currentSri = null;

			return Commandable.NORMAL;
		} else {
			if (this.outputFile != null) {
				this.outputFile.close();
				this.outputFile = null;
			}
			return Commandable.NORMAL;
		}
	}

	/**
	 * This initializes the ORB, gets the destination CF::Resource and connects
	 * to the specified port. This will retry MAX_RETRIES times to complete the
	 * connection sequence.
	 * @since 8.0
	 */
	protected boolean initConnections() {
		if (this.stub == null) {
			try {
				this.stub = getPOA().servant_to_reference(this.receiver.toServant());
			} catch (final ServantNotActive e) {
				this.M.error(e);
				return false;
			} catch (final WrongPolicy e) {
				this.M.error(e);
				return false;
			} catch (CoreException e) {
				this.M.error(e);
				return false;
			}
			return connectPort(this.stub);
		} else {
			return true;
		}
	}

	/**
	 * This will determine if we should continue processing the packet. To
	 * process, the endOfStream must be false and we must have previously
	 * received SRI.
	 *
	 * @param endOfStream true if the received stream has closed
	 * @return true if we should process the data
	 */
	private final boolean shouldProcessPacket(final boolean endOfStream, final byte type) {
		boolean status = true;
		if (endOfStream) {
			status = true;
		} else if (this.outputFile == null) {
			status = false;
		}

		return status;
	}

	/**
	 * This receives the SRI and updates or modifies the output pipe.
	 *
	 * @param sri the SRI containing information about the incoming data
	 * @since 8.0
	 */
	public void pushSRI(final StreamSRI sri) {
		setStreamSri(sri);
	}

	private DataFile createDefaultOutputFile() {
		final String format = "S" + receiver.getType();
		DataFile newOutputFile = new DataFile(this.MA.cmd, fileName, "2000", format, BaseFile.OUTPUT);
		newOutputFile.setFrameSize(frameSizeAttribute); // <-- this call will not convert 1000 to 2000
		return newOutputFile;
	}

	private DataFile createOutputFile(final StreamSRI sri, final BaseBulkIOReceiver receiver) {
		if (sri == null) {
			return createDefaultOutputFile();
		}

		int outputFramesize = sri.subsize;
		if (outputFramesize <= 0) {
			outputFramesize = this.frameSizeAttribute;
		} else if (this.overrideSRISubSize && this.frameSizeAttribute > 0) {
			outputFramesize = this.frameSizeAttribute;
		}

		outputFramesize = Math.max(outputFramesize, 1);

		final String fileType = (outputFramesize > 0) ? "2000" : "1000"; // SUPPRESS CHECKSTYLE AvoidInline
		final String format = ((sri.mode == 0) ? "S" : "C") + receiver.getType(); // SUPPRESS CHECKSTYLE AvoidInline
		final DataFile newOutputFile = new DataFile(this.MA.cmd, fileName, fileType, format, BaseFile.OUTPUT);

		if (outputFramesize > 0) {
			newOutputFile.setFrameSize(outputFramesize);
		}

		newOutputFile.setXStart(sri.xstart);
		if (sri.xdelta > 0) {
			newOutputFile.setXDelta(sri.xdelta);
		} else {
			newOutputFile.setXDelta(1);
		}
		newOutputFile.setXUnits(sri.xunits);

		newOutputFile.setYStart(sri.ystart);
		if (sri.ydelta > 0) {
			newOutputFile.setYDelta(sri.ydelta);
		} else {
			newOutputFile.setYDelta(1);
		}
		newOutputFile.setYUnits(sri.yunits);

		return newOutputFile;
	}

	private synchronized void setStreamSri(final StreamSRI sri) {
		if (!corbareceiver.isSRIChanged(sri, this.currentSri)) {
			return;
		}
		this.currentSri = sri;
		sendMessage("STREAMSRI", 1, this.currentSri);
		if (state == Commandable.PROCESS) {
			doRestart();
		} else if (state == Commandable.OPEN) {
			notifyAll();
		}
	}

	private synchronized void doRestart() {
		setState(Commandable.RESTART);
	}

	private static boolean isSRIChanged(final StreamSRI sri, final StreamSRI lastSRI) {
		boolean status = (lastSRI == null);
		if (lastSRI != null) {
			status |= (lastSRI.hversion != sri.hversion);
			status |= (lastSRI.mode != sri.mode);
			status |= !lastSRI.streamID.equals(sri.streamID);
			status |= (lastSRI.subsize != sri.subsize);
			status |= (lastSRI.xdelta != sri.xdelta);
			status |= (lastSRI.xstart != sri.xstart);
			status |= (lastSRI.xunits != sri.xunits);
			status |= (lastSRI.ydelta != sri.ydelta);
			status |= (lastSRI.ystart != sri.ystart);
			status |= (lastSRI.yunits != sri.yunits);
		}
		return status;
	}

	/**
	 * No Precision time
	 * @param dataArray
	 * @param size
	 * @param type
	 * @param endOfStream
	 * @deprecated
	 */
	@Deprecated
	public void write(final Object dataArray, final int size, final byte type, final boolean endOfStream) {
		write(dataArray, size, type, endOfStream, null);
	}

	/**
	 * This plots an arbitrary typed array of data. Type checking is performed
	 * by System.arraycopy(). The type of 'list' must match 'type'.
	 *
	 * @param dataArray the array of data to plot
	 * @param size the length of the data in the array
	 * @param type the NeXtMidas type of the data to plot (eg. Data.FLOAT)
	 * @since 8.0
	 */
	public synchronized void write(final Object dataArray, final int size, final byte type, final boolean endOfStream, PrecisionUTCTime time) {
		if (!(this.state == Commandable.PROCESS) || isStateChanged() || this.currentSri == null || !shouldProcessPacket(endOfStream, type)) {
			return;
		}

		final DataFile localOutputFile = this.outputFile;
		if (localOutputFile == null) {
			return;
		}

		final Time midasTime;
		if (time != null) {
			midasTime = new Time(time.twsec + Time.J1970TOJ1950, time.tfsec);
		} else {
			midasTime = null;
		}

		outputFile.setTimeAt(midasTime);
		int bufferSize =  outputFile.bpa * size;
		byte[] byteBuffer = new byte[bufferSize];
		Convert.ja2bb(dataArray, 0, type, byteBuffer, 0, outputFile.dataType, size);
		outputFile.write(byteBuffer, 0, byteBuffer.length);
		return;
	}

	/**
	 * @since 8.0
	 */
	public PortUsageType state() {
		return PortUsageType.ACTIVE;
	}

	/**
	 * @since 8.0
	 */
	public PortStatistics statistics() {
		return new PortStatistics();
	}

	/**
	 * @since 8.0
	 */
	public StreamSRI[] activeSRIs() {
		if (this.currentSri == null) {
			return new StreamSRI[0];
		} else {
			return new StreamSRI[] { this.currentSri };
		}
	}
}
