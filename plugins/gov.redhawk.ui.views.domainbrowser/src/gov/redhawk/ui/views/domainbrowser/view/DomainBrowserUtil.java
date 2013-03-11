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
package gov.redhawk.ui.views.domainbrowser.view;

import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaDeviceManagerFileSystem;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaDomainManagerFileSystem;
import gov.redhawk.model.sca.ScaFileStore;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.ScaWaveformFactory;
import gov.redhawk.model.sca.provider.ScaDeviceManagersContainerItemProvider;
import gov.redhawk.model.sca.provider.ScaWaveformFactoriesContainerItemProvider;
import gov.redhawk.model.sca.provider.ScaWaveformsContainerItemProvider;
import gov.redhawk.sca.util.CorbaURIUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.StructuredViewer;

public final class DomainBrowserUtil {
	private static Map<Object, Job> jobMap = new HashMap<Object, Job>();

	private DomainBrowserUtil() {

	}

	public static Object delayedContent(final Object object, final StructuredViewer viewer) {

		if (object instanceof ScaFileStore) {
			final ScaFileStore store = (ScaFileStore) object;
			if (!store.isSetChildren()) {
				Job job = DomainBrowserUtil.jobMap.get(store);
				if (job == null) {
					job = new Job("Loading...") {

						@Override
						protected IStatus run(final IProgressMonitor monitor) {
							store.fetchChildren(monitor);
							return Status.OK_STATUS;
						}

						@Override
						public String toString() {
							return getName();
						};

					};
					DomainBrowserUtil.jobMap.put(store, job);
					job.schedule();
				}
				return job;
			}
		} else if (object instanceof ScaDeviceManager) {
			final ScaDeviceManager deviceManager = (ScaDeviceManager) object;

			if (!deviceManager.isSetAllDevices() || !deviceManager.isSetFileSystem() || !deviceManager.isSetIdentifier() || !deviceManager.isSetProfile()
			        || !deviceManager.isSetServices()) {
				Job job = DomainBrowserUtil.jobMap.get(deviceManager);
				if (job == null) {
					job = new Job("Loading...") {

						@Override
						protected IStatus run(final IProgressMonitor monitor) {
							deviceManager.fetchIdentifier(monitor);
							deviceManager.fetchProfile(monitor);
							deviceManager.fetchDevices(monitor);
							deviceManager.fetchServices(monitor);
							deviceManager.fetchFileSystem(monitor);
							return Status.OK_STATUS;
						}

						@Override
						public String toString() {
							return getName();
						};
					};
					DomainBrowserUtil.jobMap.put(deviceManager, job);
					job.schedule();
				}
				return job;
			}
		} else if (object instanceof ScaWaveform) {
			final ScaWaveform waveform = (ScaWaveform) object;
			if (!waveform.isSetIdentifier() || !waveform.isSetProfile() || !waveform.isSetName() || !waveform.isSetComponents()) {
				Job job = DomainBrowserUtil.jobMap.get(waveform);
				if (job == null) {
					job = new Job("Loading...") {

						@Override
						protected IStatus run(final IProgressMonitor monitor) {
							waveform.fetchIdentifier(monitor);
							waveform.fetchName(monitor);
							waveform.fetchComponents(monitor);
							waveform.fetchProfile(monitor);
							waveform.fetchProperties(monitor);

							return Status.OK_STATUS;
						}

						@Override
						public String toString() {
							return getName();
						};
					};
					DomainBrowserUtil.jobMap.put(waveform, job);
					job.schedule();
				}
				return job;
			}
		} else if (object instanceof ScaWaveformFactory) {
			final ScaWaveformFactory factory = (ScaWaveformFactory) object;

			if (!factory.isSetIdentifier() || !factory.isSetProfile()) {
				Job job = DomainBrowserUtil.jobMap.get(factory);
				if (job == null) {
					job = new Job("Loading...") {

						@Override
						protected IStatus run(final IProgressMonitor monitor) {
							factory.fetchIdentifier(monitor);
							factory.fetchProfile(monitor);
							return Status.OK_STATUS;
						}

						@Override
						public String toString() {
							return getName();
						};
					};
					DomainBrowserUtil.jobMap.put(factory, job);
					job.schedule();
				}
				return job;
			}
		} else if (object instanceof ScaComponent) {
			final ScaComponent component = (ScaComponent) object;

			if (!component.isSetDevices() || !component.isSetProperties() || !component.isSetPorts()) {
				Job job = DomainBrowserUtil.jobMap.get(component);
				if (job == null) {
					job = new Job("Loading...") {

						@Override
						protected IStatus run(final IProgressMonitor monitor) {
							component.fetchDevices(monitor);
							component.fetchProperties(monitor);
							component.fetchPorts(monitor);
							return Status.OK_STATUS;
						}

						@Override
						public String toString() {
							return getName();
						};
					};
					DomainBrowserUtil.jobMap.put(component, job);
					job.schedule();
				}
				return job;
			}
		} else if (object instanceof ScaDomainManagerFileSystem) {
			final ScaDomainManagerFileSystem fileSystem = (ScaDomainManagerFileSystem) object;
			if (!fileSystem.isSetChildren()) {
				Job job = DomainBrowserUtil.jobMap.get(fileSystem);
				if (job == null) {
					job = new Job("Loading...") {

						@Override
						protected IStatus run(final IProgressMonitor monitor) {
							fileSystem.fetchChildren(monitor);
							return Status.OK_STATUS;
						}

						@Override
						public String toString() {
							return getName();
						};
					};
					DomainBrowserUtil.jobMap.put(fileSystem, job);
					job.schedule();
				}
				return job;
			}
		} else if (object instanceof ScaDeviceManagerFileSystem) {
			final ScaDeviceManagerFileSystem fileSystem = (ScaDeviceManagerFileSystem) object;

			if (!fileSystem.isSetChildren()) {
				Job job = DomainBrowserUtil.jobMap.get(fileSystem);
				if (job == null) {
					job = new Job("Loading...") {

						@Override
						protected IStatus run(final IProgressMonitor monitor) {
							fileSystem.fetchChildren(monitor);
							return Status.OK_STATUS;
						}

						@Override
						public String toString() {
							return getName();
						};
					};
					DomainBrowserUtil.jobMap.put(fileSystem, job);
					job.schedule();
				}
				return job;
			}
		} else if (object instanceof ScaDeviceManagersContainerItemProvider) {
			final ScaDomainManager dom = (ScaDomainManager) ((ScaDeviceManagersContainerItemProvider) object).getParent(null);
			if (!dom.isSetDeviceManagers()) {
				Job job = DomainBrowserUtil.jobMap.get(dom);
				if (job == null) {
					job = new Job("Loading...") {

						@Override
						protected IStatus run(final IProgressMonitor monitor) {
							dom.fetchDeviceManagers(monitor);
							return Status.OK_STATUS;
						}

						@Override
						public String toString() {
							return getName();
						};
					};
					DomainBrowserUtil.jobMap.put(dom, job);
					job.schedule();
				}
				return job;
			}
		} else if (object instanceof ScaWaveformFactoriesContainerItemProvider) {
			final ScaDomainManager dom = (ScaDomainManager) ((ScaWaveformFactoriesContainerItemProvider) object).getParent(null);
			if (!dom.isSetWaveformFactories()) {
				Job job = DomainBrowserUtil.jobMap.get(dom);
				if (job == null) {
					job = new Job("Loading...") {

						@Override
						protected IStatus run(final IProgressMonitor monitor) {
							dom.fetchDeviceManagers(monitor);
							return Status.OK_STATUS;
						}

						@Override
						public String toString() {
							return getName();
						};
					};
					DomainBrowserUtil.jobMap.put(dom, job);
					job.schedule();
				}
				return job;
			}
		} else if (object instanceof ScaWaveformsContainerItemProvider) {
			final ScaDomainManager dom = (ScaDomainManager) ((ScaWaveformsContainerItemProvider) object).getParent(null);
			if (!dom.isSetWaveforms()) {
				Job job = DomainBrowserUtil.jobMap.get(dom);
				if (job == null) {
					job = new Job("Loading...") {

						@Override
						protected IStatus run(final IProgressMonitor monitor) {
							dom.fetchDeviceManagers(monitor);
							return Status.OK_STATUS;
						}

						@Override
						public String toString() {
							return getName();
						};
					};
					DomainBrowserUtil.jobMap.put(dom, job);
					job.schedule();
				}
				return job;
			}
		}

		return null;
	}

	protected static String getNamingService(final String domainName) {
		final StringTokenizer token = new StringTokenizer(domainName, "@");

		if (token.countTokens() < 2) {
			return "";
		} else {
			token.nextToken();
			String initRef = token.nextToken();

			initRef = CorbaURIUtil.addDefaultPrefix(initRef);
			initRef = CorbaURIUtil.addDefaultPort(initRef);

			return initRef;
		}
	}
}
