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

import gov.redhawk.eclipsecorba.idl.Definition;
import gov.redhawk.eclipsecorba.idl.Identifiable;
import gov.redhawk.eclipsecorba.idl.Module;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Repository Module</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.library.RepositoryModule#getModuleDefinitions <em>Module Definitions</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.library.RepositoryModule#getDefinitions <em>Definitions</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.eclipsecorba.library.LibraryPackage#getRepositoryModule()
 * @model
 * @generated
 */
public interface RepositoryModule extends Definition {

	/**
	 * Returns the value of the '<em><b>Module Definitions</b></em>' reference list.
	 * The list contents are of type {@link gov.redhawk.eclipsecorba.idl.Module}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Module Definitions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Module Definitions</em>' reference list.
	 * @see gov.redhawk.eclipsecorba.library.LibraryPackage#getRepositoryModule_ModuleDefinitions()
	 * @model
	 * @generated
	 */
	EList<Module> getModuleDefinitions();

	/**
	 * Returns the value of the '<em><b>Definitions</b></em>' reference list.
	 * The list contents are of type {@link gov.redhawk.eclipsecorba.idl.Definition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Definitions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Definitions</em>' reference list.
	 * @see gov.redhawk.eclipsecorba.library.LibraryPackage#getRepositoryModule_Definitions()
	 * @model transient="true" derived="true"
	 * @generated
	 */
	EList<Definition> getDefinitions();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	Identifiable find(String repId);

} // RepositoryModule
