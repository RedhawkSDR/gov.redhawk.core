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
package gov.redhawk.eclipsecorba.idl.types.impl;

import gov.redhawk.eclipsecorba.idl.expressions.Expression;
import gov.redhawk.eclipsecorba.idl.types.FixedPtType;
import gov.redhawk.eclipsecorba.idl.types.TypesPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Fixed Pt Type</b></em>'.
 * @since 6.0
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.types.impl.FixedPtTypeImpl#getExpr1 <em>Expr1</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.types.impl.FixedPtTypeImpl#getExpr2 <em>Expr2</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FixedPtTypeImpl extends TemplateTypeImpl implements FixedPtType {

	/**
	 * The cached value of the '{@link #getExpr1() <em>Expr1</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpr1()
	 * @generated
	 * @ordered
	 */
	protected Expression expr1;
	/**
	 * The cached value of the '{@link #getExpr2() <em>Expr2</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpr2()
	 * @generated
	 * @ordered
	 */
	protected Expression expr2;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FixedPtTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypesPackage.Literals.FIXED_PT_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getExpr1() {
		return expr1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExpr1(Expression newExpr1, NotificationChain msgs) {
		Expression oldExpr1 = expr1;
		expr1 = newExpr1;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TypesPackage.FIXED_PT_TYPE__EXPR1, oldExpr1, newExpr1);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpr1(Expression newExpr1) {
		if (newExpr1 != expr1) {
			NotificationChain msgs = null;
			if (expr1 != null)
				msgs = ((InternalEObject)expr1).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TypesPackage.FIXED_PT_TYPE__EXPR1, null, msgs);
			if (newExpr1 != null)
				msgs = ((InternalEObject)newExpr1).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TypesPackage.FIXED_PT_TYPE__EXPR1, null, msgs);
			msgs = basicSetExpr1(newExpr1, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.FIXED_PT_TYPE__EXPR1, newExpr1, newExpr1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getExpr2() {
		return expr2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExpr2(Expression newExpr2, NotificationChain msgs) {
		Expression oldExpr2 = expr2;
		expr2 = newExpr2;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TypesPackage.FIXED_PT_TYPE__EXPR2, oldExpr2, newExpr2);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpr2(Expression newExpr2) {
		if (newExpr2 != expr2) {
			NotificationChain msgs = null;
			if (expr2 != null)
				msgs = ((InternalEObject)expr2).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TypesPackage.FIXED_PT_TYPE__EXPR2, null, msgs);
			if (newExpr2 != null)
				msgs = ((InternalEObject)newExpr2).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TypesPackage.FIXED_PT_TYPE__EXPR2, null, msgs);
			msgs = basicSetExpr2(newExpr2, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.FIXED_PT_TYPE__EXPR2, newExpr2, newExpr2));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TypesPackage.FIXED_PT_TYPE__EXPR1:
				return basicSetExpr1(null, msgs);
			case TypesPackage.FIXED_PT_TYPE__EXPR2:
				return basicSetExpr2(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TypesPackage.FIXED_PT_TYPE__EXPR1:
				return getExpr1();
			case TypesPackage.FIXED_PT_TYPE__EXPR2:
				return getExpr2();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TypesPackage.FIXED_PT_TYPE__EXPR1:
				setExpr1((Expression)newValue);
				return;
			case TypesPackage.FIXED_PT_TYPE__EXPR2:
				setExpr2((Expression)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case TypesPackage.FIXED_PT_TYPE__EXPR1:
				setExpr1((Expression)null);
				return;
			case TypesPackage.FIXED_PT_TYPE__EXPR2:
				setExpr2((Expression)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TypesPackage.FIXED_PT_TYPE__EXPR1:
				return expr1 != null;
			case TypesPackage.FIXED_PT_TYPE__EXPR2:
				return expr2 != null;
		}
		return super.eIsSet(featureID);
	}

} //FixedPtTypeImpl
