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
package gov.redhawk.sca.sad.diagram.edit.policies;

import gov.redhawk.diagram.edit.parts.FindByStubEditPart;
import gov.redhawk.sca.sad.diagram.RedhawkSadDiagramPlugin;
import gov.redhawk.sca.util.PluginUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mil.jpeojtrs.sca.diagram.EObjectContainerStyle;
import mil.jpeojtrs.sca.partitioning.ComponentPlacement;
import mil.jpeojtrs.sca.partitioning.ConnectInterface;
import mil.jpeojtrs.sca.partitioning.FindByStub;
import mil.jpeojtrs.sca.partitioning.FindByStubContainer;
import mil.jpeojtrs.sca.partitioning.PartitioningFactory;
import mil.jpeojtrs.sca.partitioning.PartitioningPackage;
import mil.jpeojtrs.sca.partitioning.ProvidesPortStub;
import mil.jpeojtrs.sca.partitioning.UsesPortStub;
import mil.jpeojtrs.sca.sad.HostCollocation;
import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.sad.SadConnectInterface;
import mil.jpeojtrs.sca.sad.SadPackage;
import mil.jpeojtrs.sca.sad.SadProvidesPort;
import mil.jpeojtrs.sca.sad.SadUsesPort;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentPlacementEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.HostCollocationEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.SadConnectInterfaceEditPart;
import mil.jpeojtrs.sca.sad.diagram.part.SadVisualIDRegistry;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.INodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalConnectionEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.requests.DropObjectsRequest;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;

/**
 * 
 */
public class SoftwareAssemblyCanonicalEditPolicy extends CanonicalConnectionEditPolicy {

	private Set<EStructuralFeature> myFeaturesToSynchronize;

	@Override
	protected List<EObject> getSemanticChildrenList() {
		final List<EObject> retVal = new ArrayList<EObject>();
		if (getSemanticHost() == null) {
			return Collections.emptyList();
		}
		if (getSemanticHost().getPartitioning() != null) {
			retVal.addAll(getSemanticHost().getPartitioning().getComponentPlacement());
			retVal.addAll(getSemanticHost().getPartitioning().getHostCollocation());
		}
		final View view = (View) this.host().getModel();
		final EObjectContainerStyle style = (EObjectContainerStyle) view.getStyle(NotationPackage.eINSTANCE.getEObjectValueStyle());
		if (style != null) {
			final FindByStubContainer container = (FindByStubContainer) style.getValue();
			if (container != null) {
				retVal.addAll(container.getStubs());
			}
		}
		return retVal;
	}

	@Override
	public SoftwareAssembly getSemanticHost() {
		return (SoftwareAssembly) super.getSemanticHost();
	}

	@Override
	protected Set<EStructuralFeature> getFeaturesToSynchronize() {
		if (this.myFeaturesToSynchronize == null) {
			this.myFeaturesToSynchronize = new HashSet<EStructuralFeature>();
			this.myFeaturesToSynchronize.add(PartitioningPackage.eINSTANCE.getPartitioning_ComponentPlacement());
			this.myFeaturesToSynchronize.add(SadPackage.eINSTANCE.getSadPartitioning_HostCollocation());
			this.myFeaturesToSynchronize.add(PartitioningPackage.eINSTANCE.getConnections_ConnectInterface());
		}
		return this.myFeaturesToSynchronize;
	}

	@Override
	protected List<EObject> getSemanticConnectionsList() {
		if (getSemanticHost().getConnections() == null) {
			return Collections.emptyList();
		}
		final List<EObject> retVal = new ArrayList<EObject>();
		retVal.addAll(getSemanticHost().getConnections().getConnectInterface());
		return retVal;
	}

	@Override
	protected EObject getSourceElement(final EObject relationship) {
		final SadConnectInterface connection = (SadConnectInterface) relationship;
		return connection.getSource();
	}

	@Override
	protected EObject getTargetElement(final EObject relationship) {
		final SadConnectInterface connection = (SadConnectInterface) relationship;
		return connection.getTarget();
	}

	@Override
	protected String getFactoryHint(final IAdaptable elementAdapter) {
		final Object adapter = elementAdapter.getAdapter(EObject.class);
		if (adapter instanceof HostCollocation) {
			return SadVisualIDRegistry.getType(HostCollocationEditPart.VISUAL_ID);
		} else if (adapter instanceof ComponentPlacement) {
			return SadVisualIDRegistry.getType(ComponentPlacementEditPart.VISUAL_ID);
		} else if (adapter instanceof FindByStub) {
			return SadVisualIDRegistry.getType(FindByStubEditPart.VISUAL_ID);
		} else if (adapter instanceof ConnectInterface) {
			return SadVisualIDRegistry.getType(SadConnectInterfaceEditPart.VISUAL_ID);
		}
		return super.getFactoryHint(elementAdapter);
	}

