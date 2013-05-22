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
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.PlatformUI;

/**
 * Implementation of EMFViewerElementSelector for TableViewer
 */
public class EMFTableViewerElementSelector extends EMFViewerElementSelector {

	private final TableViewer tableViewer;

	/**
	 * Instantiates a new EMFTableViewerElementSelector.
	 */
	public EMFTableViewerElementSelector(final TableViewer tableViewer) {
		super(tableViewer);
		this.tableViewer = tableViewer;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeElement(final Object obj) {
		TableItem prevItem = null;
		final List<TableItem> items = Arrays.asList(this.tableViewer.getTable().getItems());
		for (final TableItem item : items) {
			if (this.getItemObject(item) == obj) {
				if (prevItem != null) {
					selectElement(this.getItemObject(prevItem));
				} else if (items.size() > 1) {
					final int index = this.tableViewer.getTable().indexOf(item);
					selectElement(this.getItemObject(this.tableViewer.getTable().getItem(index + 1)));
				}
			}
			prevItem = item;
		}
		this.refreshViewer();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void selectElement(final Object obj) {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			public void run() {
				if (!EMFTableViewerElementSelector.this.tableViewer.getTable().isDisposed()) {
					EMFTableViewerElementSelector.this.tableViewer.refresh();
					final Table table = EMFTableViewerElementSelector.this.tableViewer.getTable();
					final List<TableItem> items = Arrays.asList(table.getItems());

					for (final TableItem item : items) {
						if (getItemObject(item) == obj) {
							EMFTableViewerElementSelector.this.tableViewer.setSelection(new StructuredSelection(
							        item.getData()));
						}
					}
				}
			}
		});

	}

}
