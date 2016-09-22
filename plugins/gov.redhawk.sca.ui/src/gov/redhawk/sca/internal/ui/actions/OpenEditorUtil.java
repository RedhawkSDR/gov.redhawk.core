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
package gov.redhawk.sca.internal.ui.actions;

import java.util.concurrent.Callable;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.progress.WorkbenchJob;
import org.eclipse.ui.statushandlers.StatusManager;

import gov.redhawk.model.sca.IRefreshable;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.sca.ui.IScaEditorDescriptor;
import gov.redhawk.sca.ui.ScaUI;
import gov.redhawk.sca.ui.ScaUiPlugin;
import mil.jpeojtrs.sca.util.CorbaUtils;

/* package */ class OpenEditorUtil {

	private OpenEditorUtil() {
	}

	/**
	 * Opens the editor for the provided descriptor. If the descriptor's object is an {@link IRefreshable}, it will be
	 * refreshed first via a job and then opened. Any jobs scheduled belong to the family
	 * {@link ScaUI#FAMILY_OPEN_EDITOR}, which allows tracking job progress.
	 * @param page
	 * @param editorDescriptor
	 */
	protected static void openEditor(final IWorkbenchPage page, final IScaEditorDescriptor editorDescriptor) {
		if (editorDescriptor == null || editorDescriptor.getEditorInput() == null || editorDescriptor.getEditorDescriptor().getId() == null) {
			return;
		}
		final Object obj = editorDescriptor.getSelectedObject();
		if (obj instanceof IRefreshable) {
			final IRefreshable refreshable = (IRefreshable) obj;

			// The first job will perform a full refresh of the item
			final Job refreshJob = new Job("Refreshing state...") {

				@Override
				public boolean belongsTo(Object family) {
					if (family == ScaUI.FAMILY_OPEN_EDITOR) {
						return true;
					}
					return super.belongsTo(family);
				}

				@Override
				protected IStatus run(final IProgressMonitor monitor) {
					try {
						CorbaUtils.invoke(new Callable<Object>() {

							public Object call() throws Exception {
								refreshable.refresh(monitor, RefreshDepth.FULL);
								return null;
							}

						}, monitor);
						return Status.OK_STATUS;
					} catch (CoreException e) {
						return new Status(e.getStatus().getSeverity(), ScaUiPlugin.PLUGIN_ID, e.getLocalizedMessage(), e);
					} catch (InterruptedException e) {
						return new Status(IStatus.CANCEL, ScaUiPlugin.PLUGIN_ID, "Interrupted while refreshing prior to opening an editor", e);
					}
				}
			};
			refreshJob.setUser(true);
			refreshJob.setPriority(Job.SHORT);

			// The second job will actually open the editor
			final WorkbenchJob openJob = new WorkbenchJob(page.getWorkbenchWindow().getShell().getDisplay(), "Opening editor") {

				@Override
				public boolean belongsTo(Object family) {
					if (family == ScaUI.FAMILY_OPEN_EDITOR) {
						return true;
					}
					return super.belongsTo(family);
				}

				@Override
				public IStatus runInUIThread(IProgressMonitor monitor) {
					open(page, editorDescriptor);
					return Status.OK_STATUS;
				}
			};
			openJob.setUser(true);
			openJob.setPriority(Job.SHORT);

			// Successful completion of refresh job -> triggers open job
			refreshJob.addJobChangeListener(new JobChangeAdapter() {
				@Override
				public void done(IJobChangeEvent event) {
					if (event.getResult().isOK()) {
						openJob.schedule();
					}
				}
			});

			refreshJob.schedule();
		} else {
			open(page, editorDescriptor);
		}
	}

	private static void open(IWorkbenchPage page, IScaEditorDescriptor editorDescriptor) {
		if (editorDescriptor == null || editorDescriptor.getEditorInput() == null || editorDescriptor.getEditorDescriptor().getId() == null) {
			return;
		}
		try {
			page.openEditor(editorDescriptor.getEditorInput(), editorDescriptor.getEditorDescriptor().getId(), true,
				IWorkbenchPage.MATCH_ID | IWorkbenchPage.MATCH_INPUT);
		} catch (final PartInitException e) {
			StatusManager.getManager().handle(e, ScaUiPlugin.PLUGIN_ID);
		}
	}
}
