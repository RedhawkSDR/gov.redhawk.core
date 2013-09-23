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
package gov.redhawk.sca.model.provider.refresh;

import gov.redhawk.model.sca.CorbaObjWrapper;
import gov.redhawk.model.sca.IDisposable;
import gov.redhawk.model.sca.ProfileObjectWrapper;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.services.AbstractDataProvider;
import gov.redhawk.sca.model.provider.refresh.preferences.RefreshPreferenceConstants;
import gov.redhawk.sca.util.IPreferenceAccessor;

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
import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;

/**
 * @since 4.0
 * 
 */
public class RefreshTask extends AbstractDataProvider implements Runnable {

	private static final ExecutorService EXECUTOR_POOL = Executors.newSingleThreadExecutor(new NamedThreadFactory(RefreshTask.class.getName()));

	/**
	 * @since 4.0
	 */
	public static final String PROP_ACTIVE = "active";

	private static final long REFRESH_TIMEOUT = 120;

	private final EObject eObject;
	private final IRefresher refresher;
	private ScheduledFuture< ? > schedule;
	private final ScheduledExecutorService threadPool;
	private boolean active = true;
	private final Adapter listener = new AdapterImpl() {
		@Override
		public void notifyChanged(final org.eclipse.emf.common.notify.Notification msg) {
			if (msg.getFeature() == ScaPackage.Literals.IDISPOSABLE__DISPOSED) {
				if (msg.getNewBooleanValue() && RefreshTask.this.schedule != null) {
					RefreshTask.this.schedule.cancel(true);
				}
			} else if (msg.getFeature() == ScaPackage.Literals.CORBA_OBJ_WRAPPER__CORBA_OBJ && !msg.isTouch()) {
				schedule();
			} else if ((msg.getFeature() == ScaPackage.Literals.PROFILE_OBJECT_WRAPPER__PROFILE_URI) && !msg.isTouch()) {
				schedule();
			}
		}
	};

	private final IPreferenceChangeListener refreshPreferenceListener = new IPreferenceChangeListener() {

		@Override
		public void preferenceChange(final PreferenceChangeEvent event) {
			if (event.getKey().equals(RefreshPreferenceConstants.REFRESH_INTERVAL)) {
				updateInterval();
			}
		}

	};

	/**
	 * @since 4.0
	 */
	public RefreshTask(final ScheduledExecutorService threadPool, final EObject eObject, final IRefresher refresher) {
		this.refresher = refresher;
		this.eObject = eObject;
		this.threadPool = threadPool;
		addListeners();
		schedule();
	}

	private synchronized void updateInterval() {
		if (this.schedule != null) {
			cancel();
			schedule();
		}
	}

	private synchronized void schedule() {
		if (this.schedule == null && shouldSchedule()) {
			final long interval = getInterval();
			if (interval > 0) {
				this.schedule = RefreshTask.this.threadPool.scheduleWithFixedDelay(RefreshTask.this, interval, interval, TimeUnit.MILLISECONDS);
			}
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
		return shouldSchedule;
	}

	protected boolean shouldRun() {
		return !isDisposed() && isEnabled() && this.active;
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
					RefreshTask.this.eObject.eAdapters().add(RefreshTask.this.listener);
				}
			});
		}
		RefreshProviderPlugin plugin = RefreshProviderPlugin.getInstance();
		if (plugin != null) {
			plugin.getPreferenceAccessor().addPreferenceChangeListener(this.refreshPreferenceListener);
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
					RefreshTask.this.eObject.eAdapters().remove(RefreshTask.this.listener);
				}
			});
		}
		final RefreshProviderPlugin instance = RefreshProviderPlugin.getInstance();
		if (instance != null) {
			final IPreferenceAccessor accessor = instance.getPreferenceAccessor();
			if (accessor != null) {
				accessor.removePreferenceChangeListener(this.refreshPreferenceListener);
			}
		}
	}

	public long getInterval() {
		final RefreshProviderPlugin instance = RefreshProviderPlugin.getInstance();
		if (instance != null) {
			final IPreferenceAccessor accessor = instance.getPreferenceAccessor();
			if (accessor != null) {
				return accessor.getLong(RefreshPreferenceConstants.REFRESH_INTERVAL);
			}
		}
		return -1;
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
		final Future< ? > task = RefreshTask.EXECUTOR_POOL.submit(new Runnable() {

			@Override
			public void run() {
				if (!shouldRun()) {
					return;
				}
				RefreshTask.this.refresher.refresh(null);
			}

		});
		try {
			task.get(RefreshTask.REFRESH_TIMEOUT, TimeUnit.SECONDS);
			setStatus(Status.OK_STATUS);
			if (!shouldSchedule()) {
				cancel();
			}
		} catch (final InterruptedException e) {
			task.cancel(true);
			setStatus(Status.CANCEL_STATUS);
		} catch (final ExecutionException e) {
			setStatus(new Status(IStatus.ERROR, RefreshProviderPlugin.PLUGIN_ID, "Refresh failed.", e));
		} catch (final TimeoutException e) {
			task.cancel(true);
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
		firePropertyChange(RefreshTask.PROP_ACTIVE, oldValue, this.active);
	}

	public boolean isActive() {
		return this.active;
	}

}
