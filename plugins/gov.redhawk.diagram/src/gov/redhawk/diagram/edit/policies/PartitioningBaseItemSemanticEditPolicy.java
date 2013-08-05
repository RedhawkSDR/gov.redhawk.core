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

package gov.redhawk.diagram.edit.policies;

import gov.redhawk.diagram.edit.helpers.PartitioningBaseEditHelper;
import gov.redhawk.diagram.part.PartitioningVisualIDRegistry;
import gov.redhawk.diagram.providers.PartitioningElementTypes;

import java.util.Iterator;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.command.ICompositeCommand;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.SemanticEditPolicy;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.GetEditContextRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.MoveRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated NOT
 * @since 3.0
 */
public class PartitioningBaseItemSemanticEditPolicy extends SemanticEditPolicy {

	/**
	* Extended request data key to hold editpart visual id.
	* @generated NOT
	*/
	public static final String VISUAL_ID_KEY = "visual_id"; //$NON-NLS-1$

	/**
	* @generated NOT
	*/
	private final IElementType myElementType;

	protected final PartitioningElementTypes elementTypes;

	protected final PartitioningVisualIDRegistry visualIdRegistry;

	/**
	* @generated NOT
	*/
	protected PartitioningBaseItemSemanticEditPolicy(final IElementType elementType, final PartitioningElementTypes elementTypes,
	        final PartitioningVisualIDRegistry visualIdRegistry) {
		this.myElementType = elementType;
		this.elementTypes = elementTypes;
		this.visualIdRegistry = visualIdRegistry;
	}

	/**
	* Extended request data key to hold editpart visual id.
	* Add visual id of edited editpart to extended data of the request
	* so command switch can decide what kind of diagram element is being edited.
	* It is done in those cases when it's not possible to deduce diagram
	* element kind from domain element.
	* 
	* @generated NOT
	*/
	@SuppressWarnings("unchecked")
	@Override
	public Command getCommand(final Request request) {
		if (request instanceof ReconnectRequest) {
			final Object view = ((ReconnectRequest) request).getConnectionEditPart().getModel();
			if (view instanceof View) {
				final Integer id = new Integer(this.visualIdRegistry.getVisualID((View) view));
				request.getExtendedData().put(VISUAL_ID_KEY, id);
			}
		}
		return super.getCommand(request);
	}

	/**
	* Returns visual id from request parameters.
	* @generated NOT
	*/
	protected int getVisualID(final IEditCommandRequest request) {
		final Object id = request.getParameter(VISUAL_ID_KEY);
		return id instanceof Integer ? ((Integer) id).intValue() : -1;
	}

	/**
	* @generated NOT
	*/
	@Override
	protected Command getSemanticCommand(final IEditCommandRequest request) {
		final IEditCommandRequest completedRequest = completeRequest(request);
		Command semanticCommand = getSemanticCommandSwitch(completedRequest);
		semanticCommand = getEditHelperCommand(completedRequest, semanticCommand);
		if (completedRequest instanceof DestroyRequest) {
			final DestroyRequest destroyRequest = (DestroyRequest) completedRequest;
			return shouldProceed(destroyRequest) ? addDeleteViewCommand(semanticCommand, destroyRequest) : null;
		}
		return semanticCommand;
	}

	/**
	* @generated NOT
	*/
	protected Command addDeleteViewCommand(final Command mainCommand, final DestroyRequest completedRequest) {
		final Command deleteViewCommand = getGEFWrapper(new DeleteCommand(getEditingDomain(), (View) getHost().getModel()));
		return mainCommand == null ? deleteViewCommand : mainCommand.chain(deleteViewCommand);
	}

	/**
	* @generated NOT
	*/
	private Command getEditHelperCommand(final IEditCommandRequest request, final Command editPolicyCommand) {
		if (editPolicyCommand != null) {
			final ICommand command = editPolicyCommand instanceof ICommandProxy ? ((ICommandProxy) editPolicyCommand).getICommand() : new CommandProxy(
			        editPolicyCommand);
			request.setParameter(PartitioningBaseEditHelper.EDIT_POLICY_COMMAND, command);
		}
		final IElementType requestContextElementType = getContextElementType(request);
		request.setParameter(PartitioningBaseEditHelper.CONTEXT_ELEMENT_TYPE, requestContextElementType);
		ICommand command = requestContextElementType.getEditCommand(request);
		request.setParameter(PartitioningBaseEditHelper.EDIT_POLICY_COMMAND, null);
		request.setParameter(PartitioningBaseEditHelper.CONTEXT_ELEMENT_TYPE, null);
		if (command != null) {
			if (!(command instanceof CompositeTransactionalCommand)) {
				command = new CompositeTransactionalCommand(getEditingDomain(), command.getLabel()).compose(command);
			}
			return new ICommandProxy(command);
		}
		return editPolicyCommand;
	}

