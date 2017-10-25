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
package gov.redhawk.prf.internal.ui.editor.composite;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;

import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.SimpleRef;
import mil.jpeojtrs.sca.prf.SimpleSequenceRef;
import mil.jpeojtrs.sca.prf.util.PrfSwitch;

public class StructValueLabelProvider extends ColumnLabelProvider {

	protected IPropertySourceProvider propertySourceProvider;  // SUPPRESS CHECKSTYLE INLINE

	public StructValueLabelProvider(IPropertySourceProvider propertySourceProvider) {
		this.propertySourceProvider = propertySourceProvider;
	}

	private IPropertyDescriptor getPropertyDescriptor(final IPropertySource propertySource, final Object object, final Object propertyID) {
		for (IPropertyDescriptor propertyDescriptor : propertySource.getPropertyDescriptors()) {
			if (propertyID.equals(propertyDescriptor.getId())) {
				return propertyDescriptor;
			}
		}
		return null;
	}

	private Object getPropertyID(final Object object) {
		EObject element = (EObject) AdapterFactoryEditingDomain.unwrap(object);
		return new PrfSwitch<Object>() {
			@Override
			public Object caseSimpleRef(SimpleRef object) {
				return PrfPackage.Literals.SIMPLE_REF__VALUE.getName();
			}

			@Override
			public Object caseSimpleSequenceRef(SimpleSequenceRef object) {
				return PrfPackage.Literals.SIMPLE_SEQUENCE_REF__VALUES.getName();
			}
		}.doSwitch(element);
	}

	@Override
	public Image getImage(Object element) {
		Object propertyID = getPropertyID(element);
		if (propertyID == null) {
			return null;
		}
		IPropertySource propertySource = propertySourceProvider.getPropertySource(element);
		IPropertyDescriptor propertyDescriptor = getPropertyDescriptor(propertySource, element, propertyID);
		return propertyDescriptor.getLabelProvider().getImage(propertySource.getPropertyValue(propertyID));
	}

	@Override
	public String getText(Object element) {
		Object propertyID = getPropertyID(element);
		if (propertyID == null) {
			return "";
		}
		IPropertySource propertySource = propertySourceProvider.getPropertySource(element);
		IPropertyDescriptor propertyDescriptor = getPropertyDescriptor(propertySource, element, propertyID);

		// Check simple sequences for empty strings and convert them to double quotes in the viewer
		Object value = propertySource.getPropertyValue(propertyID);
		if (value instanceof String[]) {
			List<String> valueList = Arrays.asList((String[]) value);
			Collections.replaceAll(valueList, "", "\"\"");
		}

		// Check simples for empty strings and convert them to double quotes in the viewer
		String text = propertyDescriptor.getLabelProvider().getText(value);
		if ("".equals(text)) {
			text = "\"\"";
		}

		return text;
	}

}
