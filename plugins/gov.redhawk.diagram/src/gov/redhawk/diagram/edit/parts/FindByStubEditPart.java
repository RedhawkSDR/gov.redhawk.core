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

package gov.redhawk.diagram.edit.parts;

import gov.redhawk.diagram.edit.policies.FindByStubCanonicalEditPolicy;
import gov.redhawk.diagram.part.PartitioningVisualIDRegistry;
import gov.redhawk.diagram.part.PartitioningVisualIDRegistry.MAPPING_ID;
import gov.redhawk.diagram.providers.PartitioningElementTypes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import mil.jpeojtrs.sca.diagram.figures.FindByStubFigure;
import mil.jpeojtrs.sca.partitioning.ComponentSupportedInterfaceStub;
import mil.jpeojtrs.sca.partitioning.ProvidesPortStub;
import mil.jpeojtrs.sca.partitioning.UsesPortStub;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.core.edithelpers.CreateElementRequestAdapter;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.BorderItemSelectionEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DragDropEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;

/**
 * @since 3.0
 * 
 */
public class FindByStubEditPart extends AbstractBorderedShapeEditPart {

	/**
	* 
	*/
	public static final int VISUAL_ID = 12002;

	/**
	* 
	*/
	protected IFigure contentPane;

	/**
	* 
	*/
	protected IFigure primaryShape;

	private final PartitioningVisualIDRegistry registry;

	private final PartitioningElementTypes elementTypes;

	/**
	* 
	*/
	public FindByStubEditPart(final View view, final PartitioningVisualIDRegistry registry, final PartitioningElementTypes elementTypes) {
		super(view);
		this.registry = registry;
		this.elementTypes = elementTypes;
	}

	/**
	* 
	*/
	@Override
	protected void createDefaultEditPolicies() {
		installEditPolicy(EditPolicyRoles.CREATION_ROLE, new CreationEditPolicy());
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE, new DragDropEditPolicy());
		installEditPolicy(EditPolicyRoles.CANONICAL_ROLE, new FindByStubCanonicalEditPolicy(this.registry));
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	/**
	* 
	*/
	protected LayoutEditPolicy createLayoutEditPolicy() {
		final org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy lep = new org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy() {

			@Override
			protected EditPolicy createChildEditPolicy(final EditPart child) {
				final View childView = (View) child.getModel();
				final MAPPING_ID mappingId = FindByStubEditPart.this.registry.getMappingID(childView);
				if (mappingId != null) {
					switch (mappingId) {
					case ProvidesPortStubEditPart:
					case UsesPortStubEditPart:
					case ComponentSupportedInterfaceStubEditPart:
						return new BorderItemSelectionEditPolicy();
					default:
						break;
					}
				}
				EditPolicy result = child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
				if (result == null) {
					result = new NonResizableEditPolicy();
				}
				return result;
			}

			@Override
			protected Command getMoveChildrenCommand(final Request request) {
				return null;
			}

			@Override
			protected Command getCreateCommand(final CreateRequest request) {
				return null;
			}
		};
		return lep;
	}

	/**
	* 
	*/
	protected IFigure createNodeShape() {
		this.primaryShape = new FindByStubFigure();
		return this.primaryShape;
	}

	/**
	* 
	*/
	public FindByStubFigure getPrimaryShape() {
		return (FindByStubFigure) this.primaryShape;
	}

	/**
	* 
	*/
	protected boolean addFixedChild(final EditPart childEditPart) {
		if (childEditPart instanceof FindByStubCompartmentEditPart) {
			final IFigure pane = getPrimaryShape().getFigureFindByStubCompartmentFigure();
			setupContentPane(pane); // FIXME each comparment should handle his content pane in his own way 
			pane.add(((FindByStubCompartmentEditPart) childEditPart).getFigure());
			return true;
		}
		final EObject semanticModel = EditPartUtil.getSemanticModelObject(childEditPart);
		if (semanticModel instanceof ProvidesPortStub) {
			final BorderItemLocator locator = new BorderItemLocator(getMainFigure(), PositionConstants.WEST);
			getBorderedFigure().getBorderItemContainer().add(((GraphicalEditPart) childEditPart).getFigure(), locator);
			return true;
		}
		if (semanticModel instanceof UsesPortStub) {
			final BorderItemLocator locator = new BorderItemLocator(getMainFigure(), PositionConstants.EAST);
			getBorderedFigure().getBorderItemContainer().add(((GraphicalEditPart) childEditPart).getFigure(), locator);
			return true;
		}
		if (semanticModel instanceof ComponentSupportedInterfaceStub) {
			final BorderItemLocator locator = new BorderItemLocator(getMainFigure(), PositionConstants.WEST);
			getBorderedFigure().getBorderItemContainer().add(((GraphicalEditPart) childEditPart).getFigure(), locator);
			return true;
		}
		if (childEditPart instanceof FindByStubNameEditPart) {
			((FindByStubNameEditPart) childEditPart).setLabel(getPrimaryShape().getFigureFindByStubLabel());
			return true;
		}
		return false;
	}

