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

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;

/**
 * 
 */
public class ScaWaveformFactoriesContainerItemProviderAdapterFactory implements IAdapterFactory {

	private static final Class< ? >[] LIST = new Class< ? >[] { IRefreshable.class };

	/**
	 * {@inheritDoc}
	 */
	public Object getAdapter(final Object adaptableObject, final Class adapterType) {
		if (adaptableObject instanceof ScaWaveformFactoriesContainerItemProvider) {
			if (adapterType == IRefreshable.class) {
				final ScaWaveformFactoriesContainerItemProvider provider = (ScaWaveformFactoriesContainerItemProvider) adaptableObject;
				return new IRefreshable() {

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
						domain.fetchWaveformFactories(monitor);
					}

					private void refreshFull(final IProgressMonitor monitor) throws InterruptedException {
						final ScaDomainManager domain = (ScaDomainManager) provider.getParent(null);
						final SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching fully waveform factories", 2);
						refreshStandard(subMonitor.newChild(1));
						final ScaWaveformFactory[] factories = ScaModelCommandWithResult.execute(domain, new ScaModelCommandWithResult<ScaWaveformFactory[]>() {

							public void execute() {
								setResult(domain.getWaveformFactories().toArray(new ScaWaveformFactory[domain.getWaveformFactories().size()]));
							}
						});
						if (factories != null) {
							final SubMonitor refreshMonitor = subMonitor.newChild(1);
							refreshMonitor.beginTask("Refresh waveforms", factories.length);
							for (final ScaWaveformFactory factory : factories) {
								factory.refresh(refreshMonitor.newChild(1), RefreshDepth.FULL);
							}
						}
					}

				};
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public Class< ? >[] getAdapterList() {
		return ScaWaveformFactoriesContainerItemProviderAdapterFactory.LIST;
	}

}
