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
import java.util.List;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IDirectEditingFeature;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.pattern.DefaultFeatureProviderWithPatterns;
import org.eclipse.graphiti.pattern.DirectEditingFeatureForPattern;
import org.eclipse.graphiti.pattern.IPattern;

import gov.redhawk.core.graphiti.ui.ext.RHContainerShape;
import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;

public abstract class AbstractGraphitiFeatureProvider extends DefaultFeatureProviderWithPatterns implements IHoverPadFeatureProvider {

	public AbstractGraphitiFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);

		// Add port patterns
		addPattern(new UsesPortPattern());
		addPattern(new ProvidesPortPattern());
	}

	@Override
	public ICustomFeature[] getContextButtonPadFeatures(CustomContext context) {
		// We only add to runtime diagrams
		Diagram diagram = getDiagramTypeProvider().getDiagram();
		if (!DUtil.isDiagramRuntime(diagram)) {
			return new ICustomFeature[0];
		}

		// Check the selection to make sure it's appropriate
		for (PictogramElement pe : context.getPictogramElements()) {
			if (!(pe instanceof RHContainerShape)) {
				return new ICustomFeature[0];
			}
			if (!(DUtil.getBusinessObject(pe) instanceof ComponentInstantiation)) {
				return new ICustomFeature[0];
			}
		}

		ICustomFeature[] features = new ICustomFeature[] { new StartFeature(this), new StopFeature(this), new ShowConsoleFeature(this) };
		return features;
	}

	/**
	 * Search for a non-null business object by traversing up a PictogramElement's parents.
	 * @param pictogramElement
	 * @return
	 */
	protected Object getNonNullBusinessObjectForPictogramElement(PictogramElement pictogramElement) {
		Object businessObject = getBusinessObjectForPictogramElement(pictogramElement);
		while (businessObject == null && pictogramElement != null) {
			pictogramElement = (PictogramElement) pictogramElement.eContainer();
			businessObject = getBusinessObjectForPictogramElement(pictogramElement);
		}
		return businessObject;
	}

	@Override
	public IDeleteFeature getDeleteFeature(IDeleteContext context) {
		// Check for shapes for which we don't want the user to have the delete capability,
		// including the diagram as a whole
		final PictogramElement pe = context.getPictogramElement();
		if (pe instanceof Diagram || pe instanceof FixPointAnchor) {
			return null;
		}

		// Use parent class logic, but disable the result if read-only
		IDeleteFeature deleteFeature = super.getDeleteFeature(context);
		final Diagram diagram = getDiagramTypeProvider().getDiagram();
		if (deleteFeature != null && DUtil.isDiagramReadOnly(diagram)) {
			deleteFeature = new DisabledDeleteFeatureWrapper(deleteFeature);
		}
		return deleteFeature;
	}

	@Override
	public IDirectEditingFeature getDirectEditingFeature(IDirectEditingContext context) {
		if (context == null) {
			throw new IllegalArgumentException("Argument context must not be null."); //$NON-NLS-1$
		}
		// For component/device/service shapes, the editable text is a child of the inner rectangle, which does not
		// have a business object link, so search for a reasonable parent.
		Object businessObject = getNonNullBusinessObjectForPictogramElement(context.getPictogramElement());
		IDirectEditingFeature ret = null;
		for (IPattern pattern : this.getPatterns()) {
			if (checkPattern(pattern, businessObject)) {
				IPattern chosenPattern = null;
				IDirectEditingFeature f = new DirectEditingFeatureForPattern(this, pattern);
				if (checkFeatureAndContext(f, context)) {
					if (ret == null) {
						ret = f;
						chosenPattern = pattern;
					} else {
						traceWarning("getDirectEditingFeature", pattern, chosenPattern); //$NON-NLS-1$
					}
				}
			}
		}

		if (ret == null) {
			ret = getDirectEditingFeatureAdditional(context);
		}

		return ret;
	}

	@Override
	public IFeature[] getDragAndDropFeatures(IPictogramElementContext context) {
		ICreateConnectionFeature[] connectionFeatures = getCreateConnectionFeatures();
		return connectionFeatures;
	}

	@Override
	public ICustomFeature[] getCustomFeatures(ICustomContext context) {
		List<ICustomFeature> features = new ArrayList<ICustomFeature>();

		PictogramElement[] pes = context.getPictogramElements();
		if (pes != null && pes.length == 1) {
			PictogramElement pictogramElement = pes[0];
			if (pictogramElement instanceof Diagram) {
				// Diagram features
				features.add(new LayoutDiagramFeature(this));
				features.add(new ExpandAllShapesFeature(this));
				features.add(new CollapseAllShapesFeature(this));
			} else if (pictogramElement instanceof RHContainerShape) {
				// Our standard shape features
				features.add(new ExpandShapeFeature(this));
				features.add(new CollapseShapeFeature(this));

				IPattern pattern = getPatternForPictogramElement(pictogramElement);
				if (pattern instanceof IDialogEditingPattern) {
					IDialogEditingPattern dialogEditing = (IDialogEditingPattern) pattern;
					if (dialogEditing.canDialogEdit(context)) {
						features.add(new DialogEditingFeatureForPattern(this, dialogEditing));
					}
				}
			}
		}

		return features.toArray(new ICustomFeature[features.size()]);
	}

}
