package gov.redhawk.scd.internal.ui.editor;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import gov.redhawk.ui.editor.TreeSection;
import mil.jpeojtrs.sca.scd.SoftwareComponent;
import mil.jpeojtrs.sca.scd.Uses;
import mil.jpeojtrs.sca.scd.provider.ScdItemProviderAdapterFactory;

public class PortsSection extends TreeSection {

	private AdapterFactory adapterFactory;
	private AdapterFactoryContentProvider contentProvider;
	private AdapterFactoryLabelProvider labelProvider;
	private TreeViewer fViewer;

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
			adapterFactory = new ScdItemProviderAdapterFactory();
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
		}
		if (fViewer != null) {
			if (scd != null) {
				fViewer.setInput(scd.getComponentFeatures().getPorts());
			} else {
				fViewer.setInput(null);
			}
		}
	
		super.refresh(resource);
	}

}
