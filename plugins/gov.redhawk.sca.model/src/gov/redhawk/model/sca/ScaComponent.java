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

import mil.jpeojtrs.sca.sad.SadComponentInstantiation;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;

import CF.Resource;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component</b></em>'.
 * @noimplement This interface is not intended to be implemented by clients.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.ScaComponent#getComponentInstantiation <em>Component Instantiation</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaComponent#getDevices <em>Devices</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaComponent#getInstantiationIdentifier <em>Instantiation Identifier</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaComponent#getWaveform <em>Waveform</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaComponent#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.model.sca.ScaPackage#getScaComponent()
 * @model superTypes="gov.redhawk.model.sca.ScaAbstractComponent<mil.jpeojtrs.sca.cf.Resource>"
 *        extendedMetaData="name='ScaComponent' kind='elementOnly'"
 * @generated
 */
public interface ScaComponent extends ScaAbstractComponent<Resource> {

	/**
	 * Returns the value of the '<em><b>Component Instantiation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component Instantiation</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component Instantiation</em>' reference.
	 * @see #isSetComponentInstantiation()
	 * @see #unsetComponentInstantiation()
	 * @see #setComponentInstantiation(SadComponentInstantiation)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaComponent_ComponentInstantiation()
	 * @model unsettable="true" required="true" transient="true"
	 *        extendedMetaData="kind='attribute' name='componentInstantiation'"
	 * @generated
	 */
	SadComponentInstantiation getComponentInstantiation();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaComponent#getComponentInstantiation <em>Component Instantiation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component Instantiation</em>' reference.
	 * @see #isSetComponentInstantiation()
	 * @see #unsetComponentInstantiation()
	 * @see #getComponentInstantiation()
	 * @generated
	 */
	void setComponentInstantiation(SadComponentInstantiation value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaComponent#getComponentInstantiation <em>Component Instantiation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetComponentInstantiation()
	 * @see #getComponentInstantiation()
	 * @see #setComponentInstantiation(SadComponentInstantiation)
	 * @generated
	 */
	void unsetComponentInstantiation();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaComponent#getComponentInstantiation <em>Component Instantiation</em>}' reference is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Component Instantiation</em>' reference is set.
	 * @see #unsetComponentInstantiation()
	 * @see #getComponentInstantiation()
	 * @see #setComponentInstantiation(SadComponentInstantiation)
	 * @generated
	 */
	boolean isSetComponentInstantiation();

	/**
	 * Returns the value of the '<em><b>Devices</b></em>' reference list.
	 * The list contents are of type {@link gov.redhawk.model.sca.ScaDevice}&lt;?>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Devices</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Devices</em>' reference list.
	 * @see #isSetDevices()
	 * @see #unsetDevices()
	 * @see gov.redhawk.model.sca.ScaPackage#getScaComponent_Devices()
	 * @model unsettable="true" transient="true"
	 *        extendedMetaData="kind='attribute' name='devices'"
	 * @generated
	 */
	EList<ScaDevice<?>> getDevices();

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaComponent#getDevices <em>Devices</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetDevices()
	 * @see #getDevices()
	 * @generated
	 */
	void unsetDevices();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaComponent#getDevices <em>Devices</em>}' reference list is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Devices</em>' reference list is set.
	 * @see #unsetDevices()
	 * @see #getDevices()
	 * @generated
	 */
	boolean isSetDevices();

	/**
	 * Returns the value of the '<em><b>Instantiation Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instantiation Identifier</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instantiation Identifier</em>' attribute.
	 * @see #isSetInstantiationIdentifier()
	 * @see gov.redhawk.model.sca.ScaPackage#getScaComponent_InstantiationIdentifier()
	 * @model unsettable="true" transient="true" changeable="false" volatile="true" derived="true"
	 *        extendedMetaData="kind='attribute' name='instantiationIdentifier'"
	 * @generated
	 */
	String getInstantiationIdentifier();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaComponent#getInstantiationIdentifier <em>Instantiation Identifier</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Instantiation Identifier</em>' attribute is set.
	 * @see #getInstantiationIdentifier()
	 * @generated
	 */
	boolean isSetInstantiationIdentifier();

	/**
	 * Returns the value of the '<em><b>Waveform</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link gov.redhawk.model.sca.ScaWaveform#getComponents <em>Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Waveform</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Waveform</em>' container reference.
	 * @see #setWaveform(ScaWaveform)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaComponent_Waveform()
	 * @see gov.redhawk.model.sca.ScaWaveform#getComponents
	 * @model opposite="components"
	 *        extendedMetaData="kind='attribute' name='waveform'"
	 * @generated
	 */
	ScaWaveform getWaveform();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaComponent#getWaveform <em>Waveform</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Waveform</em>' container reference.
	 * @see #getWaveform()
	 * @generated
	 */
	void setWaveform(ScaWaveform value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaComponent_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaComponent#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	EList<ScaDevice<?>> fetchDevices(IProgressMonitor monitor);

} // ScaComponent
