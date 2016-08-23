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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.datatypes.IDimension;
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

import gov.redhawk.core.graphiti.ui.ext.RHContainerShape;
import mil.jpeojtrs.sca.dcd.DeviceConfiguration;
import mil.jpeojtrs.sca.partitioning.ConnectInterface;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

public class DUtil {

	// Property keys
	public static final String DIAGRAM_CONTEXT = "DiagramContext";
	public static final String GA_TYPE = "GAType";
	public static final String SHAPE_TYPE = "ShapeType";

	// Diagram context property values
	public static final String DIAGRAM_CONTEXT_EXPLORER = "explorer";
	public static final String DIAGRAM_CONTEXT_LOCAL = "local";
	public static final String DIAGRAM_CONTEXT_TARGET_SDR = "target-sdr";

	private DUtil() {
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
	 * Returns the {@link SoftwareAssembly} for the provided diagram.
	 * @param featureProvider
	 * @param diagram
	 * @return
	 */
	public static SoftwareAssembly getDiagramSAD(Diagram diagram) {
		return (SoftwareAssembly) getBusinessObject(diagram, SoftwareAssembly.class);
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
