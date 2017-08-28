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
package gov.redhawk.sca.sad.diagram.providers;

import org.eclipse.draw2d.Label;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.AbstractDecorator;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.CreateDecoratorsOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorProvider;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Image;

import gov.redhawk.sca.sad.diagram.RedhawkSadDiagramPlugin;
import mil.jpeojtrs.sca.sad.HostCollocation;
import mil.jpeojtrs.sca.sad.SadComponentPlacement;
import mil.jpeojtrs.sca.sad.SadPackage;
import mil.jpeojtrs.sca.sad.SadPartitioning;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import mil.jpeojtrs.sca.sad.diagram.part.SadVisualIDRegistry;

/**
 * @since 2.0
 */
public class HostCollocationDecoratorProvider extends AbstractProvider implements IDecoratorProvider {

	private static class HostCollocationProvider extends AbstractDecorator {

		private final Label toolTip = new Label("Host Collocation", //$NON-NLS-1$
		        getImage());

		private final Adapter listener = new EContentAdapter() {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public void notifyChanged(final Notification msg) {
				super.notifyChanged(msg);
				if (msg.getEventType() == Notification.SET || msg.getEventType() == Notification.REMOVE) {
					if (msg.getNotifier() instanceof SadPartitioning) {
						switch (msg.getFeatureID(SadPartitioning.class)) {
						case SadPackage.SAD_PARTITIONING__COMPONENT_PLACEMENT:
							refresh();
							break;
						default:
							break;
						}
					} else if (msg.getNotifier() instanceof HostCollocation) {
						switch (msg.getFeatureID(HostCollocation.class)) {
						case SadPackage.SAD_PARTITIONING__HOST_COLLOCATION:
							refresh();
							break;
						case SadPackage.HOST_COLLOCATION__COMPONENT_PLACEMENT:
							refresh();
							break;
						default:
							break;
						}
					} else if (msg.getNotifier() instanceof SadComponentPlacement) {
						switch (msg.getFeatureID(SadComponentPlacement.class)) {
						case SadPackage.HOST_COLLOCATION__COMPONENT_PLACEMENT:
							refresh();
							break;
						default:
							break;
						}
					}
				}
			}
		};

		public HostCollocationProvider(final IDecoratorTarget decoratorTarget) {
			super(decoratorTarget);
		}

		@Override
		public void activate() {

		}

		private Image getImage() {
			return RedhawkSadDiagramPlugin.getDefault().getBundledImage("icons/obj16/error_x12.png");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void refresh() {
			removeDecoration();
			final View view = (View) getDecoratorTarget().getAdapter(View.class);
			if (view == null || view.eResource() == null) {
				return;
			}
			final EditPart editPart = (EditPart) getDecoratorTarget().getAdapter(EditPart.class);
			if (editPart == null || editPart.getViewer() == null) {
				return;
			}

			final HostCollocation hostCo = (HostCollocation) view.getElement();

			if (hostCo.getComponentPlacement().isEmpty() && editPart instanceof org.eclipse.gef.GraphicalEditPart) {
				int margin = -12; // SUPPRESS CHECKSTYLE MagicNumber
				if (editPart instanceof org.eclipse.gef.GraphicalEditPart) {
					margin = MapModeUtil.getMapMode(((org.eclipse.gef.GraphicalEditPart) editPart).getFigure()).DPtoLP(margin);
				}

				setDecoration(getDecoratorTarget().addShapeDecoration(getImage(), IDecoratorTarget.Direction.NORTH_WEST, margin + 6, false)); // SUPPRESS CHECKSTYLE MagicNumber
				getDecoration().setToolTip(this.toolTip);
			}

			final SoftwareAssembly sad = SoftwareAssembly.Util.getSoftwareAssembly(hostCo.eResource());
			if (!sad.eAdapters().contains(this.listener)) {
				sad.eAdapters().add(this.listener);
			}
		}

	}

	private static final String KEY = "hostCollocation"; //$NON-NLS-1$

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createDecorators(final IDecoratorTarget decoratorTarget) {
		return;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean provides(final IOperation operation) {
		if (!(operation instanceof CreateDecoratorsOperation)) {
			return false;
		}
		final IDecoratorTarget decoratorTarget = ((CreateDecoratorsOperation) operation).getDecoratorTarget();
		final View view = (View) decoratorTarget.getAdapter(View.class);
		return view != null && mil.jpeojtrs.sca.sad.diagram.edit.parts.SoftwareAssemblyEditPart.MODEL_ID.equals(SadVisualIDRegistry.getModelID(view));
	}

}
