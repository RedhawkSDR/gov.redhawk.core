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
package gov.redhawk.core.graphiti.ui.editor;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer.Delegate;
import org.eclipse.graphiti.ui.editor.DefaultMarkerBehavior;
import org.eclipse.graphiti.ui.editor.DefaultRefreshBehavior;
import org.eclipse.graphiti.ui.editor.DefaultUpdateBehavior;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.graphiti.ui.editor.IDiagramContainerUI;
import org.eclipse.graphiti.ui.editor.IDiagramEditorInput;

public class RHDiagramBehavior extends DiagramBehavior {

	private TransactionalEditingDomain editingDomain;

	public RHDiagramBehavior(IDiagramContainerUI diagramContainer, TransactionalEditingDomain editingDomain) {
		super(diagramContainer);
		this.editingDomain = editingDomain;
	}

	@Override
	public DefaultMarkerBehavior createMarkerBehavior() {
		// Override Marker behavior because it modifies the underlying sad resource
		// and the user will be prompted if they would like to replace their file with what's on disk
		return new DefaultMarkerBehavior(this) {

			public Diagnostic analyzeResourceProblems(Resource resource, Exception exception) {
				return Diagnostic.OK_INSTANCE;
			}
		};

	};

	@Override
	protected DefaultRefreshBehavior createRefreshBehavior() {
		// Run refreshes in the EMF editing domain
		return new SynchronizedRefreshBehavior(this);
	}

	@Override
	protected DefaultUpdateBehavior createUpdateBehavior() {
		return new DefaultUpdateBehavior(this) {

			@Override
			protected void createEditingDomain(IDiagramEditorInput input) {
				// We need to provide our own editing domain so that all editors are working on the same resource.
				// In order to work with a Graphiti diagram, our form creates an editing domain with the Graphiti
				// supplied Command stack.
				initializeEditingDomain(editingDomain);
			}

			@Override
			protected boolean handleDirtyConflict() {
				return true;
			}

			@Override
			protected Delegate createWorkspaceSynchronizerDelegate() {
				return null;
			}

			@Override
			protected void closeContainer() {
			}

			@Override
			protected void disposeEditingDomain() {
			}

		};
	}
}
