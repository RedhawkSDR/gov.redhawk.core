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
package gov.redhawk.sca.model.provider.event.internal.listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.omg.CORBA.Any;
import org.omg.CORBA.SystemException;
import org.omg.CORBA.TypeCode;

import CF.Application;
import CF.ApplicationFactory;
import CF.ApplicationFactoryHelper;
import CF.ApplicationHelper;
import CF.DeviceManager;
import CF.DeviceManagerHelper;
import StandardEvent.DomainManagementObjectAddedEventType;
import StandardEvent.DomainManagementObjectAddedEventTypeHelper;
import StandardEvent.DomainManagementObjectRemovedEventType;
import StandardEvent.DomainManagementObjectRemovedEventTypeHelper;
import StandardEvent.SourceCategoryType;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaService;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.ScaWaveformFactory;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.commands.ScaModelCommandWithResult;
import gov.redhawk.model.sca.services.IScaDataProvider;
import gov.redhawk.sca.model.provider.event.AbstractEventChannelDataProvider;
import gov.redhawk.sca.model.provider.event.internal.EventServiceDataProviderService;
import gov.redhawk.sca.util.PluginUtil;

/**
 * Responsible for performing model updates based on notifications from the ODM_Channel associated with a domain
 * manager.
 */
public class ScaDomainManagerEventServiceDataProvider extends AbstractEventChannelDataProvider<ScaDomainManager> implements IScaDataProvider {

	/**
	 * Handles the domain manager connecting/disconnecting or being disposed
	 */
	private final Adapter domMgrListener = new AdapterImpl() {
		@Override
		public void notifyChanged(final org.eclipse.emf.common.notify.Notification msg) {
			Object feature = msg.getFeature();
			if (feature == ScaPackage.Literals.IDISPOSABLE__DISPOSED && msg.getNewBooleanValue()) {
				dispose();
			} else if (feature == ScaPackage.Literals.SCA_DOMAIN_MANAGER__CONNECTED && !msg.isTouch()) {
				if (msg.getNewBooleanValue()) {
					connectAsync();
				} else {
					disconnectAsync();
				}
			} else if (feature == ScaPackage.Literals.ISTATUS_PROVIDER__STATUS) {
				Status newVal = (Status) msg.getNewValue();
				if (newVal != null) {
					if (newVal.isOK()) {
						connectAsync();
					} else {
						disconnectAsync();
					}
				}
			}
		}
	};

	private Job refreshDevicesJob;
	private Job refreshDevMgrsJob;
	private Job refreshServicesJob;
	private Job refreshWaveformFactoriesJob;
	private Job refreshWaveformsJob;

	public ScaDomainManagerEventServiceDataProvider(final ScaDomainManager domain) {
		super(domain, domain);
		ScaModelCommand.execute(domain, () -> {
			domain.eAdapters().add(domMgrListener);
		});
		addChannel(domain.getName() + ".ODM_Channel");
	}

	@Override
	public void dispose() {
		ScaModelCommand.execute(getContainer(), () -> {
			getContainer().eAdapters().remove(domMgrListener);
		});
		for (Job job : Arrays.asList(refreshDevicesJob, refreshDevMgrsJob, refreshServicesJob, refreshWaveformFactoriesJob, refreshWaveformsJob)) {
			if (job != null) {
				job.cancel();
			}
		}
		super.dispose();
	}

	@Override
	protected void connectAsync() {
		// Don't connect if the domain manager isn't connected or if we're not enabled
		if (!getContainer().isConnected() || !isEnabled()) {
			return;
		}

		super.connectAsync();
	}

	@Override
	public String getID() {
		return EventServiceDataProviderService.ID;
	}

	@Override
	public void handleEvent(final String channelName, final Any data) {
		final TypeCode type = data.type();
		if (type.equal(DomainManagementObjectAddedEventTypeHelper.type())) {
			final DomainManagementObjectAddedEventType event = DomainManagementObjectAddedEventTypeHelper.extract(data);
			handleAddEvent(event);
		} else if (type.equal(DomainManagementObjectRemovedEventTypeHelper.type())) {
			final DomainManagementObjectRemovedEventType event = DomainManagementObjectRemovedEventTypeHelper.extract(data);
			handleRemoveEvent(event);
		}
	}

