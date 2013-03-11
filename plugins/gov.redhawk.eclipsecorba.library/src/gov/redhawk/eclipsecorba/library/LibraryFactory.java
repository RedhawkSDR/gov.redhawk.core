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
package gov.redhawk.eclipsecorba.library;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see gov.redhawk.eclipsecorba.library.LibraryPackage
 * @generated
 */
public interface LibraryFactory extends EFactory {

	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	LibraryFactory eINSTANCE = gov.redhawk.eclipsecorba.library.impl.LibraryFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Idl Library</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Idl Library</em>'.
	 * @generated
	 */
	IdlLibrary createIdlLibrary();

	/**
	 * Returns a new object of class '<em>Repository Module</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Repository Module</em>'.
	 * @generated
	 */
	RepositoryModule createRepositoryModule();

	/**
	 * Returns a new object of class '<em>Preference Node Path Set</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Preference Node Path Set</em>'.
	 * @generated
	 */
	PreferenceNodePathSet createPreferenceNodePathSet();

	/**
	 * Returns a new object of class '<em>URI Path Set</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>URI Path Set</em>'.
	 * @generated
	 */
	URIPathSet createURIPathSet();

	/**
	 * Returns a new object of class '<em>Local File Path</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Local File Path</em>'.
	 * @generated
	 */
	LocalFilePath createLocalFilePath();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	LibraryPackage getLibraryPackage();

} //LibraryFactory
