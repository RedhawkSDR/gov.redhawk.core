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
package gov.redhawk.internal.ui;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;

import BULKIO.StreamSRI;

/**
 * 
 */
public class StreamSRIAdapterFactory implements IAdapterFactory {

	private static Class< ? >[] list = new Class< ? >[] { IPropertySourceProvider.class, IPropertySource.class };

	private static class StreamSRIPropertySourceProvider implements IPropertySourceProvider {

		public IPropertySource getPropertySource(final Object object) {
			if (object instanceof StreamSRI) {
				return new StreamSRIPropertySource((StreamSRI) object);
			}
			return null;
		}

	}

	private static final StreamSRIPropertySourceProvider PROPERTY_PROVIDER = new StreamSRIPropertySourceProvider();

	/**
	 * {@inheritDoc}
	 */
	public Object getAdapter(final Object adaptableObject, @SuppressWarnings("rawtypes") final Class adapterType) {
		if (adaptableObject instanceof StreamSRI) {
			if (adapterType == IPropertySourceProvider.class) {
				return StreamSRIAdapterFactory.PROPERTY_PROVIDER;
			} else if (adapterType == IPropertySource.class) {
				return new StreamSRIPropertySource((StreamSRI) adaptableObject);
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public Class < ? >[] getAdapterList() {
		return StreamSRIAdapterFactory.list;
	}

}
