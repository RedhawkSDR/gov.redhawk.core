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
package gov.redhawk.sca.sad.diagram.edit.parts;

import java.util.Arrays;

import mil.jpeojtrs.sca.sad.HostCollocation;
import mil.jpeojtrs.sca.sad.SadComponentPlacement;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentPlacementEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.HostCollocationCompartmentEditPart;
import mil.jpeojtrs.sca.sad.diagram.part.SadVisualIDRegistry;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.requests.ArrangeRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.progress.WorkbenchJob;

/**
 * @since 1.1
 */
public class HostCollocationEditPart extends mil.jpeojtrs.sca.sad.diagram.edit.parts.HostCollocationEditPart {

	public HostCollocationEditPart(final View view) {
		super(view);
	}
	
	private Command arrangeCommand;

	private final Adapter propertyListener = new EContentAdapter() {
		@Override
		public void notifyChanged(final org.eclipse.emf.common.notify.Notification msg) {
			super.notifyChanged(msg);
			if (!isActive()) {
				return;
			}

			if (msg.getEventType() == Notification.ADD && msg.getNewValue() instanceof SadComponentPlacement) {
				final HostCollocationCompartmentEditPart hostPart = (HostCollocationCompartmentEditPart) getChildBySemanticHint(SadVisualIDRegistry
				        .getType(HostCollocationCompartmentEditPart.VISUAL_ID));

				if (hostPart != null) {
					if (hostPart.getChildren() != null && hostPart.getChildren().size() > 0) {
						final ArrangeRequest arrangeRequest = new ArrangeRequest(ActionIds.ACTION_ARRANGE_ALL);
						arrangeRequest.setPartsToArrange(hostPart.getChildren());
						HostCollocationEditPart.this.arrangeCommand = hostPart.getParent().getCommand(arrangeRequest);
					} else {
						for (final Object obj : hostPart.getParent().getParent().getChildren()) {
							if (obj instanceof ComponentPlacementEditPart) {
								final ComponentPlacementEditPart part = (ComponentPlacementEditPart) obj;
								final View view = part.getNotationView();
								final SadComponentPlacement sadComp = (SadComponentPlacement) view.getElement();
								if (sadComp.getComponentInstantiation().size() == 0) break;
								if (((SadComponentPlacement) msg.getNewValue()).getComponentInstantiation().size() == 0) break;
								
								if (sadComp.getComponentInstantiation().get(0).getId()
								        .equals(((SadComponentPlacement) msg.getNewValue()).getComponentInstantiation().get(0).getId())) {
									final ArrangeRequest arrangeRequest = new ArrangeRequest(ActionIds.ACTION_ARRANGE_ALL);
									arrangeRequest.setPartsToArrange(Arrays.asList(new ComponentPlacementEditPart[] { part }));
									HostCollocationEditPart.this.arrangeCommand = hostPart.getParent().getCommand(arrangeRequest);
									break;
								}
							}
						}
					}

					final WorkbenchJob job = new WorkbenchJob("Arranging Components Inside of Host Collocation") {

						@Override
						public IStatus runInUIThread(final IProgressMonitor monitor) {
							if (HostCollocationEditPart.this.arrangeCommand != null) {
								HostCollocationEditPart.this.arrangeCommand.execute();
							}
							return Status.OK_STATUS;
						}
					};

					job.setUser(true);
					job.schedule();
				}
			}
		};
	};

	@Override
	public void activate() {
		super.activate();
		final View view = getPrimaryView();
		final HostCollocation host = (HostCollocation) view.getElement();
		
		if (!host.eAdapters().contains(this.propertyListener)) {
			host.eAdapters().add(this.propertyListener);	
		}
	}

}
