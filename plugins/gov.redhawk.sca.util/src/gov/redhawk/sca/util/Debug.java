/**
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
/*******************************************************************************
 * Copyright (c) 2007 Ecliptical Software Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Ecliptical Software Inc. - initial API and implementation
 *******************************************************************************/
package gov.redhawk.sca.util;

import gov.redhawk.sca.util.internal.Messages;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Date;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;

/**
 * Helper class used for debug tracing. To use, create a static final variable
 * initialized with the corresponding debug option (tag). Wherever tracing is
 * needed, check the {@link #enabled} variable first, then use
 * {@link #trace(String)} or {@link #out} directly to print debug messages.
 * E.g.,
 * 
 * <pre>
 * private static final Debug debug = new Debug(&amp;quotmyPlugin$quote&quot;myTag&quot;);
 * ...
 * if (debug.enabled)
 * debug.trace(...);
 * </pre>
 * 
 * @since 1.1
 */
public final class Debug {
	public static final String DEBUG = "/debug"; //$NON-NLS-1$

	private static final boolean SHOW_TAG;
	private static final boolean SHOW_DATE;
	private static final MessageFormat OUT_FORMAT;
	static {
		final String valueShowTag = Platform.getDebugOption("gov.redhawk.sca.util/debug/showTag"); //$NON-NLS-1$
		final String valueShowDate = Platform.getDebugOption("gov.redhawk.sca.util/debug/showDate"); //$NON-NLS-1$
		SHOW_TAG = (valueShowTag == null) ? true : Boolean.valueOf(valueShowTag); // SUPPRESS CHECKSTYLE AvoidInLine
		SHOW_DATE = (valueShowDate == null) ? true : Boolean.valueOf(valueShowDate); // SUPPRESS CHECKSTYLE AvoidInLine
		if (Debug.SHOW_TAG) {
			if (Debug.SHOW_DATE) {
				OUT_FORMAT = new MessageFormat(Messages.Debug__DATE_TAG_MSG_PATTERN);
			} else {
				OUT_FORMAT = new MessageFormat(Messages.Debug__TAG_MSG_PATTERN);
			}
		} else if (Debug.SHOW_DATE) {
			OUT_FORMAT = new MessageFormat(Messages.Debug__DATE_MSG_PATTERN);
		} else {
			OUT_FORMAT = new MessageFormat(Messages.Debug__MSG_PATTERN);
		}

	}

	//CHECKSTYLE:OFF Turned off since we gain performance by having this public
	/** True if debugging output should occur */
	public final boolean enabled;

	/** The tag for this debug tracing. */
	public final String tag;
	//CHECKSTYLE:ON

	/** The out stream to print messages to. */
	private final PrintStream out;

	/** The id of the plugin that this tracing is for */
	private final String pluginId;

	/**
	 * Use the specified tag
	 * 
	 * @param tag the tag
	 * @param plugin the plugin
	 */
	public Debug(final Plugin plugin, final String tag) {
		this((plugin == null) ? null : plugin.getBundle().getSymbolicName(), tag); // SUPPRESS CHECKSTYLE AvoidInLine
	}

	/**
	 * Uses the root DEBUG tag.
	 * 
	 * @param plugin the plugin
	 */
	public Debug(final Plugin plugin) {
		this(plugin, null);
	}

	/**
	 * Uses the root DEBUG tag.
	 * 
	 * @param pluginId the plugin id
	 */
	public Debug(final String pluginId) {
		this(pluginId, null);
	}

	/**
	 * Defaults output to System.out
	 * 
	 * @param pluginId the plugin id
	 * @param tag the tag
	 */
	public Debug(final String pluginId, final String tag) {
		this(pluginId, tag, null);
	}

	/**
	 * Customize the output behavior of DEBUG
	 * 
	 * @param pluginId the plugin id
	 * @param tag the tag
	 * @param out The print stream to write messaging to
	 */
	public Debug(final String pluginId, String tag, final PrintStream out) {
		Assert.isNotNull(pluginId, Messages.DEBUG__NULL_PLUGIN_ID);
		this.pluginId = pluginId;

		if (out == null) {
			this.out = System.out;
		} else {
			this.out = out;
		}

		final String debugTag = this.pluginId + Debug.DEBUG;
		if (tag == null) {
			tag = debugTag;
		} else if (!tag.startsWith(debugTag)) {
			tag = debugTag + "/" + tag; //$NON-NLS-1$
		}
		this.tag = tag;

		String value = Platform.getDebugOption(debugTag);
		final boolean debug = (value == null) ? false : Boolean.valueOf(value); // SUPPRESS CHECKSTYLE AvoidInLine

		value = Platform.getDebugOption(this.tag);
		final boolean tagEnabled = (value == null) ? false : Boolean.valueOf(value); // SUPPRESS CHECKSTYLE AvoidInLine

		this.enabled = debug && tagEnabled;
	}

