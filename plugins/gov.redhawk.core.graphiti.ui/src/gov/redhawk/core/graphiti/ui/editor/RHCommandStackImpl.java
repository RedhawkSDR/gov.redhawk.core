/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.core.graphiti.ui.editor;

import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.workspace.EMFCommandOperation;
import org.eclipse.graphiti.ui.internal.editor.GFWorkspaceCommandStackImpl;

/**
 * Ensures that {@link AbstractCommand.NonDirtying} commands don't make the editor dirty.
 */
@SuppressWarnings("restriction")
/* package */ class RHCommandStackImpl extends GFWorkspaceCommandStackImpl {

	public RHCommandStackImpl(IOperationHistory history) {
		super(history);
	}

	@Override
	public boolean isSaveNeeded() {
		// If the last command was NonDirtying, return false so that we don't dirty the editor
		IUndoableOperation nextUndoableOperation = getOperationHistory().getUndoOperation(getDefaultUndoContext());
		Command command = null;
		if (nextUndoableOperation instanceof EMFCommandOperation) {
			command = ((EMFCommandOperation) nextUndoableOperation).getCommand();
			if (command instanceof AbstractCommand.NonDirtying) {
				return false;
			}
		}

		return super.isSaveNeeded();
	}

}
