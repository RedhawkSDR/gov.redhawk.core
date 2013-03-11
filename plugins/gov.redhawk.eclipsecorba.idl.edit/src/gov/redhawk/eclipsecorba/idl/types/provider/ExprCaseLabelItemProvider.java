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
package gov.redhawk.eclipsecorba.idl.types.provider;

import gov.redhawk.eclipsecorba.idl.expressions.ExpressionsFactory;
import gov.redhawk.eclipsecorba.idl.types.ExprCaseLabel;
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
 * This is the item provider adapter for a {@link gov.redhawk.eclipsecorba.idl.types.ExprCaseLabel} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ExprCaseLabelItemProvider extends CaseLabelItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider,
        ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExprCaseLabelItemProvider(AdapterFactory adapterFactory) {
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
			childrenFeatures.add(TypesPackage.Literals.EXPR_CASE_LABEL__EXPR);
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
	 * This returns ExprCaseLabel.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ExprCaseLabel"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * @since 3.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTextGen(Object object) {
		ExprCaseLabel exprCaseLabel = (ExprCaseLabel)object;
		return getString("_UI_ExprCaseLabel_type") + " " + exprCaseLabel.getStartLine();
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getText(final Object object) {
		final ExprCaseLabel exprCaseLabel = (ExprCaseLabel) object;
		final IItemLabelProvider lp = (IItemLabelProvider) getRootAdapterFactory().adapt(exprCaseLabel.getExpr(), IItemLabelProvider.class);
		if (lp == null || exprCaseLabel == null) {
			return getTextGen(object);
		}
		return lp.getText(exprCaseLabel.getExpr());
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

		switch (notification.getFeatureID(ExprCaseLabel.class)) {
			case TypesPackage.EXPR_CASE_LABEL__EXPR:
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
				(TypesPackage.Literals.EXPR_CASE_LABEL__EXPR,
				 ExpressionsFactory.eINSTANCE.createConstExpression()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.EXPR_CASE_LABEL__EXPR,
				 ExpressionsFactory.eINSTANCE.createOrExpression()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.EXPR_CASE_LABEL__EXPR,
				 ExpressionsFactory.eINSTANCE.createXOrExpression()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.EXPR_CASE_LABEL__EXPR,
				 ExpressionsFactory.eINSTANCE.createAndExpression()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.EXPR_CASE_LABEL__EXPR,
				 ExpressionsFactory.eINSTANCE.createShiftExpression()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.EXPR_CASE_LABEL__EXPR,
				 ExpressionsFactory.eINSTANCE.createAddExpression()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.EXPR_CASE_LABEL__EXPR,
				 ExpressionsFactory.eINSTANCE.createMultExpression()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.EXPR_CASE_LABEL__EXPR,
				 ExpressionsFactory.eINSTANCE.createUnaryExpression()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.EXPR_CASE_LABEL__EXPR,
				 ExpressionsFactory.eINSTANCE.createScopeLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.EXPR_CASE_LABEL__EXPR,
				 ExpressionsFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.EXPR_CASE_LABEL__EXPR,
				 ExpressionsFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.EXPR_CASE_LABEL__EXPR,
				 ExpressionsFactory.eINSTANCE.createCharacterLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.EXPR_CASE_LABEL__EXPR,
				 ExpressionsFactory.eINSTANCE.createFloatingPointLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.EXPR_CASE_LABEL__EXPR,
				 ExpressionsFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.EXPR_CASE_LABEL__EXPR,
				 ExpressionsFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.EXPR_CASE_LABEL__EXPR,
				 ExpressionsFactory.eINSTANCE.createFixedPtLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.EXPR_CASE_LABEL__EXPR,
				 ExpressionsFactory.eINSTANCE.createWideStringLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.EXPR_CASE_LABEL__EXPR,
				 ExpressionsFactory.eINSTANCE.createWideCharacterLiteral()));
	}

}
