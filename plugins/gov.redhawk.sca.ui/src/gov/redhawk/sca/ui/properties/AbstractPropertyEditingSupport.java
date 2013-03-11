/**
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.sca.ui.properties;

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
	 *            the column viewer
	 * @param propertySourceProvider
	 *            the property source provider
	 * @param propertyID
	 *            the property ID
	 */
	public AbstractPropertyEditingSupport(final ColumnViewer viewer, final IPropertySourceProvider propertySourceProvider) {
		super(viewer);
		this.propertySourceProvider = propertySourceProvider;
	}

	protected abstract Object getPropertyID(Object object);

	@Override
	protected boolean canEdit(final Object object) {
		final IPropertySource propertySource = this.propertySourceProvider.getPropertySource(object);
		final IPropertyDescriptor[] propertyDescriptors = propertySource.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			final IPropertyDescriptor propertyDescriptor = propertyDescriptors[i];
			if (propertyDescriptor != null) {
				final Object propertyID = getPropertyID(object);
				if (propertyID != null && propertyID.equals(propertyDescriptor.getId())) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	protected CellEditor getCellEditor(final Object object) {
		final IPropertySource propertySource = this.propertySourceProvider.getPropertySource(object);
		final IPropertyDescriptor[] propertyDescriptors = propertySource.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			final IPropertyDescriptor propertyDescriptor = propertyDescriptors[i];
			final Object propertyID = getPropertyID(object);
			if (propertyID != null && propertyID.equals(propertyDescriptor.getId())) {
				return propertyDescriptor.createPropertyEditor((Composite) getViewer().getControl());
			}
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
}
