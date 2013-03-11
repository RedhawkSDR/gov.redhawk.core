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
package gov.redhawk.sca.dcd.diagram.edit.policies;

import gov.redhawk.diagram.edit.parts.FindByStubEditPart;
import gov.redhawk.sca.dcd.diagram.edit.parts.DeviceConfigurationEditPart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mil.jpeojtrs.sca.dcd.DcdComponentInstantiation;
import mil.jpeojtrs.sca.dcd.DcdConnectInterface;
import mil.jpeojtrs.sca.dcd.DcdPackage;
import mil.jpeojtrs.sca.dcd.DcdPartitioning;
import mil.jpeojtrs.sca.dcd.DcdProvidesPort;
import mil.jpeojtrs.sca.dcd.DcdUsesPort;
import mil.jpeojtrs.sca.dcd.DeviceConfiguration;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdComponentPlacementEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdConnectInterfaceEditPart;
import mil.jpeojtrs.sca.dcd.diagram.part.DcdVisualIDRegistry;
import mil.jpeojtrs.sca.diagram.EObjectContainerStyle;
import mil.jpeojtrs.sca.partitioning.ComponentPlacement;
import mil.jpeojtrs.sca.partitioning.ConnectInterface;
import mil.jpeojtrs.sca.partitioning.FindByStub;
import mil.jpeojtrs.sca.partitioning.FindByStubContainer;
import mil.jpeojtrs.sca.partitioning.PartitioningFactory;
import mil.jpeojtrs.sca.partitioning.PartitioningPackage;
import mil.jpeojtrs.sca.partitioning.ProvidesPortStub;
import mil.jpeojtrs.sca.partitioning.UsesPortStub;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.INodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalConnectionEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.requests.DropObjectsRequest;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.progress.WorkbenchJob;

/**
 * 
 */
public class DeviceConfigurationEditPartCanonicalEditPolicy extends CanonicalConnectionEditPolicy {

	private Set<EStructuralFeature> myFeaturesToSynchronize;

	private final Adapter propertyListener = new AdapterImpl() {
		@Override
		public void notifyChanged(final Notification msg) {
			super.notifyChanged(msg);
			switch (msg.getFeatureID(DcdPartitioning.class)) {
			case DcdPackage.DCD_PARTITIONING__COMPONENT_PLACEMENT:

				final DeviceConfigurationEditPart devPart = (DeviceConfigurationEditPart) getHost();
				final EditPolicy editPolicy = devPart.getEditPolicy(EditPolicyRoles.CANONICAL_ROLE);

				if (editPolicy instanceof DeviceConfigurationEditPartCanonicalEditPolicy) {
					final DeviceConfigurationEditPartCanonicalEditPolicy canPolicy = (DeviceConfigurationEditPartCanonicalEditPolicy) editPolicy;
					final WorkbenchJob job = new WorkbenchJob("Refreshing Device Configuration") {

						@Override
						public IStatus runInUIThread(final IProgressMonitor monitor) {
							canPolicy.refresh();
							return Status.OK_STATUS;
						}
					};

					job.setSystem(true);
					job.schedule(1000); // SUPPRESS CHECKSTYLE MagicNumber
				}

				break;
			default:
				break;
			}

		}
	};

	@Override
	protected List<EObject> getSemanticChildrenList() {
		final List<EObject> retVal = new ArrayList<EObject>();
		if (getSemanticHost() == null) {
			return Collections.emptyList();
		}
		if (getSemanticHost().getPartitioning() != null) {
			retVal.addAll(getSemanticHost().getPartitioning().getComponentPlacement());
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
	public DeviceConfiguration getSemanticHost() {
		return (DeviceConfiguration) super.getSemanticHost();
	}

	@Override
	protected Set<EStructuralFeature> getFeaturesToSynchronize() {
		if (this.myFeaturesToSynchronize == null) {
			this.myFeaturesToSynchronize = new HashSet<EStructuralFeature>();
			this.myFeaturesToSynchronize.add(PartitioningPackage.eINSTANCE.getPartitioning_ComponentPlacement());
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
		final DcdConnectInterface connection = (DcdConnectInterface) relationship;
		return connection.getSource();
	}

	@Override
	protected EObject getTargetElement(final EObject relationship) {
		final DcdConnectInterface connection = (DcdConnectInterface) relationship;
		return connection.getTarget();
	}

	@Override
	protected String getFactoryHint(final IAdaptable elementAdapter) {
		final Object adapter = elementAdapter.getAdapter(EObject.class);
		if (adapter instanceof ComponentPlacement) {
			return DcdVisualIDRegistry.getType(DcdComponentPlacementEditPart.VISUAL_ID);
		} else if (adapter instanceof FindByStub) {
			return DcdVisualIDRegistry.getType(FindByStubEditPart.VISUAL_ID);
		} else if (adapter instanceof ConnectInterface) {
			return DcdVisualIDRegistry.getType(DcdConnectInterfaceEditPart.VISUAL_ID);
		}
		return super.getFactoryHint(elementAdapter);
	}

	@Override
	protected boolean shouldCheckForConnections(final View view, final Collection<View> viewChildren) {
		return false;
	}

	@Override
	public void activate() {
		super.activate();
		final View view = (View) getHost().getModel();
		final DeviceConfiguration dcd = (DeviceConfiguration) view.getElement();
		dcd.getPartitioning().eAdapters().add(this.propertyListener);
	}

	@Override
	public void deactivate() {
		final View view = (View) getHost().getModel();
		final DeviceConfiguration dcd = (DeviceConfiguration) view.getElement();
		dcd.getPartitioning().eAdapters().remove(this.propertyListener);
		super.deactivate();
	}

	@Override
	protected boolean shouldDeleteView(final View view) {
		return !(view.getElement() instanceof FindByStub);
	};

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
			final DcdConnectInterface connectInterface = (DcdConnectInterface) connection;
			final DcdProvidesPort providesPort = connectInterface.getProvidesPort();
			if (providesPort == null) {
				return super.getTargetEditPartFor(connection);
			}

			tel = PartitioningFactory.eINSTANCE.createProvidesPortStub();

			if (providesPort.getComponentInstantiationRef() != null) {
				final DcdComponentInstantiation compInst = providesPort.getComponentInstantiationRef().getInstantiation();

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
			final DcdConnectInterface connectInterface = (DcdConnectInterface) connection;
			final DcdUsesPort usesPort = connectInterface.getUsesPort();
			if (usesPort == null) {
				return super.getSourceEditPartFor(connection);
			}

			sel = PartitioningFactory.eINSTANCE.createUsesPortStub();

			if (usesPort.getComponentInstantiationRef() != null) {
				final DcdComponentInstantiation compInst = usesPort.getComponentInstantiationRef().getInstantiation();

				final DiagramEditPart diagramEditPart = (DiagramEditPart) getHost();

				((UsesPortStub) sel).setName(usesPort.getUsesIndentifier());

				final AddCommand addCommand = new AddCommand(diagramEditPart.getEditingDomain(), compInst.getUses(), sel);
				diagramEditPart.getEditingDomain().getCommandStack().execute(addCommand);
			}
		}

		sep = getEditPartFor(sel, connection);
		return sep;
	}

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
