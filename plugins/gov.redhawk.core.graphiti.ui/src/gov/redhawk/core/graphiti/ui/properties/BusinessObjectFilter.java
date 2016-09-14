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
package gov.redhawk.core.graphiti.ui.properties;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;

/**
 * Filter that accepts/rejects objects of certain class(es).
 */
public class BusinessObjectFilter extends AbstractPropertySectionFilter {

	private final List<Class< ? >> boFilterClasses;

	private boolean shouldAllow;

	/**
	 * Create a filter that accepts or rejects the specified classes
	 * @param allow Whether classes should should be accepted (true) or rejected (false)
	 * @param filterClasses The classes to filter on
	 */
	public BusinessObjectFilter(boolean allow, Class< ? >... filterClasses) {
		Assert.isTrue(filterClasses.length > 0, "Must specify at least one Business Object class to filter");
		this.boFilterClasses = Arrays.asList(filterClasses);
		this.shouldAllow = allow;
	}

	/**
	 * Create a filter that accepts any of the specified classes
	 * @param filterClass The classes to filter on
	 */
	public BusinessObjectFilter(Class< ? >... filterClass) {
		this(true, filterClass);
	}

	@Override
	protected boolean accept(PictogramElement pictogramElement) {
		EObject eObject = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pictogramElement);
		for (Class< ? > filterClass : this.boFilterClasses) {
			if (filterClass.isInstance(eObject)) {
				return this.shouldAllow;
			}
		}
		return !this.shouldAllow;
	}

	@Override
	public String toString() {
		boolean multipleBO = boFilterClasses.size() > 1;
		StringBuilder sb = new StringBuilder();
		if (shouldAllow) {
			sb.append("of type ");
		} else {
			sb.append("not of type ");
		}
		if (multipleBO) {
			sb.append("( ");
		}
		Iterator<Class< ? >> iter = boFilterClasses.iterator();
		while (iter.hasNext()) {
			sb.append(iter.next().getName());
			if (iter.hasNext()) {
				sb.append(" OR ");
			}
		}
		if (multipleBO) {
			sb.append(" )");
		}
		return sb.toString();
	}

}
