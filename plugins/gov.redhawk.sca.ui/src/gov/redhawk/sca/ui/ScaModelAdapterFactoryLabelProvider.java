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

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.IViewerNotification;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.ui.provider.TransactionalAdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableFontProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Color;

import gov.redhawk.model.sca.CorbaObjWrapper;
import gov.redhawk.sca.ScaPlugin;
import gov.redhawk.sca.internal.ui.ScaLabelProviderChangedEvent;

/**
 * @since 6.0
 *
 */
public class ScaModelAdapterFactoryLabelProvider extends TransactionalAdapterFactoryLabelProvider
		implements IColorProvider, ITableColorProvider, IFontProvider, ITableFontProvider {

	/**
	 * @since 7.0
	 */
	public ScaModelAdapterFactoryLabelProvider(final AdapterFactory adapterFactory) {
		super(TransactionalEditingDomain.Registry.INSTANCE.getEditingDomain(ScaPlugin.EDITING_DOMAIN_ID), adapterFactory);
	}

	/**
	 * @since 9.0
	 */
	public ScaModelAdapterFactoryLabelProvider(final AdapterFactory adapterFactory, final Viewer viewer) {
		this(adapterFactory);
		if (viewer != null) {
			setDefaultForeground(viewer.getControl().getForeground());
			setDefaultBackground(viewer.getControl().getBackground());
		}
	}

	@Override
	public Color getForeground(final Object object) {
		return run(new RunnableWithResult.Impl<Color>() {
			@Override
			public void run() {
				setResult(ScaModelAdapterFactoryLabelProvider.super.getForeground(object));
			}
		});
	}

	@Override
	public Color getForeground(final Object object, final int columnIndex) {
		return run(new RunnableWithResult.Impl<Color>() {
			@Override
			public void run() {
				setResult(ScaModelAdapterFactoryLabelProvider.super.getForeground(object, columnIndex));
			}
		});
	}

	@Override
	public void notifyChanged(Notification notification) {
		if (!(notification.getNotifier() instanceof CorbaObjWrapper< ? >)) {
			super.notifyChanged(notification);
		}

		if (isFireLabelUpdateNotifications()) {
			if (!(notification instanceof IViewerNotification) || ((IViewerNotification) notification).isLabelUpdate()) {
				for (ILabelProviderListener labelProviderListener : labelProviderListeners) {
					labelProviderListener.labelProviderChanged(new ScaLabelProviderChangedEvent(this, notification.getNotifier(), notification));
				}
			}
		}
	}

}
