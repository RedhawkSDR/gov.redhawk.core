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
package gov.redhawk.ui.port.playaudio.internal.handlers;

import gov.redhawk.bulkio.util.BulkIOType;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.sca.util.PluginUtil;
import gov.redhawk.ui.port.playaudio.internal.Activator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISources;
import org.eclipse.ui.handlers.HandlerUtil;

public class PlayPortHandler extends AbstractHandler {

	public PlayPortHandler() {
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getActiveMenuSelection(event);
		if (selection == null) {
			selection = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
		}
		if (selection != null) {
			List< ? > elements = selection.toList();
			final List<ScaUsesPort> ports = new ArrayList<ScaUsesPort>();
			for (Object obj : elements) {
				ScaUsesPort port = PluginUtil.adapt(ScaUsesPort.class, obj);
				if (port != null) {
					ports.add(port);
				}
			}
			if (!ports.isEmpty()) {
				Activator.getDefault().playPorts(ports);
			}
		}
		return null;
	}

	@Override
	public void setEnabled(Object evaluationContext) {
		Object obj = HandlerUtil.getVariable(evaluationContext, ISources.ACTIVE_MENU_SELECTION_NAME);
		if (!(obj instanceof IStructuredSelection)) {
			setBaseEnabled(false);
			return;
		}

		for (Object element : ((IStructuredSelection) obj).toArray()) {
			// Must be a uses port with a 'supported' BULKIO type
			ScaUsesPort port = PluginUtil.adapt(ScaUsesPort.class, element);
			if (port == null || !BulkIOType.isTypeSupported(port.getRepid())) {
				setBaseEnabled(false);
				return;
			}

			// Check that our audio class supports the specific BULKIO type
			switch (BulkIOType.getType(port.getRepid())) {
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case LONG:
			case LONG_LONG:
			case OCTET:
			case SHORT:
			case ULONG:
			case USHORT:
				// BULKIO type is supported
				break;
			default:
				// BULKIO type is NOT supported
				setBaseEnabled(false);
				return;
			}
		}

		setBaseEnabled(true);
	}
}
