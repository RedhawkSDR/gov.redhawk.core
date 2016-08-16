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
package gov.redhawk.core.graphiti.sad.ui.editor;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.statushandlers.StatusManager;

import gov.redhawk.core.graphiti.sad.ui.GraphitiSadUIPlugin;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.commands.NonDirtyingCommand;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.sca.ui.ScaFileStoreEditorInput;
import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import mil.jpeojtrs.sca.util.CorbaUtils;

/**
 * The multi-page explorer editor for waveforms ({@link ScaWaveform}). Includes a Graphiti diagram.
 */
public class GraphitiWaveformExplorerEditor extends AbstractGraphitiSADEditor {

	private ScaGraphitiModelAdapter scaListener;
	private SadGraphitiModelAdapter sadlistener;
	private GraphitiModelMap modelMap;
	private ScaWaveform waveform;

	protected ScaWaveform getWaveform() {
		return waveform;
	}

	protected void setWaveform(ScaWaveform waveform) {
		this.waveform = waveform;
	}

	protected GraphitiModelMap getModelMap() {
		return modelMap;
	}

	@Override
	protected void setInput(IEditorInput input) {
		if (input instanceof ScaFileStoreEditorInput) {
			ScaFileStoreEditorInput scaInput = (ScaFileStoreEditorInput) input;
			if (scaInput.getScaObject() instanceof ScaWaveform) {
				setWaveform((ScaWaveform) scaInput.getScaObject());
			} else {
				throw new IllegalStateException("Diagram opened with invalid input: " + scaInput.getScaObject());
			}
		}

		super.setInput(input);
	}

	@Override
	public String getDiagramContext(Resource sadResource) {
		return DUtil.DIAGRAM_CONTEXT_EXPLORER;
	}

