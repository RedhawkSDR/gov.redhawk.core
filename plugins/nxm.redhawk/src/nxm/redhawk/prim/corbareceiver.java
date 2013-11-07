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
import java.net.URLEncoder;

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
 * Char/Short/Long/Float/Double). NOTE: The class can switch between receiving
 * Scalar and Complex, but not the actual data type. This functionality has not
 * been tested(the plot may not recover).
 */
public class corbareceiver extends CorbaPrimitive { //SUPPRESS CHECKSTYLE ClassName
	/** Name of IDL argument
	 * @since 8.0
	 */
	public static final String A_IDL = "IDL";
	/**
	 * @since 8.0
	 */
	public static final String NAME = "CORBARECEIVER";
	/**
	 * @since 8.0
	 */
	public static final String A_FILE = "FILE";

	/**
	 * Name of frame size argument
	 * @since 10.0
	 */
	public static final String A_FRAMESIZE = "FRAMESIZE";

	/**
	 * OVERRIDE FRAME SIZE (from StreamSRI) attribute
	 * @since 10.0
	 */
	public static final String A_OVERRIDE_SRI_SUBSIZE = "OVERRIDE_SRI_SUBSIZE";

	/**
	 * Name of switch to set to false to NOT block pushPacket/write(..) when pipe doesn't have enough room,
	 * which will cause that pushPacket data to get drop
	 * @since 10.0
	 */
	public static final String SW_BLOCKING = "/BLOCKING";

	/**
	 * Name of switch to grab number of seconds to wait for SRI during open()
	 * @since 10.0
	 */
	public static final String SW_WAIT = "/WAIT";
	private static final int SLEEP_INTERVAL = 100;

	/** the output file to write to */
	private volatile DataFile outputFile = null;
	private FileName fileName;

	/** The configured SRI */
	private volatile StreamSRI currentSri;
	private BulkIOReceiver receiver;

	private Integer origFrameSizeArg = null;
	/** default frame size for 1000 stream or custom frame size if {@link #overrideSRISubSize} is true. */
	private int frameSizeAttribute;
	/** override frame size specified in SRI with {@link #frameSizeAttribute}. */
	private boolean overrideSRISubSize;

	/** block if output pipe is full. */
	private boolean blocking;

	/** true if 1000 stream (SRI.subsize <= 0); false for 2000 stream (SRI.subsize > 0). */
	private boolean is1000FromSRI = true;
	/** override SRI's sample rate (1/xdelta for 1000, 1/ydelta for 2000). */
	private boolean overrideSRISampleRate = false;
	/** custom sample rate to use for output file/pipe. zero for none. */
	private double sampleRate = 0.0;
	private boolean connected;
	private String portIor;
	private BulkIOType type;

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
		this.fileName = this.MA.getFileName(A_FILE);
		final String encoded_idl = this.MA.getS(A_IDL, null);
		this.frameSizeAttribute = this.MA.getL(A_FRAMESIZE, 0);
		this.overrideSRISubSize = this.MA.getState(A_OVERRIDE_SRI_SUBSIZE, false);
		this.blocking = this.MA.getState(SW_BLOCKING, false);

		BulkIOType newType = BulkIOType.getType(corbareceiver.decodeIDL(encoded_idl));
		this.type = newType;
		
		if (this.receiver == null) {
			this.receiver = new BulkIOReceiver(this, newType);
		}
		if (origFrameSizeArg == null) {
			origFrameSizeArg = this.frameSizeAttribute;
		}

		int ret = super.open();

		// This check fails if we weren't able to connect to the ORB
		if ((ret == Commandable.NORMAL)) {

			// Check if we were able to connect to the Port
			if (!initConnections()) {
				return Commandable.ABORT; // ABORT, since we are unable to connect to the Port
			}
		}

		final double maxWaitForSRI = MA.getD(SW_WAIT, 1000);
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

