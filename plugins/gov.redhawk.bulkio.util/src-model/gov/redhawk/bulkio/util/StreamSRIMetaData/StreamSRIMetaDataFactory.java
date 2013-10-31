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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * @since 2.0
 * <!-- end-user-doc -->
 * @see gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIMetaDataPackage
 * @generated
 */
public interface StreamSRIMetaDataFactory extends EFactory
{
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	StreamSRIMetaDataFactory eINSTANCE = gov.redhawk.bulkio.util.StreamSRIMetaData.impl.StreamSRIMetaDataFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Stream SRI Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Stream SRI Model</em>'.
	 * @generated
	 */
	StreamSRIModel createStreamSRIModel();

	/**
	 * Returns a new object of class '<em>SRI</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>SRI</em>'.
	 * @generated
	 */
	SRI createSRI();

	/**
	 * Returns a new object of class '<em>Stream SRI Document Root</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Stream SRI Document Root</em>'.
	 * @generated
	 */
	StreamSRIDocumentRoot createStreamSRIDocumentRoot();

	/**
	 * Returns a new object of class '<em>Time</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Time</em>'.
	 * @generated
	 */
	Time createTime();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	StreamSRIMetaDataPackage getStreamSRIMetaDataPackage();

} //StreamSRIMetaDataFactory
