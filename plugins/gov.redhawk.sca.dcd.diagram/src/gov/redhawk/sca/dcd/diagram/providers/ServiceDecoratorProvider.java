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

import gov.redhawk.sca.dcd.diagram.DcdDiagramPluginActivator;
import gov.redhawk.sca.dcd.diagram.part.DcdDiagramEditor;
import mil.jpeojtrs.sca.dcd.DcdComponentPlacement;
import mil.jpeojtrs.sca.dcd.diagram.part.DcdVisualIDRegistry;
import mil.jpeojtrs.sca.partitioning.ComponentPlacement;
import mil.jpeojtrs.sca.scd.ComponentType;
import mil.jpeojtrs.sca.scd.SoftwareComponent;
import mil.jpeojtrs.sca.spd.Descriptor;
import mil.jpeojtrs.sca.spd.SoftPkg;

import org.eclipse.draw2d.Label;
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
public class ServiceDecoratorProvider extends AbstractProvider implements IDecoratorProvider {

	private static class ServiceDecorator extends AbstractDecorator {

		private final Label toolTip = new Label("Service", //$NON-NLS-1$
		        getImage());

		public ServiceDecorator(final IDecoratorTarget decoratorTarget) {
			super(decoratorTarget);
		}

		/**
		 * {@inheritDoc}
		 */
		public void refresh() {
			final View view = (View) getDecoratorTarget().getAdapter(View.class);
			if (view == null || view.eResource() == null) {
				return;
			}
			final EditPart editPart = (EditPart) getDecoratorTarget().getAdapter(EditPart.class);
			if (editPart == null || editPart.getViewer() == null) {
				return;
			}

			final DcdComponentPlacement cp = (DcdComponentPlacement) view.getElement();

			if (ServiceDecoratorProvider.isService(cp.getComponentFileRef().getFile().getSoftPkg()) && editPart instanceof org.eclipse.gef.GraphicalEditPart) {
				int margin = -12;
				if (editPart instanceof org.eclipse.gef.GraphicalEditPart) {
					margin = MapModeUtil.getMapMode(((org.eclipse.gef.GraphicalEditPart) editPart).getFigure()).DPtoLP(margin);
				}

				setDecoration(getDecoratorTarget().addShapeDecoration(getImage(), IDecoratorTarget.Direction.NORTH_WEST, margin - 31, false));
				getDecoration().setToolTip(this.toolTip);
			}
		}

		private Image getImage() {
			return DcdDiagramPluginActivator.getDefault().getBundledImage("icons/obj16/gear_10x.gif");
		}

		public void activate() {

		}

	}

	private static boolean isService(final SoftPkg spd) {
		if (spd != null) {
			final Descriptor desc = spd.getDescriptor();
			if (desc != null) {
				final SoftwareComponent component = desc.getComponent();
				if (component != null) {
					final ComponentType type = SoftwareComponent.Util.getWellKnownComponentType(component);
					return type == ComponentType.SERVICE;
				}
			}
		}
		return false;
	}

	private static final String KEY = "service"; //$NON-NLS-1$

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
				} else if (view.getElement() instanceof ComponentPlacement) {
					final EditDomain editDomain = editPart.getViewer().getEditDomain();
					if (!(editDomain instanceof DiagramEditDomain)) {
						return;
					}
					if (((DiagramEditDomain) editDomain).getEditorPart() instanceof DcdDiagramEditor) {
						decoratorTarget.installDecorator(ServiceDecoratorProvider.KEY, new ServiceDecorator(decoratorTarget));
					}
				}
			}
		}
	}

}
