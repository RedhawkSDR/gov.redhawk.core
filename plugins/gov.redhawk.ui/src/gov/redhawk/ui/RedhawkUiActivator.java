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
package gov.redhawk.ui;

import gov.redhawk.internal.ui.StatusUtil;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @since 2.0
 */
public class RedhawkUiActivator extends AbstractUIPlugin {

	/** The Constant IMG_HORIZONTAL. */
	public static final String IMG_HORIZONTAL = "icons/th_horizontal.gif";

	/** The Constant IMG_VERTICAL. */
	public static final String IMG_VERTICAL = "icons/th_vertical.gif";

	/** The Constant IMG_PRF_NEW_WIZBAN. */
	public static final String IMG_PRF_NEW_WIZBAN = "icons/wizban/NewPrf.gif";

	/**
     * @since 7.0
     */
	public static final String PLUGIN_ID = "gov.redhawk.ui";

	// The shared instance
	/** The plugin. */
	private static RedhawkUiActivator plugin;

	/** The form colors. */
	private FormColors formColors;

	/**
	 * The constructor.
	 */
	public RedhawkUiActivator() {
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		RedhawkUiActivator.plugin = this;
	}

	/**
	 * Gets the active page.
	 * 
	 * @return the active page
	 */
	public static IWorkbenchPage getActivePage() {
		return RedhawkUiActivator.getDefault().internalGetActivePage();
	}

