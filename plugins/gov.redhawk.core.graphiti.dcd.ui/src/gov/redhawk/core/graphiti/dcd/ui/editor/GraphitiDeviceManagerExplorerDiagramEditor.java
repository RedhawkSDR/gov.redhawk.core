/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.core.graphiti.dcd.ui.editor;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.graphiti.ui.platform.IConfigurationProvider;

import gov.redhawk.core.graphiti.ui.diagram.providers.RuntimeContextMenuProvider;
import gov.redhawk.core.graphiti.ui.editor.AbstractGraphitiDiagramEditor;
import gov.redhawk.core.graphiti.ui.editor.RHDiagramBehavior;

public class GraphitiDeviceManagerExplorerDiagramEditor extends AbstractGraphitiDiagramEditor {

	private EditingDomain editingDomain;

	public GraphitiDeviceManagerExplorerDiagramEditor(EditingDomain editingDomain) {
		super(editingDomain);
		this.editingDomain = editingDomain;
		addContext("gov.redhawk.core.graphiti.dcd.ui.contexts.explorer");
	}

	@Override
	protected DiagramBehavior createDiagramBehavior() {
		return new RHDiagramBehavior(this, (TransactionalEditingDomain) editingDomain) {

			@Override
			protected ContextMenuProvider createContextMenuProvider() {
				EditPartViewer viewer = getDiagramContainer().getGraphicalViewer();
				ActionRegistry registry = getDiagramContainer().getActionRegistry();
				IConfigurationProvider configurationProvider = getConfigurationProvider();
				return new RuntimeContextMenuProvider(viewer, registry, configurationProvider);
			}

		};
	}

}
