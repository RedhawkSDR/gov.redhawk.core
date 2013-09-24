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
package gov.redhawk.diagram.tools;

import java.util.Map;

import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.widgets.Event;

/**
 * @since 3.0
 * 
 */
public class CompositeDragTracker implements DragTracker {

	private final DragTracker elementTracker;
	private final DragTracker containerTracker;

	public CompositeDragTracker(final DragTracker elementTracker, final DragTracker containerTracker) {
		this.elementTracker = elementTracker;
		this.containerTracker = containerTracker;
	}

	@Override
	public void commitDrag() {
		this.containerTracker.commitDrag();
		//		this.elementTracker.commitDrag();
	}

	@Override
	public void activate() {
		this.containerTracker.activate();
		this.elementTracker.activate();
	}

	@Override
	public void deactivate() {
		this.containerTracker.deactivate();
		this.elementTracker.deactivate();
	}

	@Override
	public void focusGained(final FocusEvent event, final EditPartViewer viewer) {
		//		this.containerTracker.focusGained(event, viewer);
		this.elementTracker.focusGained(event, viewer);
	}

	@Override
	public void focusLost(final FocusEvent event, final EditPartViewer viewer) {
		//		this.containerTracker.focusLost(event, viewer);
		this.elementTracker.focusLost(event, viewer);
	}

	@Override
	public void keyDown(final KeyEvent keyEvent, final EditPartViewer viewer) {
		//		this.containerTracker.keyDown(keyEvent, viewer);
		this.elementTracker.keyDown(keyEvent, viewer);
	}

	@Override
	public void keyTraversed(final TraverseEvent event, final EditPartViewer viewer) {
		//		this.containerTracker.keyTraversed(event, viewer);
		this.elementTracker.keyTraversed(event, viewer);
	}

	@Override
	public void keyUp(final KeyEvent keyEvent, final EditPartViewer viewer) {
		//		this.containerTracker.keyUp(keyEvent, viewer);
		this.elementTracker.keyUp(keyEvent, viewer);
	}

	@Override
	public void mouseDoubleClick(final MouseEvent mouseEvent, final EditPartViewer viewer) {
		//	    containerTracker.mouseDoubleClick(mouseEvent, viewer);
		this.elementTracker.mouseDoubleClick(mouseEvent, viewer);
	}

	@Override
	public void mouseDown(final MouseEvent mouseEvent, final EditPartViewer viewer) {
		this.containerTracker.mouseDown(mouseEvent, viewer);
		this.elementTracker.mouseDown(mouseEvent, viewer);
	}

	@Override
	public void mouseDrag(final MouseEvent mouseEvent, final EditPartViewer viewer) {
		this.containerTracker.mouseDrag(mouseEvent, viewer);
		//	    elementTracker.mouseDrag(mouseEvent, viewer);
	}

	@Override
	public void mouseHover(final MouseEvent mouseEvent, final EditPartViewer viewer) {
		//	    containerTracker.mouseHover(mouseEvent, viewer);
		this.elementTracker.mouseHover(mouseEvent, viewer);
	}

	@Override
	public void mouseMove(final MouseEvent mouseEvent, final EditPartViewer viewer) {
		this.containerTracker.mouseMove(mouseEvent, viewer);
		this.elementTracker.mouseMove(mouseEvent, viewer);
	}

	@Override
	public void mouseUp(final MouseEvent mouseEvent, final EditPartViewer viewer) {
		this.containerTracker.mouseUp(mouseEvent, viewer);
//		this.elementTracker.mouseUp(mouseEvent, viewer);
	}

	@Override
	public void mouseWheelScrolled(final Event event, final EditPartViewer viewer) {
		//	    containerTracker.mouseWheelScrolled(event, viewer);
		this.elementTracker.mouseWheelScrolled(event, viewer);
	}

	@Override
	public void nativeDragFinished(final DragSourceEvent event, final EditPartViewer viewer) {
		this.containerTracker.nativeDragFinished(event, viewer);
		//	    elementTracker.nativeDragFinished(event, viewer);
	}

	@Override
	public void nativeDragStarted(final DragSourceEvent event, final EditPartViewer viewer) {
		this.containerTracker.nativeDragStarted(event, viewer);
		//	    elementTracker.nativeDragStarted(event, viewer);
	}

	@Override
	public void setEditDomain(final EditDomain domain) {
		this.containerTracker.setEditDomain(domain);
		this.elementTracker.setEditDomain(domain);
	}

	@Override
	public void setViewer(final EditPartViewer viewer) {
		//		this.containerTracker.setViewer(viewer);
		this.elementTracker.setViewer(viewer);
	}

	@Override
	public void viewerEntered(final MouseEvent mouseEvent, final EditPartViewer viewer) {
		//		this.containerTracker.viewerEntered(mouseEvent, viewer);
		this.elementTracker.viewerEntered(mouseEvent, viewer);
	}

	@Override
	public void viewerExited(final MouseEvent mouseEvent, final EditPartViewer viewer) {
		//		this.containerTracker.viewerExited(mouseEvent, viewer);
		this.elementTracker.viewerExited(mouseEvent, viewer);
	}

	@Override
	public void setProperties(@SuppressWarnings("rawtypes") final Map properties) {
		this.containerTracker.setProperties(properties);
		this.elementTracker.setProperties(properties);
	}

}
