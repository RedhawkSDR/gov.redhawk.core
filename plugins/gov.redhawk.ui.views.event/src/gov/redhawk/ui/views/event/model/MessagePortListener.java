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
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.omg.CORBA.NO_IMPLEMENT;
import org.omg.CORBA.SystemException;
import org.omg.CosEventChannelAdmin.AlreadyConnected;
import org.omg.CosEventChannelAdmin.ConsumerAdmin;
import org.omg.CosEventChannelAdmin.ProxyPullConsumer;
import org.omg.CosEventChannelAdmin.ProxyPushConsumer;
import org.omg.CosEventChannelAdmin.ProxyPushConsumerHelper;
import org.omg.CosEventChannelAdmin.ProxyPushConsumerOperations;
import org.omg.CosEventChannelAdmin.ProxyPushConsumerPOATie;
import org.omg.CosEventChannelAdmin.SupplierAdmin;
import org.omg.CosEventChannelAdmin.SupplierAdminHelper;
import org.omg.CosEventChannelAdmin.SupplierAdminOperations;
import org.omg.CosEventChannelAdmin.SupplierAdminPOATie;
import org.omg.CosEventComm.PushSupplier;
import org.omg.PortableServer.POAPackage.ObjectNotActive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongAdapter;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import CF.PortPackage.InvalidPort;
import CF.PortPackage.OccupiedPort;
import ExtendedEvent.MessageEvent;
import ExtendedEvent.MessageEventHelper;
import ExtendedEvent.MessageEventOperations;
import ExtendedEvent.MessageEventPOATie;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.sca.util.ORBUtil;
import gov.redhawk.sca.util.OrbSession;
import gov.redhawk.ui.views.event.EventViewPlugin;
import mil.jpeojtrs.sca.util.CFErrorFormatter;
import mil.jpeojtrs.sca.util.CorbaUtils;

public class MessagePortListener extends ChannelListener implements MessageEventOperations, SupplierAdminOperations, ProxyPushConsumerOperations {

	private OrbSession session;

	private ScaUsesPort port;
	private String connectionID;

	private MessageEvent messageEventPort;
	private ProxyPushConsumer proxyPushConsumer;
	private SupplierAdmin supplierAdmin;

	public MessagePortListener(IObservableList<Event> history, String channel, ScaUsesPort port) {
		super(history, channel);
		this.port = port;
	}

	/** CHANNEL LISTENER **/
	@Override
	public void connect(OrbSession session) throws CoreException {
		this.session = session;

		try {
			// Create a MessageEvent CORBA object for this instance
			messageEventPort = MessageEventHelper.narrow(session.getPOA().servant_to_reference(new MessageEventPOATie(this)));

			// Call connect port with the CORBA object ref we created
			String newConnectionID = createConnectionID();
			port.connectPort(messageEventPort, newConnectionID);
			connectionID = newConnectionID;
		} catch (ServantNotActive | WrongPolicy e) {
			String msg = String.format("Failed to connect to port '%s'", port.getName());
			logError(msg, e);
		} catch (InvalidPort e) {
			logError(CFErrorFormatter.format(e, port.getName()), e);
		} catch (OccupiedPort e) {
			logError(CFErrorFormatter.format(e, port.getName()), e);
		}
	}

	private String createConnectionID() {
		return System.getProperty("user.name", "user") + "_" + System.currentTimeMillis();
	}

	@Override
	public void disconnect() {
		if (connectionID != null) {
			try {
				port.disconnectPort(connectionID);
			} catch (InvalidPort e) {
				logError(CFErrorFormatter.format(e, port.getName()), e);
			} catch (SystemException e) {
				logError("Unable to disconnect port", e);
			}
			connectionID = null;
		}

		for (org.omg.CORBA.Object reference : new org.omg.CORBA.Object[] { proxyPushConsumer, supplierAdmin, messageEventPort }) {
			if (reference == null) {
				continue;
			}
			try {
				byte[] id = session.getPOA().reference_to_id(reference);
				session.getPOA().deactivate_object(id);
			} catch (ObjectNotActive | WrongPolicy | WrongAdapter | CoreException e) {
				logError("Failed to deactivate CORBA object after listening to message port", e);
			}
			CorbaUtils.release(reference);
		}
		proxyPushConsumer = null;
		supplierAdmin = null;
		messageEventPort = null;
	}

	@Override
	public String getFullChannelName() {
		return this.getChannel();
	}

	/** EVENT CHANNEL OPERATIONS **/
	@Override
	public void destroy() {
		throw new NO_IMPLEMENT();
	}

	@Override
	public ConsumerAdmin for_consumers() {
		throw new NO_IMPLEMENT();
	}

	@Override
	public SupplierAdmin for_suppliers() {
		try {
			supplierAdmin = SupplierAdminHelper.narrow(session.getPOA().servant_to_reference(new SupplierAdminPOATie(this)));
			return supplierAdmin;
		} catch (ServantNotActive | WrongPolicy | CoreException e) {
			logError("Failed to connect to event channel", e);
			return null;
		}
	}

	/** MESSAGE EVENT OPERATIONS **/
	@Override
	public void connectPort(org.omg.CORBA.Object connection, String connectionId) throws InvalidPort, OccupiedPort {
		throw new NO_IMPLEMENT();
	}

	@Override
	public void disconnectPort(String connectionId) throws InvalidPort {
		throw new NO_IMPLEMENT();
	}

	/** SUPPLIER ADMIN OPERATIONS **/
	@Override
	public ProxyPullConsumer obtain_pull_consumer() {
		throw new NO_IMPLEMENT();
	}

	@Override
	public ProxyPushConsumer obtain_push_consumer() {
		try {
			proxyPushConsumer = ProxyPushConsumerHelper.narrow(session.getPOA().servant_to_reference(new ProxyPushConsumerPOATie(this)));
			return proxyPushConsumer;
		} catch (ServantNotActive | WrongPolicy | CoreException e) {
			logError("Failed to connect to event channel", e);
			return null;
		}
	}

	/** PUSH SUPPLIER **/
	@Override
	public void connect_push_supplier(PushSupplier arg0) throws AlreadyConnected {
		ORBUtil.release(arg0);
	}

	private void logError(String msg, Throwable e) {
		Status status = new Status(Status.ERROR, EventViewPlugin.PLUGIN_ID, msg, e);
		Platform.getLog(Platform.getBundle(EventViewPlugin.PLUGIN_ID)).log(status);
	}

}
