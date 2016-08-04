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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Callable;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.ui.util.EditUIUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.statushandlers.StatusManager;

import gov.redhawk.core.graphiti.dcd.ui.GraphitiDcdUIPlugin;
import gov.redhawk.ide.debug.LocalSca;
import gov.redhawk.ide.debug.ScaDebugPlugin;
import gov.redhawk.ide.debug.internal.ScaDebugInstance;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.commands.NonDirtyingCommand;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.sca.ui.ScaFileStoreEditorInput;
import mil.jpeojtrs.sca.dcd.DcdComponentInstantiation;
import mil.jpeojtrs.sca.dcd.DcdFactory;
import mil.jpeojtrs.sca.dcd.DeviceConfiguration;
import mil.jpeojtrs.sca.util.CorbaUtils;

/**
 * The multi-page explorer editor for device managers ({@link ScaDeviceManager}). Includes a Graphiti diagram.
 */
public class GraphitiDeviceManagerExplorerEditor extends GraphitiDcdEditor {

	public static final String EDITOR_ID = "gov.redhawk.ide.graphiti.dcd.ui.editor.dcdExplorer";

	private Resource mainResource;
	private DeviceConfiguration dcd;
	private GraphitiDcdModelMap modelMap;
	private ScaDeviceManager deviceManager;
	private ScaGraphitiModelAdapter scaListener;
	private DcdGraphitiModelAdapter dcdListener;

	@Override
	public String getDiagramContext(Resource sadResource) {
		return DUtil.DIAGRAM_CONTEXT_EXPLORER;
	}

	protected ScaDeviceManager getDeviceManager() {
		return deviceManager;
	}

	protected GraphitiDcdModelMap getModelMap() {
		return modelMap;
	}

	@Override
	protected void createModel() {
		final URI resourceURI = EditUIUtil.getURI(getEditorInput());
		// For safety we'll decode the URI to make sure escape sequences have been correctly represented
		String decodedURIString = URI.decode(resourceURI.toString());
		final URI decodedURI = URI.createURI(decodedURIString);
		mainResource = getEditingDomain().getResourceSet().createResource(decodedURI);

		dcd = DcdFactory.eINSTANCE.createDeviceConfiguration();

		getEditingDomain().getCommandStack().execute(new ScaModelCommand() {

			@Override
			public void execute() {
				mainResource.getContents().add(dcd);
			}
		});

		initModelMap();
	}

