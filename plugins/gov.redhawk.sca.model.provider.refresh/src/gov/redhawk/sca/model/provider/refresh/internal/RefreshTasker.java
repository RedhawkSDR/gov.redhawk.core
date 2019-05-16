/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.sca.model.provider.refresh.internal;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;

import gov.redhawk.model.sca.CorbaObjWrapper;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.impl.ScaDomainManagerImpl;
import gov.redhawk.model.sca.services.AbstractDataProvider;
import gov.redhawk.sca.model.provider.refresh.RefreshProviderPlugin;

/**
 * Responsible for scheduling and performing periodic refreshes using a provided {@link IRefresher}.
 */
public class RefreshTasker extends AbstractDataProvider {

	private static final long MAX_REFRESH_DELAY = 60000;
	private static final IStatus STATUS_CANNOT_REFRESH = new Status(IStatus.WARNING, RefreshProviderPlugin.PLUGIN_ID, "Unable to refresh");

	/**
	 * This is the object being refreshed
	 */
	private EObject objectToRefresh;

	/**
	 * This object performs an appropriate refresh on {@link #objectToRefresh}
	 */
	private IRefresher refresher;

	/**
	 * A {@link Runnable} which has the logic of the actual refresh operation. <b>NOTE: Access synchronization
	 * required</b>.
	 */
	private RefreshTask doRefresh;

	/**
	 * A {@link ScheduledFuture} for the next scheduled refresh, if any. <b>NOTE: Access synchronization required</b>.
	 */
	private ScheduledFuture< ? > scheduledRefresh = null;

	/**
	 * The delay of the last refresh. <b>NOTE: Access synchronization required</b>.
	 */
	private long lastDelay = 0;
	private boolean firstImmediateSchedule = true;
	protected boolean doChildRefresh = false;

	private final boolean isDomMgr;

	/**
	 * Handles the target object being disposed, or its CORBA object / profile URI changing
	 */
	private final Adapter objectToRefreshListener = new AdapterImpl() {
		private boolean lostNarrowedObject = false;

		@Override
		public void notifyChanged(final org.eclipse.emf.common.notify.Notification msg) {
			Object feature = msg.getFeature();
			if (feature == ScaPackage.Literals.DATA_PROVIDER_OBJECT__DATA_PROVIDERS) {
				if (msg.getOldValue() == RefreshTasker.this && msg.getNewValue() == null) {
					// Cancel the scheduled refresh
					if (RefreshTasker.this.scheduledRefresh != null) {
						RefreshTasker.this.scheduledRefresh.cancel(false);
						RefreshTasker.this.doRefresh = null;
						RefreshTasker.this.scheduledRefresh = null;
					}
				}
			}
			if (feature == ScaPackage.Literals.IDISPOSABLE__DISPOSED && msg.getNewBooleanValue()) {
				dispose();
			} else if (feature == ScaPackage.Literals.CORBA_OBJ_WRAPPER__CORBA_OBJ && !msg.isTouch()) {
				CorbaObjWrapper oldObj = (CorbaObjWrapper) msg.getNewValue();
				CorbaObjWrapper newObj = (CorbaObjWrapper) msg.getNewValue();
				if ((newObj == null && oldObj != null) || (newObj != null && !newObj.exists())) {
					if (isDomMgr) {
						System.out.println("\tRefreshTasker[" + RefreshTasker.this.hashCode() + "] lost narrowed object!");
					}
					
					lostNarrowedObject  = true;
				} else if (newObj != null && lostNarrowedObject) {
					if (isDomMgr) {
						System.out.println("\tRefreshTasker[" + RefreshTasker.this.hashCode() + "] narrowed object came back!");
					}
					doChildRefresh  = true;
					lostNarrowedObject = false;
				}
				schedule(0);
			} else if ((feature == ScaPackage.Literals.PROFILE_OBJECT_WRAPPER__PROFILE_URI) && !msg.isTouch()) {
				schedule(0);
			}
		}
	};

	private void addListeners() {
		if (isDomMgr) {
			System.out.println("RefreshTasker[" + RefreshTasker.this.hashCode() + "] adding listener!");
		}
		ScaModelCommand.execute(objectToRefresh, new ScaModelCommand() {
			@Override
			public void execute() {
				objectToRefresh.eAdapters().add(RefreshTasker.this.objectToRefreshListener);
			}
		});
	}

