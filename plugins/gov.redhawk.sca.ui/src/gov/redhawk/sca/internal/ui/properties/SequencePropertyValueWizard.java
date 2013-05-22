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
package gov.redhawk.sca.internal.ui.properties;

import gov.redhawk.model.sca.ScaAbstractProperty;

import org.eclipse.jface.wizard.Wizard;

/**
 * 
 */
public class SequencePropertyValueWizard extends Wizard {

	private SequencePropertyValueWizardPage page;
	private final ScaAbstractProperty< ? > property;

	public SequencePropertyValueWizard(final ScaAbstractProperty< ? > property) {
		this.property = property;
		setNeedsProgressMonitor(false);
		setWindowTitle("Edit Property Value");
	}

	@Override
	public void addPages() {
		this.page = new SequencePropertyValueWizardPage(this.property);
		this.addPage(this.page);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean performFinish() {
		return true;
	}

	public ScaAbstractProperty< ? > getProperty() {
		return this.property;
	}

}
