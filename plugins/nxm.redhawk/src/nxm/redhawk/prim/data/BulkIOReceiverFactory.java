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
package nxm.redhawk.prim.data;

import nxm.redhawk.prim.corbareceiver;
import BULKIO.dataCharHelper;
import BULKIO.dataDoubleHelper;
import BULKIO.dataFloatHelper;
import BULKIO.dataLongHelper;
import BULKIO.dataLongLongHelper;
import BULKIO.dataOctetHelper;
import BULKIO.dataShortHelper;
import BULKIO.dataUlongHelper;
import BULKIO.dataUlongLongHelper;
import BULKIO.dataUshortHelper;

/**
 * 
 */
public final class BulkIOReceiverFactory {
	private BulkIOReceiverFactory() {

	}

	public static BaseBulkIOReceiver createReceiver(final corbareceiver receiver, final String portIdl) {
		if (dataCharHelper.id().equals(portIdl)) {
			return new DataCharReceiver(receiver);
		} else if (dataDoubleHelper.id().equals(portIdl)) {
			return new DataDoubleReceiver(receiver);
		} else if (dataFloatHelper.id().equals(portIdl)) {
			return new DataFloatReceiver(receiver);
		} else if (dataLongLongHelper.id().equals(portIdl)) {
			return new DataLongLongReceiver(receiver);
		} else if (dataLongHelper.id().equals(portIdl)) {
			return new DataLongReceiver(receiver);
		} else if (dataOctetHelper.id().equals(portIdl)) {
			return new DataOctetReceiver(receiver);
		} else if (dataShortHelper.id().equals(portIdl)) {
			return new DataShortReceiver(receiver);
		} else if (dataUlongLongHelper.id().equals(portIdl)) {
			return new DataULongLongReceiver(receiver);
		} else if (dataUlongHelper.id().equals(portIdl)) {
			return new DataULongReceiver(receiver);
		} else if (dataUshortHelper.id().equals(portIdl)) {
			return new DataUShortReceiver(receiver);
		} else {
			throw new IllegalArgumentException("Port type not supported: " + portIdl);
		}
	}

}
