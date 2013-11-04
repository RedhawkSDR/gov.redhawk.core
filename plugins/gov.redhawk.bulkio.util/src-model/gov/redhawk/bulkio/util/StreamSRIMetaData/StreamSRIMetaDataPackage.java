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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * @since 2.0
 * <!-- end-user-doc -->
 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIMetaDataFactory
 * @model kind="package"
 * @generated
 */
public interface StreamSRIMetaDataPackage extends EPackage
{
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "StreamSRIMetaData";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://redhawk.gov/streamSRI";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "streamSRI";

	/**
	 * The package content type ID.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eCONTENT_TYPE = "gov.redhawk.bulkio.util.streamSRI";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	StreamSRIMetaDataPackage eINSTANCE = gov.redhawk.bulkio.util.StreamSRIMetaData.impl.StreamSRIMetaDataPackageImpl.init();

	/**
	 * The meta object id for the '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.impl.StreamSRIModelImpl <em>Stream SRI Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.impl.StreamSRIModelImpl
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.impl.StreamSRIMetaDataPackageImpl#getStreamSRIModel()
	 * @generated
	 */
	int STREAM_SRI_MODEL = 2;

	/**
	 * The meta object id for the '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.impl.SRIImpl <em>SRI</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.impl.SRIImpl
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.impl.StreamSRIMetaDataPackageImpl#getSRI()
	 * @generated
	 */
	int SRI = 0;

	/**
	 * The feature id for the '<em><b>Hversion</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SRI__HVERSION = 0;

	/**
	 * The feature id for the '<em><b>Xstart</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SRI__XSTART = 1;

	/**
	 * The feature id for the '<em><b>Xdelta</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SRI__XDELTA = 2;

	/**
	 * The feature id for the '<em><b>Xunits</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SRI__XUNITS = 3;

	/**
	 * The feature id for the '<em><b>Subsize</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SRI__SUBSIZE = 4;

	/**
	 * The feature id for the '<em><b>Ystart</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SRI__YSTART = 5;

	/**
	 * The feature id for the '<em><b>Ydelta</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SRI__YDELTA = 6;

	/**
	 * The feature id for the '<em><b>Yunits</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SRI__YUNITS = 7;

	/**
	 * The feature id for the '<em><b>Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SRI__MODE = 8;

	/**
	 * The feature id for the '<em><b>Stream ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SRI__STREAM_ID = 9;

	/**
	 * The feature id for the '<em><b>Blocking</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SRI__BLOCKING = 10;

	/**
	 * The feature id for the '<em><b>Keywords</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SRI__KEYWORDS = 11;

	/**
	 * The number of structural features of the '<em>SRI</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SRI_FEATURE_COUNT = 12;

	/**
	 * The meta object id for the '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.impl.StreamSRIDocumentRootImpl <em>Stream SRI Document Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.impl.StreamSRIDocumentRootImpl
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.impl.StreamSRIMetaDataPackageImpl#getStreamSRIDocumentRoot()
	 * @generated
	 */
	int STREAM_SRI_DOCUMENT_ROOT = 1;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STREAM_SRI_DOCUMENT_ROOT__MIXED = 0;

	/**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STREAM_SRI_DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

	/**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STREAM_SRI_DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

	/**
	 * The feature id for the '<em><b>Sri</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STREAM_SRI_DOCUMENT_ROOT__SRI = 3;

	/**
	 * The number of structural features of the '<em>Stream SRI Document Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STREAM_SRI_DOCUMENT_ROOT_FEATURE_COUNT = 4;

	/**
	 * The feature id for the '<em><b>Number Of Samples</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STREAM_SRI_MODEL__NUMBER_OF_SAMPLES = 0;

	/**
	 * The feature id for the '<em><b>Data Byte Order</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STREAM_SRI_MODEL__DATA_BYTE_ORDER = 1;

	/**
	 * The feature id for the '<em><b>Time</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STREAM_SRI_MODEL__TIME = 2;

	/**
	 * The feature id for the '<em><b>Bulk IO Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STREAM_SRI_MODEL__BULK_IO_TYPE = 3;

	/**
	 * The feature id for the '<em><b>Stream SRI</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STREAM_SRI_MODEL__STREAM_SRI = 4;

	/**
	 * The number of structural features of the '<em>Stream SRI Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STREAM_SRI_MODEL_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.impl.TimeImpl <em>Time</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.impl.TimeImpl
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.impl.StreamSRIMetaDataPackageImpl#getTime()
	 * @generated
	 */
	int TIME = 3;

