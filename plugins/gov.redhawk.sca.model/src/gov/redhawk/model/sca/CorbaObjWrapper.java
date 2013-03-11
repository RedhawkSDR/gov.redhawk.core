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
package gov.redhawk.model.sca;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * <!-- begin-user-doc --> 
 * A representation of the model object '
 * <em><b>Corba Obj Wrapper</b></em>'.
 * @noimplement This interface is not intended to be implemented by clients. 
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.CorbaObjWrapper#getIor <em>Ior</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.CorbaObjWrapper#getObj <em>Obj</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.CorbaObjWrapper#getCorbaObj <em>Corba Obj</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.model.sca.ScaPackage#getCorbaObjWrapper()
 * @model abstract="true" TBounds="gov.redhawk.model.sca.Object"
 *        extendedMetaData="name='CorbaObjWrapper' kind='empty'"
 * @generated
 */
public interface CorbaObjWrapper< T extends org.omg.CORBA.Object > extends DataProviderObject {

	/**
	 * Returns the value of the '<em><b>Ior</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ior</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ior</em>' attribute.
	 * @see #isSetIor()
	 * @see #unsetIor()
	 * @see #setIor(String)
	 * @see gov.redhawk.model.sca.ScaPackage#getCorbaObjWrapper_Ior()
	 * @model unsettable="true" transient="true" derived="true"
	 *        extendedMetaData="kind='attribute' name='ior'"
	 * @generated
	 */
	String getIor();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.CorbaObjWrapper#getIor <em>Ior</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ior</em>' attribute.
	 * @see #isSetIor()
	 * @see #unsetIor()
	 * @see #getIor()
	 * @generated
	 */
	void setIor(String value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.CorbaObjWrapper#getIor <em>Ior</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIor()
	 * @see #getIor()
	 * @see #setIor(String)
	 * @generated
	 */
	void unsetIor();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.CorbaObjWrapper#getIor <em>Ior</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Ior</em>' attribute is set.
	 * @see #unsetIor()
	 * @see #getIor()
	 * @see #setIor(String)
	 * @generated
	 */
	boolean isSetIor();

	/**
	 * Returns the value of the '<em><b>Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Obj</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Obj</em>' attribute.
	 * @see #isSetObj()
	 * @see #unsetObj()
	 * @see #setObj(org.omg.CORBA.Object)
	 * @see gov.redhawk.model.sca.ScaPackage#getCorbaObjWrapper_Obj()
	 * @model unsettable="true" transient="true"
	 *        extendedMetaData="kind='attribute' name='obj'"
	 * @generated
	 */
	T getObj();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.CorbaObjWrapper#getObj <em>Obj</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Obj</em>' attribute.
	 * @see #isSetObj()
	 * @see #unsetObj()
	 * @see #getObj()
	 * @generated
	 */
	void setObj(T value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.CorbaObjWrapper#getObj <em>Obj</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetObj()
	 * @see #getObj()
	 * @see #setObj(org.omg.CORBA.Object)
	 * @generated
	 */
	void unsetObj();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.CorbaObjWrapper#getObj <em>Obj</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Obj</em>' attribute is set.
	 * @see #unsetObj()
	 * @see #getObj()
	 * @see #setObj(org.omg.CORBA.Object)
	 * @generated
	 */
	boolean isSetObj();

	/**
	 * Returns the value of the '<em><b>Corba Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Corba Obj</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Corba Obj</em>' attribute.
	 * @see #isSetCorbaObj()
	 * @see #unsetCorbaObj()
	 * @see #setCorbaObj(org.omg.CORBA.Object)
	 * @see gov.redhawk.model.sca.ScaPackage#getCorbaObjWrapper_CorbaObj()
	 * @model unsettable="true" dataType="gov.redhawk.model.sca.Object" transient="true"
	 * @generated
	 */
	org.omg.CORBA.Object getCorbaObj();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.CorbaObjWrapper#getCorbaObj <em>Corba Obj</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Corba Obj</em>' attribute.
	 * @see #isSetCorbaObj()
	 * @see #unsetCorbaObj()
	 * @see #getCorbaObj()
	 * @generated
	 */
	void setCorbaObj(org.omg.CORBA.Object value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.CorbaObjWrapper#getCorbaObj <em>Corba Obj</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetCorbaObj()
	 * @see #getCorbaObj()
	 * @see #setCorbaObj(org.omg.CORBA.Object)
	 * @generated
	 */
	void unsetCorbaObj();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.CorbaObjWrapper#getCorbaObj <em>Corba Obj</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Corba Obj</em>' attribute is set.
	 * @see #unsetCorbaObj()
	 * @see #getCorbaObj()
	 * @see #setCorbaObj(org.omg.CORBA.Object)
	 * @generated
	 */
	boolean isSetCorbaObj();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 * @generated
	 */
	boolean exists();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	void fetchAttributes(IProgressMonitor monitor);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	T fetchNarrowedObject(IProgressMonitor monitor);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean _is_a(String repId);

} // CorbaObjWrapper
