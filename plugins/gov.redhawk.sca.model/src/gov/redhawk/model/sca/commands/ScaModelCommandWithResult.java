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

import org.eclipse.emf.ecore.EObject;

/**
 * @since 14.0
 */
public abstract class ScaModelCommandWithResult<RESULT> extends ScaModelCommand {
	private RESULT result;

	@Override
	public Collection< RESULT > getResult() {
		if (result == null) {
			return null;
		}
	    return Collections.singleton(result);
	}
	
	protected void setResult(RESULT val) {
		this.result = val;
	}
	
	/**
	 * 
	 * @param <T> The return type
	 * @param context The context to run the command within
	 * @param command The command to run
	 * @return The result of the command <b> NOTE </b> May be NULL if the command failed to execute.
	 */
	public static <T> T execute(EObject context, ScaModelCommandWithResult<T> command) {
		ScaModelCommand.execute(context, command);
		return command.result;
	}
}
