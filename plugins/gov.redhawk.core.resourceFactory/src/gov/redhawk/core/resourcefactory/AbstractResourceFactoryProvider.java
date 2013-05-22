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
package gov.redhawk.core.resourcefactory;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;

public abstract class AbstractResourceFactoryProvider implements IResourceFactoryProvider, IExecutableExtension {
	private int priority;

	protected int getPriority() {
		return this.priority;
	}

	public void setInitializationData(final IConfigurationElement config, final String propertyName, final Object data) throws CoreException {
		try {
			this.priority = Integer.valueOf(config.getAttribute("priority"));
		} catch (final Exception e) {
			this.priority = Integer.MAX_VALUE;
		}

	}
}
