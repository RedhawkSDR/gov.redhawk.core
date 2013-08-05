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
package gov.redhawk.ui.views.monitor.ports;

import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaPortContainer;
import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;
import gov.redhawk.ui.views.internal.monitor.ports.PortMonitorViewConfigDialog;
import gov.redhawk.ui.views.monitor.model.ports.Monitor;
import gov.redhawk.ui.views.monitor.model.ports.MonitorRegistry;
import gov.redhawk.ui.views.monitor.model.ports.PortMonitor;
import gov.redhawk.ui.views.monitor.model.ports.PortSupplierMonitor;
import gov.redhawk.ui.views.monitor.model.ports.PortsFactory;
import gov.redhawk.ui.views.monitor.model.ports.PortsPackage;
import gov.redhawk.ui.views.monitor.model.ports.provider.MonitorRegistryItemProvider;
import gov.redhawk.ui.views.monitor.model.ports.provider.PortsItemProviderAdapterFactory;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

public class PortMonitorView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "gov.redhawk.ui.views.monitor.ports.PortMonitorView";

	private TreeViewer viewer;
	private Action configAction;
	private MonitorRegistry input = PortsFactory.eINSTANCE.createMonitorRegistry();
	private final Adapter jobScheduler = new AdapterImpl() {
		@Override
		public void notifyChanged(final org.eclipse.emf.common.notify.Notification msg) {
			switch (msg.getFeatureID(MonitorRegistry.class)) {
			case PortsPackage.MONITOR_REGISTRY__MONITORS:
				if (PortMonitorView.this.input.getMonitors().isEmpty()) {
					PortMonitorView.this.refreshJob.cancel();
				} else {
					PortMonitorView.this.refreshJob.schedule(PortMonitorView.this.refreshDelta);
				}
				break;
			default:
				break;
			}
		}
	};
	private Job refreshJob = new Job("Refreshing all port stats") {
		{
			setUser(false);
			setSystem(true);
		}

		@Override
		public boolean shouldSchedule() {
			return !PortMonitorView.this.input.getMonitors().isEmpty() && super.shouldSchedule();
		}

		@Override
		public boolean shouldRun() {
			return !PortMonitorView.this.input.getMonitors().isEmpty() && super.shouldRun();
		}

		@Override
		protected IStatus run(final IProgressMonitor monitor) {
			for (final Monitor m : PortMonitorView.this.input.getMonitors()) {
				m.fetchStats();
			}
			schedule(PortMonitorView.this.refreshDelta);
			return Status.OK_STATUS;
		}

	};

	private ComposedAdapterFactory adapterFactory;

	private long refreshDelta = 10000;

	private TreeColumnLayout treeLayout;

	/**
	 * The constructor.
	 */
	public PortMonitorView() {

	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	@Override
	public void createPartControl(final Composite root) {
		final Composite parent = new Composite(root, SWT.None);
		this.treeLayout = new TreeColumnLayout();
		parent.setLayout(this.treeLayout);

		this.viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
		this.viewer.getTree().setHeaderVisible(true);
		this.viewer.getTree().setLinesVisible(true);

		initializeColumns();
		final PortsItemProviderAdapterFactory providesPortAdapterFactory = new PortsItemProviderAdapterFactory() {
			@Override
			public Adapter createMonitorRegistryAdapter() {
				if (this.monitorRegistryItemProvider == null) {
					this.monitorRegistryItemProvider = new MonitorRegistryItemProvider(this) {
						@Override
						public Collection< ? extends EStructuralFeature> getChildrenFeatures(final Object object) {
							if (this.childrenFeatures == null) {
								this.childrenFeatures = new ArrayList<EStructuralFeature>();
								this.childrenFeatures.add(PortsPackage.Literals.MONITOR_REGISTRY__MONITORS);
							}
							return this.childrenFeatures;
						}
					};
				}

				return this.monitorRegistryItemProvider;
			}
		};
		this.adapterFactory = new ComposedAdapterFactory(new AdapterFactory[] {
		        providesPortAdapterFactory, new ScaItemProviderAdapterFactory()
		});
		this.viewer.setContentProvider(new AdapterFactoryContentProvider(this.adapterFactory));
		this.viewer.setInput(this.input);
		makeActions();
		hookContextMenu();
		contributeToActionBars();
		this.getSite().setSelectionProvider(this.viewer);
		this.input.eAdapters().add(this.jobScheduler);
	}

	private void initializeColumns() {
		for (final Column c : StatisticsColumns.DEFAULT_COLUMNS) {
			addColumn(c);
		}
	}

	/**
	 * @since 3.0
	 */
	public void addColumn(final Column c) {
		final TreeColumn column = c.createTreeColumn(this.viewer);
		this.treeLayout.setColumnData(column, new ColumnWeightData(1, column.getResizable()));
		this.viewer.getTree().getParent().layout(true);
	}

	/**
	 * @since 3.0
	 */
	public void removeColumn(final Column c) {
		final TreeColumn column = c.findColumn(this.viewer);
		if (column != null) {
			column.dispose();
		}
	}

	/**
	 * @since 3.0
	 */
	public boolean hasColumn(final Column c) {
		return c.isIn(this.viewer);
	}

	public void setRefreshDelta(final long msec) {
		this.refreshDelta = msec;
	}

	public long getRefreshDelta() {
		return this.refreshDelta;
	}

	@Override
	public void dispose() {
		super.dispose();
		if (this.adapterFactory != null) {
			this.adapterFactory.dispose();
		}
		this.adapterFactory = null;
		this.input.eAdapters().remove(this.jobScheduler);
		if (this.refreshJob != null) {
			this.refreshJob.cancel();
			this.refreshJob = null;
		}
		if (this.input != null) {
			this.input.getMonitors().clear();
			this.input = null;
		}
		this.viewer = null;
		this.configAction = null;
	}

	/**
	 * @since 3.0
	 */
	public void addMonitor(final ScaPortContainer portContainer) {
		PortSupplierMonitor monitor = findMonitor(portContainer);
		if (monitor == null) {
			monitor = PortsFactory.eINSTANCE.createPortSupplierMonitor();
			monitor.setPortContainer(portContainer);
			this.input.getMonitors().add(monitor);
		}
		monitor.fetchStats();
		this.viewer.reveal(monitor);
		this.viewer.expandToLevel(monitor, AbstractTreeViewer.ALL_LEVELS);
	}

	private PortSupplierMonitor findMonitor(final ScaPortContainer portContainer) {
		for (final Monitor m : this.input.getMonitors()) {
			if (m instanceof PortSupplierMonitor) {
				final PortSupplierMonitor ps = (PortSupplierMonitor) m;
				if (ps.getPortContainer() == portContainer) {
					return ps;
				}
			}
		}
		return null;
	}

	public void addMonitor(final ScaPort< ? , ? > port) {
		PortMonitor portMonitor = findMonitor(port);
		if (portMonitor == null) {
			portMonitor = PortsFactory.eINSTANCE.createPortMonitor();
			portMonitor.setPort(port);
			this.input.getMonitors().add(portMonitor);
		}
		portMonitor.fetchStats();
		this.viewer.reveal(portMonitor);
		this.viewer.expandToLevel(portMonitor, AbstractTreeViewer.ALL_LEVELS);
	}

	private PortMonitor findMonitor(final ScaPort< ? , ? > port) {
		for (final Monitor m : this.input.getMonitors()) {
			if (m instanceof PortMonitor) {
				final PortMonitor ps = (PortMonitor) m;
				if (ps.getPort() == port) {
					return ps;
				}
			}
		}
		return null;
	}

	public void removeMonitor(final EObject monitor) {
		EcoreUtil.remove(monitor);
	}

	private void hookContextMenu() {
		final MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(final IMenuManager manager) {
				PortMonitorView.this.fillContextMenu(manager);
			}
		});
		final Menu menu = menuMgr.createContextMenu(this.viewer.getControl());
		this.viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, this.viewer);
	}

	private void contributeToActionBars() {
		final IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
	}

	private void fillLocalPullDown(final IMenuManager manager) {
		manager.add(this.configAction);
	}

	private void fillContextMenu(final IMenuManager manager) {
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void makeActions() {
		this.configAction = new Action() {
			@Override
			public void run() {
				final PortMonitorViewConfigDialog dialog = new PortMonitorViewConfigDialog(getSite().getShell(),
				        PortMonitorView.this,
				        PortMonitorView.this.input);
				dialog.open();

			}
		};
		this.configAction.setText("Configure");
		this.configAction.setToolTipText("Configure the view");
		this.configAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
	public void setFocus() {
		if (this.viewer != null) {
			this.viewer.getControl().setFocus();
		}
	}
}
