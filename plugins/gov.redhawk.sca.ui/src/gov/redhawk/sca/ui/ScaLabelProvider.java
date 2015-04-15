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
package gov.redhawk.sca.ui;

import gov.redhawk.model.sca.IDisposable;
import gov.redhawk.model.sca.IStatusProvider;
import gov.redhawk.model.sca.ScaPort;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import mil.jpeojtrs.sca.scd.AbstractPort;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.IDescriptionProvider;
import org.eclipse.ui.progress.UIJob;

/**
 * The Class ScaLabelProvider.
 * @since 8.0
 */
public class ScaLabelProvider extends ScaModelAdapterFactoryLabelProvider implements IDescriptionProvider, ITooltipProvider {

	private boolean disposed;

	/**
	 * The constructor.
	 */
	public ScaLabelProvider() {
		super(ScaLabelProvider.createAdapterFactory());
		setFireLabelUpdateNotifications(true);
	}

	public ScaLabelProvider(final AdapterFactory factory) {
		super(factory);
		setFireLabelUpdateNotifications(true);

	}

	/**
	 * Creates the adapter factory.
	 * 
	 * @return the adapter factory
	 */
	protected static AdapterFactory createAdapterFactory() {
		// Create an adapter factory that yields item providers.
		//
		final ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

		return adapterFactory;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		((ComposedAdapterFactory) this.adapterFactory).dispose();
		this.adapterFactory = null;
		this.disposed = true;
		super.dispose();
	}

	@Override
	public String getDescription(final Object anElement) {
		if (anElement instanceof EObject) {
			final EObject eObj = (EObject) anElement;
			final IItemLabelProvider provider = (IItemLabelProvider) getAdapterFactory().adapt(eObj, IItemLabelProvider.class);
			return provider.getText(anElement);
		}
		return null;
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
			return super.shouldSchedule() && !ScaLabelProvider.this.disposed;
		}

		@Override
		public boolean shouldRun() {
			return super.shouldRun() && !ScaLabelProvider.this.disposed;
		}

		@Override
		public IStatus runInUIThread(final IProgressMonitor monitor) {
			if (ScaLabelProvider.this.disposed) {
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
				ScaLabelProvider.super.notifyChanged(notification);
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

	// IToolTipProvider
	@Override
	public void init(final ICommonContentExtensionSite aConfig) {
	}

	@Override
	public void restoreState(final IMemento aMemento) {
	}

	@Override
	public void saveState(final IMemento aMemento) {
	}

	@Override
	public Image getToolTipImage(final Object element) {
		if (element instanceof IStatusProvider) {
			final IStatusProvider dpo = (IStatusProvider) element;
			final IStatus status = dpo.getStatus();
			if (status != null && !status.isOK()) {
				switch (status.getSeverity()) {
				case IStatus.INFO:
					return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_INFO);
				case IStatus.WARNING:
					return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_WARNING);
				case IStatus.ERROR:
					return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_ERROR);
				default:
					return null;
				}
			}
		}
		return null;
	}

	@Override
	public String getToolTipText(final Object element) {
		final StringBuilder toolTip = new StringBuilder();

		String portDescription = null;
		if (element instanceof ScaPort< ? , ? >) {
			final ScaPort< ? , ? > scaPort = (ScaPort< ? , ? >) element;
			final AbstractPort port = scaPort.getProfileObj();
			toolTip.append(port.getRepID());
			portDescription = port.getDescription();
		}

		if (element instanceof IStatusProvider) {
			final IStatusProvider dpo = (IStatusProvider) element;
			if (!dpo.getStatus().isOK()) {
				if (toolTip.length() > 0) {
					toolTip.append('\n');
				}
				toolTip.append(dpo.getStatus().getMessage());
			}
		}

		if (portDescription != null && portDescription.length() > 0) {
			if (toolTip.length() > 0) {
				toolTip.append('\n');
			}
			toolTip.append(portDescription);
		}

		if (toolTip.length() > 0) {
			return toolTip.toString();
		} else {
			return null;
		}
	}

	@Override
	public Color getToolTipBackgroundColor(final Object object) {
		return null;
	}

	@Override
	public Color getToolTipForegroundColor(final Object object) {
		return null;
	}

	@Override
	public Font getToolTipFont(final Object object) {
		return null;
	}

	@Override
	public Point getToolTipShift(final Object object) {
		return new Point(5, 5); // SUPPRESS CHECKSTYLE MagicNumber
	}

	@Override
	public boolean useNativeToolTip(final Object object) {
		return false;
	}

	@Override
	public int getToolTipTimeDisplayed(final Object object) {
		return 4000;
	}

	@Override
	public int getToolTipDisplayDelayTime(final Object object) {
		return 100;
	}

	@Override
	public int getToolTipStyle(final Object object) {
		return SWT.NONE;
	}

}
