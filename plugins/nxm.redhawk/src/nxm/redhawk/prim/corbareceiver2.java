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
import gov.redhawk.sca.util.Debug;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import nxm.redhawk.lib.RedhawkOptActivator;
import nxm.redhawk.prim.data.BulkIOReceiver;
import nxm.sys.inc.Commandable;
import nxm.sys.lib.BaseFile;
import nxm.sys.lib.Convert;
import nxm.sys.lib.Data;
import nxm.sys.lib.DataFile;
import nxm.sys.lib.FileName;
import nxm.sys.lib.Midas;
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

	/** Name of switch to grab number of seconds to wait for SRI during open(). */
	public static final String SW_WAIT = "/WAIT";
	
	/** Name of switch to treat dataOctet as 8-bit unsigned integer (this will upcast format type to 16-bit signed integer to hold value). */
	public static final String SW_TREAT_OCTET_AS_UNSIGNED = "/UNSIGNEDOCTET";
	
	/** Name of switch to specify desired connection ID. */
	public static final String SW_CONNECTION_ID = "/CONNECTIONID";
	
	/**
	 * Name of switch to enable/disable increasing/growing output file's pipe size when
	 * incoming data packet size is larger than it.
	 */
	public static final String SW_GROW_PIPE = "/GROWPIPE";

	/** Name of switch to set pipe size multiplier based on incoming data packet size (when larger than current pipe size). */
	public static final String SW_PS_MULTIPLIER = "/PSMULT";

	private static final Debug TRACE_LOGGER = new Debug(RedhawkOptActivator.ID, corbareceiver2.class.getSimpleName());

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
	private String streamId;

	/** blocking option when output pipe is full. */
	private boolean blocking;

	/** override SRI's sample rate (1/xdelta for 1000, 1/ydelta for 2000). */
	/** custom sample rate to use for output file/pipe. zero for none. */
	private double sampleRate = 0.0;
	private boolean connected;
	private String portIor;
	private BulkIOType bulkioType;
	/** custom Port connection ID to use (when not null). */
	private String connectionId;

	/** allow increasing the pipe size when pushPacket data is larger than it. */
	private boolean canGrowPipe;
	/** multiplier of data packet size for setting pipe size (on {@link #outputFile} if it is less than data size. */
	private int pipeSizeMultiplier;
	private int newPipeSize;

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
		this.streamId = MA.getCS(A_STREAM_ID, null);
		this.blocking = MA.getState(SW_BLOCKING, false);
		boolean unsignedOctet = MA.getState(SW_TREAT_OCTET_AS_UNSIGNED);
		this.connectionId = MA.getCS(SW_CONNECTION_ID, null);
		this.canGrowPipe = MA.getState(SW_GROW_PIPE, true);
		setPipeSizeMultiplier(MA.getL(SW_PS_MULTIPLIER, 4));

		BulkIOType newType = BulkIOType.getType(this.idl);
		this.bulkioType = newType;
		
		if (this.receiver == null) {
			this.receiver = new BulkIOReceiver(this, newType, unsignedOctet, this.streamId);
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

		TRACE_LOGGER.exitingMethod();
		return NORMAL;
	}

	@Override
	public int process() {
		if (newPipeSize > 0) {
			setPipeSize(newPipeSize);
			newPipeSize = 0;
		}
		return NOOP;
	}

	@Override
	public synchronized int close() {
		TRACE_LOGGER.enteringMethod();
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
				String ior = this.portIor;
				BulkIOType type = this.bulkioType;
				BulkIOReceiver receiver2 = this.receiver;
				String desiredConnID = this.connectionId;
				if (ior != null && !ior.trim().isEmpty() && type != null && receiver2 != null) {
					BulkIOUtilActivator.getBulkIOPortConnectionManager().connect(ior, type, receiver2, desiredConnID);
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
			BulkIOUtilActivator.getBulkIOPortConnectionManager().disconnect(ior, type, receiver2, this.connectionId);
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

		final String fileType = (noSubSizeFromSRI) ?  "1000" : "2000"; // SUPPRESS CHECKSTYLE AvoidInline
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
		
		if (!noSubSizeFromSRI) {
			newOutputFile.setFrameSize(sri.subsize);
		}

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

	private static boolean isRestartNeed4SriChange(final StreamSRI sri, final StreamSRI lastSRI) {
		boolean shouldRestart = false;
		if (lastSRI != null) {
			shouldRestart |= (lastSRI.mode    != sri.mode);
			shouldRestart |= (lastSRI.subsize != sri.subsize);
			shouldRestart |= (lastSRI.xdelta  != sri.xdelta);
			shouldRestart |= (lastSRI.xstart  != sri.xstart);
			shouldRestart |= (lastSRI.xunits  != sri.xunits);
			shouldRestart |= (lastSRI.ydelta  != sri.ydelta);
			shouldRestart |= (lastSRI.ystart  != sri.ystart);
			shouldRestart |= (lastSRI.yunits  != sri.yunits);
			// blocking, hversion, keywords, and streamID changes do not require a restart
		} else {
			shouldRestart = true;
		}
		return shouldRestart;
	}

	/**
	 * @noreference This method is not intended to be referenced by clients.
	 */
	public synchronized void setStreamSri(String streamID, StreamSRI oldSri, StreamSRI newSri) {
		TRACE_LOGGER.message("{0}: setStreamSri to {1} id={2} blocking={3}", getID(), newSri, newSri.streamID, newSri.blocking);
		if (this.streamId == null || this.streamId.equals(streamID)) {
			this.currentSri = newSri;
			if (state == PROCESS) {
				if (isRestartNeed4SriChange(newSri, oldSri)) {
					doRestart();
				}
			} else if (state == OPEN) {
				notifyAll();
			}
		}
	}

	private synchronized void doRestart() {
		TRACE_LOGGER.message("{0}: scheduling restart... {1}",  getID(), this.outputFile);
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
	public void write(final Object dataArray, final int size, final byte type, final boolean endOfStream, PrecisionUTCTime time, final String streamId) {
		if (!shouldProcessPacket(endOfStream, type)) {
			return;
		}

		final DataFile localOutputFile = this.outputFile;
		if (localOutputFile == null) {
			return;
		}

		final int bufferSize = Data.getBPS(type) * size;  // size of data to write in bytes
		if (this.canGrowPipe && localOutputFile.getPipeSize() < bufferSize) { // increase pipe size if allowed (ticket #1554)
			if (TRACE_LOGGER.enabled) {
				TRACE_LOGGER.message("{0}: scheduling pipe size increase from {1} to ({2} x {3}) bytes for {4}",
					getID(), localOutputFile.getPipeSize(), this.pipeSizeMultiplier, bufferSize, this.outputFile);
			}
			this.newPipeSize = bufferSize * this.pipeSizeMultiplier; // this change will be picked up in the process() method
		}
		if (!blocking) {                      // === non-blocking option enabled ===
			if (M.pipeMode == Midas.PPAUSE) { // 1. pipe is PAUSED
				TRACE_LOGGER.message("Dropping packet b/c pipe is PAUSED");
				return;                       // 1b. drop packet, as write would block
			}
			if (!this.canGrowPipe && localOutputFile.getPipeSize() < bufferSize) {
				// PASS - let packet through even though this might block, otherwise no data will ever be written
			} else if (localOutputFile.getResource().avail() < bufferSize) { // 2. avail buffer is less than data/packet size
				// Time.sleep(0.01); // <-- provide slight back-pressure so we don't spin CPU for Component that does NOT throttle data
				TRACE_LOGGER.message("Dropping packet b/c not enough avail buffer without blocking write");
				return; // 2b. drop packet since write would block
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
	 * @param newSRate the custom sample rate to override in SRI (zero for none);
	 *                 (1/xdelta for 1000 stream; 1/ydelta for 2000 stream).
	 */
	public void setSampleRate(double newSRate) {
		if (newSRate <= 0) {
			newSRate = 0;
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

	/**
	 * @since 10.1
	 */
	public boolean isCanGrowPipe() {
		return canGrowPipe;
	}

	/**
	 * @since 10.1
	 */
	public void setCanGrowPipe(boolean newValue) {
		this.canGrowPipe = newValue;
		MA.put(SW_GROW_PIPE, "" + newValue);
	}

	public int getPipeSizeMultiplier() {
		return pipeSizeMultiplier;
	}

	/**
	 * @param newValue new pipe size multiplier (MUST be >= 1)
	 */
	public void setPipeSizeMultiplier(int newValue) {
		if (newValue <= 0) {
			throw new IllegalArgumentException("data size to pipe size multiplier must be greater than 0");
		}
		this.pipeSizeMultiplier = newValue;
		MA.put(SW_PS_MULTIPLIER, "" + newValue);
	}

	public int getPipeSize() {
		int retval = 0;
		DataFile df = this.outputFile;
		if (df != null) {
			retval = df.getPipeSize();
		}
		return retval;
	}
	
	/**
	 * Change output file's pipe size (immediately)
	 * @param newValue new pipe size for output data file/pipe (in bytes)
	 */
	public synchronized void setPipeSize(int newValue) {
		if (newValue <= 0) {
			throw new IllegalArgumentException("pipe size (bytes) must be greater than 0");
		}
		DataFile df = this.outputFile;
		if (df != null && df.getPipeSize() != newValue) {
			TRACE_LOGGER.message("{0}: changing pipe size from {1} to {2} bytes for {3}", getID(), df.getPipeSize(), newValue, df);
//			boolean changePipeMode = M.pipeMode == Midas.PRUN;
//			if (changePipeMode) {
//				M.pipeMode = Midas.PPAUSE; // change pipe mode to PAUSE to prevent DataFile.setPipeSize(..) from sleeping 0.2 sec
//			}
			df.setPipeSize(newValue);
//			if (changePipeMode) {
//				M.pipeMode = Midas.PRUN; // restore pipe mode back to RUN
//			}
		}
	}

}
