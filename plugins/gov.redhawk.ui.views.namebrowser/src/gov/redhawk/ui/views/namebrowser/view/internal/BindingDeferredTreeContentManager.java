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
package gov.redhawk.ui.views.namebrowser.view.internal;

import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.progress.DeferredTreeContentManager;

/**
 * 
 */
public class BindingDeferredTreeContentManager extends DeferredTreeContentManager {

	public BindingDeferredTreeContentManager(final AbstractTreeViewer viewer, final IWorkbenchPartSite site) {
		super(viewer, site);
	}

	public BindingDeferredTreeContentManager(final AbstractTreeViewer viewer) {
		super(viewer);
	}

}
