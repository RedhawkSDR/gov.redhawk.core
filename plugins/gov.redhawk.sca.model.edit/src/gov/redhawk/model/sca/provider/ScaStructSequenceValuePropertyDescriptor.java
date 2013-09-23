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
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaStructSequenceProperty;
import gov.redhawk.model.sca.commands.ScaModelCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptorDecorator;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.transaction.util.TransactionUtil;

/**
 * 
 */
class ScaStructSequenceValuePropertyDescriptor extends ItemPropertyDescriptorDecorator {

	private static class StructPropertyDecorator extends ItemPropertyDescriptorDecorator {


		private AdapterFactory factory;

		public StructPropertyDecorator(Object object, IItemPropertyDescriptor itemPropertyDescriptor, AdapterFactory factory) {
			super(object, itemPropertyDescriptor);
			this.factory = factory;
		}

		@Override
		public String getDisplayName(Object thisObject) {
			IItemLabelProvider lp = (IItemLabelProvider) factory.adapt(thisObject, IItemLabelProvider.class);
			return lp.getText(thisObject);
		}

		@Override
		public String getId(Object thisObject) {
			return getDisplayName(thisObject);
		}

		@Override
		public void setPropertyValue(Object thisObject, Object value) {
			// Do Nothing
		}
	}

	private static class StructValuePropertySource extends ItemProviderAdapter implements IItemPropertySource {
		private List<IItemPropertyDescriptor> itemPropertyDescriptors;
		private ScaStructSequenceProperty structSequenceProperty;

		public StructValuePropertySource(AdapterFactory factory, ScaStructSequenceProperty property) {
			super(factory);
			this.structSequenceProperty = property;
		}

		@Override
		public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
			if (itemPropertyDescriptors == null) {
				itemPropertyDescriptors = new ArrayList<IItemPropertyDescriptor>();
				addStructValuePropertyDescriptors(structSequenceProperty);
			}
			return itemPropertyDescriptors;
		}

		protected void addStructValuePropertyDescriptors(Object object) {
			for (final ScaAbstractProperty< ? > prop : structSequenceProperty.getStructs()) {
				IItemPropertySource source = (IItemPropertySource) getRootAdapterFactory().adapt(prop, IItemPropertySource.class);
				List<IItemPropertyDescriptor> descriptors = source.getPropertyDescriptors(prop);
				for (final IItemPropertyDescriptor desc : descriptors) {
					itemPropertyDescriptors.add(new StructPropertyDecorator(prop, desc, adapterFactory));
				}

			}
		}

		@Override
		public void setPropertyValue(Object object, String property, Object value) {
			// Do Nothing
		}
	}

	private final AdapterFactory factory;

	public ScaStructSequenceValuePropertyDescriptor(Object object, IItemPropertyDescriptor itemPropertyDescriptor, AdapterFactory factory) {
		super(object, itemPropertyDescriptor);
		this.factory = factory;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getPropertyValue(final Object object) {

		return new StructValuePropertySource(this.factory, (ScaStructSequenceProperty) object);
	}

	@Override
	public IItemLabelProvider getLabelProvider(Object thisObject) {
		final IItemLabelProvider lp = super.getLabelProvider(thisObject);
		return new IItemLabelProvider() {

			@Override
			public String getText(Object object) {
				if (object instanceof StructValuePropertySource) {
					return "";
				}
				return lp.getText(object);
			}

			@Override
			public Object getImage(Object object) {
				if (object instanceof StructValuePropertySource) {
					return null;
				}
				return lp.getImage(object);
			}
		};
	}

	@Override
	public void setPropertyValue(final Object thisObject, final Object value) {
		Object feature = itemPropertyDescriptor.getFeature(thisObject);
		if (feature == ScaPackage.Literals.SCA_STRUCT_SEQUENCE_PROPERTY__STRUCTS && value instanceof Collection< ? >) {
			final EObject eObject = (EObject) thisObject;
			if (TransactionUtil.getEditingDomain(eObject) == null) {
				eObject.eSet(ScaPackage.Literals.SCA_STRUCT_SEQUENCE_PROPERTY__STRUCTS, value);
			} else {
				ScaModelCommand.execute(eObject, new ScaModelCommand() {

					@Override
					public void execute() {
						eObject.eSet(ScaPackage.Literals.SCA_STRUCT_SEQUENCE_PROPERTY__STRUCTS, value);
					}
				});
			}
			//			super.setPropertyValue(thisObject, value);
		}
		// Do nothing
	}
}
