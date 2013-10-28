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
 * A representation of the model object '<em><b>Stream SRI Model</b></em>'.
 * @since 1.1
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIModel#getNumberOfSamples <em>Number Of Samples</em>}</li>
 *   <li>{@link gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIModel#getDataByteOrder <em>Data Byte Order</em>}</li>
 *   <li>{@link gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIModel#getTime <em>Time</em>}</li>
 *   <li>{@link gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIModel#getBulkIOType <em>Bulk IO Type</em>}</li>
 *   <li>{@link gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIModel#getStreamSRI <em>Stream SRI</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIMetaDataPackage#getStreamSRIModel()
 * @model extendedMetaData="name='StreamSRIModel' kind='elementOnly'"
 * @generated
 */
public interface StreamSRIModel extends EObject
{
	/**
	 * Returns the value of the '<em><b>Number Of Samples</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Number Of Samples</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Number Of Samples</em>' attribute.
	 * @see #isSetNumberOfSamples()
	 * @see #unsetNumberOfSamples()
	 * @see #setNumberOfSamples(long)
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIMetaDataPackage#getStreamSRIModel_NumberOfSamples()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Long" required="true"
	 *        extendedMetaData="kind='element' name='numberOfSamples'"
	 * @generated
	 */
	long getNumberOfSamples();

	/**
	 * Sets the value of the '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIModel#getNumberOfSamples <em>Number Of Samples</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Number Of Samples</em>' attribute.
	 * @see #isSetNumberOfSamples()
	 * @see #unsetNumberOfSamples()
	 * @see #getNumberOfSamples()
	 * @generated
	 */
	void setNumberOfSamples(long value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIModel#getNumberOfSamples <em>Number Of Samples</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetNumberOfSamples()
	 * @see #getNumberOfSamples()
	 * @see #setNumberOfSamples(long)
	 * @generated
	 */
	void unsetNumberOfSamples();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIModel#getNumberOfSamples <em>Number Of Samples</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Number Of Samples</em>' attribute is set.
	 * @see #unsetNumberOfSamples()
	 * @see #getNumberOfSamples()
	 * @see #setNumberOfSamples(long)
	 * @generated
	 */
	boolean isSetNumberOfSamples();

	/**
	 * Returns the value of the '<em><b>Data Byte Order</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Byte Order</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Byte Order</em>' attribute.
	 * @see #isSetDataByteOrder()
	 * @see #unsetDataByteOrder()
	 * @see #setDataByteOrder(String)
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIMetaDataPackage#getStreamSRIModel_DataByteOrder()
	 * @model default="" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='dataByteOrder'"
	 * @generated
	 */
	String getDataByteOrder();

	/**
	 * Sets the value of the '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIModel#getDataByteOrder <em>Data Byte Order</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Byte Order</em>' attribute.
	 * @see #isSetDataByteOrder()
	 * @see #unsetDataByteOrder()
	 * @see #getDataByteOrder()
	 * @generated
	 */
	void setDataByteOrder(String value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIModel#getDataByteOrder <em>Data Byte Order</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetDataByteOrder()
	 * @see #getDataByteOrder()
	 * @see #setDataByteOrder(String)
	 * @generated
	 */
	void unsetDataByteOrder();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIModel#getDataByteOrder <em>Data Byte Order</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Data Byte Order</em>' attribute is set.
	 * @see #unsetDataByteOrder()
	 * @see #getDataByteOrder()
	 * @see #setDataByteOrder(String)
	 * @generated
	 */
	boolean isSetDataByteOrder();

	/**
	 * Returns the value of the '<em><b>Time</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time</em>' containment reference.
	 * @see #setTime(Time)
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIMetaDataPackage#getStreamSRIModel_Time()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='time'"
	 * @generated
	 */
	Time getTime();

	/**
	 * Sets the value of the '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIModel#getTime <em>Time</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time</em>' containment reference.
	 * @see #getTime()
	 * @generated
	 */
	void setTime(Time value);

	/**
	 * Returns the value of the '<em><b>Bulk IO Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bulk IO Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bulk IO Type</em>' attribute.
	 * @see #setBulkIOType(String)
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIMetaDataPackage#getStreamSRIModel_BulkIOType()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='BulkIOType'"
	 * @generated
	 */
	String getBulkIOType();

	/**
	 * Sets the value of the '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIModel#getBulkIOType <em>Bulk IO Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bulk IO Type</em>' attribute.
	 * @see #getBulkIOType()
	 * @generated
	 */
	void setBulkIOType(String value);

	/**
	 * Returns the value of the '<em><b>Stream SRI</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stream SRI</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stream SRI</em>' containment reference.
	 * @see #setStreamSRI(SRI)
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIMetaDataPackage#getStreamSRIModel_StreamSRI()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='StreamSRI'"
	 * @generated
	 */
	SRI getStreamSRI();

	/**
	 * Sets the value of the '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIModel#getStreamSRI <em>Stream SRI</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stream SRI</em>' containment reference.
	 * @see #getStreamSRI()
	 * @generated
	 */
	void setStreamSRI(SRI value);

} // StreamSRIModel
