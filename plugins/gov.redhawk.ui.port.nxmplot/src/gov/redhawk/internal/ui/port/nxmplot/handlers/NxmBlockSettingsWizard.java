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

import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

/**
 * @noreference This class is not intended to be referenced by clients.
 * @since 4.4
 */
public class NxmBlockSettingsWizard extends Wizard {

	private static class BlockWizardPage extends WizardPage {
		private DataBindingContext dataBindingContext;
		private WizardPageSupport support;
		private INxmBlock[] nxmBlocks;
		/** key=INxmBlock, value=Settings for that block. */
		private ConcurrentHashMap<INxmBlock, Object> nxmBlockToSettingsMap = new ConcurrentHashMap<INxmBlock, Object>();
		
		protected BlockWizardPage(@NonNull String sourceInfo) {
			super("nxmBlockSettingspage", "Adjust Plot Settings for Source", null);
			setDescription("Adjust/override plot settings for source Port: " + sourceInfo);
		}

		@Override
		public void createControl(Composite parent) {
			Composite composite = new Composite(parent, SWT.None);
			composite.setLayout(new GridLayout(1, false));
			dataBindingContext = new DataBindingContext();
			
			if (nxmBlocks != null) {
				for (INxmBlock nxmBlock : nxmBlocks) {
					if (nxmBlock.hasControls()) {
						Group group = new Group(composite, SWT.None);
						group.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
						group.setText(nxmBlock.getLabel());
						Object settings = nxmBlock.getSettings();
						nxmBlockToSettingsMap.put(nxmBlock, settings);
						nxmBlock.createControls(group, settings, dataBindingContext);
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
	
	private BlockWizardPage page;

	public NxmBlockSettingsWizard(@NonNull String sourceInfo) {
		super();
		this.page = new BlockWizardPage(sourceInfo);
	}
	
	@Override
	public void addPages() {
		addPage(page);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		return true;
	}
	
	public void setNxmBlocks(INxmBlock... blocks) {
		page.nxmBlocks = blocks;
	}
	
	public Object getSettings(INxmBlock block) {
		return page.nxmBlockToSettingsMap.get(block);
	}

}
