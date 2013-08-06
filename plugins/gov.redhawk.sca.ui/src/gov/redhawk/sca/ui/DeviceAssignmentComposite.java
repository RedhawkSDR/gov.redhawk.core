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
package gov.redhawk.sca.ui;

import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.commands.ScaModelCommand;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.sad.SadComponentPlacement;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ListDialog;

import CF.DeviceAssignmentType;

/**
 * @since 8.0
 * 
 */
public class DeviceAssignmentComposite extends Composite {

	private static class DeviceAssignmentRef {
		public static final String PROP_DEVICE = "device";

		private final SadComponentInstantiation component;
		private ScaDevice< ? > device;
		private final PropertyChangeSupport beanSupport = new PropertyChangeSupport(this);

		public DeviceAssignmentRef(final SadComponentInstantiation component) {
			super();
			this.component = component;
		}

		public SadComponentInstantiation getComponent() {
			return this.component;
		}

		public ScaDevice< ? > getDevice() {
			return this.device;
		}

		public void addPropertyChangeListener(final PropertyChangeListener listener) {
			this.beanSupport.addPropertyChangeListener(listener);
		}

		public void setDevice(final ScaDevice< ? > device) {
			final ScaDevice< ? > oldValue = this.device;
			this.device = device;
			this.beanSupport.firePropertyChange(DeviceAssignmentRef.PROP_DEVICE, oldValue, this.device);
		}
	}

	private static class ComponentLabelProvider extends CellLabelProvider {

		@Override
		public void update(final ViewerCell cell) {
			final DeviceAssignmentRef ref = (DeviceAssignmentRef) cell.getElement();
			cell.setText(ref.getComponent().getUsageName());
		}

	}

	private static class DeviceSelectionContentProvider implements IStructuredContentProvider {

		public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {

		}

		public void dispose() {

		}

		public Object[] getElements(final Object inputElement) {
			final List<Object> retVal = new ArrayList<Object>();
			retVal.add("AUTO");
			if (inputElement instanceof ScaDomainManager) {
				final ScaDomainManager domain = (ScaDomainManager) inputElement;
				ScaModelCommand.execute(domain, new ScaModelCommand() {

					public void execute() {
						final Set<ScaDevice< ? >> devices = new HashSet<ScaDevice< ? >>();
						for (final ScaDeviceManager devMgr : domain.getDeviceManagers()) {
							devices.addAll(devMgr.getAllDevices());
						}
						retVal.addAll(devices);
					}
				});
			}
			return retVal.toArray();
		}
	}

	private static class DeviceSelectionLabelProvider extends LabelProvider {
		@Override
		public String getText(final Object element) {
			if (element instanceof ScaDevice< ? >) {
				return ((ScaDevice< ? >) element).getLabel();
			}
			return "AUTO";
		}
	}

	private static class DeviceLabelProvider extends CellLabelProvider {

		@Override
		public void update(final ViewerCell cell) {
			final DeviceAssignmentRef ref = (DeviceAssignmentRef) cell.getElement();
			if (ref.getDevice() == null) {
				cell.setText("AUTO");
			} else {
				cell.setText(ref.getDevice().getLabel());
			}
		}

	}

	private static class DeviceEditingSupport extends EditingSupport {

		private final ComboBoxViewerCellEditor cellViewer;

		public DeviceEditingSupport(final ColumnViewer viewer) {
			super(viewer);
			this.cellViewer = new ComboBoxViewerCellEditor((Composite) getViewer().getControl());
			this.cellViewer.setLabelProvider(new DeviceSelectionLabelProvider());
			this.cellViewer.setContentProvider(new DeviceSelectionContentProvider());

		}

		public void setScaDomainManager(final ScaDomainManager domain) {
			if (domain != null) {
				this.cellViewer.setInput(domain);
			} else {
				this.cellViewer.setInput("");
			}
		}

		@Override
		protected CellEditor getCellEditor(final Object element) {
			return this.cellViewer;
		}

		@Override
		protected boolean canEdit(final Object element) {
			return true;
		}

		@Override
		protected Object getValue(final Object element) {
			Object retVal = ((DeviceAssignmentRef) element).getDevice();
			if (retVal == null) {
				retVal = "AUTO";
			}
			return retVal;
		}

