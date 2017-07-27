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

import CF.FileSystem;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Device Manager File System</b></em>'.
 * @since 10.0
 *              <!-- end-user-doc -->
 *
 *              <p>
 *              The following features are supported:
 *              </p>
 *              <ul>
 *              <li>{@link gov.redhawk.model.sca.ScaDeviceManagerFileSystem#getDeviceManager <em>Device Manager</em>}
 *              </li>
 *              </ul>
 *
 * @see gov.redhawk.model.sca.ScaPackage#getScaDeviceManagerFileSystem()
 * @model superTypes="gov.redhawk.model.sca.ScaFileSystem<mil.jpeojtrs.sca.cf.FileSystem>"
 *        extendedMetaData="name='ScaDeviceManagerFileSystem' kind='empty'"
 * @generated
 */
public interface ScaDeviceManagerFileSystem extends ScaFileSystem<FileSystem> {

	/**
	 * Returns the value of the '<em><b>Device Manager</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link gov.redhawk.model.sca.ScaDeviceManager#getFileSystem
	 * <em>File System</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Device Manager</em>' container reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Device Manager</em>' container reference.
	 * @see #setDeviceManager(ScaDeviceManager)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaDeviceManagerFileSystem_DeviceManager()
	 * @see gov.redhawk.model.sca.ScaDeviceManager#getFileSystem
	 * @model opposite="fileSystem" unsettable="true"
	 * @generated
	 */
	ScaDeviceManager getDeviceManager();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaDeviceManagerFileSystem#getDeviceManager
	 * <em>Device Manager</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Device Manager</em>' container reference.
	 * @see #getDeviceManager()
	 * @generated
	 */
	void setDeviceManager(ScaDeviceManager value);

} // ScaDeviceManagerFileSystem
