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
package gov.redhawk.bulkio.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import BULKIO.PrecisionUTCTime;

/**
 * @since 4.0
 */
public class BulkIOFormatter {

	/**
	 * ISO 8601 date/time format, using space instead of 'T' between date and time for readability on UI.
	 */
	private static final String ISO_8601_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSSZ";

	private BulkIOFormatter() {
	}

	public static String toISO8601(PrecisionUTCTime time) {
		double timeMillis = (time.twsec + time.tfsec) * 1000;
		if (Double.isInfinite(timeMillis) || Double.isNaN(timeMillis)) {
			return "Invalid time";
		}
		Date date = new Date(Math.round(timeMillis));
		return new SimpleDateFormat(ISO_8601_TIME_FORMAT).format(date);
	}

}
