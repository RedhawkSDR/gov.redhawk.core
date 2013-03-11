/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.ui.views.monitor.ports;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TreeColumn;

/**
 * @since 3.0
 * 
 */
public class Column {
	private static final String COLUMN_DATA = "redhawk.columnData";
	private final String id;
	private final String name;
	private final String description;
	private final CellLabelProvider labelProvider;

	public Column(final String id, final String name, final CellLabelProvider labelProvider) {
		this(id, name, null, labelProvider);
	}

	public Column(final String id, final String name, final String desc, final CellLabelProvider labelProvider) {
		super();
		this.id = id;
		this.name = name;
		this.labelProvider = labelProvider;
		this.description = desc;
	}

	public String getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	public CellLabelProvider getLabelProvider() {
		return this.labelProvider;
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof Column) {
			return this.id.equals(((Column) obj).id);
		}
		return super.equals(obj);
	}

	public TreeColumn createTreeColumn(final TreeViewer viewer) {
		TreeColumn retVal = findColumn(viewer);
		if (retVal == null) {
			retVal = new TreeColumn(viewer.getTree(), SWT.CENTER);
			final TreeViewerColumn viewerColumn = new TreeViewerColumn(viewer, retVal);
			viewerColumn.setLabelProvider(this.labelProvider);
			retVal.setMoveable(true);
			retVal.setResizable(true);
			retVal.setText(this.name);
			if (this.description != null) {
				retVal.setToolTipText(this.description);
			}
			retVal.setData(Column.COLUMN_DATA, this.id);
		}
		return retVal;
	}

	public TreeColumn findColumn(final TreeViewer viewer) {
		for (final TreeColumn column : viewer.getTree().getColumns()) {
			final String columnId = (String) column.getData(Column.COLUMN_DATA);
			if (this.id.equals(columnId)) {
				return column;
			}
		}
		return null;
	}

	public boolean isIn(final TreeViewer viewer) {
		return findColumn(viewer) != null;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
