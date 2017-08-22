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
package gov.redhawk.sca.properties;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import gov.redhawk.sca.ScaPlugin;

/**
 * @since 5.0
 */
public class PropertiesProviderDescriptor implements IPropertiesProviderDescriptor {

	private static final String NAME_ATTRIBUTE = "name";
	private static final String CLASS_ATTRIBUTE = "class";
	private static final String ID_ATTRIBUTE = "id";

	private final IConfigurationElement element;
	private final String name;
	private final String id;

	public PropertiesProviderDescriptor(final IConfigurationElement element) {
		this.element = element;
		this.name = this.element.getAttribute(PropertiesProviderDescriptor.NAME_ATTRIBUTE);
		this.id = this.element.getAttribute(PropertiesProviderDescriptor.ID_ATTRIBUTE);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public IPropertiesProvider getProvider() {
		try {
			return (IPropertiesProvider) this.element.createExecutableExtension(PropertiesProviderDescriptor.CLASS_ATTRIBUTE);
		} catch (final CoreException e) {
			IStatus status = new Status(IStatus.ERROR, ScaPlugin.PLUGIN_ID, "Unable to create properties provider", e);
			ScaPlugin.getDefault().getLog().log(status);
			return null;
		}
	}

}
