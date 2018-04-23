/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package gov.redhawk.logging.ui.eventconsole;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.console.IOConsole;
import org.eclipse.ui.console.IOConsoleOutputStream;
import org.omg.CORBA.Any;
import org.omg.CORBA.SystemException;
import org.omg.CORBA.UserException;
import org.omg.CosEventChannelAdmin.ProxyPushSupplier;
import org.omg.CosEventChannelAdmin.ProxyPushSupplierHelper;
import org.omg.CosEventComm.Disconnected;
import org.omg.CosEventComm.PushConsumer;
import org.omg.CosEventComm.PushConsumerHelper;
import org.omg.CosEventComm.PushConsumerPOA;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import CF.LogEvent;
import CF.LogEventHelper;
import gov.redhawk.logging.ui.LogLevels;
import gov.redhawk.logging.ui.LoggingUiPlugin;
import gov.redhawk.logging.ui.jobs.EventChannelCleanupFamily;
import gov.redhawk.model.sca.ScaEventChannel;
import gov.redhawk.sca.util.OrbSession;
import gov.redhawk.sca.util.SubMonitor;
import mil.jpeojtrs.sca.util.CorbaUtils;
import mil.jpeojtrs.sca.util.CorbaUtils2;

/**
 * Receives {@link LogEvent}s from an event channel and writes them to an {@link IOConsole} output stream.
 */
public class EventChannelToConsoleStream extends PushConsumerPOA {

	private ScaEventChannel eventChannel;
	private IOConsoleOutputStream outputStream;

	private OrbSession session;
	private ProxyPushSupplier pushSupplier;

	private boolean activated = false;
	private volatile boolean disposed = false;

	public EventChannelToConsoleStream(ScaEventChannel eventChannel, IOConsole eventConsole) {
		this.eventChannel = eventChannel;
		this.outputStream = eventConsole.newOutputStream();
		this.session = OrbSession.createSession("gov.redhawk.logging.ui"); //$NON-NLS-1$
	}

	/**
	 * Activates this object with the POA and registers with the event channel.
	 * @throws CoreException
	 */
	public void activateAndConnect(IProgressMonitor monitor) throws CoreException {
		final int WORK_ACTIVATE = 1;
		final int WORK_CONNECT = 1;
		SubMonitor progress = SubMonitor.convert(monitor, WORK_ACTIVATE + WORK_CONNECT);

		// Activate with the POA
		POA poa = session.getPOA();
		final PushConsumer pushConsumer;
		try {
			pushConsumer = PushConsumerHelper.narrow(poa.servant_to_reference(this));
		} catch (ServantNotActive | WrongPolicy e) {
			disposeSession();
			throw new CoreException(new Status(IStatus.ERROR, LoggingUiPlugin.PLUGIN_ID, Messages.EventChannelToConsoleStream_1, e));
		}
		activated = true;
		progress.worked(WORK_ACTIVATE);

		// Connect to the event channel
		try {
			CorbaUtils2.invoke(() -> {
				// Create a new push supplier
				ProxyPushSupplier tmpPushSupplier = eventChannel.getObj().for_consumers().obtain_push_supplier();

				// Transition the object to our ORB (so we won't lose it if the domain gets disposed)
				String ior = session.getOrb().object_to_string(tmpPushSupplier);
				CorbaUtils.release(tmpPushSupplier);
				pushSupplier = ProxyPushSupplierHelper.unchecked_narrow(session.getOrb().string_to_object(ior));

				// Connect this class as a push consumer
				pushSupplier.connect_push_consumer(pushConsumer);
				return null;
			}, progress.newChild(WORK_CONNECT));
		} catch (ExecutionException e) {
			deactivate();
			disposeSession();
			throw new CoreException(new Status(IStatus.ERROR, LoggingUiPlugin.PLUGIN_ID, Messages.EventChannelToConsoleStream_2, e.getCause()));
		}
	}

	public void disconnectAndDeactivate(IProgressMonitor monitor) {
		final int WORK_DISCONNECT = 1;
		final int WORK_DEACTIVATE = 1;
		SubMonitor progress = SubMonitor.convert(monitor, WORK_DISCONNECT + WORK_DEACTIVATE);

		disconnect(progress.newChild(WORK_DISCONNECT));
		deactivate();
		progress.worked(WORK_DEACTIVATE);
		disposeSession();
	}

	/**
	 * Deactivate to stop receiving CORBA requests
	 */
	private void deactivate() {
		if (!activated) {
			return;
		}
		activated = false;

		try {
			POA poa = session.getPOA();
			byte[] id = poa.servant_to_id(this);
			poa.deactivate_object(id);
		} catch (UserException | SystemException e) {
			IStatus status = new Status(IStatus.ERROR, LoggingUiPlugin.PLUGIN_ID, Messages.EventChannelToConsoleStream_4, e);
			LoggingUiPlugin.getDefault().getLog().log(status);
		} catch (CoreException e) {
			IStatus status = new Status(IStatus.ERROR, LoggingUiPlugin.PLUGIN_ID, Messages.EventChannelToConsoleStream_5, e);
			LoggingUiPlugin.getDefault().getLog().log(status);
		}
	}

	/**
	 * Disconnect the push supplier
	 * @monitor The progress monitor (supports cancellation)
	 */
	private void disconnect(IProgressMonitor monitor) {
		if (pushSupplier == null) {
			monitor.done();
			return;
		}

		try {
			CorbaUtils2.invoke(() -> {
				pushSupplier.disconnect_push_supplier();
				return null;
			}, monitor);
		} catch (ExecutionException e) {
			IStatus status = new Status(IStatus.ERROR, LoggingUiPlugin.PLUGIN_ID, Messages.EventChannelToConsoleStream_6, e.getCause());
			LoggingUiPlugin.getDefault().getLog().log(status);
		}

		pushSupplier = null;
	}

	private void disposeSession() {
		if (session != null) {
			session.dispose();
			session = null;
		}
	}

	@Override
	public void disconnect_push_consumer() {
		// This means that we've been disconnected
		deactivate();
	}

	@Override
	public void push(Any any) throws Disconnected {
		if (disposed) {
			return;
		}
		if (!LogEventHelper.type().equals(any.type())) {
			return;
		}

		LogEvent logEvent = LogEventHelper.extract(any);
		LogLevels logLevel = LogLevels.intToLogLevel(logEvent.level);
		String line = String.format("%1$tY-%1$tb-%1$te %1$tT %2$s %3$s\n", logEvent.timeStamp * 1000, logLevel.toString(), logEvent.msg); //$NON-NLS-1$
		try {
			outputStream.write(line);
		} catch (IOException e) {
			asyncDispose();
		}
	}

	public synchronized void asyncDispose() {
		if (disposed) {
			return;
		}
		disposed = true;

		Job job = new Job(Messages.EventChannelToConsoleStream_8 + eventChannel.getName()) {

			@Override
			public boolean belongsTo(Object family) {
				// Make jobs of this class part of a family so they can be found from the job manager
				return family.equals(EventChannelCleanupFamily.class);
			}

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				disconnectAndDeactivate(monitor);
				return Status.OK_STATUS;
			}

		};
		job.setSystem(true);
		job.schedule();
	}

}
