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

import gov.redhawk.ui.port.nxmplot.preferences.BulkIOPreferences;

/* package */ abstract class CommonBulkIONxmBlockSettings {

	public static final String PROP_PIPE_SIZE = "pipeSize";
	public static final String PROP_CONNECTION_ID = "connectionID";

	/**
	 * PS switch
	 */
	private Integer pipeSize;

	/**
	 * Custom connection ID to use
	 */
	private String connectionID;

	protected CommonBulkIONxmBlockSettings(IPreferenceStore store) {
		if (BulkIOPreferences.PIPE_SIZE_OVERRIDE.getValue(store)) {
			pipeSize = BulkIOPreferences.PIPE_SIZE.getValue(store);
		}
		connectionID = BulkIOPreferences.CONNECTION_ID.getValue(store);
	}

	public Integer getPipeSize() {
		return pipeSize;
	}

	public void setPipeSize(Integer pipeSize) {
		this.pipeSize = pipeSize;
	}

	public String getConnectionID() {
		return connectionID;
	}

	public void setConnectionID(String connectionID) {
		this.connectionID = connectionID;
	}

}
