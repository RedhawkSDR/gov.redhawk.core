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
import BULKIO.dataDoubleOperations;
import BULKIO.dataDoublePOATie;

/**
 * 
 */
public class DataDoubleReceiver extends AbstractSriReceiver<dataDoubleOperations> implements dataDoubleOperations {

	/**
	 * {@inheritDoc}
	 */
	public void pushPacket(final double[] array, final PrecisionUTCTime time, final boolean endOfStream, final String streamID) {
		if (!pushPacket(array.length, time, endOfStream, streamID)) {
			return;
		}
		Object[] localChildren = getChildren().toArray();
		for (final Object child : localChildren) {
			SafeRunner.run(new ISafeRunnable() {
				
				@Override
				public void run() throws Exception {
					((dataDoubleOperations) child).pushPacket(array, time, endOfStream, streamID);					
				}
				
				@Override
				public void handleException(Throwable exception) {
				}
			});
		}
	}

	@Override
	public Servant toServant() {
		return new dataDoublePOATie(this);
	}

	@Override
	public Class<dataDoubleOperations> getType() {
		return dataDoubleOperations.class;
	}

}
