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
package gov.redhawk.ui.util;

import org.eclipse.core.databinding.conversion.Converter;


/**
 * 
 */
public class EmptyStringToNullConverter extends Converter {
	public static final String EMPTY_STRING = "";
	
	public EmptyStringToNullConverter() {
	    super(String.class, String.class);
    }

	public Object convert(final Object value) {
		if (value instanceof String && ((String) value).trim().equals(EMFEmptyStringToNullUpdateValueStrategy.EMPTY_STRING)) {
			return null;
		}
		return value;
	}
}
