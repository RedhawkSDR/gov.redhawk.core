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
package gov.redhawk.ui.port.nxmplot;

import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.util.RedhawkEvents;
import gov.redhawk.ui.port.nxmplot.PlotEvent.Click;
import gov.redhawk.ui.port.nxmplot.PlotEvent.DragBox;
import gov.redhawk.ui.port.nxmplot.PlotEvent.Motion;
import gov.redhawk.ui.port.nxmplot.PlotEvent.Pan;
import gov.redhawk.ui.port.nxmplot.PlotEvent.ZoomIn;
import gov.redhawk.ui.port.nxmplot.PlotEvent.ZoomOut;
import gov.redhawk.ui.port.nxmplot.PlotEvent.ZoomX;

import java.util.Map;

import org.eclipse.ui.part.ViewPart;

/**
 * @since 4.4
 * 
 */
public class PlotEventChannelForwarder {

	private PlotEventChannelForwarder() {

	}

	public static void forwardEvents(final AbstractNxmPlotWidget plot, final ScaUsesPort port, final ViewPart sourceView) {
		plot.addPlotListener(new IPlotWidgetListener() {

			private String getTopic(String subTopic) {
				return PlotEvent.EventTags.TOPIC + "/" + subTopic;
			}

			private void addSourceViewArgs(Map<String, Object> argMap, ViewPart sourceView) {
				if (sourceView == null) {
					return;
				}
				argMap.put(PlotEvent.EventTags.SOURCE_VIEW, sourceView);
				argMap.put(PlotEvent.EventTags.SOURCE_VIEW_ID, sourceView.getViewSite().getId());
				argMap.put(PlotEvent.EventTags.SOURCE_VIEW_SECONDARY_ID, sourceView.getViewSite().getSecondaryId());
			}

			@Override
			public void zoomX(double xmin, double ymin, double xmax, double ymax, Object data) {
				String topic = getTopic("zoomX");
				Map<String, Object> argMap = RedhawkEvents.createMap(port, topic);
				ZoomX event = new PlotEvent.ZoomX(plot, data, xmin, ymin, xmax, ymax);
				argMap.put(PlotEvent.EventTags.PLOT_EVENT, event);
				addSourceViewArgs(argMap, sourceView);
				RedhawkEvents.publishEvent(topic, argMap);
			}

			@Override
			public void zoomOut(double x1, double y1, double x2, double y2, Object data) {
				String topic = getTopic("zoomOut");
				Map<String, Object> argMap = RedhawkEvents.createMap(port, topic);
				ZoomOut event = new PlotEvent.ZoomOut(plot, data, x1, y1, x2, x2);
				argMap.put(PlotEvent.EventTags.PLOT_EVENT, event);
				addSourceViewArgs(argMap, sourceView);
				RedhawkEvents.publishEvent(topic, argMap);
			}

			@Override
			public void zoomIn(double xmin, double ymin, double xmax, double ymax, Object data) {
				ZoomIn event = new PlotEvent.ZoomIn(plot, data, xmin, ymin, xmax, ymax);
				String topic = getTopic("zoomIn");
				Map<String, Object> argMap = RedhawkEvents.createMap(port, topic);
				argMap.put(PlotEvent.EventTags.PLOT_EVENT, event);
				addSourceViewArgs(argMap, sourceView);
				RedhawkEvents.publishEvent(topic, argMap);
			}

			@Override
			public void unzoom(double x1, double y1, double x2, double y2, Object data) {
				ZoomIn event = new PlotEvent.ZoomIn(plot, data, x1, y1, x2, y2);
				String topic = getTopic("unzoom");
				Map<String, Object> argMap = RedhawkEvents.createMap(port, topic);
				argMap.put(PlotEvent.EventTags.PLOT_EVENT, event);
				addSourceViewArgs(argMap, sourceView);
				RedhawkEvents.publishEvent(topic, argMap);
			}

			@Override
			public void pan(double x1, double y1, double x2, double y2) {
				Pan event = new PlotEvent.Pan(plot, null, x1, y1, x2, y2);
				String topic = getTopic("pan");
				Map<String, Object> argMap = RedhawkEvents.createMap(port, topic);
				argMap.put(PlotEvent.EventTags.PLOT_EVENT, event);
				addSourceViewArgs(argMap, sourceView);
				RedhawkEvents.publishEvent(topic, argMap);
			}

			@Override
			public void motion(double x, double y, double t) {
				Motion event = new PlotEvent.Motion(plot, null, x, y, t);
				String topic = getTopic("motion");
				Map<String, Object> argMap = RedhawkEvents.createMap(port, topic);
				argMap.put(PlotEvent.EventTags.PLOT_EVENT, event);
				addSourceViewArgs(argMap, sourceView);
				RedhawkEvents.publishEvent(topic, argMap);
			}

			@Override
			public void dragBox(double xmin, double ymin, double xmax, double ymax) {
				DragBox event = new PlotEvent.DragBox(plot, null, xmin, ymin, xmax, ymax);
				String topic = getTopic("dragBox");
				Map<String, Object> argMap = RedhawkEvents.createMap(port, topic);
				argMap.put(PlotEvent.EventTags.PLOT_EVENT, event);
				addSourceViewArgs(argMap, sourceView);
				RedhawkEvents.publishEvent(topic, argMap);
			}

			@Override
			public void click(double x, double y, double t) {
				Click event = new PlotEvent.Click(plot, null, x, y, t);
				String topic = getTopic("click");
				Map<String, Object> argMap = RedhawkEvents.createMap(port, topic);
				argMap.put(PlotEvent.EventTags.PLOT_EVENT, event);
				addSourceViewArgs(argMap, sourceView);
				RedhawkEvents.publishEvent(topic, argMap);
			}
		});
	}

