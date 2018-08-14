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
package gov.redhawk.model.sca.tests.stubs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.omg.CosEventChannelAdmin.EventChannel;
import org.omg.CosEventChannelAdmin.EventChannelHelper;
import org.omg.CosEventComm.PushConsumer;
import org.omg.CosEventComm.PushSupplier;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextPackage.AlreadyBound;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POAPackage.ObjectNotActive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongAdapter;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import CF.EventChannelInfoIteratorHelper;
import CF.EventChannelInfoIteratorHolder;
import CF.EventChannelInfoIteratorPOA;
import CF.EventChannelManagerOperations;
import CF.EventRegistrantIteratorHolder;
import CF.EventChannelManagerPackage.ChannelAlreadyExists;
import CF.EventChannelManagerPackage.ChannelDoesNotExist;
import CF.EventChannelManagerPackage.EventChannelInfo;
import CF.EventChannelManagerPackage.EventChannelInfoHolder;
import CF.EventChannelManagerPackage.EventChannelInfoListHolder;
import CF.EventChannelManagerPackage.EventChannelReg;
import CF.EventChannelManagerPackage.EventRegistrantListHolder;
import CF.EventChannelManagerPackage.EventRegistration;
import CF.EventChannelManagerPackage.InvalidChannelName;
import CF.EventChannelManagerPackage.OperationFailed;
import CF.EventChannelManagerPackage.OperationNotAllowed;
import CF.EventChannelManagerPackage.PublisherReg;
import CF.EventChannelManagerPackage.RegistrationAlreadyExists;
import CF.EventChannelManagerPackage.RegistrationDoesNotExist;
import CF.EventChannelManagerPackage.RegistrationsExists;
import CF.EventChannelManagerPackage.ServiceUnavailable;
import gov.redhawk.model.sca.tests.TestEnvirornment;
import gov.redhawk.sca.util.OrbSession;

public class EventChannelManagerImpl implements EventChannelManagerOperations {

	private OrbSession session;
	private NamingContext context;

	private Map<String, org.omg.CORBA.Object> eventChannels = new HashMap<>();
	private Set<String> releaseWhenUnused = new HashSet<>();

	/**
	 * @param session
	 * @param context The domain manager's naming context
	 */
	public EventChannelManagerImpl(OrbSession session, NamingContext context) {
		this.session = session;
		this.context = context;
	}

	public void reset() {
		List<String> channelNames = new ArrayList<>(eventChannels.keySet());
		for (String channelName : channelNames) {
			try {
				releaseInternal(channelName, true);
			} catch (OperationFailed e) {
				// forced=true so an exception won't actually be thrown
			}
		}
	}

	@Override
	public EventChannel create(String channelName) throws ChannelAlreadyExists, OperationNotAllowed, OperationFailed, ServiceUnavailable {
		if (eventChannels.containsKey(channelName)) {
			throw new ChannelAlreadyExists();
		}

		// Create event channel, activate with POA
		EventChannel eventChannelRef;
		try {
			EventChannelImpl eventChannelImpl = new EventChannelImpl();
			org.omg.CORBA.Object ref = session.getPOA().servant_to_reference(eventChannelImpl);
			eventChannelRef = EventChannelHelper.unchecked_narrow(ref);
		} catch (ServantNotActive | WrongPolicy | CoreException e) {
			TestEnvirornment.log(IStatus.ERROR, "Unable to activate event channel in POA", e);
			throw new OperationFailed();
		}

		try {
			context.bind(new NameComponent[] { new NameComponent(channelName, "") }, eventChannelRef);
		} catch (NotFound | CannotProceed | InvalidName | AlreadyBound e) {
			TestEnvirornment.log(IStatus.ERROR, "Failed to bind event channel in naming context", e);
			try {
				byte[] oid = session.getPOA().reference_to_id(eventChannelRef);
				session.getPOA().deactivate_object(oid);
			} catch (ObjectNotActive | WrongPolicy | CoreException | WrongAdapter e1) {
				TestEnvirornment.log(IStatus.ERROR, "Failed to deactive event channel with POA during rollback", e);
			}
			throw new OperationFailed();
		}

		eventChannels.put(channelName, eventChannelRef);
		return eventChannelRef;
	}

	@Override
	public EventChannel get(String channelName) throws ChannelDoesNotExist, OperationNotAllowed, OperationFailed, ServiceUnavailable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventChannel createForRegistrations(String channelName) throws ChannelAlreadyExists, OperationNotAllowed, OperationFailed, ServiceUnavailable {
		EventChannel retVal = create(channelName);
		releaseWhenUnused.add(channelName);
		return retVal;
	}

