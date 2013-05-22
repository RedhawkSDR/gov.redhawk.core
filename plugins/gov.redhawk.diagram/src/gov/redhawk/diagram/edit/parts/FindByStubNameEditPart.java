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

import gov.redhawk.diagram.edit.policies.PartitioningTextSelectionEditPolicy;
import gov.redhawk.diagram.providers.PartitioningElementTypes;

import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramColorRegistry;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;

/**
 * @since 3.0
 * 
 */
public class FindByStubNameEditPart extends CompartmentEditPart {

	/**
	* 
	*/
	public static final int VISUAL_ID = 15018;

	private final PartitioningElementTypes elementTypes;

	/**
	* 
	*/
	public FindByStubNameEditPart(final View view, final PartitioningElementTypes elementTypes) {
		super(view);
		this.elementTypes = elementTypes;
	}

	/**
	* 
	*/
	@Override
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
	}

	/**
	* 
	*/
	protected String getLabelTextHelper(final IFigure figure) {
		if (figure instanceof WrappingLabel) {
			return ((WrappingLabel) figure).getText();
		} else {
			return ((Label) figure).getText();
		}
	}

	/**
	* 
	*/
	protected void setLabelTextHelper(final IFigure figure, final String text) {
		if (figure instanceof WrappingLabel) {
			((WrappingLabel) figure).setText(text);
		} else {
			((Label) figure).setText(text);
		}
	}

	/**
	* 
	*/
	protected Image getLabelIconHelper(final IFigure figure) {
		if (figure instanceof WrappingLabel) {
			return ((WrappingLabel) figure).getIcon();
		} else {
			return ((Label) figure).getIcon();
		}
	}

	/**
	* 
	*/
	protected void setLabelIconHelper(final IFigure figure, final Image icon) {
		if (figure instanceof WrappingLabel) {
			((WrappingLabel) figure).setIcon(icon);
		} else {
			((Label) figure).setIcon(icon);
		}
	}

	/**
	* 
	*/
	public void setLabel(final WrappingLabel figure) {
		unregisterVisuals();
		setFigure(figure);
		registerVisuals();
		refreshVisuals();
	}

	/**
	* 
	*/
	@Override
	@SuppressWarnings("rawtypes")
	protected List getModelChildren() {
		return Collections.EMPTY_LIST;
	}

	/**
	* 
	*/
	@Override
	public IGraphicalEditPart getChildBySemanticHint(final String semanticHint) {
		return null;
	}

	/**
	* 
	*/
	protected EObject getParserElement() {
		return resolveSemanticElement();
	}

	/**
	* 
	*/
	protected Image getLabelIcon() {
		final EObject parserElement = getParserElement();
		if (parserElement == null) {
			return null;
		}
		return this.elementTypes.getImage(parserElement.eClass());
	}

	/**
	* 
	*/
	protected String getLabelText() {
		return getLabelTextHelper(getFigure());
	}

	/**
	* 
	*/
	public void setLabelText(final String text) {
		setLabelTextHelper(getFigure(), text);
	}

	/**
	* 
	*/
	@Override
	protected void refreshVisuals() {
		super.refreshVisuals();
		refreshLabel();
		refreshFont();
		refreshFontColor();
		refreshUnderline();
		refreshStrikeThrough();
	}

	/**
	* 
	*/
	protected void refreshLabel() {
		setLabelTextHelper(getFigure(), getLabelText());
		setLabelIconHelper(getFigure(), getLabelIcon());
		final Object pdEditPolicy = getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
		if (pdEditPolicy instanceof PartitioningTextSelectionEditPolicy) {
			((PartitioningTextSelectionEditPolicy) pdEditPolicy).refreshFeedback();
		}
		final Object sfEditPolicy = getEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE);
		if (sfEditPolicy instanceof PartitioningTextSelectionEditPolicy) {
			((PartitioningTextSelectionEditPolicy) sfEditPolicy).refreshFeedback();
		}
	}

	/**
	* 
	*/
	protected void refreshUnderline() {
		final FontStyle style = (FontStyle) getFontStyleOwnerView().getStyle(NotationPackage.eINSTANCE.getFontStyle());
		if (style != null && getFigure() instanceof WrappingLabel) {
			((WrappingLabel) getFigure()).setTextUnderline(style.isUnderline());
		}
	}

	/**
	* 
	*/
	protected void refreshStrikeThrough() {
		final FontStyle style = (FontStyle) getFontStyleOwnerView().getStyle(NotationPackage.eINSTANCE.getFontStyle());
		if (style != null && getFigure() instanceof WrappingLabel) {
			((WrappingLabel) getFigure()).setTextStrikeThrough(style.isStrikeThrough());
		}
	}

	/**
	* 
	*/
	@Override
	protected void refreshFont() {
		final FontStyle style = (FontStyle) getFontStyleOwnerView().getStyle(NotationPackage.eINSTANCE.getFontStyle());
		if (style != null) {
			final FontData fontData = new FontData(style.getFontName(), style.getFontHeight(), (style.isBold() ? SWT.BOLD : SWT.NORMAL) // SUPPRESS CHECKSTYLE Inline
			        | (style.isItalic() ? SWT.ITALIC : SWT.NORMAL)); // SUPPRESS CHECKSTYLE Inline
			setFont(fontData);
		}
	}

	/**
	* 
	*/
	@Override
	protected void setFontColor(final Color color) {
		getFigure().setForegroundColor(color);
	}

	/**
	* 
	*/
	@Override
	protected AccessibleEditPart getAccessibleEditPart() {
		if (this.accessibleEP == null) {
			this.accessibleEP = new AccessibleGraphicalEditPart() {

				@Override
				public void getName(final AccessibleEvent e) {
					e.result = getLabelTextHelper(getFigure());
				}
			};
		}
		return this.accessibleEP;
	}

	/**
	* 
	*/
	private View getFontStyleOwnerView() {
		return getPrimaryView();
	}

	/**
	* 
	*/
	@Override
	protected void addNotationalListeners() {
		super.addNotationalListeners();
		addListenerFilter("PrimaryView", this, getPrimaryView()); //$NON-NLS-1$
	}

	/**
	* 
	*/
	@Override
	protected void removeNotationalListeners() {
		super.removeNotationalListeners();
		removeListenerFilter("PrimaryView"); //$NON-NLS-1$
	}

	/**
	* 
	*/
	@Override
	protected void handleNotificationEvent(final Notification event) {
		final Object feature = event.getFeature();
		if (NotationPackage.eINSTANCE.getFontStyle_FontColor().equals(feature)) {
			final Integer c = (Integer) event.getNewValue();
			setFontColor(DiagramColorRegistry.getInstance().getColor(c));
		} else if (NotationPackage.eINSTANCE.getFontStyle_Underline().equals(feature)) {
			refreshUnderline();
		} else if (NotationPackage.eINSTANCE.getFontStyle_StrikeThrough().equals(feature)) {
			refreshStrikeThrough();
		} else if (NotationPackage.eINSTANCE.getFontStyle_FontHeight().equals(feature) || NotationPackage.eINSTANCE.getFontStyle_FontName().equals(feature)
		        || NotationPackage.eINSTANCE.getFontStyle_Bold().equals(feature) || NotationPackage.eINSTANCE.getFontStyle_Italic().equals(feature)) {
			refreshFont();
		}
		super.handleNotificationEvent(event);
	}

	/**
	* 
	*/
	@Override
	protected IFigure createFigure() {
		// Parent should assign one using setLabel() method
		return null;
	}

}