	/**
	 * Internal get active page.
	 * 
	 * @return the i workbench page
	 */
	private IWorkbenchPage internalGetActivePage() {
		return getWorkbench().getActiveWorkbenchWindow().getActivePage();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stop(final BundleContext context) throws Exception {
		try {
			if (this.formColors != null) {
				this.formColors.dispose();
				this.formColors = null;
			}
		} finally {
			RedhawkUiActivator.plugin = null;
			super.stop(context);
		}
	}

	/**
	 * Gets the plugin id.
	 * 
	 * @return the plugin id
	 */
	public static String getPluginId() {
		return PLUGIN_ID;
	}

	/**
	 * Returns the shared instance.
	 * 
	 * @return the shared instance
	 */
	public static RedhawkUiActivator getDefault() {
		return RedhawkUiActivator.plugin;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initializeImageRegistry(final ImageRegistry reg) {
		reg.put(RedhawkUiActivator.IMG_HORIZONTAL, AbstractUIPlugin.imageDescriptorFromPlugin(
				RedhawkUiActivator.getPluginId(), RedhawkUiActivator.IMG_HORIZONTAL));
		reg.put(RedhawkUiActivator.IMG_VERTICAL, AbstractUIPlugin.imageDescriptorFromPlugin(
				RedhawkUiActivator.getPluginId(), RedhawkUiActivator.IMG_VERTICAL));
		reg.put(RedhawkUiActivator.IMG_PRF_NEW_WIZBAN, AbstractUIPlugin.imageDescriptorFromPlugin(
				RedhawkUiActivator.getPluginId(), RedhawkUiActivator.IMG_PRF_NEW_WIZBAN));
	}

	/**
	 * Gets the form colors.
	 * 
	 * @param display the display
	 * @return the form colors
	 */
	public FormColors getFormColors(final Display display) {
		if (this.formColors == null) {
			this.formColors = new FormColors(display);
			this.formColors.markShared();
		}
		return this.formColors;
	}

	/**
	 * Log exception.
	 * 
	 * @param e the e
	 */
	public static void logException(final Throwable e) {
		RedhawkUiActivator.logException(e, null);
	}

	/**
	 * Log exception.
	 * 
	 * @param e the e
	 * @param message the message
	 */
	public static void logException(Throwable e, String message) {
		if (e instanceof InvocationTargetException) {
			e = ((InvocationTargetException) e).getTargetException();
		}
		IStatus status = null;
		if (e instanceof CoreException) {
			status = ((CoreException) e).getStatus();
		} else {
			if (message == null) {
				message = e.getMessage();
			}
			if (message == null) {
				message = e.toString();
			}
			status = new Status(IStatus.ERROR, RedhawkUiActivator.getPluginId(), IStatus.OK, message, e);
		}
		StatusManager.getManager().handle(status, StatusManager.LOG | StatusManager.SHOW);
	}

	/**
	 * Gets the workspace.
	 * 
	 * @return the workspace
	 */
	public static IWorkspace getWorkspace() {
		return ResourcesPlugin.getWorkspace();
	}

	/**
	 * Gets the active workbench shell.
	 * 
	 * @return the active workbench shell
	 */
	public static Shell getActiveWorkbenchShell() {
		final IWorkbenchWindow window = RedhawkUiActivator.getActiveWorkbenchWindow();
		if (window != null) {
			return window.getShell();
		}
		return null;
	}

	/**
	 * Gets the active workbench window.
	 * 
	 * @return the active workbench window
	 */
	public static IWorkbenchWindow getActiveWorkbenchWindow() {
		return RedhawkUiActivator.getDefault().getWorkbench().getActiveWorkbenchWindow();
	}

	/**
	 * Logs the given throwable to the platform log, indicating the class and
	 * method from where it is being logged (this is not necessarily where it
	 * occurred). This convenience method is for internal use by the Workbench
	 * only and must not be called outside the Workbench.
	 * 
	 * @param clazz The calling class.
	 * @param methodName The calling method name.
	 * @param t The throwable from where the problem actually occurred.
	 */
	public static void log(final Class< ? > clazz, final String methodName, final Throwable t) {
		final String msg = MessageFormat.format("Exception in {0}.{1}: {2}", //$NON-NLS-1$
				new Object[] { clazz.getName(), methodName, t });
		RedhawkUiActivator.log(msg, t);
	}

	/**
	 * Logs the given message and throwable to the platform log. If you have a
	 * status object in hand call log(String, IStatus) instead. This convenience
	 * method is for internal use by the Workbench only and must not be called
	 * outside the Workbench.
	 * 
	 * @param message A high level UI message describing when the problem
	 *            happened.
	 * @param t The throwable from where the problem actually occurred.
	 */
	public static void log(final String message, final Throwable t) {
		final IStatus status = StatusUtil.newStatus(IStatus.ERROR, message, t);
		RedhawkUiActivator.log(message, status);
	}

	/**
	 * Logs the given message and status to the platform log. This convenience
	 * method is for internal use by the Workbench only and must not be called
	 * outside the Workbench.
	 * 
	 * @param message A high level UI message describing when the problem
	 *            happened. May be <code>null</code>.
	 * @param status The status describing the problem. Must not be null.
	 */
	public static void log(final String message, final IStatus status) {

		// 1FTUHE0: ITPCORE:ALL - API - Status & logging - loss of semantic info

		if (message != null) {
			RedhawkUiActivator.getDefault().getLog().log(StatusUtil.newStatus(IStatus.ERROR, message, null));
		}

		RedhawkUiActivator.getDefault().getLog().log(status);
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path.
	 * 
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(final String pluginId, final String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin(pluginId, path);
	}

	/**
	 * Add an image to the image registry
	 * 
	 * @param registry the registry to use
	 * @param imgPath the path of the image to add
	 */
	public static void addImageToRegistry(final ImageRegistry registry, final String imgPath) {
		final ImageDescriptor descriptor = RedhawkUiActivator.getImageDescriptor(
				RedhawkUiActivator.getDefault().getBundle().getSymbolicName(), imgPath);
		RedhawkUiActivator.addImageToRegistry(registry, imgPath, descriptor);
	}

	/**
	 * Register in the given ImageRegistry the ImageDescriptor using the Image's
	 * path as key.
	 * 
	 * @param registry ImageRegistry
	 * @param imgPath String
	 * @param imgDesc ImageDescriptor
	 */
	public static void addImageToRegistry(final ImageRegistry registry, final String imgPath,
			final ImageDescriptor imgDesc) {
		ImageRegistry imgRegistry = registry;
		if (imgRegistry == null) {
			imgRegistry = RedhawkUiActivator.getDefault().getImageRegistry();
		}

		imgRegistry.put(imgPath, imgDesc);
	}

	/**
	 * Get an image from the local ImageRegistry. If the given Image's path is
	 * not already registered, do it.
	 * 
	 * @param imagePath String, path and key identifying the image in the
	 *            ImageRegistry
	 * @return Image or null if nothing corresponds to the given key
	 */
	public static Image getImage(final String imagePath) {
		Image result = RedhawkUiActivator.getDefault().getImageRegistry().get(imagePath);

		if (result == null) {
			RedhawkUiActivator.addImageToRegistry(RedhawkUiActivator.getDefault().getImageRegistry(), imagePath);
			result = RedhawkUiActivator.getDefault().getImageRegistry().get(imagePath);
		}

		return result;
	}

	/**
	 * Get an ImageDescriptor from the local ImageRegistry. If the given Image's
	 * path is not already registered, do it.
	 * 
	 * @param imagePath String, path and key identifying the ImageDescriptor in
	 *            the ImageRegistry
	 * @return ImageDescriptor or null if nothing corresponds to the given key
	 */
	public static ImageDescriptor getImageDescriptor(final String imagePath) {
		ImageDescriptor result = RedhawkUiActivator.getDefault().getImageRegistry().getDescriptor(imagePath);

		if (result == null) {
			RedhawkUiActivator.addImageToRegistry(RedhawkUiActivator.getDefault().getImageRegistry(), imagePath);
			result = RedhawkUiActivator.getDefault().getImageRegistry().getDescriptor(imagePath);
		}

		return result;
	}
}
