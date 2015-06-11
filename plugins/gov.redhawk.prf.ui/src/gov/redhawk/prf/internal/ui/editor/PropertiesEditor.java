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
package gov.redhawk.prf.internal.ui.editor;

import gov.redhawk.prf.ui.PrfUiPlugin;
import gov.redhawk.prf.ui.editor.page.PropertiesFormPage;
import gov.redhawk.ui.editor.SCAFormEditor;

import java.util.ArrayList;
import java.util.List;

import mil.jpeojtrs.sca.prf.Properties;
import mil.jpeojtrs.sca.prf.provider.PrfItemProviderAdapterFactory;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

/**
 * The editor for PRF files. Shows both a form page for editing properties as well as an XML editor page.
 */
public class PropertiesEditor extends SCAFormEditor {

	/** The Constant PROP_RESOURCE_KEY. */
	public static final String PROP_RESOURCE_KEY = "prfResource";

	private TextEditor textEditor;

	private PropertiesFormPage propertiesFormPage;

	/**
	 * This creates a model editor.
	 */
	public PropertiesEditor() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void addPages() {
		try {
			addPropertiesFormPage();
			addXmlEditorPage();
		} catch (final PartInitException e) {
			PrfUiPlugin.logException(e);
		}
	}

	/**
	 * Adds the source page.
	 * 
	 * @throws PartInitException the part init exception
	 */
	private void addXmlEditorPage() throws PartInitException {
		// StructuredTextEditors only work on workspace entries
		// because org.eclipse.wst.sse.core.FileBufferModelManager:bufferCreated()
		// assumes that the editor input is in the workspace.
		if (getEditorInput() instanceof FileEditorInput) {
			try {
				this.textEditor = new org.eclipse.wst.sse.ui.StructuredTextEditor();
			} catch (final NoClassDefFoundError e) {
				// PASS
			}
		}
		if (this.textEditor == null) {
			this.textEditor = new TextEditor();
		}
		final int newPage = addPage(this.textEditor, getEditorInput(), getMainResource());
		this.setPageText(newPage, getEditorInput().getName());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(final IEditorSite site, final IEditorInput input) throws PartInitException {
		super.init(site, input);
		this.setPartName(getEditorInput().getName());
	}

	/**
	 * Adds the form page.
	 * 
	 * @throws PartInitException the part init exception
	 */
	private void addPropertiesFormPage() throws PartInitException {
		this.propertiesFormPage = new PropertiesFormPage(this);
		addPage(this.propertiesFormPage);
		this.propertiesFormPage.setInput(getMainResource());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected IContentOutlinePage createContentOutline() {
		return new PropertiesOutlinePage(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AdapterFactory getSpecificAdapterFactory() {
		return new PrfItemProviderAdapterFactory();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getEditingDomainId() {
		return "gov.redhawk.prf.editingDomain";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void gotoMarker(final IMarker marker) {
		try {
			final Object uri = marker.getAttribute(EValidator.URI_ATTRIBUTE);
			if (uri != null) {
				final EObject obj = this.getEditingDomain().getResourceSet().getEObject(URI.createURI(uri.toString()), true);
				if (this.propertiesFormPage.selectReveal(obj)) {
					this.setActivePage(this.propertiesFormPage.getId());
				}
			} else {
				this.setActivePage(this.propertiesFormPage.getId());
			}
		} catch (final CoreException e) {
			StatusManager.getManager().handle(new Status(IStatus.WARNING, PrfUiPlugin.PLUGIN_ID, "Problems occured while trying to go to problem marker.", e),
			        StatusManager.SHOW | StatusManager.LOG);
		}
		super.gotoMarker(marker);
	}

	@Override
	public List<Object> getOutlineItems() {
		final List<Object> myList = new ArrayList<Object>();
		myList.add(Properties.Util.getProperties(getMainResource()));
		return myList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isPersisted(final Resource resource) {
		return getMainResource() == resource && super.isPersisted(resource);
	}

}