	private void handleRemoveEvent(final DomainManagementObjectRemovedEventType event) {
		switch (event.sourceCategory.value()) {
		case SourceCategoryType._APPLICATION:
			handleRemoveApplication(event);
			break;
		case SourceCategoryType._APPLICATION_FACTORY:
			handleRemoveApplicationFactory(event);
			break;
		case SourceCategoryType._DEVICE:
			handleRemoveDevice(event);
			break;
		case SourceCategoryType._DEVICE_MANAGER:
			handleRemoveDeviceManager(event);
			break;
		case SourceCategoryType._SERVICE:
			handleRemoveService(event);
			break;
		default:
			break;
		}
	}

	private void handleRemoveDevice(final DomainManagementObjectRemovedEventType event) {
		ScaModelCommand.execute(getContainer(), () -> {
			boolean missingId = false;
			for (final ScaDeviceManager devMgr : getContainer().getDeviceManagers()) {
				for (ScaDevice< ? > dev : devMgr.getRootDevices()) {
					String id = dev.identifier();
					if (PluginUtil.equals(id, event.sourceId)) {
						devMgr.getRootDevices().remove(dev);
						return;
					} else if (id == null) {
						missingId = true;
					}
				}
				for (ScaDevice< ? > dev : devMgr.getChildDevices()) {
					String id = dev.identifier();
					if (PluginUtil.equals(id, event.sourceId)) {
						devMgr.getChildDevices().remove(dev);
						return;
					} else if (id == null) {
						missingId = true;
					}
				}
			}

			// We couldn't find the device, and at least one was missing an identifier and thus could be the device
			// we're supposed to remove
			if (missingId) {
				refreshDevices();
			}
		});
	}

	/**
	 * Schedules a job to refresh all lists of devices in this domain. This method is used by both add and remove, so
	 * the refresh also fetches the identifier to make removal easier later on.
	 */
	private void refreshDevices() {
		if (refreshDevicesJob == null) {
			refreshDevicesJob = Job.create("Refresh devices lists", monitor -> {
				SubMonitor progress = SubMonitor.convert(monitor);
				List<ScaDeviceManager> devMgrs = ScaModelCommand.runExclusive(getContainer(), () -> {
					return new ArrayList<>(getContainer().getDeviceManagers());
				});
				for (final ScaDeviceManager devMgr : devMgrs) {
					if (devMgr.isSetDevices()) {
						progress.setWorkRemaining(100);
						for (ScaDevice< ? > dev : devMgr.fetchDevices(progress.newChild(10), RefreshDepth.NONE)) {
							progress.setWorkRemaining(100);
							dev.fetchIdentifier(progress.newChild(10));
						}
					}
				}
				return Status.OK_STATUS;
			});
		}
		refreshDevicesJob.schedule(250);
	}

	private void handleRemoveService(final DomainManagementObjectRemovedEventType event) {
		ScaModelCommand.execute(getContainer(), () -> {
			boolean missingName = false;
			for (final ScaDeviceManager devMgr : getContainer().getDeviceManagers()) {
				for (ScaService service : devMgr.getServices()) {
					String name = service.getName();
					if (PluginUtil.equals(name, event.sourceId)) {
						devMgr.getServices().remove(service);
						return;
					} else if (name == null) {
						missingName = true;
					}
				}
			}

			// We couldn't find the service, and at least one was missing a name and thus could be the service we're
			// supposed to remove
			if (missingName) {
				refreshServices();
			}
		});
	}

	/**
	 * Schedules a job to refresh all lists of services in this domain. This method is used by both add and remove.
	 */
	private void refreshServices() {
		if (refreshServicesJob == null) {
			refreshServicesJob = Job.create("Refresh services lists", monitor -> {
				List<ScaDeviceManager> devMgrs = ScaModelCommand.runExclusive(getContainer(), () -> {
					return new ArrayList<>(getContainer().getDeviceManagers());
				});
				for (final ScaDeviceManager devMgr : devMgrs) {
					if (devMgr.isSetServices()) {
						devMgr.fetchServices(monitor, RefreshDepth.NONE);
					}
				}
				return Status.OK_STATUS;
			});
		}
		refreshServicesJob.schedule(250);
	}

