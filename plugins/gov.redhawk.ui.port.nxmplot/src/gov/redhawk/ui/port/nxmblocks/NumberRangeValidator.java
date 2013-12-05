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
import org.eclipse.jdt.annotation.NonNull;

/**
 * Validates that a Number is optionally within specified ranges for a minimum value and/or maximum value.
 * This requires the input value for validation to have been converted to a subclass of Number.
 * The min and max range can be inclusive or exclusive independently.
 * <br>
 * C should be a class that extends Number (e.g. Integer, Double).
 * @since 4.3 (package-private for now)
 */
class NumberRangeValidator<C extends Comparable<? super C>> implements IValidator {
	private final Class<? extends Number> numberClass;
	private String fieldName;
	private boolean allowNull = true;
	/** minimum number allowed if specified. */ 
	private C minValue;
	/** maximum number allowed if specified. */
	private C maxValue;
	private boolean inclusiveMin = true;
	private boolean inclusiveMax = true;
	
	/** ctor that defaults allowNull to true and no maxValue constraint. */
	public NumberRangeValidator(@NonNull String fieldName, @NonNull Class<? extends Number> numberClass, C minValue, boolean inclusiveMin) {
		this(fieldName, numberClass, true, minValue, inclusiveMin, null, true);
	}
	
	public NumberRangeValidator(@NonNull String fieldName, @NonNull Class<? extends Number> numberClass,
		boolean allowNull, C minValue, boolean inclusiveMin, C maxValue, boolean inclusiveMax) {
		this.fieldName = fieldName;
		this.numberClass = numberClass;
		this.allowNull = allowNull;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.inclusiveMin = inclusiveMin;
		this.inclusiveMax = inclusiveMax;
	}
	
	@Override
	public IStatus validate(Object value) {
		if (value == null) {
			if (!allowNull) {
				return ValidationStatus.error(fieldName + " cannot be null.");
			}
		} else if (!numberClass.isAssignableFrom(value.getClass())) {
			return ValidationStatus.error(fieldName + " is not a valid " + numberClass + " number.");
		} else {
			@SuppressWarnings("unchecked") // OK: since we do instanceof check above using Class.isAssignableFrom(..)
			C val = (C) value;
			if (minValue != null) { 
				int compareToResults = minValue.compareTo(val);
				if (inclusiveMin && compareToResults > 0) { // inclusive min value check
					return ValidationStatus.error(fieldName + " must be greater than or equal to " + minValue);
				} else if (compareToResults >= 0) {         // exclusive min value check 
					return ValidationStatus.error(fieldName + " must be greater than " + minValue);
				}
			}
			if (maxValue != null) {
				int compareToResults = maxValue.compareTo(val);
				if (inclusiveMax && compareToResults < 0) { // inclusive max value check
					return ValidationStatus.error(fieldName + " must be less than or equal to " + maxValue);
				} else if (compareToResults <= 0) {         // exclusive max value check
					return ValidationStatus.error(fieldName + " must be less than " + maxValue);
				}
			}
		}
		return ValidationStatus.ok(); // or return Status.OK_STATUS; // passed all checks
	}
}
