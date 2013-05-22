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

/**
 * @since 4.0
 */
public interface ICorbaObjectDescriptorAdapter {

	/**
	 * Get the IOR of the SCA Model object
	 * @param obj
	 * @return The value of the IOR
	 */
	String getIOR(Object obj);

	/**
	 * 
	 * @param obj
	 * @param repId
	 * @return True if the object supports the repID
	 */
	boolean supports(Object obj, String repId);
}
