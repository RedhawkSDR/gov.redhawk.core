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
package gov.redhawk.ui.views.allocmgr.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.progress.UIJob;

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.ui.views.allocmgr.AllocMgrFactory;
import gov.redhawk.ui.views.allocmgr.AllocMgrPlugin;
import gov.redhawk.ui.views.allocmgr.AllocMgrView;
import gov.redhawk.ui.views.allocmgr.ScaAllocationManager;
import gov.redhawk.ui.views.allocmgr.jobs.FetchAllocationManagerJob;

/**
 * Handler to open the allocation manager view for the selected domain ({@link ScaDomainManager}).
 */
public class ShowAllocMgrHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// Get shell, active page
		Shell shell = HandlerUtil.getActiveShellChecked(event);
		IWorkbenchPage page = HandlerUtil.getActiveWorkbenchWindowChecked(event).getActivePage();

		// Get selected domain manager
		ISelection selection = HandlerUtil.getActiveMenuSelectionChecked(event);
		Object element = ((IStructuredSelection) selection).getFirstElement();
		if (!(element instanceof ScaDomainManager)) {
			return null;
		}
		ScaDomainManager domMgr = (ScaDomainManager) element;
		if (!domMgr.isConnected()) {
			MessageDialog.openError(shell, "Not connected", "Connect to the domain first");
			return null;
		}

		// Use the domain manager's display name as the secondary ID for the view
		String secondaryId = domMgr.getLabel();

		// If the view is already open, just show it
		IViewPart existingView = page.findView(AllocMgrView.ID + ":" + secondaryId);
		if (existingView != null) {
			page.activate(existingView);
			return null;
		}

		// Job to fetch allocation manager
		ScaAllocationManager allocMgr = AllocMgrFactory.eINSTANCE.createScaAllocationManager();
		Job fetchJob = new FetchAllocationManagerJob(domMgr, allocMgr);
		fetchJob.setUser(true);

		// Open job
		Job openJob = new UIJob(shell.getDisplay(), "Open allocation manager view") {
			@Override
			public IStatus runInUIThread(IProgressMonitor monitor) {
				AllocMgrView view;
				try {
					view = (AllocMgrView) page.showView(AllocMgrView.ID, secondaryId, IWorkbenchPage.VIEW_ACTIVATE);
				} catch (PartInitException e) {
					IStatus status = new Status(IStatus.ERROR, AllocMgrPlugin.ID, "Unable to show allocation manager view", e);
					return status;
				}
				view.setInput(domMgr, allocMgr);
				return Status.OK_STATUS;
			}
		};
		openJob.setUser(true);

		// Fetch job -> Open job
		fetchJob.addJobChangeListener(new JobChangeAdapter() {
			@Override
			public void done(IJobChangeEvent event) {
				if (event.getResult() != null && event.getResult().isOK()) {
					openJob.schedule();
				}
			}
		});
		fetchJob.schedule();

		return null;
	}

}
