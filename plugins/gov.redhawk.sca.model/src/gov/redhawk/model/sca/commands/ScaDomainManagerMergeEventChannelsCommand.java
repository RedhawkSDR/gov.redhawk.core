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
package gov.redhawk.model.sca.commands;

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaEventChannel;
import gov.redhawk.model.sca.ScaPackage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @since 19.0
 */
public class ScaDomainManagerMergeEventChannelsCommand extends SetStatusCommand<ScaDomainManager> {
	
	private List<ScaEventChannel> channels;

	public ScaDomainManagerMergeEventChannelsCommand(ScaDomainManager provider, List<ScaEventChannel> channels) {
		super(provider, ScaPackage.Literals.SCA_DOMAIN_MANAGER__EVENT_CHANNELS, null);
		this.channels = channels;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	@Override
	public void execute() {
		final Map<String, ScaEventChannel> newChannelMap = new HashMap<String, ScaEventChannel>();
		if (channels != null) {
			for (final ScaEventChannel channel : channels) {
				newChannelMap.put(channel.getName(), channel);
			}
		}

		// Current Factory Map
		final Map<String, ScaEventChannel> existingChannelMap = new HashMap<String, ScaEventChannel>();
		for (final ScaEventChannel channel : provider.getEventChannels()) {
			existingChannelMap.put(channel.getName(), channel);
		}

		// Factories to remove
		final Map<String, ScaEventChannel> channelsToRemove = new HashMap<String, ScaEventChannel>();
		channelsToRemove.putAll(existingChannelMap);
		channelsToRemove.keySet().removeAll(newChannelMap.keySet());

		// Remove all duplicates
		newChannelMap.keySet().removeAll(existingChannelMap.keySet());

		// Remove factories
		if (!channelsToRemove.isEmpty() && !provider.getEventChannels().isEmpty()) {
			provider.getEventChannels().removeAll(channelsToRemove.values());
		}

		// Add Factories
		if (!newChannelMap.isEmpty()) {
			provider.getEventChannels().addAll(newChannelMap.values());
		}
		
		if (!provider.isSetEventChannels()) {
			provider.getEventChannels().clear();
		}

		super.execute();
	}

}
