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
package gov.redhawk.sca.ui.preferences;

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.sca.internal.ui.preferences.ScaDomainConnectionDef;
import gov.redhawk.sca.ui.preferences.DomainSettingModel.ConnectionMode;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.wizard.Wizard;

/**
 * @since 7.0
 */
public class DomainEntryWizard extends Wizard {

	private final DomainEntryWizardPage wizardPage = new DomainEntryWizardPage("ENTRY_PAGE");

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
		return true;
	}

	public void setShowExtraSettings(final boolean showExtraSettings) {
		this.wizardPage.setShowExtraSettings(showExtraSettings);
	}

	public boolean isShowExtraSettings() {
		return this.wizardPage.isShowExtraSettings();
	}

	public void setRegistry(final ScaDomainManagerRegistry registry) {
		final List<String> domainNames = new ArrayList<String>();
		for (final ScaDomainManager domain : registry.getDomains()) {
			domainNames.add(domain.getLabel());
		}
		this.wizardPage.setDomains(domainNames);
	}

	/**
	 * @since 8.0
	 */
	public void setDomains(final List<ScaDomainConnectionDef> domains) {
		final List<String> domainNames = new ArrayList<String>();
		for (final ScaDomainConnectionDef domain : domains) {
			domainNames.add(domain.getLabel());
		}
		this.wizardPage.setDomains(domainNames);
	}

	public String getNameServiceInitRef() {
		return this.wizardPage.getNameServiceInitRef();
	}

	public void setNameServiceInitRef(final String nameServiceInitRef) {
		this.wizardPage.setNameServiceInitRef(nameServiceInitRef);
	}

	public String getDomainName() {
		return this.wizardPage.getDomainName();
	}

	public void setDomainName(final String domainName) {
		this.wizardPage.setDomainName(domainName);
	}

	public ConnectionMode getConnectionMode() {
		return this.wizardPage.getConnectionMode();
	}

	/**
	 * @since 8.0
	 */
	public void setConnectionMode(final boolean connect) {
		ConnectionMode mode = (connect) ? ConnectionMode.AUTO : ConnectionMode.MANUAL;
		this.wizardPage.setConnectionMode(mode);
	}

	/**
	 * @since 10.0
	 */
	public void setEdit(final String localName, final String domainName, final String initRef) {
		this.wizardPage.setEdit(localName, domainName, initRef);
	}

	/**
	 * @since 10.0
	 */
	public void setLocalDomainName(String localName) {
		this.wizardPage.setLocalDomainName(localName);
	}

	/**
	 * @since 10.0
	 */
	public String getLocalDomainName() {
		return this.wizardPage.getLocalDomainName();
	}

}
