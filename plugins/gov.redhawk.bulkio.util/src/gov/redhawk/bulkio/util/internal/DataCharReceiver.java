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

import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.SafeRunner;
import org.omg.PortableServer.Servant;

import BULKIO.PrecisionUTCTime;
import BULKIO.dataCharOperations;
import BULKIO.dataCharPOATie;

/**
 * 
 */
public class DataCharReceiver extends AbstractSriReceiver<dataCharOperations> implements dataCharOperations {

	/**
	 * {@inheritDoc}
	 */
	public void pushPacket(final char[] array, final PrecisionUTCTime time, final boolean endOfStream, final String streamID) {
		if (!pushPacket(array.length, time, endOfStream, streamID)) {
			return;
		}
		Object[] children = getChildren().toArray();
		for (final Object child : children) {
			SafeRunner.run(new ISafeRunnable() {
				
				@Override
				public void run() throws Exception {
					((dataCharOperations) child).pushPacket(array, time, endOfStream, streamID);					
				}
				
				@Override
				public void handleException(Throwable exception) {
					
				}
			});
		}
	}

	@Override
	public Servant toServant() {
		return new dataCharPOATie(this);
	}

	@Override
	public Class<dataCharOperations> getType() {
		return dataCharOperations.class;
	}

}
