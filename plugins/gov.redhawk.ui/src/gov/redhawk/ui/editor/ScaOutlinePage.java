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
/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package gov.redhawk.ui.editor;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

/**
 * The Class PDEOutlinePage.
 */
public abstract class ScaOutlinePage extends ContentOutlinePage {
	// CHECKSTYLE:OFF
	/** The editor. */
	protected SCAFormEditor fEditor;

	// CHECKSTYLE:ON

	/**
	 * Instantiates a new pDE outline page.
	 * 
	 * @param editor the editor
	 */
	public ScaOutlinePage(final SCAFormEditor editor) {
		this.fEditor = editor;
	}

	/**
	 * Instantiates a new pDE outline page.
	 */
	public ScaOutlinePage() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void makeContributions(final IMenuManager menuManager, final IToolBarManager toolBarManager,
	        final IStatusLineManager statusLineManager) {

		final MenuManager popupMenuManager = new MenuManager();
		final IMenuListener listener = new IMenuListener() {
			public void menuAboutToShow(final IMenuManager manager) {
				getSelection();
			}
		};

		popupMenuManager.addMenuListener(listener);
		popupMenuManager.setRemoveAllWhenShown(true);
		final Control control = getTreeViewer().getControl();
		final Menu menu = popupMenuManager.createContextMenu(control);
		control.setMenu(menu);
	}

}
