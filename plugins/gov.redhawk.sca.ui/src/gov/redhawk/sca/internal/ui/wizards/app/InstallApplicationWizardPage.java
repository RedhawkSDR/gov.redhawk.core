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
package gov.redhawk.sca.internal.ui.wizards.app;

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.sca.ui.parts.FormFilteredTree;
import gov.redhawk.sca.ui.wizards.InstallApplicationContentProvider;
import gov.redhawk.sca.ui.wizards.WizardSadItemProviderAdapterFactory;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.PatternFilter;

class InstallApplicationWizardPage extends WizardPage {
	private CheckboxTreeViewer viewer;
	private final ScaDomainManager domMgr;

	protected InstallApplicationWizardPage(final ScaDomainManager domMgr) {
		super("installApplicationWizardPage", "Install Application", null);
		setDescription("Select the application(s) to install.");
		this.domMgr = domMgr;
	}

	public void createControl(final Composite parent) {
		final FormFilteredTree filtered = new FormFilteredTree(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER, new PatternFilter()) {
			@Override
			protected TreeViewer doCreateTreeViewer(final Composite parent, final int style) {
				InstallApplicationWizardPage.this.viewer = new CheckboxTreeViewer(parent, style);
				InstallApplicationWizardPage.this.viewer.setContentProvider(new InstallApplicationContentProvider());
				InstallApplicationWizardPage.this.viewer.setLabelProvider(new AdapterFactoryLabelProvider(new WizardSadItemProviderAdapterFactory()));
				InstallApplicationWizardPage.this.viewer.setComparator(new ViewerComparator());
				InstallApplicationWizardPage.this.viewer.addCheckStateListener(new ICheckStateListener() {
					public void checkStateChanged(final CheckStateChangedEvent event) {
						setPageComplete(InstallApplicationWizardPage.this.viewer.getCheckedElements().length > 0);
					}
				});

				InstallApplicationWizardPage.this.viewer.setInput(InstallApplicationWizardPage.this.domMgr);
				return InstallApplicationWizardPage.this.viewer;
			}
		};

		this.setPageComplete(false);

		this.setControl(filtered.getViewer().getControl());
	}

	public Object[] getCheckedElements() {
		return this.viewer.getCheckedElements();
	}

}
