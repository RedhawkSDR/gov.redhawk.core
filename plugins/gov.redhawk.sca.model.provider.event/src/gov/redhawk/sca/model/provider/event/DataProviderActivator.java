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
package gov.redhawk.sca.model.provider.event;

import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.sca.model.provider.event.internal.listener.EventJob;
import gov.redhawk.sca.util.ScopedPreferenceAccessor;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.framework.BundleContext;

public class DataProviderActivator extends Plugin {

	public static final String ID = "gov.redhawk.sca.model.provider.event";

	private static DataProviderActivator instance;

	private final ScopedPreferenceAccessor preferenceAccessor = new ScopedPreferenceAccessor(InstanceScope.INSTANCE, ScaModelPlugin.ID);

	@Override
	public void start(final BundleContext context) throws Exception {
		DataProviderActivator.instance = this;
		super.start(context);
	}

	@Override
	public void stop(final BundleContext context) throws Exception {
		Job.getJobManager().cancel(EventJob.EVENT_DATA_PROVIDER_FAMILY);
		super.stop(context);
		DataProviderActivator.instance = null;
	}

	public static DataProviderActivator getInstance() {
		return DataProviderActivator.instance;
	}

	public ScopedPreferenceAccessor getPreferenceAccessor() {
		return this.preferenceAccessor;
	}

}
