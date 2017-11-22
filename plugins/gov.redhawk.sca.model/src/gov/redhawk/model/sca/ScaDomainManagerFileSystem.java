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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Domain Manager File System</b></em>'.
 * 
 * @since 10.0
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link gov.redhawk.model.sca.ScaDomainManagerFileSystem#getDomMgr <em>Dom Mgr</em>}</li>
 * </ul>
 *
 * @see gov.redhawk.model.sca.ScaPackage#getScaDomainManagerFileSystem()
 * @model extendedMetaData="name='ScaDomainManagerFileSystem' kind='empty'"
 * @generated
 */
public interface ScaDomainManagerFileSystem extends ScaFileManager {

	/**
	 * Returns the value of the '<em><b>Dom Mgr</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link gov.redhawk.model.sca.ScaDomainManager#getFileManager <em>File
	 * Manager</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dom Mgr</em>' container reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dom Mgr</em>' container reference.
	 * @see #setDomMgr(ScaDomainManager)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaDomainManagerFileSystem_DomMgr()
	 * @see gov.redhawk.model.sca.ScaDomainManager#getFileManager
	 * @model opposite="fileManager"
	 * extendedMetaData="kind='attribute' name='domMgr'"
	 * @generated
	 */
	ScaDomainManager getDomMgr();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaDomainManagerFileSystem#getDomMgr <em>Dom Mgr</em>}'
	 * container reference.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 16.0
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dom Mgr</em>' container reference.
	 * @see #getDomMgr()
	 * @generated
	 */
	void setDomMgr(ScaDomainManager value);

} // ScaDomainManagerFileSystem
