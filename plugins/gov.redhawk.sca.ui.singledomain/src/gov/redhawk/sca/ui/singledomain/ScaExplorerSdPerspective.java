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
package gov.redhawk.sca.ui.singledomain;

import gov.redhawk.sca.ui.views.ScaExplorer;
import gov.redhawk.ui.views.namebrowser.view.NameBrowserView;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * This perspective is used to provide a simple browser interface to an SCA
 * domain. It should strive for simplicity as the most important characteristic.
 * The UI should be heavily filtered using Activities to present the fewest
 * number of menu items as possible. However, the perspective is not fixed, as
 * this prevents users from maximizing views and such. Instead, all of the views
 * should be set to not be closeable.
 * 
 * Modified from ScaExplorerPerspective to replace SCA Explorer view with single-domain
 * SCA EXplorer view
 */
public class ScaExplorerSdPerspective implements IPerspectiveFactory {

	private static final String SCA_EXPLORER_VIEW_ID = ScaExplorer.VIEW_ID;

	private static final String NAMEBROWSER_VIEW_ID = NameBrowserView.ID;

	/** The PDE Error Log view ID. */ //not available in RAP 1.3 Investigate porting, or see if available in 1.4 or 1.5
	//private static final String PDE_ERROR_LOG_VIEW_ID = "org.eclipse.pde.runtime.LogView";

	public void createInitialLayout(final IPageLayout layout) {
		// Editors are placed for free.
		final String editorArea = layout.getEditorArea();

		final IFolderLayout left = layout.createFolder("left", IPageLayout.LEFT, (float) 0.35, editorArea);

		left.addView(ScaExplorerSdPerspective.SCA_EXPLORER_VIEW_ID);
		left.addView(ScaExplorerSdPerspective.NAMEBROWSER_VIEW_ID);

		final IFolderLayout bottom = layout.createFolder("bottom", IPageLayout.BOTTOM, (float) 0.60, editorArea);

		bottom.addView(IPageLayout.ID_PROP_SHEET);
		//bottom.addView(ScaExplorerSdPerspective.PDE_ERROR_LOG_VIEW_ID);

		// DON'T USE A FIXED PERSPECTIVE BECAUSE IT DOESN'T ALLOW A VIEW
		// TO BE MAXIMIZED/MINIMIZED.

		// These are so important, don't let the operator close them.
		layout.getViewLayout(ScaExplorerSdPerspective.SCA_EXPLORER_VIEW_ID).setCloseable(false);
		layout.getViewLayout(ScaExplorerSdPerspective.NAMEBROWSER_VIEW_ID).setCloseable(false);
		layout.getViewLayout(IPageLayout.ID_PROP_SHEET).setCloseable(false);
		//layout.getViewLayout(ScaExplorerSdPerspective.PDE_ERROR_LOG_VIEW_ID).setCloseable(false);
		// Don't let anything move
		layout.getViewLayout(ScaExplorerSdPerspective.SCA_EXPLORER_VIEW_ID).setMoveable(false);
		layout.getViewLayout(ScaExplorerSdPerspective.NAMEBROWSER_VIEW_ID).setMoveable(false);
		layout.getViewLayout(IPageLayout.ID_PROP_SHEET).setMoveable(false);
		//layout.getViewLayout(ScaExplorerSdPerspective.PDE_ERROR_LOG_VIEW_ID).setMoveable(false);
	}
}
