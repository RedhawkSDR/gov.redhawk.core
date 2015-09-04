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

import java.util.concurrent.Callable;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.omg.CORBA.SystemException;

import CF.DataType;
import CF.DeviceAssignmentType;
import CF.DomainManagerPackage.ApplicationAlreadyInstalled;
import CF.DomainManagerPackage.ApplicationUninstallationError;
import CF.DomainManagerPackage.InvalidIdentifier;
import CF.LifeCyclePackage.ReleaseError;

/**
 * @since 14.0
 */
public class LaunchWaveformCallable implements Callable<ScaWaveform> {
	private static final Debug DEBUG = new Debug(ScaModelPlugin.ID, "launch/waveform");

	private final ScaDomainManager domMgr;
	private final String waveformName;
	private final IPath waveformPath;
	private final DeviceAssignmentType[] deviceAssn;
	private final DataType[] configProps;
	private final boolean autoStart;

	private SubMonitor subMonitor;

	public LaunchWaveformCallable(final ScaDomainManager domMgr, final String waveformName, final IPath waveformPath, final DeviceAssignmentType[] deviceAssn,
		final DataType[] configProps, final boolean autoStart, IProgressMonitor monitor) {
		this.domMgr = domMgr;
		this.waveformName = waveformName;
		this.waveformPath = waveformPath;
		this.deviceAssn = deviceAssn;
		this.configProps = configProps;
		this.autoStart = autoStart;
		subMonitor = SubMonitor.convert(monitor, "Launching Application: " + this.waveformName, IProgressMonitor.UNKNOWN);
	}

	@Override
	public ScaWaveform call() throws Exception {
		// Track whether we installed the ApplicationFactory ourselves.
		ScaWaveform retVal = null;
		ScaWaveformFactory factory = null;
		boolean installed = false;
		try {
			////////////////////
			// INSTALL WAVEFORM
			final String profile = this.waveformPath.toPortableString();
			subMonitor.subTask("Installing REDHAWK Waveform: " + profile);
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

							@Override
							public void run() {
								for (final ScaWaveformFactory factory : LaunchWaveformCallable.this.domMgr.getWaveformFactories()) {
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

			Assert.isNotNull(factory, "Failed to get REDHAWK Waveform Factory");

			////////////////////
			// CREATE WAVEFORM
			final IProgressMonitor createMonitor = subMonitor.newChild(1);
			createMonitor.beginTask("Creating application: " + this.waveformName, IProgressMonitor.UNKNOWN);
			retVal = factory.createWaveform(createMonitor, LaunchWaveformCallable.this.waveformName, LaunchWaveformCallable.this.configProps,
				LaunchWaveformCallable.this.deviceAssn);

			if (subMonitor.isCanceled()) {
				throw new OperationCanceledException();
			}

			if (this.autoStart) {
				final StartJob job = new StartJob(waveformName, retVal);
				job.setUser(true);
				job.schedule();
			}

			if (subMonitor.isCanceled()) {
				throw new OperationCanceledException();
			}
		} finally {
			// If we installed the ApplicationFactory above, uninstall it to prevent
			// future conflicts if the user changes the SAD.
			try {
				if (subMonitor.isCanceled()) {
					// the user requested a cancel...so let's release the app
					if (retVal != null) {
						try {
							retVal.releaseObject();
						} catch (final ReleaseError e) {
							// PASS
						}
						retVal = null;
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
				subMonitor.done();
			}
		}

		return retVal;
	}

}
