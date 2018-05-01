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
package gov.redhawk.core.graphiti.ui.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.URIHandlerImpl;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.datatypes.ILocation;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.features.context.impl.LayoutContext;
import org.eclipse.graphiti.features.context.impl.RemoveContext;
import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.mm.PropertyContainer;
import org.eclipse.graphiti.mm.algorithms.AbstractText;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.algorithms.styles.StylesFactory;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import gov.redhawk.core.graphiti.ui.GraphitiUIPlugin;
import gov.redhawk.core.graphiti.ui.editor.IDiagramUtilHelper;
import gov.redhawk.core.graphiti.ui.ext.RHContainerShape;
import gov.redhawk.core.graphiti.ui.internal.diagram.wizards.SuperPortConnectionWizard;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import mil.jpeojtrs.sca.dcd.DeviceConfiguration;
import mil.jpeojtrs.sca.partitioning.ConnectInterface;
import mil.jpeojtrs.sca.partitioning.ConnectionTarget;
import mil.jpeojtrs.sca.partitioning.UsesPortStub;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import mil.jpeojtrs.sca.util.CorbaUtils2;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;
import mil.jpeojtrs.sca.util.ScaFileSystemConstants;

public class DUtil {

	// Property keys
	public static final String DIAGRAM_CONTEXT = "DiagramContext";
	public static final String GA_TYPE = "GAType";
	public static final String SHAPE_TYPE = "ShapeType";

	// Diagram context property values
	public static final String DIAGRAM_CONTEXT_DESIGN = "design";
	public static final String DIAGRAM_CONTEXT_EXPLORER = "explorer";
	public static final String DIAGRAM_CONTEXT_LOCAL = "local";
	public static final String DIAGRAM_CONTEXT_TARGET_SDR = "target-sdr";

	protected DUtil() {
	}

	/**
	 * Add PictogramElement Connection via feature for the provided object and anchors.
	 * Relies on the framework determining which feature should be used and whether it can be added to diagram
	 * @param featureProvider
	 * @param object
	 * @param sourceAnchor
	 * @param targetAnchor
	 * @return
	 */
	public static PictogramElement addConnectionViaFeature(IFeatureProvider featureProvider, Object object, Anchor sourceAnchor, Anchor targetAnchor) {
		AddConnectionContext addConnectionContext = new AddConnectionContext(sourceAnchor, targetAnchor);
		addConnectionContext.setNewObject(object);
		IAddFeature addFeature = featureProvider.getAddFeature(addConnectionContext);
		if (addFeature.canAdd(addConnectionContext)) {
			return addFeature.add(addConnectionContext);
		}
		return null;
	}

	/**
	 * Finds an appropriate Graphiti add feature for the given object. If found, the features is used to create a
	 * {@link PictogramElement}.
	 * @param featureProvider
	 * @param targetContainer
	 * @param object
	 * @return
	 */
	public static PictogramElement addShapeViaFeature(IFeatureProvider featureProvider, ContainerShape targetContainer, Object object) {
		AddContext addContext = new AddContext();
		addContext.setNewObject(object);
		addContext.setTargetContainer(targetContainer);
		addContext.setX(0);
		addContext.setY(0);
		IAddFeature addFeature = featureProvider.getAddFeature(addContext);
		if (addFeature.canAdd(addContext)) {
			return addFeature.add(addContext);
		}
		return null;
	}

