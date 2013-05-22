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
package gov.redhawk.ui.views.domainbrowser.view;

import org.eclipse.jface.wizard.Wizard;

/**
 * The Class HostEntryDialog.
 * @since 7.0
 */
public class DomainEntryWizard extends Wizard {

	private final DomainEntryWizardPage wizardPage = new DomainEntryWizardPage("ENTRY_PAGE");
	private String domainName = null;
	private String nameService = null;
	
	public DomainEntryWizard() {
		this.setNeedsProgressMonitor(false);
		this.setHelpAvailable(false);
	}

	@Override
	public void addPages() {
		this.addPage(this.wizardPage);
	}

	@Override
	public boolean performFinish() {
		this.domainName = this.wizardPage.getDomainName();
		this.nameService = this.wizardPage.getNameServiceInitRef();
		return true;
	}

	public String getNameServiceInitRef() {
		return this.nameService;
	}

	public String getDomainName() {
		return this.domainName;
	}
}
