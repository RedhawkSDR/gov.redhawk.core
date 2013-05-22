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
package gov.redhawk.diagram.factories;

import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

/**
 * @since 3.0
 */
public class PartitioningEditPartFactory implements EditPartFactory {

	public EditPart createEditPart(final EditPart context, final Object model) {
		return createUnrecognizedEditPart(context, model);
	}

	/**
	*
	*/
	private EditPart createUnrecognizedEditPart(final EditPart context, final Object model) {
		// Handle creation of unrecognized child node EditParts here
		return null;
	}

	/**
	* 
	*/
	public static CellEditorLocator getTextCellEditorLocator(final ITextAwareEditPart source) {
		if (source.getFigure() instanceof WrappingLabel) {
			return new TextCellEditorLocator((WrappingLabel) source.getFigure());
		} else {
			return new LabelCellEditorLocator((Label) source.getFigure());
		}
	}

	/**
	* 
	*/
	private static class TextCellEditorLocator implements CellEditorLocator {

		/**
		* 
		*/
		private final WrappingLabel wrapLabel;

		/**
		* 
		*/
		public TextCellEditorLocator(final WrappingLabel wrapLabel) {
			this.wrapLabel = wrapLabel;
		}

		/**
		* 
		*/
		public WrappingLabel getWrapLabel() {
			return this.wrapLabel;
		}

		/**
		* 
		*/
		public void relocate(final CellEditor celleditor) {
			final Control text = celleditor.getControl();
			final Rectangle rect = getWrapLabel().getTextBounds().getCopy();
			getWrapLabel().translateToAbsolute(rect);
			if (!text.getFont().isDisposed()) {
				if (getWrapLabel().isTextWrapOn() && getWrapLabel().getText().length() > 0) {
					rect.setSize(new Dimension(text.computeSize(rect.width, SWT.DEFAULT)));
				} else {
					final int avr = FigureUtilities.getFontMetrics(text.getFont()).getAverageCharWidth();
					rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT, SWT.DEFAULT)).expand(avr * 2, 0));
				}
			}
			if (!rect.equals(new Rectangle(text.getBounds()))) {
				text.setBounds(rect.x, rect.y, rect.width, rect.height);
			}
		}
	}

	/**
	* 
	*/
	private static class LabelCellEditorLocator implements CellEditorLocator {

		/**
		* 
		*/
		private final Label label;

		/**
		* 
		*/
		public LabelCellEditorLocator(final Label label) {
			this.label = label;
		}

		/**
		* 
		*/
		public Label getLabel() {
			return this.label;
		}

		/**
		* 
		*/
		public void relocate(final CellEditor celleditor) {
			final Text text = (Text) celleditor.getControl();
			final Rectangle rect = getLabel().getTextBounds().getCopy();
			getLabel().translateToAbsolute(rect);
			if (!text.getFont().isDisposed()) {
				final int avr = FigureUtilities.getFontMetrics(text.getFont()).getAverageCharWidth();
				rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT, SWT.DEFAULT)).expand(avr * 2, 0));
			}
			if (!rect.equals(new Rectangle(text.getBounds()))) {
				text.setBounds(rect.x, rect.y, rect.width, rect.height);
			}
		}
	}
}
