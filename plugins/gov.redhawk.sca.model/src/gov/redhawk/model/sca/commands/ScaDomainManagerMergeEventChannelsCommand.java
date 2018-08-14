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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaEventChannel;
import gov.redhawk.model.sca.ScaPackage;

/**
 * @since 19.0
 */
public class ScaDomainManagerMergeEventChannelsCommand extends SetStatusCommand<ScaDomainManager> {

	private List<ScaEventChannel> currentChannels;

	public ScaDomainManagerMergeEventChannelsCommand(ScaDomainManager provider, List<ScaEventChannel> currentChannels) {
		super(provider, ScaPackage.Literals.SCA_DOMAIN_MANAGER__EVENT_CHANNELS, null);
		this.currentChannels = currentChannels;
	}

	@Override
	public void execute() {
		// We identify unique channels by name + IOR
		Set<String> currentChannelIds = currentChannels.stream() //
				.map(channel -> channel.getName() + ':' + channel.getIor()) //
				.collect(Collectors.toSet());

		Set<String> existingChannelIds = provider.getEventChannels().stream() //
				.map(channel -> channel.getName() + ':' + channel.getIor()) //
				.collect(Collectors.toSet());

		List<ScaEventChannel> channelsToAdd = new ArrayList<>();
		for (ScaEventChannel channel : currentChannels) {
			if (existingChannelIds.contains(channel.getName() + ':' + channel.getIor())) {
				channel.dispose();
			} else {
				channelsToAdd.add(channel);
			}
		}

		List<ScaEventChannel> channelsToRemove = provider.getEventChannels().stream() //
				.filter(channel -> !currentChannelIds.contains(channel.getName() + ':' + channel.getIor())) //
				.collect(Collectors.toList());

		// Change the model
		if (!channelsToRemove.isEmpty()) {
			provider.getEventChannels().removeAll(channelsToRemove);
		}
		if (!channelsToAdd.isEmpty()) {
			provider.getEventChannels().addAll(channelsToAdd);
		}
		super.execute();
	}

}
