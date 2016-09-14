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
package gov.redhawk.frontend;

import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaStructProperty;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tuner Status</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link gov.redhawk.frontend.TunerStatus#getTunerContainer <em>Tuner Container</em>}</li>
 * <li>{@link gov.redhawk.frontend.TunerStatus#getTunerStatusStruct <em>Tuner Status Struct</em>}</li>
 * <li>{@link gov.redhawk.frontend.TunerStatus#getSimples <em>Simples</em>}</li>
 * <li>{@link gov.redhawk.frontend.TunerStatus#isAllocated <em>Allocated</em>}</li>
 * <li>{@link gov.redhawk.frontend.TunerStatus#getTunerID <em>Tuner ID</em>}</li>
 * <li>{@link gov.redhawk.frontend.TunerStatus#getTunerType <em>Tuner Type</em>}</li>
 * <li>{@link gov.redhawk.frontend.TunerStatus#getAllocationID <em>Allocation ID</em>}</li>
 * <li>{@link gov.redhawk.frontend.TunerStatus#getCenterFrequency <em>Center Frequency</em>}</li>
 * <li>{@link gov.redhawk.frontend.TunerStatus#getBandwidth <em>Bandwidth</em>}</li>
 * <li>{@link gov.redhawk.frontend.TunerStatus#getSampleRate <em>Sample Rate</em>}</li>
 * <li>{@link gov.redhawk.frontend.TunerStatus#getGroupID <em>Group ID</em>}</li>
 * <li>{@link gov.redhawk.frontend.TunerStatus#getRfFlowID <em>Rf Flow ID</em>}</li>
 * <li>{@link gov.redhawk.frontend.TunerStatus#isEnabled <em>Enabled</em>}</li>
 * <li>{@link gov.redhawk.frontend.TunerStatus#getGain <em>Gain</em>}</li>
 * <li>{@link gov.redhawk.frontend.TunerStatus#isAgc <em>Agc</em>}</li>
 * <li>{@link gov.redhawk.frontend.TunerStatus#getReferenceSource <em>Reference Source</em>}</li>
 * <li>{@link gov.redhawk.frontend.TunerStatus#isDeviceControl <em>Device Control</em>}</li>
 * <li>{@link gov.redhawk.frontend.TunerStatus#getListenerAllocations <em>Listener Allocations</em>}</li>
 * </ul>
 *
 * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus()
 * @model
 * @generated
 */
public interface TunerStatus extends EObject {
	/**
	 * Returns the value of the '<em><b>Tuner Container</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link gov.redhawk.frontend.TunerContainer#getTunerStatus <em>Tuner
	 * Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tuner Container</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tuner Container</em>' container reference.
	 * @see #setTunerContainer(TunerContainer)
	 * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_TunerContainer()
	 * @see gov.redhawk.frontend.TunerContainer#getTunerStatus
	 * @model opposite="tunerStatus" transient="false"
	 * @generated
	 */
	TunerContainer getTunerContainer();

	/**
	 * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getTunerContainer <em>Tuner Container</em>}'
	 * container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tuner Container</em>' container reference.
	 * @see #getTunerContainer()
	 * @generated
	 */
	void setTunerContainer(TunerContainer value);

	/**
	 * Returns the value of the '<em><b>Tuner Status Struct</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tuner Status Struct</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Direct reference to device struct
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Tuner Status Struct</em>' attribute.
	 * @see #setTunerStatusStruct(ScaStructProperty)
	 * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_TunerStatusStruct()
	 * @model unique="false" dataType="gov.redhawk.frontend.ScaStructProperty"
	 * annotation="http://www.eclipse.org/emf/2002/GenModel property='Readonly'"
	 * @generated
	 */
	ScaStructProperty getTunerStatusStruct();

	/**
	 * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getTunerStatusStruct <em>Tuner Status
	 * Struct</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tuner Status Struct</em>' attribute.
	 * @see #getTunerStatusStruct()
	 * @generated
	 */
	void setTunerStatusStruct(ScaStructProperty value);

	/**
	 * Returns the value of the '<em><b>Simples</b></em>' attribute list.
	 * The list contents are of type {@link gov.redhawk.model.sca.ScaSimpleProperty}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Simples</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Simples</em>' attribute list.
	 * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_Simples()
	 * @model unique="false" dataType="gov.redhawk.frontend.ScaSimpleProperty" transient="true" changeable="false"
	 * volatile="true" derived="true"
	 * annotation="http://www.eclipse.org/emf/2002/GenModel property='Readonly'"
	 * @generated
	 */
	EList<ScaSimpleProperty> getSimples();

	/**
	 * Returns the value of the '<em><b>Allocated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocated</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Allocated</em>' attribute.
	 * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_Allocated()
	 * @model unique="false" transient="true" changeable="false" volatile="true" derived="true"
	 * annotation="http://www.eclipse.org/emf/2002/GenModel property='Readonly'"
	 * @generated
	 */
	boolean isAllocated();

	/**
	 * Returns the value of the '<em><b>Tuner ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tuner ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tuner ID</em>' attribute.
	 * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_TunerID()
	 * @model unique="false" transient="true" changeable="false" volatile="true" derived="true"
	 * annotation="http://www.eclipse.org/emf/2002/GenModel property='Readonly'"
	 * @generated
	 */
	String getTunerID();

	/**
	 * Returns the value of the '<em><b>Tuner Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tuner Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tuner Type</em>' attribute.
	 * @see #isSetTunerType()
	 * @see #unsetTunerType()
	 * @see #setTunerType(String)
	 * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_TunerType()
	 * @model unique="false" unsettable="true"
	 * @generated
	 */
	String getTunerType();

	/**
	 * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getTunerType <em>Tuner Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tuner Type</em>' attribute.
	 * @see #isSetTunerType()
	 * @see #unsetTunerType()
	 * @see #getTunerType()
	 * @generated
	 */
	void setTunerType(String value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.frontend.TunerStatus#getTunerType <em>Tuner Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTunerType()
	 * @see #getTunerType()
	 * @see #setTunerType(String)
	 * @generated
	 */
	void unsetTunerType();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.frontend.TunerStatus#getTunerType <em>Tuner Type</em>}'
	 * attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Tuner Type</em>' attribute is set.
	 * @see #unsetTunerType()
	 * @see #getTunerType()
	 * @see #setTunerType(String)
	 * @generated
	 */
	boolean isSetTunerType();

	/**
	 * Returns the value of the '<em><b>Allocation ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocation ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Allocation ID</em>' attribute.
	 * @see #isSetAllocationID()
	 * @see #unsetAllocationID()
	 * @see #setAllocationID(String)
	 * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_AllocationID()
	 * @model unique="false" unsettable="true"
	 * @generated
	 */
	String getAllocationID();

	/**
	 * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getAllocationID <em>Allocation ID</em>}'
	 * attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Allocation ID</em>' attribute.
	 * @see #isSetAllocationID()
	 * @see #unsetAllocationID()
	 * @see #getAllocationID()
	 * @generated
	 */
	void setAllocationID(String value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.frontend.TunerStatus#getAllocationID <em>Allocation ID</em>}'
	 * attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetAllocationID()
	 * @see #getAllocationID()
	 * @see #setAllocationID(String)
	 * @generated
	 */
	void unsetAllocationID();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.frontend.TunerStatus#getAllocationID <em>Allocation
	 * ID</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Allocation ID</em>' attribute is set.
	 * @see #unsetAllocationID()
	 * @see #getAllocationID()
	 * @see #setAllocationID(String)
	 * @generated
	 */
	boolean isSetAllocationID();

	/**
	 * Returns the value of the '<em><b>Center Frequency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Center Frequency</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Center Frequency</em>' attribute.
	 * @see #isSetCenterFrequency()
	 * @see #unsetCenterFrequency()
	 * @see #setCenterFrequency(double)
	 * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_CenterFrequency()
	 * @model unique="false" unsettable="true"
	 * @generated
	 */
	double getCenterFrequency();

	/**
	 * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getCenterFrequency <em>Center Frequency</em>}'
	 * attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Center Frequency</em>' attribute.
	 * @see #isSetCenterFrequency()
	 * @see #unsetCenterFrequency()
	 * @see #getCenterFrequency()
	 * @generated
	 */
	void setCenterFrequency(double value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.frontend.TunerStatus#getCenterFrequency <em>Center Frequency</em>}'
	 * attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetCenterFrequency()
	 * @see #getCenterFrequency()
	 * @see #setCenterFrequency(double)
	 * @generated
	 */
	void unsetCenterFrequency();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.frontend.TunerStatus#getCenterFrequency <em>Center
	 * Frequency</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Center Frequency</em>' attribute is set.
	 * @see #unsetCenterFrequency()
	 * @see #getCenterFrequency()
	 * @see #setCenterFrequency(double)
	 * @generated
	 */
	boolean isSetCenterFrequency();

	/**
	 * Returns the value of the '<em><b>Bandwidth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bandwidth</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bandwidth</em>' attribute.
	 * @see #isSetBandwidth()
	 * @see #unsetBandwidth()
	 * @see #setBandwidth(double)
	 * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_Bandwidth()
	 * @model unique="false" unsettable="true"
	 * @generated
	 */
	double getBandwidth();

	/**
	 * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getBandwidth <em>Bandwidth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bandwidth</em>' attribute.
	 * @see #isSetBandwidth()
	 * @see #unsetBandwidth()
	 * @see #getBandwidth()
	 * @generated
	 */
	void setBandwidth(double value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.frontend.TunerStatus#getBandwidth <em>Bandwidth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetBandwidth()
	 * @see #getBandwidth()
	 * @see #setBandwidth(double)
	 * @generated
	 */
	void unsetBandwidth();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.frontend.TunerStatus#getBandwidth <em>Bandwidth</em>}'
	 * attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Bandwidth</em>' attribute is set.
	 * @see #unsetBandwidth()
	 * @see #getBandwidth()
	 * @see #setBandwidth(double)
	 * @generated
	 */
	boolean isSetBandwidth();

	/**
	 * Returns the value of the '<em><b>Sample Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sample Rate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sample Rate</em>' attribute.
	 * @see #isSetSampleRate()
	 * @see #unsetSampleRate()
	 * @see #setSampleRate(double)
	 * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_SampleRate()
	 * @model unique="false" unsettable="true"
	 * @generated
	 */
	double getSampleRate();

	/**
	 * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getSampleRate <em>Sample Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sample Rate</em>' attribute.
	 * @see #isSetSampleRate()
	 * @see #unsetSampleRate()
	 * @see #getSampleRate()
	 * @generated
	 */
	void setSampleRate(double value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.frontend.TunerStatus#getSampleRate <em>Sample Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSampleRate()
	 * @see #getSampleRate()
	 * @see #setSampleRate(double)
	 * @generated
	 */
	void unsetSampleRate();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.frontend.TunerStatus#getSampleRate <em>Sample Rate</em>}'
	 * attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Sample Rate</em>' attribute is set.
	 * @see #unsetSampleRate()
	 * @see #getSampleRate()
	 * @see #setSampleRate(double)
	 * @generated
	 */
	boolean isSetSampleRate();

	/**
	 * Returns the value of the '<em><b>Group ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group ID</em>' attribute.
	 * @see #isSetGroupID()
	 * @see #unsetGroupID()
	 * @see #setGroupID(String)
	 * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_GroupID()
	 * @model unique="false" unsettable="true"
	 * @generated
	 */
	String getGroupID();

	/**
	 * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getGroupID <em>Group ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Group ID</em>' attribute.
	 * @see #isSetGroupID()
	 * @see #unsetGroupID()
	 * @see #getGroupID()
	 * @generated
	 */
	void setGroupID(String value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.frontend.TunerStatus#getGroupID <em>Group ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetGroupID()
	 * @see #getGroupID()
	 * @see #setGroupID(String)
	 * @generated
	 */
	void unsetGroupID();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.frontend.TunerStatus#getGroupID <em>Group ID</em>}'
	 * attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Group ID</em>' attribute is set.
	 * @see #unsetGroupID()
	 * @see #getGroupID()
	 * @see #setGroupID(String)
	 * @generated
	 */
	boolean isSetGroupID();

	/**
	 * Returns the value of the '<em><b>Rf Flow ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rf Flow ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rf Flow ID</em>' attribute.
	 * @see #isSetRfFlowID()
	 * @see #unsetRfFlowID()
	 * @see #setRfFlowID(String)
	 * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_RfFlowID()
	 * @model unique="false" unsettable="true"
	 * @generated
	 */
	String getRfFlowID();

	/**
	 * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getRfFlowID <em>Rf Flow ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rf Flow ID</em>' attribute.
	 * @see #isSetRfFlowID()
	 * @see #unsetRfFlowID()
	 * @see #getRfFlowID()
	 * @generated
	 */
	void setRfFlowID(String value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.frontend.TunerStatus#getRfFlowID <em>Rf Flow ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetRfFlowID()
	 * @see #getRfFlowID()
	 * @see #setRfFlowID(String)
	 * @generated
	 */
	void unsetRfFlowID();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.frontend.TunerStatus#getRfFlowID <em>Rf Flow ID</em>}'
	 * attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Rf Flow ID</em>' attribute is set.
	 * @see #unsetRfFlowID()
	 * @see #getRfFlowID()
	 * @see #setRfFlowID(String)
	 * @generated
	 */
	boolean isSetRfFlowID();

	/**
	 * Returns the value of the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enabled</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enabled</em>' attribute.
	 * @see #isSetEnabled()
	 * @see #unsetEnabled()
	 * @see #setEnabled(boolean)
	 * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_Enabled()
	 * @model unique="false" unsettable="true"
	 * @generated
	 */
	boolean isEnabled();

	/**
	 * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#isEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enabled</em>' attribute.
	 * @see #isSetEnabled()
	 * @see #unsetEnabled()
	 * @see #isEnabled()
	 * @generated
	 */
	void setEnabled(boolean value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.frontend.TunerStatus#isEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetEnabled()
	 * @see #isEnabled()
	 * @see #setEnabled(boolean)
	 * @generated
	 */
	void unsetEnabled();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.frontend.TunerStatus#isEnabled <em>Enabled</em>}' attribute
	 * is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Enabled</em>' attribute is set.
	 * @see #unsetEnabled()
	 * @see #isEnabled()
	 * @see #setEnabled(boolean)
	 * @generated
	 */
	boolean isSetEnabled();

	/**
	 * Returns the value of the '<em><b>Gain</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Gain</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Gain</em>' attribute.
	 * @see #isSetGain()
	 * @see #unsetGain()
	 * @see #setGain(double)
	 * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_Gain()
	 * @model unique="false" unsettable="true"
	 * @generated
	 */
	double getGain();

	/**
	 * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getGain <em>Gain</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Gain</em>' attribute.
	 * @see #isSetGain()
	 * @see #unsetGain()
	 * @see #getGain()
	 * @generated
	 */
	void setGain(double value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.frontend.TunerStatus#getGain <em>Gain</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetGain()
	 * @see #getGain()
	 * @see #setGain(double)
	 * @generated
	 */
	void unsetGain();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.frontend.TunerStatus#getGain <em>Gain</em>}' attribute is
	 * set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Gain</em>' attribute is set.
	 * @see #unsetGain()
	 * @see #getGain()
	 * @see #setGain(double)
	 * @generated
	 */
	boolean isSetGain();

	/**
	 * Returns the value of the '<em><b>Agc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Agc</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Agc</em>' attribute.
	 * @see #isSetAgc()
	 * @see #unsetAgc()
	 * @see #setAgc(boolean)
	 * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_Agc()
	 * @model unique="false" unsettable="true"
	 * @generated
	 */
	boolean isAgc();

	/**
	 * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#isAgc <em>Agc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Agc</em>' attribute.
	 * @see #isSetAgc()
	 * @see #unsetAgc()
	 * @see #isAgc()
	 * @generated
	 */
	void setAgc(boolean value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.frontend.TunerStatus#isAgc <em>Agc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetAgc()
	 * @see #isAgc()
	 * @see #setAgc(boolean)
	 * @generated
	 */
	void unsetAgc();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.frontend.TunerStatus#isAgc <em>Agc</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Agc</em>' attribute is set.
	 * @see #unsetAgc()
	 * @see #isAgc()
	 * @see #setAgc(boolean)
	 * @generated
	 */
	boolean isSetAgc();

	/**
	 * Returns the value of the '<em><b>Reference Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference Source</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference Source</em>' attribute.
	 * @see #isSetReferenceSource()
	 * @see #unsetReferenceSource()
	 * @see #setReferenceSource(int)
	 * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_ReferenceSource()
	 * @model unique="false" unsettable="true"
	 * @generated
	 */
	int getReferenceSource();

	/**
	 * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getReferenceSource <em>Reference Source</em>}'
	 * attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference Source</em>' attribute.
	 * @see #isSetReferenceSource()
	 * @see #unsetReferenceSource()
	 * @see #getReferenceSource()
	 * @generated
	 */
	void setReferenceSource(int value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.frontend.TunerStatus#getReferenceSource <em>Reference Source</em>}'
	 * attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetReferenceSource()
	 * @see #getReferenceSource()
	 * @see #setReferenceSource(int)
	 * @generated
	 */
	void unsetReferenceSource();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.frontend.TunerStatus#getReferenceSource <em>Reference
	 * Source</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Reference Source</em>' attribute is set.
	 * @see #unsetReferenceSource()
	 * @see #getReferenceSource()
	 * @see #setReferenceSource(int)
	 * @generated
	 */
	boolean isSetReferenceSource();

	/**
	 * Returns the value of the '<em><b>Device Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Device Control</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Device Control</em>' attribute.
	 * @see #isSetDeviceControl()
	 * @see #unsetDeviceControl()
	 * @see #setDeviceControl(boolean)
	 * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_DeviceControl()
	 * @model unique="false" unsettable="true"
	 * @generated
	 */
	boolean isDeviceControl();

	/**
	 * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#isDeviceControl <em>Device Control</em>}'
	 * attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Device Control</em>' attribute.
	 * @see #isSetDeviceControl()
	 * @see #unsetDeviceControl()
	 * @see #isDeviceControl()
	 * @generated
	 */
	void setDeviceControl(boolean value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.frontend.TunerStatus#isDeviceControl <em>Device Control</em>}'
	 * attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetDeviceControl()
	 * @see #isDeviceControl()
	 * @see #setDeviceControl(boolean)
	 * @generated
	 */
	void unsetDeviceControl();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.frontend.TunerStatus#isDeviceControl <em>Device
	 * Control</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Device Control</em>' attribute is set.
	 * @see #unsetDeviceControl()
	 * @see #isDeviceControl()
	 * @see #setDeviceControl(boolean)
	 * @generated
	 */
	boolean isSetDeviceControl();

	/**
	 * Returns the value of the '<em><b>Listener Allocations</b></em>' containment reference list.
	 * The list contents are of type {@link gov.redhawk.frontend.ListenerAllocation}.
	 * It is bidirectional and its opposite is '{@link gov.redhawk.frontend.ListenerAllocation#getTunerStatus <em>Tuner
	 * Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Listener Allocations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Listener Allocations</em>' containment reference list.
	 * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_ListenerAllocations()
	 * @see gov.redhawk.frontend.ListenerAllocation#getTunerStatus
	 * @model opposite="tunerStatus" containment="true"
	 * annotation="http://www.eclipse.org/emf/2002/GenModel property='Readonly'"
	 * @generated
	 */
	EList<ListenerAllocation> getListenerAllocations();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model dataType="gov.redhawk.frontend.ScaSimpleProperty" unique="false" propIDUnique="false"
	 * @generated
	 */
	ScaSimpleProperty getSimple(String propID);

} // TunerStatus
