/**
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.ui.sad.editor.presentation;

import gov.redhawk.sca.sad.diagram.part.SadDiagramEditor;
import gov.redhawk.sca.ui.actions.ReleaseAction;
import gov.redhawk.sca.ui.actions.StartAction;
import gov.redhawk.sca.ui.actions.StopAction;
import gov.redhawk.ui.editor.SCAFormEditor;
import gov.redhawk.ui.editor.ScaMultipageActionBarContributor;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IEditorActionBarContributor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.editors.text.TextEditor;

/**
 * 
 */
public class SadMultipageEditorActionBarContributor extends ScaMultipageActionBarContributor {

	private final StartAction startAction = new StartAction();
	private final StopAction stopAction = new StopAction();
	private final ReleaseAction releaseAction = new ReleaseAction();

	public SadMultipageEditorActionBarContributor() {
		this.startAction.setToolTipText("Start Waveform");
		this.stopAction.setToolTipText("Stop Waveform");
		this.releaseAction.setToolTipText("Release Waveform");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected IEditorActionBarContributor getSubActionBarContributor(final IEditorPart activeEditor) {
		if (activeEditor instanceof ExplorerDiagramEditor) {
			return new SadActionBarContributor();
		}
		return super.getSubActionBarContributor(activeEditor);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getType(final IEditorPart activeEditor) {
		if (activeEditor == null || activeEditor instanceof SCAFormEditor) {
			return "SAD Editor";
		} else if (activeEditor instanceof TextEditor) {
			return "Text Editor";
		} else if (activeEditor instanceof SadDiagramEditor) {
			return "SAD Diagram Editor";
		}
		return "";
	}

	@Override
	public void setActiveEditor(final IEditorPart part) {
		super.setActiveEditor(part);
		this.startAction.setContext(part);
		this.stopAction.setContext(part);
		this.releaseAction.setContext(part);
	}

	@Override
	public void contributeToToolBar(final IToolBarManager toolBarManager) {
		super.contributeToToolBar(toolBarManager);

		toolBarManager.add(this.startAction);
		toolBarManager.add(this.stopAction);
		toolBarManager.add(this.releaseAction);
	}

	@Override
	public void contributeToMenu(final IMenuManager menuManager) {
		super.contributeToMenu(menuManager);

		final IMenuManager waveformMenu = new MenuManager("W&aveform", "waveform");

		waveformMenu.add(this.startAction);
		waveformMenu.add(this.stopAction);
		waveformMenu.add(this.releaseAction);

		menuManager.insertAfter(IWorkbenchActionConstants.MB_ADDITIONS, waveformMenu);
	}
}
