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
package gov.redhawk.ui.views.monitor.model.ports.impl;

import gov.redhawk.ui.views.monitor.model.ports.Monitor;
import gov.redhawk.ui.views.monitor.model.ports.MonitorRegistry;
import gov.redhawk.ui.views.monitor.model.ports.PortsPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Monitor Registry</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.ui.views.monitor.model.ports.impl.MonitorRegistryImpl#getMonitors <em>Monitors</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MonitorRegistryImpl extends EObjectImpl implements MonitorRegistry {

	/**
	 * The cached value of the '{@link #getMonitors() <em>Monitors</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMonitors()
	 * @generated
	 * @ordered
	 */
	protected EList<Monitor> monitors;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MonitorRegistryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PortsPackage.Literals.MONITOR_REGISTRY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Monitor> getMonitors() {
		if (monitors == null) {
			monitors = new EObjectContainmentEList<Monitor>(Monitor.class, this, PortsPackage.MONITOR_REGISTRY__MONITORS);
		}
		return monitors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PortsPackage.MONITOR_REGISTRY__MONITORS:
				return ((InternalEList<?>)getMonitors()).basicRemove(otherEnd, msgs);
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
			case PortsPackage.MONITOR_REGISTRY__MONITORS:
				return getMonitors();
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
			case PortsPackage.MONITOR_REGISTRY__MONITORS:
				getMonitors().clear();
				getMonitors().addAll((Collection<? extends Monitor>)newValue);
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
			case PortsPackage.MONITOR_REGISTRY__MONITORS:
				getMonitors().clear();
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
			case PortsPackage.MONITOR_REGISTRY__MONITORS:
				return monitors != null && !monitors.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //MonitorRegistryImpl
