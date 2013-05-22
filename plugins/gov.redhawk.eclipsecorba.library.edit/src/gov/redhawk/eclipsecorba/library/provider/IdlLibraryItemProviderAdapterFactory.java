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

// BEGIN GENERATED CODE
package gov.redhawk.eclipsecorba.library.provider;

import org.eclipse.emf.common.notify.Adapter;

/**
 * @since 3.0
 */
public class IdlLibraryItemProviderAdapterFactory extends LibraryItemProviderAdapterFactory {

	@Override
	public Adapter createIdlLibraryAdapter() {
		if (this.idlLibraryItemProvider == null) {
			this.idlLibraryItemProvider = new CustomIdlLibraryItemProvider(this);
		}

		return this.idlLibraryItemProvider;
	}
}
