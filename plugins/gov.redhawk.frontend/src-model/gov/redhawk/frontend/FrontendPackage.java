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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see gov.redhawk.frontend.FrontendFactory
 * @model kind="package"
 * annotation="http://www.eclipse.org/emf/2002/GenModel prefix='Frontend' dataTypeConverters='true'
 * binaryCompatibleReflectiveMethods='true' fileExtensions='xml' colorProviders='true' fontProviders='true'
 * resource='XML' templateDirectory='/gov.redhawk.frontend/templates' forceOverwrite='true'
 * modelPluginVariables='org.eclipse.xtext.xbase.lib' tableProviders='true' runtimeVersion='2.9' codeFormatting='true'
 * commentFormatting='true' dynamicTemplates='true' contentTypeIdentifier='http://redhawk.gov/frontend/1.0.0'
 * modelDirectory='/gov.redhawk.frontend/src-model' editDirectory='/gov.redhawk.frontend.edit/src-gen'
 * basePackage='gov.redhawk'"
 * @generated
 */
public interface FrontendPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "frontend";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://redhawk.gov/frontend/1.0.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "frontend";

	/**
	 * The package content type ID.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eCONTENT_TYPE = "http://redhawk.gov/frontend/1.0.0";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	FrontendPackage eINSTANCE = gov.redhawk.frontend.impl.FrontendPackageImpl.init();

	/**
	 * The meta object id for the '{@link gov.redhawk.frontend.impl.TunerContainerImpl <em>Tuner Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.frontend.impl.TunerContainerImpl
	 * @see gov.redhawk.frontend.impl.FrontendPackageImpl#getTunerContainer()
	 * @generated
	 */
	int TUNER_CONTAINER = 0;

	/**
	 * The feature id for the '<em><b>Tuner Status</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUNER_CONTAINER__TUNER_STATUS = 0;

	/**
	 * The feature id for the '<em><b>Unallocated Container</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUNER_CONTAINER__UNALLOCATED_CONTAINER = 1;

	/**
	 * The number of structural features of the '<em>Tuner Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUNER_CONTAINER_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Tuner Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUNER_CONTAINER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link gov.redhawk.frontend.impl.UnallocatedTunerContainerImpl <em>Unallocated Tuner
	 * Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.frontend.impl.UnallocatedTunerContainerImpl
	 * @see gov.redhawk.frontend.impl.FrontendPackageImpl#getUnallocatedTunerContainer()
	 * @generated
	 */
	int UNALLOCATED_TUNER_CONTAINER = 1;

	/**
	 * The feature id for the '<em><b>Tuner Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNALLOCATED_TUNER_CONTAINER__TUNER_CONTAINER = 0;

	/**
	 * The feature id for the '<em><b>Tuner Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNALLOCATED_TUNER_CONTAINER__TUNER_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNALLOCATED_TUNER_CONTAINER__COUNT = 2;

	/**
	 * The number of structural features of the '<em>Unallocated Tuner Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNALLOCATED_TUNER_CONTAINER_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Unallocated Tuner Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNALLOCATED_TUNER_CONTAINER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link gov.redhawk.frontend.impl.TunerStatusImpl <em>Tuner Status</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.frontend.impl.TunerStatusImpl
	 * @see gov.redhawk.frontend.impl.FrontendPackageImpl#getTunerStatus()
	 * @generated
	 */
	int TUNER_STATUS = 2;

	/**
	 * The feature id for the '<em><b>Tuner Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUNER_STATUS__TUNER_CONTAINER = 0;

	/**
	 * The feature id for the '<em><b>Tuner Status Struct</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUNER_STATUS__TUNER_STATUS_STRUCT = 1;

	/**
	 * The feature id for the '<em><b>Simples</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUNER_STATUS__SIMPLES = 2;

	/**
	 * The feature id for the '<em><b>Allocated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUNER_STATUS__ALLOCATED = 3;

	/**
	 * The feature id for the '<em><b>Tuner ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUNER_STATUS__TUNER_ID = 4;

	/**
	 * The feature id for the '<em><b>Tuner Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUNER_STATUS__TUNER_TYPE = 5;

	/**
	 * The feature id for the '<em><b>Allocation ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUNER_STATUS__ALLOCATION_ID = 6;

	/**
	 * The feature id for the '<em><b>Center Frequency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUNER_STATUS__CENTER_FREQUENCY = 7;

	/**
	 * The feature id for the '<em><b>Bandwidth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUNER_STATUS__BANDWIDTH = 8;

	/**
	 * The feature id for the '<em><b>Sample Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUNER_STATUS__SAMPLE_RATE = 9;

	/**
	 * The feature id for the '<em><b>Group ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUNER_STATUS__GROUP_ID = 10;

	/**
	 * The feature id for the '<em><b>Rf Flow ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUNER_STATUS__RF_FLOW_ID = 11;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUNER_STATUS__ENABLED = 12;

	/**
	 * The feature id for the '<em><b>Gain</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUNER_STATUS__GAIN = 13;

	/**
	 * The feature id for the '<em><b>Agc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUNER_STATUS__AGC = 14;

	/**
	 * The feature id for the '<em><b>Reference Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUNER_STATUS__REFERENCE_SOURCE = 15;

	/**
	 * The feature id for the '<em><b>Device Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUNER_STATUS__DEVICE_CONTROL = 16;

	/**
	 * The feature id for the '<em><b>Listener Allocations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUNER_STATUS__LISTENER_ALLOCATIONS = 17;

	/**
	 * The number of structural features of the '<em>Tuner Status</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUNER_STATUS_FEATURE_COUNT = 18;

	/**
	 * The operation id for the '<em>Get Simple</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUNER_STATUS___GET_SIMPLE__STRING = 0;

	/**
	 * The number of operations of the '<em>Tuner Status</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUNER_STATUS_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link gov.redhawk.frontend.impl.ListenerAllocationImpl <em>Listener
	 * Allocation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.frontend.impl.ListenerAllocationImpl
	 * @see gov.redhawk.frontend.impl.FrontendPackageImpl#getListenerAllocation()
	 * @generated
	 */
	int LISTENER_ALLOCATION = 3;

	/**
	 * The feature id for the '<em><b>Tuner Status</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LISTENER_ALLOCATION__TUNER_STATUS = 0;

	/**
	 * The feature id for the '<em><b>Listener ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LISTENER_ALLOCATION__LISTENER_ID = 1;

	/**
	 * The number of structural features of the '<em>Listener Allocation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LISTENER_ALLOCATION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Listener Allocation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LISTENER_ALLOCATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '<em>Sca Struct Property</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaStructProperty
	 * @see gov.redhawk.frontend.impl.FrontendPackageImpl#getScaStructProperty()
	 * @generated
	 */
	int SCA_STRUCT_PROPERTY = 4;

	/**
	 * The meta object id for the '<em>Sca Simple Property</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaSimpleProperty
	 * @see gov.redhawk.frontend.impl.FrontendPackageImpl#getScaSimpleProperty()
	 * @generated
	 */
	int SCA_SIMPLE_PROPERTY = 5;

	/**
	 * Returns the meta object for class '{@link gov.redhawk.frontend.TunerContainer <em>Tuner Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tuner Container</em>'.
	 * @see gov.redhawk.frontend.TunerContainer
	 * @generated
	 */
	EClass getTunerContainer();

	/**
	 * Returns the meta object for the containment reference list
	 * '{@link gov.redhawk.frontend.TunerContainer#getTunerStatus <em>Tuner Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tuner Status</em>'.
	 * @see gov.redhawk.frontend.TunerContainer#getTunerStatus()
	 * @see #getTunerContainer()
	 * @generated
	 */
	EReference getTunerContainer_TunerStatus();

	/**
	 * Returns the meta object for the containment reference list
	 * '{@link gov.redhawk.frontend.TunerContainer#getUnallocatedContainer <em>Unallocated Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Unallocated Container</em>'.
	 * @see gov.redhawk.frontend.TunerContainer#getUnallocatedContainer()
	 * @see #getTunerContainer()
	 * @generated
	 */
	EReference getTunerContainer_UnallocatedContainer();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.frontend.UnallocatedTunerContainer <em>Unallocated Tuner
	 * Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unallocated Tuner Container</em>'.
	 * @see gov.redhawk.frontend.UnallocatedTunerContainer
	 * @generated
	 */
	EClass getUnallocatedTunerContainer();

	/**
	 * Returns the meta object for the container reference
	 * '{@link gov.redhawk.frontend.UnallocatedTunerContainer#getTunerContainer <em>Tuner Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Tuner Container</em>'.
	 * @see gov.redhawk.frontend.UnallocatedTunerContainer#getTunerContainer()
	 * @see #getUnallocatedTunerContainer()
	 * @generated
	 */
	EReference getUnallocatedTunerContainer_TunerContainer();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.frontend.UnallocatedTunerContainer#getTunerType
	 * <em>Tuner Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tuner Type</em>'.
	 * @see gov.redhawk.frontend.UnallocatedTunerContainer#getTunerType()
	 * @see #getUnallocatedTunerContainer()
	 * @generated
	 */
	EAttribute getUnallocatedTunerContainer_TunerType();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.frontend.UnallocatedTunerContainer#getCount
	 * <em>Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Count</em>'.
	 * @see gov.redhawk.frontend.UnallocatedTunerContainer#getCount()
	 * @see #getUnallocatedTunerContainer()
	 * @generated
	 */
	EAttribute getUnallocatedTunerContainer_Count();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.frontend.TunerStatus <em>Tuner Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tuner Status</em>'.
	 * @see gov.redhawk.frontend.TunerStatus
	 * @generated
	 */
	EClass getTunerStatus();

	/**
	 * Returns the meta object for the container reference '{@link gov.redhawk.frontend.TunerStatus#getTunerContainer
	 * <em>Tuner Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Tuner Container</em>'.
	 * @see gov.redhawk.frontend.TunerStatus#getTunerContainer()
	 * @see #getTunerStatus()
	 * @generated
	 */
	EReference getTunerStatus_TunerContainer();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#getTunerStatusStruct <em>Tuner
	 * Status Struct</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tuner Status Struct</em>'.
	 * @see gov.redhawk.frontend.TunerStatus#getTunerStatusStruct()
	 * @see #getTunerStatus()
	 * @generated
	 */
	EAttribute getTunerStatus_TunerStatusStruct();

	/**
	 * Returns the meta object for the attribute list '{@link gov.redhawk.frontend.TunerStatus#getSimples
	 * <em>Simples</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Simples</em>'.
	 * @see gov.redhawk.frontend.TunerStatus#getSimples()
	 * @see #getTunerStatus()
	 * @generated
	 */
	EAttribute getTunerStatus_Simples();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#isAllocated
	 * <em>Allocated</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Allocated</em>'.
	 * @see gov.redhawk.frontend.TunerStatus#isAllocated()
	 * @see #getTunerStatus()
	 * @generated
	 */
	EAttribute getTunerStatus_Allocated();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#getTunerID <em>Tuner
	 * ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tuner ID</em>'.
	 * @see gov.redhawk.frontend.TunerStatus#getTunerID()
	 * @see #getTunerStatus()
	 * @generated
	 */
	EAttribute getTunerStatus_TunerID();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#getTunerType <em>Tuner
	 * Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tuner Type</em>'.
	 * @see gov.redhawk.frontend.TunerStatus#getTunerType()
	 * @see #getTunerStatus()
	 * @generated
	 */
	EAttribute getTunerStatus_TunerType();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#getAllocationID <em>Allocation
	 * ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Allocation ID</em>'.
	 * @see gov.redhawk.frontend.TunerStatus#getAllocationID()
	 * @see #getTunerStatus()
	 * @generated
	 */
	EAttribute getTunerStatus_AllocationID();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#getCenterFrequency <em>Center
	 * Frequency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Center Frequency</em>'.
	 * @see gov.redhawk.frontend.TunerStatus#getCenterFrequency()
	 * @see #getTunerStatus()
	 * @generated
	 */
	EAttribute getTunerStatus_CenterFrequency();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#getBandwidth
	 * <em>Bandwidth</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bandwidth</em>'.
	 * @see gov.redhawk.frontend.TunerStatus#getBandwidth()
	 * @see #getTunerStatus()
	 * @generated
	 */
	EAttribute getTunerStatus_Bandwidth();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#getSampleRate <em>Sample
	 * Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sample Rate</em>'.
	 * @see gov.redhawk.frontend.TunerStatus#getSampleRate()
	 * @see #getTunerStatus()
	 * @generated
	 */
	EAttribute getTunerStatus_SampleRate();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#getGroupID <em>Group
	 * ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Group ID</em>'.
	 * @see gov.redhawk.frontend.TunerStatus#getGroupID()
	 * @see #getTunerStatus()
	 * @generated
	 */
	EAttribute getTunerStatus_GroupID();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#getRfFlowID <em>Rf Flow
	 * ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rf Flow ID</em>'.
	 * @see gov.redhawk.frontend.TunerStatus#getRfFlowID()
	 * @see #getTunerStatus()
	 * @generated
	 */
	EAttribute getTunerStatus_RfFlowID();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#isEnabled <em>Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enabled</em>'.
	 * @see gov.redhawk.frontend.TunerStatus#isEnabled()
	 * @see #getTunerStatus()
	 * @generated
	 */
	EAttribute getTunerStatus_Enabled();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#getGain <em>Gain</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Gain</em>'.
	 * @see gov.redhawk.frontend.TunerStatus#getGain()
	 * @see #getTunerStatus()
	 * @generated
	 */
	EAttribute getTunerStatus_Gain();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#isAgc <em>Agc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Agc</em>'.
	 * @see gov.redhawk.frontend.TunerStatus#isAgc()
	 * @see #getTunerStatus()
	 * @generated
	 */
	EAttribute getTunerStatus_Agc();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#getReferenceSource
	 * <em>Reference Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reference Source</em>'.
	 * @see gov.redhawk.frontend.TunerStatus#getReferenceSource()
	 * @see #getTunerStatus()
	 * @generated
	 */
	EAttribute getTunerStatus_ReferenceSource();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#isDeviceControl <em>Device
	 * Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Device Control</em>'.
	 * @see gov.redhawk.frontend.TunerStatus#isDeviceControl()
	 * @see #getTunerStatus()
	 * @generated
	 */
	EAttribute getTunerStatus_DeviceControl();

	/**
	 * Returns the meta object for the containment reference list
	 * '{@link gov.redhawk.frontend.TunerStatus#getListenerAllocations <em>Listener Allocations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Listener Allocations</em>'.
	 * @see gov.redhawk.frontend.TunerStatus#getListenerAllocations()
	 * @see #getTunerStatus()
	 * @generated
	 */
	EReference getTunerStatus_ListenerAllocations();

	/**
	 * Returns the meta object for the '{@link gov.redhawk.frontend.TunerStatus#getSimple(java.lang.String) <em>Get
	 * Simple</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Simple</em>' operation.
	 * @see gov.redhawk.frontend.TunerStatus#getSimple(java.lang.String)
	 * @generated
	 */
	EOperation getTunerStatus__GetSimple__String();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.frontend.ListenerAllocation <em>Listener Allocation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Listener Allocation</em>'.
	 * @see gov.redhawk.frontend.ListenerAllocation
	 * @generated
	 */
	EClass getListenerAllocation();

	/**
	 * Returns the meta object for the container reference
	 * '{@link gov.redhawk.frontend.ListenerAllocation#getTunerStatus <em>Tuner Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Tuner Status</em>'.
	 * @see gov.redhawk.frontend.ListenerAllocation#getTunerStatus()
	 * @see #getListenerAllocation()
	 * @generated
	 */
	EReference getListenerAllocation_TunerStatus();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.frontend.ListenerAllocation#getListenerID
	 * <em>Listener ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Listener ID</em>'.
	 * @see gov.redhawk.frontend.ListenerAllocation#getListenerID()
	 * @see #getListenerAllocation()
	 * @generated
	 */
	EAttribute getListenerAllocation_ListenerID();

	/**
	 * Returns the meta object for data type '{@link gov.redhawk.model.sca.ScaStructProperty <em>Sca Struct
	 * Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Sca Struct Property</em>'.
	 * @see gov.redhawk.model.sca.ScaStructProperty
	 * @model instanceClass="gov.redhawk.model.sca.ScaStructProperty"
	 * @generated
	 */
	EDataType getScaStructProperty();

	/**
	 * Returns the meta object for data type '{@link gov.redhawk.model.sca.ScaSimpleProperty <em>Sca Simple
	 * Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Sca Simple Property</em>'.
	 * @see gov.redhawk.model.sca.ScaSimpleProperty
	 * @model instanceClass="gov.redhawk.model.sca.ScaSimpleProperty"
	 * @generated
	 */
	EDataType getScaSimpleProperty();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	FrontendFactory getFrontendFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each operation of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link gov.redhawk.frontend.impl.TunerContainerImpl <em>Tuner
		 * Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.frontend.impl.TunerContainerImpl
		 * @see gov.redhawk.frontend.impl.FrontendPackageImpl#getTunerContainer()
		 * @generated
		 */
		EClass TUNER_CONTAINER = eINSTANCE.getTunerContainer();

		/**
		 * The meta object literal for the '<em><b>Tuner Status</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TUNER_CONTAINER__TUNER_STATUS = eINSTANCE.getTunerContainer_TunerStatus();

		/**
		 * The meta object literal for the '<em><b>Unallocated Container</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TUNER_CONTAINER__UNALLOCATED_CONTAINER = eINSTANCE.getTunerContainer_UnallocatedContainer();

		/**
		 * The meta object literal for the '{@link gov.redhawk.frontend.impl.UnallocatedTunerContainerImpl
		 * <em>Unallocated Tuner Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.frontend.impl.UnallocatedTunerContainerImpl
		 * @see gov.redhawk.frontend.impl.FrontendPackageImpl#getUnallocatedTunerContainer()
		 * @generated
		 */
		EClass UNALLOCATED_TUNER_CONTAINER = eINSTANCE.getUnallocatedTunerContainer();

		/**
		 * The meta object literal for the '<em><b>Tuner Container</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNALLOCATED_TUNER_CONTAINER__TUNER_CONTAINER = eINSTANCE.getUnallocatedTunerContainer_TunerContainer();

		/**
		 * The meta object literal for the '<em><b>Tuner Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNALLOCATED_TUNER_CONTAINER__TUNER_TYPE = eINSTANCE.getUnallocatedTunerContainer_TunerType();

		/**
		 * The meta object literal for the '<em><b>Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNALLOCATED_TUNER_CONTAINER__COUNT = eINSTANCE.getUnallocatedTunerContainer_Count();

		/**
		 * The meta object literal for the '{@link gov.redhawk.frontend.impl.TunerStatusImpl <em>Tuner Status</em>}'
		 * class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.frontend.impl.TunerStatusImpl
		 * @see gov.redhawk.frontend.impl.FrontendPackageImpl#getTunerStatus()
		 * @generated
		 */
		EClass TUNER_STATUS = eINSTANCE.getTunerStatus();

		/**
		 * The meta object literal for the '<em><b>Tuner Container</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TUNER_STATUS__TUNER_CONTAINER = eINSTANCE.getTunerStatus_TunerContainer();

		/**
		 * The meta object literal for the '<em><b>Tuner Status Struct</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TUNER_STATUS__TUNER_STATUS_STRUCT = eINSTANCE.getTunerStatus_TunerStatusStruct();

		/**
		 * The meta object literal for the '<em><b>Simples</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TUNER_STATUS__SIMPLES = eINSTANCE.getTunerStatus_Simples();

		/**
		 * The meta object literal for the '<em><b>Allocated</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TUNER_STATUS__ALLOCATED = eINSTANCE.getTunerStatus_Allocated();

		/**
		 * The meta object literal for the '<em><b>Tuner ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TUNER_STATUS__TUNER_ID = eINSTANCE.getTunerStatus_TunerID();

		/**
		 * The meta object literal for the '<em><b>Tuner Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TUNER_STATUS__TUNER_TYPE = eINSTANCE.getTunerStatus_TunerType();

		/**
		 * The meta object literal for the '<em><b>Allocation ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TUNER_STATUS__ALLOCATION_ID = eINSTANCE.getTunerStatus_AllocationID();

		/**
		 * The meta object literal for the '<em><b>Center Frequency</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TUNER_STATUS__CENTER_FREQUENCY = eINSTANCE.getTunerStatus_CenterFrequency();

		/**
		 * The meta object literal for the '<em><b>Bandwidth</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TUNER_STATUS__BANDWIDTH = eINSTANCE.getTunerStatus_Bandwidth();

		/**
		 * The meta object literal for the '<em><b>Sample Rate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TUNER_STATUS__SAMPLE_RATE = eINSTANCE.getTunerStatus_SampleRate();

		/**
		 * The meta object literal for the '<em><b>Group ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TUNER_STATUS__GROUP_ID = eINSTANCE.getTunerStatus_GroupID();

		/**
		 * The meta object literal for the '<em><b>Rf Flow ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TUNER_STATUS__RF_FLOW_ID = eINSTANCE.getTunerStatus_RfFlowID();

		/**
		 * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TUNER_STATUS__ENABLED = eINSTANCE.getTunerStatus_Enabled();

		/**
		 * The meta object literal for the '<em><b>Gain</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TUNER_STATUS__GAIN = eINSTANCE.getTunerStatus_Gain();

		/**
		 * The meta object literal for the '<em><b>Agc</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TUNER_STATUS__AGC = eINSTANCE.getTunerStatus_Agc();

		/**
		 * The meta object literal for the '<em><b>Reference Source</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TUNER_STATUS__REFERENCE_SOURCE = eINSTANCE.getTunerStatus_ReferenceSource();

		/**
		 * The meta object literal for the '<em><b>Device Control</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TUNER_STATUS__DEVICE_CONTROL = eINSTANCE.getTunerStatus_DeviceControl();

		/**
		 * The meta object literal for the '<em><b>Listener Allocations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TUNER_STATUS__LISTENER_ALLOCATIONS = eINSTANCE.getTunerStatus_ListenerAllocations();

		/**
		 * The meta object literal for the '<em><b>Get Simple</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation TUNER_STATUS___GET_SIMPLE__STRING = eINSTANCE.getTunerStatus__GetSimple__String();

		/**
		 * The meta object literal for the '{@link gov.redhawk.frontend.impl.ListenerAllocationImpl <em>Listener
		 * Allocation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.frontend.impl.ListenerAllocationImpl
		 * @see gov.redhawk.frontend.impl.FrontendPackageImpl#getListenerAllocation()
		 * @generated
		 */
		EClass LISTENER_ALLOCATION = eINSTANCE.getListenerAllocation();

		/**
		 * The meta object literal for the '<em><b>Tuner Status</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LISTENER_ALLOCATION__TUNER_STATUS = eINSTANCE.getListenerAllocation_TunerStatus();

		/**
		 * The meta object literal for the '<em><b>Listener ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LISTENER_ALLOCATION__LISTENER_ID = eINSTANCE.getListenerAllocation_ListenerID();

		/**
		 * The meta object literal for the '<em>Sca Struct Property</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.model.sca.ScaStructProperty
		 * @see gov.redhawk.frontend.impl.FrontendPackageImpl#getScaStructProperty()
		 * @generated
		 */
		EDataType SCA_STRUCT_PROPERTY = eINSTANCE.getScaStructProperty();

		/**
		 * The meta object literal for the '<em>Sca Simple Property</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.model.sca.ScaSimpleProperty
		 * @see gov.redhawk.frontend.impl.FrontendPackageImpl#getScaSimpleProperty()
		 * @generated
		 */
		EDataType SCA_SIMPLE_PROPERTY = eINSTANCE.getScaSimpleProperty();

	}

} // FrontendPackage
