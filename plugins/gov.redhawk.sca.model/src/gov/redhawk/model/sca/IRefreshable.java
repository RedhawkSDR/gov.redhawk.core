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

package gov.redhawk.model.sca;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * An object who's local state is a cached state of some remote value and thus should be refreshed
 * @since 14.0
 */
public interface IRefreshable {

	/**
	 * Refresh the state of the Model Object.  This is a blocking call.
	 * @param monitor Monitor to report status to, may be null.
	 * @param depth The depth to refresh
	 * @throws InterruptedException if the current thread is interrupted while
	 *    waiting for access to the resource set
	 */
	void refresh(IProgressMonitor monitor, RefreshDepth depth) throws InterruptedException;

}
