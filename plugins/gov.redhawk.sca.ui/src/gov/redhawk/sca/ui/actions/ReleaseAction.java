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
package gov.redhawk.sca.ui.actions;

import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.sca.ui.ScaUiPlugin;
import gov.redhawk.sca.util.PluginUtil;

import java.util.concurrent.Callable;

import mil.jpeojtrs.sca.util.CorbaUtils;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import CF.LifeCycleOperations;
import CF.LifeCyclePackage.ReleaseError;

/**
 * @since 3.0
 * 
 */
public class ReleaseAction extends Action {
	private Object context;

	private IWorkbenchWindow window;

	public ReleaseAction() {
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(ScaUiPlugin.PLUGIN_ID, "icons/clcl16/release.gif"));
		setText("Release");
		setToolTipText("Release");
		this.setEnabled(false);
	}

	public void setContext(final Object obj) {
		this.context = obj;
		setEnabled(PluginUtil.adapt(LifeCycleOperations.class, obj) != null);
	}

	public void setWindow(final IWorkbenchWindow window) {
		this.window = window;
	}

	@Override
	public void run() {
		if (!this.isEnabled()) {
			return;
		}
		release(this.context, (this.window == null) ? PlatformUI.getWorkbench().getActiveWorkbenchWindow() : this.window); // SUPPRESS CHECKSTYLE AvoidInline
	}

	private IStatus release(final Object obj, final IWorkbenchWindow workbenchWindow) {
		final LifeCycleOperations op = PluginUtil.adapt(LifeCycleOperations.class, obj);
		if (op != null) {
			final Job job = new Job("Releasing") {

				@Override
				protected IStatus run(final IProgressMonitor monitor) {
					String releaseName = op.toString();
					final ScaWaveform scaWaveform = PluginUtil.adapt(ScaWaveform.class, obj);
					if (scaWaveform != null) {
						releaseName = scaWaveform.getName();
					}
					final String finalReleaseName = releaseName;
					monitor.beginTask("Releasing: " + finalReleaseName, IProgressMonitor.UNKNOWN);
					try {
						CorbaUtils.invoke(new Callable<Object>() {

							@Override
							public Object call() throws Exception {
								try {
									op.releaseObject();
								} catch (final ReleaseError e) {
									throw new CoreException(new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, "Failed to release: " + finalReleaseName, e));
								}
								return null;
							}

						}, monitor);
						return Status.OK_STATUS;
					} catch (CoreException e) {
						return new Status(e.getStatus().getSeverity(), ScaUiPlugin.PLUGIN_ID, "Failed to release: " + finalReleaseName, e);
					} catch (InterruptedException e) {
						return Status.CANCEL_STATUS;
					} finally {
						monitor.done();
					}

				}

			};
			job.setUser(true);
			job.schedule();
		}
		return Status.OK_STATUS;
	}
}
