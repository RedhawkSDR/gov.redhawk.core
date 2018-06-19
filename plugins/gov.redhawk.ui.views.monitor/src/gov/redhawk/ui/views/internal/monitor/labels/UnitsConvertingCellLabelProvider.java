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
package gov.redhawk.ui.views.internal.monitor.labels;

import java.math.BigInteger;
import java.text.NumberFormat;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;

import gov.redhawk.ui.views.internal.monitor.values.ValueProvider;

/**
 * Provides cell labels that are scaled based on the magnitude and the units (e.g. B/s, KiB/s, MiB/s, etc)
 */
public class UnitsConvertingCellLabelProvider extends CellLabelProvider {

	private static final long TO_KIBIBYTES = 2 << 9;
	private static final long TO_MEBIBYTES = 2 << 19;
	private static final long TO_GIBIBYTES = 2 << 29;
	private static final long TO_TEBIBYTES = 2 << 39;

	private static final double TO_THOUSANDS = 1e3;
	private static final double TO_MILLIONS = 1e6;

	private static final double BITS_TO_BYTES = 8;
	private static final double BITS_TO_KIBIBYTES = 2 << 12;
	private static final double BITS_TO_MEBIBYTES = 2 << 22;

	private static final long SEC_PER_MIN = 60;
	private static final long SEC_PER_HOUR = SEC_PER_MIN * 60;
	private static final long SEC_PER_DAY = SEC_PER_HOUR * 24;

	public enum Units {
		/**
		 * Bytes
		 */
		BYTES,
		/**
		 * Input is bits/sec, output is bytes/sec
		 */
		BYTES_SEC_FROM_BITS_SEC,
		/**
		 * Thingamabobs/sec. Converts for thousands and millions.
		 */
		THINGS_SEC,
		/**
		 * Time span in seconds (as in "3 seconds", <b>not</b> "3 seconds since J1970")
		 */
		TIME_SPAN
	}

	private ValueProvider< ? extends Number> valueProvider;
	private Units units;

	/**
	 * @param valueProvider
	 * @param units The units of the value
	 */
	public UnitsConvertingCellLabelProvider(ValueProvider< ? extends Number> valueProvider, Units units) {
		this.valueProvider = valueProvider;
		this.units = units;
	}

	@Override
	public void update(ViewerCell cell) {
		Number value = valueProvider.getValue(cell.getElement());
		String cellLabel = "";
		if (value instanceof BigInteger) {
			cellLabel = compute((BigInteger) value);
		} else if (value instanceof Double) {
			cellLabel = compute(value.doubleValue());
		} else if (value != null) {
			cellLabel = compute(value.longValue());
		}
		cell.setText(cellLabel);
	}

	@Override
	public String getToolTipText(Object element) {
		Number value = valueProvider.getValue(element);
		if (value == null) {
			return "";
		}
		switch (units) {
		case BYTES:
			return Messages.bind(Messages.UnitsConvertingCellLabelProvider_BytesFull, value.toString());
		case BYTES_SEC_FROM_BITS_SEC:
			return Messages.bind(Messages.UnitsConvertingCellLabelProvider_BitsPerSecondFull, value.toString());
		case THINGS_SEC:
			return Messages.bind(Messages.UnitsConvertingCellLabelProvider_UnitlessPerSecondFull, value.toString());
		case TIME_SPAN:
			return Messages.bind(Messages.UnitsConvertingCellLabelProvider_SecondsFull, value.toString());
		default:
			return "";
		}
	}

	@Override
	public int getToolTipTimeDisplayed(Object object) {
		return 5000;
	}

	@Override
	public int getToolTipDisplayDelayTime(Object object) {
		return 500;
	}

	private String compute(BigInteger value) {
		// Special case - value bigger than max long
		if (value.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0) {
			return tooBig();
		}

		switch (units) {
		case BYTES:
			return bytes(value.longValue());
		case BYTES_SEC_FROM_BITS_SEC:
			return bytesPerSec(value.doubleValue());
		case THINGS_SEC:
			return somethingsPerSec(value.doubleValue());
		case TIME_SPAN:
			return timeSpan(value.longValue());
		default:
			return "";
		}
	}

	private String compute(double value) {
		// Special case - value bigger than max long
		if (value > Long.MAX_VALUE) {
			return tooBig();
		}

		switch (units) {
		case BYTES:
			return bytes((long) value);
		case BYTES_SEC_FROM_BITS_SEC:
			return bytesPerSec(value);
		case THINGS_SEC:
			return somethingsPerSec(value);
		case TIME_SPAN:
			return (value < 10.0) ? smallTimeSpan(value) : timeSpan((long) value);
		default:
			return "";
		}
	}

	private String compute(long value) {
		switch (units) {
		case BYTES:
			return bytes(value);
		case BYTES_SEC_FROM_BITS_SEC:
			return bytesPerSec(value);
		case THINGS_SEC:
			return somethingsPerSec(value);
		case TIME_SPAN:
			return timeSpan(value);
		default:
			return "";
		}
	}

