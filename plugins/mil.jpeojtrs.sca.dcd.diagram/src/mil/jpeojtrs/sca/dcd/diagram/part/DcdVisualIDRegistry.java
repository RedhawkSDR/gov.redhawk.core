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
package mil.jpeojtrs.sca.dcd.diagram.part;

import mil.jpeojtrs.sca.dcd.DcdPackage;
import mil.jpeojtrs.sca.dcd.DeviceConfiguration;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.ComponentSupportedInterfaceStubEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdComponentInstantiationEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdComponentInstantiationUsageNameEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdComponentPlacementCompartmentEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdComponentPlacementEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdComponentPlacementNameEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdConnectInterfaceEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdPartitioningCompartmentEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdPartitioningEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DeviceConfigurationEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.ProvidesPortStubEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.ProvidesPortStubNameEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.UsesPortStubEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.UsesPortStubNameEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.WrappingLabelEditPart;
import mil.jpeojtrs.sca.partitioning.PartitioningPackage;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class DcdVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "mil.jpeojtrs.sca.dcd.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (DeviceConfigurationEditPart.MODEL_ID.equals(view.getType())) {
				return DeviceConfigurationEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return mil.jpeojtrs.sca.dcd.diagram.part.DcdVisualIDRegistry
				.getVisualID(view.getType());
	}

	/**
	 * @generated
	 */
	public static String getModelID(View view) {
		View diagram = view.getDiagram();
		while (view != diagram) {
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if (annotation != null) {
				return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}

	/**
	 * @generated
	 */
	public static int getVisualID(String type) {
		try {
			return Integer.parseInt(type);
		} catch (NumberFormatException e) {
			if (Boolean.TRUE.toString().equalsIgnoreCase(
					Platform.getDebugOption(DEBUG_KEY))) {
				DcdDiagramEditorPlugin.getInstance().logError(
						"Unable to parse view type as a visualID number: "
								+ type);
			}
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static String getType(int visualID) {
		return Integer.toString(visualID);
	}

	/**
	 * @generated
	 */
	public static int getDiagramVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (DcdPackage.eINSTANCE.getDeviceConfiguration().isSuperTypeOf(
				domainElement.eClass())
				&& isDiagram((DeviceConfiguration) domainElement)) {
			return DeviceConfigurationEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static int getNodeVisualID(View containerView, EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		String containerModelID = mil.jpeojtrs.sca.dcd.diagram.part.DcdVisualIDRegistry
				.getModelID(containerView);
		if (!DeviceConfigurationEditPart.MODEL_ID.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (DeviceConfigurationEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = mil.jpeojtrs.sca.dcd.diagram.part.DcdVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = DeviceConfigurationEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case DeviceConfigurationEditPart.VISUAL_ID:
			if (DcdPackage.eINSTANCE.getDcdPartitioning().isSuperTypeOf(
					domainElement.eClass())) {
				return DcdPartitioningEditPart.VISUAL_ID;
			}
			break;
		case DcdComponentInstantiationEditPart.VISUAL_ID:
			if (PartitioningPackage.eINSTANCE.getUsesPortStub().isSuperTypeOf(
					domainElement.eClass())) {
				return UsesPortStubEditPart.VISUAL_ID;
			}
			if (PartitioningPackage.eINSTANCE.getProvidesPortStub()
					.isSuperTypeOf(domainElement.eClass())) {
				return ProvidesPortStubEditPart.VISUAL_ID;
			}
			if (PartitioningPackage.eINSTANCE
					.getComponentSupportedInterfaceStub().isSuperTypeOf(
							domainElement.eClass())) {
				return ComponentSupportedInterfaceStubEditPart.VISUAL_ID;
			}
			break;
		case DcdPartitioningCompartmentEditPart.VISUAL_ID:
			if (DcdPackage.eINSTANCE.getDcdComponentPlacement().isSuperTypeOf(
					domainElement.eClass())) {
				return DcdComponentPlacementEditPart.VISUAL_ID;
			}
			break;
		case DcdComponentPlacementCompartmentEditPart.VISUAL_ID:
			if (DcdPackage.eINSTANCE.getDcdComponentInstantiation()
					.isSuperTypeOf(domainElement.eClass())) {
				return DcdComponentInstantiationEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = mil.jpeojtrs.sca.dcd.diagram.part.DcdVisualIDRegistry
				.getModelID(containerView);
		if (!DeviceConfigurationEditPart.MODEL_ID.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (DeviceConfigurationEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = mil.jpeojtrs.sca.dcd.diagram.part.DcdVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = DeviceConfigurationEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case DeviceConfigurationEditPart.VISUAL_ID:
			if (DcdPartitioningEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DcdPartitioningEditPart.VISUAL_ID:
			if (WrappingLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DcdPartitioningCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DcdComponentPlacementEditPart.VISUAL_ID:
			if (DcdComponentPlacementNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DcdComponentPlacementCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DcdComponentInstantiationEditPart.VISUAL_ID:
			if (DcdComponentInstantiationUsageNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (UsesPortStubEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ProvidesPortStubEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ComponentSupportedInterfaceStubEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case UsesPortStubEditPart.VISUAL_ID:
			if (UsesPortStubNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ProvidesPortStubEditPart.VISUAL_ID:
			if (ProvidesPortStubNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DcdPartitioningCompartmentEditPart.VISUAL_ID:
			if (DcdComponentPlacementEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DcdComponentPlacementCompartmentEditPart.VISUAL_ID:
			if (DcdComponentInstantiationEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static int getLinkWithClassVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (DcdPackage.eINSTANCE.getDcdConnectInterface().isSuperTypeOf(
				domainElement.eClass())) {
			return DcdConnectInterfaceEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(DeviceConfiguration element) {
		return true;
	}

}
