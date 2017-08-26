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
package gov.redhawk.sca.sad.diagram.part;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.internal.ui.palette.editparts.ToolbarEditPart;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteToolbar;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.ui.palette.PaletteEditPartFactory;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramDropTargetListener;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.EditorInputProxy;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocumentProvider;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;

/**
 * 
 */
public class SadDiagramEditor extends mil.jpeojtrs.sca.sad.diagram.part.SadDiagramEditor {

	private static final List<String> FILTER_TOOL_IDS = Arrays.asList(new String[] {
	        "createComponentInstantiation1CreationTool", "createComponentPlacement2CreationTool", "createPartitioning5CreationTool"
	});

	public static final String EDITING_DOMAIN_ID = "mil.jpeojtrs.sca.sad.diagram.EditingDomain";

	public SadDiagramEditor(final boolean hasflyoutPalette) {
		super(false);
	}

	public SadDiagramEditor() {
		super(false);
	}

	
	
	@Override
	protected IDocumentProvider getDocumentProvider(final IEditorInput input) {
		if (input instanceof IFileEditorInput || input instanceof URIEditorInput || input instanceof EditorInputProxy) {
			return SadDocumentProvider.getInstance();
		}
		return super.getDocumentProvider(input);
	}

	@Override
	public TransactionalEditingDomain getEditingDomain() {
		final IDocumentProvider provider = getDocumentProvider();
		final IEditorInput input = getEditorInput();
		if (provider == null || input == null) {
			// editor has been programmatically closed while the dialog was open
			return null;
		}
		final IDocument document = provider.getDocument(input);
		if (document instanceof IDiagramDocument) {
			return ((IDiagramDocument) document).getEditingDomain();
		}
		return super.getEditingDomain();
	}

	@Override
	protected void setDocumentProvider(final IEditorInput input) {
		if (input instanceof IFileEditorInput || input instanceof URIEditorInput || input instanceof EditorInputProxy) {
			setDocumentProvider(SadDocumentProvider.getInstance());
		} else {
			super.setDocumentProvider(input);
		}
	}

	@Override
	protected void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();
		getDiagramGraphicalViewer().addDropTargetListener(new DropTargetListener(getDiagramGraphicalViewer(), LocalSelectionTransfer.getTransfer()) {

			@Override
			protected Object getJavaObject(final TransferData data) {
				return LocalSelectionTransfer.getTransfer().getSelection();
				//				return LocalSelectionTransfer.getTransfer().nativeToJava(data);
			}
		});
		getDiagramGraphicalViewer().addDropTargetListener(new DropTargetListener(getDiagramGraphicalViewer(), LocalTransfer.getInstance()) {

			@Override
			protected Object getJavaObject(final TransferData data) {
				return LocalTransfer.getInstance().nativeToJava(data);
			}

		});
	}

	private abstract class DropTargetListener extends DiagramDropTargetListener {

		public DropTargetListener(final EditPartViewer viewer, final Transfer xfer) {
			super(viewer, xfer);
		}

		@Override
		protected List< ? extends EObject> getObjectsBeingDropped() {
			final TransferData data = getCurrentEvent().currentDataType;
			final Collection<URI> uris = new HashSet<URI>();

			final Object transferedObject = getJavaObject(data);
			if (transferedObject instanceof IStructuredSelection) {
				final IStructuredSelection selection = (IStructuredSelection) transferedObject;
				for (final Iterator< ? > it = selection.iterator(); it.hasNext();) {
					Object nextSelectedObject = it.next();
					if (nextSelectedObject instanceof IAdaptable) {
						final IAdaptable adaptable = (IAdaptable) nextSelectedObject;
						nextSelectedObject = adaptable.getAdapter(EObject.class);
					}

					if (nextSelectedObject instanceof EObject) {
						final EObject modelElement = (EObject) nextSelectedObject;
						final Resource modelElementResource = modelElement.eResource();
						uris.add(modelElementResource.getURI().appendFragment(modelElementResource.getURIFragment(modelElement)));
					}
				}
			}

			final List<EObject> result = new ArrayList<EObject>();
			for (final Iterator<URI> it = uris.iterator(); it.hasNext();) {
				final URI nextURI = it.next();
				final EObject modelObject = getEditingDomain().getResourceSet().getEObject(nextURI, true);
				result.add(modelObject);
			}
			return result;
		}

		protected abstract Object getJavaObject(TransferData data);

	}

	@Override
	protected PaletteRoot createPaletteRoot(final PaletteRoot existingPaletteRoot) {
		final PaletteRoot root = super.createPaletteRoot(existingPaletteRoot);
		filterOutTools(root);
		return root;
	}
	
	/*
	 * @see org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditorWithFlyOutPalette#constructPaletteViewer()
	 */
	@Override
	protected PaletteViewer constructPaletteViewer() {
		PaletteViewer retVal = super.constructPaletteViewer();
		setPaletteViewerFactory(retVal);
		return retVal;
	}
	
	
	private void setPaletteViewerFactory(final PaletteViewer paletteViewer) {
		paletteViewer.setEditPartFactory(new PaletteEditPartFactory() {
			
			/*
			 * @see org.eclipse.gef.ui.palette.PaletteEditPartFactory#createToolbarEditPart(org.eclipse.gef.EditPart, java.lang.Object)
			 */
			@SuppressWarnings("unchecked")
			@Override
			protected EditPart createToolbarEditPart(EditPart parentEditPart, final Object model) {
				ToolbarEditPart retVal = new ToolbarEditPart((PaletteToolbar) model) {
					
					/*
					 * @see org.eclipse.gef.editparts.AbstractEditPart#createChild(java.lang.Object)
					 */
					@Override
					protected EditPart createChild(Object model) {
						return super.createChild(model);
					}
					
					/*
					 * @see org.eclipse.gef.ui.palette.editparts.PaletteEditPart#getModelChildren()
					 */
					@Override
					public List<Object> getModelChildren() {
						List<Object> retVal =  super.getModelChildren();
						retVal.add("Text");
						return retVal;
					}
				};
				
				return retVal;
			}
		});
	}

	private void filterOutTools(final PaletteEntry entry) {
		if (entry instanceof PaletteContainer) {
			for (final Object obj : ((PaletteContainer) entry).getChildren()) {
				if (obj instanceof PaletteEntry) {
					filterOutTools((PaletteEntry) obj);
				}
			}
		}
		if (entry instanceof ToolEntry) {
			final ToolEntry tool = (ToolEntry) entry;
			if (SadDiagramEditor.FILTER_TOOL_IDS.contains(tool.getId())) {
				tool.setVisible(false);
			}
		}

	}
}
