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
package gov.redhawk.diagram.factories;

import gov.redhawk.diagram.edit.parts.DomainFinderEditPart;
import gov.redhawk.diagram.edit.parts.DomainFinderNameEditPart;
import gov.redhawk.diagram.edit.parts.DomainFinderTypeEditPart;
import gov.redhawk.diagram.edit.parts.FindByStubCompartmentEditPart;
import gov.redhawk.diagram.edit.parts.FindByStubEditPart;
import gov.redhawk.diagram.edit.parts.FindByStubNameEditPart;
import gov.redhawk.diagram.edit.parts.NamingServiceEditPart;
import gov.redhawk.diagram.edit.parts.NamingServiceNameEditPart;
import gov.redhawk.diagram.part.PartitioningVisualIDRegistry;
import gov.redhawk.diagram.part.PartitioningVisualIDRegistry.MAPPING_ID;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.core.view.factories.ViewFactory;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.notation.DecorationNode;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.gmf.runtime.notation.RoutingStyle;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.Smoothness;
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.gmf.runtime.notation.TitleStyle;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;

/**
 * @since 3.0
 * 
 */
public class PartitioningViewFactory implements ViewFactory {

	private final PartitioningVisualIDRegistry visualIdRegistry;
	private final IPartitioningViewFactory basicViewProvider;

	public PartitioningViewFactory(final PartitioningVisualIDRegistry visualIdRegistry, final IPartitioningViewFactory basicViewProvider) {
		this.visualIdRegistry = visualIdRegistry;
		this.basicViewProvider = basicViewProvider;
	}

	public View createView(final IAdaptable semanticAdapter, final View containerView, final String semanticHint, final int index, final boolean persisted,
	        final PreferencesHint preferencesHint) {
		final int visualId = this.visualIdRegistry.getVisualID(semanticHint);
		final EObject domainElement = (EObject) semanticAdapter.getAdapter(EObject.class);
		switch (visualId) {
		case FindByStubEditPart.VISUAL_ID:
			return createFindByStub(domainElement, containerView, index, persisted, preferencesHint);
		case DomainFinderEditPart.VISUAL_ID:
			return createDomainFinder(domainElement, containerView, index, persisted, preferencesHint);
		case NamingServiceEditPart.VISUAL_ID:
			return createNamingService(domainElement, containerView, index, persisted, preferencesHint);
		default:
			final MAPPING_ID mappingId = this.visualIdRegistry.getMappingID(visualId);
			if (mappingId != null) {
				switch (mappingId) {
				case ConnectInterfaceEditPart:
					return createConnectInterface(domainElement, containerView, index, persisted, preferencesHint);
				case ComponentPlacementEditPart:
					return this.basicViewProvider.basicCreateComponentPlacement(domainElement, containerView, index, persisted, preferencesHint);
				case ComponentSupportedInterfaceStubEditPart:
					return this.basicViewProvider.basicCreateComponentSupportedInterfaceStub(domainElement, containerView, index, persisted, preferencesHint);
				case ProvidesPortStubEditPart:
					return this.basicViewProvider.basicCreateProvidesPortStub(domainElement, containerView, index, persisted, preferencesHint);
				case UsesPortStubEditPart:
					return this.basicViewProvider.basicCreateUsesPortStub(domainElement, containerView, index, persisted, preferencesHint);
				default:
					break;
				}
			}
		}
		return null;
	}

	public Edge createConnectInterface(final EObject domainElement, final View containerView, final int index, final boolean persisted, // SUPPRESS CHECKSTYLE methodName
	        final PreferencesHint preferencesHint) {
		final Edge retVal = this.basicViewProvider.basicCreateConnectInterface(domainElement, containerView, index, persisted, preferencesHint);
		final Style style = retVal.getStyle(NotationPackage.eINSTANCE.getRoutingStyle());
		if (style instanceof RoutingStyle) {
			final RoutingStyle routingStyle = (RoutingStyle) style;
			routingStyle.setAvoidObstructions(true);
			routingStyle.setRouting(Routing.RECTILINEAR_LITERAL);
			routingStyle.setSmoothness(Smoothness.NONE_LITERAL);
		}
		return retVal;
	}

