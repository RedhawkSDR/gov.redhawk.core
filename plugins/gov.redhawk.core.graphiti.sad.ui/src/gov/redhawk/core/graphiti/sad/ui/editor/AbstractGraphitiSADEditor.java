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
 * The multi-page editor for SAD files / runtime waveforms. Includes a Graphiti diagram.
 */
public abstract class AbstractGraphitiSADEditor extends AbstractGraphitiMultiPageEditor {

	public static final String ID = "gov.redhawk.ide.graphiti.sad.ui.editor.presentation.SadEditorID";

	public static final String EDITING_DOMAIN_ID = "mil.jpeojtrs.sca.sad.diagram.EditingDomain";

	public AbstractGraphitiSADEditor() {
		super();
	}

	protected SoftwareAssembly getSoftwareAssembly() {
		return SoftwareAssembly.Util.getSoftwareAssembly(getMainResource());
	}

	@Override
	protected EObject getMainObject() {
		return getSoftwareAssembly();
	}

	protected AbstractGraphitiDiagramEditor createDiagramEditor() {
		return new GraphitiWaveformDiagramEditor((TransactionalEditingDomain) getEditingDomain());
	}

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

	@Override
	public String getEditingDomainId() {
		return AbstractGraphitiSADEditor.EDITING_DOMAIN_ID;
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
	public boolean isPersisted(final Resource resource) {
		return resource.getURI().equals(getSoftwareAssembly().eResource().getURI()) && super.isPersisted(resource);
	}
}
