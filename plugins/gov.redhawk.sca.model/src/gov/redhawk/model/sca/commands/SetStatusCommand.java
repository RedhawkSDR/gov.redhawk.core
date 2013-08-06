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
package gov.redhawk.model.sca.commands;

import gov.redhawk.model.sca.IStatusProvider;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * @since 14.0
 * 
 */
public class SetStatusCommand< T extends IStatusProvider > extends ScaModelCommand {

	protected final T provider; // SUPPRESS CHECKSTYLE Final Protected field
	protected final EStructuralFeature feature; // SUPPRESS CHECKSTYLE Final Protected field
	protected final IStatus status; // SUPPRESS CHECKSTYLE Final Protected field

	public SetStatusCommand(T provider, EStructuralFeature feature, IStatus status) {
		Assert.isNotNull(feature);
		Assert.isNotNull(provider);
		this.provider = provider;
		this.feature = feature;
		this.status = (status == null) ? Status.OK_STATUS : status;

	}

	/**
	 * {@inheritDoc}
	 */
	public void execute() {
		provider.setStatus(feature, status);
	}

}
