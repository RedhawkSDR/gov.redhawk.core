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
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.sca.util.PluginUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mil.jpeojtrs.sca.prf.Enumeration;
import mil.jpeojtrs.sca.prf.Enumerations;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.provider.RadixLabelProviderUtil;
import mil.jpeojtrs.sca.util.AnyUtils;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
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
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor.PropertyValueWrapper;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptorDecorator;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link gov.redhawk.model.sca.ScaSimpleProperty} object.
 * <!-- begin-user-doc -->
 * @since 7.0
 * <!-- end-user-doc -->
 * @generated
 */
public class ScaSimplePropertyItemProvider extends ScaAbstractPropertyItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource, ITableItemLabelProvider, ITableItemColorProvider, IItemColorProvider {
	
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaSimplePropertyItemProvider(AdapterFactory adapterFactory) {
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

			addValuePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	private class ValueItemPropertyDescriptor extends ItemPropertyDescriptorDecorator {
		
		public ValueItemPropertyDescriptor(Object object, IItemPropertyDescriptor itemPropertyDescriptor) {
	        super(object, itemPropertyDescriptor);
        }

		@Override
		public IItemLabelProvider getLabelProvider(final Object thisObject) {
			final ScaSimpleProperty property = (ScaSimpleProperty) thisObject;
			final IItemLabelProvider lp = super.getLabelProvider(thisObject);
			return new IItemLabelProvider() {
				
				public String getText(Object object) {
					if (object instanceof PropertyValueWrapper) {
						PropertyValueWrapper wrapper = (PropertyValueWrapper) object;
						return getValueText(property, wrapper.getEditableValue(null));
					} else {
						return getValueText(property, object);
					}
				}
				
				public Object getImage(Object object) {
					return lp.getImage(property);
				}
			};
		}

		@Override
		public Collection< ? > getChoiceOfValues(Object thisObject) {
			ScaSimpleProperty property = (ScaSimpleProperty) thisObject;
			Simple def = property.getDefinition();
			if (def != null) {
				Enumerations enums = def.getEnumerations();
				if (enums != null) {
					List<Object> result = new ArrayList<Object>();
					for (Enumeration e : enums.getEnumeration()) {
						result.add(AnyUtils.convertString(e.getValue(), def.getType().getLiteral(), def.getComplex()));
					}
					return result;
				}
			}
			return super.getChoiceOfValues(thisObject);
		}
	}
	
	/**
	 * This adds a property descriptor for the Value feature.
	 * <!-- begin-user-doc -->
	 * @since 9.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addValuePropertyDescriptorGen(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ScaSimpleProperty_value_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ScaSimpleProperty_value_feature", "_UI_ScaSimpleProperty_type"),
				 ScaPackage.Literals.SCA_SIMPLE_PROPERTY__VALUE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}
	
	/**
	 * This adds a property descriptor for the Value feature.
	 * <!-- begin-user-doc -->
	 * @since 9.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 * 
	 */
	protected void addValuePropertyDescriptor(Object object) {
		// END GENERATED CODE
		final ItemPropertyDescriptor defaultDescriptor = createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
		        getResourceLocator(),
		        getString("_UI_ScaSimpleProperty_value_feature"),
		        getString("_UI_PropertyDescriptor_description", "_UI_ScaSimpleProperty_value_feature", "_UI_ScaSimpleProperty_type"),
		        ScaPackage.Literals.SCA_SIMPLE_PROPERTY__VALUE,
		        true,
		        false,
		        false,
		        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
		        null,
		        null);

		this.itemPropertyDescriptors.add(new ValueItemPropertyDescriptor(object, defaultDescriptor));
		// BEGIN GENERATED CODE
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getText(final Object object) {
		// END GENERATED CODE
		String label = ((ScaSimpleProperty) object).getName();
		if (label == null) {
			label = ((ScaSimpleProperty) object).getId();
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

		switch (notification.getFeatureID(ScaSimpleProperty.class)) {
			case ScaPackage.SCA_SIMPLE_PROPERTY__VALUE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
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
		ScaSimpleProperty simple = (ScaSimpleProperty) object; 
	    return getValueText(simple, simple.getValue());
	}
	
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getPrfResourceLocator().getImage("full/obj16/Simple"));
	}
	
	/**
     * @since 12.0
     */
	public static String getValueText(ScaSimpleProperty scaProperty, Object value) {
		String retVal = null;
		Simple property = scaProperty.getDefinition();
		if (value == null || value.toString().length() == 0) {
			return "";
		}
		if (property == null) {
			return value.toString();
		}
		
		if (property.getEnumerations() != null) {
			for (Enumeration e : property.getEnumerations().getEnumeration()) {
				Object enumValue = AnyUtils.convertString(e.getValue(), property.getType().getLiteral(), property.getComplex());
				if (PluginUtil.equals(enumValue, value)) {
					retVal = e.getLabel();
					break;
				}
			}
		} else if (RadixLabelProviderUtil.supports(property.getType(), property.isComplex())) {
			retVal = RadixLabelProviderUtil.getText(value, RadixLabelProviderUtil.getRadix(property.getValue()));
		}
		
		if (retVal == null) {
			retVal = value.toString();
		}
		
		final String units = property.getUnits();
		if (units != null) {
			return retVal + " " + units;
		} else {
			return retVal;
		}
	}

}
