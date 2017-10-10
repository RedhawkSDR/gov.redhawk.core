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

import gov.redhawk.ui.port.nxmblocks.BulkIONxmBlockSettings.BlockingOption;

import java.util.List;

/**
 * @since 4.4
 */
public class BulkIOPreferences extends CommonBulkIOPreferences {

	/**
	 * @since 5.0
	 */
	public static final Preference<String>  BLOCKING_OPTION = new Preference<String>("bulkIOBlock.blockingOption", BlockingOption.FROMSRI.name());
	public static final Preference<Boolean> REMOVE_ON_EOS = new Preference<Boolean>("bulkIOBlock.removeOnEndOfStream", true);
	public static final Preference<Integer> SAMPLE_RATE = new Preference<Integer>("bulkIOBlock.sampleRate", 0);
	public static final Preference<Boolean> SAMPLE_RATE_OVERRIDE = new Preference<Boolean>("bulkIOBlock.sampleRate.override", false);
	public static final Preference<Integer> TLL = new Preference<Integer>("bulkIOBlock.tll", 200);
	public static final Preference<Boolean> CAN_GROW_PIPE = new Preference<Boolean>("bulkIOBlock.canGrowPipe", true);
	public static final Preference<Integer> PIPE_SIZE_MULTIPLIER = new Preference<Integer>("bulkIOBlock.pipeSizeMultipler", 4);

	private BulkIOPreferences() {
	}

	public static List<Preference< ? >> getAllPreferences() {
		return Preference.gettAllPreferencesFor(BulkIOPreferences.class);
	}
}