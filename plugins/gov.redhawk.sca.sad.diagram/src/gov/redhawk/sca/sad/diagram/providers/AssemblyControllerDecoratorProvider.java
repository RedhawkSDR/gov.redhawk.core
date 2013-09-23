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

import gov.redhawk.sca.sad.diagram.part.SadDiagramEditor;

import java.util.Iterator;

import mil.jpeojtrs.sca.diagram.figures.ComponentInstantiationFigure;
import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;
import mil.jpeojtrs.sca.partitioning.ComponentInstantiationRef;
import mil.jpeojtrs.sca.partitioning.PartitioningPackage;
import mil.jpeojtrs.sca.sad.AssemblyController;
import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.sad.SadComponentPlacement;
import mil.jpeojtrs.sca.sad.SadPackage;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import mil.jpeojtrs.sca.sad.diagram.part.SadDiagramEditorPlugin;
import mil.jpeojtrs.sca.sad.diagram.part.SadVisualIDRegistry;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditDomain;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.AbstractDecorator;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.CreateDecoratorsOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorProvider;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.progress.WorkbenchJob;

public class AssemblyControllerDecoratorProvider extends AbstractProvider implements IDecoratorProvider {

	private static final String KEY = "assemblyControllerStatus"; //$NON-NLS-1$

	private static class AssemblyControllerDecorator extends AbstractDecorator {
		private String viewId;
		private int lineWidth = 0;
		private Color color = null;
		private Label toolTip = null;

		private final Adapter listener = new EContentAdapter() {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public void notifyChanged(final Notification msg) {
				super.notifyChanged(msg);
				if (msg.getEventType() == Notification.SET) {
					if (msg.getNotifier() instanceof SoftwareAssembly) {
						switch (msg.getFeatureID(SoftwareAssembly.class)) {
						case SadPackage.SOFTWARE_ASSEMBLY__ASSEMBLY_CONTROLLER:
							refresh();
							break;
						default:
							break;
						}
					} else if (msg.getNotifier() instanceof AssemblyController) {
						switch (msg.getFeatureID(AssemblyController.class)) {
						case SadPackage.ASSEMBLY_CONTROLLER__COMPONENT_INSTANTIATION_REF:
							refresh();
							break;
						default:
							break;
						}
					} else if (msg.getNotifier() instanceof ComponentInstantiationRef) {
						switch (msg.getFeatureID(ComponentInstantiationRef.class)) {
						case PartitioningPackage.COMPONENT_INSTANTIATION_REF__REFID:
							refresh();
							break;
						default:
							break;
						}
					}
				}

			}
		};

