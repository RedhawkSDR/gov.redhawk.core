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
package gov.redhawk.ui.views.internal.monitor.tester;

import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaProvidesPort;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.sca.util.PluginUtil;

import org.eclipse.core.expressions.PropertyTester;

import BULKIO.ProvidesPortStatisticsProviderHelper;
import BULKIO.UsesPortStatisticsProviderHelper;

/**
 * 
 */
public class MonitorPortTester extends PropertyTester {

	/**
	 * {@inheritDoc}
	 */
	public boolean test(final Object receiver, final String property, final Object[] args, final Object expectedValue) {
		final ScaPort< ? , ? > port = PluginUtil.adapt(ScaPort.class, receiver, false);
		if (port instanceof ScaUsesPort) {
			return port._is_a(UsesPortStatisticsProviderHelper.id());
		} else if (port instanceof ScaProvidesPort) {
			return port._is_a(ProvidesPortStatisticsProviderHelper.id());
		}
		return false;
	}

}
