/*******************************************************************************
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.ui.port.nxmplot.preferences;

import gov.redhawk.ui.port.nxmplot.PlotSettings.PlotMode;

import java.util.List;

/**
 * @since 4.4
 */
public class PlotPreferences {
	public static final Preference<Boolean> ENABLE_CONFIGURE_MENU_USING_MOUSE = new Preference<Boolean>("plotSettings.enablePlotConfigureMenuUsingMouse", false);
	public static final Preference<Boolean> ENABLE_QUICK_CONTROLS = new Preference<Boolean>("plotSettings.enableQuickControls", false);
	public static final Preference<String>  MODE = new Preference<String>("plotSettings.mode", PlotMode.AUTO.toString());
	public static final Preference<Integer> FRAMESIZE = new Preference<Integer>("plotSettings.framesize", 1024);
	public static final Preference<Boolean> FRAMESIZE_OVERRIDE = new Preference<Boolean>("plotSettings.framesize.override", false);
	public static final Preference<Integer> PIPESIZE = new Preference<Integer>("plotSettings.pipeSize", 131072);
	public static final Preference<Boolean> PIPESIZE_OVERRIDE = new Preference<Boolean>("plotSettings.pipeSize.override", false);
	public static final Preference<Integer> LINE_PLOT_CONSUMELENGTH = new Preference<Integer>("plotSettings.linePlotConsumeLength", -1);
	public static final Preference<Boolean> LINE_PLOT_CONSUMELENGTH_OVERRIDE = new Preference<Boolean>("plotSettings.linePlotConsumeLength.override", false);
	/** @since 5.0 */
	public static final Preference<Integer> REFRESH_RATE = new Preference<Integer>("plotSettings.refreshRate", 30);
	/** @since 5.0 */
	public static final Preference<Boolean> REFRESH_RATE_OVERRIDE = new Preference<Boolean>("plotSettings.refreshRate.override", false);
	/** @since 5.0 */
	public static final Preference<Boolean> DISABLE_CENTERFREQ_KEYWORDS = new Preference<Boolean>("plotSettings.disableCenterFreq.keywords", false);
	/** @since 5.0 */
	public static final Preference<Double>  CENTERFREQ = new Preference<Double>("plotSettings.centerFreq", -1.0);
	/** @since 5.0 */
	public static final Preference<Boolean> CENTERFREQ_OVERRIDE = new Preference<Boolean>("plotSettings.Freq.override", false);

	public static final Preference<Double>  MIN = new Preference<Double>("plotSettings.min", 0.0);
	public static final Preference<Boolean> MIN_OVERRIDE = new Preference<Boolean>("plotSettings.min.override", false);
	public static final Preference<Double>  MAX = new Preference<Double>("plotSettings.max", 100.0);
	public static final Preference<Boolean> MAX_OVERRIDE = new Preference<Boolean>("plotSettings.max.override", false);

	public static final Preference<String>  LAUNCH_ARGS = new Preference<String>("plotSettings.launchArgs", "");
	public static final Preference<Boolean> LAUNCH_ARGS_OVERRIDE = new Preference<Boolean>("plotSettings.launchArgs.override", false);
	public static final Preference<String>  LAUNCH_SWITCHES = new Preference<String>("plotSettings.launchSwitches", "");
	public static final Preference<Boolean> LAUNCH_SWITCHES_OVERRIDE = new Preference<Boolean>("plotSettings.launchSwitches.override", false);

	//	public static final Preference<String> TYPE = new Preference<String>("plotSettings.type", PlotType.LINE.toString());

	/** prevent instantiation as this class only contains constants and/or utility methods. */
	private PlotPreferences() {
	}
	
	public static List<Preference< ? >> getAllPreferences() {
		return Preference.gettAllPreferencesFor(PlotPreferences.class);
	}

}
