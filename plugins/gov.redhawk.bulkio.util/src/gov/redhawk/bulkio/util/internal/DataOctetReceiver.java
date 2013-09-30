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

import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.jdt.annotation.NonNull;
import org.omg.PortableServer.Servant;

import BULKIO.PrecisionUTCTime;
import BULKIO.dataOctetOperations;
import BULKIO.dataOctetPOATie;

/**
 * 
 */
public class DataOctetReceiver extends AbstractSriReceiver<dataOctetOperations> implements dataOctetOperations {
	
	public DataOctetReceiver() {
		super(BulkIOType.OCTET);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void pushPacket(final byte[] array, final PrecisionUTCTime time, final boolean endOfStream, final String streamID) {
		if (!pushPacket(array.length, time, endOfStream, streamID)) {
			return;
		}
		Object[] localChildren = getChildren().toArray();
		for (final Object child : localChildren) {
			SafeRunner.run(new ISafeRunnable() {

				@Override
				public void run() throws Exception {
					((dataOctetOperations) child).pushPacket(array, time, endOfStream, streamID);
				}

				@Override
				public void handleException(Throwable exception) {
				}
			});

		}
	}

	@Override
	@NonNull
	public Servant toServant() {
		return new dataOctetPOATie(this);
	}

	@Override
	@NonNull
	public Class<dataOctetOperations> getType() {
		return dataOctetOperations.class;
	}
}
