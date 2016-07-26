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
package gov.redhawk.sca.ui.wizards;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.progress.UIJob;

import gov.redhawk.model.sca.DomainConnectionException;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaDomainManagerFileSystem;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.WaveformsContainer;
import gov.redhawk.sca.ScaPlugin;
import gov.redhawk.sca.preferences.ScaPreferenceConstants;
import gov.redhawk.sca.ui.ScaUiPlugin;
import mil.jpeojtrs.sca.sad.SadPackage;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import mil.jpeojtrs.sca.util.ScaResourceFactoryUtil;

/**
 * @since 9.0
 * 
 */
public class InstallApplicationContentProvider implements ITreeContentProvider {
	private List<SoftwareAssembly> children;

	private class RefreshJob extends UIJob {

		public RefreshJob(final Display jobDisplay) {
			super(jobDisplay, "Refreshing");
		}

		@Override
		public IStatus runInUIThread(final IProgressMonitor monitor) {
			if (!InstallApplicationContentProvider.this.viewer.getControl().isDisposed()) {
				InstallApplicationContentProvider.this.viewer.refresh();
			}
			return Status.OK_STATUS;
		}

	};

	private final Job fetchChildrenJob = new Job("Loading...") {

		{
			setSystem(true);
		}

		@Override
		public boolean shouldSchedule() {
			return super.shouldSchedule() && children == null;
		}
		
		@Override
		public boolean shouldRun() {
			return super.shouldRun() && children == null;
		}

		@Override
		protected IStatus run(final IProgressMonitor monitor) {
			if (children != null) {
				return Status.CANCEL_STATUS;
			}
			final MultiStatus mergedStatus = new MultiStatus(ScaUiPlugin.PLUGIN_ID, IStatus.WARNING, "Problems while loading SAD files.", null);
			InstallApplicationContentProvider.this.children = InstallApplicationContentProvider.fetchDeferredChildren(InstallApplicationContentProvider.this.input, monitor,
					mergedStatus);
			InstallApplicationContentProvider.this.refreshJob.schedule();
			if (!mergedStatus.isOK()) {
				InstallApplicationContentProvider.this.loadStatus = mergedStatus;
			} else {
				InstallApplicationContentProvider.this.loadStatus = Status.OK_STATUS;
			}
			return Status.OK_STATUS;
		}

		@Override
		public String toString() {
			return getName();
		}
	};
	private Viewer viewer;
	private RefreshJob refreshJob;
	private ScaDomainManager input;
	private IStatus loadStatus;

	/**
	 * Returns the status of loading the SAD files from the domain.
	 * @since 9.0
	 */
	public IStatus getLoadStatus() {
		return this.loadStatus;
	}

	@Override
	public void dispose() {
		this.children = null;
	}

