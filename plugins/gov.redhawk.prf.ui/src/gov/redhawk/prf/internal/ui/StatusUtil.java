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
package gov.redhawk.prf.internal.ui;

import gov.redhawk.prf.ui.PrfUiPlugin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.statushandlers.StatusManager;

/**
 * Utility class to create status objects.
 * 
 * @private - This class is an internal implementation class and should not be
 *          referenced or subclassed outside of the workbench
 */
public final class StatusUtil {

	/**
	 * Instantiates a new status util.
	 */
	private StatusUtil() {
	}

	/**
	 * Answer a flat collection of the passed status and its recursive children.
	 * 
	 * @param aStatus the a status
	 * 
	 * @return the list
	 */
	protected static List< ? > flatten(final IStatus aStatus) {
		final List<Object> result = new ArrayList<Object>();

		if (aStatus.isMultiStatus()) {
			final IStatus[] children = aStatus.getChildren();
			for (int i = 0; i < children.length; i++) {
				final IStatus currentChild = children[i];
				if (currentChild.isMultiStatus()) {
					final Iterator< ? > childStatiiEnum = StatusUtil.flatten(currentChild).iterator();
					while (childStatiiEnum.hasNext()) {
						result.add(childStatiiEnum.next());
					}
				} else {
					result.add(currentChild);
				}
			}
		} else {
			result.add(aStatus);
		}

		return result;
	}

	/**
	 * This method must not be called outside the workbench.
	 * 
	 * Utility method for creating status.
	 * 
	 * @param stati the stati
	 * @param message the message
	 * @param exception the exception
	 * 
	 * @return the i status
	 */
	protected static IStatus newStatus(final IStatus[] stati, final String message, final Throwable exception) {

		Assert.isTrue(message != null);
		Assert.isTrue(message.trim().length() != 0);

		return new MultiStatus(PrfUiPlugin.getPluginId(), IStatus.OK, stati, message, exception);
	}

	/**
	 * New status.
	 * 
	 * @param pluginId the plugin id
	 * @param exception the exception
	 * 
	 * @return the i status
	 */
	public static IStatus newStatus(final String pluginId, final Throwable exception) {
		return StatusUtil.newStatus(pluginId, StatusUtil.getLocalizedMessage(exception), exception);
	}

	/**
	 * Returns a localized message describing the given exception. If the given
	 * exception does not have a localized message, this returns the string
	 * "An error occurred".
	 * 
	 * @param exception the exception
	 * 
	 * @return the localized message
	 */
	public static String getLocalizedMessage(final Throwable exception) {
		final String message = exception.getLocalizedMessage();

		if (message != null) {
			return message;
		}

		// Workaround for the fact that CoreException does not implement a
		// getLocalizedMessage() method.
		// Remove this branch when and if CoreException implements
		// getLocalizedMessage()
		if (exception instanceof CoreException) {
			final CoreException ce = (CoreException) exception;
			return ce.getStatus().getMessage();
		}

		return "An unexpected exception was thrown.";
	}

	/**
	 * Creates a new Status based on the original status, but with a different
	 * message.
	 * 
	 * @param originalStatus the original status
	 * @param newMessage the new message
	 * 
	 * @return the i status
	 */
	public static IStatus newStatus(final IStatus originalStatus, final String newMessage) {
		return new Status(originalStatus.getSeverity(), originalStatus.getPlugin(), originalStatus.getCode(), newMessage, originalStatus.getException());
	}

	/**
	 * New status.
	 * 
	 * @param pluginId the plugin id
	 * @param message the message
	 * @param exception the exception
	 * 
	 * @return the i status
	 */
	public static IStatus newStatus(final String pluginId, final String message, final Throwable exception) {
		return new Status(IStatus.ERROR, pluginId, IStatus.OK, message, StatusUtil.getCause(exception));
	}

	/**
	 * Gets the cause.
	 * 
	 * @param exception the exception
	 * 
	 * @return the cause
	 */
	public static Throwable getCause(final Throwable exception) {
		// Figure out which exception should actually be logged -- if the given
		// exception is
		// a wrapper, unwrap it
		Throwable cause = null;
		if (exception != null) {
			if (exception instanceof CoreException) {
				// Workaround: CoreException contains a cause, but does not
				// actually implement getCause().
				// If we get a CoreException, we need to manually unpack the
				// cause. Otherwise, use
				// the general-purpose mechanism. Remove this branch if
				// CoreException ever implements
				// a correct getCause() method.
				final CoreException ce = (CoreException) exception;
				cause = ce.getStatus().getException();
			} else {
				// use reflect instead of a direct call to getCause(), to allow
				// compilation against JCL Foundation (bug 80053)
				try {
					final Method causeMethod = exception.getClass().getMethod("getCause", new Class[0]); //$NON-NLS-1$
					final Object o = causeMethod.invoke(exception, new Object[0]);
					if (o instanceof Throwable) {
						cause = (Throwable) o;
					}
				} catch (final NoSuchMethodException e) {
					// PASS
				} catch (final IllegalArgumentException e) {
					// PASS
				} catch (final IllegalAccessException e) {
					// PASS
				} catch (final InvocationTargetException e) {
					// PASS
				}
			}

			if (cause == null) {
				cause = exception;
			}
		}

		return cause;
	}

