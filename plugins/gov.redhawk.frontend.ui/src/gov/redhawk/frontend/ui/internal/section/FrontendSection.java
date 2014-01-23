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
package gov.redhawk.frontend.ui.internal.section;

import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.edit.utils.TunerProperties.TunerStatusAllocationProperties;
import gov.redhawk.frontend.edit.utils.TunerPropertyWrapper;
import gov.redhawk.frontend.ui.internal.FrontEndContentProvider;
import gov.redhawk.frontend.ui.internal.FrontEndLabelProvider;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

public class FrontendSection extends AbstractPropertySection {

	private TreeViewer viewer;
	private TreeColumnLayout treeLayout;

	/**
	 * The Property Sheet Page.
	 */
	protected PropertySheetPage page;

	public FrontendSection() {
	}

	@Override
	public void createControls(Composite parent, final TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		final Composite composite = new Composite(parent, SWT.None);
		viewer = new TreeViewer(composite, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
		viewer.setUseHashlookup(true);
		viewer.getTree().setHeaderVisible(true);
		viewer.getTree().setLinesVisible(true);

		treeLayout = new TreeColumnLayout();
		composite.setLayout(treeLayout);

		GridData layoutData = new GridData();
		layoutData.grabExcessHorizontalSpace = true;
		layoutData.grabExcessVerticalSpace = true;
		layoutData.horizontalAlignment = GridData.FILL;
		viewer.getControl().setLayoutData(layoutData);

		initializeColumns();

		viewer.setContentProvider(new FrontEndContentProvider());
		viewer.setLabelProvider(new FrontEndLabelProvider());
		viewer.setAutoExpandLevel(2);

	}

	@Override
	public boolean shouldUseExtraSpace() {
		return true;
	}

	private void initializeColumns() {
		TreeColumn propertyColumn = new TreeColumn(viewer.getTree(), SWT.None);
		propertyColumn.setWidth(200);
		propertyColumn.setText("Property");
		treeLayout.setColumnData(propertyColumn, new ColumnWeightData(40, 50));

		TreeViewerColumn valueColumn = new TreeViewerColumn(viewer, SWT.None);
		valueColumn.getColumn().setWidth(400);
		valueColumn.getColumn().setText("Value");
		treeLayout.setColumnData(valueColumn.getColumn(), new ColumnWeightData(60, 100));
		final TextCellEditor cellEditor = new TextCellEditor(viewer.getTree());
		valueColumn.setEditingSupport(new EditingSupport(viewer) {

			@Override
			protected void setValue(Object element, final Object value) {
				if (element instanceof TunerPropertyWrapper) {
					TunerPropertyWrapper wrapper = (TunerPropertyWrapper) element;
					TunerStatusAllocationProperties.updateValue(wrapper, value);
				}

				viewer.refresh();
				viewer.setAutoExpandLevel(2);
			}

			@Override
			protected Object getValue(Object element) {
				if (element instanceof TunerPropertyWrapper) {
					TunerPropertyWrapper wrapper = (TunerPropertyWrapper) element;
					Object value = wrapper.getValue();
					return value.toString();
				}
				return "";
			}

			@Override
			protected CellEditor getCellEditor(Object element) {
				return cellEditor;
			}

			@Override
			protected boolean canEdit(Object element) {
				if (element instanceof TunerPropertyWrapper) {
					TunerPropertyWrapper wrapper = (TunerPropertyWrapper) element;

					// If tuner is not allocated, all fields are read only
					String allocID = wrapper.getTuner().getAllocationID();
					if (allocID == null || allocID == "" || allocID.isEmpty()) {
						return false;
					}

					// Return true for editable properties
					String id = wrapper.getName();
					if (isEditable(id)) {
						return true;
					}
				}
				return false;
			}

			private boolean isEditable(Object id) {
				List<String> editableProperties = new ArrayList<String>();
				editableProperties.add(TunerStatusAllocationProperties.AGC.getName());
				editableProperties.add(TunerStatusAllocationProperties.BANDWIDTH.getName());
				editableProperties.add(TunerStatusAllocationProperties.CENTER_FREQUENCY.getName());
				editableProperties.add(TunerStatusAllocationProperties.ENABLED.getName());
				editableProperties.add(TunerStatusAllocationProperties.GAIN.getName());
				editableProperties.add(TunerStatusAllocationProperties.REFERENCE_SOURCE.getName());

				for (String prop : editableProperties) {
					if (id.equals(prop)) {
						return true;
					}
				}
				return false;
			}
		});
	}

	public void setInput(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof StructuredSelection) {
			StructuredSelection sel = (StructuredSelection) selection;
			if (sel.getFirstElement() instanceof TunerStatus) {
				TunerStatus tuner = (TunerStatus) sel.getFirstElement();
				viewer.setInput(tuner);
			}
		}
	}
}
