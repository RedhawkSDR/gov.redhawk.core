/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
// BEGIN GENERATED CODE
package gov.redhawk.model.sca.impl;

import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaNegotiatedConnection;
import gov.redhawk.model.sca.ScaPackage;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Negotiated Connection</b></em>'.
 * 
 * @since 21.1
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link gov.redhawk.model.sca.impl.ScaNegotiatedConnectionImpl#isAlive <em>Alive</em>}</li>
 * <li>{@link gov.redhawk.model.sca.impl.ScaNegotiatedConnectionImpl#getTransportType <em>Transport Type</em>}</li>
 * <li>{@link gov.redhawk.model.sca.impl.ScaNegotiatedConnectionImpl#getTransportInfo <em>Transport Info</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ScaNegotiatedConnectionImpl extends ScaConnectionImpl implements ScaNegotiatedConnection {
	/**
	 * The default value of the '{@link #isAlive() <em>Alive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAlive()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ALIVE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAlive() <em>Alive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAlive()
	 * @generated
	 * @ordered
	 */
	protected boolean alive = ALIVE_EDEFAULT;

	/**
	 * This is true if the Alive attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean aliveESet;

	/**
	 * The default value of the '{@link #getTransportType() <em>Transport Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransportType()
	 * @generated
	 * @ordered
	 */
	protected static final String TRANSPORT_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTransportType() <em>Transport Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransportType()
	 * @generated
	 * @ordered
	 */
	protected String transportType = TRANSPORT_TYPE_EDEFAULT;

	/**
	 * This is true if the Transport Type attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean transportTypeESet;

	/**
	 * The cached value of the '{@link #getTransportInfo() <em>Transport Info</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransportInfo()
	 * @generated
	 * @ordered
	 */
	protected EList<ScaAbstractProperty< ? >> transportInfo;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScaNegotiatedConnectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScaPackage.Literals.SCA_NEGOTIATED_CONNECTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAlive(boolean newAlive) {
		boolean oldAlive = alive;
		alive = newAlive;
		boolean oldAliveESet = aliveESet;
		aliveESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_NEGOTIATED_CONNECTION__ALIVE, oldAlive, alive, !oldAliveESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetAlive() {
		boolean oldAlive = alive;
		boolean oldAliveESet = aliveESet;
		alive = ALIVE_EDEFAULT;
		aliveESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScaPackage.SCA_NEGOTIATED_CONNECTION__ALIVE, oldAlive, ALIVE_EDEFAULT, oldAliveESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetAlive() {
		return aliveESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTransportType() {
		return transportType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransportType(String newTransportType) {
		String oldTransportType = transportType;
		transportType = newTransportType;
		boolean oldTransportTypeESet = transportTypeESet;
		transportTypeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_NEGOTIATED_CONNECTION__TRANSPORT_TYPE, oldTransportType, transportType,
				!oldTransportTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetTransportType() {
		String oldTransportType = transportType;
		boolean oldTransportTypeESet = transportTypeESet;
		transportType = TRANSPORT_TYPE_EDEFAULT;
		transportTypeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScaPackage.SCA_NEGOTIATED_CONNECTION__TRANSPORT_TYPE, oldTransportType,
				TRANSPORT_TYPE_EDEFAULT, oldTransportTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetTransportType() {
		return transportTypeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ScaAbstractProperty< ? >> getTransportInfo() {
		if (transportInfo == null) {
			transportInfo = new EObjectContainmentEList.Resolving<ScaAbstractProperty< ? >>(ScaAbstractProperty.class, this,
				ScaPackage.SCA_NEGOTIATED_CONNECTION__TRANSPORT_INFO);
		}
		return transportInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ScaPackage.SCA_NEGOTIATED_CONNECTION__TRANSPORT_INFO:
			return ((InternalEList< ? >) getTransportInfo()).basicRemove(otherEnd, msgs);
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
		case ScaPackage.SCA_NEGOTIATED_CONNECTION__ALIVE:
			return isAlive();
		case ScaPackage.SCA_NEGOTIATED_CONNECTION__TRANSPORT_TYPE:
			return getTransportType();
		case ScaPackage.SCA_NEGOTIATED_CONNECTION__TRANSPORT_INFO:
			return getTransportInfo();
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
		case ScaPackage.SCA_NEGOTIATED_CONNECTION__ALIVE:
			setAlive((Boolean) newValue);
			return;
		case ScaPackage.SCA_NEGOTIATED_CONNECTION__TRANSPORT_TYPE:
			setTransportType((String) newValue);
			return;
		case ScaPackage.SCA_NEGOTIATED_CONNECTION__TRANSPORT_INFO:
			getTransportInfo().clear();
			getTransportInfo().addAll((Collection< ? extends ScaAbstractProperty< ? >>) newValue);
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
		case ScaPackage.SCA_NEGOTIATED_CONNECTION__ALIVE:
			unsetAlive();
			return;
		case ScaPackage.SCA_NEGOTIATED_CONNECTION__TRANSPORT_TYPE:
			unsetTransportType();
			return;
		case ScaPackage.SCA_NEGOTIATED_CONNECTION__TRANSPORT_INFO:
			getTransportInfo().clear();
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
		case ScaPackage.SCA_NEGOTIATED_CONNECTION__ALIVE:
			return isSetAlive();
		case ScaPackage.SCA_NEGOTIATED_CONNECTION__TRANSPORT_TYPE:
			return isSetTransportType();
		case ScaPackage.SCA_NEGOTIATED_CONNECTION__TRANSPORT_INFO:
			return transportInfo != null && !transportInfo.isEmpty();
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
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (alive: ");
		if (aliveESet)
			result.append(alive);
		else
			result.append("<unset>");
		result.append(", transportType: ");
		if (transportTypeESet)
			result.append(transportType);
		else
			result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} // ScaNegotiatedConnectionImpl
