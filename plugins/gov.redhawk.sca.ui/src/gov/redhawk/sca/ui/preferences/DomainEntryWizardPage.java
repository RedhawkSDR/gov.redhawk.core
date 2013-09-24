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
package gov.redhawk.sca.ui.preferences;

import gov.redhawk.sca.ui.preferences.DomainSettingModel.ConnectionMode;
import gov.redhawk.sca.util.CorbaURIUtil;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.progress.UIJob;

/**
 * @since 7.0
 * 
 */
public class DomainEntryWizardPage extends WizardPage {

	private final DomainSettingModel model = new DomainSettingModel();
	private boolean showExtraSettings;
	private final DataBindingContext context = new DataBindingContext();
	private List<String> domainNames;
	private String editDomainName = null;
	private String editInitRef = null;
	private final String[] errors;

	/**
	 * Instantiates a host entry dialog.
	 * 
	 * @param parentShell the parent shell
	 * @since 4.0
	 */
	public DomainEntryWizardPage(final String pageName) {
		super(pageName, "Domain Manager Connection Settings", null);
		this.setDescription("Enter the settings for the Domain Manager.");
		this.domainNames = new ArrayList<String>();
		this.errors = new String[2];
	}

	public void setShowExtraSettings(final boolean showExtraSettings) {
		this.showExtraSettings = showExtraSettings;
	}

	public boolean isShowExtraSettings() {
		return this.showExtraSettings;
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createControl(final Composite parent) {
		final Composite container = new Composite(parent, SWT.NONE);
		final GridLayout gridLayout = new GridLayout(2, false);
		container.setLayout(gridLayout);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		final Label domainNameLabel = new Label(container, SWT.NONE);
		domainNameLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
		domainNameLabel.setText("Domain Name:");

		final Text domainNameField = new Text(container, SWT.BORDER);
		domainNameField.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false, 1, 1));
		UpdateValueStrategy validator = new UpdateValueStrategy();

		final Label nameServiceLabel = new Label(container, SWT.NONE);
		nameServiceLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
		nameServiceLabel.setText("Name Service Initial Reference:");

		final Text nameServiceField = new Text(container, SWT.BORDER);
		nameServiceField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		// Add the domainName validator, needs to reference the initref
		validator.setAfterConvertValidator(new IValidator() {

			@Override
			public IStatus validate(final Object value) {
				try {
					final String name = (String) value;
					if ((name == null) || (name.length() == 0)) {
						DomainEntryWizardPage.this.errors[0] = "Must enter a domain name.";
						return ValidationStatus.error(DomainEntryWizardPage.this.errors[0]);
					}

					final boolean contained = DomainEntryWizardPage.this.domainNames.contains(name);
					// if we're not editing, the domain name must be different
					if (DomainEntryWizardPage.this.editDomainName == null) {
						if (contained) {
							DomainEntryWizardPage.this.errors[0] = "Domain Name already exists, please enter another.";
							return ValidationStatus.error(DomainEntryWizardPage.this.errors[0]);
						}
					} else {
						// If we are editing, the domain name can be the same, but it must
						// match the edited domain name and the initRef must be different
						final boolean initRefSame = DomainEntryWizardPage.this.editInitRef.equals(DomainEntryWizardPage.this.model.getNameServiceInitRef());
						if (contained && !(name.equals(DomainEntryWizardPage.this.editDomainName) && !initRefSame)) {
							DomainEntryWizardPage.this.errors[0] = "Domain Name already exists, please enter another.";
							return ValidationStatus.error(DomainEntryWizardPage.this.errors[0]);
						}
					}
					DomainEntryWizardPage.this.errors[0] = null;

					return ValidationStatus.ok();
				} finally {
					DomainEntryWizardPage.this.updateMessage();
				}
			}
		});
		final Binding nameBinding = this.context.bindValue(SWTObservables.observeText(domainNameField, SWT.Modify),
		        BeansObservables.observeValue(this.model, DomainSettingModel.PROP_DOMAIN_NAME), validator, null);

