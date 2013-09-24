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
package gov.redhawk.diagram.ui.tools;

import java.util.Map;

import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.handles.ConnectionHandle;
import org.eclipse.gmf.runtime.diagram.ui.handles.ConnectionHandle.HandleDirection;
import org.eclipse.gmf.runtime.diagram.ui.internal.tools.ConnectionHandleTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.DragEditPartsTrackerEx;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.widgets.Event;

/**
 * @since 2.0
 *
 */
@SuppressWarnings("restriction")
public class DragConnectionCreationProxy implements DragTracker {

	private final ConnectionHandleTool connectionTool;
	private final DragEditPartsTrackerEx editPartTracker;

	public DragConnectionCreationProxy(final IGraphicalEditPart editPart, final HandleDirection direction, final String tooltip) {
		this.connectionTool = new ConnectionHandleTool(new ConnectionHandle(editPart, direction, tooltip));
		this.editPartTracker = new DragEditPartsTrackerEx(editPart);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.DragTracker#commitDrag()
	 */
	@Override
	public void commitDrag() {
		this.connectionTool.commitDrag();
		//		editPartTracker.commitDrag();
	}

	@Override
	public void activate() {
		this.editPartTracker.activate();
		this.connectionTool.activate();
	}

	@Override
	public void deactivate() {
		this.editPartTracker.deactivate();
		this.connectionTool.deactivate();
	}

	@Override
	public void focusGained(final FocusEvent event, final EditPartViewer viewer) {
		this.connectionTool.focusGained(event, viewer);
		this.editPartTracker.focusGained(event, viewer);
	}

	@Override
	public void focusLost(final FocusEvent event, final EditPartViewer viewer) {
		this.connectionTool.focusLost(event, viewer);
		this.editPartTracker.focusLost(event, viewer);
	}

	@Override
	public void keyDown(final KeyEvent keyEvent, final EditPartViewer viewer) {
		this.connectionTool.keyDown(keyEvent, viewer);
		this.editPartTracker.keyDown(keyEvent, viewer);
	}

	@Override
	public void keyTraversed(final TraverseEvent event, final EditPartViewer viewer) {
		this.connectionTool.keyTraversed(event, viewer);
		this.editPartTracker.keyTraversed(event, viewer);
	}

	@Override
	public void keyUp(final KeyEvent keyEvent, final EditPartViewer viewer) {
		this.connectionTool.keyUp(keyEvent, viewer);
		this.editPartTracker.keyUp(keyEvent, viewer);
	}

	@Override
	public void mouseDoubleClick(final MouseEvent mouseEvent, final EditPartViewer viewer) {
		this.editPartTracker.mouseDoubleClick(mouseEvent, viewer);
		this.connectionTool.mouseDoubleClick(mouseEvent, viewer);
	}

	@Override
	public void mouseDown(final MouseEvent mouseEvent, final EditPartViewer viewer) {
		this.editPartTracker.mouseDown(mouseEvent, viewer);
		this.connectionTool.mouseDown(mouseEvent, viewer);
	}

	@Override
	public void mouseDrag(final MouseEvent mouseEvent, final EditPartViewer viewer) {
		this.connectionTool.mouseDrag(mouseEvent, viewer);
		//		editPartTracker.mouseDrag(mouseEvent, viewer);
	}

	@Override
	public void mouseHover(final MouseEvent mouseEvent, final EditPartViewer viewer) {
		this.connectionTool.mouseHover(mouseEvent, viewer);
		this.editPartTracker.mouseHover(mouseEvent, viewer);
	}

	@Override
	public void mouseMove(final MouseEvent mouseEvent, final EditPartViewer viewer) {
		this.editPartTracker.mouseMove(mouseEvent, viewer);
		this.connectionTool.mouseMove(mouseEvent, viewer);
	}

	@Override
	public void mouseUp(final MouseEvent mouseEvent, final EditPartViewer viewer) {
		this.editPartTracker.mouseUp(mouseEvent, viewer);
		this.connectionTool.mouseUp(mouseEvent, viewer);
	}

	@Override
	public void mouseWheelScrolled(final Event event, final EditPartViewer viewer) {
		this.editPartTracker.mouseWheelScrolled(event, viewer);
		this.connectionTool.mouseWheelScrolled(event, viewer);
	}

	@Override
	public void nativeDragFinished(final DragSourceEvent event, final EditPartViewer viewer) {
		this.connectionTool.nativeDragFinished(event, viewer);
		//		editPartTracker.nativeDragFinished(event, viewer);
	}

	@Override
	public void nativeDragStarted(final DragSourceEvent event, final EditPartViewer viewer) {
		this.connectionTool.nativeDragStarted(event, viewer);
		//		editPartTracker.nativeDragStarted(event, viewer);
	}

	@Override
	public void setEditDomain(final EditDomain domain) {
		this.connectionTool.setEditDomain(domain);
		this.editPartTracker.setEditDomain(domain);
	}

	@Override
	public void setViewer(final EditPartViewer viewer) {
		this.connectionTool.setViewer(viewer);
		this.editPartTracker.setViewer(viewer);
	}

	@Override
	public void viewerEntered(final MouseEvent mouseEvent, final EditPartViewer viewer) {
		this.connectionTool.viewerEntered(mouseEvent, viewer);
		this.editPartTracker.viewerEntered(mouseEvent, viewer);
	}

	@Override
	public void viewerExited(final MouseEvent mouseEvent, final EditPartViewer viewer) {
		this.connectionTool.viewerExited(mouseEvent, viewer);
		this.editPartTracker.viewerExited(mouseEvent, viewer);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void setProperties(final Map properties) {
		this.connectionTool.setProperties(properties);
		this.editPartTracker.setProperties(properties);
	}

}
