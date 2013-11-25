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

import gov.redhawk.bulkio.util.BulkIOType;
import gov.redhawk.bulkio.util.BulkIOUtilActivator;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import nxm.redhawk.prim.data.BulkIOReceiver;
import nxm.sys.inc.Commandable;
import nxm.sys.lib.BaseFile;
import nxm.sys.lib.Convert;
import nxm.sys.lib.Data;
import nxm.sys.lib.DataFile;
import nxm.sys.lib.FileName;
import nxm.sys.lib.MidasException;
import nxm.sys.lib.Time;

import org.eclipse.core.runtime.CoreException;

import BULKIO.PrecisionUTCTime;
import BULKIO.StreamSRI;

/**
 * This class connects to the specified CORBA host and receives data and writes
 * it to a file. Generics are used to receive all types of data(Scalar/Complex,
 * Char/Byte(octet),Short/Integer/Long/Float/Double).
 * @since 10.1
 */
public class corbareceiver2 extends CorbaPrimitive implements IMidasDataWriter { //SUPPRESS CHECKSTYLE ClassName
	/** name of FILE= argument. */
	public static final String A_FILE = "FILE";
	/** name of IOR= argument. */
	public static final String A_IOR = "IOR";
	/** Name of IDL= argument. */
	public static final String A_IDL = "IDL";
	/** Name of STREAMID= argument. */
	public static final String A_STREAM_ID = "STREAMID";

	/**
	 * Name of switch to set to false to NOT block pushPacket/write(..) when pipe doesn't have enough room,
	 * which will cause that pushPacket data to get drop
	 */
	public static final String SW_BLOCKING = "/BLOCKING";

	/**
	 * Name of switch to grab number of seconds to wait for SRI during open()
	 */
	public static final String SW_WAIT = "/WAIT";
	
	/** treat dataOctet as 8-bit unsigned integer (this will upcast format type to 16-bit signed integer to hold value).  
	 * @since 10.1
	 */
	public static final String SW_TREAT_OCTET_AS_UNSIGNED = "/UNSIGNEDOCTET";
	
	/** sleep interval (milliseconds) for {@link #SW_WAIT}. */
	private static final int SLEEP_INTERVAL = 100;

	/** the output file to write to */
	private volatile DataFile outputFile = null;
	private FileName fileName;

	private String idl;
	
	/** The configured SRI */
	private volatile StreamSRI currentSri;
	private BulkIOReceiver receiver;
	
	/** the streamID to only receive data from (null for all streams). */
	private String streamID;

	// === blocking/non-blocking options and constants ===
	/** blocking option when output pipe is full. */
	private boolean blocking;

	/** override SRI's sample rate (1/xdelta for 1000, 1/ydelta for 2000). */
	/** custom sample rate to use for output file/pipe. zero for none. */
	private double sampleRate = 0.0;
	private boolean connected;
	private String portIor;
	private BulkIOType bulkioType;

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
		this.fileName = MA.getFileName(A_FILE);
		this.portIor  = MA.getCS(A_IOR);
		final String encoded_idl = MA.getCS(A_IDL);
		this.idl      = decodeIDL(encoded_idl);
		this.streamID = MA.getCS(A_STREAM_ID, null);
		this.blocking = MA.getState(SW_BLOCKING, false);
		boolean unsignedOctet = MA.getState(SW_TREAT_OCTET_AS_UNSIGNED); 

		BulkIOType newType = BulkIOType.getType(this.idl);
		this.bulkioType = newType;
		
		if (this.receiver == null) {
			this.receiver = new BulkIOReceiver(this, newType, unsignedOctet);
		}

		// Check if we were able to connect to the Port
		if (!initConnections()) {
			return ABORT; // since we are unable to connect to the Port
		}

		final double maxWaitForSRI = MA.getD(SW_WAIT, 1000);   // in milliseconds
		int numLoops = (int) (maxWaitForSRI / SLEEP_INTERVAL); // 0 or positive values will effect timeout
		if (maxWaitForSRI < 0) {
			numLoops = Integer.MAX_VALUE; // effectively infinity
		}
		while (this.currentSri == null && (numLoops-- > 0)) {
			try {
				wait(SLEEP_INTERVAL);
			} catch (InterruptedException e) {
				break;
			}
		}

