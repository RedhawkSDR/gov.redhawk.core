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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import mil.jpeojtrs.sca.util.QueryParser;
import mil.jpeojtrs.sca.util.ScaFileSystemConstants;
import CF.FileSystem;
import CF.FileSystemOperations;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>File System</b></em>'.
 * @noimplement This interface is not intended to be implemented by clients.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.ScaFileSystem#getFileSystemURI <em>File System URI</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.model.sca.ScaPackage#getScaFileSystem()
 * @model abstract="true" superTypes="gov.redhawk.model.sca.CorbaObjWrapper<F> mil.jpeojtrs.sca.cf.FileSystemOperations gov.redhawk.model.sca.ScaFileStore" FBounds="mil.jpeojtrs.sca.cf.FileSystem"
 *        extendedMetaData="name='ScaFileSystem' kind='empty'"
 * @generated
 */
public interface ScaFileSystem< F extends FileSystem > extends CorbaObjWrapper<F>, FileSystemOperations, ScaFileStore {
	/**
	 * Returns the value of the '<em><b>File System URI</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>File System URI</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>File System URI</em>' attribute.
	 * @see #setFileSystemURI(URI)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaFileSystem_FileSystemURI()
	 * @model default="" dataType="gov.redhawk.model.sca.URI" required="true" transient="true" derived="true"
	 *        extendedMetaData="kind='attribute' name='fileSystemURI'"
	 * @generated
	 */
	URI getFileSystemURI();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaFileSystem#getFileSystemURI <em>File System URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 16.0
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File System URI</em>' attribute.
	 * @see #getFileSystemURI()
	 * @generated
	 */
	void setFileSystemURI(URI value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model dataType="mil.jpeojtrs.sca.spd.URI"
	 * @generated
	 */
	org.eclipse.emf.common.util.URI createURI(String path);

	/**
     * @since 14.0
     */
	final class Util {
		private Util(){
			
		}
		/**
		 * @since 13.0
		 * @param ior
		 * @return
		 * @throws URISyntaxException
		 */
		public static URI createFileSystemURI(String ior) throws URISyntaxException {
			if (ior == null) {
				return null;
			}
			final Map<String, String> queryParams = new HashMap<String, String>();
			queryParams.put(ScaFileSystemConstants.QUERY_PARAM_FS, ior);
			return new URI(ScaFileSystemConstants.SCHEME + "://?" + QueryParser.createQuery(queryParams));
		}
		
		public static URI createFileSystemURI(ScaFileSystem<?> fileSystem) throws URISyntaxException {
			return createFileSystemURI(fileSystem.getIor());
		}
	}
} // ScaFileSystem
