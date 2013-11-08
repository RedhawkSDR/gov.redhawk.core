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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.NonNullByDefault;
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

@NonNullByDefault
public enum BulkIOType {
	DOUBLE(8, double.class, false, dataDoubleOperations.class),
	FLOAT(4, float.class, false, dataFloatOperations.class),
	LONG(4, int.class, false, dataLongOperations.class),
	ULONG(4, int.class, true, dataUlongOperations.class),
	LONG_LONG(8, long.class, false, dataLongLongOperations.class),
	ULONG_LONG(8, long.class, true, dataUlongLongOperations.class),
	SHORT(2, short.class, false, dataShortOperations.class),
	USHORT(2, short.class, true, dataUshortOperations.class),
	CHAR(2, char.class, false, dataCharOperations.class),
	OCTET(1, byte.class, false, dataOctetOperations.class);

	private final int bytePerAtom;
	private final Class< ? > javaType;
	private final boolean unsigned;
	private final Class< ? > portType;

	private BulkIOType(int bytePerAtom, Class< ? > javaType, boolean unsigned, Class< ? > portType) {
		this.bytePerAtom = bytePerAtom;
		this.javaType = javaType;
		this.unsigned = unsigned;
		this.portType = portType;
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
	@NonNull
	public Class< ? > getJavaType() {
		return this.javaType;
	}

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
	 * @deprecated since 2.0 use {@link #createServant(Object)} then call {@link POA#servant_to_reference(Servant)} instead.
	 */
	@Deprecated
	@NonNull
	public org.omg.CORBA.Object createRef(@NonNull POA poa, @NonNull Object handler) throws ServantNotActive, WrongPolicy {
		Servant tie = createServant(handler);
		return poa.servant_to_reference(tie);
	}

	/**
	 * @since 2.0
	 */
	@NonNull
	public Servant createServant(Object handler) {
		if (!portType.isAssignableFrom(handler.getClass())) {
			throw new IllegalArgumentException(this + " can not create servant.  Handler must be of type " + portType);
		}
		switch (this) {
		case CHAR:
			return new dataCharPOATie((dataCharOperations) handler);
		case DOUBLE:
			return new dataDoublePOATie((dataDoubleOperations) handler);
		case FLOAT:
			return new dataFloatPOATie((dataFloatOperations) handler);
		case LONG:
			return new dataLongLongPOATie((dataLongLongOperations) handler);
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
