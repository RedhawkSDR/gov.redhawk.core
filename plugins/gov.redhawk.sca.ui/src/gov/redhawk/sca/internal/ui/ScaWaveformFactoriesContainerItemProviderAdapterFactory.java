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
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaWaveformFactory;
import gov.redhawk.model.sca.commands.ScaModelCommandWithResult;
import gov.redhawk.model.sca.provider.ScaWaveformFactoriesContainerItemProvider;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubMonitor;

public class ScaWaveformFactoriesContainerItemProviderAdapterFactory implements IAdapterFactory {

	private static final Class< ? >[] LIST = new Class< ? >[] { IRefreshable.class };

	@Override
	public < T > T getAdapter(Object adaptableObject, Class<T> adapterType) {
		if (adaptableObject instanceof ScaWaveformFactoriesContainerItemProvider && adapterType.isAssignableFrom(IRefreshable.class)) {
			IRefreshable refreshable = adapt((ScaWaveformFactoriesContainerItemProvider) adaptableObject);
			return adapterType.cast(refreshable);
		}

		return null;
	}

	private IRefreshable adapt(final ScaWaveformFactoriesContainerItemProvider provider) {
		return new IRefreshable() {

			@Override
			public void refresh(final IProgressMonitor monitor, final RefreshDepth depth) throws InterruptedException {
				final SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching waveform factories", 100);
				switch (depth) {
				case FULL:
					refreshFull(subMonitor.newChild(100));
					break;
				case CHILDREN:
				case SELF:
					refreshFull(subMonitor.newChild(100));
					break;
				default:
					break;
				}
			}

			private void refreshStandard(final IProgressMonitor monitor) throws InterruptedException {
				final ScaDomainManager domain = (ScaDomainManager) provider.getParent(null);
				domain.fetchWaveformFactories(monitor, RefreshDepth.SELF);
			}

			private void refreshFull(final IProgressMonitor monitor) throws InterruptedException {
				final ScaDomainManager domain = (ScaDomainManager) provider.getParent(null);
				final SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching fully waveform factories", 2);
				if (subMonitor.isCanceled()) {
					throw new OperationCanceledException();
				}
				refreshStandard(subMonitor.newChild(1));

				final List<ScaWaveformFactory> factories = ScaModelCommandWithResult.execute(domain, new ScaModelCommandWithResult<List<ScaWaveformFactory>>() {

					@Override
					public void execute() {
						setResult(new ArrayList<ScaWaveformFactory>(domain.getWaveformFactories()));
					}
				});
				if (factories != null) {
					final SubMonitor refreshMonitor = subMonitor.newChild(1);
					refreshMonitor.setWorkRemaining(factories.size());
					for (final ScaWaveformFactory factory : factories) {
						if (subMonitor.isCanceled()) {
							throw new OperationCanceledException();
						}
						factory.refresh(refreshMonitor.newChild(1), RefreshDepth.FULL);
					}
				}
			}

		};
	}

	@Override
	public Class< ? >[] getAdapterList() {
		return ScaWaveformFactoriesContainerItemProviderAdapterFactory.LIST;
	}
}
