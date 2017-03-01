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
package gov.redhawk.model.sca.commands;

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaWaveform;

import java.util.HashMap;
import java.util.Map;

import CF.Application;

/**
 * Updates the domain manager's waveform list based on a list of {@link Application} objects.
 * @since 14.0
 */
public class ScaDomainManagerMergeWaveformsCommand extends SetStatusCommand<ScaDomainManager> {

	private final Map<String, Application> corbaApplications;

	/**
	 * This constructor performs {@link Object#toString()} calls on the {@link Application} CORBA objects, and thus is
	 * subject to blocking.
	 * @param provider the domain manager
	 * @param applications the current application list for the domain manager
	 */
	public ScaDomainManagerMergeWaveformsCommand(ScaDomainManager provider, Application[] applications) {
		super(provider, ScaPackage.Literals.SCA_DOMAIN_MANAGER__WAVEFORMS, null);

		// Populate a map of IORs -> CORBA Application objects
		this.corbaApplications = new HashMap<String, Application>();
		if (applications != null) {
			for (final Application app : applications) {
				corbaApplications.put(app.toString(), app);
			}
		}
	}

	@Override
	public void execute() {
		// Populate a map of IORs -> SCA model waveforms
		final Map<String, ScaWaveform> scaApplications = new HashMap<String, ScaWaveform>();
		for (final ScaWaveform app : provider.getWaveforms()) {
			scaApplications.put(app.getIor(), app);
		}

		// Waveforms to remove
		final Map<String, ScaWaveform> scaApplicationsToRemove = new HashMap<String, ScaWaveform>();
		scaApplicationsToRemove.putAll(scaApplications);
		scaApplicationsToRemove.keySet().removeAll(corbaApplications.keySet());

		// Remove all duplicates
		corbaApplications.keySet().removeAll(scaApplications.keySet());

		// Remove waveforms
		if (!scaApplicationsToRemove.isEmpty() && !provider.getWaveforms().isEmpty()) {
			provider.getWaveforms().removeAll(scaApplicationsToRemove.values());
		}

		// Add waveforms
		for (final Application appData : corbaApplications.values()) {
			ScaWaveform scaWaveform = ScaFactory.eINSTANCE.createScaWaveform();
			scaWaveform.setCorbaObj(appData);
			provider.getWaveforms().add(scaWaveform);
		}

		if (!provider.isSetWaveforms()) {
			provider.getWaveforms().clear();
		}

		super.execute();
	}

}
