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
package gov.redhawk.sca.launch.ui.tabs;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.sca.launch.ScaLaunchConfigurationConstants;
import gov.redhawk.sca.launch.ScaLaunchConfigurationUtil;
import gov.redhawk.sca.launch.ui.ScaLauncherActivator;
import gov.redhawk.sca.launch.ui.ScaUIImages;
import gov.redhawk.sca.ui.ScaComponentFactory;
import gov.redhawk.sca.ui.properties.ScaPropertiesAdapterFactory;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import mil.jpeojtrs.sca.util.ScaResourceFactoryUtil;

public class WaveformPropertiesTab extends AbstractLaunchConfigurationTab {

	private Image propImage;
	private TreeViewer viewer;
	private final AdapterFactory adapterFactory;

	/**
	 * The SAD XML model, associated SPD and PRF XMLs, and property overrides
	 * <p/>
	 * Contains the results from the last time {@link #loadSadAndProperties(ILaunchConfiguration)} was called. It
	 * should never be assumed to hold the "current" mappings unless it was just loaded.
	 */
	private ScaWaveform waveform = null;

	public WaveformPropertiesTab() {
		this.propImage = ScaUIImages.DESC_VARIABLE_TAB.createImage();
		this.adapterFactory = new ScaPropertiesAdapterFactory();
	}

	@Override
	public void dispose() {
		this.propImage.dispose();
		this.propImage = null;
		super.dispose();
	}

	@Override
	public String getName() {
		return "&Properties";
	}

	@Override
	public Image getImage() {
		return this.propImage;
	}

	@Override
	public void createControl(final Composite parent) {
		final Composite main = new Composite(parent, SWT.None);
		main.setLayout(new GridLayout());
		final Composite propComposite = new Composite(main, SWT.BORDER);
		propComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		this.viewer = ScaComponentFactory.createPropertyTable(propComposite, SWT.H_SCROLL | SWT.FULL_SELECTION | SWT.SINGLE, this.adapterFactory);

		final Button resetButton = new Button(main, SWT.PUSH);
		resetButton.setText("Reset");
		resetButton.setToolTipText("Reset all the property values to default");
		resetButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				setDefaults(null);
			}
		});
		resetButton.setLayoutData(GridDataFactory.swtDefaults().align(SWT.END, SWT.FILL).create());

		setControl(main);

	}

	@Override
	public void setDefaults(final ILaunchConfigurationWorkingCopy configuration) {
		configuration.removeAttribute(ScaLaunchConfigurationConstants.ATT_PROPERTIES);
	}

	@Override
	public void initializeFrom(final ILaunchConfiguration configuration) {
		try {
			loadSadAndProperties(configuration);
		} catch (CoreException e) {
			// PASS - Handled in isValid(ILaunchConfiguration)
		}
		this.viewer.setInput(waveform);
	}

	@Override
	public void performApply(final ILaunchConfigurationWorkingCopy configuration) {
		if (this.waveform != null) {
			ScaLaunchConfigurationUtil.saveProperties(configuration, this.waveform);
		}
	}

	@Override
	public boolean isValid(ILaunchConfiguration launchConfig) {
		try {
			loadSadAndProperties(launchConfig);
			setWarningMessage(null);
			setErrorMessage(null);
			return true;
		} catch (CoreException e) {
			if (e.getStatus().getSeverity() == IStatus.WARNING) {
				setWarningMessage(e.getMessage());
			} else {
				setErrorMessage(e.getMessage());
			}
			return false;
		}
	}

	/**
	 * Loads the SAD XML, associated XML, and property overrides into {@link #waveform}.
	 * @param configuration The configuration to load
	 * @throws CoreException An error occurs while loading. The {@link #waveform} member variable may contain
	 * partially loaded results (e.g. SAD XML was loaded, but prop overrides couldn't all be loaded/applied)
	 */
	private void loadSadAndProperties(final ILaunchConfiguration configuration) throws CoreException {
		this.waveform = null;

		// Load the SAD file
		SoftwareAssembly sad;
		try {
			URI uri = ScaLaunchConfigurationUtil.getProfileURI(configuration);
			final ResourceSet resourceSet = ScaResourceFactoryUtil.createResourceSet();
			final Resource resource = resourceSet.getResource(uri, true);
			sad = SoftwareAssembly.Util.getSoftwareAssembly(resource);
		} catch (WrappedException e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaLauncherActivator.PLUGIN_ID, "Unable to load SAD file", e));
		}

		// Initialize properties
		this.waveform = ScaFactory.eINSTANCE.createScaWaveform();
		this.waveform.setDataProvidersEnabled(false);
		this.waveform.setProfileObj(sad);
		for (final ScaAbstractProperty< ? > prop : this.waveform.fetchProperties(new NullProgressMonitor())) {
			prop.setIgnoreRemoteSet(true);
		}

		// Load saved property overrides
		ScaLaunchConfigurationUtil.loadProperties(configuration, this.waveform);
	}

	/**
	 * @deprecated Do not call. No effect.
	 */
	@Deprecated
	public void setSoftwareAssembly(final SoftwareAssembly sad) {
		ScaLauncherActivator.getDefault().getLog().log(new Status(IStatus.WARNING, ScaLauncherActivator.PLUGIN_ID,
			"The method gov.redhawk.sca.launch.ui.tabs.WaveformPropertiesTab.setSoftwareAssembly(SoftwareAssembly) is deprecated. Do not use."));
	}
}
