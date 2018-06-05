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
import gov.redhawk.model.sca.ScaEventChannel;
import gov.redhawk.model.sca.ScaPackage;
import java.util.Collection;
import java.util.List;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link gov.redhawk.model.sca.ScaEventChannel} object.
 * <!-- begin-user-doc -->
 * @since 12.2
 * <!-- end-user-doc -->
 * @generated
 */
public class ScaEventChannelItemProvider extends CorbaObjWrapperItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaEventChannelItemProvider(AdapterFactory adapterFactory) {
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

			addNamePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ScaEventChannel_name_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ScaEventChannel_name_feature", "_UI_ScaEventChannel_type"),
			ScaPackage.Literals.SCA_EVENT_CHANNEL__NAME, false, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This returns ScaEventChannel.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ScaEventChannel"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {
		String label = ((ScaEventChannel) object).getName();
		return label == null || label.length() == 0 ? getString("_UI_ScaEventChannel_type") : label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void notifyChanged(Notification notification) {
		// END GENERATED CODE
		updateChildren(notification);

		switch (notification.getFeatureID(ScaEventChannel.class)) {
		case ScaPackage.SCA_EVENT_CHANNEL__NAME:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		default:
			break;
		}
		// BEGIN GENERATED CODE
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
	public Object getParent(Object object) {
		Object parent = super.getParent(object);
		if (parent instanceof ScaDomainManager) {
			ITreeItemContentProvider cp = (ITreeItemContentProvider) adapterFactory.adapt(parent, ITreeItemContentProvider.class);
			Collection< ? > children = cp.getChildren(parent);
			for (Object obj : children) {
				if (obj instanceof ScaEventChannelsContainerItemProvider) {
					return obj;
				}
			}
		}
		return parent;
	}

}
