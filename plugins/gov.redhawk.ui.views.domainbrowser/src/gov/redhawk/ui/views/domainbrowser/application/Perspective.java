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
package gov.redhawk.ui.views.domainbrowser.application;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		final String editorArea = layout.getEditorArea();
		
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);
		
		final IFolderLayout top = layout.createFolder("top", IPageLayout.LEFT, (float) 1.0, editorArea);
		top.addView("gov.redhawk.ui.views.domainbrowserview");
		top.addView("gov.redhawk.ui.views.namebrowserview");
	}

}
