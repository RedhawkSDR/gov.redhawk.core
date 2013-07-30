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

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Local File Path</b></em>'.
 * @since 4.0
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.library.LocalFilePath#getLocalPaths <em>Local Paths</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.eclipsecorba.library.LibraryPackage#getLocalFilePath()
 * @model
 * @generated
 */
public interface LocalFilePath extends Path {

	/**
	 * Returns the value of the '<em><b>Local Paths</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.core.runtime.IPath}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Local Paths</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Local Paths</em>' attribute list.
	 * @see gov.redhawk.eclipsecorba.library.LibraryPackage#getLocalFilePath_LocalPaths()
	 * @model dataType="gov.redhawk.eclipsecorba.library.IPath"
	 * @generated
	 */
	EList<IPath> getLocalPaths();

} // LocalFilePath
