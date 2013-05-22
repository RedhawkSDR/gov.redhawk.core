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
package gov.redhawk.sca.ui;

import gov.redhawk.sca.ScaPlugin;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * @since 7.0
 * 
 */
public class NoEditorAvailableException extends CoreException {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;

	public NoEditorAvailableException(IStatus status) {
	    super(status);
	    // TODO Auto-generated constructor stub
    }

	public NoEditorAvailableException(String message, Throwable nestedException) {
	    super(new Status(Status.ERROR, ScaPlugin.PLUGIN_ID, message, nestedException));
	    // TODO Auto-generated constructor stub
    }

	public NoEditorAvailableException(String message) {
	    super(new Status(Status.ERROR, ScaPlugin.PLUGIN_ID, message));
	    // TODO Auto-generated constructor stub
    }

}
