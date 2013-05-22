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
package mil.jpeojtrs.sca.diagram;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.NamedStyle;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EObject Container Style</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link mil.jpeojtrs.sca.diagram.EObjectContainerStyle#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see mil.jpeojtrs.sca.diagram.DiagramPackage#getEObjectContainerStyle()
 * @model
 * @generated
 */
public interface EObjectContainerStyle extends NamedStyle {

	/**
	 * Returns the value of the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' containment reference.
	 * @see #setValue(EObject)
	 * @see mil.jpeojtrs.sca.diagram.DiagramPackage#getEObjectContainerStyle_Value()
	 * @model containment="true"
	 * @generated
	 */
	EObject getValue();

	/**
	 * Sets the value of the '{@link mil.jpeojtrs.sca.diagram.EObjectContainerStyle#getValue <em>Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' containment reference.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(EObject value);

} // EObjectContainerStyle
