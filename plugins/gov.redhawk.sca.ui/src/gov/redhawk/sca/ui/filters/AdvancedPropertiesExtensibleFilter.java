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
package gov.redhawk.sca.ui.filters;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IFilter;

/**
 * @since 9.3
 *
 */
public class AdvancedPropertiesExtensibleFilter implements IFilter {

	private static List<IFilter> subFilters = new ArrayList<IFilter>();
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
	 */
	@Override
	public boolean select(Object toTest) {
		for (IFilter subFilter: subFilters) {
			if (!subFilter.select(toTest)) {
				return false;
			}
		}
		return true;
	}

	public static void addSubFilter(IFilter subFilter) {
		subFilters.add(subFilter);
	}
}
