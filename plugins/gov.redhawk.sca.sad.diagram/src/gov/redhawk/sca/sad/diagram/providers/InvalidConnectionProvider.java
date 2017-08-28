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
import org.eclipse.emf.common.notify.impl.AdapterImpl;
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

import gov.redhawk.diagram.util.InterfacesUtil;
import gov.redhawk.sca.sad.diagram.RedhawkSadDiagramPlugin;
import gov.redhawk.sca.sad.diagram.edit.parts.SadConnectInterfaceEditPart;
import mil.jpeojtrs.sca.sad.SadConnectInterface;
import mil.jpeojtrs.sca.sad.SadPackage;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import mil.jpeojtrs.sca.sad.diagram.part.SadVisualIDRegistry;

/**
 * @since 2.0
 */
public class InvalidConnectionProvider extends AbstractProvider implements IDecoratorProvider {

	private static final String KEY = "invalidConnection"; //$NON-NLS-1$

	private static class InvalidConnectProvider extends AbstractDecorator {

		private final Label toolTip = new Label("Incompatible connection detected", getImage());

		public InvalidConnectProvider(final IDecoratorTarget decoratorTarget) {
			super(decoratorTarget);
		}

		private final Adapter listener = new AdapterImpl() {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public void notifyChanged(final Notification msg) {
				super.notifyChanged(msg);
				if (msg.getEventType() == Notification.SET || msg.getEventType() == Notification.REMOVE) {
					if (msg.getNotifier() instanceof SadConnectInterface) {
						switch (msg.getFeatureID(SadConnectInterface.class)) {
						case SadPackage.SAD_CONNECTIONS__CONNECT_INTERFACE:
							refresh();
							break;
						default:
							break;
						}
					}
				}
			}
		};

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void activate() {
			refresh();
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
			if (editPart instanceof SadConnectInterfaceEditPart) {
				final SadConnectInterfaceEditPart connPart = (SadConnectInterfaceEditPart) editPart;

				if (connPart.getRoot() == null) {
					return;
				}

				if (view.getElement() instanceof SadConnectInterface) {
					final SadConnectInterface conn = (SadConnectInterface) view.getElement();

					if (conn.getSource() != null && conn.getTarget() != null) {
						if (!InterfacesUtil.areCompatible(conn.getSource(), conn.getTarget())) {
							int margin = 0;
							if (connPart instanceof org.eclipse.gef.GraphicalEditPart) {
								margin = MapModeUtil.getMapMode(((org.eclipse.gef.GraphicalEditPart) connPart).getFigure()).DPtoLP(margin);
							}

							setDecoration(getDecoratorTarget().addShapeDecoration(getImage(), IDecoratorTarget.Direction.CENTER, margin, false)); // SUPPRESS CHECKSTYLE MagicNumber
							getDecoration().setToolTip(this.toolTip);
						}

						final SoftwareAssembly sad = SoftwareAssembly.Util.getSoftwareAssembly(conn.eResource());
						if (sad != null && !sad.eAdapters().contains(this.listener)) {
							sad.eAdapters().add(this.listener);
						}
					}
				}
			}
		}

		private Image getImage() {
			return RedhawkSadDiagramPlugin.getDefault().getBundledImage("icons/obj16/error_x12.png");
		}
	}

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
