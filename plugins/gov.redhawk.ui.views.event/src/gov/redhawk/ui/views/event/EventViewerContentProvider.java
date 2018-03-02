/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.ui.views.event;

import java.util.Collections;
import java.util.List;

import org.eclipse.jface.databinding.viewers.IViewerUpdater;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.nebula.widgets.xviewer.XViewer;

public class EventViewerContentProvider implements ITreeContentProvider {

	private IViewerUpdater viewerUpdater = new IViewerUpdater() {

		@Override
		public void replace(Object oldElement, Object newElement, int position) {
			viewer.replace(input, position, newElement);
			scroll();
		}

		@Override
		public void remove(Object element, int position) {
			viewer.remove(element, position);
			scroll();
		}

		@Override
		public void remove(Object[] elements) {
			viewer.remove(elements);
			scroll();
		}

		@Override
		public void move(Object element, int oldPosition, int newPosition) {
			viewer.refresh();
			scroll();
		}

		@Override
		public void insert(Object element, int position) {
			viewer.insert(input, element, position);
			scroll();
		}

		@Override
		public void add(Object[] elements) {
			viewer.add(input, elements);
			scroll();
		}

		private void scroll() {
			if (!scrollLock) {
				List< ? > inputList = (List< ? >) input;
				if (!inputList.isEmpty()) {
					viewer.reveal(inputList.get(inputList.size() - 1));
				}
			}
		}
	};
	private ObservableListContentProvider contentProvider = new ObservableListContentProvider(viewerUpdater);
	private XViewer viewer;
	private Object input;
	private boolean scrollLock = false;

	public EventViewerContentProvider() {
	}

	@Override
	public void dispose() {
		contentProvider.dispose();
	}

	public void setScrollLock(boolean scrollLock) {
		this.scrollLock = scrollLock;
	}

	public boolean isScrollLock() {
		return scrollLock;
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.input = newInput;
		contentProvider.inputChanged(viewer, oldInput, newInput);
		this.viewer = (XViewer) viewer;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return contentProvider.getElements(inputElement);
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		return Collections.EMPTY_LIST.toArray();
	}

	@Override
	public Object getParent(Object element) {
		if (element == input) {
			return null;
		}
		return input;
	}

	@Override
	public boolean hasChildren(Object element) {
		return false;
	}

}
