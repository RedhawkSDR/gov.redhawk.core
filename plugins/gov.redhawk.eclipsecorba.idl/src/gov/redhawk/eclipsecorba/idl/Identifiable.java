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
package gov.redhawk.eclipsecorba.idl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Identifiable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.Identifiable#getName <em>Name</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.Identifiable#getScopedName <em>Scoped Name</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.Identifiable#getRepId <em>Rep Id</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.Identifiable#getPrefix <em>Prefix</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.Identifiable#getVersion <em>Version</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.Identifiable#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.eclipsecorba.idl.IdlPackage#getIdentifiable()
 * @model abstract="true"
 * @generated
 */
public interface Identifiable extends EObject {

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see gov.redhawk.eclipsecorba.idl.IdlPackage#getIdentifiable_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.idl.Identifiable#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Scoped Name</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scoped Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scoped Name</em>' attribute.
	 * @see gov.redhawk.eclipsecorba.idl.IdlPackage#getIdentifiable_ScopedName()
	 * @model default="" transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	String getScopedName();

	/**
	 * Returns the value of the '<em><b>Rep Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rep Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rep Id</em>' attribute.
	 * @see #setRepId(String)
	 * @see gov.redhawk.eclipsecorba.idl.IdlPackage#getIdentifiable_RepId()
	 * @model id="true" transient="true" volatile="true" derived="true"
	 * @generated
	 */
	String getRepId();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.idl.Identifiable#getRepId <em>Rep Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rep Id</em>' attribute.
	 * @see #getRepId()
	 * @generated
	 */
	void setRepId(String value);

	/**
	 * Returns the value of the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Prefix</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Prefix</em>' attribute.
	 * @see #setPrefix(String)
	 * @see gov.redhawk.eclipsecorba.idl.IdlPackage#getIdentifiable_Prefix()
	 * @model derived="true"
	 * @generated
	 */
	String getPrefix();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.idl.Identifiable#getPrefix <em>Prefix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Prefix</em>' attribute.
	 * @see #getPrefix()
	 * @generated
	 */
	void setPrefix(String value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see gov.redhawk.eclipsecorba.idl.IdlPackage#getIdentifiable_Version()
	 * @model derived="true"
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.idl.Identifiable#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see gov.redhawk.eclipsecorba.idl.IdlPackage#getIdentifiable_Id()
	 * @model derived="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.idl.Identifiable#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void setFullId(String id);

} // Identifiable
