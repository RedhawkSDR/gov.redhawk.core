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
package gov.redhawk.ui.views.namebrowser.view.internal;

import gov.redhawk.ui.views.namebrowser.view.BindingNode;

import java.util.Collection;

import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.progress.DeferredTreeContentManager;
import org.eclipse.ui.progress.IDeferredWorkbenchAdapter;

public class BindingContentProvider implements ITreeContentProvider, IStructuredContentProvider {
	private DeferredTreeContentManager fManager = null;

	private static final Object[] EMPTY = new Object[0];

	public BindingContentProvider() {
		super();
	}

	public void dispose() {
	}

	public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
		if (newInput == null) {
			if (this.fManager != null) {
				this.fManager.cancel(oldInput);
			}
			return;
		}
		this.fManager = new DeferredTreeContentManager((AbstractTreeViewer) viewer);
		this.fManager.addUpdateCompleteListener(getCompletionJobListener());
	}

	private IJobChangeListener getCompletionJobListener() {
		return new JobChangeAdapter() {

		};
	}

	public Object[] getElements(final Object inputElement) {
		return getChildren(inputElement);
	}

	public Object[] getChildren(final Object parentElement) {
		if (parentElement instanceof IDeferredWorkbenchAdapter) {
			return this.fManager.getChildren(parentElement);
		} else if (parentElement instanceof BindingNode) {
			final BindingNode[] retVal = ((BindingNode) parentElement).getContents();
			if (retVal == null) {
				return this.fManager.getChildren(parentElement);
			}
		} else if (parentElement instanceof Collection< ? >) {
			return ((Collection< ? >) parentElement).toArray();
		}
		return EMPTY;
	}

	public boolean hasChildren(final Object element) {
		if (element instanceof IDeferredWorkbenchAdapter) {
			return this.fManager.mayHaveChildren(element);
		} else if (element instanceof BindingNode) {
			return ((BindingNode) element).hasContents();
		}
		return false;
	}

	public Object getParent(final Object element) {
		if (element instanceof BindingNode) {
			return ((BindingNode) element).getParent();
		}
		return EMPTY;
	}
}
