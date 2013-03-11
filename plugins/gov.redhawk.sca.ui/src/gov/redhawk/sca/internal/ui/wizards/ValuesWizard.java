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
package gov.redhawk.sca.internal.ui.wizards;

import mil.jpeojtrs.sca.prf.PropertyValueType;

import org.eclipse.jface.wizard.Wizard;

/**
 * 
 */
public class ValuesWizard extends Wizard {

	private ValuesWizardPage valuesPage;
	private String[] input;
	private final PropertyValueType type;

	public ValuesWizard(final PropertyValueType type) {
		this.setNeedsProgressMonitor(false);
		this.setWindowTitle("Values");
		this.type = type;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addPages() {
		this.valuesPage = new ValuesWizardPage(this.type);
		this.valuesPage.setInput(this.input);
		this.addPage(this.valuesPage);
		super.addPages();
	}

	public void setInput(final String[] input) {
		this.input = input;
		if (this.valuesPage != null) {
			this.valuesPage.setInput(input);
		}
	}

	public String[] getValues() {
		return this.valuesPage.getValues();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean performFinish() {
		return true;
	}

}