		public AssemblyControllerDecorator(final IDecoratorTarget decoratorTarget) {
			super(decoratorTarget);
			try {
				final View view = (View) getDecoratorTarget().getAdapter(View.class);
				TransactionUtil.getEditingDomain(view).runExclusive(new Runnable() {

					@Override
					public void run() {
						AssemblyControllerDecorator.this.viewId = (view != null) ? ViewUtil.getIdStr(view) : null; // SUPPRESS CHECKSTYLE AvoidInLine
					}
				});
			} catch (final Exception e) {  // SUPPRESS CHECKSTYLE Catch All
				SadDiagramEditorPlugin.getInstance().logError("ViewID access failure", e); //$NON-NLS-1$
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void activate() {
			if (this.viewId == null) {
				return;
			}

			// start listening to changes in resources
			final View view = (View) getDecoratorTarget().getAdapter(View.class);
			if (view == null) {
				return;
			}
			SoftwareAssembly sad = null;
			final EObject element = view.getElement();
			if (element instanceof SadComponentInstantiation) {
				final SadComponentInstantiation ci = (SadComponentInstantiation) element;
				sad = AssemblyControllerDecoratorProvider.getSoftwareAssembly(ci);
			} else if (element instanceof SoftwareAssembly) {
				sad = (SoftwareAssembly) element;
			}
			if (sad != null) {
				sad.eAdapters().add(this.listener);
			}
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

			if (view.getElement() instanceof SadComponentInstantiation) {
				final SadComponentInstantiation ci = (SadComponentInstantiation) view.getElement();
				final SoftwareAssembly sad = AssemblyControllerDecoratorProvider.getSoftwareAssembly(ci);
				if (sad != null) {
					final AssemblyController assemblyController = sad.getAssemblyController();
					boolean isAssemblyController = false;

					if ((assemblyController != null) && (assemblyController.getComponentInstantiationRef() != null)) {
						final String refId = assemblyController.getComponentInstantiationRef().getRefid();
						for (final ComponentInstantiation inst : ((SadComponentPlacement) ci.eContainer()).getComponentInstantiation()) {
							if (inst.getId().equals(refId)) {
								isAssemblyController = true;
							}
						}
					}

					// add decoration
					if (editPart instanceof org.eclipse.gef.GraphicalEditPart) {
						final ComponentInstantiationFigure figure = AssemblyControllerDecoratorProvider
						        .getComponentInstantiationFigure(((org.eclipse.gef.GraphicalEditPart) editPart).getFigure());

						if (isAssemblyController) {
							this.toolTip = new Label("Assembly Controller");
							this.color = ComponentInstantiationFigure.ASSEMBLY_CONTROLLER_COLOR;
							this.lineWidth = ComponentInstantiationFigure.ASSEMBLY_CONTROLLER_LINE_WIDTH;
						} else {
							this.toolTip = null;
							this.color = ComponentInstantiationFigure.DEFAULT_COMPONENT_COLOR;
							this.lineWidth = ComponentInstantiationFigure.DEFAULT_LINE_WIDTH;
						}

						final WorkbenchJob job = new WorkbenchJob("") {

							@Override
							public IStatus runInUIThread(final IProgressMonitor monitor) {
								figure.setToolTip(AssemblyControllerDecorator.this.toolTip);
								figure.setForegroundColor(AssemblyControllerDecorator.this.color);
								figure.setLineWidth(AssemblyControllerDecorator.this.lineWidth);
								return Status.OK_STATUS;
							}
						};

						job.schedule();
					}
				}
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void deactivate() {
			super.deactivate();
			final View view = (View) getDecoratorTarget().getAdapter(View.class);
			if (view != null) {
				final EObject element = view.getElement();
				SoftwareAssembly sad = null;
				if (element instanceof SadComponentInstantiation) {
					final SadComponentInstantiation ci = (SadComponentInstantiation) view.getElement();
					sad = AssemblyControllerDecoratorProvider.getSoftwareAssembly(ci);
				} else if (element instanceof SoftwareAssembly) {
					sad = (SoftwareAssembly) element;
				}
				if (sad != null) {
					sad.eAdapters().remove(this.listener);
				}
			}
		}
	}

	private static SoftwareAssembly getSoftwareAssembly(final SadComponentInstantiation ci) {
		if (ci != null) {
			for (EObject obj = ci.eContainer(); obj != null; obj = obj.eContainer()) {
				if (obj instanceof SoftwareAssembly) {
					return (SoftwareAssembly) obj;
				}
			}
		}
		return null;
	}

	private static ComponentInstantiationFigure getComponentInstantiationFigure(final IFigure figure) {
		if (figure instanceof ComponentInstantiationFigure) {
			return (ComponentInstantiationFigure) figure;
		} else {
			final Iterator< ? > iter = figure.getChildren().iterator();

			return AssemblyControllerDecoratorProvider.getComponentInstantiationFigure((IFigure) iter.next());
		}
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createDecorators(final IDecoratorTarget decoratorTarget) {
		final EditPart editPart = (EditPart) decoratorTarget.getAdapter(EditPart.class);
		if (editPart instanceof GraphicalEditPart || editPart instanceof AbstractConnectionEditPart) {
			final Object model = editPart.getModel();
			if ((model instanceof View)) {
				final View view = (View) model;
				if (!(view instanceof Edge) && !view.isSetElement()) {
					return;
				} else if (view.getElement() instanceof SadComponentInstantiation) {
					final EditDomain editDomain = editPart.getViewer().getEditDomain();
					if (!(editDomain instanceof DiagramEditDomain)) {
						return;
					}
					if (((DiagramEditDomain) editDomain).getEditorPart() instanceof SadDiagramEditor) {
						decoratorTarget.installDecorator(AssemblyControllerDecoratorProvider.KEY, new AssemblyControllerDecorator(decoratorTarget));
					}
				}
			}
		}
	}
}
