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
* Identification: $Revision: 4927 $
*/

package gov.redhawk.diagram.edit.policies;

import gov.redhawk.diagram.part.PartitioningVisualIDRegistry;
import gov.redhawk.diagram.providers.PartitioningElementTypes;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @since 3.0
 * 
 */
public class DomainFinderItemSemanticEditPolicy extends PartitioningBaseItemSemanticEditPolicy {

	/**
	* 
	*/
	public DomainFinderItemSemanticEditPolicy(final PartitioningElementTypes elementTypes, final PartitioningVisualIDRegistry visualIdRegistry) {
		super(PartitioningElementTypes.DomainFinder, elementTypes, visualIdRegistry);
	}

	/**
	* 
	*/
	@Override
    public Command getDestroyElementCommand(final DestroyElementRequest req) {
		final View view = (View) getHost().getModel();
		final CompositeTransactionalCommand cmd = new CompositeTransactionalCommand(getEditingDomain(), null);
		cmd.setTransactionNestingEnabled(false);
		final EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
		if (annotation == null) {
			// there are indirectly referenced children, need extra commands: false
			addDestroyShortcutsCommand(cmd, view);
			// delete host element
			cmd.add(new DestroyElementCommand(req));
		} else {
			cmd.add(new DeleteCommand(getEditingDomain(), view));
		}
		return getGEFWrapper(cmd.reduce());
	}

}
