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
package gov.redhawk.model.sca.impl;

import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaPropertyContainer;
import gov.redhawk.model.sca.commands.ScaModelCommand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;

import CF.Device;
import CF.ExecutableDeviceHelper;
import CF.LoadableDeviceHelper;

final class DeviceContainerHelper {
	private DeviceContainerHelper() {

	}

	private static class DeviceData {
		private final Device dev;
		private final EClass deviceType;

		private DeviceData(Device dev, EClass deviceType) {
			super();
			this.dev = dev;
			this.deviceType = deviceType;
		}
	}

	public static Command fetchDevices(IProgressMonitor monitor,
	        final ScaPropertyContainer< ? , ? > container,
	        final List<ScaDevice< ? >> deviceList,
	        Device[] corbaDevices) {
		SubMonitor subMonitor = SubMonitor.convert(monitor, 2);
		final Map<String, DeviceContainerHelper.DeviceData> newDevices = new HashMap<String, DeviceContainerHelper.DeviceData>();
		if (corbaDevices != null) {
			SubMonitor deviceMonitor = subMonitor.newChild(1);
			deviceMonitor.beginTask("Init Device", corbaDevices.length);
			for (final Device dev : corbaDevices) {
				// Only Add devices to the root device manager if they are not child devices
				EClass type = ScaPackage.Literals.SCA_DEVICE;
				if (dev._is_a(ExecutableDeviceHelper.id())) {
					type = ScaPackage.Literals.SCA_EXECUTABLE_DEVICE;
				} else if (dev._is_a(LoadableDeviceHelper.id())) {
					type = ScaPackage.Literals.SCA_LOADABLE_DEVICE;
				}
				newDevices.put(dev.toString(), new DeviceContainerHelper.DeviceData(dev, type));
			}
		} else {
			subMonitor.setWorkRemaining(1);
		}

		return new ScaModelCommand() {

			public void execute() {
				DeviceContainerHelper.mergeDevices(deviceList, newDevices);
			}

		};
	}

	private static void mergeDevices(final List<ScaDevice< ? >> deviceList, final Map<String, DeviceData> newDevices) {
		// END GENERATED CODE
		// Perform Actions	
		// Setup Current Device List
		final Map<String, ScaDevice< ? >> scaDevices = new HashMap<String, ScaDevice< ? >>();
		for (final ScaDevice< ? > device : deviceList) {
			scaDevices.put(device.getIor(), device);
		}

		// Setup Remove devices list that have been deleted
		final Map<String, ScaDevice< ? >> removeDevices = new HashMap<String, ScaDevice< ? >>();
		removeDevices.putAll(scaDevices);
		removeDevices.keySet().removeAll(newDevices.keySet());

		// Remove Duplicates
		newDevices.keySet().removeAll(scaDevices.keySet());

		// Remove Devices
		deviceList.removeAll(removeDevices.values());

		// Add devices
		for (DeviceData dev : newDevices.values()) {
			ScaDevice< ? > newDevice = (ScaDevice< ? >) ScaFactory.eINSTANCE.create(dev.deviceType);
			deviceList.add(newDevice);
			newDevice.setCorbaObj(dev.dev);
		}
		
		// Do this to ensure we always set the list
		if (newDevices.isEmpty() && deviceList.isEmpty()) {
			deviceList.clear();
		}
	}
}
