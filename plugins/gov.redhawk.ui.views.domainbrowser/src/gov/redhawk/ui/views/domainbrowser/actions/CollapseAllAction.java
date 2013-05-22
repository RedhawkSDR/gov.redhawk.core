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
package gov.redhawk.ui.views.domainbrowser.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreeViewer;

public class CollapseAllAction extends Action {
	private final TreeViewer treeViewer;

	public CollapseAllAction(final TreeViewer treeViewer) {
		this.treeViewer = treeViewer;
	}

	@Override
	public void run() {
		if (this.treeViewer != null) {
			this.treeViewer.collapseAll();
		}
	}
}
