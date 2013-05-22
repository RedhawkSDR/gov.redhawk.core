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
package gov.redhawk.eclipsecorba.idl.impl;

import gov.redhawk.eclipsecorba.idl.IdlPackage;
import gov.redhawk.eclipsecorba.idl.IdlType;
import gov.redhawk.eclipsecorba.idl.ValueBoxDcl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Value Box Dcl</b></em>'.
 * @since 6.0
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.impl.ValueBoxDclImpl#getTypeSpec <em>Type Spec</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ValueBoxDclImpl extends ValueTypeDclImpl implements ValueBoxDcl {

	/**
	 * The cached value of the '{@link #getTypeSpec() <em>Type Spec</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeSpec()
	 * @generated
	 * @ordered
	 */
	protected IdlType typeSpec;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ValueBoxDclImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IdlPackage.Literals.VALUE_BOX_DCL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdlType getTypeSpec() {
		if (typeSpec != null && typeSpec.eIsProxy()) {
			InternalEObject oldTypeSpec = (InternalEObject)typeSpec;
			typeSpec = (IdlType)eResolveProxy(oldTypeSpec);
			if (typeSpec != oldTypeSpec) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IdlPackage.VALUE_BOX_DCL__TYPE_SPEC, oldTypeSpec, typeSpec));
			}
		}
		return typeSpec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdlType basicGetTypeSpec() {
		return typeSpec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeSpec(IdlType newTypeSpec) {
		IdlType oldTypeSpec = typeSpec;
		typeSpec = newTypeSpec;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdlPackage.VALUE_BOX_DCL__TYPE_SPEC, oldTypeSpec, typeSpec));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IdlPackage.VALUE_BOX_DCL__TYPE_SPEC:
				if (resolve) return getTypeSpec();
				return basicGetTypeSpec();
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
			case IdlPackage.VALUE_BOX_DCL__TYPE_SPEC:
				setTypeSpec((IdlType)newValue);
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
			case IdlPackage.VALUE_BOX_DCL__TYPE_SPEC:
				setTypeSpec((IdlType)null);
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
			case IdlPackage.VALUE_BOX_DCL__TYPE_SPEC:
				return typeSpec != null;
		}
		return super.eIsSet(featureID);
	}

} //ValueBoxDclImpl
