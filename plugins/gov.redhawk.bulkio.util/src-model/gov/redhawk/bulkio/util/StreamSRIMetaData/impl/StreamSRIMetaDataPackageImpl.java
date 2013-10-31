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

import gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIDocumentRoot;
import gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIMetaDataFactory;
import gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIMetaDataPackage;
import gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIModel;
import gov.redhawk.bulkio.util.StreamSRIMetaData.Time;
import mil.jpeojtrs.sca.prf.PrfPackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * @since 2.0
 * <!-- end-user-doc -->
 * @generated
 */
public class StreamSRIMetaDataPackageImpl extends EPackageImpl implements StreamSRIMetaDataPackage
{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass streamSRIModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sriEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass streamSRIDocumentRootEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass timeEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIMetaDataPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private StreamSRIMetaDataPackageImpl()
	{
		super(eNS_URI, StreamSRIMetaDataFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link StreamSRIMetaDataPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static StreamSRIMetaDataPackage init()
	{
		if (isInited) return (StreamSRIMetaDataPackage)EPackage.Registry.INSTANCE.getEPackage(StreamSRIMetaDataPackage.eNS_URI);

		// Obtain or create and register package
		StreamSRIMetaDataPackageImpl theStreamSRIMetaDataPackage = (StreamSRIMetaDataPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof StreamSRIMetaDataPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new StreamSRIMetaDataPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		PrfPackage.eINSTANCE.eClass();
		XMLTypePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theStreamSRIMetaDataPackage.createPackageContents();

		// Initialize created meta-data
		theStreamSRIMetaDataPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theStreamSRIMetaDataPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(StreamSRIMetaDataPackage.eNS_URI, theStreamSRIMetaDataPackage);
		return theStreamSRIMetaDataPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStreamSRIModel()
	{
		return streamSRIModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStreamSRIModel_NumberOfSamples()
	{
		return (EAttribute)streamSRIModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStreamSRIModel_DataByteOrder()
	{
		return (EAttribute)streamSRIModelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStreamSRIModel_Time()
	{
		return (EReference)streamSRIModelEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStreamSRIModel_BulkIOType()
	{
		return (EAttribute)streamSRIModelEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStreamSRIModel_StreamSRI()
	{
		return (EReference)streamSRIModelEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSRI()
	{
		return sriEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSRI_Hversion()
	{
		return (EAttribute)sriEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSRI_Xstart()
	{
		return (EAttribute)sriEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSRI_Xdelta()
	{
		return (EAttribute)sriEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSRI_Xunits()
	{
		return (EAttribute)sriEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSRI_Subsize()
	{
		return (EAttribute)sriEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSRI_Ystart()
	{
		return (EAttribute)sriEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSRI_Ydelta()
	{
		return (EAttribute)sriEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSRI_Yunits()
	{
		return (EAttribute)sriEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSRI_Mode()
	{
		return (EAttribute)sriEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSRI_StreamID()
	{
		return (EAttribute)sriEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSRI_Blocking()
	{
		return (EAttribute)sriEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSRI_Keywords()
	{
		return (EReference)sriEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStreamSRIDocumentRoot()
	{
		return streamSRIDocumentRootEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStreamSRIDocumentRoot_Mixed()
	{
		return (EAttribute)streamSRIDocumentRootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStreamSRIDocumentRoot_XMLNSPrefixMap()
	{
		return (EReference)streamSRIDocumentRootEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStreamSRIDocumentRoot_XSISchemaLocation()
	{
		return (EReference)streamSRIDocumentRootEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStreamSRIDocumentRoot_Sri()
	{
		return (EReference)streamSRIDocumentRootEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTime()
	{
		return timeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTime_StartTime()
	{
		return (EAttribute)timeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTime_EndTime()
	{
		return (EAttribute)timeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StreamSRIMetaDataFactory getStreamSRIMetaDataFactory()
	{
		return (StreamSRIMetaDataFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents()
	{
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		sriEClass = createEClass(SRI);
		createEAttribute(sriEClass, SRI__HVERSION);
		createEAttribute(sriEClass, SRI__XSTART);
		createEAttribute(sriEClass, SRI__XDELTA);
		createEAttribute(sriEClass, SRI__XUNITS);
		createEAttribute(sriEClass, SRI__SUBSIZE);
		createEAttribute(sriEClass, SRI__YSTART);
		createEAttribute(sriEClass, SRI__YDELTA);
		createEAttribute(sriEClass, SRI__YUNITS);
		createEAttribute(sriEClass, SRI__MODE);
		createEAttribute(sriEClass, SRI__STREAM_ID);
		createEAttribute(sriEClass, SRI__BLOCKING);
		createEReference(sriEClass, SRI__KEYWORDS);

		streamSRIDocumentRootEClass = createEClass(STREAM_SRI_DOCUMENT_ROOT);
		createEAttribute(streamSRIDocumentRootEClass, STREAM_SRI_DOCUMENT_ROOT__MIXED);
		createEReference(streamSRIDocumentRootEClass, STREAM_SRI_DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(streamSRIDocumentRootEClass, STREAM_SRI_DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEReference(streamSRIDocumentRootEClass, STREAM_SRI_DOCUMENT_ROOT__SRI);

		streamSRIModelEClass = createEClass(STREAM_SRI_MODEL);
		createEAttribute(streamSRIModelEClass, STREAM_SRI_MODEL__NUMBER_OF_SAMPLES);
		createEAttribute(streamSRIModelEClass, STREAM_SRI_MODEL__DATA_BYTE_ORDER);
		createEReference(streamSRIModelEClass, STREAM_SRI_MODEL__TIME);
		createEAttribute(streamSRIModelEClass, STREAM_SRI_MODEL__BULK_IO_TYPE);
		createEReference(streamSRIModelEClass, STREAM_SRI_MODEL__STREAM_SRI);

		timeEClass = createEClass(TIME);
		createEAttribute(timeEClass, TIME__START_TIME);
		createEAttribute(timeEClass, TIME__END_TIME);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents()
	{
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);
		PrfPackage thePrfPackage = (PrfPackage)EPackage.Registry.INSTANCE.getEPackage(PrfPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(sriEClass, gov.redhawk.bulkio.util.StreamSRIMetaData.SRI.class, "SRI", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSRI_Hversion(), theXMLTypePackage.getInt(), "hversion", null, 1, 1, gov.redhawk.bulkio.util.StreamSRIMetaData.SRI.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSRI_Xstart(), theXMLTypePackage.getDouble(), "xstart", null, 1, 1, gov.redhawk.bulkio.util.StreamSRIMetaData.SRI.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSRI_Xdelta(), theXMLTypePackage.getDouble(), "xdelta", null, 1, 1, gov.redhawk.bulkio.util.StreamSRIMetaData.SRI.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSRI_Xunits(), theXMLTypePackage.getShort(), "xunits", null, 1, 1, gov.redhawk.bulkio.util.StreamSRIMetaData.SRI.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSRI_Subsize(), theXMLTypePackage.getDouble(), "subsize", null, 1, 1, gov.redhawk.bulkio.util.StreamSRIMetaData.SRI.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSRI_Ystart(), theXMLTypePackage.getDouble(), "ystart", null, 1, 1, gov.redhawk.bulkio.util.StreamSRIMetaData.SRI.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSRI_Ydelta(), theXMLTypePackage.getDouble(), "ydelta", null, 1, 1, gov.redhawk.bulkio.util.StreamSRIMetaData.SRI.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSRI_Yunits(), theXMLTypePackage.getShort(), "yunits", null, 1, 1, gov.redhawk.bulkio.util.StreamSRIMetaData.SRI.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSRI_Mode(), theXMLTypePackage.getShort(), "mode", null, 1, 1, gov.redhawk.bulkio.util.StreamSRIMetaData.SRI.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSRI_StreamID(), theXMLTypePackage.getString(), "streamID", null, 1, 1, gov.redhawk.bulkio.util.StreamSRIMetaData.SRI.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSRI_Blocking(), theXMLTypePackage.getBoolean(), "blocking", null, 1, 1, gov.redhawk.bulkio.util.StreamSRIMetaData.SRI.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSRI_Keywords(), thePrfPackage.getProperties(), null, "keywords", null, 1, 1, gov.redhawk.bulkio.util.StreamSRIMetaData.SRI.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(streamSRIDocumentRootEClass, StreamSRIDocumentRoot.class, "StreamSRIDocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStreamSRIDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStreamSRIDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStreamSRIDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStreamSRIDocumentRoot_Sri(), this.getStreamSRIModel(), null, "sri", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(streamSRIModelEClass, StreamSRIModel.class, "StreamSRIModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStreamSRIModel_NumberOfSamples(), theXMLTypePackage.getLong(), "numberOfSamples", null, 1, 1, StreamSRIModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStreamSRIModel_DataByteOrder(), theXMLTypePackage.getString(), "dataByteOrder", "", 1, 1, StreamSRIModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStreamSRIModel_Time(), this.getTime(), null, "time", null, 1, 1, StreamSRIModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStreamSRIModel_BulkIOType(), theXMLTypePackage.getString(), "bulkIOType", null, 1, 1, StreamSRIModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStreamSRIModel_StreamSRI(), this.getSRI(), null, "streamSRI", null, 1, 1, StreamSRIModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(timeEClass, Time.class, "Time", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTime_StartTime(), theXMLTypePackage.getString(), "startTime", "0", 1, 1, Time.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTime_EndTime(), theXMLTypePackage.getString(), "endTime", "0", 1, 1, Time.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations()
	{
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		
		addAnnotation
		  (sriEClass, 
		   source, 
		   new String[] 
		   {
			 "name", "SRI",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getSRI_Hversion(), 
		   source, 
		   new String[] 
		   {
			 "kind", "element",
			 "name", "hversion"
		   });		
		addAnnotation
		  (getSRI_Xstart(), 
		   source, 
		   new String[] 
		   {
			 "kind", "element",
			 "name", "xstart"
		   });		
		addAnnotation
		  (getSRI_Xdelta(), 
		   source, 
		   new String[] 
		   {
			 "kind", "element",
			 "name", "xdelta"
		   });		
		addAnnotation
		  (getSRI_Xunits(), 
		   source, 
		   new String[] 
		   {
			 "kind", "element",
			 "name", "xunits"
		   });		
		addAnnotation
		  (getSRI_Subsize(), 
		   source, 
		   new String[] 
		   {
			 "kind", "element",
			 "name", "subsize"
		   });		
		addAnnotation
		  (getSRI_Ystart(), 
		   source, 
		   new String[] 
		   {
			 "kind", "element",
			 "name", "ystart"
		   });		
		addAnnotation
		  (getSRI_Ydelta(), 
		   source, 
		   new String[] 
		   {
			 "kind", "element",
			 "name", "ydelta"
		   });		
		addAnnotation
		  (getSRI_Yunits(), 
		   source, 
		   new String[] 
		   {
			 "kind", "element",
			 "name", "yunits"
		   });		
		addAnnotation
		  (getSRI_Mode(), 
		   source, 
		   new String[] 
		   {
			 "kind", "element",
			 "name", "mode"
		   });		
		addAnnotation
		  (getSRI_StreamID(), 
		   source, 
		   new String[] 
		   {
			 "kind", "element",
			 "name", "streamID"
		   });		
		addAnnotation
		  (getSRI_Blocking(), 
		   source, 
		   new String[] 
		   {
			 "kind", "element",
			 "name", "blocking"
		   });		
		addAnnotation
		  (getSRI_Keywords(), 
		   source, 
		   new String[] 
		   {
			 "kind", "element",
			 "name", "keywords"
		   });		
		addAnnotation
		  (streamSRIDocumentRootEClass, 
		   source, 
		   new String[] 
		   {
			 "name", "",
			 "kind", "mixed"
		   });		
		addAnnotation
		  (getStreamSRIDocumentRoot_Mixed(), 
		   source, 
		   new String[] 
		   {
			 "kind", "elementWildcard",
			 "name", ":mixed"
		   });		
		addAnnotation
		  (getStreamSRIDocumentRoot_XMLNSPrefixMap(), 
		   source, 
		   new String[] 
		   {
			 "kind", "attribute",
			 "name", "xmlns:prefix"
		   });		
		addAnnotation
		  (getStreamSRIDocumentRoot_XSISchemaLocation(), 
		   source, 
		   new String[] 
		   {
			 "kind", "attribute",
			 "name", "xsi:schemaLocation"
		   });		
		addAnnotation
		  (getStreamSRIDocumentRoot_Sri(), 
		   source, 
		   new String[] 
		   {
			 "kind", "element",
			 "name", "sri",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (streamSRIModelEClass, 
		   source, 
		   new String[] 
		   {
			 "name", "StreamSRIModel",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getStreamSRIModel_NumberOfSamples(), 
		   source, 
		   new String[] 
		   {
			 "kind", "element",
			 "name", "numberOfSamples"
		   });		
		addAnnotation
		  (getStreamSRIModel_DataByteOrder(), 
		   source, 
		   new String[] 
		   {
			 "kind", "element",
			 "name", "dataByteOrder"
		   });		
		addAnnotation
		  (getStreamSRIModel_Time(), 
		   source, 
		   new String[] 
		   {
			 "kind", "element",
			 "name", "time"
		   });		
		addAnnotation
		  (getStreamSRIModel_BulkIOType(), 
		   source, 
		   new String[] 
		   {
			 "kind", "element",
			 "name", "BulkIOType"
		   });		
		addAnnotation
		  (getStreamSRIModel_StreamSRI(), 
		   source, 
		   new String[] 
		   {
			 "kind", "element",
			 "name", "StreamSRI"
		   });		
		addAnnotation
		  (timeEClass, 
		   source, 
		   new String[] 
		   {
			 "name", "Time",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTime_StartTime(), 
		   source, 
		   new String[] 
		   {
			 "kind", "element",
			 "name", "startTime"
		   });		
		addAnnotation
		  (getTime_EndTime(), 
		   source, 
		   new String[] 
		   {
			 "kind", "element",
			 "name", "endTime"
		   });
	}

} //StreamSRIMetaDataPackageImpl