		// Add the InitRef validator
		validator = new UpdateValueStrategy();
		validator.setAfterConvertValidator(new IValidator() {

			@Override
			public IStatus validate(final Object value) {
				final int WAIT_TIME = 100;
				try {
					final String newValue = (String) value;
					if (newValue == null || newValue.length() == 0) {
						DomainEntryWizardPage.this.errors[1] = "Must enter a Name Service Initial Reference.";
						return ValidationStatus.error(DomainEntryWizardPage.this.errors[1]);
					}
					DomainEntryWizardPage.this.errors[1] = null;

					if (DomainEntryWizardPage.this.editDomainName != null) {
						final UIJob j = new UIJob(getShell().getDisplay(), "Revalidate Domain Name") {
							@Override
							public IStatus runInUIThread(final IProgressMonitor monitor) {
								nameBinding.validateTargetToModel();
								return Status.OK_STATUS;
							}
						};
						j.schedule(WAIT_TIME);
					}
					return ValidationStatus.ok();
				} finally {
					DomainEntryWizardPage.this.updateMessage();
				}
			}
		});
		this.context.bindValue(SWTObservables.observeText(nameServiceField, SWT.Modify),
		        BeansObservables.observeValue(this.model, DomainSettingModel.PROP_NAME_SERVICE_INIT_REF), validator, null);

		if (this.showExtraSettings) {
			createConnectionSettingsGroup(container);
		}

		setControl(container);
	}

	private void createConnectionSettingsGroup(final Composite parent) {
		final Group connectionSettings = new Group(parent, SWT.BORDER);
		connectionSettings.setText("Connection Settings");
		connectionSettings.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		connectionSettings.setLayout(new RowLayout(SWT.VERTICAL));

		final Button manualConnect = new Button(connectionSettings, SWT.RADIO);
		manualConnect.setText("Don't connect");
		manualConnect.setSelection(this.model.getConnectionMode() == ConnectionMode.MANUAL);
		manualConnect.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(final SelectionEvent e) {
				widgetSelected(e);
			}

			@Override
			public void widgetSelected(final SelectionEvent e) {
				DomainEntryWizardPage.this.model.setConnectionMode(ConnectionMode.MANUAL);
			}

		});

		final Button connectNow = new Button(connectionSettings, SWT.RADIO);
		connectNow.setText("Connect once");
		connectNow.setSelection(this.model.getConnectionMode() == ConnectionMode.NOW);
		connectNow.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(final SelectionEvent e) {
				widgetSelected(e);
			}

			@Override
			public void widgetSelected(final SelectionEvent e) {
				DomainEntryWizardPage.this.model.setConnectionMode(ConnectionMode.NOW);
			}

		});

		final Button connectAuto = new Button(connectionSettings, SWT.RADIO);
		connectAuto.setText("Always connect");
		connectAuto.setSelection(this.model.getConnectionMode() == ConnectionMode.AUTO);
		connectAuto.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(final SelectionEvent e) {
				widgetSelected(e);
			}

			@Override
			public void widgetSelected(final SelectionEvent e) {
				DomainEntryWizardPage.this.model.setConnectionMode(ConnectionMode.AUTO);
			}

		});
	}

	public String getNameServiceInitRef() {
		String retVal = this.model.getNameServiceInitRef();
		retVal = CorbaURIUtil.addDefaultPrefix(retVal);
		retVal = CorbaURIUtil.addDefaultPort(retVal);
		return retVal;
	}

	public void setNameServiceInitRef(final String nameServiceInitRef) {
		this.model.setNameServiceInitRef(nameServiceInitRef);
	}

	public String getDomainName() {
		return this.model.getDomainName();
	}

	public void setDomainName(final String domainName) {
		this.model.setDomainName(domainName);
	}

	public ConnectionMode getConnectionMode() {
		return this.model.getConnectionMode();
	}

	public void setConnectionMode(final ConnectionMode mode) {
		this.model.setConnectionMode(mode);
	}

	/**
	 * This sets the list of domain names that are to be checked against for 
	 * duplicates.
	 * 
	 * @param domainNames a List of domain names
	 * @since 8.0
	 */
	public void setDomains(final List<String> domainNames) {
		this.domainNames = domainNames;
	}

	/**
	 * This updates the page's error message and completion status. It is used
	 * to validate two fields at the same time since editing the initRef could
	 * make two domain names being the same a valid scenario (the original one
	 * is removed).
	 * @since 8.0
	 */
	protected void updateMessage() {
		final boolean error = (this.errors[0] != null) || (this.errors[1] != null);
		if (error) {
			setErrorMessage((this.errors[0] != null) ? this.errors[0] : this.errors[1]); // SUPPRESS CHECKSTYLE AvoidInline
		} else {
			setErrorMessage(null);
		}

		setPageComplete(!error);
	}

	/**
	 * This configures the wizard page to edit a connection, as opposed to 
	 * creating a new one.
	 * 
	 * @param domainName the Domain Name of the connection
	 * @param initRef the NameService Initial Reference of the connection
	 * @since 8.0
	 */
	public void setEdit(final String domainName, final String initRef) {
		this.model.setNameServiceInitRef(initRef);
		this.model.setDomainName(domainName);
		this.editDomainName = domainName;
		this.editInitRef = initRef;
	}

}
