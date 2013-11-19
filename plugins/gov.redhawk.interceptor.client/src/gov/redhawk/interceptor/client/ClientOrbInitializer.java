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
package gov.redhawk.interceptor.client;

import org.omg.CORBA.LocalObject;
import org.omg.CORBA.Object;
import org.omg.PortableInterceptor.ClientRequestInterceptor;
import org.omg.PortableInterceptor.Current;
import org.omg.PortableInterceptor.CurrentHelper;
import org.omg.PortableInterceptor.ORBInitInfo;
import org.omg.PortableInterceptor.ORBInitializer;

/**
 * 
 */
public class ClientOrbInitializer extends LocalObject implements ORBInitializer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void pre_init(final ORBInitInfo info) {
		// Do Nothing
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void post_init(final ORBInitInfo info) {
		try {
			// Get a reference to TSC PICurrent
			final Object obj = info.resolve_initial_references("PICurrent");
			final Current piCurrent = CurrentHelper.narrow(obj);

			/**
			 * Allocate a slot id to use for the interceptor to indicate
			 * that it is making an outcall. This is used to avoid
			 * infinite recursion.
			 */

			final int outCallIndicatorSlotId = info.allocate_slot_id();

			/**
			 * Create (with the above data) and register the client side interceptor
			 */
			final ClientRequestInterceptor interceptor = new ClientInterceptor(piCurrent, outCallIndicatorSlotId);

			info.add_client_request_interceptor(interceptor);
		} catch (final Throwable e) { // SUPPRESS CHECKSTYLE Logged Catch all exception
			throw new RuntimeException(e);
		}
	}

}
