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
package gov.redhawk.sca.sad.diagram.edit.parts;

import mil.jpeojtrs.sca.sad.HostCollocation;
import mil.jpeojtrs.sca.sad.SadComponentPlacement;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentPlacementEditPart;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.diagram.core.commands.AddCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.ArrangeRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateUnspecifiedTypeConnectionRequest;
import org.eclipse.gmf.runtime.emf.type.core.commands.MoveElementsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.MoveRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.progress.WorkbenchJob;

public class SoftwareAssemblyEditPart extends mil.jpeojtrs.sca.sad.diagram.edit.parts.SoftwareAssemblyEditPart {

	public SoftwareAssemblyEditPart(final View view) {
		super(view);
	}

	@Override
	public Command getCommand(final Request request) {

		// Case when Host Collocation is created on top of Component Placements
		if (request instanceof ArrangeRequest) {
			final ArrangeRequest arrange = (ArrangeRequest) request;
			final CompositeCommand command = new CompositeCommand("");
			if (arrange.getPartsToArrange().get(0) instanceof HostCollocationEditPart) {

				final HostCollocationEditPart hostPart = (HostCollocationEditPart) arrange.getPartsToArrange().get(0);
				final Rectangle bounds = hostPart.getFigure().getBounds();

				for (final Object obj : this.children) {
					if (obj == hostPart) {
						continue;
					} else if (hostPart instanceof HostCollocationEditPart && obj instanceof ComponentPlacementEditPart
					        && bounds.contains(((IGraphicalEditPart) obj).getFigure().getBounds())) {

						final ComponentPlacementEditPart componentPart = (ComponentPlacementEditPart) obj;

						final HostCollocation hostCollocation = (HostCollocation) ((View) hostPart.getModel()).getElement();
						final SadComponentPlacement componentPlacement = (SadComponentPlacement) ((View) componentPart.getModel()).getElement();

						final MoveRequest moveRequest = new MoveRequest(getEditingDomain(), hostCollocation, componentPlacement);
						final MoveElementsCommand moveCommand = new MoveElementsCommand(moveRequest);
						command.compose(moveCommand);

						final AddCommand addCommand = new AddCommand(getEditingDomain(), hostPart.getChildBySemanticHint(String
						        .valueOf(mil.jpeojtrs.sca.sad.diagram.edit.parts.HostCollocationCompartmentEditPart.VISUAL_ID)), componentPart);
						command.compose(addCommand);

						final Point p = new Point();
						p.x = Math.abs(hostPart.getFigure().getBounds().x - componentPart.getFigure().getBounds().x);
						p.y = Math.abs(hostPart.getFigure().getBounds().y - componentPart.getFigure().getBounds().y);
						final SetBoundsCommand setBoundsCommand = new SetBoundsCommand(getEditingDomain(), "Move Element", componentPart, p);
						command.compose(setBoundsCommand);

					} else if (obj instanceof IGraphicalEditPart && ((IGraphicalEditPart) obj).getFigure().getBounds().intersects(bounds)) {
						return UnexecutableCommand.INSTANCE;
					}
				}

				final WorkbenchJob job = new WorkbenchJob("Arrange Elements") {

					@Override
					public IStatus runInUIThread(final IProgressMonitor monitor) {
						getEditDomain().getCommandStack().execute(new ICommandProxy(command.reduce()));
						return Status.OK_STATUS;
					}
				};
				
				job.schedule(100); // SUPPRESS CHECKSTYLE MagicNumber
			}
		} else if (request instanceof ChangeBoundsRequest) {
			// Case when already existing Host Collocation is placed on top of Component Placements

			final ChangeBoundsRequest changeBoundsRequest = (ChangeBoundsRequest) request;
			CompositeCommand command = null;
			for (final Object part : changeBoundsRequest.getEditParts()) {
				if (part instanceof IGraphicalEditPart) {
					final Rectangle bounds = changeBoundsRequest.getTransformedRectangle(((IGraphicalEditPart) part).getFigure().getBounds());
					for (final Object obj : this.children) {
						if (part == obj) {
							continue;
						} else if (part instanceof HostCollocationEditPart && obj instanceof ComponentPlacementEditPart
						        && bounds.contains(((IGraphicalEditPart) obj).getFigure().getBounds())) {
							if (command == null) {
								command = new CompositeCommand("Move Elements");
							}
							final HostCollocationEditPart hostPart = (HostCollocationEditPart) part;
							final ComponentPlacementEditPart componentPart = (ComponentPlacementEditPart) obj;

							final HostCollocation hostCollocation = (HostCollocation) ((View) hostPart.getModel()).getElement();
							final SadComponentPlacement componentPlacement = (SadComponentPlacement) ((View) componentPart.getModel()).getElement();

							final MoveRequest moveRequest = new MoveRequest(getEditingDomain(), hostCollocation, componentPlacement);
							final MoveElementsCommand moveCommand = new MoveElementsCommand(moveRequest);
							command.compose(moveCommand);

							final AddCommand addCommand = new AddCommand(getEditingDomain(), hostPart.getChildBySemanticHint(String
							        .valueOf(mil.jpeojtrs.sca.sad.diagram.edit.parts.HostCollocationCompartmentEditPart.VISUAL_ID)), componentPart);
							command.compose(addCommand);

							final Point p = new Point();
							p.x = Math.abs(hostPart.getFigure().getBounds().x - componentPart.getFigure().getBounds().x);
							p.y = Math.abs(hostPart.getFigure().getBounds().y - componentPart.getFigure().getBounds().y);
							final SetBoundsCommand setBoundsCommand = new SetBoundsCommand(getEditingDomain(), "Move Element", componentPart, p);
							command.compose(setBoundsCommand);

						} else if (obj instanceof IGraphicalEditPart && ((IGraphicalEditPart) obj).getFigure().getBounds().intersects(bounds)) {
							return UnexecutableCommand.INSTANCE;
						}
					}
				}
			}
			if (command != null) {
				final Command retVal = super.getCommand(request);
				if (retVal != null) {
					return retVal.chain(new ICommandProxy(command));
				} else {
					return new ICommandProxy(command);
				}
			}

		}

		if (request instanceof CreateUnspecifiedTypeConnectionRequest) {
			// Do not allow creating connections to nowhere
			// By default this would allow creating an item.  However, we don't have any items types that could be created, therefore we disable this feature
			return UnexecutableCommand.INSTANCE;
		}
		return super.getCommand(request);
	}
}
