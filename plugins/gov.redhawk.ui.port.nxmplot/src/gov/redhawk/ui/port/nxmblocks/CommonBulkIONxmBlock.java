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
package gov.redhawk.ui.port.nxmblocks;

import org.eclipse.jface.preference.IPreferenceStore;

import gov.redhawk.ui.port.nxmplot.AbstractNxmPlotWidget;
import gov.redhawk.ui.port.nxmplot.preferences.BulkIOPreferences;
import nxm.sys.lib.Command;

/**
 * This class abstracts several operations dealing with preferences which are common to regular BULKIO ports as well
 * as SDDS BULKIO ports.
 */
/* package */ abstract class CommonBulkIONxmBlock< C extends Command > extends AbstractNxmBlock<C> {

	protected CommonBulkIONxmBlock(Class<C> desiredLaunchCommandClass, AbstractNxmPlotWidget plotWidget, IPreferenceStore store) {
		super(desiredLaunchCommandClass, plotWidget, store);
	}

	public int getPipeSize() {
		return BulkIOPreferences.PIPE_SIZE.getValue(getPreferences());
	}

	public void setPipeSize(int pipeSize) {
		BulkIOPreferences.PIPE_SIZE.setValue(getPreferences(), pipeSize);
		BulkIOPreferences.PIPE_SIZE_OVERRIDE.setValue(getPreferences(), true);
	}

	public boolean isSetPipeSize() {
		return BulkIOPreferences.PIPE_SIZE_OVERRIDE.getValue(getPreferences());
	}

	public void unsetPipeSize() {
		BulkIOPreferences.PIPE_SIZE.setToDefault(getPreferences());
		BulkIOPreferences.PIPE_SIZE_OVERRIDE.setToDefault(getPreferences());
	}

	public String getConnectionID() {
		return BulkIOPreferences.CONNECTION_ID.getValue(getPreferences());
	}

	public void setConnectionID(String connectionID) {
		if (connectionID != null && !connectionID.isEmpty()) {
			BulkIOPreferences.CONNECTION_ID.setValue(getPreferences(), connectionID);
		}
	}
}