	public Node createFindByStub(final EObject domainElement, final View containerView, final int index, final boolean persisted,
	        final PreferencesHint preferencesHint) {
		final Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(this.visualIdRegistry.getType(FindByStubEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		stampShortcut(containerView, node);
		// initializeFromPreferences 
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		final org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_LINE_COLOR);
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(lineRGB));
		final FontStyle nodeFontStyle = (FontStyle) node.getStyle(NotationPackage.Literals.FONT_STYLE);
		if (nodeFontStyle != null) {
			final FontData fontData = PreferenceConverter.getFontData(prefStore, IPreferenceConstants.PREF_DEFAULT_FONT);
			nodeFontStyle.setFontName(fontData.getName());
			nodeFontStyle.setFontHeight(fontData.getHeight());
			nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
			nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
			final org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
			nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
		}
		final org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FILL_COLOR);
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillRGB));
		createCompartment(node, this.visualIdRegistry.getType(FindByStubCompartmentEditPart.VISUAL_ID), false, false, true, true);
		createLabel(node, this.visualIdRegistry.getType(FindByStubNameEditPart.VISUAL_ID));
		return node;
	}

	public Node createNamingService(final EObject domainElement, final View containerView, final int index, final boolean persisted,
	        final PreferencesHint preferencesHint) {
		final Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(this.visualIdRegistry.getType(NamingServiceEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		// initializeFromPreferences 
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		final org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_LINE_COLOR);
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(lineRGB));
		final FontStyle nodeFontStyle = (FontStyle) node.getStyle(NotationPackage.Literals.FONT_STYLE);
		if (nodeFontStyle != null) {
			final FontData fontData = PreferenceConverter.getFontData(prefStore, IPreferenceConstants.PREF_DEFAULT_FONT);
			nodeFontStyle.setFontName(fontData.getName());
			nodeFontStyle.setFontHeight(fontData.getHeight());
			nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
			nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
			final org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
			nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
		}
		final org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FILL_COLOR);
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillRGB));
		final Node label5013 = createLabel(node, this.visualIdRegistry.getType(NamingServiceNameEditPart.VISUAL_ID));
		return node;
	}

	public Node createDomainFinder(final EObject domainElement, final View containerView, final int index, final boolean persisted,
	        final PreferencesHint preferencesHint) {
		final Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(this.visualIdRegistry.getType(DomainFinderEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		// initializeFromPreferences 
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		final org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_LINE_COLOR);
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(lineRGB));
		final FontStyle nodeFontStyle = (FontStyle) node.getStyle(NotationPackage.Literals.FONT_STYLE);
		if (nodeFontStyle != null) {
			final FontData fontData = PreferenceConverter.getFontData(prefStore, IPreferenceConstants.PREF_DEFAULT_FONT);
			nodeFontStyle.setFontName(fontData.getName());
			nodeFontStyle.setFontHeight(fontData.getHeight());
			nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
			nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
			final org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
			nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
		}
		final org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FILL_COLOR);
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillRGB));
		final Node label5014 = createLabel(node, this.visualIdRegistry.getType(DomainFinderNameEditPart.VISUAL_ID));
		final Node label5015 = createLabel(node, this.visualIdRegistry.getType(DomainFinderTypeEditPart.VISUAL_ID));
		return node;
	}

	private Node createCompartment(final View owner, final String hint, final boolean canCollapse, final boolean hasTitle, final boolean canSort,
	        final boolean canFilter) {
		//SemanticListCompartment rv = NotationFactory.eINSTANCE.createSemanticListCompartment();
		//rv.setShowTitle(showTitle);
		//rv.setCollapsed(isCollapsed);
		Node rv;
		if (canCollapse) {
			rv = NotationFactory.eINSTANCE.createBasicCompartment();
		} else {
			rv = NotationFactory.eINSTANCE.createDecorationNode();
		}
		if (hasTitle) {
			final TitleStyle ts = NotationFactory.eINSTANCE.createTitleStyle();
			ts.setShowTitle(true);
			rv.getStyles().add(ts);
		}
		if (canSort) {
			rv.getStyles().add(NotationFactory.eINSTANCE.createSortingStyle());
		}
		if (canFilter) {
			rv.getStyles().add(NotationFactory.eINSTANCE.createFilteringStyle());
		}
		rv.setType(hint);
		ViewUtil.insertChildView(owner, rv, ViewUtil.APPEND, true);
		return rv;
	}

	private Node createLabel(final View owner, final String hint) {
		final DecorationNode rv = NotationFactory.eINSTANCE.createDecorationNode();
		rv.setType(hint);
		ViewUtil.insertChildView(owner, rv, ViewUtil.APPEND, true);
		return rv;
	}

	private void stampShortcut(final View containerView, final Node target) {
		if (!this.visualIdRegistry.getModelID().equals(this.visualIdRegistry.getModelID(containerView))) {
			final EAnnotation shortcutAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
			shortcutAnnotation.setSource("Shortcut"); //$NON-NLS-1$
			shortcutAnnotation.getDetails().put("modelID", this.visualIdRegistry.getModelID()); //$NON-NLS-1$
			target.getEAnnotations().add(shortcutAnnotation);
		}
	}

}
