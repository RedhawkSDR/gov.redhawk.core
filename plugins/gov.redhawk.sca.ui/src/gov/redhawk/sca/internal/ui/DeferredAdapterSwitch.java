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
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaFileStore;
import gov.redhawk.model.sca.ScaService;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.provider.ScaDeviceManagersContainerItemProvider;
import gov.redhawk.model.sca.provider.ScaWaveformFactoriesContainerItemProvider;
import gov.redhawk.model.sca.provider.ScaWaveformsContainerItemProvider;
import gov.redhawk.model.sca.util.ScaSwitch;
import gov.redhawk.sca.internal.ui.DeferredAdapterSwitch.IDeferredAdapter;

import org.eclipse.core.runtime.IProgressMonitor;
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
	
	private static class DeferredAdapter implements IDeferredAdapter {
		
		private final IRefreshable refreshable;
		DeferredAdapter(IRefreshable refreshable) {
			this.refreshable = refreshable;
		}

		@Override
		public boolean isContainer() {
	        return true;
        }

		@Override
		public boolean isSet() {
	        return false;
        }

		@Override
		public void fetchDeferredChildren(IProgressMonitor monitor) {
			try {
	            refreshable.refresh(monitor, RefreshDepth.CHILDREN);
            } catch (InterruptedException e) {
	            // PASS
            }
        }	
	}
	
	@Override
	public IDeferredAdapter caseScaService(ScaService object) {
		return new DeferredAdapter(object);
	}
	
	@Override
	public < D extends Device > IDeferredAdapter caseScaDevice(ScaDevice<D> object) {
		return new DeferredAdapter(object);
	}
	
	@Override
	public IDeferredAdapter caseScaUsesPort(final ScaUsesPort object) {
		return new DeferredAdapter(object) {
			@Override
			public boolean isContainer() {
				return object._is_a(QueryablePortHelper.id());
			}
		};
	}
	
	@Override
	public IDeferredAdapter caseScaFileStore(final ScaFileStore object) {
		return new IDeferredAdapter() {

			@Override
			public boolean isSet() {
				return object.isSetChildren();
			}

			@Override
			public boolean isContainer() {
				return object.isDirectory();
			}

			@Override
			public void fetchDeferredChildren(final IProgressMonitor monitor) {
				object.fetchChildren(monitor);
			}
		};
	}
	
	@Override
	public IDeferredAdapter caseScaWaveform(ScaWaveform object) {
		return new DeferredAdapter(object);
	}
	
	@Override
	public IDeferredAdapter caseScaComponent(ScaComponent object) {
		return new DeferredAdapter(object);
	}

	@Override
	public IDeferredAdapter caseScaDeviceManager(final ScaDeviceManager object) {
		return new DeferredAdapter(object);
	}

	public IDeferredAdapter caseScaDeviceManagersContainerItemProvider(final ScaDeviceManagersContainerItemProvider object) {
		return new IDeferredAdapter() {

			@Override
			public boolean isContainer() {
				return true;
			}

			@Override
			public boolean isSet() {
				final ScaDomainManager domMgr = (ScaDomainManager) object.getParent(null);
				return domMgr.isSetDeviceManagers();
			}

			@Override
			public void fetchDeferredChildren(final IProgressMonitor monitor) {
				final ScaDomainManager domMgr = (ScaDomainManager) object.getParent(null);
				domMgr.fetchDeviceManagers(monitor);
			}

		};
	}

	public IDeferredAdapter caseScaWaveformFactoriesContainerItemProvider(final ScaWaveformFactoriesContainerItemProvider object) {
		return new IDeferredAdapter() {

			@Override
			public boolean isContainer() {
				return true;
			}

			@Override
			public boolean isSet() {
				final ScaDomainManager domMgr = (ScaDomainManager) object.getParent(null);
				return domMgr.isSetWaveformFactories();
			}

			@Override
			public void fetchDeferredChildren(final IProgressMonitor monitor) {
				final ScaDomainManager domMgr = (ScaDomainManager) object.getParent(null);
				domMgr.fetchWaveformFactories(monitor);
			}

		};
	}

	public IDeferredAdapter caseScaWaveformsContainerItemProvider(final ScaWaveformsContainerItemProvider object) {
		return new IDeferredAdapter() {

			@Override
			public boolean isContainer() {
				return true;
			}

			@Override
			public boolean isSet() {
				final ScaDomainManager domMgr = (ScaDomainManager) object.getParent(null);
				return domMgr.isSetWaveforms();
			}

			@Override
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
