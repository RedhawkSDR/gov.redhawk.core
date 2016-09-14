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
package gov.redhawk.core.graphiti.dcd.ui.diagram.providers;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;

public class DeviceManagerImageProvider extends AbstractImageProvider {

	private static final String PREFIX = "gov.redhawk.core.graphiti.dcd.ui.DeviceManagerImageProvider.";

	public static final String IMG_DEVICE = PREFIX + "device";
	public static final String IMG_SERVICE = PREFIX + "service";

	@Override
	protected void addAvailableImages() {
		addImageFilePath(IMG_DEVICE, "icons/Device.gif");
		addImageFilePath(IMG_SERVICE, "icons/Service.gif");
	}
}
