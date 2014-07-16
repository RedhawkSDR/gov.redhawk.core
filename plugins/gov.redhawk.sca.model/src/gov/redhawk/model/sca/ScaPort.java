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

import mil.jpeojtrs.sca.scd.AbstractPort;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Port</b></em>'.
 * @noimplement This interface is not intended to be implemented by clients.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link gov.redhawk.model.sca.ScaPort#getName <em>Name</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaPort#getProfileObj <em>Profile Obj</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaPort#getRepid <em>Repid</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaPort#getPortContainer <em>Port Container</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.model.sca.ScaPackage#getScaPort()
 * @model abstract="true" P2Bounds="mil.jpeojtrs.sca.cf.Object"
 * extendedMetaData="name='ScaPort' kind='empty'"
 * @generated
 */
public interface ScaPort< P extends AbstractPort, P2 extends org.omg.CORBA.Object > extends CorbaObjWrapper<P2> {

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
	 * @see gov.redhawk.model.sca.ScaPackage#getScaPort_Name()
	 * @model required="true"
	 * extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaPort#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 18.0
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Profile Obj</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Profile Obj</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Profile Obj</em>' reference.
	 * @see #setProfileObj(AbstractPort)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaPort_ProfileObj()
	 * @model transient="true"
	 * extendedMetaData="kind='attribute' name='profileObj'"
	 * @generated
	 */
	P getProfileObj();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaPort#getProfileObj <em>Profile Obj</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Profile Obj</em>' reference.
	 * @see #getProfileObj()
	 * @generated
	 */
	void setProfileObj(P value);

	/**
	 * Returns the value of the '<em><b>Repid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Repid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Repid</em>' attribute.
	 * @see gov.redhawk.model.sca.ScaPackage#getScaPort_Repid()
	 * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
	 * extendedMetaData="kind='attribute' name='repid'"
	 * @generated
	 */
	String getRepid();

	/**
	 * Returns the value of the '<em><b>Port Container</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link gov.redhawk.model.sca.ScaPortContainer#getPorts <em>Ports</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Port Container</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Port Container</em>' container reference.
	 * @see #setPortContainer(ScaPortContainer)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaPort_PortContainer()
	 * @see gov.redhawk.model.sca.ScaPortContainer#getPorts
	 * @model opposite="ports" required="true"
	 * @generated
	 */
	ScaPortContainer getPortContainer();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaPort#getPortContainer <em>Port Container</em>}' container
	 * reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port Container</em>' container reference.
	 * @see #getPortContainer()
	 * @generated
	 */
	void setPortContainer(ScaPortContainer value);

} // ScaPort
