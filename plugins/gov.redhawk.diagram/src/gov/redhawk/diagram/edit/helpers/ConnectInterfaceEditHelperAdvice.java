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
package gov.redhawk.diagram.edit.helpers;

import mil.jpeojtrs.sca.partitioning.ConnectInterface;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelperAdvice;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;

/**
 * @since 3.0
 * 
 */
public class ConnectInterfaceEditHelperAdvice extends AbstractEditHelperAdvice {

	/**
	 * @since 4.0
	 */
	public static final String CONFIGURE_OPTIONS_ID = "id";

	private static class ConfigureCommand extends AbstractTransactionalCommand {

		private final ConfigureRequest request;

		public ConfigureCommand(final ConfigureRequest req) {
			super(req.getEditingDomain(), "Create default ID in Connect Interface", null);
			this.request = req;
		}

		@Override
		protected CommandResult doExecuteWithResult(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
			final Object obj = this.request.getElementToConfigure();
			final ConnectInterface< ? , ? , ? > ci = (ConnectInterface< ? , ? , ? >) obj;
			ci.setId((String) this.request.getParameter(ConnectInterfaceEditHelperAdvice.CONFIGURE_OPTIONS_ID));

			return CommandResult.newOKCommandResult();
		}
	}

	@Override
	protected ICommand getAfterConfigureCommand(final ConfigureRequest request) {
		return new ConfigureCommand(request);
	}
}
