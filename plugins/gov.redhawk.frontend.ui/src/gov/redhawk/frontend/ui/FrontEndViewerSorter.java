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
package gov.redhawk.frontend.ui;

import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.UnallocatedTunerContainer;
import gov.redhawk.sca.ui.ScaViewerSorter;

import java.text.Collator;

import org.eclipse.jface.viewers.Viewer;

public class FrontEndViewerSorter extends ScaViewerSorter {

	/**
	 * 
	 */
	public FrontEndViewerSorter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param collator
	 */
	public FrontEndViewerSorter(Collator collator) {
		super(collator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		if (e1 instanceof TunerStatus) {
			if (e2 instanceof UnallocatedTunerContainer) {
				return -1;
			}
			TunerStatus t1 = (TunerStatus) e1;
			String id1 = t1.getAllocationID();
			if (e2 instanceof TunerStatus) {
				TunerStatus t2 = (TunerStatus) e2;
				String id2 = t2.getAllocationID();
				if (id1 == null || "".equals(id1) && !(id2 == null || "".equals(id2))) {
					return 1;
				}
				if (id2 == null || "".equals(id2) && !(id1 == null || "".equals(id1))) {
					return -1;
				}
			}
		}
		if (e1 instanceof UnallocatedTunerContainer) {
			if (e2 instanceof TunerStatus) {
				return 1;
			}
		}
		return super.compare(viewer, e1, e2);
	}
}
