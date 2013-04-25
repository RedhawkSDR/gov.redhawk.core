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
import gov.redhawk.sca.util.OrbSession;
import gov.redhawk.sca.util.SilentJob;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.omg.CORBA.Any;
import org.omg.CORBA.SystemException;
import org.omg.CORBA.UserException;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import CF.DomainManager;
import CF.InvalidObjectReference;
import CF.DomainManagerPackage.AlreadyConnected;
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
	
	private OrbSession session = OrbSession.createSession();

	private final BlockingQueue<Any> eventQueue = new LinkedBlockingQueue<Any>();
	private final String channelName;

	private final AbstractEventChannelDataProvider< ? > dp;

	private DomainManager domMgr;

	private final UUID id;

	private boolean disposed;

	private PushConsumer stub;


	public EventJob(final String channelName, final AbstractEventChannelDataProvider< ? > dp, final ScaDomainManager domain) throws CoreException {
		super(channelName + " event queue");
		this.channelName = channelName;
		this.dp = dp;
		
		this.domMgr = domain.fetchNarrowedObject(null);
		this.id = UUID.randomUUID();

		
		POA poa = session.getPOA();
		try {
	        this.stub = PushConsumerHelper.narrow(poa.servant_to_reference(new PushConsumerPOATie(this)));
	        this.domMgr.registerWithEventChannel(stub, this.id.toString(), channelName);
        } catch (ServantNotActive e) {
	        throw new CoreException(new Status(Status.ERROR, DataProviderActivator.ID, "Failed to register with event channel.", e));
        } catch (WrongPolicy e) {
        	throw new CoreException(new Status(Status.ERROR, DataProviderActivator.ID, "Failed to register with event channel.", e));
        } catch (InvalidObjectReference e) {
        	throw new CoreException(new Status(Status.ERROR, DataProviderActivator.ID, "Failed to register with event channel.", e));
        } catch (InvalidEventChannelName e) {
        	throw new CoreException(new Status(Status.ERROR, DataProviderActivator.ID, "Failed to register with event channel.", e));
        } catch (AlreadyConnected e) {
        	throw new CoreException(new Status(Status.ERROR, DataProviderActivator.ID, "Failed to register with event channel.", e));
        }
		
		
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
		}
		
		if (this.stub != null) {
			this.stub._release();
			this.stub = null;
		}
		this.disposed = true;
		if (this.session != null) {
			session.dispose();
			session = null;
		}
		cancel();
	}
}
