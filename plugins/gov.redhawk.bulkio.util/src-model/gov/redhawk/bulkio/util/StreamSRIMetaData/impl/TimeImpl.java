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
package gov.redhawk.bulkio.util.StreamSRIMetaData.impl;

import gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIMetaDataPackage;
import gov.redhawk.bulkio.util.StreamSRIMetaData.Time;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Time</b></em>'.
 * @since 1.1
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.bulkio.util.StreamSRIMetaData.impl.TimeImpl#getStartTime <em>Start Time</em>}</li>
 *   <li>{@link gov.redhawk.bulkio.util.StreamSRIMetaData.impl.TimeImpl#getEndTime <em>End Time</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TimeImpl extends EObjectImpl implements Time
{
	/**
	 * The default value of the '{@link #getStartTime() <em>Start Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartTime()
	 * @generated
	 * @ordered
	 */
	protected static final String START_TIME_EDEFAULT = "0";

	/**
	 * The cached value of the '{@link #getStartTime() <em>Start Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartTime()
	 * @generated
	 * @ordered
	 */
	protected String startTime = START_TIME_EDEFAULT;

	/**
	 * This is true if the Start Time attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean startTimeESet;

	/**
	 * The default value of the '{@link #getEndTime() <em>End Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndTime()
	 * @generated
	 * @ordered
	 */
	protected static final String END_TIME_EDEFAULT = "0";

	/**
	 * The cached value of the '{@link #getEndTime() <em>End Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndTime()
	 * @generated
	 * @ordered
	 */
	protected String endTime = END_TIME_EDEFAULT;

	/**
	 * This is true if the End Time attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean endTimeESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TimeImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return StreamSRIMetaDataPackage.Literals.TIME;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStartTime()
	{
		return startTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartTime(String newStartTime)
	{
		String oldStartTime = startTime;
		startTime = newStartTime;
		boolean oldStartTimeESet = startTimeESet;
		startTimeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StreamSRIMetaDataPackage.TIME__START_TIME, oldStartTime, startTime, !oldStartTimeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetStartTime()
	{
		String oldStartTime = startTime;
		boolean oldStartTimeESet = startTimeESet;
		startTime = START_TIME_EDEFAULT;
		startTimeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, StreamSRIMetaDataPackage.TIME__START_TIME, oldStartTime, START_TIME_EDEFAULT, oldStartTimeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetStartTime()
	{
		return startTimeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEndTime()
	{
		return endTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEndTime(String newEndTime)
	{
		String oldEndTime = endTime;
		endTime = newEndTime;
		boolean oldEndTimeESet = endTimeESet;
		endTimeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StreamSRIMetaDataPackage.TIME__END_TIME, oldEndTime, endTime, !oldEndTimeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetEndTime()
	{
		String oldEndTime = endTime;
		boolean oldEndTimeESet = endTimeESet;
		endTime = END_TIME_EDEFAULT;
		endTimeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, StreamSRIMetaDataPackage.TIME__END_TIME, oldEndTime, END_TIME_EDEFAULT, oldEndTimeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetEndTime()
	{
		return endTimeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch (featureID)
		{
			case StreamSRIMetaDataPackage.TIME__START_TIME:
				return getStartTime();
			case StreamSRIMetaDataPackage.TIME__END_TIME:
				return getEndTime();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch (featureID)
		{
			case StreamSRIMetaDataPackage.TIME__START_TIME:
				setStartTime((String)newValue);
				return;
			case StreamSRIMetaDataPackage.TIME__END_TIME:
				setEndTime((String)newValue);
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
	public void eUnset(int featureID)
	{
		switch (featureID)
		{
			case StreamSRIMetaDataPackage.TIME__START_TIME:
				unsetStartTime();
				return;
			case StreamSRIMetaDataPackage.TIME__END_TIME:
				unsetEndTime();
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
	public boolean eIsSet(int featureID)
	{
		switch (featureID)
		{
			case StreamSRIMetaDataPackage.TIME__START_TIME:
				return isSetStartTime();
			case StreamSRIMetaDataPackage.TIME__END_TIME:
				return isSetEndTime();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString()
	{
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (startTime: ");
		if (startTimeESet) result.append(startTime); else result.append("<unset>");
		result.append(", endTime: ");
		if (endTimeESet) result.append(endTime); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //TimeImpl
