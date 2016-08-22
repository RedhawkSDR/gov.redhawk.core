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
package gov.redhawk.core.graphiti.sad.ui.ext.impl;

import gov.redhawk.core.graphiti.sad.ui.ext.ComponentShape;
import gov.redhawk.core.graphiti.sad.ui.ext.RHSadGxPackage;
import gov.redhawk.core.graphiti.sad.ui.internal.diagram.patterns.ComponentPattern;
import gov.redhawk.core.graphiti.ui.ext.impl.RHContainerShapeImpl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.graphiti.mm.algorithms.Text;

import org.eclipse.graphiti.mm.pictograms.ContainerShape;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component Shape</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class ComponentShapeImpl extends RHContainerShapeImpl implements ComponentShape {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentShapeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RHSadGxPackage.Literals.COMPONENT_SHAPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ContainerShape getStartOrderEllipseShape() {
		return (ContainerShape) DUtil.findChildShapeByProperty(getInnerContainerShape(), DUtil.SHAPE_TYPE, ComponentPattern.SHAPE_START_ORDER_ELLIPSE_SHAPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Text getStartOrderText() {
		return (Text) DUtil.findFirstPropertyContainer(getStartOrderEllipseShape(), ComponentPattern.GA_START_ORDER_TEXT);
	}

} //ComponentShapeImpl
