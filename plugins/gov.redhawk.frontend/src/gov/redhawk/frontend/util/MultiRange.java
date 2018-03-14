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
package gov.redhawk.frontend.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides the ability to parse FRONTEND interfaces-style ranges. Each range in the string is separated by a comma. A
 * range is either a minimum and maximum (e.g. "1-100"), or a single discrete value (e.g. "5"). Some examples:
 * <ul>
 * <li>"1-2,4-5"</li>
 * <li>"1-3,5,7-10"</li>
 * </ul>
 * Parsing errors are ignored.
 * @since 2.0
 */
public class MultiRange {

	private class Range {

		public Range(double lower, double upper) {
			this.lower = lower;
			this.upper = upper;
		}

		private double lower;
		private double upper;
	}

	private List<Range> ranges = new ArrayList<>();

	/**
	 * Parse the given string and store all ranges found in it
	 * @param multiRangeString
	 */
	public void addRange(String multiRangeString) {
		if (multiRangeString == null) {
			return;
		}
		String[] rangeStrings = multiRangeString.split(",");
		for (String rangeString : rangeStrings) {
			String[] numbers = rangeString.split("-");
			double lower, upper;
			try {
				if (numbers.length == 1) {
					lower = Double.parseDouble(numbers[0].trim());
					upper = lower;
				} else if (numbers.length == 2) {
					lower = Double.parseDouble(numbers[0].trim());
					upper = Double.parseDouble(numbers[1].trim());
				} else {
					continue;
				}
				ranges.add(new Range(lower, upper));
			} catch (NumberFormatException e) {
				continue;
			}
		}
	}

	/**
	 * @return True if there are 1 or more ranges in this multi-range
	 */
	public boolean hasRanges() {
		return !ranges.isEmpty();
	}

	/**
	 * @param value The value to test
	 * @return True if the value is within one of the ranges this {@link MultiRange} represents.
	 */
	public boolean inRange(double value) {
		// If there are no ranges, all values are in range
		if (ranges.size() == 0) {
			return true;
		}

		for (Range range : ranges) {
			if (range.lower <= value && value <= range.upper) {
				return true;
			}
		}
		return false;
	}

}
