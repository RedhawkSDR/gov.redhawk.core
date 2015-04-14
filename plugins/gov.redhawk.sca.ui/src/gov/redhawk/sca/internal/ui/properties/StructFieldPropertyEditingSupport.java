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
import gov.redhawk.model.sca.ScaSimpleSequenceProperty;
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

	private Object getElement(final Object object) {
		if (object instanceof ScaStructProperty) {
			final ScaStructProperty struct = (ScaStructProperty) object;
			for (final ScaSimpleProperty simple : struct.getSimples()) {
				if (this.fieldDceId.equals(simple.getId())) {
					return simple;
				}
			}
			for (final ScaSimpleSequenceProperty sequence : struct.getSequences()) {
				if (this.fieldDceId.equals(sequence.getId())) {
					return sequence;
				}
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
		super.setValue(getElement(object), value);
	}

}
