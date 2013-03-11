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
package gov.redhawk.ui.util;

import org.eclipse.core.commands.ICommandListener;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;

/**
 * A binding between a {@link Control} and a {@link Command}
 * 
 * @since 5.0
 */
public class ControlCommandBinding {

	private ICommandListener listener;
	private String commandId;

	/**
	 * Creates a new ControlCommandBinding.
	 * 
	 * @param commandId the commandId associated with the binding
	 * @param listener the listener associated with the binding
	 */
	public ControlCommandBinding(String commandId, ICommandListener listener) {
		this.commandId = commandId;
		this.listener = listener;
	}

	/**
	 * Gets the Id off the command.
	 * 
	 * @return the commandId associated with the binding
	 */
	public String getCommandId() {
		return this.commandId;
	}

	/**
	 * Removes the listener from the command.
	 */
	public void dispose() {
		if (listener != null) {
			final ICommandService commandService = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
			commandService.getCommand(this.commandId).removeCommandListener(this.listener);
		}
	}
}
