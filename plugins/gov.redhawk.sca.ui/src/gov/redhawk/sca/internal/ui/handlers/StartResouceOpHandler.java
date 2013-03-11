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
package gov.redhawk.sca.internal.ui.handlers;

import gov.redhawk.sca.ui.actions.StartAction;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class StartResouceOpHandler.
 */
public class StartResouceOpHandler extends AbstractHandler {

	private final StartAction action = new StartAction();

	/**
	 * {@inheritDoc}
	 */

	public Object execute(final ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getActiveMenuSelection(event);
		if (selection == null) {
			selection = HandlerUtil.getCurrentSelection(event);
		}
		if (selection instanceof IStructuredSelection) {
			final IStructuredSelection ss = (IStructuredSelection) selection;
			for (final Object obj : ss.toArray()) {
				start(obj);
			}
		} else {
			final IEditorPart editor = HandlerUtil.getActiveEditor(event);
			start(editor);
		}
		// TODO Auto-generated method stub
		return null;
	}

	private void start(final Object obj) {
		this.action.setContext(obj);
		this.action.run();
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public void setEnabled(final Object evaluationContext) {
		// TODO Auto-generated method stub
		super.setEnabled(evaluationContext);
	}

}
