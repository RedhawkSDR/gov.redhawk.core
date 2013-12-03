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

/**
 * Custom String to Double converter allowing special strings (e.g. "default") to be converted to null.
 * @since 4.3 (package-private for now)
 */
class CustomStringToDoubleConverter extends Converter {
	private final boolean emptyAsNull;
	private final String[] stringsToConvertToNull;
	
	public CustomStringToDoubleConverter(boolean emptyAsNull, String... stringsToConvertToNull) {
		super(String.class, Double.class);
		this.emptyAsNull = emptyAsNull;
		this.stringsToConvertToNull = stringsToConvertToNull;
	}

	@Override
	public Object convert(Object fromObject) {
		if (fromObject == null) {
			return null;
		} else if (emptyAsNull && ((String) fromObject).trim().isEmpty()) {
			return null;
		} else if (stringsToConvertToNull != null) {
			for (String str : stringsToConvertToNull) {
				if (str.equals(fromObject)) {
					return null;
				}
			}
		}
		return Double.valueOf((String) fromObject);
	}
}
