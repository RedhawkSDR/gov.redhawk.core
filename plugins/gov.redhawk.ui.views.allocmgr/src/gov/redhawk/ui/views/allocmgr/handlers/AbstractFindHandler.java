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

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ISetSelectionTarget;
import org.eclipse.ui.progress.UIJob;

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.ui.views.allocmgr.AllocMgrPlugin;

public abstract class AbstractFindHandler extends AbstractHandler {

	private static final String EXPLORER_VIEW_ID = "gov.redhawk.ui.sca_explorer";

	/**
	 * @return A list of all connected domains
	 */
	protected List<ScaDomainManager> getConnectedDomains(ScaDomainManagerRegistry registry) {
		List<ScaDomainManager> domMgrs = ScaModelCommand.runExclusive(registry, () -> {
			return registry.getDomains().stream() //
					.filter(domMgr -> domMgr.isConnected()) //
					.collect(Collectors.toList());
		});
		return domMgrs;
	}

	/**
	 * Show the explorer view and set its selection.
	 * @param display
	 * @param activeWorkbenchWindow
	 * @param selection The object to select
	 */
	protected void setExplorerSelectionAsync(Display display, IWorkbenchPage activeWorkbenchWindow, Object selection) {
		Job job = new UIJob(display, "Show device in explorer view") {
			@Override
			public IStatus runInUIThread(IProgressMonitor monitor) {
				try {
					ISetSelectionTarget view = (ISetSelectionTarget) activeWorkbenchWindow.showView(EXPLORER_VIEW_ID, null, IWorkbenchPage.VIEW_ACTIVATE);
					view.selectReveal(new StructuredSelection(selection));
				} catch (PartInitException e) {
					IStatus status = new Status(IStatus.ERROR, AllocMgrPlugin.ID, "Unable to show explorer view", e);
					return status;
				}
				return Status.OK_STATUS;
			}
		};
		job.setUser(true);
		job.setPriority(Job.INTERACTIVE);
		job.schedule();
	}
}
