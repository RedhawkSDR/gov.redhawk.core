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

import gov.redhawk.model.sca.IStatusProvider;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * @since 14.0
 */
public class SetLocalAttributeCommand extends SetStatusCommand<IStatusProvider> {

	private Object newValue;
	
	/**
	 * @since 19.0
	 */
	public SetLocalAttributeCommand(IStatusProvider target, Object newValue, EStructuralFeature feature, IStatus status) {
		super(target, feature, status);
		this.newValue = newValue;
	}

	public SetLocalAttributeCommand(IStatusProvider target, Object newValue, EStructuralFeature feature) {
		super(target, feature, null);
		this.newValue = newValue;
	}

	@Override
	public void execute() {
		if (status.isOK()) {
			this.provider.eSet(feature, newValue);
		} else {
			this.provider.eUnset(feature);
		}
		super.execute();
	}

}
