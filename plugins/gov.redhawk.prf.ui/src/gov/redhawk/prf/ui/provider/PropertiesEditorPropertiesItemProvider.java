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
package gov.redhawk.prf.ui.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.Properties;
import mil.jpeojtrs.sca.prf.provider.PropertiesItemProvider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;

/**
 * @since 2.0
 */
public class PropertiesEditorPropertiesItemProvider extends PropertiesItemProvider {

	public PropertiesEditorPropertiesItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection< ? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(PrfPackage.Literals.PROPERTIES__PROPERTIES);
		}
		return childrenFeatures;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection< ? > getChildren(Object object) {
		List<Object> children = new ArrayList<Object>();
		Properties properties = (Properties) object;
		for (FeatureMap.Entry entry : properties.getProperties()) {
			children.add(AdapterFactoryEditingDomain.unwrap(entry));
		}
		return children;
	}

}
