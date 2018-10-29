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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
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
import org.eclipse.ui.dialogs.ListDialog;

import gov.redhawk.sca.jobs.FindDomainsJob;
import gov.redhawk.sca.ui.ScaUiPlugin;
import gov.redhawk.sca.ui.preferences.DomainSettingModel.ConnectionMode;
import gov.redhawk.sca.util.CorbaURIUtil;
import gov.redhawk.sca.validation.NamingServiceValidator;

/**
 * @since 7.0
 */
public class DomainEntryWizardPage extends WizardPage {

	/**
	 * Delay between animation updates of the progress icons.
	 */
	private static final int ANIMATION_DELAY_MS = 250;

	/**
	 * Delay between the user typing a syntactically valid name service ref, and the IDE searching for domains. This
	 * helps ensure we don't try to search for domains after every single keystroke a user might make.
	 */
	private static final long DOMAIN_SEARCH_DELAY_MS = 1000;

	private final DomainSettingModel model = new DomainSettingModel();
	private boolean showExtraSettings;
	private final DataBindingContext context = new DataBindingContext();

	/**
	 * The list of existing domain display names (used to avoid a UI name collision).
	 */
	private List<String> existingDisplayNames;

	private String editLocalDomainName = null;

	private ResourceManager resourceManager;

	private static final String PATH_IMAGE_NO_DOMAINS = "/icons/clcl16/uninstall.gif"; //$NON-NLS-1$

	/**
	 * Image displayed on the button when there are no domains.
	 */
	private ImageDescriptor imageNoDomains;

	private static final String PATH_IMAGE_DOMAINS = "/icons/clcl16/add.gif"; //$NON-NLS-1$

	/**
	 * Image displayed on the button when there are domains that can be selected.
	 */
	private ImageDescriptor imageDomains;

	private static final String PATH_IMAGES_PROGRESS = "/icons/domainEntryWizard/%d.png"; //$NON-NLS-1$

	/**
	 * Images that are displayed on the button when a search for domains is in progress.
	 */
	private ImageDescriptor[] imagesProgress;

	/**
	 * Index of the image within {@link #imagesProgress} that is being displayed.
	 */
	private int imageIndex = 0;

	private Text displayNameText;
	private Text domainNameText;
	private Button domainSelectButton;
	private Text nameServiceText;
	private Binding nameServiceBinding;

	private FindDomainsJob findDomainsJob = null;

