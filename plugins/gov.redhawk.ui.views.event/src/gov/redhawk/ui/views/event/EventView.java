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
package gov.redhawk.ui.views.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.nebula.widgets.xviewer.XViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.omg.CosEventChannelAdmin.EventChannel;

import gov.redhawk.model.sca.DomainConnectionException;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;
import gov.redhawk.sca.util.OrbSession;
import gov.redhawk.ui.views.event.model.ChannelListener;
import gov.redhawk.ui.views.event.model.DomainChannelListener;
import gov.redhawk.ui.views.event.model.Event;
import gov.redhawk.ui.views.event.model.EventChannelListener;
import gov.redhawk.ui.views.event.model.MessagePortListener;
import mil.jpeojtrs.sca.util.CorbaUtils2;

public class EventView extends ViewPart implements ITabbedPropertySheetPageContributor {

	public static final String ID = "gov.redhawk.ui.views.event.eventViewer"; //$NON-NLS-1$

	private EventViewerFactory viewerFactory;

	private XViewer viewer;

	private Action clearAction = new Action(Messages.EventView_Action_Clear,
		AbstractUIPlugin.imageDescriptorFromPlugin(EventViewPlugin.PLUGIN_ID, "icons/clear_co.gif")) { //$NON-NLS-1$
		@Override
		public void run() {
			history.clear();
			viewer.refresh(true);
		}
	};

	private Action scrollLockAction = new Action(Messages.EventView_Action_ScrollLock, IAction.AS_CHECK_BOX) {
		{
			setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(EventViewPlugin.PLUGIN_ID, "icons/lock_co.gif")); //$NON-NLS-1$
		}

