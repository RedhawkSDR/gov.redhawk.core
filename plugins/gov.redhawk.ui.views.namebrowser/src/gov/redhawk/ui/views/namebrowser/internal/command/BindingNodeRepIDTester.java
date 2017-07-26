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
import org.jacorb.orb.ORB;
import org.jacorb.orb.ParsedIOR;
import org.omg.CORBA.SystemException;

public class BindingNodeRepIDTester extends PropertyTester {

	public BindingNodeRepIDTester() {
	}

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		if (!(receiver instanceof BindingNode)) {
			return false;
		}
		BindingNode node = (BindingNode) receiver;

		// Parse the IOR
		ParsedIOR parsedIOR;
		try {
			parsedIOR = new ParsedIOR((ORB) node.getOrb(), node.getIOR());
		} catch (IllegalArgumentException | SystemException e) {
			return false;
		}
		String typeId = parsedIOR.getIOR().type_id;

		// If any arg matches, return true
		for (Object arg : args) {
			if (typeId.equals(arg)) {
				return true;
			}
		}

		return false;
	}
}
