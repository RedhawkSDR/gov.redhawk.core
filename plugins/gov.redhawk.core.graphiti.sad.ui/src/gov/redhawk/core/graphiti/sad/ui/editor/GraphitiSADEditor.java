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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.mm.pictograms.PictogramsFactory;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.statushandlers.StatusManager;

import CF.Application;
import gov.redhawk.core.graphiti.sad.ui.GraphitiSadUIPlugin;
import gov.redhawk.core.graphiti.sad.ui.diagram.providers.SADDiagramTypeProvider;
import gov.redhawk.core.graphiti.ui.editor.AbstractGraphitiMultiPageEditor;
import gov.redhawk.ide.sad.internal.ui.editor.SadOverviewPage;
import gov.redhawk.ide.sad.internal.ui.editor.SadPropertiesPage;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.commands.NonDirtyingCommand;
import gov.redhawk.model.sca.util.ModelUtil;
import gov.redhawk.sca.util.PluginUtil;
import mil.jpeojtrs.sca.prf.provider.PrfItemProviderAdapterFactory;
import mil.jpeojtrs.sca.sad.SadPackage;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import mil.jpeojtrs.sca.sad.provider.SadItemProviderAdapterFactory;
import mil.jpeojtrs.sca.scd.provider.ScdItemProviderAdapterFactory;
import mil.jpeojtrs.sca.spd.provider.SpdItemProviderAdapterFactory;

/**
 * The multi-page editor for SAD files. Includes a Graphiti diagram.
 */
public class GraphitiSADEditor extends AbstractGraphitiMultiPageEditor {

	public static final String ID = "gov.redhawk.ide.graphiti.sad.ui.editor.presentation.SadEditorID";

	public static final String EDITING_DOMAIN_ID = "mil.jpeojtrs.sca.sad.diagram.EditingDomain";

	private ResourceListener nameListener;
	private IFormPage propertiesPage;

	private class ResourceListener extends AdapterImpl {
		private SoftwareAssembly sad;
		private final Resource sadResource;

		public ResourceListener(final Resource spdResource) {
			this.sadResource = spdResource;
			if (this.sadResource != null) {
				this.sadResource.eAdapters().add(this);
				this.sad = SoftwareAssembly.Util.getSoftwareAssembly(this.sadResource);
				if (this.sad != null) {
					this.sad.eAdapters().add(this);
					updateTitle();
				}
			}
		}

		public void dispose() {
			if (this.sad != null) {
				this.sad.eAdapters().remove(this);
			}
			if (this.sadResource != null) {
				this.sadResource.eAdapters().remove(this);
			}
		}

		@Override
		public void notifyChanged(final Notification msg) {
			if (msg.getNotifier() instanceof Resource) {
				switch (msg.getFeatureID(Resource.class)) {
				case Resource.RESOURCE__IS_LOADED:
					if (this.sad != null) {
						this.sad.eAdapters().remove(this);
						this.sad = null;
					}
					if (this.sadResource.isLoaded()) {
						this.sad = SoftwareAssembly.Util.getSoftwareAssembly(this.sadResource);
						if (this.sad != null) {
							this.sad.eAdapters().add(this);
							updateTitle();
						}
					}
					break;
				default:
					break;
				}
			} else if (msg.getNotifier() instanceof SoftwareAssembly) {
				final int featureID = msg.getFeatureID(SoftwareAssembly.class);
				if (featureID == SadPackage.SOFTWARE_ASSEMBLY__NAME) {
					if (msg.getEventType() == Notification.SET) {
						updateTitle();
					}
				}
			}
		}
	}

	public GraphitiSADEditor() {
		super();
	}

	@Override
	public <T> T getAdapter(final Class<T> key) {
		if (key.equals(ScaWaveform.class) || key.isAssignableFrom(Application.class)) {
			return key.cast(PluginUtil.adapt(ScaWaveform.class, getSoftwareAssembly()));
		}
		return super.getAdapter(key);
	}

	@Override
	public void dispose() {
		if (this.nameListener != null) {
			this.nameListener.dispose();
			this.nameListener = null;
		}
		super.dispose();
	}

	@Override
	public void updateTitle() {
		String name = null;
		final SoftwareAssembly sad = getSoftwareAssembly();
		if (sad != null) {
			name = sad.getName();
			if (name == null) {
				name = getEditorInput().getName();
			}
		}
		if (name == null) {
			name = "";
		}
		setPartName(name);
	}

	private SoftwareAssembly getSoftwareAssembly() {
		return SoftwareAssembly.Util.getSoftwareAssembly(getMainResource());
	}

	@Override
	protected EObject getMainObject() {
		return getSoftwareAssembly();
	}

