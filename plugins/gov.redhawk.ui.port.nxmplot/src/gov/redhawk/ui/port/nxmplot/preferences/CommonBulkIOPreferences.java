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
package gov.redhawk.ui.port.nxmplot.preferences;

/* package */ abstract class CommonBulkIOPreferences {

	public static final Preference<Integer> PIPE_SIZE = new Preference<Integer>("bulkIOBlock.pipeSize", 131072);
	public static final Preference<Boolean> PIPE_SIZE_OVERRIDE = new Preference<Boolean>("bulkIOBlock.pipeSize.override", false);
	public static final Preference<String>  CONNECTION_ID = new Preference<String>("bulkIOBlock.connectionID", "");

	protected CommonBulkIOPreferences() {
	}
}
