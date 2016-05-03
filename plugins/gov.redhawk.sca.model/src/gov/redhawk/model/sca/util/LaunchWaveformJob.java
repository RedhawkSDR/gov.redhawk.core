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
package gov.redhawk.model.sca.util;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.SystemException;

import CF.Application;
import CF.DataType;
import CF.DeviceAssignmentType;
import CF.InvalidFileName;
import CF.InvalidProfile;
import CF.ApplicationFactoryPackage.CreateApplicationError;
import CF.ApplicationFactoryPackage.CreateApplicationInsufficientCapacityError;
import CF.ApplicationFactoryPackage.CreateApplicationRequestError;
import CF.ApplicationFactoryPackage.InvalidInitConfiguration;
import CF.DomainManagerPackage.ApplicationAlreadyInstalled;
import CF.DomainManagerPackage.ApplicationInstallationError;
import CF.DomainManagerPackage.ApplicationUninstallationError;
import CF.DomainManagerPackage.InvalidIdentifier;
import CF.LifeCyclePackage.ReleaseError;
import CF.ResourcePackage.StartError;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.ScaWaveformFactory;
import gov.redhawk.model.sca.commands.ScaModelCommandWithResult;
import gov.redhawk.sca.util.Debug;
import gov.redhawk.sca.util.SilentJob;

/**
 * @since 14.0
 */
public class LaunchWaveformJob extends SilentJob {

	private static final Debug DEBUG = new Debug(ScaModelPlugin.ID, "launch/waveform");

	private final ScaDomainManager domMgr;
	private final String waveformName;
	private final IPath waveformPath;
	private final DeviceAssignmentType[] deviceAssn;
	private final DataType[] configProps;
	private final boolean autoStart;
	private final boolean uninstallExistingApplicationFactory;
	private final Object waitLock;
	private ScaWaveform waveform = null;

	public LaunchWaveformJob(final ScaDomainManager domMgr, final String waveformName, final IPath waveformPath, final DeviceAssignmentType[] deviceAssn,
		final DataType[] configProps, final boolean autoStart, final Object waitLock) {
		this(domMgr, waveformName, waveformPath, deviceAssn, configProps, autoStart, waitLock, false);
	}

	/**
	 * @since 20.0
	 */
	public LaunchWaveformJob(final ScaDomainManager domMgr, final String waveformName, final IPath waveformPath, final DeviceAssignmentType[] deviceAssn,
		final DataType[] configProps, final boolean autoStart, final Object waitLock, final boolean uninstallExistingAppFactory) {
		super("Launching waveform " + waveformName);
		this.domMgr = domMgr;
		this.waveformName = waveformName;
		this.waveformPath = waveformPath;
		this.deviceAssn = deviceAssn;
		this.configProps = configProps;
		this.autoStart = autoStart;
		this.uninstallExistingApplicationFactory = uninstallExistingAppFactory;
		this.waitLock = waitLock;
		this.setSystem(true);
		this.setUser(false);
		this.setPriority(Job.LONG);
	}

	public ScaWaveform getWaveform() {
		return this.waveform;
	}

