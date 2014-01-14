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

import mil.jpeojtrs.sca.spd.SoftPkg;

import org.eclipse.core.runtime.IProgressMonitor;

import CF.Resource;
import CF.ResourceOperations;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Component</b></em>'.
 * @since 2.0
 * @noimplement This interface is not intended to be implemented by clients.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.ScaAbstractComponent#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaAbstractComponent#getStarted <em>Started</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaAbstractComponent#getProfile <em>Profile</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.model.sca.ScaPackage#getScaAbstractComponent()
 * @model abstract="true" superTypes="gov.redhawk.model.sca.ScaPropertyContainer<R, mil.jpeojtrs.sca.spd.SoftPkg> mil.jpeojtrs.sca.cf.ResourceOperations gov.redhawk.model.sca.ScaPortContainer" RBounds="mil.jpeojtrs.sca.cf.Resource"
 *        extendedMetaData="name='ScaAbstractComponent' kind='elementOnly'"
 * @generated
 */
public interface ScaAbstractComponent< R extends Resource > extends ScaPropertyContainer<R, SoftPkg>, ResourceOperations, ScaPortContainer {

	/**
	 * Returns the value of the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Identifier</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Identifier</em>' attribute.
	 * @see #isSetIdentifier()
	 * @see #unsetIdentifier()
	 * @see #setIdentifier(String)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaAbstractComponent_Identifier()
	 * @model unsettable="true" id="true" derived="true"
	 *        extendedMetaData="kind='attribute' name='identifier'"
	 * @generated
	 */
	String getIdentifier();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaAbstractComponent#getIdentifier <em>Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Identifier</em>' attribute.
	 * @see #isSetIdentifier()
	 * @see #unsetIdentifier()
	 * @see #getIdentifier()
	 * @generated
	 */
	void setIdentifier(String value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaAbstractComponent#getIdentifier <em>Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIdentifier()
	 * @see #getIdentifier()
	 * @see #setIdentifier(String)
	 * @generated
	 */
	void unsetIdentifier();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaAbstractComponent#getIdentifier <em>Identifier</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Identifier</em>' attribute is set.
	 * @see #unsetIdentifier()
	 * @see #getIdentifier()
	 * @see #setIdentifier(String)
	 * @generated
	 */
	boolean isSetIdentifier();

	/**
	 * Returns the value of the '<em><b>Started</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Started</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Started</em>' attribute.
	 * @see #isSetStarted()
	 * @see #unsetStarted()
	 * @see #setStarted(Boolean)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaAbstractComponent_Started()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.BooleanObject" transient="true"
	 * @generated
	 */
	Boolean getStarted();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaAbstractComponent#getStarted <em>Started</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 16.0
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Started</em>' attribute.
	 * @see #isSetStarted()
	 * @see #unsetStarted()
	 * @see #getStarted()
	 * @generated
	 */
	void setStarted(Boolean value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaAbstractComponent#getStarted <em>Started</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 16.0
	 * <!-- end-user-doc -->
	 * @see #isSetStarted()
	 * @see #getStarted()
	 * @see #setStarted(Boolean)
	 * @generated
	 */
	void unsetStarted();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaAbstractComponent#getStarted <em>Started</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Started</em>' attribute is set.
	 * @see #unsetStarted()
	 * @see #getStarted()
	 * @see #setStarted(Boolean)
	 * @generated
	 */
	boolean isSetStarted();

	/**
	 * Returns the value of the '<em><b>Profile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Profile</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Profile</em>' attribute.
	 * @see #isSetProfile()
	 * @see #unsetProfile()
	 * @see #setProfile(String)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaAbstractComponent_Profile()
	 * @model unsettable="true" transient="true"
	 * @generated
	 */
	String getProfile();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaAbstractComponent#getProfile <em>Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Profile</em>' attribute.
	 * @see #isSetProfile()
	 * @see #unsetProfile()
	 * @see #getProfile()
	 * @generated
	 */
	void setProfile(String value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaAbstractComponent#getProfile <em>Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetProfile()
	 * @see #getProfile()
	 * @see #setProfile(String)
	 * @generated
	 */
	void unsetProfile();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaAbstractComponent#getProfile <em>Profile</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Profile</em>' attribute is set.
	 * @see #unsetProfile()
	 * @see #getProfile()
	 * @see #setProfile(String)
	 * @generated
	 */
	boolean isSetProfile();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	String fetchIdentifier(IProgressMonitor monitor);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	Boolean fetchStarted(IProgressMonitor monitor);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	String fetchProfile(IProgressMonitor monitor);
} // ScaAbstractComponent
