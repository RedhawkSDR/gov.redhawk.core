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
package gov.redhawk.sca.rap;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;


public class ScaRapPlugin extends Plugin {

	private static ScaRapPlugin plugin;
	private static BundleContext bundleContext;
	
	public static final String PROP_SHARED_DOMAINS = "gov.redhawk.sca.sharedDomains";

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		ScaRapPlugin.bundleContext = context;
		ScaRapPlugin.plugin = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		ScaRapPlugin.bundleContext = null;
		ScaRapPlugin.plugin = null;
	}
	
	 /* Returns the shared instance.
	 * 
	 * @return the shared instance
	 */
	public static ScaRapPlugin getDefault() {
		return ScaRapPlugin.plugin;
	}
	
	public static BundleContext getBudleContext() {
		return ScaRapPlugin.bundleContext;
	}

}
