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
import BULKIO.dataCharOperations;
import BULKIO.dataCharPOATie;

/**
 * 
 */
public class DataCharReceiver extends BaseBulkIOReceiver implements dataCharOperations {

	public DataCharReceiver(final corbareceiver receiver) {
		super(receiver);
	}

	/**
	 * {@inheritDoc}
	 */
	public void pushPacket(final char[] charArray, final PrecisionUTCTime time, final boolean endOfStream, final String arg3) {
		getReceiver().write(charArray, charArray.length, DataTypes.INT, endOfStream, time);
	}

	@Override
	public char getType() {
		return 'I';
	}

	@Override
	public Servant toServant() {
		return new dataCharPOATie(this);
	}

}
