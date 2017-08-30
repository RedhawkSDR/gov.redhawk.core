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
package gov.redhawk.model.sca.util;

import CF.LifeCycleOperations;

/**
 * @since 20.0
 * @deprecated Moved to {@link gov.redhawk.sca.model.jobs.ReleaseJob}
 */
@Deprecated
public class ReleaseJob extends gov.redhawk.sca.model.jobs.ReleaseJob {

	public ReleaseJob(LifeCycleOperations obj) {
		super(obj);
	}

}
