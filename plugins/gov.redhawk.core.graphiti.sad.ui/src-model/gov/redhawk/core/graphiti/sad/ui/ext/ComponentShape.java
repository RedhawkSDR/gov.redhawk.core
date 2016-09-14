/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */

// BEGIN GENERATED CODE
package gov.redhawk.core.graphiti.sad.ui.ext;

import gov.redhawk.core.graphiti.ui.ext.RHContainerShape;

import org.eclipse.graphiti.mm.algorithms.Text;

import org.eclipse.graphiti.mm.pictograms.ContainerShape;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Shape</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see gov.redhawk.core.graphiti.sad.ui.ext.RHSadGxPackage#getComponentShape()
 * @model
 * @generated
 */
public interface ComponentShape extends RHContainerShape {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns the start order ellipse
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 * @generated
	 */
	ContainerShape getStartOrderEllipseShape();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns the start order text
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 * @generated
	 */
	Text getStartOrderText();

} // ComponentShape
