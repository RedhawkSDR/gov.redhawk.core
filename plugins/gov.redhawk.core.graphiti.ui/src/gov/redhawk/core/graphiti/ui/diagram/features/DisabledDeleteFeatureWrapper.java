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
package gov.redhawk.core.graphiti.ui.diagram.features;

import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IDeleteContext;

public class DisabledDeleteFeatureWrapper implements IDeleteFeature {

	private IDeleteFeature wrappedFeature;

	public DisabledDeleteFeatureWrapper(IDeleteFeature wrappedFeature) {
		this.wrappedFeature = wrappedFeature;
	}

	@Override
	public boolean isAvailable(IContext context) {
		return wrappedFeature.isAvailable(context);
	}

	@Override
	public boolean canExecute(IContext context) {
		return false;
	}

	@Override
	public void execute(IContext context) {
		wrappedFeature.execute(context);
	}

	@Override
	public boolean canUndo(IContext context) {
		return wrappedFeature.canUndo(context);
	}

	@Override
	public boolean hasDoneChanges() {
		return wrappedFeature.hasDoneChanges();
	}

	@Override
	public String getName() {
		return wrappedFeature.getName();
	}

	@Override
	public String getDescription() {
		return wrappedFeature.getDescription();
	}

	@Override
	public IFeatureProvider getFeatureProvider() {
		return wrappedFeature.getFeatureProvider();
	}

	@Override
	public boolean canDelete(IDeleteContext context) {
		return false;
	}

	@Override
	public void preDelete(IDeleteContext context) {
		wrappedFeature.preDelete(context);
	}

	@Override
	public boolean isDeleteAbort() {
		return wrappedFeature.isDeleteAbort();
	}

	@Override
	public void delete(IDeleteContext context) {
		wrappedFeature.delete(context);
	}

	@Override
	public void postDelete(IDeleteContext context) {
		wrappedFeature.postDelete(context);
	}
}
