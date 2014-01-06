package gov.redhawk.frontend.ui;

import gov.redhawk.frontend.Tuner;
import gov.redhawk.sca.ui.ScaUiPlugin;
import gov.redhawk.sca.ui.filters.AdvancedPropertiesExtensibleFilter;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class FrontEndUIActivator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "gov.redhawk.frontend.ui"; //$NON-NLS-1$

	// The shared instance
	private static FrontEndUIActivator plugin;
	
	/**
	 * The constructor
	 */
	public FrontEndUIActivator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		if (ScaUiPlugin.getDefault() != null) {
			AdvancedPropertiesExtensibleFilter.addSubFilter(new org.eclipse.jface.viewers.IFilter() {

				@Override
				public boolean select(Object toTest) {
					if (toTest instanceof Tuner) {
						return false;
					}
					return true;
				}
				
			});
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static FrontEndUIActivator getDefault() {
		return plugin;
	}

}
