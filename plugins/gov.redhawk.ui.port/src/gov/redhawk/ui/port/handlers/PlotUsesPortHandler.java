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
package gov.redhawk.ui.port.handlers;

import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.ui.port.Activator;
import gov.redhawk.ui.port.IPortHandler;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * @since 4.0
 */
public class PlotUsesPortHandler extends AbstractHandler {
	public static final String COMMAND_ID = "gov.redhawk.ui.port.command.plot";
	public static final String FILTER_PARAM = "gov.redhawk.ui.port.command.plot.filterparam";
	public static final String LIST_PARAM = "gov.redhawk.ui.port.command.plot.listparam";
	/**
	 * This variable is for the programmatically plotting a port. The value must
	 * be a list of ScaPorts
	 */
	public static final String PORTS_VAR = "gov.redhawk.ui.port.command.plot.portsvar";

	public PlotUsesPortHandler() {
		super();
	}

	public Object execute(final ExecutionEvent event) throws ExecutionException {
		// Get the currently selected item
		final ISelection sel = HandlerUtil.getActiveMenuSelection(event);
		// Get the filter parameter for the command
		final String var = event.getParameter(PlotUsesPortHandler.FILTER_PARAM);
		// Check if we should send each play command individually or as a group
		final boolean sendList = Boolean.parseBoolean(event.getParameter(PlotUsesPortHandler.LIST_PARAM));
		// Get the ports variable for the command - this is only used via programmatic command execution
		final Object ports = HandlerUtil.getVariable(event, PlotUsesPortHandler.PORTS_VAR);

		String message = null;
		final List<ScaUsesPort> portList = new ArrayList<ScaUsesPort>();

		// First, check if we're called from a menu
		if ((sel != null) && (sel instanceof IStructuredSelection)) {
			final IStructuredSelection ss = (IStructuredSelection) sel;
			for (final Object element : ss.toList()) {
				if (element instanceof ScaUsesPort) {
					final ScaUsesPort port = ((ScaUsesPort) element);
					portList.add(port);
				} else {
					final Object obj = Platform.getAdapterManager().getAdapter(element, ScaUsesPort.class);
					if (obj instanceof ScaUsesPort) {
						portList.add((ScaUsesPort) obj);
					}
				}
			}

			// If it's called programmatically, ports should be a list of ScaPort objects
		} else if ((ports != null) && (ports instanceof List)) {
			for (final Object element : (List< ? >) ports) {
				if (element instanceof ScaUsesPort) {
					final ScaUsesPort port = ((ScaUsesPort) element);
					portList.add(port);
				} else {
					final Object obj = Platform.getAdapterManager().getAdapter(element, ScaUsesPort.class);
					if (obj instanceof ScaUsesPort) {
						portList.add((ScaUsesPort) obj);
					}
				}
			}
		} else {
			message = "Unable to determine what to plot";
		}

		// Check if we found any ports to play
		if (portList.size() > 0) {
			message = plotPort(var, portList, sendList);
		}

		// Any errors will be put into message
		if (message != null) {
			MessageDialog.openInformation(HandlerUtil.getActiveShell(event), "Unable to Display", message);
		}
		return null;
	}

	private String plotPort(final String var, final List< ? extends ScaUsesPort> portList, final boolean sendList) {
		String message = "";

		if (!sendList) {
			for (final ScaUsesPort port : portList) {
				final String type = port.getRepid();
				final IPortHandler[] handlers = Activator.getPortHandlerRegistry().findPortHandlersByType(type);

				// Make sure we found a handler to play this port
				if (handlers.length > 0) {
					final List<ScaUsesPort> ports = new ArrayList<ScaUsesPort>();
					ports.add(port);
					for (final IPortHandler handler : handlers) {
						handler.connect(ports, var);
					}
				} else {
					message += "Unable to plot a port with the type: " + port.getRepid() + "\n";
				}
			}
		} else {
			final ScaUsesPort port = portList.get(0);
			final String type = port.getRepid();
			final IPortHandler[] handlers = Activator.getPortHandlerRegistry().findPortHandlersByType(type);

			// Make sure we found a handler to play this port
			if (handlers.length > 0) {
				for (final IPortHandler handler : handlers) {
					handler.connect(portList, var);
				}
			} else {
				message = "Unable to plot a port with the type: " + port.getRepid() + "\n";
			}
		}

		return (message.length() > 0) ? message : null; // SUPPRESS CHECKSTYLE AvoidInline
	}

}
