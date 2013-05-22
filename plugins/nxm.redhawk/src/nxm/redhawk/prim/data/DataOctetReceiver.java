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
import BULKIO.dataOctetOperations;
import BULKIO.dataOctetPOATie;

/**
 * 
 */
public class DataOctetReceiver extends BaseBulkIOReceiver implements dataOctetOperations {

	public DataOctetReceiver(final corbareceiver receiver) {
		super(receiver);
	}

	/**
	 * {@inheritDoc}
	 */
	public void pushPacket(final byte[] dataArray, final PrecisionUTCTime time, final boolean endOfStream, final String arg3) {
		final short[] data = UnsignedUtils.toSigned(dataArray);
		getReceiver().write(data, data.length, DataTypes.INT, endOfStream, time);
	}

	@Override
	public char getType() {
		return 'I';
	}

	@Override
	public Servant toServant() {
		return new dataOctetPOATie(this);
	}
}
