/*******************************************************************************
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.bulkio.util;

import org.omg.PortableServer.POA;
import org.omg.PortableServer.Servant;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import BULKIO.dataCharHelper;
import BULKIO.dataCharOperations;
import BULKIO.dataCharPOATie;
import BULKIO.dataDoubleHelper;
import BULKIO.dataDoubleOperations;
import BULKIO.dataDoublePOATie;
import BULKIO.dataFloatHelper;
import BULKIO.dataFloatOperations;
import BULKIO.dataFloatPOATie;
import BULKIO.dataLongHelper;
import BULKIO.dataLongLongHelper;
import BULKIO.dataLongLongOperations;
import BULKIO.dataLongLongPOATie;
import BULKIO.dataLongOperations;
import BULKIO.dataOctetHelper;
import BULKIO.dataOctetOperations;
import BULKIO.dataOctetPOATie;
import BULKIO.dataShortHelper;
import BULKIO.dataShortOperations;
import BULKIO.dataShortPOATie;
import BULKIO.dataUlongHelper;
import BULKIO.dataUlongLongHelper;
import BULKIO.dataUlongLongOperations;
import BULKIO.dataUlongLongPOATie;
import BULKIO.dataUlongOperations;
import BULKIO.dataUlongPOATie;
import BULKIO.dataUshortHelper;
import BULKIO.dataUshortOperations;
import BULKIO.dataUshortPOATie;
import BULKIO.updateSRIOperations;

public enum BulkIOType {
	DOUBLE(8, double.class, false), 
	FLOAT(4, float.class, false), 
	LONG(4, int.class, false), 
	ULONG(4, int.class, true),
	LONG_LONG(8, long.class, false),
	ULONG_LONG(8, long.class, true),
	SHORT(2, short.class, false),
	USHORT(2, short.class, true),
	CHAR(2, char.class, false),
	OCTET(1, byte.class, false);

	private final int bytePerAtom;
	private final Class< ? > javaType;
	private final boolean unsigned;

	private BulkIOType(int bytePerAtom, Class< ? > javaType, boolean unsigned) {
		this.bytePerAtom = bytePerAtom;
		this.javaType = javaType;
		this.unsigned = unsigned;
	}

	public org.omg.CORBA.Object createRef(POA poa, Object obj) throws ServantNotActive, WrongPolicy {
		Servant tie;
		switch (this) {
		case CHAR:
			tie = new dataCharPOATie((dataCharOperations) obj);
			break;
		case DOUBLE:
			tie = new dataDoublePOATie((dataDoubleOperations) obj);
			break;
		case FLOAT:
			tie = new dataFloatPOATie((dataFloatOperations) obj);
			break;
		case LONG:
			tie = new dataLongLongPOATie((dataLongLongOperations) obj);
			break;
		case LONG_LONG:
			tie = new dataLongLongPOATie((dataLongLongOperations) obj);
			break;
		case OCTET:
			tie = new dataOctetPOATie((dataOctetOperations) obj);
			break;
		case SHORT:
			tie = new dataShortPOATie((dataShortOperations) obj);
			break;
		case ULONG:
			tie = new dataUlongPOATie((dataUlongOperations) obj);
			break;
		case ULONG_LONG:
			tie = new dataUlongLongPOATie((dataUlongLongOperations) obj);
			break;
		case USHORT:
			tie = new dataUshortPOATie((dataUshortOperations) obj);
			break;
		default:
			throw new IllegalArgumentException("Invalid type: " + this);
		}
		return poa.servant_to_reference(tie);
	}

	public int getBytePerAtom() {
		return bytePerAtom;
	}

	public static BulkIOType getType(updateSRIOperations impl) {
		BulkIOType retVal = null;
		if (dataCharOperations.class.isAssignableFrom(impl.getClass())) {
			retVal = BulkIOType.CHAR;
		}
		if (dataDoubleOperations.class.isAssignableFrom(impl.getClass())) {
			if (retVal != null) {
				throw new IllegalArgumentException(impl.getClass() + " implements more than more type of BulkIO Interface.");
			}
			retVal = BulkIOType.DOUBLE;
		}
		if (dataFloatOperations.class.isAssignableFrom(impl.getClass())) {
			if (retVal != null) {
				throw new IllegalArgumentException(impl.getClass() + " implements more than more type of BulkIO Interface.");
			}
			retVal = BulkIOType.FLOAT;
		}
		if (dataLongLongOperations.class.isAssignableFrom(impl.getClass())) {
			if (retVal != null) {
				throw new IllegalArgumentException(impl.getClass() + " implements more than more type of BulkIO Interface.");
			}
			retVal = BulkIOType.LONG_LONG;
		}
		if (dataLongOperations.class.isAssignableFrom(impl.getClass())) {
			if (retVal != null) {
				throw new IllegalArgumentException(impl.getClass() + " implements more than more type of BulkIO Interface.");
			}
			retVal = BulkIOType.LONG;
		}
		if (dataOctetOperations.class.isAssignableFrom(impl.getClass())) {
			if (retVal != null) {
				throw new IllegalArgumentException(impl.getClass() + " implements more than more type of BulkIO Interface.");
			}
			retVal = BulkIOType.OCTET;
		}
		if (dataShortOperations.class.isAssignableFrom(impl.getClass())) {
			if (retVal != null) {
				throw new IllegalArgumentException(impl.getClass() + " implements more than more type of BulkIO Interface.");
			}
			retVal = BulkIOType.SHORT;
		}
		if (dataUlongLongOperations.class.isAssignableFrom(impl.getClass())) {
			if (retVal != null) {
				throw new IllegalArgumentException(impl.getClass() + " implements more than more type of BulkIO Interface.");
			}
			retVal = BulkIOType.ULONG_LONG;
		}
		if (dataUlongOperations.class.isAssignableFrom(impl.getClass())) {
			if (retVal != null) {
				throw new IllegalArgumentException(impl.getClass() + " implements more than more type of BulkIO Interface.");
			}
			retVal = BulkIOType.ULONG;
		}
		if (dataUshortOperations.class.isAssignableFrom(impl.getClass())) {
			if (retVal != null) {
				throw new IllegalArgumentException(impl.getClass() + " implements more than more type of BulkIO Interface.");
			}
			retVal = BulkIOType.USHORT;
		}
		if (retVal == null) {
			throw new IllegalArgumentException("Port type not supported: " + impl.getClass());
		}
		return retVal;
	}

	public static BulkIOType getType(String idl) {
		if (dataCharHelper.id().equals(idl)) {
			return BulkIOType.CHAR;
		} else if (dataDoubleHelper.id().equals(idl)) {
			return BulkIOType.DOUBLE;
		} else if (dataFloatHelper.id().equals(idl)) {
			return BulkIOType.FLOAT;
		} else if (dataLongHelper.id().equals(idl)) {
			return BulkIOType.LONG;
		} else if (dataLongLongHelper.id().equals(idl)) {
			return BulkIOType.LONG_LONG;
		} else if (dataOctetHelper.id().equals(idl)) {
			return BulkIOType.OCTET;
		} else if (dataShortHelper.id().equals(idl)) {
			return BulkIOType.SHORT;
		} else if (dataUlongHelper.id().equals(idl)) {
			return BulkIOType.ULONG;
		} else if (dataUlongLongHelper.id().equals(idl)) {
			return BulkIOType.ULONG_LONG;
		} else if (dataUshortHelper.id().equals(idl)) {
			return BulkIOType.USHORT;
		} else {
			throw new IllegalArgumentException("Unknown type: " + idl);
		}
	}

	/**
	 * @return The non upcasted Java class container type
	 */
	public Class< ? > getJavaType() {
		return this.javaType;
	}
	
	public boolean isUnsigned() {
		return this.unsigned;
	}
}
