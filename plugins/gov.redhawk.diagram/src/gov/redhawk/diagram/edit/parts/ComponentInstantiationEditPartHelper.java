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

import gov.redhawk.diagram.layout.CustomBorderItemLocator;
import gov.redhawk.diagram.tools.CompositeDragTracker;
import gov.redhawk.model.sca.IDisposable;
import gov.redhawk.model.sca.ScaAbstractComponent;
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.sca.util.PluginUtil;

import java.util.Collections;

import mil.jpeojtrs.sca.dcd.DcdComponentInstantiation;
import mil.jpeojtrs.sca.diagram.figures.ComponentInstantiationFigure;
import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;
import mil.jpeojtrs.sca.partitioning.ComponentSupportedInterfaceStub;
import mil.jpeojtrs.sca.partitioning.ProvidesPortStub;
import mil.jpeojtrs.sca.partitioning.UsesPortStub;
import mil.jpeojtrs.sca.sad.SadComponentInstantiation;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.PropertiesSetStyle;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.progress.WorkbenchJob;

/**
 * @since 3.0
 */
public class ComponentInstantiationEditPartHelper {

	private final IComponentInstantiationEditPart editPart;

	public ComponentInstantiationEditPartHelper(final IComponentInstantiationEditPart editPart) {
		this.editPart = editPart;
	}

	public NodeFigure createNodePlate() {
		final NodeFigure retVal = this.editPart.basicCreateNodePlate();
		retVal.setBorder(new MarginBorder(0, 20, 0, 20)); // SUPPRESS CHECKSTYLE MagicNumber
		return retVal;
	}

	public boolean addFixedChild(final EditPart childEditPart) {
		if (childEditPart instanceof GraphicalEditPart) {
			final GraphicalEditPart graphicalEditPart = (GraphicalEditPart) childEditPart;
			View view = (View) graphicalEditPart.getModel();
			if (view.isSetElement()) {
				final EObject semanticElement = view.getElement();
				if (semanticElement instanceof UsesPortStub) {
					final BorderItemLocator locator = new CustomBorderItemLocator(this.editPart.getPrimaryShape(), PositionConstants.EAST);
					this.editPart.getBorderedFigure().getBorderItemContainer().add(graphicalEditPart.getFigure(), locator);
					return true;
				} else if (semanticElement instanceof ProvidesPortStub) {
					final BorderItemLocator locator = new CustomBorderItemLocator(this.editPart.getPrimaryShape(), PositionConstants.WEST);
					this.editPart.getBorderedFigure().getBorderItemContainer().add(graphicalEditPart.getFigure(), locator);
					return true;
				} else if (semanticElement instanceof ComponentSupportedInterfaceStub) {
					final BorderItemLocator locator = new CustomBorderItemLocator(this.editPart.getPrimaryShape(), PositionConstants.WEST);
					this.editPart.getBorderedFigure().getBorderItemContainer().add(graphicalEditPart.getFigure(), locator);
					Collections.reverse(this.editPart.getBorderedFigure().getBorderItemContainer().getChildren());
					return true;
				} else {
					return this.editPart.basicAddFixedChild(childEditPart);
				}
			} else {
				return this.editPart.basicAddFixedChild(childEditPart);
			}
		} else {
			return this.editPart.basicAddFixedChild(childEditPart);
		}
	}

	public IFigure createNodeShape() {
		final ComponentInstantiationFigure retVal = new ComponentInstantiationFigure();
		final View view = (View) this.editPart.getModel();
		if (view.getElement() instanceof ComponentInstantiation) {
			final ComponentInstantiation ci = (ComponentInstantiation) view.getElement();
			retVal.setNumPorts(Math.max(ci.getUses().size(), ci.getProvides().size()) + 1);
			retVal.setAdjustedWidth(getAdjustedWidth(ci));
		}
		this.editPart.setPrimaryShape(retVal);
		return retVal;
	}

	/**
	 * Determine the length by which we need to expand by comparing the largest port names against the component name and return
	 * the longer of the two.
	 * @param ci The Component Instantiation that we shall examine the characteristics of
	 * @return int Return the length by which we need to expand the associated ComponentInstantiationFigure
	 */
	private int getAdjustedWidth(final ComponentInstantiation ci) {
		int left = 0, right = 0;
		if (ci == null) {
			return 0;
		}
		String usageName = ci.getUsageName();
		if (usageName == null) {
			return 0;
		}

		final int name = usageName.length();

		for (final UsesPortStub uses : ci.getUses()) {
			if (uses.getName().length() > left) {
				left = uses.getName().length();
			}
		}

		for (final ProvidesPortStub provides : ci.getProvides()) {
			if (provides.getName().length() > right) {
				right = provides.getName().length();
			}
		}

		return (left + right > name ? left + right : name); // SUPPRESS CHECKSTYLE Ternary 
	}

	public void addNotationalListeners() {
		this.editPart.basicAddNotationalListeners();
		final PropertiesSetStyle properties = (PropertiesSetStyle) this.editPart.getNotationView().getStyle(NotationPackage.eINSTANCE.getPropertiesSetStyle());
		if (properties != null) {
			properties.eAdapters().add(this.propertyListener);
		}

		addRuntimeListeners();
	}

