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
package gov.redhawk.sca.model.provider.refresh.internal;

import org.eclipse.core.runtime.IProgressMonitor;

import gov.redhawk.model.sca.RefreshDepth;

/**
 * Abstracts a refresh operation for a target object.
 */
public interface IRefresher {

	/**
	 * Should perform a simple check to ensure the object appears to be refreshable (e.g. CORBA object exists). May
	 * block.
	 * @return True if refresh appears to be possible, false if there is a problem that will prevent refresh
	 */
	public boolean canRefresh();

	/**
	 * Perform a refresh of the target object
	 * @param monitor A progress monitor
	 */
	public void refresh(IProgressMonitor monitor);

	/**
	 * Perform a refresh of the target object
	 * @param monitor A progress monitor
	 */
	public void refresh(IProgressMonitor monitor, RefreshDepth depth);

}
