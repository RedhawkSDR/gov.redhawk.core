/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

// BEGIN GENERATED CODE
package gov.redhawk.core.graphiti.ui.ext;

import org.eclipse.graphiti.mm.algorithms.Image;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Text;

import org.eclipse.graphiti.mm.pictograms.ContainerShape;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>RH Container Shape</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link gov.redhawk.core.graphiti.ui.ext.RHContainerShape#isStarted <em>Started</em>}</li>
 *   <li>{@link gov.redhawk.core.graphiti.ui.ext.RHContainerShape#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link gov.redhawk.core.graphiti.ui.ext.RHContainerShape#getIStatusSeverity <em>IStatus Severity</em>}</li>
 *   <li>{@link gov.redhawk.core.graphiti.ui.ext.RHContainerShape#isCollapsed <em>Collapsed</em>}</li>
 *   <li>{@link gov.redhawk.core.graphiti.ui.ext.RHContainerShape#isHasSuperPortsContainerShape <em>Has Super Ports Container Shape</em>}</li>
 *   <li>{@link gov.redhawk.core.graphiti.ui.ext.RHContainerShape#isHasPortsContainerShape <em>Has Ports Container Shape</em>}</li>
 *   <li>{@link gov.redhawk.core.graphiti.ui.ext.RHContainerShape#isHideUnusedPorts <em>Hide Unused Ports</em>}</li>
 * </ul>
 *
 * @see gov.redhawk.core.graphiti.ui.ext.RHGxPackage#getRHContainerShape()
 * @model
 * @generated
 */
public interface RHContainerShape extends ContainerShape {

	// END GENERATED CODE

	// These are property key/value pairs that help us resize an existing shape by properly identifying
	// graphicsAlgorithms
	public static final String GA_FIX_POINT_ANCHOR_RECTANGLE = "fixPointAnchorRectangle";

	// Property key/value pairs help us identify Shapes to enable/disable user actions
	// (move, resize, delete, remove, etc.)
	public static final String SHAPE_INTERFACE_CONTAINER = "interfaceContainerShape";
	public static final String SUPER_PROVIDES_PORTS_RECTANGLE = "superProvidesPortsContainer";
	public static final String SUPER_USES_PORTS_RECTANGLE = "superUsesPortsContainer";

	// BEGIN GENERATED CODE

	/**
	 * Returns the value of the '<em><b>Started</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If the component is started
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Started</em>' attribute.
	 * @see #setStarted(boolean)
	 * @see gov.redhawk.core.graphiti.ui.ext.RHGxPackage#getRHContainerShape_Started()
	 * @model unique="false" transient="true"
	 * @generated
	 */
	boolean isStarted();

	/**
	 * Sets the value of the '{@link gov.redhawk.core.graphiti.ui.ext.RHContainerShape#isStarted <em>Started</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Started</em>' attribute.
	 * @see #isStarted()
	 * @generated
	 */
	void setStarted(boolean value);

	/**
	 * Returns the value of the '<em><b>Enabled</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Is the component enabled?
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Enabled</em>' attribute.
	 * @see #setEnabled(boolean)
	 * @see gov.redhawk.core.graphiti.ui.ext.RHGxPackage#getRHContainerShape_Enabled()
	 * @model default="true" unique="false" transient="true"
	 * @generated
	 */
	boolean isEnabled();

	/**
	 * Sets the value of the '{@link gov.redhawk.core.graphiti.ui.ext.RHContainerShape#isEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enabled</em>' attribute.
	 * @see #isEnabled()
	 * @generated
	 */
	void setEnabled(boolean value);

	/**
	 * Returns the value of the '<em><b>IStatus Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The severity of the component's status
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>IStatus Severity</em>' attribute.
	 * @see #setIStatusSeverity(int)
	 * @see gov.redhawk.core.graphiti.ui.ext.RHGxPackage#getRHContainerShape_IStatusSeverity()
	 * @model unique="false" transient="true"
	 * @generated
	 */
	int getIStatusSeverity();

	/**
	 * Sets the value of the '{@link gov.redhawk.core.graphiti.ui.ext.RHContainerShape#getIStatusSeverity <em>IStatus Severity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>IStatus Severity</em>' attribute.
	 * @see #getIStatusSeverity()
	 * @generated
	 */
	void setIStatusSeverity(int value);

	/**
	 * Returns the value of the '<em><b>Collapsed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Flag for whether ports are expanded or not
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Collapsed</em>' attribute.
	 * @see #setCollapsed(boolean)
	 * @see gov.redhawk.core.graphiti.ui.ext.RHGxPackage#getRHContainerShape_Collapsed()
	 * @model unique="false" transient="true" volatile="true" derived="true"
	 * @generated
	 */
	boolean isCollapsed();

	/**
	 * Sets the value of the '{@link gov.redhawk.core.graphiti.ui.ext.RHContainerShape#isCollapsed <em>Collapsed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Collapsed</em>' attribute.
	 * @see #isCollapsed()
	 * @generated
	 */
	void setCollapsed(boolean value);

	/**
	 * Returns the value of the '<em><b>Has Super Ports Container Shape</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Super Ports Container Shape</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Super Ports Container Shape</em>' attribute.
	 * @see #setHasSuperPortsContainerShape(boolean)
	 * @see gov.redhawk.core.graphiti.ui.ext.RHGxPackage#getRHContainerShape_HasSuperPortsContainerShape()
	 * @model unique="false"
	 * @generated
	 */
	boolean isHasSuperPortsContainerShape();

	/**
	 * Sets the value of the '{@link gov.redhawk.core.graphiti.ui.ext.RHContainerShape#isHasSuperPortsContainerShape <em>Has Super Ports Container Shape</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Super Ports Container Shape</em>' attribute.
	 * @see #isHasSuperPortsContainerShape()
	 * @generated
	 */
	void setHasSuperPortsContainerShape(boolean value);

	/**
	 * Returns the value of the '<em><b>Has Ports Container Shape</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Ports Container Shape</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Ports Container Shape</em>' attribute.
	 * @see #setHasPortsContainerShape(boolean)
	 * @see gov.redhawk.core.graphiti.ui.ext.RHGxPackage#getRHContainerShape_HasPortsContainerShape()
	 * @model unique="false"
	 * @generated
	 */
	boolean isHasPortsContainerShape();

	/**
	 * Sets the value of the '{@link gov.redhawk.core.graphiti.ui.ext.RHContainerShape#isHasPortsContainerShape <em>Has Ports Container Shape</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Ports Container Shape</em>' attribute.
	 * @see #isHasPortsContainerShape()
	 * @generated
	 */
	void setHasPortsContainerShape(boolean value);

	/**
	 * Returns the value of the '<em><b>Hide Unused Ports</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hide Unused Ports</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hide Unused Ports</em>' attribute.
	 * @see #setHideUnusedPorts(boolean)
	 * @see gov.redhawk.core.graphiti.ui.ext.RHGxPackage#getRHContainerShape_HideUnusedPorts()
	 * @model unique="false"
	 * @generated
	 */
	boolean isHideUnusedPorts();

	/**
	 * Sets the value of the '{@link gov.redhawk.core.graphiti.ui.ext.RHContainerShape#isHideUnusedPorts <em>Hide Unused Ports</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hide Unused Ports</em>' attribute.
	 * @see #isHideUnusedPorts()
	 * @generated
	 */
	void setHideUnusedPorts(boolean value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void init();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Updates the shape with supplied values
	 * <!-- end-model-doc -->
	 * @model unique="false"
	 * @generated
	 */
	boolean update();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Return the text for outer container
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 * @generated
	 */
	Text getOuterText();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Return the image for outer container
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 * @generated
	 */
	Image getOuterImage();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Return the image for inner container
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 * @generated
	 */
	Image getInnerImage();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Return the text for inner container
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 * @generated
	 */
	Text getInnerText();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Return the internal separator line
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 * @generated
	 */
	Polyline getInnerPolyline();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Return the inner container shape
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 * @generated
	 */
	ContainerShape getInnerContainerShape();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Return the interface lollipop shape
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 * @generated
	 */
	ContainerShape getLollipop();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Return the provides ports container
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 * @generated
	 */
	ContainerShape getProvidesPortsContainerShape();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Return the collapsed provides ports container
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 * @generated
	 */
	ContainerShape getSuperProvidesPortsContainerShape();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Return to uses ports container
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 * @generated
	 */
	ContainerShape getUsesPortsContainerShape();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Return the collapsed uses ports container
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 * @generated
	 */
	ContainerShape getSuperUsesPortsContainerShape();

} // RHContainerShape
