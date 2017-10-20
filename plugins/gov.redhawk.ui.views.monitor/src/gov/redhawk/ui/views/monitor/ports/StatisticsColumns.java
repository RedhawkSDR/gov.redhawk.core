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
package gov.redhawk.ui.views.monitor.ports;

import gov.redhawk.common.ui.AdapterFactoryCellLabelProvider;
import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;
import gov.redhawk.monitor.model.ports.PortStatisticsProvider;
import gov.redhawk.monitor.model.ports.provider.PortsItemProviderAdapterFactory;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;

import BULKIO.PortStatistics;

public final class StatisticsColumns {

	private static final int BITS_PER_KIBIBYTE = 8 * 1024;
	private static final int BITS_PER_MEBIBYTE = BITS_PER_KIBIBYTE * 1024;
	private static final String B_PER_S = Messages.StatisticsColumns_BytesPerSecond;
	private static final String KIB_PER_S = Messages.StatisticsColumns_KibibytesPerSecond;
	private static final String MIB_PER_S = Messages.StatisticsColumns_MebibytesPerSecond;

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
		Messages.StatisticsColumns_Column_ElementsPerSec_Description, new CellLabelProvider() {

			@Override
			public void update(final ViewerCell cell) {
				PortStatistics stat = null;
				if (cell.getElement() instanceof PortStatisticsProvider) {
					stat = ((PortStatisticsProvider) cell.getElement()).getData();
				}
				if (stat == null) {
					cell.setText(""); //$NON-NLS-1$
				} else {
					cell.setText(FORMATTER.format(stat.elementsPerSecond));
				}
			}
		});

	public static final Column BITS_PER_SECOND = new Column("bytes_per_second", Messages.StatisticsColumns_Column_BytesPerSec, //$NON-NLS-1$
		Messages.StatisticsColumns_Column_BytesPerSec_Description, new CellLabelProvider() {
			@Override
			public void update(final ViewerCell cell) {
				PortStatistics stat = null;
				if (cell.getElement() instanceof PortStatisticsProvider) {
					stat = ((PortStatisticsProvider) cell.getElement()).getData();
				}
				String text;
				if (stat == null) {
					text = ""; //$NON-NLS-1$
				} else if (stat.bitsPerSecond < BITS_PER_KIBIBYTE) {
					text = FORMATTER.format(stat.bitsPerSecond) + B_PER_S;
				} else if (stat.bitsPerSecond < BITS_PER_MEBIBYTE) {
					text = FORMATTER.format(stat.bitsPerSecond / BITS_PER_KIBIBYTE) + KIB_PER_S;
				} else {
					text = FORMATTER.format(stat.bitsPerSecond / BITS_PER_MEBIBYTE) + MIB_PER_S;
				}
				cell.setText(text);
			}
		});

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
		new CellLabelProvider() {
			@Override
			public void update(final ViewerCell cell) {
				PortStatistics stat = null;
				if (cell.getElement() instanceof PortStatisticsProvider) {
					stat = ((PortStatisticsProvider) cell.getElement()).getData();
				}
				if (stat == null) {
					cell.setText(""); //$NON-NLS-1$
				} else {
					cell.setText(FORMATTER.format(stat.timeSinceLastCall));
				}
			}
		});

	public static final List<Column> DEFAULT_COLUMNS = Collections.unmodifiableList(
		Arrays.asList(StatisticsColumns.NAME, StatisticsColumns.ELEMENTS_PER_SEC, StatisticsColumns.BITS_PER_SECOND, StatisticsColumns.CALLS_PER_SECOND,
			StatisticsColumns.STREAM_IDS, StatisticsColumns.AVG_QUEUE_DEPTH, StatisticsColumns.TIME));
}