	private void removeListeners() {
		if (isDomMgr) {
			System.out.println("RefreshTasker[" + RefreshTasker.this.hashCode() + "] removing listener!");
		}
		ScaModelCommand.execute(objectToRefresh, new ScaModelCommand() {
			@Override
			public void execute() {
				objectToRefresh.eAdapters().remove(RefreshTasker.this.objectToRefreshListener);
			}
		});
	}

	public RefreshTasker(EObject objectToRefresh, IRefresher refresher) {
		this.objectToRefresh = objectToRefresh;
		this.refresher = refresher;
		this.isDomMgr = objectToRefresh instanceof ScaDomainManager;
		addListeners();
		schedule(RefreshProviderPlugin.getRefreshInterval());
		if (isDomMgr) {
			System.out.println("Created RefreshTasker for " + ((gov.redhawk.model.sca.impl.ScaDomainManagerImpl)objectToRefresh).getName());
		}
	}

	@Override
	public void dispose() {
		if (this.isDomMgr) {
			System.out.println("RefreshTasker[" + RefreshTasker.this.hashCode() + "] being disposed!");
		}
		removeListeners();
		super.dispose();
	}

	@Override
	public String getID() {
		return ScaRefreshableDataProviderService.ID;
	}

	/**
	 * Schedules a refresh after a certain delay. If a refresh is already scheduled, its time will be adjusted if
	 * the requested delay is sooner.
	 * @param delay The delay in milliseconds before refreshing.
	 */
	private synchronized void schedule(long delay) {
		if (delay < 0) {
			throw new IllegalArgumentException("Delay must be 0 or greater");
		}
		if (this.firstImmediateSchedule && (delay == 0)) {
			this.firstImmediateSchedule = false;
			return;
		}
		if (!isEnabled() && !this.isDomMgr) { // TODO: Handle the "active" stuff
			if (this.isDomMgr) {
				System.out.println("RefreshTasker[" + RefreshTasker.this.hashCode() + "] bailing schedule, not enabled");
			}
			return;
		}
		if (this.isDomMgr) {
			System.out.println("RefreshTasker[" + RefreshTasker.this.hashCode() + "] scheduling for " + this.scheduledRefresh + " (" + delay + ")");
		}

		// If a refresh is already scheduled
		if (this.scheduledRefresh != null) {
			// If the scheduled refresh will run at least a 1/2 second sooner, do nothing
			if (this.scheduledRefresh.getDelay(TimeUnit.MILLISECONDS) < (delay + 500)) {
				return;
			}

			// Cancel the pending refresh
			this.scheduledRefresh.cancel(false);
			this.doRefresh = null;
			this.scheduledRefresh = null;
		}

		// Schedule a new refresh
		this.lastDelay = delay;
		this.doRefresh = new RefreshTask();
		this.scheduledRefresh = RefreshThreadPools.getRefreshExecutor().schedule(this.doRefresh, delay, TimeUnit.MILLISECONDS);
	}

	private class RefreshTask implements Runnable {

		@Override
		public void run() {
			if (isDomMgr) {
				System.out.println("\n\n\n");
			}
			// Make sure we're still scheduled to run
			synchronized (this) {
				if (doRefresh != this || !isEnabled()) {
					if (RefreshTasker.this.isDomMgr) {
						System.out.println("RefreshTasker[" + RefreshTasker.this.hashCode() + "] not running[" + isEnabled() + "] ref: " + (this != doRefresh));
					}
					return;
				}
			}

			// Schedule a timeout that will cancel the refresh and update the status
			final IProgressMonitor monitor = new NullProgressMonitor();
			final AtomicBoolean completed = new AtomicBoolean(false);
			final long REFRESH_TIMEOUT = RefreshProviderPlugin.getRefreshTimeout();
			Future< ? > checkOnRefresh = RefreshThreadPools.getEventExecutor().schedule(() -> {
				// If we beat the refresh, cancel it and update the status
				if (completed.compareAndSet(false, true)) {
					monitor.setCanceled(true);
					String msg = String.format("Refresh timed out after %d milliseconds", REFRESH_TIMEOUT);
					if (!getStatus().getMessage().equals(msg)) {
						setStatus(new Status(IStatus.WARNING, RefreshProviderPlugin.PLUGIN_ID, msg));
					}
				}
			}, REFRESH_TIMEOUT, TimeUnit.MILLISECONDS);

			// Run the refresh, capture status / exception
			IStatus refreshStatus = null;
			Throwable refreshThrowable = null;
			try {
				refreshStatus = refresh(monitor);
			} catch (OperationCanceledException e) {
				refreshStatus = Status.CANCEL_STATUS;
			} catch (Exception | LinkageError | AssertionError e) { // SUPPRESS CHECKSTYLE Catch all non-fatal
				refreshThrowable = e;
			}

			// If we beat the timeout
			boolean backOff = false;
			try {
				if (completed.compareAndSet(false, true)) {
					// Our checkup/timeout task doesn't need to run (if it hasn't already)
					checkOnRefresh.cancel(false);

					// Set status as appropriate
					if (refreshThrowable != null) {
						backOff = true;
						setStatus(new Status(IStatus.ERROR, RefreshProviderPlugin.PLUGIN_ID, "Refresh failed", refreshThrowable));
					} else if (refreshStatus != null) {
						backOff = (refreshStatus.getSeverity() == Status.CANCEL);
						setStatus(refreshStatus);
					} else {
						// This should never occur - display the error AND log it
						backOff = true;
						IStatus status = new Status(IStatus.ERROR, RefreshProviderPlugin.PLUGIN_ID, "Received a null status from refresh");
						setStatus(status);
						RefreshProviderPlugin.getInstance().getLog().log(status);
					}
				} else {
					// We took too long and the timeout cancelled us
					backOff = true;
				}
			} finally {
				// Reschedule
				if (isDomMgr) {
					System.out.println("Rescheduling in RefreshTasker after refresh");
					backOff = false;
				}
				reschedule(backOff);
			}
		}
	}

