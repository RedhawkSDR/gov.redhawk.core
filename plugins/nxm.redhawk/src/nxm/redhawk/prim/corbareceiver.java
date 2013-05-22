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
import nxm.sys.lib.Command;
import nxm.sys.lib.Data;
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

	private static final int DEFAULT_SUBSIZE = 1024;
	/**
	 * @since 8.0
	 */
	public static final String NAME = "CORBARECEIVER";
	/**
	 * @since 8.0
	 */
	public static final String A_FORCE_2000 = "FORCE2000";
	/**
	 * @since 8.0
	 */
	public static final String A_FILE = "FILE";

	/** the output file to write to */
	private DataFile outputFile = null;

	/** If the data should be force to type 2000, useful for 1d plotting */
	private boolean force2000;
	/** The configured SRI */
	private StreamSRI currentSri;
	private org.omg.CORBA.Object stub;
	private BaseBulkIOReceiver receiver;

	private Data dataBuffer;

	private int framesize = corbareceiver.DEFAULT_SUBSIZE;

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

	/** {@inheritDoc} */
	@Override
	public int open() {
		final FileName fileName = this.MA.getFileName(corbareceiver.A_FILE);
		final String encoded_idl = this.MA.getS(corbareceiver.A_IDL, null);
		final String idl = corbareceiver.decodeIDL(encoded_idl);

		if (this.receiver == null) {
			this.receiver = BulkIOReceiverFactory.createReceiver(this, idl);
		}

		this.force2000 = this.MA.getState(corbareceiver.A_FORCE_2000, false);

		int ret = super.open();

		this.outputFile = corbareceiver.createOutputFile(this.currentSri, this.MA.cmd, this.receiver, fileName, this.force2000, this.framesize);
		this.outputFile.open();

		// This check fails if we weren't able to connect to the ORB - MA is the
		// argument list
		if ((ret == Commandable.NORMAL)) {

			// Check if we were able to connect to the port
			if (initConnections()) {
				ret = Commandable.NORMAL;
			} else {
				ret = Commandable.ABORT;
			}
		}
		return ret;
	}

	/** {@inheritDoc} */
	@Override
	public synchronized int close() {
		if (this.state != Commandable.RESTART) {
			//Avoid hanging the UI if the CORBA call to the component fails to return
			super.close();
			if (this.outputFile != null) {
				this.outputFile.close();
				this.outputFile = null;
			}
			if (this.dataBuffer != null) {
				this.dataBuffer.buf = null;
				this.dataBuffer.size = -1;
				this.dataBuffer = null;
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

	private static DataFile createOutputFile(final StreamSRI sri, final Command cmd, final BaseBulkIOReceiver receiver, final FileName fileName,
	        boolean force2000, final int frameSize) {
		if (sri == null) {
			return new DataFile(cmd, fileName, (force2000) ? "2000" : "1000", "S" + receiver.getType(), // SUPPRESS CHECKSTYLE AvoidInline
			        BaseFile.OUTPUT,
			        -1.0,
			        null);
		}
		final String format = ((sri.mode == 0) ? "S" : "C") + receiver.getType(); // SUPPRESS CHECKSTYLE AvoidInline

		final DataFile outputFile = new DataFile(cmd, fileName, ((sri.subsize != 0) || (force2000)) ? "2000" : "1000", format, // SUPPRESS CHECKSTYLE AvoidInline
		        BaseFile.OUTPUT,
		        -1.0,
		        null);

		if (sri.subsize > 0) {
			outputFile.setFrameSize(sri.subsize);
			force2000 = false;
		} else {
			if (force2000) {
				outputFile.setFrameSize(frameSize);
			}
		}

		outputFile.setXStart(sri.xstart);
		if (sri.xdelta == 0.0) { // NXM refuses to plot correctly if xdelta=0
			sri.xdelta = 1.0;
		}
		outputFile.setXDelta(sri.xdelta);
		outputFile.setXUnits(sri.xunits);

		outputFile.setYStart(sri.ystart);
		if (sri.ydelta == 0.0) { // NXM refuses to plot correctly if xdelta=0
			sri.ydelta = 1.0;
		}
		//TEMP workaround until sri is correct
		outputFile.setYDelta(/*sri.ydelta*/0.085);
		outputFile.setYUnits(sri.yunits);
		
		return outputFile;
	}

	private synchronized void setStreamSri(final StreamSRI sri) {
		if (!corbareceiver.isSRIChanged(sri, this.currentSri)) {
			return;
		}
		this.currentSri = sri;
		sendMessage("STREAMSRI", 1, this.currentSri);
		doRestart();
	}

	private void doRestart() {
		this.dataBuffer = null;
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
		if (!(this.state == Commandable.PROCESS) || this.currentSri == null || !shouldProcessPacket(endOfStream, type)) {
			return;
		}
		final DataFile localOutputFile = this.outputFile;
		if (localOutputFile == null) {
			return;
		}

		Data dat = this.dataBuffer;
		final int fs = size / localOutputFile.getSPA();
		if (localOutputFile.getType() == 2000) { // SUPPRESS CHECKSTYLE MagicNumber
			if (localOutputFile.getFrameSize() != fs) {
				this.framesize = fs;
				doRestart();
				return;
			}
			if (dat == null) {
				dat = localOutputFile.getDataBuffer(1);
			}
		} else {
			if (dat == null) {
				dat = localOutputFile.getDataBuffer(fs);
			} else if (dat.size != fs) {
				dat.setSize(fs);
			}
		}
		this.dataBuffer = dat;

		if ((this.currentSri.blocking) || (localOutputFile.avail() >= dat.size)) {
			if (time != null) {
				this.dataBuffer.setHeader(new Time(time.twsec + Time.J1970TOJ1950, time.tfsec));
			}
			dataBuffer.fromArray(dataArray, 0, type);
			localOutputFile.write(dataBuffer);
		}

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
			return new StreamSRI[] {
				this.currentSri
			};
		}
	}
}
