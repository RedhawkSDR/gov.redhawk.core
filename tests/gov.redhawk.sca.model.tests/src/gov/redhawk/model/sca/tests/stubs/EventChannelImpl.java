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
package gov.redhawk.model.sca.tests.stubs;

import org.omg.CosEventChannelAdmin.ConsumerAdmin;
import org.omg.CosEventChannelAdmin.EventChannelOperations;
import org.omg.CosEventChannelAdmin.EventChannelPOA;
import org.omg.CosEventChannelAdmin.SupplierAdmin;

public class EventChannelImpl extends EventChannelPOA implements EventChannelOperations {

	public EventChannelImpl() {
	}

	@Override
	public void destroy() {
	}

	@Override
	public ConsumerAdmin for_consumers() {
		return null;
	}

	@Override
	public SupplierAdmin for_suppliers() {
		return null;
	}

}
