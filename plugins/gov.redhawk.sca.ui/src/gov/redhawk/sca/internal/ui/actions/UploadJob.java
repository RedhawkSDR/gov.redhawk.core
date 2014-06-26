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

import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaFileStore;
import gov.redhawk.sca.ui.ScaUiPlugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;

public class UploadJob extends Job {

	private static final IResource[] EMPTY_RESOURCES = new IResource[0];
	private static final ScaFileStore[] EMPTY_STORE = new ScaFileStore[0];
	private static final String[] EMPTY_FILES = new String[0];

	private final IResource[] resources;
	private final String[] files;
	private final ScaFileStore targetContainer;
	private final ScaFileStore[] sourceStores;

	public UploadJob(final ScaFileStore targetContainer, final IResource[] resources) {
		this(targetContainer, resources, UploadJob.EMPTY_FILES, UploadJob.EMPTY_STORE);
	}

	public UploadJob(final ScaFileStore targetContainer, final String[] files) {
		this(targetContainer, UploadJob.EMPTY_RESOURCES, files, UploadJob.EMPTY_STORE);
	}

	public UploadJob(final ScaFileStore targetContainer, final ScaFileStore[] sourceStores) {
		this(targetContainer, UploadJob.EMPTY_RESOURCES, UploadJob.EMPTY_FILES, UploadJob.EMPTY_STORE);
	}

	public UploadJob(final ScaFileStore targetContainer, final IResource[] resources, final String[] files, final ScaFileStore[] sourceStores) {
		super("Upload files...");
		this.resources = resources;
		this.files = files;
		this.targetContainer = targetContainer;
		this.sourceStores = sourceStores;
		setUser(true);
		setPriority(Job.LONG);
	}

	@Override
	public boolean shouldSchedule() {
		return super.shouldSchedule() && (this.resources.length > 0 || this.files.length > 0 || this.sourceStores.length > 0);
	}

	@Override
	protected IStatus run(final IProgressMonitor monitor) {
		final int size = this.resources.length + this.files.length + this.sourceStores.length + 1;
		final SubMonitor subMonitor = SubMonitor.convert(monitor, "Copying files...", size);
		final MultiStatus status = new MultiStatus(ScaUiPlugin.PLUGIN_ID, IStatus.OK, "Problems while copying file(s)", null);
		for (final IResource resource : this.resources) {
			status.add(UploadJob.copyIResource(resource, this.targetContainer.getFileStore(), subMonitor.newChild(1)));
		}
		for (final String filePath : this.files) {
			final File file = new File(filePath);
			if (file.exists()) {
				status.add(UploadJob.copy(file, this.targetContainer.getFileStore(), subMonitor.newChild(1)));
			}
		}
		for (final ScaFileStore store : this.sourceStores) {
			try {
				store.getFileStore().copy(this.targetContainer.getFileStore().getFileStore(new Path(store.getName())), EFS.NONE, subMonitor.newChild(1));
			} catch (final CoreException e) {
				status.add(new Status(e.getStatus().getSeverity(), ScaUiPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
			}
		}
		try {
			this.targetContainer.refresh(subMonitor.newChild(1), RefreshDepth.CHILDREN);
		} catch (final InterruptedException e) {
			// PASS
		}
		return status;
	}

	private static IStatus copy(final File file, final IFileStore target, final IProgressMonitor monitor) {
		if (file.isDirectory()) {
			return UploadJob.copyDirectory(file, target, monitor);
		} else {
			return UploadJob.copyFile(file, target, monitor);
		}
	}

	private static IStatus copyFile(final File file, final IFileStore target, final IProgressMonitor monitor) {
		final IFileStore childStore = target.getFileStore(new Path(file.getName()));
		OutputStream outputStream = null;
		InputStream inputStream = null;
		try {
			outputStream = childStore.openOutputStream(EFS.NONE, monitor);
			inputStream = new FileInputStream(file);
			IOUtils.copy(inputStream, outputStream);
			return Status.OK_STATUS;
		} catch (final CoreException e) {
			return new Status(e.getStatus().getSeverity(), ScaUiPlugin.PLUGIN_ID, e.getLocalizedMessage(), e);
		} catch (final IOException e) {
			return new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, "Failed to copy " + file + " to " + target, e);
		} finally {
			IOUtils.closeQuietly(inputStream);
			IOUtils.closeQuietly(outputStream);
		}
	}

	private static IStatus copyDirectory(final File directory, final IFileStore target, final IProgressMonitor monitor) {
		final IFileStore childStore = target.getFileStore(new Path(directory.getName()));
		final SubMonitor subMonitor = SubMonitor.convert(monitor, "Copying folder " + directory.getName(), 3);

		try {
			final IFileInfo info = childStore.fetchInfo(EFS.NONE, subMonitor.newChild(1));
			if (!info.exists() || info.isDirectory()) {
				if (!info.exists()) {
					childStore.mkdir(EFS.NONE, subMonitor.newChild(1));
				} else {
					subMonitor.setWorkRemaining(1);
				}
				final File[] members = directory.listFiles();
				final MultiStatus status = new MultiStatus(ScaUiPlugin.PLUGIN_ID, IStatus.OK, "Problems while copying folder " + directory.getName(), null);
				final SubMonitor memberMonitor = subMonitor.newChild(1);
				memberMonitor.beginTask("Copying contents of folder " + directory.getName(), members.length);
				for (final File resource : members) {
					status.add(UploadJob.copy(resource, childStore, memberMonitor.newChild(1)));
				}
				return status;
			} else {
				return new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, "File of the same name exists: " + directory.getName(), null);
			}
		} catch (final CoreException e) {
			return new Status(e.getStatus().getSeverity(), ScaUiPlugin.PLUGIN_ID, e.getLocalizedMessage(), e);
		}
	}

