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
package gov.redhawk.internal.ui.port.nxmplot;

/** @since 4.2 */
public class CustomDoubleValidator implements IOtherAllowedInputValidator {
	private String errMsg;
	private final String otherValidValue;

	public CustomDoubleValidator(String fieldName, String otherAllowedValue) {
		this.errMsg = fieldName + " must be a double.";
		this.otherValidValue = otherAllowedValue;
	}

	@Override
	public String getOtherAllowedValue() {
		return otherValidValue;
	}

	@Override
	public String isValid(String text) {
		String status = errMsg; // assume error until passes validation check
		if (text != null) {
			text = text.trim();
			if (text.length() > 0) {
				try {
					@SuppressWarnings("unused")
					double val = Double.parseDouble(text);
					status = null; // valid user input
				} catch (final NumberFormatException nfe) {
					if (otherValidValue != null) {
						if (text.equals(otherValidValue)) {
							status = null; // allowed value
						}
					}
				}
			}
		}
		return status;
	}
}
