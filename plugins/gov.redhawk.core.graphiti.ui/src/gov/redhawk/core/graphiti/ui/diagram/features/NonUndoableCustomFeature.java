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

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;

public abstract class NonUndoableCustomFeature extends AbstractCustomFeature {

	public NonUndoableCustomFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canUndo(IContext context) {
		return false;
	}
}
