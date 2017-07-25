package gov.redhawk.ui.views.event;
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
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
import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;
import gov.redhawk.sca.util.OrbSession;
import gov.redhawk.ui.views.event.model.ChannelListener;
import gov.redhawk.ui.views.event.model.DomainChannelListener;
import gov.redhawk.ui.views.event.model.Event;
import gov.redhawk.ui.views.event.model.EventChannelListener;
import mil.jpeojtrs.sca.util.CorbaUtils;

public class EventView extends ViewPart implements ITabbedPropertySheetPageContributor {

	public static final String ID = "gov.redhawk.ui.views.event.eventViewer";

	private EventViewerFactory viewerFactory;

	private XViewer viewer;

	private Action clearAction = new Action("Clear", AbstractUIPlugin.imageDescriptorFromPlugin(EventViewPlugin.PLUGIN_ID, "icons/clear_co.gif")) {
		@Override
		public void run() {
			history.clear();
			viewer.refresh(true);
		}
	};

	private Action scrollLockAction = new Action("Scroll Lock", IAction.AS_CHECK_BOX) {
		{
			setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(EventViewPlugin.PLUGIN_ID, "icons/lock_co.gif"));
		}

		@Override
		public void run() {
			contentProvider.setScrollLock(!contentProvider.isScrollLock());
		}
	};

	private Action detailsAction = new Action("Show Details", IAction.AS_PUSH_BUTTON) {
		{
			setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(EventViewPlugin.PLUGIN_ID, "icons/details.png"));
		}

		@Override
		public void run() {
			try {
				EventView.this.getSite().getPage().showView(IPageLayout.ID_PROP_SHEET);
			} catch (PartInitException e) {
				StatusManager.getManager().handle(new Status(IStatus.ERROR, EventViewPlugin.PLUGIN_ID, "Failed to open Properties view: " + e.getMessage(), e),
					StatusManager.SHOW | StatusManager.LOG);
			}
		}
	};

	private Action disconnectAction = new Action("Disconnect") {
		{
			setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(EventViewPlugin.PLUGIN_ID, "icons/disconnect.gif"));
		}

		@Override
		public void run() {
			disconnectAll(true);
			setEnabled(false);
		};
	};

	private WritableList<Event> history = new WritableList<Event>();

	private List<ChannelListener> channelListeners = new ArrayList<ChannelListener>();

	private OrbSession session = OrbSession.createSession();

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
					StatusManager.getManager().handle(
						new Status(IStatus.ERROR, EventViewPlugin.PLUGIN_ID, "Failed to open Properties view: " + e.getMessage(), e),
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
		Job disconnectAll = new Job("Disconnect all event channels") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				synchronized (EventView.this) {
					SubMonitor subMonitor = SubMonitor.convert(monitor, channelListeners.size());
					for (final ChannelListener listener : channelListeners) {
						try {
							CorbaUtils.invoke(new Callable<Object>() {

								@Override
								public Object call() throws Exception {
									listener.disconnect();
									return null;
								}

							}, subMonitor.newChild(1));
						} catch (CoreException e) {
							// PASS
						} catch (InterruptedException e) {
							// PASS
						}
					}
					channelListeners.clear();
					session.dispose();
				}
				return Status.OK_STATUS;
			}

		};
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
				throw new CoreException(new Status(IStatus.ERROR, EventViewPlugin.PLUGIN_ID, "Failed to connect to domain.", e));
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object getAdapter(Class adapter) {
		if (adapter == IPropertySheetPage.class) {
			return new TabbedPropertySheetPage(this);
		}
		return super.getAdapter(adapter);
	}

}
