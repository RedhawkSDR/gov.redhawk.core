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

import gov.redhawk.eclipsecorba.library.LibraryPackage;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * @since 3.0
 */
public class CustomIdlLibraryItemProvider extends IdlLibraryItemProvider {

	public CustomIdlLibraryItemProvider(final AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public Collection< ? extends EStructuralFeature> getChildrenFeatures(final Object object) {
		if (this.childrenFeatures == null) {
			this.childrenFeatures = new ArrayList<EStructuralFeature>();
			this.childrenFeatures.add(LibraryPackage.Literals.IDL_LIBRARY__SPECIFICATIONS);
		}
		return this.childrenFeatures;
	}

}
