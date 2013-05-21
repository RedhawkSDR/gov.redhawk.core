/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.sca.internal.ui;

import gov.redhawk.model.sca.IRefreshable;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.commands.ScaModelCommandWithResult;
import gov.redhawk.model.sca.provider.ScaWaveformsContainerItemProvider;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;

/**
 * 
 */
public class ScaWaveformsContainerItemProviderAdapterFactory implements IAdapterFactory {

	private static final Class< ? >[] LIST = new Class< ? >[] {
		IRefreshable.class
	};

	private static class Refresher implements IRefreshable {

		private final ScaDomainManager domain;

		public Refresher(final ScaDomainManager domMgr) {
			this.domain = domMgr;
		}

		public void refresh(final IProgressMonitor monitor, final RefreshDepth depth) throws InterruptedException {
			final SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching waveforms", 100);

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
		}

		private void refreshStandard(final IProgressMonitor monitor) throws InterruptedException {
			this.domain.fetchWaveforms(monitor);
		}

		private void refreshFull(final IProgressMonitor monitor) throws InterruptedException {
			final SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching waveforms fully", 2);
			refreshStandard(subMonitor.newChild(1));
			final ScaWaveform[] waveforms = ScaModelCommandWithResult.execute(this.domain, new ScaModelCommandWithResult<ScaWaveform[]>() {

				public void execute() {
					setResult(Refresher.this.domain.getWaveforms().toArray(new ScaWaveform[Refresher.this.domain.getWaveforms().size()]));
				}
			});
			final SubMonitor refreshMonitor = subMonitor.newChild(1);
			if (waveforms != null) {
				refreshMonitor.beginTask("Refreshing waveforms", waveforms.length);
				for (final ScaWaveform waveform : waveforms) {
					waveform.refresh(refreshMonitor.newChild(1), RefreshDepth.SELF);
				}
			}

		}

	}

	/**
	 * {@inheritDoc}
	 */
	public Object getAdapter(final Object adaptableObject, final Class adapterType) {
		if (adaptableObject instanceof ScaWaveformsContainerItemProvider) {
			if (adapterType == IRefreshable.class) {
				return new Refresher((ScaDomainManager) ((ScaWaveformsContainerItemProvider) adaptableObject).getTarget());
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public Class< ? >[] getAdapterList() {
		return ScaWaveformsContainerItemProviderAdapterFactory.LIST;
	}

}
