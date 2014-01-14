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

import gov.redhawk.model.sca.IStatusProvider;
import gov.redhawk.model.sca.ScaPackage;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
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
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * This is the item provider adapter for a {@link gov.redhawk.model.sca.IStatusProvider} object.
 * <!-- begin-user-doc -->
 * @since 11.0
 * <!-- end-user-doc -->
 * @generated
 */
public class IStatusProviderItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource, ITableItemLabelProvider, ITableItemColorProvider, IItemColorProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IStatusProviderItemProvider(AdapterFactory adapterFactory) {
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

			addStatusPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Status feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOt
	 */
	protected void addStatusPropertyDescriptor(Object object) {
		//		itemPropertyDescriptors.add
		//			(createItemPropertyDescriptor
		//				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
		//				 getResourceLocator(),
		//				 getString("_UI_IStatusProvider_status_feature"),
		//				 getString("_UI_PropertyDescriptor_description", "_UI_IStatusProvider_status_feature", "_UI_IStatusProvider_type"),
		//				 ScaPackage.Literals.ISTATUS_PROVIDER__STATUS,
		//				 true,
		//				 false,
		//				 false,
		//				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
		//				 null,
		//				 null));
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_IStatusProvider_status_feature"), getString("_UI_PropertyDescriptor_description", "_UI_IStatusProvider_status_feature",
				"_UI_IStatusProvider_type"), ScaPackage.Literals.ISTATUS_PROVIDER__STATUS, false, true, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
			null, null) {
			@Override
			public IItemLabelProvider getLabelProvider(Object object) {
				return new IItemLabelProvider() {

					@Override
					public String getText(Object object) {
						IStatus status = (IStatus) object;
						if (status == null) {
							return "";
						}
						switch (status.getSeverity()) {
						case IStatus.OK:
							return "OK";
						case IStatus.CANCEL:
							return "OK";
						case IStatus.INFO:
							return "OK";
						case IStatus.WARNING:
							return "WARNING: " + status.getMessage();
						case IStatus.ERROR:
							return "ERROR: " + status.getMessage();
						default:
							return "";
						}
					}

					@Override
					public Object getImage(Object object) {
						ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
						IStatus status = (IStatus) object;
						if (status == null) {
							return null;
						}
						switch (status.getSeverity()) {
						case IStatus.OK:
							return null;
						case IStatus.CANCEL:
							return null;
						case IStatus.INFO:
							return null;
						case IStatus.WARNING:
							return sharedImages.getImage(ISharedImages.IMG_DEC_FIELD_WARNING);
						case IStatus.ERROR:
							return sharedImages.getImage(ISharedImages.IMG_DEC_FIELD_ERROR);
						default:
							return null;
						}
					}
				};
			}
		});
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		IStatus labelValue = ((IStatusProvider) object).getStatus();
		String label = labelValue == null ? null : labelValue.toString();
		return label == null || label.length() == 0 ? getString("_UI_IStatusProvider_type") : getString("_UI_IStatusProvider_type") + " " + label;
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
		return ScaEditPlugin.INSTANCE;
	}

}
