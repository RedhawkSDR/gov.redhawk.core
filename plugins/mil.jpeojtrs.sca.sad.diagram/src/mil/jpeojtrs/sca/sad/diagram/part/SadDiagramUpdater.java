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

package mil.jpeojtrs.sca.sad.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;
import mil.jpeojtrs.sca.partitioning.ComponentPlacement;
import mil.jpeojtrs.sca.partitioning.ComponentSupportedInterfaceStub;
import mil.jpeojtrs.sca.partitioning.ConnectionTarget;
import mil.jpeojtrs.sca.partitioning.Connections;
import mil.jpeojtrs.sca.partitioning.PartitioningPackage;
import mil.jpeojtrs.sca.partitioning.ProvidesPortStub;
import mil.jpeojtrs.sca.partitioning.UsesPortStub;
import mil.jpeojtrs.sca.sad.HostCollocation;
import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.sad.SadComponentPlacement;
import mil.jpeojtrs.sca.sad.SadConnectInterface;
import mil.jpeojtrs.sca.sad.SadPartitioning;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentPlacement2EditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentPlacementCompartment2EditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentPlacementCompartmentEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentPlacementEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentSupportedInterfaceStubEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.HostCollocationCompartmentEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.HostCollocationEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.PartitioningCompartmentEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.PartitioningEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ProvidesPortStubEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.SadComponentInstantiationEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.SadConnectInterfaceEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.SoftwareAssemblyEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.UsesPortStubEditPart;
import mil.jpeojtrs.sca.sad.diagram.providers.SadElementTypes;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class SadDiagramUpdater {

	/**
	 * @generated
	 */
	public static List<SadNodeDescriptor> getSemanticChildren(View view) {
		switch (SadVisualIDRegistry.getVisualID(view)) {
		case SoftwareAssemblyEditPart.VISUAL_ID:
			return getSoftwareAssembly_1000SemanticChildren(view);
		case SadComponentInstantiationEditPart.VISUAL_ID:
			return getSadComponentInstantiation_3002SemanticChildren(view);
		case PartitioningCompartmentEditPart.VISUAL_ID:
			return getSadPartitioningPartitioningCompartment_7001SemanticChildren(view);
		case ComponentPlacementCompartmentEditPart.VISUAL_ID:
			return getSadComponentPlacementComponentPlacementCompartment_7002SemanticChildren(view);
		case HostCollocationCompartmentEditPart.VISUAL_ID:
			return getHostCollocationHostCollocationCompartment_7003SemanticChildren(view);
		case ComponentPlacementCompartment2EditPart.VISUAL_ID:
			return getSadComponentPlacementComponentPlacementCompartment_7004SemanticChildren(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SadNodeDescriptor> getSoftwareAssembly_1000SemanticChildren(
			View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		SoftwareAssembly modelElement = (SoftwareAssembly) view.getElement();
		LinkedList<SadNodeDescriptor> result = new LinkedList<SadNodeDescriptor>();
		{
			SadPartitioning childElement = modelElement.getPartitioning();
			int visualID = SadVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == PartitioningEditPart.VISUAL_ID) {
				result.add(new SadNodeDescriptor(childElement, visualID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SadNodeDescriptor> getSadComponentInstantiation_3002SemanticChildren(
			View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		SadComponentInstantiation modelElement = (SadComponentInstantiation) view
				.getElement();
		LinkedList<SadNodeDescriptor> result = new LinkedList<SadNodeDescriptor>();
		for (Iterator<?> it = modelElement.getUses().iterator(); it.hasNext();) {
			UsesPortStub childElement = (UsesPortStub) it.next();
			int visualID = SadVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == UsesPortStubEditPart.VISUAL_ID) {
				result.add(new SadNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getProvides().iterator(); it
				.hasNext();) {
			ProvidesPortStub childElement = (ProvidesPortStub) it.next();
			int visualID = SadVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ProvidesPortStubEditPart.VISUAL_ID) {
				result.add(new SadNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		{
			ComponentSupportedInterfaceStub childElement = modelElement
					.getInterfaceStub();
			int visualID = SadVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ComponentSupportedInterfaceStubEditPart.VISUAL_ID) {
				result.add(new SadNodeDescriptor(childElement, visualID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SadNodeDescriptor> getSadPartitioningPartitioningCompartment_7001SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		SadPartitioning modelElement = (SadPartitioning) containerView
				.getElement();
		LinkedList<SadNodeDescriptor> result = new LinkedList<SadNodeDescriptor>();
		for (Iterator<?> it = modelElement.getComponentPlacement().iterator(); it
				.hasNext();) {
			ComponentPlacement childElement = (ComponentPlacement) it.next();
			int visualID = SadVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ComponentPlacementEditPart.VISUAL_ID) {
				result.add(new SadNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getHostCollocation().iterator(); it
				.hasNext();) {
			HostCollocation childElement = (HostCollocation) it.next();
			int visualID = SadVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == HostCollocationEditPart.VISUAL_ID) {
				result.add(new SadNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SadNodeDescriptor> getSadComponentPlacementComponentPlacementCompartment_7002SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		SadComponentPlacement modelElement = (SadComponentPlacement) containerView
				.getElement();
		LinkedList<SadNodeDescriptor> result = new LinkedList<SadNodeDescriptor>();
		for (Iterator<?> it = modelElement.getComponentInstantiation()
				.iterator(); it.hasNext();) {
			ComponentInstantiation childElement = (ComponentInstantiation) it
					.next();
			int visualID = SadVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == SadComponentInstantiationEditPart.VISUAL_ID) {
				result.add(new SadNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SadNodeDescriptor> getHostCollocationHostCollocationCompartment_7003SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		HostCollocation modelElement = (HostCollocation) containerView
				.getElement();
		LinkedList<SadNodeDescriptor> result = new LinkedList<SadNodeDescriptor>();
		for (Iterator<?> it = modelElement.getComponentPlacement().iterator(); it
				.hasNext();) {
			SadComponentPlacement childElement = (SadComponentPlacement) it
					.next();
			int visualID = SadVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ComponentPlacement2EditPart.VISUAL_ID) {
				result.add(new SadNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SadNodeDescriptor> getSadComponentPlacementComponentPlacementCompartment_7004SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		SadComponentPlacement modelElement = (SadComponentPlacement) containerView
				.getElement();
		LinkedList<SadNodeDescriptor> result = new LinkedList<SadNodeDescriptor>();
		for (Iterator<?> it = modelElement.getComponentInstantiation()
				.iterator(); it.hasNext();) {
			ComponentInstantiation childElement = (ComponentInstantiation) it
					.next();
			int visualID = SadVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == SadComponentInstantiationEditPart.VISUAL_ID) {
				result.add(new SadNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SadLinkDescriptor> getContainedLinks(View view) {
		switch (SadVisualIDRegistry.getVisualID(view)) {
		case SoftwareAssemblyEditPart.VISUAL_ID:
			return getSoftwareAssembly_1000ContainedLinks(view);
		case PartitioningEditPart.VISUAL_ID:
			return getSadPartitioning_2001ContainedLinks(view);
		case ComponentPlacementEditPart.VISUAL_ID:
			return getSadComponentPlacement_3001ContainedLinks(view);
		case SadComponentInstantiationEditPart.VISUAL_ID:
			return getSadComponentInstantiation_3002ContainedLinks(view);
		case UsesPortStubEditPart.VISUAL_ID:
			return getUsesPortStub_3003ContainedLinks(view);
		case ProvidesPortStubEditPart.VISUAL_ID:
			return getProvidesPortStub_3004ContainedLinks(view);
		case ComponentSupportedInterfaceStubEditPart.VISUAL_ID:
			return getComponentSupportedInterfaceStub_3005ContainedLinks(view);
		case HostCollocationEditPart.VISUAL_ID:
			return getHostCollocation_3006ContainedLinks(view);
		case ComponentPlacement2EditPart.VISUAL_ID:
			return getSadComponentPlacement_3007ContainedLinks(view);
		case SadConnectInterfaceEditPart.VISUAL_ID:
			return getSadConnectInterface_4001ContainedLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SadLinkDescriptor> getIncomingLinks(View view) {
		switch (SadVisualIDRegistry.getVisualID(view)) {
		case PartitioningEditPart.VISUAL_ID:
			return getSadPartitioning_2001IncomingLinks(view);
		case ComponentPlacementEditPart.VISUAL_ID:
			return getSadComponentPlacement_3001IncomingLinks(view);
		case SadComponentInstantiationEditPart.VISUAL_ID:
			return getSadComponentInstantiation_3002IncomingLinks(view);
		case UsesPortStubEditPart.VISUAL_ID:
			return getUsesPortStub_3003IncomingLinks(view);
		case ProvidesPortStubEditPart.VISUAL_ID:
			return getProvidesPortStub_3004IncomingLinks(view);
		case ComponentSupportedInterfaceStubEditPart.VISUAL_ID:
			return getComponentSupportedInterfaceStub_3005IncomingLinks(view);
		case HostCollocationEditPart.VISUAL_ID:
			return getHostCollocation_3006IncomingLinks(view);
		case ComponentPlacement2EditPart.VISUAL_ID:
			return getSadComponentPlacement_3007IncomingLinks(view);
		case SadConnectInterfaceEditPart.VISUAL_ID:
			return getSadConnectInterface_4001IncomingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SadLinkDescriptor> getOutgoingLinks(View view) {
		switch (SadVisualIDRegistry.getVisualID(view)) {
		case PartitioningEditPart.VISUAL_ID:
			return getSadPartitioning_2001OutgoingLinks(view);
		case ComponentPlacementEditPart.VISUAL_ID:
			return getSadComponentPlacement_3001OutgoingLinks(view);
		case SadComponentInstantiationEditPart.VISUAL_ID:
			return getSadComponentInstantiation_3002OutgoingLinks(view);
		case UsesPortStubEditPart.VISUAL_ID:
			return getUsesPortStub_3003OutgoingLinks(view);
		case ProvidesPortStubEditPart.VISUAL_ID:
			return getProvidesPortStub_3004OutgoingLinks(view);
		case ComponentSupportedInterfaceStubEditPart.VISUAL_ID:
			return getComponentSupportedInterfaceStub_3005OutgoingLinks(view);
		case HostCollocationEditPart.VISUAL_ID:
			return getHostCollocation_3006OutgoingLinks(view);
		case ComponentPlacement2EditPart.VISUAL_ID:
			return getSadComponentPlacement_3007OutgoingLinks(view);
		case SadConnectInterfaceEditPart.VISUAL_ID:
			return getSadConnectInterface_4001OutgoingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SadLinkDescriptor> getSoftwareAssembly_1000ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SadLinkDescriptor> getSadPartitioning_2001ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SadLinkDescriptor> getSadComponentPlacement_3001ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SadLinkDescriptor> getSadComponentInstantiation_3002ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SadLinkDescriptor> getUsesPortStub_3003ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SadLinkDescriptor> getProvidesPortStub_3004ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SadLinkDescriptor> getComponentSupportedInterfaceStub_3005ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SadLinkDescriptor> getHostCollocation_3006ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SadLinkDescriptor> getSadComponentPlacement_3007ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SadLinkDescriptor> getSadConnectInterface_4001ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SadLinkDescriptor> getSadPartitioning_2001IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SadLinkDescriptor> getSadComponentPlacement_3001IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SadLinkDescriptor> getSadComponentInstantiation_3002IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SadLinkDescriptor> getUsesPortStub_3003IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SadLinkDescriptor> getProvidesPortStub_3004IncomingLinks(
			View view) {
		ProvidesPortStub modelElement = (ProvidesPortStub) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<SadLinkDescriptor> result = new LinkedList<SadLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SadConnectInterface_4001(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SadLinkDescriptor> getComponentSupportedInterfaceStub_3005IncomingLinks(
			View view) {
		ComponentSupportedInterfaceStub modelElement = (ComponentSupportedInterfaceStub) view
				.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<SadLinkDescriptor> result = new LinkedList<SadLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SadConnectInterface_4001(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SadLinkDescriptor> getHostCollocation_3006IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SadLinkDescriptor> getSadComponentPlacement_3007IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SadLinkDescriptor> getSadConnectInterface_4001IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SadLinkDescriptor> getSadPartitioning_2001OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SadLinkDescriptor> getSadComponentPlacement_3001OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SadLinkDescriptor> getSadComponentInstantiation_3002OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SadLinkDescriptor> getUsesPortStub_3003OutgoingLinks(
			View view) {
		UsesPortStub modelElement = (UsesPortStub) view.getElement();
		LinkedList<SadLinkDescriptor> result = new LinkedList<SadLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SadConnectInterface_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SadLinkDescriptor> getProvidesPortStub_3004OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SadLinkDescriptor> getComponentSupportedInterfaceStub_3005OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SadLinkDescriptor> getHostCollocation_3006OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SadLinkDescriptor> getSadComponentPlacement_3007OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SadLinkDescriptor> getSadConnectInterface_4001OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	private static Collection<SadLinkDescriptor> getIncomingTypeModelFacetLinks_SadConnectInterface_4001(
			ConnectionTarget target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<SadLinkDescriptor> result = new LinkedList<SadLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != PartitioningPackage.eINSTANCE
					.getConnectInterface_Target()
					|| false == setting.getEObject() instanceof SadConnectInterface) {
				continue;
			}
			SadConnectInterface link = (SadConnectInterface) setting
					.getEObject();
			if (SadConnectInterfaceEditPart.VISUAL_ID != SadVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			UsesPortStub src = link.getSource();
			result.add(new SadLinkDescriptor(src, target, link,
					SadElementTypes.SadConnectInterface_4001,
					SadConnectInterfaceEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<SadLinkDescriptor> getOutgoingTypeModelFacetLinks_SadConnectInterface_4001(
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
		LinkedList<SadLinkDescriptor> result = new LinkedList<SadLinkDescriptor>();
		for (Iterator<?> links = container.getConnectInterface().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof SadConnectInterface) {
				continue;
			}
			SadConnectInterface link = (SadConnectInterface) linkObject;
			if (SadConnectInterfaceEditPart.VISUAL_ID != SadVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			ConnectionTarget dst = link.getTarget();
			UsesPortStub src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new SadLinkDescriptor(src, dst, link,
					SadElementTypes.SadConnectInterface_4001,
					SadConnectInterfaceEditPart.VISUAL_ID));
		}
		return result;
	}

}
