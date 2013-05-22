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
import gov.redhawk.ui.port.IPortHandler;
import gov.redhawk.ui.port.playaudio.internal.Activator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayPortHandler implements IPortHandler {

	public PlayPortHandler() {
	}

	public void connect(final List< ? > portList) {
		final Map<ScaUsesPort, String> portMap = new HashMap<ScaUsesPort, String>();
		for (final Object o : portList) {
			if (o instanceof ScaUsesPort) {
				portMap.put((ScaUsesPort) o, null);
			}
		}
		Activator.getDefault().playPort(portMap);
	}
	
	public void connect(final List< ? > portList, final String filter) {
		if (filter != null && filter.startsWith(IPortHandler.FILTER_PLAY)) {
			connect(portList);
		}
	}
}