	@Override
	protected void addPages() {
		// Only creates the other pages if there is something that can be edited

		if (!getEditingDomain().getResourceSet().getResources().isEmpty()
			&& !(getEditingDomain().getResourceSet().getResources().get(0)).getContents().isEmpty()) {
			try {
				int pageIndex = 0;

				final Resource sadResource = getMainResource();

				addNameListener(sadResource);

				final IFormPage page = createOverviewPage(sadResource);
				setOverviewPage(page);
				this.addPage(page);

				propertiesPage = createPropertiesPage(sadResource);
				addPage(propertiesPage);

				final DiagramEditor editor = createDiagramEditor();
				setDiagramEditor(editor);
				final IEditorInput diagramInput = createDiagramInput(sadResource);
				pageIndex = addPage(editor, diagramInput);
				setPageText(pageIndex, "Diagram");

				// set layout for target-sdr editors
				DUtil.layout(editor);

				IEditorPart textEditor = createTextEditor(getEditorInput());
				setTextEditor(textEditor);
				if (textEditor != null) {
					final int sadSourcePageNum = addPage(-1, textEditor, getEditorInput(), sadResource);
					this.setPageText(sadSourcePageNum, getEditorInput().getName());
				}

			} catch (final PartInitException e) {
				StatusManager.getManager().handle(new Status(IStatus.ERROR, GraphitiSadUIPlugin.PLUGIN_ID, "Failed to create editor parts.", e),
					StatusManager.LOG | StatusManager.SHOW);
			} catch (final IOException e) {
				StatusManager.getManager().handle(new Status(IStatus.ERROR, GraphitiSadUIPlugin.PLUGIN_ID, "Failed to create editor parts.", e),
					StatusManager.LOG | StatusManager.SHOW);
			} catch (final CoreException e) {
				StatusManager.getManager().handle(new Status(IStatus.ERROR, GraphitiSadUIPlugin.PLUGIN_ID, "Failed to create editor parts.", e),
					StatusManager.LOG | StatusManager.SHOW);
			}
		}
	}

	protected IFormPage createPropertiesPage(Resource sadResource) {
		SadPropertiesPage page = new SadPropertiesPage(this, "propertiesPage", "Properties", true);
		page.setInput(sadResource);
		return page;
	}

	private void addNameListener(final Resource sadResource) {
		this.nameListener = new ResourceListener(sadResource);
	}

	/**
	 * Given the sad resource (sad.xml) create a Diagram resource, associate it with the sad resource and return
	 * diagram editor input
	 * @param sadResource
	 * @return
	 * @throws IOException
	 * @throws CoreException
	 */
	protected IEditorInput createDiagramInput(final Resource sadResource) throws IOException, CoreException {
		final URI diagramURI = DUtil.getDiagramResourceURI(SadDiagramUtilHelper.INSTANCE, sadResource);

		DUtil.initializeDiagramResource(SadDiagramUtilHelper.INSTANCE, SADDiagramTypeProvider.DIAGRAM_TYPE_ID,
			SADDiagramTypeProvider.PROVIDER_ID, diagramURI, sadResource);

		Resource diagramResource = getEditingDomain().getResourceSet().getResource(diagramURI, true);

		// load diagram from resource
		final Diagram diagram = (Diagram) diagramResource.getContents().get(0);

		// load sad from resource
		final SoftwareAssembly sad = SoftwareAssembly.Util.getSoftwareAssembly(sadResource);

		// link diagram with SoftwareAssembly
		NonDirtyingCommand.execute(diagram, new NonDirtyingCommand() {
			@Override
			public String getLabel() {
				return "Link diagram with SAD file";
			}

			@Override
			public void execute() {
				// set property specifying diagram context (design, local, domain)
				Graphiti.getPeService().setPropertyValue(diagram, DUtil.DIAGRAM_CONTEXT, getDiagramContext(sadResource));

				// link diagram and sad
				PictogramLink link = PictogramsFactory.eINSTANCE.createPictogramLink();
				link.getBusinessObjects().add(sad);
				diagram.setLink(link);
				addDiagramLinks(diagram);
			}
		});

		// return editor input from diagram with sad diagram type
		return DiagramEditorInput.createEditorInput(diagram, SADDiagramTypeProvider.PROVIDER_ID);

	}

	/**
	 * Subclasses may override to add additional business object to the diagram (e.g., runtime waveform).
	 * @param diagram
	 */
	protected void addDiagramLinks(Diagram diagram) {
	}

	protected DiagramEditor createDiagramEditor() {
		GraphitiWaveformDiagramEditor d = new GraphitiWaveformDiagramEditor((TransactionalEditingDomain) getEditingDomain());
		return d;
	}


	protected IFormPage createOverviewPage(final Resource sadResource) {
		final SadOverviewPage page = new SadOverviewPage(this);
		page.setInput(sadResource);
		return page;
	}

	@Override
	public String getEditingDomainId() {
		return GraphitiSADEditor.EDITING_DOMAIN_ID;
	}

	@Override
	protected AdapterFactory getSpecificAdapterFactory() {
		final ComposedAdapterFactory factory = new ComposedAdapterFactory();
		factory.addAdapterFactory(new SadItemProviderAdapterFactory());
		factory.addAdapterFactory(new EcoreItemProviderAdapterFactory());
		factory.addAdapterFactory(new SpdItemProviderAdapterFactory());
		factory.addAdapterFactory(new ScdItemProviderAdapterFactory());
		factory.addAdapterFactory(new PrfItemProviderAdapterFactory());
		return factory;
	}

	@Override
	public List<Object> getOutlineItems() {
		final List<Object> myList = new ArrayList<Object>();
		if (getOverviewPage() != null) {
			myList.add(getOverviewPage());
		}
		if (getSoftwareAssembly().getPartitioning() != null) {
			myList.add(getSoftwareAssembly().getPartitioning());
		}
		return myList;
	}

	@Override
	public boolean isPersisted(final Resource resource) {
		return resource.getURI().equals(getSoftwareAssembly().eResource().getURI()) && super.isPersisted(resource);
	}
}
