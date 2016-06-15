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
package gov.redhawk.sca.util;

import java.util.Comparator;

import org.eclipse.core.runtime.IPath;

/**
 * Compares two {@link IPath} objects, performing lexicographic comparison of each path segment.
 * @since 4.1
 */
public class PathComparator implements Comparator<IPath> {

	@Override
	public int compare(IPath o1, IPath o2) {
		// Handle equal or nulls
		if (o1 == o2) {
			return 0;
		}
		if (o1 == null) {
			return -1;
		}
		if (o2 == null) {
			return 1;
		}

		// Lengths by segments
		int len1 = o1.segmentCount();
		int len2 = o2.segmentCount();
		int lim = Math.min(len1, len2);

		// Compare each segment; tie breaker by number of segments
		for (int k = 0; k < lim; k++) {
			String segment1 = o1.segment(k);
			String segment2 = o2.segment(k);
			int compare = segment1.compareTo(segment2);
			if (compare != 0) {
				return compare;
			}
		}
		return len1 - len2;
	}

}
