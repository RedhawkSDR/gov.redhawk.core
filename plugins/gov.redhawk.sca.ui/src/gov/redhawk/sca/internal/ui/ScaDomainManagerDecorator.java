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

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaWaveformFactory;
import gov.redhawk.model.sca.commands.ScaModelCommand;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;

public class ScaDomainManagerDecorator extends LabelProvider implements ILightweightLabelDecorator {

	/** The adapter. */
	private final Adapter adapter = new AdapterImpl() {
		@Override
		public void notifyChanged(final Notification msg) {
			if (ScaDomainManagerDecorator.this.disposed) {
				if (msg.getNotifier() instanceof Notifier) {
					((Notifier) msg.getNotifier()).eAdapters().remove(this);
				}
				return;
			}
			if (msg.isTouch()) {
				return;
			}

			switch (msg.getFeatureID(ScaWaveformFactory.class)) {
			case ScaPackage.SCA_DOMAIN_MANAGER__STATE:
				fireStatusChanged(msg.getNotifier());
				break;
			default:
				break;
			}
			super.notifyChanged(msg);
		}
	};

	private boolean disposed;

	private void fireStatusChanged(final Object object) {
		final LabelProviderChangedEvent event = new LabelProviderChangedEvent(this, object);
		fireLabelProviderChanged(event);
	}

	@Override
	public void decorate(final Object element, final IDecoration decoration) {
		if (element instanceof ScaDomainManager) {
			final ScaDomainManager domMgr = (ScaDomainManager) element;
			ScaModelCommand.execute(domMgr, new ScaModelCommand() {

				@Override
				public void execute() {
					if (!domMgr.eAdapters().contains(adapter)) {
						domMgr.eAdapters().add(ScaDomainManagerDecorator.this.adapter);
					}
				}

			});

			switch (domMgr.getState()) {
			case CONNECTED:
				decoration.addSuffix(" CONNECTED");
				break;
			case DISCONNECTED:
				decoration.addSuffix(" DISCONNECTED");
				break;
			case DISCONNECTING:
				decoration.addSuffix(" DISCONNECTING");
				break;
			case CONNECTING:
				decoration.addSuffix(" CONNECTING");
				break;
			case FAILED:
				decoration.addSuffix(" FAILED");
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void dispose() {
		super.dispose();
		this.disposed = true;
	}

}
