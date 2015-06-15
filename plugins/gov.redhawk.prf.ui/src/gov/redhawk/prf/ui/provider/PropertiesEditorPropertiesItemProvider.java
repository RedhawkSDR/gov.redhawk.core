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

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;

import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.provider.PropertiesItemProvider;

/**
 * @since 2.0
 */
public class PropertiesEditorPropertiesItemProvider extends PropertiesItemProvider {

	public PropertiesEditorPropertiesItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	protected Object unwrap(Object object) {
		// Where appropriate, convert feature map entries for properties from within a struct; implemented here
		// as the least intrusive way of handling cross-feature map drag-and-drop
		return convertFeatureMapEntry(super.unwrap(object));
	}

	/**
	 * Convert struct field simples and simple sequences, which are feature map entries under "fields", to the
	 * corresponding "properties" entry
	 */
	private Object convertFeatureMapEntry(Object object) {
		if (object instanceof FeatureMap.Entry) {
			// Where appropriate, convert feature map entries for simples and simple sequences from within a struct;
			// implemented here as the least intrusive way of handling cross-feature map drag-and-drop
			final FeatureMap.Entry entry = (FeatureMap.Entry) object;
			final EStructuralFeature feature = entry.getEStructuralFeature();
			if (feature == PrfPackage.Literals.STRUCT__SIMPLE) {
				return FeatureMapUtil.createEntry(PrfPackage.Literals.PROPERTIES__SIMPLE, entry.getValue());
			} else if (feature == PrfPackage.Literals.STRUCT__SIMPLE_SEQUENCE) {
				return FeatureMapUtil.createEntry(PrfPackage.Literals.PROPERTIES__SIMPLE_SEQUENCE, entry.getValue());
			}
		}
		return object;
	}

}
