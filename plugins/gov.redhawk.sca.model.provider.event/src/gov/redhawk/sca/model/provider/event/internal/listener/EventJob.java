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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import mil.jpeojtrs.sca.util.CorbaUtils;
import mil.jpeojtrs.sca.util.NamedThreadFactory;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.omg.CORBA.Any;
import org.omg.CORBA.SystemException;
import org.omg.CosEventComm.Disconnected;
import org.omg.CosEventComm.PushConsumer;
import org.omg.CosEventComm.PushConsumerHelper;
import org.omg.CosEventComm.PushConsumerOperations;
import org.omg.CosEventComm.PushConsumerPOATie;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ObjectNotActive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongAdapter;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import CF.DomainManager;
import CF.DomainManagerOperations;
import CF.InvalidObjectReference;
import CF.DomainManagerPackage.AlreadyConnected;
import CF.DomainManagerPackage.InvalidEventChannelName;
import CF.DomainManagerPackage.NotConnected;

public class EventJob extends SilentJob implements PushConsumerOperations {

	private static final boolean DEBUG = "true".equalsIgnoreCase(Platform.getDebugOption(DataProviderActivator.ID + "/debug"));
	private static final boolean DEBUG_EVENTS = "true".equalsIgnoreCase(Platform.getDebugOption(DataProviderActivator.ID + "/debug/events"));
	private static final ExecutorService EXECUTOR_POOL = Executors.newSingleThreadExecutor(new NamedThreadFactory(EventJob.class.getName()));

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
		setSystem(true);
		this.channelName = channelName;
		this.dp = dp;

		if (DEBUG) {
			IStatus status = new Status(IStatus.INFO, DataProviderActivator.ID, "Creating job: " + getName());
			DataProviderActivator.getInstance().getLog().log(status);
		}

		try {
			this.domMgr = domain.fetchNarrowedObject(null);
			if (this.domMgr == null) {
				IStatus status = new Status(IStatus.ERROR, DataProviderActivator.ID, "The " + domain.getName() + " Domain Manager appears to be down");
				throw new CoreException(status);
			}
			this.id = UUID.randomUUID();

			POA poa = session.getPOA();
			this.stub = PushConsumerHelper.narrow(poa.servant_to_reference(new PushConsumerPOATie(this)));
			this.domMgr.registerWithEventChannel(this.stub, this.id.toString(), channelName);
		} catch (ServantNotActive | WrongPolicy | InvalidObjectReference | InvalidEventChannelName | AlreadyConnected | SystemException e) {
			IStatus status = new Status(IStatus.ERROR, DataProviderActivator.ID, "Failed to register with event channel " + channelName, e);
			if (DEBUG) {
				DataProviderActivator.getInstance().getLog().log(status);
			}
			if (this.stub != null) {
				POA poa = session.getPOA();
				try {
					poa.deactivate_object(poa.reference_to_id(this.stub));
				} catch (ObjectNotActive | WrongPolicy | WrongAdapter e1) {
					// PASS - we did our best
				}
				CorbaUtils.release(this.stub);
				this.stub = null;
			}
			if (this.session != null) {
				this.session.dispose();
			}
			throw new CoreException(status);
		}

		if (DEBUG) {
			IStatus status = new Status(IStatus.INFO, DataProviderActivator.ID, "Job ready: " + getName());
			DataProviderActivator.getInstance().getLog().log(status);
		}
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
		if (DEBUG) {
			IStatus status = new Status(IStatus.INFO, DataProviderActivator.ID, "Canceling the EventJob");
			DataProviderActivator.getInstance().getLog().log(status);
		}

		super.canceling();
		this.eventQueue.clear();

		final Thread t = getThread();
		if (t != null) {
			t.interrupt();
		}
	}

	@Override
	public boolean shouldRun() {
		return !this.dp.isDisposed() && !this.disposed;
	}

	@Override
	public boolean shouldSchedule() {
		return !this.dp.isDisposed() && !this.disposed;
	}

	@Override
	protected IStatus runSilent(final IProgressMonitor monitor) {
		if (this.dp.isDisposed()) {
			return Status.CANCEL_STATUS;
		}
		final List<Any> events = drainEvents();
		if (DEBUG_EVENTS) {
			IStatus status = new Status(IStatus.INFO, DataProviderActivator.ID, "Processing " + events.size() + " events");
			DataProviderActivator.getInstance().getLog().log(status);
		}
		for (final Any data : events) {
			if (monitor.isCanceled()) {
				return Status.CANCEL_STATUS;
			}
			this.dp.handleEvent(this.channelName, data);
		}
		return Status.OK_STATUS;
	}

	@Override
	public void push(final Any data) throws Disconnected {
		if (DEBUG_EVENTS) {
			IStatus status = new Status(IStatus.INFO, DataProviderActivator.ID, "Received event for " + ((this.domMgr != null) ? this.domMgr.identifier() : null));
			DataProviderActivator.getInstance().getLog().log(status);
		}
		addEvent(data);
	}

	@Override
	public void disconnect_push_consumer() {
		if (DEBUG) {
			IStatus status = new Status(IStatus.INFO, DataProviderActivator.ID, "disconnect_push_consumer: " + getName());
			DataProviderActivator.getInstance().getLog().log(status);
		}

		try {
			if (!this.dp.isDisposed()) {
				this.dp.disconnectChannel(this.channelName, null);
			}
		} catch (final InvalidEventChannelName | NotConnected | SystemException e) {
			// PASS
		}
	}

	public void dispose() {
		if (!this.disposed) {
			if (DEBUG) {
				IStatus status = new Status(IStatus.INFO, DataProviderActivator.ID, "Disposing job: " + getName());
				DataProviderActivator.getInstance().getLog().log(status);
			}
			if (this.domMgr != null) {
				final DomainManagerOperations localDomMgr = domMgr;
				this.domMgr = null;
				final String localId = this.id.toString();
				final String localChannelName = this.channelName;
				final PushConsumer localStub = stub;
				stub = null;
				final OrbSession localSession = session;
				session = null;
				EventJob.EXECUTOR_POOL.submit(new Runnable() {

					@Override
					public void run() {
						try {
							if (localDomMgr != null && localId != null && localChannelName != null) {
								localDomMgr.unregisterFromEventChannel(localId, localChannelName);
							}
						} catch (InvalidEventChannelName | NotConnected | SystemException e) {
							// PASS
						} finally {
							if (localStub != null) {
								try {
									POA poa = localSession.getPOA();
									poa.deactivate_object(poa.reference_to_id(localStub));
								} catch (ObjectNotActive | WrongPolicy | WrongAdapter e1) {
									// PASS - we did our best
								} catch (CoreException e) {
									// PASS
								}
								CorbaUtils.release(localStub);
							}
							if (localSession != null) {
								localSession.dispose();
							}
						}
					}
				});
			}
		}

		this.disposed = true;
		cancel();
	}
}