	/**
	* @generated NOT
	*/
	private IElementType getContextElementType(final IEditCommandRequest request) {
		final IElementType requestContextElementType = this.elementTypes.getElementType(getVisualID(request));
		return requestContextElementType != null ? requestContextElementType : this.myElementType;
	}

	/**
	* @generated NOT
	*/
	protected Command getSemanticCommandSwitch(final IEditCommandRequest req) {
		if (req instanceof CreateRelationshipRequest) {
			return getCreateRelationshipCommand((CreateRelationshipRequest) req);
		} else if (req instanceof CreateElementRequest) {
			return getCreateCommand((CreateElementRequest) req);
		} else if (req instanceof ConfigureRequest) {
			return getConfigureCommand((ConfigureRequest) req);
		} else if (req instanceof DestroyElementRequest) {
			return getDestroyElementCommand((DestroyElementRequest) req);
		} else if (req instanceof DestroyReferenceRequest) {
			return getDestroyReferenceCommand((DestroyReferenceRequest) req);
		} else if (req instanceof DuplicateElementsRequest) {
			return getDuplicateCommand((DuplicateElementsRequest) req);
		} else if (req instanceof GetEditContextRequest) {
			return getEditContextCommand((GetEditContextRequest) req);
		} else if (req instanceof MoveRequest) {
			return getMoveCommand((MoveRequest) req);
		} else if (req instanceof ReorientReferenceRelationshipRequest) {
			return getReorientReferenceRelationshipCommand((ReorientReferenceRelationshipRequest) req);
		} else if (req instanceof ReorientRelationshipRequest) {
			return getReorientRelationshipCommand((ReorientRelationshipRequest) req);
		} else if (req instanceof SetRequest) {
			return getSetCommand((SetRequest) req);
		}
		return null;
	}

	/**
	* @generated NOT
	*/
	protected Command getConfigureCommand(final ConfigureRequest req) {
		return null;
	}

	/**
	* @generated NOT
	*/
	protected Command getCreateRelationshipCommand(final CreateRelationshipRequest req) {
		return null;
	}

	/**
	* @generated NOT
	*/
	protected Command getCreateCommand(final CreateElementRequest req) {
		return null;
	}

	/**
	* @generated NOT
	*/
	protected Command getSetCommand(final SetRequest req) {
		return null;
	}

	/**
	* @generated NOT
	*/
	protected Command getEditContextCommand(final GetEditContextRequest req) {
		return null;
	}

	/**
	* @generated NOT
	*/
	protected Command getDestroyElementCommand(final DestroyElementRequest req) {
		return null;
	}

	/**
	* @generated NOT
	*/
	protected Command getDestroyReferenceCommand(final DestroyReferenceRequest req) {
		return null;
	}

	/**
	* @generated NOT
	*/
	protected Command getDuplicateCommand(final DuplicateElementsRequest req) {
		return null;
	}

	/**
	* @generated NOT
	*/
	protected Command getMoveCommand(final MoveRequest req) {
		return null;
	}

	/**
	* @generated NOT
	*/
	protected Command getReorientReferenceRelationshipCommand(final ReorientReferenceRelationshipRequest req) {
		return UnexecutableCommand.INSTANCE;
	}

	/**
	* @generated NOT
	*/
	protected Command getReorientRelationshipCommand(final ReorientRelationshipRequest req) {
		return UnexecutableCommand.INSTANCE;
	}

	/**
	* @generated NOT
	*/
	protected final Command getGEFWrapper(final ICommand cmd) {
		return new ICommandProxy(cmd);
	}

	/**
	* Returns editing domain from the host edit part.
	* @generated NOT
	*/
	protected TransactionalEditingDomain getEditingDomain() {
		return ((IGraphicalEditPart) getHost()).getEditingDomain();
	}

	/**
	* Clean all shortcuts to the host element from the same diagram
	* @generated NOT
	*/
	protected void addDestroyShortcutsCommand(final ICompositeCommand cmd, final View view) {
		assert view.getEAnnotation("Shortcut") == null; //$NON-NLS-1$
		for (final Iterator< ? > it = view.getDiagram().getChildren().iterator(); it.hasNext();) {
			final View nextView = (View) it.next();
			if (nextView.getEAnnotation("Shortcut") == null || !nextView.isSetElement() || nextView.getElement() != view.getElement()) { //$NON-NLS-1$
				continue;
			}
			cmd.add(new DeleteCommand(getEditingDomain(), nextView));
		}
	}

}
