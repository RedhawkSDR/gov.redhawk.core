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

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.Callable;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;

import gov.redhawk.model.sca.ScaModelPlugin;

/**
 * @since 14.0
 */
public abstract class ScaModelCommandWithResult< RESULT > extends ScaModelCommand {
	private RESULT result;

	@Override
	public Collection<RESULT> getResult() {
		if (result == null) {
			return null;
		}
		return Collections.singleton(result);
	}

	protected void setResult(RESULT val) {
		this.result = val;
	}

	/**
	 * Runs a command which changes the model and also returns a result.
	 * @param <T> The return type
	 * @param context The context in which to run the command
	 * @param command The command to run
	 * @return The result of the command, or null if there was an error
	 */
	public static < T > T execute(EObject context, ScaModelCommandWithResult<T> command) {
		ScaModelCommand.execute(context, command);
		return command.result;
	}

	/**
	 * Runs a command which changes the model and also returns a result.
	 * @param <T> The return type
	 * @param context The context in which to run the command
	 * @param callable The command to run
	 * @return The result of the command, or null if there was an error
	 * @since 21.1
	 */
	public static < T > T execute(EObject context, Callable<T> callable) {
		TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(context);
		ScaModelCommandWithResult<T> command = new ScaModelCommandWithResult<T>() {
			@Override
			public void execute() {
				try {
					T callableResult = callable.call();
					setResult(callableResult);
				} catch (Exception e) { // SUPPRESS CHECKSTYLE Logged
					ScaModelPlugin.logError("Error while executing model command", e);
				}
			}
		};
		domain.getCommandStack().execute(command);
		return command.result;
	}
}
