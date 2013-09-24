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
package gov.redhawk.sca.internal.ui.wizards;

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.sca.ui.DeviceAssignmentComposite;

import java.util.HashMap;
import java.util.Map;

import mil.jpeojtrs.sca.sad.SoftwareAssembly;

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import CF.DeviceAssignmentType;

public class DeviceAssignmentWizardPage extends WizardPage {
	private final ScaDomainManager domain;
	private String waveformId;
	private DeviceAssignmentComposite deviceAssignmentComposite;

	public DeviceAssignmentWizardPage(final String pageName, final ScaDomainManager domain) {
		super(pageName);
		this.domain = domain;
		setTitle("Assign components to devices");
		setDescription("Select device assignment for components.");
	}

	@Override
	public void createControl(final Composite root) {
		this.deviceAssignmentComposite = new DeviceAssignmentComposite(root, SWT.None);
		this.deviceAssignmentComposite.setScaDomainManager(this.domain);
		setControl(this.deviceAssignmentComposite);
	}

	public DeviceAssignmentType[] getDeviceAssignment() {
		return this.deviceAssignmentComposite.getDeviceAssignment();
	}

	public void init(final SoftwareAssembly waveformFactory) {
		this.waveformId = waveformFactory.getId();
		this.deviceAssignmentComposite.setSoftwareAssembly(waveformFactory);
		restoreSettings();
	}

	private void restoreSettings() {
		final IDialogSettings settings = getDialogSettings().getSection(getName());
		if (settings != null) {
			final IDialogSettings waveformSettings = settings.getSection(this.waveformId);
			final Map<String, String> currentDeviceMap = new HashMap<String, String>();
			this.deviceAssignmentComposite.storeSettings(currentDeviceMap);
			final Map<String, String> newDeviceMap = new HashMap<String, String>();
			if (waveformSettings != null) {
				for (final Map.Entry<String, String> entry : currentDeviceMap.entrySet()) {
					waveformSettings.get(entry.getKey());
					newDeviceMap.put(entry.getKey(), waveformSettings.get(entry.getKey()));
				}
			}
			this.deviceAssignmentComposite.restoreSettings(newDeviceMap);
		}
	}

}
