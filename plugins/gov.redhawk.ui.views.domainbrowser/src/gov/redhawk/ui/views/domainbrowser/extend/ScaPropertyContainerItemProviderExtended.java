/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.ui.views.domainbrowser.extend;

import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaPropertyContainer;
import gov.redhawk.model.sca.provider.TransientItemProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class ScaPropertyContainerItemProviderExtended extends TransientItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider,
        ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource, ITableItemLabelProvider {

	public ScaPropertyContainerItemProviderExtended(final AdapterFactory adapterFactory, final EObject target) {
		super(adapterFactory, target);
	}

	@Override
	protected Collection< ? extends EStructuralFeature> getChildrenFeatures(final Object object) {
		final Collection< ? extends EStructuralFeature> superFeatures = super.getChildrenFeatures(object);

		if (superFeatures != null && superFeatures instanceof List< ? >) {
			final Set<EStructuralFeature> set = new HashSet<EStructuralFeature>(superFeatures);
			set.add(ScaPackage.Literals.SCA_PROPERTY_CONTAINER__PROPERTIES);

			return new ArrayList<EStructuralFeature>(set);
		}

		return superFeatures;
	}

	@Override
	public String getText(final Object object) {
		return "Properties";
	}

	@Override
	public Collection< ? > getChildren(final Object object) {
		return super.getChildren(object);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(final Object object) {
		if (this.itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addProfileURIPropertyDescriptor(object);
			addProfileObjPropertyDescriptor(object);
		}
		return this.itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Profile URI feature.
	 * <!-- begin-user-doc -->
	 * @since 11.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addProfileURIPropertyDescriptor(final Object object) {
		this.itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
		        getResourceLocator(), getString("_UI_ProfileObjectWrapper_profileURI_feature"),
		        getString("_UI_PropertyDescriptor_description", "_UI_ProfileObjectWrapper_profileURI_feature", "_UI_ProfileObjectWrapper_type"),
		        ScaPackage.Literals.PROFILE_OBJECT_WRAPPER__PROFILE_URI, false, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Profile Obj feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addProfileObjPropertyDescriptor(final Object object) {
		this.itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
		        getResourceLocator(), getString("_UI_ProfileObjectWrapper_profileObj_feature"),
		        getString("_UI_PropertyDescriptor_description", "_UI_ProfileObjectWrapper_profileObj_feature", "_UI_ProfileObjectWrapper_type"),
		        ScaPackage.Literals.PROFILE_OBJECT_WRAPPER__PROFILE_OBJ, false, false, true, null, null, null));
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(final Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(ScaPropertyContainer.class)) {
		case ScaPackage.SCA_PROPERTY_CONTAINER__PROFILE_URI:
		case ScaPackage.SCA_PROPERTY_CONTAINER__PROPERTIES:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		default:
			break;
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
	protected void collectNewChildDescriptors(final Collection<Object> newChildDescriptors, final Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

	@Override
	public Object getImage(final Object object) {
		if (object instanceof ScaPropertyContainerItemProviderExtended) {
			return AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui.views", "icons/full/eview16/prop_ps.gif").createImage();
		}

		return super.getImage(object);
	}

}
