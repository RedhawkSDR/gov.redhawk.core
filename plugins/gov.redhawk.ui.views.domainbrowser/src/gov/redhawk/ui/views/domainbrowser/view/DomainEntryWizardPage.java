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
package gov.redhawk.ui.views.domainbrowser.view;

import gov.redhawk.sca.ui.preferences.DomainSettingModel;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @since 7.0
 * 
 */
public class DomainEntryWizardPage extends WizardPage {

	private Text domainNameField;
	private Text nameServiceField;
	private final String[] errors;
	private final DataBindingContext context = new DataBindingContext();
	private final DomainSettingModel model = new DomainSettingModel();

	/**
	 * Instantiates a host entry dialog.
	 * 
	 * @param parentShell the parent shell
	 * @since 4.0
	 */
	public DomainEntryWizardPage(final String pageName) {
		super(pageName, "Domain Manager Connection Settings", null);
		this.setDescription("Enter the settings for the Domain Manager.");
		this.errors = new String[2];
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

		this.domainNameField = new Text(container, SWT.BORDER);
		domainNameField.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false, 1, 1));
		UpdateValueStrategy validator = new UpdateValueStrategy();

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

					DomainEntryWizardPage.this.errors[0] = null;
					return ValidationStatus.ok();
				} finally {
					DomainEntryWizardPage.this.updateMessage();
				}
			}
		});
		
		final Binding nameBinding = this.context.bindValue(SWTObservables.observeText(domainNameField, SWT.Modify),
		        BeansObservables.observeValue(this.model, DomainSettingModel.PROP_DOMAIN_NAME), validator, null);

		final Label nameServiceLabel = new Label(container, SWT.NONE);
		nameServiceLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
		nameServiceLabel.setText("Name Service Initial Reference:");

		this.nameServiceField = new Text(container, SWT.BORDER);
		nameServiceField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		// Add the InitRef validator
		validator = new UpdateValueStrategy();
		validator.setAfterConvertValidator(new IValidator() {

			@Override
			public IStatus validate(final Object value) {
				try {
					final String newValue = (String) value;
					if (newValue == null || newValue.length() == 0) {
						DomainEntryWizardPage.this.errors[1] = "Must enter a Name Service Initial Reference.";
						return ValidationStatus.error(DomainEntryWizardPage.this.errors[1]);
					}
					DomainEntryWizardPage.this.errors[1] = null;
					return ValidationStatus.ok();
					
				} finally {
					DomainEntryWizardPage.this.updateMessage();
				}
			}
		});

		this.context.bindValue(SWTObservables.observeText(nameServiceField, SWT.Modify),
		        BeansObservables.observeValue(this.model, DomainSettingModel.PROP_NAME_SERVICE_INIT_REF), validator, null);
		
		setControl(container);
	}

	public String getNameServiceInitRef() {
		return this.model.getNameServiceInitRef();
	}

	public String getDomainName() {
		return this.model.getDomainName();
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

}
