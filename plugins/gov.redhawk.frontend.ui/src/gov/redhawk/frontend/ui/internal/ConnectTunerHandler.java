/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.frontend.ui.internal;

import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.ui.FrontEndUIActivator;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaUsesPort;

import java.util.ArrayList;
import java.util.List;

import mil.jpeojtrs.sca.util.ScaEcoreUtils;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.statushandlers.StatusManager;

/**
 * 
 */
public class ConnectTunerHandler extends AbstractHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// need to grab Port selections first, otherwise Plot Wizard option below will change the selection
		IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getActiveMenuSelection(event);
		if (selection == null) {
			selection = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
			if (selection == null) {
				return null;
			}
		}

		final List< ? > elements = selection.toList();

		for (Object obj : elements) {
			if (obj instanceof TunerStatus) {
				final TunerStatus tuner = (TunerStatus) obj;
				final ScaDevice< ? > device = ScaEcoreUtils.getEContainerOfType(tuner, ScaDevice.class);
				List<ScaPort< ? , ? >> devicePorts = device.getPorts();
				final List<ScaUsesPort> usesPorts = new ArrayList<ScaUsesPort>();
				for (ScaPort< ? , ? > port : devicePorts) {
					if (port instanceof ScaUsesPort) {
						usesPorts.add((ScaUsesPort) port);
					}
				}
				if (usesPorts.isEmpty()) {
					Status status = new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "No output ports available, can not make a connection from "
							+ tuner.getTunerID(), new Exception().fillInStackTrace());
					StatusManager.getManager().handle(status, StatusManager.LOG | StatusManager.SHOW);
					return null;
				}

				ConnectTunerWizard wizard = new ConnectTunerWizard();
				wizard.setTuner(tuner);
				WizardDialog dialog = new WizardDialog(Display.getCurrent().getActiveShell(), wizard);
				dialog.open();
			}
		}
		return null;
	}

}
