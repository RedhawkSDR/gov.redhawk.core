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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Path</b></em>'.
 * @since 4.0
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.library.Path#getDerivedPath <em>Derived Path</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.eclipsecorba.library.LibraryPackage#getPath()
 * @model abstract="true"
 * @generated
 */
public interface Path extends EObject {

	/**
	 * Returns the value of the '<em><b>Derived Path</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.common.util.URI}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Derived Path</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Derived Path</em>' attribute list.
	 * @see gov.redhawk.eclipsecorba.library.LibraryPackage#getPath_DerivedPath()
	 * @model dataType="gov.redhawk.eclipsecorba.library.URI" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<URI> getDerivedPath();

} // Path
