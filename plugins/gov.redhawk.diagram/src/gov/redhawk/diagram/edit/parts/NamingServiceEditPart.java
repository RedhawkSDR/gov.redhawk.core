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

import gov.redhawk.diagram.edit.policies.NamingServiceItemSemanticEditPolicy;
import gov.redhawk.diagram.part.PartitioningVisualIDRegistry;
import gov.redhawk.diagram.providers.PartitioningElementTypes;
import mil.jpeojtrs.sca.diagram.figures.NamingServiceFigure;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;

/**
 * @since 3.0
 * 
 */
public class NamingServiceEditPart extends ShapeNodeEditPart {

	/**
	* 
	*/
	public static final int VISUAL_ID = 13011;

	/**
	* 
	*/
	protected IFigure contentPane; // SUPPRESS CHECKSTYLE Protected field

	/**
	* 
	*/
	protected IFigure primaryShape; // SUPPRESS CHECKSTYLE Protected field

	/**
	* 
	*/
	public NamingServiceEditPart(final View view, final PartitioningElementTypes elementTypes, final PartitioningVisualIDRegistry visualIdRegistry) {
		super(view);
		this.elementTypes = elementTypes;
		this.visualIdRegistry = visualIdRegistry;
	}

	/**
	 * 
	 */
	private final PartitioningElementTypes elementTypes;

	/**
	 * 
	 */
	private final PartitioningVisualIDRegistry visualIdRegistry;

	/**
	* 
	*/
	@Override
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new NamingServiceItemSemanticEditPolicy(this.elementTypes, this.visualIdRegistry));
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
		this.primaryShape = new NamingServiceFigure();
		return this.primaryShape;
	}

	/**
	* 
	*/
	public NamingServiceFigure getPrimaryShape() {
		return (NamingServiceFigure) this.primaryShape;
	}

	/**
	* 
	*/
	protected boolean addFixedChild(final EditPart childEditPart) {
		if (childEditPart instanceof NamingServiceNameEditPart) {
			((NamingServiceNameEditPart) childEditPart).setLabel(getPrimaryShape().getFigureNamingServiceNameLabel());
			return true;
		}
		return false;
	}

	/**
	* 
	*/
	protected boolean removeFixedChild(final EditPart childEditPart) {
		if (childEditPart instanceof NamingServiceNameEditPart) {
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
	protected NodeFigure createNodeFigure() {
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
	@Override
	public EditPart getPrimaryChildEditPart() {
		return getChildBySemanticHint(this.visualIdRegistry.getType((NamingServiceNameEditPart.VISUAL_ID)));
	}

}
