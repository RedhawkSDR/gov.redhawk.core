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
package gov.redhawk.sca.model.provider.refresh.internal;

import gov.redhawk.model.sca.CorbaObjWrapper;
import gov.redhawk.model.sca.IDisposable;
import gov.redhawk.model.sca.ProfileObjectWrapper;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.services.AbstractDataProvider;
import gov.redhawk.sca.model.provider.refresh.RefreshProviderPlugin;
import gov.redhawk.sca.util.Debug;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import mil.jpeojtrs.sca.util.NamedThreadFactory;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;

public class RefreshTasker extends AbstractDataProvider implements Runnable {
	private static final Debug DEBUG = new Debug(RefreshProviderPlugin.PLUGIN_ID, "tasker");

	/**
	 * One global tasking thread pool  The actual CORBA invocation is handled by the refresher
	 */
	public static final ScheduledExecutorService TASKER_POOL = Executors.newScheduledThreadPool(4, new NamedThreadFactory(
		ScaRefreshableDataProviderService.class.getName()));

	/**
	 * @since 4.0
	 */
	public static final String PROP_ACTIVE = "active";

	public static final ExecutorService WORKER_POOL = new StarvationThreadPoolExecutor(new NamedThreadFactory(ScaRefreshableDataProviderService.class.getName()
		+ ":WorkerPool"));
	private final EObject eObject;
	private final IRefresher refresher;
	private ScheduledFuture< ? > schedule;
	private boolean active = true;
	private final Adapter listener = new AdapterImpl() {
		@Override
		public void notifyChanged(final org.eclipse.emf.common.notify.Notification msg) {
			if (msg.getFeature() == ScaPackage.Literals.IDISPOSABLE__DISPOSED) {
				if (msg.getNewBooleanValue()) {
					dispose();
				}
			} else if (msg.getFeature() == ScaPackage.Literals.CORBA_OBJ_WRAPPER__CORBA_OBJ && !msg.isTouch()) {
				schedule();
			} else if ((msg.getFeature() == ScaPackage.Literals.PROFILE_OBJECT_WRAPPER__PROFILE_URI) && !msg.isTouch()) {
				schedule();
			}
		}
	};

	public RefreshTasker(final EObject eObject, final IRefresher refresher) {
		this.refresher = refresher;
		this.eObject = eObject;
		addListeners();
		doSchedule(RefreshProviderPlugin.getRefreshInterval());
	}
	
	private synchronized void doSchedule(long delay) {
		if (this.schedule == null) {
			if (DEBUG.enabled) {
				DEBUG.enteringMethod(delay);
				DEBUG.message("Scheduled (" + delay + " " + TimeUnit.MILLISECONDS + "): " + this.eObject);
			}
			this.schedule = TASKER_POOL.schedule(RefreshTasker.this, delay, TimeUnit.MILLISECONDS);
		} else {
			long remaining = this.schedule.getDelay(TimeUnit.MILLISECONDS);
			if (remaining > delay && remaining > 500) {
				if (DEBUG.enabled) {
					DEBUG.enteringMethod(delay);
					DEBUG.message("ReScheduled (" + delay + " " + TimeUnit.MILLISECONDS + "): " + this.eObject);
				}
				this.schedule.cancel(false);
				this.schedule = TASKER_POOL.schedule(RefreshTasker.this, delay, TimeUnit.MILLISECONDS);
			}
		}
	}
	
	public synchronized void schedule() {
		schedule(0);
	}

	public synchronized void schedule(long delay) {
		if (shouldSchedule()) {
			doSchedule(delay);
		} else if (DEBUG.enabled) {
			DEBUG.message("Ignoring schedule on " + this.eObject);
		}
	}

	protected boolean shouldSchedule() {
		boolean shouldSchedule = shouldRun();
		if (shouldSchedule && this.eObject instanceof CorbaObjWrapper< ? >) {
			final CorbaObjWrapper< ? > wrapper = (CorbaObjWrapper< ? >) this.eObject;
			shouldSchedule = wrapper.getCorbaObj() != null;
		}
		if (shouldSchedule && this.eObject instanceof ProfileObjectWrapper< ? >) {
			final ProfileObjectWrapper< ? > wrapper = (ProfileObjectWrapper< ? >) this.eObject;
			shouldSchedule |= wrapper.getProfileURI() != null;
		}
		return DEBUG.exitingMethodWithResult(shouldSchedule);
	}

	protected boolean shouldRun() {
		return DEBUG.exitingMethodWithResult(!isDisposed() && isEnabled() && this.active);
	}

	public synchronized void cancel() {
		if (this.schedule != null) {
			this.schedule.cancel(true);
		}
		this.schedule = null;
	}

	@Override
	public void setEnabled(final boolean enabled) {
		super.setEnabled(enabled);
		if (enabled) {
			schedule();
		} else {
			cancel();
		}
	}

	private void addListeners() {
		if (this.eObject instanceof EObject) {
			ScaModelCommand.execute(this.eObject, new ScaModelCommand() {

				@Override
				public void execute() {
					RefreshTasker.this.eObject.eAdapters().add(RefreshTasker.this.listener);
				}
			});
		}
	}

	@Override
	public void dispose() {
		removeListeners();
		cancel();
		super.dispose();
	}

	private void removeListeners() {
		if (this.eObject instanceof EObject) {
			ScaModelCommand.execute(this.eObject, new ScaModelCommand() {

				@Override
				public void execute() {
					RefreshTasker.this.eObject.eAdapters().remove(RefreshTasker.this.listener);
				}
			});
		}
	}

	@Override
	public void run() {
		if (!shouldRun()) {
			return;
		}
		if (this.eObject instanceof IDisposable) {
			final IDisposable disposable = (IDisposable) this.eObject;
			if (disposable.isDisposed()) {
				setEnabled(false);
				return;
			}
		}

		final Future< ? > task;
		task = WORKER_POOL.submit(new Runnable() {

			@Override
			public void run() {
				if (!shouldRun()) {
					return;
				}
				try {
					RefreshTasker.this.refresher.refresh(null);
				} finally {
					schedule = null;
					schedule(RefreshProviderPlugin.getRefreshInterval());
				}
			}

		});
		try {
			task.get(RefreshProviderPlugin.getRefreshTimeout(), TimeUnit.MILLISECONDS);
			setStatus(Status.OK_STATUS);
		} catch (final InterruptedException e) {
			setStatus(Status.CANCEL_STATUS);
		} catch (final ExecutionException e) {
			setStatus(new Status(IStatus.ERROR, RefreshProviderPlugin.PLUGIN_ID, "Refresh failed.", e));
		} catch (final TimeoutException e) {
			setStatus(new Status(IStatus.WARNING, RefreshProviderPlugin.PLUGIN_ID, "Refresh timed out.", e));
		}
	}

	public void setActive(final boolean active) {
		final boolean oldValue = this.active;
		this.active = active;
		if (this.active) {
			schedule();
		} else {
			cancel();
		}
		firePropertyChange(RefreshTasker.PROP_ACTIVE, oldValue, this.active);
	}

	public boolean isActive() {
		return this.active;
	}

}
