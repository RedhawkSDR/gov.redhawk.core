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

import gov.redhawk.eclipsecorba.idl.IdlFactory;
import gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl;
import gov.redhawk.eclipsecorba.idl.IdlPackage;
import gov.redhawk.eclipsecorba.idl.operations.OperationsFactory;
import gov.redhawk.eclipsecorba.idl.types.TypesFactory;

import java.util.Collection;
import java.util.List;

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
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a
 * {@link gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class IdlInterfaceDclItemProvider extends IdlTypeDclItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider,
        ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdlInterfaceDclItemProvider(AdapterFactory adapterFactory) {
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

			addInheritedInterfacesPropertyDescriptor(object);
			addAbstractPropertyDescriptor(object);
			addLocalPropertyDescriptor(object);
			addForwardDclPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Inherited Interfaces feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addInheritedInterfacesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_IdlInterfaceDcl_inheritedInterfaces_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_IdlInterfaceDcl_inheritedInterfaces_feature", "_UI_IdlInterfaceDcl_type"),
				 IdlPackage.Literals.IDL_INTERFACE_DCL__INHERITED_INTERFACES,
				 false,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Abstract feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAbstractPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_IdlInterfaceDcl_abstract_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_IdlInterfaceDcl_abstract_feature", "_UI_IdlInterfaceDcl_type"),
				 IdlPackage.Literals.IDL_INTERFACE_DCL__ABSTRACT,
				 false,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Local feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLocalPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_IdlInterfaceDcl_local_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_IdlInterfaceDcl_local_feature", "_UI_IdlInterfaceDcl_type"),
				 IdlPackage.Literals.IDL_INTERFACE_DCL__LOCAL,
				 false,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Forward Dcl feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addForwardDclPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_IdlInterfaceDcl_forwardDcl_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_IdlInterfaceDcl_forwardDcl_feature", "_UI_IdlInterfaceDcl_type"),
				 IdlPackage.Literals.IDL_INTERFACE_DCL__FORWARD_DCL,
				 false,
				 false,
				 true,
				 null,
				 null,
				 null));
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
			childrenFeatures.add(IdlPackage.Literals.EXPORT_CONTAINER__BODY);
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
	 * This returns IdlInterfaceDcl.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/IdlInterfaceDcl"));
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public String getText(final Object object) {
		final IdlInterfaceDcl inter = (IdlInterfaceDcl) object;
		final StringBuilder label = new StringBuilder();
		label.append(inter.getName());
		return label.length() == 0 ? getString("_UI_IdlInterfaceDcl_type") : label.toString();
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

		switch (notification.getFeatureID(IdlInterfaceDcl.class)) {
			case IdlPackage.IDL_INTERFACE_DCL__ABSTRACT:
			case IdlPackage.IDL_INTERFACE_DCL__LOCAL:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case IdlPackage.IDL_INTERFACE_DCL__DEFINITIONS:
			case IdlPackage.IDL_INTERFACE_DCL__BODY:
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

		newChildDescriptors.add
			(createChildParameter
				(IdlPackage.Literals.EXPORT_CONTAINER__BODY,
				 IdlFactory.eINSTANCE.createIdlConstDcl()));

		newChildDescriptors.add
			(createChildParameter
				(IdlPackage.Literals.EXPORT_CONTAINER__BODY,
				 IdlFactory.eINSTANCE.createIdlException()));

		newChildDescriptors.add
			(createChildParameter
				(IdlPackage.Literals.EXPORT_CONTAINER__BODY,
				 IdlFactory.eINSTANCE.createForwardDcl()));

		newChildDescriptors.add
			(createChildParameter
				(IdlPackage.Literals.EXPORT_CONTAINER__BODY,
				 IdlFactory.eINSTANCE.createIdlInterfaceDcl()));

		newChildDescriptors.add
			(createChildParameter
				(IdlPackage.Literals.EXPORT_CONTAINER__BODY,
				 IdlFactory.eINSTANCE.createNativeTypeDcl()));

		newChildDescriptors.add
			(createChildParameter
				(IdlPackage.Literals.EXPORT_CONTAINER__BODY,
				 IdlFactory.eINSTANCE.createValueTypeDcl()));

		newChildDescriptors.add
			(createChildParameter
				(IdlPackage.Literals.EXPORT_CONTAINER__BODY,
				 IdlFactory.eINSTANCE.createValueForwardDcl()));

		newChildDescriptors.add
			(createChildParameter
				(IdlPackage.Literals.EXPORT_CONTAINER__BODY,
				 IdlFactory.eINSTANCE.createValueDcl()));

		newChildDescriptors.add
			(createChildParameter
				(IdlPackage.Literals.EXPORT_CONTAINER__BODY,
				 IdlFactory.eINSTANCE.createValueBoxDcl()));

		newChildDescriptors.add
			(createChildParameter
				(IdlPackage.Literals.EXPORT_CONTAINER__BODY,
				 OperationsFactory.eINSTANCE.createOperation()));

		newChildDescriptors.add
			(createChildParameter
				(IdlPackage.Literals.EXPORT_CONTAINER__BODY,
				 OperationsFactory.eINSTANCE.createAttribute()));

		newChildDescriptors.add
			(createChildParameter
				(IdlPackage.Literals.EXPORT_CONTAINER__BODY,
				 TypesFactory.eINSTANCE.createTypeDef()));

		newChildDescriptors.add
			(createChildParameter
				(IdlPackage.Literals.EXPORT_CONTAINER__BODY,
				 TypesFactory.eINSTANCE.createUnionType()));

		newChildDescriptors.add
			(createChildParameter
				(IdlPackage.Literals.EXPORT_CONTAINER__BODY,
				 TypesFactory.eINSTANCE.createEnumType()));

		newChildDescriptors.add
			(createChildParameter
				(IdlPackage.Literals.EXPORT_CONTAINER__BODY,
				 TypesFactory.eINSTANCE.createStructType()));

		newChildDescriptors.add
			(createChildParameter
				(IdlPackage.Literals.EXPORT_CONTAINER__BODY,
				 TypesFactory.eINSTANCE.createUnionForwardDcl()));

		newChildDescriptors.add
			(createChildParameter
				(IdlPackage.Literals.EXPORT_CONTAINER__BODY,
				 TypesFactory.eINSTANCE.createStructForwardDcl()));

		newChildDescriptors.add
			(createChildParameter
				(IdlPackage.Literals.EXPORT_CONTAINER__BODY,
				 TypesFactory.eINSTANCE.createEnumeration()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify =
			childFeature == IdlPackage.Literals.DEFINITION_CONTAINER__DEFINITIONS ||
			childFeature == IdlPackage.Literals.EXPORT_CONTAINER__BODY;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
