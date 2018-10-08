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

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaPackage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link gov.redhawk.model.sca.ScaDomainManager} object.
 * <!-- begin-user-doc
 * --> <!-- end-user-doc -->
 * @generated
 */
public class ScaDomainManagerItemProvider extends ScaPropertyContainerItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaDomainManagerItemProvider(AdapterFactory adapterFactory) {
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

			addConnectionPropertiesPropertyDescriptor(object);
			addAutoConnectPropertyDescriptor(object);
			addConnectedPropertyDescriptor(object);
			addIdentifierPropertyDescriptor(object);
			addNamePropertyDescriptor(object);
			addRootContextPropertyDescriptor(object);
			addStatePropertyDescriptor(object);
			addProfilePropertyDescriptor(object);
			addLocalNamePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Connection Properties feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addConnectionPropertiesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ScaDomainManager_connectionProperties_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ScaDomainManager_connectionProperties_feature", "_UI_ScaDomainManager_type"),
			ScaPackage.Literals.SCA_DOMAIN_MANAGER__CONNECTION_PROPERTIES, true, true, false, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Auto Connect feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAutoConnectPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ScaDomainManager_autoConnect_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ScaDomainManager_autoConnect_feature", "_UI_ScaDomainManager_type"),
			ScaPackage.Literals.SCA_DOMAIN_MANAGER__AUTO_CONNECT, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Connected feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addConnectedPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ScaDomainManager_connected_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ScaDomainManager_connected_feature", "_UI_ScaDomainManager_type"),
			ScaPackage.Literals.SCA_DOMAIN_MANAGER__CONNECTED, false, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Identifier feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIdentifierPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ScaDomainManager_identifier_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ScaDomainManager_identifier_feature", "_UI_ScaDomainManager_type"),
			ScaPackage.Literals.SCA_DOMAIN_MANAGER__IDENTIFIER, false, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ScaDomainManager_name_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ScaDomainManager_name_feature", "_UI_ScaDomainManager_type"),
			ScaPackage.Literals.SCA_DOMAIN_MANAGER__NAME, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Root Context feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRootContextPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ScaDomainManager_rootContext_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ScaDomainManager_rootContext_feature", "_UI_ScaDomainManager_type"),
			ScaPackage.Literals.SCA_DOMAIN_MANAGER__ROOT_CONTEXT, false, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the State feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addStatePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ScaDomainManager_state_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ScaDomainManager_state_feature", "_UI_ScaDomainManager_type"),
			ScaPackage.Literals.SCA_DOMAIN_MANAGER__STATE, false, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Profile feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addProfilePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ScaDomainManager_profile_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ScaDomainManager_profile_feature", "_UI_ScaDomainManager_type"),
			ScaPackage.Literals.SCA_DOMAIN_MANAGER__PROFILE, false, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Local Name feature.
	 * <!-- begin-user-doc -->
	 * @since 12.3
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLocalNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ScaDomainManager_localName_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ScaDomainManager_localName_feature", "_UI_ScaDomainManager_type"),
			ScaPackage.Literals.SCA_DOMAIN_MANAGER__LOCAL_NAME, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
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
			childrenFeatures.add(ScaPackage.Literals.SCA_DOMAIN_MANAGER__FILE_MANAGER);
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
	 * This returns ScaDomainManager.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ScaDomainManager"));
	}

	// END GENERATED CODE

	private ScaDeviceManagersContainerItemProvider deviceManagersItemProvider;
	private ScaEventChannelsContainerItemProvider eventChannelItemProvider;
	private ScaWaveformFactoriesContainerItemProvider waveformFactoriesItemProvider;
	private ScaWaveformsContainerItemProvider waveformItemProvider;

	@Override
	public Collection< ? > getChildren(final Object object) {
		if (object instanceof ScaDomainManager) {
			ScaDomainManager domMgr = (ScaDomainManager) object;
			List<Object> retVal = new ArrayList<Object>();
			if (domMgr.isConnected()) {
				retVal.add(getDeviceManagersContainerItemProvider());
			}
			retVal.addAll(super.getChildren(object));
			if (domMgr.isConnected()) {
				retVal.add(getWaveformFactoriesContainerItemProvider());
				retVal.add(getWaveformContainerItemProvider());
				retVal.add(getEventChannelContainerItemProvider());
			}
			return retVal;
		} else {
			return Collections.emptyList();
		}
	}

	// BEGIN GENERATED CODE

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getTextGen(Object object) {
		String label = ((ScaDomainManager) object).getName();
		return label == null || label.length() == 0 ? getString("_UI_ScaDomainManager_type") : getString("_UI_ScaDomainManager_type") + " " + label;
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getText(final Object object) {
		// END GENERATED CODE
		return ((ScaDomainManager) object).getLabel();
		// BEGIN GENERATED CODE
	}

	@Override
	public String getColumnText(Object object, int columnIndex) {
		if (columnIndex == 0) {
			return getText(object);
		}
		return super.getColumnText(object, columnIndex);
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * @since 11.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(ScaDomainManager.class)) {
		case ScaPackage.SCA_DOMAIN_MANAGER__NAME:
		case ScaPackage.SCA_DOMAIN_MANAGER__LOCAL_NAME:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case ScaPackage.SCA_DOMAIN_MANAGER__FILE_MANAGER:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
			return;
			// END GENERATED CODE
		case ScaPackage.SCA_DOMAIN_MANAGER__DEVICE_MANAGERS:
			if (notification.getEventType() == Notification.UNSET && this.deviceManagersItemProvider != null) {
				this.deviceManagersItemProvider.dispose();
				this.deviceManagersItemProvider = null;
			}
			return;
		case ScaPackage.SCA_DOMAIN_MANAGER__EVENT_CHANNELS:
			if (notification.getEventType() == Notification.UNSET && this.eventChannelItemProvider != null) {
				this.eventChannelItemProvider.dispose();
				this.eventChannelItemProvider = null;
			}
			return;
		case ScaPackage.SCA_DOMAIN_MANAGER__WAVEFORM_FACTORIES:
			if (notification.getEventType() == Notification.UNSET && this.waveformFactoriesItemProvider != null) {
				this.waveformFactoriesItemProvider.dispose();
				this.waveformFactoriesItemProvider = null;
			}
			return;
		case ScaPackage.SCA_DOMAIN_MANAGER__WAVEFORMS:
			if (notification.getEventType() == Notification.UNSET && this.waveformItemProvider != null) {
				this.waveformItemProvider.dispose();
				this.waveformItemProvider = null;
			}
			return;
			// BEGIN GENERATED CODE
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

	// END GENERATED CODE

	private void disposeContainerItemProviders() {
		if (this.deviceManagersItemProvider != null) {
			this.deviceManagersItemProvider.dispose();
			this.deviceManagersItemProvider = null;
		}
		if (this.eventChannelItemProvider != null) {
			this.eventChannelItemProvider.dispose();
			this.eventChannelItemProvider = null;
		}
		if (this.waveformFactoriesItemProvider != null) {
			this.waveformFactoriesItemProvider.dispose();
			this.waveformFactoriesItemProvider = null;
		}
		if (this.waveformItemProvider != null) {
			this.waveformItemProvider.dispose();
			this.waveformItemProvider = null;
		}
	}

	/**
	 * @since 12.2
	 */
	public ScaEventChannelsContainerItemProvider getEventChannelContainerItemProvider() {
		if (eventChannelItemProvider == null) {
			eventChannelItemProvider = new ScaEventChannelsContainerItemProvider(adapterFactory, (EObject) target);
		}
		return eventChannelItemProvider;
	}

	/**
	 * @since 11.0
	 */
	public ScaWaveformsContainerItemProvider getWaveformContainerItemProvider() {
		if (waveformItemProvider == null) {
			waveformItemProvider = new ScaWaveformsContainerItemProvider(adapterFactory, (EObject) target);
		}
		return waveformItemProvider;
	}

	/**
	 * @since 11.0
	 */
	public ScaWaveformFactoriesContainerItemProvider getWaveformFactoriesContainerItemProvider() {
		if (waveformFactoriesItemProvider == null) {
			waveformFactoriesItemProvider = new ScaWaveformFactoriesContainerItemProvider(adapterFactory, (ScaDomainManager) target);
		}
		return waveformFactoriesItemProvider;
	}

	/**
	 * @since 11.0
	 */
	public ScaDeviceManagersContainerItemProvider getDeviceManagersContainerItemProvider() {
		if (deviceManagersItemProvider == null) {
			deviceManagersItemProvider = new ScaDeviceManagersContainerItemProvider(adapterFactory, (ScaDomainManager) target);
		}
		return deviceManagersItemProvider;
	}

	@Override
	public void dispose() {
		disposeContainerItemProviders();
		super.dispose();
	}

	// BEGIN GENERATED CODE
}
