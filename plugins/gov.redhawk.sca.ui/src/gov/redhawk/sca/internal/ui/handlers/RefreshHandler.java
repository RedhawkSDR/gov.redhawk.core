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

import gov.redhawk.model.sca.IRefreshable;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;
import gov.redhawk.sca.util.PluginUtil;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.progress.WorkbenchJob;

/**
 * 
 */
public class RefreshHandler extends AbstractHandler implements IHandler {

	/**
	 * {@inheritDoc}
	 */
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getActiveMenuSelection(event);
		if (selection == null) {
			selection = HandlerUtil.getCurrentSelection(event);
		}
		if (selection instanceof IStructuredSelection) {
			final IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			final List<IRefreshable> refreshList = new ArrayList<IRefreshable>();
			for (final Object obj : structuredSelection.toList()) {
				final IRefreshable dataObj = PluginUtil.adapt(IRefreshable.class, obj, true);
				if (dataObj != null) {
					refreshList.add(dataObj);
				}
			}
			final int size = refreshList.size();
			if (size > 0) {
				final IProgressMonitor refreshProgressGroup = Job.getJobManager().createProgressGroup();
				final IWorkbenchPart activePart = HandlerUtil.getActivePart(event);
				refreshProgressGroup.beginTask("Refreshing elements...", size);
				final ScaItemProviderAdapterFactory adapterFactory = new ScaItemProviderAdapterFactory();
				final Display display = HandlerUtil.getActiveShell(event).getDisplay();
				for (final IRefreshable dataObj : refreshList) {
					final IItemLabelProvider lp = (IItemLabelProvider) adapterFactory.adapt(dataObj, IItemLabelProvider.class);
					String label = "";
					if (lp != null) {
						label = " " + lp.getText(dataObj);
					}
					final Job job = new Job("Refreshing" + label) {

						@Override
						protected IStatus run(final IProgressMonitor monitor) {
							try {
								dataObj.refresh(monitor, RefreshDepth.CHILDREN);
								final WorkbenchJob uiJob = new WorkbenchJob(display, "Refresh viewer") {

									@Override
									public IStatus runInUIThread(final IProgressMonitor monitor) {
										if (activePart instanceof CommonNavigator) {
											final CommonNavigator navigator = (CommonNavigator) activePart;
											navigator.getCommonViewer().refresh(structuredSelection.toArray());
										}
										refreshProgressGroup.done();
										return Status.OK_STATUS;
									}
								};
								uiJob.setPriority(Job.INTERACTIVE);
								uiJob.setUser(false);
								uiJob.schedule();
							} catch (final InterruptedException e) {
								return Status.CANCEL_STATUS;
							}
							return Status.OK_STATUS;
						}

						@Override
						protected void canceling() {
							getThread().interrupt();
							super.canceling();
						}
					};
					job.setProgressGroup(refreshProgressGroup, 1);
					job.setUser(true);
					job.schedule();
				}
				adapterFactory.dispose();
			}
		}
		return null;
	}

}
