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
package gov.redhawk.model.sca;

import org.eclipse.emf.ecore.EObject;

/**
 * @since 4.0
 * 
 */
public interface IScaObjectIdentifierAdapter {
	/**
	 * Get the SCA profile identifier
	 * @param obj
	 * @return
	 */
	String getIdentifier(Object obj);

	/**
	 * Get the SCA Profile Object
	 * @param obj
	 * @return
	 */
	EObject getScaEObject(Object obj);
}
