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

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Section;

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.WaveformsContainer;
import gov.redhawk.sca.ui.ScaUiPlugin;
import gov.redhawk.sca.ui.parts.FormFilteredTree;
import gov.redhawk.sca.ui.wizards.InstallApplicationContentProvider;
import gov.redhawk.sca.ui.wizards.InstallApplicationLabelProvider;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;

public class WaveformSelectionWizardPage extends WizardPage {

	private static final String WAVEFORM_ID = "WAVEFORM_ID";
	private static final String AUTO_START = "AUTO_START";
	/**
	 * @deprecated Use {@link #getName()} instead
	 */
	@Deprecated
	private static final String SECTION = "gov.redhawk.sca.internal.ui.wizards.WaveformSelectionWizardPage";

	private String waveformName;

	private boolean autoStart;
	private FormFilteredTree waveformSelectionList;
	private ScaDomainManager domMgr;
	private boolean defaultNameChanged;
	private Button startWaveform;
	private Button uninstallExistAppFactory;
	private SoftwareAssembly sad;
	private InstallApplicationContentProvider contentProvider;
	private IDialogSettings waveformSelectionPageSettings;
	private String sadSelection;

	public WaveformSelectionWizardPage(final String pageName) {
		super(pageName);
		setTitle("Select a Waveform");
		setDescription("Select a Waveform to launch");
	}

	public void init(final ScaDomainManager domMgr) {
		this.domMgr = domMgr;
		if (this.waveformSelectionList != null) {
			this.waveformSelectionList.getViewer().setInput(this.domMgr);
		}
	}

