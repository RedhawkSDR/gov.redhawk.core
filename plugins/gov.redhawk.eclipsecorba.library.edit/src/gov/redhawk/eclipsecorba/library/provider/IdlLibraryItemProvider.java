/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */

 // BEGIN GENERATED CODE
package gov.redhawk.eclipsecorba.library.provider;

import gov.redhawk.eclipsecorba.library.IdlLibrary;
import gov.redhawk.eclipsecorba.library.LibraryFactory;
import gov.redhawk.eclipsecorba.library.LibraryPackage;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptorDecorator;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * This is the item provider adapter for a {@link gov.redhawk.eclipsecorba.library.IdlLibrary} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class IdlLibraryItemProvider extends RepositoryModuleItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider,
        ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdlLibraryItemProvider(AdapterFactory adapterFactory) {
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

			addLoadStatusPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Load Status feature.
	 * <!-- begin-user-doc -->
	 *  @since 4.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addLoadStatusPropertyDescriptor(final Object object) {
		final IStatus loadStatus = ((IdlLibrary) object).getLoadStatus();
		final ItemPropertyDescriptor propDescriptor = createItemPropertyDescriptor(((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
		        getResourceLocator(), getString("_UI_IdlLibrary_loadStatus_feature"),
		        getString("_UI_PropertyDescriptor_description", "_UI_IdlLibrary_loadStatus_feature", "_UI_IdlLibrary_type"),
		        LibraryPackage.Literals.IDL_LIBRARY__LOAD_STATUS, loadStatus == null || !loadStatus.isOK(), true, false,
		        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null);
		final ItemPropertyDescriptorDecorator decorator = new ItemPropertyDescriptorDecorator(object, propDescriptor) {
			@Override
			public IItemLabelProvider getLabelProvider(final Object thisObject) {
				return new IItemLabelProvider() {

					public String getText(final Object object) {
						if (object == null) {
							return "";
						}
						final IStatus status = (IStatus) object;
						return status.getMessage();
					}

					public Object getImage(final Object object) {
						final ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
						if (object == null) {
							return sharedImages.getImageDescriptor(ISharedImages.IMG_OBJS_ERROR_TSK);
						}
						
						final IStatus status = (IStatus) object;
						switch (status.getSeverity()) {
						case IStatus.WARNING:
							return sharedImages.getImageDescriptor(ISharedImages.IMG_OBJS_WARN_TSK);
						case IStatus.ERROR:
							return sharedImages.getImageDescriptor(ISharedImages.IMG_OBJS_ERROR_TSK);
						default:
							break;
						}
						return null;
					}
				};
			}

			@Override
			public void setPropertyValue(final Object thisObject, final Object value) {
				// PASS
			}

		};

		this.itemPropertyDescriptors.add(decorator);
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
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(LibraryPackage.Literals.IDL_LIBRARY__SPECIFICATIONS);
			childrenFeatures.add(LibraryPackage.Literals.IDL_LIBRARY__PATHS);
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
	 * This returns IdlLibrary.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/IdlLibrary"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getText(final Object object) {
		return getString("_UI_IdlLibrary_type");
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

		switch (notification.getFeatureID(IdlLibrary.class)) {
			case LibraryPackage.IDL_LIBRARY__LOAD_STATUS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case LibraryPackage.IDL_LIBRARY__SPECIFICATIONS:
			case LibraryPackage.IDL_LIBRARY__PATHS:
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

		newChildDescriptors.add
			(createChildParameter
				(LibraryPackage.Literals.IDL_LIBRARY__PATHS,
				 LibraryFactory.eINSTANCE.createPreferenceNodePathSet()));

		newChildDescriptors.add
			(createChildParameter
				(LibraryPackage.Literals.IDL_LIBRARY__PATHS,
				 LibraryFactory.eINSTANCE.createURIPathSet()));

		newChildDescriptors.add
			(createChildParameter
				(LibraryPackage.Literals.IDL_LIBRARY__PATHS,
				 LibraryFactory.eINSTANCE.createLocalFilePath()));
	}

}