		this.outputFile = createOutputFile(this.currentSri, this.receiver);
		this.outputFile.open();

		return NORMAL;
	}

	@Override
	public synchronized int close() {
		DataFile localOutputFile = this.outputFile;
		this.outputFile = null;
		if (localOutputFile != null) {
			localOutputFile.close();
		}
		if (this.state != Commandable.RESTART) {
			//Avoid hanging the UI if the CORBA call to the component fails to return
			super.close();
			this.currentSri = null;
		}
		return Commandable.NORMAL;
	}

	/**
	 * @return false if connection has not been or failed to initialize
	 */
	private boolean initConnections() {
		if (!connected) {
			try {
				String ior = portIor;
				BulkIOType type = bulkioType;
				BulkIOReceiver receiver2 = receiver;
				if (ior != null && !ior.trim().isEmpty() && type != null && receiver2 != null) {
					BulkIOUtilActivator.getBulkIOPortConnectionManager().connect(ior, type, receiver2);
				} else {
					return false;
				}
				connected = true;
			} catch (CoreException e) {
				throw new MidasException("Failed to connect port", e);
			}
		}
		return connected;
	}

	@Override
	protected void shutdown() {
		final String ior = portIor;
		final BulkIOType type = bulkioType;
		final BulkIOReceiver receiver2 = receiver;
		if (ior != null && !ior.trim().isEmpty() && type != null && receiver2 != null) {
			BulkIOUtilActivator.getBulkIOPortConnectionManager().disconnect(ior, type, receiver2);
		}
		connected = false;
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
		if (this.state != PROCESS || isStateChanged() || this.currentSri == null || this.outputFile == null) {
			return false;
		}
		return true;
	}

	private DataFile createDefaultOutputFile() {
		final String format = "S" + receiver.getMidasType();
		DataFile newOutputFile = new DataFile(this, fileName, "1000", format, BaseFile.OUTPUT);
		return newOutputFile;
	}

	private DataFile createOutputFile(final StreamSRI sri, final BulkIOReceiver receiver) {
		if (sri == null) {
			return createDefaultOutputFile();
		}

		boolean noSubSizeFromSRI = (sri.subsize <= 0); // true for 1000 stream, false for 2000 stream

		final String fileType = (noSubSizeFromSRI) ?  "1000" : "2000";
		final String format = ((sri.mode == 0) ? "S" : "C") + receiver.getMidasType(); // SUPPRESS CHECKSTYLE AvoidInline
		final DataFile newOutputFile = new DataFile(MA.cmd, fileName, fileType, format, BaseFile.OUTPUT);

		final double sampleDelta = 1 / getSampleRateFor(this.sampleRate, sri); // zero will be ignored (not used)

		double xdelta;
		final boolean overrideSampleDelta = (this.sampleRate > 0);
		if (overrideSampleDelta && noSubSizeFromSRI) {
			xdelta = sampleDelta;
		} else if (sri.xdelta > 0) {
			xdelta = sri.xdelta;
		} else {
			xdelta = 1.0;
		}
		newOutputFile.setXStart(sri.xstart);
		newOutputFile.setXDelta(xdelta);
		newOutputFile.setXUnits(sri.xunits);

		double ydelta;
		if (overrideSampleDelta && !noSubSizeFromSRI) {
			ydelta = sampleDelta;
		} else if (sri.ydelta > 0) {
			ydelta = sri.ydelta;
		} else {
			ydelta = 1.0;
		}
		newOutputFile.setYStart(sri.ystart);
		newOutputFile.setYDelta(ydelta);
		newOutputFile.setYUnits(sri.yunits);

		return newOutputFile;
	}

	/**
	 * @noreference This method is not intended to be referenced by clients.
	 */
	public synchronized void setStreamSri(String streamID, StreamSRI oldSri, StreamSRI newSri) {
		if (this.streamID == null || this.streamID.equals(streamID)) {
			this.currentSri = newSri;
			if (state == PROCESS) {
				if (oldSri != null) {
					if (oldSri.mode != newSri.mode || oldSri.subsize != newSri.subsize
						|| oldSri.xdelta != newSri.xdelta ||  oldSri.xstart != newSri.xstart || oldSri.xunits !=  newSri.xunits 
						|| oldSri.ydelta != newSri.ydelta ||  oldSri.ystart != newSri.ystart || oldSri.yunits !=  newSri.yunits) {
						doRestart(); // only restart of one of above SRI field changes
					}
				}
			} else if (state == OPEN) {
				notifyAll();
			}
		}
	}

	private synchronized void doRestart() {
		setState(RESTART);
	}

	/**
	 * This plots an arbitrary typed array of data. Type checking is performed
	 * by System.arraycopy(). The type of 'list' must match 'type'.
	 *
	 * @param dataArray the array of data to plot
	 * @param size the length of the data in the array
	 * @param type the NeXtMidas type of the data to plot (eg. Data.FLOAT)
	 */
	public void write(final Object dataArray, final int size, final byte type, final boolean endOfStream, PrecisionUTCTime time) {
		if (!shouldProcessPacket(endOfStream, type)) {
			return;
		}

		final DataFile localOutputFile = this.outputFile;
		if (localOutputFile == null) {
			return;
		}

		final int bufferSize = Data.getBPS(type) * size; // in bytes
		if (!blocking) { // non-blocking option enabled
			if (localOutputFile.getResource().avail() < bufferSize) {
				// Time.sleep(0.01); // <-- provide slight back-pressure so we don't spin CPU for Component that does NOT throttle data
				return; // drop packet since write would block
			}
		}

		final Time midasTime;
		if (time != null) {
			midasTime = new Time(time.twsec + Time.J1970TOJ1950, time.tfsec);
		} else {
			midasTime = null;
		}
		localOutputFile.setTimeAt(midasTime);

		byte[] byteBuffer = new byte[bufferSize];
		Convert.ja2bb(dataArray, 0, type, byteBuffer, 0, localOutputFile.dataType, size);
		localOutputFile.write(byteBuffer, 0, byteBuffer.length);
		return;
	}
	
	/**
	 * @return the current custom sample rate to override in SRI.
	 */
	public double getSampleRate() {
		return sampleRate;
	}

	private static double getSampleRateFor(double sr, StreamSRI sri) {
		if (sr > 0) {
			return sr;
		}
		final double sampleDelta; // zero will be ignored (not used)
		if (sri.subsize > 1) { // type 2000 stream
			if (sri.ydelta > 0) {
				sampleDelta = 1 / sri.ydelta;
			} else {
				sampleDelta = 1.0;
			}
		} else { // type 1000 stream
				if (sri.xdelta > 0) {
					sampleDelta = 1 / sri.xdelta;
				} else {
					sampleDelta = 1.0;
				}
		}
		return sampleDelta;
	}

	/**
	 * @param newSRate the custom sample rate to override in SRI
	 *                 (1/xdelta for 1000 stream; 1/ydelta for 2000 stream).
	 */
	public void setSampleRate(double newSRate) {
		if (newSRate <= 0) {
			newSRate = -1;
		}
		double oldValue = getSampleRateFor(this.sampleRate, currentSri);
		if (this.sampleRate != newSRate) {
			this.sampleRate = newSRate;
			double newValue = getSampleRateFor(this.sampleRate, currentSri);
			if (oldValue != newValue) {
				doRestart(); // restart since specified sample rate is different than in SRI
			}
		}
	}

	/**
	 * @return the blocking option for when output pipe is full
	 */
	public boolean isBlocking() {
		return blocking;
	}

	/**
	 * @param blocking the blocking option to set for when output pipe is full
	 */
	public void setBlocking(boolean blocking) {
		this.blocking = blocking;
	}

}
