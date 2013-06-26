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

import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaDeviceManager;

import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * @since 9.1
 */
public class DeviceManagerChildDevicesFilter extends ViewerFilter {

	public DeviceManagerChildDevicesFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
		Object realParent = parentElement;
		if (parentElement instanceof TreePath) {
			realParent = ((TreePath) parentElement).getLastSegment();
		}
		if ((element instanceof ScaDevice< ? >) && (realParent instanceof ScaDeviceManager)) {
			final ScaDevice< ? > device = (ScaDevice< ? >) element;
			if (device.getParentDevice() != null) {
				return false;
			}
		}
		return true;
	}

}
