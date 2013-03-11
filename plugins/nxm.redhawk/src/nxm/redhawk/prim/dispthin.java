/**
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package nxm.redhawk.prim;

import nxm.sys.inc.Commandable;
import nxm.sys.lib.BaseFile;
import nxm.sys.lib.Data;
import nxm.sys.lib.DataFile;
import nxm.sys.lib.Message;
import nxm.sys.lib.Primitive;

/**
 * @since 2.1
 */
public class dispthin extends Primitive { // SUPPRESS CHECKSTYLE ClassName

	private static final double MILLIS_PER_SEC = 1000;
	private DataFile hin;
	private DataFile hout;
	private Data dataBuffer;

	private double refreshRate;
	private boolean firstTime = true;
	private boolean update;
	private long nextUpdate;
	private int frameLen;
	private int pipeClass;
	private int lps;
	private String outputFileName;
	private boolean isDone;

	@Override
	public int open() {
		// Open the input file
		this.hin = new DataFile(this.MA.cmd, this.MA.getFileName("INFILE"), "1000,2000,3000,4000,5000,6000", "S*,C*", 0);
		this.hin.open();
		this.pipeClass = this.hin.typeClass;

		// Get lines per screen switch
		this.lps = this.MA.getL("/LPS");
		if (this.pipeClass > 2) {
			this.lps = sign(this.hin.getRecLength(), this.lps);
		}
		this.lps = Math.max(1, Math.abs(this.lps));

		this.frameLen = this.MA.getL("FRAMELENGTH");
		// Get the frame length
		if (this.pipeClass == 1) {
			this.pipeClass = 1;
			final int xfer = this.MA.getL("/TL", this.frameLen);
			this.dataBuffer = this.hin.getDataBuffer(xfer * this.lps);
			this.hin.setXferLength(this.dataBuffer.getSize());
		} else if (this.pipeClass == 2) {
			this.frameLen = this.hin.getFrameSize();
			if (this.frameLen == 0) {
				this.M.warning("File has frame size 0, using 1");
				this.frameLen = 1;
			}
			final int xfer = this.MA.getL("/TL", 1);
			this.dataBuffer = this.hin.getDataBuffer(xfer * this.lps);
			this.hin.setXferLength(this.dataBuffer.getSize());
		} else if (this.frameLen == 0) {
			this.M.warning("Illegal frame size 0 requested, using subsize");
			this.frameLen = this.hin.getFrameSize();
		}

		this.refreshRate = this.MA.getD("REFRESHRATE");

		// Set the Poll Time to be at least the Refresh Rate
		this.setPollTime(Math.min((1 / this.refreshRate), this.getPollTime()));

		// Save the output file name
		this.outputFileName = this.MA.getS("OUTFILE");

		// Make the output file and copy the input data. Copying the file
		// directly messes up the pipe.
		this.hout = new DataFile(this.MA.cmd, this.outputFileName, Integer.toString(this.hin.getType()), this.hin.getFormat(), BaseFile.OUTPUT, -1.0, null);

		this.hout.setXStart(this.hin.getXStart());
		this.hout.setXDelta(this.hin.getXDelta());
		this.hout.setXUnits(this.hin.getXUnits());
		this.hout.setYStart(this.hin.getYStart());
		this.hout.setYDelta(this.hin.getYDelta());
		this.hout.setYUnits(this.hin.getYUnits());
		this.hout.setFrameSize(this.hin.getFrameSize());
		this.hout.open(BaseFile.OUTPUT);

		return (this.hin.isOpen() ? Commandable.NORMAL : Commandable.FINISH); // SUPPRESS CHECKSTYLE AvoidInline
	}

	@Override
	public int process() {
		if (this.isDone || this.hin == null || !this.hin.isOpen()) {
			return Commandable.FINISH;
		} else if (this.hin.getXferLength() < 0) {
			return Commandable.NOOP;
		} else if (this.firstTime) {
			this.firstTime = false;

			// Prepare for the first update
			this.update = true;
			this.nextUpdate = System.currentTimeMillis();

			int inbufsize = (int) (this.hin.getXferLength() * this.hin.getBPE());
			if (this.hin.getFormat().charAt(0) == 'C') {
				inbufsize = inbufsize * 2;
			}
		}

		int status = Commandable.NORMAL;
		// The next line may not be necessary
		final int numRead = this.hin.read(this.dataBuffer);

		// Check if we need to update
		final long current_time = System.currentTimeMillis();
		if (current_time > this.nextUpdate) {
			this.update = true;
		}

		// If we read < 0, the pipe is closed so finish
		if (numRead < 0) {
			status = Commandable.FINISH;

			// If we read 0, nothing was in the pipe, continue unless we need to
			// write the data
		} else if ((numRead == 0) && !this.update) {
			status = Commandable.NOOP;

			// Process the data we read
		} else {
			if (this.update) {
				
				// Drop packet if write is blocking
				if (this.hout.avail() >= this.dataBuffer.getSize()) {
					this.hout.write(this.dataBuffer);
				}

				if (this.refreshRate > 0.0) {
					this.nextUpdate = current_time;
					this.nextUpdate = (long) (this.nextUpdate + (((1.0 / this.refreshRate) * dispthin.MILLIS_PER_SEC)));
					this.update = false;
				} else {
					this.update = true;
				}
			}
		}
		return status;
	}

	/** {@inheritDoc} 
	 * @since 8.0*/
	@Override
	public int processMessage(final Message msg) {
		if (msg.getName().equalsIgnoreCase("EXIT")) {
			this.isDone = true;
		}
		return super.processMessage(msg);
	}

	@Override
	public int close() {
		if (this.hin != null) {
			this.hin.close();
			this.hin = null;
		}
		if (this.hout != null) {
			this.hout.close();
			this.hout = null;
		}
		if (this.dataBuffer != null) {
			this.dataBuffer.buf = null;
			this.dataBuffer.size = -1;
			this.dataBuffer = null;
		}
		this.outputFileName = null;
		return super.close();
	}

	private final int sign(final int a, final int b) {
		final int[] multipliers = new int[] {
		        1, // Both negative
		        -1, // A positive, B negative
		        -1, // A negative, B positive
		        1
		}; // Both positive
		final int lookup = ((a >= 0) ? 1 : 0) + ((a >= 0) ? 1 : 0) * 2; // SUPPRESS CHECKSTYLE AvoidInline
		return a * multipliers[lookup];
	}

}
