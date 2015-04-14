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
package gov.redhawk.model.sca.provider;

import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaStructProperty;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptorDecorator;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;

/**
 * 
 */
class ScaStructValuePropertyDescriptor extends ItemPropertyDescriptorDecorator {

	private static class SimplePropertyDecorator extends ItemPropertyDescriptorDecorator {

		private String displayName;

		public SimplePropertyDecorator(Object object, IItemPropertyDescriptor itemPropertyDescriptor, String displayName) {
			super(object, itemPropertyDescriptor);
			this.displayName = displayName;
		}

		@Override
		public String getDisplayName(Object thisObject) {
			return this.displayName;
		}

		@Override
		public String getId(Object thisObject) {
			return getDisplayName(this.object);

		}
	}

	private static class SimplesValuePropertySource extends ItemProviderAdapter implements IItemPropertySource {
		private List<IItemPropertyDescriptor> itemPropertyDescriptors;
		private ScaStructProperty structProperty;

		public SimplesValuePropertySource(AdapterFactory factory, ScaStructProperty property) {
			super(factory);
			this.structProperty = property;
		}

		@Override
		public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
			if (itemPropertyDescriptors == null) {
				itemPropertyDescriptors = new ArrayList<IItemPropertyDescriptor>();
				addSimpleValuePropertyDescriptors(structProperty);
				addSequenceValuePropertyDescriptors(structProperty);
			}
			return itemPropertyDescriptors;
		}

		protected void addSimpleValuePropertyDescriptors(Object object) {
			for (final ScaAbstractProperty< ? > prop : structProperty.getSimples()) {
				IItemPropertySource source = (IItemPropertySource) getRootAdapterFactory().adapt(prop, IItemPropertySource.class);
				List<IItemPropertyDescriptor> descriptors = source.getPropertyDescriptors(prop);
				for (final IItemPropertyDescriptor desc : descriptors) {
					IItemLabelProvider lp = (IItemLabelProvider) getRootAdapterFactory().adapt(prop, IItemLabelProvider.class);
					String displayName = lp.getText(prop);
					itemPropertyDescriptors.add(new SimplePropertyDecorator(prop, desc, displayName));
				}

			}
		}
		
		protected void addSequenceValuePropertyDescriptors(Object object) {
			for (final ScaAbstractProperty< ? > prop : structProperty.getSequences()) {
				IItemPropertySource source = (IItemPropertySource) getRootAdapterFactory().adapt(prop, IItemPropertySource.class);
				List<IItemPropertyDescriptor> descriptors = source.getPropertyDescriptors(prop);
				for (final IItemPropertyDescriptor desc : descriptors) {
					IItemLabelProvider lp = (IItemLabelProvider) getRootAdapterFactory().adapt(prop, IItemLabelProvider.class);
					String displayName = lp.getText(prop);
					itemPropertyDescriptors.add(new SimplePropertyDecorator(prop, desc, displayName));
				}

			}
		}
		
		@Override
		public void setPropertyValue(Object object, String property, Object value) {
		    // Do nothing
		}
	}

	private final AdapterFactory factory;

	public ScaStructValuePropertyDescriptor(Object object, IItemPropertyDescriptor itemPropertyDescriptor, AdapterFactory factory) {
		super(object, itemPropertyDescriptor);
		this.factory = factory;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getPropertyValue(final Object object) {
		return new SimplesValuePropertySource(this.factory, (ScaStructProperty) object);
	}

	@Override
	public IItemLabelProvider getLabelProvider(Object thisObject) {
		final IItemLabelProvider lp = super.getLabelProvider(thisObject);
		return new IItemLabelProvider() {

			@Override
			public String getText(Object object) {
				if (object instanceof SimplesValuePropertySource) {
					return "";
				}
				return lp.getText(object);
			}

			@Override
			public Object getImage(Object object) {
				if (object instanceof SimplesValuePropertySource) {
					return null;
				}
				return lp.getImage(object);
			}
		};
	}

	@Override
	public void setPropertyValue(Object thisObject, Object value) {
		// PASS Do nothing here since the value should already be updated and the listener of the Simple Property takes care of setting the value on the server
	}

}
