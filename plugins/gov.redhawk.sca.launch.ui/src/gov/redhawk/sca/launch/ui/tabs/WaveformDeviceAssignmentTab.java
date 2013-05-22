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
package gov.redhawk.sca.launch.ui.tabs;

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.sca.launch.ScaLaunchConfigurationConstants;
import gov.redhawk.sca.launch.ui.ScaUIImages;
import gov.redhawk.sca.ui.DeviceAssignmentComposite;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import mil.jpeojtrs.sca.sad.SoftwareAssembly;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

/**
 * 
 */
public class WaveformDeviceAssignmentTab extends AbstractLaunchConfigurationTab {

	private Image deviceImage;
	private DeviceAssignmentComposite deviceAssignmentComposite;
	private boolean disposed;
	private SoftwareAssembly softwareAssembly;
	private ScaDomainManager domainManager;

	public WaveformDeviceAssignmentTab(final WaveformMainTab mainTab) {
		this.deviceImage = ScaUIImages.DESC_DEVICE_TAB.createImage();
	}

	public void setSoftwareAssembly(final SoftwareAssembly softwareAssembly) {
		this.softwareAssembly = softwareAssembly;
		if (this.deviceAssignmentComposite != null) {
			this.deviceAssignmentComposite.setSoftwareAssembly(softwareAssembly);
		}
		updateLaunchConfigurationDialog();
	}

	public void setScaDomainManager(final ScaDomainManager domainManager) {
		this.domainManager = domainManager;
		if (this.deviceAssignmentComposite != null) {
			this.deviceAssignmentComposite.setScaDomainManager(domainManager);
		}
		updateLaunchConfigurationDialog();
	}

	@Override
	public Image getImage() {
		return this.deviceImage;
	}

	@Override
	public void dispose() {
		this.disposed = true;
		if (this.deviceImage != null) {
			this.deviceImage.dispose();
			this.deviceImage = null;
		}
		super.dispose();
	}

	/**
	 * {@inheritDoc}
	 */
	public void createControl(final Composite parent) {
		this.deviceAssignmentComposite = new DeviceAssignmentComposite(parent, SWT.None);
		this.deviceAssignmentComposite.setScaDomainManager(this.domainManager);
		this.deviceAssignmentComposite.setSoftwareAssembly(this.softwareAssembly);
		this.deviceAssignmentComposite.addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(final PropertyChangeEvent evt) {
				if (WaveformDeviceAssignmentTab.this.disposed) {
					return;
				}
				updateLaunchConfigurationDialog();
			}
		});
		setControl(this.deviceAssignmentComposite);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setDefaults(final ILaunchConfigurationWorkingCopy configuration) {
		if (this.deviceAssignmentComposite != null) {
			this.deviceAssignmentComposite.setDefaults();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void initializeFrom(final ILaunchConfiguration configuration) {
		try {
			final Map< ? , ? > deviceAssignment = configuration.getAttribute(ScaLaunchConfigurationConstants.ATT_WAVEFORM_DEVICE_ASSIGNMENT, Collections.emptyMap());
			this.deviceAssignmentComposite.restoreSettings((Map<String, String>) deviceAssignment);
		} catch (final CoreException e) {
			// PASS
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public void performApply(final ILaunchConfigurationWorkingCopy configuration) {
		final Map<String, String> deviceAssignment = new HashMap<String, String>();
		this.deviceAssignmentComposite.storeSettings(deviceAssignment);
		for (final Iterator<Entry<String, String>> i = deviceAssignment.entrySet().iterator(); i.hasNext();) {
			if (i.next().getValue() == null) {
				i.remove();
			}
		}
		if (deviceAssignment.isEmpty()) {
			configuration.removeAttribute(ScaLaunchConfigurationConstants.ATT_WAVEFORM_DEVICE_ASSIGNMENT);
		} else {
			configuration.setAttribute(ScaLaunchConfigurationConstants.ATT_WAVEFORM_DEVICE_ASSIGNMENT, deviceAssignment);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public String getName() {
		return "Device Assignment";
	}

}
