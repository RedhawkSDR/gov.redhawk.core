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

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;

/**
 * @since 9.0
 */
public abstract class AbstractPropertyColumnLabelProvider extends ColumnLabelProvider {
	protected IPropertySourceProvider propertySourceProvider;

	/**
	 * Creates a new instance based on the given property source provider and
	 * property id.
	 * 
	 * @param propertySourceProvider
	 *            the property source provider
	 * @param propertyID
	 *            the property id
	 */
	public AbstractPropertyColumnLabelProvider(final IPropertySourceProvider propertySourceProvider) {
		this.propertySourceProvider = propertySourceProvider;
	}

	protected abstract Object getPropertyID(Object object);

	@Override
	public String getText(final Object object) {
		final IPropertySource propertySource = this.propertySourceProvider.getPropertySource(object);
		final IPropertyDescriptor[] propertyDescriptors = propertySource.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			final IPropertyDescriptor propertyDescriptor = propertyDescriptors[i];
			final Object propertyID = getPropertyID(object);
			if (propertyID != null && propertyID.equals(propertyDescriptor.getId())) {
				return propertyDescriptor.getLabelProvider().getText(propertySource.getPropertyValue(propertyID));
			}
		}
		return ""; //$NON-NLS-1$
	}

	@Override
	public Image getImage(final Object object) {
		final IPropertySource propertySource = this.propertySourceProvider.getPropertySource(object);
		final IPropertyDescriptor[] propertyDescriptors = propertySource.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			final IPropertyDescriptor propertyDescriptor = propertyDescriptors[i];
			final Object propertyID = getPropertyID(object);
			if (propertyID != null && propertyID.equals(propertyDescriptor.getId())) {
				return propertyDescriptor.getLabelProvider().getImage(propertySource.getPropertyValue(propertyID));
			}
		}
		return null;
	}
}
