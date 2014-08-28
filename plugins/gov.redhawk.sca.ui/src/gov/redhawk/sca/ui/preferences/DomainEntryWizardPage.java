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
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
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

/**
 * @since 7.0
 * 
 */
public class DomainEntryWizardPage extends WizardPage {

	private final DomainSettingModel model = new DomainSettingModel();
	private boolean showExtraSettings;
	private final DataBindingContext context = new DataBindingContext();
	private List<String> domainNames;
	private String editLocalDomainName = null;
	private static final IValidator NON_EMPTY_STRING = new IValidator() {

		@Override
		public IStatus validate(Object value) {
			if (((String) value).isEmpty()) {
				return ValidationStatus.error("Missing value.");
			}
			return ValidationStatus.ok();
		}
	};

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

		final Label localDomainNameLabel = new Label(container, SWT.NONE);
		localDomainNameLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
		localDomainNameLabel.setText("Display Name:");

		final Text localDomainNameField = new Text(container, SWT.BORDER);
		localDomainNameField.setToolTipText("Name that this domain will be displayed as locally.");
		localDomainNameField.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false, 1, 1));

		final Label domainNameLabel = new Label(container, SWT.NONE);
		domainNameLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
		domainNameLabel.setText("Domain Name:");

		final Text domainNameField = new Text(container, SWT.BORDER);
		domainNameField.setToolTipText("Name the domain is registered as within the naming service.");
		domainNameField.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false, 1, 1));

		context.bindValue(SWTObservables.observeText(localDomainNameField, SWT.Modify), SWTObservables.observeText(domainNameField, SWT.Modify), null,
			new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER));

		final Label nameServiceLabel = new Label(container, SWT.NONE);
		nameServiceLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
		nameServiceLabel.setText("Name Service:");

		final Text nameServiceField = new Text(container, SWT.BORDER);
		nameServiceField.setToolTipText("The CORBA URI that points to the naming service.  This is usually of the form 'corbaname::<hostname>'");
		nameServiceField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		// Add the domainName validator, needs to reference the initref
		UpdateValueStrategy validator = new UpdateValueStrategy();
		validator.setAfterConvertValidator(new IValidator() {

			@Override
			public IStatus validate(final Object value) {
				final String name = (String) value;
				if ((name == null) || (name.length() == 0)) {
					return ValidationStatus.error("Must enter a domain name.");
				}

				// if we're not editing, the domain name must be different
				if (name.equals(DomainEntryWizardPage.this.editLocalDomainName)) {
					return ValidationStatus.ok();
				} else if (DomainEntryWizardPage.this.domainNames.contains(name)) {
					return ValidationStatus.error("Domain Name already exists, please enter another.");
				}

				return ValidationStatus.ok();
			}
		});
		Binding binding = this.context.bindValue(SWTObservables.observeText(localDomainNameField, SWT.Modify),
			BeansObservables.observeValue(this.model, DomainSettingModel.PROP_LOCAL_DOMAIN_NAME), validator, null);
		ControlDecorationSupport.create(binding, SWT.TOP | SWT.LEFT);

		validator = new UpdateValueStrategy();
		validator.setAfterConvertValidator(NON_EMPTY_STRING);
		binding = this.context.bindValue(SWTObservables.observeText(domainNameField, SWT.Modify),
			BeansObservables.observeValue(this.model, DomainSettingModel.PROP_DOMAIN_NAME), validator, null);
		ControlDecorationSupport.create(binding, SWT.TOP | SWT.LEFT);

		validator = new UpdateValueStrategy();
		validator.setAfterConvertValidator(NON_EMPTY_STRING);
		binding = this.context.bindValue(SWTObservables.observeText(nameServiceField, SWT.Modify),
			BeansObservables.observeValue(this.model, DomainSettingModel.PROP_NAME_SERVICE_INIT_REF), validator, null);
		ControlDecorationSupport.create(binding, SWT.TOP | SWT.LEFT);

		if (this.showExtraSettings) {
			createConnectionSettingsGroup(container);
		}

		setControl(container);

		WizardPageSupport.create(this, context);
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

	/**
	 * @since 10.0
	 */
	public void setLocalDomainName(String name) {
		model.setLocalDomainName(name);
	}

	/**
	 * @since 10.0
	 */
	public String getLocalDomainName() {
		return model.getLocalDomainName();
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
	 * This configures the wizard page to edit a connection, as opposed to
	 * creating a new one.
	 * 
	 * @param domainName the Domain Name of the connection
	 * @param initRef the NameService Initial Reference of the connection
	 * @since 10.0
	 */
	public void setEdit(final String name, final String domainName, final String initRef) {
		if (name != null) {
			this.model.setLocalDomainName(name);
			this.editLocalDomainName = name;
		} else {
			this.model.setLocalDomainName(domainName);
			this.editLocalDomainName = domainName;
		}
		this.model.setDomainName(domainName);
		this.model.setNameServiceInitRef(initRef);
	}
	
	/**
	 * 
	 * This configures the wizard page to edit a connection, as opposed to
	 * creating a new one.
	 * 
	 * @param domainName the Domain Name of the connection
	 * @param initRef the NameService Initial Reference of the connection
	 * @since 8.0
	 * @deprecated Use {@link #setEdit(String, String, String)}
	 */
	@Deprecated
	public void setEdit(final String domainName, final String initRef) {
		setEdit(domainName, domainName, initRef);
	}

}
