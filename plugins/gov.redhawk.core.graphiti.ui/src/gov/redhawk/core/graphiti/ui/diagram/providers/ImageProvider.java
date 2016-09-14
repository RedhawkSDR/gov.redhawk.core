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

	private static final String PREFIX = "gov.redhawk.core.graphiti.ui.ImageProvider.";

	public static final String IMG_COLLAPSE = PREFIX + "collapse";
	public static final String IMG_CONNECTION = PREFIX + "connection";
	public static final String IMG_EXPAND = PREFIX + "expand";
	public static final String IMG_SPD = PREFIX + "spd";
	public static final String IMG_START = PREFIX + "start";
	public static final String IMG_STOP = PREFIX + "stop";

	@Override
	protected void addAvailableImages() {
		addImageFilePath(IMG_COLLAPSE, "icons/collapse.png");
		addImageFilePath(IMG_CONNECTION, "icons/connection.gif");
		addImageFilePath(IMG_EXPAND, "icons/expand.png");
		addImageFilePath(IMG_SPD, "icons/SoftPkg.gif");
		addImageFilePath(IMG_START, "icons/start.gif");
		addImageFilePath(IMG_STOP, "icons/stop.gif");
	}
}
