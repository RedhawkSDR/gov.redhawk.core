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
package gov.redhawk.core.graphiti.ui.modelmap;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.impl.DeleteContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.pattern.DeleteFeatureForPattern;
import org.eclipse.graphiti.pattern.IFeatureProviderWithPatterns;
import org.eclipse.graphiti.pattern.IPattern;

/**
 * Handles deleting the business model and graphical model objects from a diagram. Used for components/devices, as well
 * as connections.
 */
/* package */ class DeleteCommand extends RecordingCommand {

	private PictogramElement pictogramElement;
	private IFeatureProvider featureProvider;
	private boolean runtime;

	public DeleteCommand(TransactionalEditingDomain domain, PictogramElement pictogramElement, IFeatureProvider featureProvider, boolean runtime) {
		super(domain);
		this.pictogramElement = pictogramElement;
		this.featureProvider = featureProvider;
		this.runtime = runtime;
	}

	@Override
	public boolean canUndo() {
		if (runtime) {
			return false;
		}
		return super.canUndo();
	}

	@Override
	protected void doExecute() {
		// The IDeleteFeature from the IFeatureProvider only kicks off an async CORBA release for components/devices.
		// For those cases, we use a DeleteFeatureForPattern instead. This way we delete the diagram model object and
		// the diagram graphical objects.
		DeleteContext context = new DeleteContext(pictogramElement);
		IFeatureProviderWithPatterns fp = (IFeatureProviderWithPatterns) featureProvider;
		IPattern pattern = fp.getPatternForPictogramElement(pictogramElement);
		IDeleteFeature deleteFeature;
		if (pattern != null) {
			deleteFeature = new DeleteFeatureForPattern(featureProvider, pattern);
		} else {
			deleteFeature = featureProvider.getDeleteFeature(context);
		}
		deleteFeature.delete(context);
	}

}
