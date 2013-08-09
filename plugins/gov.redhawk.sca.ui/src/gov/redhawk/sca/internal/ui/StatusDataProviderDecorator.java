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
package gov.redhawk.sca.internal.ui;

import gov.redhawk.model.sca.IStatusProvider;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.commands.ScaModelCommand;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * 
 */
public class StatusDataProviderDecorator extends LabelProvider implements ILightweightLabelDecorator {

	private boolean disposed;

	private final Adapter adaperListener = new AdapterImpl() {
		@Override
		public void notifyChanged(final org.eclipse.emf.common.notify.Notification msg) {
			if (StatusDataProviderDecorator.this.disposed) {
				if (msg.getNotifier() instanceof EObject) {
					((EObject) msg.getNotifier()).eAdapters().remove(this);
				}
				return;
			}

			switch (msg.getFeatureID(IStatusProvider.class)) {
			case ScaPackage.ISTATUS_PROVIDER__STATUS:
				final Object oldValue = msg.getOldValue();
				final Object newValue = msg.getNewValue();
				if (oldValue instanceof IStatus && newValue instanceof IStatus) {
					final IStatus newStatus = (IStatus) newValue;
					final IStatus oldStatus = (IStatus) oldValue;
					if (newStatus.getSeverity() != oldStatus.getSeverity()) {
						fireStatusChanged(msg.getNotifier());
					}
				} else {
					fireStatusChanged(msg.getNotifier());
				}
				break;
			default:
				break;
			}
		}
	};

	private void fireStatusChanged(final Object object) {
		final LabelProviderChangedEvent event = new LabelProviderChangedEvent(this, object);
		fireLabelProviderChanged(event);
	}

	@Override
	public void dispose() {
		super.dispose();
		this.disposed = true;
	}

	public void decorate(final Object element, final IDecoration decoration) {
		if (element instanceof IStatusProvider) {
			final IStatusProvider statusProvider = (IStatusProvider) element;

			ScaModelCommand.execute(statusProvider, new ScaModelCommand() {

				public void execute() {
					if (!statusProvider.eAdapters().contains(StatusDataProviderDecorator.this.adaperListener)) {
						statusProvider.eAdapters().add(StatusDataProviderDecorator.this.adaperListener);
					}
				}

			});

			final ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
			IStatus status = statusProvider.getStatus();
			if (status == null) {
				status = Status.OK_STATUS;
			}
			switch (status.getSeverity()) {
			case IStatus.WARNING:
				decoration.addOverlay(sharedImages.getImageDescriptor(ISharedImages.IMG_DEC_FIELD_WARNING), IDecoration.BOTTOM_LEFT);
				break;
			case IStatus.ERROR:
				decoration.addOverlay(sharedImages.getImageDescriptor(ISharedImages.IMG_DEC_FIELD_ERROR), IDecoration.BOTTOM_LEFT);
				break;
			default:
				break;
			}
		}

	}

}
