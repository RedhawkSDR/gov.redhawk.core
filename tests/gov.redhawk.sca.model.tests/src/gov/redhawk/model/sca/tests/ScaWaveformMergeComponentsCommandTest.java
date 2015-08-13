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
package gov.redhawk.model.sca.tests;

import org.eclipse.core.runtime.Status;
import org.junit.Test;

import CF.ComponentEnumType;
import CF.ComponentType;
import CF.PortType;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.commands.ScaWaveformMergeComponentsCommand;
import junit.framework.Assert;
import mil.jpeojtrs.sca.sad.SadFactory;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;

public class ScaWaveformMergeComponentsCommandTest {

	/**
	 * IDE-1349 Ensure the model can handle getting null objects for a waveform's components
	 * @throws Exception
	 */
	@Test
	public void nullComponents() throws Exception {
		ScaWaveform waveform = ScaFactory.eINSTANCE.createScaWaveform();
		SoftwareAssembly sad = SadFactory.eINSTANCE.createSoftwareAssembly();
		waveform.setProfileObj(sad);
		ComponentType[] compTypes = new ComponentType[1];
		compTypes[0] = new ComponentType("identifier", "/components/SigGen/SigGen.spd.xml", ComponentEnumType.APPLICATION_COMPONENT, null, new PortType[0]);

		ScaWaveformMergeComponentsCommand command = new ScaWaveformMergeComponentsCommand(waveform, compTypes, Status.OK_STATUS);
		command.execute();

		Assert.assertEquals(1, waveform.getComponents().size());
		Assert.assertEquals(null, waveform.getComponents().get(0).getCorbaObj());
		Assert.assertEquals(null, waveform.getComponents().get(0).getObj());
		Assert.assertFalse(waveform.getComponents().get(0).getStatus(ScaPackage.Literals.CORBA_OBJ_WRAPPER__CORBA_OBJ).isOK());
	}

}
