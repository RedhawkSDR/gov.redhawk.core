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
package gov.redhawk.sca.ui;

import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaProvidesPort;
import gov.redhawk.model.sca.ScaService;
import gov.redhawk.model.sca.provider.ScaWaveformExternalPortsItemProvider;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

/**
 * @since 9.1
 *
 */
public class ScaViewerSorter extends ViewerSorter {

	public ScaViewerSorter() {
		super();
	}

	@Override
	public int compare(final Viewer viewer, final Object e1, final Object e2) {
		if (e1 instanceof ScaWaveformExternalPortsItemProvider) {
			if (e2 instanceof ScaWaveformExternalPortsItemProvider) {
				return 0;
			}
			return -1;
		}
		if (e2 instanceof ScaWaveformExternalPortsItemProvider) {
			return 1;
		}

		if (e1 instanceof ScaService) {
			if (e2 instanceof ScaService) {
				return super.compare(viewer, e1, e2);
			}
			return -1;
		} else if (e2 instanceof ScaService) {
			return 1;
		}

		if (e1 instanceof ScaDevice< ? >) {
			if (e2 instanceof ScaDevice< ? >) {
				return super.compare(viewer, e1, e2);
			}
			return -1;
		} else if (e2 instanceof ScaDevice< ? >) {
			if (e1 instanceof ScaDevice< ? >) {
				return super.compare(viewer, e1, e2);
			}
			return 1;
		} else if (e1 instanceof ScaProvidesPort) {
			if (e2 instanceof ScaProvidesPort) {
				return super.compare(viewer, e1, e2);
			}
			return -1;
		} else if (e2 instanceof ScaProvidesPort) {
			if (e1 instanceof ScaProvidesPort) {
				return super.compare(viewer, e1, e2);
			}
			return 1;
		}

		return super.compare(viewer, e1, e2);
	}

}
