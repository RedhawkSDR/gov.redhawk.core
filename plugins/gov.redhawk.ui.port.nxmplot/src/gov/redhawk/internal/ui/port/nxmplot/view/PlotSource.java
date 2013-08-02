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
package gov.redhawk.internal.ui.port.nxmplot.view;

import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.ui.port.nxmplot.FftSettings;

/**
 * @since 5.0
 */
public class PlotSource {
	private final ScaUsesPort input;
	private final FftSettings fftOptions;
	private final String qualifiers;

	public PlotSource(ScaUsesPort input, FftSettings fftOptions, String qualifiers) {
		super();
		this.input = input;
		this.fftOptions = fftOptions;
		this.qualifiers = qualifiers;
	}

	public ScaUsesPort getInput() {
		return input;
	}

	public FftSettings getFftOptions() {
		return fftOptions;
	}

	public String getQualifiers() {
		return qualifiers;
	}
}