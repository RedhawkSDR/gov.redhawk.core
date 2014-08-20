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
package gov.redhawk.sca.ui;

import gov.redhawk.model.sca.DomainConnectionException;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.commands.ScaModelCommandWithResult;
import gov.redhawk.sca.ScaPlugin;
import gov.redhawk.sca.ui.preferences.DomainEntryWizard;
import gov.redhawk.sca.ui.preferences.DomainSettingModel.ConnectionMode;

import java.util.Collections;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * @since 2.1
 */
public class DomainConnectionUtil {

	private DomainConnectionUtil() {
	}

	/**
	 * @since 10.0
	 * @deprecated Use new {@link #showDialog(Display, String, String)}
	 */
	@Deprecated
	public static void showDialog(final String host, final String domainName, String localName) {
		DomainConnectionUtil.showDialog(null, host, domainName, localName);
	}
	
	/**
	 * @deprecated Use {@link #showDialog(Display, String, String, String)} instead.
	 * @since 9.3
	 */
	@Deprecated
	public static void showDialog(Display context, final String host, final String domainName) {
		showDialog(context, host, domainName, null);
	}

	/**
	 * @since 10.0
	 */
	public static void showDialog(Display context, final String host, final String domainName, final String localName) {
		final DomainEntryWizard wizard = new DomainEntryWizard();
		final ScaDomainManagerRegistry registry = ScaPlugin.getDefault().getDomainManagerRegistry(context);
		wizard.setNameServiceInitRef(host);
		wizard.setRegistry(registry);
		wizard.setDomainName(domainName);
		wizard.setLocalDomainName(localName);
		wizard.setShowExtraSettings(true);
		wizard.setWindowTitle("Edit Domain Manager");

		final WizardDialog dialog = new WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), wizard);
		if (dialog.open() == IStatus.OK) {
			final boolean autoConnect = wizard.getConnectionMode() == ConnectionMode.AUTO;
			final Map<String, String> connectionProperties = Collections.singletonMap(ScaDomainManager.NAMING_SERVICE_PROP, wizard.getNameServiceInitRef());

			// In the "AUTO" connect case the preference monitor
			// will connect for us
			// for the "NOW" case we need to connect ourselves
			if (wizard.getConnectionMode() == ConnectionMode.NOW) {
				final Job connectionJob = new Job("Connecting to domain") {

					@Override
					protected IStatus run(final IProgressMonitor monitor) {
						final ScaDomainManager domain = ScaModelCommandWithResult.execute(registry, new ScaModelCommandWithResult<ScaDomainManager>() {
							@Override
							public void execute() {
								setResult(registry.createDomain(localName, domainName, autoConnect, connectionProperties));
							}
						});
						if (domain != null) {
							try {
								domain.connect(monitor, RefreshDepth.SELF);
								return Status.OK_STATUS;
							} catch (final DomainConnectionException e) {
								return new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, "Failed to connect to domain", e);
							}
						} else {
							return Status.CANCEL_STATUS;
						}
					}

				};
				connectionJob.setUser(true);
				connectionJob.schedule();
			}
		}
	}
}
