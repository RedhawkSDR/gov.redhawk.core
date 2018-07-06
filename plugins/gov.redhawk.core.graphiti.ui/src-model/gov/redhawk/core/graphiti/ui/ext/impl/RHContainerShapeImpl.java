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
package gov.redhawk.core.graphiti.ui.ext.impl;

import gov.redhawk.core.graphiti.ui.ext.RHContainerShape;
import gov.redhawk.core.graphiti.ui.ext.RHGxPackage;
import gov.redhawk.core.graphiti.ui.util.DUtil;
import gov.redhawk.core.graphiti.ui.util.StyleUtil;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.graphiti.mm.algorithms.Image;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;

import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.mm.pictograms.impl.ContainerShapeImpl;
import org.eclipse.graphiti.services.Graphiti;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>RH Container Shape</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link gov.redhawk.core.graphiti.ui.ext.impl.RHContainerShapeImpl#isStarted <em>Started</em>}</li>
 *   <li>{@link gov.redhawk.core.graphiti.ui.ext.impl.RHContainerShapeImpl#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link gov.redhawk.core.graphiti.ui.ext.impl.RHContainerShapeImpl#getIStatusSeverity <em>IStatus Severity</em>}</li>
 *   <li>{@link gov.redhawk.core.graphiti.ui.ext.impl.RHContainerShapeImpl#isCollapsed <em>Collapsed</em>}</li>
 *   <li>{@link gov.redhawk.core.graphiti.ui.ext.impl.RHContainerShapeImpl#isHasSuperPortsContainerShape <em>Has Super Ports Container Shape</em>}</li>
 *   <li>{@link gov.redhawk.core.graphiti.ui.ext.impl.RHContainerShapeImpl#isHasPortsContainerShape <em>Has Ports Container Shape</em>}</li>
 *   <li>{@link gov.redhawk.core.graphiti.ui.ext.impl.RHContainerShapeImpl#isHideUnusedPorts <em>Hide Unused Ports</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RHContainerShapeImpl extends ContainerShapeImpl implements RHContainerShape {
	/**
	 * The default value of the '{@link #isStarted() <em>Started</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStarted()
	 * @generated
	 * @ordered
	 */
	protected static final boolean STARTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isStarted() <em>Started</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStarted()
	 * @generated
	 * @ordered
	 */
	protected boolean started = STARTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENABLED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected boolean enabled = ENABLED_EDEFAULT;

	/**
	 * The default value of the '{@link #getIStatusSeverity() <em>IStatus Severity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIStatusSeverity()
	 * @generated NOT
	 * @ordered
	 */
	protected static final int ISTATUS_SEVERITY_EDEFAULT = IStatus.OK;

	/**
	 * The cached value of the '{@link #getIStatusSeverity() <em>IStatus Severity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIStatusSeverity()
	 * @generated
	 * @ordered
	 */
	protected int iStatusSeverity = ISTATUS_SEVERITY_EDEFAULT;

	/**
	 * The default value of the '{@link #isCollapsed() <em>Collapsed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCollapsed()
	 * @generated
	 * @ordered
	 */
	protected static final boolean COLLAPSED_EDEFAULT = false;

	/**
	 * The default value of the '{@link #isHasSuperPortsContainerShape() <em>Has Super Ports Container Shape</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasSuperPortsContainerShape()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HAS_SUPER_PORTS_CONTAINER_SHAPE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHasSuperPortsContainerShape() <em>Has Super Ports Container Shape</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasSuperPortsContainerShape()
	 * @generated
	 * @ordered
	 */
	protected boolean hasSuperPortsContainerShape = HAS_SUPER_PORTS_CONTAINER_SHAPE_EDEFAULT;

	/**
	 * The default value of the '{@link #isHasPortsContainerShape() <em>Has Ports Container Shape</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasPortsContainerShape()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HAS_PORTS_CONTAINER_SHAPE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHasPortsContainerShape() <em>Has Ports Container Shape</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasPortsContainerShape()
	 * @generated
	 * @ordered
	 */
	protected boolean hasPortsContainerShape = HAS_PORTS_CONTAINER_SHAPE_EDEFAULT;

	/**
	 * The default value of the '{@link #isHideUnusedPorts() <em>Hide Unused Ports</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHideUnusedPorts()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HIDE_UNUSED_PORTS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHideUnusedPorts() <em>Hide Unused Ports</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHideUnusedPorts()
	 * @generated
	 * @ordered
	 */
	protected boolean hideUnusedPorts = HIDE_UNUSED_PORTS_EDEFAULT;

	// END GENERATED CODE

	// These are property key/value pairs that help us resize an existing shape by properly identifying
	// graphicsAlgorithms
	private static final String GA_OUTER_ROUNDED_RECTANGLE = "outerRoundedRectangle";
	private static final String GA_INNER_ROUNDED_RECTANGLE = "innerRoundedRectangle";
	private static final String GA_OUTER_ROUNDED_RECTANGLE_TEXT = "outerRoundedRectangleText";
	private static final String GA_INNER_ROUNDED_RECTANGLE_TEXT = "innerRoundedRectangleText";
	private static final String GA_OUTER_ROUNDED_RECTANGLE_IMAGE = "outerRoundedRectangleImage";
	private static final String GA_INNER_ROUNDED_RECTANGLE_IMAGE = "innerRoundedRectangleImage";
	private static final String GA_INNER_ROUNDED_RECTANGLE_LINE = "innerRoundedRectangleLine";
	private static final String GA_PROVIDES_PORT_RECTANGLE = "providesPortsRectangle";
	private static final String GA_USES_PORTS_RECTANGLE = "usesPortsRectangle";

	// Property key/value pairs help us identify Shapes to enable/disable user actions
	// (move, resize, delete, remove, etc.)
	private static final String SHAPE_OUTER_CONTAINER = "outerContainerShape";
	private static final String SHAPE_INNER_CONTAINER = "innerContainerShape";
	private static final String SHAPE_USES_PORTS_CONTAINER = "usesPortsContainerShape";
	private static final String SHAPE_PROVIDES_PORTS_CONTAINER = "providesPortsContainerShape";

	// Shape size constants
	private static final int INNER_ROUNDED_RECTANGLE_CORNER_WIDTH = 10;
	private static final int INNER_ROUNDED_RECTANGLE_CORNER_HEIGHT = 10;

	// BEGIN GENERATED CODE

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RHContainerShapeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RHGxPackage.Literals.RH_CONTAINER_SHAPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isStarted() {
		return started;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartedGen(boolean newStarted) {
		boolean oldStarted = started;
		started = newStarted;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RHGxPackage.RH_CONTAINER_SHAPE__STARTED, oldStarted, started));
	}

	// END GENERATED CODE

	@Override
	public void setStarted(boolean newStarted) {
		setStartedGen(newStarted);
		updateStyleForComponentInner();
	}

	// BEGIN GENERATED CODE

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnabledGen(boolean newEnabled) {
		boolean oldEnabled = enabled;
		enabled = newEnabled;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RHGxPackage.RH_CONTAINER_SHAPE__ENABLED, oldEnabled, enabled));
	}

	// END GENERATED CODE

	@Override
	public void setEnabled(boolean newEnabled) {
		setEnabledGen(newEnabled);
		updateStyleForComponentInner();
	}

	// BEGIN GENERATED CODE

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getIStatusSeverity() {
		return iStatusSeverity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIStatusSeverityGen(int newIStatusSeverity) {
		int oldIStatusSeverity = iStatusSeverity;
		iStatusSeverity = newIStatusSeverity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RHGxPackage.RH_CONTAINER_SHAPE__ISTATUS_SEVERITY, oldIStatusSeverity, iStatusSeverity));
	}

	// END GENERATED CODE

	public void setIStatusSeverity(int newIStatusSeverity) {
		setIStatusSeverityGen(newIStatusSeverity);
		updateStyleForComponentInner();
	}

	// BEGIN GENERATED CODE

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isCollapsed() {
		return isHasSuperPortsContainerShape();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setCollapsed(boolean newCollapsed) {
		if (newCollapsed) {
			setHasSuperPortsContainerShape(true);
			setHasPortsContainerShape(false);
		} else {
			setHasSuperPortsContainerShape(false);
			setHasPortsContainerShape(true);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isHasSuperPortsContainerShape() {
		return hasSuperPortsContainerShape;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHasSuperPortsContainerShape(boolean newHasSuperPortsContainerShape) {
		boolean oldHasSuperPortsContainerShape = hasSuperPortsContainerShape;
		hasSuperPortsContainerShape = newHasSuperPortsContainerShape;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RHGxPackage.RH_CONTAINER_SHAPE__HAS_SUPER_PORTS_CONTAINER_SHAPE,
				oldHasSuperPortsContainerShape, hasSuperPortsContainerShape));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isHasPortsContainerShape() {
		return hasPortsContainerShape;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHasPortsContainerShape(boolean newHasPortsContainerShape) {
		boolean oldHasPortsContainerShape = hasPortsContainerShape;
		hasPortsContainerShape = newHasPortsContainerShape;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RHGxPackage.RH_CONTAINER_SHAPE__HAS_PORTS_CONTAINER_SHAPE, oldHasPortsContainerShape,
				hasPortsContainerShape));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isHideUnusedPorts() {
		return hideUnusedPorts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHideUnusedPorts(boolean newHideUnusedPorts) {
		boolean oldHideUnusedPorts = hideUnusedPorts;
		hideUnusedPorts = newHideUnusedPorts;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RHGxPackage.RH_CONTAINER_SHAPE__HIDE_UNUSED_PORTS, oldHideUnusedPorts, hideUnusedPorts));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void init() {
		// Add property for this shape
		Graphiti.getPeService().setPropertyValue(this, DUtil.GA_TYPE, SHAPE_OUTER_CONTAINER);

		// Outer rectangle
		RoundedRectangle outerRoundedRectangle = Graphiti.getCreateService().createPlainRoundedRectangle(this, 5, 5);
		Graphiti.getPeService().setPropertyValue(outerRoundedRectangle, DUtil.GA_TYPE, GA_OUTER_ROUNDED_RECTANGLE);
		outerRoundedRectangle.setTransparency(null); // inherit from style

		// Outer image
		Image outerImage = Graphiti.getGaCreateService().createImage(outerRoundedRectangle, null);
		Graphiti.getPeService().setPropertyValue(outerImage, DUtil.GA_TYPE, GA_OUTER_ROUNDED_RECTANGLE_IMAGE);

		// Outer label
		Text outerText = Graphiti.getCreateService().createPlainText(outerRoundedRectangle);
		StyleUtil.setStyle(outerText, StyleUtil.OUTER_TEXT);
		Graphiti.getPeService().setPropertyValue(outerText, DUtil.GA_TYPE, GA_OUTER_ROUNDED_RECTANGLE_TEXT);

		// Inner container shape
		ContainerShape innerContainerShape = Graphiti.getCreateService().createContainerShape(this, false);
		Graphiti.getPeService().setPropertyValue(innerContainerShape, DUtil.GA_TYPE, SHAPE_INNER_CONTAINER);
		RoundedRectangle innerRoundedRectangle = Graphiti.getCreateService().createPlainRoundedRectangle(innerContainerShape,
			INNER_ROUNDED_RECTANGLE_CORNER_WIDTH, INNER_ROUNDED_RECTANGLE_CORNER_HEIGHT);
		Graphiti.getPeService().setPropertyValue(innerRoundedRectangle, DUtil.GA_TYPE, GA_INNER_ROUNDED_RECTANGLE);

		// Inner image
		Image innerImage = Graphiti.getGaCreateService().createImage(innerRoundedRectangle, null);
		Graphiti.getPeService().setPropertyValue(innerImage, DUtil.GA_TYPE, GA_INNER_ROUNDED_RECTANGLE_IMAGE);

		// Inner label
		Text innerText = Graphiti.getCreateService().createPlainText(innerRoundedRectangle);
		StyleUtil.setStyle(innerText, StyleUtil.INNER_TEXT);
		Graphiti.getPeService().setPropertyValue(innerText, DUtil.GA_TYPE, GA_INNER_ROUNDED_RECTANGLE_TEXT);

		// Inner separator line; points are created here, but will be positioned in a subsequent layout
		Polyline polyline = Graphiti.getGaCreateService().createPlainPolyline(innerRoundedRectangle, new int[] { 0, 0, innerRoundedRectangle.getWidth(), 0 });
		Graphiti.getPeService().setPropertyValue(polyline, DUtil.GA_TYPE, GA_INNER_ROUNDED_RECTANGLE_LINE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean update() {
		boolean updateStatus = false;
		if (isCollapsed()) {
			if (getSuperProvidesPortsContainerShape() == null) {
				addSuperProvidesPortContainerShape();
				updateStatus = true;
			}
			if (getSuperUsesPortsContainerShape() == null) {
				addSuperUsesPortContainerShape();
				updateStatus = true;
			}
		} else {
			if (getProvidesPortsContainerShape() == null) {
				addProvidesPortsContainerShape();
				updateStatus = true;
			}
			if (getUsesPortsContainerShape() == null) {
				addUsesPortsContainerShape();
				updateStatus = true;
			}
		}
		return updateStatus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Text getOuterText() {
		return (Text) DUtil.findFirstPropertyContainer(this, GA_OUTER_ROUNDED_RECTANGLE_TEXT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Image getOuterImage() {
		return (Image) DUtil.findFirstPropertyContainer(this, GA_OUTER_ROUNDED_RECTANGLE_IMAGE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Image getInnerImage() {
		return (Image) DUtil.findChildGraphicsAlgorithmByProperty(getInnerRoundedRectangle(), DUtil.GA_TYPE, GA_INNER_ROUNDED_RECTANGLE_IMAGE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Text getInnerText() {
		return (Text) DUtil.findChildGraphicsAlgorithmByProperty(getInnerRoundedRectangle(), DUtil.GA_TYPE, GA_INNER_ROUNDED_RECTANGLE_TEXT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Polyline getInnerPolyline() {
		return (Polyline) DUtil.findChildGraphicsAlgorithmByProperty(getInnerRoundedRectangle(), DUtil.GA_TYPE, GA_INNER_ROUNDED_RECTANGLE_LINE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ContainerShape getInnerContainerShape() {
		// Note: the inner shape is tagged with the GA_TYPE instead of the SHAPE_TYPE property
		return (ContainerShape) DUtil.findChildShapeByProperty(this, DUtil.GA_TYPE, SHAPE_INNER_CONTAINER);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ContainerShape getLollipop() {
		// Note: the lollipop shape is tagged with the GA_TYPE instead of the SHAPE_TYPE property
		return (ContainerShape) DUtil.findChildShapeByProperty(this, DUtil.GA_TYPE, SHAPE_INTERFACE_CONTAINER);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ContainerShape getProvidesPortsContainerShape() {
		return (ContainerShape) DUtil.findChildShapeByProperty(this, DUtil.SHAPE_TYPE, SHAPE_PROVIDES_PORTS_CONTAINER);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ContainerShape getSuperProvidesPortsContainerShape() {
		return (ContainerShape) DUtil.findChildShapeByProperty(this, DUtil.SHAPE_TYPE, SUPER_PROVIDES_PORTS_RECTANGLE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ContainerShape getUsesPortsContainerShape() {
		return (ContainerShape) DUtil.findChildShapeByProperty(this, DUtil.SHAPE_TYPE, SHAPE_USES_PORTS_CONTAINER);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ContainerShape getSuperUsesPortsContainerShape() {
		return (ContainerShape) DUtil.findChildShapeByProperty(this, DUtil.SHAPE_TYPE, SUPER_USES_PORTS_RECTANGLE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ContainerShape getStartOrderEllipseShape() {
		return (ContainerShape) DUtil.findChildShapeByProperty(getInnerContainerShape(), DUtil.SHAPE_TYPE, SHAPE_START_ORDER_ELLIPSE_SHAPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Text getStartOrderText() {
		return (Text) DUtil.findFirstPropertyContainer(getStartOrderEllipseShape(), GA_START_ORDER_TEXT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case RHGxPackage.RH_CONTAINER_SHAPE__STARTED:
			return isStarted();
		case RHGxPackage.RH_CONTAINER_SHAPE__ENABLED:
			return isEnabled();
		case RHGxPackage.RH_CONTAINER_SHAPE__ISTATUS_SEVERITY:
			return getIStatusSeverity();
		case RHGxPackage.RH_CONTAINER_SHAPE__COLLAPSED:
			return isCollapsed();
		case RHGxPackage.RH_CONTAINER_SHAPE__HAS_SUPER_PORTS_CONTAINER_SHAPE:
			return isHasSuperPortsContainerShape();
		case RHGxPackage.RH_CONTAINER_SHAPE__HAS_PORTS_CONTAINER_SHAPE:
			return isHasPortsContainerShape();
		case RHGxPackage.RH_CONTAINER_SHAPE__HIDE_UNUSED_PORTS:
			return isHideUnusedPorts();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case RHGxPackage.RH_CONTAINER_SHAPE__STARTED:
			setStarted((Boolean) newValue);
			return;
		case RHGxPackage.RH_CONTAINER_SHAPE__ENABLED:
			setEnabled((Boolean) newValue);
			return;
		case RHGxPackage.RH_CONTAINER_SHAPE__ISTATUS_SEVERITY:
			setIStatusSeverity((Integer) newValue);
			return;
		case RHGxPackage.RH_CONTAINER_SHAPE__COLLAPSED:
			setCollapsed((Boolean) newValue);
			return;
		case RHGxPackage.RH_CONTAINER_SHAPE__HAS_SUPER_PORTS_CONTAINER_SHAPE:
			setHasSuperPortsContainerShape((Boolean) newValue);
			return;
		case RHGxPackage.RH_CONTAINER_SHAPE__HAS_PORTS_CONTAINER_SHAPE:
			setHasPortsContainerShape((Boolean) newValue);
			return;
		case RHGxPackage.RH_CONTAINER_SHAPE__HIDE_UNUSED_PORTS:
			setHideUnusedPorts((Boolean) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case RHGxPackage.RH_CONTAINER_SHAPE__STARTED:
			setStarted(STARTED_EDEFAULT);
			return;
		case RHGxPackage.RH_CONTAINER_SHAPE__ENABLED:
			setEnabled(ENABLED_EDEFAULT);
			return;
		case RHGxPackage.RH_CONTAINER_SHAPE__ISTATUS_SEVERITY:
			setIStatusSeverity(ISTATUS_SEVERITY_EDEFAULT);
			return;
		case RHGxPackage.RH_CONTAINER_SHAPE__COLLAPSED:
			setCollapsed(COLLAPSED_EDEFAULT);
			return;
		case RHGxPackage.RH_CONTAINER_SHAPE__HAS_SUPER_PORTS_CONTAINER_SHAPE:
			setHasSuperPortsContainerShape(HAS_SUPER_PORTS_CONTAINER_SHAPE_EDEFAULT);
			return;
		case RHGxPackage.RH_CONTAINER_SHAPE__HAS_PORTS_CONTAINER_SHAPE:
			setHasPortsContainerShape(HAS_PORTS_CONTAINER_SHAPE_EDEFAULT);
			return;
		case RHGxPackage.RH_CONTAINER_SHAPE__HIDE_UNUSED_PORTS:
			setHideUnusedPorts(HIDE_UNUSED_PORTS_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case RHGxPackage.RH_CONTAINER_SHAPE__STARTED:
			return started != STARTED_EDEFAULT;
		case RHGxPackage.RH_CONTAINER_SHAPE__ENABLED:
			return enabled != ENABLED_EDEFAULT;
		case RHGxPackage.RH_CONTAINER_SHAPE__ISTATUS_SEVERITY:
			return iStatusSeverity != ISTATUS_SEVERITY_EDEFAULT;
		case RHGxPackage.RH_CONTAINER_SHAPE__COLLAPSED:
			return isCollapsed() != COLLAPSED_EDEFAULT;
		case RHGxPackage.RH_CONTAINER_SHAPE__HAS_SUPER_PORTS_CONTAINER_SHAPE:
			return hasSuperPortsContainerShape != HAS_SUPER_PORTS_CONTAINER_SHAPE_EDEFAULT;
		case RHGxPackage.RH_CONTAINER_SHAPE__HAS_PORTS_CONTAINER_SHAPE:
			return hasPortsContainerShape != HAS_PORTS_CONTAINER_SHAPE_EDEFAULT;
		case RHGxPackage.RH_CONTAINER_SHAPE__HIDE_UNUSED_PORTS:
			return hideUnusedPorts != HIDE_UNUSED_PORTS_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (started: ");
		result.append(started);
		result.append(", enabled: ");
		result.append(enabled);
		result.append(", iStatusSeverity: ");
		result.append(iStatusSeverity);
		result.append(", hasSuperPortsContainerShape: ");
		result.append(hasSuperPortsContainerShape);
		result.append(", hasPortsContainerShape: ");
		result.append(hasPortsContainerShape);
		result.append(", hideUnusedPorts: ");
		result.append(hideUnusedPorts);
		result.append(')');
		return result.toString();
	}

	// END GENERATED CODE

	private void updateStyleForComponentInner() {
		String styleId;
		if (iStatusSeverity == IStatus.ERROR) {
			styleId = StyleUtil.COMPONENT_INNER_ERROR;
		} else if (!enabled) {
			styleId = StyleUtil.COMPONENT_INNER_DISABLED;
		} else if (started) {
			styleId = StyleUtil.COMPONENT_INNER_STARTED;
		} else {
			styleId = StyleUtil.COMPONENT_INNER;
		}
		StyleUtil.setStyle(getInnerContainerShape().getGraphicsAlgorithm(), styleId);
		StyleUtil.setStyle(getInnerPolyline(), styleId);
	}

	/**
	 * Adds Super Uses Port to shape
	 * @param usesPortStubs
	 * @param featureProvider
	 */
	private void addSuperUsesPortContainerShape() {
		// port shape
		ContainerShape superUsesPortsRectangleShape = Graphiti.getCreateService().createContainerShape(this, true);
		// ref prevent move
		Graphiti.getPeService().setPropertyValue(superUsesPortsRectangleShape, DUtil.SHAPE_TYPE, SUPER_USES_PORTS_RECTANGLE);
		Rectangle superUsesPortsRectangle = Graphiti.getCreateService().createRectangle(superUsesPortsRectangleShape);
		StyleUtil.setStyle(superUsesPortsRectangle, StyleUtil.SUPER_USES_PORT);

		// fix point anchor
		createPortAnchor(superUsesPortsRectangleShape, 0);
	}

	/**
	 * Adds Super Provides Port to shape
	 * @param providesPortStubs
	 * @param featureProvider
	 */
	private void addSuperProvidesPortContainerShape() {

		// port shape
		ContainerShape superProvidesPortsRectangleShape = Graphiti.getCreateService().createContainerShape(this, true);
		Graphiti.getPeService().setPropertyValue(superProvidesPortsRectangleShape, DUtil.SHAPE_TYPE, SUPER_PROVIDES_PORTS_RECTANGLE); // ref

		Rectangle superProvidesPortsRectangle = Graphiti.getCreateService().createRectangle(superProvidesPortsRectangleShape);
		StyleUtil.setStyle(superProvidesPortsRectangle, StyleUtil.SUPER_PROVIDES_PORT);

		// fix point anchor
		createPortAnchor(superProvidesPortsRectangleShape, 0);
	}

	/**
	 * Create an anchor overlay for a port, with the anchor point vertically centered at horizontal position x.
	 * The returned anchor has an invisible rectangle for its graphics algorithm.
	 */
	private FixPointAnchor createPortAnchor(ContainerShape portShape, int x) {
		FixPointAnchor fixPointAnchor = DUtil.createOverlayAnchor(portShape, x);
		Rectangle fixPointAnchorRectangle = Graphiti.getCreateService().createPlainRectangle(fixPointAnchor);
		Graphiti.getPeService().setPropertyValue(fixPointAnchorRectangle, DUtil.GA_TYPE, GA_FIX_POINT_ANCHOR_RECTANGLE);
		fixPointAnchorRectangle.setFilled(false);
		fixPointAnchorRectangle.setLineVisible(false);
		return fixPointAnchor;
	}

	/**
	 * Adds an empty uses port container to provided container shape.
	 */
	private ContainerShape addUsesPortsContainerShape() {
		ContainerShape usesPortsContainerShape = Graphiti.getPeService().createContainerShape(this, true);
		Graphiti.getPeService().setPropertyValue(usesPortsContainerShape, DUtil.SHAPE_TYPE, SHAPE_USES_PORTS_CONTAINER);
		Rectangle usesPortsRectangle = Graphiti.getCreateService().createRectangle(usesPortsContainerShape);
		usesPortsRectangle.setTransparency(1d);
		Graphiti.getPeService().setPropertyValue(usesPortsRectangle, DUtil.GA_TYPE, GA_USES_PORTS_RECTANGLE);
		return usesPortsContainerShape;
	}

	/**
	 * Adds an empty provides port container to provided container shape.
	 */
	private ContainerShape addProvidesPortsContainerShape() {
		// provides (input)
		ContainerShape providesPortsContainerShape = Graphiti.getCreateService().createContainerShape(this, true);
		Graphiti.getPeService().setPropertyValue(providesPortsContainerShape, DUtil.SHAPE_TYPE, SHAPE_PROVIDES_PORTS_CONTAINER);
		Rectangle providesPortsRectangle = Graphiti.getCreateService().createRectangle(providesPortsContainerShape);
		providesPortsRectangle.setTransparency(1d);
		Graphiti.getPeService().setPropertyValue(providesPortsRectangle, DUtil.GA_TYPE, GA_PROVIDES_PORT_RECTANGLE);
		return providesPortsContainerShape;
	}

	private RoundedRectangle getInnerRoundedRectangle() {
		return (RoundedRectangle) getInnerContainerShape().getGraphicsAlgorithm();
	}

	// BEGIN GENERATED CODE

} //RHContainerShapeImpl
