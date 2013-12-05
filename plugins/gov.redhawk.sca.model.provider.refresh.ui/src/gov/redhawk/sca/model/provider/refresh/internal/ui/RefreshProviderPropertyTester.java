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
package gov.redhawk.sca.model.provider.refresh.internal.ui;

import gov.redhawk.model.sca.DataProviderObject;
import gov.redhawk.sca.model.provider.refresh.internal.RefreshTasker;

import org.eclipse.core.expressions.PropertyTester;

/**
 * 
 */
public class RefreshProviderPropertyTester extends PropertyTester {

	/**
	 * 
	 */
	public RefreshProviderPropertyTester() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean test(final Object receiver, final String property, final Object[] args, final Object expectedValue) {
		if (receiver instanceof DataProviderObject) {
			final DataProviderObject dataProvider = (DataProviderObject) receiver;
			final Object[] providers = dataProvider.getDataProviders().toArray();
			for (final Object provider : providers) {
				if (provider instanceof RefreshTasker) {
					return true;
				}
			}
		}
		return false;
	}

}
