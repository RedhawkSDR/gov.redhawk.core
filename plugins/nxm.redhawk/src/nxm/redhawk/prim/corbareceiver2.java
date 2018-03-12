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
import nxm.sys.inc.DataTypes;
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
 * @since 10.2
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
	 * Name of switch to set the blocking option (Blocking, NonBlocking (drop data), FromSRI (use setting from StreamSRI.blocking)
	 * on what to do in pushPacket/write(..) when pipe doesn't have enough room.
	 * @since 11.0 
	 */
	public static final String SW_BLOCKING_OPTION = "/BLOCKINGOPTION";

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

	/** Name of switch to set override sample rate (use 0.0 for auto based on StreamSRI). */
	public static final String SW_SAMPLE_RATE = "/SAMPLERATE";

	private static final Debug TRACE_LOGGER = new Debug(RedhawkOptActivator.ID, corbareceiver2.class.getSimpleName());

	/** sleep interval (milliseconds) for {@link #SW_WAIT}. */
	private static final int SLEEP_INTERVAL = 100;

	/** max seconds allowed since UTC (J1950) */
	private static final double MAX_UTC_WSEC = Time.MAX_INPUT_WSEC + Time.J1950TOJ1970;
	private static final double MIN_UTC_WSEC = Time.J1950TOJ1970;

	/** blocking option for what to do when pipe is full */
	static enum BlockingOption {
		NONBLOCKING, FALSE, // <-- drop data
		BLOCKING,    TRUE,
		FROMSRI             // <-- use setting from current StreamSRI.blocking
	};
	
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
	private BlockingOption blockingOption;
	
	/**
	 * If non-zero, override for SRI's sample rate (1/xdelta for type 1000, 1/ydelta for type 2000)
	 */
	private int sampleRate = 0;

	private boolean connected;
	private String portIor;
	private BulkIOType bulkioType;

	/**
	 * If non-null, connection ID to use for port connection
	 */
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
		this.fileName = MA.getFileName(corbareceiver2.A_FILE);
		this.portIor = MA.getCS(corbareceiver2.A_IOR);
		final String encoded_idl = MA.getCS(corbareceiver2.A_IDL);
		this.idl = corbareceiver2.decodeIDL(encoded_idl);
		this.streamId = MA.getCS(corbareceiver2.A_STREAM_ID, null);
		BlockingOption defBlockingOption = BlockingOption.FROMSRI;
		this.blockingOption = MA.getSelection(corbareceiver2.SW_BLOCKING_OPTION, defBlockingOption);
		boolean unsignedOctet = MA.getState(corbareceiver2.SW_TREAT_OCTET_AS_UNSIGNED);
		this.connectionId = MA.getCS(corbareceiver2.SW_CONNECTION_ID, null);
		this.canGrowPipe = MA.getState(corbareceiver2.SW_GROW_PIPE, true);
		this.sampleRate = MA.getL(corbareceiver2.SW_SAMPLE_RATE, sampleRate);
		setPipeSizeMultiplier(MA.getL(corbareceiver2.SW_PS_MULTIPLIER, 4));

		BulkIOType newType = BulkIOType.getType(this.idl);
		this.bulkioType = newType;

		if (this.receiver == null) {
			this.receiver = new BulkIOReceiver(this, newType, unsignedOctet, this.streamId);
		}

		// Check if we were able to connect to the Port
		if (!initConnections()) {
			return Commandable.ABORT; // since we are unable to connect to the Port
		}

		final double maxWaitForSRI = MA.getD(corbareceiver2.SW_WAIT, 1000); // in milliseconds
		int numLoops = (int) (maxWaitForSRI / corbareceiver2.SLEEP_INTERVAL); // 0 or positive values will effect timeout
		if (maxWaitForSRI < 0) {
			numLoops = Integer.MAX_VALUE; // effectively infinity
		}
		while (this.currentSri == null && (numLoops-- > 0)) {
			try {
				wait(corbareceiver2.SLEEP_INTERVAL);
			} catch (InterruptedException e) {
				break;
			}
		}

		this.outputFile = createOutputFile(this.currentSri, this.receiver);
		this.outputFile.open();

		corbareceiver2.TRACE_LOGGER.exitingMethod();
		return Commandable.NORMAL;
	}

	@Override
	public int process() {
		if (newPipeSize > 0) {
			setPipeSize(newPipeSize);
			newPipeSize = 0;
		}
		return Commandable.NOOP;
	}

	@Override
	public synchronized int close() {
		corbareceiver2.TRACE_LOGGER.enteringMethod();
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
		this.connectionId = null;
		this.receiver = null;
		this.connected = false;
		this.portIor = null;
		this.bulkioType = null;
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
		if (this.state != Commandable.PROCESS || isStateChanged() || this.currentSri == null || this.outputFile == null) {
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

		final String fileType = (noSubSizeFromSRI) ? "1000" : "2000"; // SUPPRESS CHECKSTYLE AvoidInline
		final String format = ((sri.mode == 0) ? "S" : "C") + receiver.getMidasType(); // SUPPRESS CHECKSTYLE AvoidInline
		final DataFile newOutputFile = new DataFile(MA.cmd, fileName, fileType, format, BaseFile.OUTPUT);

		final double sampleDelta = 1.0 / corbareceiver2.getSampleRateFor(this.sampleRate, sri); // zero will be ignored (not used)

		double xdelta;
		final boolean overrideSampleDelta = (this.sampleRate > 0);
		if (overrideSampleDelta && noSubSizeFromSRI) {
			xdelta = sampleDelta;
		} else if (sri.xdelta > 0 && sri.xdelta <= Double.MAX_VALUE) { // ignore <= 0, Infinity and NaN
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
		} else if (sri.ydelta > 0 && sri.ydelta <= Double.MAX_VALUE) { // ignore <= 0, Infinity and NaN
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
			shouldRestart |= (lastSRI.mode != sri.mode);
			shouldRestart |= (lastSRI.subsize != sri.subsize);
			shouldRestart |= (lastSRI.xdelta != sri.xdelta);
			shouldRestart |= (lastSRI.xstart != sri.xstart);
			shouldRestart |= (lastSRI.xunits != sri.xunits);
			shouldRestart |= (lastSRI.ydelta != sri.ydelta);
			shouldRestart |= (lastSRI.ystart != sri.ystart);
			shouldRestart |= (lastSRI.yunits != sri.yunits);
			// blocking, hversion, keywords, and streamID changes do not require a restart
		} else {
			shouldRestart = true;
		}
		return shouldRestart;
	}

	/**
	 * @noreference This method is not intended to be referenced by clients.
	 */
	@Override
	public synchronized void setStreamSri(String streamID, StreamSRI oldSri, StreamSRI newSri) {
		corbareceiver2.TRACE_LOGGER.message("{0}: setStreamSri to {1} id={2} blocking={3}", getID(), newSri, newSri.streamID, newSri.blocking);
		if (this.streamId == null || this.streamId.equals(streamID)) {
			this.currentSri = newSri;
			sendMessage("STREAMSRI", 1, this.currentSri);
			if (state == Commandable.PROCESS) {
				if (corbareceiver2.isRestartNeed4SriChange(newSri, oldSri)) {
					doRestart();
				}
			} else if (state == Commandable.OPEN) {
				notifyAll();
			}
		}
	}

	private synchronized void doRestart() {
		corbareceiver2.TRACE_LOGGER.message("{0}: scheduling restart... {1}", getID(), this.outputFile);
		setState(Commandable.RESTART);
	}

	/**
	 * This plots an arbitrary typed array of data. Type checking is performed
	 * by System.arraycopy(). The type of 'list' must match 'type'.
	 *
	 * @param dataArray the array of data to plot
	 * @param size the length of the data in the array
	 * @param type the NeXtMidas type of the data to plot (eg. Data.FLOAT)
	 */
	@Override
	public void write(final Object dataArray, final int size, final byte type, final boolean endOfStream, PrecisionUTCTime time, final String streamId) {
		if (!shouldProcessPacket(endOfStream, type)) {
			return;
		}

		final DataFile localOutputFile = this.outputFile;
		if (localOutputFile == null) {
			return;
		}

		final int bufferSize = Data.getBPS(type) * size; // size of data to write in bytes
		if (this.canGrowPipe && localOutputFile.getPipeSize() < bufferSize) { // increase pipe size if allowed (ticket #1554)
			if (corbareceiver2.TRACE_LOGGER.enabled) {
				corbareceiver2.TRACE_LOGGER.message("{0}: scheduling pipe size increase from {1} to ({2} x {3}) bytes for {4}",
					getID(), localOutputFile.getPipeSize(), this.pipeSizeMultiplier, bufferSize, this.outputFile);
			}
			this.newPipeSize = bufferSize * this.pipeSizeMultiplier; // this change will be picked up in the process() method
		}
		if (!isBlocking()) { // === non-blocking option enabled (drop data if pipe is full) ===
			if (M.pipeMode == Midas.PPAUSE) { // 1. pipe is PAUSED
				corbareceiver2.TRACE_LOGGER.message("Dropping packet b/c pipe is PAUSED");
				return; // 1b. drop packet, as write would block
			}
			if (!this.canGrowPipe && localOutputFile.getPipeSize() < bufferSize) {
				// PASS - let packet through even though this might block, otherwise no data will ever be written
			} else if (localOutputFile.getResource().avail() < bufferSize) { // 2. avail buffer is less than data/packet size
				// Time.sleep(0.01); // <-- provide slight back-pressure so we don't spin CPU for Component that does NOT throttle data
				corbareceiver2.TRACE_LOGGER.message("Dropping packet b/c not enough avail buffer without blocking write");
				return; // 2b. drop packet since write would block
			}
		}

		if (time != null && time.twsec <= MAX_UTC_WSEC && time.twsec >= MIN_UTC_WSEC && time.tfsec <= MAX_UTC_WSEC && time.tfsec >= MIN_UTC_WSEC) {
			Time midasTime = new Time(time.twsec + Time.J1970TOJ1950, time.tfsec);
			localOutputFile.setTimeAt(midasTime);
		}

		if (type != DataTypes.BYTE) {
			byte[] byteBuffer = new byte[bufferSize];
			Convert.ja2bb(dataArray, 0, type, byteBuffer, 0, localOutputFile.dataType, size);
			localOutputFile.write(byteBuffer, 0, byteBuffer.length);
		} else {
			localOutputFile.write((byte[]) dataArray, 0, size);
		}
	}

	/**
	 * @return the current custom sample rate to override in SRI, zero for no override.
	 */
	public int getSampleRate() {
		return sampleRate;
	}

	private static int getSampleRateFor(int sr, StreamSRI sri) {
		if (sr > 0) {
			return sr;
		}
		final int sampleRateFromSRI;
		if (sri.subsize > 1) { // type 2000 stream
			if (sri.ydelta > 0 && sri.ydelta <= Double.MAX_VALUE) { // ignore <= 0, Infinity and NaN
				sampleRateFromSRI = (int) Math.rint(1 / sri.ydelta);
			} else {
				sampleRateFromSRI = 1; // invalid value from SRI, fallback to 1
			}
		} else { // type 1000 stream
			if (sri.xdelta > 0 && sri.xdelta <= Double.MAX_VALUE) { // ignore <= 0, Infinity and NaN
				sampleRateFromSRI = (int) Math.rint(1 / sri.xdelta);
			} else {
				sampleRateFromSRI = 1; // invalid value from SRI, fallback to 1
			}
		}
		return sampleRateFromSRI;
	}

	/**
	 * @param newSRate the custom sample rate to override in SRI (zero for none);
	 *                 (1/xdelta for 1000 stream; 1/ydelta for 2000 stream).
	 */
	public void setSampleRate(int newSRate) {
		if (newSRate <= 0) {
			newSRate = 0;
		}
		int oldValue = corbareceiver2.getSampleRateFor(this.sampleRate, currentSri);
		if (this.sampleRate != newSRate) {
			this.sampleRate = newSRate;
			int newValue = corbareceiver2.getSampleRateFor(this.sampleRate, currentSri);
			if (oldValue != newValue) {
				doRestart(); // restart since specified sample rate is different than in SRI
			}
		}
	}

	/**
	 * @return the blocking option for when output pipe is full
	 */
	public boolean isBlocking() {
		switch (this.blockingOption) {
			case FROMSRI:
				StreamSRI sri = this.currentSri;
				boolean retval = (sri != null) ? sri.blocking : false;
				return retval;
			case TRUE:  /** fallthrough, same as Blocking */
			case BLOCKING:
				return true;
			default:    /** fallthrough, same as NonBlocking */
			case FALSE: /** fallthrough, same as NonBlocking */
			case NONBLOCKING:
				return false;
		}
	}

	public void setBlocking(boolean block) {
		if (block) {
			this.blockingOption = BlockingOption.BLOCKING;
		} else {
			this.blockingOption = BlockingOption.NONBLOCKING;
		}
	}

	void setBlocking(BlockingOption blockingOption) {
		this.blockingOption = blockingOption;
	}

	/** @since 11.0 */
	public void setBlockingOption(String blockingOptionStrVal) {
		this.blockingOption = BlockingOption.valueOf(blockingOptionStrVal);
	}

	/** @since 11.0 */
	public BlockingOption getBlockingOption() {
		return this.blockingOption;
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
		MA.put(corbareceiver2.SW_GROW_PIPE, "" + newValue);
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
		MA.put(corbareceiver2.SW_PS_MULTIPLIER, "" + newValue);
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
			corbareceiver2.TRACE_LOGGER.message("{0}: changing pipe size from {1} to {2} bytes for {3}", getID(), df.getPipeSize(), newValue, df);
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
	
	public StreamSRI getCurrentSri() {
		return currentSri;
	}

}
