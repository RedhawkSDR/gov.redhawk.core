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
// BEGIN GENERATED CODE

package gov.redhawk.diagram.edit.helpers;

import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelper;
import org.eclipse.gmf.runtime.emf.type.core.edithelper.IEditHelperAdvice;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;

/**
 *
 * @since 3.0
 */
public class PartitioningBaseEditHelper extends AbstractEditHelper {

	/**
	*
	*/
	public static final String EDIT_POLICY_COMMAND = "edit policy command"; //$NON-NLS-1$

	/**
	*
	*/
	public static final String CONTEXT_ELEMENT_TYPE = "context element type"; //$NON-NLS-1$

	/**
	*
	*/
	@Override
	protected IEditHelperAdvice[] getEditHelperAdvice(final IEditCommandRequest req) {
		if (req.getParameter(PartitioningBaseEditHelper.CONTEXT_ELEMENT_TYPE) instanceof IElementType) {
			return ElementTypeRegistry.getInstance().getEditHelperAdvice((IElementType) req.getParameter(PartitioningBaseEditHelper.CONTEXT_ELEMENT_TYPE));
		}
		return super.getEditHelperAdvice(req);
	}

	/**
	*
	*/
	@Override
	protected ICommand getInsteadCommand(final IEditCommandRequest req) {
		final ICommand epCommand = (ICommand) req.getParameter(PartitioningBaseEditHelper.EDIT_POLICY_COMMAND);
		req.setParameter(PartitioningBaseEditHelper.EDIT_POLICY_COMMAND, null);
		final ICommand ehCommand = super.getInsteadCommand(req);
		if (epCommand == null) {
			return ehCommand;
		}
		if (ehCommand == null) {
			return epCommand;
		}
		final CompositeCommand command = new CompositeCommand(null);
		command.add(epCommand);
		command.add(ehCommand);
		return command;
	}

	/**
	*
	*/
	@Override
	protected ICommand getCreateCommand(final CreateElementRequest req) {
		return null;
	}

	/**
	*
	*/
	@Override
	protected ICommand getCreateRelationshipCommand(final CreateRelationshipRequest req) {
		return null;
	}

	/**
	*
	*/
	@Override
	protected ICommand getDestroyElementCommand(final DestroyElementRequest req) {
		return null;
	}

	/**
	*
	*/
	@Override
	protected ICommand getDestroyReferenceCommand(final DestroyReferenceRequest req) {
		return null;
	}
}
