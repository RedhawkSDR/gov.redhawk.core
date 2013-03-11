/**
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.sca.internal.ui.handlers;

import gov.redhawk.model.sca.DomainConnectionException;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.sca.ScaPlugin;
import gov.redhawk.sca.ui.ScaUiPlugin;
import gov.redhawk.sca.ui.preferences.DomainEntryWizard;
import gov.redhawk.sca.ui.preferences.DomainSettingModel.ConnectionMode;

import java.util.Collections;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.handlers.HandlerUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class InitHandler.
 */
public class NewDomainHandler extends AbstractHandler implements IHandler {

	/**
	 * {@inheritDoc}
	 */

	public Object execute(final ExecutionEvent event) throws ExecutionException {
		final ScaDomainManagerRegistry registry = ScaPlugin.getDefault().getDomainManagerRegistry();

		final DomainEntryWizard wizard = new DomainEntryWizard();
		wizard.setShowExtraSettings(true);
		wizard.setRegistry(registry);
		wizard.setWindowTitle("New Domain Manager");

		final WizardDialog dialog = new WizardDialog(HandlerUtil.getActiveShell(event), wizard);
		if (dialog.open() == IStatus.OK) {
			final boolean autoConnect = wizard.getConnectionMode() == ConnectionMode.AUTO;
			final String domainName = wizard.getDomainName();
			final Map<String, String> connectionProperties = Collections.singletonMap(ScaDomainManager.NAMING_SERVICE_PROP, wizard.getNameServiceInitRef());

			// for the "AUTO" or "NOW" case we need to connect ourselves
			final ConnectionMode mode = wizard.getConnectionMode();

			final Job connectionJob = new Job("Creating domain connection") {

				@Override
				protected IStatus run(final IProgressMonitor monitor) {
					try {
						final ScaDomainManager[] domain = new ScaDomainManager[1];
						ScaModelCommand.execute(registry, new ScaModelCommand() {

							public void execute() {
								domain[0] = registry.createDomain(domainName, autoConnect, connectionProperties);
							}

						});

						if (mode == ConnectionMode.NOW || mode == ConnectionMode.AUTO) {
							domain[0].connect(monitor, RefreshDepth.SELF);
						}
					} catch (final DomainConnectionException e) {
						return new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, "Failed to connect to domain", e);
					}
					return Status.OK_STATUS;
				}

			};
			connectionJob.setUser(true);
			connectionJob.schedule();
		}
		return null;
	}

}
