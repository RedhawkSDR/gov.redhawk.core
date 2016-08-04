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

import gov.redhawk.core.graphiti.dcd.ui.GraphitiDcdUIPlugin;
import gov.redhawk.core.graphiti.ui.editor.AbstractGraphitiMultiPageEditor;
import gov.redhawk.ide.dcd.internal.ui.editor.DevicesPage;
import gov.redhawk.ide.dcd.internal.ui.editor.NodeOverviewPage;
import gov.redhawk.model.sca.commands.NonDirtyingCommand;
import gov.redhawk.model.sca.util.ModelUtil;
import mil.jpeojtrs.sca.dcd.DcdPackage;
import mil.jpeojtrs.sca.dcd.DeviceConfiguration;
import mil.jpeojtrs.sca.dcd.provider.DcdItemProviderAdapterFactory;
import mil.jpeojtrs.sca.prf.provider.PrfItemProviderAdapterFactory;
import mil.jpeojtrs.sca.scd.provider.ScdItemProviderAdapterFactory;
import mil.jpeojtrs.sca.spd.provider.SpdItemProviderAdapterFactory;

/**
 * The multi-page editor for DCD files. Includes a Graphiti diagram.
 */
public class GraphitiDcdEditor extends AbstractGraphitiMultiPageEditor {

	public static final String ID = "gov.redhawk.ide.graphiti.dcd.ui.editor.DcdEditor";

	public static final String EDITING_DOMAIN_ID = "mil.jpeojtrs.sca.dcd.diagram.EditingDomain";

	private ResourceListener nameListener;

	private class ResourceListener extends AdapterImpl {
		private DeviceConfiguration dcd;
		private final Resource dcdResource;

		public ResourceListener(final Resource dcdResource) {
			this.dcdResource = dcdResource;
			if (this.dcdResource != null) {
				this.dcdResource.eAdapters().add(this);
				this.dcd = DeviceConfiguration.Util.getDeviceConfiguration(this.dcdResource);
				if (this.dcd != null) {
					this.dcd.eAdapters().add(this);
					updateTitle();
				}
			}
		}

		public void dispose() {
			if (this.dcd != null) {
				this.dcd.eAdapters().remove(this);
			}
			if (this.dcdResource != null) {
				this.dcdResource.eAdapters().remove(this);
			}
		}

		@Override
		public void notifyChanged(final Notification msg) {
			if (msg.getNotifier() instanceof Resource) {
				switch (msg.getFeatureID(Resource.class)) {
				case Resource.RESOURCE__IS_LOADED:
					if (this.dcd != null) {
						this.dcd.eAdapters().remove(this);
						this.dcd = null;
					}
					if (this.dcdResource.isLoaded()) {
						this.dcd = DeviceConfiguration.Util.getDeviceConfiguration(this.dcdResource);
						if (this.dcd != null) {
							this.dcd.eAdapters().add(this);
							updateTitle();
						}
					}
					break;
				default:
					break;
				}
			} else if (msg.getNotifier() instanceof DeviceConfiguration) {
				final int featureID = msg.getFeatureID(DeviceConfiguration.class);
				if (featureID == DcdPackage.DEVICE_CONFIGURATION__NAME) {
					if (msg.getEventType() == Notification.SET) {
						updateTitle();
					}
				}
			}
		}
	}

	public GraphitiDcdEditor() {
		super();
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
		final DeviceConfiguration dcd = getDeviceConfiguration();
		if (dcd != null) {
			name = dcd.getName();
			if (name == null) {
				name = getEditorInput().getName();
			}
		}
		if (name == null) {
			name = "";
		}
		setPartName(name);
	}

	private DeviceConfiguration getDeviceConfiguration() {
		return DeviceConfiguration.Util.getDeviceConfiguration(getMainResource());
	}

	@Override
	protected EObject getMainObject() {
		return getDeviceConfiguration();
	}

