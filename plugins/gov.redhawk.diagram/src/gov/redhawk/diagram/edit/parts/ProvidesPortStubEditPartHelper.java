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
import gov.redhawk.diagram.ui.tools.DragConnectionCreationProxy;
import mil.jpeojtrs.sca.partitioning.PartitioningPackage;
import mil.jpeojtrs.sca.partitioning.ProvidesPortStub;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;
import org.eclipse.gmf.runtime.diagram.ui.handles.ConnectionHandle.HandleDirection;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * @since 3.0
 */
public class ProvidesPortStubEditPartHelper {

	private FixedConnectionAnchor fixedAnchor;

	private final IProvidesPortStubEditPart editPart;

	private final Adapter adapter = new AdapterImpl() {
		@Override
		public void notifyChanged(final org.eclipse.emf.common.notify.Notification msg) {
			switch (msg.getFeatureID(ProvidesPortStub.class)) {
			case PartitioningPackage.PROVIDES_PORT_STUB__NAME:
				if (Display.getCurrent() == null) {
					PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

						public void run() {
							ProvidesPortStubEditPartHelper.this.label.setText(msg.getNewStringValue());
						}
					});
				} else {
					ProvidesPortStubEditPartHelper.this.label.setText(msg.getNewStringValue());
				}
				break;
			default:
				break;
			}
		}
	};

	private Label label;

	public ProvidesPortStubEditPartHelper(final IProvidesPortStubEditPart editPart) {
		this.editPart = editPart;
	}

	public EditPolicy getPrimaryDragEditPolicy() {
		final NonResizableEditPolicy result = new NonResizableEditPolicy();
		result.setDragAllowed(false);
		return result;
	}

	public IFigure createNodeShape() {
		final IFigure figure = this.editPart.basicCreateNodeShape();

		// Setup Anchor
		this.fixedAnchor = new FixedConnectionAnchor(figure);
		this.fixedAnchor.setOffsetH(0); // SUPPRESS CHECKSTYLE MagicNumber
		this.fixedAnchor.setOffsetV(figure.getPreferredSize().height / 2);

		// Setup Tooltip
		if (editPart.getModel() instanceof View && ((View) editPart.getModel()).getElement() instanceof ProvidesPortStub) {
			final String tooltip = ((ProvidesPortStub) ((View) this.editPart.getModel()).getElement()).getName();
			this.label = new Label(tooltip);
			this.editPart.getPrimaryShape().setToolTip(this.label);
		}

		return figure;

	}

	public ConnectionAnchor getTargetConnectionAnchor(final ConnectionEditPart connEditPart) {
		return this.fixedAnchor;
	}

	public ConnectionAnchor getTargetConnectionAnchor(final Request request) {
		return this.fixedAnchor;
	}

	public void addBorderItem(final IFigure borderItemContainer, final IBorderItemEditPart borderItemEditPart) {
		if (this.editPart.isInstanceofProvidesPortStubNameEditPart(borderItemEditPart)) {
			final BorderItemLocator locator = new BorderItemLocator(this.editPart.getMainFigure(), PositionConstants.EAST);
			locator.setBorderItemOffset(new Dimension(-5, 0)); // SUPPRESS CHECKSTYLE MagicNumber
			borderItemContainer.add(borderItemEditPart.getFigure(), locator);
		} else {
			this.editPart.basicAddBorderItem(borderItemContainer, borderItemEditPart);
		}
	}

	public DragTracker getDragTracker(final Request request) {
		return new DragConnectionCreationProxy(this.editPart, HandleDirection.INCOMING, "Connection Interface");
	}

	public void addSemanticListeners() {
		final ProvidesPortStub stub = (ProvidesPortStub) ((View) this.editPart.getModel()).getElement();
		stub.eAdapters().add(this.adapter);
		this.editPart.basicAddSemanticListeners();
	}

	public void removeSemanticListeners() {
		final ProvidesPortStub stub = (ProvidesPortStub) ((View) this.editPart.getModel()).getElement();

		if (stub != null) {
			stub.eAdapters().remove(this.adapter);
			this.editPart.basicRemoveSemanticListeners();
		}
	}

}