	@Override
	public void createControl(final Composite parent) {
		setupDialogSettings();

		GridData gd;
		// create the top level composite for the dialog area
		final Composite composite = new Composite(parent, SWT.NONE);
		setControl(composite);

		final GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.makeColumnsEqualWidth = false;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.verticalSpacing = 5; // SUPPRESS CHECKSTYLE MagicNumber
		layout.horizontalSpacing = 5; // SUPPRESS CHECKSTYLE MagicNumber
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		composite.setFont(parent.getFont());
		// Build the separator line
		final Label titleBarSeparator = new Label(composite, SWT.HORIZONTAL | SWT.SEPARATOR);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		titleBarSeparator.setLayoutData(gd);

		PatternFilter patternFilter = new PatternFilter();
		patternFilter.setIncludeLeadingWildcard(true);
		this.waveformSelectionList = new FormFilteredTree(composite, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER, patternFilter);
		gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd.horizontalSpan = 2;
		gd.minimumHeight = 200;
		this.waveformSelectionList.setLayoutData(gd);

		this.contentProvider = new InstallApplicationContentProvider();
		this.contentProvider.addContentCompletionListener(new JobChangeAdapter() {

			@Override
			public void done(final IJobChangeEvent event) {
				String waveformId = WaveformSelectionWizardPage.this.sadSelection;
				if (waveformId == null) {
					waveformId = WaveformSelectionWizardPage.this.waveformSelectionPageSettings.get(WaveformSelectionWizardPage.WAVEFORM_ID);
				}
				final String id = waveformId;
				if (!getControl().isDisposed()) {
					getControl().getDisplay().asyncExec(new Runnable() {

						@Override
						public void run() {
							final IStatus loadStatus = WaveformSelectionWizardPage.this.contentProvider.getLoadStatus();
							if (loadStatus != null && !loadStatus.isOK()) {
								int severity = 0;
								if (loadStatus.getSeverity() == IStatus.WARNING) {
									severity = IMessageProvider.WARNING;
								} else if (loadStatus.getSeverity() == IStatus.ERROR) {
									severity = IMessageProvider.ERROR;
								}
								ScaUiPlugin.getDefault().getLog().log(loadStatus);
								setMessage("Problems occurred while scanning for waveforms.\nSee log for details.", severity);
							}
							restorePreviousWaveformSelection(id);
						}

					});
				}
			}
		});
		this.waveformSelectionList.getViewer().setContentProvider(this.contentProvider);
		this.waveformSelectionList.getViewer().setLabelProvider(new InstallApplicationLabelProvider());
		this.waveformSelectionList.getViewer().addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(final DoubleClickEvent event) {
				StructuredSelection selection = (StructuredSelection) waveformSelectionList.getViewer().getSelection();
				if (getWizard().canFinish() && !(selection.getFirstElement() instanceof WaveformsContainer)) {
					if (getWizard().performFinish()) {
						getWizard().getContainer().getShell().dispose();
					}
				}
			}
		});

		this.waveformSelectionList.getViewer().setComparator(new ViewerComparator() {
			private Comparator<String> viewerComparator;

			@Override
			protected Comparator<String> getComparator() {
				if (this.viewerComparator == null) {
					this.viewerComparator = new Comparator<String>() {
						@Override
						public int compare(final String s1, final String s2) {
							return s1.compareToIgnoreCase(s2);
						}
					};
				}
				return this.viewerComparator;
			}
		});

		this.waveformSelectionList.getViewer().setInput(this.domMgr);

		final Label label = new Label(composite, SWT.NONE);
		label.setText("Waveform Name:");
		gd = new GridData(SWT.LEFT, SWT.CENTER, false, false);
		label.setLayoutData(gd);
		final Text waveformNameTextBox = new Text(composite, SWT.BORDER);
		gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		waveformNameTextBox.setLayoutData(gd);

		waveformNameTextBox.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(final ModifyEvent e) {
				WaveformSelectionWizardPage.this.defaultNameChanged = true;
				WaveformSelectionWizardPage.this.waveformName = waveformNameTextBox.getText();
				updateFinished();
			}

		});

		this.startWaveform = new Button(composite, SWT.CHECK);
		this.startWaveform.setText("Start the Waveform after launching");
		this.startWaveform.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(false, false).create());
		restoreShouldAutoStartWaveform();

		this.startWaveform.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(final SelectionEvent e) {
				WaveformSelectionWizardPage.this.autoStart = WaveformSelectionWizardPage.this.startWaveform.getSelection();
			}

			@Override
			public void widgetDefaultSelected(final SelectionEvent e) {
				// PASS
			}
		});

		Section advancedComposite = new Section(composite, ExpandableComposite.TWISTIE);
		advancedComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
		advancedComposite.setText("Advanced");
		advancedComposite.setFont(composite.getFont());
		advancedComposite.setLayout(GridLayoutFactory.fillDefaults().create());
		Composite advancedSection = new Composite(advancedComposite, SWT.NONE);
		advancedComposite.setClient(advancedSection);
		advancedSection.setLayout(GridLayoutFactory.fillDefaults().create());

		this.uninstallExistAppFactory = new Button(advancedSection, SWT.CHECK);
		this.uninstallExistAppFactory.setText("Uninstall existing application factory (pre-2.0 Redhawk domains only)");
		this.waveformSelectionList.getViewer().addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(final SelectionChangedEvent event) {
				final Object selected = ((StructuredSelection) event.getSelection()).getFirstElement();
				if (selected instanceof SoftwareAssembly) {
					if (WaveformSelectionWizardPage.this.sad == selected) {
						updateFinished();
						return;
					}
					WaveformSelectionWizardPage.this.sad = (SoftwareAssembly) selected;
					if (WaveformSelectionWizardPage.this.getWizard() instanceof LaunchWaveformWizard) {
						((LaunchWaveformWizard) WaveformSelectionWizardPage.this.getWizard()).reinitalizePages();
					}
					if (WaveformSelectionWizardPage.this.defaultNameChanged) {
						// PASS since user had explicitly set the Waveform name
					} else if (WaveformSelectionWizardPage.this.sad == null) {
						waveformNameTextBox.setText("");
						WaveformSelectionWizardPage.this.defaultNameChanged = false;
					} else {
						final SimpleDateFormat dateFormat = new SimpleDateFormat("DDD_HHmmssSSS");
						final String initialValue = WaveformSelectionWizardPage.this.sad.getName() + "_" + dateFormat.format(new Date());
						waveformNameTextBox.setText(initialValue);
						WaveformSelectionWizardPage.this.defaultNameChanged = false;
					}
				}
				updateFinished();
			}

		});

		this.setPageComplete(false);
	}

	private void setupDialogSettings() {
		this.waveformSelectionPageSettings = getDialogSettings().getSection(getName());
		if (this.waveformSelectionPageSettings == null) {
			this.waveformSelectionPageSettings = getDialogSettings().getSection(WaveformSelectionWizardPage.SECTION);
			if (this.waveformSelectionPageSettings == null) {
				this.waveformSelectionPageSettings = getDialogSettings().addNewSection(getName());
			}
		}
	}

	private void restorePreviousWaveformSelection(final String waveformId) {
		final TreeViewer viewer = WaveformSelectionWizardPage.this.waveformSelectionList.getViewer();
		if (viewer.getControl().isDisposed()) {
			return;
		}
		viewer.refresh();

		// Ignore if there is already a selection
		if (!viewer.getSelection().isEmpty()) {
			return;
		}

		SoftwareAssembly selection = null;
		if (this.contentProvider.getChildren() != null) {
			for (final SoftwareAssembly s : this.contentProvider.getChildren()) {
				if (s.getId().equals(waveformId)) {
					selection = s;
				}
			}
		}

		if (selection == null && !this.contentProvider.getChildren().isEmpty()) {
			selection = this.contentProvider.getChildren().get(0);
		}
		if (selection != null) {
			viewer.setSelection(new StructuredSelection(selection));
		}

	}

	private void restoreShouldAutoStartWaveform() {
		this.autoStart = this.waveformSelectionPageSettings.getBoolean(WaveformSelectionWizardPage.AUTO_START);
		this.startWaveform.setSelection(this.autoStart);
	}

	private void updateFinished() {
		StructuredSelection selection = (StructuredSelection) waveformSelectionList.getViewer().getSelection();
		boolean isContainer = selection.getFirstElement() instanceof WaveformsContainer;
		this.setPageComplete(this.sad != null && this.waveformName != null && this.waveformName.trim().length() > 0 && !(isContainer));
	}

	@Override
	public void dispose() {
		// Explicitly call the dispose method on the extensions tree
		if (this.waveformSelectionList != null) {
			this.waveformSelectionList.dispose();
		}
		super.dispose();
	}

	public SoftwareAssembly getSoftwareAssembly() {
		return this.sad;
	}

	public String getWaveformName() {
		return this.waveformName;
	}

	public boolean isAutoStart() {
		return this.autoStart;
	}

	public boolean isUninstallExistingApplicationFactory() {
		return this.uninstallExistAppFactory.getSelection();
	}
	
	public void saveWidgetValues() {
		this.waveformSelectionPageSettings.put(WaveformSelectionWizardPage.AUTO_START, this.autoStart);
		this.waveformSelectionPageSettings.put(WaveformSelectionWizardPage.WAVEFORM_ID, this.sad.getId());
	}

	public void setSoftwareAssemblySelection(final String sadId) {
		this.sadSelection = sadId;
	}

}
