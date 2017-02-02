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
import org.omg.CosEventChannelAdmin.EventChannel;
import org.omg.CosEventChannelAdmin.ProxyPushSupplier;
import org.omg.CosEventChannelAdmin.TypeError;
import org.omg.CosEventComm.PushConsumer;
import org.omg.CosEventComm.PushConsumerHelper;
import org.omg.CosEventComm.PushConsumerPOATie;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import gov.redhawk.ui.views.event.EventViewPlugin;
import gov.redhawk.sca.util.ORBUtil;
import gov.redhawk.sca.util.OrbSession;

public class EventChannelListener extends ChannelListener {

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
		POA poa = session.getPOA();
		try {
			ref = PushConsumerHelper.narrow(poa.servant_to_reference(new PushConsumerPOATie(this)));
			pushSupplier = eventChannel.for_consumers().obtain_push_supplier();
			pushSupplier.connect_push_consumer(ref);
		} catch (SystemException e) {
			throw new CoreException(new Status(IStatus.ERROR, EventViewPlugin.PLUGIN_ID, "Failed to connect to event channel for monitor: " + "("
					+ getChannel() + ")", e));
		} catch (ServantNotActive e) {
			throw new CoreException(new Status(IStatus.ERROR, EventViewPlugin.PLUGIN_ID, "Failed to connect to event channel for monitor: " + "("
					+ getChannel() + ")", e));
		} catch (WrongPolicy e) {
			throw new CoreException(new Status(IStatus.ERROR, EventViewPlugin.PLUGIN_ID, "Failed to connect to event channel for monitor: " + "("
					+ getChannel() + ")", e));
		} catch (AlreadyConnected e) {
			throw new CoreException(new Status(IStatus.ERROR, EventViewPlugin.PLUGIN_ID, "Failed to connect to event channel for monitor: " + "("
					+ getChannel() + ")", e));
		} catch (TypeError e) {
			throw new CoreException(new Status(IStatus.ERROR, EventViewPlugin.PLUGIN_ID, "Failed to connect to event channel for monitor: " + "("
					+ getChannel() + ")", e));
		}

	}

	@Override
	public void disconnect() {
		if (ref == null) {
			return;
		}
		if (pushSupplier != null) {
			try {
				pushSupplier.disconnect_push_supplier();
			} catch (SystemException e) {
				// PASS
			}
		}
		if (ref != null) {
			ORBUtil.release(ref);
		}
		ref = null;
	}

	@Override
	public void disconnect_push_consumer() {
		// TODO Auto-generated method stub

	}

	public EventChannel getEventChannel() {
		return this.eventChannel;
	}

	@Override
	public String getFullChannelName() {
		return getChannel();
	}

}
