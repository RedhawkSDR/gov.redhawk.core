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
package gov.redhawk.frontend.provider;

import gov.redhawk.frontend.FrontendPackage;
import gov.redhawk.frontend.UnallocatedTunerContainer;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemColorProvider;
import org.eclipse.emf.edit.provider.IItemFontProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITableItemColorProvider;
import org.eclipse.emf.edit.provider.ITableItemFontProvider;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link gov.redhawk.frontend.UnallocatedTunerContainer} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class UnallocatedTunerContainerItemProvider extends ItemProviderAdapter
		implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource,
		ITableItemLabelProvider, ITableItemColorProvider, ITableItemFontProvider, IItemColorProvider, IItemFontProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnallocatedTunerContainerItemProvider(AdapterFactory adapterFactory) {
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

			addTunerContainerPropertyDescriptor(object);
			addTunerTypePropertyDescriptor(object);
			addCountPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Tuner Container feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTunerContainerPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_UnallocatedTunerContainer_tunerContainer_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_UnallocatedTunerContainer_tunerContainer_feature", "_UI_UnallocatedTunerContainer_type"),
			FrontendPackage.Literals.UNALLOCATED_TUNER_CONTAINER__TUNER_CONTAINER, true, false, true, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Tuner Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTunerTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_UnallocatedTunerContainer_tunerType_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_UnallocatedTunerContainer_tunerType_feature", "_UI_UnallocatedTunerContainer_type"),
			FrontendPackage.Literals.UNALLOCATED_TUNER_CONTAINER__TUNER_TYPE, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Count feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCountPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_UnallocatedTunerContainer_count_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_UnallocatedTunerContainer_count_feature", "_UI_UnallocatedTunerContainer_type"),
			FrontendPackage.Literals.UNALLOCATED_TUNER_CONTAINER__COUNT, true, false, false, ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE, null, null));
	}

	/**
	 * This returns UnallocatedTunerContainer.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated_NOT
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/TuningFork"));
	}

	/**
	 * This returns <code>getImage(object)</code> for the column index <code>0</code> or
	 * <code>super.getImage(object)</code> otherwise.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getText(Object)
	 * @see #getColumnText(Object, int)
	 * @generated
	 */
	@Override
	public Object getColumnImage(Object object, int columnIndex) {
		// TODO: implement this method to return appropriate information for each column.
		// Ensure that you remove @generated or mark it @generated NOT
		return columnIndex == 0 ? getImage(object) : super.getImage(object);
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generate_NOT
	 */
	@Override
	public String getText(Object object) {
		UnallocatedTunerContainer container = (UnallocatedTunerContainer) object;

		// Get a count of how many tuners are unallocated
		int unallocatedCount = container.getCount();

		String label = container.getTunerType();
		return label == null || label.length() == 0 ? getString("_UI_UnallocatedTunerContainer_type")
			: "Unallocated " + label + ": " + unallocatedCount + " available";
	}

	/**
	 * This returns <code>getText(object)</code> for the column index <code>0</code> or
	 * <code>super.getText(object)</code> otherwise.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImage(Object)
	 * @see #getColumnImage(Object, int)
	 * @generated
	 */
	@Override
	public String getColumnText(Object object, int columnIndex) {
		// TODO: implement this method to return appropriate information for each column.
		// Ensure that you remove @generated or mark it @generated NOT
		return columnIndex == 0 ? getText(object) : super.getText(object);
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

		switch (notification.getFeatureID(UnallocatedTunerContainer.class)) {
		case FrontendPackage.UNALLOCATED_TUNER_CONTAINER__TUNER_TYPE:
		case FrontendPackage.UNALLOCATED_TUNER_CONTAINER__COUNT:
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

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return FrontendEditPlugin.INSTANCE;
	}
}
