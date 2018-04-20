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
package gov.redhawk.core.graphiti.dcd.ui.editor;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;

import gov.redhawk.core.graphiti.dcd.ui.diagram.providers.DevMgrExplorerDiagramTypeProvider;
import gov.redhawk.core.graphiti.dcd.ui.modelmap.GraphitiDCDModelAdapter;
import gov.redhawk.core.graphiti.dcd.ui.modelmap.GraphitiDCDModelMap;
import gov.redhawk.core.graphiti.dcd.ui.modelmap.GraphitiDCDModelMapInitializerCommand;
import gov.redhawk.core.graphiti.dcd.ui.modelmap.ScaDeviceManagerModelAdapter;
import gov.redhawk.core.graphiti.ui.editor.AbstractGraphitiDiagramEditor;
import gov.redhawk.core.graphiti.ui.util.DUtil;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.commands.NonDirtyingCommand;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.sca.ui.ScaFileStoreEditorInput;
import mil.jpeojtrs.sca.dcd.DeviceConfiguration;

/**
 * The multi-page explorer editor for device managers ({@link ScaDeviceManager}). Includes a Graphiti diagram.
 */
public class GraphitiDeviceManagerExplorerEditor extends AbstractGraphitiDCDEditor {

	public static final String EDITOR_ID = "gov.redhawk.ide.graphiti.dcd.ui.editor.dcdExplorer";

	private ScaDeviceManager deviceManager;
	private GraphitiDCDModelMap modelMap;
	private ScaDeviceManagerModelAdapter scaListener;
	private GraphitiDCDModelAdapter dcdListener;

	protected ScaDeviceManager getDeviceManager() {
		return deviceManager;
	}

	protected void setDeviceManager(ScaDeviceManager deviceManager) {
		this.deviceManager = deviceManager;
	}

	@Override
	public < T > T getAdapter(Class<T> adapter) {
		if (adapter.isInstance(this.deviceManager)) {
			return adapter.cast(this.deviceManager);
		}
		return super.getAdapter(adapter);
	}

	@Override
	protected void setInput(IEditorInput input) {
		if (input instanceof ScaFileStoreEditorInput) {
			ScaFileStoreEditorInput scaInput = (ScaFileStoreEditorInput) input;
			if (scaInput.getScaObject() instanceof ScaDeviceManager) {
				deviceManager = (ScaDeviceManager) scaInput.getScaObject();
			} else {
				throw new IllegalStateException("Diagram opened with invalid input: " + scaInput.getScaObject());
			}
		}

		super.setInput(input);
	}

	@Override
	protected void addPages() {
		super.addPages();

		getEditingDomain().getCommandStack().removeCommandStackListener(getCommandStackListener());

		// make sure diagram elements reflect current runtime state
		this.modelMap.reflectRuntimeStatus();

		// set layout for sandbox editors
		DUtil.layout(getDiagramEditor());

		// Adjust the text editor's title to the profile file name if possible
		IEditorPart textEditor = getTextEditor();
		if (textEditor != null) {
			int pageIndex = getPages().indexOf(textEditor);
			URI profileURI = deviceManager.getProfileURI();
			if (profileURI != null) {
				setPageText(pageIndex, profileURI.lastSegment());
			}
		}

		// Hide the grid for the explorer diagram
		final Diagram diagram = this.getDiagramEditor().getDiagramBehavior().getDiagramTypeProvider().getDiagram();
		if (DUtil.isDiagramExplorer(diagram)) {
			NonDirtyingCommand.execute(diagram, new NonDirtyingCommand() {
				@Override
				public void execute() {
					diagram.setGridUnit(-1); // hide grid on diagram by setting grid units to -1
				}
			});
		}
	}

	////////////////////////////////////////////////////
	// 1. createDiagramEditor()
	////////////////////////////////////////////////////

	@Override
	protected AbstractGraphitiDiagramEditor createDiagramEditor() {
		return new GraphitiDeviceManagerExplorerDiagramEditor(getEditingDomain());
	}

	////////////////////////////////////////////////////
	// 2. initModelMap()
	////////////////////////////////////////////////////

