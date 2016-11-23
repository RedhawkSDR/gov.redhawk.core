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
package gov.redhawk.frontend.ui.internal;

import gov.redhawk.frontend.ListenerAllocation;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.provider.FrontendItemProviderAdapterFactory;
import gov.redhawk.frontend.util.TunerUtils;
import gov.redhawk.model.sca.IDisposable;
import gov.redhawk.sca.ui.ITooltipProvider;
import gov.redhawk.sca.ui.ScaModelAdapterFactoryLabelProvider;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.IDescriptionProvider;
import org.eclipse.ui.progress.UIJob;

public class FrontEndLabelProvider extends ScaModelAdapterFactoryLabelProvider implements IDescriptionProvider, ITooltipProvider {

	private boolean disposed;

	public FrontEndLabelProvider() {
		super(FrontEndLabelProvider.createAdapterFactory());
		setFireLabelUpdateNotifications(true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		((FrontendItemProviderAdapterFactory) this.adapterFactory).dispose();
		this.adapterFactory = null;
		this.disposed = true;
		super.dispose();
	}

	private static AdapterFactory createAdapterFactory() {
		return new FrontendItemProviderAdapterFactory();
	}

	private class LabelRefreshJob extends UIJob {
		public LabelRefreshJob() {
			super("Label update");
			setSystem(true);
			setPriority(Job.INTERACTIVE);
		}

		private final BlockingQueue<Notification> notificationQueue = new LinkedBlockingQueue<Notification>();

		public void addNotification(final Notification notification) {
			this.notificationQueue.offer(notification);
			schedule();
		}

		@Override
		public boolean shouldSchedule() {
			return super.shouldSchedule() && !disposed;
		}

		@Override
		public boolean shouldRun() {
			return super.shouldRun() && !disposed;
		}

		@Override
		public IStatus runInUIThread(final IProgressMonitor monitor) {
			if (disposed) {
				return Status.CANCEL_STATUS;
			}
			final ArrayList<Notification> notifications = new ArrayList<Notification>();
			this.notificationQueue.drainTo(notifications);
			for (final Notification notification : notifications) {
				if (notification.getNotifier() instanceof IDisposable) {
					final IDisposable disposable = (IDisposable) notification.getNotifier();
					if (disposable.isDisposed()) {
						continue;
					}
				}
				notifyChanged(notification);
			}
			return Status.OK_STATUS;
		}

	}

	private final LabelRefreshJob labelRefresh = new LabelRefreshJob();

	@Override
	public void notifyChanged(final Notification notification) {
		if (notification.isTouch()) {
			return;
		}
		if (Display.getCurrent() != null) {
			super.notifyChanged(notification);
		} else {
			this.labelRefresh.addNotification(notification);
		}
	}

	@Override
	public void init(ICommonContentExtensionSite aConfig) {

	}

	@Override
	public void restoreState(IMemento aMemento) {

	}

	@Override
	public void saveState(IMemento aMemento) {

	}

	@Override
	public Image getToolTipImage(Object object) {
		return null;
	}

	@Override
	public String getToolTipText(Object element) {
		if (element instanceof TunerStatus) {
			return TunerUtils.getControlId((TunerStatus) element);
		}
		if (element instanceof ListenerAllocation) {
			return ((ListenerAllocation) element).getListenerID();
		}
		return null;
	}

	@Override
	public Color getToolTipBackgroundColor(Object object) {
		return null;
	}

	@Override
	public Color getToolTipForegroundColor(Object object) {
		return null;
	}

	@Override
	public Font getToolTipFont(Object object) {
		return null;
	}

	@Override
	public Point getToolTipShift(Object object) {
		return null;
	}

	@Override
	public boolean useNativeToolTip(Object object) {
		if (object instanceof TunerStatus) {
			return true;
		}
		if (object instanceof ListenerAllocation) {
			return true;
		}
		return false;
	}

	@Override
	public int getToolTipTimeDisplayed(Object object) {
		return 0;
	}

	@Override
	public int getToolTipDisplayDelayTime(Object object) {
		return 0;
	}

	@Override
	public int getToolTipStyle(Object object) {
		return 0;
	}

	@Override
	public String getDescription(Object anElement) {
		if (anElement instanceof TunerStatus) {
			TunerStatus tunerStatus = (TunerStatus) anElement;
			if (tunerStatus.getAllocationID() != null) {
				return tunerStatus.getAllocationID();
			}
		}
		return getText(anElement);
	}

	@Override
	public boolean isFireLabelUpdateNotifications() {
		return true;
	}
}
