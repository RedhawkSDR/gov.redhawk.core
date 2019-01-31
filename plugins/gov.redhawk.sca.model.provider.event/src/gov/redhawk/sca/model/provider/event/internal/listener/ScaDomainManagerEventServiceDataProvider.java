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
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
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
import gov.redhawk.model.sca.ScaEventChannel;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.ScaWaveformFactory;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.commands.ScaModelCommandWithResult;
import gov.redhawk.model.sca.services.IScaDataProvider;
import gov.redhawk.sca.model.provider.event.AbstractEventChannelDataProvider;
import gov.redhawk.sca.model.provider.event.DataProviderActivator;
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
			}
		}
	};

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
		case SourceCategoryType._EVENT_CHANNEL:
			handleRemoveEventChannel(event);
			break;
		case SourceCategoryType._SERVICE:
			break;
		default:
			break;
		}
	}

	private void handleRemoveDevice(final DomainManagementObjectRemovedEventType event) {
		ScaModelCommand.execute(getContainer(), () -> {
			for (final ScaDeviceManager devMgr : getContainer().getDeviceManagers()) {
				for (ScaDevice< ? > dev : devMgr.getRootDevices()) {
					if (PluginUtil.equals(dev.identifier(), event.sourceId)) {
						devMgr.getRootDevices().remove(dev);
						return;
					}
				}
				for (ScaDevice< ? > dev : devMgr.getChildDevices()) {
					if (PluginUtil.equals(dev.identifier(), event.sourceId)) {
						devMgr.getChildDevices().remove(dev);
						return;
					}
				}
			}
		});
	}

	private void handleRemoveDeviceManager(final DomainManagementObjectRemovedEventType event) {
		final ScaDomainManager domain = getContainer();
		ScaModelCommand.execute(domain, () -> {
			for (final ScaDeviceManager deviceManager : domain.getDeviceManagers()) {
				if (PluginUtil.equals(deviceManager.identifier(), event.sourceId)) {
					domain.getDeviceManagers().remove(deviceManager);
					return;
				}
			}
		});
	}

	private void handleRemoveApplicationFactory(final DomainManagementObjectRemovedEventType event) {
		final ScaDomainManager domain = getContainer();
		ScaModelCommand.execute(domain, () -> {
			for (final ScaWaveformFactory factory : domain.getWaveformFactories()) {
				if (PluginUtil.equals(event.sourceId, factory.getIdentifier())) {
					domain.getWaveformFactories().remove(factory);
					return;
				}
			}
		});
	}

	private void handleRemoveApplication(final DomainManagementObjectRemovedEventType event) {
		final ScaDomainManager domain = getContainer();
		ScaModelCommand.execute(domain, () -> {
			for (final ScaWaveform waveform : domain.getWaveforms()) {
				if (PluginUtil.equals(event.sourceId, waveform.getIdentifier())) {
					domain.getWaveforms().remove(waveform);
					return;
				}
			}
		});
	}

	private void handleRemoveEventChannel(final DomainManagementObjectRemovedEventType event) {
		final ScaDomainManager domain = getContainer();
		ScaModelCommand.execute(domain, () -> {
			for (ScaEventChannel eventChannel : domain.getEventChannels()) {
				if (PluginUtil.equals(event.sourceId, eventChannel.getName())) {
					domain.getEventChannels().remove(eventChannel);
					return;
				}
			}
		});
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
		case SourceCategoryType._EVENT_CHANNEL:
			handleAddEventChannel(event);
			break;
		case SourceCategoryType._SERVICE:
			break;
		default:
			break;
		}
	}

	private void handleAddDevice(final DomainManagementObjectAddedEventType event) {
		List<ScaDeviceManager> devMgrs = ScaModelCommand.runExclusive(getContainer(), () -> {
			return new ArrayList<>(getContainer().getDeviceManagers());
		});
		for (final ScaDeviceManager devMgr : devMgrs) {
			if (devMgr.isSetDevices()) {
				devMgr.fetchDevices(null, RefreshDepth.SELF);
			}
		}
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
				devMgr.refresh(null, RefreshDepth.SELF);
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
				waveformFactory.refresh(null, RefreshDepth.SELF);
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
				waveform.refresh(null, RefreshDepth.SELF);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

	private void handleAddEventChannel(final DomainManagementObjectAddedEventType event) {
		if (event.sourceId == null || event.sourceId.isEmpty()) {
			IStatus status = new Status(IStatus.ERROR, DataProviderActivator.ID, "DomainManagementObjectAddedEventType for event channel did not contain a source ID");
			DataProviderActivator.getInstance().getLog().log(status);
			return;
		}
		if (event.sourceIOR == null) {
			IStatus status = new Status(IStatus.ERROR, DataProviderActivator.ID, "DomainManagementObjectAddedEventType for event channel did not contain an object (sourceIOR)");
			DataProviderActivator.getInstance().getLog().log(status);
			return;
		}

		final ScaDomainManager domain = getContainer();
		ScaEventChannel newEventChannel = ScaFactory.eINSTANCE.createScaEventChannel();
		newEventChannel.setName(event.sourceId);
		newEventChannel.setCorbaObj(event.sourceIOR);

		Boolean added = ScaModelCommandWithResult.execute(domain, () -> {
			if (!domain.isSetEventChannels()) {
				return false;
			}
			for (ScaEventChannel eventChannel : domain.getEventChannels()) {
				if (PluginUtil.equals(eventChannel.getName(), newEventChannel.getName())) {
					return false;
				}
			}
			domain.getEventChannels().add(newEventChannel);
			return true;
		});
		if (added != null && added) {
			try {
				newEventChannel.refresh(null, RefreshDepth.SELF);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

}
