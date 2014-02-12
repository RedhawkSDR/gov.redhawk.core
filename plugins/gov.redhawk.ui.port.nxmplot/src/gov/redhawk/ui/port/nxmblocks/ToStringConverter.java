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
 * A simple to string converter which translate using fromObject's toString() method,
 * if fromObject is null, it just returns null.
 * @since 4.4 (package-private for now)
 */
class ToStringConverter extends Converter {

	public ToStringConverter() {
		super(null, String.class);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.databinding.conversion.IConverter#convert(java.lang.Object)
	 */
	@Override
	public Object convert(Object fromObject) {
		if (fromObject != null) {
			return fromObject.toString();
		}
		return null;
	}

}
