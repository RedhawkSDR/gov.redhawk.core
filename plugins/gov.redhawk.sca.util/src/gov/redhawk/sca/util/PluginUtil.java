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
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;

/**
 * 
 */
public class PluginUtil {

	/**
	 * Matches <code>${varname}</code> first, then <code>$varname</code> second.
	 * 
	 * @since 1.4
	 */
	public static final Pattern ENV_PATTERN = Pattern.compile("\\$(?:\\{(\\w+)\\}|(\\w+))");

	private PluginUtil() {

	}

	/**
	 * Logs an error on a plugin
	 * 
	 * @param plugin The plugin to log against
	 * @param msg The error message
	 * @param e Any associated Exceptions
	 */
	public static final void logError(final Plugin plugin, final String msg, final Throwable e) {
		plugin.getLog().log(new Status(IStatus.ERROR, plugin.getBundle().getSymbolicName(), msg, e));
	}

	/**
	 * Logs a warning on a plugin
	 * 
	 * @param plugin The plugin to log against
	 * @param msg The message
	 * @param e Any associated Exceptions
	 */
	public static final void logWarning(final Plugin plugin, final String msg, final Throwable e) {
		plugin.getLog().log(new Status(IStatus.ERROR, plugin.getBundle().getSymbolicName(), msg, e));
	}

	/**
	 * @since 1.2
	 */
	public static final < T > T adapt(final Class<T> adapter, final Object obj) {
		return PluginUtil.adapt(adapter, obj, false);
	}

	/**
	 * @since 1.2
	 */
	public static final < T > T adapt(final Class<T> adapterType, final Object adaptable, final boolean loadPlugin) {
		if (adaptable == null) {
			return null;
		}
		if (adapterType == null) {
			return null;
		}
		if (adapterType.isInstance(adaptable)) {
			return adapterType.cast(adaptable);
		}
		Object retVal = null;
		if (adaptable instanceof IAdaptable) {
			retVal = ((IAdaptable) adaptable).getAdapter(adapterType);
		}
		if (retVal == null) {
			retVal = Platform.getAdapterManager().getAdapter(adaptable, adapterType);
			if (retVal == null && loadPlugin) {
				retVal = Platform.getAdapterManager().loadAdapter(adaptable, adapterType.getName());
			}
		}

		return adapterType.cast(retVal);
	}

	/**
	 * @since 1.2
	 */
	public static boolean equals(final Object left, final Object right) {
		if (left == right) {
			return true;
		} else if (left == null) {
			return false;
		} else {
			if (right == null) {
				return false;
			} else if (left.getClass() != right.getClass()) {
				return false;
			} else if (left instanceof Object[]) {
				return Arrays.deepEquals((Object[]) left, (Object[]) right);
			} else if (left instanceof byte[]) {
				return Arrays.equals((byte[]) left, (byte[]) right);
			} else if (left instanceof char[]) {
				return Arrays.equals((char[]) left, (char[]) right);
			} else if (left instanceof short[]) {
				return Arrays.equals((short[]) left, (short[]) right);
			} else if (left instanceof int[]) {
				return Arrays.equals((int[]) left, (int[]) right);
			} else if (left instanceof long[]) {
				return Arrays.equals((long[]) left, (long[]) right);
			} else if (left instanceof float[]) {
				return Arrays.equals((float[]) left, (float[]) right);
			} else if (left instanceof double[]) {
				return Arrays.equals((double[]) left, (double[]) right);
			} else if (left instanceof boolean[]) {
				return Arrays.equals((boolean[]) left, (boolean[]) right);
			} else {
				return left.equals(right);
			}
		}
	}

	/**
	 * Expands variable references in a string to their corresponding values in the environment.
	 * 
	 * @param value The string to perform variable expansion on
	 * @param override A mapping of variable names to values that should override values in the environment 
	 * @return The string with variable references expanded
	 * @since 1.4
	 */
	public static String replaceEnvIn(final String value, final Map<String, String> override) {
		final StringBuffer retVal = new StringBuffer();
		final Matcher matcher = PluginUtil.ENV_PATTERN.matcher(value);
		while (matcher.find()) {
			// Grab the just variable name
			String envName = null;
			for (int i = 1; i <= matcher.groupCount(); i++) {
				envName = matcher.group(i);
				if (envName != null) {
					break;
				}
			}

			// Get the variable's value, either from the environment, or from the override map 
			String envValue = System.getenv(envName);
			if ((override != null) && (override.containsKey(envName))) {
				envValue = override.get(envName);
			}
			if (envValue == null) {
				envValue = "";
			}

			matcher.appendReplacement(retVal, envValue);
		}
		matcher.appendTail(retVal);
		return retVal.toString();
	}
}
