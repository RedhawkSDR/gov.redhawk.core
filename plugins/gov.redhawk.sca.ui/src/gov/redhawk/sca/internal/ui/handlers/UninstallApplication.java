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

import gov.redhawk.model.sca.ScaWaveformFactory;
import gov.redhawk.sca.ui.ScaUiPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.omg.CORBA.SystemException;

import CF.DomainManagerPackage.ApplicationUninstallationError;
import CF.DomainManagerPackage.InvalidIdentifier;

// TODO: Auto-generated Javadoc
/**
 * The Class InstallApplication.
 */
public class UninstallApplication extends AbstractHandler {

	/**
	 * {@inheritDoc}
	 */

	public Object execute(final ExecutionEvent event) throws ExecutionException {
		ISelection sel = HandlerUtil.getActiveMenuSelection(event);
		if (sel == null) {
			sel = HandlerUtil.getCurrentSelection(event);
		}
		if (sel instanceof IStructuredSelection) {
			final IStructuredSelection ss = (IStructuredSelection) sel;
			final List<ScaWaveformFactory> waveforms = new ArrayList<ScaWaveformFactory>();
			for (final Object element : ss.toList()) {
				if (element instanceof ScaWaveformFactory) {
					waveforms.add((ScaWaveformFactory) element);
				}
			}
			final IProgressMonitor progressGroup = Job.getJobManager().createProgressGroup();
			final int size = waveforms.size();
			progressGroup.beginTask("Uninstalling Waveform factories", size);
			final JobChangeAdapter adapter = new JobChangeAdapter() {
				private final AtomicInteger jobList = new AtomicInteger(size);

				@Override
				public void done(final IJobChangeEvent event) {
					final int num = this.jobList.decrementAndGet();
					if (num <= 0) {
						progressGroup.done();
					}
				}
			};

			for (final ScaWaveformFactory swf : waveforms) {
				final Job job = new Job("Uninstalling Application Job") {

					@Override
					protected IStatus run(final IProgressMonitor monitor) {
						try {
							monitor.beginTask("Uninstalling application: " + swf.getName(), IProgressMonitor.UNKNOWN);
							if (swf.getDomMgr() != null) {
								swf.getDomMgr().uninstallScaWaveformFactory(swf);
							}
						} catch (final InvalidIdentifier e) {
							return new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, "Failed to uninstall application.", e);
						} catch (final ApplicationUninstallationError e) {
							return new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, "Failed to uninstall application.", e);
						} catch (final SystemException e) {
							return new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, "Failed to uninstall application.", e);
						} finally {
							monitor.done();
						}
						return Status.OK_STATUS;
					}

				};
				job.addJobChangeListener(adapter);
				job.setProgressGroup(progressGroup, 1);
				job.schedule();
			}
		}
		return null;
	}

}