	private void handleRemoveDeviceManager(final DomainManagementObjectRemovedEventType event) {
		final ScaDomainManager domain = getContainer();
		ScaModelCommand.execute(domain, () -> {
			boolean missingId = false;
			for (final ScaDeviceManager deviceManager : domain.getDeviceManagers()) {
				String id = deviceManager.identifier();
				if (PluginUtil.equals(id, event.sourceId)) {
					domain.getDeviceManagers().remove(deviceManager);
					return;
				} else if (id == null) {
					missingId = true;
				}
			}

			// We couldn't find the device manager, and at least one was missing an identifier and thus could be the
			// device manager we're supposed to remove
			if (missingId) {
				refreshDeviceManagers();
			}
		});
	}

	/**
	 * Schedules a job to refresh the device managers when we were unable to find and remove a device manager
	 */
	private void refreshDeviceManagers() {
		if (refreshDevMgrsJob == null) {
			refreshDevMgrsJob = Job.create("Refresh device manager list", monitor -> {
				getContainer().fetchDeviceManagers(monitor, RefreshDepth.NONE);
				return Status.OK_STATUS;
			});
		}
		refreshDevMgrsJob.schedule(250);
	}

	private void handleRemoveApplicationFactory(final DomainManagementObjectRemovedEventType event) {
		final ScaDomainManager domain = getContainer();
		ScaModelCommand.execute(domain, () -> {
			boolean missingId = false;
			for (final ScaWaveformFactory factory : domain.getWaveformFactories()) {
				String id = factory.getIdentifier();
				if (PluginUtil.equals(event.sourceId, id)) {
					domain.getWaveformFactories().remove(factory);
					return;
				} else if (id == null) {
					missingId = true;
				}
			}

			// We couldn't find the waveform factory, and at least one was missing an identifier and thus could be the
			// waveform factory we're supposed to remove
			if (missingId) {
				refreshWaveformFactories();
			}
		});
	}

	/**
	 * Schedules a job to refresh the waveforms when we were unable to find and remove a waveform
	 */
	private void refreshWaveformFactories() {
		if (refreshWaveformFactoriesJob == null) {
			refreshWaveformFactoriesJob = Job.create("Refresh waveform factories list", monitor -> {
				getContainer().fetchWaveformFactories(new NullProgressMonitor(), RefreshDepth.NONE);
				return Status.OK_STATUS;
			});
		}
		refreshWaveformFactoriesJob.schedule(250);
	}

	private void handleRemoveApplication(final DomainManagementObjectRemovedEventType event) {
		final ScaDomainManager domain = getContainer();
		ScaModelCommand.execute(domain, () -> {
			boolean missingId = false;
			for (final ScaWaveform waveform : domain.getWaveforms()) {
				String id = waveform.getIdentifier();
				if (PluginUtil.equals(event.sourceId, id)) {
					domain.getWaveforms().remove(waveform);
					return;
				} else if (id == null) {
					missingId = true;
				}
			}

			// We couldn't find the waveform, and at least one was missing an identifier and thus could be the waveform
			// we're supposed to remove
			if (missingId) {
				refreshWaveforms();
			}
		});
	}

	/**
	 * Schedules a job to refresh the waveforms when we were unable to find and remove a waveform
	 */
	private void refreshWaveforms() {
		if (refreshWaveformsJob == null) {
			refreshWaveformsJob = Job.create("Refresh waveforms list", monitor -> {
				getContainer().fetchWaveforms(monitor, RefreshDepth.NONE);
				return Status.OK_STATUS;
			});
		}
		refreshWaveformsJob.schedule(250);
	}

