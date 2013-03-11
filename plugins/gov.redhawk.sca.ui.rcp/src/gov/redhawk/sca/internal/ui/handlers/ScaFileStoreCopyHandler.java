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

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * 
 */
public class ScaFileStoreCopyHandler extends AbstractHandler {

	/**
	 * {@inheritDoc}
	 */
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getActiveMenuSelection(event);
		if (selection == null) {
			selection = HandlerUtil.getCurrentSelection(event);
		}
		if (selection instanceof IStructuredSelection) {
			final Clipboard clipboard = new Clipboard(HandlerUtil.getActiveShell(event).getDisplay());
			clipboard.setContents(new Object[] {
			        selection, ((IStructuredSelection) selection).toArray()
			}, new Transfer[] {
			        LocalSelectionTransfer.getTransfer(), LocalTransfer.getInstance()
			});
			clipboard.dispose();
		}
		return null;
	}

}
