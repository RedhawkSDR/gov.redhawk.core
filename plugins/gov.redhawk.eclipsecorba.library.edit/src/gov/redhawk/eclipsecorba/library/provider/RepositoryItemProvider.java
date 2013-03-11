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
import gov.redhawk.eclipsecorba.library.LibraryPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a
 * {@link gov.redhawk.eclipsecorba.library.IdlLibrary} object.
 */
public class RepositoryItemProvider extends IdlLibraryItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider,
        ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public RepositoryItemProvider(final AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to
	 * deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand},
	 * {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in
	 * {@link #createCommand}.
	 */
	@Override
	public Collection< ? extends EStructuralFeature> getChildrenFeatures(final Object object) {
		if (this.childrenFeatures == null) {
			super.getChildrenFeatures(object);
			this.childrenFeatures.clear();
			this.childrenFeatures.add(LibraryPackage.Literals.REPOSITORY_MODULE__DEFINITIONS);
		}
		return this.childrenFeatures;
	}

	/**
	 * This returns Repository.gif.
	 */
	@Override
	public Object getImage(final Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Repository"));
	}

	/**
	 * This returns the label text for the adapted class.
	 */
	@Override
	public String getText(final Object object) {
		return "IDL Repository";
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to
	 * update any cached children and by creating a viewer notification, which
	 * it passes to {@link #fireNotifyChanged}.
	 */
	@Override
	public void notifyChanged(final Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(IdlLibrary.class)) {
		case LibraryPackage.IDL_LIBRARY__SPECIFICATIONS:
		case LibraryPackage.IDL_LIBRARY__PATHS:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case LibraryPackage.REPOSITORY_MODULE__DEFINITIONS:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
			return;
		}
		super.notifyChanged(notification);
	}

}
