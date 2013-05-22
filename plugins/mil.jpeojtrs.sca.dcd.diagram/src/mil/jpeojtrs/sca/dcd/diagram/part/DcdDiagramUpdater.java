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

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import mil.jpeojtrs.sca.dcd.DcdComponentInstantiation;
import mil.jpeojtrs.sca.dcd.DcdComponentPlacement;
import mil.jpeojtrs.sca.dcd.DcdConnectInterface;
import mil.jpeojtrs.sca.dcd.DcdPartitioning;
import mil.jpeojtrs.sca.dcd.DeviceConfiguration;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.ComponentSupportedInterfaceStubEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdComponentInstantiationEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdComponentPlacementCompartmentEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdComponentPlacementEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdConnectInterfaceEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdPartitioningCompartmentEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdPartitioningEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DeviceConfigurationEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.ProvidesPortStubEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.UsesPortStubEditPart;
import mil.jpeojtrs.sca.dcd.diagram.providers.DcdElementTypes;
import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;
import mil.jpeojtrs.sca.partitioning.ComponentPlacement;
import mil.jpeojtrs.sca.partitioning.ComponentSupportedInterfaceStub;
import mil.jpeojtrs.sca.partitioning.ConnectionTarget;
import mil.jpeojtrs.sca.partitioning.Connections;
import mil.jpeojtrs.sca.partitioning.PartitioningPackage;
import mil.jpeojtrs.sca.partitioning.ProvidesPortStub;
import mil.jpeojtrs.sca.partitioning.UsesPortStub;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class DcdDiagramUpdater {

	/**
	 * @generated
	 */
	public static List<DcdNodeDescriptor> getSemanticChildren(View view) {
		switch (DcdVisualIDRegistry.getVisualID(view)) {
		case DeviceConfigurationEditPart.VISUAL_ID:
			return getDeviceConfiguration_1000SemanticChildren(view);
		case DcdComponentInstantiationEditPart.VISUAL_ID:
			return getDcdComponentInstantiation_3002SemanticChildren(view);
		case DcdPartitioningCompartmentEditPart.VISUAL_ID:
			return getDcdPartitioningPartitioningCompartment_7001SemanticChildren(view);
		case DcdComponentPlacementCompartmentEditPart.VISUAL_ID:
			return getDcdComponentPlacementComponentPlacementCompartment_7002SemanticChildren(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<DcdNodeDescriptor> getDeviceConfiguration_1000SemanticChildren(
			View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		DeviceConfiguration modelElement = (DeviceConfiguration) view
				.getElement();
		LinkedList<DcdNodeDescriptor> result = new LinkedList<DcdNodeDescriptor>();
		{
			DcdPartitioning childElement = modelElement.getPartitioning();
			int visualID = DcdVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == DcdPartitioningEditPart.VISUAL_ID) {
				result.add(new DcdNodeDescriptor(childElement, visualID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<DcdNodeDescriptor> getDcdComponentInstantiation_3002SemanticChildren(
			View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		DcdComponentInstantiation modelElement = (DcdComponentInstantiation) view
				.getElement();
		LinkedList<DcdNodeDescriptor> result = new LinkedList<DcdNodeDescriptor>();
		for (Iterator<?> it = modelElement.getUses().iterator(); it.hasNext();) {
			UsesPortStub childElement = (UsesPortStub) it.next();
			int visualID = DcdVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == UsesPortStubEditPart.VISUAL_ID) {
				result.add(new DcdNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getProvides().iterator(); it
				.hasNext();) {
			ProvidesPortStub childElement = (ProvidesPortStub) it.next();
			int visualID = DcdVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ProvidesPortStubEditPart.VISUAL_ID) {
				result.add(new DcdNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		{
			ComponentSupportedInterfaceStub childElement = modelElement
					.getInterfaceStub();
			int visualID = DcdVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ComponentSupportedInterfaceStubEditPart.VISUAL_ID) {
				result.add(new DcdNodeDescriptor(childElement, visualID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<DcdNodeDescriptor> getDcdPartitioningPartitioningCompartment_7001SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		DcdPartitioning modelElement = (DcdPartitioning) containerView
				.getElement();
		LinkedList<DcdNodeDescriptor> result = new LinkedList<DcdNodeDescriptor>();
		for (Iterator<?> it = modelElement.getComponentPlacement().iterator(); it
				.hasNext();) {
			ComponentPlacement childElement = (ComponentPlacement) it.next();
			int visualID = DcdVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == DcdComponentPlacementEditPart.VISUAL_ID) {
				result.add(new DcdNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<DcdNodeDescriptor> getDcdComponentPlacementComponentPlacementCompartment_7002SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		DcdComponentPlacement modelElement = (DcdComponentPlacement) containerView
				.getElement();
		LinkedList<DcdNodeDescriptor> result = new LinkedList<DcdNodeDescriptor>();
		for (Iterator<?> it = modelElement.getComponentInstantiation()
				.iterator(); it.hasNext();) {
			ComponentInstantiation childElement = (ComponentInstantiation) it
					.next();
			int visualID = DcdVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == DcdComponentInstantiationEditPart.VISUAL_ID) {
				result.add(new DcdNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<DcdLinkDescriptor> getContainedLinks(View view) {
		switch (DcdVisualIDRegistry.getVisualID(view)) {
		case DeviceConfigurationEditPart.VISUAL_ID:
			return getDeviceConfiguration_1000ContainedLinks(view);
		case DcdPartitioningEditPart.VISUAL_ID:
			return getDcdPartitioning_2001ContainedLinks(view);
		case DcdComponentPlacementEditPart.VISUAL_ID:
			return getDcdComponentPlacement_3001ContainedLinks(view);
		case DcdComponentInstantiationEditPart.VISUAL_ID:
			return getDcdComponentInstantiation_3002ContainedLinks(view);
		case UsesPortStubEditPart.VISUAL_ID:
			return getUsesPortStub_3003ContainedLinks(view);
		case ProvidesPortStubEditPart.VISUAL_ID:
			return getProvidesPortStub_3004ContainedLinks(view);
		case ComponentSupportedInterfaceStubEditPart.VISUAL_ID:
			return getComponentSupportedInterfaceStub_3005ContainedLinks(view);
		case DcdConnectInterfaceEditPart.VISUAL_ID:
			return getDcdConnectInterface_4001ContainedLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<DcdLinkDescriptor> getIncomingLinks(View view) {
		switch (DcdVisualIDRegistry.getVisualID(view)) {
		case DcdPartitioningEditPart.VISUAL_ID:
			return getDcdPartitioning_2001IncomingLinks(view);
		case DcdComponentPlacementEditPart.VISUAL_ID:
			return getDcdComponentPlacement_3001IncomingLinks(view);
		case DcdComponentInstantiationEditPart.VISUAL_ID:
			return getDcdComponentInstantiation_3002IncomingLinks(view);
		case UsesPortStubEditPart.VISUAL_ID:
			return getUsesPortStub_3003IncomingLinks(view);
		case ProvidesPortStubEditPart.VISUAL_ID:
			return getProvidesPortStub_3004IncomingLinks(view);
		case ComponentSupportedInterfaceStubEditPart.VISUAL_ID:
			return getComponentSupportedInterfaceStub_3005IncomingLinks(view);
		case DcdConnectInterfaceEditPart.VISUAL_ID:
			return getDcdConnectInterface_4001IncomingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<DcdLinkDescriptor> getOutgoingLinks(View view) {
		switch (DcdVisualIDRegistry.getVisualID(view)) {
		case DcdPartitioningEditPart.VISUAL_ID:
			return getDcdPartitioning_2001OutgoingLinks(view);
		case DcdComponentPlacementEditPart.VISUAL_ID:
			return getDcdComponentPlacement_3001OutgoingLinks(view);
		case DcdComponentInstantiationEditPart.VISUAL_ID:
			return getDcdComponentInstantiation_3002OutgoingLinks(view);
		case UsesPortStubEditPart.VISUAL_ID:
			return getUsesPortStub_3003OutgoingLinks(view);
		case ProvidesPortStubEditPart.VISUAL_ID:
			return getProvidesPortStub_3004OutgoingLinks(view);
		case ComponentSupportedInterfaceStubEditPart.VISUAL_ID:
			return getComponentSupportedInterfaceStub_3005OutgoingLinks(view);
		case DcdConnectInterfaceEditPart.VISUAL_ID:
			return getDcdConnectInterface_4001OutgoingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<DcdLinkDescriptor> getDeviceConfiguration_1000ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<DcdLinkDescriptor> getDcdPartitioning_2001ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<DcdLinkDescriptor> getDcdComponentPlacement_3001ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<DcdLinkDescriptor> getDcdComponentInstantiation_3002ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<DcdLinkDescriptor> getUsesPortStub_3003ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<DcdLinkDescriptor> getProvidesPortStub_3004ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<DcdLinkDescriptor> getComponentSupportedInterfaceStub_3005ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<DcdLinkDescriptor> getDcdConnectInterface_4001ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<DcdLinkDescriptor> getDcdPartitioning_2001IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<DcdLinkDescriptor> getDcdComponentPlacement_3001IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<DcdLinkDescriptor> getDcdComponentInstantiation_3002IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<DcdLinkDescriptor> getUsesPortStub_3003IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<DcdLinkDescriptor> getProvidesPortStub_3004IncomingLinks(
			View view) {
		ProvidesPortStub modelElement = (ProvidesPortStub) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<DcdLinkDescriptor> result = new LinkedList<DcdLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_DcdConnectInterface_4001(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<DcdLinkDescriptor> getComponentSupportedInterfaceStub_3005IncomingLinks(
			View view) {
		ComponentSupportedInterfaceStub modelElement = (ComponentSupportedInterfaceStub) view
				.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<DcdLinkDescriptor> result = new LinkedList<DcdLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_DcdConnectInterface_4001(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<DcdLinkDescriptor> getDcdConnectInterface_4001IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<DcdLinkDescriptor> getDcdPartitioning_2001OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<DcdLinkDescriptor> getDcdComponentPlacement_3001OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<DcdLinkDescriptor> getDcdComponentInstantiation_3002OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<DcdLinkDescriptor> getUsesPortStub_3003OutgoingLinks(
			View view) {
		UsesPortStub modelElement = (UsesPortStub) view.getElement();
		LinkedList<DcdLinkDescriptor> result = new LinkedList<DcdLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_DcdConnectInterface_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<DcdLinkDescriptor> getProvidesPortStub_3004OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<DcdLinkDescriptor> getComponentSupportedInterfaceStub_3005OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<DcdLinkDescriptor> getDcdConnectInterface_4001OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	private static Collection<DcdLinkDescriptor> getIncomingTypeModelFacetLinks_DcdConnectInterface_4001(
			ConnectionTarget target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<DcdLinkDescriptor> result = new LinkedList<DcdLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != PartitioningPackage.eINSTANCE
					.getConnectInterface_Target()
					|| false == setting.getEObject() instanceof DcdConnectInterface) {
				continue;
			}
			DcdConnectInterface link = (DcdConnectInterface) setting
					.getEObject();
			if (DcdConnectInterfaceEditPart.VISUAL_ID != DcdVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			UsesPortStub src = link.getSource();
			result.add(new DcdLinkDescriptor(src, target, link,
					DcdElementTypes.DcdConnectInterface_4001,
					DcdConnectInterfaceEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<DcdLinkDescriptor> getOutgoingTypeModelFacetLinks_DcdConnectInterface_4001(
			UsesPortStub source) {
		Connections container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Connections) {
				container = (Connections) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<DcdLinkDescriptor> result = new LinkedList<DcdLinkDescriptor>();
		for (Iterator<?> links = container.getConnectInterface().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof DcdConnectInterface) {
				continue;
			}
			DcdConnectInterface link = (DcdConnectInterface) linkObject;
			if (DcdConnectInterfaceEditPart.VISUAL_ID != DcdVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			ConnectionTarget dst = link.getTarget();
			UsesPortStub src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new DcdLinkDescriptor(src, dst, link,
					DcdElementTypes.DcdConnectInterface_4001,
					DcdConnectInterfaceEditPart.VISUAL_ID));
		}
		return result;
	}

}
