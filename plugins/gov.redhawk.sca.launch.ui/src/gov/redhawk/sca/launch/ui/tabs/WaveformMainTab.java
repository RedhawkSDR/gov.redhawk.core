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
import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;
import gov.redhawk.sca.ScaPlugin;
import gov.redhawk.sca.launch.ScaLaunchConfigurationConstants;
import gov.redhawk.sca.launch.ui.ScaUIImages;
import gov.redhawk.sca.ui.wizards.InstallApplicationContentProvider;
import gov.redhawk.sca.ui.wizards.WizardSadItemProviderAdapterFactory;
import gov.redhawk.ui.parts.FormFilteredTree;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Comparator;

import mil.jpeojtrs.sca.sad.SoftwareAssembly;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.progress.WorkbenchJob;

/**
 * 
 */
public class WaveformMainTab extends AbstractLaunchConfigurationTab {

	public static final String SAD = "SOFTWARE_ASSEMBLY";
	public static final String DOMAIN = "SCA_DOMAIN_MANAGER";
	private Image mainImage;
	private Button startWaveform;
	private InstallApplicationContentProvider contentProvider;
	private FormFilteredTree waveformSelectionList;
	private ComboViewer domainCombo;
	private String waveformProfile;

	private final PropertyChangeSupport support = new PropertyChangeSupport(this);
	private Button openEditorButton;
	private ScaItemProviderAdapterFactory adapterFactory;

	public WaveformMainTab() {
		this.mainImage = ScaUIImages.DESC_MAIN_TAB.createImage();
	}

	@Override
	public Image getImage() {
		return this.mainImage;
	}

	@Override
	public void dispose() {
		if (this.adapterFactory != null) {
			this.adapterFactory.dispose();
			this.adapterFactory = null;
		}
		if (this.mainImage != null) {
			this.mainImage.dispose();
			this.mainImage = null;
		}
		super.dispose();
	}

	public SoftwareAssembly getSoftwareAssembly() {
		return (SoftwareAssembly) ((IStructuredSelection) this.waveformSelectionList.getViewer().getSelection()).getFirstElement();
	}

	public ScaDomainManager getScaDomainManager() {
		return (ScaDomainManager) ((IStructuredSelection) this.domainCombo.getSelection()).getFirstElement();
	}

	public void addPropertyChangeListener(final PropertyChangeListener listener) {
		this.support.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(final PropertyChangeListener listener) {
		this.support.removePropertyChangeListener(listener);
	}

	/**
	 * {@inheritDoc}
	 */
	public void createControl(final Composite parent) {
		// create the top level composite for the dialog area
		final Composite composite = new Composite(parent, SWT.NONE);
		setControl(composite);
		composite.setFont(parent.getFont());

		composite.setLayout(new GridLayout(2, false));

		final Label domainName = new Label(composite, SWT.None);
		domainName.setText("Domain:");

		this.adapterFactory = new ScaItemProviderAdapterFactory();
		this.domainCombo = new ComboViewer(composite, SWT.BORDER);
		this.domainCombo.setContentProvider(new ArrayContentProvider());
		this.domainCombo.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		this.domainCombo.setInput(ScaPlugin.getDefault().getDomainManagerRegistry().getDomains());
		this.domainCombo.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		this.domainCombo.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(final SelectionChangedEvent event) {
				final IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				final Object element = selection.getFirstElement();
				if (element instanceof ScaDomainManager) {
					final ScaDomainManager domain = (ScaDomainManager) element;
					WaveformMainTab.this.waveformSelectionList.getViewer().setInput(domain);
				} else {
					WaveformMainTab.this.waveformSelectionList.getViewer().setInput("");
				}
				WaveformMainTab.this.support.firePropertyChange(WaveformMainTab.DOMAIN, null, getScaDomainManager());
				updateLaunchConfigurationDialog();
			}
		});

