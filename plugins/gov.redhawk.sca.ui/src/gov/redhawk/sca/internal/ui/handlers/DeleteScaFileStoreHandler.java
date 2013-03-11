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
import gov.redhawk.model.sca.ScaFileStore;
import gov.redhawk.sca.ui.ScaUiPlugin;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * 
 */
public class DeleteScaFileStoreHandler extends AbstractHandler implements IHandler {

	private static class DeleteJob extends Job {

		private final List<ScaFileStore> stores;

		public DeleteJob(final List<ScaFileStore> stores) {
			super("Deleting...");
			setUser(true);
			setPriority(Job.LONG);
			this.stores = stores;
		}

		@Override
		protected IStatus run(final IProgressMonitor monitor) {
			final SubMonitor subMonitor = SubMonitor.convert(monitor, "Deleting files...", this.stores.size());
			final MultiStatus retVal = new MultiStatus(ScaUiPlugin.PLUGIN_ID, IStatus.OK, "Problems deleting files", null);
			for (final ScaFileStore store : this.stores) {
				try {
					final SubMonitor storeMonitor = subMonitor.newChild(1);
					storeMonitor.beginTask("Deleting " + store.getName(), 2);
					store.getFileStore().delete(EFS.NONE, storeMonitor.newChild(1));
					((IRefreshable) store.eContainer()).refresh(storeMonitor.newChild(1), RefreshDepth.CHILDREN);
				} catch (final CoreException e) {
					retVal.add(e.getStatus());
				} catch (final InterruptedException e) {
					// PASS
				}
			}
			return retVal;
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getActiveMenuSelection(event);
		if (selection == null) {
			selection = HandlerUtil.getCurrentSelection(event);
		}
		if (selection instanceof IStructuredSelection) {
			final IStructuredSelection ss = (IStructuredSelection) selection;
			final List<ScaFileStore> files = new ArrayList<ScaFileStore>();
			for (final Object obj : ss.toArray()) {
				if (obj instanceof ScaFileStore) {
					final ScaFileStore store = (ScaFileStore) obj;
					files.add(store);
				}
			}
			final DeleteJob job = new DeleteJob(files);
			job.schedule();
		}
		return null;
	}

}