	/**
	 * @return A string appropriate to the units indicating the value is too large
	 */
	private String tooBig() {
		switch (units) {
		case TIME_SPAN:
			return Messages.UnitsConvertingCellLabelProvider_ALongTime;
		case BYTES:
		case BYTES_SEC_FROM_BITS_SEC:
		case THINGS_SEC:
		default:
			return Messages.UnitsConvertingCellLabelProvider_ALot;
		}
	}

	private String bytes(long bytes) {
		if (bytes < TO_KIBIBYTES) {
			return Messages.bind(Messages.UnitsConvertingCellLabelProvider_Bytes, NumberFormat.getNumberInstance().format(bytes));
		} else if (bytes < TO_MEBIBYTES) {
			return Messages.bind(Messages.UnitsConvertingCellLabelProvider_Kibibytes, NumberFormat.getNumberInstance().format(bytes / TO_KIBIBYTES));
		} else if (bytes < TO_GIBIBYTES) {
			return Messages.bind(Messages.UnitsConvertingCellLabelProvider_Mebibytes, NumberFormat.getNumberInstance().format(bytes / TO_MEBIBYTES));
		} else if (bytes < TO_TEBIBYTES) {
			return Messages.bind(Messages.UnitsConvertingCellLabelProvider_Gibibytes, NumberFormat.getNumberInstance().format(bytes / TO_GIBIBYTES));
		} else {
			return Messages.bind(Messages.UnitsConvertingCellLabelProvider_Tebibytes, NumberFormat.getNumberInstance().format(bytes / TO_TEBIBYTES));
		}
	}

	private String bytesPerSec(double bitsPerSecond) {
		if (bitsPerSecond < BITS_TO_KIBIBYTES) {
			return Messages.bind(Messages.UnitsConvertingCellLabelProvider_BytesPerSecond, Formatters.DECIMAL_FORMATTER.format(bitsPerSecond / BITS_TO_BYTES));
		} else if (bitsPerSecond < BITS_TO_MEBIBYTES) {
			return Messages.bind(Messages.UnitsConvertingCellLabelProvider_KibibytesPerSecond,
				Formatters.DECIMAL_FORMATTER.format(bitsPerSecond / BITS_TO_KIBIBYTES));
		} else {
			return Messages.bind(Messages.UnitsConvertingCellLabelProvider_MebibytesPerSecond,
				Formatters.DECIMAL_FORMATTER.format(bitsPerSecond / BITS_TO_MEBIBYTES));
		}
	}

	private String somethingsPerSec(double somethingsPerSecond) {
		if (somethingsPerSecond < TO_THOUSANDS) {
			return Messages.bind(Messages.UnitsConvertingCellLabelProvider_UnitlessPerSecond, Formatters.DECIMAL_FORMATTER.format(somethingsPerSecond));
		} else if (somethingsPerSecond < TO_MILLIONS) {
			return Messages.bind(Messages.UnitsConvertingCellLabelProvider_UnitlessThousandsPerSecond,
				Formatters.DECIMAL_FORMATTER.format(somethingsPerSecond / TO_THOUSANDS));
		} else {
			return Messages.bind(Messages.UnitsConvertingCellLabelProvider_UnitlessMillionsPerSecond,
				Formatters.DECIMAL_FORMATTER.format(somethingsPerSecond / TO_MILLIONS));
		}
	}

	/**
	 * Handles only the cases where the number of seconds is a double and is very small.
	 * @param seconds
	 * @return
	 */
	private String smallTimeSpan(double seconds) {
		return Messages.bind(Messages.UnitsConvertingCellLabelProvider_Seconds, Formatters.DECIMAL_FORMATTER.format(seconds));
	}

	private String timeSpan(long seconds) {
		NumberFormat formatter = Formatters.NUMBER_FORMATTER;
		long day = seconds / SEC_PER_DAY;
		seconds = seconds % SEC_PER_DAY;
		long hour = seconds / SEC_PER_HOUR;
		seconds = seconds % SEC_PER_HOUR;
		long min = seconds / SEC_PER_MIN;
		seconds = seconds % SEC_PER_MIN;
		if (day > 0) {
			return Messages.bind(Messages.UnitsConvertingCellLabelProvider_DaysHoursMinutes, new String[] { formatter.format(day), formatter.format(hour), formatter.format(min) });
		} else if (hour > 0) {
			return Messages.bind(Messages.UnitsConvertingCellLabelProvider_HoursMinutesSeconds, new String[] { formatter.format(hour), formatter.format(min), formatter.format(seconds) });
		} else if (min > 0) {
			return Messages.bind(Messages.UnitsConvertingCellLabelProvider_MinutesSeconds, formatter.format(min), formatter.format(seconds));
		} else {
			return Messages.bind(Messages.UnitsConvertingCellLabelProvider_Seconds, formatter.format(seconds));
		}
	}
}
