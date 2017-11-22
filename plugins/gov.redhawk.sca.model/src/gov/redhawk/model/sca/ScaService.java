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

import mil.jpeojtrs.sca.scd.Interface;
import mil.jpeojtrs.sca.spd.SoftPkg;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Service</b></em>'.
 * 
 * @since 12.0
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link gov.redhawk.model.sca.ScaService#getName <em>Name</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaService#getDevMgr <em>Dev Mgr</em>}</li>
 * </ul>
 *
 * @see gov.redhawk.model.sca.ScaPackage#getScaService()
 * @model superTypes="gov.redhawk.model.sca.ScaPropertyContainer&lt;gov.redhawk.model.sca.Object,
 * mil.jpeojtrs.sca.spd.SoftPkg&gt; gov.redhawk.model.sca.ScaPortContainer"
 * @generated
 */
public interface ScaService extends ScaPropertyContainer<org.omg.CORBA.Object, SoftPkg>, ScaPortContainer {

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaService_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaService#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Dev Mgr</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link gov.redhawk.model.sca.ScaDeviceManager#getServices
	 * <em>Services</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 18.0
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dev Mgr</em>' container reference.
	 * @see gov.redhawk.model.sca.ScaPackage#getScaService_DevMgr()
	 * @see gov.redhawk.model.sca.ScaDeviceManager#getServices
	 * @model opposite="services" resolveProxies="false" changeable="false" ordered="false"
	 * @generated
	 */
	ScaDeviceManager getDevMgr();

	/**
	 * <!-- begin-user-doc -->
	 * Checks the profile (SCD) to see if the service supports the requested interface. This method is designed to be
	 * quick, and thus will return <code>false</code> if it is unable to make a determination.
	 * <p/>
	 * The profile should be present before invoking this method
	 * ({@link #fetchProfileObject(org.eclipse.core.runtime.IProgressMonitor)}) or it will return false.
	 * 
	 * @since 21.0
	 * <!-- end-user-doc -->
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean" intfRequired="true"
	 * @generated
	 */
	boolean isInstance(Interface intf);

} // ScaService
