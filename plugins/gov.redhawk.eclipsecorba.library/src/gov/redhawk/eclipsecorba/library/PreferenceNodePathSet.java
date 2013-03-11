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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Preference Node Path Set</b></em>'.
 * @since 4.0
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.library.PreferenceNodePathSet#getQualifier <em>Qualifier</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.library.PreferenceNodePathSet#getKey <em>Key</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.library.PreferenceNodePathSet#getDelimiter <em>Delimiter</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.library.PreferenceNodePathSet#isFileUri <em>File Uri</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.library.PreferenceNodePathSet#isReplaceEnv <em>Replace Env</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.eclipsecorba.library.LibraryPackage#getPreferenceNodePathSet()
 * @model
 * @generated
 */
public interface PreferenceNodePathSet extends Path {

	/**
	 * Returns the value of the '<em><b>Qualifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qualifier</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Qualifier</em>' attribute.
	 * @see #setQualifier(String)
	 * @see gov.redhawk.eclipsecorba.library.LibraryPackage#getPreferenceNodePathSet_Qualifier()
	 * @model
	 * @generated
	 */
	String getQualifier();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.library.PreferenceNodePathSet#getQualifier <em>Qualifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Qualifier</em>' attribute.
	 * @see #getQualifier()
	 * @generated
	 */
	void setQualifier(String value);

	/**
	 * Returns the value of the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Key</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Key</em>' attribute.
	 * @see #setKey(String)
	 * @see gov.redhawk.eclipsecorba.library.LibraryPackage#getPreferenceNodePathSet_Key()
	 * @model
	 * @generated
	 */
	String getKey();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.library.PreferenceNodePathSet#getKey <em>Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Key</em>' attribute.
	 * @see #getKey()
	 * @generated
	 */
	void setKey(String value);

	/**
	 * Returns the value of the '<em><b>Delimiter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Delimiter</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Delimiter</em>' attribute.
	 * @see #setDelimiter(String)
	 * @see gov.redhawk.eclipsecorba.library.LibraryPackage#getPreferenceNodePathSet_Delimiter()
	 * @model
	 * @generated
	 */
	String getDelimiter();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.library.PreferenceNodePathSet#getDelimiter <em>Delimiter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Delimiter</em>' attribute.
	 * @see #getDelimiter()
	 * @generated
	 */
	void setDelimiter(String value);

	/**
	 * Returns the value of the '<em><b>File Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>File Uri</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>File Uri</em>' attribute.
	 * @see #setFileUri(boolean)
	 * @see gov.redhawk.eclipsecorba.library.LibraryPackage#getPreferenceNodePathSet_FileUri()
	 * @model
	 * @generated
	 */
	boolean isFileUri();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.library.PreferenceNodePathSet#isFileUri <em>File Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File Uri</em>' attribute.
	 * @see #isFileUri()
	 * @generated
	 */
	void setFileUri(boolean value);

	/**
	 * Returns the value of the '<em><b>Replace Env</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Replace Env</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Replace Env</em>' attribute.
	 * @see #setReplaceEnv(boolean)
	 * @see gov.redhawk.eclipsecorba.library.LibraryPackage#getPreferenceNodePathSet_ReplaceEnv()
	 * @model
	 * @generated
	 */
	boolean isReplaceEnv();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.library.PreferenceNodePathSet#isReplaceEnv <em>Replace Env</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Replace Env</em>' attribute.
	 * @see #isReplaceEnv()
	 * @generated
	 */
	void setReplaceEnv(boolean value);

} // PreferenceNodePathSet