	@Override
	public IStatus refresh(IProgressMonitor monitor) {
		if (this.refresher.canRefresh()) {
			if (this.isEnabled()) {
				if (this.doChildRefresh) {
					if (this.isDomMgr) {
						System.out.println("\tRefreshTasker[" + RefreshTasker.this.hashCode() + "] Refreshing children!");
					}
					this.refresher.refresh(monitor, RefreshDepth.CHILDREN);
					this.doChildRefresh = false;
				} else {
					this.refresher.refresh(monitor);
				}
			} else if (this.isDomMgr) {
				System.out.println("RefreshTasker[" + RefreshTasker.this.hashCode() + "] not enabled for refresh!");
			}
			return (monitor.isCanceled()) ? Status.CANCEL_STATUS : Status.OK_STATUS;
		} else {
			return (monitor.isCanceled()) ? Status.CANCEL_STATUS : STATUS_CANNOT_REFRESH;
		}
	}

	@Override
	public void setEnabled(boolean enabled) {
		if (this.isDomMgr) {
			System.out.println("RefreshTasker[" + RefreshTasker.this.hashCode() + "] enable being set to: " + enabled);
		}
		boolean oldEnabled = isEnabled();
		super.setEnabled(enabled);

		// Schedule immediately if disabled -> enabled
		if (!oldEnabled && enabled) {
			schedule(0);
		}
	}

	@Override
	public void reEnable() {
		if (this.isDomMgr) {
			System.out.println("RefreshTasker[" + RefreshTasker.this.hashCode() + "] being re-enabled");
		}
		// Explicitly don't schedule now. This is used by the refresh methods, no need to refresh
		// while we're already refreshing.
		super.setEnabled(true);

		// Make sure that the periodic refresh is scheduled. If it gets disabled for some reason,
		// a manual refresh of the model object should reschedule it.
		reschedule(false);
	}

	/**
	 * Indicates that a refresh has completed and should be rescheduled.
	 * @param backoff If true, indicates that the refresh should back off. False to use the default delay before the
	 * next refresh.
	 */
	private synchronized void reschedule(boolean backOff) {
		if (this.scheduledRefresh != null) {
			if (isDomMgr) {
				System.out.println("Cancelling previous scheduled refresh");
			}
			this.scheduledRefresh.cancel(false);
			this.scheduledRefresh = null;
			this.doRefresh = null;
		}
		final long REFRESH_DELAY = RefreshProviderPlugin.getRefreshInterval();
		if (backOff) {
			// We'll reschedule for the previous delay + delay (i.e. normal_delay, normal_delay*2, normal_delay*3). We
			// bound this on the low end by 2 * normal delay, and on the high end by an absolute max delay.
			long delay = Math.min(Math.max(this.lastDelay + REFRESH_DELAY, REFRESH_DELAY * 2), MAX_REFRESH_DELAY);
			schedule(delay);
		} else {
			schedule(REFRESH_DELAY);
		}
	}

}
