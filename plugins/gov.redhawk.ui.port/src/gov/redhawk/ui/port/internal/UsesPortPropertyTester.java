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
package gov.redhawk.ui.port.internal;

import java.util.regex.Pattern;

import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.sca.util.PluginUtil;
import gov.redhawk.ui.port.Activator;

import org.eclipse.core.expressions.PropertyTester;

/**
 * 
 */
public class UsesPortPropertyTester extends PropertyTester {

	/* (non-Javadoc)
	 * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object, java.lang.String, java.lang.Object[], java.lang.Object)
	 */
	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		Activator.logError("Use the property tester gov.redhawk.sca.model.port (.interface) instead", null);
		ScaUsesPort port = PluginUtil.adapt(ScaUsesPort.class, receiver, true);
		if (port != null) {
			if ("interface".equals(property)) {
				if (expectedValue != null) {
					return Pattern.matches(expectedValue.toString(), port.getRepid());
				} else if (args.length > 0) {
					for (Object obj : args) {
						if (!obj.toString().equals(port.getRepid())) {
							return false;
						}
					}
					return true;
				}

			}
		}
		return false;
	}

}
