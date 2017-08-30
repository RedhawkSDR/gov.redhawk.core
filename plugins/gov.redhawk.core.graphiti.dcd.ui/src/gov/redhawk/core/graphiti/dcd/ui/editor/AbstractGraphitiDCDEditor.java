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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.mm.pictograms.PictogramsFactory;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;
import org.eclipse.ui.IEditorInput;

import gov.redhawk.core.graphiti.dcd.ui.diagram.providers.DCDDiagramTypeProvider;
import gov.redhawk.core.graphiti.ui.editor.AbstractGraphitiMultiPageEditor;
import gov.redhawk.core.graphiti.ui.util.DUtil;
import gov.redhawk.model.sca.commands.NonDirtyingCommand;
import mil.jpeojtrs.sca.dcd.DeviceConfiguration;
import mil.jpeojtrs.sca.dcd.provider.DcdItemProviderAdapterFactory;
import mil.jpeojtrs.sca.prf.provider.PrfItemProviderAdapterFactory;
import mil.jpeojtrs.sca.scd.provider.ScdItemProviderAdapterFactory;
import mil.jpeojtrs.sca.spd.provider.SpdItemProviderAdapterFactory;

/**
 * The multi-page editor for DCD files / runtime nodes. Includes a Graphiti diagram.
 */
public abstract class AbstractGraphitiDCDEditor extends AbstractGraphitiMultiPageEditor {

	public static final String ID = "gov.redhawk.ide.graphiti.dcd.ui.editor.DcdEditor";

	public static final String EDITING_DOMAIN_ID = "gov.redhawk.core.graphiti.dcd.ui.EditingDomain";

	public AbstractGraphitiDCDEditor() {
		super();
	}

	protected DeviceConfiguration getDeviceConfiguration() {
		return DeviceConfiguration.Util.getDeviceConfiguration(getMainResource());
	}

	@Override
	protected EObject getMainObject() {
		return getDeviceConfiguration();
	}

	@Override
	protected IEditorInput createDiagramInput() throws IOException, CoreException {
		// Create the diagram resource which will hold information only relevant to the Graphiti diagram
		final URI diagramURI = DUtil.getDiagramResourceURI(DcdDiagramUtilHelper.INSTANCE, getMainResource());
		DUtil.initializeDiagramResource(DcdDiagramUtilHelper.INSTANCE, DCDDiagramTypeProvider.DIAGRAM_TYPE_ID, getDiagramTypeProviderID(), diagramURI);
		Resource diagramResource = getEditingDomain().getResourceSet().getResource(diagramURI, true);
		final Diagram diagram = (Diagram) diagramResource.getContents().get(0);

		// Link the diagram with the DCD
		NonDirtyingCommand.execute(diagram, new NonDirtyingCommand() {
			@Override
			public String getLabel() {
				return "Link diagram with DCD file";
			}

			@Override
			public void execute() {
				// set property specifying diagram context (design, local, domain)
				Graphiti.getPeService().setPropertyValue(diagram, DUtil.DIAGRAM_CONTEXT, getDiagramContext());

				// link diagram and DCD
				PictogramLink link = PictogramsFactory.eINSTANCE.createPictogramLink();
				link.getBusinessObjects().add(getDeviceConfiguration());
				diagram.setLink(link);

				// Potentially link other objects in child classes
				addDiagramLinks(diagram);
			}
		});

		// return editor input from diagram with sad diagram type
		return DiagramEditorInput.createEditorInput(diagram, getDiagramTypeProviderID());
	}

	/**
	 * Subclasses may override to add additional business objects to the diagram (e.g., runtime waveform).
	 * @param diagram
	 */
	protected void addDiagramLinks(Diagram diagram) {
	}

	@Override
	public String getEditingDomainId() {
		return AbstractGraphitiDCDEditor.EDITING_DOMAIN_ID;
	}

	@Override
	protected AdapterFactory getSpecificAdapterFactory() {
		final ComposedAdapterFactory factory = new ComposedAdapterFactory();
		// TODO: How to handle this?
//		final DcdItemProviderAdapterFactoryAdapter dcdAdapter = new DcdItemProviderAdapterFactoryAdapter();
//		dcdAdapter.setComponentPlacementAdapter(new DevicesSectionComponentPlacementItemProvider(dcdAdapter));
//		factory.addAdapterFactory(dcdAdapter);
		factory.addAdapterFactory(new DcdItemProviderAdapterFactory());
		factory.addAdapterFactory(new EcoreItemProviderAdapterFactory());
		factory.addAdapterFactory(new SpdItemProviderAdapterFactory());
		factory.addAdapterFactory(new ScdItemProviderAdapterFactory());
		factory.addAdapterFactory(new PrfItemProviderAdapterFactory());
		return factory;
	}

	@Override
	public boolean isPersisted(final Resource resource) {
		if (resource == null || resource.getURI() == null) {
			return false;
		}
		if (getDeviceConfiguration() == null || getDeviceConfiguration().eResource() == null) {
			return false;
		}
		return resource.getURI().equals(getDeviceConfiguration().eResource().getURI()) && super.isPersisted(resource);
	}
}
