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
import gov.redhawk.model.sca.util.ModelUtil;

import java.util.Collection;
import java.util.List;

import mil.jpeojtrs.sca.prf.provider.AbstractPropertyItemProvider;
import mil.jpeojtrs.sca.prf.provider.PrfEditPlugin;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
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
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.swt.SWT;
import org.eclipse.ui.PlatformUI;

/**
 * This is the item provider adapter for a {@link gov.redhawk.model.sca.ScaAbstractProperty} object.
 * <!-- begin-user-doc -->
 * @since 7.0
 * <!-- end-user-doc -->
 * @generated
 */
public class ScaAbstractPropertyItemProvider extends IStatusProviderItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource, ITableItemLabelProvider, ITableItemColorProvider, IItemColorProvider {

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaAbstractPropertyItemProvider(AdapterFactory adapterFactory) {
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

			addDefinitionPropertyDescriptor(object);
			addDescriptionPropertyDescriptor(object);
			addIdPropertyDescriptor(object);
			addModePropertyDescriptor(object);
			addNamePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Definition feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDefinitionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ScaAbstractProperty_definition_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ScaAbstractProperty_definition_feature", "_UI_ScaAbstractProperty_type"),
			ScaPackage.Literals.SCA_ABSTRACT_PROPERTY__DEFINITION, false, false, false, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Description feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDescriptionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ScaAbstractProperty_description_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ScaAbstractProperty_description_feature", "_UI_ScaAbstractProperty_type"),
			ScaPackage.Literals.SCA_ABSTRACT_PROPERTY__DESCRIPTION, false, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Id feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ScaAbstractProperty_id_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ScaAbstractProperty_id_feature", "_UI_ScaAbstractProperty_type"),
			ScaPackage.Literals.SCA_ABSTRACT_PROPERTY__ID, false, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Mode feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addModePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ScaAbstractProperty_mode_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ScaAbstractProperty_mode_feature", "_UI_ScaAbstractProperty_type"),
			ScaPackage.Literals.SCA_ABSTRACT_PROPERTY__MODE, false, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ScaAbstractProperty_name_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ScaAbstractProperty_name_feature", "_UI_ScaAbstractProperty_type"),
			ScaPackage.Literals.SCA_ABSTRACT_PROPERTY__NAME, false, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((ScaAbstractProperty< ? >) object).getName();
		return label == null || label.length() == 0 ? getString("_UI_ScaAbstractProperty_type") : getString("_UI_ScaAbstractProperty_type") + " " + label;
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

		switch (notification.getFeatureID(ScaAbstractProperty.class)) {
		case ScaPackage.SCA_ABSTRACT_PROPERTY__NAME:
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
	public Object getForeground(Object object) {
		if (!ModelUtil.isSettable((ScaAbstractProperty< ? >) object)) {
			return PlatformUI.getWorkbench().getDisplay().getSystemColor(SWT.COLOR_DARK_GRAY);
		} else {
			return super.getForeground(object);
		}
	}

	@Override
	public Object getForeground(Object object, int columnIndex) {
		return getForeground(object);
	}

	/**
	 * {@inheritDoc}
	 * @generated NOT
	 */
	@Override
	public Object getColumnImage(Object object, int columnIndex) {
		switch (columnIndex) {
		case AbstractPropertyItemProvider.PROP_NAME_COLUMN:
			return getImage(object);
		case AbstractPropertyItemProvider.PROP_VALUE_COLUMN:
			return null;
		default:
			return super.getColumnImage(object, columnIndex);
		}
	}

	/**
	 * @since 12.0
	 */
	protected ResourceLocator getPrfResourceLocator() {
		return PrfEditPlugin.INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getColumnText(final Object object, final int columnIndex) {
		switch (columnIndex) {
		case AbstractPropertyItemProvider.PROP_VALUE_COLUMN:
			return getValueText((ScaAbstractProperty< ? >) object);
		case AbstractPropertyItemProvider.PROP_NAME_COLUMN:
			return getText(object);
		default:
			return super.getColumnText(object, columnIndex);
		}
	}

	/**
	 * @since 12.0
	 */
	protected String getValueText(ScaAbstractProperty< ? > object) {
		return "";
	}

}
