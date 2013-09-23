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
package gov.redhawk.sca.ui;

import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor.PropertyValueWrapper;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.ui.provider.TransactionalPropertySource;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySource2;

/**
 * @since 7.0
 */
public class RedhawkUiAdapterFactory implements IAdapterFactory {

	public static class ScaPropertySource extends PropertySource {
		public ScaPropertySource(final Object object, final IItemPropertySource itemPropertySource) {
			super(object, itemPropertySource);
		}

		@Override
		public Object getPropertyValue(final Object propertyId) {
			final Object result = super.getPropertyValue(propertyId);
			if (result instanceof PropertyValueWrapper) {
				return ((PropertyValueWrapper) result).getEditableValue(null);
			} else {
				return result;
			}
		}
	}

	/** The Constant LIST. */
	private static final Class< ? >[] LIST = new Class[] { IPropertySource.class, IPropertySource2.class };

	private static ComposedAdapterFactory adapterFactory;

	public RedhawkUiAdapterFactory() {
		if (RedhawkUiAdapterFactory.adapterFactory == null) {
			RedhawkUiAdapterFactory.adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

			RedhawkUiAdapterFactory.adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
			RedhawkUiAdapterFactory.adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
			RedhawkUiAdapterFactory.adapterFactory.addAdapterFactory(new ScaItemProviderAdapterFactory());
		}
	}

	protected IItemPropertySource getItemPropertySource(final Object adaptableObject) {
		return (IItemPropertySource) RedhawkUiAdapterFactory.adapterFactory.adapt(adaptableObject, IItemPropertySource.class);
	}

	protected IPropertySource createPropertySource(final Object adaptableObject, final IItemPropertySource itemPropertySource) {
		return new ScaPropertySource(adaptableObject, itemPropertySource);
	}

	@Override
	public Object getAdapter(final Object input, @SuppressWarnings("rawtypes") final Class adapterType) {
		final Object adaptableObject = AdapterFactoryEditingDomain.unwrap(input);
		if (adaptableObject instanceof EObject) {
			final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(adaptableObject);
			if (editingDomain != null) {
				try {
	                return TransactionUtil.runExclusive(editingDomain,  new RunnableWithResult.Impl<Object>() {

						@Override
						public void run() {
							final IItemPropertySource itemPropertySource = getItemPropertySource(adaptableObject);
							final IPropertySource propertySource = createPropertySource(adaptableObject, itemPropertySource);
							setResult(new TransactionalPropertySource(editingDomain, propertySource));
                        } 
	                	
	                });
                } catch (InterruptedException e) {
	                return null;
                }
			}
			if (adapterType == IPropertySource.class || adapterType == IPropertySource2.class) {
				final IItemPropertySource itemPropertySource = getItemPropertySource(adaptableObject);
				final IPropertySource propertySource = createPropertySource(adaptableObject, itemPropertySource);
				return propertySource;
			}
		}
		return null;

	}

	@Override
	public Class< ? >[] getAdapterList() {
		return RedhawkUiAdapterFactory.LIST;
	}

}
