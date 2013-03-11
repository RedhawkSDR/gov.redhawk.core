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
package gov.redhawk.sca.internal.ui;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;

/**
 * 
 */
public enum ResourceRegistry {
	INSTANCE;

	private LocalResourceManager resourceManager;

	/**
	 * Return the resourceManager used by this plug-in.
	 * @return
	 */
	public ResourceManager getResourceManager() {
		if (this.resourceManager == null) {
			this.resourceManager = new LocalResourceManager(JFaceResources.getResources());
		}
		return this.resourceManager;
	}

	public void dispose() {
		if (this.resourceManager != null) {
			this.resourceManager.dispose();
			this.resourceManager = null;
		}
	}
}
