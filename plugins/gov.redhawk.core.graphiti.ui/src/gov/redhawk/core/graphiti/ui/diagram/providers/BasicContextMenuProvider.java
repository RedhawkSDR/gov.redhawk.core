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
package gov.redhawk.core.graphiti.ui.diagram.providers;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.graphiti.ui.editor.DiagramEditorContextMenuProvider;
import org.eclipse.graphiti.ui.platform.IConfigurationProvider;
import org.eclipse.jface.action.IMenuManager;

/**
 * This Graphiti diagram editor supports the chalkboard waveform editor and the SAD XML editor.
 */
public class BasicContextMenuProvider extends DiagramEditorContextMenuProvider {

	public BasicContextMenuProvider(EditPartViewer viewer, ActionRegistry registry, IConfigurationProvider configurationProvider) {
		super(viewer, registry, configurationProvider);
	}

	/**
	 * {@inheritDoc}
	 * <p/>
	 * This is a modified version of {@link DiagramEditorContextMenuProvider#buildContextMenu(IMenuManager)}. It omits
	 * adding the print and export diagram (save as image) actions.
	 */
	@Override
	public void buildContextMenu(IMenuManager manager) {
		GEFActionConstants.addStandardActionGroups(manager);

		addDefaultMenuGroupUndo(manager);
		//addDefaultMenuGroupSave(manager);
		addDefaultMenuGroupEdit(manager);
		//addDefaultMenuGroupPrint(manager);
		addDefaultMenuGroupRest(manager);
	}
}
