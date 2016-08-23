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
package gov.redhawk.core.graphiti.ui.diagram.providers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.features.context.impl.LayoutContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.palette.IPaletteCompartmentEntry;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.tb.ContextButtonEntry;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IColorDecorator;
import org.eclipse.graphiti.tb.IContextButtonPadData;
import org.eclipse.graphiti.tb.IDecorator;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.swt.graphics.Color;

import gov.redhawk.core.graphiti.ui.ext.RHContainerShape;
import gov.redhawk.core.graphiti.ui.util.DUtil;
import mil.jpeojtrs.sca.partitioning.ComponentSupportedInterfaceStub;
import mil.jpeojtrs.sca.partitioning.ProvidesPortStub;
import mil.jpeojtrs.sca.partitioning.UsesPortStub;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

/**
 * Provides tool behavior common to (almost) all Graphiti diagrams.
 */
public abstract class AbstractToolBehaviorProvider extends DefaultToolBehaviorProvider {

	private List<IDecoratorProvider> decoratorProviders = new ArrayList<IDecoratorProvider>();
	private List<IToolTipDelegate> tooltipDelegates = new ArrayList<IToolTipDelegate>();
	private ConnectionHighlightingDecoratorProvider connectionHighlighter = new ConnectionHighlightingDecoratorProvider();
	private PortMonitorDecoratorProvider portMonitor;

	public AbstractToolBehaviorProvider(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
		if (DUtil.isDiagramRuntime(diagramTypeProvider.getDiagram())) {
			portMonitor = new PortMonitorDecoratorProvider(diagramTypeProvider);
			addDecoratorProvider(portMonitor);
		}
		addDecoratorProvider(connectionHighlighter);
	}

	@Override
	public void dispose() {
		connectionHighlighter.dispose();
		if (portMonitor != null) {
			portMonitor.dispose();
		}
		super.dispose();
	}

	@Override
	public boolean isShowFlyoutPalette() {
		// This class does not provide a palette
		return false;
	}

	/**
	 * Turn on highlighting of potential connection endpoints that are compatible with source.
	 * @param source the starting Anchor of the connection
	 */
	public void startConnectionHighlighting(Anchor source) {
		connectionHighlighter.startHighlighting(source);
		if (portMonitor != null) {
			portMonitor.setEnabled(false);
		}
		getDiagramTypeProvider().getDiagramBehavior().refreshContent();
	}

	/**
	 * Turn off highlighting of potential connection endpoints.
	 */
	public void endConnectionHighlighting() {
		connectionHighlighter.endHighlighting();
		if (portMonitor != null) {
			portMonitor.setEnabled(true);
		}
		getDiagramTypeProvider().getDiagramBehavior().refreshContent();
	}

	public List<IDecoratorProvider> getDecoratorProviders() {
		return decoratorProviders;
	}

	public void addDecoratorProvider(IDecoratorProvider provider) {
		getDecoratorProviders().add(provider);
	}

	public void removeDecoratorProvider(IDecoratorProvider provider) {
		if (decoratorProviders != null) {
			decoratorProviders.remove(provider);
		}
	}

	@Override
	public IDecorator[] getDecorators(PictogramElement pe) {
		List<IDecorator> decorators = new ArrayList<IDecorator>();
		for (IDecoratorProvider provider : getDecoratorProviders()) {
			decorators.addAll(Arrays.asList(provider.getDecorators(pe)));
		}
		if (pe instanceof Connection) {
			applyConnectionDecorators((Connection) pe, decorators);
		}
		return decorators.toArray(new IDecorator[decorators.size()]);
	}

	private void applyConnectionDecorators(Connection connection, List<IDecorator> decorators) {
		DiagramBehavior diagramBehavior = (DiagramBehavior) getDiagramTypeProvider().getDiagramBehavior();
		GraphicalViewer viewer = diagramBehavior.getDiagramContainer().getGraphicalViewer();
		GraphicalEditPart part = (GraphicalEditPart) viewer.getEditPartRegistry().get(connection);

		Color foreground = null;
		Color background = null;
		for (IDecorator decorator : decorators) {
			if (decorator instanceof IColorDecorator) {
				IColorDecorator colorDecorator = (IColorDecorator) decorator;
				if (colorDecorator.getForegroundColor() != null) {
					foreground = getSwtColor(colorDecorator.getForegroundColor());
				}
				if (colorDecorator.getBackgroundColor() != null) {
					background = getSwtColor(colorDecorator.getBackgroundColor());
				}
			}
		}

		// Assume that there's a 1:1 mapping between figure children and connection decorators, as there does not
		// appear to be any other way to reconcile the two
		List< ? > children = part.getFigure().getChildren();
		for (int index = 0; index < children.size(); index++) {
			ConnectionDecorator connectionDecorator = connection.getConnectionDecorators().get(index);
			if (!connectionDecorator.isActive()) {
				IFigure figure = (IFigure) children.get(index);
				refreshColors(figure, connectionDecorator.getGraphicsAlgorithm(), foreground, background);
			}
		}
	}