	@Override
	protected IStatus runSilent(final IProgressMonitor m) {
		// Track whether we installed the ApplicationFactory ourselves.
		final SubMonitor subMonitor = SubMonitor.convert(m, "Launching Application: " + this.waveformName, IProgressMonitor.UNKNOWN);
		ScaWaveformFactory factory = null;
		boolean installedAppFactory = false;

		try {
			final String profilePath = this.waveformPath.toPortableString();

			try {
				// Create waveform using the new CF.DomainManager#createApplication() method
				final Application app = domMgr.createApplication(profilePath, this.waveformName, this.configProps, this.deviceAssn);

				final String ior = app.toString();
				waveform = ScaModelCommandWithResult.execute(domMgr, new ScaModelCommandWithResult<ScaWaveform>() {
					@Override
					public void execute() {

						if (domMgr != null) {
							// Check to be sure someone else didn't already add the waveform
							for (ScaWaveform w : domMgr.getWaveforms()) {
								if (ior.equals(w.getIor())) {
									setResult(w);
									return;
								}
							}

							ScaWaveform newWaveform = ScaFactory.eINSTANCE.createScaWaveform();
							newWaveform.setCorbaObj(app);
							domMgr.getWaveforms().add(newWaveform);
							setResult(newWaveform);
						}
					}

				});
				subMonitor.worked(1);
			} catch (BAD_OPERATION exception) {
				// IDE-1109: It's possible to get here if domain is pre-2.0, fall back to old method for launching waveforms
				// use existing Application Factory if exists, since only ONE can exist per Waveform profilePath in Domain
				for (final ScaWaveformFactory temp : LaunchWaveformJob.this.domMgr.fetchWaveformFactories(null, RefreshDepth.SELF)) { // TODO: Better progress monitor
					if (temp.getProfile().equals(profilePath)) {
						factory = temp;
					}
				}

				// unless power user specified to uninstall existing Application Factory
				if (factory != null && uninstallExistingApplicationFactory) {
					try {
						this.domMgr.uninstallScaWaveformFactory(factory);
						factory = null;
					} catch (ApplicationUninstallationError | InvalidIdentifier | SystemException ex) {
						DEBUG.catching("Failed to uninstall existing Waveform factory before launching a waveform.", ex);
						return new Status(Status.ERROR, ScaModelPlugin.ID,
							"Failed to uninstall existing Waveform factory before launching a waveform: " + waveformName, ex);
					}
				}

				////////////////////
				// INSTALL WAVEFORM (Application) Factory
				subMonitor.subTask("Installing REDHAWK Waveform Factory: " + profilePath);
				while (factory == null) {
					DEBUG.message("Installing REDHAWK Waveform Factory...");
					if (subMonitor.isCanceled()) {
						throw new OperationCanceledException();
					}

					try {
						factory = this.domMgr.installScaWaveformFactory(profilePath);
						installedAppFactory = true;
					} catch (final ApplicationAlreadyInstalled e) {
						String errorMsg = "The domain manager reports the application factory is already installed, but it was not found. "
							+ "Another installed waveform may be using the same softwareassembly id in its XML file.";
						return new Status(Status.ERROR, ScaModelPlugin.ID, errorMsg, e);
					}

				}
				subMonitor.worked(1);

				if (subMonitor.isCanceled()) {
					throw new OperationCanceledException();
				}

				Assert.isNotNull(factory, "Failed to get/install REDHAWK Waveform Factory");

				////////////////////
				// CREATE WAVEFORM
				final IProgressMonitor createMonitor = subMonitor.newChild(1);
				createMonitor.beginTask("Creating Waveform (application): " + this.waveformName, IProgressMonitor.UNKNOWN);
				this.waveform = factory.createWaveform(createMonitor, LaunchWaveformJob.this.waveformName, LaunchWaveformJob.this.configProps,
					LaunchWaveformJob.this.deviceAssn);

				if (subMonitor.isCanceled()) {
					throw new OperationCanceledException();
				}
			}

			if (this.autoStart) {
				try {
					waveform.start();
				} catch (StartError e) {
					return new Status(Status.ERROR, ScaModelPlugin.ID, CFErrorFormatter.format(e, "waveform " + waveformName), e);
				}
			}

			if (subMonitor.isCanceled()) {
				throw new OperationCanceledException();
			}
		} catch (final InvalidProfile e) {
			return StatusFactory.createStatus(e, ScaModelPlugin.ID, this.waveformName);
		} catch (final InvalidFileName e) {
			return StatusFactory.createStatus(e, ScaModelPlugin.ID, this.waveformName);
		} catch (final ApplicationInstallationError e) {
			return StatusFactory.createStatus(e, ScaModelPlugin.ID, this.waveformName);
		} catch (final CreateApplicationError e) {
			return StatusFactory.createStatus(e, ScaModelPlugin.ID, this.waveformName);
		} catch (final CreateApplicationRequestError e) {
			return StatusFactory.createStatus(e, ScaModelPlugin.ID, this.waveformName);
		} catch (final InvalidInitConfiguration e) {
			return StatusFactory.createStatus(e, ScaModelPlugin.ID, this.waveformName);
		} catch (final SystemException e) {
			return StatusFactory.createStatus(e, ScaModelPlugin.ID, this.waveformName);
		} catch (CreateApplicationInsufficientCapacityError e) {
			return StatusFactory.createStatus(e, ScaModelPlugin.ID, this.waveformName);
		} finally {
			// If we installed the ApplicationFactory above, uninstall it to prevent
			// future conflicts if the user changes the SAD.
			try {
				if (subMonitor.isCanceled()) {
					// the user requested a cancel...so let's release the app
					if (this.waveform != null) {
						try {
							this.waveform.releaseObject();
						} catch (final ReleaseError e) {
							// PASS
						}
						this.waveform = null;
					}
				}
				if (installedAppFactory && factory != null) {
					DEBUG.message("Uninstall waveform factory = {0}", factory);
					try {
						this.domMgr.uninstallScaWaveformFactory(factory);
					} catch (ApplicationUninstallationError | InvalidIdentifier | SystemException ex) {
						if (DEBUG.enabled) {
							DEBUG.catching("Failed to uninstall existing Waveform factory before launching a waveform.", ex);
						}
					}
				}
			} finally {
				synchronized (this.waitLock) {
					this.waitLock.notifyAll();
				}
				subMonitor.done();
			}
		}

		return Status.OK_STATUS;
	}

}
