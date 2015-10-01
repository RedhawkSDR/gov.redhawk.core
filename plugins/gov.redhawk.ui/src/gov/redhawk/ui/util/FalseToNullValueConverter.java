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
 * @since 8.0
 */
public class FalseToNullValueConverter extends Converter {
	public FalseToNullValueConverter() {
		super(Boolean.class, Boolean.class);
	}

	@Override
	public Object convert(Object fromObject) {
		if (fromObject == Boolean.FALSE) {
			return null;
		}
		return fromObject;
	}
}
