/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.sca.model.internal;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.emf.ecore.EObject;

/**
 * 
 */
public class EmfPropertyTester extends PropertyTester {

	/**
	 * 
	 */
	public EmfPropertyTester() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean test(final Object receiver, final String property, final Object[] args, final Object expectedValue) {
		if (!(receiver instanceof EObject)) {
			return false;
		}
		final EObject obj = (EObject) receiver;
		if (property.equals("namespace")) {
			if (obj.eClass().getEPackage().getNsURI().equals(expectedValue)) {
				return true;
			}
		}
		return false;
	}

}
