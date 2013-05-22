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

import java.util.HashMap;
import java.util.Map;

import CF.DataType;
import CF.Resource;
import CF.ResourceFactoryOperations;
import CF.ResourceFactoryPackage.CreateResourceFailure;
import CF.ResourceFactoryPackage.InvalidResourceId;
import CF.ResourceFactoryPackage.ShutdownFailure;

public abstract class BundleResourceFactory implements ResourceFactoryOperations {

	private final Map<String, Resource> resourceCache = new HashMap<String, Resource>();

	public BundleResourceFactory() {

	}

	protected abstract Resource createResource(String resourceId);

	public Resource createResource(final String resourceId, final DataType[] qualifiers) throws CreateResourceFailure {
		Resource retVal = this.resourceCache.get(resourceId);
		if (retVal == null) {
			retVal = createResource(resourceId);
		}
		// TODO Auto-generated method stub
		return retVal;
	}

	public void releaseResource(final String resourceId) throws InvalidResourceId {
		this.resourceCache.remove(resourceId);
		// TODO Auto-generated method stub

	}

	public void shutdown() throws ShutdownFailure {
		// TODO Auto-generated method stub

	}

}
