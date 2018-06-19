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
package gov.redhawk.ui.views.internal.monitor.ports;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;

import BULKIO.PortStatistics;
import gov.redhawk.common.ui.AdapterFactoryCellLabelProvider;
import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;
import gov.redhawk.monitor.model.ports.PortStatisticsProvider;
import gov.redhawk.monitor.model.ports.provider.PortsItemProviderAdapterFactory;
import gov.redhawk.ui.views.internal.monitor.labels.ULongLongValueCellLabelProvider;
import gov.redhawk.ui.views.internal.monitor.labels.UnitsConvertingCellLabelProvider;
import gov.redhawk.ui.views.internal.monitor.labels.UnitsConvertingCellLabelProvider.Units;
import gov.redhawk.ui.views.internal.monitor.values.DoubleDataTypeValueProvider;
import gov.redhawk.ui.views.internal.monitor.values.ULongLongDataTypeValueProvider;

public final class StatisticsColumns {

	private static final NumberFormat FORMATTER;
	static {
		FORMATTER = NumberFormat.getNumberInstance();
		FORMATTER.setGroupingUsed(true);
		FORMATTER.setMinimumFractionDigits(1);
		FORMATTER.setMaximumFractionDigits(1);
		FORMATTER.setMinimumIntegerDigits(1);
	}

	private static final NumberFormat PERCENTAGE_FORMATER = NumberFormat.getPercentInstance();

	private StatisticsColumns() {
	}

	public static final Column NAME = new Column("name", Messages.StatisticsColumns_Column_Name, Messages.StatisticsColumns_Column_Name_Description, //$NON-NLS-1$
		new AdapterFactoryCellLabelProvider(
			new ComposedAdapterFactory(new AdapterFactory[] { new PortsItemProviderAdapterFactory(), new ScaItemProviderAdapterFactory() })));

	public static final Column ELEMENTS_PER_SEC = new Column("element_per_sec", Messages.StatisticsColumns_Column_ElementsPerSec, //$NON-NLS-1$
		Messages.StatisticsColumns_Column_ElementsPerSec_Description, new UnitsConvertingCellLabelProvider(input -> {
			if (!(input instanceof PortStatisticsProvider)) {
				return null;
			}
			PortStatistics stat = ((PortStatisticsProvider) input).getData();
			return (stat == null) ? null : (double) stat.elementsPerSecond;
		}, Units.THINGS_SEC));

	public static final Column BITS_PER_SECOND = new Column("bytes_per_second", Messages.StatisticsColumns_Column_BytesPerSec, //$NON-NLS-1$
		Messages.StatisticsColumns_Column_BytesPerSec_Description, new UnitsConvertingCellLabelProvider(input -> {
			if (!(input instanceof PortStatisticsProvider)) {
				return null;
			}
			PortStatistics stat = ((PortStatisticsProvider) input).getData();
			return (stat == null) ? null : (double) stat.bitsPerSecond;
		}, Units.BYTES_SEC_FROM_BITS_SEC));

	public static final Column CALLS_PER_SECOND = new Column("calls_sec", Messages.StatisticsColumns_Column_CallsPerSec, //$NON-NLS-1$
		Messages.StatisticsColumns_Column_CallsPerSec_Description, new CellLabelProvider() {
			@Override
			public void update(final ViewerCell cell) {
				PortStatistics stat = null;
				if (cell.getElement() instanceof PortStatisticsProvider) {
					stat = ((PortStatisticsProvider) cell.getElement()).getData();
				}
				if (stat == null) {
					cell.setText(""); //$NON-NLS-1$
				} else {
					cell.setText(FORMATTER.format(stat.callsPerSecond));
				}
			}
		});

	public static final Column STREAM_IDS = new Column("stream_ids", Messages.StatisticsColumns_Column_StreamIDs, //$NON-NLS-1$
		Messages.StatisticsColumns_Column_StreamIDs_Description, new CellLabelProvider() {
			@Override
			public void update(final ViewerCell cell) {
				PortStatistics stat = null;
				if (cell.getElement() instanceof PortStatisticsProvider) {
					stat = ((PortStatisticsProvider) cell.getElement()).getData();
				}
				if (stat == null) {
					cell.setText(""); //$NON-NLS-1$
				} else if (stat.streamIDs == null) {
					cell.setText("[]"); //$NON-NLS-1$
				} else {
					cell.setText(Arrays.toString(stat.streamIDs));
				}
			}
		});

	public static final Column AVG_QUEUE_DEPTH = new Column("avg_queue_depth", Messages.StatisticsColumns_Column_AvgQueueDepth, //$NON-NLS-1$
		Messages.StatisticsColumns_Column_AvgQueueDepth_Description, new CellLabelProvider() {
			@Override
			public void update(final ViewerCell cell) {
				PortStatistics stat = null;
				if (cell.getElement() instanceof PortStatisticsProvider) {
					stat = ((PortStatisticsProvider) cell.getElement()).getData();
				}
				if (stat == null) {
					cell.setText(""); //$NON-NLS-1$
				} else {
					cell.setText(PERCENTAGE_FORMATER.format(stat.averageQueueDepth));
				}
			}
		});

