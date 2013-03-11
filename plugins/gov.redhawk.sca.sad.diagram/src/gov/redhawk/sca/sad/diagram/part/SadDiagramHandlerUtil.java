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
package gov.redhawk.sca.sad.diagram.part;

import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;
import mil.jpeojtrs.sca.partitioning.ComponentPlacement;
import mil.jpeojtrs.sca.sad.SadComponentPlacement;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * @since 2.0
 */
public class SadDiagramHandlerUtil {
	private SadDiagramHandlerUtil() {
	}
	
	public static TransactionalEditingDomain getEditingDomain(final ISelection selection) {
		final IStructuredSelection structuredSelection = (IStructuredSelection) selection;
		if (structuredSelection.size() != 1) {
			return null;
		}
		if (structuredSelection.getFirstElement() instanceof GraphicalEditPart
		        && ((GraphicalEditPart) structuredSelection.getFirstElement()).getModel() instanceof View) {
			final GraphicalEditPart editPart = (GraphicalEditPart) structuredSelection.getFirstElement();
			final TransactionalEditingDomain editingDomain = editPart.getEditingDomain();
			return editingDomain;
		}
		return null;
	}
	
	public static SadComponentPlacement getComponentPlacementFromSelection(final ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			final IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() != 1) {
				return null;
			}
			if (structuredSelection.getFirstElement() instanceof GraphicalEditPart
			        && ((GraphicalEditPart) structuredSelection.getFirstElement()).getModel() instanceof View) {
				final GraphicalEditPart editPart = (GraphicalEditPart) structuredSelection.getFirstElement();
				final View view = (View) editPart.getModel();
				
				SadComponentPlacement cp = null;

				if (view.getElement() instanceof ComponentPlacement) {
					return (SadComponentPlacement) view.getElement();
				} else if (view.getElement() instanceof ComponentInstantiation) {
					return (SadComponentPlacement) view.getElement().eContainer();
				} else {
					return null;
				}
			}
		}
		return null;
	}
	
}
