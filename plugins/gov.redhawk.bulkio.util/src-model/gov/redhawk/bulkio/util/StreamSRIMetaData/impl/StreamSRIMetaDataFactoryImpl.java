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

import gov.redhawk.bulkio.util.StreamSRIMetaData.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * @since 2.0
 * <!-- end-user-doc -->
 * @generated
 */
public class StreamSRIMetaDataFactoryImpl extends EFactoryImpl implements StreamSRIMetaDataFactory
{
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static StreamSRIMetaDataFactory init()
	{
		try
		{
			StreamSRIMetaDataFactory theStreamSRIMetaDataFactory = (StreamSRIMetaDataFactory)EPackage.Registry.INSTANCE.getEFactory(StreamSRIMetaDataPackage.eNS_URI);
			if (theStreamSRIMetaDataFactory != null)
			{
				return theStreamSRIMetaDataFactory;
			}
		}
		catch (Exception exception)
		{
			EcorePlugin.INSTANCE.log(exception);
		}
		return new StreamSRIMetaDataFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StreamSRIMetaDataFactoryImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass)
	{
		switch (eClass.getClassifierID())
		{
			case StreamSRIMetaDataPackage.SRI: return createSRI();
			case StreamSRIMetaDataPackage.STREAM_SRI_DOCUMENT_ROOT: return createStreamSRIDocumentRoot();
			case StreamSRIMetaDataPackage.STREAM_SRI_MODEL: return createStreamSRIModel();
			case StreamSRIMetaDataPackage.TIME: return createTime();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StreamSRIModel createStreamSRIModel()
	{
		StreamSRIModelImpl streamSRIModel = new StreamSRIModelImpl();
		return streamSRIModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SRI createSRI()
	{
		SRIImpl sri = new SRIImpl();
		return sri;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StreamSRIDocumentRoot createStreamSRIDocumentRoot()
	{
		StreamSRIDocumentRootImpl streamSRIDocumentRoot = new StreamSRIDocumentRootImpl();
		return streamSRIDocumentRoot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Time createTime()
	{
		TimeImpl time = new TimeImpl();
		return time;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StreamSRIMetaDataPackage getStreamSRIMetaDataPackage()
	{
		return (StreamSRIMetaDataPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static StreamSRIMetaDataPackage getPackage()
	{
		return StreamSRIMetaDataPackage.eINSTANCE;
	}

} //StreamSRIMetaDataFactoryImpl
