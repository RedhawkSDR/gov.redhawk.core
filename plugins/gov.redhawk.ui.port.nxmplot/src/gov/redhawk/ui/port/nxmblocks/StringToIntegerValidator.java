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

import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

/**
 * Used before custom converter in data bindings (e.g. afterGetValidator when value from widget is still a String).
 * @since 4.3 (package-private for now)
 */
class StringToIntegerValidator extends AllowableStringValidator {

	public StringToIntegerValidator(String fieldName, String... allowableStrings) {
		this(fieldName, true, true, allowableStrings);
	}
	
	public StringToIntegerValidator(String fieldName, boolean allowNull, boolean allowEmpty, String... allowableStrings) {
		super(fieldName, allowNull, allowEmpty, allowableStrings);
	}

	@Override
	public IStatus doValidate(String value) {
		try {
			Integer.parseInt(value);
		} catch (NumberFormatException nfe) {
			return ValidationStatus.error(getFieldName() + " must be an integer number.");
		}
		return ValidationStatus.ok(); 
	}
}
