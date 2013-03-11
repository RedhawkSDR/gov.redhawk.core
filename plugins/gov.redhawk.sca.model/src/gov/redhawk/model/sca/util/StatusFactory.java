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
package gov.redhawk.model.sca.util;

import java.text.MessageFormat;
import java.util.Arrays;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.omg.CORBA.SystemException;

import CF.DataType;
import CF.DeviceAssignmentType;
import CF.InvalidFileName;
import CF.InvalidProfile;
import CF.ApplicationFactoryPackage.CreateApplicationError;
import CF.ApplicationFactoryPackage.CreateApplicationInsufficientCapacityError;
import CF.ApplicationFactoryPackage.CreateApplicationRequestError;
import CF.ApplicationFactoryPackage.InvalidInitConfiguration;
import CF.DomainManagerPackage.ApplicationAlreadyInstalled;
import CF.DomainManagerPackage.ApplicationInstallationError;
import CF.LifeCyclePackage.ReleaseError;

/**
 * @since 14.0
 * 
 */
public final class StatusFactory {
	private StatusFactory() {

	}

	public static Status createStatus(final CreateApplicationError e, final String pluginId, final String appName) {
		if (e.msg != null) {
			return new Status(IStatus.ERROR, pluginId, "Failed to create application: " + appName + " " + e.msg, e);
		} else {
			return new Status(IStatus.ERROR, pluginId, "Failed to create application: " + appName, e);
		}
	}

	public static Status createStatus(final CreateApplicationRequestError e, final String pluginId, final String appName) {
		final StringBuilder builder = new StringBuilder();
		if (e.invalidAssignments != null && e.invalidAssignments.length > 0) {
			builder.append("Invalid Device types:");
			for (final DeviceAssignmentType type : e.invalidAssignments) {
				builder.append("(");
				builder.append(type.assignedDeviceId);
				builder.append(" = ");
				builder.append(type.componentId);
				builder.append(") ");
			}
		}
		return new Status(IStatus.ERROR, pluginId, "Invalid application request: " + appName + " " + builder.toString(), e);
	}

	public static Status createStatus(final InvalidInitConfiguration e, final String pluginId, final String appName) {
		final StringBuilder builder = new StringBuilder();
		if (e.invalidProperties != null && e.invalidProperties.length > 0) {
			builder.append("Invalid Properties:");
			for (final DataType type : e.invalidProperties) {
				builder.append('(');
				builder.append(type.id);
				builder.append(" = ");
				builder.append(type.value);
				builder.append(") ");
			}
		}
		return new Status(IStatus.ERROR, pluginId, "Invalid Application configuration: " + appName + " " + builder.toString(), e);
	}

	public static IStatus createStatus(final InvalidProfile e, final String pluginId, final String name) {
		return new Status(IStatus.ERROR, pluginId, "Invalid Profile configuration: " + name, e);
	}

	public static IStatus createStatus(final InvalidFileName e, final String pluginId, final String name) {
		return new Status(IStatus.ERROR, pluginId, "Invalid File Name(" + e.errorNumber + "): " + e.msg, e);
	}

	public static IStatus createStatus(final ApplicationInstallationError e, final String pluginId, final String name) {
		return new Status(IStatus.ERROR, pluginId, "Application installation error(" + e.errorNumber + "): " + e.msg, e);
	}

	public static IStatus createStatus(final ApplicationAlreadyInstalled cause, final String pluginId, final String name) {
		return new Status(IStatus.ERROR, pluginId, "Application already installed: " + name, cause);
	}

	public static IStatus createStatus(final ReleaseError e, final String pluginId, final String waveformName) {
		if (e.errorMessages != null) {
			return new Status(IStatus.ERROR, pluginId, "Failed to create application: " + waveformName + " " + Arrays.toString(e.errorMessages), e);
		} else {
			return new Status(IStatus.ERROR, pluginId, "Failed to create application: " + waveformName, e);
		}
	}

	public static IStatus createStatus(final SystemException e, final String pluginId, final String msg) {
		return new Status(IStatus.ERROR, pluginId, MessageFormat.format("Failed: CORBA System Exception. {0}", msg), e);
	}

	public static IStatus createStatus(CreateApplicationInsufficientCapacityError e, String pluginId, String waveformName) {
		if (e.msg != null) {
			return new Status(IStatus.ERROR, pluginId, "Failed to create application: " + waveformName + " " + e.msg, e);		
		} else {
			return new Status(IStatus.ERROR, pluginId, "Failed to create application: " + waveformName, e);
		}
    }
}