	@Override
	protected void initModelMap() {
		if (deviceManager == null) {
			throw new IllegalStateException("Can not initialize the model map without a device manager");
		}
		DeviceConfiguration dcd = getDeviceConfiguration();
		if (dcd == null) {
			throw new IllegalStateException("Can not initialize the model map without a device configuration (DCD)");
		}

		modelMap = createModelMapInstance();

		this.dcdListener = new GraphitiDCDModelAdapter(modelMap);
		this.scaListener = getScaModelAdapter();

		// Initialize the model map, then begin listening to the model
		CommandStack stack = getEditingDomain().getCommandStack();
		CompoundCommand command = new CompoundCommand();
		command.append(createModelInitializeCommand());
		command.append(new ScaModelCommand() {

			@Override
			public void execute() {
				scaListener.addAdapter(deviceManager);
			}
		});
		stack.execute(command);
		stack.flush();

		// Listen to the DCD for changes
		dcd.eAdapters().add(this.dcdListener);
	}

	/**
	 * Creates the model map (DCD <-> ScaDeviceManager).
	 * @return
	 */
	protected GraphitiDCDModelMap createModelMapInstance() {
		return new GraphitiDCDModelMap(this, deviceManager);
	}
	
	/**
	 * Load the SCA model adapter 
	 * @return
	 */
	protected ScaDeviceManagerModelAdapter getScaModelAdapter() {
		return new ScaDeviceManagerModelAdapter(modelMap) {

			@Override
			public void notifyChanged(Notification notification) {
				super.notifyChanged(notification);
				if (notification.getNotifier() == deviceManager) {
					if (deviceManager.isDisposed() && !isDisposed()) {
						getEditorSite().getPage().getWorkbenchWindow().getWorkbench().getDisplay().asyncExec(new Runnable() {

							@Override
							public void run() {
								if (!isDisposed()) {
									getEditorSite().getPage().closeEditor(GraphitiDeviceManagerExplorerEditor.this, false);
								}
							}

						});
					}
				}
			}
		};
	}

	/**
	 * Creates an EMF {@link Command} to initialize the model map.
	 * @return
	 */
	protected Command createModelInitializeCommand() {
		DeviceConfiguration dcd = getDeviceConfiguration();
		return new GraphitiDCDModelMapInitializerCommand(modelMap, dcd, deviceManager);
	}

	////////////////////////////////////////////////////
	// 3. createDiagramInput()
	////////////////////////////////////////////////////

	@Override
	protected String getDiagramTypeProviderID() {
		return DevMgrExplorerDiagramTypeProvider.PROVIDER_ID;
	}

	@Override
	public String getDiagramContext() {
		return DUtil.DIAGRAM_CONTEXT_EXPLORER;
	}

	@Override
	protected void addDiagramLinks(Diagram diagram) {
		diagram.getLink().getBusinessObjects().add(deviceManager);
	}

	@Override
	public void dispose() {
		if (this.dcdListener != null) {
			DeviceConfiguration dcd = getDeviceConfiguration();
			if (dcd != null) {
				dcd.eAdapters().remove(this.dcdListener);
			}
			this.dcdListener = null;
		}

		if (this.scaListener != null) {
			ScaModelCommand.execute(deviceManager, new ScaModelCommand() {

				@Override
				public void execute() {
					deviceManager.eAdapters().remove(GraphitiDeviceManagerExplorerEditor.this.scaListener);
				}
			});
			this.scaListener = null;
		}

		super.dispose();
	}

	@Override
	public List<Object> getOutlineItems() {
		return Collections.emptyList();
	}

	@Override
	public void updateTitle() {
		final String name;
		if (deviceManager != null) {
			name = (deviceManager.getLabel() != null) ? deviceManager.getLabel() : "Device Manager";
		} else {
			name = "Device Manager";
		}
		if (Display.getCurrent() != null) {
			setPartName(name);
		} else {
			Display.getDefault().asyncExec(new Runnable() {
				@Override
				public void run() {
					if (!isDisposed()) {
						setPartName(name);
					}
				}
			});
		}
	}
}
