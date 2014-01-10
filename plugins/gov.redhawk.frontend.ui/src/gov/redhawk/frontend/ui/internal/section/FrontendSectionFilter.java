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
package gov.redhawk.frontend.ui.internal.section;

import gov.redhawk.frontend.Tuner;

import org.eclipse.jface.viewers.IFilter;

public class FrontendSectionFilter implements IFilter {

	@Override
	public boolean select(Object toTest) {
		if(toTest instanceof Tuner) {
			return true;
		}
		return false;
	}

}
