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

import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;

import gov.redhawk.core.graphiti.sad.ui.diagram.providers.WaveformExplorerDiagramTypeProvider;
import gov.redhawk.core.graphiti.sad.ui.modelmap.GraphitiSADModelAdapter;
import gov.redhawk.core.graphiti.sad.ui.modelmap.GraphitiSADModelMap;
import gov.redhawk.core.graphiti.sad.ui.modelmap.GraphitiSADModelMapInitializerCommand;
import gov.redhawk.core.graphiti.sad.ui.modelmap.ScaWaveformModelAdapter;
import gov.redhawk.core.graphiti.ui.editor.AbstractGraphitiDiagramEditor;
import gov.redhawk.core.graphiti.ui.util.DUtil;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.commands.NonDirtyingCommand;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.sca.ui.ScaFileStoreEditorInput;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;

/**
 * The multi-page explorer editor for waveforms ({@link ScaWaveform}). Includes a Graphiti diagram.
 */
public class GraphitiWaveformExplorerEditor extends AbstractGraphitiSADEditor {

	private ScaWaveform waveform;
	private GraphitiSADModelMap modelMap;
	private ScaWaveformModelAdapter scaListener;
	private GraphitiSADModelAdapter sadlistener;

	protected ScaWaveform getWaveform() {
		return waveform;
	}

	protected void setWaveform(ScaWaveform waveform) {
		this.waveform = waveform;
	}

	@Override
	public < T > T getAdapter(Class<T> adapter) {
		if (adapter.isInstance(this.waveform)) {
			return adapter.cast(this.waveform);
		}
		return super.getAdapter(adapter);
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
	protected void addPages() {
		super.addPages();

		getEditingDomain().getCommandStack().removeCommandStackListener(getCommandStackListener());

		// reflect runtime aspects here
		modelMap.reflectRuntimeStatus();

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

	////////////////////////////////////////////////////
	// 1. createDiagramEditor()
	////////////////////////////////////////////////////

	@Override
	protected AbstractGraphitiDiagramEditor createDiagramEditor() {
		return new GraphitiWaveformExplorerDiagramEditor(getEditingDomain());
	}

	////////////////////////////////////////////////////
	// 2. initModelMap()
	////////////////////////////////////////////////////

	@Override
	protected void initModelMap() throws CoreException {
		if (waveform == null) {
			throw new IllegalStateException("Can not initialize the model map without a waveform");
		}
		SoftwareAssembly sad = getSoftwareAssembly();
		if (sad == null) {
			throw new IllegalStateException("Can not initialize the model map without a software assembly (SAD)");
		}

		modelMap = createModelMapInstance();

		this.sadlistener = new GraphitiSADModelAdapter(modelMap);
		this.scaListener = new ScaWaveformModelAdapter(modelMap) {

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
	 * Creates the model map (SAD <-> ScaWaveform).
	 * @return
	 */
	protected GraphitiSADModelMap createModelMapInstance() {
		return new GraphitiSADModelMap(this, waveform);
	}

	/**
	 * Creates an EMF {@link Command} to initialize the model map.
	 * @return
	 */
	protected Command createModelInitializeCommand() {
		SoftwareAssembly sad = getSoftwareAssembly();
		return new GraphitiSADModelMapInitializerCommand(modelMap, sad, waveform);
	}

	////////////////////////////////////////////////////
	// 3. createDiagramInput()
	////////////////////////////////////////////////////

	@Override
	public String getDiagramTypeProviderID() {
		return WaveformExplorerDiagramTypeProvider.PROVIDER_ID;
	}

	@Override
	public String getDiagramContext() {
		return DUtil.DIAGRAM_CONTEXT_EXPLORER;
	}

	@Override
	protected void addDiagramLinks(Diagram diagram) {
		diagram.getLink().getBusinessObjects().add(waveform);
	}

	////////////////////////////////////////////////////
	// Other
	////////////////////////////////////////////////////

	@Override
	public void dispose() {
		if (this.sadlistener != null) {
			SoftwareAssembly sad = getSoftwareAssembly();
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
}
