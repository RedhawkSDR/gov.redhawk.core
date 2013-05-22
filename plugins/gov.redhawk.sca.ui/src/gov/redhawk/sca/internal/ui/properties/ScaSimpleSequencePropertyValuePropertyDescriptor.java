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
import gov.redhawk.model.sca.ScaSimpleSequenceProperty;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;

/**
 * @since 8.0
 * 
 */
public class ScaSimpleSequencePropertyValuePropertyDescriptor extends PropertyValueTypePropertyDescriptor {

	public static class ScaSimpleSequencePropertyValueCellEditor extends PropertyValueTypeCellEditor {

		public ScaSimpleSequencePropertyValueCellEditor(final ScaSimpleSequenceProperty property, final Composite arg1) {
			super(property.getDefinition().getType(), arg1);
		}
	}

	public ScaSimpleSequencePropertyValuePropertyDescriptor(final Object obj, final IItemPropertyDescriptor itemPropertyDescriptor) {
		super(obj, itemPropertyDescriptor);
	}

	@Override
	protected CellEditor createEDataTypeCellEditor(final EDataType eDataType, final Composite composite) {
		if (!this.itemPropertyDescriptor.canSetProperty(this.object)) {
			return null;
		}
		final ScaAbstractProperty< ? > property = (ScaAbstractProperty< ? >) this.object;
		return new ScaSimpleSequencePropertyValueCellEditor((ScaSimpleSequenceProperty) property, composite);
	}

}
