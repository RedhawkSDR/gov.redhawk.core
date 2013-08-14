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

import BULKIO.updateSRIOperations;

public interface IBulkIOPortConnectionManager {

	/**
	 * Infer the BulkIO type based on the implemented interface of internalPort
	 * <b>WARNING</b> If internalPort implements more than one type of 
	 * BulkIO operation you MUST use {@link #connect(String, BulkIOType, updateSRIOperations)}
	 */
	void connect(String ior, updateSRIOperations internalPort) throws CoreException;

	void connect(String ior, BulkIOType type, updateSRIOperations internalPort) throws CoreException;

	void disconnect(String ior, updateSRIOperations internalPort);

	AbstractBulkIOPort getExternalPort(String ior);
}
