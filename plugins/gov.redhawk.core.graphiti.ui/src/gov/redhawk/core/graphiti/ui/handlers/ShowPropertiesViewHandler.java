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
package gov.redhawk.core.graphiti.ui.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import gov.redhawk.core.graphiti.ui.GraphitiUIPlugin;

public class ShowPropertiesViewHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			HandlerUtil.getActiveSite(event).getPage().showView("org.eclipse.ui.views.PropertySheet"); //$NON-NLS-1$
		} catch (PartInitException e) {
			IStatus status = new Status(IStatus.ERROR, GraphitiUIPlugin.PLUGIN_ID, "Unable to open properties view", e);
			Platform.getLog(Platform.getBundle(GraphitiUIPlugin.PLUGIN_ID)).log(status);
		}
		return null;
	}

}