		@Override
		protected void setValue(final Object element, final Object value) {
			if (value instanceof ScaDevice< ? >) {
				((DeviceAssignmentRef) element).setDevice((ScaDevice< ? >) value);
			} else {
				((DeviceAssignmentRef) element).setDevice(null);
			}
			getViewer().refresh(element);
		}

	}

	private final Adapter domainListener = new AdapterImpl() {
		@Override
		public void notifyChanged(final org.eclipse.emf.common.notify.Notification msg) {
			switch (msg.getFeatureID(ScaDomainManager.class)) {
			case ScaPackage.SCA_DOMAIN_MANAGER__CONNECTED:
				if (DeviceAssignmentComposite.this.disposed) {
					if (msg.getNotifier() instanceof EObject) {
						((EObject) msg.getNotifier()).eAdapters().remove(this);
					}
					return;
				}
				if (msg.getNewBooleanValue()) {
					PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

						public void run() {
							if (DeviceAssignmentComposite.this.disposed) {
								return;
							}
							restoreSettings(DeviceAssignmentComposite.this.currentSettings);
							if (!DeviceAssignmentComposite.this.deviceAssignmentViewer.getControl().isDisposed()) {
								DeviceAssignmentComposite.this.deviceAssignmentViewer.refresh();
							}
						}

					});
				}
				break;
			default:
				break;
			}
		}
	};

	private boolean disposed;

	private List<DeviceAssignmentRef> deviceAssignments = new ArrayList<DeviceAssignmentComposite.DeviceAssignmentRef>();
	private TableViewer deviceAssignmentViewer;
	private ScaDomainManager domain;
	private DeviceEditingSupport deviceEditor;

	private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	private final PropertyChangeListener listener = new PropertyChangeListener() {

		public void propertyChange(final PropertyChangeEvent evt) {
			DeviceAssignmentComposite.this.propertyChangeSupport.firePropertyChange(evt);
		}
	};

	private Map<String, String> currentSettings;

	public DeviceAssignmentComposite(final Composite parent, final int style) {
		super(parent, style);
		createControl();
	}

	@Override
	public void dispose() {
		this.disposed = true;
		super.dispose();
	}

	public void setScaDomainManager(final ScaDomainManager domain) {
		if (this.domain != null) {
			this.domain.eAdapters().remove(this.domainListener);
		}
		this.domain = domain;
		if (this.deviceEditor != null) {
			this.deviceEditor.setScaDomainManager(this.domain);
		}
		if (this.domain != null) {
			this.domain.eAdapters().add(this.domainListener);
		}
		if (this.deviceAssignmentViewer != null) {
			restoreSettings(this.currentSettings);
			this.deviceAssignmentViewer.refresh();
		}
	}

	private void createControl() {
		setLayout(new GridLayout(2, false));

		final Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(true, true).create());
		final TableColumnLayout tableLayout = new TableColumnLayout();
		composite.setLayout(tableLayout);

		this.deviceAssignmentViewer = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.SINGLE);
		this.deviceAssignmentViewer.setContentProvider(new ArrayContentProvider());
		this.deviceAssignmentViewer.getTable().setHeaderVisible(true);
		this.deviceAssignmentViewer.getTable().setLinesVisible(true);

		final TableViewerColumn componentColumn = new TableViewerColumn(this.deviceAssignmentViewer, SWT.LEFT);
		componentColumn.setLabelProvider(new ComponentLabelProvider());
		componentColumn.getColumn().setText("Component");

		final TableViewerColumn deviceColumn = new TableViewerColumn(this.deviceAssignmentViewer, SWT.CENTER);
		deviceColumn.setLabelProvider(new DeviceLabelProvider());
		deviceColumn.getColumn().setText("Device");
		this.deviceEditor = new DeviceEditingSupport(this.deviceAssignmentViewer);
		this.deviceEditor.setScaDomainManager(this.domain);
		deviceColumn.setEditingSupport(this.deviceEditor);

		tableLayout.setColumnData(componentColumn.getColumn(), new ColumnWeightData(150));
		tableLayout.setColumnData(deviceColumn.getColumn(), new ColumnWeightData(280));

		new Label(this, SWT.None).setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

		final Button applyToAll = new Button(this, SWT.PUSH);
		applyToAll.setText("Apply To All...");
		applyToAll.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(final SelectionEvent e) {
				handleApplyToAll();
			}

		});
		applyToAll.setLayoutData(GridDataFactory.fillDefaults().align(SWT.RIGHT, SWT.CENTER).create());

	}

	public void setSoftwareAssembly(final SoftwareAssembly waveformFactory) {
		this.deviceAssignments = new ArrayList<DeviceAssignmentRef>();
		if (waveformFactory != null) {
			for (final SadComponentPlacement cp : waveformFactory.getPartitioning().getComponentPlacement()) {
				for (final SadComponentInstantiation ci : cp.getComponentInstantiation()) {
					final DeviceAssignmentRef ref = new DeviceAssignmentRef(ci);
					this.deviceAssignments.add(ref);
					ref.addPropertyChangeListener(this.listener);
				}
			}
		}
		if (this.deviceAssignmentViewer != null) {
			this.deviceAssignmentViewer.setInput(this.deviceAssignments);
		}
	}

	private void handleApplyToAll() {
		final ListDialog dialog = new ListDialog(getShell());
		dialog.setContentProvider(new DeviceSelectionContentProvider());
		dialog.setLabelProvider(new DeviceSelectionLabelProvider());
		dialog.setMessage("Select Device to apply to all components:");
		dialog.setTitle("Apply To All");
		if (this.domain != null) {
			dialog.setInput(this.domain);
		}
		if (dialog.open() == Window.OK) {
			ScaDevice< ? > device = null;
			if (dialog.getResult().length > 0 && dialog.getResult()[0] instanceof ScaDevice< ? >) {
				device = (ScaDevice< ? >) dialog.getResult()[0];
			}
			for (final DeviceAssignmentRef ref : this.deviceAssignments) {
				ref.setDevice(device);
			}
			this.deviceAssignmentViewer.refresh();
		}
	}

	public void storeSettings(final Map<String, String> deviceAssignmentMap) {
		for (final DeviceAssignmentRef ref : this.deviceAssignments) {
			final ScaDevice< ? > device = ref.getDevice();
			if (device != null) {
				deviceAssignmentMap.put(ref.getComponent().getId(), device.getIdentifier());
			} else {
				deviceAssignmentMap.put(ref.getComponent().getId(), null);
			}
		}
	}

	public DeviceAssignmentType[] getDeviceAssignment() {
		final List<DeviceAssignmentType> retVal = new ArrayList<DeviceAssignmentType>();
		final Map<String, String> deviceAssignment = new HashMap<String, String>();
		storeSettings(deviceAssignment);
		for (final Map.Entry<String, String> entry : deviceAssignment.entrySet()) {
			if (entry.getValue() != null) {
				retVal.add(new DeviceAssignmentType(entry.getKey(), entry.getValue()));
			}
		}
		return retVal.toArray(new DeviceAssignmentType[retVal.size()]);
	}

	public void restoreSettings(final Map<String, String> deviceAssignmentMap) {
		this.currentSettings = deviceAssignmentMap;
		for (final DeviceAssignmentRef ref : this.deviceAssignments) {
			ScaDevice< ? > device = null;
			if (deviceAssignmentMap != null) {
				final String deviceId = deviceAssignmentMap.get(ref.getComponent().getId());
				device = this.domain.getDevice(deviceId);
			}
			ref.setDevice(device);
		}
		if (!this.deviceAssignmentViewer.getControl().isDisposed()) {
			this.deviceAssignmentViewer.refresh();
		}
	}

	public void setDefaults() {
		for (final DeviceAssignmentRef ref : this.deviceAssignments) {
			ref.setDevice(null);
		}
		if (!this.deviceAssignmentViewer.getControl().isDisposed()) {
			this.deviceAssignmentViewer.refresh();
		}
	}

	public void addPropertyChangeListener(final PropertyChangeListener listener) {
		this.propertyChangeSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(final PropertyChangeListener listener) {
		this.propertyChangeSupport.removePropertyChangeListener(listener);
	}

}
