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
package gov.redhawk.ui.views.internal.monitor.handler;

import gov.redhawk.monitor.model.ports.Monitor;
import gov.redhawk.monitor.model.ports.PortConnectionMonitor;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

public class RefreshHandler extends AbstractHandler {

	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		final ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (selection instanceof IStructuredSelection) {
			final IStructuredSelection ss = (IStructuredSelection) selection;
			for (final Object obj : ss.toList()) {
				if (obj instanceof Monitor) {
					refresh((Monitor) obj);
				} else if (obj instanceof PortConnectionMonitor) {
					PortConnectionMonitor child = (PortConnectionMonitor) obj;
					EObject container = child.eContainer();
					if (container instanceof Monitor) {
						refresh((Monitor) container);
					}
				}
			}
		}
		return null;
	}

	private void refresh(final Monitor monitor) {
		monitor.fetchStats();
	}

}
