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
package gov.redhawk.internal.ui;

import gov.redhawk.ui.RedhawkUiActivator;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

/**
 * The Class ScaPluginImages.
 */
public final class ScaPluginImages {

	/** The PLUGI n_ registry. */
	// CHECKSTYLE:OFF
	private static ImageRegistry PLUGIN_REGISTRY;

	/** The Constant ICONS_PATH. */
	public static final String ICONS_PATH = "icons/"; //$NON-NLS-1$

	/** The Constant PATH_LCL. */
	private static final String PATH_LCL = ScaPluginImages.ICONS_PATH + "elcl16/"; //$NON-NLS-1$

	/** The Constant DESC_ALPHAB_SORT_CO. */
	public static final ImageDescriptor DESC_ALPHAB_SORT_CO = ScaPluginImages.create(ScaPluginImages.PATH_LCL, "alphab_sort_co.gif"); //$NON-NLS-1$

	/** The Constant DESC_HELP. */
	//public static final ImageDescriptor DESC_HELP = ScaPluginImages.create(ScaPluginImages.PATH_LCL, "help.gif"); //$NON-NLS-1$

	// CHECKSTYLE:ON
	/**
	 * Instantiates a new sca plugin images.
	 */
	private ScaPluginImages() {

	}

	/**
	 * Creates the.
	 * 
	 * @param prefix the prefix
	 * @param name the name
	 * @return the image descriptor
	 */
	private static ImageDescriptor create(final String prefix, final String name) {
		return ImageDescriptor.createFromURL(ScaPluginImages.makeImageURL(prefix, name));
	}

	/**
	 * Gets the.
	 * 
	 * @param key the key
	 * @return the image
	 */
	public static Image get(final String key) {
		if (ScaPluginImages.PLUGIN_REGISTRY == null) {
			ScaPluginImages.initialize();
		}
		return ScaPluginImages.PLUGIN_REGISTRY.get(key);
	}

	/* package */
	/**
	 * Initialize.
	 */
	private static void initialize() {
		ScaPluginImages.PLUGIN_REGISTRY = new ImageRegistry();
	}

	/**
	 * Make image url.
	 * 
	 * @param prefix the prefix
	 * @param name the name
	 * @return the uRL
	 */
	private static URL makeImageURL(final String prefix, final String name) {
		final String path = "$nl$/" + prefix + name; //$NON-NLS-1$
		return FileLocator.find(RedhawkUiActivator.getDefault().getBundle(), new Path(path), null);
	}

	/**
	 * Manage.
	 * 
	 * @param key the key
	 * @param desc the desc
	 * @return the image
	 */
	public static Image manage(final String key, final ImageDescriptor desc) {
		final Image image = desc.createImage();
		ScaPluginImages.PLUGIN_REGISTRY.put(key, image);
		return image;
	}
}
