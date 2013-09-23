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
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;

/**
 * @since 14.0
 */
public abstract class ScaModelCommand extends AbstractCommand {

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
		} else {
			final TransactionalEditingDomain editingDomain = TransactionalEditingDomain.Registry.INSTANCE.getEditingDomain("gov.redhawk.sca.editingDomain");
			if (editingDomain != null) {
				editingDomain.getCommandStack().execute(command);
			} else {
				command.execute();
			}
		}
	}

	@Override
	public String getLabel() {
		return "SCA Model Protected Command";
	}

	@Override
	public String getDescription() {
		return getLabel();
	}

	/**
	 * Run a <b>read only</b> operation within the model.  This ensure the state of the model is not altered during the read.
	 * @param <T> The return type
	 * @param context The EObject to get the editing domain from
	 * @param runnable The <b>read only</b> operation to run
	 * @return The optional return value
	 * @throws InterruptedException
	 */
	public static < T > T runExclusive(EObject context, RunnableWithResult<T> runnable) throws InterruptedException {
		Assert.isNotNull(context);
		Assert.isNotNull(runnable);
		TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(context);
		if (domain != null) {
			return TransactionUtil.runExclusive(domain, runnable);
		} else {
			runnable.run();
			return runnable.getResult();
		}
//		return null;
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
