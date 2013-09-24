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

import org.eclipse.jdt.annotation.NonNull;

import BULKIO.dataCharOperations;
import BULKIO.dataDoubleOperations;
import BULKIO.dataFloatOperations;
import BULKIO.dataLongLongOperations;
import BULKIO.dataLongOperations;
import BULKIO.dataOctetOperations;
import BULKIO.dataShortOperations;
import BULKIO.dataUlongLongOperations;
import BULKIO.dataUlongOperations;
import BULKIO.dataUshortOperations;

/**
 * 
 */
public abstract class AbstractUberBulkIOPort extends AbstractBulkIOPort implements dataUshortOperations, dataCharOperations, dataDoubleOperations, dataFloatOperations,
		dataLongLongOperations, dataLongOperations, dataOctetOperations, dataShortOperations, dataUlongLongOperations, dataUlongOperations {

	public AbstractUberBulkIOPort() {
		super();
	}

	public AbstractUberBulkIOPort(@NonNull BulkIOType type) {
		super(type);
	}

}
