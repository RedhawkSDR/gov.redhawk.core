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

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.ScaWaveformFactory;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.sca.util.Debug;
import gov.redhawk.sca.util.SilentJob;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.omg.CORBA.SystemException;

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
	private final Object waitLock;
	private ScaWaveform waveform = null;

	public LaunchWaveformJob(final ScaDomainManager domMgr, final String waveformName, final IPath waveformPath, final DeviceAssignmentType[] deviceAssn,
	        final DataType[] configProps, final boolean autoStart, final Object waitLock) {
		super("Launching waveform " + waveformName);
		this.domMgr = domMgr;
		this.waveformName = waveformName;
		this.waveformPath = waveformPath;
		this.deviceAssn = deviceAssn;
		this.configProps = configProps;
		this.autoStart = autoStart;
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
		boolean installed = false;
		try {
			final String profile = this.waveformPath.toPortableString();
			for (final ScaWaveformFactory temp : LaunchWaveformJob.this.domMgr.fetchWaveformFactories(null)) {
				if (temp.getProfile().equals(profile)) {
					factory = temp;
				}
			}
			
			////////////////////
			// INSTALL WAVEFORM
			subMonitor.subTask("Installing SCA Waveform: " + profile);
			while (factory == null) {
				if (subMonitor.isCanceled()) {
					throw new OperationCanceledException();
				}
				
				try {
					factory = this.domMgr.installScaWaveformFactory(profile);
					installed = true;
				} catch (final ApplicationAlreadyInstalled a) {
					try {
						factory = ScaModelCommand.runExclusive(this.domMgr, new RunnableWithResult.Impl<ScaWaveformFactory>() {

							public void run() {
								for (final ScaWaveformFactory factory : LaunchWaveformJob.this.domMgr.fetchWaveformFactories(null)) {
									if (factory.getProfile().equals(profile)) {
										setResult(factory);
									}
								}
							}

						});
					} catch (final InterruptedException e) {
						// PASS
					}
				}
			}
			subMonitor.worked(1);

			if (subMonitor.isCanceled()) {
				throw new OperationCanceledException();
			}

			Assert.isNotNull(factory, "Failed to get SCA Waveform Factory");

			////////////////////
			// CREATE WAVEFORM
			final IProgressMonitor createMonitor = subMonitor.newChild(1);
			createMonitor.beginTask("Creating application: " + this.waveformName, IProgressMonitor.UNKNOWN);
			this.waveform = factory.createWaveform(createMonitor, LaunchWaveformJob.this.waveformName, LaunchWaveformJob.this.configProps,
			        LaunchWaveformJob.this.deviceAssn);

			if (subMonitor.isCanceled()) {
				throw new OperationCanceledException();
			}

			if (this.autoStart) {
				try {
	                waveform.start();
                } catch (StartError e) {
	               return new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to start: " + waveformName, e);
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
				if (installed && factory != null) {
					try {
						this.domMgr.uninstallScaWaveformFactory(factory);
					} catch (final ApplicationUninstallationError a) {
						if (DEBUG.enabled) {
							DEBUG.message("Failed to uninstall Waveform factory after launching a waveform.");
							DEBUG.catching(a);
						}
					} catch (final InvalidIdentifier i) {
						if (DEBUG.enabled) {
							DEBUG.message("Failed to uninstall Waveform factory after launching a waveform.");
							DEBUG.catching(i);
						}
					} catch (final SystemException e) {
						if (DEBUG.enabled) {
							DEBUG.message("Failed to uninstall Waveform factory after launching a waveform.");
							DEBUG.catching(e);
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
