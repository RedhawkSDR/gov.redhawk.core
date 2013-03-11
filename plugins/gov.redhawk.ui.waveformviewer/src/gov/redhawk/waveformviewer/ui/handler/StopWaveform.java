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
import CF.LifeCyclePackage.ReleaseError;
import CF.ResourcePackage.StopError;

public class StopWaveform extends WaveformActivity {
	public static final String ID = "gov.redhawk.ui.waveform.commands.stop";

	@Override
	protected Object processWave(final ScaDomainManager dom, final SoftwareAssembly wave) {
		for (final Application app : dom.applications()) {
			if (app.identifier().equals(wave.getId())) {
				try {
					app.stop();
				} catch (final StopError e) {
					WaveformProjectPlugin.logError("Failed to stop waveform: " + app.identifier(), e);
				}
				break;
			}
		}
		return null;
	}

	@Override
	protected void processSelection(final Object selection, final ArrayList<Object> ret) {
		if (selection instanceof ResourceOperations) {
			final ResourceOperations device = (ResourceOperations) selection;
			try {
				device.stop();
			} catch (final StopError e) {
				WaveformProjectPlugin.logError("Failed to stop device: " + device.identifier(), e);
			}
		} else if (selection instanceof LifeCycleOperations) {
			final LifeCycleOperations op = (LifeCycleOperations) selection;
			try {
				op.releaseObject();
			} catch (final ReleaseError e) {
				WaveformProjectPlugin.logError("Failed to stop lifecycle: " + op, e);
			}
		} else {
			super.processSelection(selection, ret);
		}
	}
}
