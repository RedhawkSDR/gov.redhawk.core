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

import java.io.File;
import java.util.concurrent.Callable;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchListener;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.TextEditor;

import CF.LogConfigurationOperations;
import gov.redhawk.logging.ui.LoggingUiPlugin;
import gov.redhawk.model.sca.ScaAbstractComponent;
import gov.redhawk.model.sca.ScaWaveform;
import mil.jpeojtrs.sca.util.CorbaUtils;

public class LogConfigEditor extends TextEditor {

	public static final String ID = "gov.redhawk.logging.ui.logconfig.editor";

	private LogConfigurationOperations resource;
	private IPath path;

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
		String toolTip = super.getTitleToolTip();

		if (resource instanceof ScaAbstractComponent< ? >) {
			toolTip = ((ScaAbstractComponent< ? >) resource).getIdentifier();
		} else if (resource instanceof ScaWaveform) {
			toolTip = ((ScaWaveform) resource).getName();
		}
		return toolTip;
	}

	@Override
	public void doSave(IProgressMonitor progressMonitor) {
		super.doSave(progressMonitor);
		updateLogConfig();
	}

	private void updateLogConfig() {
		final String newLogConfig = getEditorText();
		final Job saveLogConfigJob = new Job("Saving Log Configuration File...") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask(getName(), IProgressMonitor.UNKNOWN);
				try {
					CorbaUtils.invoke(new Callable<Object>() {
						public Object call() throws Exception {
							resource.setLogConfig(newLogConfig);
							return Status.OK_STATUS;
						};
					}, monitor);
				} catch (CoreException e) {
					return new Status(e.getStatus().getSeverity(), LoggingUiPlugin.PLUGIN_ID, e.getLocalizedMessage(), e);
				} catch (InterruptedException e) {
					return Status.CANCEL_STATUS;
				} finally {
					monitor.done();
				}

				return Status.OK_STATUS;
			}
		};

		saveLogConfigJob.setUser(true);
		saveLogConfigJob.schedule();
	}

	private String getEditorText() {
		return this.getDocumentProvider().getDocument(this.getEditorInput()).get();
	}

	public LogConfigurationOperations getResource() {
		return resource;
	}

	public void setResource(LogConfigurationOperations resource) {
		this.resource = resource;
		setTitleToolTip(getTitleToolTip());
	}

	public void setFilePath(IPath path) {
		this.path = path;
	}

	@Override
	public void dispose() {
		super.dispose();
		File file = path.toFile();
		if (file.exists()) {
			file.delete();
		}

		PlatformUI.getWorkbench().removeWorkbenchListener(this.workBenchListener);
	}

}
