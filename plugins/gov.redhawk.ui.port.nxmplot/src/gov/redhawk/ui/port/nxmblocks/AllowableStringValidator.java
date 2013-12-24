/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.ui.port.nxmblocks;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.annotation.NonNull;

/**
 * Used before custom converter in data bindings (e.g. afterGetValidator when value from widget is still a String).
 * @since 4.3 (package-private for now)
 */
class AllowableStringValidator implements IValidator {

	private String fieldName;
	private boolean allowNull;
	private boolean allowEmpty;
	private String[] allowableStrings;

	public AllowableStringValidator(@NonNull String fieldName, String... allowableStrings) {
		this(fieldName, true, true, allowableStrings);
	}
	
	public AllowableStringValidator(@NonNull String fieldName, boolean allowNull, boolean allowEmpty, String... allowableStrings) {
		this.fieldName = fieldName;
		this.allowNull = allowNull;
		this.allowEmpty = allowEmpty;
		this.allowableStrings = allowableStrings;
	}

	@Override
	public final IStatus validate(Object value) {
		if (value == null) {
			if (!allowNull) {
				return ValidationStatus.error(fieldName + " cannot be null.");
			}
			return ValidationStatus.ok();
		} else if (value instanceof String) {
			String strValue = ((String) value).trim();
			if (!allowEmpty && "".equals(strValue)) {
				return ValidationStatus.error(fieldName + " cannot be empty.");
			}
			if (allowableStrings != null) {
				for (String allowableStr : allowableStrings) {
					if (strValue.equals(allowableStr)) {
						return ValidationStatus.ok();
					}
				}
			}
			return doValidate(strValue);
		}
		return ValidationStatus.ok(); // passed all checks
	}

	public String getFieldName() {
		return this.fieldName;
	}

	/** subclasses should override this to do the actual String to Number validation. */
	public IStatus doValidate(String value) {
		return ValidationStatus.ok(); 
	}

}
