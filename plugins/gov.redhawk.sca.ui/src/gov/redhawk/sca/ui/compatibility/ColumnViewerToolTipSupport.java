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
package gov.redhawk.sca.ui.compatibility;

import org.eclipse.jface.viewers.ColumnViewer;

/**
 * @since 9.2
 * @deprecated Not used now that we don't support RAP.
 */
@Deprecated
public final class ColumnViewerToolTipSupport {
	private ColumnViewerToolTipSupport() {

	}

	/**
	 * @since 10.0
	 * @deprecated Use {@link ColumnViewerToolTipSupport#enableFor(ColumnViewer)}
	 */
	@Deprecated
	public static void enableFor(final ColumnViewer viewer) {
		org.eclipse.jface.viewers.ColumnViewerToolTipSupport.enableFor(viewer);
	}

	/**
	 * @deprecated Use {@link ColumnViewerToolTipSupport#enableFor(ColumnViewer, int)}
	 */
	@Deprecated
	public static void enableFor(final ColumnViewer viewer, final int style) {
		org.eclipse.jface.viewers.ColumnViewerToolTipSupport.enableFor(viewer, style);
	}

}
