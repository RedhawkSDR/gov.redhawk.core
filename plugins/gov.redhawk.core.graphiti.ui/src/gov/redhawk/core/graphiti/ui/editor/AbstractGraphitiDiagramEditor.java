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
package gov.redhawk.core.graphiti.ui.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.contexts.IContextActivation;
import org.eclipse.ui.contexts.IContextService;

public abstract class AbstractGraphitiDiagramEditor extends DiagramEditor {

	private List<String> contexts = new ArrayList<String>();
	private List<IContextActivation> contextActivations = new ArrayList<IContextActivation>();
	private MouseListener mouseListener = null;

	public AbstractGraphitiDiagramEditor(EditingDomain editingDomain) {
		super();
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		super.init(site, input);

		// Activate contexts specified pre-init()
		for (String context : contexts) {
			activateContext(context);
		}
	}

	@Override
	public void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();

		// Set a margin around the diagram border to make it easier to scroll a little bit to the side
		EditPart ep = getGraphicalViewer().getRootEditPart().getContents();
		if (ep instanceof AbstractGraphicalEditPart) {
			IFigure fig = ((AbstractGraphicalEditPart) ep).getFigure();
			fig.setBorder(new MarginBorder(50));
		}
	}

	@Override
	public void dispose() {
		deactivateAllContexts();
		super.dispose();
	}

	/**
	 * Every time the diagram receives focus update the diagram's components and connections
	 */
	@Override
	public void setFocus() {
		super.setFocus();

		updateDiagram();
		getDiagramBehavior().refresh();
	}

	/**
	 * Update the diagram's components and connections
	 */
	protected void updateDiagram() {
		Diagram diagram = getDiagramTypeProvider().getDiagram();
		UpdateContext updateContext = new UpdateContext(diagram);
		IFeatureProvider featureProvider = getDiagramTypeProvider().getFeatureProvider();
		featureProvider.updateIfPossibleAndNeeded(updateContext);
	}

	@Override
	public void hookGraphicalViewer() {
		super.hookGraphicalViewer();

		// Normally in Graphiti, clicking on anchors will not select them, which is not the behavior we want. As noted
		// in IDE-1029, right-clicking on a port pops up the context menu for the currently selected object (often the
		// wrong one). In order to enable selection, we intercept mouse down events on the viewer's control, and if it
		// looks like an anchor we forcibly update the selection.
		//
		// It does not appear to be necessary to remove the listener in dispose(), as the CanvasViewer has already
		// been disposed at that point.
		getGraphicalControl().addMouseListener(getMouseListener());
	}

	protected void handleMouseDown(MouseEvent e) {
		if (e.button == 1 || e.button == 3) {
			GraphicalViewer viewer = getGraphicalViewer();
			if (viewer != null) {
				EditPart part = viewer.findObjectAt(new Point(e.x, e.y));
				if (part.getModel() instanceof Anchor) {
					Anchor anchor = (Anchor) part.getModel();
					IToolBehaviorProvider behaviorProvider = getDiagramTypeProvider().getCurrentToolBehaviorProvider();
					PictogramElement selectedPe = behaviorProvider.getSelection(anchor, getDiagramBehavior().getSelectedPictogramElements());
					if (selectedPe == null) {
						selectedPe = anchor;
					}
					getDiagramBehavior().setPictogramElementForSelection(selectedPe);
					getDiagramBehavior().selectBufferedPictogramElements();
				}
			}
		}
	}

	public void addContext(String context) {
		contexts.add(context);
	}

	protected void activateContext(String context) {
		IContextService contextService = (IContextService) getSite().getService(IContextService.class);
		if (contextService != null) {
			IContextActivation activation = contextService.activateContext(context);
			contextActivations.add(activation);
		}

	}

	private void deactivateAllContexts() {
		if (!contextActivations.isEmpty()) {
			IContextService contextService = (IContextService) getSite().getService(IContextService.class);
			for (IContextActivation activation : contextActivations) {
				contextService.deactivateContext(activation);
			}
		}
	}

	private MouseListener getMouseListener() {
		if (mouseListener == null) {
			mouseListener = new MouseAdapter() {

				@Override
				public void mouseDown(MouseEvent e) {
					AbstractGraphitiDiagramEditor.this.handleMouseDown(e);
				}
			};
		}
		return mouseListener;
	}

	@Override
	protected abstract DiagramBehavior createDiagramBehavior();

}
