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
package gov.redhawk.core.graphiti.sad.ui.diagram.providers;

import org.eclipse.graphiti.tb.IToolBehaviorProvider;

public class WaveformExplorerDiagramTypeProvider extends SADDiagramTypeProvider {

	public static final String PROVIDER_ID = "gov.redhawk.core.graphiti.sad.ui.WaveformExplorerDiagramTypeProvider";

	private IToolBehaviorProvider[] toolBehaviorProviders;

	public WaveformExplorerDiagramTypeProvider() {
		super();
		setFeatureProvider(new WaveformExplorerFeatureProvider(this));
	}

	@Override
	public IToolBehaviorProvider[] getAvailableToolBehaviorProviders() {
		if (toolBehaviorProviders == null) {
			toolBehaviorProviders = new IToolBehaviorProvider[] { new WaveformExplorerToolBehaviorProvider(this) };
		}
		return toolBehaviorProviders;
	}
}
