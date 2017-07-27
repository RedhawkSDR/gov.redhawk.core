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

import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.WaveformsContainer;

import java.util.Collection;

import mil.jpeojtrs.sca.sad.SoftwareAssembly;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Waveforms Container</b></em>'.
 * 
 * @since 20.2
 *        <!-- end-user-doc -->
 *        <p>
 *        The following features are implemented:
 *        </p>
 *        <ul>
 *        <li>{@link gov.redhawk.model.sca.impl.WaveformsContainerImpl#getSubContainers <em>Sub Containers</em>}</li>
 *        <li>{@link gov.redhawk.model.sca.impl.WaveformsContainerImpl#getWaveforms <em>Waveforms</em>}</li>
 *        <li>{@link gov.redhawk.model.sca.impl.WaveformsContainerImpl#getContainerName <em>Container Name</em>}</li>
 *        </ul>
 *
 * @generated
 */
public class WaveformsContainerImpl extends EObjectImpl implements WaveformsContainer {
	/**
	 * The cached value of the '{@link #getSubContainers() <em>Sub Containers</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getSubContainers()
	 * @generated
	 * @ordered
	 */
	protected EList<WaveformsContainer> subContainers;

	/**
	 * The cached value of the '{@link #getWaveforms() <em>Waveforms</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getWaveforms()
	 * @generated
	 * @ordered
	 */
	protected EList<SoftwareAssembly> waveforms;

	/**
	 * The default value of the '{@link #getContainerName() <em>Container Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getContainerName()
	 * @generated
	 * @ordered
	 */
	protected static final String CONTAINER_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getContainerName() <em>Container Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getContainerName()
	 * @generated
	 * @ordered
	 */
	protected String containerName = CONTAINER_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected WaveformsContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScaPackage.Literals.WAVEFORMS_CONTAINER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<WaveformsContainer> getSubContainers() {
		if (subContainers == null) {
			subContainers = new EObjectResolvingEList<WaveformsContainer>(WaveformsContainer.class, this, ScaPackage.WAVEFORMS_CONTAINER__SUB_CONTAINERS);
		}
		return subContainers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<SoftwareAssembly> getWaveforms() {
		if (waveforms == null) {
			waveforms = new EObjectResolvingEList<SoftwareAssembly>(SoftwareAssembly.class, this, ScaPackage.WAVEFORMS_CONTAINER__WAVEFORMS);
		}
		return waveforms;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getContainerName() {
		return containerName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setContainerName(String newContainerName) {
		String oldContainerName = containerName;
		containerName = newContainerName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.WAVEFORMS_CONTAINER__CONTAINER_NAME, oldContainerName, containerName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ScaPackage.WAVEFORMS_CONTAINER__SUB_CONTAINERS:
			return getSubContainers();
		case ScaPackage.WAVEFORMS_CONTAINER__WAVEFORMS:
			return getWaveforms();
		case ScaPackage.WAVEFORMS_CONTAINER__CONTAINER_NAME:
			return getContainerName();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ScaPackage.WAVEFORMS_CONTAINER__SUB_CONTAINERS:
			getSubContainers().clear();
			getSubContainers().addAll((Collection< ? extends WaveformsContainer>) newValue);
			return;
		case ScaPackage.WAVEFORMS_CONTAINER__WAVEFORMS:
			getWaveforms().clear();
			getWaveforms().addAll((Collection< ? extends SoftwareAssembly>) newValue);
			return;
		case ScaPackage.WAVEFORMS_CONTAINER__CONTAINER_NAME:
			setContainerName((String) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case ScaPackage.WAVEFORMS_CONTAINER__SUB_CONTAINERS:
			getSubContainers().clear();
			return;
		case ScaPackage.WAVEFORMS_CONTAINER__WAVEFORMS:
			getWaveforms().clear();
			return;
		case ScaPackage.WAVEFORMS_CONTAINER__CONTAINER_NAME:
			setContainerName(CONTAINER_NAME_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case ScaPackage.WAVEFORMS_CONTAINER__SUB_CONTAINERS:
			return subContainers != null && !subContainers.isEmpty();
		case ScaPackage.WAVEFORMS_CONTAINER__WAVEFORMS:
			return waveforms != null && !waveforms.isEmpty();
		case ScaPackage.WAVEFORMS_CONTAINER__CONTAINER_NAME:
			return CONTAINER_NAME_EDEFAULT == null ? containerName != null : !CONTAINER_NAME_EDEFAULT.equals(containerName);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (containerName: ");
		result.append(containerName);
		result.append(')');
		return result.toString();
	}

} //WaveformsContainerImpl
