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
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.waveformviewer.ui.ApplicationActionListener;
import gov.redhawk.waveformviewer.ui.WaveformProjectPlugin;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import CF.DomainManagerPackage.ApplicationUninstallationError;
import CF.DomainManagerPackage.InvalidIdentifier;
import CF.LifeCyclePackage.ReleaseError;
import CF.ResourcePackage.StopError;

public class UninstallWaveform extends WaveformActivity {
	public static final String ID = "gov.redhawk.ui.waveform.commands.uninstall";

	@Override
	protected Object processWave(final ScaDomainManager dom, final SoftwareAssembly wave) {
		ScaWaveform ret = null;
		setAction(ApplicationActionListener.UNINSTALL);

		// Ask the Domain Manager for its applications then find the matching
		// on.
		// Call processApplication if it's found
		for (final ScaWaveform app : dom.getWaveforms()) {
			if (app.identifier().equals(wave.getId())) {
				processWaveform(dom, app);
				ret = app;
				break;
			}
		}
		return ret;
	}

	@Override
	protected Object processWaveform(final ScaDomainManager dom, final ScaWaveform app) {
		final String id = app.identifier();
		setAction(ApplicationActionListener.UNINSTALL);

		// The process to uninstall an application is to:
		// 1- Stop the application
		// 2- Release it
		// 3- Tell the Domain Manager to uninstall the application
		try {
			final int size = app.eContainer().eContents().size();
			app.stop();
			app.releaseObject();
			if (size == 1) {
				dom.uninstallApplication(id);
			}
		} catch (final InvalidIdentifier e) {
			WaveformProjectPlugin.logError("Failed to uninstall waveform: " + id, e);
		} catch (final ApplicationUninstallationError e) {
			WaveformProjectPlugin.logError("Failed to uninstall waveform: " + id, e);
		} catch (final StopError e) {
			WaveformProjectPlugin.logError("Failed to uninstall waveform: " + id, e);
		} catch (final ReleaseError e) {
			WaveformProjectPlugin.logError("Failed to uninstall waveform: " + id, e);
		} catch (final Exception e) {
			WaveformProjectPlugin.logError("Failed to uninstall waveform: " + id, e);
		}

		return app;
	}
}
