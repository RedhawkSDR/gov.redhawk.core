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

import gov.redhawk.eclipsecorba.idl.ForwardDcl;
import gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl;
import gov.redhawk.eclipsecorba.idl.IdlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Forward Dcl</b></em>'.
 * @since 6.0
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.impl.ForwardDclImpl#getImplementation <em>Implementation</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.impl.ForwardDclImpl#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.impl.ForwardDclImpl#isLocal <em>Local</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ForwardDclImpl extends IdlTypeDclImpl implements ForwardDcl {
	/**
	 * The cached value of the '{@link #getImplementation() <em>Implementation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplementation()
	 * @generated
	 * @ordered
	 */
	protected IdlInterfaceDcl implementation;
	/**
	 * The default value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAbstract()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ABSTRACT_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAbstract()
	 * @generated
	 * @ordered
	 */
	protected boolean abstract_ = ABSTRACT_EDEFAULT;
	/**
	 * The default value of the '{@link #isLocal() <em>Local</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLocal()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LOCAL_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isLocal() <em>Local</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLocal()
	 * @generated
	 * @ordered
	 */
	protected boolean local = LOCAL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ForwardDclImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IdlPackage.Literals.FORWARD_DCL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdlInterfaceDcl getImplementation() {
		if (implementation != null && implementation.eIsProxy()) {
			InternalEObject oldImplementation = (InternalEObject)implementation;
			implementation = (IdlInterfaceDcl)eResolveProxy(oldImplementation);
			if (implementation != oldImplementation) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IdlPackage.FORWARD_DCL__IMPLEMENTATION, oldImplementation, implementation));
			}
		}
		return implementation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdlInterfaceDcl basicGetImplementation() {
		return implementation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImplementation(IdlInterfaceDcl newImplementation) {
		IdlInterfaceDcl oldImplementation = implementation;
		implementation = newImplementation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdlPackage.FORWARD_DCL__IMPLEMENTATION, oldImplementation, implementation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAbstract() {
		return abstract_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAbstract(boolean newAbstract) {
		boolean oldAbstract = abstract_;
		abstract_ = newAbstract;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdlPackage.FORWARD_DCL__ABSTRACT, oldAbstract, abstract_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isLocal() {
		return local;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocal(boolean newLocal) {
		boolean oldLocal = local;
		local = newLocal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdlPackage.FORWARD_DCL__LOCAL, oldLocal, local));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IdlPackage.FORWARD_DCL__IMPLEMENTATION:
				if (resolve) return getImplementation();
				return basicGetImplementation();
			case IdlPackage.FORWARD_DCL__ABSTRACT:
				return isAbstract();
			case IdlPackage.FORWARD_DCL__LOCAL:
				return isLocal();
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
			case IdlPackage.FORWARD_DCL__IMPLEMENTATION:
				setImplementation((IdlInterfaceDcl)newValue);
				return;
			case IdlPackage.FORWARD_DCL__ABSTRACT:
				setAbstract((Boolean)newValue);
				return;
			case IdlPackage.FORWARD_DCL__LOCAL:
				setLocal((Boolean)newValue);
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
			case IdlPackage.FORWARD_DCL__IMPLEMENTATION:
				setImplementation((IdlInterfaceDcl)null);
				return;
			case IdlPackage.FORWARD_DCL__ABSTRACT:
				setAbstract(ABSTRACT_EDEFAULT);
				return;
			case IdlPackage.FORWARD_DCL__LOCAL:
				setLocal(LOCAL_EDEFAULT);
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
			case IdlPackage.FORWARD_DCL__IMPLEMENTATION:
				return implementation != null;
			case IdlPackage.FORWARD_DCL__ABSTRACT:
				return abstract_ != ABSTRACT_EDEFAULT;
			case IdlPackage.FORWARD_DCL__LOCAL:
				return local != LOCAL_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (abstract: ");
		result.append(abstract_);
		result.append(", local: ");
		result.append(local);
		result.append(')');
		return result.toString();
	}

	@Override
	public String getId() {
		if (this.id == null) {
			// TODO Auto-generated method stub
			return super.getId()+"Forward";
		}
		return super.getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getScopedName() {
		// TODO Auto-generated method stub
		return super.getScopedName() + "Forward";
	}

} //ForwardDclImpl
