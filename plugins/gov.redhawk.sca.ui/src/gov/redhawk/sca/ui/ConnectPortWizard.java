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
package gov.redhawk.sca.ui;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.progress.UIJob;
import org.eclipse.ui.statushandlers.StatusManager;

import CF.PortPackage.InvalidPort;
import gov.redhawk.model.sca.CorbaObjWrapper;
import gov.redhawk.model.sca.ScaUsesPort;
import mil.jpeojtrs.sca.util.CorbaUtils;

/**
 * @since 9.3
 */
public class ConnectPortWizard extends Wizard {
	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

	public static String generateDefaultConnectionID() {
		return System.getProperty("user.name", "user") + "_" + ConnectPortWizard.FORMAT.format(new Date());
	}

	private ConnectWizardPage page;

	public ConnectPortWizard() {
		this(new HashMap<>());
	}

	/**
	 * @since 10.2
	 */
	public ConnectPortWizard(Map<String, Boolean> connectionIds) {
		setWindowTitle("Connect");
		setNeedsProgressMonitor(true);
		page = new ConnectWizardPage(connectionIds);
	}

	@Override
	public void addPages() {
		addPage(page);
	}

	@Override
	public final boolean performFinish() {
		try {
			getContainer().run(true, true, new IRunnableWithProgress() {

				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					performFinish(monitor);
				}
			});
		} catch (InvocationTargetException e) {
			StatusManager.getManager().handle(new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, "Failed to make connection.", e.getCause()));
			return false;
		} catch (InterruptedException e) {
			return true;
		}

		return true;
	}

	public String getConnectionID() {
		return page.getConnectionID();
	}

	public void setConnectionID(String connectionID) {
		page.setConnectionID(connectionID);
	}

	public ScaUsesPort getSource() {
		return page.getSource();
	}

	public void setSource(ScaUsesPort source) {
		page.setSource(source);
	}

	public CorbaObjWrapper< ? > getTarget() {
		return page.getTarget();
	}

	public void setTarget(CorbaObjWrapper< ? > target) {
		page.setTarget(target);
	}

	public void setConnectionIDReadOnly(boolean connectionIDReadOnly) {
		page.setConnectionIDReadOnly(!connectionIDReadOnly);
	}

	public boolean isConnectionIDReadOnly() {
		return !page.isConnectionIDReadOnly();
	}

	/**
	 * @param sourceInput
	 * @see gov.redhawk.sca.ui.ConnectWizardPage#setSourceInput(java.lang.Object)
	 */
	public void setSourceInput(Object sourceInput) {
		page.setSourceInput(sourceInput);
	}

	/**
	 * @return
	 * @see gov.redhawk.sca.ui.ConnectWizardPage#getSourceInput()
	 */
	public Object getSourceInput() {
		return page.getSourceInput();
	}

	/**
	 * @param targetInput
	 * @see gov.redhawk.sca.ui.ConnectWizardPage#setTargetInput(java.lang.Object)
	 */
	public void setTargetInput(Object targetInput) {
		page.setTargetInput(targetInput);
	}

	/**
	 * @return
	 * @see gov.redhawk.sca.ui.ConnectWizardPage#getTargetInput()
	 */
	public Object getTargetInput() {
		return page.getTargetInput();
	}
	
	public boolean isShowAllInputs() {
		return page.isShowAllInputs();
	}

	public void setShowAllInputs(boolean showAllInputs) {
		page.setShowAllInputs(showAllInputs);
	}

	public boolean isShowAllOutputs() {
		return page.isShowAllOutputs();
	}

	public void setShowAllOutputs(boolean showAllOutputs) {
		page.setShowAllOutputs(showAllOutputs);
	}

	protected void performFinish(IProgressMonitor monitor) throws InterruptedException, InvocationTargetException {
		monitor.beginTask("Connecting...", IProgressMonitor.UNKNOWN);
		try {
			CorbaUtils.invoke(new Callable<Object>() {

				@Override
				public Object call() throws Exception {
					page.getSource().connectPort(page.getTarget().getCorbaObj(), page.getConnectionID());
					return null;
				}
			}, monitor);
		} catch (final CoreException e) {
			UIJob uiJob = new UIJob("Port connection error") {
				@Override
				public IStatus runInUIThread(IProgressMonitor monitor) {
					Throwable cause = e.getCause();
					String errorMsg;
					if (cause instanceof InvalidPort) {
						InvalidPort invalidPort = ((InvalidPort) cause);
						errorMsg = "The source port refused to connect to the target.\n";
						errorMsg += String.format("Received an InvalidPort exception (code %d, message '%s')", invalidPort.errorCode, invalidPort.msg); 
					} else {
						errorMsg = "Error completing connection: " + cause.getMessage();
					}
					MessageDialog errorDialog = new MessageDialog(getShell(), "Error", null, errorMsg, MessageDialog.ERROR, new String[] { "OK" }, 0);
					errorDialog.open();
					return Status.OK_STATUS;
				}
			};
			uiJob.schedule();

			throw new InvocationTargetException(e);

		}
	}

}
