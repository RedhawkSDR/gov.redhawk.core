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
package gov.redhawk.prf.ui.editor.page;

import gov.redhawk.common.ui.doc.HelpConstants;
import gov.redhawk.prf.internal.ui.editor.PropertiesBlock;
import gov.redhawk.ui.editor.SCAFormEditor;
import gov.redhawk.ui.editor.ScaFormPage;
import mil.jpeojtrs.sca.prf.Range;

import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.menus.IMenuService;

// TODO: Auto-generated Javadoc
/**
 * The Class PropertiesFormPage.
 */
public class PropertiesFormPage extends ScaFormPage implements IViewerProvider {

	/** The Constant PAGE_ID. */
	public static final String PAGE_ID = "properties"; //$NON-NLS-1$
	/**
	 * The toolbar contribution ID
	 * @since 2.0
	 */
	public static final String TOOLBAR_ID = "gov.redhawk.prf.ui.editor.page.toolbar";
	private final PropertiesBlock fBlock;

	/**
	 * Instantiates a new properties form page.
	 * 
	 * @param editor the editor
	 * @param propertiesResource the properties resource
	 */
	public PropertiesFormPage(final SCAFormEditor editor) {
		super(editor, PropertiesFormPage.PAGE_ID, "Properties");
		this.fBlock = new PropertiesBlock(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getHelpResource() {
		return HelpConstants.reference_editors_prf_propertiesPage;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void createFormContent(final IManagedForm managedForm) {
		final ScrolledForm form = managedForm.getForm();
		form.setText("Properties");
		// TODO
		// form.setImage(PDEPlugin.getDefault().getLabelProvider().get(PDEPluginImages.DESC_EXTENSIONS_OBJ));
		this.fBlock.createContent(managedForm);

		PlatformUI.getWorkbench().getHelpSystem().setHelp(form.getBody(), HelpConstants.reference_editors_prf_propertiesPage);
		
		super.createFormContent(managedForm);
		final ToolBarManager manager = (ToolBarManager) form.getToolBarManager();
		final IMenuService service = (IMenuService) getSite().getService(IMenuService.class);
		service.populateContributionManager(manager, "toolbar:" + PropertiesFormPage.TOOLBAR_ID);
		manager.update(true);
	}

	/**
	 * {@inheritDoc}
	 */
	public Viewer getViewer() {
		return this.fBlock.getSection().getStructuredViewerPart().getViewer();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void refresh(final Resource resource) {
		this.fBlock.refresh(resource);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean selectReveal(final Object object) {
		if (object instanceof Range) {
			return super.selectReveal(((Range) object).eContainer());
		}
		return super.selectReveal(object);
	}
}
