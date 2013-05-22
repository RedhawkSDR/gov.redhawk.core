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
package gov.redhawk.sca.util;

import gov.redhawk.sca.util.internal.ScaUtilPluginActivator;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

/**
 * An Eclipse Job that will not log or report any errors
 * @since 2.2
 */
public abstract class SilentJob extends Job {

	private static final Debug DEBUG = new Debug(ScaUtilPluginActivator.ID, "silentErrors");

	/**
	 * @since 3.0
	 */
	public static final String PROP_SILENT_STATUS = "silentStatus";

	private IStatus status;

	private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	public SilentJob(final String msg) {
		super(msg);
	}

	/**
	 * 
	 * @return The silent status
	 * @deprecated Use {@link getSilentStatus()}
	 */
	@Deprecated
	public IStatus getStatus() {
		return this.status;
	}

	/**
	 * @since 3.0
	 */
	public IStatus getSilentStatus() {
		return this.status;
	}

	/**
	 * @since 3.0
	 */
	protected void setSilentStatus(final IStatus status) {
		final IStatus oldStatus = this.status;
		this.status = status;
		this.propertyChangeSupport.firePropertyChange(SilentJob.PROP_SILENT_STATUS, oldStatus, this.status);
	}

	/**
	 * @since 3.0
	 */
	public void addPropertyChangeListener(final PropertyChangeListener listener) {
		this.propertyChangeSupport.addPropertyChangeListener(listener);
	}

	/**
	 * @since 3.0
	 */
	public void addPropertyChangeListener(final String propertyName, final PropertyChangeListener listener) {
		this.propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	/**
	 * @since 3.0
	 */
	public void removePropertyChangeListener(final PropertyChangeListener listener) {
		this.propertyChangeSupport.removePropertyChangeListener(listener);
	}

	/**
	 * @since 3.0
	 */
	public void removePropertyChangeListener(final String propertyName, final PropertyChangeListener listener) {
		this.propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
	}

	/**
	 * Client is excepted to override this method.  This method is wrapped in a protected call such that the Job result is always Status.Ok
	 * @param monitor
	 * @return The result of the run, must not be null, can be any status and the Job will not show an error in the dialog.
	 */
	protected abstract IStatus runSilent(final IProgressMonitor monitor);

	@Override
	protected final IStatus run(final IProgressMonitor monitor) {
		IStatus newStatus = null;
		try {
			try {
				if (monitor.isCanceled()) {
					return Status.CANCEL_STATUS;
				}
				newStatus = runSilent(monitor);
			} catch (final org.omg.CORBA.SystemException e) {
				newStatus = handleCorbaException(e);
			} catch (final OperationCanceledException e) {
				newStatus = Status.CANCEL_STATUS;
			} catch (final Exception e) {
				// Handle unexpected conditions
				newStatus = handleException(e);
			} catch (final ThreadDeath e) {
				//must not consume thread death
				newStatus = handleException(e);
				throw e;
			} catch (final Error e) {
				newStatus = handleException(e);
			} finally {
				//result must not be null
				if (newStatus == null) {
					newStatus = handleException(new NullPointerException("Null status returned from: " + getClass()));
				}
			}

			if (SilentJob.DEBUG.enabled) {
				if (newStatus != null && !newStatus.isOK()) {
					SilentJob.DEBUG.message("Silent Job ending with silent errors {0}.", this.status);
					SilentJob.DEBUG.catching(newStatus.getException());
				}
			}

			if (monitor.isCanceled() || newStatus == Status.CANCEL_STATUS) {
				return Status.CANCEL_STATUS;
			}

			// Always return OK status so that dialogs don't pop up
			return Status.OK_STATUS;
		} finally {
			setSilentStatus(newStatus);
		}
	}

	/**
	 * @since 3.0
	 */
	protected IStatus handleCorbaException(final org.omg.CORBA.SystemException e) {
		final StringBuilder message = new StringBuilder();

		// Covert some common CORBA exceptions to friendly equivalents.
		if (e instanceof org.omg.CORBA.COMM_FAILURE) {
			message.append("A CORBA communcations failure has occured");
		} else if (e instanceof org.omg.CORBA.OBJECT_NOT_EXIST) {
			message.append("The CORBA expected servant object has been deleted and no longer exits.");
		} else if (e instanceof org.omg.CORBA.TIMEOUT) {
			message.append("A CORBA communcations timeout has occured while an operation was in progress.");
		} else if (e instanceof org.omg.CORBA.TRANSIENT) {
			message.append("A transient error has occured while attempting to establish communication with the CORBA servant.");
		}
		message.append("\n" + e.toString());
		return new Status(IStatus.ERROR, ScaUtilPluginActivator.getDefault().getBundleId(this), IStatus.ERROR, message.toString(), e);
	}

	/**
	 * @since 3.0
	 */
	protected IStatus handleException(final Throwable t) {
		final StringBuilder message = new StringBuilder();
		message.append(t.toString());
		String bundleId = ScaUtilPluginActivator.getDefault().getBundleId(this);
		if (bundleId == null) {
			bundleId = "unknown";
		}
		return new Status(IStatus.ERROR, bundleId, IStatus.ERROR, message.toString(), t);
	}
}
