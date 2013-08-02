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
package gov.redhawk.bulkio.util.internal;

import BULKIO.dataCharOperations;
import BULKIO.dataDoubleOperations;
import BULKIO.dataFloatOperations;
import BULKIO.dataLongLongOperations;
import BULKIO.dataLongOperations;
import BULKIO.dataOctetOperations;
import BULKIO.dataShortOperations;
import BULKIO.dataUlongLongOperations;
import BULKIO.dataUlongOperations;
import BULKIO.dataUshortOperations;
import BULKIO.updateSRIOperations;

/**
 * 
 */
public final class BulkIOReceiverFactory {
	private BulkIOReceiverFactory() {

	}
	
	public static AbstractSriReceiver<?> createReceiver(Class<? extends updateSRIOperations> receiver) {
		if (dataCharOperations.class.isAssignableFrom(receiver)) {
			return new DataCharReceiver();
		} else if (dataDoubleOperations.class.isAssignableFrom(receiver)) {
			return new DataDoubleReceiver();
		} else if (dataFloatOperations.class.isAssignableFrom(receiver)) {
			return new DataFloatReceiver();
		} else if (dataLongLongOperations.class.isAssignableFrom(receiver)) {
			return new DataLongLongReceiver();
		} else if (dataLongOperations.class.isAssignableFrom(receiver)) {
			return new DataLongReceiver();
		} else if (dataOctetOperations.class.isAssignableFrom(receiver)) {
			return new DataOctetReceiver();
		} else if (dataShortOperations.class.isAssignableFrom(receiver)) {
			return new DataShortReceiver();
		} else if (dataUlongLongOperations.class.isAssignableFrom(receiver)) {
			return new DataULongLongReceiver();
		} else if (dataUlongOperations.class.isAssignableFrom(receiver)) {
			return new DataULongReceiver();
		} else if (dataUshortOperations.class.isAssignableFrom(receiver)) {
			return new DataUShortReceiver();
		} else {
			throw new IllegalArgumentException("Port type not supported: " + receiver.getClass());
		}
	}

}
