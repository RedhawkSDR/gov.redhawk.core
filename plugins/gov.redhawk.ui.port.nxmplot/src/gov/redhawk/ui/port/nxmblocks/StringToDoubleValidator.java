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

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.annotation.NonNull;

/**
 * @since 4.3 (package-private for now) - TODO: DELETE - no longer used
 */
class StringToDoubleValidator implements IValidator {
	private final boolean allowNull;
	private final boolean allowEmpty;
	private final String fieldName;
	private final String allowableString;
	/** inclusive minimum number allowed if specified. */ 
	private final Double minValue;
	/** inclusive maximum number allowed if specified. */
	private final Double maxValue;
	
	public StringToDoubleValidator(@NonNull String fieldName, boolean allowNull, boolean allowEmpty) {
		this(fieldName, allowNull, allowEmpty, null, null, null);
	}
	
	public StringToDoubleValidator(@NonNull String fieldName, boolean allowNull, boolean allowEmpty, String allowableString, Double minValue, Double maxValue) {
		this.fieldName = fieldName;
		this.allowNull = allowNull;
		this.allowEmpty = allowEmpty;
		this.allowableString = allowableString;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
	
	@Override
	public IStatus validate(Object value) {
		if (value == null) {
			if (!allowNull) {
				return ValidationStatus.error(fieldName + " cannot be null.");
			}
		} else if ("".equals(value)) {
			if (!allowEmpty) {
				return ValidationStatus.error(fieldName + " cannot be empty.");
			}
		} else if (allowableString != null && allowableString.equals(value)) {
			return Status.OK_STATUS;
		} else {
			try {
				double dval = Double.parseDouble((String) value);
				if (minValue != null && dval < minValue) {
					return ValidationStatus.error(fieldName + " must be greater than " + minValue);
				} else if (maxValue != null && dval > maxValue) {
					return ValidationStatus.error(fieldName + " must be less than " + maxValue);
				}
			} catch (NumberFormatException nfe) {
				return ValidationStatus.error(fieldName + " must be a floating point number.");
			}
		}
		return Status.OK_STATUS; // passed all checks
	}
}
