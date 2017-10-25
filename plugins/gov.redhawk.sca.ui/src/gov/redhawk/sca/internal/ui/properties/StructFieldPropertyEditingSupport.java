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
package gov.redhawk.sca.internal.ui.properties;

import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaStructProperty;

import java.util.Collections;
import java.util.List;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertyEditingSupport;

/**
 * 
 */
public class StructFieldPropertyEditingSupport extends PropertyEditingSupport {

	private final String fieldDceId;

	public StructFieldPropertyEditingSupport(final ColumnViewer viewer, final IPropertySourceProvider propertySourceProvider, final String fieldDceId, boolean isSequence) {
		super(viewer, propertySourceProvider, (isSequence) ? ScaPackage.Literals.SCA_SIMPLE_SEQUENCE_PROPERTY__VALUES.getName() : ScaPackage.Literals.SCA_SIMPLE_PROPERTY__VALUE.getName());
		this.fieldDceId = fieldDceId;
	}

	public StructFieldPropertyEditingSupport(final ColumnViewer viewer, final IPropertySourceProvider propertySourceProvider, final String fieldDceId) {
		this(viewer, propertySourceProvider, fieldDceId, false);
	}

	private Object getElement(final Object object) {
		if (object instanceof ScaStructProperty) {
			final ScaStructProperty struct = (ScaStructProperty) object;
			final ScaAbstractProperty< ? > property = struct.getField(this.fieldDceId);
			if (property != null) {
				return property;
			}
		}
		return object;
	}

	@Override
	protected boolean canEdit(final Object object) {
		return super.canEdit(getElement(object));
	}

	@Override
	protected CellEditor getCellEditor(final Object object) {
		return super.getCellEditor(getElement(object));
	}

	@Override
	protected Object getValue(final Object object) {
		return super.getValue(getElement(object));
	}

	@Override
	protected void setValue(final Object object, final Object value) {
		Object newValue = value;
		// We need to replace any double quotes with an empty string as the model value
		if (value instanceof String && "\"\"".equals(value)) {
			newValue = "";
		} else if (value instanceof List< ? >) {
			@SuppressWarnings("unchecked")
			List< String > list = (List< String >) value;
			Collections.replaceAll(list, "\"\"", "");
		}
		super.setValue(getElement(object), newValue);
	}

}
