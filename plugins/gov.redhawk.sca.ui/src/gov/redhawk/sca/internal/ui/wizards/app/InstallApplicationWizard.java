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
package gov.redhawk.sca.internal.ui.wizards.app;

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.sca.ui.ScaUiPlugin;

import java.lang.reflect.InvocationTargetException;

import mil.jpeojtrs.sca.sad.SoftwareAssembly;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.statushandlers.StatusManager;

import CF.InvalidFileName;
import CF.InvalidProfile;
import CF.DomainManagerPackage.ApplicationAlreadyInstalled;
import CF.DomainManagerPackage.ApplicationInstallationError;

/**
 * 
 */
public class InstallApplicationWizard extends Wizard {

	private InstallApplicationWizardPage page;
	private final ScaDomainManager domMgr;

	public InstallApplicationWizard(final ScaDomainManager domMgr) {
		this.domMgr = domMgr;
		this.setNeedsProgressMonitor(true);
	}

	@Override
	public void addPages() {
		super.addPages();
		this.page = new InstallApplicationWizardPage(this.domMgr);
		addPage(this.page);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean performFinish() {
		final Object[] elements = this.page.getCheckedElements();
		try {
			this.getContainer().run(true, false, new IRunnableWithProgress() {

				public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					monitor.beginTask("Installing application(s)...", elements.length);
					try {
						for (final Object obj : elements) {
							if (obj instanceof SoftwareAssembly) {
								final SoftwareAssembly sad = (SoftwareAssembly) obj;
								monitor.subTask("Installing application " + sad.getName());
								try {
									InstallApplicationWizard.this.domMgr.installApplication(sad.eResource().getURI().path());
								} catch (final InvalidProfile e) {
									throw new InvocationTargetException(e);
								} catch (final InvalidFileName e) {
									throw new InvocationTargetException(e);
								} catch (final ApplicationInstallationError e) {
									throw new InvocationTargetException(e);
								} catch (final ApplicationAlreadyInstalled e) {
									throw new InvocationTargetException(e);
								}
							}
							monitor.worked(1);
						}
					} finally {
						monitor.done();
					}
				}
			});
		} catch (final InvocationTargetException e) {
			final Throwable cause = e.getCause();
			String msg = "";
			if (cause instanceof InvalidFileName) {
				msg = ((InvalidFileName) cause).msg;
			} else if (cause instanceof ApplicationInstallationError) {
				msg = ((ApplicationInstallationError) cause).msg;
			}
			final Status status = new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, "Failed to install application." + msg, cause);
			StatusManager.getManager().handle(status, StatusManager.SHOW | StatusManager.LOG);
			return false;
		} catch (final InterruptedException e) {
			// PASS
		}

		// TODO Auto-generated method stub
		return true;
	}
}
