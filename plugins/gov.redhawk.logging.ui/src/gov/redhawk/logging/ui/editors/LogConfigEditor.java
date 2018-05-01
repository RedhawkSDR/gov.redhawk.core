/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.logging.ui.editors;

import java.net.URI;
import java.util.concurrent.ExecutionException;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchListener;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.TextEditor;
import org.osgi.framework.FrameworkUtil;

import CF.LogConfigurationOperations;
import gov.redhawk.logging.ui.LoggingUiPlugin;
import gov.redhawk.model.sca.ScaAbstractComponent;
import gov.redhawk.model.sca.ScaWaveform;
import mil.jpeojtrs.sca.util.CorbaUtils2;

public class LogConfigEditor extends TextEditor {

	public static final String ID = "gov.redhawk.logging.ui.logconfig.editor";

	private LogConfigurationOperations resource;
	private IWorkbenchListener workBenchListener;

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		super.init(site, input);
		addWorkbenchListener();
	}

	private void addWorkbenchListener() {
		this.workBenchListener = new IWorkbenchListener() {

			@Override
			public boolean preShutdown(IWorkbench workbench, boolean forced) {
				workbench.getActiveWorkbenchWindow().getActivePage().closeEditor(LogConfigEditor.this, false);
				return true;
			}

			@Override
			public void postShutdown(IWorkbench workbench) {
			}
		};

		PlatformUI.getWorkbench().addWorkbenchListener(workBenchListener);
	}

	@Override
	protected void setPartName(String partName) {
		super.setPartName("Edit Log Config");
	}

	@Override
	public String getTitleToolTip() {
		if (resource instanceof ScaAbstractComponent< ? >) {
			return ((ScaAbstractComponent< ? >) resource).getIdentifier();
		} else if (resource instanceof ScaWaveform) {
			return ((ScaWaveform) resource).getName();
		}
		return super.getTitleToolTip();
	}

	@Override
	public void doSave(IProgressMonitor progressMonitor) {
		super.doSave(progressMonitor);
		updateLogConfig();
	}

	private void updateLogConfig() {
		final String newLogConfig = getEditorText();
		final Job saveLogConfigJob = Job.create("Saving log configuration file...", monitor -> {
			try {
				return CorbaUtils2.invoke(() -> {
					resource.setLogConfig(newLogConfig);
					return Status.OK_STATUS;
				}, monitor);
			} catch (ExecutionException e) {
				return new Status(IStatus.ERROR, LoggingUiPlugin.PLUGIN_ID, "Unable to set logging configuration on resource", e.getCause());
			}
		});
		saveLogConfigJob.setUser(true);
		saveLogConfigJob.schedule();
	}

	private String getEditorText() {
		return this.getDocumentProvider().getDocument(this.getEditorInput()).get();
	}

	public void setResource(LogConfigurationOperations resource) {
		this.resource = resource;
		setTitleToolTip(getTitleToolTip());
	}

	@Override
	public void dispose() {
		super.dispose();
		IEditorInput input = getEditorInput();
		if (input instanceof IURIEditorInput) {
			URI uri = ((IURIEditorInput) input).getURI();
			try {
				EFS.getStore(uri).delete(EFS.NONE, new NullProgressMonitor());
			} catch (CoreException e) {
				ILog log = Platform.getLog(FrameworkUtil.getBundle(getClass()));
				log.log(new Status(IStatus.WARNING, LoggingUiPlugin.PLUGIN_ID, "Unable to delete temporary file: " + uri.toString(), e));
			}
		}
		PlatformUI.getWorkbench().removeWorkbenchListener(this.workBenchListener);
	}

}
