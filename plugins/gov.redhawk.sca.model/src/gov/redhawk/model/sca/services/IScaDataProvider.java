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
package gov.redhawk.model.sca.services;

import java.beans.PropertyChangeListener;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

/**
 * @since 9.0
 */
public interface IScaDataProvider {
	public static final String STATUS_PROPERTY = IScaDataProvider.class.getName() + ".status";
	/**
	 * @since 14.0
	 */
	public static final String DISPOSED_PROPERTY = IScaDataProvider.class.getName() + ".disposed";

	/**
	 * @since 14.0
	 */
	public static final String ENABLED_PROPERTY = IScaDataProvider.class.getName() + ".enabled";

	public void addPropertyChangeListener(PropertyChangeListener listener);

	public void removePropertyChangeListener(PropertyChangeListener listener);

	public IStatus getStatus();

	public IStatus refresh(IProgressMonitor monitor);

	public void dispose();

	/**
	 * @since 14.0
	 */
	public boolean isDisposed();

	/**
	 * @since 14.0
	 */
	public void setEnabled(boolean enabled);

	/**
	 * @since 14.0
	 */
	public boolean isEnabled();
	
	public String getID();
}
