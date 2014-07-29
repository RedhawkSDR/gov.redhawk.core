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
package gov.redhawk.ui.views.monitor.ports;

import gov.redhawk.common.ui.AdapterFactoryCellLabelProvider;
import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;
import gov.redhawk.ui.views.monitor.model.ports.PortStatisticsProvider;
import gov.redhawk.ui.views.monitor.model.ports.provider.PortsItemProviderAdapterFactory;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;

import BULKIO.PortStatistics;

/**
 * @since 3.0
 * 
 */
public final class StatisticsColumns {

	private static final int BITS_PER_MEGABYTE = 8388608;

	private static final NumberFormat FORMATER = new DecimalFormat("#0.#");
	private static final NumberFormat PERCENTAGE_FORMATER = NumberFormat.getPercentInstance();

	private StatisticsColumns() {

	}

	public static final Column NAME = new Column("name",
	        "Name",
	        "Name of the port or port connection",
	        new AdapterFactoryCellLabelProvider(new ComposedAdapterFactory(new AdapterFactory[] {
	                new PortsItemProviderAdapterFactory(), new ScaItemProviderAdapterFactory()
	        })));

	public static final Column ELEMENTS_PER_SEC = new Column("element_per_sec",
	        "Elements/sec",
	        "The rate of CORBA elements transferred in the pushPacket data.  It is recommended that this be calculated over TBD pushPacket calls using an EMA.",
	        new CellLabelProvider() {

		        @Override
		        public void update(final ViewerCell cell) {
			        PortStatistics stat = null;
			        if (cell.getElement() instanceof PortStatisticsProvider) {
				        stat = ((PortStatisticsProvider) cell.getElement()).getData();
			        }
			        if (stat == null) {
				        return;
			        }
			        final String label = StatisticsColumns.FORMATER.format(stat.elementsPerSecond);
			        cell.setText(label);

		        }
	        });

	public static final Column BITS_PER_SECOND = new Column("bits_per_second", "MBps", "The rate of MegaBytes transferred.\n"
	        + "\t- In the case of CORBA pushPacket calls   rate of transfer\n" + "\t- In the case of dataXML size of XML data (not schema) in that second\n"
	        + "\t- In the case of dataFile size of file (or part of file) transferred in that second", new CellLabelProvider() {
		@Override
		public void update(final ViewerCell cell) {
			PortStatistics stat = null;
			if (cell.getElement() instanceof PortStatisticsProvider) {
				stat = ((PortStatisticsProvider) cell.getElement()).getData();
			}
			if (stat == null) {
				return;
			}
			cell.setText(StatisticsColumns.FORMATER.format(stat.bitsPerSecond / StatisticsColumns.BITS_PER_MEGABYTE));
		}
	});

	public static final Column CALLS_PER_SECOND = new Column("calls_sec", "calls/sec", "Number of calls per second (push or send)", new CellLabelProvider() {
		@Override
		public void update(final ViewerCell cell) {
			PortStatistics stat = null;
			if (cell.getElement() instanceof PortStatisticsProvider) {
				stat = ((PortStatisticsProvider) cell.getElement()).getData();
			}
			if (stat == null) {
				return;
			}
			cell.setText(StatisticsColumns.FORMATER.format(stat.callsPerSecond));
		}
	});

	public static final Column STREAM_IDS = new Column("stream_ids",
	        "Stream ID(s)",
	        "List of all active streamIDs (that have not been ended)",
	        new CellLabelProvider() {
		        @Override
		        public void update(final ViewerCell cell) {
			        PortStatistics stat = null;
			        if (cell.getElement() instanceof PortStatisticsProvider) {
				        stat = ((PortStatisticsProvider) cell.getElement()).getData();
			        }
			        if (stat == null) {
				        return;
			        }
			        if (stat.streamIDs == null) {
				        cell.setText("[]");
			        } else {
				        cell.setText(Arrays.toString(stat.streamIDs));
			        }
		        }
	        });

	public static final Column AVG_QUEUE_DEPTH = new Column("avg_queue_depth", "Avg. Queue Depth", "For components that queue data before processing/sending,"
	        + "the averageQueueDepth, measured as a percentage." + "It is recommended that this be calculated over TBD pushPacket" + "calls using an EMA."
	        + "\n" + "If a port does not queue data, this value shall be set to 0", new CellLabelProvider() {
		@Override
		public void update(final ViewerCell cell) {
			PortStatistics stat = null;
			if (cell.getElement() instanceof PortStatisticsProvider) {
				stat = ((PortStatisticsProvider) cell.getElement()).getData();
			}
			if (stat == null) {
				return;
			}
			cell.setText(StatisticsColumns.PERCENTAGE_FORMATER.format(stat.averageQueueDepth));
		}
	});

	public static final Column TIME = new Column("time",
	        "Time",
	        "The elapsed time, in seconds, since the last packet was transfered via a pushPacket call",
	        new CellLabelProvider() {
		        @Override
		        public void update(final ViewerCell cell) {
			        PortStatistics stat = null;
			        if (cell.getElement() instanceof PortStatisticsProvider) {
				        stat = ((PortStatisticsProvider) cell.getElement()).getData();
			        }
			        if (stat == null) {
				        return;
			        }
			        cell.setText(StatisticsColumns.FORMATER.format(stat.timeSinceLastCall));
		        }
	        });

	public static final List<Column> DEFAULT_COLUMNS = Collections.unmodifiableList(Arrays.asList(
	        StatisticsColumns.NAME,
	        StatisticsColumns.ELEMENTS_PER_SEC,
	        StatisticsColumns.BITS_PER_SECOND,
	        StatisticsColumns.CALLS_PER_SECOND,
	        StatisticsColumns.STREAM_IDS,
	        StatisticsColumns.AVG_QUEUE_DEPTH,
	        StatisticsColumns.TIME
	));
}
