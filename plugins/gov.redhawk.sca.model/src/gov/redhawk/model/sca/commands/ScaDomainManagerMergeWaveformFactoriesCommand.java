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
import gov.redhawk.model.sca.ScaWaveformFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import CF.ApplicationFactory;

/**
 * @since 14.0
 * 
 */
public class ScaDomainManagerMergeWaveformFactoriesCommand extends SetStatusCommand<ScaDomainManager> {

	private ApplicationFactory[] factories;

	public ScaDomainManagerMergeWaveformFactoriesCommand(ScaDomainManager provider, ApplicationFactory[] factories) {
		super(provider, ScaPackage.Literals.SCA_DOMAIN_MANAGER__WAVEFORM_FACTORIES, null);
		this.factories = factories;
	}

	@Override
	public void execute() {
		final Map<String, ApplicationFactory> corbaFactoriesMap = new HashMap<String, ApplicationFactory>();
		if (factories != null) {
			for (final ApplicationFactory factory : factories) {
				corbaFactoriesMap.put(factory.toString(), factory);
			}
		}

		// Current Factory Map
		final Map<String, ScaWaveformFactory> scaFactoriesMap = new HashMap<String, ScaWaveformFactory>();
		for (final ScaWaveformFactory factory : provider.getWaveformFactories()) {
			scaFactoriesMap.put(factory.getIor(), factory);
		}

		// Factories to remove
		final Map<String, ScaWaveformFactory> factoriesToRemove = new HashMap<String, ScaWaveformFactory>();
		factoriesToRemove.putAll(scaFactoriesMap);
		factoriesToRemove.keySet().removeAll(corbaFactoriesMap.keySet());

		// Remove all duplicates
		corbaFactoriesMap.keySet().removeAll(scaFactoriesMap.keySet());

		// Remove factories
		if (!factoriesToRemove.isEmpty() && !provider.getWaveformFactories().isEmpty()) {
			provider.getWaveformFactories().removeAll(factoriesToRemove.values());
		}

		// Add Factories
		for (final Entry<String, ApplicationFactory> entry : corbaFactoriesMap.entrySet()) {
			ScaWaveformFactory scaWaveformFactory = ScaFactory.eINSTANCE.createScaWaveformFactory();
			provider.getWaveformFactories().add(scaWaveformFactory);
			scaWaveformFactory.setCorbaObj(entry.getValue());
		}

		if (!provider.isSetWaveformFactories()) {
			provider.getWaveformFactories().clear();
		}
		super.execute();
	}

}
