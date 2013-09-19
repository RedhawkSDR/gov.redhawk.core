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
package gov.redhawk.sca.ui;

import gov.redhawk.sca.ScaPlugin;
import gov.redhawk.sca.internal.ui.ResourceRegistry;
import gov.redhawk.sca.internal.ui.ScaContentTypeRegistry;
import gov.redhawk.sca.internal.ui.ScaUiModelJob;
import gov.redhawk.sca.util.ScopedPreferenceAccessor;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.rap.ui.internal.preferences.SessionScope;
import org.eclipse.rwt.RWT;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.osgi.framework.BundleContext;

// TODO: Auto-generated Javadoc
/**
 * The activator class controls the plug-in life cycle.
 */
public class ScaUiPlugin extends AbstractUIPlugin {

	// The plug-in ID
	/** The Constant PLUGIN_ID. */
	public static final String PLUGIN_ID = "gov.redhawk.sca.ui";

	// The shared instance
	/** The plugin. */
	private static ScaUiPlugin plugin;

	private IPreferenceStore scaPreferenceStore;

	/**
	 * The constructor.
	 */
	public ScaUiPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * 
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
		ScaUiPlugin.plugin = this;
	}

	@Override
	protected ImageRegistry createImageRegistry() {
		final ImageRegistry registry = super.createImageRegistry();
		registry.put(ScaUiPluginImages.IMG_DEFAULT_DOMAIN_OVR,
		        AbstractUIPlugin.imageDescriptorFromPlugin(ScaUiPlugin.PLUGIN_ID, "icons/ovr16/default_domain_ovr.gif"));
		return registry;
	}

	/**
	 * @since 3.0
	 */
	public IPreferenceStore getScaPreferenceStore() {
		if (SWT.getPlatform().startsWith("rap")) {
			Assert.isNotNull(Display.getCurrent(), "This method must be called from the UI thread");
			ScaPlugin.getDefault().getCompatibilityUtil().initializeSettingStore(Display.getCurrent());
			this.scaPreferenceStore = new ScopedPreferenceStore(new SessionScope(), ScaPlugin.getDefault().getBundle().getSymbolicName());
		} else {
			this.scaPreferenceStore = new ScopedPreferenceStore(InstanceScope.INSTANCE, ScaPlugin.getDefault().getBundle().getSymbolicName());
		}
		return this.scaPreferenceStore;
//		if (this.scaPreferenceStore == null) {
//			Assert.isNotNull(Display.getCurrent(), "This method must be called from the UI thread");
//			ScaPlugin.getDefault().getCompatibilityUtil().initializeSettingStore(Display.getCurrent());
//			if (SWT.getPlatform().startsWith("rap")) {
//				this.scaPreferenceStore = new ScopedPreferenceStore(new SessionScope(), ScaPlugin.getDefault().getBundle().getSymbolicName());
//			} else {
//				this.scaPreferenceStore = new ScopedPreferenceStore(InstanceScope.INSTANCE, ScaPlugin.getDefault().getBundle().getSymbolicName());
//			}
//		}
//		return this.scaPreferenceStore;
	}
	
	@Override
	public IPreferenceStore getPreferenceStore() {
		return getScaPreferenceStore();
	}
	
	
//	/**
//	 * @since 9.2
//	 */
//	public void setScaPreferenceStore(IScopeContext scope) {
//		this.scaPreferenceStore = new ScopedPreferenceStore(scope, ScaPlugin.getDefault().getBundle().getSymbolicName());
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stop(final BundleContext context) throws Exception {
		ScaUiPlugin.plugin = null;
		ScaUiModelJob.setShouldRun(false);
		Job.getJobManager().cancel(ScaUiModelJob.JOB_FAMILY);
		ResourceRegistry.INSTANCE.dispose();
		//		Job.getJobManager().join(ScaUiModelJob.JOB_FAMILY, null);
		super.stop(context);
	}

	/**
	 * Returns the shared instance.
	 * 
	 * @return the shared instance
	 */
	public static ScaUiPlugin getDefault() {
		return ScaUiPlugin.plugin;
	}

	/**
	 * Log error.
	 * 
	 * @param msg the msg
	 * @param e the e
	 */
	public static void logError(final String msg, final Throwable e) {
		ScaUiPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, msg, e));
	}

	/**
	 * Log error.
	 * 
	 * @param msg the msg
	 * @param e the e
	 * @since 5.0
	 */
	public static void logInfo(final String msg) {
		ScaUiPlugin.getDefault().getLog().log(new Status(IStatus.INFO, ScaUiPlugin.PLUGIN_ID, msg));
	}

	/**
	 * @since 6.0
	 */
	public static IScaContentTypeRegistry getContentTypeRegistry() {
		return ScaContentTypeRegistry.INSTANCE;
	}

	/**
	 * @since 8.0
	 */
	public ImageDescriptor getImageDescriptor(final String string) {
		ImageDescriptor desc = getImageRegistry().getDescriptor(string);
		if (desc == null) {
			desc = AbstractUIPlugin.imageDescriptorFromPlugin(ScaUiPlugin.PLUGIN_ID, string);
			if (desc != null) {
				getImageRegistry().put(string, desc);
			}
		}
		return desc;
	}

	/**
	 * @since 8.0
	 */
	public Image getImage(final String string) {
		// Ensure it is in the registry first
		getImageDescriptor(string);
		return getImageRegistry().get(string);
	}
}
