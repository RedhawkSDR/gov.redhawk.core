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

import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.sca.launch.ScaLaunchConfigurationUtil;
import gov.redhawk.sca.launch.ui.ScaUIImages;
import gov.redhawk.sca.ui.ScaComponentFactory;
import gov.redhawk.sca.ui.properties.ScaPropertiesAdapterFactory;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import mil.jpeojtrs.sca.util.ScaResourceFactoryUtil;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * 
 */
public class WaveformPropertiesTab extends AbstractLaunchConfigurationTab {

	private Image propImage;
	private final AdapterFactory adapterFactory;
	private ScaComponent assemblyController = null;
	private ILaunchConfiguration configuration;
	private boolean loadFromConfig = true;
	private TreeViewer viewer;

	public WaveformPropertiesTab() {
		this.propImage = ScaUIImages.DESC_VARIABLE_TAB.createImage();
		this.adapterFactory = new ScaPropertiesAdapterFactory();
	}

	public void setSoftwareAssembly(final SoftwareAssembly sad) {
		this.loadFromConfig = false;
		setSoftwareAssembly(sad, this.configuration);
		updateLaunchConfigurationDialog();
	}

	private void setSoftwareAssembly(final SoftwareAssembly sad, final ILaunchConfiguration configuration) {
		if (sad == null || sad.getAssemblyController() == null) {
			this.assemblyController = null;
			this.viewer.setInput(null);
			setErrorMessage("Invalid Software Assembly Descriptor");
		} else if (sad.getAssemblyController().getComponentInstantiationRef() != null) {
			this.assemblyController = ScaFactory.eINSTANCE.createScaComponent();
			this.assemblyController.setProfileObj(sad.getAssemblyController().getComponentInstantiationRef().getInstantiation().getPlacement().getComponentFileRef().getFile().getSoftPkg());
			for (final ScaAbstractProperty< ? > prop : this.assemblyController.fetchProperties(null)) {
				prop.setIgnoreRemoteSet(true);
			}
			try {
				ScaLaunchConfigurationUtil.loadProperties(configuration, this.assemblyController);
			} catch (final CoreException e1) {
				setErrorMessage(e1.getMessage());
				return;
			}
			this.viewer.setInput(this.assemblyController);
			this.assemblyController.eAdapters().add(new EContentAdapter() {
				@Override
				public void notifyChanged(final Notification notification) {
					super.notifyChanged(notification);
					updateLaunchConfigurationDialog();
				}
			});
			setErrorMessage(null);
		}
	}

	@Override
	public Image getImage() {
		return this.propImage;
	}

	@Override
	public void dispose() {
		if (this.propImage != null) {
			this.propImage.dispose();
			this.propImage = null;
		}
		super.dispose();
	}

	/**
	 * {@inheritDoc}
	 */
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
				for (final ScaAbstractProperty< ? > prop : WaveformPropertiesTab.this.assemblyController.getProperties()) {
					prop.restoreDefaultValue();
				}
			}
		});
		resetButton.setLayoutData(GridDataFactory.swtDefaults().align(SWT.END, SWT.FILL).create());

		setControl(main);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDefaults(final ILaunchConfigurationWorkingCopy configuration) {
		if (this.assemblyController != null) {
			for (final ScaAbstractProperty< ? > prop : this.assemblyController.getProperties()) {
				prop.restoreDefaultValue();
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initializeFrom(final ILaunchConfiguration configuration) {
		this.configuration = configuration;
		if (this.loadFromConfig) {
			updateSoftwareAssembly(configuration);
		}
	}

	private void updateSoftwareAssembly(final ILaunchConfiguration configuration) {
		try {
			URI uri = ScaLaunchConfigurationUtil.getProfileURI(configuration);
			final ResourceSet resourceSet = ScaResourceFactoryUtil.createResourceSet();
			final Resource resource = resourceSet.getResource(uri, true);
			setSoftwareAssembly(SoftwareAssembly.Util.getSoftwareAssembly(resource), configuration);
		} catch (final CoreException e) {
			setSoftwareAssembly(null, configuration);
		} catch (final Exception e) { // SUPPRESS CHECKSTYLE Logged Catch all exception
			setSoftwareAssembly(null, configuration);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void performApply(final ILaunchConfigurationWorkingCopy configuration) {
		if (this.assemblyController != null) {
			ScaLaunchConfigurationUtil.saveProperties(configuration, this.assemblyController);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return "&Properties";
	}

}
