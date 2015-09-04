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
package gov.redhawk.sca.model.internal;

import gov.redhawk.model.sca.IScaDataProviderServiceDescriptor;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.services.IScaDataProviderService;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

/**
 *
 * 
 */
public class ScaDataProviderServiceDescriptor implements IScaDataProviderServiceDescriptor {

	private static final String NAME_ATTRIBUTE = "name";
	private static final String CLASS_ATTRIBUTE = "class";
	private static final String ID_ATTRIBUTE = "id";

	private final IConfigurationElement element;
	private final String name;
	private final String id;
	private IScaDataProviderService service;
	private boolean enabled = true;

	public ScaDataProviderServiceDescriptor(final IConfigurationElement element, String preferenceValue) {
		this.element = element;
		this.name = this.element.getAttribute(ScaDataProviderServiceDescriptor.NAME_ATTRIBUTE);
		this.id = this.element.getAttribute(ScaDataProviderServiceDescriptor.ID_ATTRIBUTE);
		this.enabled = !(preferenceValue.contains(id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getId() {
		return this.id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IScaDataProviderService getService() {
		if (this.service == null) {
			try {
				this.service = (IScaDataProviderService) this.element.createExecutableExtension(ScaDataProviderServiceDescriptor.CLASS_ATTRIBUTE);
				this.service.setEnabled(enabled);
			} catch (final CoreException e) {
				ScaModelPlugin.logError("Error creating Data Provider Service: " + this.name, e);
			}
		}
		return this.service;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		if (this.service != null) {
			this.service.setEnabled(enabled);
		}
	}

}
