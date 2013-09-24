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
// BEGIN GENERATED CODE

package gov.redhawk.diagram.edit.policies;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FigureListener;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;

/**
 * 
 * @since 3.0
 */
public class PartitioningTextSelectionEditPolicy extends SelectionEditPolicy {

	/**
	* 
	*/
	private IFigure selectionFeedbackFigure;

	/**
	* 
	*/
	private IFigure focusFeedbackFigure;

	/**
	* 
	*/
	private FigureListener hostPositionListener;

	/**
	* 
	*/
	@Override
	protected void showPrimarySelection() {
		if (getHostFigure() instanceof WrappingLabel) {
			((WrappingLabel) getHostFigure()).setSelected(true);
			((WrappingLabel) getHostFigure()).setFocus(true);
		} else {
			showSelection();
			showFocus();
		}
	}

	/**
	* 
	*/
	@Override
	protected void showSelection() {
		if (getHostFigure() instanceof WrappingLabel) {
			((WrappingLabel) getHostFigure()).setSelected(true);
			((WrappingLabel) getHostFigure()).setFocus(false);
		} else {
			hideSelection();
			addFeedback(this.selectionFeedbackFigure = createSelectionFeedbackFigure());
			getHostFigure().addFigureListener(getHostPositionListener());
			refreshSelectionFeedback();
			hideFocus();
		}
	}

	/**
	* 
	*/
	@Override
	protected void hideSelection() {
		if (getHostFigure() instanceof WrappingLabel) {
			((WrappingLabel) getHostFigure()).setSelected(false);
			((WrappingLabel) getHostFigure()).setFocus(false);
		} else {
			if (this.selectionFeedbackFigure != null) {
				removeFeedback(this.selectionFeedbackFigure);
				getHostFigure().removeFigureListener(getHostPositionListener());
				this.selectionFeedbackFigure = null;
			}
			hideFocus();
		}
	}

	/**
	* 
	*/
	@Override
	protected void showFocus() {
		if (getHostFigure() instanceof WrappingLabel) {
			((WrappingLabel) getHostFigure()).setFocus(true);
		} else {
			hideFocus();
			addFeedback(this.focusFeedbackFigure = createFocusFeedbackFigure());
			refreshFocusFeedback();
		}
	}

	/**
	* 
	*/
	@Override
	protected void hideFocus() {
		if (getHostFigure() instanceof WrappingLabel) {
			((WrappingLabel) getHostFigure()).setFocus(false);
		} else {
			if (this.focusFeedbackFigure != null) {
				removeFeedback(this.focusFeedbackFigure);
				this.focusFeedbackFigure = null;
			}
		}
	}

	/**
	* 
	*/
	protected Rectangle getFeedbackBounds() {
		Rectangle bounds;
		if (getHostFigure() instanceof Label) {
			bounds = ((Label) getHostFigure()).getTextBounds();
			bounds.intersect(getHostFigure().getBounds());
		} else {
			bounds = getHostFigure().getBounds().getCopy();
		}
		getHostFigure().getParent().translateToAbsolute(bounds);
		getFeedbackLayer().translateToRelative(bounds);
		return bounds;
	}

	/**
	* 
	*/
	protected IFigure createSelectionFeedbackFigure() {
		if (getHostFigure() instanceof Label) {
			final Label feedbackFigure = new Label();
			feedbackFigure.setOpaque(true);
			feedbackFigure.setBackgroundColor(ColorConstants.menuBackgroundSelected);
			feedbackFigure.setForegroundColor(ColorConstants.menuForegroundSelected);
			return feedbackFigure;
		} else {
			final RectangleFigure feedbackFigure = new RectangleFigure();
			feedbackFigure.setFill(false);
			return feedbackFigure;
		}
	}

	/**
	* 
	*/
	protected IFigure createFocusFeedbackFigure() {
		return new Figure() {

			@Override
			protected void paintFigure(final Graphics graphics) {
				graphics.drawFocus(getBounds().getResized(-1, -1));
			}
		};
	}

	/**
	* 
	*/
	protected void updateLabel(final Label target) {
		final Label source = (Label) getHostFigure();
		target.setText(source.getText());
		target.setTextAlignment(source.getTextAlignment());
		target.setFont(source.getFont());
	}

	/**
	* 
	*/
	protected void refreshSelectionFeedback() {
		if (this.selectionFeedbackFigure != null) {
			if (this.selectionFeedbackFigure instanceof Label) {
				updateLabel((Label) this.selectionFeedbackFigure);
				this.selectionFeedbackFigure.setBounds(getFeedbackBounds());
			} else {
				this.selectionFeedbackFigure.setBounds(getFeedbackBounds().expand(5, 5));
			}
		}
	}

	/**
	* 
	*/
	protected void refreshFocusFeedback() {
		if (this.focusFeedbackFigure != null) {
			this.focusFeedbackFigure.setBounds(getFeedbackBounds());
		}
	}

	/**
	* 
	*/
	public void refreshFeedback() {
		refreshSelectionFeedback();
		refreshFocusFeedback();
	}

	/**
	* 
	*/
	private FigureListener getHostPositionListener() {
		if (this.hostPositionListener == null) {
			this.hostPositionListener = new FigureListener() {
				@Override
				public void figureMoved(final IFigure source) {
					refreshFeedback();
				}
			};
		}
		return this.hostPositionListener;
	}
}
