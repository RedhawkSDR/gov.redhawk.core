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

import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;

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

	/**
	 * @since 11.1
	 */
	protected IPropertySource2 createPropertySource(final Object adaptableObject, final IItemPropertySource itemPropertySource) {
		return new ScaPropertySource(adaptableObject, itemPropertySource);
	}

	@Override
	public < T > T getAdapter(Object input, Class<T> adapterType) {
		final Object adaptableObject = AdapterFactoryEditingDomain.unwrap(input);
		if (adaptableObject instanceof EObject && (adapterType == IPropertySource.class || adapterType == IPropertySource2.class)) {
			return adapterType.cast(getPropertySourceAdapter((EObject) adaptableObject));
		}
		return null;
	}

	private IPropertySource2 getPropertySourceAdapter(EObject adaptableObject) {
		final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(adaptableObject);
		if (editingDomain != null) {
			try {
				IPropertySource2 propertySource = TransactionUtil.runExclusive(editingDomain, new RunnableWithResult.Impl<IPropertySource2>() {
					@Override
					public void run() {
						IItemPropertySource itemPropertySource = getItemPropertySource(adaptableObject);
						IPropertySource2 propertySource = createPropertySource(adaptableObject, itemPropertySource);
						setResult(new TransactionalPropertySource(editingDomain, propertySource));
					}
				});
				return propertySource;
			} catch (InterruptedException e) {
				return null;
			}
		} else {
			IItemPropertySource itemPropertySource = getItemPropertySource(adaptableObject);
			IPropertySource2 propertySource = createPropertySource(adaptableObject, itemPropertySource);
			return propertySource;
		}

	}

	@Override
	public Class< ? >[] getAdapterList() {
		return RedhawkUiAdapterFactory.LIST;
	}

}