	/**
	 * The feature id for the '<em><b>Start Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME__START_TIME = 0;

	/**
	 * The feature id for the '<em><b>End Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME__END_TIME = 1;

	/**
	 * The number of structural features of the '<em>Time</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FEATURE_COUNT = 2;

	/**
	 * Returns the meta object for class '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIModel <em>Stream SRI Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stream SRI Model</em>'.
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIModel
	 * @generated
	 */
	EClass getStreamSRIModel();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIModel#getNumberOfSamples <em>Number Of Samples</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number Of Samples</em>'.
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIModel#getNumberOfSamples()
	 * @see #getStreamSRIModel()
	 * @generated
	 */
	EAttribute getStreamSRIModel_NumberOfSamples();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIModel#getDataByteOrder <em>Data Byte Order</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Byte Order</em>'.
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIModel#getDataByteOrder()
	 * @see #getStreamSRIModel()
	 * @generated
	 */
	EAttribute getStreamSRIModel_DataByteOrder();

	/**
	 * Returns the meta object for the containment reference '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIModel#getTime <em>Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Time</em>'.
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIModel#getTime()
	 * @see #getStreamSRIModel()
	 * @generated
	 */
	EReference getStreamSRIModel_Time();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIModel#getBulkIOType <em>Bulk IO Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bulk IO Type</em>'.
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIModel#getBulkIOType()
	 * @see #getStreamSRIModel()
	 * @generated
	 */
	EAttribute getStreamSRIModel_BulkIOType();

	/**
	 * Returns the meta object for the containment reference '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIModel#getStreamSRI <em>Stream SRI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Stream SRI</em>'.
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIModel#getStreamSRI()
	 * @see #getStreamSRIModel()
	 * @generated
	 */
	EReference getStreamSRIModel_StreamSRI();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.SRI <em>SRI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>SRI</em>'.
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.SRI
	 * @generated
	 */
	EClass getSRI();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.SRI#getHversion <em>Hversion</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hversion</em>'.
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.SRI#getHversion()
	 * @see #getSRI()
	 * @generated
	 */
	EAttribute getSRI_Hversion();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.SRI#getXstart <em>Xstart</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Xstart</em>'.
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.SRI#getXstart()
	 * @see #getSRI()
	 * @generated
	 */
	EAttribute getSRI_Xstart();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.SRI#getXdelta <em>Xdelta</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Xdelta</em>'.
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.SRI#getXdelta()
	 * @see #getSRI()
	 * @generated
	 */
	EAttribute getSRI_Xdelta();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.SRI#getXunits <em>Xunits</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Xunits</em>'.
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.SRI#getXunits()
	 * @see #getSRI()
	 * @generated
	 */
	EAttribute getSRI_Xunits();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.SRI#getSubsize <em>Subsize</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Subsize</em>'.
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.SRI#getSubsize()
	 * @see #getSRI()
	 * @generated
	 */
	EAttribute getSRI_Subsize();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.SRI#getYstart <em>Ystart</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ystart</em>'.
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.SRI#getYstart()
	 * @see #getSRI()
	 * @generated
	 */
	EAttribute getSRI_Ystart();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.SRI#getYdelta <em>Ydelta</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ydelta</em>'.
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.SRI#getYdelta()
	 * @see #getSRI()
	 * @generated
	 */
	EAttribute getSRI_Ydelta();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.SRI#getYunits <em>Yunits</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Yunits</em>'.
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.SRI#getYunits()
	 * @see #getSRI()
	 * @generated
	 */
	EAttribute getSRI_Yunits();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.SRI#getMode <em>Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mode</em>'.
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.SRI#getMode()
	 * @see #getSRI()
	 * @generated
	 */
	EAttribute getSRI_Mode();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.SRI#getStreamID <em>Stream ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stream ID</em>'.
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.SRI#getStreamID()
	 * @see #getSRI()
	 * @generated
	 */
	EAttribute getSRI_StreamID();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.SRI#isBlocking <em>Blocking</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Blocking</em>'.
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.SRI#isBlocking()
	 * @see #getSRI()
	 * @generated
	 */
	EAttribute getSRI_Blocking();

