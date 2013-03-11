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
package gov.redhawk.sca.model.provider.event.internal.listener;

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.sca.model.provider.event.AbstractEventChannelDataProvider;
import gov.redhawk.sca.model.provider.event.DataProviderActivator;
import gov.redhawk.sca.util.ORBUtil;
import gov.redhawk.sca.util.SilentJob;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.omg.CORBA.Any;
import org.omg.CORBA.ORB;
import org.omg.CORBA.SystemException;
import org.omg.CORBA.UserException;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import CF.DomainManager;
import CF.DomainManagerPackage.InvalidEventChannelName;
import CF.DomainManagerPackage.NotConnected;
import COS.CosEventComm.Disconnected;
import COS.CosEventComm.PushConsumer;
import COS.CosEventComm.PushConsumerHelper;
import COS.CosEventComm.PushConsumerOperations;
import COS.CosEventComm.PushConsumerPOATie;

/**
 * 
 */
public class EventJob extends SilentJob implements PushConsumerOperations {

	public static final String EVENT_DATA_PROVIDER_FAMILY = DataProviderActivator.ID + ".jobFamily";

	private final BlockingQueue<Any> eventQueue = new LinkedBlockingQueue<Any>();
	private final String channelName;

	private final AbstractEventChannelDataProvider< ? > dp;

	private DomainManager domMgr;

	private final UUID id;

	private PushConsumer stub;

	private boolean disposed;

	private ORB orb;

	public EventJob(final String channelName, final AbstractEventChannelDataProvider< ? > dp, final ScaDomainManager domain) throws UserException {
		super(channelName + " event queue");
		this.channelName = channelName;
		this.dp = dp;
		
		this.domMgr = domain.fetchNarrowedObject(null);
		this.id = UUID.randomUUID();
		this.orb = ORBUtil.init(null);

		final POA poa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
		poa.the_POAManager().activate();
		
		this.stub = PushConsumerHelper.narrow(poa.servant_to_reference(new PushConsumerPOATie(this)));

		// Use this to work with 1.7
		//		final org.omg.CORBA.Object obj = domain.getRootContext().resolve_str(domain.getName() + "/" + channelName);
		//		final EventChannel eventChannel = EventChannelHelper.narrow(obj);
		//		final ConsumerAdmin consumerAdmin = eventChannel.for_consumers();
		//		this.proxySupplier = consumerAdmin.obtain_push_supplier();
		//		this.proxySupplier.connect_push_consumer(this.stub);

		this.domMgr.registerWithEventChannel(this.stub, this.id.toString(), channelName);
		setSystem(true);

	}

	public void addEvent(final Any event) {
		if (event == null) {
			return;
		}
		this.eventQueue.offer(event);
		schedule();
	}

	protected List<Any> drainEvents() {
		final ArrayList<Any> notifications = new ArrayList<Any>();
		this.eventQueue.drainTo(notifications);
		return Collections.unmodifiableList(notifications);
	}

	@Override
	public boolean belongsTo(final Object family) {
		return EventJob.EVENT_DATA_PROVIDER_FAMILY.equals(family);
	}

	@Override
	protected void canceling() {
		super.canceling();
		this.eventQueue.clear();
		getThread().interrupt();
	}

	@Override
	public boolean shouldRun() {
		return super.shouldRun() && !this.dp.isDisposed() && !this.disposed;
	}

	@Override
	public boolean shouldSchedule() {
		return super.shouldSchedule() && !this.dp.isDisposed() && !this.disposed;
	}

	@Override
	protected IStatus runSilent(final IProgressMonitor monitor) {
		if (this.dp.isDisposed()) {
			return Status.CANCEL_STATUS;
		}
		final List<Any> events = drainEvents();
		for (final Any data : events) {
			if (monitor.isCanceled()) {
				return Status.CANCEL_STATUS;
			}
			this.dp.handleEvent(this.channelName, data);
		}
		return Status.OK_STATUS;
	}

	public void push(final Any data) throws Disconnected {
		addEvent(data);
	}

	public void disconnect_push_consumer() {
		try {
			if (!this.dp.isDisposed()) {
				this.dp.disconnectChannel(this.channelName, null);
			}
		} catch (final InvalidEventChannelName e) {
			// PASS
		} catch (final NotConnected e) {
			// PASS
		} catch (final SystemException e) {
			// PASS
		}
	}

	public void dispose() {
		if (!this.disposed) {
			if (this.domMgr != null) {
				try {
					this.domMgr.unregisterFromEventChannel(this.id.toString(), this.channelName);
				} catch (final UserException e) {
					// PASS
				} catch (final SystemException e) {
					// PASS
				} finally {
					this.domMgr = null;
				}
			}
			this.stub = null;
		}
		this.disposed = true;
		if (this.orb != null) {
			orb.destroy();
			orb = null;
		}
		cancel();
	}
}
