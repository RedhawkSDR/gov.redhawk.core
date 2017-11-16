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
package gov.redhawk.ui.views.connmgr;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.nebula.widgets.xviewer.customize.XViewerCustomMenu;
import org.eclipse.ui.IWorkbenchActionConstants;

public class ConnMgrMenu extends XViewerCustomMenu {

	public void setupMenuForTable(MenuManager menuManager) {
		menuManager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		super.setupMenuForTable(menuManager);
	}

}
