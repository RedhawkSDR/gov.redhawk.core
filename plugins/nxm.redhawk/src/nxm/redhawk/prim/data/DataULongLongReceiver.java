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
import nxm.sys.inc.DataTypes;

import org.omg.PortableServer.Servant;

import BULKIO.PrecisionUTCTime;
import BULKIO.dataUlongLongOperations;
import BULKIO.dataUlongLongPOATie;

/**
 * 
 */
public class DataULongLongReceiver extends BaseBulkIOReceiver implements dataUlongLongOperations {

	public DataULongLongReceiver(final corbareceiver receiver) {
		super(receiver);
	}

	/**
	 * {@inheritDoc}
	 */
	public void pushPacket(final long[] arg0, final PrecisionUTCTime time, final boolean endOfStream, final String arg3) {
		final long[] dataArray = new long[arg0.length];
		for (int i = 0; i < dataArray.length; i++) {
			if (arg0[i] >= 0) {
				dataArray[i] = arg0[i];
			} else {
				// NextMidas does not accept integer precision values outside of Long.MAX_VALUE,  therefore clip.
				dataArray[i] = Long.MAX_VALUE;
			}
		}
		getReceiver().write(dataArray, dataArray.length, DataTypes.XLONG, endOfStream, time);
	}

	@Override
	public char getType() {
		return 'X';
	}

	@Override
	public Servant toServant() {
		return new dataUlongLongPOATie(this);
	}

}
