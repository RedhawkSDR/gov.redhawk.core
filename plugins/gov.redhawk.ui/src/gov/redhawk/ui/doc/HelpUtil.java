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
package gov.redhawk.ui.doc;

import org.eclipse.core.runtime.Assert;
import org.eclipse.help.HelpSystem;
import org.eclipse.help.IContext;
import org.eclipse.swt.widgets.Control;

/**
 * @since 2.1
 */
public final class HelpUtil {
	private HelpUtil() {

	}
	public static void assignTooltip(final Control control, final String contextId) {
		Assert.isNotNull(control);
		Assert.isNotNull(contextId);
		final IContext context = HelpSystem.getContext(contextId);
		if (context != null) {
			control.setToolTipText(context.getText());
		}
	}
}
