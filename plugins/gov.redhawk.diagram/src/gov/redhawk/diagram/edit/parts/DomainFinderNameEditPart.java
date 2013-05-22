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
import gov.redhawk.diagram.factories.PartitioningEditPartFactory;
import gov.redhawk.diagram.part.PartitioningVisualIDRegistry;
import gov.redhawk.diagram.providers.PartitioningElementTypes;
import gov.redhawk.diagram.providers.PartitioningParserProviderHelper;

import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.LabelDirectEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramColorRegistry;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.diagram.ui.tools.TextDirectEditManager;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ISemanticParser;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;

/**
 * @since 3.0
 * 
 */
public class DomainFinderNameEditPart extends CompartmentEditPart implements ITextAwareEditPart {

	/**
	* 
	*/
	public static final int VISUAL_ID = 15014;

	/**
	* 
	*/
	private DirectEditManager manager;

	/**
	* 
	*/
	private IParser parser;

	/**
	* 
	*/
	private List< ? > parserElements;

	/**
	* 
	*/
	private String defaultText;

	private final PartitioningElementTypes elementTypes;

	private final PartitioningVisualIDRegistry visualIdRegistry;

	/**
	* 
	*/
	public DomainFinderNameEditPart(final View view, final PartitioningElementTypes elementTypes, final PartitioningVisualIDRegistry visualIdRegistry) {
		super(view);
		this.elementTypes = elementTypes;
		this.visualIdRegistry = visualIdRegistry;
	}

	/**
	* 
	*/
	@Override
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new PartitioningTextSelectionEditPolicy());
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new LabelDirectEditPolicy());
		installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, new NodeLabelDragPolicy());
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
		this.defaultText = getLabelTextHelper(figure);
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
		String text = null;
		final EObject parserElement = getParserElement();
		if (parserElement != null && getParser() != null) {
			text = getParser().getPrintString(new EObjectAdapter(parserElement), getParserOptions().intValue());
		}
		if (text == null || text.length() == 0) {
			text = this.defaultText;
		}
		return text;
	}

	/**
	* 
	*/
	public void setLabelText(final String text) {
		setLabelTextHelper(getFigure(), text);
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
	public String getEditText() {
		if (getParserElement() == null || getParser() == null) {
			return ""; //$NON-NLS-1$
		}
		return getParser().getEditString(new EObjectAdapter(getParserElement()), getParserOptions().intValue());
	}

	/**
	* 
	*/
	protected boolean isEditable() {
		return getParser() != null;
	}

	/**
	* 
	*/
	public ICellEditorValidator getEditTextValidator() {
		return new ICellEditorValidator() {

			public String isValid(final Object value) {
				if (value instanceof String) {
					final EObject element = getParserElement();
					final IParser localParser = getParser();
					try {
						final IParserEditStatus valid = (IParserEditStatus) getEditingDomain().runExclusive(new RunnableWithResult.Impl<IParserEditStatus>() {

							public void run() {
								setResult(localParser.isValidEditString(new EObjectAdapter(element), (String) value));
							}
						});
						return (valid.getCode() == IParserEditStatus.EDITABLE) ? null : valid.getMessage(); // SUPPRESS CHECKSTYLE Inline
					} catch (final InterruptedException ie) {
						// PASS
					}
				}

				// shouldn't get here
				return null;
			}
		};
	}

	/**
	* 
	*/
	public IContentAssistProcessor getCompletionProcessor() {
		if (getParserElement() == null || getParser() == null) {
			return null;
		}
		return getParser().getCompletionProcessor(new EObjectAdapter(getParserElement()));
	}

	/**
	* 
	*/
	public ParserOptions getParserOptions() {
		return ParserOptions.NONE;
	}

	/**
	* 
	*/
	public IParser getParser() {
		if (this.parser == null) {
			this.parser = PartitioningParserProviderHelper.getParser(PartitioningElementTypes.DomainFinder, getParserElement(),
			        this.visualIdRegistry.getType(DomainFinderNameEditPart.VISUAL_ID));
		}
		return this.parser;
	}

	/**
	* 
	*/
	@SuppressWarnings("deprecation")
	protected DirectEditManager getManager() {
		if (this.manager == null) {
			setManager(new TextDirectEditManager(this, TextDirectEditManager.getTextCellEditorClass(this),
			        PartitioningEditPartFactory.getTextCellEditorLocator(this)));
		}
		return this.manager;
	}

	/**
	* 
	*/
	protected void setManager(final DirectEditManager manager) {
		this.manager = manager;
	}

	/**
	* 
	*/
	protected void performDirectEdit() {
		getManager().show();
	}

	/**
	* 
	*/
	protected void performDirectEdit(final Point eventLocation) {
		if (getManager().getClass() == TextDirectEditManager.class) {
			((TextDirectEditManager) getManager()).show(eventLocation.getSWTPoint());
		}
	}

	/**
	* 
	*/
	private void performDirectEdit(final char initialCharacter) {
		if (getManager() instanceof TextDirectEditManager) {
			((TextDirectEditManager) getManager()).show(initialCharacter);
		} else {
			performDirectEdit();
		}
	}

	/**
	* 
	*/
	@Override
	protected void performDirectEditRequest(final Request request) {
		final Request theRequest = request;
		try {
			getEditingDomain().runExclusive(new Runnable() {

				public void run() {
					if (isActive() && isEditable()) {
						if (theRequest.getExtendedData().get(RequestConstants.REQ_DIRECTEDIT_EXTENDEDDATA_INITIAL_CHAR) instanceof Character) {
							final Character initialChar = (Character) theRequest.getExtendedData().get(
							        RequestConstants.REQ_DIRECTEDIT_EXTENDEDDATA_INITIAL_CHAR);
							performDirectEdit(initialChar.charValue());
						} else if ((theRequest instanceof DirectEditRequest) && (getEditText().equals(getLabelText()))) {
							final DirectEditRequest editRequest = (DirectEditRequest) theRequest;
							performDirectEdit(editRequest.getLocation());
						} else {
							performDirectEdit();
						}
					}
				}
			});
		} catch (final InterruptedException e) {
			// PASS
		}
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
	protected void addSemanticListeners() {
		if (getParser() instanceof ISemanticParser) {
			final EObject element = resolveSemanticElement();
			this.parserElements = ((ISemanticParser) getParser()).getSemanticElementsBeingParsed(element);
			for (int i = 0; i < this.parserElements.size(); i++) {
				addListenerFilter("SemanticModel" + i, this, (EObject) this.parserElements.get(i)); //$NON-NLS-1$
			}
		} else {
			super.addSemanticListeners();
		}
	}

	/**
	* 
	*/
	@Override
	protected void removeSemanticListeners() {
		if (this.parserElements != null) {
			for (int i = 0; i < this.parserElements.size(); i++) {
				removeListenerFilter("SemanticModel" + i); //$NON-NLS-1$
			}
		} else {
			super.removeSemanticListeners();
		}
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
		} else {
			if (getParser() != null && getParser().isAffectingEvent(event, getParserOptions().intValue())) {
				refreshLabel();
			}
			if (getParser() instanceof ISemanticParser) {
				final ISemanticParser modelParser = (ISemanticParser) getParser();
				if (modelParser.areSemanticElementsAffected(null, event)) {
					removeSemanticListeners();
					if (resolveSemanticElement() != null) {
						addSemanticListeners();
					}
					refreshLabel();
				}
			}
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