	/**
	 * This method must not be called outside the workbench.
	 * 
	 * Utility method for creating status.
	 * 
	 * @param severity the severity
	 * @param message the message
	 * @param exception the exception
	 * 
	 * @return the i status
	 */
	public static IStatus newStatus(final int severity, final String message, final Throwable exception) {

		String statusMessage = message;
		if (message == null || message.trim().length() == 0) {
			if (exception.getMessage() == null) {
				statusMessage = exception.toString();
			} else {
				statusMessage = exception.getMessage();
			}
		}

		return new Status(severity, PrfUiPlugin.getPluginId(), severity, statusMessage, StatusUtil.getCause(exception));
	}

	/**
	 * This method must not be called outside the workbench.
	 * 
	 * Utility method for creating status.
	 * 
	 * @param children the children
	 * @param message the message
	 * @param exception the exception
	 * 
	 * @return the i status
	 */
	public static IStatus newStatus(final List< ? > children, final String message, final Throwable exception) {

		final List<Object> flatStatusCollection = new ArrayList<Object>();
		final Iterator< ? > iter = children.iterator();
		while (iter.hasNext()) {
			final IStatus currentStatus = (IStatus) iter.next();
			final Iterator< ? > childrenIter = StatusUtil.flatten(currentStatus).iterator();
			while (childrenIter.hasNext()) {
				flatStatusCollection.add(childrenIter.next());
			}
		}

		final IStatus[] stati = new IStatus[flatStatusCollection.size()];
		flatStatusCollection.toArray(stati);
		return StatusUtil.newStatus(stati, message, exception);
	}

	/**
	 * This method must not be called outside the workbench.
	 * 
	 * Utility method for handling status.
	 * 
	 * @param status the status
	 * @param hint the hint
	 * @param shell the shell
	 */
	public static void handleStatus(final IStatus status, final int hint, final Shell shell) {
		StatusManager.getManager().handle(status, hint);
	}

	/**
	 * This method must not be called outside the workbench.
	 * 
	 * Utility method for handling status.
	 * 
	 * @param e the e
	 * @param hint the hint
	 */
	public static void handleStatus(final Throwable e, final int hint) {
		StatusManager.getManager().handle(StatusUtil.newStatus(PrfUiPlugin.getPluginId(), e), hint);
	}

	/**
	 * This method must not be called outside the workbench.
	 * 
	 * Utility method for handling status.
	 * 
	 * @param message the message
	 * @param e the e
	 * @param hint the hint
	 */
	public static void handleStatus(final String message, final Throwable e, final int hint) {
		StatusManager.getManager().handle(StatusUtil.newStatus(PrfUiPlugin.getPluginId(), message, e), hint);
	}

	/**
	 * This method must not be called outside the workbench.
	 * 
	 * Utility method for handling status.
	 * 
	 * @param message the message
	 * @param e the e
	 * @param hint the hint
	 * @param shell the shell
	 */
	public static void handleStatus(final String message, final Throwable e, final int hint, final Shell shell) {
		StatusManager.getManager().handle(StatusUtil.newStatus(PrfUiPlugin.getPluginId(), message, e), hint);
	}

	/**
	 * This method must not be called outside the workbench.
	 * 
	 * Utility method for handling status.
	 * 
	 * @param status the status
	 * @param message the message
	 * @param hint the hint
	 */
	public static void handleStatus(final IStatus status, final String message, final int hint) {
		StatusManager.getManager().handle(StatusUtil.newStatus(status, message), hint);
	}

	/**
	 * This method must not be called outside the workbench.
	 * 
	 * Utility method for handling status.
	 * 
	 * @param status the status
	 * @param message the message
	 * @param hint the hint
	 * @param shell the shell
	 */
	public static void handleStatus(final IStatus status, final String message, final int hint, final Shell shell) {
		StatusManager.getManager().handle(StatusUtil.newStatus(status, message), hint);
	}

	/**
	 * This method must not be called outside the workbench.
	 * 
	 * Utility method for handling status.
	 * 
	 * @param message the message
	 * @param hint the hint
	 */
	public static void handleStatus(final String message, final int hint) {
		StatusUtil.handleStatus(message, null, hint);
	}

	/**
	 * This method must not be called outside the workbench.
	 * 
	 * Utility method for handling status.
	 * 
	 * @param message the message
	 * @param hint the hint
	 * @param shell the shell
	 */
	public static void handleStatus(final String message, final int hint, final Shell shell) {
		StatusUtil.handleStatus(message, null, hint);
	}
}
