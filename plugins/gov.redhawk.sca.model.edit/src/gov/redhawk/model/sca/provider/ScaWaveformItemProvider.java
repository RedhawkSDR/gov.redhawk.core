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
import gov.redhawk.model.sca.ScaWaveform;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link gov.redhawk.model.sca.ScaWaveform} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ScaWaveformItemProvider extends ScaPropertyContainerItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaWaveformItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public Object getParent(Object object) {
		Object parent = super.getParent(object);
		if (parent instanceof ScaDomainManager) {
			ITreeItemContentProvider cp = (ITreeItemContentProvider) adapterFactory.adapt(parent, ITreeItemContentProvider.class);
			Collection< ? > children = cp.getChildren(parent);
			for (Object obj : children) {
				if (obj instanceof ScaWaveformsContainerItemProvider) {
					return obj;
				}
			}
		}
		return parent;
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

			addComponentsPropertyDescriptor(object);
			addAssemblyControllerPropertyDescriptor(object);
			addIdentifierPropertyDescriptor(object);
			addNamePropertyDescriptor(object);
			addStartedPropertyDescriptor(object);
			addProfilePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Components feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addComponentsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ScaWaveform_components_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ScaWaveform_components_feature", "_UI_ScaWaveform_type"),
			ScaPackage.Literals.SCA_WAVEFORM__COMPONENTS, false, false, false, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Assembly Controller feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAssemblyControllerPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ScaWaveform_assemblyController_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ScaWaveform_assemblyController_feature", "_UI_ScaWaveform_type"),
			ScaPackage.Literals.SCA_WAVEFORM__ASSEMBLY_CONTROLLER, true, false, true, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Identifier feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIdentifierPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ScaWaveform_identifier_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ScaWaveform_identifier_feature", "_UI_ScaWaveform_type"),
			ScaPackage.Literals.SCA_WAVEFORM__IDENTIFIER, false, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ScaWaveform_name_feature"), getString("_UI_PropertyDescriptor_description", "_UI_ScaWaveform_name_feature", "_UI_ScaWaveform_type"),
			ScaPackage.Literals.SCA_WAVEFORM__NAME, false, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Started feature.
	 * <!-- begin-user-doc -->
	 * @since 9.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addStartedPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ScaWaveform_started_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ScaWaveform_started_feature", "_UI_ScaWaveform_type"),
			ScaPackage.Literals.SCA_WAVEFORM__STARTED, false, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Profile feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addProfilePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ScaWaveform_profile_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ScaWaveform_profile_feature", "_UI_ScaWaveform_type"),
			ScaPackage.Literals.SCA_WAVEFORM__PROFILE, false, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Collection< ? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(ScaPackage.Literals.SCA_WAVEFORM__COMPONENTS);
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
	 * This returns ScaWaveform.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Object getImage(Object object) {
		// END GENERATED CODE
		// Don't overlayImage because it adds a link decoration for controlled objects.
		// return overlayImage(object, getResourceLocator().getImage("full/obj16/ScaWaveform"));
		return getResourceLocator().getImage("full/obj16/ScaWaveform");
		// BEGIN GENERATED CODE
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getText(final Object object) {
		// END GENERATED CODE
		ScaWaveform waveform = (ScaWaveform) object;
		String label = waveform.name();
		if (label == null || label.length() == 0) {
			IStatus status = waveform.getStatus(ScaPackage.Literals.SCA_WAVEFORM__NAME);
			if (status != null && !status.isOK()) {
				label = getString("CanNotLoadError");
			} else {
				label = getString("Loading");
			}
		}
		return label;
		// BEGIN GENERATED CODE
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

		switch (notification.getFeatureID(ScaWaveform.class)) {
		case ScaPackage.SCA_WAVEFORM__NAME:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case ScaPackage.SCA_WAVEFORM__PORTS:
		case ScaPackage.SCA_WAVEFORM__COMPONENTS:
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
	}

	private ScaWaveformExternalPortsItemProvider portsItemProvider = null;

	@Override
	public Collection< ? > getChildren(Object object) {
		ScaWaveform waveform = (ScaWaveform) object;
		if (!waveform.getPorts().isEmpty()) {
			List<Object> retVal = new ArrayList<Object>();
			if (portsItemProvider == null) {
				portsItemProvider = new ScaWaveformExternalPortsItemProvider(adapterFactory, waveform);
			}
			retVal.add(portsItemProvider);
			retVal.addAll(super.getChildren(object));
			return retVal;
		} else {
			return super.getChildren(object);
		}
	}
}