	@Override
	protected void addPages() {
		// Only creates the other pages if there is something that can be edited
		//
		if (!getEditingDomain().getResourceSet().getResources().isEmpty()
			&& !(getEditingDomain().getResourceSet().getResources().get(0)).getContents().isEmpty()) {
			try {
				int pageIndex = 0;

				final Resource dcdResource = getMainResource();

				addNameListener(dcdResource);

				final IFormPage overviewPage = createOverviewPage(dcdResource);
				setOverviewPage(overviewPage);
				this.addPage(overviewPage);

				final IFormPage devicesPage = createDcdDevicesPage(dcdResource);
				addPage(devicesPage);

				// This is the page for the graphical diagram viewer
				final DiagramEditor editor = createDiagramEditor();
				setDiagramEditor(editor);
				final IEditorInput diagramInput = createDiagramInput(dcdResource);
				pageIndex = addPage(editor, diagramInput);
				setPageText(pageIndex, "Diagram");

				// TODO: Do we need to set layout like in SAD Diagram?
				// set layout for target-sdr editors
				// DUtil.layout(editor);

				IEditorPart textEditor = createTextEditor(getEditorInput());
				setTextEditor(textEditor);
				if (textEditor != null) {
					final int dcdSourcePageNum = addPage(-1, textEditor, getEditorInput(), dcdResource);
					this.setPageText(dcdSourcePageNum, getEditorInput().getName());
				}

			} catch (final PartInitException e) {
				StatusManager.getManager().handle(new Status(IStatus.ERROR, GraphitiDcdUIPlugin.PLUGIN_ID, "Failed to add pages.", e));
			} catch (final IOException e) {
				StatusManager.getManager().handle(new Status(IStatus.ERROR, GraphitiDcdUIPlugin.PLUGIN_ID, "Failed to add pages.", e));
			} catch (final CoreException e) {
				StatusManager.getManager().handle(new Status(IStatus.ERROR, GraphitiDcdUIPlugin.PLUGIN_ID, "Failed to add pages.", e));
			}
		}
	}

	private void addNameListener(final Resource dcdResource) {
		this.nameListener = new ResourceListener(dcdResource);
	}

	protected IEditorInput createDiagramInput(final Resource dcdResource) throws IOException, CoreException {
		final URI diagramURI = DUtil.getDiagramResourceURI(DcdDiagramUtilHelper.INSTANCE, dcdResource);

		DUtil.initializeDiagramResource(DcdDiagramUtilHelper.INSTANCE, DCDDiagramTypeProvider.DIAGRAM_TYPE_ID, DCDDiagramTypeProvider.PROVIDER_ID, diagramURI,
			dcdResource);

		Resource diagramResource = getEditingDomain().getResourceSet().getResource(diagramURI, true);

		// load diagram from resource
		final Diagram diagram = (Diagram) diagramResource.getContents().get(0);

		// load dcd from resource
		final DeviceConfiguration dcd = DeviceConfiguration.Util.getDeviceConfiguration(dcdResource);

		// link diagram with DeviceConfiguration
		NonDirtyingCommand.execute(diagram, new NonDirtyingCommand() {
			@Override
			public String getLabel() {
				return "Link diagram with DCD file";
			}

			@Override
			public void execute() {
				// set property specifying diagram context (design, local, domain)
				Graphiti.getPeService().setPropertyValue(diagram, DUtil.DIAGRAM_CONTEXT, getDiagramContext(dcdResource));
				
				// link diagram and dcd
				PictogramLink link = PictogramsFactory.eINSTANCE.createPictogramLink();
				link.getBusinessObjects().add(dcd);
				diagram.setLink(link);
				addDiagramLinks(diagram);
			}
		});

		// return editor input from diagram with sad diagram type
		return DiagramEditorInput.createEditorInput(diagram, DCDDiagramTypeProvider.PROVIDER_ID);
	}

	/**
	 * Subclasses may override to add additional business object to the diagram (e.g., runtime waveform).
	 * @param diagram
	 */
	protected void addDiagramLinks(Diagram diagram) {
	}

	protected DiagramEditor createDiagramEditor() {
		GraphitiDcdDiagramEditor d = new GraphitiDcdDiagramEditor((TransactionalEditingDomain) getEditingDomain());
		return d;
	}

	protected IFormPage createOverviewPage(final Resource dcdResource) {
		final NodeOverviewPage retVal = new NodeOverviewPage(this);
		retVal.setInput(dcdResource);
		return retVal;
	}

	protected IFormPage createDcdDevicesPage(final Resource dcdResource) {
		final DevicesPage retVal = new DevicesPage(this);
		retVal.setInput(dcdResource);
		return retVal;
	}

	@Override
	public String getEditingDomainId() {
		return GraphitiDcdEditor.EDITING_DOMAIN_ID;
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
	public List<Object> getOutlineItems() {
		final List<Object> myList = new ArrayList<Object>();
		myList.add(getOverviewPage());
		myList.add(getDeviceConfiguration().getPartitioning());
		return myList;
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
