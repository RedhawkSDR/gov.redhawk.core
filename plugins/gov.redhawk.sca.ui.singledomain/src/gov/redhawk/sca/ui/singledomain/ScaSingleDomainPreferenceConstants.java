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
package gov.redhawk.sca.ui.singledomain;

public class ScaSingleDomainPreferenceConstants {
	
	private ScaSingleDomainPreferenceConstants() {
		
	}
	
	/**
	 * The Constant SCA_ACTIVE_DOMAIN - name of the active domain (used for
	 * single-domain SCA Explorer only)
	 * @since 6.0
	 */
	public static final String SCA_ACTIVE_DOMAIN = "activeDomain";
	
	/**
	 * The Constant SCA_SET_NEW_DOMAIN_ACTIVE - a control flag specifying that when 
	 * a domain is created it will be set as the active domain
	 * @since 6.0
	 */
	public static final String SCA_SET_NEW_DOMAIN_ACTIVE = "setNewDomainActive";
	
	/**
	 * The Constant SCA_SET_NEW_DOMAIN_ACTIVE - a control flag specifying that 
	 * a domain should be disconnected when another domain is set to be the active domain.
	 * @since 6.0
	 */
	public static final String SCA_DISCONNECT_INACTIVE = "disconnectInative";


}
