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
package gov.redhawk.internal.ui;

import gov.redhawk.ui.port.nxmplot.PlotType;

/**
 * 
 */
public class PlotWizardSettings {
	public static final String PROP_TYPE = "type";
	public static final String PROP_FFT = "fft";
	public static final String PROP_CONNECTION_ID = "connectionID";
	
	private PlotType type = PlotType.LINE;
	private boolean fft = Boolean.FALSE;
	private String connectionID;
	
	public PlotType getType() {
		return type;
	}
	public void setType(PlotType type) {
		this.type = type;
	}
	public boolean isFft() {
		return fft;
	}
	public void setFft(boolean fft) {
		this.fft = fft;
	}
	public String getConnectionID() {
		return connectionID;
	}
	public void setConnectionID(String connectionID) {
		this.connectionID = connectionID;
	}
	
	
}
