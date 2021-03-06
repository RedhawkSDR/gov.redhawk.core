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
package gov.redhawk.core.graphiti.ui.diagram.providers;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;

public class ImageProvider extends AbstractImageProvider {

	// Prefixes

	/**
	 * Maintained for SAD/DCD/waveform/device manager diagram icons for backwards compatibility purposes.
	 * @deprecated Use {@link #PREFIX}
	 */
	@Deprecated
	private static final String OLD_COMMON_PREFIX = "gov.redhawk.ide.graphiti.ui.diagram.providers.imageProvider.";

	/**
	 * Maintained for SAD/waveform diagram icons for backwards compatibility purposes.
	 * @deprecated Use {@link #PREFIX}
	 */
	@Deprecated
	private static final String OLD_SAD_PREFIX = "gov.redhawk.ide.graphiti.sad.ui.diagram.providers.imageProvider.";

	/**
	 * Maintained for DCD/device manager diagram icons for backwards compatibility purposes.
	 * @deprecated Use {@link #PREFIX}
	 */
	@Deprecated
	private static final String OLD_DCD_PREFIX = "gov.redhawk.ide.graphiti.dcd.ui.diagram.providers.deviceImageProvider.";

	private static final String PREFIX = "gov.redhawk.core.graphiti.ui.ImageProvider.";

	// Diagram icons

	/**
	 * The image for an SPD file (used by components, devices, services)
	 */
	public static final String IMG_SPD = OLD_SAD_PREFIX + "componentPlacement";

	/**
	 * Same as {@link #IMG_SPD}, but for backwards-compatibility in DCD diagrams.
	 * @deprecated Use {@link #IMG_SPD}.
	 */
	@Deprecated
	private static final String IMG_SPD2 = OLD_DCD_PREFIX + "componentPlacement";

	/**
	 * The outer image for a findby
	 */
	public static final String IMG_FIND_BY = OLD_COMMON_PREFIX + "findBy";

	// Both diagram and custom feature / pattern icons

	public static final String IMG_FIND_BY_CORBA_NAME = OLD_COMMON_PREFIX + "findByCORBAName";
	public static final String IMG_FIND_BY_SERVICE = OLD_COMMON_PREFIX + "findByService";
	public static final String IMG_FIND_BY_DOMAIN_MANAGER = OLD_COMMON_PREFIX + "findByDomainManager";
	public static final String IMG_FIND_BY_FILE_MANAGER = OLD_COMMON_PREFIX + "findByFileManager";
	public static final String IMG_CONSOLE_VIEW = OLD_COMMON_PREFIX + "consoleView";

	public static final String IMG_USES_DEVICE = OLD_COMMON_PREFIX + "usesDevice";
	public static final String IMG_USES_DEVICE_FRONTEND_TUNER = OLD_COMMON_PREFIX + "usesDeviceFrontEndTuner";

	// Custom feature / pattern icons

	public static final String IMG_COLLAPSE = PREFIX + "collapse";
	public static final String IMG_CONNECTION = PREFIX + "connection";
	public static final String IMG_EXPAND = PREFIX + "expand";
	public static final String IMG_START = PREFIX + "start";
	public static final String IMG_STOP = PREFIX + "stop";

	@Override
	protected void addAvailableImages() {
		addImageFilePath(IMG_COLLAPSE, "icons/collapse.png");
		addImageFilePath(IMG_CONNECTION, "icons/connection.gif");
		addImageFilePath(IMG_EXPAND, "icons/expand.png");
		addImageFilePath(IMG_FIND_BY_CORBA_NAME, "icons/NamingService.gif");
		addImageFilePath(IMG_FIND_BY_DOMAIN_MANAGER, "icons/DomainFinder.gif");
		addImageFilePath(IMG_FIND_BY_FILE_MANAGER, "icons/DomainFinder.gif");
		addImageFilePath(IMG_FIND_BY_SERVICE, "icons/DomainFinder.gif");
		addImageFilePath(IMG_FIND_BY, "icons/FindBy.gif");
		addImageFilePath(IMG_SPD, "icons/SoftPkg.gif");
		addImageFilePath(IMG_SPD2, "icons/SoftPkg.gif");
		addImageFilePath(IMG_START, "icons/start.gif");
		addImageFilePath(IMG_STOP, "icons/stop.gif");
		addImageFilePath(IMG_USES_DEVICE, "icons/UsesDevice.gif");
		addImageFilePath(IMG_USES_DEVICE_FRONTEND_TUNER, "icons/UsesDevice.gif");
	}
}
