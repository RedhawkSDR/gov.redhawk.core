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
import gov.redhawk.frontend.ListenerAllocation;

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
 * This is the item provider adapter for a {@link gov.redhawk.frontend.ListenerAllocation} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ListenerAllocationItemProvider extends ItemProviderAdapter
		implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource,
		ITableItemLabelProvider, ITableItemColorProvider, ITableItemFontProvider, IItemColorProvider, IItemFontProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ListenerAllocationItemProvider(AdapterFactory adapterFactory) {
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

			addTunerStatusPropertyDescriptor(object);
			addListenerIDPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Tuner Status feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTunerStatusPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ListenerAllocation_tunerStatus_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ListenerAllocation_tunerStatus_feature", "_UI_ListenerAllocation_type"),
			FrontendPackage.Literals.LISTENER_ALLOCATION__TUNER_STATUS, true, false, true, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Listener ID feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addListenerIDPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ListenerAllocation_listenerID_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ListenerAllocation_listenerID_feature", "_UI_ListenerAllocation_type"),
			FrontendPackage.Literals.LISTENER_ALLOCATION__LISTENER_ID, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	private boolean isPlotListener(Object obj) {
		if (!(obj instanceof ListenerAllocation)) {
			return false;
		}
		ListenerAllocation listener = (ListenerAllocation) obj;
		if (listener.getListenerID().startsWith("Plot_")) {
			return true;
		}
		return false;
	}

	/**
	 * This returns ListenerAllocation.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Object getImage(Object object) {
		if (isPlotListener(object)) {
			return overlayImage(object, getResourceLocator().getImage("full/obj16/PlotListener"));
		}
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ListenerEar"));
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
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {
		String label = ((ListenerAllocation) object).getListenerID();
		if (isPlotListener(object)) {
			return label;
		}
		int index = label.indexOf(":");
		if (index > -1) {
			label = label.substring(0, index);
		}
		return label;
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

		switch (notification.getFeatureID(ListenerAllocation.class)) {
		case FrontendPackage.LISTENER_ALLOCATION__LISTENER_ID:
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
