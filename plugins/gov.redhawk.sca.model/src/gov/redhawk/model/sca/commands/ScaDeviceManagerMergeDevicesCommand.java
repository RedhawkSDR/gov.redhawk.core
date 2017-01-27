/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.model.sca.commands;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.omg.CORBA.SystemException;

import CF.Device;
import CF.ExecutableDeviceHelper;
import CF.LoadableDeviceHelper;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaPackage;

/**
 * Used when updating the devices belonging to a device manager
 * 
 * @since 20.4
 */
public class ScaDeviceManagerMergeDevicesCommand extends SetStatusCommand<ScaDeviceManager> {

	protected static class DeviceData {

		private final Device deviceObject;
		private final EClass deviceType;

		public DeviceData(Device dev, EClass deviceType) {
			super();
			this.deviceObject = dev;
			this.deviceType = deviceType;
		}
	}

	private final Map<String, DeviceData> rootDevices;
	private final Map<String, DeviceData> childDevices;

	/**
	 * Creates an EMF command which will update a device manager's devices based on data from a CORBA call.
	 * <p/>
	 * Note that this method narrows CORBA objects, and makes CORBA calls to the device, and therefore may block.
	 * 
	 * @param provider The device manager to update
	 * @param devices The information from a CORBA call about the device manager's registered devices
	 * @param status The status from having fetched CORBA data
	 */
	public ScaDeviceManagerMergeDevicesCommand(ScaDeviceManager provider, Device[] devices, IStatus status) {
		super(provider, ScaPackage.Literals.SCA_DEVICE_MANAGER__DEVICES, status);
		rootDevices = new HashMap<>();
		childDevices = new HashMap<>();
		for (Device device : devices) {
			String ior = device.toString();
			DeviceData data = getDeviceData(device);
			try {
				if (device.compositeDevice() == null) {
					rootDevices.put(ior, data);
				} else {
					childDevices.put(ior, data);
				}
			} catch (SystemException e) {
				// Problem reaching device - assume root
				rootDevices.put(ior, data);
			}
		}
	}

	@Override
	public void execute() {
		super.execute();

		// If bad status, unset the devices
		if (!status.isOK()) {
			provider.unsetDevices();
			return;
		}

		process(provider.getRootDevices(), rootDevices);
		process(provider.getChildDevices(), childDevices);
	}

	private void process(EList<ScaDevice< ? >> providerDeviceList, Map<String, DeviceData> newDevicesMap) {
		// Current device map
		final Map<String, ScaDevice< ? >> currentDevices = new HashMap<>();
		for (final ScaDevice< ? > device : providerDeviceList) {
			String deviceIor = device.getIor();
			currentDevices.put(deviceIor, device);
		}

		// Devices to remove
		final Map<String, ScaDevice< ? >> removeDevices = new HashMap<>();
		removeDevices.putAll(currentDevices);
		removeDevices.keySet().removeAll(newDevicesMap.keySet());

		// Remove devices
		if (!removeDevices.isEmpty()) {
			providerDeviceList.removeAll(removeDevices.values());
		}

		// Remove duplicates
		newDevicesMap.keySet().removeAll(currentDevices.keySet());

		// Add new devices to the model
		for (final DeviceData deviceData : newDevicesMap.values()) {
			final ScaDevice< ? > device = createDevice(deviceData.deviceObject, deviceData.deviceType);
			providerDeviceList.add(device);
		}
	}

	/**
	 * Determines the type of a device, narrows it, and returns a DeviceData containing the narrowed object and the
	 * appropriate EMF class.
	 * 
	 * @param device The device to examine
	 * @return
	 */
	protected DeviceData getDeviceData(Device device) {
		if (device._is_a(ExecutableDeviceHelper.id())) {
			return new DeviceData(ExecutableDeviceHelper.unchecked_narrow(device), ScaPackage.Literals.SCA_EXECUTABLE_DEVICE);
		} else if (device._is_a(LoadableDeviceHelper.id())) {
			return new DeviceData(LoadableDeviceHelper.unchecked_narrow(device), ScaPackage.Literals.SCA_LOADABLE_DEVICE);
		}
		return new DeviceData(device, ScaPackage.Literals.SCA_DEVICE);
	}

	/**
	 * Create the appropriate SCA model object for the device, and update it based on the CORBA object provided.
	 * 
	 * @param deviceObject The {@link Device} object for the device
	 * @param deviceType The EMF class in the SCA model that corresponds to the device's type
	 * @return The newly created model object
	 */
	protected ScaDevice< ? > createDevice(org.omg.CORBA.Object deviceObject, EClass deviceType) {
		ScaDevice< ? > device = (ScaDevice< ? >) ScaFactory.eINSTANCE.create(deviceType);
		device.setCorbaObj(deviceObject);
		return device;
	}

}
