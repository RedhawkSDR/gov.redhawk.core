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
package gov.redhawk.ui.views.event.handlers;

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.omg.CosEventChannelAdmin.EventChannel;
import org.omg.CosEventChannelAdmin.EventChannelHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaEventChannel;
import gov.redhawk.model.sca.ScaPortContainer;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;
import gov.redhawk.ui.views.event.EventView;
import gov.redhawk.ui.views.event.EventViewPlugin;
import gov.redhawk.ui.views.namebrowser.view.BindingNode;
import mil.jpeojtrs.sca.util.CorbaUtils2;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

public class EventChannelListenerHandler extends AbstractHandler {

	/**
	 * Parameter used to indicate the user should be prompted for the connection ID (for {@link ScaUsesPort} only).
	 */
	private static final String PARAM_PROMPT_CONN_ID = "gov.redhawk.ui.views.event.portMessageListener.promptConnectionId"; //$NON-NLS-1$

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// Get window, page, selection(s)
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		IWorkbenchPage page = window.getActivePage();
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (selection == null) {
			selection = HandlerUtil.getActiveMenuSelection(event);
		}
		if (!(selection instanceof IStructuredSelection)) {
			return null;
		}

		// Selection
		List< ? > selectedItems = ((IStructuredSelection) selection).toList();
		boolean isMany = selectedItems.size() > 1;

		// Parameters
		boolean promptConnId = Boolean.parseBoolean(event.getParameter(PARAM_PROMPT_CONN_ID));

		// Iterate each selection element - all are added to the same view
		EventView view = null;
		for (final Object obj : selectedItems) {
			if (obj instanceof BindingNode) {
				view = showBindingNode((BindingNode) obj, page, view, isMany);
			} else if (obj instanceof ScaEventChannel) {
				view = showScaEventChannel((ScaEventChannel) obj, page, view, isMany);
			} else if (Platform.getAdapterManager().getAdapter(obj, ScaUsesPort.class) != null) {
				ScaUsesPort usesPort = Platform.getAdapterManager().getAdapter(obj, ScaUsesPort.class);
				view = showPort(usesPort, page, view, isMany, promptConnId);
			}
		}

