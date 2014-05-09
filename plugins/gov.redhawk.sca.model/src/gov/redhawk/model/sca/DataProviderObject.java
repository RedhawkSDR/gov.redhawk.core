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
package gov.redhawk.model.sca;

import gov.redhawk.model.sca.services.IScaDataProvider;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Provider Object</b></em>'.
 * @noimplement This interface is not intended to be implemented by clients.
 * @since 9.0
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link gov.redhawk.model.sca.DataProviderObject#getDataProviders <em>Data Providers</em>}</li>
 * <li>{@link gov.redhawk.model.sca.DataProviderObject#isDataProvidersEnabled <em>Data Providers Enabled</em>}</li>
 * <li>{@link gov.redhawk.model.sca.DataProviderObject#getEnabledDataProviders <em>Enabled Data Providers</em>}</li>
 * </ul>
 * </p>
 * 
 * @see gov.redhawk.model.sca.ScaPackage#getDataProviderObject()
 * @model abstract="true" superTypes=
 * "gov.redhawk.model.sca.IStatusProvider gov.redhawk.model.sca.IDisposable gov.redhawk.model.sca.IRefreshable"
 * extendedMetaData="name='DataProviderObject' kind='empty'"
 * @generated
 */
public interface DataProviderObject extends IStatusProvider, IDisposable, IRefreshable {

	/**
	 * Returns the value of the '<em><b>Data Providers</b></em>' attribute list.
	 * The list contents are of type {@link gov.redhawk.model.sca.services.IScaDataProvider}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Providers</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Providers</em>' attribute list.
	 * @see #isSetDataProviders()
	 * @see #unsetDataProviders()
	 * @see gov.redhawk.model.sca.ScaPackage#getDataProviderObject_DataProviders()
	 * @model unsettable="true" dataType="gov.redhawk.model.sca.IScaDataProvider" transient="true"
	 * extendedMetaData="kind='attribute' name='dataProviders'"
	 * @generated
	 */
	EList<IScaDataProvider> getDataProviders();

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.DataProviderObject#getDataProviders <em>Data
	 * Providers</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetDataProviders()
	 * @see #getDataProviders()
	 * @generated
	 */
	void unsetDataProviders();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.DataProviderObject#getDataProviders <em>Data
	 * Providers</em>}' attribute list is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Data Providers</em>' attribute list is set.
	 * @see #unsetDataProviders()
	 * @see #getDataProviders()
	 * @generated
	 */
	boolean isSetDataProviders();

	/**
	 * Returns the value of the '<em><b>Data Providers Enabled</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Providers Enabled</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Providers Enabled</em>' attribute.
	 * @see #setDataProvidersEnabled(boolean)
	 * @see gov.redhawk.model.sca.ScaPackage#getDataProviderObject_DataProvidersEnabled()
	 * @model default="true"
	 * @generated
	 */
	boolean isDataProvidersEnabled();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.DataProviderObject#isDataProvidersEnabled <em>Data Providers
	 * Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Providers Enabled</em>' attribute.
	 * @see #isDataProvidersEnabled()
	 * @generated
	 */
	void setDataProvidersEnabled(boolean value);

	/**
	 * Returns the value of the '<em><b>Enabled Data Providers</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * @since 19.0
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enabled Data Providers</em>' attribute list.
	 * @see gov.redhawk.model.sca.ScaPackage#getDataProviderObject_EnabledDataProviders()
	 * @model
	 * @generated
	 */
	EList<String> getEnabledDataProviders();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void attachDataProviders();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void detachDataProviders();

} // DataProviderObject
