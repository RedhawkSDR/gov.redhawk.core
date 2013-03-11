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
package gov.redhawk.sca.internal.ui.actions;

import gov.redhawk.sca.ui.ScaUiPlugin;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.statushandlers.StatusManager;

/**
 * @deprecated Switch to using {@link OpenFileAction}
 */
@Deprecated
public class OpenAction extends Action implements IWorkbenchAction {

	/** The page. */
	private final IWorkbenchPage activePage;

	private IEditorInput editorInput;

	/** The editor descriptor. */
	private IEditorDescriptor editorDescriptor;

	public OpenAction(final IWorkbenchPage activePage) {
		this(activePage, null, null);
	}

	public OpenAction(final IWorkbenchPage activePage, final IEditorInput editorInput, final IEditorDescriptor editorDescriptor) {
		Assert.isNotNull(activePage);
		this.activePage = activePage;
		this.setAction(editorInput, editorDescriptor);
	}

	@Override
	public void run() {
		if (!isEnabled()) {
			return;
		}
		try {
			this.activePage.openEditor(this.editorInput, this.editorDescriptor.getId());
		} catch (final PartInitException e) {
			final IStatus status = new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, "Failed to open editor: " + this.editorDescriptor.getId(), e);
			StatusManager.getManager().handle(status, StatusManager.LOG | StatusManager.SHOW);
		}
	}

	public void setAction(final IEditorInput editorInput, final IEditorDescriptor editorDescriptor) {
		this.editorInput = editorInput;
		this.editorDescriptor = editorDescriptor;

		this.setEnabled(editorInput != null && editorDescriptor != null);

		if (editorDescriptor != null) {
			this.setText(this.editorDescriptor.getLabel());
			this.setImageDescriptor(this.editorDescriptor.getImageDescriptor());
		} else {
			this.setText(null);
			this.setImageDescriptor(null);
		}
	}

	public void dispose() {
		// TODO Auto-generated method stub

	}
}
