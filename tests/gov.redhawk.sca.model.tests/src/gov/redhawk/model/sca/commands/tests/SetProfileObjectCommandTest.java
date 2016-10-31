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
package gov.redhawk.model.sca.commands.tests;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.junit.Assert;
import org.junit.Test;

import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.commands.SetProfileObjectCommand;
import mil.jpeojtrs.sca.spd.SoftPkg;
import mil.jpeojtrs.sca.spd.SpdFactory;

/**
 * Tests for {@link SetProfileObjectCommand}.
 */
public class SetProfileObjectCommandTest {

	private static final String PLUGIN_ID = "gov.redhawk.sca.model.tests";

	/**
	 * IDE-1717 Tests that the status gets set by {@link SetProfileObjectCommand}.
	 */
	@Test
	public void status() {
		// Create an ScaComponent - assert that status is okay
		ScaComponent component = ScaFactory.eINSTANCE.createScaComponent();
		Assert.assertTrue(component.getStatus().isOK());
		IStatus status = component.getStatus(ScaPackage.Literals.PROFILE_OBJECT_WRAPPER__PROFILE_OBJ);
		Assert.assertNull(status);

		// Invoke SetProfileObjectCommand with an error status
		final String MSG = "Test message";
		SoftPkg spd = SpdFactory.eINSTANCE.createSoftPkg();
		status = new Status(IStatus.ERROR, PLUGIN_ID, MSG);
		Command command = new SetProfileObjectCommand<SoftPkg>(component, spd, status);
		command.execute();

		// Assert that the component's status contains the error
		Assert.assertFalse(component.getStatus().isOK());
		status = component.getStatus(ScaPackage.Literals.PROFILE_OBJECT_WRAPPER__PROFILE_OBJ);
		Assert.assertNotNull(status);
		Assert.assertFalse(status.isOK());
		Assert.assertEquals(IStatus.ERROR, status.getSeverity());
		Assert.assertEquals(MSG, status.getMessage());

		// Invoke SetProfileObjectCommand with an OK status
		status = Status.OK_STATUS;
		command = new SetProfileObjectCommand<SoftPkg>(component, spd, status);
		command.execute();

		// Assert that the component's status is OK
		Assert.assertTrue(component.getStatus().isOK());
		status = component.getStatus(ScaPackage.Literals.PROFILE_OBJECT_WRAPPER__PROFILE_OBJ);
		Assert.assertNull(status);
	}

}
