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

import java.util.HashMap;
import java.util.Map;

import org.omg.PortableServer.Servant;

import BULKIO.dataBitHelper;
import BULKIO.dataBitOperations;
import BULKIO.dataBitPOATie;
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
import BULKIO.dataLongPOATie;
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

/**
 * This enumeration describes all BULKIO types implemented by the abstract class {@link AbstractUberBulkIOPort}.
 * <b>NOT ALL BULKIO TYPES ARE SUPPORTED.</b> In general the supported types are those that:
 * <ol>
 * <li>convey sample data</li>
 * <li>convey data in-band (via base class implementation)</li>
 * </ol>
 * So for example, dataXML isn't supported because it transmits XML not sample data. dataSDDS also isn't supported
 * because the sample data is conveyed out-of-band.
 */
public enum BulkIOType {
	/**
	 * @since 4.0
	 */
	BIT(0, byte.class, true, dataBitOperations.class, 'P'),
	CHAR(2, char.class, false, dataCharOperations.class, 'I'),
	DOUBLE(8, double.class, false, dataDoubleOperations.class, 'D'),
	FLOAT(4, float.class, false, dataFloatOperations.class, 'F'),
	LONG(4, int.class, false, dataLongOperations.class, 'L'),
	LONG_LONG(8, long.class, false, dataLongLongOperations.class, 'X'),
	OCTET(1, byte.class, false, dataOctetOperations.class, 'B'),
	SHORT(2, short.class, false, dataShortOperations.class, 'I'),
	ULONG(4, int.class, true, dataUlongOperations.class, 'X'),
	ULONG_LONG(8, long.class, true, dataUlongLongOperations.class, 'X'),
	USHORT(2, short.class, true, dataUshortOperations.class, 'L');

	private static final Map<String, BulkIOType> MAP;

	static {
		MAP = new HashMap<>();
		MAP.put(dataBitHelper.id(), BulkIOType.BIT);
		MAP.put(dataCharHelper.id(), BulkIOType.CHAR);
		MAP.put(dataDoubleHelper.id(), BulkIOType.DOUBLE);
		MAP.put(dataFloatHelper.id(), BulkIOType.FLOAT);
		MAP.put(dataLongHelper.id(), BulkIOType.LONG);
		MAP.put(dataLongLongHelper.id(), BulkIOType.LONG_LONG);
		MAP.put(dataOctetHelper.id(), BulkIOType.OCTET);
		MAP.put(dataShortHelper.id(), BulkIOType.SHORT);
		MAP.put(dataUlongHelper.id(), BulkIOType.ULONG);
		MAP.put(dataUlongLongHelper.id(), BulkIOType.ULONG_LONG);
		MAP.put(dataUshortHelper.id(), BulkIOType.USHORT);
	}

	private final int bytePerAtom;
	private final Class< ? > javaType;
	private final boolean unsigned;
	private final Class< ? > portType;
	private final char midasType;

	private BulkIOType(int bytePerAtom, Class< ? > javaType, boolean unsigned, Class< ? > portType, char midasType) {
		this.bytePerAtom = bytePerAtom;
		this.javaType = javaType;
		this.unsigned = unsigned;
		this.portType = portType;
		this.midasType = midasType;
	}

	/**
	 * @return The number of bytes per 'atom' (size of 1 scalar sample). For {@link #BIT}, this returns 0.
	 */
	public int getBytePerAtom() {
		return bytePerAtom;
	}

	/**
	 * @since 2.0
	 */
	public static BulkIOType getType(updateSRIOperations impl) {
		BulkIOType retVal = null;
		if (dataBitOperations.class.isAssignableFrom(impl.getClass())) {
			retVal = BulkIOType.BIT;
		}
		if (dataCharOperations.class.isAssignableFrom(impl.getClass())) {
			if (retVal != null) {
				throw new IllegalArgumentException(impl.getClass() + " implements more than more type of BulkIO Interface.");
			}
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
		BulkIOType type = MAP.get(idl);
		if (type != null) {
			return type;
		} else {
			throw new IllegalArgumentException("Unknown type: " + idl);
		}
	}

	/**
	 * @since 2.0
	 */
	public static boolean isTypeSupported(String idl) {
		return MAP.containsKey(idl);
	}

	/**
	 * @return The non-upcast Java class container type
	 * @since 2.0
	 */
	public Class< ? > getJavaType() {
		return this.javaType;
	}

	/**
	 * @since 2.0
	 */
	public boolean isUnsigned() {
		return this.unsigned;
	}

	/**
	 * @return the portType
	 * @since 2.0
	 */
	public Class< ? > getPortType() {
		return portType;
	}

	/**
	 * @since 2.0
	 */
	public char getMidasType() {
		return midasType;
	}

	/**
	 * @since 2.0
	 */
	public Servant createServant(Object handler) {
		if (!portType.isAssignableFrom(handler.getClass())) {
			throw new IllegalArgumentException(this + " can not create servant.  Handler must be of type " + portType);
		}
		switch (this) {
		case BIT:
			return new dataBitPOATie((dataBitOperations) handler);
		case CHAR:
			return new dataCharPOATie((dataCharOperations) handler);
		case DOUBLE:
			return new dataDoublePOATie((dataDoubleOperations) handler);
		case FLOAT:
			return new dataFloatPOATie((dataFloatOperations) handler);
		case LONG:
			return new dataLongPOATie((dataLongOperations) handler);
		case LONG_LONG:
			return new dataLongLongPOATie((dataLongLongOperations) handler);
		case OCTET:
			return new dataOctetPOATie((dataOctetOperations) handler);
		case SHORT:
			return new dataShortPOATie((dataShortOperations) handler);
		case ULONG:
			return new dataUlongPOATie((dataUlongOperations) handler);
		case ULONG_LONG:
			return new dataUlongLongPOATie((dataUlongLongOperations) handler);
		case USHORT:
			return new dataUshortPOATie((dataUshortOperations) handler);
		default:
			throw new IllegalStateException("Unhandled port type: " + this);
		}
	}
}
