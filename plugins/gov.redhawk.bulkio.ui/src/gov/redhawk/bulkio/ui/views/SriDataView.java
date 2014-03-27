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
package gov.redhawk.bulkio.ui.views;

import gov.redhawk.bulkio.ui.BulkIOUIActivator;
import gov.redhawk.bulkio.ui.internal.SriDataViewContentProvider;
import gov.redhawk.bulkio.ui.internal.SriDataViewLabelProvider;
import gov.redhawk.bulkio.ui.internal.SriDataViewReceiver;
import gov.redhawk.bulkio.ui.internal.SriWrapper;
import gov.redhawk.bulkio.ui.writer.SriFileWriter;
import gov.redhawk.bulkio.ui.writer.SriXmlWriter;
import gov.redhawk.bulkio.util.BulkIOType;
import gov.redhawk.model.sca.IDisposable;
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.ScaWaveform;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.progress.IWorkbenchSiteProgressService;
import org.eclipse.ui.statushandlers.StatusManager;

public class SriDataView extends ViewPart {
	public static final String ID = "gov.redhawk.bulkio.ui.sridata.view";

	private TreeViewer viewer;
	private TreeColumnLayout treeLayout;

	private SriDataViewContentProvider contentProvider;
	private SriDataViewReceiver sriReceiver;
	private Map<String, SriWrapper> streamMap;

	private SwitchStreamMenuAction switchStreamAction;
	private IAction clearTerminatedAction;
	private IAction clearAllTerminatedAction;
	private IAction getNotificationsAction;
	private IAction pauseSri;
	private IAction saveSriToFile;
	private boolean notifyOnChanged = true;
	private boolean paused = false;
	private boolean terminatedStreams = true;

	private Adapter portListener = new AdapterImpl() {
		@Override
		public void notifyChanged(final Notification msg) {
			Display.getDefault().syncExec(new Runnable() {
				@Override
				public void run() {
					switch (msg.getFeatureID(IDisposable.class)) {
					case ScaPackage.IDISPOSABLE__DISPOSED:
						getSite().getPage().hideView(SriDataView.this);
						break;
					default:
						break;
					}
				}
			});
		}
	};

	@Override
	public void createPartControl(Composite root) {
		final Composite parent = new Composite(root, SWT.None);
		this.viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
		this.viewer.setUseHashlookup(true);
		this.viewer.getTree().setHeaderVisible(true);
		this.viewer.getTree().setLinesVisible(true);

		this.treeLayout = new TreeColumnLayout();
		parent.setLayout(treeLayout);

		initializeColumns();

		contentProvider = new SriDataViewContentProvider();
		this.viewer.setContentProvider(contentProvider);
		this.viewer.setLabelProvider(new SriDataViewLabelProvider());

		createActions();
		createToolBars();
	}

	private void initializeColumns() {
		TreeColumn nameColumn = new TreeColumn(viewer.getTree(), SWT.NONE);
		nameColumn.setWidth(100);
		nameColumn.setText("Property: ");
		treeLayout.setColumnData(nameColumn, new ColumnWeightData(20, 50));

		TreeColumn valueColumn = new TreeColumn(viewer.getTree(), SWT.NONE);
		valueColumn.setWidth(200);
		valueColumn.setText("Value: ");
		treeLayout.setColumnData(valueColumn, new ColumnWeightData(80, 100));
	}

