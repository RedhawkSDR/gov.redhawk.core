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

import mil.jpeojtrs.sca.util.UnsignedUtils;
import nxm.redhawk.prim.corbareceiver;
import nxm.sys.inc.DataTypes;

import org.omg.PortableServer.Servant;

import BULKIO.PrecisionUTCTime;
import BULKIO.dataUshortOperations;
import BULKIO.dataUshortPOATie;

/**
 * 
 */
public class DataUShortReceiver extends BaseBulkIOReceiver implements dataUshortOperations {

	public DataUShortReceiver(final corbareceiver receiver) {
		super(receiver);
	}

	public void pushPacket(final short[] arg0, final PrecisionUTCTime time, final boolean endOfStream, final String arg3) {
		final int[] dataArray = UnsignedUtils.toSigned(arg0);
		getReceiver().write(dataArray, dataArray.length, DataTypes.LONG, endOfStream, time);
	}

	@Override
	public char getType() {
		return 'L';
	}

	@Override
	public Servant toServant() {
		return new dataUshortPOATie(this);
	}

}
