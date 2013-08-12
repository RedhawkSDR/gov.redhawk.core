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
import gov.redhawk.sca.ui.ScaUiPlugin;
import gov.redhawk.sca.ui.ScaUiPluginImages;
import gov.redhawk.sca.util.PluginUtil;

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

			switch (msg.getFeatureID(ScaWaveformFactory.class)) {
			case ScaPackage.SCA_DOMAIN_MANAGER__STATE:
				if (!PluginUtil.equals(msg.getOldValue(), msg.getNewValue())) {
					fireDomainManagerChanged((ScaDomainManager) msg.getNotifier());
				}
				break;
			default:
				break;
			}
			super.notifyChanged(msg);
		}
	};

	private boolean disposed;

	public void decorate(final Object element, final IDecoration decoration) {
		if (element instanceof ScaDomainManager) {
			ScaUiPlugin.getDefault().getImageRegistry().getDescriptor(ScaUiPluginImages.IMG_DEFAULT_DOMAIN_OVR);

			final ScaDomainManager domMgr = (ScaDomainManager) element;
			ScaModelCommand.execute(domMgr, new ScaModelCommand() {

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

	protected void fireDomainManagerChanged(final ScaDomainManager... managers) {
		final LabelProviderChangedEvent event = new LabelProviderChangedEvent(ScaDomainManagerDecorator.this, managers);
		fireLabelProviderChanged(event);
	}

	@Override
	public void dispose() {
		super.dispose();
		this.disposed = true;
	}

}