	/**
	 * @return the pluginId that this Debugger is associated with
	 */
	public String getPluginId() {
		return this.pluginId;
	}

	/**
	 * @return the tag that this debugger is enabled for
	 */
	public String getTag() {
		return this.tag;
	}

	/**
	 * Entering method.
	 * 
	 * @param args the arguments (if any) of the method
	 */
	public void enteringMethod(final Object... args) {
		if (this.enabled) {
			final StackTraceElement stack = getCaller();
			final String fileName = stack.getFileName();
			final String lineNumber = getLineNumberString(stack);
			String argStr = ""; //$NON-NLS-1$;
			if (args != null && args.length > 0) {
				argStr = Arrays.toString(args);
			}
			if (fileName == null) {
				internalMessage(Messages.Debug__ENTERING_TYPE
				        + MessageFormat.format(Messages.DEBUG__ENTERING_METHOD_NO_FILE, stack.getClassName(), stack.getMethodName(), argStr));
			} else {
				internalMessage(Messages.Debug__ENTERING_TYPE
				        + MessageFormat.format(Messages.DEBUG__ENTERING_METHOD, stack.getClassName(), stack.getMethodName(), fileName, lineNumber, argStr));
			}
		}
	}

	private String getLineNumberString(final StackTraceElement element) {
		if (element != null && element.getLineNumber() > 0) {
			return Integer.toString(element.getLineNumber());
		} else {
			return Messages.DEBUG__UNKNOWN_LINE_NUMBER;
		}
	}

	/**
	 * Gets the first stack trace element called before this class was called
	 */
	private StackTraceElement getCaller() {
		final StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		for (final StackTraceElement element : trace) {
			if (!getClass().getName().equals(element.getClassName()) && !Thread.class.getName().equals(element.getClassName())) {
				return element;
			}
		}
		return null;
	}

	/**
	 * Exiting method.
	 * 
	 * @param retVal the return value of the method
	 */
	public void exitingMethod(final Object retVal) {
		if (this.enabled) {
			final StackTraceElement stack = getCaller();
			final String fileName = stack.getFileName();
			final String lineNumber = getLineNumberString(stack);
			if (fileName == null) {
				internalMessage(Messages.Debug__EXITING_TYPE
				        + MessageFormat.format(Messages.DEBUG__EXITING_METHOD_WITH_RETURN_VALUE_NO_FILE, stack.getClassName(), stack.getMethodName(), retVal));
			} else {
				internalMessage(Messages.Debug__EXITING_TYPE
				        + MessageFormat.format(Messages.DEBUG__EXITING_METHOD_WITH_RETURN_VALUE,
				                stack.getClassName(),
				                stack.getMethodName(),
				                fileName,
				                lineNumber,
				                retVal));
			}
		}
	}

	/**
	 * Leaving method.
	 */
	public void exitingMethod() {
		if (this.enabled) {
			final StackTraceElement stack = getCaller();
			final String fileName = stack.getFileName();
			final String lineNumber = getLineNumberString(stack);
			if (fileName == null) {
				internalMessage(Messages.Debug__EXITING_TYPE
				        + MessageFormat.format(Messages.DEBUG__EXITING_METHOD_NO_FILE, stack.getClassName(), stack.getMethodName()));
			} else {
				internalMessage(Messages.Debug__EXITING_TYPE
				        + MessageFormat.format(Messages.DEBUG__EXITING_METHOD, stack.getClassName(), stack.getMethodName(), fileName, lineNumber));
			}
		}
	}

	/**
	 * Print a debug message for throwing an exception.
	 * 
	 * The return value is equal to the parameter argument.  This allow chaining in the throw statement.
	 * <code>
	 * throw Debug.throwing(new Exception());
	 * </code>
	 * 
	 * @param e the exception that is to be thrown
	 * @return Returns the exception that is the argument.
	 * @since 3.0
	 */
	public < T extends Throwable > T throwing(final T e) {
		if (this.enabled) {
			internalMessage(Messages.Debug__THROWN_TYPE + MessageFormat.format(Messages.DEBUG__THROWN, e));
			//CHECKSTYLE:OFF
			e.printStackTrace(this.out);
			//CHECKSTYLE:ON
		}
		return e;
	}

