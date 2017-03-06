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

import gov.redhawk.model.sca.IRefreshable;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.commands.ScaModelCommandWithResult;
import gov.redhawk.model.sca.provider.ScaDeviceManagersContainerItemProvider;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;

public class ScaDeviceManagersContainerItemProviderAdapterFactory implements IAdapterFactory {

	private static final Class< ? >[] LIST = new Class< ? >[] { IRefreshable.class };

	@Override
	public < T > T getAdapter(Object adaptableObject, Class<T> adapterType) {
		if (adaptableObject instanceof ScaDeviceManagersContainerItemProvider && adapterType.isAssignableFrom(IRefreshable.class)) {
			IRefreshable refreshable = adapt((ScaDeviceManagersContainerItemProvider) adaptableObject);
			return adapterType.cast(refreshable);
		}

		return null;
	}

	private IRefreshable adapt(final ScaDeviceManagersContainerItemProvider provider) {
		return new IRefreshable() {

			@Override
			public void refresh(final IProgressMonitor monitor, final RefreshDepth depth) throws InterruptedException {
				final SubMonitor subMonitor = SubMonitor.convert(monitor, "Refreshing Device Managers...", 100);
				switch (depth) {
				case FULL:
					refreshFull(subMonitor.newChild(100));
					break;
				case CHILDREN:
				case SELF:
					refreshStandard(subMonitor.newChild(100));
					break;
				default:
					break;
				}
				subMonitor.done();
			}

			private void refreshStandard(final IProgressMonitor monitor) throws InterruptedException {
				final ScaDomainManager domain = (ScaDomainManager) provider.getParent(null);
				domain.fetchDeviceManagers(monitor, RefreshDepth.SELF);
			}

			private void refreshFull(final IProgressMonitor monitor) throws InterruptedException {
				final SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching device managers fully", 2);

				refreshStandard(subMonitor.split(1));

				final ScaDomainManager domain = (ScaDomainManager) provider.getParent(null);
				final SubMonitor refreshMonitor = subMonitor.newChild(1);
				final List<ScaDeviceManager> devMgrs = ScaModelCommandWithResult.execute(domain, new ScaModelCommandWithResult<List<ScaDeviceManager>>() {

					@Override
					public void execute() {
						setResult(new ArrayList<ScaDeviceManager>(domain.getDeviceManagers()));
					}
				});
				if (devMgrs != null) {
					refreshMonitor.setWorkRemaining(devMgrs.size());
					for (final ScaDeviceManager manager : devMgrs) {
						manager.refresh(refreshMonitor.split(1), RefreshDepth.FULL);
					}
				}
			}
		};
	}

	@Override
	public Class< ? >[] getAdapterList() {
		return ScaDeviceManagersContainerItemProviderAdapterFactory.LIST;
	}

}
