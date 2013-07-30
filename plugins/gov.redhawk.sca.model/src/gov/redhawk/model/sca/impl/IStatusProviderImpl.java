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

import gov.redhawk.model.sca.IStatusProvider;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.impl.listeners.DisposableObjectContainerListener;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IStatus Provider</b></em>'.
 * @since 14.0
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.impl.IStatusProviderImpl#getStatus <em>Status</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class IStatusProviderImpl extends EObjectImpl implements IStatusProvider {
	/**
	 * The default value of the '{@link #getStatus() <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
	protected static final IStatus STATUS_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IStatusProviderImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScaPackage.Literals.ISTATUS_PROVIDER;
	}

	{
		eAdapters().add(new DisposableObjectContainerListener());
		eAdapters().add(new AdapterImpl(){
			@Override
			public void notifyChanged(Notification msg) {
				IStatusProviderImpl.this.notifyChanged(msg);
			}
		});
	}

	private Map<EStructuralFeature, IStatus> objectStatusMap = Collections.synchronizedMap(new HashMap<EStructuralFeature, IStatus>());

	protected void notifyChanged(Notification msg) {
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public IStatus getStatus() {
		// END GENERATED CODE
		IStatus retVal = Status.OK_STATUS;
		IStatus[] objectStatus = objectStatusMap.values().toArray(new IStatus[objectStatusMap.size()]);
		if (objectStatus.length > 1) {
			MultiStatus status = new MultiStatus(ScaModelPlugin.ID, Status.OK, objectStatus, "Multiple problems exist within this item.", null);
			if (!status.isOK()) {
				retVal = status;
			}
		} else if (objectStatus.length == 1) {
			retVal = objectStatus[0];
		} else {
			retVal = Status.OK_STATUS;
		}

		return retVal;
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setStatus(EStructuralFeature feature, IStatus status) {
		// END GENERATED CODE
		IStatus oldStatus = getStatus();
		boolean changed = false;
		if (status == null || status.isOK()) {
			changed = objectStatusMap.remove(feature) != null;
		} else {
			objectStatusMap.put(feature, status);
			changed = true;
		}
		if (changed && eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.ISTATUS_PROVIDER__STATUS, oldStatus, getStatus()));
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void clearAllStatus() {
		// END GENERATED CODE
		IStatus oldStatus = getStatus();
		objectStatusMap.clear();
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.ISTATUS_PROVIDER__STATUS, oldStatus, getStatus()));
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public IStatus getStatus(EStructuralFeature feature) {
		// END GENERATED CODE
		return objectStatusMap.get(feature);
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ScaPackage.ISTATUS_PROVIDER__STATUS:
				return getStatus();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ScaPackage.ISTATUS_PROVIDER__STATUS:
				return STATUS_EDEFAULT == null ? getStatus() != null : !STATUS_EDEFAULT.equals(getStatus());
		}
		return super.eIsSet(featureID);
	}

} //IStatusProviderImpl
