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
package gov.redhawk.sca.ui.properties;

import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.util.ModelUtil;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;

/**
 * @since 9.0
 */
public abstract class AbstractPropertyEditingSupport extends EditingSupport {
	protected IPropertySourceProvider propertySourceProvider;

	/**
	 * Creates a new instance to be used with the given viewer, based on the
	 * given property source provider and property ID.
	 * 
	 * @param viewer
	 * the column viewer
	 * @param propertySourceProvider
	 * the property source provider
	 * @param propertyID
	 * the property ID
	 */
	public AbstractPropertyEditingSupport(final ColumnViewer viewer, final IPropertySourceProvider propertySourceProvider) {
		super(viewer);
		this.propertySourceProvider = propertySourceProvider;
	}

	protected abstract Object getPropertyID(Object object);

	@Override
	protected boolean canEdit(final Object object) {
		final IPropertyDescriptor propertyDescriptor = getPropertyDescriptor(object);
		boolean isEditable = (propertyDescriptor != null);
		if (isEditable && object instanceof ScaAbstractProperty< ? >) {
			return canEdit((ScaAbstractProperty< ? >) object);
		}
		return isEditable;
	}

	/**
	 * @since 9.3
	 */
	protected boolean canEdit(ScaAbstractProperty< ? > prop) {
		return ModelUtil.isSettable(prop);
	}

	@Override
	protected CellEditor getCellEditor(final Object object) {
		if (!canEdit(object)) {
			return null;
		}
		final IPropertyDescriptor propertyDescriptor = getPropertyDescriptor(object);
		if (propertyDescriptor != null) {
			return propertyDescriptor.createPropertyEditor((Composite) getViewer().getControl());
		}
		return null;
	}

	@Override
	protected Object getValue(final Object object) {
		final IPropertySource propertySource = this.propertySourceProvider.getPropertySource(object);
		final Object propertyID = getPropertyID(object);
		Object value = propertySource.getPropertyValue(propertyID);
		final IPropertySource valuePropertySource = this.propertySourceProvider.getPropertySource(value);
		if (valuePropertySource != null) {
			value = valuePropertySource.getEditableValue();
		}
		return value;
	}

	@Override
	protected void setValue(final Object object, final Object value) {
		final IPropertySource propertySource = this.propertySourceProvider.getPropertySource(object);
		final Object propertyID = getPropertyID(object);
		propertySource.setPropertyValue(propertyID, value);
	}
	
	private IPropertyDescriptor getPropertyDescriptor(final Object object) {
		final IPropertySource propertySource = this.propertySourceProvider.getPropertySource(object);
		if (propertySource != null) {
			final Object propertyID = getPropertyID(object);
			if (propertyID != null) {
				for (final IPropertyDescriptor propertyDescriptor : propertySource.getPropertyDescriptors()) {
					if (propertyID.equals(propertyDescriptor.getId())) {
						return propertyDescriptor;
					}
				}
			}
		}
		return null;
	}
}
