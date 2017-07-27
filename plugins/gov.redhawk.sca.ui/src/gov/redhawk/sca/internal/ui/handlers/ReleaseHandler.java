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
package gov.redhawk.sca.internal.ui.handlers;

import gov.redhawk.sca.ui.actions.ReleaseAction;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * Handler that releases resources (via a call to <code>releaseObject()</code>).
 */
public class ReleaseHandler extends AbstractHandler implements IHandler {

	private final ReleaseAction action = new ReleaseAction();

	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getActiveMenuSelection(event);
		if (selection == null) {
			selection = HandlerUtil.getCurrentSelection(event);
		}
		if (selection instanceof IStructuredSelection) {
			final IStructuredSelection ss = (IStructuredSelection) selection;
			for (final Object obj : ss.toArray()) {
				release(obj, event);
			}
		} else {
			final IEditorPart editor = HandlerUtil.getActiveEditor(event);
			release(editor, event);
		}

		return null;
	}

	private void release(final Object obj, final ExecutionEvent event) {
		this.action.setContext(obj);
		this.action.setWindow(HandlerUtil.getActiveWorkbenchWindow(event));
		this.action.run();
	}

}
