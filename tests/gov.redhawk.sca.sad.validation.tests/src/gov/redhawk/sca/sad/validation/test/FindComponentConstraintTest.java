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
package gov.redhawk.sca.sad.validation.test;

import java.net.URISyntaxException;

import org.eclipse.emf.ecore.util.Diagnostician;
import org.junit.Test;

import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.sad.SadComponentPlacement;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;

public class FindComponentConstraintTest {

	/**
	 * Ensure we don't have issues with the uniqueness check validating a findcomponent
	 * in a SoftwareAssembly where some component instantiations that do NOT have findcomponents
	 */
	@Test
	public void validNullFindComponent() throws URISyntaxException {
		SoftwareAssembly sad = SadSdrHelper.loadSAD("/waveforms/FindComponentWF/FindComponentWF.sad.xml");
		SadComponentPlacement placement1 = sad.getPartitioning().getComponentPlacement().get(2);
		SadComponentInstantiation ci = placement1.getComponentInstantiation().get(0);
		Diagnostician.INSTANCE.validate(ci);
	}
}
