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
package gov.redhawk.sca.dcd.diagram.providers;

import gov.redhawk.diagram.util.InterfacesUtil;
import gov.redhawk.sca.dcd.diagram.DcdDiagramPluginActivator;
import gov.redhawk.sca.dcd.diagram.edit.parts.DcdConnectInterfaceEditPart;
import gov.redhawk.sca.dcd.diagram.part.DcdDiagramEditor;
import mil.jpeojtrs.sca.dcd.DcdConnectInterface;
import mil.jpeojtrs.sca.dcd.DcdPackage;
import mil.jpeojtrs.sca.dcd.DeviceConfiguration;
import mil.jpeojtrs.sca.dcd.diagram.part.DcdVisualIDRegistry;

import org.eclipse.draw2d.Label;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditDomain;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.AbstractDecorator;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.CreateDecoratorsOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorProvider;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Image;

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
					if (msg.getNotifier() instanceof DcdConnectInterface) {
						switch (msg.getFeatureID(DcdConnectInterface.class)) {
						case DcdPackage.DCD_CONNECTIONS__CONNECT_INTERFACE:
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
		public void activate() {
			refresh();
		}

		/**
		 * {@inheritDoc}
		 */
		public void refresh() {
			removeDecoration();
			final View view = (View) getDecoratorTarget().getAdapter(View.class);
			if (view == null || view.eResource() == null) {
				return;
			}

			final EditPart editPart = (EditPart) getDecoratorTarget().getAdapter(EditPart.class);
			if (editPart instanceof DcdConnectInterfaceEditPart) {
				final DcdConnectInterfaceEditPart connPart = (DcdConnectInterfaceEditPart) editPart;

				if (connPart.getRoot() == null) {
					return;
				}

				if (view.getElement() instanceof DcdConnectInterface) {
					final DcdConnectInterface conn = (DcdConnectInterface) view.getElement();

					if (conn.getSource() != null && conn.getTarget() != null) {
						if (!InterfacesUtil.areCompatible(conn.getSource(), conn.getTarget())) {
							int margin = 0;
							if (connPart instanceof org.eclipse.gef.GraphicalEditPart) {
								margin = MapModeUtil.getMapMode(((org.eclipse.gef.GraphicalEditPart) connPart).getFigure()).DPtoLP(margin);
							}

							setDecoration(getDecoratorTarget().addShapeDecoration(getImage(), IDecoratorTarget.Direction.CENTER, margin, false)); // SUPPRESS CHECKSTYLE MagicNumber
							getDecoration().setToolTip(this.toolTip);
						}

						final DeviceConfiguration dcd = DeviceConfiguration.Util.getDeviceConfiguration(conn.eResource());
						if (dcd != null && !dcd.eAdapters().contains(this.listener)) {
							dcd.eAdapters().add(this.listener);
						}
					}
				}
			}
		}

		private Image getImage() {
			return DcdDiagramPluginActivator.getDefault().getBundledImage("icons/obj16/error_x12.png");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void createDecorators(final IDecoratorTarget decoratorTarget) {
		final EditPart editPart = (EditPart) decoratorTarget.getAdapter(EditPart.class);
		if (editPart instanceof GraphicalEditPart || editPart instanceof AbstractConnectionEditPart) {
			final Object model = editPart.getModel();
			if ((model instanceof View)) {
				final View view = (View) model;
				if (!(view instanceof Edge) && !view.isSetElement()) {
					return;
				} else if (view.getElement() instanceof DcdConnectInterface) {
					final EditDomain editDomain = editPart.getViewer().getEditDomain();
					if (!(editDomain instanceof DiagramEditDomain)) {
						return;
					}
					if (((DiagramEditDomain) editDomain).getEditorPart() instanceof DcdDiagramEditor) {
						decoratorTarget.installDecorator(InvalidConnectionProvider.KEY, new InvalidConnectProvider(decoratorTarget));
					}
				}
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean provides(final IOperation operation) {
		if (!(operation instanceof CreateDecoratorsOperation)) {
			return false;
		}
		final IDecoratorTarget decoratorTarget = ((CreateDecoratorsOperation) operation).getDecoratorTarget();
		final View view = (View) decoratorTarget.getAdapter(View.class);
		return view != null && mil.jpeojtrs.sca.dcd.diagram.edit.parts.DeviceConfigurationEditPart.MODEL_ID.equals(DcdVisualIDRegistry.getModelID(view));
	}
}
