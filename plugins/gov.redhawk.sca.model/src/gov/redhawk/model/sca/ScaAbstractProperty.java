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

import mil.jpeojtrs.sca.prf.AbstractProperty;
import mil.jpeojtrs.sca.prf.AccessType;

import org.omg.CORBA.Any;

import CF.DataType;
import CF.PropertySetPackage.InvalidConfiguration;
import CF.PropertySetPackage.PartialConfiguration;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Property</b></em>'.
 * 
 * @since 9.0
 * @noimplement This interface is not intended to be implemented by clients.
 *              <!-- end-user-doc -->
 *
 *              <p>
 *              The following features are supported:
 *              <ul>
 *              <li>{@link gov.redhawk.model.sca.ScaAbstractProperty#getDefinition <em>Definition</em>}</li>
 *              <li>{@link gov.redhawk.model.sca.ScaAbstractProperty#getDescription <em>Description</em>}</li>
 *              <li>{@link gov.redhawk.model.sca.ScaAbstractProperty#getId <em>Id</em>}</li>
 *              <li>{@link gov.redhawk.model.sca.ScaAbstractProperty#getMode <em>Mode</em>}</li>
 *              <li>{@link gov.redhawk.model.sca.ScaAbstractProperty#getName <em>Name</em>}</li>
 *              <li>{@link gov.redhawk.model.sca.ScaAbstractProperty#isIgnoreRemoteSet <em>Ignore Remote Set</em>}</li>
 *              </ul>
 *              </p>
 *
 * @see gov.redhawk.model.sca.ScaPackage#getScaAbstractProperty()
 * @model abstract="true"
 *        extendedMetaData="name='ScaProperty' kind='empty'"
 * @generated
 */
public interface ScaAbstractProperty< T extends AbstractProperty > extends IStatusProvider {

	/**
	 * Returns the value of the '<em><b>Definition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Definition</em>' reference isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Definition</em>' reference.
	 * @see #setDefinition(AbstractProperty)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaAbstractProperty_Definition()
	 * @model transient="true"
	 *        extendedMetaData="kind='attribute' name='definition'"
	 * @generated
	 */
	T getDefinition();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaAbstractProperty#getDefinition <em>Definition</em>}'
	 * reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Definition</em>' reference.
	 * @see #getDefinition()
	 * @generated
	 */
	void setDefinition(T value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaAbstractProperty_Description()
	 * @model transient="true"
	 *        extendedMetaData="kind='attribute' name='description'"
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaAbstractProperty#getDescription <em>Description</em>}'
	 * attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 16.0
	 *        <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaAbstractProperty_Id()
	 * @model transient="true"
	 *        extendedMetaData="kind='attribute' name='id'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaAbstractProperty#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 16.0
	 *        <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Mode</b></em>' attribute.
	 * The literals are from the enumeration {@link mil.jpeojtrs.sca.prf.AccessType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mode</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Mode</em>' attribute.
	 * @see mil.jpeojtrs.sca.prf.AccessType
	 * @see #setMode(AccessType)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaAbstractProperty_Mode()
	 * @model transient="true"
	 *        extendedMetaData="kind='attribute' name='mode'"
	 * @generated
	 */
	AccessType getMode();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaAbstractProperty#getMode <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 16.0
	 *        <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mode</em>' attribute.
	 * @see mil.jpeojtrs.sca.prf.AccessType
	 * @see #getMode()
	 * @generated
	 */
	void setMode(AccessType value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaAbstractProperty_Name()
	 * @model transient="true"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaAbstractProperty#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 16.0
	 *        <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Ignore Remote Set</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ignore Remote Set</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Ignore Remote Set</em>' attribute.
	 * @see #setIgnoreRemoteSet(boolean)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaAbstractProperty_IgnoreRemoteSet()
	 * @model transient="true" volatile="true" derived="true"
	 * @generated
	 */
	boolean isIgnoreRemoteSet();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaAbstractProperty#isIgnoreRemoteSet
	 * <em>Ignore Remote Set</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Ignore Remote Set</em>' attribute.
	 * @see #isIgnoreRemoteSet()
	 * @generated
	 */
	void setIgnoreRemoteSet(boolean value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @model dataType="gov.redhawk.model.sca.Any"
	 * @generated
	 */
	Any toAny();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @model anyDataType="gov.redhawk.model.sca.Any"
	 * @generated
	 */
	void fromAny(Any any);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @model exceptions="mil.jpeojtrs.sca.cf.PartialConfiguration mil.jpeojtrs.sca.cf.InvalidConfiguration"
	 *        anyDataType="gov.redhawk.model.sca.Any"
	 * @generated
	 */
	void setRemoteValue(Any any) throws PartialConfiguration, InvalidConfiguration;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @model kind="operation" dataType="mil.jpeojtrs.sca.cf.DataType"
	 * @generated
	 */
	DataType getProperty();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @model kind="operation"
	 * @generated
	 */
	boolean isDefaultValue();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	void restoreDefaultValue();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @model anyDataType="mil.jpeojtrs.sca.prf.Any"
	 * @generated
	 */
	boolean valueEquals(Any any);

} // ScaAbstractProperty
