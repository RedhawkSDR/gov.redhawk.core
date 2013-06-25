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
package gov.redhawk.core.resourcefactory;

import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import CF.ResourceFactoryOperations;

/**
 * @since 2.0
 */
public class ApplicationDesc extends ResourceDesc {

	public ApplicationDesc(SoftwareAssembly sad,  ResourceFactoryOperations factory) {
		super(sad.getId(), sad.eResource().getURI(), createProfile(sad), sad.getVersion(), factory);
		setName(sad.getName());
		setDescription(sad.getDescription());
	}
	
	private static String createProfile(SoftwareAssembly sad) {
		return "/waveforms/" + sad.getName() + "/" + sad.eResource().getURI().lastSegment();
	}

}