	@Override
	public void markForRegistrations(String channelName) throws ChannelDoesNotExist, OperationNotAllowed, OperationFailed, ServiceUnavailable {
		if (!eventChannels.containsKey(channelName)) {
			throw new ChannelDoesNotExist();
		}
		releaseWhenUnused.add(channelName);
	}

	@Override
	public void release(String channelName) throws ChannelDoesNotExist, RegistrationsExists, OperationNotAllowed, OperationFailed, ServiceUnavailable {
		if (!eventChannels.containsKey(channelName)) {
			throw new ChannelDoesNotExist();
		}

		// TODO: Check for registrants

		releaseInternal(channelName, false);
	}

	@Override
	public void forceRelease(String channelName) throws ChannelDoesNotExist, OperationNotAllowed, OperationFailed, ServiceUnavailable {
		if (!eventChannels.containsKey(channelName)) {
			throw new ChannelDoesNotExist();
		}

		// TODO: Remove registrants?

		releaseInternal(channelName, true);
		return;
	}

	private void releaseInternal(String channelName, boolean force) throws OperationFailed {
		try {
			context.unbind(new NameComponent[] { new NameComponent(channelName, "") });
		} catch (NotFound | CannotProceed | InvalidName e) {
			TestEnvirornment.log(IStatus.ERROR, "Couldn't unbind event channel from naming context", e);
		}

		try {
			byte[] oid = session.getPOA().reference_to_id(eventChannels.get(channelName));
			session.getPOA().deactivate_object(oid);
		} catch (ObjectNotActive | WrongAdapter | WrongPolicy | CoreException e) {
			TestEnvirornment.log(IStatus.ERROR, "Unable to deactive event channel in POA", e);
			if (!force) {
				throw new OperationFailed();
			}
		}

		eventChannels.remove(channelName);
		releaseWhenUnused.remove(channelName);
	}

	@Override
	public EventChannelReg registerConsumer(PushConsumer consumer, EventRegistration req)
		throws InvalidChannelName, RegistrationAlreadyExists, OperationFailed, OperationNotAllowed, ServiceUnavailable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PublisherReg registerPublisher(EventRegistration req, PushSupplier disconnectReceiver)
		throws InvalidChannelName, RegistrationAlreadyExists, OperationFailed, OperationNotAllowed, ServiceUnavailable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventChannelReg registerResource(EventRegistration req)
		throws InvalidChannelName, RegistrationAlreadyExists, OperationFailed, OperationNotAllowed, ServiceUnavailable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void unregister(EventRegistration reg) throws ChannelDoesNotExist, RegistrationDoesNotExist, ServiceUnavailable {
		// TODO Auto-generated method stub
	}

	@Override
	public void listChannels(int howMany, EventChannelInfoListHolder elist, EventChannelInfoIteratorHolder eiter) {
		// TODO: Only handles -1 case for now
		if (howMany != -1) {
			throw new IllegalArgumentException("Current test implementation only handles -1 for how_many");
		}

		// Get all channels
		elist.value = eventChannels.keySet().stream() //
				.map(name -> new EventChannelInfo(name, 0)) //
				.toArray(EventChannelInfo[]::new);

		// Create an iterator
		EventChannelInfoIteratorPOA iter = new EventChannelInfoIteratorPOA() {

			@Override
			public boolean next_one(EventChannelInfoHolder eci) {
				return false;
			}

			@Override
			public boolean next_n(int howMany, EventChannelInfoListHolder ecil) {
				return false;
			}

			@Override
			public void destroy() {
				try {
					byte[] oid = session.getPOA().servant_to_id(this);
					session.getPOA().deactivate_object(oid);
				} catch (CoreException | ObjectNotActive | WrongPolicy | ServantNotActive e) {
					TestEnvirornment.log(IStatus.ERROR, "Unable to deactive event channel list iterator", e);
				}
			}
		};
		try {
			org.omg.CORBA.Object ref = session.getPOA().servant_to_reference(iter);
			eiter.value = EventChannelInfoIteratorHelper.unchecked_narrow(ref);
		} catch (ServantNotActive | WrongPolicy | CoreException e) {
			TestEnvirornment.log(IStatus.ERROR, "Unable to create reference for event channel list iterator", e);
		}
	}

	@Override
	public void listRegistrants(String channelName, int howMany, EventRegistrantListHolder rlist, EventRegistrantIteratorHolder riter) {
		// TODO Auto-generated method stub
	}
}
