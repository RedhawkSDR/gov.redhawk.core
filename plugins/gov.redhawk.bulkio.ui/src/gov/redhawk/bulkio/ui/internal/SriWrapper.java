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

import java.util.Date;

import org.eclipse.jdt.annotation.NonNull;

import BULKIO.StreamSRI;

public class SriWrapper {

	private StreamSRI sri;
	private Date date;
	private String precisionTime;
	private boolean eos;

	public SriWrapper(@NonNull StreamSRI streamSRI, @NonNull Date date) {
		this.setSri(streamSRI);
		this.setDate(date);
	}

	public SriWrapper(SriWrapper value) {
		this.sri = value.getSri();
		this.date = value.getDate();
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

	@NonNull
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@NonNull
	public String getPrecisionTime() {
		return precisionTime;
	}

	public void setPrecisionTime(String precisionTime) {
		this.precisionTime = precisionTime;
	}

	public boolean isEOS() {
		return eos;
	}

	public void setEOS(boolean eos) {
		this.eos = eos;
	}
	
}
