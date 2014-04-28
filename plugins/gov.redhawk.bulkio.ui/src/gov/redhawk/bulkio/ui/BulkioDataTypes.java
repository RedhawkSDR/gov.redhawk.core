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
package gov.redhawk.bulkio.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public enum BulkioDataTypes {
	UNITS_NONE        (0,  "N/A"),
	UNITS_TIME        (1,  "Time (sec)"),
	UNITS_DELAY       (2,  "Delay (sec)"),
	UNITS_FREQUENCY   (3,  "Frequency (Hz)"),
	UNITS_TIMECODE    (4,  "Time code format"),
	UNITS_DISTANCE    (5,  "Distance (m)"),
	UNITS_VELOCITY    (6,  "Velocity (m/sec)"),
	UNITS_ACCELERATION(7,  "Acceleration (m/sec^2)"),
	UNITS_JERK        (8,  "Jerk (m/sec^3)"),
	UNITS_DOPPLER     (9,  "Doppler (Hz)"),
	UNITS_DOPPLERRATE (10, "Doppler rate (Hz/sec)"),
	UNITS_ENERGY      (11, "Energy (J)"),
	UNITS_POWER       (12, "Power (W)"),
	UNITS_MASS        (13, "Mass (g)");

	private static final Map<Integer, BulkioDataTypes> INT_TO_ENUM_TYPE_MAP = new HashMap<Integer, BulkioDataTypes>();
	static {
		for (BulkioDataTypes type : BulkioDataTypes.values()) {
			INT_TO_ENUM_TYPE_MAP.put(type.value, type);
		}
	}

	private final int value;
	private final String unitsName;

	private BulkioDataTypes(int value, @NonNull String unitsName) {
		this.value = value;
		this.unitsName = unitsName;
	}

	public String getUnitsName() {
		return this.unitsName;
	}
	
	@Nullable
	public static BulkioDataTypes fromInt(int value) {
		BulkioDataTypes type = INT_TO_ENUM_TYPE_MAP.get(value);
		return type;
	}
}
