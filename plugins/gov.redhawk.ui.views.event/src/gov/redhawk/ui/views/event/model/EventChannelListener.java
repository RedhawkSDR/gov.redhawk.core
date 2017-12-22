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
package gov.redhawk.ui.views.event.model;

import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.omg.CORBA.SystemException;
import org.omg.CosEventChannelAdmin.AlreadyConnected;
import org.omg.CosEventChannelAdmin.ConsumerAdmin;
import org.omg.CosEventChannelAdmin.EventChannel;
import org.omg.CosEventChannelAdmin.ProxyPushSupplier;
import org.omg.CosEventChannelAdmin.TypeError;
import org.omg.CosEventComm.PushConsumer;
import org.omg.CosEventComm.PushConsumerHelper;
import org.omg.CosEventComm.PushConsumerPOATie;
import org.omg.PortableServer.POAPackage.ObjectNotActive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongAdapter;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import gov.redhawk.ui.views.event.EventViewPlugin;
import mil.jpeojtrs.sca.util.CorbaUtils;
import gov.redhawk.sca.util.OrbSession;

public class EventChannelListener extends ChannelListener {

	private OrbSession session;

	private EventChannel eventChannel;
	private PushConsumer ref;
	private ProxyPushSupplier pushSupplier;

	public EventChannelListener(IObservableList<Event> history, EventChannel eventChannel, String channel) {
		super(history, channel);
		this.eventChannel = eventChannel;
	}

	@Override
	public void connect(OrbSession session) throws CoreException {
		if (ref != null) {
			disconnect();
			return;
		}

		this.session = session;
		try {
			// Create a PushConsumer for this instance to receive events
			ref = PushConsumerHelper.narrow(session.getPOA().servant_to_reference(new PushConsumerPOATie(this)));

			// Connect to the event channel
			ConsumerAdmin consumerAdmin = eventChannel.for_consumers();
			pushSupplier = consumerAdmin.obtain_push_supplier();
			CorbaUtils.release(consumerAdmin);
			pushSupplier.connect_push_consumer(ref);
		} catch (SystemException | ServantNotActive | WrongPolicy | AlreadyConnected | TypeError e) {
			String msg = String.format("Failed to connect to event channel '%s'", getChannel());
			throw new CoreException(new Status(IStatus.ERROR, EventViewPlugin.PLUGIN_ID, msg, e));
		}
	}

	@Override
	public void disconnect() {
		if (pushSupplier != null) {
			try {
				pushSupplier.disconnect_push_supplier();
			} catch (SystemException e) {
				String msg = String.format("Failed to disconnect push supplier after listening to event channel '%s'", getChannel());
				logError(msg, e);
			}
			CorbaUtils.release(pushSupplier);
			pushSupplier = null;
		}

		if (ref != null) {
			try {
				byte[] id = session.getPOA().reference_to_id(ref);
				session.getPOA().deactivate_object(id);
			} catch (WrongAdapter | WrongPolicy | CoreException | ObjectNotActive e) {
				String msg = String.format("Failed to deactivate CORBA object after listening to event channel '%s'", getChannel());
				logError(msg, e);
			}
			CorbaUtils.release(ref);
			ref = null;
		}
	}

	@Override
	public void disconnect_push_consumer() {
	}

	public EventChannel getEventChannel() {
		return this.eventChannel;
	}

	@Override
	public String getFullChannelName() {
		return getChannel();
	}

}
