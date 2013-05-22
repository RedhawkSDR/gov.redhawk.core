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
package gov.redhawk.waveformviewer.ui.handler;

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.waveformviewer.ui.ApplicationActionListener;
import gov.redhawk.waveformviewer.ui.WaveformProjectPlugin;
import gov.redhawk.waveformviewer.ui.views.RunWaveformView;

import java.util.ArrayList;
import java.util.List;

import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.sad.SadComponentPlacement;
import mil.jpeojtrs.sca.sad.SadPackage;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;

import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;

import CF.Application;
import CF.DataType;
import CF.DeviceAssignmentType;
import CF.InvalidFileName;
import CF.InvalidProfile;
import CF.ApplicationFactoryPackage.CreateApplicationError;
import CF.ApplicationFactoryPackage.CreateApplicationInsufficientCapacityError;
import CF.ApplicationFactoryPackage.CreateApplicationRequestError;
import CF.ApplicationFactoryPackage.InvalidInitConfiguration;
import CF.DomainManagerPackage.ApplicationAlreadyInstalled;
import CF.DomainManagerPackage.ApplicationInstallationError;
import CF.ResourcePackage.StartError;

public class InstallWaveform extends WaveformActivity {
	public static final String ID = "gov.redhawk.ui.waveform.commands.install";

	@Override
	protected Object processWave(final ScaDomainManager dom, final SoftwareAssembly wave) {
		Application appl = null;

		setAction(ApplicationActionListener.INSTALL);
		// If the active pages isn't null, find the right view
		if (this.getPages() != null) {
			// Search each page to see if it contains the RunWaveform view
			for (final IWorkbenchPage page : this.getPages()) {
				final IViewPart vp = page.findView(RunWaveformView.ID);
				if ((vp != null) && (vp instanceof ApplicationActionListener)) {
					setAppListener((ApplicationActionListener) vp);
					break;
				}
			}
		}

		try {
			// Parse out the name of the waveform
			final int endNS = wave.getName().lastIndexOf(':');
			final String waveName = (endNS != -1) ? wave.getName().substring(endNS + 1) : wave.getName(); // SUPPRESS CHECKSTYLE AvoidInLine

			// Ask the Domain Manager to install the file for the waveform
			dom.installApplication("/waveforms/" + waveName + "/" + waveName + SadPackage.FILE_EXTENSION);

			// This just finds the first device in the Domain Manager and
			// assigns all
			// components to that device
			// TODO Change the assignment to use the DAS file
			final ArrayList<DeviceAssignmentType> list = new ArrayList<DeviceAssignmentType>();
			final List<SadComponentPlacement> placeList = wave.getPartitioning().getComponentPlacement();
			final String devID = dom.deviceManagers()[0].registeredDevices()[0].identifier();

			// Make the Device Assignments
			for (final SadComponentPlacement place : placeList) {
				final List<SadComponentInstantiation> instList = place.getComponentInstantiation();
				for (final SadComponentInstantiation inst : instList) {
					final DeviceAssignmentType dev = new DeviceAssignmentType();
					dev.assignedDeviceId = devID;
					dev.componentId = inst.getId();
					list.add(dev);
				}
			}
			final DeviceAssignmentType[] type = new DeviceAssignmentType[list.size()];
			list.toArray(type);

			// Create and start the application
			appl = dom.applicationFactories()[0].create(dom.applicationFactories()[0].name(), new DataType[0], type);
			// appl = startWaveform(dom.applicationFactories()[0], dom, wave);
			appl.start();
		} catch (final InvalidProfile e) {
			WaveformProjectPlugin.logError("Failed to create waveform: " + wave.getName(), e);
		} catch (final InvalidFileName e) {
			WaveformProjectPlugin.logError("Failed to create waveform: " + wave.getName(), e);
		} catch (final ApplicationInstallationError e) {
			WaveformProjectPlugin.logError("Failed to create waveform: " + wave.getName(), e);
		} catch (final CreateApplicationError e) {
			WaveformProjectPlugin.logError("Failed to create waveform: " + wave.getName(), e);
		} catch (final CreateApplicationRequestError e) {
			WaveformProjectPlugin.logError("Failed to create waveform: " + wave.getName(), e);
		} catch (final InvalidInitConfiguration e) {
			WaveformProjectPlugin.logError("Failed to create waveform: " + wave.getName(), e);
		} catch (final StartError e) {
			WaveformProjectPlugin.logError("Failed to create waveform: " + wave.getName(), e);
		} catch (final ApplicationAlreadyInstalled e) {
			WaveformProjectPlugin.logError("Failed to create waveform: " + wave.getName(), e);
		} catch (final CreateApplicationInsufficientCapacityError e) {
			WaveformProjectPlugin.logError("Failed to create waveform: " + wave.getName(), e);
		}

		return appl;
	}
}
