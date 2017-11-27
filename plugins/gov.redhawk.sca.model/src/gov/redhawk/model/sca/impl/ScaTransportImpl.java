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
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaTransport;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Transport</b></em>'.
 * @since 21.0
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link gov.redhawk.model.sca.impl.ScaTransportImpl#getTransportType <em>Transport Type</em>}</li>
 * <li>{@link gov.redhawk.model.sca.impl.ScaTransportImpl#getTransportProperties <em>Transport Properties</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ScaTransportImpl extends EObjectImpl implements ScaTransport {
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
	 * The cached value of the '{@link #getTransportProperties() <em>Transport Properties</em>}' containment reference
	 * list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransportProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<ScaAbstractProperty< ? >> transportProperties;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScaTransportImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScaPackage.Literals.SCA_TRANSPORT;
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
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_TRANSPORT__TRANSPORT_TYPE, oldTransportType, transportType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ScaAbstractProperty< ? >> getTransportProperties() {
		if (transportProperties == null) {
			transportProperties = new EObjectContainmentEList.Resolving<ScaAbstractProperty< ? >>(ScaAbstractProperty.class, this,
				ScaPackage.SCA_TRANSPORT__TRANSPORT_PROPERTIES);
		}
		return transportProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ScaPackage.SCA_TRANSPORT__TRANSPORT_PROPERTIES:
			return ((InternalEList< ? >) getTransportProperties()).basicRemove(otherEnd, msgs);
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
		case ScaPackage.SCA_TRANSPORT__TRANSPORT_TYPE:
			return getTransportType();
		case ScaPackage.SCA_TRANSPORT__TRANSPORT_PROPERTIES:
			return getTransportProperties();
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
		case ScaPackage.SCA_TRANSPORT__TRANSPORT_TYPE:
			setTransportType((String) newValue);
			return;
		case ScaPackage.SCA_TRANSPORT__TRANSPORT_PROPERTIES:
			getTransportProperties().clear();
			getTransportProperties().addAll((Collection< ? extends ScaAbstractProperty< ? >>) newValue);
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
		case ScaPackage.SCA_TRANSPORT__TRANSPORT_TYPE:
			setTransportType(TRANSPORT_TYPE_EDEFAULT);
			return;
		case ScaPackage.SCA_TRANSPORT__TRANSPORT_PROPERTIES:
			getTransportProperties().clear();
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
		case ScaPackage.SCA_TRANSPORT__TRANSPORT_TYPE:
			return TRANSPORT_TYPE_EDEFAULT == null ? transportType != null : !TRANSPORT_TYPE_EDEFAULT.equals(transportType);
		case ScaPackage.SCA_TRANSPORT__TRANSPORT_PROPERTIES:
			return transportProperties != null && !transportProperties.isEmpty();
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
		result.append(" (transportType: ");
		result.append(transportType);
		result.append(')');
		return result.toString();
	}

} // ScaTransportImpl
