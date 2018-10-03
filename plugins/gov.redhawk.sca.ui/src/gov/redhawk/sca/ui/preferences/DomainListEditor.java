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
package gov.redhawk.sca.ui.preferences;

import gov.redhawk.model.sca.DomainConnectionException;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.sca.internal.ui.preferences.ScaDomainConnectionDef;
import gov.redhawk.sca.preferences.ScaPreferenceInitializer;
import gov.redhawk.sca.ui.ScaUiPlugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * A field editor that manages a list of input values. The editor displays a list containing the values, buttons for
 * connecting and disconnecting a domain and setting the target deployment domain.
 */
public class DomainListEditor extends FieldEditor {
	/**
	 * The list widget; <code>null</code> if none (before creation or after disposal).
	 */
	private ListViewer list;

	/**
	 * The button box containing the Add, Remove, Up, and Down buttons; <code>null</code> if none (before creation or
	 * after disposal).
	 */
	private Composite buttonBox;

	private ScaDomainManagerRegistry registry;

	private static final int NUM_BUTTONS = 5;

	private final List<ScaDomainConnectionDef> domainConnectionDefs = new ArrayList<ScaDomainConnectionDef>();

	private Button editButton;

	private Button removeButton;

	private Button autoConnectButton;

	/**
	 * Creates a new list field editor
	 */
	public DomainListEditor() {
	}

	/**
	 * Creates a list field editor.
	 * 
	 * @param labelText the label text of the field editor
	 * @param parent the parent of the field editor's control
	 * @since 4.0
	 */
	public DomainListEditor(final ScaDomainManagerRegistry registry, final String labelText, final Composite parent) {
		init("", labelText);
		createControl(parent);
		this.registry = registry;
	}

	@Override
	protected void adjustForNumColumns(final int numColumns) {
		final Control control = getLabelControl();
		((GridData) control.getLayoutData()).horizontalSpan = numColumns;
		((GridData) this.list.getList().getLayoutData()).horizontalSpan = numColumns - 1;
	}

