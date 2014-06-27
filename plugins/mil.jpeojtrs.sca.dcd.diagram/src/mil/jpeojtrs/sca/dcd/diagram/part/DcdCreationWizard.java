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

// BEGIN GENERATED CODE
package mil.jpeojtrs.sca.dcd.diagram.part;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.statushandlers.StatusManager;

/**
 * @generated
 */
public class DcdCreationWizard extends Wizard implements INewWizard {

	/**
	 * @generated
	 */
	private IWorkbench workbench;
	/**
	 * @generated
	 */
	protected IStructuredSelection selection;
	/**
	 * @generated
	 */
	protected DcdCreationWizardPage diagramModelFilePage;
	/**
	 * @generated
	 */
	protected DcdCreationWizardPage domainModelFilePage;
	/**
	 * @generated
	 */
	protected Resource diagram;
	/**
	 * @generated
	 */
	private boolean openNewlyCreatedDiagramEditor = true;

	/**
	 * @generated
	 */
	public IWorkbench getWorkbench() {
		return workbench;
	}

	/**
	 * @generated
	 */
	public IStructuredSelection getSelection() {
		return selection;
	}

	/**
	 * @generated
	 */
	public final Resource getDiagram() {
		return diagram;
	}

	/**
	 * @generated
	 */
	public final boolean isOpenNewlyCreatedDiagramEditor() {
		return openNewlyCreatedDiagramEditor;
	}

	/**
	 * @generated
	 */
	public void setOpenNewlyCreatedDiagramEditor(boolean openNewlyCreatedDiagramEditor) {
		this.openNewlyCreatedDiagramEditor = openNewlyCreatedDiagramEditor;
	}

	/**
	 * @generated
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		this.selection = selection;
		setWindowTitle(Messages.DcdCreationWizardTitle);
		setDefaultPageImageDescriptor(DcdDiagramEditorPlugin.getBundledImageDescriptor("icons/wizban/NewDcdWizard.gif")); //$NON-NLS-1$
		setNeedsProgressMonitor(true);
	}

	/**
	 * @generated
	 */
	public void addPages() {
		diagramModelFilePage = new DcdCreationWizardPage("DiagramModelFile", getSelection(), "dcd_diagramV2"); //$NON-NLS-1$ //$NON-NLS-2$
		diagramModelFilePage.setTitle(Messages.DcdCreationWizard_DiagramModelFilePageTitle);
		diagramModelFilePage.setDescription(Messages.DcdCreationWizard_DiagramModelFilePageDescription);
		addPage(diagramModelFilePage);

		domainModelFilePage = new DcdCreationWizardPage("DomainModelFile", getSelection(), "xml") { //$NON-NLS-1$ //$NON-NLS-2$

			public void setVisible(boolean visible) {
				if (visible) {
					String fileName = diagramModelFilePage.getFileName();
					fileName = fileName.substring(0, fileName.length() - ".dcd_diagramV2".length()); //$NON-NLS-1$
					setFileName(DcdDiagramEditorUtil.getUniqueFileName(getContainerFullPath(), fileName, "xml")); //$NON-NLS-1$
				}
				super.setVisible(visible);
			}
		};
		domainModelFilePage.setTitle(Messages.DcdCreationWizard_DomainModelFilePageTitle);
		domainModelFilePage.setDescription(Messages.DcdCreationWizard_DomainModelFilePageDescription);
		addPage(domainModelFilePage);
	}

	/**
	 * @generated
	 */
	public boolean performFinish() {
		IRunnableWithProgress op = new WorkspaceModifyOperation(null) {

			protected void execute(IProgressMonitor monitor) throws CoreException, InterruptedException {
				diagram = DcdDiagramEditorUtil.createDiagram(diagramModelFilePage.getURI(), domainModelFilePage.getURI(), monitor);
				if (isOpenNewlyCreatedDiagramEditor() && diagram != null) {
					try {
						DcdDiagramEditorUtil.openDiagram(diagram);
					} catch (PartInitException e) {
						PartInitException ce = (PartInitException) e;
						StatusManager.getManager().handle(
							new Status(ce.getStatus().getSeverity(), "mil.jpeojtrs.sca.dcd.diagram", Messages.DcdCreationWizardOpenEditorError, e),
							StatusManager.SHOW | StatusManager.LOG);
					}
				}
			}
		};
		try {
			getContainer().run(false, true, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			if (e.getTargetException() instanceof CoreException) {
				CoreException ce = (CoreException) e.getTargetException();
				StatusManager.getManager().handle(
					new Status(ce.getStatus().getSeverity(), "mil.jpeojtrs.sca.dcd.diagram", Messages.DcdCreationWizardCreationError, e),
					StatusManager.SHOW | StatusManager.LOG);
			} else {
				DcdDiagramEditorPlugin.getInstance().logError("Error creating diagram", e.getTargetException()); //$NON-NLS-1$
			}
			return false;
		}
		return diagram != null;
	}
}
