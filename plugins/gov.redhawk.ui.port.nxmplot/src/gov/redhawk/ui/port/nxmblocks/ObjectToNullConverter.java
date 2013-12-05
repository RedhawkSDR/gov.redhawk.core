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
package gov.redhawk.ui.port.nxmblocks;

import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.jdt.annotation.NonNull;

/**
 * Wrap an optional existing {@link IConverter} to convert values, overriding it to convert
 * specified Object values (e.g. "default" String) to null. This uses's the value Object's equals(..) 
 * method to compare for equality for converting it to null.
 * If converter is null, it uses the {@link String#valueOf(Object)) conversion of the fromObject 
 * (i.e. a plain vanilla ObjectToStringConverter).
 * <li>
 *   Has option to convert null fromObject to null (since StringToNumberConverter throws
 *   IllegalArgumentException and NumberToStringConverter converts null to empty String).
 * </li>
 * <li>
 *   Has option to convert blank/empty String fromObject (regardless of whitespace) to null.
 * </li>
 * @since 4.3 (package-private for now)
 */
class ObjectToNullConverter extends Converter {
	private final boolean nullToNull;
	private final boolean blankToNull;
	private final Object[] valuesToConvertToNull;
	private final IConverter converter;
	
	public ObjectToNullConverter() {
		super(null, String.class);
		this.converter = null;
		this.nullToNull = true;
		this.blankToNull = true;
		this.valuesToConvertToNull = null;
	}
	
	/** calls {@link #ObjectsToNullConverterWrapper(IConverter, boolean, boolean, Object...)}
	 *  with nullToNull and blankToNull parms set to true.
	 */
	public ObjectToNullConverter(@NonNull IConverter converter) {
		this(converter, true, true);
	}

	/**
	 * @param converter the IConverter to wrap
	 * @param nullToNull true to convert null fromObject to null
	 * @param blankToNull true to convert blank fromObject String (empty or just whitespaces) to null
	 * @param valuesToConvertToNull (must NOT contain any nulls in this varargs)
	 */
	public ObjectToNullConverter(@NonNull IConverter converter, boolean nullToNull, boolean blankToNull, @NonNull Object... valuesToConvertToNull) {
		super(converter.getFromType(), converter.getToType());
		this.converter = converter;
		this.nullToNull = nullToNull;
		this.blankToNull = blankToNull;
		this.valuesToConvertToNull = valuesToConvertToNull;
	}

	@Override
	public Object convert(final Object fromObject) {
		if (fromObject == null) {
			if (nullToNull) {
				return null;
			} // else defer to converter
		} else if (blankToNull && (fromObject instanceof String) && ((String) fromObject).trim().isEmpty()) {
			return null;
		} else if (valuesToConvertToNull != null) {
			for (Object obj : valuesToConvertToNull) {
				if (obj.equals(fromObject)) {
					return null;
				}
			}
		}
		if (converter != null) {
			return converter.convert(fromObject);
		} else {
			return String.valueOf(fromObject);
		}
	}
}
