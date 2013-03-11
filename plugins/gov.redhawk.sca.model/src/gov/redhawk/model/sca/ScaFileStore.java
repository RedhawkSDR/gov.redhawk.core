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

import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>File Store</b></em>'.
 * @noimplement This interface is not intended to be implemented by clients.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.ScaFileStore#getFileStore <em>File Store</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaFileStore#getChildren <em>Children</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaFileStore#getImageDesc <em>Image Desc</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaFileStore#isDirectory <em>Directory</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaFileStore#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.model.sca.ScaPackage#getScaFileStore()
 * @model superTypes="gov.redhawk.model.sca.IStatusProvider gov.redhawk.model.sca.IRefreshable"
 *        extendedMetaData="name='ScaFileStore' kind='empty'"
 * @generated
 */
public interface ScaFileStore extends IStatusProvider, IRefreshable {

	/**
	 * Returns the value of the '<em><b>File Store</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>File Store</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>File Store</em>' attribute.
	 * @see #setFileStore(IFileStore)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaFileStore_FileStore()
	 * @model dataType="gov.redhawk.model.sca.IFileStore" transient="true"
	 *        extendedMetaData="kind='attribute' name='fileStore'"
	 * @generated
	 */
	IFileStore getFileStore();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaFileStore#getFileStore <em>File Store</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File Store</em>' attribute.
	 * @see #getFileStore()
	 * @generated
	 */
	void setFileStore(IFileStore value);

	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link gov.redhawk.model.sca.ScaFileStore}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see #isSetChildren()
	 * @see #unsetChildren()
	 * @see gov.redhawk.model.sca.ScaPackage#getScaFileStore_Children()
	 * @model containment="true" resolveProxies="true" unsettable="true" transient="true"
	 *        extendedMetaData="kind='attribute' name='children'"
	 * @generated
	 */
	EList<ScaFileStore> getChildren();

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaFileStore#getChildren <em>Children</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetChildren()
	 * @see #getChildren()
	 * @generated
	 */
	void unsetChildren();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaFileStore#getChildren <em>Children</em>}' containment reference list is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Children</em>' containment reference list is set.
	 * @see #unsetChildren()
	 * @see #getChildren()
	 * @generated
	 */
	boolean isSetChildren();

	/**
	 * Returns the value of the '<em><b>Image Desc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Image Desc</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Image Desc</em>' attribute.
	 * @see #isSetImageDesc()
	 * @see #unsetImageDesc()
	 * @see #setImageDesc(Object)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaFileStore_ImageDesc()
	 * @model unsettable="true"
	 * @generated
	 */
	Object getImageDesc();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaFileStore#getImageDesc <em>Image Desc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Image Desc</em>' attribute.
	 * @see #isSetImageDesc()
	 * @see #unsetImageDesc()
	 * @see #getImageDesc()
	 * @generated
	 */
	void setImageDesc(Object value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaFileStore#getImageDesc <em>Image Desc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetImageDesc()
	 * @see #getImageDesc()
	 * @see #setImageDesc(Object)
	 * @generated
	 */
	void unsetImageDesc();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaFileStore#getImageDesc <em>Image Desc</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Image Desc</em>' attribute is set.
	 * @see #unsetImageDesc()
	 * @see #getImageDesc()
	 * @see #setImageDesc(Object)
	 * @generated
	 */
	boolean isSetImageDesc();

	/**
	 * Returns the value of the '<em><b>Directory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Directory</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Directory</em>' attribute.
	 * @see #setDirectory(boolean)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaFileStore_Directory()
	 * @model unique="false"
	 * @generated
	 */
	boolean isDirectory();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaFileStore#isDirectory <em>Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Directory</em>' attribute.
	 * @see #isDirectory()
	 * @generated
	 */
	void setDirectory(boolean value);

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
	 * @see gov.redhawk.model.sca.ScaPackage#getScaFileStore_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaFileStore#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	EList<ScaFileStore> fetchChildren(IProgressMonitor monitor);

} // ScaFileStore
