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
package gov.redhawk.ui.views.namebrowser.internal.command;

import gov.redhawk.ui.views.namebrowser.view.BindingNode;

import org.eclipse.core.expressions.PropertyTester;

/**
 * 
 */
public class BindingNodeRepIDTester extends PropertyTester {

	/**
	 * 
	 */
	public BindingNodeRepIDTester() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object, java.lang.String, java.lang.Object[], java.lang.Object)
	 */
	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		if (receiver instanceof BindingNode) {
			BindingNode node = (BindingNode) receiver;
			if (args != null && args.length > 0) {
				for (Object obj : args) {
					if (obj instanceof String) {
						if (!node.is_a((String) obj)) {
							return false;
						}
					}
				}
				return true;
			}
		}
		return false;
	}

}
