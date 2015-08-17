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
package gov.redhawk.scd.internal.ui.editor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import gov.redhawk.scd.ui.provider.PortsEditorScdItemProviderAdapterFactory;
import gov.redhawk.ui.editor.TreeSection;
import mil.jpeojtrs.sca.scd.AbstractPort;
import mil.jpeojtrs.sca.scd.Ports;
import mil.jpeojtrs.sca.scd.Provides;
import mil.jpeojtrs.sca.scd.ScdFactory;
import mil.jpeojtrs.sca.scd.ScdPackage;
import mil.jpeojtrs.sca.scd.SoftwareComponent;
import mil.jpeojtrs.sca.scd.Uses;

public class PortsSection extends TreeSection {

	private final String DEFAULT_PORT_NAME = "dataFloat";
	private final String DEFAULT_PORT_INTERFACE = "IDL:BULKIO/dataFloat:1.0";

	private static final int BUTTON_ADD = 0;
	private static final int BUTTON_REMOVE = 1;

	private AdapterFactory adapterFactory;
	private AdapterFactoryContentProvider contentProvider;
	private AdapterFactoryLabelProvider labelProvider;
	private TreeViewer fViewer;
	private Ports ports;

	private static class IndexedColumnLabelProvider extends ColumnLabelProvider {
		private final ITableLabelProvider labelProvider;
		private final int column;

		public IndexedColumnLabelProvider(ITableLabelProvider labelProvider, int column) {
			this.labelProvider = labelProvider;
			this.column = column;
		}

		@Override
		public Image getImage(Object element) {
			return labelProvider.getColumnImage(element, column);
		}

		@Override
		public String getText(Object element) {
			return labelProvider.getColumnText(element, column);
		}
	}
	
	public PortsSection(PortsBlock block, Composite parent) {
		super(block.getPage(), parent, Section.DESCRIPTION, new String[] { "Add", "Remove" });
	}

	@Override
	protected void createClient(Section section, FormToolkit toolkit) {
		final Composite container = this.createClientContainer(section, 2, toolkit);
		this.createViewerPartControl(container, SWT.SINGLE, 2, toolkit);

		fViewer = getTreePart().getTreeViewer();
		contentProvider = new AdapterFactoryContentProvider(getAdapterFactory());
		labelProvider = new AdapterFactoryLabelProvider(getAdapterFactory());
		fViewer.setContentProvider(contentProvider);
		fViewer.getTree().setHeaderVisible(true);
		fViewer.getTree().setLinesVisible(true);
		fViewer.addFilter(new ViewerFilter() {
			
			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				element = AdapterFactoryEditingDomain.unwrap(element);
				if (element instanceof Uses) {
					return !((Uses) element).isBiDirectional();
				}
				return true;
			}
		});

		TreeViewerColumn column = new TreeViewerColumn(fViewer, SWT.DEFAULT);
		column.getColumn().setText("Name");
		column.getColumn().setWidth(200);
		column.setLabelProvider(new IndexedColumnLabelProvider(labelProvider, 0));

		column = new TreeViewerColumn(fViewer, SWT.DEFAULT);
		column.getColumn().setText("Interface");
		column.getColumn().setWidth(200);
		column.setLabelProvider(new IndexedColumnLabelProvider(labelProvider, 1));

		toolkit.paintBordersFor(container);
		section.setClient(container);
		section.setDescription("Define ports within the following section.");
		section.setText("All Ports");
	}

	private AdapterFactory getAdapterFactory() {
		if (adapterFactory == null) {
			adapterFactory = new PortsEditorScdItemProviderAdapterFactory();
		}
		return adapterFactory;
	}

	@Override
	public void dispose() {
		super.dispose();
		if (adapterFactory != null) {
			((IDisposable) adapterFactory).dispose();
			adapterFactory = null;
		}
		if (contentProvider != null) {
			contentProvider.dispose();
			contentProvider = null;
		}
		if (labelProvider != null) {
			labelProvider.dispose();
			labelProvider = null;
		}
	}

	@Override
	public void refresh(Resource resource) {
		SoftwareComponent scd = null;
		if (resource != null) {
			scd = SoftwareComponent.Util.getSoftwareComponent(resource);
			ports = scd.getComponentFeatures().getPorts();
		} else {
			ports = null;
		}
		if (fViewer != null) {
			fViewer.setInput(ports);
		}
	
		super.refresh(resource);

		IStructuredSelection selection = fViewer.getStructuredSelection();
		if (selection.isEmpty()) {
			selectDefault();
		} else {
			fViewer.setSelection(selection);
		}
	}

	@Override
	protected void selectionChanged(IStructuredSelection selection) {
		if (selection.isEmpty()) {
			selectDefault();
		} else {
			getTreePart().setButtonEnabled(BUTTON_REMOVE, true);
			getPage().setSelection(selection);
		}
	}

	private void selectDefault() {
		TreeItem[] items = fViewer.getTree().getItems();
		if (items.length == 0) {
			getTreePart().setButtonEnabled(BUTTON_REMOVE, false);
			return;
		}
		fViewer.setSelection(new StructuredSelection(items[0].getData()));
	}

	@Override
	protected void buttonSelected(int index) {
		switch (index) {
		case BUTTON_ADD:
			handleAddPort();
			break;
		case BUTTON_REMOVE:
			handleRemovePort();
		default:
			break;
		}
	}

	private void handleAddPort() {
		if (ports != null) {
			EditingDomain domain = getPage().getEditingDomain();
			Provides provides = ScdFactory.eINSTANCE.createProvides();
			provides.setName(getDefaultPortName());
			provides.setRepID(DEFAULT_PORT_INTERFACE);
			Command addCommand = AddCommand.create(domain, ports, ScdPackage.Literals.PORTS__PROVIDES, provides);
			domain.getCommandStack().execute(addCommand);
		}
	}

	private String getDefaultPortName() {
		Set<String> portNameList = new HashSet<String>();
		for (AbstractPort port : ports.getAllPorts()) {
			portNameList.add(port.getName());
		}

		String defaultName = DEFAULT_PORT_NAME;

		int nameIncrement = 1;
		while (portNameList.contains(defaultName)) {
			defaultName = DEFAULT_PORT_NAME + "_" + nameIncrement++;
		}

		return defaultName;
	}

	private void handleRemovePort() {
		IStructuredSelection selection = (IStructuredSelection) fViewer.getSelection();
		EditingDomain domain = getPage().getEditingDomain();
		List<Object> removed = new ArrayList<Object>();
		for (Object item : selection.toList()) {
			removed.add(item);
			AbstractPort port = (AbstractPort) AdapterFactoryEditingDomain.unwrap(item);
			if (port.isBiDirectional()) {
				removed.add(port.getSibling());
			}
		}
		Command removeCommand = RemoveCommand.create(domain, removed);
		domain.getCommandStack().execute(removeCommand);
	}
}
