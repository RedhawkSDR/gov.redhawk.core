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

import gov.redhawk.bulkio.util.BulkIOType;

/**
 * 
 */
public final class BulkIOReceiverFactory {
	private BulkIOReceiverFactory() {

	}

	public static AbstractSriReceiver< ? > createReceiver(BulkIOType type) {
		if (type != null) {
			switch (type) {
			case CHAR:
				return new DataCharReceiver();
			case DOUBLE:
				return new DataDoubleReceiver();
			case FLOAT:
				return new DataFloatReceiver();
			case LONG:
				return new DataLongReceiver();
			case LONG_LONG:
				return new DataLongLongReceiver();
			case OCTET:
				return new DataOctetReceiver();
			case SHORT:
				return new DataShortReceiver();
			case ULONG:
				return new DataULongReceiver();
			case ULONG_LONG:
				return new DataULongLongReceiver();
			case USHORT:
				return new DataUShortReceiver();
			default:
				throw new IllegalArgumentException("Port type not supported: " + type);
			}
		} else {
			throw new IllegalArgumentException("Null BulkIO Type.");
		}
	}

}
