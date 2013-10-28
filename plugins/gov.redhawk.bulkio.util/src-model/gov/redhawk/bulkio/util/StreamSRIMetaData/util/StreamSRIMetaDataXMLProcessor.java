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
package gov.redhawk.bulkio.util.StreamSRIMetaData.util;

import gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIMetaDataPackage;
import java.util.Map;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * @since 1.1
 * <!-- end-user-doc -->
 * @generated
 */
public class StreamSRIMetaDataXMLProcessor extends XMLProcessor
{

	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StreamSRIMetaDataXMLProcessor()
	{
		super((EPackage.Registry.INSTANCE));
		StreamSRIMetaDataPackage.eINSTANCE.eClass();
	}
	
	/**
	 * Register for "*" and "xml" file extensions the StreamSRIMetaDataResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Map<String, Resource.Factory> getRegistrations()
	{
		if (registrations == null)
		{
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new StreamSRIMetaDataResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new StreamSRIMetaDataResourceFactoryImpl());
		}
		return registrations;
	}

} //StreamSRIMetaDataXMLProcessor
