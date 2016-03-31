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
package gov.redhawk.sca.internal.ui;

import gov.redhawk.model.sca.IRefreshable;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.provider.ScaEventChannelsContainerItemProvider;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;

public class ScaEventChannelsContainerItemProviderAdapterFactory implements IAdapterFactory {

	private static final Class< ? >[] LIST = new Class< ? >[] {
		IRefreshable.class
	};

	private static class Refresher implements IRefreshable {

		private final ScaDomainManager domain;

		public Refresher(final ScaDomainManager domMgr) {
			this.domain = domMgr;
		}

		@Override
		public void refresh(final IProgressMonitor monitor, final RefreshDepth depth) throws InterruptedException {
			final SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching event channels", 100);

			switch (depth) {
			case FULL:
			case CHILDREN:
			case SELF:
				refreshStandard(subMonitor.newChild(100));
				break;
			default:
				break;
			}
		}

		private void refreshStandard(final IProgressMonitor monitor) throws InterruptedException {
			this.domain.fetchEventChannels(monitor);
		}

	}

	@Override
	public Object getAdapter(final Object adaptableObject, @SuppressWarnings("rawtypes") final Class adapterType) {
		if (adaptableObject instanceof ScaEventChannelsContainerItemProvider) {
			if (adapterType == IRefreshable.class) {
				return new Refresher((ScaDomainManager) ((ScaEventChannelsContainerItemProvider) adaptableObject).getTarget());
			}
		}
		return null;
	}

	@Override
	public Class< ? >[] getAdapterList() {
		return ScaEventChannelsContainerItemProviderAdapterFactory.LIST;
	}

}
