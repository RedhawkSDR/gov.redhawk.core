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
package gov.redhawk.ui.port.playaudio.internal.handlers;

import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.sca.util.PluginUtil;
import gov.redhawk.ui.port.playaudio.internal.Activator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

public class PlayPortHandler extends AbstractHandler {

	public PlayPortHandler() {
	}

	public void connect(final List< ? > portList) {

	}

	public Object execute(ExecutionEvent event) throws ExecutionException {
		IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getActiveMenuSelection(event);
		if (selection == null) {
			selection = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
		}
		if (selection != null) {
			List< ? > elements = selection.toList();
			final Map<ScaUsesPort, String> portMap = new HashMap<ScaUsesPort, String>();
			for (Object obj : elements) {
				ScaUsesPort port = PluginUtil.adapt(ScaUsesPort.class, obj);
				if (port != null) {
					portMap.put(port, null);
				}
			}
			if (!portMap.isEmpty()) {
				Activator.getDefault().playPort(portMap);
			}
		}
		return null;
	}
}
