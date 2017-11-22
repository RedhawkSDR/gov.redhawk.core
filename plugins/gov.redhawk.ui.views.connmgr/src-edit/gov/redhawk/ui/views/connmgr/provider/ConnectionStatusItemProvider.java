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
package gov.redhawk.ui.views.connmgr.provider;

import gov.redhawk.ui.views.connmgr.ConnMgrPackage;
import gov.redhawk.ui.views.connmgr.ConnectionStatus;
import mil.jpeojtrs.sca.util.DceUuidUtil;
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
 * This is the item provider adapter for a {@link gov.redhawk.ui.views.connmgr.ConnectionStatus} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ConnectionStatusItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource, ITableItemLabelProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectionStatusItemProvider(AdapterFactory adapterFactory) {
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

			addConnectionRecordIDPropertyDescriptor(object);
			addConnectionIDPropertyDescriptor(object);
			addRequesterIDPropertyDescriptor(object);
			addConnectedPropertyDescriptor(object);
			addSourceIORPropertyDescriptor(object);
			addSourceEntityNamePropertyDescriptor(object);
			addSourcePortNamePropertyDescriptor(object);
			addSourceRepoIDPropertyDescriptor(object);
			addTargetIORPropertyDescriptor(object);
			addTargetEntityNamePropertyDescriptor(object);
			addTargetPortNamePropertyDescriptor(object);
			addTargetRepoIDPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Connection Record ID feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addConnectionRecordIDPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ConnectionStatus_connectionRecordID_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ConnectionStatus_connectionRecordID_feature", "_UI_ConnectionStatus_type"),
			ConnMgrPackage.Literals.CONNECTION_STATUS__CONNECTION_RECORD_ID, false, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Connection ID feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addConnectionIDPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ConnectionStatus_connectionID_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ConnectionStatus_connectionID_feature", "_UI_ConnectionStatus_type"),
			ConnMgrPackage.Literals.CONNECTION_STATUS__CONNECTION_ID, false, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Requester ID feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRequesterIDPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ConnectionStatus_requesterID_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ConnectionStatus_requesterID_feature", "_UI_ConnectionStatus_type"),
			ConnMgrPackage.Literals.CONNECTION_STATUS__REQUESTER_ID, false, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Connected feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addConnectedPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ConnectionStatus_connected_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ConnectionStatus_connected_feature", "_UI_ConnectionStatus_type"),
			ConnMgrPackage.Literals.CONNECTION_STATUS__CONNECTED, false, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Source IOR feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSourceIORPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ConnectionStatus_sourceIOR_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ConnectionStatus_sourceIOR_feature", "_UI_ConnectionStatus_type"),
			ConnMgrPackage.Literals.CONNECTION_STATUS__SOURCE_IOR, false, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Source Entity Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSourceEntityNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ConnectionStatus_sourceEntityName_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ConnectionStatus_sourceEntityName_feature", "_UI_ConnectionStatus_type"),
			ConnMgrPackage.Literals.CONNECTION_STATUS__SOURCE_ENTITY_NAME, false, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Source Port Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSourcePortNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ConnectionStatus_sourcePortName_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ConnectionStatus_sourcePortName_feature", "_UI_ConnectionStatus_type"),
			ConnMgrPackage.Literals.CONNECTION_STATUS__SOURCE_PORT_NAME, false, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Source Repo ID feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSourceRepoIDPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ConnectionStatus_sourceRepoID_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ConnectionStatus_sourceRepoID_feature", "_UI_ConnectionStatus_type"),
			ConnMgrPackage.Literals.CONNECTION_STATUS__SOURCE_REPO_ID, false, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Target IOR feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTargetIORPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ConnectionStatus_targetIOR_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ConnectionStatus_targetIOR_feature", "_UI_ConnectionStatus_type"),
			ConnMgrPackage.Literals.CONNECTION_STATUS__TARGET_IOR, false, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Target Entity Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTargetEntityNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ConnectionStatus_targetEntityName_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ConnectionStatus_targetEntityName_feature", "_UI_ConnectionStatus_type"),
			ConnMgrPackage.Literals.CONNECTION_STATUS__TARGET_ENTITY_NAME, false, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Target Port Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTargetPortNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ConnectionStatus_targetPortName_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ConnectionStatus_targetPortName_feature", "_UI_ConnectionStatus_type"),
			ConnMgrPackage.Literals.CONNECTION_STATUS__TARGET_PORT_NAME, false, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Target Repo ID feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTargetRepoIDPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ConnectionStatus_targetRepoID_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ConnectionStatus_targetRepoID_feature", "_UI_ConnectionStatus_type"),
			ConnMgrPackage.Literals.CONNECTION_STATUS__TARGET_REPO_ID, false, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This returns ConnectionStatus.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ConnectionStatus"));
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
		ConnectionStatus status = (ConnectionStatus) object;
		switch (columnIndex) {
		case ConnMgrPackage.CONNECTION_STATUS__CONNECTED:
			if (status.isConnected()) {
				return getResourceLocator().getImage("connected.png");
			}
			return null;
		default:
			return null;
		}
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
		String label = ((ConnectionStatus) object).getConnectionRecordID();
		return label == null || label.length() == 0 ? getString("_UI_ConnectionStatus_type") : getString("_UI_ConnectionStatus_type") + " " + label;
	}

	// END GENERATED CODE

	@Override
	public String getColumnText(Object object, int columnIndex) {
		ConnectionStatus status = (ConnectionStatus) object;
		String label = null;
		switch (columnIndex) {
		case ConnMgrPackage.CONNECTION_STATUS__CONNECTION_RECORD_ID:
			label = status.getConnectionRecordID();
			break;
		case ConnMgrPackage.CONNECTION_STATUS__CONNECTION_ID:
			label = status.getConnectionID();
			break;
		case ConnMgrPackage.CONNECTION_STATUS__REQUESTER_ID:
			label = status.getRequesterID();
			break;
		case ConnMgrPackage.CONNECTION_STATUS__CONNECTED:
			label = "";
			break;
		case ConnMgrPackage.CONNECTION_STATUS__SOURCE_ENTITY_NAME:
			label = status.getSourceEntityName();
			label = DceUuidUtil.trimPrefix(label);
			break;
		case ConnMgrPackage.CONNECTION_STATUS__SOURCE_PORT_NAME:
			label = status.getSourcePortName();
			break;
		case ConnMgrPackage.CONNECTION_STATUS__SOURCE_REPO_ID:
			label = status.getSourceRepoID();
			break;
		case ConnMgrPackage.CONNECTION_STATUS__TARGET_ENTITY_NAME:
			label = status.getTargetEntityName();
			label = DceUuidUtil.trimPrefix(label);
			break;
		case ConnMgrPackage.CONNECTION_STATUS__TARGET_PORT_NAME:
			label = status.getTargetPortName();
			break;
		case ConnMgrPackage.CONNECTION_STATUS__TARGET_REPO_ID:
			label = status.getTargetRepoID();
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

		switch (notification.getFeatureID(ConnectionStatus.class)) {
		case ConnMgrPackage.CONNECTION_STATUS__CONNECTION_RECORD_ID:
		case ConnMgrPackage.CONNECTION_STATUS__CONNECTION_ID:
		case ConnMgrPackage.CONNECTION_STATUS__REQUESTER_ID:
		case ConnMgrPackage.CONNECTION_STATUS__CONNECTED:
		case ConnMgrPackage.CONNECTION_STATUS__SOURCE_IOR:
		case ConnMgrPackage.CONNECTION_STATUS__SOURCE_ENTITY_NAME:
		case ConnMgrPackage.CONNECTION_STATUS__SOURCE_PORT_NAME:
		case ConnMgrPackage.CONNECTION_STATUS__SOURCE_REPO_ID:
		case ConnMgrPackage.CONNECTION_STATUS__TARGET_IOR:
		case ConnMgrPackage.CONNECTION_STATUS__TARGET_ENTITY_NAME:
		case ConnMgrPackage.CONNECTION_STATUS__TARGET_PORT_NAME:
		case ConnMgrPackage.CONNECTION_STATUS__TARGET_REPO_ID:
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
		return ConnMgrEditPlugin.INSTANCE;
	}

}
