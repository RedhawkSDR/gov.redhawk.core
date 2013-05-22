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
package gov.redhawk.ui.views.namebrowser.internal.command;

import gov.redhawk.ui.views.namebrowser.view.BindingNode;
import gov.redhawk.ui.views.namebrowser.view.NameBrowserView;

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
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

public class RemoveContext extends AbstractHandler implements IHandler {

	public Object execute(final ExecutionEvent event) throws ExecutionException {
		final ISelection selection = HandlerUtil.getCurrentSelection(event);
		final IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		if (page != null) {
			final NameBrowserView view;
			view = (NameBrowserView) page.findView(NameBrowserView.ID);
			if (view != null) {
				if (selection instanceof IStructuredSelection) {
					final IStructuredSelection sel = (IStructuredSelection) selection;
					final List<BindingNode> unbindList = new ArrayList<BindingNode>();
					for (final Object selected : sel.toList()) {
						if (selected instanceof BindingNode) {
							unbindList.add((BindingNode) selected);
						}
					}
					final Job job = new Job("Remove context") {

						@Override
						protected IStatus run(final IProgressMonitor monitor) {
							view.removeContext(unbindList);
							return Status.OK_STATUS;
						}

					};
					job.schedule();

				}

			}
		}

		return null;
	}

}
