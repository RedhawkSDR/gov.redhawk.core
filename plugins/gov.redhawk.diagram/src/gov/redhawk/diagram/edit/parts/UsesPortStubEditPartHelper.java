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
package gov.redhawk.diagram.edit.parts;

import gov.redhawk.diagram.layout.FixedConnectionAnchor;
import gov.redhawk.diagram.part.PartitioningVisualIDRegistry;
import gov.redhawk.diagram.part.PartitioningVisualIDRegistry.MAPPING_ID;
import gov.redhawk.diagram.providers.PartitioningElementTypes;
import gov.redhawk.diagram.ui.tools.DragConnectionCreationProxy;

import java.util.LinkedList;
import java.util.List;

import mil.jpeojtrs.sca.partitioning.ComponentSupportedInterfaceStub;
import mil.jpeojtrs.sca.partitioning.PartitioningPackage;
import mil.jpeojtrs.sca.partitioning.ProvidesPortStub;
import mil.jpeojtrs.sca.partitioning.UsesPortStub;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;
import org.eclipse.gmf.runtime.diagram.ui.handles.ConnectionHandle.HandleDirection;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * @since 3.0
 */
public class UsesPortStubEditPartHelper {

	private FixedConnectionAnchor fixedAnchor;
	private final IUsesPortStubEditPart editPart;
	private final PartitioningElementTypes elementTypes;
	private final PartitioningVisualIDRegistry visualIdRegistry;
	private Label label;

	private final Adapter adapter = new AdapterImpl() {
		@Override
		public void notifyChanged(final org.eclipse.emf.common.notify.Notification msg) {
			switch (msg.getFeatureID(UsesPortStub.class)) {
			case PartitioningPackage.USES_PORT_STUB__NAME:
				if (Display.getCurrent() == null) {
					PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

						public void run() {
							UsesPortStubEditPartHelper.this.label.setText(msg.getNewStringValue());
						}
					});
				} else {
					UsesPortStubEditPartHelper.this.label.setText(msg.getNewStringValue());
				}
				break;
			default:
				break;
			}
		}
	};

	public UsesPortStubEditPartHelper(final IUsesPortStubEditPart editPart, final PartitioningElementTypes elementTypes,
	        final PartitioningVisualIDRegistry visualIdRegistry) {
		this.editPart = editPart;
		this.elementTypes = elementTypes;
		this.visualIdRegistry = visualIdRegistry;
	}

	public IFigure createNodeShape() {
		final IFigure figure = this.editPart.basicCreateNodeShape();

		// Setup Anchor
		this.fixedAnchor = new FixedConnectionAnchor(figure);
		this.fixedAnchor.setOffsetH(figure.getPreferredSize().width);
		this.fixedAnchor.setOffsetV(figure.getPreferredSize().height / 2);

		// Setup Tooltip
		final EObject element = ((View) this.editPart.getModel()).getElement();
		if (element instanceof UsesPortStub) {
			final String tooltip = ((UsesPortStub) element).getName();
			this.label = new Label(tooltip);
			this.editPart.getPrimaryShape().setToolTip(this.label);
		}

		return figure;
	}

	public ConnectionAnchor getSourceConnectionAnchor(final ConnectionEditPart connEditPart) {
		return this.fixedAnchor;
	}

	public ConnectionAnchor getSourceConnectionAnchor(final Request request) {
		return this.fixedAnchor;
	}

	public void addBorderItem(final IFigure borderItemContainer, final IBorderItemEditPart borderItemEditPart) {
		final EObject semanticElement = EditPartUtil.getSemanticModelObject(borderItemEditPart);
		if (semanticElement instanceof UsesPortStub) {
			final BorderItemLocator locator = new BorderItemLocator(this.editPart.getMainFigure(), PositionConstants.WEST);
			locator.setBorderItemOffset(new Dimension(-5, 0)); // SUPPRESS CHECKSTYLE MagicNumber
			borderItemContainer.add(borderItemEditPart.getFigure(), locator);
		} else {
			this.editPart.basicAddBorderItem(borderItemContainer, borderItemEditPart);
		}
	}

	public EditPolicy getPrimaryDragEditPolicy() {
		final NonResizableEditPolicy result = new NonResizableEditPolicy();
		result.setDragAllowed(false);
		return result;
	}

	public EditPart getPrimaryChildEditPart() {
		return this.editPart.getChildBySemanticHint(this.visualIdRegistry.getType(this.visualIdRegistry.getVisualId(MAPPING_ID.UsesPortStubNameEditPart)));
	}

	public DragTracker getDragTracker(final Request request) {
		return new DragConnectionCreationProxy(this.editPart, HandleDirection.OUTGOING, "Connection Interface");
	}

	public List<IElementType> getMARelTypesOnSourceAndTarget(final IGraphicalEditPart targetEditPart) {
		final LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (targetEditPart instanceof FindByStubEditPart) {
			types.add(this.elementTypes.getConnectInterfaceElementType());
		}
		final EObject semanticElement = EditPartUtil.getSemanticModelObject(targetEditPart);
		if (semanticElement instanceof ProvidesPortStub) {
			types.add(this.elementTypes.getConnectInterfaceElementType());
		}
		if (semanticElement instanceof ComponentSupportedInterfaceStub) {
			types.add(this.elementTypes.getConnectInterfaceElementType());
		}
		return types;
	}

	public List<IElementType> getMATypesForTarget(final IElementType relationshipType) {
		final LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (relationshipType == this.elementTypes.getConnectInterfaceElementType()) {
			types.add(PartitioningElementTypes.FindByStub);
			types.add(this.elementTypes.getProvidesPortStubElementType());
			types.add(this.elementTypes.getComponentSupportedInterfaceStubElementType());
		}
		return types;
	}

	public void addSemanticListeners() {
		final UsesPortStub stub = (UsesPortStub) ((View) this.editPart.getModel()).getElement();
		stub.eAdapters().add(this.adapter);
		this.editPart.basicAddSemanticListeners();
	}

	public void removeSemanticListeners() {
		final UsesPortStub stub = (UsesPortStub) ((View) this.editPart.getModel()).getElement();

		if (stub != null) {
			stub.eAdapters().remove(this.adapter);
			this.editPart.basicRemoveSemanticListeners();
		}
	}

}
