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
package gov.redhawk.core.graphiti.sad.ui.diagram.providers;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;

public class WaveformImageProvider extends AbstractImageProvider {

	private static final String PREFIX = "gov.redhawk.core.graphiti.sad.ui.WaveformImageProvider.";

	public static final String IMG_COMPONENT = PREFIX + "component";

	@Override
	protected void addAvailableImages() {
		addImageFilePath(IMG_COMPONENT, "icons/Component.gif");
	}
}
