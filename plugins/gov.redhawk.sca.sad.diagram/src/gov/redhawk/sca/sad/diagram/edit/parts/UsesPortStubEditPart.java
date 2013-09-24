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

import gov.redhawk.diagram.edit.parts.IUsesPortStubEditPart;
import gov.redhawk.diagram.edit.parts.UsesPortStubEditPartHelper;
import gov.redhawk.sca.sad.diagram.part.RedhawkSadVisualIdRegistry;
import gov.redhawk.sca.sad.diagram.providers.RedhawkSadElementTypes;

import java.util.List;

import mil.jpeojtrs.sca.diagram.figures.UsesPortStubFigure;
import mil.jpeojtrs.sca.partitioning.UsesPortStub;
import mil.jpeojtrs.sca.sad.Port;
import mil.jpeojtrs.sca.sad.SadPackage;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

import org.eclipse.core.databinding.observable.list.IListChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.ListChangeEvent;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.emf.databinding.IEMFListProperty;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.core.resources.GMFResource;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.progress.WorkbenchJob;

public class UsesPortStubEditPart extends mil.jpeojtrs.sca.sad.diagram.edit.parts.UsesPortStubEditPart implements IUsesPortStubEditPart {
	private final UsesPortStubEditPartHelper editPartHelper;
	private static final Color EXTERNAL_PORT_BACKGROUND = new Color(null, 0, 0, 194);
	private static final Color EXTERNAL_PORT_FOREGROUND = UsesPortStubFigure.getDefaultForegroundColor();

	private IObservableList observer;

	public UsesPortStubEditPart(final View view) {
		super(view);
		this.editPartHelper = new UsesPortStubEditPartHelper(this, RedhawkSadElementTypes.INSTANCE, RedhawkSadVisualIdRegistry.INSTANCE);
	}

	@Override
	protected IFigure createNodeShape() {
		return this.editPartHelper.createNodeShape();
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(final ConnectionEditPart connEditPart) {
		return this.editPartHelper.getSourceConnectionAnchor(connEditPart);
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(final Request request) {
		return this.editPartHelper.getSourceConnectionAnchor(request);
	}

	@Override
	protected void addBorderItem(final IFigure borderItemContainer, final IBorderItemEditPart borderItemEditPart) {
		this.editPartHelper.addBorderItem(borderItemContainer, borderItemEditPart);
	}

	@Override
	public EditPolicy getPrimaryDragEditPolicy() {
		return this.editPartHelper.getPrimaryDragEditPolicy();
	}

	@Override
	public EditPart getPrimaryChildEditPart() {
		return this.editPartHelper.getPrimaryChildEditPart();
	}

	@Override
	public DragTracker getDragTracker(final Request request) {
		return this.editPartHelper.getDragTracker(request);
	}

	@Override
	protected void addSemanticListeners() {
		super.addSemanticListeners();
		final IEMFListProperty portsValue = EMFProperties.list(FeaturePath.fromList(SadPackage.Literals.SOFTWARE_ASSEMBLY__EXTERNAL_PORTS,
			SadPackage.Literals.EXTERNAL_PORTS__PORT));
		disposeObserver();
		View view = ((View) this.getModel());
		if (view.isSetElement()) {
			final EObject obj = view.getElement();
			if (obj != null) {
				final SoftwareAssembly sad = ScaEcoreUtils.getEContainerOfType(obj, SoftwareAssembly.class);
				if (sad != null) {
					this.observer = portsValue.observe(sad);
					this.observer.addListChangeListener(new IListChangeListener() {
						@Override
						public void handleListChange(final ListChangeEvent event) {
							final WorkbenchJob job = new WorkbenchJob("") {

								@Override
								public IStatus runInUIThread(final IProgressMonitor monitor) {
									updateColor();
									return Status.OK_STATUS;
								}
							};
							job.schedule();
						}
					});
					updateColor();
				}
			}
		}
	}

	private void disposeObserver() {
		if (this.observer != null) {
			this.observer.dispose();
			this.observer = null;
		}
	}

	@Override
	protected void removeSemanticListeners() {
		super.removeSemanticListeners();
		disposeObserver();
	}

	private SoftwareAssembly getSoftwareAssembly(final EObject object) {
		SoftwareAssembly sad = null;

		if (!(object.eResource() instanceof GMFResource)) {
			sad = SoftwareAssembly.Util.getSoftwareAssembly(object.eResource());
		} else {
			final Diagram diagram = (Diagram) EcoreUtil.getRootContainer(object);
			sad = (SoftwareAssembly) diagram.getElement();
		}

		return sad;
	}

	private void updateColor() {
		View view = (View) this.getModel();
		if (view.isSetElement()) {
			final UsesPortStub port = (UsesPortStub) view.getElement();
			final EObject obj = ((View) this.getModel()).getElement();
			if (obj != null) {
				final SoftwareAssembly sad = getSoftwareAssembly(obj);
				boolean found = false;
				if (sad != null && sad.getExternalPorts() != null) {
					for (final Port externalPort : sad.getExternalPorts().getPort()) {
						if (externalPort.getComponentInstantiationRef().getInstantiation() == port.eContainer()
							&& port.getUses().getUsesName().equals(externalPort.getUsesIdentifier())) {
							found = true;
							break;
						}
					}
				}
				if (found) {
					this.primaryShape.setForegroundColor(getExternalPortForegroundColor());
					this.primaryShape.setBackgroundColor(getExternalPortBackgroundColor());
				} else {
					this.primaryShape.setBackgroundColor(UsesPortStubFigure.getDefaultBackgroundColor());
					this.primaryShape.setForegroundColor(UsesPortStubFigure.getDefaultForegroundColor());
				}
			}
		}
	}

	private Color getExternalPortBackgroundColor() {
		return UsesPortStubEditPart.EXTERNAL_PORT_BACKGROUND;
	}

	private Color getExternalPortForegroundColor() {
		return UsesPortStubEditPart.EXTERNAL_PORT_FOREGROUND;
	}

	@Override
	public List<IElementType> getMARelTypesOnSourceAndTarget(final IGraphicalEditPart targetEditPart) {
		return this.editPartHelper.getMARelTypesOnSourceAndTarget(targetEditPart);
	}

	@Override
	public List<IElementType> getMATypesForTarget(final IElementType relationshipType) {
		return this.editPartHelper.getMATypesForTarget(relationshipType);
	}

	@Override
	public IFigure basicCreateNodeShape() {
		return super.createNodeShape();
	}

	@Override
	public void basicAddBorderItem(final IFigure borderItemContainer, final IBorderItemEditPart borderItemEditPart) {
		super.addBorderItem(borderItemContainer, borderItemEditPart);
	}

	@Override
	public void setVisibility(final boolean vis) {
		super.setVisibility(vis);
	}
}
