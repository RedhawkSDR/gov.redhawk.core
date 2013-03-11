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
package gov.redhawk.sca.ui.singledomain.providers;

import gov.redhawk.model.sca.ScaDocumentRoot;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.sca.ui.ScaContentProvider;

import org.eclipse.emf.ecore.EObject;

public class ScaSingleDomainContentProvider extends ScaContentProvider {
	
	@Override
	public Object getParent(final Object object) {
		if (object instanceof ScaDomainManager) {
			return null;
		}
		final Object retVal = super.getParent(object);
		if (!(retVal instanceof EObject) || (retVal instanceof ScaDocumentRoot || (retVal instanceof ScaDomainManagerRegistry))) {
			return null;
		}
		return retVal;
	}

}
