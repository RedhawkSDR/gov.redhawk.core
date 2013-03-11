/**
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.prf.internal.ui.editor;

import mil.jpeojtrs.sca.prf.AbstractProperty;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

public class AbstractPropertyViewerComparator extends ViewerComparator {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		if (e1 instanceof AbstractProperty && e2 instanceof AbstractProperty) {
			AbstractProperty prop1 = (AbstractProperty) e1;
			AbstractProperty prop2 = (AbstractProperty) e2;
			String name1 = prop1.getName();
			String name2 = prop2.getName();
			if (name1 == null) {
				name1 = prop1.getId();
			}
			if (name2 == null) {
				name2 = prop2.getId();
			}
			if (name1 == null || name2 == null) {
				return super.compare(viewer, e1, e2);
			}
			return name1.compareTo(name2);
		}
		return super.compare(viewer, e1, e2);
	}
}