	@Override
	protected boolean shouldCheckForConnections(final View view, final Collection<View> viewChildren) {
		return true;
	}

	@Override
	public Command getCommand(final Request request) {
		if (understandsRequest(request)) {
			if (isEnabled() && request instanceof DropObjectsRequest) {
				return null;
			}
		}
		return super.getCommand(request);
	}

	@Override
	protected EditPart getTargetEditPartFor(final EObject connection) {
		EObject tel;
		EditPart tep;
		tel = getTargetElement(connection);

		/**
		 * If the target element comes back null, derive it from the connection and add it to the appropriate edit part.
		 */
		if (tel == null) {
			final SadConnectInterface connectInterface = (SadConnectInterface) connection;
			final SadProvidesPort providesPort = connectInterface.getProvidesPort();
			if (providesPort == null) {
				return super.getTargetEditPartFor(connection);
			}

			tel = PartitioningFactory.eINSTANCE.createProvidesPortStub();

			if (providesPort.getComponentInstantiationRef() != null) {
				final SadComponentInstantiation compInst = providesPort.getComponentInstantiationRef().getInstantiation();
				if (compInst == null) {
					PluginUtil.logWarning(RedhawkSadDiagramPlugin.getDefault(), "Unable to find target edit part: " + providesPort, null);
					return null;
				}

				final DiagramEditPart diagramEditPart = (DiagramEditPart) getHost();

				((ProvidesPortStub) tel).setName(providesPort.getProvidesIdentifier());

				final AddCommand addCommand = new AddCommand(diagramEditPart.getEditingDomain(), compInst.getProvides(), tel);
				diagramEditPart.getEditingDomain().getCommandStack().execute(addCommand);
			}
		}

		tep = getEditPartFor(tel, connection);
		return tep;
	}

	@Override
	protected EditPart getSourceEditPartFor(final EObject connection) {
		EObject sel;
		EditPart sep;
		sel = getSourceElement(connection);

		/**
		 * If the source element comes back null, derive it from the connection and add it to the appropriate edit part
		 */
		if (sel == null) {
			final SadConnectInterface connectInterface = (SadConnectInterface) connection;
			final SadUsesPort usesPort = connectInterface.getUsesPort();
			if (usesPort == null) {
				return super.getSourceEditPartFor(connection);
			}

			sel = PartitioningFactory.eINSTANCE.createUsesPortStub();

			if (usesPort.getComponentInstantiationRef() != null) {
				final SadComponentInstantiation compInst = usesPort.getComponentInstantiationRef().getInstantiation();
				if (compInst == null) {
					PluginUtil.logWarning(RedhawkSadDiagramPlugin.getDefault(), "Unable to find source edit part: " + usesPort, null);
					return null;
				}

				final DiagramEditPart diagramEditPart = (DiagramEditPart) getHost();

				((UsesPortStub) sel).setName(usesPort.getUsesIdentifier());

				final AddCommand addCommand = new AddCommand(diagramEditPart.getEditingDomain(), compInst.getUses(), sel);
				diagramEditPart.getEditingDomain().getCommandStack().execute(addCommand);
			}
		}

		sep = getEditPartFor(sel, connection);
		return sep;
	}

	@SuppressWarnings("unchecked")
	private EditPart getEditPartFor(final EObject element, final EObject context) {
		if (element != null && !(element instanceof View)) {
			final EditPartViewer viewer = getHost().getViewer();
			if (viewer instanceof IDiagramGraphicalViewer) {
				List<EditPart> parts = ((IDiagramGraphicalViewer) viewer).findEditPartsForElement(EMFCoreUtil.getProxyID(element), INodeEditPart.class);

				if (parts.isEmpty()) {
					// reach for the container's editpart instead and force it
					// to refresh
					final EObject container = element.eContainer();
					final EditPart containerEP = getEditPartFor(container, null);
					if (containerEP != null) {
						containerEP.refresh();
						parts = ((IDiagramGraphicalViewer) viewer).findEditPartsForElement(EMFCoreUtil.getProxyID(element), INodeEditPart.class);
					}
				}

				// Check if the part is contained with-in the host EditPart
				// since we are canonically updated the host.
				return findEditPartForElement(element, context, parts);
			}
		}
		return (EditPart) host().getViewer().getEditPartRegistry().get(element);
	}
}
