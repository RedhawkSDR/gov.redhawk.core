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
package gov.redhawk.ui.views.allocmgr;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.UnwrappingSelectionProvider;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.nebula.widgets.xviewer.XViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.ui.views.allocmgr.actions.ShowDetailsAction;
import gov.redhawk.ui.views.allocmgr.jobs.FetchAllocationManagerJob;
import gov.redhawk.ui.views.allocmgr.jobs.FetchAllocationsJob;
import gov.redhawk.ui.views.allocmgr.provider.AllocMgrItemProviderAdapterFactory;

public class AllocMgrView extends ViewPart implements ITabbedPropertySheetPageContributor {

	public static final String ID = "gov.redhawk.ui.views.allocmgr.view";

	private XViewer viewer;
	private AllocMgrItemProviderAdapterFactory adapterFactory;
	private FetchAllocationsJob fetchAllocStatusesJob;

	private ScaAllocationManager input;

	public AllocMgrView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		// Our top-level composite
		Composite viewerComposite = new Composite(parent, SWT.NONE);
		viewerComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());

		// The viewer
		viewer = new XViewer(viewerComposite, SWT.MULTI, new AllocMgrXViewerFactory());
		viewer.getTree().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		this.adapterFactory = new AllocMgrItemProviderAdapterFactory();
		ITreeContentProvider contentProvider = new AdapterFactoryContentProvider(adapterFactory);
		viewer.setContentProvider(contentProvider);
		IBaseLabelProvider labelProvider = new AdapterFactoryXViewerLabelProvider(viewer, adapterFactory, AllocMgrXViewerFactory.getColumnsToFeatures());
		viewer.setLabelProvider(labelProvider);

		// Event handlers for the viewer
		viewer.addDoubleClickListener(event -> {
			try {
				getSite().getPage().showView(IPageLayout.ID_PROP_SHEET);
			} catch (PartInitException e) {
				IStatus status = new Status(IStatus.ERROR, AllocMgrPlugin.ID, "Unable to open properties view", e);
				ErrorDialog.openError(parent.getShell(), "Error", "Unable to open properties view", status);
			}
		});

		IToolBarManager toolBarMgr = getViewSite().getActionBars().getToolBarManager();
		toolBarMgr.add(new ShowDetailsAction(getSite().getPage()));

		getSite().registerContextMenu(viewer.getMenuManager(), new UnwrappingSelectionProvider(viewer));
		getSite().setSelectionProvider(viewer);
	}

	@Override
	public void setFocus() {
		viewer.getTree().setFocus();
	}

	/**
	 * Set the domain manager whose allocation manager is to be displayed.
	 * @param domMgr
	 */
	public void setDomainManager(ScaDomainManager domMgr) {
		// Tell the adapter factory some (most) labels can be retrieved from our domain manager model's children
		adapterFactory.setDomainManager(domMgr);

		// Create a model of the allocation manager
		input = AllocMgrFactory.eINSTANCE.createScaAllocationManager();
		viewer.setInput(input);

		// Fetch the allocation manager, then the allocation statuses periodically
		Job fetchAllocMgr = new FetchAllocationManagerJob(domMgr, input);
		fetchAllocStatusesJob = new FetchAllocationsJob(domMgr, input);
		fetchAllocMgr.addJobChangeListener(new JobChangeAdapter() {
			@Override
			public void done(IJobChangeEvent event) {
				if (event.getResult().isOK()) {
					fetchAllocStatusesJob.schedule();
				}
			}
		});
		fetchAllocMgr.schedule();
	}

	@Override
	public void dispose() {
		fetchAllocStatusesJob.cancel();
		super.dispose();
		adapterFactory.dispose();
	}

	@Override
	public String getContributorId() {
		return getSite().getId();
	}

	@Override
	public < T > T getAdapter(Class<T> adapter) {
		if (adapter == IPropertySheetPage.class) {
			return adapter.cast(new TabbedPropertySheetPage(this));
		}
		return super.getAdapter(adapter);
	}
}
