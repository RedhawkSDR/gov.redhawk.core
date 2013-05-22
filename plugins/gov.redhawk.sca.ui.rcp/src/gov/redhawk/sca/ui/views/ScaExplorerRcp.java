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
package gov.redhawk.sca.ui.views;


import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.navigator.CommonViewer;

/**
 * 
 */
public class ScaExplorerRcp extends ScaExplorer {

	/**
     * @since 10.0
     */
	@Override
	protected CommonViewer createCommonViewerObject(final Composite aParent) {
		return new ScaCommonViewer(getViewSite().getId(), aParent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
	}

	/**
     * @since 10.0
     */
	@Override
	protected CommonViewer createCommonViewer(final Composite aParent) {
		final CommonViewer retVal = super.createCommonViewer(aParent);
		ColumnViewerToolTipSupport.enableFor(retVal);
		return retVal;
	}
	
}
