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
package gov.redhawk.sca.compatibility;

import java.io.File;

/**
 * @since 6.1
 */
public class CompatibilityUtil implements ICompatibilityUtil {

	
	/* (non-Javadoc)
	 * @see gov.redhawk.sca.compatibility.ICompatibilityUtil#getUserSpecificPath(java.lang.Object)
	 */
	@Override
	public String getUserSpecificPath(Object context) {
		return "";
	}

	/* (non-Javadoc)
	 * @see gov.redhawk.sca.compatibility.ICompatibilityUtil#initializeSettingStore(java.lang.Object)
	 */
	@Override
	public void initializeSettingStore(Object context) {
		throw new UnsupportedOperationException("This method is used in RAP only");
	}

	/* (non-Javadoc)
	 * @see gov.redhawk.sca.compatibility.ICompatibilityUtil#getSettingStoreWorkDir()
	 */
	@Override
	public File getSettingStoreWorkDir() {
		throw new UnsupportedOperationException("This method is used in RAP only");
	}

}
