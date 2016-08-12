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

import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;

public interface IHoverPadFeatureProvider {

	/**
	 * Gets a list of custom features that uniquely apply to the hover pad (but not the context menu, etc).
	 * @param context
	 * @return
	 */
	public ICustomFeature[] getContextButtonPadFeatures(CustomContext context);

}
