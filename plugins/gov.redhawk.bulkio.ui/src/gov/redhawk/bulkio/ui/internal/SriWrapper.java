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
package gov.redhawk.bulkio.ui.internal;

import org.eclipse.jdt.annotation.NonNull;

import BULKIO.PrecisionUTCTime;
import BULKIO.StreamSRI;

public class SriWrapper {

	private StreamSRI sri;
	private PrecisionUTCTime pushSriDate;      // time when SRI was received in local machine time
	private PrecisionUTCTime precisionTime;  // time specified in pushPacket
	private boolean eos;           // end of stream

	public SriWrapper(@NonNull StreamSRI streamSRI, @NonNull PrecisionUTCTime date) {
		this.setSri(streamSRI);
		this.setPushSriDate(date);
	}

	public SriWrapper(SriWrapper value) {
		this.sri = value.getSri();
		this.pushSriDate = value.getPushSriDate();
		this.precisionTime = value.getPrecisionTime();
		this.eos = value.isEOS();
	}

	@NonNull
	public StreamSRI getSri() {
		return sri;
	}

	public void setSri(StreamSRI sri) {
		this.sri = sri;
	}

	/** Get most recent pushSRI date (local machine time). */
	@NonNull
	public PrecisionUTCTime getPushSriDate() {
		return pushSriDate;
	}

	public void setPushSriDate(PrecisionUTCTime date) {
		this.pushSriDate = date;
	}

	/** Get most recent packet's precision time (received in pushPacket call). */ 
	@NonNull
	public PrecisionUTCTime getPrecisionTime() {
		return precisionTime;
	}

	public void setPrecisionTime(PrecisionUTCTime precisionTime) {
		this.precisionTime = precisionTime;
	}

	public boolean isEOS() {
		return eos;
	}

	public void setEOS(boolean eos) {
		this.eos = eos;
	}
	
}