		return null;
	}

	private EventView showPort(final ScaUsesPort usesPort, IWorkbenchPage page, EventView view, boolean isMany, boolean promptConnId)
		throws ExecutionException {
		ScaItemProviderAdapterFactory factory = new ScaItemProviderAdapterFactory();
		AdapterFactoryLabelProvider labelProvider = new AdapterFactoryLabelProvider(factory);
		ScaPortContainer portContainer = ScaEcoreUtils.getEContainerOfType(usesPort, ScaPortContainer.class);

		// Parameters we'll need
		final String channel = EcoreUtil.getID(portContainer).replace(':', '/') + '/' + usesPort.getName();
		final String uiName = usesPort.getName();
		String tooltip = labelProvider.getText(portContainer) + " -> " + usesPort.getName();
		final String connectionID;

		factory.dispose();

		// If requested, prompt the user for the connection ID
		if (promptConnId) {
			// Dialog params
			String genConnectionID = System.getProperty("user.name", Messages.EventChannelListenerHandler_DefaultUserName) + '_' + System.currentTimeMillis(); //$NON-NLS-1$
			IInputValidator validator = newText -> {
				return newText.isEmpty() ? Messages.EventChannelListenerHandler_Error_ConnIdEmpty : null;
			};

			// Show dialog
			InputDialog dialog = new InputDialog(page.getWorkbenchWindow().getShell(), Messages.EventChannelListenerHandler_ConnectionId_DialogTitle,
				Messages.bind(Messages.EventChannelListenerHandler_ConnectionId_DialogMessage, uiName), genConnectionID, validator);
			dialog.setBlockOnOpen(true);
			if (dialog.open() == InputDialog.CANCEL) {
				return null;
			}

			// Use what the user specified
			connectionID = dialog.getValue();
		} else {
			// No specific ID requested
			connectionID = null;
		}

		// Open the event view
		final EventView finalView;
		if (view == null) {
			try {
				finalView = showView(page, isMany, channel, uiName, tooltip);
			} catch (PartInitException e) {
				throw new ExecutionException(Messages.EventChannelListenerHandler_CannotOpenEventViewer, e);
			}
		} else {
			finalView = view;
		}

		// Connect the event channel to the view
		Job connectJob = Job.create(Messages.bind(Messages.EventChannelListenerHandler_ConnectToEventChannelJobTitle, uiName), monitor -> {
			try {
				// Make the connection
				return CorbaUtils2.invoke(() -> {
					finalView.connect(channel, usesPort, connectionID);
					return Status.OK_STATUS;
				}, monitor);
			} catch (java.util.concurrent.ExecutionException e) {
				return new Status(IStatus.ERROR, EventViewPlugin.PLUGIN_ID,
					Messages.bind(Messages.EventChannelListenerHandler_CannotConnectToEventChannel, uiName), e.getCause());
			}
		});
		connectJob.setUser(true);
		connectJob.schedule();
		return finalView;
	}

	private EventView showBindingNode(final BindingNode node, IWorkbenchPage page, EventView view, boolean isMany) throws ExecutionException {
		final String name = node.getPath();
		final String uiName;
		if (name.indexOf('/') != -1) {
			uiName = name.substring(name.lastIndexOf('/') + 1);
		} else {
			uiName = name;
		}
		final String tooltip = name.replaceAll("/", " -> ");

		// Open the event view
		final EventView finalView;
		if (view == null) {
			try {
				finalView = showView(page, isMany, name, uiName, tooltip);
			} catch (PartInitException e) {
				throw new ExecutionException(Messages.EventChannelListenerHandler_CannotOpenEventViewer, e);
			}
		} else {
			finalView = view;
		}

		// Connect the event channel to the view
		Job connectJob = Job.create(Messages.bind(Messages.EventChannelListenerHandler_ConnectToEventChannelJobTitle, uiName), monitor -> {
			try {
				return CorbaUtils2.invoke(() -> {
					try {
						org.omg.CORBA.Object ref = node.getNamingContext().resolve_str(name);
						EventChannel eventChannel = EventChannelHelper.narrow(ref);
						finalView.connect(name, eventChannel);
						return Status.OK_STATUS;
					} catch (NotFound | CannotProceed | InvalidName e) {
						return new Status(IStatus.ERROR, EventViewPlugin.PLUGIN_ID,
							Messages.bind(Messages.EventChannelListenerHandler_CannotConnectToEventChannel, uiName), e);
					}
				}, monitor);
			} catch (java.util.concurrent.ExecutionException e) {
				return new Status(IStatus.ERROR, EventViewPlugin.PLUGIN_ID,
					Messages.bind(Messages.EventChannelListenerHandler_CannotConnectToEventChannel, uiName), e.getCause());
			}
		});
		connectJob.setUser(true);
		connectJob.schedule();

		return finalView;
	}

	private EventView showScaEventChannel(final ScaEventChannel channel, IWorkbenchPage page, EventView view, boolean isMany) throws ExecutionException {
		// Find the domain manager
		final ScaDomainManager domMgr = ScaEcoreUtils.getEContainerOfType(channel, ScaDomainManager.class);
		if (domMgr == null) {
			return view;
		}

		// Parameters
		final String uiName = domMgr.getLabel() + '/' + channel.getName();
		String tooltip = domMgr.getLabel() + " -> " + channel.getName();

		// Open the event view
		final EventView finalView;
		if (view == null) {
			try {
				finalView = showView(page, isMany, uiName, channel.getName(), tooltip);
			} catch (PartInitException e) {
				throw new ExecutionException(Messages.EventChannelListenerHandler_CannotOpenEventViewer, e);
			}
		} else {
			finalView = view;
		}

		// Connect the event channel to the view
		Job connectJob = Job.create(Messages.bind(Messages.EventChannelListenerHandler_ConnectToEventChannelJobTitle, uiName), monitor -> {
			try {
				return CorbaUtils2.invoke(() -> {
					finalView.connect(domMgr, channel.getName());
					return Status.OK_STATUS;
				}, monitor);
			} catch (java.util.concurrent.ExecutionException e) {
				return new Status(IStatus.ERROR, EventViewPlugin.PLUGIN_ID,
					Messages.bind(Messages.EventChannelListenerHandler_CannotConnectToEventChannel, uiName), e.getCause());
			}
		});
		connectJob.setUser(true);
		connectJob.schedule();

		return finalView;
	}

	/**
	 * @param page
	 * @param isMany
	 * @param name
	 * @return
	 * @throws PartInitException
	 */
	private EventView showView(IWorkbenchPage page, boolean isMany, String viewId, String viewTitle, String viewTooltip) throws PartInitException {
		EventView view;
		if (isMany) {
			view = (EventView) page.showView(EventView.ID, String.valueOf(System.currentTimeMillis()), IWorkbenchPage.VIEW_ACTIVATE);
		} else {
			view = (EventView) page.showView(EventView.ID, viewId, IWorkbenchPage.VIEW_ACTIVATE);
			view.setPartName(viewTitle);
			view.setTitleToolTip(viewTooltip);
		}

		// Show the Properties view whenever the Event View is opened
		page.showView(IPageLayout.ID_PROP_SHEET);

		return view;
	}
}
