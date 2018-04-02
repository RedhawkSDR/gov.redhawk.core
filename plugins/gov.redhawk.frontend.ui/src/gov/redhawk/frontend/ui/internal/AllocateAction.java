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

import gov.redhawk.frontend.TunerContainer;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.UnallocatedTunerContainer;
import gov.redhawk.frontend.ui.internal.section.FrontendSection;
import gov.redhawk.frontend.ui.wizard.TunerAllocationWizard;
import gov.redhawk.sca.util.PluginUtil;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

/**
 * 
 */
public class AllocateAction extends FrontendAction {
	
	public AllocateAction(FrontendSection theSection) {
		super(theSection, "Allocate...", "gov.redhawk.frontend.actions.allocate", "gov.redhawk.frontend.commands.allocate",
			"icons/add.png");
	}

	@Override
	public void run() {
		EObject obj = getSection().getInput();
		if (obj instanceof UnallocatedTunerContainer) {
			UnallocatedTunerContainer container = (UnallocatedTunerContainer) obj;
			TunerStatus[] tuners = getUnallocatedTunersOfType(container.getTunerContainer(), container.getTunerType());
			if (tuners.length > 0) {
				WizardDialog dialog = new WizardDialog(Display.getCurrent().getActiveShell(), new TunerAllocationWizard(tuners[0]));
				dialog.open();
			}
		} else if (obj instanceof TunerContainer) {
			TunerContainer container = (TunerContainer) obj;
			TunerStatus[] tuners = container.getTunerStatus().toArray(new TunerStatus[0]);
			if (tuners.length > 0) {
				WizardDialog dialog = new WizardDialog(Display.getCurrent().getActiveShell(), new TunerAllocationWizard(tuners[0]));
				dialog.open();
			}
		}
	}

	private TunerStatus[] getUnallocatedTunersOfType(TunerContainer container, String tunerType) {
		List<TunerStatus> tuners = new ArrayList<TunerStatus>();
		for (TunerStatus tuner : container.getTunerStatus()) {
			if ((tuner.getAllocationID() == null || tuner.getAllocationID().length() == 0) && PluginUtil.equals(tuner.getTunerType(), tunerType)) {
				tuners.add(tuner);
			}
		}
		return tuners.toArray(new TunerStatus[0]);
	}

}
