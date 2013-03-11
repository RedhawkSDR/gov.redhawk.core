/**
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
// BEGIN GENERATED CODE

package mil.jpeojtrs.sca.sad.diagram.edit.parts;

import mil.jpeojtrs.sca.sad.diagram.part.SadVisualIDRegistry;

import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

/**
 * @generated
 */
public class SadEditPartFactory implements EditPartFactory {

	/**
	 * @generated
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			switch (SadVisualIDRegistry.getVisualID(view)) {

			case SoftwareAssemblyEditPart.VISUAL_ID:
				return new SoftwareAssemblyEditPart(view);

			case PartitioningEditPart.VISUAL_ID:
				return new PartitioningEditPart(view);

			case WrappingLabelEditPart.VISUAL_ID:
				return new WrappingLabelEditPart(view);

			case ComponentPlacementEditPart.VISUAL_ID:
				return new ComponentPlacementEditPart(view);

			case ComponentPlacementNameEditPart.VISUAL_ID:
				return new ComponentPlacementNameEditPart(view);

			case SadComponentInstantiationEditPart.VISUAL_ID:
				return new SadComponentInstantiationEditPart(view);

			case SadComponentInstantiationUsageNameEditPart.VISUAL_ID:
				return new SadComponentInstantiationUsageNameEditPart(view);

			case UsesPortStubEditPart.VISUAL_ID:
				return new UsesPortStubEditPart(view);

			case UsesPortStubNameEditPart.VISUAL_ID:
				return new UsesPortStubNameEditPart(view);

			case ProvidesPortStubEditPart.VISUAL_ID:
				return new ProvidesPortStubEditPart(view);

			case ProvidesPortStubNameEditPart.VISUAL_ID:
				return new ProvidesPortStubNameEditPart(view);

			case ComponentSupportedInterfaceStubEditPart.VISUAL_ID:
				return new ComponentSupportedInterfaceStubEditPart(view);

			case HostCollocationEditPart.VISUAL_ID:
				return new HostCollocationEditPart(view);

			case HostCollocationNameEditPart.VISUAL_ID:
				return new HostCollocationNameEditPart(view);

			case HostCollocationIdEditPart.VISUAL_ID:
				return new HostCollocationIdEditPart(view);

			case ComponentPlacement2EditPart.VISUAL_ID:
				return new ComponentPlacement2EditPart(view);

			case ComponentPlacementName2EditPart.VISUAL_ID:
				return new ComponentPlacementName2EditPart(view);

			case PartitioningCompartmentEditPart.VISUAL_ID:
				return new PartitioningCompartmentEditPart(view);

			case ComponentPlacementCompartmentEditPart.VISUAL_ID:
				return new ComponentPlacementCompartmentEditPart(view);

			case HostCollocationCompartmentEditPart.VISUAL_ID:
				return new HostCollocationCompartmentEditPart(view);

			case ComponentPlacementCompartment2EditPart.VISUAL_ID:
				return new ComponentPlacementCompartment2EditPart(view);

			case SadConnectInterfaceEditPart.VISUAL_ID:
				return new SadConnectInterfaceEditPart(view);

			}
		}
		return createUnrecognizedEditPart(context, model);
	}

	/**
	 * @generated
	 */
	private EditPart createUnrecognizedEditPart(EditPart context, Object model) {
		// Handle creation of unrecognized child node EditParts here
		return null;
	}

	/**
	 * @generated
	 */
	public static CellEditorLocator getTextCellEditorLocator(
			ITextAwareEditPart source) {
		if (source.getFigure() instanceof WrappingLabel)
			return new TextCellEditorLocator((WrappingLabel) source.getFigure());
		else {
			return new LabelCellEditorLocator((Label) source.getFigure());
		}
	}

	/**
	 * @generated
	 */
	static private class TextCellEditorLocator implements CellEditorLocator {

		/**
		 * @generated
		 */
		private WrappingLabel wrapLabel;

		/**
		 * @generated
		 */
		public TextCellEditorLocator(WrappingLabel wrapLabel) {
			this.wrapLabel = wrapLabel;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getWrapLabel() {
			return wrapLabel;
		}

		/**
		 * @generated
		 */
		public void relocate(CellEditor celleditor) {
			Text text = (Text) celleditor.getControl();
			Rectangle rect = getWrapLabel().getTextBounds().getCopy();
			getWrapLabel().translateToAbsolute(rect);
			if (!text.getFont().isDisposed()) {
				if (getWrapLabel().isTextWrapOn()
						&& getWrapLabel().getText().length() > 0) {
					rect.setSize(new Dimension(text.computeSize(rect.width,
							SWT.DEFAULT)));
				} else {
					int avr = FigureUtilities.getFontMetrics(text.getFont())
							.getAverageCharWidth();
					rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT,
							SWT.DEFAULT)).expand(avr * 2, 0));
				}
			}
			if (!rect.equals(new Rectangle(text.getBounds()))) {
				text.setBounds(rect.x, rect.y, rect.width, rect.height);
			}
		}
	}

	/**
	 * @generated
	 */
	private static class LabelCellEditorLocator implements CellEditorLocator {

		/**
		 * @generated
		 */
		private Label label;

		/**
		 * @generated
		 */
		public LabelCellEditorLocator(Label label) {
			this.label = label;
		}

		/**
		 * @generated
		 */
		public Label getLabel() {
			return label;
		}

		/**
		 * @generated
		 */
		public void relocate(CellEditor celleditor) {
			Text text = (Text) celleditor.getControl();
			Rectangle rect = getLabel().getTextBounds().getCopy();
			getLabel().translateToAbsolute(rect);
			if (!text.getFont().isDisposed()) {
				int avr = FigureUtilities.getFontMetrics(text.getFont())
						.getAverageCharWidth();
				rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT,
						SWT.DEFAULT)).expand(avr * 2, 0));
			}
			if (!rect.equals(new Rectangle(text.getBounds()))) {
				text.setBounds(rect.x, rect.y, rect.width, rect.height);
			}
		}
	}
}