	/**
	 * Add source and target to a {@link ConnectInterface}. One anchor must be a {@link UsesPortStub}, and the
	 * other a {@link ConnectionTarget}. Handles super ports and will pop a dialog if necessary for the user to choose
	 * amongst multiple possible choices.
	 * @param anchor1
	 * @param anchor2
	 * @return True if source and target were located and assigned, false otherwise
	 */
	public static boolean assignAnchorObjectsToConnection(ConnectInterface< ? , ? , ? > connectInterface, Anchor anchor1, Anchor anchor2) {
		if (anchor1 == null || anchor2 == null) {
			return false;
		}

		// get business objects for both anchors
		EList<EObject> anchorObjects1 = anchor1.getParent().getLink().getBusinessObjects();
		EList<EObject> anchorObjects2 = anchor2.getParent().getLink().getBusinessObjects();

		UsesPortStub source = null;
		ConnectionTarget target = null;

		if (anchorObjects1.size() == 0 || anchorObjects2.size() == 0) {
			return false;
		}

		// Check to ensure the first anchor is a UsesPortStub and the second is a ConnectionTarget. Swap if necessary.
		boolean sourceIsFirst = true;
		boolean sourceIsSecond = true;
		boolean targetIsFirst = true;
		boolean targetIsSecond = true;
		for (EObject sourceObj : anchorObjects1) {
			if (!(sourceObj instanceof UsesPortStub)) {
				sourceIsFirst = false;
			}
			if (!(sourceObj instanceof ConnectionTarget)) {
				targetIsFirst = false;
			}
		}
		for (EObject targetObj : anchorObjects2) {
			if (!(targetObj instanceof UsesPortStub)) {
				sourceIsSecond = false;
			}
			if (!(targetObj instanceof ConnectionTarget)) {
				targetIsSecond = false;
			}
		}
		if (targetIsFirst && sourceIsSecond) {
			// Correct objects are present, but they're reversed; swap them
			EList<EObject> swap = anchorObjects1;
			anchorObjects1 = anchorObjects2;
			anchorObjects2 = swap;
		} else if (!(sourceIsFirst && targetIsSecond)) {
			// The only other acceptable case is source first, target second, and that isn't true
			return false;
		}

		Set<UsesPortStub> possibleSources = new HashSet<UsesPortStub>();
		Set<ConnectionTarget> possibleTargets = new HashSet<ConnectionTarget>();

		if (anchorObjects1.size() == 1 && anchorObjects2.size() == 1) {
			// Always attempt to honor direct connections
			possibleSources.add((UsesPortStub) anchorObjects1.get(0));
			possibleTargets.add((ConnectionTarget) anchorObjects2.get(0));
		} else {
			// If either side is a super port, then build a list of possible connections
			for (EObject sourceObj : anchorObjects1) {
				for (EObject targetObj : anchorObjects2) {
					if (InterfacesUtil.areSuggestedMatch((UsesPortStub) sourceObj, targetObj)) {
						possibleSources.add((UsesPortStub) sourceObj);
						possibleTargets.add((ConnectionTarget) targetObj);
					}
				}
			}
		}

		if (possibleSources.size() > 1 || possibleTargets.size() > 1) {
			// If more than one connection is possible, display a wizard to complete the action
			SuperPortConnectionWizard wizard = new SuperPortConnectionWizard(possibleSources, possibleTargets);
			Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
			WizardDialog dialog = new WizardDialog(shell, wizard);
			int retVal = dialog.open();

			if (retVal == Window.OK) {
				// Get user selections
				source = wizard.getPage().getSource();
				target = wizard.getPage().getTarget();
			} else {
				return false;
			}
		} else if (!possibleSources.isEmpty() && !possibleTargets.isEmpty()) {
			// If only one connection is possible, just go ahead and do it
			source = (UsesPortStub) possibleSources.iterator().next();
			target = (ConnectionTarget) possibleTargets.iterator().next();
		}

		if (source == null || target == null) {
			return false;
		}

		connectInterface.setSource(source);
		connectInterface.setTarget(target);
		return true;
	}

	/**
	 * Calculates the width and height of the given text in the font of the given text. Unlike Graphiti's layout
	 * service, this method takes the text's style into account when getting the font.
	 * @param text the {@link AbstractText} to calculate the rendering size for
	 * @return
	 */
	public static IDimension calculateTextSize(AbstractText text) {
		return GraphitiUi.getUiLayoutService().calculateTextSize(text.getValue(), Graphiti.getGaService().getFont(text, true));
	}

	/**
	 * Creates a {@link FixPointAnchor} overlay for a shape, with the anchor point vertically centered at horizontal
	 * position x. The returned anchor has no graphics algorithm.
	 * @param parentShape shape on which to overlay anchor
	 * @param x horizontal anchor point
	 * @return new anchor
	 */
	public static FixPointAnchor createOverlayAnchor(Shape parentShape, int x) {
		FixPointAnchor fixPointAnchor = Graphiti.getCreateService().createFixPointAnchor(parentShape);
		IDimension parentSize = Graphiti.getGaLayoutService().calculateSize(parentShape.getGraphicsAlgorithm());
		Point point = StylesFactory.eINSTANCE.createPoint();
		point.setX(x);
		point.setY(parentSize.getHeight() / 2);
		fixPointAnchor.setLocation(point);
		fixPointAnchor.setUseAnchorLocationAsConnectionEndpoint(true);
		fixPointAnchor.setReferencedGraphicsAlgorithm(parentShape.getGraphicsAlgorithm());
		return fixPointAnchor;
	}

