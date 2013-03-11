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
package gov.redhawk.ui.editor;

import gov.redhawk.common.ui.editor.FormLayoutFactory;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.MasterDetailsBlock;
import org.eclipse.ui.forms.widgets.Section;

// TODO: Auto-generated Javadoc
/**
 * The Class SCAMasterDetailsBlock.
 */
public abstract class SCAMasterDetailsBlock extends MasterDetailsBlock {

	private final ScaFormPage fPage;
	private ScaSection fSection;
	private Resource inputResource;

	/**
	 * Instantiates a new sCA master details block.
	 * 
	 * @param page the page
	 */
	public SCAMasterDetailsBlock(final ScaFormPage page) {
		this.fPage = page;
	}

	/**
	 * Gets the page.
	 * 
	 * @return the page
	 */
	public ScaFormPage getPage() {
		return this.fPage;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void createMasterPart(final IManagedForm managedForm, final Composite parent) {
		final Composite container = managedForm.getToolkit().createComposite(parent);
		container.setLayout(FormLayoutFactory.createMasterGridLayout(false, 1));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		this.fSection = createMasterSection(managedForm, container);
		this.fSection.refresh(this.inputResource);
		managedForm.addPart(this.fSection);
		final Section section = this.fSection.getSection();
		section.setLayout(FormLayoutFactory.createClearGridLayout(false, 1));
		section.setLayoutData(new GridData(GridData.FILL_BOTH));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void createToolBarActions(final IManagedForm managedForm) {
	}

	/**
	 * Creates the master section.
	 * 
	 * @param managedForm the managed form
	 * @param parent the parent
	 * @return the sca section
	 */
	protected abstract ScaSection createMasterSection(IManagedForm managedForm, Composite parent);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createContent(final IManagedForm managedForm) {
		super.createContent(managedForm);
		managedForm.getForm().getBody().setLayout(FormLayoutFactory.createFormGridLayout(false, 1));
		refresh(this.inputResource);
	}

	/**
	 * Gets the details part.
	 * 
	 * @return the details part
	 */
	public DetailsPart getDetailsPart() {
		return this.detailsPart;
	}

	public void refresh(final Resource resource) {
		this.inputResource = resource;
		if (this.fSection != null) {
			this.fSection.refresh(resource);
		}
	}

}
