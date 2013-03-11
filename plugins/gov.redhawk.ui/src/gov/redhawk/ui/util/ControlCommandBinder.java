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

import gov.redhawk.ui.RedhawkUiActivator;

import org.eclipse.core.commands.CommandEvent;
import org.eclipse.core.commands.ICommandListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;

/**
 * Utility class that binds SWT Controls to Commands.
 * 
 * @since 5.0
 */
public class ControlCommandBinder {

	private ControlCommandBinder() {
		//Prevent instantiation
	}

	/**
	 * Binds a command to a button widget;  the button enablement will be tied to the command enablement and 
	 * button selection will result in the active handler being called.
	 * 
	 * @param myButton the {@link Button} to bind to the specified command
	 * @param commandId the id of the {@link Command} to bind
	 */
	public static ControlCommandBinding bindButton(final Button myButton, final String commandId) {
		final ICommandService commandService = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
		ICommandListener listener = new ICommandListener() {

			private boolean enabled = false;

			//If command enablement has changed, update the button accordingly.
			public void commandChanged(final CommandEvent commandEvent) {
				if (commandEvent.isEnabledChanged()) {
					this.enabled = !this.enabled;
					myButton.setEnabled(this.enabled);
				}
			}
		};
		commandService.getCommand(commandId).addCommandListener(listener);

		//Execute the command if the button is pressed.
		myButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				try {
					((IHandlerService) PlatformUI.getWorkbench().getService(IHandlerService.class)).executeCommand(commandId, null);
				} catch (final Exception ex) {
					RedhawkUiActivator.logException(ex, "Unable to execute command: " + commandId);
				}
			}
		});
		
		return new ControlCommandBinding(commandId, listener);
	}
}