		return ret;
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
	 * This initializes the ORB, gets the destination CF::Resource and connects
	 * to the specified port. This will retry MAX_RETRIES times to complete the
	 * connection sequence.
	 * @since 8.0
	 */
	private boolean initConnections() {
		if (!connected) {
			try {
				String portIor2 = getPortIOR();
				portIor = portIor2;
				BulkIOType type2 = type;
				BulkIOReceiver receiver2 = receiver;
				if (portIor2 != null && type2 != null && receiver2 != null) {
					BulkIOUtilActivator.getBulkIOPortConnectionManager().connect(portIor2, type2, receiver2);
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
		super.shutdown();
		final String portIor2 = portIor;
		final BulkIOType type2 = type;
		final BulkIOReceiver receiver2 = receiver;
		if (portIor2 != null && type2 != null && receiver2 != null) {
			BulkIOUtilActivator.getBulkIOPortConnectionManager().disconnect(portIor2, type2, receiver2);
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
	private synchronized boolean shouldProcessPacket(final boolean endOfStream, final byte type) {
		if (this.state != Commandable.PROCESS || isStateChanged() || this.currentSri == null || this.outputFile == null) {
			return false;
		}
		return true;
	}

	private DataFile createDefaultOutputFile() {
		final String format = "S" + receiver.getMidasType();
		DataFile newOutputFile = new DataFile(this.MA.cmd, fileName, "2000", format, BaseFile.OUTPUT);
		newOutputFile.setFrameSize(frameSizeAttribute); // <-- this call will not convert 1000 to 2000
		return newOutputFile;
	}

	private DataFile createOutputFile(final StreamSRI sri, final BulkIOReceiver receiver) {
		if (sri == null) {
			return createDefaultOutputFile();
		}

		this.is1000FromSRI = (sri.subsize <= 0); // true for 1000 stream, false for 2000 stream
		int outputFramesize = sri.subsize;
		if (this.is1000FromSRI) {
			outputFramesize = this.frameSizeAttribute; // frame 1000 stream with our default frame size
		} else if (this.overrideSRISubSize && this.frameSizeAttribute > 0) {
			outputFramesize = this.frameSizeAttribute;
		}

		outputFramesize = Math.max(outputFramesize, 1);

		final String fileType = (outputFramesize > 0) ? "2000" : "1000"; // SUPPRESS CHECKSTYLE AvoidInline
		final String format = ((sri.mode == 0) ? "S" : "C") + receiver.getMidasType(); // SUPPRESS CHECKSTYLE AvoidInline
		final DataFile newOutputFile = new DataFile(this.MA.cmd, fileName, fileType, format, BaseFile.OUTPUT);

		if (outputFramesize > 0) {
			newOutputFile.setFrameSize(outputFramesize);
		}

		double sampleDelta = 0; // zero will be ignored (not used)
		if (isOverrideSRISampleRate() && this.sampleRate != 0) {
			sampleDelta = 1 / this.sampleRate;
		}
		final boolean overrideSampleDelta = this.overrideSRISampleRate && (sampleDelta != 0);

		double xdelta = 1.0;
		if (overrideSampleDelta && this.is1000FromSRI) {
			xdelta = sampleDelta;
		} else if (sri.xdelta > 0) {
			xdelta = sri.xdelta;
		}
		newOutputFile.setXStart(sri.xstart);
		newOutputFile.setXDelta(xdelta);
		newOutputFile.setXUnits(sri.xunits);

		double ydelta = 1.0;
		if (overrideSampleDelta && !this.is1000FromSRI) {
			ydelta = sampleDelta;
		} else if (sri.ydelta > 0) {
			ydelta = sri.ydelta;
		}
		newOutputFile.setYStart(sri.ystart);
		newOutputFile.setYDelta(ydelta);
		newOutputFile.setYUnits(sri.yunits);

		return newOutputFile;
	}

	/**
	 * @since 10.0
	 */
	public synchronized void setStreamSri(final StreamSRI sri) {
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

	/**
	 * This plots an arbitrary typed array of data. Type checking is performed
	 * by System.arraycopy(). The type of 'list' must match 'type'.
	 *
	 * @param dataArray the array of data to plot
	 * @param size the length of the data in the array
	 * @param type the NeXtMidas type of the data to plot (eg. Data.FLOAT)
	 * @since 8.0
	 */
	public void write(final Object dataArray, final int size, final byte type, final boolean endOfStream, PrecisionUTCTime time) {
		if (!shouldProcessPacket(endOfStream, type)) {
			return;
		}

		final DataFile localOutputFile = this.outputFile;
		if (localOutputFile == null || !localOutputFile.isOpen) {
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
	 * @return the overrideSRISubSize
	 * @since 10.0
	 */
	public boolean isOverrideSRISubSize() {
		return overrideSRISubSize;
	}

	/**
	 * @param overrideSRISubSize true to override the SRI SubSize (frame size) with {@link #getFrameSize()}.
	 * @see #setFrameSize(int)
	 * @since 10.0
	 */
	public void setOverrideSRISubSize(boolean overrideSRISubSize) {
		if (this.overrideSRISubSize != overrideSRISubSize) {
			this.overrideSRISubSize = overrideSRISubSize;
			MA.put(A_OVERRIDE_SRI_SUBSIZE, "" + overrideSRISubSize);
			if ((this.currentSri != null) && (this.frameSizeAttribute != this.currentSri.subsize)) {
				doRestart(); // restart since specified subsize is different than in SRI
			}
		}
	}

	/**
	 * @return the frame size to use
	 * @since 10.0
	 */
	public int getFrameSize() {
		return frameSizeAttribute;
	}

	/**
	 * @param set the custom frame size (SubSize) to use when {@link #overrideSRISubSize} is true.;
	 *        use -1 to reset to original frame size argument
	 * @see #setOverrideSRISubSize(boolean)
	 * @since 10.0
	 */
	public void setFrameSize(int frameSizeAttribute) {
		if (frameSizeAttribute == -1) {
			frameSizeAttribute = origFrameSizeArg;
		}
		if (this.frameSizeAttribute != frameSizeAttribute) {
			this.frameSizeAttribute = frameSizeAttribute;
			MA.put(A_FRAMESIZE, "" + frameSizeAttribute);
			if (this.overrideSRISubSize) {
				doRestart(); // restart since specified subsize is different than in SRI
			}
		}
	}

	/** true if custom sample rate differs from SRI's sample rate, otherwise false. */
	private boolean isSampleRateDifferent() {
		if ((this.currentSri != null) && (this.sampleRate != 0)) { // cannot have zero sample rate
			final double delta = 1.0 / this.sampleRate;
			final boolean xdDiffers = this.is1000FromSRI && delta != this.currentSri.xdelta;
			final boolean ydDiffers = !this.is1000FromSRI && delta != this.currentSri.ydelta;
			if (xdDiffers || ydDiffers) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return the overrideSRISampleRate
	 * @since 10.0
	 */
	public boolean isOverrideSRISampleRate() {
		return overrideSRISampleRate;
	}

	/**
	 * @param overrideSRISampleRate the overrideSRISampleRate to set
	 * @since 10.0
	 */
	public void setOverrideSRISampleRate(boolean overrideSRISampleRate) {
		if (this.overrideSRISampleRate != overrideSRISampleRate) {
			this.overrideSRISampleRate = overrideSRISampleRate;
			if (isSampleRateDifferent()) {
				doRestart(); // restart since specified sample rate is different than in SRI
			}
		}
	}

	/**
	 * @return the current custom sample rate to override in SRI.
	 * @since 10.0
	 */
	public double getSampleRate() {
		return sampleRate;
	}

	/**
	 * @param newSRate the custom sample rate to override in SRI
	 *                 (1/xdelta for 1000 stream; 1/ydelta for 2000 stream).
	 * @since 10.0
	 */
	public void setSampleRate(double newSRate) {
		if (this.sampleRate != newSRate) {
			this.sampleRate = newSRate;
			if (this.overrideSRISampleRate && isSampleRateDifferent()) {
				doRestart(); // restart since specified sample rate is different than in SRI
			}
		}
	}

	/**
	 * @return the blocking option
	 * @since 10.0
	 */
	public boolean isBlocking() {
		return blocking;
	}

	/**
	 * @param blocking the blocking option to set
	 * @since 10.0
	 */
	public void setBlocking(boolean blocking) {
		this.blocking = blocking;
	}
}
