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

import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaStructProperty;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertyEditingSupport;

/**
 * 
 */
public class StructFieldPropertyEditingSupport extends PropertyEditingSupport {

	private final String fieldDceId;

	public StructFieldPropertyEditingSupport(final ColumnViewer viewer, final IPropertySourceProvider propertySourceProvider, final String fieldDceId) {
		super(viewer, propertySourceProvider, "value");
		this.fieldDceId = fieldDceId;
	}

	private Object getSimple(final Object object) {
		if (object instanceof ScaStructProperty) {
			final ScaStructProperty struct = (ScaStructProperty) object;
			for (final ScaSimpleProperty simple : struct.getSimples()) {
				if (this.fieldDceId.equals(simple.getId())) {
					return simple;
				}
			}
		}
		return object;
	}

	@Override
	protected boolean canEdit(final Object object) {
		return super.canEdit(getSimple(object));
	}

	@Override
	protected CellEditor getCellEditor(final Object object) {
		return super.getCellEditor(getSimple(object));
	}

	@Override
	protected Object getValue(final Object object) {
		return super.getValue(getSimple(object));
	}

	@Override
	protected void setValue(final Object object, final Object value) {
		super.setValue(getSimple(object), value);
	}

}
