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
package gov.redhawk.interceptor.client;

import org.omg.CORBA.Any;
import org.omg.CORBA.LocalObject;
import org.omg.CORBA.ORB;
import org.omg.PortableInterceptor.ClientRequestInfo;
import org.omg.PortableInterceptor.ClientRequestInterceptor;
import org.omg.PortableInterceptor.Current;
import org.omg.PortableInterceptor.ForwardRequest;
import org.omg.PortableInterceptor.InvalidSlot;

/**
 * 
 */
public class ClientInterceptor extends LocalObject implements ClientRequestInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int outCallIndicatorSlotId;
	private final Current piCurrent;

	public ClientInterceptor(final Current piCurrent, final int outCallIndicatorSlotId) {
		this.piCurrent = piCurrent;
		this.outCallIndicatorSlotId = outCallIndicatorSlotId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void send_request(final ClientRequestInfo ri) throws ForwardRequest {
		assertThreadState(ri);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void send_poll(final ClientRequestInfo ri) {
		assertThreadState(ri);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void receive_reply(final ClientRequestInfo ri) {
		//		assertThreadState(ri);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void receive_exception(final ClientRequestInfo ri) throws ForwardRequest {
		//		assertThreadState(ri);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void receive_other(final ClientRequestInfo ri) throws ForwardRequest {
		//		assertThreadState(ri);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String name() {
		return "ClientInterceptor";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void destroy() {

	}

	private String getMethodString(final ClientRequestInfo ri) {
		final StringBuilder retVal = new StringBuilder();
		retVal.append(ri.operation());
		return retVal.toString();
	}

	private void assertThreadState(final ClientRequestInfo ri) {
		final Thread thread = Thread.currentThread();
		if (assertThread(thread)) {
			final String msg = "Invoking CORBA call '" + getMethodString(ri) + "' within UI thread.";
			final RuntimeException exception = new ClientInvalidThreadStateException(msg);
			exception.fillInStackTrace();
			exception.printStackTrace(); // SUPPRESS CHECKSTYLE DEBUG
			throw exception;
		}

		/*
		 * IMPORTANT: Always set the TSC out call indicator in case other interceptors make outcalls for this request.
		 * Otherwise the outcall will not be set for the other interceptor's outcall resulting in infinite recursion.
		 */
		final Any indicator = ORB.init().create_any();
		indicator.insert_boolean(true);
		try {
			this.piCurrent.set_slot(this.outCallIndicatorSlotId, indicator);
		} catch (final InvalidSlot e) {
			// PASS
		}
	}

	private boolean assertThread(final Thread thread) {
		if ("main".equals(thread.getName()) || thread.getId() == 0) {
			return true;
		}
		for (final StackTraceElement element : thread.getStackTrace()) {
			final String className = element.getClassName();
			if (className.startsWith("org.eclipse.swt")) {
				return true;
			} else if ("gov.redhawk.model.sca.commands.ScaModelCommand".equals(className)) {
				return true;
			} else if ("org.eclipse.emf.transaction.impl.TransactionalCommandStackImpl".equals(className)) {
				return true;
			}
		}
		return false;
	}

}
