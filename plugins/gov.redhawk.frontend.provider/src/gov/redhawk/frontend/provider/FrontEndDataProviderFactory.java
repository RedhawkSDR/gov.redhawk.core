/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.frontend.provider;

import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.services.AbstractDataProviderService;
import gov.redhawk.model.sca.services.IScaDataProvider;

import org.eclipse.emf.ecore.EObject;

/**
 * 
 */
public class FrontEndDataProviderFactory extends AbstractDataProviderService {

	@Override
	protected IScaDataProvider createDataProvider(EObject object) {
		if (object instanceof ScaDevice< ? >) {
			return new FrontEndDataProvider((ScaDevice< ? >) object);
		}
		return null;
	}

}
