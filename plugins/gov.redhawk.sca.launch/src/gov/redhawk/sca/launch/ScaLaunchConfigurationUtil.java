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
package gov.redhawk.sca.launch;

import gov.redhawk.model.sca.ScaAbstractComponent;
import gov.redhawk.model.sca.ScaPropertyContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;

import CF.DeviceAssignmentType;

public final class ScaLaunchConfigurationUtil {
	private ScaLaunchConfigurationUtil() {

	}

	public static void saveDeviceAssignment(final ILaunchConfigurationWorkingCopy wc, final DeviceAssignmentType[] deviceAssn) {
		final Map<String, String> deviceAssignment = new HashMap<String, String>();
		for (final Iterator<Entry<String, String>> i = deviceAssignment.entrySet().iterator(); i.hasNext();) {
			if (i.next().getValue() == null) {
				i.remove();
			}
		}
		if (deviceAssignment.isEmpty()) {
			wc.removeAttribute(ScaLaunchConfigurationConstants.ATT_WAVEFORM_DEVICE_ASSIGNMENT);
		} else {
			wc.setAttribute(ScaLaunchConfigurationConstants.ATT_WAVEFORM_DEVICE_ASSIGNMENT, deviceAssignment);
		}
	}

	public static DeviceAssignmentType[] loadDeviceAssignment(final ILaunchConfiguration configuration) throws CoreException {
		final List<DeviceAssignmentType> retVal = new ArrayList<DeviceAssignmentType>();
		@SuppressWarnings("unchecked")
		final Map<String, String> deviceAssignments = configuration.getAttribute(ScaLaunchConfigurationConstants.ATT_WAVEFORM_DEVICE_ASSIGNMENT,
			(Map<String, String>) Collections.EMPTY_MAP);
		for (final Map.Entry<String, String> entry : deviceAssignments.entrySet()) {
			if (entry.getValue().length() > 0) {
				retVal.add(new DeviceAssignmentType(entry.getKey(), entry.getValue()));
			}
		}
		return retVal.toArray(new DeviceAssignmentType[retVal.size()]);
	}

	/**
	 * @param configuration
	 * @param component
	 */
	public static void saveProperties(final ILaunchConfigurationWorkingCopy configuration, ScaAbstractComponent< ? > component) {
		saveProperties(configuration, (ScaPropertyContainer< ? , ? >) component);
	}

	/**
	 * @since 1.1
	 */
	public static void saveProperties(final ILaunchConfigurationWorkingCopy configuration, final ScaPropertyContainer< ? , ? > component) {
		if (component == null) {
			return;
		}

		final String xml = ScaPropertyUtil.save(component);
		if (xml == null || xml.length() == 0) {
			configuration.removeAttribute(ScaLaunchConfigurationConstants.ATT_PROPERTIES);
		} else {
			configuration.setAttribute(ScaLaunchConfigurationConstants.ATT_PROPERTIES, xml);
		}

	}

	/**
	 * @param configuration
	 * @param component
	 */
	public static void loadProperties(final ILaunchConfiguration configuration, final ScaAbstractComponent< ? > component) throws CoreException {
		loadProperties(configuration, (ScaPropertyContainer< ? , ? >) component);
	}

	/**
	 * @since 1.1
	 */
	public static void loadProperties(final ILaunchConfiguration configuration, final ScaPropertyContainer< ? , ? > component) throws CoreException {
		if (component == null || configuration == null) {
			return;
		}
		final String properties = configuration.getAttribute(ScaLaunchConfigurationConstants.ATT_PROPERTIES, (String) null);
		if (properties != null) {
			ScaPropertyUtil.load(component, properties);
		}
	}

}
