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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.features.context.impl.LayoutContext;
import org.eclipse.graphiti.features.context.impl.RemoveContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
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
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import gov.redhawk.core.graphiti.ui.GraphitiUIPlugin;
import gov.redhawk.core.graphiti.ui.diagram.features.LayoutDiagramFeature;
import gov.redhawk.core.graphiti.ui.editor.IDiagramUtilHelper;
import gov.redhawk.core.graphiti.ui.ext.RHContainerShape;
import gov.redhawk.core.graphiti.ui.internal.diagram.wizards.SuperPortConnectionWizard;
import gov.redhawk.diagram.util.InterfacesUtil;
import mil.jpeojtrs.sca.dcd.DeviceConfiguration;
import mil.jpeojtrs.sca.partitioning.ConnectInterface;
import mil.jpeojtrs.sca.partitioning.ConnectionTarget;
import mil.jpeojtrs.sca.partitioning.UsesPortStub;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;
import mil.jpeojtrs.sca.util.ScaResourceFactoryUtil;

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

	// Spacing/distance constants
	private static final int DIAGRAM_SHAPE_HORIZONTAL_PADDING = 100;
	private static final int DIAGRAM_SHAPE_ROOT_VERTICAL_PADDING = 50;
	private static final int DIAGRAM_SHAPE_SIBLING_VERTICAL_PADDING = 5;

	protected DUtil() {
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

	// do this because we need to pass it to layout diagram, assumes we already have shapes drawn of a certain
	// size and that we are just moving them
	public static Point calculateDiagramBounds(Diagram diagram) {
		// get all shapes in diagram, components, findby's etc
		List<RHContainerShape> rootShapes = new ArrayList<RHContainerShape>();
		for (Shape shape : diagram.getChildren()) {
			if (shape instanceof RHContainerShape) {
				RHContainerShape rhContainerShape = (RHContainerShape) shape;
				// if it has no provides ports or it has ports WITH NO CONNECTIONS than its a root in the tree
				if (rhContainerShape.getProvidesPortStubs() != null
					&& (rhContainerShape.getProvidesPortStubs().size() < 1 || getIncomingConnectionsContainedInContainerShape(rhContainerShape).size() < 1)) {
					rootShapes.add(rhContainerShape);
				}
			}
		}

		// combine dimensions of each root tree to determine total dimension required to house all shapes in diagram
		int height = 0;
		int width = 0;
		for (RHContainerShape shape : rootShapes) {
			Point childTreeDimension = calculateTreeDimensions(shape);
			height += childTreeDimension.getY();
			// use largest width
			width = Math.max(childTreeDimension.getX(), width);
		}
		// add padding between roots
		height += DIAGRAM_SHAPE_ROOT_VERTICAL_PADDING * rootShapes.size() - 1;

		Point point = StylesFactory.eINSTANCE.createPoint();
		point.setX(width);
		point.setY(height);
		return point;
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
	 * Returns dimensions required to contain all shapes aligned in a horizontal tree diagram beginning with the
	 * provided root shape.
	 * @param rootShape
	 * @return
	 */
	private static Point calculateTreeDimensions(RHContainerShape rootShape) {
		return calculateTreeDimensions(rootShape, new HashSet<RHContainerShape>());
	}

	/**
	 * Internal method used by {@link #calculateTreeDimensions(RHContainerShape)}.
	 * @param rootShape
	 * @return
	 */
	private static Point calculateTreeDimensions(RHContainerShape rootShape, Set<RHContainerShape> visitedShapes) {
		// Keep track of the shape we're visiting; if we've been here, we're in a circular recursion
		if (!visitedShapes.add(rootShape)) {
			return null;
		}

		int height = rootShape.getGraphicsAlgorithm().getHeight();
		int width = rootShape.getGraphicsAlgorithm().getWidth();
		int childWidth = 0;
		int childHeight = 0;

		List<Connection> outs = getOutgoingConnectionsContainedInContainerShape(rootShape);
		for (Connection conn : outs) {
			RHContainerShape targetRHContainerShape = ScaEcoreUtils.getEContainerOfType(conn.getEnd(), RHContainerShape.class);
			Point childDimension = calculateTreeDimensions(targetRHContainerShape, visitedShapes);
			if (childDimension == null) {
				continue;
			}
			childHeight += childDimension.getY() + DIAGRAM_SHAPE_SIBLING_VERTICAL_PADDING;
			// use largest width but don't add
			childWidth = Math.max(childDimension.getX(), childWidth);
		}
		if (outs.size() > 0) {
			width += childWidth + DIAGRAM_SHAPE_HORIZONTAL_PADDING;
		}
		// choose the largest of parent height or combined child height
		height = Math.max(childHeight, height);

		Point point = StylesFactory.eINSTANCE.createPoint();
		point.setX(width);
		point.setY(height);
		return point;
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

	public static URI getDiagramResourceURI(final IDiagramUtilHelper options, final Resource resource) throws IOException {
		if (resource != null) {
			final URI uri = resource.getURI();
			if (uri.isPlatformResource()) {
				final IFile file = options.getFile(resource);
				return getRelativeDiagramResourceURI(options, file);
			} else {
				return getTemporaryDiagramResourceURI(options, uri);
			}
		}
		return null;
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
	private static List<Connection> getOutgoingConnectionsContainedInContainerShape(ContainerShape containerShape) {
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
	 * Create a {@link URI} for the Graphiti diagram's resource file that is relative to the XML file that will be
	 * edited.
	 * @param options
	 * @param file The {@link IFile} (XML) that will be edited
	 */
	private static URI getRelativeDiagramResourceURI(final IDiagramUtilHelper options, final IFile file) {
		int fileNameLengthNoExtension = file.getName().length() - options.getSemanticFileExtension().length();
		final IFile diagramFile = file.getParent().getFile(
			new Path(file.getName().substring(0, fileNameLengthNoExtension) + options.getDiagramFileExtension()));
		final URI uri = URI.createPlatformResourceURI(diagramFile.getFullPath().toString(), true);
		return uri;
	}

	/**
	 * Create a temporary file to contain the Graphiti diagram's resource. Return a {@link URI} to that file.
	 * @param options
	 * @param uri The {@link URI} of the XML that will be edited
	 * @throws IOException
	 */
	private static URI getTemporaryDiagramResourceURI(final IDiagramUtilHelper options, final URI uri) throws IOException {
		final String name = uri.lastSegment();
		int fileNameLengthNoExtension = name.length() - options.getSemanticFileExtension().length();

		java.nio.file.Path tmpFile;
		try {
			// Create a temporary file in our bundle's state location
			IPath statePath = Platform.getStateLocation(Platform.getBundle(GraphitiUIPlugin.PLUGIN_ID));
			java.nio.file.Path stateDir = Paths.get(statePath.toFile().getAbsolutePath());
			tmpFile = Files.createTempFile(stateDir, name.substring(0, fileNameLengthNoExtension), options.getDiagramFileExtension());
		} catch (IllegalStateException e) {
			// Unable to init the bundle state location; create the file in the system temporary directory
			tmpFile = Files.createTempFile(name.substring(0, fileNameLengthNoExtension), options.getDiagramFileExtension());
		}

		tmpFile.toFile().deleteOnExit();
		return URI.createURI(tmpFile.toUri().toString());
	}

	/**
	 * Causes a diagram layout to be invoked for runtime diagrams and target SDR diagrams.
	 * @param diagramEditor
	 */
	public static void layout(DiagramEditor diagramEditor) {
		Diagram diagram = diagramEditor.getDiagramTypeProvider().getDiagram();
		if (isDiagramTargetSdr(diagram) || isDiagramRuntime(diagram)) {
			DiagramBehavior diagramBehavior = diagramEditor.getDiagramBehavior();
			IFeatureProvider featureProvider = diagramEditor.getDiagramTypeProvider().getFeatureProvider();

			final ICustomContext context = new CustomContext(new PictogramElement[] { diagram });
			ICustomFeature[] features = featureProvider.getCustomFeatures(context);
			for (final ICustomFeature feature : features) {
				if (feature instanceof LayoutDiagramFeature) {
					TransactionalEditingDomain ed = diagramBehavior.getEditingDomain();
					TransactionalCommandStack cs = (TransactionalCommandStack) ed.getCommandStack();
					cs.execute(new RecordingCommand(ed) {

						@Override
						protected void doExecute() {
							((LayoutDiagramFeature) feature).execute(context);
						}
					});
				}
			}
		}
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

	public static void initializeDiagramResource(final IDiagramUtilHelper options, final String diagramTypeId, final String diagramTypeProviderId,
		final URI diagramURI) throws IOException, CoreException {
		if (diagramURI.isPlatform()) {
			final IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(diagramURI.toPlatformString(true)));

			file.refreshLocal(IResource.DEPTH_ONE, new NullProgressMonitor());

			if (!file.exists()) {
				final IWorkspaceRunnable operation = new IWorkspaceRunnable() {

					@Override
					public void run(final IProgressMonitor monitor) throws CoreException {
						final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
						try {
							populateDiagram(options, diagramTypeId, diagramTypeProviderId, diagramURI, buffer);
						} catch (final IOException e) {
							// PASS
						}
						file.create(new ByteArrayInputStream(buffer.toByteArray()), true, monitor);
					}

				};
				final ISchedulingRule rule = ResourcesPlugin.getWorkspace().getRuleFactory().createRule(file);

				ResourcesPlugin.getWorkspace().run(operation, rule, 0, null);
			}
		} else {
			populateDiagram(options, diagramTypeId, diagramTypeProviderId, diagramURI, null);
		}
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

	private static void populateDiagram(final IDiagramUtilHelper options, final String diagramTypeId, final String diagramTypeProviderId, final URI diagramURI,
		final OutputStream buffer) throws IOException {
		// Create Resource
		final ResourceSet resourceSet = ScaResourceFactoryUtil.createResourceSet();
		final Resource diagramResource = resourceSet.createResource(diagramURI);

		// Create Diagram and add to Resource
		final String diagramName = diagramURI.lastSegment();
		Diagram diagram = Graphiti.getPeCreateService().createDiagram(diagramTypeId, diagramName, true);
		diagramResource.getContents().add(diagram);

		// TODO:we will want to move this logic somewhere else
		IDiagramTypeProvider dtp = GraphitiUi.getExtensionManager().createDiagramTypeProvider(diagram, diagramTypeProviderId);
		IFeatureProvider featureProvider = dtp.getFeatureProvider();

		if (buffer != null) {
			diagramResource.save(buffer, options.getSaveOptions());
		} else {
			diagramResource.save(options.getSaveOptions());
		}
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
