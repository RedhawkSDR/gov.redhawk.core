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
 *
 * Derrived from org.eclipse.ui.actions.OpenFileAction
 *******************************************************************************/
package gov.redhawk.sca.internal.ui.actions;

import gov.redhawk.model.sca.IRefreshable;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.sca.internal.ui.ScaContentTypeRegistry;
import gov.redhawk.sca.ui.IScaEditorDescriptor;
import gov.redhawk.sca.ui.ScaUiPlugin;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.eclipse.ui.progress.WorkbenchJob;
import org.eclipse.ui.statushandlers.StatusManager;

/**
 * Standard action for opening editors on SCA Model Types
 * <p>
 * This class may be instantiated; it is not intended to be subclassed.
 * </p>
 * @noextend This class is not intended to be subclassed by clients.
 */
public class OpenFileAction extends BaseSelectionListenerAction {

	/**
	 * The id of this action.
	 */
	public static final String ID = PlatformUI.PLUGIN_ID + ".OpenFileAction"; //$NON-NLS-1$

	/**
	 * The editor to open.
	 */
	private IScaEditorDescriptor editorDescriptor;

	private final IWorkbenchPage page;

	private Object selectObj;

	/**
	 * Creates a new action that will open editors on the then-selected file 
	 * resources. Equivalent to <code>OpenFileAction(page,null)</code>.
	 *
	 * @param page the workbench page in which to open the editor
	 */
	public OpenFileAction(final IWorkbenchPage page) {
		this(page, null);
	}

	/**
	 * Creates a new action that will open instances of the specified editor on 
	 * the then-selected SCA resources.
	 *
	 * @param page the workbench page in which to open the editor
	 * @param descriptor the editor descriptor, or <code>null</code> if unspecified
	 */
	public OpenFileAction(final IWorkbenchPage page, final IScaEditorDescriptor descriptor) {
		super("&Open");
		this.page = page;
		setText((descriptor == null) ? "&Open" : descriptor.getEditorDescriptor().getLabel());
		setToolTipText("Edit File");
		this.editorDescriptor = descriptor;
	}

	@Override
	public void run() {
		if (this.editorDescriptor == null || this.editorDescriptor.getEditorInput() == null || this.editorDescriptor.getEditorDescriptor().getId() == null) {
			return;
		}
		if (this.selectObj instanceof IRefreshable) {
			final IRefreshable refreshable = (IRefreshable) this.selectObj;
			final Job job = new Job("Opening...") {

				@Override
				protected IStatus run(final IProgressMonitor monitor) {
					try {
						refreshable.refresh(null, RefreshDepth.FULL);
						Display display = page.getWorkbenchWindow().getShell().getDisplay();
						if (display != null) {
							final WorkbenchJob openJob = new WorkbenchJob(display, "Open") {

								@Override
								public IStatus runInUIThread(final IProgressMonitor monitor) {
									open();
									return Status.OK_STATUS;
								}
							};
							openJob.schedule();
						}
					} catch (final InterruptedException e) {
						// PASS
					}
					return Status.OK_STATUS;
				}

			};
			job.setUser(true);
			job.schedule();
		} else {
			open();
		}

	}

	private void open() {
		try {
			this.page.openEditor(this.editorDescriptor.getEditorInput(), this.editorDescriptor.getEditorDescriptor().getId(), true, IWorkbenchPage.MATCH_ID
					| IWorkbenchPage.MATCH_INPUT);
		} catch (final PartInitException e) {
			StatusManager.getManager().handle(e, ScaUiPlugin.PLUGIN_ID);
		}
	}

	@Override
	protected boolean updateSelection(final IStructuredSelection selection) {
		this.selectObj = selection.getFirstElement();
		setImageDescriptor(null);
		this.editorDescriptor = ScaContentTypeRegistry.INSTANCE.getScaEditorDescriptor(this.selectObj);
		setText("&Open");
		setImageDescriptor(null);
		if (this.editorDescriptor != null) {
			setText(this.editorDescriptor.getEditorDescriptor().getLabel());
			setImageDescriptor(this.editorDescriptor.getEditorDescriptor().getImageDescriptor());
			return true;
		}
		return false;
	}

}
