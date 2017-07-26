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
package gov.redhawk.ui.views.namebrowser.internal.command;

import gov.redhawk.ui.views.namebrowser.NameBrowserPlugin;
import gov.redhawk.ui.views.namebrowser.view.BindingNode;
import gov.redhawk.ui.views.namebrowser.view.NameBrowserView;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.statushandlers.StatusManager;

public class DomainManagerTester extends PropertyTester {
	public static final String ID = "gov.redhawk.ui.views.namebrowser.command.DomainManagerTester";

	@Override
	public boolean test(final Object receiver, final String property, final Object[] args, final Object expectedValue) {
		StatusManager.getManager().handle(new Status(IStatus.ERROR, NameBrowserPlugin.PLUGIN_ID,
			"The property tester gov.redhawk.ui.views.namebrowser.isDomainManager is deprecated and should not be used"), StatusManager.LOG);
		if (!(receiver instanceof BindingNode)) {
			return false;
		}
		final BindingNode node = (BindingNode) receiver;
		return NameBrowserView.isDomainManager(node);
	}

}
