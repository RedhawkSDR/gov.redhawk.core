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
package gov.redhawk.sca.internal.ui.actions;

import gov.redhawk.model.sca.ScaFileStore;
import gov.redhawk.sca.ui.ScaUiPlugin;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceRuleFactory;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.Job;

public class DownloadJob extends Job {

	private final ScaFileStore[] stores;
	private final IContainer target;

	public DownloadJob(final ScaFileStore[] stores, final IContainer target) {
		super("Downloading REDHAWK Files");
		this.stores = stores;
		this.target = target;
		setUser(true);
	}

	@Override
	protected IStatus run(final IProgressMonitor monitor) {
		final MultiStatus status = new MultiStatus(ScaUiPlugin.PLUGIN_ID, IStatus.OK, "Problems downloading REDHAWK files", null);
		final SubMonitor subMonitor = SubMonitor.convert(monitor, "Downloading files", this.stores.length);
		try {
			for (final ScaFileStore store : this.stores) {
				final IStatus childStatus = DownloadJob.downloadStore(store, this.target, subMonitor.newChild(1));
				if (!childStatus.isOK()) {
					status.add(childStatus);
				}
			}
		} finally {
			subMonitor.done();
		}
		return status;
	}

	private static IStatus downloadStore(final ScaFileStore store, final IContainer target, final IProgressMonitor monitor) {
		if (store.isDirectory()) {
			return DownloadJob.downloadDirectory(store, target, monitor);
		} else {
			return DownloadJob.downloadFile(store, target, monitor);
		}
	}

	private static IStatus downloadFile(final ScaFileStore store, final IContainer target, final IProgressMonitor monitor) {
		final IFile targetFile = target.getFile(new Path(store.getName()));
		final SubMonitor subMonitor = SubMonitor.convert(monitor, "Downloading file " + store.getName(), 100);
		try {
			ISchedulingRule rule;
			IWorkspaceRunnable runnable;
			try (InputStream inputStream = store.getFileStore().openInputStream(EFS.NONE, subMonitor.newChild(1))) {
				final IResourceRuleFactory ruleFactory = ResourcesPlugin.getWorkspace().getRuleFactory();
				if (!targetFile.exists()) {
					rule = ruleFactory.createRule(targetFile);
					runnable = monitor2 -> {
						targetFile.create(inputStream, true, monitor2);
					};
				} else {
					rule = ruleFactory.modifyRule(targetFile);
					runnable = monitor2 -> {
						targetFile.setContents(inputStream, 0, monitor);
					};
				}
				ResourcesPlugin.getWorkspace().run(runnable, rule, 0, subMonitor.newChild(99));
			}
		} catch (CoreException e) {
			return new Status(e.getStatus().getSeverity(), ScaUiPlugin.PLUGIN_ID, e.getLocalizedMessage(), e);
		} catch (IOException e) {
			return new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, "Unable to open file " + store.getName(), e);
		}
		return Status.OK_STATUS;
	}

	private static IStatus downloadDirectory(final ScaFileStore store, final IContainer target, final IProgressMonitor monitor) {
		final IFolder targetFolder = target.getFolder(new Path(store.getName()));
		final SubMonitor subMonitor = SubMonitor.convert(monitor, "Downloading folder " + store.getName(), 100);
		try {
			if (!targetFolder.exists()) {
				ResourcesPlugin.getWorkspace().run(monitor2 -> {
					targetFolder.create(false, true, monitor2);
				}, targetFolder.getParent(), IResource.NONE, subMonitor.newChild(5));
			} else {
				subMonitor.setWorkRemaining(95);
			}

			final SubMonitor childMonitor = subMonitor.newChild(95);
			childMonitor.beginTask("Downloading folder contents", store.fetchChildren(childMonitor.newChild(10)).size());
			final MultiStatus childStoreStatus = new MultiStatus(ScaUiPlugin.PLUGIN_ID, IStatus.OK, "Problems downloading folder contents " + store.getName(),
				null);
			for (final ScaFileStore child : store.getChildren()) {
				final IStatus childStatus = DownloadJob.downloadStore(child, targetFolder, childMonitor.newChild(1));
				if (!childStatus.isOK()) {
					childStoreStatus.add(childStatus);
				}
			}
			childMonitor.done();

			return childStoreStatus;
		} catch (final CoreException e) {
			return new Status(e.getStatus().getSeverity(), ScaUiPlugin.PLUGIN_ID, e.getLocalizedMessage(), e);
		}
	}

}
