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
package gov.redhawk.model.sca.commands;

import gov.redhawk.model.sca.ProfileObjectWrapper;
import gov.redhawk.model.sca.ScaPackage;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;

/**
 * @since 14.0
 * 
 */
public class SetProfileObjectCommand< T extends EObject > extends SetStatusCommand<ProfileObjectWrapper<T>> {

	private T profileObject;

	public SetProfileObjectCommand(ProfileObjectWrapper<T> provider, T profileObject, IStatus status) {
		super(provider, ScaPackage.Literals.PROFILE_OBJECT_WRAPPER__PROFILE_OBJ, status);
		this.profileObject = profileObject;
	}

	@Override
	public void execute() {
		if (status.isOK()) {
			provider.setProfileObj(this.profileObject);
		} else {
			provider.unsetProfileObj();
		}
		provider.setStatus(ScaPackage.Literals.PROFILE_OBJECT_WRAPPER__PROFILE_OBJ, status);
		super.execute();
	}

}
