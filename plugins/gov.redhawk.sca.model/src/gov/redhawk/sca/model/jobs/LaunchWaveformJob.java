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
package gov.redhawk.sca.model.jobs;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
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
import CF.DomainManagerPackage.ApplicationInstallationError;
import CF.LifeCyclePackage.ReleaseError;
import CF.ResourcePackage.StartError;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.commands.ScaModelCommandWithResult;
import gov.redhawk.model.sca.util.StatusFactory;
import gov.redhawk.sca.util.SilentJob;
import mil.jpeojtrs.sca.util.CFErrorFormatter;

/**
 * @since 22.0
 */
public class LaunchWaveformJob extends SilentJob {

	private final ScaDomainManager domMgr;
	private final String waveformName;
	private final IPath waveformPath;
	private final DeviceAssignmentType[] deviceAssn;
	private final DataType[] configProps;
	private final boolean autoStart;
	private Object waitLock = null;
	private ScaWaveform waveform = null;

	public LaunchWaveformJob(final ScaDomainManager domMgr, final String waveformName, final IPath waveformPath, final DeviceAssignmentType[] deviceAssn,
		final DataType[] configProps, final boolean autoStart) {
		super("Launching waveform " + waveformName);
		this.domMgr = domMgr;
		this.waveformName = waveformName;
		this.waveformPath = waveformPath;
		this.deviceAssn = deviceAssn;
		this.configProps = configProps;
		this.autoStart = autoStart;
		this.setSystem(true);
		this.setUser(false);
		this.setPriority(Job.LONG);
	}

	/**
	 * The job will lock the provided object and call {@link Object#notifyAll()} when it completes.
	 * @param waitLock
	 * @deprecated Use {@link Job#addJobChangeListener(org.eclipse.core.runtime.jobs.IJobChangeListener)
	 * addJobChangeListener} or
	 * {@link Job#join(long, IProgressMonitor) join}
	 */
	@Deprecated
	public void setWaitLock(Object waitLock) {
		this.waitLock = waitLock;
	}

	public ScaWaveform getWaveform() {
		return this.waveform;
	}

	@Override
	protected IStatus runSilent(final IProgressMonitor m) {
		final SubMonitor subMonitor = SubMonitor.convert(m, "Launching Application: " + this.waveformName, IProgressMonitor.UNKNOWN);
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
			} finally {
				if (this.waitLock != null) {
					synchronized (this.waitLock) {
						this.waitLock.notifyAll();
					}
				}
				subMonitor.done();
			}
		}

		return Status.OK_STATUS;
	}
}