	public void removeNotationalListeners() {
		final PropertiesSetStyle properties = (PropertiesSetStyle) this.editPart.getNotationView().getStyle(NotationPackage.eINSTANCE.getPropertiesSetStyle());
		if (properties != null) {
			properties.eAdapters().remove(this.propertyListener);
		}
		this.editPart.basicRemoveNotationalListeners();
		removeRuntimeListeners();
	}

	public DragTracker getDragTracker(final Request request) {

		final DragTracker elementTracker = this.editPart.basicGetDragTracker(request);

		final EditPart comparementEditPart = this.editPart.getParent();
		final EditPart cpEditPart = comparementEditPart.getParent();

		final DragTracker containerTracker = cpEditPart.getDragTracker(request);

		return new CompositeDragTracker(elementTracker, containerTracker);
	}

	private final Adapter propertyListener = new EContentAdapter() {
		@Override
		public void notifyChanged(final org.eclipse.emf.common.notify.Notification msg) {
			super.notifyChanged(msg);
			if (!ComponentInstantiationEditPartHelper.this.editPart.isActive()) {
				return;
			}

			final WorkbenchJob job = new WorkbenchJob("Refreshing Component") {

				@Override
				public IStatus runInUIThread(final IProgressMonitor monitor) {
					ComponentInstantiationEditPartHelper.this.editPart.refresh();
					return Status.OK_STATUS;
				}
			};

			job.setUser(true);
			job.schedule();
		}
	};

	private final Adapter statusListener = new AdapterImpl() {
		@Override
		public void notifyChanged(final org.eclipse.emf.common.notify.Notification msg) {
			switch (msg.getFeatureID(ScaComponent.class)) {
			case ScaPackage.SCA_COMPONENT__STARTED:
				if (msg.getNotifier() instanceof ScaComponent) {
					final Boolean started = (Boolean) msg.getNewValue();
					paintResource(started);
				}
				break;
			default:
				break;
			}

			switch (msg.getFeatureID(ScaDevice.class)) {
			case ScaPackage.SCA_DEVICE__STARTED:
				if (msg.getNotifier() instanceof ScaDevice) {
					final Boolean started = (Boolean) msg.getNewValue();
					paintResource(started);
				}
				break;
			default:
				break;
			}

			switch (msg.getFeatureID(IDisposable.class)) {
			case ScaPackage.IDISPOSABLE__DISPOSED:
				if (msg.getNotifier() instanceof Notifier) {
					final Notifier notifier = (Notifier) msg.getNotifier();
					notifier.eAdapters().remove(this);
				}
				break;
			default:
				break;
			}
		};
	};

	private EObject scaResource;

	private void paintResource(final Boolean started) {
		final WorkbenchJob job = new WorkbenchJob("Repainting Component") {

			@Override
			public IStatus runInUIThread(final IProgressMonitor monitor) {
				if (ComponentInstantiationEditPartHelper.this.editPart.isActive()) {
					final IFigure figure = ComponentInstantiationEditPartHelper.this.editPart.getFigure();
					ComponentInstantiationFigure compFigure = null;

					for (final Object obj : figure.getChildren()) {
						if (obj instanceof DefaultSizeNodeFigure) {
							for (final Object obj2 : ((DefaultSizeNodeFigure) obj).getChildren()) {
								if (obj2 instanceof ComponentInstantiationFigure) {
									compFigure = (ComponentInstantiationFigure) obj2;
								}
							}
						}
					}
					if (compFigure != null) {
						if (started != null && started) {
							compFigure.setGradientColor(ComponentInstantiationFigure.COMPONENT_STARTED_COLOR);
							compFigure.repaint();
							compFigure.setOpaque(true);
						} else {
							compFigure.setGradientColor(ComponentInstantiationFigure.COMPONENT_IDLE_COLOR);
							compFigure.repaint();
							compFigure.setOpaque(true);
						}
					}
				}
				return Status.OK_STATUS;
			}
		};
		job.setSystem(true);
		job.setUser(false);
		job.schedule();
	}

	private EObject getScaResource() {
		final Shape shape = (Shape) this.editPart.getModel();
		final EObject element = shape.getElement();

		if (element instanceof SadComponentInstantiation) {
			return PluginUtil.adapt(ScaComponent.class, element, true);
		} else if (element instanceof DcdComponentInstantiation) {
			return PluginUtil.adapt(ScaDevice.class, element, true);
		}

		return null;
	}

	/**
	 * @since 6.0
	 * 
	 */
	public void removeRuntimeListeners() {
		if (scaResource != null) {
			ScaModelCommand.execute(scaResource, new ScaModelCommand() {

				@Override
				public void execute() {
					scaResource.eAdapters().add(ComponentInstantiationEditPartHelper.this.statusListener);
				}
			});
		}

	}

	/**
	 * @since 6.0
	 * 
	 */
	public void addRuntimeListeners() {
		removeRuntimeListeners();
		scaResource = getScaResource();

		if (scaResource != null) {
			final boolean hasStatusListener = scaResource.eAdapters().contains(this.statusListener);

			if (!hasStatusListener) {
				ScaModelCommand.execute(scaResource, new ScaModelCommand() {

					@Override
					public void execute() {
						scaResource.eAdapters().add(ComponentInstantiationEditPartHelper.this.statusListener);
					}
				});
			}
			if (scaResource instanceof ScaAbstractComponent< ? >) {
				final ScaAbstractComponent< ? > comp = (ScaAbstractComponent< ? >) scaResource;
				paintResource(comp.started());
			}
		}
	}
}
