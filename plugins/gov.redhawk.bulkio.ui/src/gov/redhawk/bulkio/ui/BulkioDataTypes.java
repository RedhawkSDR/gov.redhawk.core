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

public enum BulkioDataTypes {
	UNITS_NONE(0),
	UNITS_TIME(1),
	UNITS_DELAY(2),
	UNITS_FREQUENCY(3),
	UNITS_TIMECODE(4),
	UNITS_DISTANCE(5),
	UNITS_VELOCITY(6),
	UNITS_ACCELERATION(7),
	UNITS_JERK(8),
	UNITS_DOPPLER(9),
	UNITS_DOPPLERRATE(10),
	UNITS_ENERGY(11),
	UNITS_POWER(12),
	UNITS_MASS(13);

	private int value;

	private BulkioDataTypes(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		switch (value) {
		case 0:
			return "N/A";
		case 1:
			return "Time (sec)";
		case 2:
			return "Delay (sec)";
		case 3:
			return "Frequency (Hz)";
		case 4:
			return "Time code format";
		case 5:
			return "Distance (m)";
		case 6:
			return "Velocity (m/sec)";
		case 7:
			return "Acceleration (m/sec^2)";
		case 8:
			return "Jerk (m/sec^3)";
		case 9:
			return "Doppler (Hz)";
		case 10:
			return "Doppler rate (Hz/sec)";
		case 11:
			return "Energy (J)";
		case 12:
			return "Power (W)";
		case 13:
			return "Mass (g)";
		default:
			return "";
		}
	}
}
