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

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Properties</b></em>'.
 *  @since 8.0
 *  @noimplement This interface is not intended to be implemented by clients.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.Properties#getProperty <em>Property</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.model.sca.ScaPackage#getProperties()
 * @model extendedMetaData="name='Properties' kind='elementOnly'"
 * @generated
 */
public interface Properties extends EObject {

	/**
	 * Returns the value of the '<em><b>Property</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property</em>' map.
	 * @see gov.redhawk.model.sca.ScaPackage#getProperties_Property()
	 * @model mapType="gov.redhawk.model.sca.StringToStringMap<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>"
	 *        extendedMetaData="kind='element' name='property'"
	 * @generated
	 */
	EMap<String, String> getProperty();

} // Properties