	private static IStatus copyIResource(final IResource resource, final IFileStore target, final IProgressMonitor monitor) {
		if (resource instanceof IFile) {
			return UploadJob.copyIFileTo((IFile) resource, target, monitor);
		} else if (resource instanceof IContainer) {
			return UploadJob.copyIContainerTo((IContainer) resource, target, monitor);
		} else {
			return new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, "Unknown resource type " + resource.getName(), null);
		}
	}

	private static IStatus copyIContainerTo(final IContainer container, final IFileStore target, final IProgressMonitor monitor) {
		final IFileStore childStore = target.getFileStore(new Path(container.getName()));
		final SubMonitor subMonitor = SubMonitor.convert(monitor, "Copying folder " + container.getName(), 3);

		try {
			final IFileInfo info = childStore.fetchInfo(EFS.NONE, subMonitor.newChild(1));
			if (!info.exists() || info.isDirectory()) {
				if (!info.exists()) {
					childStore.mkdir(EFS.NONE, subMonitor.newChild(1));
				} else {
					subMonitor.setWorkRemaining(1);
				}
				final IResource[] members = container.members();
				final MultiStatus status = new MultiStatus(ScaUiPlugin.PLUGIN_ID, IStatus.OK, "Problems while copying folder " + container.getName(), null);
				final SubMonitor memberMonitor = subMonitor.newChild(1);
				memberMonitor.beginTask("Copying contents of folder " + container.getName(), members.length);
				for (final IResource resource : members) {
					status.add(UploadJob.copyIResource(resource, childStore, memberMonitor.newChild(1)));
				}
				return status;
			} else {
				return new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, "File of the same name exists: " + container.getName(), null);
			}
		} catch (final CoreException e) {
			return new Status(e.getStatus().getSeverity(), ScaUiPlugin.PLUGIN_ID, e.getLocalizedMessage(), e);
		}
	}

	private static IStatus copyIFileTo(final IFile file, final IFileStore target, final IProgressMonitor monitor) {
		final IFileStore childStore = target.getFileStore(new Path(file.getName()));
		OutputStream outputStream = null;
		InputStream inputStream = null;
		try {
			outputStream = childStore.openOutputStream(EFS.NONE, monitor);
			inputStream = file.getContents();
			IOUtils.copy(inputStream, outputStream);
			return Status.OK_STATUS;
		} catch (final CoreException e) {
			return new Status(e.getStatus().getSeverity(), ScaUiPlugin.PLUGIN_ID, e.getLocalizedMessage(), e);
		} catch (final IOException e) {
			return new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, "Failed to copy " + file + " to " + target, e);
		} finally {
			IOUtils.closeQuietly(inputStream);
			IOUtils.closeQuietly(outputStream);
		}
	}

};
