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
package gov.redhawk.model.sca.services;

import org.eclipse.emf.ecore.EObject;

/**
 * @since 9.0
 */
public interface IScaDataProviderService {

	public IScaDataProvider hookDataProvider(EObject object);
	
	/**
     * @since 14.0
     */
	public void setEnabled(boolean enabled);
	
	/**
     * @since 14.0
     */
	public boolean isEnabled();
	
	/**
     * @since 14.0
     */
	public void dispose();
}
