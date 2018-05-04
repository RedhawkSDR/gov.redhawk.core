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

import gov.redhawk.model.sca.ScaPropertyContainer;
import gov.redhawk.sca.launch.internal.ScaPropertyUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.variables.IStringVariableManager;
import org.eclipse.core.variables.VariablesPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.emf.common.util.URI;

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
	 * Saves all modified properties in the {@link ScaPropertyContainer} to the {@link ILaunchConfigurationWorkingCopy}.
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
	 * Loads the properties stored in the {@link ILaunchConfiguration} into the {@link ScaPropertyContainer}.
	 * <p/>
	 * The profile of the {@link ScaPropertyContainer} should be appropriately initialized so the properties can be
	 * found and their values set.
	 * @param configuration
	 * @param component
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

	/**
	 * @since 2.0
	 */
	public static String uriToLocation(URI uri) {
		if (uri.isPlatform()) {
			return "${workspace_loc:" + uri.toPlatformString(true) + "}";
		} else if (uri.isFile()) {
			return uri.path();
		} else {
			try {
				IFileStore store = EFS.getStore(java.net.URI.create(uri.toString()));
				File localFile = store.toLocalFile(0, null);
				String sdrPathString = getValue("${SdrRoot}");
				if (localFile.getAbsolutePath().startsWith(sdrPathString)) {
					return "${SdrRoot}" + localFile.getAbsolutePath().substring(sdrPathString.length());
				}
				return localFile.getAbsolutePath();
			} catch (CoreException e) {
				throw new IllegalArgumentException("Can not convert uri to location: " + uri, e);
			}
		}
	}

	/**
	 * @since 2.0
	 */
	public static File locationToFile(String location) {
		if (location == null || location.length() < 1) {
			return null;
		}

		String expandedLocation = null;
		try {
			expandedLocation = resolveValue(location);
		} catch (CoreException e) {
			return null;
		}

		File file = new File(expandedLocation);
		if (file.exists()) {
			return file;
		}

		// Try and see if the location is a path within the workspace, this is for backwards compatibility
		// with old run configurations where the location could be workspace-relative
		IResource member = ResourcesPlugin.getWorkspace().getRoot().findMember(new Path(location));
		if (member != null && member.exists()) {
			file = new File(member.getFullPath().toOSString());
			if (file.exists()) {
				return file;
			}
		}
		return null;
	}

	/**
	 * @since 2.0
	 */
	public static URI getProfileURI(ILaunchConfiguration config) throws CoreException {
		String profile = config.getAttribute(ScaLaunchConfigurationConstants.ATT_PROFILE, (String) null);
		if (profile == null || profile.isEmpty()) {
			throw new CoreException(new Status(Status.ERROR, ScaLaunchActivator.ID, "No XML profile specified"));
		}
		File file = locationToFile(profile);
		if (file != null) {
			return URI.createFileURI(file.getAbsolutePath());
		} else {
			throw new CoreException(new Status(Status.ERROR, ScaLaunchActivator.ID, "Failed to load XML profile: " + profile));
		}
	}

	/**
	 * @since 2.0
	 */
	public static void setProfileURI(ILaunchConfigurationWorkingCopy config, URI uri) throws CoreException {
		config.setAttribute(ScaLaunchConfigurationConstants.ATT_PROFILE, uriToLocation(uri));
	}

	/**
	 * @since 2.0
	 */
	public static String resolveValue(String expression) throws CoreException {
		String expanded = null;
		try {
			expanded = getValue(expression);
		} catch (CoreException e) { // possibly just a variable that needs to be resolved at runtime
			return null;
		}
		return expanded;
	}

	/**
	 * Validates the value of the given string to determine if any/all variables are valid
	 *
	 * @param expression expression with variables
	 * @return whether the expression contained any variable values
	 * @exception CoreException if variable resolution fails
	 * @since 2.0
	 */
	public static String getValue(String expression) throws CoreException {
		IStringVariableManager manager = VariablesPlugin.getDefault().getStringVariableManager();
		return manager.performStringSubstitution(expression);
	}

}
