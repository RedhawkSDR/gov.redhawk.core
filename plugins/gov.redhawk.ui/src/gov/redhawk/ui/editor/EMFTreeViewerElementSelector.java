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
package gov.redhawk.ui.editor;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.PlatformUI;

/**
 * Implementation of EMFViewerElementSelector for TreeViewer
 */
public class EMFTreeViewerElementSelector extends EMFViewerElementSelector {

	private final TreeViewer treeViewer;

	/**
	 * Instantiates a new EMFTreeViewerElementSelector.
	 */
	public EMFTreeViewerElementSelector(final TreeViewer treeViewer) {
		super(treeViewer);
		this.treeViewer = treeViewer;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeElement(final Object obj) {
		TreeItem prevItem = null;
		final int numItems = this.treeViewer.getTree().getItems().length;
		for (final TreeItem item : Arrays.asList(this.treeViewer.getTree().getItems())) {
			if (this.getItemObject(item) == obj) {
				if (prevItem != null) {
					selectElement(this.getItemObject(prevItem));
				} else {
					final int index = this.treeViewer.getTree().indexOf(item);
					if (index != numItems - 1) {
						selectElement(this.getItemObject(this.treeViewer.getTree().getItem(index + 1)));
					} else {
						this.treeViewer.refresh();
					}
				}
				this.refreshViewer();
				break;
			}
			prevItem = item;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void selectElement(final Object obj) {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				EMFTreeViewerElementSelector.this.treeViewer.refresh();
				final Tree tree = EMFTreeViewerElementSelector.this.treeViewer.getTree();
				final List<TreeItem> items = Arrays.asList(tree.getItems());

				for (final TreeItem item : items) {
					if (getItemObject(item) == obj) {
						EMFTreeViewerElementSelector.this.treeViewer.setSelection(new StructuredSelection(
						        item.getData()));
					}
				}
			}
		});
	}
}
