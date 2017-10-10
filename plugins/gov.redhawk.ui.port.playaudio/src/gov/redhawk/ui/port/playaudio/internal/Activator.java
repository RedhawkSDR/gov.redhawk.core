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
package gov.redhawk.ui.port.playaudio.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.progress.UIJob;
import org.osgi.framework.BundleContext;

import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.ui.port.playaudio.internal.views.PlayAudioView;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "gov.redhawk.ui.port.playaudio";

	// The shared instance
	private static Activator plugin;

	public Activator() {
	}

	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		Activator.plugin = this;
	}

	@Override
	public void stop(final BundleContext context) throws Exception {
		Activator.plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return Activator.plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path
	 * 
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(final String path) {
		ImageDescriptor desc = Activator.getDefault().getImageRegistry().getDescriptor(path);
		if (desc == null) {
			desc = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, path);
			Activator.getDefault().getImageRegistry().put(path, desc);
		}
		return desc;
	}
	
	public void playPorts(final List<ScaUsesPort> portList) {
		new UIJob("Starting Play Port") {
			@Override
			public IStatus runInUIThread(final IProgressMonitor monitor) {
				monitor.beginTask("Opening Play Port View", IProgressMonitor.UNKNOWN);
				final IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				if (page == null) {
					return new Status(IStatus.ERROR, PLUGIN_ID, "Unable to open play port view. No workbench page.");
				}

				PlayAudioView view;
				try {
					view = (PlayAudioView) page.showView(PlayAudioView.ID);
					for (ScaUsesPort port : portList) {
						view.playPort(port);
					}
				} catch (final PartInitException e) {
					getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Error finding Play Port View", e));
				}
				return Status.OK_STATUS;
			}
		}.schedule();
	}

	/**
	 * This is used to connect to an arbitrary Uses port to play audio.
	 * 
	 * @param portList the map of Ports and Names to connect
	 * @deprecated Use {@link #playPorts(List)}
	 */
	@Deprecated
	public void playPort(final Map<ScaUsesPort, String> portList) {
		playPorts(new ArrayList<>(portList.keySet()));
	}

	/**
	 * This will log the given error message
	 * 
	 * @param msg the error message to log
	 * @param e the (optional) Throwable that caused the error
	 */
	public static final void logError(final String msg, final Throwable e) {
		Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, msg, e));
	}

	/**
	 * This will log the given warning message
	 * 
	 * @param msg the warning message to log
	 * @param e the (optional) Throwable that caused the warning
	 */
	public static final void logWarn(final String msg, final Throwable e) {
		Activator.getDefault().getLog().log(new Status(IStatus.WARNING, Activator.PLUGIN_ID, msg, e));
	}
}