		this.waveformSelectionList = new FormFilteredTree(composite, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER, new PatternFilter());
		this.waveformSelectionList.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create());
		this.contentProvider = new InstallApplicationContentProvider();
		this.contentProvider.addContentCompletionListener(new JobChangeAdapter() {

			@Override
			public void done(final IJobChangeEvent event) {
				WorkbenchJob job = new WorkbenchJob("Restore Selection") {
					
					@Override
					public IStatus runInUIThread(IProgressMonitor monitor) {
						if (!getControl().isDisposed()) {
							restorePreviousWaveformSelection();
						}
						return Status.OK_STATUS;
					}
				};
				job.schedule();
			}
		});
		this.waveformSelectionList.getViewer().setContentProvider(this.contentProvider);
		this.waveformSelectionList.getViewer().setLabelProvider(new AdapterFactoryLabelProvider(new WizardSadItemProviderAdapterFactory()));
		this.waveformSelectionList.getViewer().setComparator(new ViewerComparator() {
			private Comparator<String> viewerComparator;

			@Override
			protected Comparator<String> getComparator() {
				if (this.viewerComparator == null) {
					this.viewerComparator = new Comparator<String>() {
						public int compare(final String s1, final String s2) {
							return s1.compareToIgnoreCase(s2);
						}
					};
				}
				return this.viewerComparator;
			}
		});

		this.startWaveform = new Button(composite, SWT.CHECK);
		this.startWaveform.setText("Start the waveform after launching");
		this.startWaveform.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(false, false).create());
		this.startWaveform.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				updateLaunchConfigurationDialog();
			}
		});

		this.openEditorButton = new Button(composite, SWT.CHECK);
		this.openEditorButton.setText("Open editor after launch");
		this.openEditorButton.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(false, false).create());
		this.openEditorButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				updateLaunchConfigurationDialog();
			}
		});

		this.waveformSelectionList.getViewer().addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(final SelectionChangedEvent event) {
				final Object selected = ((StructuredSelection) event.getSelection()).getFirstElement();
				if (selected instanceof SoftwareAssembly) {
					final SoftwareAssembly sad = (SoftwareAssembly) selected;
					final String initialValue = sad.getName();
					WaveformMainTab.this.waveformProfile = sad.eResource().getURI().path();
				} else {
					WaveformMainTab.this.waveformProfile = "";
				}
				WaveformMainTab.this.support.firePropertyChange(WaveformMainTab.SAD, null, getSoftwareAssembly());
				updateLaunchConfigurationDialog();
			}

		});
	}

	private void restorePreviousWaveformSelection() {
		final TreeViewer viewer = this.waveformSelectionList.getViewer();
		viewer.refresh();

		SoftwareAssembly selection = null;
		if (this.contentProvider.getChildren() != null) {
			for (final SoftwareAssembly s : this.contentProvider.getChildren()) {
				if (s.eResource().getURI().path().equals(this.waveformProfile)) {
					selection = s;
				}
			}
		}

		if (selection != null) {
			viewer.setSelection(new StructuredSelection(selection));
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public void setDefaults(final ILaunchConfigurationWorkingCopy configuration) {
		if (this.domainCombo != null) {
			this.domainCombo.setSelection(new StructuredSelection());
		}
	}

	@Override
	public boolean isValid(final ILaunchConfiguration launchConfig) {
		setMessage(null);
		setErrorMessage(null);

		return validateDomain() && validateWaveformSelection();
	}

	private boolean validateWaveformSelection() {
		final ISelection selection = this.waveformSelectionList.getViewer().getSelection();
		if (selection.isEmpty()) {
			setErrorMessage("Must select a waveform to launch");
			return false;
		}
		return true;
	}

	private boolean validateDomain() {
		final ISelection selection = this.domainCombo.getSelection();
		if (selection.isEmpty()) {
			setErrorMessage("Must select a domain");
			return false;
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public void initializeFrom(final ILaunchConfiguration configuration) {
		try {
			final String domainName = configuration.getAttribute(ScaLaunchConfigurationConstants.ATT_DOMAIN_NAME, "");
			final String waveformName = configuration.getName();
			this.waveformProfile = configuration.getAttribute(ScaLaunchConfigurationConstants.ATT_PROFILE, "");
			final boolean autoStart = configuration.getAttribute(ScaLaunchConfigurationConstants.ATT_START,
			        ScaLaunchConfigurationConstants.DEFAULT_VALUE_ATT_START);
			final boolean openEditor = configuration.getAttribute(ScaLaunchConfigurationConstants.ATT_OPEN,
			        ScaLaunchConfigurationConstants.DEFAULT_VALUE_ATT_OPEN);
			for (final ScaDomainManager domain : ScaPlugin.getDefault().getDomainManagerRegistry().getDomains()) {
				if (domainName.equals(domain.getName())) {
					this.domainCombo.setSelection(new StructuredSelection(domain));
					break;
				}
			}

			this.startWaveform.setSelection(autoStart);
			this.openEditorButton.setSelection(openEditor);
			restorePreviousWaveformSelection();
		} catch (final CoreException e) {
			// PASS
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void performApply(final ILaunchConfigurationWorkingCopy configuration) {
		String domainName = "";
		final IStructuredSelection domainSelection = (IStructuredSelection) this.domainCombo.getSelection();
		final Object domain = domainSelection.getFirstElement();
		if (domain instanceof ScaDomainManager) {
			domainName = ((ScaDomainManager) domain).getName();
		}
		configuration.setAttribute(ScaLaunchConfigurationConstants.ATT_DOMAIN_NAME, domainName);
		configuration.setAttribute(ScaLaunchConfigurationConstants.ATT_START, this.startWaveform.getSelection());
		configuration.setAttribute(ScaLaunchConfigurationConstants.ATT_PROFILE, this.waveformProfile);
		configuration.setAttribute(ScaLaunchConfigurationConstants.ATT_OPEN, this.openEditorButton.getSelection());

	}

	/**
	 * {@inheritDoc}
	 */
	public String getName() {
		return "Main";
	}

}
