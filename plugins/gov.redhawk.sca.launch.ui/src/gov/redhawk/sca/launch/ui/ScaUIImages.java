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
package gov.redhawk.sca.launch.ui;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * 
 */
public final class ScaUIImages {
	private ScaUIImages() {

	}

	public static final String ICONS_PATH = "icons/"; //$NON-NLS-1$

	private static final String PATH_OBJ = ScaUIImages.ICONS_PATH + "obj16/"; //$NON-NLS-1$

	public static final ImageDescriptor DESC_MAIN_TAB = ScaUIImages.create(ScaUIImages.PATH_OBJ, "main_tab.gif"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_DEVICE_TAB = ScaUIImages.create(ScaUIImages.PATH_OBJ, "deviceAlloc_tab.gif"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_VARIABLE_TAB = ScaUIImages.create(ScaUIImages.PATH_OBJ, "variable_tab.gif"); //$NON-NLS-1$

	private static ImageDescriptor create(final String prefix, final String name) {
		return ImageDescriptor.createFromURL(ScaUIImages.makeImageURL(prefix, name));
	}

	private static URL makeImageURL(final String prefix, final String name) {
		final String path = "$nl$/" + prefix + name; //$NON-NLS-1$
		return FileLocator.find(ScaLauncherActivator.getDefault().getBundle(), new Path(path), null);
	}
}
