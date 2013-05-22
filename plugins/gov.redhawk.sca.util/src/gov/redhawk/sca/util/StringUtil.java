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
package gov.redhawk.sca.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.regex.Pattern;

/**
 * Utility class for string manipulation
 * @since 2.1
 */
public class StringUtil {

	public static final String DEFAULT_UNIQUE_REGEX = "([0-9])+";
	public static final String DEFAULT_DELIMITER = "_";

	private StringUtil() {
		//Prevent instantiation
	}

	/**
	 * Gets the default update strategy that uses the specified delimiter.
	 * 
	 * @param delimiter the {@link String} delimiter to use
	 * @return the default {@link StringUpdateStrategy} used by this utility class
	 */
	public static StringUpdateStrategy getDefaultUpdateStrategy(final String delimiter) {
		return StringUtil.getDefaultUpdateStrategy(delimiter, 1);
	}

	/**
	 * Gets the default update strategy that uses the specified delimiter.
	 * 
	 * @param delimiter the {@link String} delimiter to use
	 * @param start the index to start from 
	 * @return the default {@link StringUpdateStrategy} used by this utility class
	 * @since 3.0
	 */
	public static StringUpdateStrategy getDefaultUpdateStrategy(final String delimiter, final int start) {

		return new StringUpdateStrategy() {

			public String update(final String input) {
				int index = start;
				final StringBuilder retVal = new StringBuilder();
				final int last = input.lastIndexOf(delimiter);

				if (last != -1) {
					//append the first part of the string to the return value
					retVal.append(input.substring(0, last));
					final int sum = last + delimiter.length();
					//don't go past the end of the input string
					if (sum < input.length()) {
						final String suffix = input.substring(sum);
						//if the suffix matches the regex, get the new value and append
						if (Pattern.matches(StringUtil.DEFAULT_UNIQUE_REGEX, suffix)) {
							index = Integer.parseInt(suffix);
							retVal.append(delimiter);
							retVal.append(++index);
							//there was no regex match, so add the delimiter and suffix back on then the delimiter and the uniq index
						} else {
							retVal.append(delimiter);
							retVal.append(suffix);
							retVal.append(delimiter);
							retVal.append(++index);
						}
						//put the delimiter back on and the index
					} else {
						retVal.append(delimiter);
						retVal.append(++index);
					}
					//there is no delimiter, so append it and the index
				} else {
					retVal.append(input);
					retVal.append(delimiter + ++index);
				}

				return retVal.toString();
			}
		};
	}

	/**
	 * Gets the update strategy used by default.
	 * 
	 * @return the default {@link StringUpdateStrategy} used by this utility class
	 */
	public static StringUpdateStrategy getDefaultUpdateStrategy() {
		return StringUtil.getDefaultUpdateStrategy(StringUtil.DEFAULT_DELIMITER);
	}

	/**
	 * Gets the string comparator used by default.
	 * 
	 * @return the default {@link Comparator} used by this utility class
	 */
	public static Comparator<String> getDefaultComparator() {
		return new Comparator<String>() {

			public int compare(final String o1, final String o2) {
				if (o1 == null) {
					return (o2 != null) ? 1 : 0; // SUPPRESS CHECKSTYLE AvoidInline
				} else if (o2 == null) {
					return -1;
				}
				return o1.compareTo(o2);
			}
		};
	}

	/**
	 * Creates a unique string give an input string and a collection of strings to compare against.
	 * If the string is not present in the collection, it is considered unique and returned; otherwise, a unique string is created.
	 * 
	 * @param input the {@link String} to compare 
	 * @param strings the String {@link Collection} to compare against
	 * @return the input string if it doesn't exist in the collection; a unique string based on the default comparator 
	 * 	and default update strategy otherwise
	 */
	public static String defaultCreateUniqueString(final String input, final Collection<String> strings) {
		return StringUtil.createUniqueString(input, strings, StringUtil.getDefaultComparator(), StringUtil.getDefaultUpdateStrategy());
	}

	/**
	 * Creates a unique string give an input string and a collection of strings to compare against.
	 * If the string is not present in the collection, it is considered unique and returned; otherwise, a unique string is created.
	 * 
	 * @param input input the {@link String} to compare 
	 * @param strings the String {@link Collection} to compare against
	 * @param strategy the {@link UpdateStrategy} to use when creating the unique string
	 * @return the input string if it doesn't exist in the collection; a unique string based on the default comparator 
	 * 	and specified update strategy otherwise
	 */
	public static String defaultCreateUniqueString(final String input, final Collection<String> strings, final StringUpdateStrategy strategy) {
		return StringUtil.createUniqueString(input, strings, StringUtil.getDefaultComparator(), strategy);
	}

	/**
	 * Creates a unique string give an input string and a collection of strings to compare against.
	 * If the string is not present in the collection, it is considered unique and returned; otherwise, a unique string is created.
	 * 
	 * @param input the {@link String} to compare
	 * @param strings the String {@link Collection} to compare against
	 * @param comparator the {@link Comparator} to use when comparing the strings
	 * @param strategy the {@link UpdateStrategy} to use when creating the unique string
	 * @return the input string if it doesn't exist in the collection; a unique string based on the specified comparator 
	 * 	and specified update strategy otherwise
	 */
	public static String createUniqueString(final String input, final Collection<String> strings, final Comparator<String> comparator,
	        final StringUpdateStrategy strategy) {
		String uniqueString = input;
		for (final String string : strings) {
			if (comparator.compare(input, string) == 0) {
				uniqueString = StringUtil.createUniqueString(strategy.update(input), strings, comparator, strategy);
				break;
			}
		}

		return uniqueString;
	}

	/**
	 * Remove the white space and swap pairs of characters for one another if they are found in the input
	 * 
	 * @param input the {@link String} to modify
	 * @param filter the two dimensional array of characters that will be used to swap the primary with the seconday, should
	 * they be found in the input String
	 * 
	 * @return the input String after it has been modified by trimming and filters applied to it
	 * 
	 * @since 2.2
	 */
	public static String cleanUp(String input, final char[][] filter) {
		input = input.trim();

		for (final char[] replace : Arrays.asList(filter)) {
			input = input.replace(replace[0], replace[1]);
		}

		return input;
	}
}
