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
package gov.redhawk.prf.ui.wizard;

import gov.redhawk.sca.properties.IPropertiesProviderDescriptor;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.Wizard;

/**
 * 
 */
public class BrowsePropertiesWizard extends Wizard {

	private final List<IPropertiesProviderDescriptor> descriptors;

	private final BrowsePropertiesWizardPage page;

	public BrowsePropertiesWizard(final List<IPropertiesProviderDescriptor> descriptors) {

		this.descriptors = descriptors;
		this.page = new BrowsePropertiesWizardPage(this.descriptors);
		this.setWindowTitle("Browse Properties");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addPages() {
		this.addPage(this.page);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean performFinish() {
		return true;
	}

	/**
	 * 
	 * @return
	 */
	public List<EObject> getProperties() {
		return this.page.getProperties();
	}
}