	private void handleAddEvent(final DomainManagementObjectAddedEventType event) {
		switch (event.sourceCategory.value()) {
		case SourceCategoryType._APPLICATION:
			handleAddApplication(event);
			break;
		case SourceCategoryType._APPLICATION_FACTORY:
			handleAddApplicationFactory(event);
			break;
		case SourceCategoryType._DEVICE:
			handleAddDevice(event);
			break;
		case SourceCategoryType._DEVICE_MANAGER:
			handleAddDeviceManager(event);
			break;
		case SourceCategoryType._SERVICE:
			handleAddService(event);
			break;
		default:
			break;
		}
	}

	private void handleAddDevice(final DomainManagementObjectAddedEventType event) {
		refreshDevices();
	}

	private void handleAddService(final DomainManagementObjectAddedEventType event) {
		refreshServices();
	}

	private void handleAddDeviceManager(final DomainManagementObjectAddedEventType event) {
		final DeviceManager devMgrObj;
		final String ior;
		try {
			devMgrObj = DeviceManagerHelper.narrow(event.sourceIOR);
			ior = event.sourceIOR.toString();
		} catch (SystemException e) {
			return;
		}

		final ScaDeviceManager devMgr = ScaFactory.eINSTANCE.createScaDeviceManager();
		devMgr.setCorbaObj(devMgrObj);
		devMgr.setIdentifier(event.sourceId);
		devMgr.setLabel(event.sourceName);

		final ScaDomainManager domain = getContainer();
		final Boolean added = ScaModelCommandWithResult.execute(domain, () -> {
			if (!domain.isSetDeviceManagers()) {
				return false;
			}
			for (final ScaDeviceManager deviceManager : domain.getDeviceManagers()) {
				if (PluginUtil.equals(ior, deviceManager.getIor())) {
					return false;
				}
			}
			domain.getDeviceManagers().add(devMgr);
			return true;
		});

		if (added != null && added) {
			try {
				devMgr.refresh(new NullProgressMonitor(), RefreshDepth.SELF);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

	private void handleAddApplicationFactory(final DomainManagementObjectAddedEventType event) {
		final ApplicationFactory appFactoryObj;
		final String ior;
		try {
			appFactoryObj = ApplicationFactoryHelper.narrow(event.sourceIOR);
			ior = appFactoryObj.toString();
		} catch (SystemException e) {
			return;
		}

		final ScaWaveformFactory waveformFactory = ScaFactory.eINSTANCE.createScaWaveformFactory();
		waveformFactory.setCorbaObj(appFactoryObj);
		waveformFactory.setIdentifier(event.sourceId);
		waveformFactory.setName(event.sourceName);

		final ScaDomainManager domain = getContainer();
		final Boolean added = ScaModelCommandWithResult.execute(domain, () -> {
			if (!domain.isSetWaveformFactories()) {
				return false;
			}
			for (final ScaWaveformFactory factory : domain.getWaveformFactories()) {
				if (PluginUtil.equals(ior, factory.getIor())) {
					return false;
				}
			}
			domain.getWaveformFactories().add(waveformFactory);
			return true;
		});

		if (added != null && added) {
			try {
				waveformFactory.refresh(new NullProgressMonitor(), RefreshDepth.SELF);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

	private void handleAddApplication(final DomainManagementObjectAddedEventType event) {
		final Application appObj;
		final String ior;
		try {
			appObj = ApplicationHelper.narrow(event.sourceIOR);
			ior = appObj.toString();
		} catch (SystemException e) {
			return;
		}

		final ScaWaveform waveform = ScaFactory.eINSTANCE.createScaWaveform();
		waveform.setCorbaObj(appObj);
		waveform.setIdentifier(event.sourceId);
		waveform.setName(event.sourceName);

		final ScaDomainManager domain = getContainer();
		final Boolean added = ScaModelCommandWithResult.execute(domain, () -> {
			if (!domain.isSetWaveforms()) {
				return false;
			}
			for (final ScaWaveform w : domain.getWaveforms()) {
				if (PluginUtil.equals(ior, w.getIor())) {
					return false;
				}
			}
			domain.getWaveforms().add(waveform);
			return true;
		});

		if (added != null && added) {
			try {
				waveform.refresh(new NullProgressMonitor(), RefreshDepth.SELF);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}
