/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.sca.ui.tests;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.sca.ui.describers.ScaWaveformDescriber;
import gov.redhawk.sca.ui.editors.IScaContentDescriber;

public class ScaWaveformDescriberTest {

	@Test
	public void describeWaveform() throws IOException {
		ScaWaveform waveform = ScaFactory.eINSTANCE.createScaWaveform();
		Assert.assertEquals(IScaContentDescriber.VALID, new ScaWaveformDescriber().describe(waveform));
	}

	@Test
	public void describeNonWaveform() throws IOException {
		IScaContentDescriber describer = new ScaWaveformDescriber();
		Assert.assertEquals(IScaContentDescriber.INVALID, describer.describe(ScaFactory.eINSTANCE.createScaComponent()));
		Assert.assertEquals(IScaContentDescriber.INVALID, describer.describe(null));
	}
}
