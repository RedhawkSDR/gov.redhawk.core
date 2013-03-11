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

package mil.jpeojtrs.sca.sad.diagram.part;

import mil.jpeojtrs.sca.partitioning.PartitioningPackage;
import mil.jpeojtrs.sca.sad.SadPackage;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentPlacement2EditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentPlacementCompartment2EditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentPlacementCompartmentEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentPlacementEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentPlacementName2EditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentPlacementNameEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentSupportedInterfaceStubEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.HostCollocationCompartmentEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.HostCollocationEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.HostCollocationIdEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.HostCollocationNameEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.PartitioningCompartmentEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.PartitioningEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ProvidesPortStubEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ProvidesPortStubNameEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.SadComponentInstantiationEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.SadComponentInstantiationUsageNameEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.SadConnectInterfaceEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.SoftwareAssemblyEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.UsesPortStubEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.UsesPortStubNameEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.WrappingLabelEditPart;

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
public class SadVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "mil.jpeojtrs.sca.sad.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (SoftwareAssemblyEditPart.MODEL_ID.equals(view.getType())) {
				return SoftwareAssemblyEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return mil.jpeojtrs.sca.sad.diagram.part.SadVisualIDRegistry
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
				SadDiagramEditorPlugin.getInstance().logError(
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
		if (SadPackage.eINSTANCE.getSoftwareAssembly().isSuperTypeOf(
				domainElement.eClass())
				&& isDiagram((SoftwareAssembly) domainElement)) {
			return SoftwareAssemblyEditPart.VISUAL_ID;
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
		String containerModelID = mil.jpeojtrs.sca.sad.diagram.part.SadVisualIDRegistry
				.getModelID(containerView);
		if (!SoftwareAssemblyEditPart.MODEL_ID.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (SoftwareAssemblyEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = mil.jpeojtrs.sca.sad.diagram.part.SadVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = SoftwareAssemblyEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case SoftwareAssemblyEditPart.VISUAL_ID:
			if (SadPackage.eINSTANCE.getSadPartitioning().isSuperTypeOf(
					domainElement.eClass())) {
				return PartitioningEditPart.VISUAL_ID;
			}
			break;
		case SadComponentInstantiationEditPart.VISUAL_ID:
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
		case PartitioningCompartmentEditPart.VISUAL_ID:
			if (SadPackage.eINSTANCE.getSadComponentPlacement().isSuperTypeOf(
					domainElement.eClass())) {
				return ComponentPlacementEditPart.VISUAL_ID;
			}
			if (SadPackage.eINSTANCE.getHostCollocation().isSuperTypeOf(
					domainElement.eClass())) {
				return HostCollocationEditPart.VISUAL_ID;
			}
			break;
		case ComponentPlacementCompartmentEditPart.VISUAL_ID:
			if (SadPackage.eINSTANCE.getSadComponentInstantiation()
					.isSuperTypeOf(domainElement.eClass())) {
				return SadComponentInstantiationEditPart.VISUAL_ID;
			}
			break;
		case HostCollocationCompartmentEditPart.VISUAL_ID:
			if (SadPackage.eINSTANCE.getSadComponentPlacement().isSuperTypeOf(
					domainElement.eClass())) {
				return ComponentPlacement2EditPart.VISUAL_ID;
			}
			break;
		case ComponentPlacementCompartment2EditPart.VISUAL_ID:
			if (SadPackage.eINSTANCE.getSadComponentInstantiation()
					.isSuperTypeOf(domainElement.eClass())) {
				return SadComponentInstantiationEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = mil.jpeojtrs.sca.sad.diagram.part.SadVisualIDRegistry
				.getModelID(containerView);
		if (!SoftwareAssemblyEditPart.MODEL_ID.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (SoftwareAssemblyEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = mil.jpeojtrs.sca.sad.diagram.part.SadVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = SoftwareAssemblyEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case SoftwareAssemblyEditPart.VISUAL_ID:
			if (PartitioningEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case PartitioningEditPart.VISUAL_ID:
			if (WrappingLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (PartitioningCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ComponentPlacementEditPart.VISUAL_ID:
			if (ComponentPlacementNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ComponentPlacementCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case SadComponentInstantiationEditPart.VISUAL_ID:
			if (SadComponentInstantiationUsageNameEditPart.VISUAL_ID == nodeVisualID) {
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
		case HostCollocationEditPart.VISUAL_ID:
			if (HostCollocationNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (HostCollocationIdEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (HostCollocationCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ComponentPlacement2EditPart.VISUAL_ID:
			if (ComponentPlacementName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ComponentPlacementCompartment2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case PartitioningCompartmentEditPart.VISUAL_ID:
			if (ComponentPlacementEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (HostCollocationEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ComponentPlacementCompartmentEditPart.VISUAL_ID:
			if (SadComponentInstantiationEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case HostCollocationCompartmentEditPart.VISUAL_ID:
			if (ComponentPlacement2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ComponentPlacementCompartment2EditPart.VISUAL_ID:
			if (SadComponentInstantiationEditPart.VISUAL_ID == nodeVisualID) {
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
		if (SadPackage.eINSTANCE.getSadConnectInterface().isSuperTypeOf(
				domainElement.eClass())) {
			return SadConnectInterfaceEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(SoftwareAssembly element) {
		return true;
	}

}
