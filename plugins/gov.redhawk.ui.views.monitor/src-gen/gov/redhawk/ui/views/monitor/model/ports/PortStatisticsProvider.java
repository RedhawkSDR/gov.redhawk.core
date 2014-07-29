/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */

// BEGIN GENERATED CODE
package gov.redhawk.ui.views.monitor.model.ports;

import org.eclipse.emf.ecore.EObject;

import BULKIO.PortStatistics;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Port Statistics Provider</b></em>'.
 * @since 5.0
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.ui.views.monitor.model.ports.PortStatisticsProvider#getData <em>Data</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.ui.views.monitor.model.ports.PortsPackage#getPortStatisticsProvider()
 * @model
 * @generated
 */
public interface PortStatisticsProvider extends EObject {

	/**
	 * Returns the value of the '<em><b>Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data</em>' attribute.
	 * @see #setData(PortStatistics)
	 * @see gov.redhawk.ui.views.monitor.model.ports.PortsPackage#getPortStatisticsProvider_Data()
	 * @model dataType="gov.redhawk.ui.views.monitor.model.ports.PortStatistics" transient="true"
	 * @generated
	 */
	PortStatistics getData();

	/**
	 * Sets the value of the '{@link gov.redhawk.ui.views.monitor.model.ports.PortStatisticsProvider#getData <em>Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data</em>' attribute.
	 * @see #getData()
	 * @generated
	 */
	void setData(PortStatistics value);

} // PortStatisticsProvider
