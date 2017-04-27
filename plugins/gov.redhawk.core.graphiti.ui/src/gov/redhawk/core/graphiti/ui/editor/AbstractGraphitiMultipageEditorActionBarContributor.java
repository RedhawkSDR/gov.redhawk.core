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
package gov.redhawk.core.graphiti.ui.editor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.AlignmentRetargetAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.MatchHeightRetargetAction;
import org.eclipse.gef.ui.actions.MatchWidthRetargetAction;
import org.eclipse.gef.ui.actions.ZoomComboContributionItem;
import org.eclipse.gef.ui.actions.ZoomInRetargetAction;
import org.eclipse.gef.ui.actions.ZoomOutRetargetAction;
import org.eclipse.graphiti.platform.IPlatformImageConstants;
import org.eclipse.graphiti.ui.internal.action.ToggleContextButtonPadAction;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.RetargetAction;

import gov.redhawk.ui.editor.ScaMultipageActionBarContributor;

@SuppressWarnings("restriction")
public abstract class AbstractGraphitiMultipageEditorActionBarContributor extends ScaMultipageActionBarContributor {

	private ActionRegistry registry = new ActionRegistry();
	private List<String> diagramActionIds = new ArrayList<String>();
	private boolean actionsBuilt = false;
	private ZoomComboContributionItem zoomCombo;

	/**
	 * Build all actions to be used by the diagram. Actions <b>must</b> inherit from {@link RetargetAction}
	 */
	private void buildActions() {
		registry.registerAction((RetargetAction) ActionFactory.UNDO.create(PlatformUI.getWorkbench().getActiveWorkbenchWindow()));
		registry.registerAction((RetargetAction) ActionFactory.REDO.create(PlatformUI.getWorkbench().getActiveWorkbenchWindow()));
		registry.registerAction((RetargetAction) ActionFactory.SELECT_ALL.create(PlatformUI.getWorkbench().getActiveWorkbenchWindow()));

		registry.registerAction(new AlignmentRetargetAction(PositionConstants.LEFT));
		registry.registerAction(new AlignmentRetargetAction(PositionConstants.CENTER));
		registry.registerAction(new AlignmentRetargetAction(PositionConstants.RIGHT));
		registry.registerAction(new AlignmentRetargetAction(PositionConstants.TOP));
		registry.registerAction(new AlignmentRetargetAction(PositionConstants.MIDDLE));
		registry.registerAction(new AlignmentRetargetAction(PositionConstants.BOTTOM));

		registry.registerAction(new MatchWidthRetargetAction());
		registry.registerAction(new MatchHeightRetargetAction());

		RetargetAction toggleContextPadAction = new RetargetAction(ToggleContextButtonPadAction.ACTION_ID, ToggleContextButtonPadAction.TEXT,
			IAction.AS_CHECK_BOX);
		toggleContextPadAction.setImageDescriptor(GraphitiUi.getImageService().getPlatformImageDescriptorForId(IPlatformImageConstants.IMG_TOGGLE_PAD));
		registry.registerAction(toggleContextPadAction);

		registry.registerAction(new ZoomInRetargetAction());
		registry.registerAction(new ZoomOutRetargetAction());
		registry.registerAction(new RetargetAction(GEFActionConstants.TOGGLE_GRID_VISIBILITY, "&Grid", IAction.AS_CHECK_BOX));

		// Build a registry of action IDs and add part listeners
		@SuppressWarnings("unchecked")
		Iterator<RetargetAction> registryIterator = registry.getActions();
		while (registryIterator.hasNext()) {
			RetargetAction action = registryIterator.next();
			diagramActionIds.add(action.getId());
			getPage().addPartListener(action);
		}

		actionsBuilt = true;

		contributeToMenu(getActionBars().getMenuManager());
		contributeToToolBar(getActionBars().getToolBarManager());
	}

	@Override
	public void contributeToToolBar(final IToolBarManager toolBarManager) {
		super.contributeToToolBar(toolBarManager);

		if (!actionsBuilt) {
			return;
		}

		toolBarManager.add(getAction(ActionFactory.UNDO.getId()));
		toolBarManager.add(getAction(ActionFactory.REDO.getId()));
		toolBarManager.add(new Separator());

		toolBarManager.add(getAction(GEFActionConstants.ALIGN_LEFT));
		toolBarManager.add(getAction(GEFActionConstants.ALIGN_CENTER));
		toolBarManager.add(getAction(GEFActionConstants.ALIGN_RIGHT));
		toolBarManager.add(new Separator());

		toolBarManager.add(getAction(GEFActionConstants.ALIGN_TOP));
		toolBarManager.add(getAction(GEFActionConstants.ALIGN_MIDDLE));
		toolBarManager.add(getAction(GEFActionConstants.ALIGN_BOTTOM));
		toolBarManager.add(new Separator());

		toolBarManager.add(getAction(GEFActionConstants.MATCH_WIDTH));
		toolBarManager.add(getAction(GEFActionConstants.MATCH_HEIGHT));
		toolBarManager.add(new Separator());

		toolBarManager.add(getAction(ToggleContextButtonPadAction.ACTION_ID));
		toolBarManager.add(getAction(GEFActionConstants.ZOOM_OUT));
		toolBarManager.add(getAction(GEFActionConstants.ZOOM_IN));
		zoomCombo = new ZoomComboContributionItem(getPage());
		toolBarManager.add(zoomCombo);
		toolBarManager.add(new Separator());
	}