	public static final Column TIME = new Column("time", Messages.StatisticsColumns_Column_Time, Messages.StatisticsColumns_Column_Time_Description, //$NON-NLS-1$
		new UnitsConvertingCellLabelProvider(input -> {
			if (!(input instanceof PortStatisticsProvider)) {
				return null;
			}
			PortStatistics stat = ((PortStatisticsProvider) input).getData();
			return (stat == null) ? null : (double) stat.timeSinceLastCall;
		}, Units.TIME_SPAN));

	public static final List<Column> DEFAULT_COLUMNS = Collections.unmodifiableList(
		Arrays.asList(StatisticsColumns.NAME, StatisticsColumns.ELEMENTS_PER_SEC, StatisticsColumns.BITS_PER_SECOND, StatisticsColumns.CALLS_PER_SECOND,
			StatisticsColumns.STREAM_IDS, StatisticsColumns.AVG_QUEUE_DEPTH, StatisticsColumns.TIME));

	public static final Column BIO_TIME_LAST_FLUSH = new Column("timeSinceLastFlush", "Last Flush", "The time since the last flush",
		new UnitsConvertingCellLabelProvider(new DoubleDataTypeValueProvider("timeSinceLastFlush"), Units.TIME_SPAN));

	private static final String VITA49_PREFIX = "bulkio::transport::vita49::";

	public static final Column V49_AVG_PKT_RATE = new Column(VITA49_PREFIX + "avg_pkt_rate", "V49 Packets/sec", "Average VITA49 packets received per second",
		new UnitsConvertingCellLabelProvider(new DoubleDataTypeValueProvider(VITA49_PREFIX + "avg_pkt_rate"), Units.THINGS_SEC));

	public static final Column V49_BYTES_RECV = new Column(VITA49_PREFIX + "bytes_received", "V49 Bytes", "Total number of VITA49 bytes received",
		new UnitsConvertingCellLabelProvider(new ULongLongDataTypeValueProvider(VITA49_PREFIX + "bytes_received"), Units.BYTES));

	public static final Column V49_ERRORS = new Column(VITA49_PREFIX + "errors", "V49 Errors", "Number of reported VITA49 errors",
		new ULongLongValueCellLabelProvider(new ULongLongDataTypeValueProvider(VITA49_PREFIX + "errors")));

	public static final Column V49_PACKETS = new Column(VITA49_PREFIX + "packets", "V49 Packets", "Total number of VITA49 packets received",
		new ULongLongValueCellLabelProvider(new ULongLongDataTypeValueProvider(VITA49_PREFIX + "packets")));

	public static final Column V49_RATE = new Column(VITA49_PREFIX + "rate", "V49 Bytes/sec", "Average VITA49 data ingest rate in bytes per second",
		new UnitsConvertingCellLabelProvider(new DoubleDataTypeValueProvider(VITA49_PREFIX + "rate"), Units.BYTES_SEC_FROM_BITS_SEC));

	public static final Column V49_TOTAL_TIME = new Column(VITA49_PREFIX + "total_time", "V49 Time", "Total elapsed time during VITA49 read operations",
		new UnitsConvertingCellLabelProvider(new DoubleDataTypeValueProvider(VITA49_PREFIX + "total_time"), Units.TIME_SPAN));

	public static final Column V49_TRANS_FLUSH = new Column(VITA49_PREFIX + "transport_flush_amount", "V49 Flushed",
		"Total amount of bytes flushed from the VITA49 transport queue",
		new ULongLongValueCellLabelProvider(new ULongLongDataTypeValueProvider(VITA49_PREFIX + "transport_flush_amount")));

	public static final Column V49_WARNINGS = new Column(VITA49_PREFIX + "warnings", "V49 Warnings", "Number of reported VITA49 warnings",
		new ULongLongValueCellLabelProvider(new ULongLongDataTypeValueProvider(VITA49_PREFIX + "warnings")));

	public static final Map<String, Column> DATA_TYPE_COLUMNS;
	static {
		Map<String, Column> values = new HashMap<>();
		values.put(BIO_TIME_LAST_FLUSH.getId(), BIO_TIME_LAST_FLUSH);
		values.put(V49_AVG_PKT_RATE.getId(), V49_AVG_PKT_RATE);
		values.put(V49_BYTES_RECV.getId(), V49_BYTES_RECV);
		values.put(V49_ERRORS.getId(), V49_ERRORS);
		values.put(V49_PACKETS.getId(), V49_PACKETS);
		values.put(V49_RATE.getId(), V49_RATE);
		values.put(V49_TOTAL_TIME.getId(), V49_TOTAL_TIME);
		values.put(V49_TRANS_FLUSH.getId(), V49_TRANS_FLUSH);
		values.put(V49_WARNINGS.getId(), V49_WARNINGS);
		DATA_TYPE_COLUMNS = Collections.unmodifiableMap(values);
	}
}
