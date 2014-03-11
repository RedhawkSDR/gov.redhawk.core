/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

// BEGIN GENERATED CODE
package gov.redhawk.model.sca.provider;

import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaSimpleSequenceProperty;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.provider.RadixLabelProviderUtil;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemColorProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITableItemColorProvider;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptorDecorator;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link gov.redhawk.model.sca.ScaSimpleSequenceProperty} object.
 * <!-- begin-user-doc -->
 * @since 7.0
 * <!-- end-user-doc -->
 * @generated
 */
public class ScaSimpleSequencePropertyItemProvider extends ScaAbstractPropertyItemProvider implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource, ITableItemLabelProvider, ITableItemColorProvider,
		IItemColorProvider {

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaSimpleSequencePropertyItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addValuesPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	private static class ScaSimpleSequenceValueDecorator extends ItemPropertyDescriptorDecorator {
		// END GENERATED CODE

		public ScaSimpleSequenceValueDecorator(Object object, IItemPropertyDescriptor itemPropertyDescriptor) {
			super(object, itemPropertyDescriptor);
		}

		@Override
		public IItemLabelProvider getLabelProvider(final Object thisObject) {
			final ScaSimpleSequenceProperty property = (ScaSimpleSequenceProperty) thisObject;

			final IItemLabelProvider lp = super.getLabelProvider(thisObject);
			return new IItemLabelProvider() {

				@Override
				public String getText(Object object) {
					List< ? > value = null;
					if (object != null && object.getClass().isArray()) {
						value = Arrays.asList((Object[]) object);
					} else if (object instanceof List< ? >) {
						value = (List< ? >) object;
					}
					return getValueText(property, value);
				}

				@Override
				public Object getImage(Object object) {
					return lp.getImage(object);
				}
			};
		}

		// BEGIN GENERATED CODE
	}

	/**
	 * @deprecated Use {@link #addValuesPropertyDescriptor(Object)}
	 */
	@Deprecated
	protected void addValuePropertyDescriptor(Object object) {
		addValuesPropertyDescriptor(object);
	}

	/**
	 * This adds a property descriptor for the Values feature.
	 * <!-- begin-user-doc -->
	 * @since 12.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addValuesPropertyDescriptorGen(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ScaSimpleSequenceProperty_values_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ScaSimpleSequenceProperty_values_feature", "_UI_ScaSimpleSequenceProperty_type"),
			ScaPackage.Literals.SCA_SIMPLE_SEQUENCE_PROPERTY__VALUES, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Current Value feature.
	 * <!-- begin-user-doc -->
	 * @since 11.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 * 
	 */
	protected void addValuesPropertyDescriptor(Object object) {
		// END GENERATED CODE
		final ItemPropertyDescriptor defaultDescriptor = createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
			getResourceLocator(), getString("_UI_ScaSimpleSequenceProperty_values_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ScaSimpleSequenceProperty_values_feature", "_UI_ScaSimpleSequenceProperty_type"),
			ScaPackage.Literals.SCA_SIMPLE_SEQUENCE_PROPERTY__VALUES, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null);

		this.itemPropertyDescriptors.add(new ScaSimpleSequenceValueDecorator(object, defaultDescriptor));
		// BEGIN GENERATED CODE
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection< ? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(ScaPackage.Literals.SCA_SIMPLE_SEQUENCE_PROPERTY__VALUES);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {
		// END GENERATED CODE
		String label = ((ScaSimpleSequenceProperty) object).getName();
		if (label == null) {
			label = ((ScaSimpleSequenceProperty) object).getId();
		}
		return label;
		// BEGIN GENERATED CODE
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(ScaSimpleSequenceProperty.class)) {
		case ScaPackage.SCA_SIMPLE_SEQUENCE_PROPERTY__VALUES:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
			return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

	@Override
	protected String getValueText(ScaAbstractProperty< ? > object) {
		ScaSimpleSequenceProperty prop = (ScaSimpleSequenceProperty) object;
		return getValueText(prop, prop.getValues());
	}

	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getPrfResourceLocator().getImage("full/obj16/SimpleSequence"));
	}

	/**
	 * @since 12.0
	 */
	public static String getValueText(ScaSimpleSequenceProperty scaProperty, List< ? > value) {
		String retVal = null;
		SimpleSequence property = scaProperty.getDefinition();

		if (value == null || value.isEmpty()) {
			retVal = Collections.emptyList().toString();
		} else {
			if (property == null) {
				retVal = value.toString();
			} else if (RadixLabelProviderUtil.supports(property.getType(), property.isComplex())) {
				String[] defValue = new String[0];
				if (property.getValues() != null && !property.getValues().getValue().isEmpty()) {
					defValue = property.getValues().getValue().toArray(defValue);
				}
				retVal = RadixLabelProviderUtil.getText(value.toArray(), RadixLabelProviderUtil.getRadix(defValue));
			} else {
				retVal = value.toString();
			}
		}

		final String units;
		if (property != null) {
			units = property.getUnits();
		} else {
			units = null;
		}

		if (units != null) {
			return retVal + " " + units;
		} else {
			return retVal;
		}
	}

}
