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
package gov.redhawk.bulkio.util.StreamSRIMetaData;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Time</b></em>'.
 * @since 2.0
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.bulkio.util.StreamSRIMetaData.Time#getStartTime <em>Start Time</em>}</li>
 *   <li>{@link gov.redhawk.bulkio.util.StreamSRIMetaData.Time#getEndTime <em>End Time</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIMetaDataPackage#getTime()
 * @model extendedMetaData="name='Time' kind='elementOnly'"
 * @generated
 */
public interface Time extends EObject
{
	/**
	 * Returns the value of the '<em><b>Start Time</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start Time</em>' attribute.
	 * @see #isSetStartTime()
	 * @see #unsetStartTime()
	 * @see #setStartTime(String)
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIMetaDataPackage#getTime_StartTime()
	 * @model default="0" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='startTime'"
	 * @generated
	 */
	String getStartTime();

	/**
	 * Sets the value of the '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.Time#getStartTime <em>Start Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Time</em>' attribute.
	 * @see #isSetStartTime()
	 * @see #unsetStartTime()
	 * @see #getStartTime()
	 * @generated
	 */
	void setStartTime(String value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.Time#getStartTime <em>Start Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetStartTime()
	 * @see #getStartTime()
	 * @see #setStartTime(String)
	 * @generated
	 */
	void unsetStartTime();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.Time#getStartTime <em>Start Time</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Start Time</em>' attribute is set.
	 * @see #unsetStartTime()
	 * @see #getStartTime()
	 * @see #setStartTime(String)
	 * @generated
	 */
	boolean isSetStartTime();

	/**
	 * Returns the value of the '<em><b>End Time</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End Time</em>' attribute.
	 * @see #isSetEndTime()
	 * @see #unsetEndTime()
	 * @see #setEndTime(String)
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIMetaDataPackage#getTime_EndTime()
	 * @model default="0" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='endTime'"
	 * @generated
	 */
	String getEndTime();

	/**
	 * Sets the value of the '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.Time#getEndTime <em>End Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End Time</em>' attribute.
	 * @see #isSetEndTime()
	 * @see #unsetEndTime()
	 * @see #getEndTime()
	 * @generated
	 */
	void setEndTime(String value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.Time#getEndTime <em>End Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetEndTime()
	 * @see #getEndTime()
	 * @see #setEndTime(String)
	 * @generated
	 */
	void unsetEndTime();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.Time#getEndTime <em>End Time</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>End Time</em>' attribute is set.
	 * @see #unsetEndTime()
	 * @see #getEndTime()
	 * @see #setEndTime(String)
	 * @generated
	 */
	boolean isSetEndTime();

} // Time
