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

import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaFileStore;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.provider.ScaDeviceManagersContainerItemProvider;
import gov.redhawk.model.sca.provider.ScaWaveformFactoriesContainerItemProvider;
import gov.redhawk.model.sca.provider.ScaWaveformsContainerItemProvider;
import gov.redhawk.model.sca.util.ScaSwitch;
import gov.redhawk.sca.internal.ui.DeferredAdapterSwitch.IDeferredAdapter;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.ecore.EObject;

import CF.Device;
import ExtendedCF.QueryablePortHelper;

/**
 * 
 */
public class DeferredAdapterSwitch extends ScaSwitch<IDeferredAdapter> {
	public static final DeferredAdapterSwitch INSTANCE = new DeferredAdapterSwitch();

	private DeferredAdapterSwitch() {

	}

	public static interface IDeferredAdapter {
		public boolean isContainer();

		public boolean isSet();

		public void fetchDeferredChildren(IProgressMonitor monitor);
	}

	@Override
	public < D extends Device > IDeferredAdapter caseScaDevice(final ScaDevice<D> object) {
		return new IDeferredAdapter() {

			public boolean isSet() {
				return object.isSetChildDevices() && object.isSetPorts();
			}

			public boolean isContainer() {
				return true;
			}

			public void fetchDeferredChildren(final IProgressMonitor monitor) {
				final SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching Children...", 2);
				try {
					object.fetchAggregateDevices(subMonitor.newChild(1));
					object.fetchPorts(subMonitor.newChild(1));
				} catch (final InterruptedException e) {
					// PASS
				}
			}
		};
	}

	@Override
	public IDeferredAdapter caseScaUsesPort(final ScaUsesPort object) {
		return new IDeferredAdapter() {

			public boolean isSet() {
				return object.isSetConnections();
			}

			public boolean isContainer() {
				return object._is_a(QueryablePortHelper.id());
			}

			public void fetchDeferredChildren(final IProgressMonitor monitor) {
				object.fetchConnections(monitor);
			}
		};
	}

	@Override
	public IDeferredAdapter caseScaFileStore(final ScaFileStore object) {
		return new IDeferredAdapter() {

			public boolean isSet() {
				return object.isSetChildren();
			}

			public boolean isContainer() {
				return object.isDirectory();
			}

			public void fetchDeferredChildren(final IProgressMonitor monitor) {
				object.fetchChildren(monitor);
			}
		};
	}

	@Override
	public IDeferredAdapter caseScaWaveform(final ScaWaveform object) {
		return new IDeferredAdapter() {

			public boolean isContainer() {
				return true;
			}

			public boolean isSet() {
				return object.isSetComponents() && object.isSetPorts();
			}

			public void fetchDeferredChildren(final IProgressMonitor monitor) {
				final SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching Children...", 2);
				object.fetchComponents(subMonitor.newChild(1));
				object.fetchPorts(subMonitor.newChild(1));
			}

		};
	}

	@Override
	public IDeferredAdapter caseScaComponent(final ScaComponent object) {
		return new IDeferredAdapter() {

			public boolean isContainer() {
				return true;
			}

			public void fetchDeferredChildren(final IProgressMonitor monitor) {
				final SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching Children...", 2);
				object.fetchDevices(subMonitor.newChild(1));
				object.fetchPorts(subMonitor.newChild(1));
			}

			public boolean isSet() {
				return object.isSetDevices() && object.isSetPorts();
			}
		};
	}

	@Override
	public IDeferredAdapter caseScaDeviceManager(final ScaDeviceManager object) {
		return new IDeferredAdapter() {

			public boolean isSet() {
				return object.isSetDevices() && object.isSetPorts();
			}

			public boolean isContainer() {
				return true;
			}

			public void fetchDeferredChildren(final IProgressMonitor monitor) {
				final SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching Children...", 2);
				object.fetchDevices(subMonitor.newChild(1));
				object.fetchPorts(subMonitor.newChild(1));
			}
		};
	}

	public IDeferredAdapter caseScaDeviceManagersContainerItemProvider(final ScaDeviceManagersContainerItemProvider object) {
		return new IDeferredAdapter() {

			public boolean isContainer() {
				return true;
			}

			public boolean isSet() {
				final ScaDomainManager domMgr = (ScaDomainManager) object.getParent(null);
				return domMgr.isSetDeviceManagers();
			}

			public void fetchDeferredChildren(final IProgressMonitor monitor) {
				final ScaDomainManager domMgr = (ScaDomainManager) object.getParent(null);
				domMgr.fetchDeviceManagers(monitor);
			}

		};
	}

	public IDeferredAdapter caseScaWaveformFactoriesContainerItemProvider(final ScaWaveformFactoriesContainerItemProvider object) {
		return new IDeferredAdapter() {

			public boolean isContainer() {
				return true;
			}

			public boolean isSet() {
				final ScaDomainManager domMgr = (ScaDomainManager) object.getParent(null);
				return domMgr.isSetWaveformFactories();
			}

			public void fetchDeferredChildren(final IProgressMonitor monitor) {
				final ScaDomainManager domMgr = (ScaDomainManager) object.getParent(null);
				domMgr.fetchWaveformFactories(monitor);
			}

		};
	}

	public IDeferredAdapter caseScaWaveformsContainerItemProvider(final ScaWaveformsContainerItemProvider object) {
		return new IDeferredAdapter() {

			public boolean isContainer() {
				return true;
			}

			public boolean isSet() {
				final ScaDomainManager domMgr = (ScaDomainManager) object.getParent(null);
				return domMgr.isSetWaveforms();
			}

			public void fetchDeferredChildren(final IProgressMonitor monitor) {
				final ScaDomainManager domMgr = (ScaDomainManager) object.getParent(null);
				domMgr.fetchWaveforms(monitor);
			}

		};
	}

	public static IDeferredAdapter doSwitch(final Object obj) {
		if (obj instanceof ScaDeviceManagersContainerItemProvider) {
			return DeferredAdapterSwitch.INSTANCE.caseScaDeviceManagersContainerItemProvider((ScaDeviceManagersContainerItemProvider) obj);
		} else if (obj instanceof ScaWaveformFactoriesContainerItemProvider) {
			return DeferredAdapterSwitch.INSTANCE.caseScaWaveformFactoriesContainerItemProvider((ScaWaveformFactoriesContainerItemProvider) obj);
		} else if (obj instanceof ScaWaveformsContainerItemProvider) {
			return DeferredAdapterSwitch.INSTANCE.caseScaWaveformsContainerItemProvider((ScaWaveformsContainerItemProvider) obj);
		} else if (obj instanceof EObject) {
			return DeferredAdapterSwitch.INSTANCE.doSwitch((EObject) obj);
		} else {
			return null;
		}
	}
}
