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
package gov.redhawk.core.graphiti.ui.diagram.providers;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.graphiti.ui.platform.IConfigurationProvider;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;

import gov.redhawk.core.graphiti.ui.ext.RHContainerShape;

public class RuntimeContextMenuProvider extends BasicContextMenuProvider {

	private static final String GROUP_CONTROL_ID = "group.control"; //$NON-NLS-1$
	private static final String DELETE_ID = "delete"; //$NON-NLS-1$

	/**
	 * @param viewer
	 * @param registry
	 * @param configurationProvider
	 */
	public RuntimeContextMenuProvider(EditPartViewer viewer, ActionRegistry registry, IConfigurationProvider configurationProvider) {
		super(viewer, registry, configurationProvider);
	}

	/**
	 * Method needed to change text of DeleteAction from "Delete" to "Release" if component is selected
	 */
	@Override
	public void buildContextMenu(IMenuManager manager) {
		super.buildContextMenu(manager);

		// Insert the control group (contains start, stop, etc)
		manager.insertBefore(GEFActionConstants.GROUP_EDIT, new Separator(GROUP_CONTROL_ID));

		adjustDeleteToRelease(manager);
	}

	/**
	 * Renames the delete action to release, and moves it to the end of the control group
	 * @param manager
	 */
	private void adjustDeleteToRelease(IMenuManager manager) {
		// Ensure selection is RHContainerShape(s)
		ISelection selection = this.getViewer().getSelection();
		if (!(selection instanceof StructuredSelection)) {
			return;
		}
		StructuredSelection ss = (StructuredSelection) selection;
		for (Object selectedObj : ss.toList()) {
			if (!(selectedObj instanceof AbstractEditPart)) {
				return;
			}
			AbstractEditPart editPart = (AbstractEditPart) selectedObj;
			if (!(editPart.getModel() instanceof RHContainerShape)) {
				return;
			}
		}

		// Find delete action
		IContributionItem deleteContrib = manager.find(DELETE_ID);
		if (deleteContrib == null || !(deleteContrib instanceof ActionContributionItem)) {
			return;
		}
		IAction deleteAction = ((ActionContributionItem) deleteContrib).getAction();

		// Rename
		deleteAction.setText("Release");

		// Place at the end of the control group
		manager.remove(deleteContrib);
		manager.appendToGroup(GROUP_CONTROL_ID, deleteContrib);
	}
}
