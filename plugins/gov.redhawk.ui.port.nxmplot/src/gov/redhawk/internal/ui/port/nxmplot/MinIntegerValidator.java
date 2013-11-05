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

/**
 * @since 4.2
 * @noreference This class is not intended to be referenced by clients.
 **/
public class MinIntegerValidator implements IOtherAllowedInputValidator {
	private int minAllowed = Integer.MIN_VALUE; // inclusive
	private String errMsg;
	private final String otherValidValue;

	public MinIntegerValidator(String fieldName, int minVal, String otherAllowedValue) {
		this.minAllowed = minVal;
		this.errMsg = fieldName + " must be an integer >= " + minVal + ".";
		this.otherValidValue = otherAllowedValue;
    }

	@Override
	public String getOtherAllowedValue() {
		return otherValidValue;
	}

	@Override
	public String isValid(final String text) {
		String status = errMsg; // assume error until passes validation check
		if ((text != null) && !"".equals(text.trim())) {
			try {
				int val = Integer.parseInt(text.trim());
				if (val >= minAllowed) {
					status = null; // valid user input
				} // else invalid input (minAllowed)
			} catch (final NumberFormatException nfe) {
				if (otherValidValue != null) {
					if (text.equals(otherValidValue)) {
						status = null; // allowed value
					}
				}
			}
		}
		return status;
	}
}
