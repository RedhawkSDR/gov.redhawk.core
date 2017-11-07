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

import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.nebula.widgets.xviewer.XViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.ui.views.allocmgr.provider.AllocMgrItemProviderAdapterFactory;

public class AllocMgrView extends ViewPart {

	public static final String ID = "gov.redhawk.ui.views.allocmgr.view";

	private XViewer viewer;
	private AllocMgrItemProviderAdapterFactory adapterFactory;
	private AllocationManager input;

	public AllocMgrView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		Composite viewerComposite = new Composite(parent, SWT.NONE);
		viewerComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());

		viewer = new XViewer(viewerComposite, SWT.NONE, new AllocMgrXViewerFactory());
		viewer.getTree().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		this.adapterFactory = new AllocMgrItemProviderAdapterFactory();
		ITreeContentProvider contentProvider = new AdapterFactoryContentProvider(adapterFactory);
		viewer.setContentProvider(contentProvider);
		IBaseLabelProvider labelProvider = new AdapterFactoryXViewerLabelProvider(viewer, adapterFactory, AllocMgrXViewerFactory.getColumnsToFeatures());
		viewer.setLabelProvider(labelProvider);
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
		input = AllocMgrFactory.eINSTANCE.createAllocationManager();
		AllocationStatus status = AllocMgrFactory.eINSTANCE.createAllocationStatus();
		status.setAllocationID("abc");
		status.setRequestingDomain("REDHAWK_DEV");
		status.setDeviceIOR("IOR:123456");
		status.setDeviceMgrIOR("IOR:234567");
		status.setSourceID("def");
		input.getStatus().add(status);
		status = AllocMgrFactory.eINSTANCE.createAllocationStatus();
		status.setAllocationID("ghi");
		status.setRequestingDomain("REDHAWK_DEV");
		status.setDeviceIOR("IOR:345678");
		status.setDeviceLabel("device label");
		status.setDeviceMgrIOR("IOR:234567");
		status.setDeviceMgrLabel("device mgr label");
		status.setSourceID("jkl");
		input.getStatus().add(status);
		viewer.setInput(input);
	}

	@Override
	public void dispose() {
		super.dispose();
		adapterFactory.dispose();
	}
}
