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
package gov.redhawk.model.sca.commands;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;

/**
 * A command that can't be undone and should not dirty an editor.
 * @since 20.0
 */
public abstract class NonDirtyingCommand extends AbstractCommand implements AbstractCommand.NonDirtying {

	/**
	 * Run a writable command within the editing domain of the given EObject. If the EObject is not in an editing domain the command is ignored.
	 * @param context EObject to obtain editing domain from
	 * @param command Command to run
	 */
	public static void execute(EObject context, Command command) {
		Assert.isNotNull(context);
		Assert.isNotNull(command);
		TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(context);
		if (domain != null) {
			domain.getCommandStack().execute(command);
		}
	}

	@Override
	public String getLabel() {
		return "NonDirtyingCommand Command";
	}

	@Override
	public String getDescription() {
		return getLabel();
	}

	@Override
	protected boolean prepare() {
		return true;
	}

	@Override
	public boolean canUndo() {
		return false;
	}

	@Override
	public void redo() {
		throw new UnsupportedOperationException("redo");
	}

}