	public static void forwardEvents(final PlotPageBook2 pageBook, final ScaUsesPort port, final ViewPart sourceView) {
		pageBook.addPlotListener(new IPlotWidgetListener() {

			private String getTopic(String subTopic) {
				return PlotEvent.EventTags.TOPIC + "/" + subTopic;
			}

			private void addSourceViewArgs(Map<String, Object> argMap, ViewPart sourceView) {
				if (sourceView == null) {
					return;
				}
				argMap.put(PlotEvent.EventTags.SOURCE_VIEW, sourceView);
				argMap.put(PlotEvent.EventTags.SOURCE_VIEW_ID, sourceView.getViewSite().getId());
				argMap.put(PlotEvent.EventTags.SOURCE_VIEW_SECONDARY_ID, sourceView.getViewSite().getSecondaryId());
			}

			@Override
			public void zoomX(double xmin, double ymin, double xmax, double ymax, Object data) {
				String topic = getTopic("zoomX");
				Map<String, Object> argMap = RedhawkEvents.createMap(port, topic);
				ZoomX event = new PlotEvent.ZoomX(pageBook.getActivePlotWidget(), data, xmin, ymin, xmax, ymax);
				argMap.put(PlotEvent.EventTags.PLOT_EVENT, event);
				addSourceViewArgs(argMap, sourceView);
				RedhawkEvents.publishEvent(topic, argMap);
			}

			@Override
			public void zoomOut(double x1, double y1, double x2, double y2, Object data) {
				String topic = getTopic("zoomOut");
				Map<String, Object> argMap = RedhawkEvents.createMap(port, topic);
				ZoomOut event = new PlotEvent.ZoomOut(pageBook.getActivePlotWidget(), data, x1, y1, x2, x2);
				argMap.put(PlotEvent.EventTags.PLOT_EVENT, event);
				addSourceViewArgs(argMap, sourceView);
				RedhawkEvents.publishEvent(topic, argMap);
			}

			@Override
			public void zoomIn(double xmin, double ymin, double xmax, double ymax, Object data) {
				ZoomIn event = new PlotEvent.ZoomIn(pageBook.getActivePlotWidget(), data, xmin, ymin, xmax, ymax);
				String topic = getTopic("zoomIn");
				Map<String, Object> argMap = RedhawkEvents.createMap(port, topic);
				argMap.put(PlotEvent.EventTags.PLOT_EVENT, event);
				addSourceViewArgs(argMap, sourceView);
				RedhawkEvents.publishEvent(topic, argMap);
			}

			@Override
			public void unzoom(double x1, double y1, double x2, double y2, Object data) {
				ZoomIn event = new PlotEvent.ZoomIn(pageBook.getActivePlotWidget(), data, x1, y1, x2, y2);
				String topic = getTopic("unzoom");
				Map<String, Object> argMap = RedhawkEvents.createMap(port, topic);
				argMap.put(PlotEvent.EventTags.PLOT_EVENT, event);
				addSourceViewArgs(argMap, sourceView);
				RedhawkEvents.publishEvent(topic, argMap);
			}

			@Override
			public void pan(double x1, double y1, double x2, double y2) {
				Pan event = new PlotEvent.Pan(pageBook.getActivePlotWidget(), null, x1, y1, x2, y2);
				String topic = getTopic("pan");
				Map<String, Object> argMap = RedhawkEvents.createMap(port, topic);
				argMap.put(PlotEvent.EventTags.PLOT_EVENT, event);
				addSourceViewArgs(argMap, sourceView);
				RedhawkEvents.publishEvent(topic, argMap);
			}

			@Override
			public void motion(double x, double y, double t) {
				Motion event = new PlotEvent.Motion(pageBook.getActivePlotWidget(), null, x, y, t);
				String topic = getTopic("motion");
				Map<String, Object> argMap = RedhawkEvents.createMap(port, topic);
				argMap.put(PlotEvent.EventTags.PLOT_EVENT, event);
				addSourceViewArgs(argMap, sourceView);
				RedhawkEvents.publishEvent(topic, argMap);
			}

			@Override
			public void dragBox(double xmin, double ymin, double xmax, double ymax) {
				DragBox event = new PlotEvent.DragBox(pageBook.getActivePlotWidget(), null, xmin, ymin, xmax, ymax);
				String topic = getTopic("dragBox");
				Map<String, Object> argMap = RedhawkEvents.createMap(port, topic);
				argMap.put(PlotEvent.EventTags.PLOT_EVENT, event);
				addSourceViewArgs(argMap, sourceView);
				RedhawkEvents.publishEvent(topic, argMap);
			}

			@Override
			public void click(double x, double y, double t) {
				Click event = new PlotEvent.Click(pageBook.getActivePlotWidget(), null, x, y, t);
				String topic = getTopic("click");
				Map<String, Object> argMap = RedhawkEvents.createMap(port, topic);
				argMap.put(PlotEvent.EventTags.PLOT_EVENT, event);
				addSourceViewArgs(argMap, sourceView);
				RedhawkEvents.publishEvent(topic, argMap);
			}
		});
	}
}
