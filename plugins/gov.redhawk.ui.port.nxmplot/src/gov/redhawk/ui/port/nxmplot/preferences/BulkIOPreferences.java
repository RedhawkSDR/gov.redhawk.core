/*******************************************************************************
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.ui.port.nxmplot.preferences;

import java.util.List;

/**
 * @since 4.4
 */
public class BulkIOPreferences {

	public static final Preference<Boolean> BLOCKING = new Preference<Boolean>("bulkIOBlock.blocking", false);
	public static final Preference<Integer> PIPE_SIZE = new Preference<Integer>("bulkIOBlock.pipeSize", 131072);
	public static final Preference<Boolean> PIPE_SIZE_OVERRIDE = new Preference<Boolean>("bulkIOBlock.pipeSize.override", false);
	public static final Preference<Boolean> REMOVE_ON_EOS = new Preference<Boolean>("bulkIOBlock.removeOnEndOfStream", false);
	public static final Preference<Integer> SAMPLE_RATE = new Preference<Integer>("bulkIOBlock.sampleRate", 0);
	public static final Preference<Boolean> SAMPLE_RATE_OVERRIDE = new Preference<Boolean>("bulkIOBlock.sampleRate.override", false);
	public static final Preference<String>  CONNECTION_ID = new Preference<String>("bulkIOBlock.connectionID", "");
	public static final Preference<Boolean> CONNECTION_ID_OVERRIDE = new Preference<Boolean>("bulkIOBlock.connectionID.override", false);
	public static final Preference<Integer> TLL = new Preference<Integer>("bulkIOBlock.tll", 200);
	public static final Preference<Boolean> CAN_GROW_PIPE = new Preference<Boolean>("bulkIOBlock.canGrowPipe", true);
	public static final Preference<Integer> PIPE_SIZE_MULTIPLIER = new Preference<Integer>("bulkIOBlock.pipeSizeMultipler", 4);

	/** prevent instantiation as this class only contains constants and/or utility methods. */
	private BulkIOPreferences() {
	}

	public static List<Preference< ? >> getAllPreferences() {
		return Preference.gettAllPreferencesFor(BulkIOPreferences.class);
	}

}