	/**
	 * Creates the Add, Remove, Up, and Down button in the given button box.
	 * 
	 * @param box the box for the buttons
	 */
	private void createButtons(final Composite box) {
		final Button newButton = createPushButton(box, "New...");
		newButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(final SelectionEvent e) {
				widgetSelected(e);
			}

			@Override
			public void widgetSelected(final SelectionEvent e) {
				final DomainEntryWizard wizard = new DomainEntryWizard();
				wizard.setShowExtraSettings(false);
				wizard.setDomains(DomainListEditor.this.domainConnectionDefs);
				wizard.setWindowTitle("New Domain Manager");
				final WizardDialog dialog = new WizardDialog(box.getShell(), wizard);
				if (dialog.open() == IStatus.OK) {
					final ScaDomainConnectionDef def = new ScaDomainConnectionDef(wizard.getLocalDomainName(), wizard.getDomainName(),
						wizard.getNameServiceInitRef(), false);
					DomainListEditor.this.domainConnectionDefs.add(def);
					DomainListEditor.this.list.refresh();
				}
			}

		});
		this.editButton = createPushButton(box, "Edit");
		this.editButton.setEnabled(false);
		this.editButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(final SelectionEvent e) {
				widgetSelected(e);
			}

			@Override
			public void widgetSelected(final SelectionEvent e) {
				final ScaDomainConnectionDef def = (ScaDomainConnectionDef) ((IStructuredSelection) DomainListEditor.this.list.getSelection()).getFirstElement();
				final DomainEntryWizard wizard = new DomainEntryWizard();
				wizard.setShowExtraSettings(false);
				wizard.setDomains(DomainListEditor.this.domainConnectionDefs);
				wizard.setWindowTitle("Edit Domain Manager");
				wizard.setEdit(def.getLocalName(), def.getDomainName(), def.getNameServiceInitRef());
				wizard.setConnectionMode(def.isConnectOnStartup());
				final WizardDialog dialog = new WizardDialog(box.getShell(), wizard);
				if (dialog.open() == IStatus.OK) {
					final ScaDomainConnectionDef newDef = new ScaDomainConnectionDef(wizard.getLocalDomainName(), wizard.getDomainName(),
						wizard.getNameServiceInitRef(), false);
					if (!def.equals(newDef)) {
						final int index = DomainListEditor.this.domainConnectionDefs.indexOf(def);
						DomainListEditor.this.domainConnectionDefs.remove(index);
						DomainListEditor.this.domainConnectionDefs.add(index, newDef);
						DomainListEditor.this.list.refresh();
					}
				}
			}

		});
		this.removeButton = createPushButton(box, "Remove");
		this.removeButton.setEnabled(false);
		this.removeButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(final SelectionEvent e) {
				widgetSelected(e);
			}

			@Override
			public void widgetSelected(final SelectionEvent e) {
				final boolean confirmedDelete = MessageDialog.openConfirm(box.getShell(), "Delete domain connection?",
					"Are you sure you want to delete the domain connection?");
				if (confirmedDelete) {
					for (final Object obj : ((StructuredSelection) DomainListEditor.this.list.getSelection()).toArray()) {
						DomainListEditor.this.domainConnectionDefs.remove(obj);
					}

					DomainListEditor.this.list.refresh();
				}
			}

		});
		this.autoConnectButton = createPushButton(box, "Toggle Auto Connect");
		this.autoConnectButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(final SelectionEvent e) {
				widgetSelected(e);
			}

			@Override
			public void widgetSelected(final SelectionEvent e) {
				final Object obj = ((StructuredSelection) DomainListEditor.this.list.getSelection()).getFirstElement();
				for (final ScaDomainConnectionDef def : DomainListEditor.this.domainConnectionDefs) {
					if (def == obj) {
						def.setConnectOnStartup(!def.isConnectOnStartup());
					}
				}
				DomainListEditor.this.list.refresh();
			}
		});
	}

	/**
	 * Helper method to create a push button.
	 * 
	 * @param parent the parent control
	 * @param key the resource name used to supply the button's label text
	 * @return Button
	 */
	private Button createPushButton(final Composite parent, final String text) {
		final Button button = new Button(parent, SWT.PUSH);
		button.setText(text);
		button.setFont(parent.getFont());
		final GridData data = new GridData(GridData.FILL_HORIZONTAL);
		final int widthHint = convertHorizontalDLUsToPixels(button, IDialogConstants.BUTTON_WIDTH);
		data.widthHint = Math.max(widthHint, button.computeSize(SWT.DEFAULT, SWT.DEFAULT, true).x);
		button.setLayoutData(data);
		return button;
	}

	@Override
	protected void doFillIntoGrid(final Composite parent, final int numColumns) {
		final Control control = getLabelControl(parent);
		GridData gd = new GridData();
		gd.horizontalSpan = numColumns;
		control.setLayoutData(gd);

		this.list = new ListViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		this.list.setContentProvider(new ArrayContentProvider());
		this.list.setLabelProvider(new LabelProvider());
		this.list.setInput(this.domainConnectionDefs);
		this.list.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(final SelectionChangedEvent event) {
				DomainListEditor.this.editButton.setEnabled(!event.getSelection().isEmpty());
				DomainListEditor.this.removeButton.setEnabled(!event.getSelection().isEmpty());
				DomainListEditor.this.autoConnectButton.setEnabled(!event.getSelection().isEmpty());
			}
		});

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.verticalAlignment = GridData.FILL;
		gd.horizontalSpan = numColumns - 1;
		gd.grabExcessHorizontalSpace = true;
		final int heightHint = convertVerticalDLUsToPixels(this.list.getList(), IDialogConstants.BUTTON_HEIGHT * DomainListEditor.NUM_BUTTONS);
		gd.heightHint = Math.max(heightHint, this.list.getList().computeSize(SWT.DEFAULT, SWT.DEFAULT, true).x);
		this.list.getList().setLayoutData(gd);

		this.buttonBox = getButtonBoxControl(parent);
		gd = new GridData();
		gd.verticalAlignment = GridData.BEGINNING;
		this.buttonBox.setLayoutData(gd);
	}

	@Override
	protected void doLoad() {
		this.domainConnectionDefs.clear();

		for (final ScaDomainManager manager : this.registry.getDomains()) {
			final ScaDomainConnectionDef def = new ScaDomainConnectionDef(manager.getLocalName(), manager.getName(),
				manager.getConnectionProperties().get(ScaDomainManager.NAMING_SERVICE_PROP), manager.isAutoConnect());
			this.domainConnectionDefs.add(def);
		}
		if (this.list != null) {
			this.list.refresh();
		}
	}

	@Override
	protected void doLoadDefault() {
		this.domainConnectionDefs.clear();

		for (final ScaDomainManager manager : ScaPreferenceInitializer.getDefaultScaDomainManagerRegistry().getDomains()) {
			final ScaDomainConnectionDef def = new ScaDomainConnectionDef(manager.getLocalName(), manager.getName(),
				manager.getConnectionProperties().get(ScaDomainManager.NAMING_SERVICE_PROP), manager.isAutoConnect());
			this.domainConnectionDefs.add(def);
		}

		if (this.list != null) {
			this.list.refresh();
		}
	}

	@Override
	protected void doStore() {
		for (final ScaDomainConnectionDef def : this.domainConnectionDefs) {
			final ScaDomainManager domain = this.registry.findDomain(def.getLabel());
			if (domain == null) {
				final Job job = new Job("Connecting to domain " + def.getLabel()) {

					@Override
					protected IStatus run(final IProgressMonitor monitor) {
						try {
							final ScaDomainManager[] newDomain = new ScaDomainManager[1];
							ScaModelCommand.execute(DomainListEditor.this.registry, new ScaModelCommand() {

								@Override
								public void execute() {
									newDomain[0] = DomainListEditor.this.registry.createDomain(def.getLocalName(), def.getDomainName(),
										def.isConnectOnStartup(), Collections.singletonMap(ScaDomainManager.NAMING_SERVICE_PROP, def.getNameServiceInitRef()));

								}

							});
							newDomain[0].connect(monitor, RefreshDepth.SELF);
						} catch (final DomainConnectionException e) {
							return new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, "Failed to connect to domain " + def.getLabel(), e);
						}
						return Status.OK_STATUS;
					}

				};
				job.schedule();
			} else {
				ScaModelCommand.execute(domain, new ScaModelCommand() {
					@Override
					public void execute() {
						domain.setAutoConnect(def.isConnectOnStartup());
						domain.getConnectionProperties().put(ScaDomainManager.NAMING_SERVICE_PROP, def.getNameServiceInitRef());
					}
				});
			}
		}

		final List<ScaDomainManager> domainsToRemove = new ArrayList<ScaDomainManager>();
		for (final ScaDomainManager domain : this.registry.getDomains()) {
			boolean found = false;
			for (final ScaDomainConnectionDef def : this.domainConnectionDefs) {
				if (domain.getLabel().equals(def.getLabel())) {
					found = true;
					break;
				}
			}
			if (!found) {
				domainsToRemove.add(domain);
			}
		}

		ScaModelCommand.execute(this.registry, new ScaModelCommand() {

			@Override
			public void execute() {
				DomainListEditor.this.registry.getDomains().removeAll(domainsToRemove);
			}

		});
	}

	/**
	 * @since 9.3
	 */
	public void setAllAutoConnect(boolean auto) {
		for (ScaDomainConnectionDef def : this.domainConnectionDefs) {
			def.setConnectOnStartup(auto);
		}
		this.list.refresh();
	}

	/**
	 * Returns this field editor's button box containing the Add, Remove, Up, and Down button.
	 * 
	 * @param parent the parent control
	 * @return the button box
	 */
	public Composite getButtonBoxControl(final Composite parent) {
		if (this.buttonBox == null) {
			this.buttonBox = new Composite(parent, SWT.NULL);
			final GridLayout layout = new GridLayout();
			layout.marginWidth = 0;
			this.buttonBox.setLayout(layout);
			createButtons(this.buttonBox);
			this.buttonBox.addDisposeListener(new DisposeListener() {
				@Override
				public void widgetDisposed(final DisposeEvent event) {
					DomainListEditor.this.buttonBox = null;
				}
			});

		} else {
			checkParent(this.buttonBox, parent);
		}

		return this.buttonBox;
	}

	@Override
	public int getNumberOfControls() {
		return 2;
	}

	@Override
	public void setFocus() {
		if (this.list != null) {
			this.list.getList().setFocus();
		}
	}

	@Override
	public void setEnabled(final boolean enabled, final Composite parent) {
		super.setEnabled(enabled, parent);
		this.list.getList().setEnabled(enabled);
	}
}
