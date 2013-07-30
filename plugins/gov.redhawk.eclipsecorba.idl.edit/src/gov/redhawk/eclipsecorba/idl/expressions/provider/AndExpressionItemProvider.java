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
package gov.redhawk.eclipsecorba.idl.expressions.provider;


import gov.redhawk.eclipsecorba.idl.expressions.AndExpression;
import gov.redhawk.eclipsecorba.idl.expressions.Expression;
import gov.redhawk.eclipsecorba.idl.expressions.ExpressionsFactory;
import gov.redhawk.eclipsecorba.idl.expressions.ExpressionsPackage;

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
 * This is the item provider adapter for a {@link gov.redhawk.eclipsecorba.idl.expressions.AndExpression} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AndExpressionItemProvider
	extends ExpressionItemProvider
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
	public AndExpressionItemProvider(AdapterFactory adapterFactory) {
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
			childrenFeatures.add(ExpressionsPackage.Literals.AND_EXPRESSION__LEFT);
			childrenFeatures.add(ExpressionsPackage.Literals.AND_EXPRESSION__RIGHT);
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
	 * This returns AndExpression.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/AndExpression"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {
		AndExpression andExpression = (AndExpression)object;
		
		Expression left = andExpression.getLeft();
		Expression right = andExpression.getRight();
		IItemLabelProvider leftLp = (IItemLabelProvider) getRootAdapterFactory().adapt(left, IItemLabelProvider.class);
		IItemLabelProvider rightLp = (IItemLabelProvider) getRootAdapterFactory().adapt(right, IItemLabelProvider.class);
		return leftLp.getText(left) + " && " + rightLp.getText(right);
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

		switch (notification.getFeatureID(AndExpression.class)) {
			case ExpressionsPackage.AND_EXPRESSION__LEFT:
			case ExpressionsPackage.AND_EXPRESSION__RIGHT:
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
				(ExpressionsPackage.Literals.AND_EXPRESSION__LEFT,
				 ExpressionsFactory.eINSTANCE.createConstExpression()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__LEFT,
				 ExpressionsFactory.eINSTANCE.createOrExpression()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__LEFT,
				 ExpressionsFactory.eINSTANCE.createXOrExpression()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__LEFT,
				 ExpressionsFactory.eINSTANCE.createAndExpression()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__LEFT,
				 ExpressionsFactory.eINSTANCE.createShiftExpression()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__LEFT,
				 ExpressionsFactory.eINSTANCE.createAddExpression()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__LEFT,
				 ExpressionsFactory.eINSTANCE.createMultExpression()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__LEFT,
				 ExpressionsFactory.eINSTANCE.createUnaryExpression()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__LEFT,
				 ExpressionsFactory.eINSTANCE.createScopeLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__LEFT,
				 ExpressionsFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__LEFT,
				 ExpressionsFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__LEFT,
				 ExpressionsFactory.eINSTANCE.createCharacterLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__LEFT,
				 ExpressionsFactory.eINSTANCE.createFloatingPointLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__LEFT,
				 ExpressionsFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__LEFT,
				 ExpressionsFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__LEFT,
				 ExpressionsFactory.eINSTANCE.createFixedPtLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__LEFT,
				 ExpressionsFactory.eINSTANCE.createWideStringLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__LEFT,
				 ExpressionsFactory.eINSTANCE.createWideCharacterLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__RIGHT,
				 ExpressionsFactory.eINSTANCE.createConstExpression()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__RIGHT,
				 ExpressionsFactory.eINSTANCE.createOrExpression()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__RIGHT,
				 ExpressionsFactory.eINSTANCE.createXOrExpression()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__RIGHT,
				 ExpressionsFactory.eINSTANCE.createAndExpression()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__RIGHT,
				 ExpressionsFactory.eINSTANCE.createShiftExpression()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__RIGHT,
				 ExpressionsFactory.eINSTANCE.createAddExpression()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__RIGHT,
				 ExpressionsFactory.eINSTANCE.createMultExpression()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__RIGHT,
				 ExpressionsFactory.eINSTANCE.createUnaryExpression()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__RIGHT,
				 ExpressionsFactory.eINSTANCE.createScopeLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__RIGHT,
				 ExpressionsFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__RIGHT,
				 ExpressionsFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__RIGHT,
				 ExpressionsFactory.eINSTANCE.createCharacterLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__RIGHT,
				 ExpressionsFactory.eINSTANCE.createFloatingPointLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__RIGHT,
				 ExpressionsFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__RIGHT,
				 ExpressionsFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__RIGHT,
				 ExpressionsFactory.eINSTANCE.createFixedPtLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__RIGHT,
				 ExpressionsFactory.eINSTANCE.createWideStringLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.AND_EXPRESSION__RIGHT,
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
			childFeature == ExpressionsPackage.Literals.AND_EXPRESSION__LEFT ||
			childFeature == ExpressionsPackage.Literals.AND_EXPRESSION__RIGHT;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
