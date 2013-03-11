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
package gov.redhawk.eclipsecorba.idl.impl;

import gov.redhawk.eclipsecorba.idl.ArrayDeclarator;
import gov.redhawk.eclipsecorba.idl.IdlPackage;
import gov.redhawk.eclipsecorba.idl.expressions.Expression;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Array Declarator</b></em>'.
 * @since 6.0
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.impl.ArrayDeclaratorImpl#getArraySizeExpressions <em>Array Size Expressions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ArrayDeclaratorImpl extends DeclaratorImpl implements ArrayDeclarator {

	/**
	 * The cached value of the '{@link #getArraySizeExpressions() <em>Array Size Expressions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArraySizeExpressions()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> arraySizeExpressions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArrayDeclaratorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IdlPackage.Literals.ARRAY_DECLARATOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Expression> getArraySizeExpressions() {
		if (arraySizeExpressions == null) {
			arraySizeExpressions = new EObjectContainmentEList<Expression>(Expression.class, this, IdlPackage.ARRAY_DECLARATOR__ARRAY_SIZE_EXPRESSIONS);
		}
		return arraySizeExpressions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case IdlPackage.ARRAY_DECLARATOR__ARRAY_SIZE_EXPRESSIONS:
				return ((InternalEList<?>)getArraySizeExpressions()).basicRemove(otherEnd, msgs);
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
			case IdlPackage.ARRAY_DECLARATOR__ARRAY_SIZE_EXPRESSIONS:
				return getArraySizeExpressions();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case IdlPackage.ARRAY_DECLARATOR__ARRAY_SIZE_EXPRESSIONS:
				getArraySizeExpressions().clear();
				getArraySizeExpressions().addAll((Collection<? extends Expression>)newValue);
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
			case IdlPackage.ARRAY_DECLARATOR__ARRAY_SIZE_EXPRESSIONS:
				getArraySizeExpressions().clear();
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
			case IdlPackage.ARRAY_DECLARATOR__ARRAY_SIZE_EXPRESSIONS:
				return arraySizeExpressions != null && !arraySizeExpressions.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ArrayDeclaratorImpl