		@Override
		public void run() {
			contentProvider.setScrollLock(!contentProvider.isScrollLock());
		}
	};

	private Action detailsAction = new Action(Messages.EventView_Action_ShowDetails, IAction.AS_PUSH_BUTTON) {
		{
			setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(EventViewPlugin.PLUGIN_ID, "icons/details.png")); //$NON-NLS-1$
		}

		@Override
		public void run() {
			try {
				EventView.this.getSite().getPage().showView(IPageLayout.ID_PROP_SHEET);
			} catch (PartInitException e) {
				StatusManager.getManager().handle(new Status(IStatus.ERROR, EventViewPlugin.PLUGIN_ID, Messages.EventView_CannotOpenPropertiesView, e),
					StatusManager.SHOW | StatusManager.LOG);
			}
		}
	};

	private Action disconnectAction = new Action(Messages.EventView_Action_Disconnect) {
		{
			setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(EventViewPlugin.PLUGIN_ID, "icons/disconnect.gif")); //$NON-NLS-1$
		}

		@Override
		public void run() {
			disconnectAll(true);
			setEnabled(false);
		};
	};

	private WritableList<Event> history = new WritableList<Event>();

	private List<ChannelListener> channelListeners = new ArrayList<ChannelListener>();

	private OrbSession session = OrbSession.createPersistentServerSession("eventSession");

	private ScaItemProviderAdapterFactory factory;

	private EventViewerContentProvider contentProvider;

	public EventView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());

		Composite viewerComposite = new Composite(parent, SWT.BORDER);
		createViewer(viewerComposite);
		viewerComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

		IActionBars actionBars = getViewSite().getActionBars();
		createToolbarItems(actionBars.getToolBarManager());
	}

	private void createToolbarItems(IToolBarManager toolBarManager) {
		toolBarManager.add(detailsAction);
		toolBarManager.add(disconnectAction);
		toolBarManager.add(clearAction);
		toolBarManager.add(scrollLockAction);
	}

	private void createViewer(Composite parent) {
		parent.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
		viewerFactory = new EventViewerFactory();
		viewer = new XViewer(parent, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI | SWT.FULL_SELECTION, viewerFactory);
		viewer.getTree().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		contentProvider = new EventViewerContentProvider();
		viewer.setContentProvider(contentProvider);
		viewer.setLabelProvider(new EventViewerLabelProvider(viewer));
		viewer.setInput(this.history);

		viewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				try {
					EventView.this.getSite().getPage().showView(IPageLayout.ID_PROP_SHEET);
				} catch (PartInitException e) {
					StatusManager.getManager().handle(new Status(IStatus.ERROR, EventViewPlugin.PLUGIN_ID, Messages.EventView_CannotOpenPropertiesView, e),
						StatusManager.SHOW | StatusManager.LOG);
				}
			}
		});

		getSite().setSelectionProvider(viewer);
	}

	@Override
	public void dispose() {
		super.dispose();
		if (factory != null) {
			factory.dispose();
		}

		disconnectAll(false);
	}

	private void disconnectAll(boolean user) {
		Job disconnectAll = Job.create(Messages.EventView_DisconnectAllJobTitle, monitor -> {
			synchronized (EventView.this) {
				SubMonitor subMonitor = SubMonitor.convert(monitor, channelListeners.size());
				for (final ChannelListener listener : channelListeners) {
					try {
						CorbaUtils2.invoke(() -> {
							listener.disconnect();
							return null;
						}, subMonitor.newChild(1));
					} catch (ExecutionException e) {
						// PASS
					}
				}
				channelListeners.clear();
				session.dispose();
			}
			return Status.OK_STATUS;
		});
		disconnectAll.setUser(user);
		disconnectAll.schedule();
	}

	public synchronized void connect(String channel, final EventChannel eventChannel) throws CoreException {
		// Don't add duplicate listeners
		for (ChannelListener l : channelListeners) {
			if (l.getFullChannelName().equals(channel)) {
				return;
			}
		}

		if (!disconnectAction.isEnabled()) {
			disconnectAction.setEnabled(true);
		}

		final ChannelListener newListener = new EventChannelListener(history, eventChannel, channel);

		newListener.connect(session);
		channelListeners.add(newListener);
	}

	/**
	 * Connect to a port and listen for messages. Note that the channel name and connection ID should be consistent
	 * between method calls. This allows the code to ignore duplicate calls for a connection.
	 * @param channel The channel name
	 * @param port The port to connect to
	 * @param connectionId The connection ID to use, or null to generate one
	 * @throws CoreException
	 */
	public synchronized void connect(final String channel, final ScaUsesPort port, String connectionId) throws CoreException {
		// Don't add duplicate listeners
		String fullChannelId = channel;
		if (connectionId != null) {
			fullChannelId = fullChannelId + '/' + connectionId;
		}
		for (ChannelListener l : channelListeners) {
			if (l.getFullChannelName().equals(fullChannelId)) {
				return;
			}
		}

		if (!disconnectAction.isEnabled()) {
			disconnectAction.setEnabled(true);
		}

		final ChannelListener newListener = new MessagePortListener(history, channel, port, connectionId);
		newListener.connect(session);
		channelListeners.add(newListener);
	}

	public synchronized void connect(final ScaDomainManager domain, final String channel) throws CoreException {
		// Don't add duplicate listeners
		for (ChannelListener l : channelListeners) {
			if (l.getFullChannelName().equals(domain.getLabel() + "/" + channel)) {
				return;
			}
		}

		if (!disconnectAction.isEnabled()) {
			disconnectAction.setEnabled(true);
		}

		final ChannelListener newListener = new DomainChannelListener(history, domain, channel);

		if (!domain.isConnected()) {
			try {
				domain.connect(null, RefreshDepth.SELF);
			} catch (DomainConnectionException e) {
				throw new CoreException(new Status(IStatus.ERROR, EventViewPlugin.PLUGIN_ID, Messages.EventView_CannotConnectToDomain, e));
			}
		}
		newListener.connect(session);
		channelListeners.add(newListener);
	}

	@Override
	public void setFocus() {
		if (viewer != null) {
			viewer.getTree().setFocus();
		}
	}

	@Override
	public void setPartName(String partName) {
		super.setPartName(partName);
	}

	@Override
	public void setTitleToolTip(String toolTip) {
		super.setTitleToolTip(toolTip);
	}

	@Override
	public String getContributorId() {
		return getSite().getId();
	}

	@Override
	public < T > T getAdapter(Class<T> adapter) {
		if (adapter == IPropertySheetPage.class) {
			return adapter.cast(new TabbedPropertySheetPage(this));
		}
		return super.getAdapter(adapter);
	}

}
