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
package gov.redhawk.core.graphiti.sad.ui.editor;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.graphiti.ui.platform.IConfigurationProvider;
import org.eclipse.jface.util.TransferDropTargetListener;

import gov.redhawk.core.graphiti.ui.diagram.providers.RuntimeContextMenuProvider;
import gov.redhawk.core.graphiti.ui.editor.AbstractGraphitiDiagramEditor;

public class GraphitiWaveformExplorerDiagramEditor extends AbstractGraphitiDiagramEditor {

	public GraphitiWaveformExplorerDiagramEditor(EditingDomain editingDomain) {
		super(editingDomain);
		addContext("gov.redhawk.ide.graphiti.sad.ui.contexts.diagram");
		addContext("gov.redhawk.ide.graphiti.sad.ui.contexts.sandbox");
	}

	@Override
	protected TransferDropTargetListener createDropTargetListener(GraphicalViewer viewer, DiagramBehavior behavior) {
		return null;
	}

	@Override
	protected ContextMenuProvider createContextMenuProvider(EditPartViewer viewer, ActionRegistry registry, IConfigurationProvider configurationProvider) {
		return new RuntimeContextMenuProvider(viewer, registry, configurationProvider);
	}
}
