/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
// BEGIN GENERATED CODE
package gov.redhawk.ui.views.allocmgr.provider;

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.ui.views.allocmgr.AllocMgrPackage;
import gov.redhawk.ui.views.allocmgr.AllocationStatus;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link gov.redhawk.ui.views.allocmgr.AllocationStatus} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AllocationStatusItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource, ITableItemLabelProvider {

	// END GENERATED CODE

	/**
	 * The delay before the fetch job should run. Allows multiple requests to be batched.
	 */
	private static final long FETCH_DELAY_MS = 500;

	private FetchLabelsJob fetchLabelsJob = new FetchLabelsJob();

	// BEGIN GENERATED CODE

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AllocationStatusItemProvider(AdapterFactory adapterFactory) {
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

			addAllocationIDPropertyDescriptor(object);
			addRequestingDomainPropertyDescriptor(object);
			addDeviceIORPropertyDescriptor(object);
			addDeviceLabelPropertyDescriptor(object);
			addDeviceMgrIORPropertyDescriptor(object);
			addDeviceMgrLabelPropertyDescriptor(object);
			addSourceIDPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Allocation ID feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAllocationIDPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_AllocationStatus_allocationID_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_AllocationStatus_allocationID_feature", "_UI_AllocationStatus_type"),
			AllocMgrPackage.Literals.ALLOCATION_STATUS__ALLOCATION_ID, false, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Requesting Domain feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addRequestingDomainPropertyDescriptor(Object object) {
		// END GENERATED CODE
		Object image = getResourceLocator().getImage("full/obj16/ScaDomainManager");
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_AllocationStatus_requestingDomain_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_AllocationStatus_requestingDomain_feature", "_UI_AllocationStatus_type"),
			AllocMgrPackage.Literals.ALLOCATION_STATUS__REQUESTING_DOMAIN, false, false, false, image, null, null));
		// BEGIN GENERATED CODE
	}

	/**
	 * This adds a property descriptor for the Device IOR feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDeviceIORPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_AllocationStatus_deviceIOR_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_AllocationStatus_deviceIOR_feature", "_UI_AllocationStatus_type"),
			AllocMgrPackage.Literals.ALLOCATION_STATUS__DEVICE_IOR, false, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Device Label feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addDeviceLabelPropertyDescriptor(Object object) {
		// END GENERATED CODE
		Object image = getResourceLocator().getImage("full/obj16/ScaDevice");
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_AllocationStatus_deviceLabel_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_AllocationStatus_deviceLabel_feature", "_UI_AllocationStatus_type"),
			AllocMgrPackage.Literals.ALLOCATION_STATUS__DEVICE_LABEL, false, false, false, image, null, null));
		// BEGIN GENERATED CODE
	}

	/**
	 * This adds a property descriptor for the Device Mgr IOR feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDeviceMgrIORPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_AllocationStatus_deviceMgrIOR_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_AllocationStatus_deviceMgrIOR_feature", "_UI_AllocationStatus_type"),
			AllocMgrPackage.Literals.ALLOCATION_STATUS__DEVICE_MGR_IOR, false, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Device Mgr Label feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addDeviceMgrLabelPropertyDescriptor(Object object) {
		// END GENERATED CODE
		Object image = getResourceLocator().getImage("full/obj16/ScaDeviceManager");
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_AllocationStatus_deviceMgrLabel_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_AllocationStatus_deviceMgrLabel_feature", "_UI_AllocationStatus_type"),
			AllocMgrPackage.Literals.ALLOCATION_STATUS__DEVICE_MGR_LABEL, false, false, false, image, null, null));
		// BEGIN GENERATED CODE
	}

	/**
	 * This adds a property descriptor for the Source ID feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSourceIDPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_AllocationStatus_sourceID_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_AllocationStatus_sourceID_feature", "_UI_AllocationStatus_type"),
			AllocMgrPackage.Literals.ALLOCATION_STATUS__SOURCE_ID, false, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This returns AllocationStatus.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/AllocationStatus"));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean shouldComposeCreationImage() {
		return true;
	}

	// END GENERATED CODE

	@Override
	public Object getColumnImage(Object object, int columnIndex) {
		return null;
	}

	// BEGIN GENERATED CODE

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((AllocationStatus) object).getAllocationID();
		return label == null || label.length() == 0 ? getString("_UI_AllocationStatus_type") : getString("_UI_AllocationStatus_type") + " " + label;
	}

	// END GENERATED CODE

	@Override
	public String getColumnText(Object object, int columnIndex) {
		AllocationStatus status = (AllocationStatus) object;
		String label = null;
		switch (columnIndex) {
		case AllocMgrPackage.ALLOCATION_STATUS__ALLOCATION_ID:
			label = status.getAllocationID();
			break;
		case AllocMgrPackage.ALLOCATION_STATUS__REQUESTING_DOMAIN:
			label = status.getRequestingDomain();
			break;
		case AllocMgrPackage.ALLOCATION_STATUS__ALLOCATION_PROPS:
			break;
		case AllocMgrPackage.ALLOCATION_STATUS__DEVICE_IOR:
			label = status.getDeviceIOR();
			break;
		case AllocMgrPackage.ALLOCATION_STATUS__DEVICE_LABEL:
			label = status.getDeviceLabel();
			if (label == null) {
				fetchLabelsJob.addStatusWithoutLables(status);
				fetchLabelsJob.schedule(FETCH_DELAY_MS);
				label = status.getDeviceIOR();
			}
			break;
		case AllocMgrPackage.ALLOCATION_STATUS__DEVICE_MGR_IOR:
			label = status.getDeviceMgrIOR();
			break;
		case AllocMgrPackage.ALLOCATION_STATUS__DEVICE_MGR_LABEL:
			label = status.getDeviceMgrLabel();
			if (label == null) {
				fetchLabelsJob.addStatusWithoutLables(status);
				fetchLabelsJob.schedule(FETCH_DELAY_MS);
				label = status.getDeviceMgrIOR();
			}
			break;
		case AllocMgrPackage.ALLOCATION_STATUS__SOURCE_ID:
			label = status.getSourceID();
			break;
		default:
			label = "";
		}
		return (label != null) ? label : "";
	}

	// BEGIN GENERATED CODE

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

		switch (notification.getFeatureID(AllocationStatus.class)) {
		case AllocMgrPackage.ALLOCATION_STATUS__ALLOCATION_ID:
		case AllocMgrPackage.ALLOCATION_STATUS__REQUESTING_DOMAIN:
		case AllocMgrPackage.ALLOCATION_STATUS__ALLOCATION_PROPS:
		case AllocMgrPackage.ALLOCATION_STATUS__DEVICE_IOR:
		case AllocMgrPackage.ALLOCATION_STATUS__DEVICE_LABEL:
		case AllocMgrPackage.ALLOCATION_STATUS__DEVICE_MGR_IOR:
		case AllocMgrPackage.ALLOCATION_STATUS__DEVICE_MGR_LABEL:
		case AllocMgrPackage.ALLOCATION_STATUS__SOURCE_ID:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return AllocMgrEditPlugin.INSTANCE;
	}

	// END GENERATED CODE

	@Override
	public void dispose() {
		super.dispose();
		fetchLabelsJob.cancel();
	}

	/**
	 * @param domMgr The domain manager model object whose children will be used to help resolve labels.
	 */
	public void setDomainManager(ScaDomainManager domMgr) {
		fetchLabelsJob.setDomainManager(domMgr);
	}

	// BEGIN GENERATED CODE

}
