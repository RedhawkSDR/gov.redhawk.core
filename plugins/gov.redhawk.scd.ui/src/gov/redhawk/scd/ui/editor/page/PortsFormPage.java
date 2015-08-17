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
package gov.redhawk.scd.ui.editor.page;

import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.menus.IMenuService;

import gov.redhawk.scd.internal.ui.editor.PortsBlock;
import gov.redhawk.ui.editor.SCAFormEditor;
import gov.redhawk.ui.editor.ScaFormPage;

public class PortsFormPage extends ScaFormPage implements IViewerProvider {
	private final PortsBlock fBlock;
	
	/** The Constant PAGE_ID. */
	public static final String PAGE_ID = "ports"; //$NON-NLS-1$

	/** The toolbar contribution ID */
	public static final String TOOLBAR_ID = "gov.redhawk.ide.spd.internal.ui.editor.overview.toolbar";

	public PortsFormPage(SCAFormEditor editor) {
		super(editor, PortsFormPage.PAGE_ID, "Ports");
		fBlock = new PortsBlock(this);
	}

	@Override
	protected void createFormContent(IManagedForm managedForm) {
		final ScrolledForm form = managedForm.getForm();
		form.setText("Ports");

		this.fBlock.createContent(managedForm);

		final ToolBarManager manager = (ToolBarManager) form.getToolBarManager();
		super.createFormContent(managedForm);
		final IMenuService service = (IMenuService) getSite().getService(IMenuService.class);
		service.populateContributionManager(manager, "toolbar:" + PortsFormPage.TOOLBAR_ID);
		manager.update(true);
	}

	@Override
	protected void refresh(Resource resource) {
		this.fBlock.refresh(resource);
	}

	@Override
	public Viewer getViewer() {
		return this.fBlock.getSection().getStructuredViewerPart().getViewer();
	}

}
