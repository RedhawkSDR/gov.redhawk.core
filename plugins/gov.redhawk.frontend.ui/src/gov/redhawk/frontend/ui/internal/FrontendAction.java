/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.frontend.ui.internal;

import gov.redhawk.frontend.ui.FrontEndUIActivator;
import gov.redhawk.frontend.ui.internal.section.FrontendSection;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.common.CommandException;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.ISources;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;

/**
 * 
 */
public class FrontendAction extends Action {

	private FrontendSection section;
	private String actionName;
	private String actionId;
	private String commandId;
	private ImageDescriptor imageDescriptor;
	
	public FrontendAction(FrontendSection theSection, String theActionName, String theActionId, String theCommandId, String iconPath) {
		section = theSection;
		actionName = theActionName;
		actionId = theActionId;
		commandId = theCommandId;
		imageDescriptor = FrontEndUIActivator.imageDescriptorFromPlugin(FrontEndUIActivator.PLUGIN_ID, iconPath);
	}
	
	@Override
	public String getId() {
		return actionId;
	}
	
	@Override
	public ImageDescriptor getImageDescriptor() {
		return imageDescriptor;
	}
	
	@Override
	public String getText() {
		return actionName;
	}
	
	@Override
	public void run() {
		final ICommandService commandService = (ICommandService) section.getInputPart()
				.getSite()
				.getService(ICommandService.class);
		Command command = null;
		if (commandService != null) {
			command = commandService.getCommand(commandId);
		}
		if (command == null) {
			return;
		}
		final IHandlerService handlerService = (IHandlerService) section.getInputPart().getSite().getService(IHandlerService.class);
		if (handlerService == null) {
			return;
		}
		EObject obj = section.getInput();
		IStructuredSelection selection = new StructuredSelection(obj);
		final ExecutionEvent evt;
		evt = handlerService.createExecutionEvent(command, null);
		((IEvaluationContext) evt.getApplicationContext()).addVariable(ISources.ACTIVE_CURRENT_SELECTION_NAME, selection);
		((IEvaluationContext) evt.getApplicationContext()).addVariable("gov.redhawk.frontend.propertySection", section);
		try {
			command.executeWithChecks(evt);
		} catch (CommandException e) {
			e.printStackTrace();
		}
	}
}
