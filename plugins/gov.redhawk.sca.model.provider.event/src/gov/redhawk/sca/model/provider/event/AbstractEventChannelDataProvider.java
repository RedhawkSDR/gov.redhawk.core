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
package gov.redhawk.sca.model.provider.event;

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.services.AbstractDataProvider;
import gov.redhawk.sca.model.provider.event.internal.listener.EventJob;
import gov.redhawk.sca.util.MutexRule;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.omg.CORBA.Any;
import org.omg.CORBA.SystemException;

import CF.DomainManagerPackage.InvalidEventChannelName;
import CF.DomainManagerPackage.NotConnected;

/**
 * 
 */
public abstract class AbstractEventChannelDataProvider< T > extends AbstractDataProvider {

	private final T container;
	private final ScaDomainManager domain;
	private final Map<String, EventJob> connectedChannels = Collections.synchronizedMap(new HashMap<String, EventJob>());
	private final MutexRule rule = new MutexRule(this);
	private final Job connectJob = new Job("Connect Job") {
		{
			setUser(false);
			setRule(AbstractEventChannelDataProvider.this.rule);
		}

		@Override
		protected IStatus run(final IProgressMonitor monitor) {
			connectAll(monitor);
			return Status.OK_STATUS;
		}

	};
	private final Job disconnectJob = new Job("Disconnect Job") {
		{
			setUser(false);
			setRule(AbstractEventChannelDataProvider.this.rule);
		}

		@Override
		protected IStatus run(final IProgressMonitor monitor) {
			disconnectAll(monitor);
			return Status.OK_STATUS;
		}

	};

	public AbstractEventChannelDataProvider(final T container, final ScaDomainManager domain) {
		Assert.isNotNull(container);
		Assert.isNotNull(domain);
		this.container = container;
		this.domain = domain;
	}

	protected void setStatus(final int type, final String msg, final Throwable e) {
		setStatus(new Status(type, DataProviderActivator.ID, msg, e));
	}

	/**
	 * Handle events from the channels
	 * @param data
	 */
	public abstract void handleEvent(String channel, final Any data);

	public final T getContainer() {
		return this.container;
	}

	/**
	 * Add a channel to the list of channels.  <b> Note </b> This does NOT actually connect to the channel.
	 * @param channelName Channel to add
	 */
	public synchronized void addChannel(final String channelName) {
		if (!this.connectedChannels.containsKey(channelName)) {
			this.connectedChannels.put(channelName, null);
		}
	}

	/**
	 * Remove a channel
	 * @param channelName Channel to remove
	 * @throws IllegalStateException If the channel is currently connected
	 */
	public synchronized void removeChannel(final String channelName) {
		if (isConnected(channelName)) {
			throw new IllegalStateException(channelName + " is currently connected");
		}
		this.connectedChannels.remove(channelName);
	}

	/**
	 * Remove all channels
	 * @throws IllegalStateException If the channel is currently connected
	 */
	public synchronized void clearChannels() {
		for (final Iterator<String> i = this.connectedChannels.keySet().iterator(); i.hasNext();) {
			final String channelName = i.next();
			if (isConnected(channelName)) {
				throw new IllegalStateException(channelName + " is currently connected");
			}
		}
		this.connectedChannels.clear();
	}

	/**
	 * Return true if the channel is connected.
	 * @param channelName
	 * @return
	 */
	public synchronized boolean isConnected(final String channelName) {
		return this.connectedChannels.get(channelName) != null;
	}

	/**
	 * Connect to the Channel. This is a blocking operation.
	 * @param channelName Name of the channel to connect to.
	 * @param monitor Monitor for reporting progress
	 * @throws CoreException
	 */
	public synchronized IStatus connectChannel(final String channelName, IProgressMonitor monitor) {
		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}
		monitor.beginTask("Connecting channel " + channelName, 1);
		try {
			addChannel(channelName);
			if (!isConnected(channelName)) {
				if (this.domain.isSetCorbaObj()) {
					EventJob job;
					try {
						job = new EventJob(channelName, this, this.domain);
						this.connectedChannels.put(channelName, job);
					} catch (Exception e) {
						return new Status(IStatus.ERROR, DataProviderActivator.ID, "Failed to connect to event channel " + channelName, e);
					}
				}
			}
			return Status.OK_STATUS;
		} finally {
			monitor.done();
		}
	}

	/**
	 * Disconnect from the channel. This is a blocking operation.
	 * @param channelName
	 * @param monitor
	 * @throws InvalidEventChannelName
	 * @throws NotConnected
	 */
	public synchronized void disconnectChannel(final String channelName, IProgressMonitor monitor) throws InvalidEventChannelName, NotConnected {
		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}
		monitor.beginTask("Disconnecting " + channelName, 1);
		final EventJob oldJob = this.connectedChannels.remove(channelName);
		if (oldJob != null) {
			oldJob.dispose();
		}
		monitor.done();
	}

	/**
	 * Disconnects from all registered channels. This is a blocking operation.
	 * @param monitor
	 */
	public synchronized void disconnectAll(IProgressMonitor monitor) {
		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}
		monitor.beginTask("Disconnecting all", this.connectedChannels.size());
		for (final String s : this.connectedChannels.keySet()) {
			try {
				disconnectChannel(s, new SubProgressMonitor(monitor, 1));
			} catch (final InvalidEventChannelName e) {
				// PASS
			} catch (final NotConnected e) {
				// PASS
			} catch (final SystemException e) {
				// PASS
			}
		}
		monitor.done();
		setStatus(Status.OK_STATUS);
	}

	/**
	 * Connects to all registered channels. This is a blocking operation.
	 * @param monitor
	 * @throws InvalidObjectReference
	 * @throws InvalidEventChannelName
	 * @throws AlreadyConnected
	 * @throws ServantNotActive
	 * @throws WrongPolicy
	 */
	public synchronized IStatus connectAll(IProgressMonitor monitor) {
		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}
		
		MultiStatus connectAllStatus = new MultiStatus(DataProviderActivator.ID, Status.OK, "Error connecting to event channels", null);
		monitor.beginTask("Connecting to event channels", this.connectedChannels.size());
		for (final String s : this.connectedChannels.keySet()) {
			IStatus connectStatus = connectChannel(s, new SubProgressMonitor(monitor, 1));
			connectAllStatus.add(connectStatus);
			
		}
		setStatus(connectAllStatus);
		return connectAllStatus;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IStatus refresh(final IProgressMonitor monitor) {
		return Status.OK_STATUS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		super.dispose();
		disconnectAll(null);
		this.connectedChannels.clear();
	}

	@Override
	public void setEnabled(final boolean enabled) {
		super.setEnabled(enabled);
		if (enabled) {
			this.connectJob.schedule();
		} else {
			this.disconnectJob.schedule();
		}
	}
}
