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
import gov.redhawk.frontend.UnallocatedTunerContainer;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Unallocated Tuner Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link gov.redhawk.frontend.impl.UnallocatedTunerContainerImpl#getTunerContainer <em>Tuner Container</em>}</li>
 * <li>{@link gov.redhawk.frontend.impl.UnallocatedTunerContainerImpl#getTunerType <em>Tuner Type</em>}</li>
 * <li>{@link gov.redhawk.frontend.impl.UnallocatedTunerContainerImpl#getCount <em>Count</em>}</li>
 * </ul>
 *
 * @generated
 */
public class UnallocatedTunerContainerImpl extends MinimalEObjectImpl.Container implements UnallocatedTunerContainer {
	/**
	 * The default value of the '{@link #getTunerType() <em>Tuner Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTunerType()
	 * @generated
	 * @ordered
	 */
	protected static final String TUNER_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTunerType() <em>Tuner Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTunerType()
	 * @generated
	 * @ordered
	 */
	protected String tunerType = TUNER_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getCount() <em>Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCount()
	 * @generated
	 * @ordered
	 */
	protected static final int COUNT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCount() <em>Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCount()
	 * @generated
	 * @ordered
	 */
	protected int count = COUNT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UnallocatedTunerContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FrontendPackage.Literals.UNALLOCATED_TUNER_CONTAINER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TunerContainer getTunerContainer() {
		if (eContainerFeatureID() != FrontendPackage.UNALLOCATED_TUNER_CONTAINER__TUNER_CONTAINER)
			return null;
		return (TunerContainer) eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TunerContainer basicGetTunerContainer() {
		if (eContainerFeatureID() != FrontendPackage.UNALLOCATED_TUNER_CONTAINER__TUNER_CONTAINER)
			return null;
		return (TunerContainer) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTunerContainer(TunerContainer newTunerContainer, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newTunerContainer, FrontendPackage.UNALLOCATED_TUNER_CONTAINER__TUNER_CONTAINER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTunerContainer(TunerContainer newTunerContainer) {
		if (newTunerContainer != eInternalContainer()
			|| (eContainerFeatureID() != FrontendPackage.UNALLOCATED_TUNER_CONTAINER__TUNER_CONTAINER && newTunerContainer != null)) {
			if (EcoreUtil.isAncestor(this, newTunerContainer))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newTunerContainer != null)
				msgs = ((InternalEObject) newTunerContainer).eInverseAdd(this, FrontendPackage.TUNER_CONTAINER__UNALLOCATED_CONTAINER, TunerContainer.class,
					msgs);
			msgs = basicSetTunerContainer(newTunerContainer, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.UNALLOCATED_TUNER_CONTAINER__TUNER_CONTAINER, newTunerContainer,
				newTunerContainer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTunerType() {
		return tunerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTunerType(String newTunerType) {
		String oldTunerType = tunerType;
		tunerType = newTunerType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.UNALLOCATED_TUNER_CONTAINER__TUNER_TYPE, oldTunerType, tunerType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getCount() {
		return count;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCount(int newCount) {
		int oldCount = count;
		count = newCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.UNALLOCATED_TUNER_CONTAINER__COUNT, oldCount, count));
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
		case FrontendPackage.UNALLOCATED_TUNER_CONTAINER__TUNER_CONTAINER:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetTunerContainer((TunerContainer) otherEnd, msgs);
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
		case FrontendPackage.UNALLOCATED_TUNER_CONTAINER__TUNER_CONTAINER:
			return basicSetTunerContainer(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
		case FrontendPackage.UNALLOCATED_TUNER_CONTAINER__TUNER_CONTAINER:
			return eInternalContainer().eInverseRemove(this, FrontendPackage.TUNER_CONTAINER__UNALLOCATED_CONTAINER, TunerContainer.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case FrontendPackage.UNALLOCATED_TUNER_CONTAINER__TUNER_CONTAINER:
			if (resolve)
				return getTunerContainer();
			return basicGetTunerContainer();
		case FrontendPackage.UNALLOCATED_TUNER_CONTAINER__TUNER_TYPE:
			return getTunerType();
		case FrontendPackage.UNALLOCATED_TUNER_CONTAINER__COUNT:
			return getCount();
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
		case FrontendPackage.UNALLOCATED_TUNER_CONTAINER__TUNER_CONTAINER:
			setTunerContainer((TunerContainer) newValue);
			return;
		case FrontendPackage.UNALLOCATED_TUNER_CONTAINER__TUNER_TYPE:
			setTunerType((String) newValue);
			return;
		case FrontendPackage.UNALLOCATED_TUNER_CONTAINER__COUNT:
			setCount((Integer) newValue);
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
		case FrontendPackage.UNALLOCATED_TUNER_CONTAINER__TUNER_CONTAINER:
			setTunerContainer((TunerContainer) null);
			return;
		case FrontendPackage.UNALLOCATED_TUNER_CONTAINER__TUNER_TYPE:
			setTunerType(TUNER_TYPE_EDEFAULT);
			return;
		case FrontendPackage.UNALLOCATED_TUNER_CONTAINER__COUNT:
			setCount(COUNT_EDEFAULT);
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
		case FrontendPackage.UNALLOCATED_TUNER_CONTAINER__TUNER_CONTAINER:
			return basicGetTunerContainer() != null;
		case FrontendPackage.UNALLOCATED_TUNER_CONTAINER__TUNER_TYPE:
			return TUNER_TYPE_EDEFAULT == null ? tunerType != null : !TUNER_TYPE_EDEFAULT.equals(tunerType);
		case FrontendPackage.UNALLOCATED_TUNER_CONTAINER__COUNT:
			return count != COUNT_EDEFAULT;
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
		result.append(" (tunerType: ");
		result.append(tunerType);
		result.append(", count: ");
		result.append(count);
		result.append(')');
		return result.toString();
	}

} // UnallocatedTunerContainerImpl
