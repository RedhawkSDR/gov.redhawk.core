/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.bulkio.util;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.annotation.NonNull;

import BULKIO.updateSRIOperations;

/**
 * @noimplement This interface is not intended to be implemented by clients.
 */
public interface IBulkIOPortConnectionManager {

	void connect(@NonNull String ior, @NonNull BulkIOType type, @NonNull updateSRIOperations internalPort) throws CoreException;

	void disconnect(@NonNull String ior, @NonNull BulkIOType type, @NonNull updateSRIOperations internalPort);

	AbstractBulkIOPort getExternalPort(@NonNull String ior, @NonNull BulkIOType type);
}
