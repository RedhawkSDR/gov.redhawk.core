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
package gov.redhawk.sca.launch;

/**
 * @since 8.0
 * @noimplement This interface is not intended to be implemented by clients.
 */
public interface ScaLaunchConfigurationConstants { // SUPPRESS CHECKSTYLE Ignore

	/**
	 * Attribute to indicate the name of the domain to launch on
	 */
	public static final String ATT_DOMAIN_NAME = ScaLaunchActivator.ID + ".domainName";

	/**
	 * Attribute to indicate the path to the profile xml
	 */
	public static final String ATT_PROFILE = ScaLaunchActivator.ID + ".profile";
	/**
     * @since 1.1
     */
	public static final String ATT_WORKSPACE = ScaLaunchActivator.ID + ".workspace";
	/**
	 * Attribute to indicate the resource should be started after launching
	 */
	public static final String ATT_START = ScaLaunchActivator.ID + ".autoStart";
	public static final boolean DEFAULT_VALUE_ATT_START = false;
	/**
	 * Attribute to indicate the resource should be opened after launching
	 */
	public static final String ATT_OPEN = ScaLaunchActivator.ID + ".openEditor";
	public static final boolean DEFAULT_VALUE_ATT_OPEN = true;

	/**
	 * Attribute to indicate the exec and configure property overrides
	 */
	public static final String ATT_PROPERTIES = ScaLaunchActivator.ID + ".scaProperties";

	/**
	 * Attribute to indicate the device assignment for the waveform
	 */
	public static final String ATT_WAVEFORM_DEVICE_ASSIGNMENT = ScaLaunchActivator.ID + ".waveformDeviceAssignment";

	public static final String ID_WAVEFORM = "gov.redhawk.sca.ui.launchConfigurationType.waveform";
}
