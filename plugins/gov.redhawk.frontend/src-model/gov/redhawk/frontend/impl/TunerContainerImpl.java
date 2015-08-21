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
package gov.redhawk.frontend.impl;

import gov.redhawk.frontend.FrontendPackage;
import gov.redhawk.frontend.TunerContainer;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.UnallocatedTunerContainer;
import java.util.Collection;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tuner Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link gov.redhawk.frontend.impl.TunerContainerImpl#getTunerStatus <em>Tuner Status</em>}</li>
 * <li>{@link gov.redhawk.frontend.impl.TunerContainerImpl#getUnallocatedContainer <em>Unallocated Container</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TunerContainerImpl extends MinimalEObjectImpl.Container implements TunerContainer {
	/**
	 * The cached value of the '{@link #getTunerStatus() <em>Tuner Status</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTunerStatus()
	 * @generated
	 * @ordered
	 */
	protected EList<TunerStatus> tunerStatus;

	/**
	 * The cached value of the '{@link #getUnallocatedContainer() <em>Unallocated Container</em>}' containment reference
	 * list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnallocatedContainer()
	 * @generated
	 * @ordered
	 */
	protected EList<UnallocatedTunerContainer> unallocatedContainer;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TunerContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FrontendPackage.Literals.TUNER_CONTAINER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TunerStatus> getTunerStatus() {
		if (tunerStatus == null) {
			tunerStatus = new EObjectContainmentWithInverseEList<TunerStatus>(TunerStatus.class, this, FrontendPackage.TUNER_CONTAINER__TUNER_STATUS,
				FrontendPackage.TUNER_STATUS__TUNER_CONTAINER);
		}
		return tunerStatus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<UnallocatedTunerContainer> getUnallocatedContainer() {
		if (unallocatedContainer == null) {
			unallocatedContainer = new EObjectContainmentWithInverseEList<UnallocatedTunerContainer>(UnallocatedTunerContainer.class, this,
				FrontendPackage.TUNER_CONTAINER__UNALLOCATED_CONTAINER, FrontendPackage.UNALLOCATED_TUNER_CONTAINER__TUNER_CONTAINER);
		}
		return unallocatedContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case FrontendPackage.TUNER_CONTAINER__TUNER_STATUS:
			return ((InternalEList<InternalEObject>) (InternalEList< ? >) getTunerStatus()).basicAdd(otherEnd, msgs);
		case FrontendPackage.TUNER_CONTAINER__UNALLOCATED_CONTAINER:
			return ((InternalEList<InternalEObject>) (InternalEList< ? >) getUnallocatedContainer()).basicAdd(otherEnd, msgs);
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
		case FrontendPackage.TUNER_CONTAINER__TUNER_STATUS:
			return ((InternalEList< ? >) getTunerStatus()).basicRemove(otherEnd, msgs);
		case FrontendPackage.TUNER_CONTAINER__UNALLOCATED_CONTAINER:
			return ((InternalEList< ? >) getUnallocatedContainer()).basicRemove(otherEnd, msgs);
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
		case FrontendPackage.TUNER_CONTAINER__TUNER_STATUS:
			return getTunerStatus();
		case FrontendPackage.TUNER_CONTAINER__UNALLOCATED_CONTAINER:
			return getUnallocatedContainer();
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
		case FrontendPackage.TUNER_CONTAINER__TUNER_STATUS:
			getTunerStatus().clear();
			getTunerStatus().addAll((Collection< ? extends TunerStatus>) newValue);
			return;
		case FrontendPackage.TUNER_CONTAINER__UNALLOCATED_CONTAINER:
			getUnallocatedContainer().clear();
			getUnallocatedContainer().addAll((Collection< ? extends UnallocatedTunerContainer>) newValue);
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
		case FrontendPackage.TUNER_CONTAINER__TUNER_STATUS:
			getTunerStatus().clear();
			return;
		case FrontendPackage.TUNER_CONTAINER__UNALLOCATED_CONTAINER:
			getUnallocatedContainer().clear();
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
		case FrontendPackage.TUNER_CONTAINER__TUNER_STATUS:
			return tunerStatus != null && !tunerStatus.isEmpty();
		case FrontendPackage.TUNER_CONTAINER__UNALLOCATED_CONTAINER:
			return unallocatedContainer != null && !unallocatedContainer.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // TunerContainerImpl
