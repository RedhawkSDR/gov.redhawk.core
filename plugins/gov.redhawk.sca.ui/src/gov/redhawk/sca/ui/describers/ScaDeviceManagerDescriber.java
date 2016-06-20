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
package gov.redhawk.sca.ui.describers;

import java.io.IOException;

import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.sca.ui.editors.IScaContentDescriber;

public class ScaDeviceManagerDescriber extends AbstractScaContentDescriber {

	public int describe(Object contents) throws IOException {
		return (contents instanceof ScaDeviceManager) ? IScaContentDescriber.VALID : IScaContentDescriber.INVALID;
	}

}