	/**
	 * Traces the catching of the specified throwable.
	 * 
	 * @param throwable The throwable that is being caught.
	 */
	public void catching(final Throwable e) {
		if (this.enabled) {
			internalMessage(Messages.Debug__CATCHING_TYPE + MessageFormat.format(Messages.DEBUG__CATCHING, e));
			//CHECKSTYLE:OFF
			e.printStackTrace(this.out);
			//CHECKSTYLE:ON
		}
	}

	/**
	 * Traces the catching of the specified throwable.
	 * 
	 * @param throwable The throwable that is being caught.
	 * @since 3.0
	 */
	public void catching(final String msg, final Throwable e) {
		if (this.enabled) {
			internalMessage(Messages.Debug__CATCHING_TYPE + " " + msg + " " + MessageFormat.format(Messages.DEBUG__CATCHING, e));
			//CHECKSTYLE:OFF
			e.printStackTrace(this.out);
			//CHECKSTYLE:ON
		}
	}

	/**
	 * Gets the Class and method from the stack trace of the caller.
	 * 
	 * @param valueDescription The description of the value which is changing.
	 * @param oldValue The old value.
	 * @param newValue The new value.
	 */
	public void changing(final String valueDescription, final Object oldValue, final Object newValue) {
		if (this.enabled) {
			trace(Messages.Debug__CHANGING_TYPE + MessageFormat.format(Messages.DEBUG__CHANGING, valueDescription, oldValue, newValue));
		}
	}

	/**
	 * Trace.
	 * 
	 * @param format the format
	 * @param args the args
	 */
	public void trace(final String format, final Object... args) {
		if (this.enabled) {
			final String msg = MessageFormat.format(format, args);
			final StackTraceElement stack = getCaller();
			final String fileName = stack.getFileName();
			final String lineNumber = getLineNumberString(stack);
			if (fileName != null) {
				internalMessage("TRACE: " + MessageFormat.format(Messages.Debug__TRACE_FILE_PATTERN, //$NON-NLS-1$
				        stack.getClassName(),
				        stack.getMethodName(),
				        stack.getFileName(),
				        lineNumber,
				        msg));
			} else {
				internalMessage("TRACE: " + MessageFormat.format(Messages.Debug__TRACE_NO_FILE_PATTERN, //$NON-NLS-1$
				        stack.getClassName(),
				        stack.getMethodName(),
				        msg));
			}
		}
	}

	/**
	 * Trace.
	 * 
	 * @param msg the msg
	 */
	public void trace(final String msg) {
		if (this.enabled) {
			final StackTraceElement stack = getCaller();
			final String fileName = stack.getFileName();
			final String lineNumber = getLineNumberString(stack);
			if (fileName != null) {
				internalMessage(Messages.Debug__TRACE_TYPE
				        + MessageFormat.format(Messages.Debug__TRACE_FILE_PATTERN,
				                stack.getClassName(),
				                stack.getMethodName(),
				                stack.getFileName(),
				                lineNumber,
				                msg));
			} else {
				internalMessage(Messages.Debug__TRACE_TYPE
				        + MessageFormat.format(Messages.Debug__TRACE_NO_FILE_PATTERN, stack.getClassName(), stack.getMethodName(), msg));
			}
		}
	}

	/**
	 * Message.
	 * 
	 * @param msg the msg
	 */
	public void message(final String msg) {
		if (this.enabled) {
			internalMessage(Messages.Debug__MESSAGE_TYPE + msg);
		}
	}

	/**
	 * Message.
	 * 
	 * @param msg the msg
	 */
	public void message(final String pattern, final Object... args) {
		if (this.enabled) {
			internalMessage(Messages.Debug__MESSAGE_TYPE + MessageFormat.format(pattern, args));
		}
	}

	/**
	 * Message.
	 * 
	 * @param msg the msg
	 */
	private void internalMessage(final String msg) {
		final Object[] arguments;
		if (Debug.SHOW_TAG) {
			if (Debug.SHOW_DATE) {
				arguments = new Object[] {
				        new Date(), this.tag, msg
				};
			} else {
				arguments = new Object[] {
				        this.tag, msg
				};
			}
		} else if (Debug.SHOW_DATE) {
			arguments = new Object[] {
			        new Date(), msg
			};
		} else {
			arguments = new Object[] {
				msg
			};
		}
		final StringBuffer result = new StringBuffer();
		Debug.OUT_FORMAT.format(arguments, result, null);
		this.out.println(result.toString());
	}
}
