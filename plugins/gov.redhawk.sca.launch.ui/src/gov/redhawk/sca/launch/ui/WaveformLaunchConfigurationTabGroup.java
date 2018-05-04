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
package gov.redhawk.sca.launch.ui;

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.sca.launch.ui.tabs.WaveformDeviceAssignmentTab;
import gov.redhawk.sca.launch.ui.tabs.WaveformMainTab;
import gov.redhawk.sca.launch.ui.tabs.WaveformPropertiesTab;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;

/**
 * @since 8.0
 */
public class WaveformLaunchConfigurationTabGroup extends AbstractLaunchConfigurationTabGroup {

	public WaveformLaunchConfigurationTabGroup() {
	}

	@Override
	public void createTabs(final ILaunchConfigurationDialog dialog, final String mode) {
		final WaveformMainTab mainTab = new WaveformMainTab();
		final WaveformPropertiesTab propTabs = new WaveformPropertiesTab();
		final WaveformDeviceAssignmentTab deviceAssignment = new WaveformDeviceAssignmentTab(mainTab);
		mainTab.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(final PropertyChangeEvent evt) {
				if (WaveformMainTab.DOMAIN.equals(evt.getPropertyName())) {
					deviceAssignment.setScaDomainManager((ScaDomainManager) evt.getNewValue());
				}
			}

		});
		final ILaunchConfigurationTab[] tabs = new ILaunchConfigurationTab[] {
		        mainTab, propTabs, deviceAssignment, new CommonTab()
		};
		setTabs(tabs);
	}
}
