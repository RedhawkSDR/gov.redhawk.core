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

import gov.redhawk.model.sca.ScaFileStore;

import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.IAdapterFactory;

/**
 * 
 */
public class ScaFileStoreAdapterFactory implements IAdapterFactory {
	
	private static final Class<?> [] LIST = new Class []{
		IFileStore.class
	};

	/**
	 * {@inheritDoc}
	 */
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adaptableObject instanceof ScaFileStore) {
			ScaFileStore store = (ScaFileStore) adaptableObject;
			if (adapterType == IFileStore.class && !store.isDirectory()) {
				return store.getFileStore();
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public Class<?>[] getAdapterList() {
		return LIST;
	}

}
