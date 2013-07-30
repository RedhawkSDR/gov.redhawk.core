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
package gov.redhawk.eclipsecorba.library;

import gov.redhawk.eclipsecorba.idl.Specification;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Idl Library</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.library.IdlLibrary#getSpecifications <em>Specifications</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.library.IdlLibrary#getPaths <em>Paths</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.library.IdlLibrary#getLoadStatus <em>Load Status</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.eclipsecorba.library.LibraryPackage#getIdlLibrary()
 * @model
 * @generated
 */
public interface IdlLibrary extends RepositoryModule {

	/**
	 * Returns the value of the '<em><b>Specifications</b></em>' reference list.
	 * The list contents are of type {@link gov.redhawk.eclipsecorba.idl.Specification}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Specifications</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Specifications</em>' reference list.
	 * @see gov.redhawk.eclipsecorba.library.LibraryPackage#getIdlLibrary_Specifications()
	 * @model transient="true" derived="true"
	 * @generated
	 */
	EList<Specification> getSpecifications();

	/**
	 * Returns the value of the '<em><b>Paths</b></em>' containment reference list.
	 * The list contents are of type {@link gov.redhawk.eclipsecorba.library.Path}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Paths</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Paths</em>' containment reference list.
	 * @see gov.redhawk.eclipsecorba.library.LibraryPackage#getIdlLibrary_Paths()
	 * @model containment="true"
	 * @generated
	 */
	EList<Path> getPaths();

	/**
	 * Returns the value of the '<em><b>Load Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Load Status</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Load Status</em>' attribute.
	 * @see gov.redhawk.eclipsecorba.library.LibraryPackage#getIdlLibrary_LoadStatus()
	 * @model dataType="gov.redhawk.eclipsecorba.library.IStatus" transient="true" suppressedSetVisibility="true"
	 * @generated
	 */
	IStatus getLoadStatus();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Loads the IDL Library based on the current state of the Path.
	 * 
	 * Overwrites any existing entries in the library and reloads those
	 * <!-- end-model-doc -->
	 * @model dataType="gov.redhawk.eclipsecorba.library.IStatus" exceptions="gov.redhawk.eclipsecorba.library.CoreExceptoin" monitorDataType="gov.redhawk.eclipsecorba.library.IProgressMonitor"
	 * @generated
	 */
	IStatus load(IProgressMonitor monitor) throws CoreException;

} // IdlLibrary