	protected RetargetAction getAction(String id) {
		return (RetargetAction) registry.getAction(id);
	}

	protected boolean getActionsBuilt() {
		return actionsBuilt;
	}

	@Override
	public void contributeToMenu(final IMenuManager menuManager) {
		super.contributeToMenu(menuManager);

		if (!actionsBuilt) {
			return;
		}

		MenuManager viewMenu = new MenuManager("&View", "view");
		viewMenu.add(getAction(GEFActionConstants.ZOOM_IN));
		viewMenu.add(getAction(GEFActionConstants.ZOOM_OUT));
		viewMenu.add(getAction(GEFActionConstants.TOGGLE_GRID_VISIBILITY));

		IMenuManager editMenu = menuManager.findMenuUsingPath(IWorkbenchActionConstants.M_EDIT);
		if (editMenu != null) {
			MenuManager alignments = new MenuManager("A&lign", "align");
			alignments.add(getAction(GEFActionConstants.ALIGN_LEFT));
			alignments.add(getAction(GEFActionConstants.ALIGN_CENTER));
			alignments.add(getAction(GEFActionConstants.ALIGN_RIGHT));
			alignments.add(new Separator());
			alignments.add(getAction(GEFActionConstants.ALIGN_TOP));
			alignments.add(getAction(GEFActionConstants.ALIGN_MIDDLE));
			alignments.add(getAction(GEFActionConstants.ALIGN_BOTTOM));
			alignments.add(new Separator());
			alignments.add(getAction(GEFActionConstants.MATCH_WIDTH));
			alignments.add(getAction(GEFActionConstants.MATCH_HEIGHT));
			editMenu.insertAfter(ActionFactory.SELECT_ALL.getId(), alignments);

			menuManager.insertAfter(IWorkbenchActionConstants.M_EDIT, viewMenu);
		} else {
			menuManager.add(viewMenu);
		}
	}

	@Override
	public void setActivePage(IEditorPart newEditor) {
		if (!(newEditor instanceof AbstractGraphitiDiagramEditor)) {
			if (actionsBuilt) {
				setActionsVisible(false);
			}
			return;
		}

		if (newEditor instanceof AbstractGraphitiDiagramEditor) {
			if (!actionsBuilt) {
				buildActions();
				actionsBuilt = false;
			}
			setActiveEditor(newEditor);
			setActionsVisible(true);

			// Assigns handlers diagram actions when the page is selected
			ActionRegistry globalActionRegistry = (ActionRegistry) newEditor.getAdapter(ActionRegistry.class);
			for (String id : diagramActionIds) {
				IAction action = (globalActionRegistry != null) ? globalActionRegistry.getAction(id) : null;
				getActionBars().setGlobalActionHandler(id, action);

				// This call forces the RetargetAction's to update their action handlers
				// Needed for instances where the initial editor page is not the diagram
				IWorkbenchPart workbenchPart = newEditor.getSite().getPart();
				if (workbenchPart != null) {
					getAction(id).partActivated(workbenchPart);
				}
			}

			// Need this to enable zoom combo when editor opens on a non-diagram page
			if (zoomCombo.getZoomManager() == null) {
				zoomCombo.setZoomManager((ZoomManager) newEditor.getAdapter(ZoomManager.class));
			}

			getActionBars().getToolBarManager().update(true);
			actionsBuilt = true;
		}
		getActionBars().updateActionBars();
	}

	/**
	 * Hide actions from ToolBar and Menu managers. For when we change from a diagram page to a non-diagram page.
	 */
	protected void setActionsVisible(final boolean setVisible) {
		IToolBarManager toolbarManager = getActionBars().getToolBarManager();
		IMenuManager menuManager = getActionBars().getMenuManager();

		@SuppressWarnings("unchecked")
		Iterator<RetargetAction> iter = registry.getActions();
		while (iter.hasNext()) {
			RetargetAction action = iter.next();
			IContributionItem toolBarItem = toolbarManager.find(action.getId());
			if (toolBarItem != null) {
				toolBarItem.setVisible(setVisible);
			}
		}
		zoomCombo.setVisible(setVisible);

		toolbarManager.update(true);
		menuManager.setVisible(setVisible);
	}

	@Override
	public void dispose() {
		for (int i = 0; i < diagramActionIds.size(); i++) {
			RetargetAction action = getAction(diagramActionIds.get(i));
			if (action != null) {
				getPage().removePartListener(action);
				action.dispose();
			}
		}

		registry.dispose();
		diagramActionIds = null;
		super.dispose();
	}

}