	/**
	 * Instantiates a host entry dialog.
	 * 
	 * @param parentShell the parent shell
	 * @since 4.0
	 */
	public DomainEntryWizardPage(final String pageName) {
		super(pageName, Messages.DomainEntryWizardPage_PageTitle, null);
		this.setDescription(Messages.DomainEntryWizardPage_PageDescription);
		this.existingDisplayNames = new ArrayList<String>();
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
		resourceManager.dispose();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void createControl(final Composite parent) {
		createImageDescriptors();

		final Composite container = new Composite(parent, SWT.NONE);
		final GridLayout gridLayout = new GridLayout(3, false);
		container.setLayout(gridLayout);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		// Name service
		final Label nameServiceLabel = new Label(container, SWT.NONE);
		nameServiceLabel.setText(Messages.DomainEntryWizardPage_NameService);
		GridDataFactory labelGdf = GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER);
		nameServiceLabel.setLayoutData(labelGdf.create());

		nameServiceText = new Text(container, SWT.BORDER);
		nameServiceText.setToolTipText(Messages.DomainEntryWizardPage_NameServiceTooltip);
		GridDataFactory textGdf = GridDataFactory.fillDefaults().grab(true, false).span(2, 1);
		nameServiceText.setLayoutData(textGdf.create());

		// Domain name
		final Label domainNameLabel = new Label(container, SWT.NONE);
		domainNameLabel.setText(Messages.DomainEntryWizardPage_DomainName);
		domainNameLabel.setLayoutData(labelGdf.create());

		domainNameText = new Text(container, SWT.BORDER);
		domainNameText.setToolTipText(Messages.DomainEntryWizardPage_DomainNameTooltip);
		domainNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		domainSelectButton = new Button(container, SWT.PUSH);
		domainSelectButton.setImage(resourceManager.createImage(imageNoDomains));
		domainSelectButton.setToolTipText(Messages.DomainEntryWizardPage_DomainButtonTooltip_InvalidNameServiceRef);
		domainSelectButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));

		// Display Name
		final Label displayNameLabel = new Label(container, SWT.NONE);
		displayNameLabel.setText(Messages.DomainEntryWizardPage_DisplayName);
		displayNameLabel.setLayoutData(labelGdf.create());

		displayNameText = new Text(container, SWT.BORDER);
		displayNameText.setToolTipText(Messages.DomainEntryWizardPage_DisplayNameTooltip);
		displayNameText.setLayoutData(textGdf.create());

		// Display name binding & validation
		UpdateValueStrategy validator = new UpdateValueStrategy();
		validator.setAfterConvertValidator(value -> {
			final String name = (String) value;
			if ((name == null) || (name.length() == 0)) {
				return ValidationStatus.error(Messages.DomainEntryWizardPage_DomainName_NoName);
			}

			// if we're not editing, the domain name must be different
			if (name.equals(editLocalDomainName)) {
				return ValidationStatus.ok();
			} else if (existingDisplayNames.contains(name)) {
				return ValidationStatus.error(Messages.DomainEntryWizardPage_DomainName_AlreadyExists);
			}

			return ValidationStatus.ok();
		});
		Binding binding = this.context.bindValue(WidgetProperties.text(SWT.Modify).observe(displayNameText),
			BeanProperties.value(this.model.getClass(), DomainSettingModel.PROP_LOCAL_DOMAIN_NAME).observe(this.model), validator, null);
		ControlDecorationSupport.create(binding, SWT.TOP | SWT.LEFT);

		// Domain name binding & validation
		validator = new UpdateValueStrategy();
		validator.setAfterConvertValidator(value -> {
			if (((String) value).isEmpty()) {
				return ValidationStatus.error(Messages.DomainEntryWizardPage_Validation_MissingValue);
			}
			return ValidationStatus.ok();
		});
		binding = this.context.bindValue(WidgetProperties.text(SWT.Modify).observe(domainNameText),
			BeanProperties.value(this.model.getClass(), DomainSettingModel.PROP_DOMAIN_NAME).observe(this.model), validator, null);
		ControlDecorationSupport.create(binding, SWT.TOP | SWT.LEFT);

		// Name service binding & validation
		validator = new UpdateValueStrategy();
		validator.setConverter(IConverter.create(String.class, String.class, fromObject -> ((String) fromObject).trim()));
		validator.setAfterGetValidator(new NamingServiceValidator());
		nameServiceBinding = this.context.bindValue(WidgetProperties.text(SWT.Modify).observe(nameServiceText),
			BeanProperties.value(this.model.getClass(), DomainSettingModel.PROP_NAME_SERVICE_INIT_REF).observe(this.model), validator, null);
		ControlDecorationSupport.create(nameServiceBinding, SWT.TOP | SWT.LEFT);

		// Setting domain name -> sets display name
		context.bindValue(WidgetProperties.text(SWT.Modify).observe(domainNameText), WidgetProperties.text(SWT.Modify).observe(displayNameText), null,
			new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER));

		// Setting the name service reference -> search for domains
		nameServiceText.addModifyListener(event -> {
			doDomainSearch(false);
		});

		// Clicking the domain selection button
		domainSelectButton.addListener(SWT.Selection, event -> {
			domainSelectionButtonClicked();
		});

		// Start an initial search
		doDomainSearch(true);

		if (this.showExtraSettings) {
			createConnectionSettingsGroup(container);
		}

		setControl(container);

		WizardPageSupport.create(this, context);
	}

	private void createImageDescriptors() {
		resourceManager = new LocalResourceManager(JFaceResources.getResources());
		try {
			IPath path = new Path(ScaUiPlugin.PLUGIN_ID).append(PATH_IMAGE_NO_DOMAINS);
			imageNoDomains = ImageDescriptor.createFromURL(new URL(URI.createPlatformPluginURI(path.toString(), false).toString()));
			path = new Path(ScaUiPlugin.PLUGIN_ID).append(PATH_IMAGE_DOMAINS);
			imageDomains = ImageDescriptor.createFromURL(new URL(URI.createPlatformPluginURI(path.toString(), false).toString()));
			imagesProgress = new ImageDescriptor[8];
			for (int i = 0; i < 8; i++) {
				path = new Path(ScaUiPlugin.PLUGIN_ID).append(String.format(PATH_IMAGES_PROGRESS, i + 1));
				imagesProgress[i] = ImageDescriptor.createFromURL(new URL(URI.createPlatformPluginURI(path.toString(), false).toString()));
				resourceManager.createImage(imagesProgress[i]);
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Kicks off the process that searches for domains. Any existing search is canceled / its results are removed.
	 * @param immediate True if the search should begin immediately. False to schedule the search with a slight delay
	 * which helps avoid prematurely searching while a user might still be typing the naming service name.
	 */
	private void doDomainSearch(boolean immediate) {
		// Cancel & remove an existing search job
		if (findDomainsJob != null) {
			if (findDomainsJob.getResult() == null) {
				findDomainsJob.cancel();
			}
			findDomainsJob = null;
		}

		// If the field doesn't validate, we can't search for domains
		IStatus status = (IStatus) nameServiceBinding.getValidationStatus().getValue();
		if (!status.isOK()) {
			domainSelectButton.setImage(resourceManager.createImage(imageNoDomains));
			domainSelectButton.setToolTipText(Messages.DomainEntryWizardPage_DomainButtonTooltip_InvalidNameServiceRef);
			return;
		}

		FindDomainsJob newJob = new FindDomainsJob(nameServiceText.getText(), true);
		findDomainsJob = newJob;

		// On job completion, update the UI
		Runnable updateDomainList = () -> {
			// Ensure the control hasn't been disposed, and a new job wasn't started
			if (domainSelectButton.isDisposed() || findDomainsJob != newJob) {
				return;
			}

			if (!findDomainsJob.getResult().isOK()) {
				// Job encountered problems
				domainSelectButton.setImage(resourceManager.createImage(imageNoDomains));
				domainSelectButton.setToolTipText(findDomainsJob.getResult().getMessage());
			} else if (findDomainsJob.getDomainNames().isEmpty()) {
				// Job completed, but no domains found
				domainSelectButton.setImage(resourceManager.createImage(imageNoDomains));
				domainSelectButton.setToolTipText(Messages.DomainEntryWizardPage_DomainButtonTooltip_NoDomainsFound);
			} else {
				// Domains were found
				domainSelectButton.setImage(resourceManager.createImage(imageDomains));
				domainSelectButton.setToolTipText(Messages.DomainEntryWizardPage_DomainButtonTooltip_ClickToSelect);
			}
		};
		findDomainsJob.addJobChangeListener(new JobChangeAdapter() {
			@Override
			public void done(IJobChangeEvent event) {
				if (event.getResult().getSeverity() == IStatus.CANCEL) {
					return;
				}
				try {
					domainSelectButton.getDisplay().asyncExec(updateDomainList);
				} catch (SWTException e) {
					// Widget was likely disposed in the interim (wizard has been closed)
				}
			}
		});

		// Show a progress indicator on the button while the job runs
		Runnable progressIndicator = new Runnable() {
			@Override
			public void run() {
				// Ensure the control hasn't been disposed, and the job is still running
				if (domainSelectButton.isDisposed() || findDomainsJob != newJob || findDomainsJob.getResult() != null) {
					return;
				}

				// Advanced the image
				imageIndex = (imageIndex + 1) % imagesProgress.length;
				domainSelectButton.setImage(resourceManager.createImage(imagesProgress[imageIndex]));
				domainSelectButton.setToolTipText(Messages.DomainEntryWizardPage_DomainButtonTooltip_Searching);
				getShell().getDisplay().timerExec(ANIMATION_DELAY_MS, this);
			}
		};
		getShell().getDisplay().timerExec(ANIMATION_DELAY_MS, progressIndicator);

		findDomainsJob.schedule(DOMAIN_SEARCH_DELAY_MS);
	}

	/**
	 * Called when the domain selection button is clicked.
	 */
	private void domainSelectionButtonClicked() {
		// Nothing to do if the search hasn't run, or hasn't completed yet
		if (findDomainsJob == null || findDomainsJob.getResult() == null) {
			return;
		}

		List<String> runningDomainNames = findDomainsJob.getDomainNames();
		if (runningDomainNames.isEmpty()) {
			// No domains - run the search again
			doDomainSearch(true);
		} else {
			// Let the user choose a domain
			ListDialog dialog = new ListDialog(getShell());
			dialog.setTitle(Messages.DomainEntryWizardPage_DomainSelectionDialog_Title);
			dialog.setMessage(Messages.DomainEntryWizardPage_DomainSelectionDialog_Message);
			dialog.setContentProvider(ArrayContentProvider.getInstance());
			dialog.setLabelProvider(new LabelProvider());
			dialog.setInput(runningDomainNames);
			if (dialog.open() != Window.OK) {
				return;
			}

			// Ensure the display name is unique
			String domainName = (String) dialog.getResult()[0];
			String displayName = domainName;
			for (int i = 2; existingDisplayNames.contains(displayName); i++) {
				displayName = domainName + "_" + i; //$NON-NLS-1$
			}

			// Be sure to set the display name first, since it will adjust the domain name
			displayNameText.setText(displayName);
			domainNameText.setText(domainName);
		}
	}

	private void createConnectionSettingsGroup(final Composite parent) {
		final Group connectionSettings = new Group(parent, SWT.BORDER);
		connectionSettings.setText(Messages.DomainEntryWizardPage_ConnectionSettingsGroup);
		connectionSettings.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		connectionSettings.setLayout(new RowLayout(SWT.VERTICAL));

		final Button manualConnect = new Button(connectionSettings, SWT.RADIO);
		manualConnect.setText(Messages.DomainEntryWizardPage_DontConnect);
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
		connectNow.setText(Messages.DomainEntryWizardPage_ConnectOnce);
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
		connectAuto.setText(Messages.DomainEntryWizardPage_AlwaysConnect);
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
	public void setLocalDomainName(String localName) {
		model.setLocalDomainName(localName);
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
		this.existingDisplayNames = domainNames;
	}

	/**
	 * This configures the wizard page to edit a connection, as opposed to
	 * creating a new one.
	 * 
	 * @param localName The label to show for the domain (instead of the domain's name)
	 * @param domainName The name of the domain in the naming service
	 * @param initRef an appropriate initial reference for the naming service
	 * @since 10.0
	 */
	public void setEdit(final String localName, final String domainName, final String initRef) {
		if (localName != null) {
			this.model.setLocalDomainName(localName);
			this.editLocalDomainName = localName;
		} else {
			this.model.setLocalDomainName(domainName);
			this.editLocalDomainName = domainName;
		}
		this.model.setDomainName(domainName);
		this.model.setNameServiceInitRef(initRef);
	}

}
