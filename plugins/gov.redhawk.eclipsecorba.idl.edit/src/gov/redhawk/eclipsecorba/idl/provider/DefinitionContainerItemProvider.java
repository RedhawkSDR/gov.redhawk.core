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

 // BEGIN GENERATED CODE
package gov.redhawk.eclipsecorba.idl.provider;


import gov.redhawk.eclipsecorba.idl.DefinitionContainer;
import gov.redhawk.eclipsecorba.idl.IdlFactory;
import gov.redhawk.eclipsecorba.idl.IdlPackage;
import gov.redhawk.eclipsecorba.idl.types.TypesFactory;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link gov.redhawk.eclipsecorba.idl.DefinitionContainer} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class DefinitionContainerItemProvider
	extends FileRegionItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DefinitionContainerItemProvider(AdapterFactory adapterFactory) {
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

		}
		return itemPropertyDescriptors;
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
			childrenFeatures.add(IdlPackage.Literals.DEFINITION_CONTAINER__DEFINITIONS);
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
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		DefinitionContainer definitionContainer = (DefinitionContainer)object;
		return getString("_UI_DefinitionContainer_type") + " " + definitionContainer.getStartLine();
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

		switch (notification.getFeatureID(DefinitionContainer.class)) {
			case IdlPackage.DEFINITION_CONTAINER__DEFINITIONS:
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
				(IdlPackage.Literals.DEFINITION_CONTAINER__DEFINITIONS,
				 IdlFactory.eINSTANCE.createModule()));

		newChildDescriptors.add
			(createChildParameter
				(IdlPackage.Literals.DEFINITION_CONTAINER__DEFINITIONS,
				 IdlFactory.eINSTANCE.createIdlConstDcl()));

		newChildDescriptors.add
			(createChildParameter
				(IdlPackage.Literals.DEFINITION_CONTAINER__DEFINITIONS,
				 IdlFactory.eINSTANCE.createIdlException()));

		newChildDescriptors.add
			(createChildParameter
				(IdlPackage.Literals.DEFINITION_CONTAINER__DEFINITIONS,
				 IdlFactory.eINSTANCE.createForwardDcl()));

		newChildDescriptors.add
			(createChildParameter
				(IdlPackage.Literals.DEFINITION_CONTAINER__DEFINITIONS,
				 IdlFactory.eINSTANCE.createIdlInterfaceDcl()));

		newChildDescriptors.add
			(createChildParameter
				(IdlPackage.Literals.DEFINITION_CONTAINER__DEFINITIONS,
				 IdlFactory.eINSTANCE.createNativeTypeDcl()));

		newChildDescriptors.add
			(createChildParameter
				(IdlPackage.Literals.DEFINITION_CONTAINER__DEFINITIONS,
				 IdlFactory.eINSTANCE.createValueTypeDcl()));

		newChildDescriptors.add
			(createChildParameter
				(IdlPackage.Literals.DEFINITION_CONTAINER__DEFINITIONS,
				 IdlFactory.eINSTANCE.createValueForwardDcl()));

		newChildDescriptors.add
			(createChildParameter
				(IdlPackage.Literals.DEFINITION_CONTAINER__DEFINITIONS,
				 IdlFactory.eINSTANCE.createValueDcl()));

		newChildDescriptors.add
			(createChildParameter
				(IdlPackage.Literals.DEFINITION_CONTAINER__DEFINITIONS,
				 IdlFactory.eINSTANCE.createValueBoxDcl()));

		newChildDescriptors.add
			(createChildParameter
				(IdlPackage.Literals.DEFINITION_CONTAINER__DEFINITIONS,
				 TypesFactory.eINSTANCE.createTypeDef()));

		newChildDescriptors.add
			(createChildParameter
				(IdlPackage.Literals.DEFINITION_CONTAINER__DEFINITIONS,
				 TypesFactory.eINSTANCE.createUnionType()));

		newChildDescriptors.add
			(createChildParameter
				(IdlPackage.Literals.DEFINITION_CONTAINER__DEFINITIONS,
				 TypesFactory.eINSTANCE.createEnumType()));

		newChildDescriptors.add
			(createChildParameter
				(IdlPackage.Literals.DEFINITION_CONTAINER__DEFINITIONS,
				 TypesFactory.eINSTANCE.createStructType()));

		newChildDescriptors.add
			(createChildParameter
				(IdlPackage.Literals.DEFINITION_CONTAINER__DEFINITIONS,
				 TypesFactory.eINSTANCE.createUnionForwardDcl()));

		newChildDescriptors.add
			(createChildParameter
				(IdlPackage.Literals.DEFINITION_CONTAINER__DEFINITIONS,
				 TypesFactory.eINSTANCE.createStructForwardDcl()));

		newChildDescriptors.add
			(createChildParameter
				(IdlPackage.Literals.DEFINITION_CONTAINER__DEFINITIONS,
				 TypesFactory.eINSTANCE.createEnumeration()));
	}

}
