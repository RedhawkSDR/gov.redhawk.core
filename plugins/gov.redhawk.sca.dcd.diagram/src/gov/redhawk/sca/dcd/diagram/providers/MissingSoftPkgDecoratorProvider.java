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
package gov.redhawk.sca.dcd.diagram.providers;

import gov.redhawk.diagram.DiagramUtil;
import gov.redhawk.sca.dcd.diagram.DcdDiagramPluginActivator;
import gov.redhawk.sca.dcd.diagram.edit.parts.DcdComponentInstantiationEditPart;
import gov.redhawk.sca.dcd.diagram.part.DcdDiagramEditor;

import java.util.List;

import mil.jpeojtrs.sca.dcd.DcdComponentInstantiation;
import mil.jpeojtrs.sca.dcd.DcdComponentPlacement;
import mil.jpeojtrs.sca.dcd.DcdPackage;
import mil.jpeojtrs.sca.dcd.DcdPartitioning;
import mil.jpeojtrs.sca.dcd.DeviceConfiguration;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdComponentPlacementEditPart;
import mil.jpeojtrs.sca.dcd.diagram.part.DcdVisualIDRegistry;
import mil.jpeojtrs.sca.diagram.figures.ComponentInstantiationFigure;
import mil.jpeojtrs.sca.spd.SoftPkg;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.Label;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.INodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditDomain;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.AbstractDecorator;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.CreateDecoratorsOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorProvider;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.progress.WorkbenchJob;

/**
 * @since 2.0
 */
public class MissingSoftPkgDecoratorProvider extends AbstractProvider implements IDecoratorProvider {

	private static class MissingSoftPkgProvider extends AbstractDecorator {

		private final Label toolTip = new Label("Component not found in the target SDR", //$NON-NLS-1$
		        getImage());

		private final Adapter listener = new EContentAdapter() {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public void notifyChanged(final Notification msg) {
				super.notifyChanged(msg);
				if (msg.getEventType() == Notification.SET || msg.getEventType() == Notification.REMOVE) {
					if (msg.getNotifier() instanceof DcdPartitioning) {
						switch (msg.getFeatureID(DcdPartitioning.class)) {
						case DcdPackage.DCD_PARTITIONING__COMPONENT_PLACEMENT:
							refresh();
							break;
						default:
							break;
						}
					} else if (msg.getNotifier() instanceof DcdComponentPlacement) {
						switch (msg.getFeatureID(DcdComponentPlacement.class)) {
						case DcdPackage.DCD_PARTITIONING__COMPONENT_PLACEMENT:
							refresh();
							break;
						default:
							break;
						}
					}
				}
			}
		};

		public MissingSoftPkgProvider(final IDecoratorTarget decoratorTarget) {
			super(decoratorTarget);
		}

		public void activate() {
			refresh();
		}

		private Image getImage() {
			return DcdDiagramPluginActivator.getDefault().getBundledImage("icons/obj16/error_x12.png");
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
			if (editPart instanceof DcdComponentPlacementEditPart) {
				final DcdComponentPlacementEditPart compPart = (DcdComponentPlacementEditPart) editPart;
				try {
					if (compPart.getViewer() == null) {
						return;
					}
				} catch (NullPointerException e) {
					return;
				}

				if (view.getElement() instanceof DcdComponentPlacement) {
					final DcdComponentPlacement comp = (DcdComponentPlacement) view.getElement();
					final DeviceConfiguration dcd = DeviceConfiguration.Util.getDeviceConfiguration(comp.eResource());
					if (DiagramUtil.isDiagramLocalSandbox(dcd.eResource())) {
						return;
					}
					if (!comp.getComponentInstantiation().isEmpty()) {
						final DcdComponentInstantiation inst = comp.getComponentInstantiation().get(0);
						if (comp.getComponentFileRef() != null && comp.getComponentFileRef().getFile() != null) {
							final SoftPkg softPkg = comp.getComponentFileRef().getFile().getSoftPkg();

							if (((softPkg == null) || softPkg.eIsProxy()) && compPart instanceof org.eclipse.gef.GraphicalEditPart) {
								int margin = -12; // SUPPRESS CHECKSTYLE MagicNumber
								if (compPart instanceof org.eclipse.gef.GraphicalEditPart) {
									margin = MapModeUtil.getMapMode(((org.eclipse.gef.GraphicalEditPart) compPart).getFigure()).DPtoLP(margin);
								}

								setDecoration(getDecoratorTarget().addShapeDecoration(getImage(), IDecoratorTarget.Direction.NORTH_WEST, margin - 30, false)); // SUPPRESS CHECKSTYLE MagicNumber
								getDecoration().setToolTip(this.toolTip);

								final EditPart foundPart = MissingSoftPkgDecoratorProvider.getEditPartFor(editPart, inst, INodeEditPart.class);

								if (foundPart != null) {
									final ComponentInstantiationFigure figure = (ComponentInstantiationFigure) ((DcdComponentInstantiationEditPart) foundPart)
									        .getContentPane();
									figure.setLineStyle(SWT.LINE_DASH);
								}
							}

							if (!dcd.eAdapters().contains(this.listener)) {
								dcd.eAdapters().add(this.listener);
							}
						}
					}

				}
			}
		}
	}

	private static final String KEY = "missingSoftPkg"; //$NON-NLS-1$

	@SuppressWarnings("unchecked")
	private static EditPart getEditPartFor(final EditPart hostPart, final EObject element, @SuppressWarnings("rawtypes") final Class expectedClass) {
		if (element != null && !(element instanceof View)) {
			final EditPartViewer viewer = hostPart.getViewer();
			if (viewer instanceof IDiagramGraphicalViewer) {
				List<EditPart> parts = ((IDiagramGraphicalViewer) viewer).findEditPartsForElement(EMFCoreUtil.getProxyID(element), INodeEditPart.class);

				if (parts.isEmpty()) {
					// reach for the container's editpart instead and force it
					// to refresh
					final EObject container = element.eContainer();
					final EditPart containerEP = MissingSoftPkgDecoratorProvider.getEditPartFor(hostPart, container, INodeEditPart.class);
					if (containerEP != null) {
						final WorkbenchJob job = new WorkbenchJob("Refresh container") {

							@Override
							public IStatus runInUIThread(final IProgressMonitor monitor) {
								containerEP.refresh();
								return Status.OK_STATUS;
							}
						};
						job.schedule();
						parts = ((IDiagramGraphicalViewer) viewer).findEditPartsForElement(EMFCoreUtil.getProxyID(element), INodeEditPart.class);
					}
				}

				if (!parts.isEmpty()) {
					return parts.get(0);
				}
			}
		}

		return (EditPart) hostPart.getViewer().getEditPartRegistry().get(element);
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
				} else if (view.getElement() instanceof DcdComponentPlacement) {
					final EditDomain editDomain = editPart.getViewer().getEditDomain();
					if (!(editDomain instanceof DiagramEditDomain)) {
						return;
					}
					if (((DiagramEditDomain) editDomain).getEditorPart() instanceof DcdDiagramEditor) {
						decoratorTarget.installDecorator(MissingSoftPkgDecoratorProvider.KEY, new MissingSoftPkgProvider(decoratorTarget));
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