	@Override
	public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
		this.viewer = viewer;
		if (viewer != null) {
			this.refreshJob = new RefreshJob(viewer.getControl().getDisplay());
		}
		this.input = (ScaDomainManager) newInput;
	}

	/**
	 * @since 9.0
	 */
	public void addContentCompletionListener(final IJobChangeListener listener) {
		this.fetchChildrenJob.addJobChangeListener(listener);
	}

	@Override
	public Object[] getElements(final Object inputElement) {
		return getChildren(inputElement);
	}

	public List<SoftwareAssembly> getChildren() {
		if (this.children != null) {
			return Collections.unmodifiableList(this.children);
		} else {
			return Collections.emptyList();
		}
	}

	@Override
	public Object[] getChildren(final Object parentElement) {
		if (parentElement instanceof ScaDomainManager) {
			if (this.children == null) {
				this.fetchChildrenJob.schedule();
				return new Object[] { this.fetchChildrenJob };
			} else {
				return createNamespaceStructure(this.children);
			}
		} else if (parentElement instanceof WaveformsContainer) {
			WaveformsContainer container = (WaveformsContainer) parentElement;
			List<Object> containerChildren = new ArrayList<Object>();
			containerChildren.addAll(container.getSubContainers());
			containerChildren.addAll(container.getWaveforms());
			return containerChildren.toArray();
		}
		return Collections.EMPTY_LIST.toArray();
	}

	private Object[] createNamespaceStructure(List<SoftwareAssembly> sadList) {
		List<Object> retList = new ArrayList<Object>();
		List<WaveformsContainer> waveformContainers = new ArrayList<WaveformsContainer>();
		for (SoftwareAssembly sad : sadList) {
			String name = sad.getName();
			if (name != null && name.contains(".")) {
				createSubContainers(sad, waveformContainers);
			} else {
				retList.add(sad);
			}
		}
		retList.addAll(waveformContainers);
		return retList.toArray();
	}

	private void createSubContainers(SoftwareAssembly sad, List<WaveformsContainer> topLevelContainers) {
		// Namespaces are all segments of the name except the last (the 'basename')
		String[] names = sad.getName().split("\\.");
		int numContainers = names.length - 1;
		List<WaveformsContainer> containerList = new ArrayList<WaveformsContainer>();

		// Create containers for each namespace
		for (int i = 0; i < numContainers; i++) {
			WaveformsContainer container = null;

			if (i == 0) {
				// Top-level namespace -- check if a container already exists
				for (WaveformsContainer topLevelContainer : topLevelContainers) {
					if (names[i].equals(topLevelContainer.getContainerName())) {
						container = topLevelContainer;
						break;
					}
				}

				// Create a new container if we couldn't find an existing one
				if (container == null) {
					container = ScaFactory.eINSTANCE.createWaveformsContainer();
					container.setContainerName(names[i]);
					topLevelContainers.add(container);
				}
			} else {
				// Namespace OTHER than top-level -- check if its parent already has a container for the namespace
				WaveformsContainer parent = containerList.get(i - 1);
				for (WaveformsContainer child : parent.getSubContainers()) {
					if (names[i].equals(child.getContainerName())) {
						container = child;
						break;
					}
				}

				// Create a new container if we couldn't find an existing one
				if (container == null) {
					container = ScaFactory.eINSTANCE.createWaveformsContainer();
					container.setContainerName(names[i]);
					parent.getSubContainers().add(container);
				}
			}

			// Keep track of the containers for this waveform
			containerList.add(container);
		}

		// Add the waveform to the final container
		containerList.get(numContainers - 1).getWaveforms().add(sad);
	}

	@Override
	public Object getParent(final Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(final Object element) {
		if (element instanceof ScaDomainManager) {
			return true;
		} else if (element instanceof WaveformsContainer) {
			return true;
		}
		return false;
	}

	private static List<SoftwareAssembly> fetchDeferredChildren(final ScaDomainManager domMgr, final IProgressMonitor monitor, final MultiStatus status) {
		try {
			if (domMgr != null) {
				try {
					final SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching SAD files...", 3);
					domMgr.connect(subMonitor.newChild(1), RefreshDepth.SELF);
					final ScaDomainManagerFileSystem fileManager = domMgr.fetchFileManager(subMonitor.newChild(1), RefreshDepth.SELF);
					if (fileManager != null) {
						final IFileStore fileStore = fileManager.getFileStore();
						final ResourceSet resourceSet = ScaResourceFactoryUtil.createResourceSet();
						final String[] paths = ScaPreferenceConstants.parsePath(ScaPlugin.getDefault()
						        .getScaPreferenceAccessor()
						        .getString(ScaPreferenceConstants.SCA_DOMAIN_WAVEFORMS_SEARCH_PATH));
						return InstallApplicationContentProvider.fetchAssemblies(domMgr, resourceSet, fileStore, paths, status);
					} else {
						final IllegalStateException exception = new IllegalStateException("No file manager available");
						exception.fillInStackTrace();
						status.add(new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, "Failed to load SAD files: " + exception.getMessage(), exception));
					}
				} catch (final DomainConnectionException e1) {
					status.add(new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, "Failed to connect to domain: " + domMgr.getLabel(), e1));
				}
			}
			return Collections.emptyList();
		} finally {
			monitor.done();
		}
	}

	private static List<SoftwareAssembly> fetchAssemblies(final ScaDomainManager domMgr, final ResourceSet resourceSet, final IFileStore fileStore, final String[] paths, final MultiStatus status) {
		IFileInfo info;
		try {
			info = fileStore.fetchInfo(EFS.NONE, null);
		} catch (final CoreException e1) {
			status.add(new Status(e1.getStatus().getSeverity(), ScaUiPlugin.PLUGIN_ID, e1.getLocalizedMessage(), e1));
			return Collections.emptyList();
		}
		boolean proceed = false;
		final String fileStorePath = fileStore.toURI().getPath();
		if (fileStorePath.length() == 0) {
			proceed = true;
		} else if (fileStore.getName().charAt(0) != '.') {
			final IFileStore parent = fileStore.getParent();
			if (parent.getParent() != null) {
				proceed = true;
			} else {
				if (paths.length == 0) {
					proceed = true;
				} else {
					for (final String s : paths) {
						if (fileStorePath.contains(s)) {
							proceed = true;
							break;
						}
					}
				}
			}
		}
		if (!proceed) {
			return Collections.emptyList();
		}
		if (info.isDirectory()) {
			final ArrayList<SoftwareAssembly> retVal = new ArrayList<SoftwareAssembly>();
			try {
				final IFileStore[] stores = fileStore.childStores(0, null);
				for (final IFileStore child : stores) {
					try {
						retVal.addAll(InstallApplicationContentProvider.fetchAssemblies(domMgr, resourceSet, child, paths, status));
					} catch (WrappedException we) {
						String uriPath = child.toURI().getPath();
						status.add(new Status(Status.WARNING, ScaUiPlugin.PLUGIN_ID,
								"Failed to load SAD file: " + uriPath + " from Domain: " + domMgr.getLabel(), we));
					}
				}
			} catch (final CoreException e) {
				status.add(new Status(e.getStatus().getSeverity(), ScaUiPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
			}
			return retVal;
		} else {
			if (!fileStore.getName().endsWith(SadPackage.FILE_EXTENSION)) {
				return Collections.emptyList();
			}
			final URI uri = fileStore.toURI();
			final Resource sadResource = resourceSet.getResource(org.eclipse.emf.common.util.URI.createURI(uri.toString()), true);
			try {
				final SoftwareAssembly sad = SoftwareAssembly.Util.getSoftwareAssembly(sadResource);
				if (sad != null) {
					return Collections.singletonList(sad);
				}
			} catch (final Exception e) { // SUPPRESS CHECKSTYLE Logged Error
				status.add(new Status(IStatus.WARNING, ScaUiPlugin.PLUGIN_ID, "Failed to parse SAD file: " + uri.getPath(), e));
			}
			return Collections.emptyList();
		}
	}

	/**
	 * @deprecated Use {@link #addContentCompletionListener(IJobChangeListener)} instead
	 * @param jobChangeAdapter
	 * @since 9.0
	 */
	@Deprecated
	public void setContentCompletionListener(final IJobChangeListener jobChangeAdapter) {
		addContentCompletionListener(jobChangeAdapter);
	}
}