	private void refreshColors(IFigure figure, GraphicsAlgorithm ga, Color foreground, Color background) {
		if (foreground == null) {
			foreground = getSwtColor(Graphiti.getGaService().getForegroundColor(ga, true));
		}
		if (background == null) {
			background = getSwtColor(Graphiti.getGaService().getBackgroundColor(ga, true));
		}
		figure.setForegroundColor(foreground);
		figure.setBackgroundColor(background);
	}

	private Color getSwtColor(org.eclipse.graphiti.mm.algorithms.styles.Color color) {
		return new Color(null, color.getRed(), color.getGreen(), color.getBlue());
	}

	private Color getSwtColor(IColorConstant constant) {
		return new Color(null, constant.getRed(), constant.getGreen(), constant.getBlue());
	}

	public List<IToolTipDelegate> getToolTipDelegates() {
		return tooltipDelegates;
	}

	public void addToolTipDelegate(IToolTipDelegate provider) {
		tooltipDelegates.add(provider);
	}

	public void removeToolTipDelegate(IToolTipDelegate provider) {
		tooltipDelegates.remove(provider);
	}

	@Override
	public Object getToolTip(GraphicsAlgorithm ga) {
		for (IToolTipDelegate delegate : getToolTipDelegates()) {
			Object tooltip = delegate.getToolTip(ga);
			if (tooltip != null) {
				return tooltip;
			}
		}
		return null;
	}

	@Override
	public boolean equalsBusinessObjects(Object o1, Object o2) {
		// Some of our model objects need their parent objects checked as well to determine equality
		if ((o1 instanceof ProvidesPortStub && o2 instanceof ProvidesPortStub) || (o1 instanceof UsesPortStub && o2 instanceof UsesPortStub)
			|| (o1 instanceof ComponentSupportedInterfaceStub && o2 instanceof ComponentSupportedInterfaceStub)) {
			EObject eObject1 = (EObject) o1;
			EObject eObject2 = (EObject) o2;
			if (!EcoreUtil.equals(eObject1, eObject2)) {
				return false;
			}
			return EcoreUtil.equals(eObject1.eContainer(), eObject2.eContainer());
		}

		// Everything else can be handled by the base class
		return super.equalsBusinessObjects(o1, o2);
	}

	/**
	 * {@inheritDoc}
	 * <p/>
	 * Adds start/stop/etc. buttons to hover context button pad of component as applicable.
	 */
	@Override
	public IContextButtonPadData getContextButtonPad(IPictogramElementContext context) {
		// Allow button pad to appear when cursor is anywhere inside the ComponentShape
		PictogramElement pe = context.getPictogramElement();
		if (pe instanceof Shape) {
			while (!(pe instanceof RHContainerShape || pe == null)) {
				pe = (PictogramElement) pe.eContainer();
			}
			if (pe == null) {
				return null;
			}
		}
		context = new LayoutContext(pe);
		IContextButtonPadData pad = super.getContextButtonPad(context);

		// Check the feature provider's custom features and add anything that can run and has an icon
		CustomContext customContext = new CustomContext(new PictogramElement[] { pe });
		IFeatureProvider featureProvider = getFeatureProvider();
		ICustomFeature[] customFeatures = featureProvider.getCustomFeatures(customContext);
		for (ICustomFeature feature : customFeatures) {
			if (feature.getImageId() != null && feature.isAvailable(customContext) && feature.canExecute(customContext)) {
				pad.getDomainSpecificContextButtons().add(new ContextButtonEntry(feature, customContext));
			}
		}

		// Add any hover-specific features from the feature provider that can run
		if (featureProvider instanceof IHoverPadFeatureProvider) {
			IHoverPadFeatureProvider hoverFeatureProvider = (IHoverPadFeatureProvider) featureProvider;
			ICustomFeature[] hoverFeatures = hoverFeatureProvider.getContextButtonPadFeatures(customContext);
			for (ICustomFeature hoverFeature : hoverFeatures) {
				if (hoverFeature.isAvailable(customContext) && hoverFeature.canExecute(customContext)) {
					pad.getDomainSpecificContextButtons().add(new ContextButtonEntry(hoverFeature, customContext));
				}
			}
		}

		return pad;
	}

	@Override
	public IPaletteCompartmentEntry[] getPalette() {
		// This class does not provide a palette
		return new IPaletteCompartmentEntry[0];
	}

	@Override
	public PictogramElement getSelection(PictogramElement originalPe, PictogramElement[] oldSelection) {
		// Disable selection for PictogramElements that contain certain property values
		// Select ports directly (including super ports)
		if (originalPe instanceof Anchor) {
			Object bo = DUtil.getBusinessObject(originalPe);
			if (bo instanceof UsesPortStub || bo instanceof ProvidesPortStub) {
				return null;
			}
		}

		// Select the container shape itself
		if (originalPe instanceof RHContainerShape) {
			return null;
		}

		// Otherwise, always select outer container instead of its contents
		return ScaEcoreUtils.getEContainerOfType(originalPe, RHContainerShape.class);
	}
}
