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

import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.ScaWaveformFactory;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.commands.ScaModelCommandWithResult;
import gov.redhawk.model.sca.services.IScaDataProvider;
import gov.redhawk.sca.model.provider.event.AbstractEventChannelDataProvider;
import gov.redhawk.sca.model.provider.event.internal.EventServiceDataProviderService;
import gov.redhawk.sca.util.PluginUtil;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.omg.CORBA.Any;
import org.omg.CORBA.OBJECT_NOT_EXIST;
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
		case SourceCategoryType._SERVICE:
			break;
		default:
			break;
		}
	}

	private void handleRemoveDevice(final DomainManagementObjectRemovedEventType event) {
		for (final ScaDeviceManager manager : getContainer().getDeviceManagers()) {
			manager.fetchDevices(null, RefreshDepth.SELF);
		}
	}

	private void handleRemoveDeviceManager(final DomainManagementObjectRemovedEventType event) {
		final String sourceId = event.sourceId;
		final ScaDomainManager domain = getContainer();
		ScaModelCommand.execute(domain, () -> {
			for (final ScaDeviceManager deviceManager : domain.getDeviceManagers()) {
				if (deviceManager != null && PluginUtil.equals(deviceManager.identifier(), sourceId)) {
					domain.getDeviceManagers().remove(deviceManager);
					break;
				}
			}
		});
	}

	private void handleRemoveApplicationFactory(final DomainManagementObjectRemovedEventType event) {
		final String sourceId = event.sourceId;
		final ScaDomainManager domain = getContainer();
		ScaModelCommand.execute(domain, () -> {
			for (final ScaWaveformFactory factory : domain.getWaveformFactories()) {
				if (factory != null && PluginUtil.equals(sourceId, factory.getIdentifier())) {
					domain.getWaveformFactories().remove(factory);
					break;
				}
			}
		});
	}

	private void handleRemoveApplication(final DomainManagementObjectRemovedEventType event) {
		final ScaDomainManager domain = getContainer();
		ScaModelCommand.execute(domain, () -> {
			for (final ScaWaveform waveform : domain.getWaveforms()) {
				if (waveform != null && PluginUtil.equals(event.sourceId, waveform.getIdentifier())) {
					domain.getWaveforms().remove(waveform);
					break;
				}
			}
		});
	}

	private void handleAddEvent(final DomainManagementObjectAddedEventType event) {
		switch (event.sourceCategory.value()) {
		case SourceCategoryType._APPLICATION:
			handleAddApplicationEvent(event);
			break;
		case SourceCategoryType._APPLICATION_FACTORY:
			handleAddApplicationFactory(event);
			break;
		case SourceCategoryType._DEVICE:
			handleAddDeviceEvent(event);
			break;
		case SourceCategoryType._DEVICE_MANAGER:
			handleAddDeviceManager(event);
			break;
		case SourceCategoryType._SERVICE:
			break;
		default:
			break;
		}

	}

	private void handleAddDeviceEvent(final DomainManagementObjectAddedEventType event) {
		for (final ScaDeviceManager manager : getContainer().getDeviceManagers()) {
			manager.fetchDevices(null, RefreshDepth.SELF);
		}
	}

	private void handleAddDeviceManager(final DomainManagementObjectAddedEventType event) {
		try {
			final DeviceManager devMgrObj = DeviceManagerHelper.narrow(event.sourceIOR);
			final String ior = devMgrObj.toString();
			final ScaDomainManager domain = getContainer();
			final ScaDeviceManager newDeviceManager = ScaModelCommandWithResult.execute(domain, () -> {
				for (final ScaDeviceManager deviceManager : domain.getDeviceManagers()) {
					if (deviceManager != null && PluginUtil.equals(ior, deviceManager.getIor())) {
						return null;
					}
				}
				final ScaDeviceManager retVal = ScaFactory.eINSTANCE.createScaDeviceManager();
				retVal.setCorbaObj(devMgrObj);
				domain.getDeviceManagers().add(retVal);
				return retVal;
			});
			if (newDeviceManager != null) {
				newDeviceManager.refresh(null, RefreshDepth.SELF);
			}
		} catch (final InterruptedException | SystemException e) {
			// PASS
		}
	}

	private void handleAddApplicationFactory(final DomainManagementObjectAddedEventType event) {
		try {
			final ApplicationFactory appFactoryObj = ApplicationFactoryHelper.narrow(event.sourceIOR);
			final String ior = appFactoryObj.toString();
			final ScaDomainManager domain = getContainer();
			final ScaWaveformFactory newWaveformFactory = ScaModelCommandWithResult.execute(domain, () -> {
				for (final ScaWaveformFactory factory : domain.getWaveformFactories()) {
					if (factory != null && PluginUtil.equals(ior, factory.getIor())) {
						return null;
					}
				}
				final ScaWaveformFactory retVal = ScaFactory.eINSTANCE.createScaWaveformFactory();
				retVal.setCorbaObj(appFactoryObj);
				domain.getWaveformFactories().add(retVal);
				return retVal;
			});
			if (newWaveformFactory != null && !newWaveformFactory.isDisposed()) {
				newWaveformFactory.refresh(null, RefreshDepth.SELF);
			}

		} catch (final OBJECT_NOT_EXIST | InterruptedException e) {
			// PASS
		}
	}

	private void handleAddApplicationEvent(final DomainManagementObjectAddedEventType event) {
		try {
			final Application appObj = ApplicationHelper.narrow(event.sourceIOR);
			final String ior = appObj.toString();
			final ScaDomainManager domain = getContainer();
			final ScaWaveform newWaveform = ScaModelCommandWithResult.execute(domain, () -> {
				for (final ScaWaveform w : domain.getWaveforms()) {
					if (w != null && PluginUtil.equals(ior, w.getIor())) {
						return null;
					}
				}
				final ScaWaveform retVal = ScaFactory.eINSTANCE.createScaWaveform();
				retVal.setCorbaObj(appObj);
				domain.getWaveforms().add(retVal);
				return retVal;
			});
			if (newWaveform != null) {
				newWaveform.refresh(null, RefreshDepth.SELF);
			}
		} catch (final InterruptedException | SystemException e) {
			// PASS if the object does not exist ignore the add request
		}
	}

}