	/**
	 * Returns the meta object for the containment reference '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.SRI#getKeywords <em>Keywords</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Keywords</em>'.
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.SRI#getKeywords()
	 * @see #getSRI()
	 * @generated
	 */
	EReference getSRI_Keywords();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIDocumentRoot <em>Stream SRI Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stream SRI Document Root</em>'.
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIDocumentRoot
	 * @generated
	 */
	EClass getStreamSRIDocumentRoot();

	/**
	 * Returns the meta object for the attribute list '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIDocumentRoot#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIDocumentRoot#getMixed()
	 * @see #getStreamSRIDocumentRoot()
	 * @generated
	 */
	EAttribute getStreamSRIDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the map '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIDocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIDocumentRoot#getXMLNSPrefixMap()
	 * @see #getStreamSRIDocumentRoot()
	 * @generated
	 */
	EReference getStreamSRIDocumentRoot_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIDocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIDocumentRoot#getXSISchemaLocation()
	 * @see #getStreamSRIDocumentRoot()
	 * @generated
	 */
	EReference getStreamSRIDocumentRoot_XSISchemaLocation();

	/**
	 * Returns the meta object for the containment reference '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIDocumentRoot#getSri <em>Sri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Sri</em>'.
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIDocumentRoot#getSri()
	 * @see #getStreamSRIDocumentRoot()
	 * @generated
	 */
	EReference getStreamSRIDocumentRoot_Sri();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.Time <em>Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Time</em>'.
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.Time
	 * @generated
	 */
	EClass getTime();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.Time#getStartTime <em>Start Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Time</em>'.
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.Time#getStartTime()
	 * @see #getTime()
	 * @generated
	 */
	EAttribute getTime_StartTime();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.Time#getEndTime <em>End Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>End Time</em>'.
	 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.Time#getEndTime()
	 * @see #getTime()
	 * @generated
	 */
	EAttribute getTime_EndTime();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	StreamSRIMetaDataFactory getStreamSRIMetaDataFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals
	{
		/**
		 * The meta object literal for the '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.impl.StreamSRIModelImpl <em>Stream SRI Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.impl.StreamSRIModelImpl
		 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.impl.StreamSRIMetaDataPackageImpl#getStreamSRIModel()
		 * @generated
		 */
		EClass STREAM_SRI_MODEL = eINSTANCE.getStreamSRIModel();

		/**
		 * The meta object literal for the '<em><b>Number Of Samples</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STREAM_SRI_MODEL__NUMBER_OF_SAMPLES = eINSTANCE.getStreamSRIModel_NumberOfSamples();

		/**
		 * The meta object literal for the '<em><b>Data Byte Order</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STREAM_SRI_MODEL__DATA_BYTE_ORDER = eINSTANCE.getStreamSRIModel_DataByteOrder();

		/**
		 * The meta object literal for the '<em><b>Time</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STREAM_SRI_MODEL__TIME = eINSTANCE.getStreamSRIModel_Time();

		/**
		 * The meta object literal for the '<em><b>Bulk IO Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STREAM_SRI_MODEL__BULK_IO_TYPE = eINSTANCE.getStreamSRIModel_BulkIOType();

		/**
		 * The meta object literal for the '<em><b>Stream SRI</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STREAM_SRI_MODEL__STREAM_SRI = eINSTANCE.getStreamSRIModel_StreamSRI();

		/**
		 * The meta object literal for the '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.impl.SRIImpl <em>SRI</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.impl.SRIImpl
		 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.impl.StreamSRIMetaDataPackageImpl#getSRI()
		 * @generated
		 */
		EClass SRI = eINSTANCE.getSRI();

		/**
		 * The meta object literal for the '<em><b>Hversion</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SRI__HVERSION = eINSTANCE.getSRI_Hversion();

		/**
		 * The meta object literal for the '<em><b>Xstart</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SRI__XSTART = eINSTANCE.getSRI_Xstart();

		/**
		 * The meta object literal for the '<em><b>Xdelta</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SRI__XDELTA = eINSTANCE.getSRI_Xdelta();

		/**
		 * The meta object literal for the '<em><b>Xunits</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SRI__XUNITS = eINSTANCE.getSRI_Xunits();

		/**
		 * The meta object literal for the '<em><b>Subsize</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SRI__SUBSIZE = eINSTANCE.getSRI_Subsize();

		/**
		 * The meta object literal for the '<em><b>Ystart</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SRI__YSTART = eINSTANCE.getSRI_Ystart();

		/**
		 * The meta object literal for the '<em><b>Ydelta</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SRI__YDELTA = eINSTANCE.getSRI_Ydelta();

		/**
		 * The meta object literal for the '<em><b>Yunits</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SRI__YUNITS = eINSTANCE.getSRI_Yunits();

		/**
		 * The meta object literal for the '<em><b>Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SRI__MODE = eINSTANCE.getSRI_Mode();

		/**
		 * The meta object literal for the '<em><b>Stream ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SRI__STREAM_ID = eINSTANCE.getSRI_StreamID();

		/**
		 * The meta object literal for the '<em><b>Blocking</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SRI__BLOCKING = eINSTANCE.getSRI_Blocking();

		/**
		 * The meta object literal for the '<em><b>Keywords</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SRI__KEYWORDS = eINSTANCE.getSRI_Keywords();

		/**
		 * The meta object literal for the '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.impl.StreamSRIDocumentRootImpl <em>Stream SRI Document Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.impl.StreamSRIDocumentRootImpl
		 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.impl.StreamSRIMetaDataPackageImpl#getStreamSRIDocumentRoot()
		 * @generated
		 */
		EClass STREAM_SRI_DOCUMENT_ROOT = eINSTANCE.getStreamSRIDocumentRoot();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STREAM_SRI_DOCUMENT_ROOT__MIXED = eINSTANCE.getStreamSRIDocumentRoot_Mixed();

		/**
		 * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STREAM_SRI_DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getStreamSRIDocumentRoot_XMLNSPrefixMap();

		/**
		 * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STREAM_SRI_DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getStreamSRIDocumentRoot_XSISchemaLocation();

		/**
		 * The meta object literal for the '<em><b>Sri</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STREAM_SRI_DOCUMENT_ROOT__SRI = eINSTANCE.getStreamSRIDocumentRoot_Sri();

		/**
		 * The meta object literal for the '{@link gov.redhawk.bulkio.util.StreamSRIMetaData.impl.TimeImpl <em>Time</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.impl.TimeImpl
		 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.impl.StreamSRIMetaDataPackageImpl#getTime()
		 * @generated
		 */
		EClass TIME = eINSTANCE.getTime();

		/**
		 * The meta object literal for the '<em><b>Start Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIME__START_TIME = eINSTANCE.getTime_StartTime();

		/**
		 * The meta object literal for the '<em><b>End Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIME__END_TIME = eINSTANCE.getTime_EndTime();

	}

} //StreamSRIMetaDataPackage
