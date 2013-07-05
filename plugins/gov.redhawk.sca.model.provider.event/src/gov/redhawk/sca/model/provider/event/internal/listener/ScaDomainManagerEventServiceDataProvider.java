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
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.ScaWaveformFactory;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.commands.ScaModelCommandWithResult;
import gov.redhawk.model.sca.services.IScaDataProvider;
import gov.redhawk.sca.model.provider.event.AbstractEventChannelDataProvider;
import gov.redhawk.sca.util.PluginUtil;

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

/**
 * 
 */
public class ScaDomainManagerEventServiceDataProvider extends AbstractEventChannelDataProvider<ScaDomainManager> implements IScaDataProvider {

	public ScaDomainManagerEventServiceDataProvider(final ScaDomainManager domain) {
		super(domain, domain);
		addChannel(domain.getName() + ".ODM_Channel");
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
			manager.fetchDevices(null);
		}
	}

	private void handleRemoveDeviceManager(final DomainManagementObjectRemovedEventType event) {
		final String sourceId = event.sourceId;
		final ScaDomainManager domain = getContainer();
		ScaModelCommand.execute(domain, new ScaModelCommand() {
			public void execute() {
				for (final ScaDeviceManager deviceManager : domain.getDeviceManagers()) {
					if (deviceManager != null && PluginUtil.equals(deviceManager.identifier(), sourceId)) {
						domain.getDeviceManagers().remove(deviceManager);
						break;
					}
				}

			}

		});
	}

	private void handleRemoveApplicationFactory(final DomainManagementObjectRemovedEventType event) {
		final String sourceId = event.sourceId;
		final ScaDomainManager domain = getContainer();
		ScaModelCommand.execute(domain, new ScaModelCommand() {
			public void execute() {
				for (final ScaWaveformFactory factory : domain.getWaveformFactories()) {
					if (factory != null && PluginUtil.equals(sourceId, factory.getIdentifier())) {
						domain.getWaveformFactories().remove(factory);
						break;
					}
				}
			}

		});
	}

	private void handleRemoveApplication(final DomainManagementObjectRemovedEventType event) {
		final ScaDomainManager domain = getContainer();
		ScaModelCommand.execute(domain, new ScaModelCommand() {
			public void execute() {
				for (final ScaWaveform waveform : domain.getWaveforms()) {
					if (waveform != null && PluginUtil.equals(event.sourceId, waveform.getIdentifier())) {
						domain.getWaveforms().remove(waveform);
						break;
					}
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
			manager.fetchDevices(null);
		}
	}

	private void handleAddDeviceManager(final DomainManagementObjectAddedEventType event) {
		try {
			final DeviceManager devMgr = DeviceManagerHelper.narrow(event.sourceIOR);
			final String ior = devMgr.toString();
			final ScaDomainManager domain = getContainer();
			final ScaDeviceManager newDeviceManager = ScaModelCommandWithResult.execute(domain, new ScaModelCommandWithResult<ScaDeviceManager>() {
				public void execute() {
					for (final ScaDeviceManager deviceManager : domain.getDeviceManagers()) {
						if (deviceManager != null && PluginUtil.equals(ior, deviceManager.getIor())) {
							return;
						}
					}
					final ScaDeviceManager newScaDeviceManager = ScaFactory.eINSTANCE.createScaDeviceManager();
					newScaDeviceManager.setCorbaObj(devMgr);
					domain.getDeviceManagers().add(newScaDeviceManager);
					setResult(newScaDeviceManager);
				}

			});
			if (newDeviceManager != null) {
				newDeviceManager.refresh(null, RefreshDepth.SELF);
			}
		} catch (final org.omg.CORBA.OBJECT_NOT_EXIST e) {
			// PASS if the object does not exist ignore the add request
		} catch (final InterruptedException e) {
			// PASS
		} catch (final SystemException e) {
			// PASS
		}
	}

	private void handleAddApplicationFactory(final DomainManagementObjectAddedEventType event) {
		try {
			final ApplicationFactory appFactory = ApplicationFactoryHelper.narrow(event.sourceIOR);
			final String ior = appFactory.toString();
			final ScaDomainManager domain = getContainer();
			final ScaWaveformFactory newWaveformFactory = ScaModelCommandWithResult.execute(domain, new ScaModelCommandWithResult<ScaWaveformFactory>() {
				public void execute() {
					for (final ScaWaveformFactory factory : domain.getWaveformFactories()) {
						if (factory != null && PluginUtil.equals(ior, factory.getIor())) {
							return;
						}
					}
					final ScaWaveformFactory newWaveformFactory = ScaFactory.eINSTANCE.createScaWaveformFactory();
					newWaveformFactory.setCorbaObj(appFactory);
					domain.getWaveformFactories().add(newWaveformFactory);
					setResult(newWaveformFactory);
				}

			});
			if (newWaveformFactory != null && !newWaveformFactory.isDisposed()) {
				newWaveformFactory.refresh(null, RefreshDepth.SELF);
			}

		} catch (final org.omg.CORBA.OBJECT_NOT_EXIST e) {
			// PASS if the object does not exist ignore the add request
		} catch (final InterruptedException e) {
			// PASS if the object does not exist ignore the add request
		}
	}

	private void handleAddApplicationEvent(final DomainManagementObjectAddedEventType event) {
		try {
			final Application app = ApplicationHelper.narrow(event.sourceIOR);
			final String ior = app.toString();
			final ScaDomainManager domain = getContainer();
			final ScaWaveform newWaveform = ScaModelCommandWithResult.execute(domain, new ScaModelCommandWithResult<ScaWaveform>() {
				public void execute() {
					for (final ScaWaveform w : domain.getWaveforms()) {
						if (w != null && PluginUtil.equals(ior, w.getIor())) {
							return;
						}
					}
					final ScaWaveform newWaveform = ScaFactory.eINSTANCE.createScaWaveform();
					newWaveform.setCorbaObj(app);
					domain.getWaveforms().add(newWaveform);
					setResult(newWaveform);
				}
			});
			if (newWaveform != null) {
				newWaveform.refresh(null, RefreshDepth.SELF);
			}
		} catch (final org.omg.CORBA.OBJECT_NOT_EXIST e) {
			// PASS if the object does not exist ignore the add request
		} catch (final SystemException e) {
			// PASS
		} catch (final InterruptedException e) {
			// PASS
		}

	}

}
