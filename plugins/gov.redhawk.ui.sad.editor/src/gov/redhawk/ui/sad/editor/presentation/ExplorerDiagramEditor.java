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
package gov.redhawk.ui.sad.editor.presentation;

import gov.redhawk.sca.sad.diagram.edit.parts.SadComponentPlacementCompartmentEditPart;
import gov.redhawk.sca.sad.diagram.part.SadDiagramEditor;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentPlacementEditPart;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.SemanticEditPolicy;
import org.eclipse.swt.widgets.Composite;

/**
 * 
 */
public class ExplorerDiagramEditor extends SadDiagramEditor {

	public ExplorerDiagramEditor() {
		super(false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createPartControl(final Composite parent) {
		super.createPartControl(parent);

		for (final Object obj : getDiagramEditPart().getChildren()) {
			if (obj != null && obj instanceof ComponentPlacementEditPart) {
				final ComponentPlacementEditPart compPart = (ComponentPlacementEditPart) obj;
				compPart.installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new SemanticEditPolicy() {
					@Override
					protected boolean shouldProceed(final org.eclipse.gmf.runtime.emf.type.core.requests.DestroyRequest destroyRequest) {
						return false;
					};
				});

				for (final Object obj2 : compPart.getChildren()) {
					if (obj2 != null && obj2 instanceof SadComponentPlacementCompartmentEditPart) {
						final SadComponentPlacementCompartmentEditPart compartPart = (SadComponentPlacementCompartmentEditPart) obj2;
						compartPart.disableEditMode();
					}
				}
			}
		}
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public void doSave(final IProgressMonitor progressMonitor) {
		throw new UnsupportedOperationException();
	}
}