	/**
	* 
	*/
	protected boolean removeFixedChild(final EditPart childEditPart) {
		if (childEditPart instanceof FindByStubCompartmentEditPart) {
			final IFigure pane = getPrimaryShape().getFigureFindByStubCompartmentFigure();
			setupContentPane(pane); // FIXME each comparment should handle his content pane in his own way 
			pane.remove(((FindByStubCompartmentEditPart) childEditPart).getFigure());
			return true;
		}
		final EObject semanticModel = EditPartUtil.getSemanticModelObject(childEditPart);
		if (semanticModel instanceof ProvidesPortStub) {
			getBorderedFigure().getBorderItemContainer().remove(((GraphicalEditPart) childEditPart).getFigure());
			return true;
		}
		if (semanticModel instanceof UsesPortStub) {
			getBorderedFigure().getBorderItemContainer().remove(((GraphicalEditPart) childEditPart).getFigure());
			return true;
		}
		if (semanticModel instanceof ComponentSupportedInterfaceStub) {
			getBorderedFigure().getBorderItemContainer().remove(((GraphicalEditPart) childEditPart).getFigure());
			return true;
		}
		if (childEditPart instanceof FindByStubNameEditPart) {
			return true;
		}
		return false;
	}

	/**
	* 
	*/
	@Override
	protected void addChildVisual(final EditPart childEditPart, final int index) {
		if (addFixedChild(childEditPart)) {
			return;
		}
		super.addChildVisual(childEditPart, -1);
	}

	/**
	* 
	*/
	@Override
	protected void removeChildVisual(final EditPart childEditPart) {
		if (removeFixedChild(childEditPart)) {
			return;
		}
		super.removeChildVisual(childEditPart);
	}

	/**
	* 
	*/
	@Override
	protected IFigure getContentPaneFor(final IGraphicalEditPart editPart) {
		if (editPart instanceof FindByStubCompartmentEditPart) {
			return getPrimaryShape().getFigureFindByStubCompartmentFigure();
		}
		if (editPart instanceof IBorderItemEditPart) {
			return getBorderedFigure().getBorderItemContainer();
		}
		return getContentPane();
	}

	/**
	* 
	*/
	protected NodeFigure createNodePlate() {
		final DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(40, 40);
		return result;
	}

	/**
	* Creates figure for this edit part.
	* 
	* Body of this method does not depend on settings in generation model
	* so you may safely remove <i>generated</i> tag and modify it.
	* 
	* 
	*/
	@Override
	protected NodeFigure createMainFigure() {
		final NodeFigure figure = createNodePlate();
		figure.setLayoutManager(new StackLayout());
		final IFigure shape = createNodeShape();
		figure.add(shape);
		this.contentPane = setupContentPane(shape);
		return figure;
	}

	/**
	* Default implementation treats passed figure as content pane.
	* Respects layout one may have set for generated figure.
	* @param nodeShape instance of generated figure class
	* 
	*/
	protected IFigure setupContentPane(final IFigure nodeShape) {
		if (nodeShape.getLayoutManager() == null) {
			final ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
			layout.setSpacing(5);
			nodeShape.setLayoutManager(layout);
		}
		return nodeShape; // use nodeShape itself as contentPane
	}

	/**
	* 
	*/
	@Override
	public IFigure getContentPane() {
		if (this.contentPane != null) {
			return this.contentPane;
		}
		return super.getContentPane();
	}

	/**
	* 
	*/
	@Override
	protected void setForegroundColor(final Color color) {
		if (this.primaryShape != null) {
			this.primaryShape.setForegroundColor(color);
		}
	}

	/**
	* 
	*/
	@Override
	protected void setBackgroundColor(final Color color) {
		if (this.primaryShape != null) {
			this.primaryShape.setBackgroundColor(color);
		}
	}

	/**
	* 
	*/
	@Override
	protected void setLineWidth(final int width) {
		if (this.primaryShape instanceof Shape) {
			((Shape) this.primaryShape).setLineWidth(width);
		}
	}

	/**
	* 
	*/
	@Override
	protected void setLineType(final int style) {
		if (this.primaryShape instanceof Shape) {
			((Shape) this.primaryShape).setLineStyle(style);
		}
	}

	/**
	* 
	*/
	public List<IElementType> getMARelTypesOnTarget() {
		final ArrayList<IElementType> types = new ArrayList<IElementType>(1);
		types.add(this.elementTypes.getConnectInterfaceElementType());
		return types;
	}

	/**
	* 
	*/
	public List<IElementType> getMATypesForSource(final IElementType relationshipType) {
		final LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (relationshipType == this.elementTypes.getConnectInterfaceElementType()) {
			types.add(this.elementTypes.getUsesPortStubElementType());
		}
		return types;
	}

	/**
	* 
	*/
	@Override
	public EditPart getTargetEditPart(final Request request) {
		if (request instanceof CreateViewAndElementRequest) {
			final CreateElementRequestAdapter adapter = ((CreateViewAndElementRequest) request).getViewAndElementDescriptor().getCreateElementRequestAdapter();
			final IElementType type = (IElementType) adapter.getAdapter(IElementType.class);
			if (type == PartitioningElementTypes.NamingService) {
				return getChildBySemanticHint(this.registry.getType(FindByStubCompartmentEditPart.VISUAL_ID));
			}
			if (type == PartitioningElementTypes.DomainFinder) {
				return getChildBySemanticHint(this.registry.getType(FindByStubCompartmentEditPart.VISUAL_ID));
			}
		}
		return super.getTargetEditPart(request);
	}

}
