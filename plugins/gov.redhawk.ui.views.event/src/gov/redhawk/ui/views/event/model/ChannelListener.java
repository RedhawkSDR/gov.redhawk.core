/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.ui.views.event.model;

import gov.redhawk.sca.util.OrbSession;
import gov.redhawk.ui.views.event.EventViewPlugin;

import java.util.Date;

import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.omg.CORBA.Any;
import org.omg.CosEventComm.Disconnected;
import org.omg.CosEventComm.PushConsumerOperations;

public abstract class ChannelListener implements PushConsumerOperations {

	private IObservableList<Event> history;
	private String channel;

	public ChannelListener(IObservableList<Event> history, String channel) {
		this.history = history;
		this.channel = channel;
	}

	public abstract void connect(OrbSession session) throws CoreException;

	public abstract void disconnect();

	/**
	 * @return The full channel name used to unambiguously identify a connection to some object for this view.
	 */
	public abstract String getFullChannelName();

	@Override
	public void disconnect_push_consumer() {
	}

	@Override
	public void push(final Any arg0) throws Disconnected {
		this.history.getRealm().asyncExec(() -> {
			final Event event = new Event(arg0, channel, new Date());
			history.add(event);
		});
	}

	public String getChannel() {
		return channel;
	}

	protected void logError(String msg, Throwable e) {
		Status status = new Status(Status.ERROR, EventViewPlugin.PLUGIN_ID, msg, e);
		Platform.getLog(Platform.getBundle(EventViewPlugin.PLUGIN_ID)).log(status);
	}

}
