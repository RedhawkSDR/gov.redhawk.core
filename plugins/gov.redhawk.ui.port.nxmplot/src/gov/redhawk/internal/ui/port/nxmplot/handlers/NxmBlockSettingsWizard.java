/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.internal.ui.port.nxmplot.handlers;

import gov.redhawk.ui.port.nxmplot.INxmBlock;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * 
 */
public class NxmBlockSettingsWizard extends Wizard {

	private static class BlockWizardPage extends WizardPage {
		private DataBindingContext dataBindingContext;
		private WizardPageSupport support;
		private INxmBlock< ? >[] nxmBlocks;
		
		protected BlockWizardPage() {
			super("nxmBlockSettingspage", "Adjust Plot Settings", null);
			setDescription("Adjust plot settings.");
		}

		@Override
		public void createControl(Composite parent) {
			Composite composite = new Composite(parent, SWT.None);
			dataBindingContext = new DataBindingContext();
			
			if (nxmBlocks != null) {
				for (INxmBlock<?> nxmBlock : nxmBlocks) {
					if (nxmBlock.hasControls()) {
						Label label = new Label(composite, SWT.None);
						label.setText(nxmBlock.getLabel());
						nxmBlock.createControls(composite, nxmBlock.getSettings(), dataBindingContext);
					}
				} // end for loop
			}
			
			support = WizardPageSupport.create(this, dataBindingContext);
			setControl(composite);
		}
		
		@Override
		public void dispose() {
			super.dispose();
			support.dispose();
			dataBindingContext.dispose();
		}
	} // end class BlockWizardPage
	
	private BlockWizardPage page = new BlockWizardPage();
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		return false;
	}

}
