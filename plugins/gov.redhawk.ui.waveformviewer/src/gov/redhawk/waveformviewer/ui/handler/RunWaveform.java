/**
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.waveformviewer.ui.handler;

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.waveformviewer.ui.WaveformProjectPlugin;

import java.util.ArrayList;

import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import CF.Application;
import CF.LifeCycleOperations;
import CF.ResourceOperations;
import CF.LifeCyclePackage.InitializeError;
import CF.ResourcePackage.StartError;

public class RunWaveform extends InstallWaveform {
	public static final String ID = "gov.redhawk.ui.waveform.commands.run";

	@Override
	protected Object processWave(final ScaDomainManager dom, final SoftwareAssembly wave) {
		Object appl = null;

		// Check if the waveform was started
		if (!startWaveform(dom, wave)) {
			// If not, install the waveform(done in InstallWaveform)
			appl = super.processWave(dom, wave);

			// then try start it again
			startWaveform(dom, wave);
		}
		return appl;
	}

	private boolean startWaveform(final ScaDomainManager dom, final SoftwareAssembly wave) {
		// Ask the Domain Manager for its applications and start the one
		// matching the given waveform
		for (final Application app : dom.applications()) {
			if (app.identifier().equals(wave.getId())) {
				try {
					app.start();
				} catch (final StartError e) {
					WaveformProjectPlugin.logError("Failed to start waveform: " + app.identifier(), e);
				}
				return true;
			}
		}
		return false;
	}

	@Override
	protected void processSelection(final Object selection, final ArrayList<Object> ret) {
		if (selection instanceof ResourceOperations) {
			final ResourceOperations resource = (ResourceOperations) selection;
			try {
				resource.start();
			} catch (final StartError e) {
				WaveformProjectPlugin.logError("Failed to start resource: " + resource.identifier(), e);
			}
		} else if (selection instanceof LifeCycleOperations) {
			final LifeCycleOperations op = (LifeCycleOperations) selection;
			try {
				op.initialize();
			} catch (final InitializeError e) {
				WaveformProjectPlugin.logError("Failed to start LifeCycle: " + op, e);
			}
		} else {
			super.processSelection(selection, ret);
		}
	}

}
