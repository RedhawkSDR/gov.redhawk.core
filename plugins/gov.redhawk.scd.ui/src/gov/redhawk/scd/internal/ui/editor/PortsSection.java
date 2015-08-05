package gov.redhawk.scd.internal.ui.editor;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import gov.redhawk.ui.editor.TreeSection;
import mil.jpeojtrs.sca.scd.AbstractPort;
import mil.jpeojtrs.sca.scd.SoftwareComponent;
import mil.jpeojtrs.sca.scd.Uses;
import mil.jpeojtrs.sca.scd.provider.ScdItemProviderAdapterFactory;

public class PortsSection extends TreeSection {

	private AdapterFactory adapterFactory;
	private TreeViewer fViewer;
	
	public PortsSection(PortsBlock block, Composite parent) {
		super(block.getPage(), parent, Section.DESCRIPTION, new String[] { "Add", "Remove" });
	}

	@Override
	protected void createClient(Section section, FormToolkit toolkit) {
		final Composite container = this.createClientContainer(section, 2, toolkit);
		this.createViewerPartControl(container, SWT.SINGLE, 2, toolkit);

		fViewer = getTreePart().getTreeViewer();
		fViewer.setContentProvider(new AdapterFactoryContentProvider(getAdapterFactory()));
		fViewer.setLabelProvider(new AdapterFactoryLabelProvider(getAdapterFactory()));
		fViewer.addFilter(new ViewerFilter() {
			
			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				element = AdapterFactoryEditingDomain.unwrap(element);
				if (!(element instanceof AbstractPort)) {
					return false;
				}
				if (element instanceof Uses) {
					return !((Uses) element).isBiDirectional();
				}
				return true;
			}
		});

		toolkit.paintBordersFor(container);
		section.setClient(container);
		section.setDescription("Define ports within the following section.");
		// See Bug # 160554: Set text before text client
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
