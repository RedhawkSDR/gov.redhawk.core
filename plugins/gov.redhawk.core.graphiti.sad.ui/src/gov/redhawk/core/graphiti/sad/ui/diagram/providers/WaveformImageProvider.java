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

	/**
	 * Maintained for SAD/waveform diagram icons for backwards compatibility purposes.
	 * @deprecated Use {@link #PREFIX}
	 */
	@Deprecated
	private static final String OLD_PREFIX = "gov.redhawk.ide.graphiti.sad.ui.diagram.providers.imageProvider.";

	@SuppressWarnings("unused")
	private static final String PREFIX = "gov.redhawk.core.graphiti.sad.ui.WaveformImageProvider.";

	// Both diagram and custom feature / pattern

	/**
	 * Image for a component.
	 */
	public static final String IMG_COMPONENT = OLD_PREFIX + "componentInstance";

	/**
	 * Image for host collocations. Note that the prefix is omitted for backwards-compatibility reasons.
	 */
	public static final String IMG_HOST_COLLOCATION = "hostCollocation";

	@Override
	protected void addAvailableImages() {
		addImageFilePath(IMG_COMPONENT, "icons/Component.gif");
		addImageFilePath(IMG_HOST_COLLOCATION, "icons/HostCollocation.gif");
	}
}
