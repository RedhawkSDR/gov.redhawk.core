/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package gov.redhawk.logging.ui.eventconsole;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.IConsolePageParticipant;
import org.eclipse.ui.part.IPageBookViewPage;

/**
 * Contributes {@link StopEventConsoleAction} to the {@link EventConsole} via extension point.
 */
public class EventConsolePageParticipant implements IConsolePageParticipant {

	private StopEventConsoleAction stopEventTailAction;

	@Override
	public < T > T getAdapter(Class<T> adapter) {
		return null;
	}

	@Override
	public void init(IPageBookViewPage page, IConsole console) {
		// Create action
		stopEventTailAction = new StopEventConsoleAction(console);

		// Contribute action
		IActionBars actionBars = page.getSite().getActionBars();
		IToolBarManager toolbarMgr = actionBars.getToolBarManager();
		toolbarMgr.appendToGroup(IConsoleConstants.LAUNCH_GROUP, stopEventTailAction);
		actionBars.updateActionBars();
	}

	@Override
	public void dispose() {
		stopEventTailAction = null;
	}

	@Override
	public void activated() {
		// PASS
	}

	@Override
	public void deactivated() {
		// PASS
	}

}
