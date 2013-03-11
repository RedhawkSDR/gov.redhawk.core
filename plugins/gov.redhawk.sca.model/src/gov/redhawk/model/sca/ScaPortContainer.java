/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */

 // BEGIN GENERATED CODE
package gov.redhawk.model.sca;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Port Container</b></em>'.
 * @since 14.0
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.ScaPortContainer#getPorts <em>Ports</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.model.sca.ScaPackage#getScaPortContainer()
 * @model interface="true" abstract="true" superTypes="gov.redhawk.model.sca.IRefreshable gov.redhawk.model.sca.IStatusProvider"
 * @generated
 */
public interface ScaPortContainer extends IRefreshable, IStatusProvider {

	/**
	 * Returns the value of the '<em><b>Ports</b></em>' containment reference list.
	 * The list contents are of type {@link gov.redhawk.model.sca.ScaPort}&lt;?, ?>.
	 * It is bidirectional and its opposite is '{@link gov.redhawk.model.sca.ScaPort#getPortContainer <em>Port Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ports</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ports</em>' containment reference list.
	 * @see #isSetPorts()
	 * @see #unsetPorts()
	 * @see gov.redhawk.model.sca.ScaPackage#getScaPortContainer_Ports()
	 * @see gov.redhawk.model.sca.ScaPort#getPortContainer
	 * @model opposite="portContainer" containment="true" unsettable="true" transient="true"
	 * @generated
	 */
	EList<ScaPort<?, ?>> getPorts();

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaPortContainer#getPorts <em>Ports</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPorts()
	 * @see #getPorts()
	 * @generated
	 */
	void unsetPorts();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaPortContainer#getPorts <em>Ports</em>}' containment reference list is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Ports</em>' containment reference list is set.
	 * @see #unsetPorts()
	 * @see #getPorts()
	 * @generated
	 */
	boolean isSetPorts();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	ScaPort<?, ?> getScaPort(String name);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	EList<ScaPort<?, ?>> fetchPorts(IProgressMonitor monitor);

} // ScaPortContainer