	@Override
	protected void setInput(IEditorInput input) {
		if (input instanceof ScaFileStoreEditorInput) {
			ScaFileStoreEditorInput scaInput = (ScaFileStoreEditorInput) input;
			if (scaInput.getScaObject() instanceof ScaDeviceManager) {
				deviceManager = (ScaDeviceManager) scaInput.getScaObject();
			} else {
				throw new IllegalStateException("Node Diagram opened on invalid sca input " + scaInput.getScaObject());
			}
		} else if (input instanceof URIEditorInput) {
			URIEditorInput uriInput = (URIEditorInput) input;
			if (uriInput.getURI().equals(ScaDebugInstance.getLocalSandboxDeviceManagerURI())) {
				final LocalSca localSca = ScaDebugPlugin.getInstance().getLocalSca();
				if (!ScaDebugInstance.INSTANCE.isInit()) {
					ProgressMonitorDialog dialog = new ProgressMonitorDialog(Display.getCurrent().getActiveShell());
					try {
						dialog.run(true, true, new IRunnableWithProgress() {

							@Override
							public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
								monitor.beginTask("Starting Chalkboard...", IProgressMonitor.UNKNOWN);
								try {
									ScaDebugInstance.INSTANCE.init(monitor);
								} catch (CoreException e) {
									throw new InvocationTargetException(e);
								}
								monitor.done();
							}

						});
					} catch (InvocationTargetException e1) {
						StatusManager.getManager().handle(new Status(Status.ERROR, GraphitiDcdUIPlugin.ID, "Failed to initialize sandbox.", e1),
							StatusManager.SHOW | StatusManager.LOG);
					} catch (InterruptedException e1) {
						// PASS
					}
				}

				deviceManager = localSca.getSandboxDeviceManager();
				if (deviceManager == null) {
					ProgressMonitorDialog dialog = new ProgressMonitorDialog(Display.getCurrent().getActiveShell());
					try {
						dialog.run(true, true, new IRunnableWithProgress() {

							@Override
							public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
								monitor.beginTask("Starting Sandbox...", IProgressMonitor.UNKNOWN);
								try {
									ScaDebugPlugin.getInstance().getLocalSca(monitor);
								} catch (CoreException e) {
									throw new InvocationTargetException(e);
								}

							}
						});
					} catch (InvocationTargetException e) {
						throw new IllegalStateException("Failed to setup sandbox", e);
					} catch (InterruptedException e) {
						throw new IllegalStateException("Sandbox setup canceled, can not load editor.");
					}
					deviceManager = localSca.getSandboxDeviceManager();
					if (deviceManager == null) {
						throw new IllegalStateException("Failed to setup sandbox, null device manager.", null);
					}
				}
			}
		} else {
			throw new IllegalStateException("Node Diagram opened on invalid input " + input);
		}

		super.setInput(input);
	}

	@Override
	public Resource getMainResource() {
		if (mainResource == null) {
			return super.getMainResource();
		}
		return mainResource;
	}

	protected void initModelMap() {
		if (deviceManager == null) {
			throw new IllegalStateException("Can not initialize the Model Map with null local device manager");
		}

		if (dcd == null) {
			throw new IllegalStateException("Can not initialize the Model Map with null dcd");
		}

		if (!deviceManager.isSetDevices()) {

			if (Display.getCurrent() != null) {
				ProgressMonitorDialog dialog = new ProgressMonitorDialog(Display.getCurrent().getActiveShell());
				try {
					dialog.run(true, true, new IRunnableWithProgress() {

						@Override
						public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
							try {
								CorbaUtils.invoke(new Callable<Object>() {

									@Override
									public Object call() throws Exception {
										deviceManager.refresh(monitor, RefreshDepth.FULL);
										return null;
									}

								}, monitor);
							} catch (CoreException e) {
								throw new InvocationTargetException(e);
							}
						}
					});
				} catch (InvocationTargetException e) {
					// PASS
				} catch (InterruptedException e) {
					// PASS
				}
			} else {
				try {
					deviceManager.refresh(null, RefreshDepth.FULL);
				} catch (InterruptedException e) {
					// PASS
				}
			}

		}

		modelMap = new GraphitiDcdModelMap(this, dcd, deviceManager);
		getEditingDomain().getCommandStack().execute(new GraphitiDcdModelMapInitializerCommand(modelMap, dcd, deviceManager));
		getEditingDomain().getCommandStack().flush();

		this.dcdListener = new DcdGraphitiModelAdapter(modelMap);
		this.scaListener = new ScaGraphitiModelAdapter(modelMap) {
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

		ScaModelCommand.execute(this.deviceManager, new ScaModelCommand() {

			@Override
			public void execute() {
				scaListener.addAdapter(deviceManager);
			}
		});

		dcd.eAdapters().add(this.dcdListener);
	}

	@Override
	public void dispose() {
		if (this.dcdListener != null) {
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
	protected void addPages() {
		if (!getEditingDomain().getResourceSet().getResources().isEmpty()
			&& !(getEditingDomain().getResourceSet().getResources().get(0)).getContents().isEmpty()) {
			try {
				final Resource dcdResource = getMainResource();

				final DiagramEditor editor = createDiagramEditor();
				setDiagramEditor(editor);
				final IEditorInput input = createDiagramInput(dcdResource);
				int pageIndex = addPage(editor, input);
				setPageText(pageIndex, "Diagram");

				getEditingDomain().getCommandStack().removeCommandStackListener(getCommandStackListener());

				// make sure diagram elements reflect current runtime state
				this.modelMap.reflectRuntimeStatus();

				// set layout for sandbox editors
				DUtil.layout(editor);
			} catch (final PartInitException e) {
				StatusManager.getManager().handle(new Status(IStatus.ERROR, GraphitiDcdUIPlugin.PLUGIN_ID, "Failed to create editor parts.", e),
					StatusManager.LOG | StatusManager.SHOW);
			} catch (final IOException e) {
				StatusManager.getManager().handle(new Status(IStatus.ERROR, GraphitiDcdUIPlugin.PLUGIN_ID, "Failed to create editor parts.", e),
					StatusManager.LOG | StatusManager.SHOW);
			} catch (final CoreException e) {
				StatusManager.getManager().handle(new Status(IStatus.ERROR, GraphitiDcdUIPlugin.PLUGIN_ID, "Failed to create editor parts.", e),
					StatusManager.LOG | StatusManager.SHOW);
			}
		}

		try {
			IEditorPart textEditor = createTextEditor(getEditorInput());
			setTextEditor(textEditor);
			if (textEditor != null) {
				final int dcdSourcePageNum = addPage(-1, textEditor, getEditorInput(), getMainResource());
				for (String s : deviceManager.getProfile().split("/")) {
					if (s.contains(".xml")) {
						this.setPageText(dcdSourcePageNum, s);
						break;
					}
					this.setPageText(dcdSourcePageNum, deviceManager.getLabel());
				}
			}
		} catch (PartInitException e) {
			StatusManager.getManager().handle(new Status(IStatus.ERROR, GraphitiDcdUIPlugin.PLUGIN_ID, "Failed to add pages.", e));
		}

		final Diagram diagram = this.getDiagramEditor().getDiagramBehavior().getDiagramTypeProvider().getDiagram();
		NonDirtyingCommand.execute(diagram, new NonDirtyingCommand() {
			@Override
			public void execute() {
				diagram.setGridUnit(-1); // hide grid on diagram by setting grid units to -1
			}
		});
	}

	@Override
	protected DiagramEditor createDiagramEditor() {
		GraphitiDcdDiagramEditor editor = new GraphitiDcdDiagramEditor((TransactionalEditingDomain) getEditingDomain());
		editor.addContext("gov.redhawk.ide.dcd.graphiti.ui.contexts.sandbox");
		return editor;
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

	public void deviceRegistered(DcdComponentInstantiation device) {
		refreshSelectedObject(device);
	}

	@Override
	protected void addDiagramLinks(Diagram diagram) {
		diagram.getLink().getBusinessObjects().add(deviceManager);
	}
}
