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
package gov.redhawk.eclipsecorba.idl.types.impl;

import gov.redhawk.eclipsecorba.idl.impl.IdlTypeDclImpl;
import gov.redhawk.eclipsecorba.idl.types.Switch;
import gov.redhawk.eclipsecorba.idl.types.TypesPackage;
import gov.redhawk.eclipsecorba.idl.types.UnionForwardDcl;
import gov.redhawk.eclipsecorba.idl.types.UnionType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Union Type</b></em>'.
 * @since 6.0
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.types.impl.UnionTypeImpl#getForwardDcl <em>Forward Dcl</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.types.impl.UnionTypeImpl#getIdlSwitch <em>Idl Switch</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UnionTypeImpl extends IdlTypeDclImpl implements UnionType {

	/**
	 * The cached value of the '{@link #getForwardDcl() <em>Forward Dcl</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForwardDcl()
	 * @generated
	 * @ordered
	 */
	protected UnionForwardDcl forwardDcl;
	/**
	 * The cached value of the '{@link #getIdlSwitch() <em>Idl Switch</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdlSwitch()
	 * @generated
	 * @ordered
	 */
	protected Switch idlSwitch;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UnionTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypesPackage.Literals.UNION_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnionForwardDcl getForwardDcl() {
		if (forwardDcl != null && forwardDcl.eIsProxy()) {
			InternalEObject oldForwardDcl = (InternalEObject)forwardDcl;
			forwardDcl = (UnionForwardDcl)eResolveProxy(oldForwardDcl);
			if (forwardDcl != oldForwardDcl) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TypesPackage.UNION_TYPE__FORWARD_DCL, oldForwardDcl, forwardDcl));
			}
		}
		return forwardDcl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnionForwardDcl basicGetForwardDcl() {
		return forwardDcl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetForwardDcl(UnionForwardDcl newForwardDcl, NotificationChain msgs) {
		UnionForwardDcl oldForwardDcl = forwardDcl;
		forwardDcl = newForwardDcl;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TypesPackage.UNION_TYPE__FORWARD_DCL, oldForwardDcl, newForwardDcl);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setForwardDcl(UnionForwardDcl newForwardDcl) {
		if (newForwardDcl != forwardDcl) {
			NotificationChain msgs = null;
			if (forwardDcl != null)
				msgs = ((InternalEObject)forwardDcl).eInverseRemove(this, TypesPackage.UNION_FORWARD_DCL__IMPLEMENTATION, UnionForwardDcl.class, msgs);
			if (newForwardDcl != null)
				msgs = ((InternalEObject)newForwardDcl).eInverseAdd(this, TypesPackage.UNION_FORWARD_DCL__IMPLEMENTATION, UnionForwardDcl.class, msgs);
			msgs = basicSetForwardDcl(newForwardDcl, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.UNION_TYPE__FORWARD_DCL, newForwardDcl, newForwardDcl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Switch getIdlSwitch() {
		return idlSwitch;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIdlSwitch(Switch newIdlSwitch, NotificationChain msgs) {
		Switch oldIdlSwitch = idlSwitch;
		idlSwitch = newIdlSwitch;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TypesPackage.UNION_TYPE__IDL_SWITCH, oldIdlSwitch, newIdlSwitch);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdlSwitch(Switch newIdlSwitch) {
		if (newIdlSwitch != idlSwitch) {
			NotificationChain msgs = null;
			if (idlSwitch != null)
				msgs = ((InternalEObject)idlSwitch).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TypesPackage.UNION_TYPE__IDL_SWITCH, null, msgs);
			if (newIdlSwitch != null)
				msgs = ((InternalEObject)newIdlSwitch).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TypesPackage.UNION_TYPE__IDL_SWITCH, null, msgs);
			msgs = basicSetIdlSwitch(newIdlSwitch, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.UNION_TYPE__IDL_SWITCH, newIdlSwitch, newIdlSwitch));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TypesPackage.UNION_TYPE__FORWARD_DCL:
				if (forwardDcl != null)
					msgs = ((InternalEObject)forwardDcl).eInverseRemove(this, TypesPackage.UNION_FORWARD_DCL__IMPLEMENTATION, UnionForwardDcl.class, msgs);
				return basicSetForwardDcl((UnionForwardDcl)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TypesPackage.UNION_TYPE__FORWARD_DCL:
				return basicSetForwardDcl(null, msgs);
			case TypesPackage.UNION_TYPE__IDL_SWITCH:
				return basicSetIdlSwitch(null, msgs);
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
			case TypesPackage.UNION_TYPE__FORWARD_DCL:
				if (resolve) return getForwardDcl();
				return basicGetForwardDcl();
			case TypesPackage.UNION_TYPE__IDL_SWITCH:
				return getIdlSwitch();
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
			case TypesPackage.UNION_TYPE__FORWARD_DCL:
				setForwardDcl((UnionForwardDcl)newValue);
				return;
			case TypesPackage.UNION_TYPE__IDL_SWITCH:
				setIdlSwitch((Switch)newValue);
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
			case TypesPackage.UNION_TYPE__FORWARD_DCL:
				setForwardDcl((UnionForwardDcl)null);
				return;
			case TypesPackage.UNION_TYPE__IDL_SWITCH:
				setIdlSwitch((Switch)null);
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
			case TypesPackage.UNION_TYPE__FORWARD_DCL:
				return forwardDcl != null;
			case TypesPackage.UNION_TYPE__IDL_SWITCH:
				return idlSwitch != null;
		}
		return super.eIsSet(featureID);
	}

} //UnionTypeImpl
