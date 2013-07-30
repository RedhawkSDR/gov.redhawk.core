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
package gov.redhawk.eclipsecorba.idl.types.provider;

import gov.redhawk.eclipsecorba.idl.expressions.ExpressionsFactory;
import gov.redhawk.eclipsecorba.idl.types.FixedPtType;
import gov.redhawk.eclipsecorba.idl.types.TypesPackage;

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
 * This is the item provider adapter for a
 * {@link gov.redhawk.eclipsecorba.idl.types.FixedPtType} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class FixedPtTypeItemProvider extends TemplateTypeItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider,
        ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FixedPtTypeItemProvider(AdapterFactory adapterFactory) {
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
			childrenFeatures.add(TypesPackage.Literals.FIXED_PT_TYPE__EXPR1);
			childrenFeatures.add(TypesPackage.Literals.FIXED_PT_TYPE__EXPR2);
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
	 * This returns FixedPtType.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/FixedPtType"));
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public String getText(final Object object) {
		return "fixed pt";
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

		switch (notification.getFeatureID(FixedPtType.class)) {
			case TypesPackage.FIXED_PT_TYPE__EXPR1:
			case TypesPackage.FIXED_PT_TYPE__EXPR2:
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
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR1,
				 ExpressionsFactory.eINSTANCE.createConstExpression()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR1,
				 ExpressionsFactory.eINSTANCE.createOrExpression()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR1,
				 ExpressionsFactory.eINSTANCE.createXOrExpression()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR1,
				 ExpressionsFactory.eINSTANCE.createAndExpression()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR1,
				 ExpressionsFactory.eINSTANCE.createShiftExpression()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR1,
				 ExpressionsFactory.eINSTANCE.createAddExpression()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR1,
				 ExpressionsFactory.eINSTANCE.createMultExpression()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR1,
				 ExpressionsFactory.eINSTANCE.createUnaryExpression()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR1,
				 ExpressionsFactory.eINSTANCE.createScopeLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR1,
				 ExpressionsFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR1,
				 ExpressionsFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR1,
				 ExpressionsFactory.eINSTANCE.createCharacterLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR1,
				 ExpressionsFactory.eINSTANCE.createFloatingPointLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR1,
				 ExpressionsFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR1,
				 ExpressionsFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR1,
				 ExpressionsFactory.eINSTANCE.createFixedPtLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR1,
				 ExpressionsFactory.eINSTANCE.createWideStringLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR1,
				 ExpressionsFactory.eINSTANCE.createWideCharacterLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR2,
				 ExpressionsFactory.eINSTANCE.createConstExpression()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR2,
				 ExpressionsFactory.eINSTANCE.createOrExpression()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR2,
				 ExpressionsFactory.eINSTANCE.createXOrExpression()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR2,
				 ExpressionsFactory.eINSTANCE.createAndExpression()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR2,
				 ExpressionsFactory.eINSTANCE.createShiftExpression()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR2,
				 ExpressionsFactory.eINSTANCE.createAddExpression()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR2,
				 ExpressionsFactory.eINSTANCE.createMultExpression()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR2,
				 ExpressionsFactory.eINSTANCE.createUnaryExpression()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR2,
				 ExpressionsFactory.eINSTANCE.createScopeLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR2,
				 ExpressionsFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR2,
				 ExpressionsFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR2,
				 ExpressionsFactory.eINSTANCE.createCharacterLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR2,
				 ExpressionsFactory.eINSTANCE.createFloatingPointLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR2,
				 ExpressionsFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR2,
				 ExpressionsFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR2,
				 ExpressionsFactory.eINSTANCE.createFixedPtLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR2,
				 ExpressionsFactory.eINSTANCE.createWideStringLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.FIXED_PT_TYPE__EXPR2,
				 ExpressionsFactory.eINSTANCE.createWideCharacterLiteral()));
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
			childFeature == TypesPackage.Literals.FIXED_PT_TYPE__EXPR1 ||
			childFeature == TypesPackage.Literals.FIXED_PT_TYPE__EXPR2;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