	/**
	 * Returns true if the {@link PictogramElement} has a property with the provided value.
	 * @param context
	 * @param propertyKeys
	 * @return
	 */
	private static boolean doesPictogramContainProperty(PictogramElement pe, String propertyValue) {
		if (pe == null || pe.getProperties() == null) {
			return false;
		}
		for (Property p : pe.getProperties()) {
			if (p.getValue().equals(propertyValue)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Deletes a Connection from its Diagram without doing a cross-reference search.
	 * @see {@link #deletePictogramElement(PictogramElement)}
	 * @param connection the connection to delete
	 */
	public static void fastDeleteConnection(Connection connection) {
		// Connections may be referenced by their endpoints, so remove them from the anchors if necessary
		Anchor end = connection.getEnd();

		if (end != null) {
			end.getIncomingConnections().remove(connection);
		}
		Anchor start = connection.getStart();
		if (start != null) {
			start.getOutgoingConnections().remove(connection);
		}

		// FindBy objects have Graphiti Property reference to connections, so check if we need to remove this.
		String connectionId = null;
		for (EObject obj : connection.getLink().getBusinessObjects()) {
			if (obj instanceof ConnectInterface< ? , ? , ? >) {
				ConnectInterface< ? , ? , ? > ci = (ConnectInterface< ? , ? , ? >) obj;
				connectionId = ci.getId();
				break;
			}
		}
		if (connectionId != null) {
			RHContainerShape startShape = ScaEcoreUtils.getEContainerOfType(start, RHContainerShape.class);
			if (startShape != null) {
				List<Property> properties = startShape.getProperties();
				for (int i = 0; i < properties.size(); i++) {
					if (connectionId.equals(properties.get(i).getValue())) {
						properties.remove(i);
					}
				}
			}
			RHContainerShape endShape = ScaEcoreUtils.getEContainerOfType(end, RHContainerShape.class);
			if (endShape != null) {
				List<Property> properties = endShape.getProperties();
				for (int i = 0; i < properties.size(); i++) {
					if (connectionId.equals(properties.get(i).getValue())) {
						properties.remove(i);
					}
				}
			}
		}

		// The diagram may be null, if the connection is no longer part of one
		Diagram diagram = connection.getParent();
		if (diagram != null) {
			diagram.getPictogramLinks().remove(connection.getLink());
			diagram.getConnections().remove(connection);
		}
	}

	/**
	 * Deletes a PictogramElement from its Diagram without doing a cross-reference search. The default PeService
	 * implementation of deletePictogramElement() recursively deletes all of the children of a container using
	 * EcoreUtil.delete(), which searches the entire resource set for references and removes them as well. This
	 * quickly becomes an expensive operation as the graph grows, and in our case, should be unnecessary.
	 * @param pe the pictogram element to delete
	 */
	public static void fastDeletePictogramElement(PictogramElement pe) {
		if (pe instanceof Connection) {
			fastDeleteConnection((Connection) pe);
		} else {
			// Recursively remove any connections or links
			unlinkPictogramElement(pe);

			// Remove directly from the parent; as long as there are no cross-references, this effectively deletes all
			// children as well
			ContainerShape container = (ContainerShape) pe.eContainer();
			container.getChildren().remove(pe);
		}
	}

	/**
	 * Finds a direct child graphics algorithm with the given property key/value pair.
	 * @param ga
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 */
	public static GraphicsAlgorithm findChildGraphicsAlgorithmByProperty(GraphicsAlgorithm ga, String propertyName, String propertyValue) {
		if (ga == null) {
			return null;
		}
		for (GraphicsAlgorithm child : ga.getGraphicsAlgorithmChildren()) {
			if (propertyValue.equals(Graphiti.getPeService().getPropertyValue(child, propertyName))) {
				return child;
			}
		}
		return null;
	}

	/**
	 * Finds a direct child shape with the given property key/value pair.
	 * @param containerShape
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 */
	public static Shape findChildShapeByProperty(ContainerShape containerShape, String propertyName, String propertyValue) {
		if (containerShape == null) {
			return null;
		}
		for (Shape child : containerShape.getChildren()) {
			if (propertyValue.equals(Graphiti.getPeService().getPropertyValue(child, propertyName))) {
				return child;
			}
		}
		return null;
	}

	/**
	 * Search the provided PropertyContainer and all descendants for a property with key {@link #GA_TYPE} or
	 * {@link #SHAPE_TYPE} with the provided value.
	 * @param diagramElement
	 * @return
	 */
	public static PropertyContainer findFirstPropertyContainer(PropertyContainer diagramElement, String propertyValue) {
		// Check the current property container for a match
		if (isPropertyElementType(diagramElement, propertyValue)) {
			return diagramElement;
		}

		PropertyContainer p = null;
		if (diagramElement instanceof ContainerShape) {
			// If ContainerShape, iterate through PE children recursively, then GA children recursively
			ContainerShape cs = (ContainerShape) diagramElement;
			for (Shape c : cs.getChildren()) {
				p = findFirstPropertyContainer(c, propertyValue);
				if (p != null) {
					return p;
				}
			}
			if (cs.getGraphicsAlgorithm() != null) {
				p = findFirstPropertyContainer(cs.getGraphicsAlgorithm(), propertyValue);
				if (p != null) {
					return p;
				}
			}
		} else if (diagramElement instanceof GraphicsAlgorithm) {
			// If GraphicsAlgorithm, iterate through GA children recursively
			GraphicsAlgorithm ga = (GraphicsAlgorithm) diagramElement;
			for (GraphicsAlgorithm c : ga.getGraphicsAlgorithmChildren()) {
				p = findFirstPropertyContainer(c, propertyValue);
				if (p != null) {
					return p;
				}
			}
		} else if (diagramElement instanceof Shape) {
			// If Shape, check the GA
			Shape shape = (Shape) diagramElement;
			if (isPropertyElementType(shape.getGraphicsAlgorithm(), propertyValue)) {
				return shape.getGraphicsAlgorithm();
			}
		}

		return null;
	}

	/**
	 * Returns the business object for the pictogram element. <b>NOTE</b>: If possible, use
	 * {@link #getBusinessObject(PictogramElement, Class)} instead.
	 * @param pe
	 * @param cls
	 * @return
	 */
	public static EObject getBusinessObject(PictogramElement pe) {
		return GraphitiUi.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
	}

	/**
	 * Returns the business object for the pictogram element of the appropriate type.
	 * @param pe
	 * @param cls
	 * @return
	 */
	public static < T > T getBusinessObject(PictogramElement pe, Class<T> cls) {
		for (EObject eObj : Graphiti.getLinkService().getAllBusinessObjectsForLinkedPictogramElement(pe)) {
			if (cls.isInstance(eObj)) {
				return cls.cast(eObj);
			}
		}
		return null;
	}

	/**
	 * Returns the property value that indicates the mode the diagram is operating in.
	 * @param diagram
	 */
	public static String getDiagramContext(Diagram diagram) {
		return Graphiti.getPeService().getPropertyValue(diagram, DIAGRAM_CONTEXT);
	}

	/**
	 * Returns the {@link DeviceConfiguration} for the provided diagram.
	 * @param featureProvider
	 * @param diagram
	 * @return
	 */
	public static DeviceConfiguration getDiagramDCD(Diagram diagram) {
		return (DeviceConfiguration) getBusinessObject(diagram, DeviceConfiguration.class);
	}

	/**
	 * Loads (and possibly creates) the diagram.
	 * @param helper The diagram helper
	 * @param profileResource The resource containing the profile (SAD/DCD XML file)
	 * @param diagramTypeId The type of diagram to create
	 * @return
	 * @throws CoreException
	 */
	public static Diagram getDiagram(IDiagramUtilHelper helper, Resource profileResource, String diagramTypeId) throws CoreException {
		final URI profileURI = profileResource.getURI();
		if (profileURI.isPlatformResource()) {
			URI diagramURI = getDiagramRelativeURI(profileURI, helper);

			// Get the IFile in the workspace
			IPath path = new Path(diagramURI.toPlatformString(true));
			IFile diagramFile = ResourcesPlugin.getWorkspace().getRoot().getFile(path);

			// Refresh Eclipse's info on the file
			diagramFile.refreshLocal(IResource.DEPTH_ONE, new NullProgressMonitor());

			// Load the diagram, or create a new one, as appropriate
			if (diagramFile.exists()) {
				return loadDiagram(profileResource.getResourceSet(), diagramURI);
			} else {
				return createDiagram(profileResource.getResourceSet(), diagramURI, helper.getSaveOptions(), diagramTypeId);
			}
		} else if (profileURI.isFile()) {
			URI diagramURI = getDiagramRelativeURI(profileURI, helper);

			// If the file exists, load it
			if (Files.exists(Paths.get(diagramURI.toFileString()))) {
				return loadDiagram(profileResource.getResourceSet(), diagramURI);
			}
		} else if (ScaFileSystemConstants.SCHEME.equals(profileURI.scheme())) {
			URI diagramURI = getDiagramRelativeURI(profileURI, helper);

			// Code to load the diagram using the SCA EFS file system (may perform a CORBA call)
			Diagram[] retVal = new Diagram[1];
			IRunnableWithProgress runnable = monitor -> {
				CorbaUtils2.invokeUI(() -> {
					// Get file store / info for the diagram
					IFileStore fileStore = EFS.getStore(new java.net.URI(diagramURI.toString()));
					IFileInfo info = fileStore.fetchInfo(EFS.NONE, new NullProgressMonitor());

					// Load the diagram if the file exists. Otherwise we'll fall back to a temporary diagram.
					if (info.exists()) {
						retVal[0] = loadDiagram(profileResource.getResourceSet(), diagramURI);
					}
					return null;
				}, monitor);
			};

			try {
				// If we're in the display thread, use a progress monitor dialog so it can be cancelled
				if (Display.getCurrent() != null) {
					new ProgressMonitorDialog(Display.getCurrent().getActiveShell()).run(true, true, runnable);
				} else {
					runnable.run(new NullProgressMonitor());
				}
				if (retVal[0] != null) {
					return retVal[0];
				}
			} catch (InvocationTargetException e) {
				Throwable cause = e.getCause();
				if (cause instanceof CoreException) {
					// Log a warning. We'll use a temporary diagram instead.
					IStatus status = new Status(IStatus.WARNING, GraphitiUIPlugin.PLUGIN_ID, "Unable to retrieve diagram file from " + diagramURI.toString(),
						cause);
					Platform.getLog(Platform.getBundle(GraphitiUIPlugin.PLUGIN_ID)).log(status);
				} else if (cause instanceof URISyntaxException) {
					throw new CoreException(new Status(IStatus.ERROR, GraphitiUIPlugin.PLUGIN_ID, "Unable to format URI " + diagramURI.toString(), cause));
				} else {
					throw new CoreException(new Status(IStatus.ERROR, GraphitiUIPlugin.PLUGIN_ID, cause.getMessage(), cause));
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		// Diagram doesn't exist, can't be loaded, or has some URI we weren't expecting. Create an ephemeral one.
		return createDiagram(profileResource.getResourceSet(), URI.createURI("mem:/" + (int) (Math.random() * 1e6) + helper.getDiagramFileExtension()),
			helper.getSaveOptions(), diagramTypeId);
	}

	/**
	 * Construct the URI for a diagram relative to the profile
	 * @param profileURI The profile's URI
	 * @param helper The diagram helper
	 * @return The diagram resource's URI
	 */
	private static URI getDiagramRelativeURI(URI profileURI, IDiagramUtilHelper helper) {
		String profileName = profileURI.lastSegment();
		String basename = profileName.substring(0, profileName.length() - helper.getSemanticFileExtension().length());
		return profileURI.trimSegments(1).appendSegment(basename + helper.getDiagramFileExtension());
	}

	/**
	 * Creates a diagram for a profile in the workspace.
	 * @param resourceSet The resource set to use
	 * @param diagramURI The diagram resource's URI
	 * @param saveOptions EMF save options
	 * @param diagramTypeId The type of the diagram to create
	 * @return The diagram
	 * @throws CoreException
	 */
	private static Diagram createDiagram(ResourceSet resourceSet, URI diagramURI, Map< ? , ? > saveOptions, final String diagramTypeId) throws CoreException {
		// Create diagram
		final Resource diagramResource = resourceSet.createResource(diagramURI);
		Diagram diagram = Graphiti.getPeCreateService().createDiagram(diagramTypeId, diagramURI.lastSegment(), true);
		TransactionUtil.getEditingDomain(resourceSet).getCommandStack().execute(new ScaModelCommand() {
			@Override
			public void execute() {
				diagramResource.getContents().add(diagram);
			}
		});

		// Save if local
		if (diagramURI.isPlatform()) {
			try {
				diagramResource.save(saveOptions);
			} catch (IOException e) {
				throw new CoreException(new Status(IStatus.ERROR, GraphitiUIPlugin.PLUGIN_ID, "Unable to create diagram file contents", e));
			}
		}

		return diagram;
	}

	/**
	 * Loads a diagram for a profile in the workspace.
	 * @param resourceSet The resource set to use
	 * @param diagramURI The diagram resource's URI
	 * @return The diagram
	 * @throws CoreException
	 */
	private static Diagram loadDiagram(ResourceSet resourceSet, URI diagramURI) throws CoreException {
		Resource diagramResource = resourceSet.createResource(diagramURI);
		Map<String, Object> options = new HashMap<>();
		options.put(XMLResource.OPTION_URI_HANDLER, new URIHandlerImpl() {
			@Override
			public URI resolve(URI uri) {
				// Unfortunately, our style URIs are being encoded as relative paths to the SAD file i.e.
				// "../../plugin/plugin_id/etc"
				if (uri.toString().startsWith("../../plugin/")) {
					String path = String.join("/", uri.segmentsList().subList(3, uri.segmentCount()));
					return URI.createPlatformPluginURI(path, true).appendFragment(uri.fragment());
				}
				return uri.resolve(baseURI).appendQuery(baseURI.query());
			}
		});
		try {
			diagramResource.load(options);
		} catch (IOException e) {
			throw new CoreException(new Status(IStatus.ERROR, GraphitiUIPlugin.PLUGIN_ID, "Unable to load diagram resource", e));
		}
		return (Diagram) diagramResource.getContents().get(0);
	}

	/**
	 * Returns the {@link SoftwareAssembly} for the provided diagram.
	 * @param featureProvider
	 * @param diagram
	 * @return
	 */
	public static SoftwareAssembly getDiagramSAD(Diagram diagram) {
		return (SoftwareAssembly) getBusinessObject(diagram, SoftwareAssembly.class);
	}

	/**
	 * Return all incoming connections originating from within the provided ContainerShape
	 * @param containerShape
	 * @return
	 */
	public static List<Connection> getIncomingConnectionsContainedInContainerShape(ContainerShape containerShape) {
		List<Connection> connections = new ArrayList<Connection>();
		Diagram diagram = Graphiti.getPeService().getDiagramForShape(containerShape);
		for (Connection conn : diagram.getConnections()) {
			for (PictogramElement e : Graphiti.getPeService().getAllContainedPictogramElements(containerShape)) {
				if (e == conn.getEnd()) {
					connections.add(conn);
				}
			}
		}
		return connections;
	}

	/**
	 * Return all outgoing connections originating from within the provided ContainerShape
	 * @param containerShape
	 * @return
	 */
	public static List<Connection> getOutgoingConnectionsContainedInContainerShape(ContainerShape containerShape) {
		List<Connection> connections = new ArrayList<Connection>();
		Diagram diagram = Graphiti.getPeService().getDiagramForShape(containerShape);
		for (Connection conn : diagram.getConnections()) {
			for (PictogramElement e : Graphiti.getPeService().getAllContainedPictogramElements(containerShape)) {
				if (e == conn.getStart()) {
					connections.add(conn);
				}
			}
		}
		return connections;
	}

	/**
	 * Returns the {@link PictogramElement} of the specified type that is linked to business object
	 * @param diagram
	 * @param eObj
	 * @param pictogramClass
	 * @return
	 */
	public static < T extends PictogramElement > T getPictogramElementForBusinessObject(Diagram diagram, EObject eObj, Class<T> pictogramClass) {
		List<PictogramElement> pes = Graphiti.getLinkService().getPictogramElements(diagram, eObj);
		if (pes != null && pes.size() > 0) {
			for (PictogramElement p : pes) {
				if (pictogramClass.isInstance(p)) {
					return pictogramClass.cast(p);
				}
			}
		}
		return null;
	}

	/**
	 * Checks the container shape and all its children and returns any which overlap any of the specified area.
	 * @param containerShape Usually this should be the {@link Diagram}
	 * @param width
	 * @param height
	 * @param x Absolute x
	 * @param y Absolute y
	 * @return
	 */
	public static List<Shape> getShapesInArea(final ContainerShape containerShape, int width, int height, int x, int y) {
		List<Shape> retList = new ArrayList<Shape>();
		EList<Shape> shapes = containerShape.getChildren();
		for (Shape s : shapes) {
			if (shapeExistsPartiallyInArea(s, width, height, x, y)) {
				retList.add(s);
			}
		}
		return retList;
	}

	/**
	 * Layout PictogramElement via feature
	 * Relies on the framework determining which feature should be used and whether it lay out the shape
	 * @param featureProvider
	 * @param pe
	 * @return true if layout was applied
	 */
	public static boolean layoutShapeViaFeature(IFeatureProvider featureProvider, PictogramElement pe) {
		LayoutContext updateContext = new LayoutContext(pe);
		ILayoutFeature layoutFeature = featureProvider.getLayoutFeature(updateContext);
		if (layoutFeature != null && layoutFeature.canLayout(updateContext)) {
			return layoutFeature.layout(updateContext);
		}
		return false;
	}

	/**
	 * Determines if the diagram is running in explorer mode.
	 */
	public static boolean isDiagramExplorer(final Diagram diagram) {
		return getDiagramContext(diagram).equals(DIAGRAM_CONTEXT_EXPLORER);
	}

	/**
	 * Determines if the diagram is read-only (only applies to design-time).
	 * @param diagram
	 * @return
	 */
	public static boolean isDiagramReadOnly(Diagram diagram) {
		return isDiagramTargetSdr(diagram);
	}

	/**
	 * Determines if this is a runtime diagram.
	 * @param diagram
	 * @return
	 */
	public static boolean isDiagramRuntime(final Diagram diagram) {
		return getDiagramContext(diagram).equals(DIAGRAM_CONTEXT_LOCAL) || getDiagramContext(diagram).equals(DIAGRAM_CONTEXT_EXPLORER);
	}

	/**
	 * Determines if the diagram is a design-time diagram for a file in the target SDR (usually these editors are
	 * read-only).
	 * @param diagram
	 * @return
	 */
	public static boolean isDiagramTargetSdr(final Diagram diagram) {
		return getDiagramContext(diagram).equals(DIAGRAM_CONTEXT_TARGET_SDR);
	}

	/**
	 * Determines if the diagram is a design-time diagram for file in the workspace.
	 * @param diagram
	 * @return
	 */
	public static boolean isDiagramWorkpace(final Diagram diagram) {
		return getDiagramContext(diagram).equals(DIAGRAM_CONTEXT_DESIGN);
	}

	/**
	 * Returns true if the property container contains a property key {@link #GA_TYPE} or {@link #SHAPE_TYPE} with the
	 * specified value.
	 * @param pc
	 * @param propertyValue
	 * @return
	 */
	public static boolean isPropertyElementType(PropertyContainer pc, String propertyValue) {
		if (pc == null) {
			return false;
		}
		for (Property p : pc.getProperties()) {
			if ((GA_TYPE.equals(p.getKey()) || SHAPE_TYPE.equals(p.getKey())) && propertyValue.equals(p.getValue())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns true if the portContainer is a super port
	 * @param portContainer - The port container to be tested
	 * @return
	 */
	public static boolean isSuperPort(ContainerShape portContainer) {
		boolean isSuperProvides = DUtil.doesPictogramContainProperty(portContainer, RHContainerShape.SUPER_PROVIDES_PORTS_RECTANGLE);
		boolean isSuperUses = DUtil.doesPictogramContainProperty(portContainer, RHContainerShape.SUPER_USES_PORTS_RECTANGLE);
		return (isSuperProvides || isSuperUses);
	}

	/**
	 * Checks whether a {@link GraphicsAlgorithm} is visible (i.e, will draw something).
	 * @param ga GraphicsAlgorithm to check
	 * @return true if ga is visible, false if it is not
	 */
	public static boolean isVisible(GraphicsAlgorithm ga) {
		return ga.getLineVisible() || ga.getFilled();
	}

	/**
	 * Remove a {@link PictogramElement} using the appropriate Graphiti remove feature, if available.
	 * @param featureProvider
	 * @param pe
	 */
	public static void removeShapeViaFeature(IFeatureProvider featureProvider, PictogramElement pe) {
		RemoveContext removeContext = new RemoveContext(pe);
		IRemoveFeature removeFeature = featureProvider.getRemoveFeature(removeContext);
		if (removeFeature != null) {
			removeFeature.remove(removeContext);
		}
	}

	/**
	 * Updates whether a {@link GraphicsAlgorithm} is visible (i.e, will draw something).
	 * @param ga {@link GraphicsAlgorithm} to modify
	 * @param visible new visibility of ga
	 * @return true if the visibility changed, false if it was already set
	 */
	public static boolean setVisible(GraphicsAlgorithm ga, boolean visible) {
		if (isVisible(ga) != visible) {
			ga.setFilled(visible);
			ga.setLineVisible(visible);
			return true;
		}
		return false;
	}

	/**
	 * Determine if a shape overlaps an area. Coordinates should be absolute.
	 * @param s
	 * @param width
	 * @param height
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean shapeExistsPartiallyInArea(final Shape s, int width, int height, int x, int y) {
		GraphicsAlgorithm ga = s.getGraphicsAlgorithm();
		ILocation shapeLoc = GraphitiUi.getUiLayoutService().getLocationRelativeToDiagram(s);
		return ((x + width) > ga.getX() && x < (shapeLoc.getX() + ga.getWidth()) && (y + height) > ga.getY() && y < (shapeLoc.getY() + ga.getHeight()));
	}

	/**
	 * Adjust children x/y so they remain in the same relative position after resize
	 * @param containerShape
	 * @param context
	 */
	public static void shiftChildrenRelativeToParentResize(ContainerShape containerShape, IResizeShapeContext context) {

		int widthDiff = containerShape.getGraphicsAlgorithm().getWidth() - context.getWidth();
		int heightDiff = containerShape.getGraphicsAlgorithm().getHeight() - context.getHeight();
		switch (context.getDirection()) {
		case (IResizeShapeContext.DIRECTION_NORTH_EAST):
			shiftChildrenYPositionUp(containerShape, heightDiff);
			break;
		case (IResizeShapeContext.DIRECTION_WEST):
		case (IResizeShapeContext.DIRECTION_SOUTH_WEST):
			shiftChildrenXPositionLeft(containerShape, widthDiff);
			break;
		case (IResizeShapeContext.DIRECTION_NORTH_WEST):
			shiftChildrenXPositionLeft(containerShape, widthDiff);
			shiftChildrenYPositionUp(containerShape, heightDiff);
			break;
		case (IResizeShapeContext.DIRECTION_NORTH): // handle top of box getting smaller
			shiftChildrenYPositionUp(containerShape, heightDiff);
			break;
		default:
			break;
		}
	}

	/**
	 * Shifts children of container x value to the left by specified amount
	 * Can be negative
	 * @param ga
	 * @param shiftLeftAmount
	 */
	private static void shiftChildrenXPositionLeft(ContainerShape containerShape, int shiftLeftAmount) {
		for (Shape s : containerShape.getChildren()) {
			GraphicsAlgorithm ga = s.getGraphicsAlgorithm();
			Graphiti.getGaService().setLocation(ga, ga.getX() - shiftLeftAmount, ga.getY());
		}
	}

	/**
	 * Shifts children of container Y value up by specified amount
	 * Can be negative
	 * @param ga
	 * @param shiftUpAmount
	 */
	private static void shiftChildrenYPositionUp(ContainerShape containerShape, int shiftUpAmount) {
		for (Shape s : containerShape.getChildren()) {
			GraphicsAlgorithm ga = s.getGraphicsAlgorithm();
			Graphiti.getGaService().setLocation(ga, ga.getX(), ga.getY() - shiftUpAmount);
		}
	}

	/**
	 * Internal method to recursively removes all business object links from a PictogramElement and its children.
	 * Used by {@link #fastDeletePictogramElement(PictogramElement)}.
	 * @param pe the pictogram element to unlink
	 */
	private static void unlinkPictogramElement(PictogramElement pe) {
		if (pe instanceof AnchorContainer) {
			// Remove business objects links from anchors
			for (Anchor anchor : ((AnchorContainer) pe).getAnchors()) {
				unlinkPictogramElement(anchor);
			}
		}

		// The diagram holds references to all the of links as well
		Diagram diagram = Graphiti.getPeService().getDiagramForPictogramElement(pe);
		diagram.getPictogramLinks().remove(pe.getLink());

		// Recursively unlink children
		if (pe instanceof ContainerShape) {
			for (Shape child : ((ContainerShape) pe).getChildren()) {
				unlinkPictogramElement(child);
			}
		}
	}
}
