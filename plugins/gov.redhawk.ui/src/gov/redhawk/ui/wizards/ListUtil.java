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
/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package gov.redhawk.ui.wizards;

import java.util.Comparator;

import org.eclipse.jface.util.Policy;
import org.eclipse.jface.viewers.IBasicPropertyConstants;
import org.eclipse.jface.viewers.ViewerComparator;

/**
 * The Class ListUtil.
 * 
 * @since 2.0
 */
public final class ListUtil {

	/** The Constant NAME_COMPARATOR. */
	public static final ViewerComparator NAME_COMPARATOR = new NameComparator();

	private static final Comparator<Object> STRING_CCOMPARATOR = new Comparator<Object>() {

		@Override
		@SuppressWarnings("unchecked")
		public int compare(final Object arg0, final Object arg1) {
			if (arg0 instanceof String && arg1 instanceof String) {
				return ((String) arg0).compareToIgnoreCase((String) arg1);
			}
			// if not two Strings like we expect, then use default comparator
			return Policy.getComparator().compare(arg0, arg1);
		}

	};

	/**
	 * The Class NameComparator.
	 */
	static class NameComparator extends ViewerComparator {

		/**
		 * Instantiates a new name comparator.
		 */
		public NameComparator() {
			// when comparing names, always use the comparator above to do a
			// String comparison
			super(ListUtil.STRING_CCOMPARATOR);
		}

		/**
		 * Checks if is sorter property.
		 * 
		 * @param element the element
		 * @param propertyId the property id
		 * @return true, if is sorter property
		 */
		public boolean isSorterProperty(final Object element, final Object propertyId) {
			return propertyId.equals(IBasicPropertyConstants.P_TEXT);
		}
	}

	/**
	 * Instantiates a new list util.
	 */
	private ListUtil() {
		super();
	}
}