	private void createToolBars() {
		final IActionBars bars = getViewSite().getActionBars();
		final IToolBarManager toolBarManager = bars.getToolBarManager();
		if (clearTerminatedAction != null) {
			if (!terminatedStreams) {
				this.clearTerminatedAction.setEnabled(false);
			} else {
				this.clearTerminatedAction.setEnabled(true);
			}
			toolBarManager.add(this.clearTerminatedAction);
		}
		if (clearAllTerminatedAction != null) {
			toolBarManager.add(this.clearAllTerminatedAction);
		}
		toolBarManager.add(new Separator());
		if (pauseSri != null) {
			toolBarManager.add(this.pauseSri);
		}
		if (getNotificationsAction != null) {
			toolBarManager.add(this.getNotificationsAction);
		}
		if (switchStreamAction != null) {
			toolBarManager.add(this.switchStreamAction);
		}
		if (saveSriToFile != null) {
			toolBarManager.add(this.saveSriToFile);
		}
		toolBarManager.add(new Separator());
		toolBarManager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void createActions() {
		//Drop-down action that holds a list of actions to use when switching between multiple streams
		this.switchStreamAction = new SwitchStreamMenuAction();
		final ImageDescriptor switchStreamImageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(BulkIOUIActivator.PLUGIN_ID, "icons/sri.gif");
		this.switchStreamAction.setImageDescriptor(switchStreamImageDescriptor);

		//Action to turn on or off notification of a change in the SRI content (bolding of the tab text) 
		this.getNotificationsAction = new Action("Notify on receiving new Push SRI", IAction.AS_CHECK_BOX) {
			@Override
			public void run() {
				if (notifyOnChanged) {
					notifyOnChanged = false;
				} else {
					notifyOnChanged = true;
				}
			}
		};
		this.getNotificationsAction.setEnabled(true);
		final ImageDescriptor getNotificationsImageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(BulkIOUIActivator.PLUGIN_ID,
			"icons/sri_updating.gif");
		this.getNotificationsAction.setImageDescriptor(getNotificationsImageDescriptor);

		//Action to pause/un-pause reception of new data
		this.pauseSri = new Action("Pause incoming SRI data", IAction.AS_CHECK_BOX) {
			@Override
			public void run() {
				if (paused) {
					paused = false;
					sriReceiver.updateViewStreamMap();
				} else {
					paused = true;
					sriReceiver.updateViewStreamMap();
				}
			}
		};
		this.pauseSri.setEnabled(true);
		final ImageDescriptor pauseSriImageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(BulkIOUIActivator.PLUGIN_ID, "icons/sri_pause.gif");
		this.pauseSri.setImageDescriptor(pauseSriImageDescriptor);

		//Action to save SRI data to file
		this.saveSriToFile = new Action("Save SRI Data to file", IAction.AS_PUSH_BUTTON) {
			@Override
			public void run() {
				Shell parent = getSite().getShell();
				FileDialog saveDialog = new FileDialog(parent, SWT.SAVE | SWT.CANCEL);
				saveDialog.setText("Save SRI to File");
				String saveLocation = saveDialog.open();

				Map<String, SriWrapper> streamMapToSave = sriReceiver.getStreamMap();
				List<String> filesWritten = new ArrayList<String>();
				SriFileWriter writer = new SriFileWriter();
				SriXmlWriter xmlWriter = new SriXmlWriter();
				try {
					String nl = System.getProperty("line.separator");
					BulkIOType bulkioType = sriReceiver.getBulkIOType();

					writer.performSave(streamMapToSave, saveLocation, bulkioType, parent);
					if (writer.getFileName() != null) {
						filesWritten.add(nl + writer.getFileName());
					}
					
					xmlWriter.performSave(streamMapToSave, saveLocation, bulkioType, parent);
					if (xmlWriter.getFileName() != null) {					
						filesWritten.add(nl + xmlWriter.getFileName());
					}
					
					if (saveLocation != null && !filesWritten.isEmpty()) {
						displayFiles(filesWritten);
					}
					
				} catch (IOException e) {
					MessageBox error = new MessageBox(getSite().getShell(), SWT.ICON_ERROR | SWT.OK);
					error.setMessage("Error during save operation.  Files were not saved");
					error.open();
				}
			}

			private void displayFiles(List<String> filesWritten) {
				MessageBox confirmation = new MessageBox(getSite().getShell(), SWT.ICON_INFORMATION | SWT.OK);
				String savedFiles = "";
				for (String s : filesWritten) {
					savedFiles += s;
				}
				confirmation.setMessage("Files Saved Successfully: " + savedFiles);
				confirmation.open();
			}
		};
		this.saveSriToFile.setEnabled(true);
		final ImageDescriptor saveSriImageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(BulkIOUIActivator.PLUGIN_ID, "icons/sri_save.gif");
		this.saveSriToFile.setImageDescriptor(saveSriImageDescriptor);

		//Action to clear an Active SRI (the one visible in the view) if it has been terminated)
		this.clearTerminatedAction = new Action() {
			@Override
			public void run() {
				streamMap = sriReceiver.getStreamMap();
				String activeSriID = sriReceiver.getActiveStreamID();
				SriWrapper stream = streamMap.get(activeSriID);
				if (stream != null) {
					if (stream.isEOS()) {
						//If EOS is reached for the active (visible) SRI, then remove it from the Map
						streamMap.remove(activeSriID);
					}
					if (streamMap.isEmpty()) {
						//If this was the only SRI being tracked, clear the view
						sriReceiver.setActiveStreamID("");
					} else {
						//Otherwise, switch the view to the next SRI in the Map
						Map.Entry<String, SriWrapper> nextStream = streamMap.entrySet().iterator().next();
						sriReceiver.setActiveStreamID(nextStream.getKey());
					}
				}
				setTerminatedStreams(false);
			}
		};
		this.clearTerminatedAction.setToolTipText("Clear Selected SRI");
		final ImageDescriptor clearTerminatedImageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(BulkIOUIActivator.PLUGIN_ID, "icons/sri_rem.gif");
		this.clearTerminatedAction.setImageDescriptor(clearTerminatedImageDescriptor);

		//Action to clear all terminated SRIs from the view
		this.clearAllTerminatedAction = new Action() {
			@Override
			public void run() {
				streamMap = sriReceiver.getStreamMap();
				for (Iterator<Entry<String, SriWrapper>> i = streamMap.entrySet().iterator(); i.hasNext();) {
					Entry<String, SriWrapper> stream = i.next();
					if (stream.getValue().isEOS()) {
						i.remove();
					}
				}
				if (streamMap.isEmpty()) {
					//If there are no non-terminated SRIs, clear the view
					sriReceiver.setActiveStreamID("");
				} else {
					//Otherwise, switch the view to the next SRI in the Map
					Map.Entry<String, SriWrapper> nextStream = streamMap.entrySet().iterator().next();
					sriReceiver.setActiveStreamID(nextStream.getKey());
				}
				setTerminatedStreams(false);
			}
		};
		this.clearAllTerminatedAction.setToolTipText("Clear All SRIs");
		final ImageDescriptor clearAllTerminatedImageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(BulkIOUIActivator.PLUGIN_ID,
			"icons/sri_remall.gif");
		this.clearAllTerminatedAction.setImageDescriptor(clearAllTerminatedImageDescriptor);
	}

	private class SwitchStreamMenuAction extends Action implements IMenuCreator {
		private Menu menu;
		private List<Action> changeStreamActions = new ArrayList<Action>();

		public SwitchStreamMenuAction() {
			super("Change Active Stream", IAction.AS_DROP_DOWN_MENU);
			setMenuCreator(this);
		}

		@Override
		public void run() {
		}

		@Override
		public void dispose() {
			if (menu != null) {
				menu.dispose();
				menu = null;
			}
		}

		@Override
		public Menu getMenu(Control parent) {
			if (menu != null) {
				menu.dispose();
			}
			menu = new Menu(parent.getShell(), SWT.POP_UP | SWT.NONE);
			changeStreamActions = getStreamActions();
			if (!changeStreamActions.isEmpty()) {
				for (Action action : changeStreamActions) {
					addActionToMenu(action);
				}
			}
			return menu;
		}

		@Override
		public Menu getMenu(Menu parent) {
			return null;
		}

		public void addActionToMenu(@NonNull Action action) {
			ActionContributionItem item = new ActionContributionItem(action);
			item.fill(menu, -1);
		}
	}

	@NonNull
	private List<Action> getStreamActions() {
		if (sriReceiver != null) {
			List<Action> streamActions = new ArrayList<Action>();
			streamMap = sriReceiver.getStreamMap();
			for (final Map.Entry<String, SriWrapper> e : streamMap.entrySet()) {
				Action action = new Action(e.getKey(), IAction.AS_RADIO_BUTTON) {
					@Override
					public void run() {
						sriReceiver.setActiveStreamID(e.getKey()); //informs receiver of active SRI
						contentProvider.setActiveSriID(e.getKey()); //informs content provider of active SRI
					}
				};
				action.setEnabled(true);

				//Use icon to denote active stream in dropdown menu
				if (sriReceiver.getActiveStreamID().equals(e.getKey())) {
					action.setChecked(true);
					action.setText(e.getKey());
				} else {
					action.setText(e.getKey());
				}

				//add <EOS> to any streams that have reached End of Stream
				if (e.getValue().isEOS()) {
					action.setText("<EOS> " + e.getKey());
				}
				action.setToolTipText("View SRI Data for " + e.getKey());
				streamActions.add(action);
			}
			return streamActions;
		} else {
			//If the receiver has not been activated yet, simply return an empty list
			return new ArrayList<Action>();
		}
	}

	public void contentChanged() {
		createActions();
		createToolBars();
		IWorkbenchPart part = getSite().getPart();
		IWorkbenchSiteProgressService service = (IWorkbenchSiteProgressService) part.getSite().getAdapter(IWorkbenchSiteProgressService.class);
		if (notifyOnChanged) {
			service.warnOfContentChange();
		}
	}
	
	public void activateReceiver(@NonNull ScaUsesPort port, @NonNull String connectionId) {
		if (sriReceiver != null) {
			return;
		}
		BulkIOType type = BulkIOType.getType(port.getRepid());
		sriReceiver = new SriDataViewReceiver(type, viewer, this);
		sriReceiver.setPort(port);
		sriReceiver.setConnectionID(connectionId);
		try {
			sriReceiver.connect();
			port.eAdapters().add(SriDataView.this.portListener);
		} catch (CoreException e) {
			StatusManager.getManager().handle(new Status(Status.ERROR, BulkIOUIActivator.PLUGIN_ID, "Could not connect to port", e));
		}
	}

	public void activateReceiver(@NonNull ScaUsesPort port) {
		activateReceiver(port, null);
	}

	@Override
	public void dispose() {
		if (sriReceiver != null) {
			sriReceiver.disconnect();
			sriReceiver = null;
		}

		super.dispose();
	}

	@Override
	public void setFocus() {
		if (this.viewer != null) {
			this.viewer.getControl().setFocus();
		}
	}

	@Override
	public void setPartName(@Nullable String partName) {
		super.setPartName(partName);
	}

	@Override
	public void setTitleToolTip(@Nullable String toolTip) {
		super.setTitleToolTip(toolTip);
	}

	public boolean isPaused() {
		return paused;
	}

	public TreeViewer getTreeViewer() {
		return viewer;
	}
	
	public void setTerminatedStreams(boolean flag) {
		terminatedStreams = flag;
	}

	@NonNull
	public static String createSecondaryId(@NonNull ScaUsesPort port) {
		EObject eObj = port.eContainer();

		StringBuilder retVal = new StringBuilder();

		if (eObj instanceof ScaComponent) {
			ScaComponent component = (ScaComponent) eObj;
			retVal.append(component.getIdentifier());
		} else if (eObj instanceof ScaWaveform) {
			ScaWaveform waveform = (ScaWaveform) eObj;
			retVal.append(waveform.getIdentifier());
		} else {
			retVal.append("port");
		}

		retVal.append("_" + port.getName());
		
		return retVal.toString().replace(':', '_');
	}
}
