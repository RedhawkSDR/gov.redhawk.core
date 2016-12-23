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

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchActionConstants;

import gov.redhawk.sca.ui.actions.ReleaseAction;
import gov.redhawk.sca.ui.actions.StartAction;
import gov.redhawk.sca.ui.actions.StopAction;

public class GraphitiWaveformRuntimeActionBarContributor extends GraphitiWaveformMultipageEditorActionBarContributor {

	private final StartAction startAction = new StartAction();
	private final StopAction stopAction = new StopAction();
	private final ReleaseAction releaseAction = new ReleaseAction();
	private final String[] redhawkActions = { startAction.getId(), stopAction.getId(), releaseAction.getId() };

	public GraphitiWaveformRuntimeActionBarContributor() {
		this.startAction.setToolTipText("Start Waveform");
		this.stopAction.setToolTipText("Stop Waveform");
		this.releaseAction.setToolTipText("Release Waveform");
	}

	@Override
	public void setActiveEditor(final IEditorPart part) {
		super.setActiveEditor(part);
		this.startAction.setContext(part);
		this.stopAction.setContext(part);
		this.releaseAction.setContext(part);
	}

	@Override
	public void setActivePage(IEditorPart newEditor) {
		super.setActivePage(newEditor);
		if (newEditor instanceof GraphitiWaveformExplorerDiagramEditor) {
			startAction.setContext(newEditor.getSite().getPart());
			stopAction.setContext(newEditor.getSite().getPart());
			releaseAction.setContext(newEditor.getSite().getPart());
		}
	}

	@Override
	protected void setActionsVisible(boolean setVisible) {
		IToolBarManager toolbarManager = getActionBars().getToolBarManager();
		for (String id : redhawkActions) {
			IContributionItem toolBarItem = toolbarManager.find(id);
			if (toolBarItem != null) {
				toolBarItem.setVisible(setVisible);
			}
		}

		super.setActionsVisible(setVisible);
	}

	@Override
	public void contributeToToolBar(IToolBarManager toolBarManager) {
		if (!getActionsBuilt()) {
			return;
		}

		toolBarManager.add(this.startAction);
		toolBarManager.add(this.stopAction);
		toolBarManager.add(this.releaseAction);
		super.contributeToToolBar(toolBarManager);
	}

	@Override
	public void contributeToMenu(IMenuManager menuManager) {
		if (!getActionsBuilt()) {
			return;
		}

		final IMenuManager waveformMenu = new MenuManager("W&aveform", "waveform");
		waveformMenu.add(this.startAction);
		waveformMenu.add(this.stopAction);
		waveformMenu.add(this.releaseAction);
		menuManager.insertAfter(IWorkbenchActionConstants.MB_ADDITIONS, waveformMenu);
		super.contributeToMenu(menuManager);
	}
}