	@Override
	protected void addPages() {
		super.addPages();

		getEditingDomain().getCommandStack().removeCommandStackListener(getCommandStackListener());

		// reflect runtime aspects here
		getModelMap().reflectRuntimeStatus();

		// set layout for sandbox editors
		DUtil.layout(editor);

		// Adjust the text editor's title to the profile file name if possible
		IEditorPart textEditor = getTextEditor();
		if (textEditor != null) {
			int pageIndex = getPages().indexOf(textEditor);
			URI profileURI = waveform.getProfileURI();
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

	@Override
	protected AbstractGraphitiDiagramEditor createDiagramEditor() {
		AbstractGraphitiDiagramEditor editor = super.createDiagramEditor();
		editor.addContext("gov.redhawk.ide.graphiti.sad.ui.contexts.sandbox");
		return editor;
	}

	@Override
	protected void initModelMap() throws CoreException {
		if (waveform == null) {
			throw new IllegalStateException("Can not initialize the model map without a waveform");
		}
		SoftwareAssembly sad = getSoftwareAssembly();
		if (sad == null) {
			throw new IllegalStateException("Can not initialize the model map without a software assembly (SAD)");
		}

		if (!waveform.isSetComponents()) {
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
										waveform.refresh(monitor, RefreshDepth.FULL);
										return null;
									}

								}, monitor);
							} catch (CoreException e) {
								throw new InvocationTargetException(e);
							}
						}
					});
				} catch (InvocationTargetException | InterruptedException e) {
					// PASS
				}
			} else {
				try {
					waveform.refresh(null, RefreshDepth.FULL);
				} catch (InterruptedException e) {
					// PASS
				}
			}
		}

		try {
			ProgressMonitorDialog loadCompDialog = new ProgressMonitorDialog(Display.getCurrent().getActiveShell());
			final int numOfLoadingItems = waveform.getComponents().size() * 2; // for getProfile and getStarted calls
			loadCompDialog.run(true, true, new IRunnableWithProgress() {

				@Override
				public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					monitor.beginTask("Loading Waveform Components...", numOfLoadingItems);

					ExecutorService executor = Executors.newSingleThreadExecutor();
					Future<Object> future = executor.submit(new Callable<Object>() {

						@Override
						public Object call() throws Exception {
							int totalProgress = 0;
							while (totalProgress < numOfLoadingItems && !monitor.isCanceled()) {
								int newProgress = 0;

								for (ScaComponent component : waveform.getComponents()) {
									if (component.getProfile() != null) {
										newProgress++;
									}

									if (component.getStarted() != null) {
										newProgress++;
									}
								}

								if (newProgress > totalProgress) {
									monitor.worked(newProgress - totalProgress);
									totalProgress = newProgress;
								}
								Thread.sleep(250);
							}
							return null;
						}

					});

					try {
						future.get(30, TimeUnit.SECONDS);
					} catch (InterruptedException | ExecutionException | TimeoutException e) {
						monitor.setCanceled(true);
						StatusManager.getManager().handle(new Status(IStatus.ERROR, GraphitiSadUIPlugin.PLUGIN_ID, "Waveform components failed to load", e),
							StatusManager.SHOW | StatusManager.LOG);
					} finally {
						monitor.done();
					}
				}
			});
		} catch (final Exception e) { // SUPPRESS CHECKSTYLE Logged Catch all exception
			StatusManager.getManager().handle(new Status(IStatus.ERROR, GraphitiSadUIPlugin.PLUGIN_ID, "Errors occured while loading waveform components", e),
				StatusManager.SHOW | StatusManager.LOG);
		}

		modelMap = new GraphitiModelMap(this, waveform);

		this.sadlistener = new SadGraphitiModelAdapter(modelMap);
		this.scaListener = new ScaGraphitiModelAdapter(modelMap) {

			@Override
			public void notifyChanged(Notification notification) {
				super.notifyChanged(notification);
				if (notification.getNotifier() == waveform) {
					if (waveform.isDisposed() && !isDisposed()) {
						getEditorSite().getPage().getWorkbenchWindow().getWorkbench().getDisplay().asyncExec(new Runnable() {

							@Override
							public void run() {
								if (!isDisposed()) {
									getEditorSite().getPage().closeEditor(GraphitiWaveformExplorerEditor.this, false);
								}
							}

						});
					}
				}
			}
		};

		// Initialize the model map, then begin listening to the model
		CommandStack stack = getEditingDomain().getCommandStack();
		CompoundCommand command = new CompoundCommand();
		command.append(createModelInitializeCommand());
		command.append(new ScaModelCommand() {

			@Override
			public void execute() {
				scaListener.addAdapter(waveform);

			}
		});
		stack.execute(command);
		stack.flush();

		// Listen to the SAD for changes
		sad.eAdapters().add(this.sadlistener);
	}

	/**
	 * Creates an EMF {@link Command} to initialize the model map.
	 * @return
	 */
	protected Command createModelInitializeCommand() {
		SoftwareAssembly sad = getSoftwareAssembly();
		return new GraphitiModelMapInitializerCommand(modelMap, sad, waveform);
	}

	@Override
	public void dispose() {
		if (this.sadlistener != null) {
			if (sad != null) {
				sad.eAdapters().remove(this.sadlistener);
			}
			this.sadlistener = null;
		}

		if (this.scaListener != null) {
			ScaModelCommand.execute(waveform, new ScaModelCommand() {

				@Override
				public void execute() {
					waveform.eAdapters().remove(GraphitiWaveformExplorerEditor.this.scaListener);
				}
			});
			this.scaListener = null;
		}

		super.dispose();
	}

	@Override
	public void doSave(final IProgressMonitor monitor) {
		doSaveAs();
	}

	@Override
	public List<Object> getOutlineItems() {
		return Collections.emptyList();
	}

	@Override
	public void updateTitle() {
		final String name;
		if (waveform != null) {
			name = (waveform.getName() != null) ? waveform.getName() : "Waveform";
		} else {
			name = "Waveform";
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

	public void componentRegistered(final SadComponentInstantiation component) {
		refreshSelectedObject(component);
	}

	protected void addDiagramLinks(Diagram diagram) {
		diagram.getLink().getBusinessObjects().add(waveform);
	}
}
