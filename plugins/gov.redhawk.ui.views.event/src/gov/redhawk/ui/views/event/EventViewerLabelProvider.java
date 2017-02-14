package gov.redhawk.ui.views.event;
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


import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.nebula.widgets.xviewer.XViewer;
import org.eclipse.nebula.widgets.xviewer.XViewerLabelProvider;
import org.eclipse.nebula.widgets.xviewer.core.model.XViewerColumn;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.statushandlers.StatusManager;
import org.omg.CORBA.TypeCodePackage.BadKind;

import CF.DataType;
import CF.LogEvent;
import CF.LogEventHelper;
import CF.PropertyChangeListenerPackage.PropertyChangeEvent;
import CF.PropertyChangeListenerPackage.PropertyChangeEventHelper;
import CF.PropertyChangeListenerPackage.PropertyChangeEventHelper_2_0;
import ExtendedEvent.PropertySetChangeEventType;
import ExtendedEvent.PropertySetChangeEventTypeHelper;
import ExtendedEvent.ResourceStateChangeEventType;
import ExtendedEvent.ResourceStateChangeEventTypeHelper;
import StandardEvent.AbnormalComponentTerminationEventType;
import StandardEvent.AbnormalComponentTerminationEventTypeHelper;
import StandardEvent.DomainManagementObjectAddedEventType;
import StandardEvent.DomainManagementObjectAddedEventTypeHelper;
import StandardEvent.DomainManagementObjectRemovedEventType;
import StandardEvent.DomainManagementObjectRemovedEventTypeHelper;
import StandardEvent.StateChangeEventType;
import StandardEvent.StateChangeEventTypeHelper;
import gov.redhawk.ui.views.event.model.Event;
import gov.redhawk.ui.views.event.utils.EventViewUtils;

public class EventViewerLabelProvider extends XViewerLabelProvider {

	private static final DateFormat DATE_FORMAT_SS = new SimpleDateFormat("HH:mm:ss");

	public EventViewerLabelProvider(XViewer viewer) {
		super(viewer);
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		// do nothing
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// do nothing
	}

	@Override
	public void dispose() {
	}

	@Override
	public Image getColumnImage(Object element, XViewerColumn xCol, int columnIndex) throws Exception {
		return null;
	}

	@Override
	public String getColumnText(Object element, XViewerColumn xCol, int columnIndex) throws Exception {
		if (element instanceof Event) {
			Event event = (Event) element;
			if (xCol.equals(EventViewerFactory.TIME_COL_SS)) {
				return EventViewerLabelProvider.DATE_FORMAT_SS.format(event.getTimestamp());
			} else if (xCol.equals(EventViewerFactory.TYPE_COL)) {
				if (event.valueIsType(CF.PropertiesHelper.type())) {
					return "MessageEvent";
				}

				try {
					return event.getValue().type().name();
				} catch (BadKind e) {
					StatusManager.getManager().handle(
						new Status(IStatus.ERROR, EventViewPlugin.PLUGIN_ID, "Error retrieving event name: " + e.getMessage(), e),
						StatusManager.SHOW | StatusManager.LOG);
				}
				return "UNKNOWN";
			} else if (xCol.equals(EventViewerFactory.SUMMARY)) {
				return getEventSummary(event);
			}
		}
		return "";
	}

	private String getEventSummary(Event event) {
		if (event.valueIsType(DomainManagementObjectAddedEventTypeHelper.type())) {
			DomainManagementObjectAddedEventType value = DomainManagementObjectAddedEventTypeHelper.extract(event.getValue());
			return (value.sourceName + " added to the domain");
		} else if (event.valueIsType(DomainManagementObjectRemovedEventTypeHelper.type())) {
			DomainManagementObjectRemovedEventType value = DomainManagementObjectRemovedEventTypeHelper.extract(event.getValue());
			return (value.sourceName + " removed from the domain");
		} else if (event.valueIsType(StateChangeEventTypeHelper.type())) {
			StateChangeEventType value = StateChangeEventTypeHelper.extract(event.getValue());
			return (value.sourceId + " state changed from " + EventViewUtils.toString(value.stateChangeFrom) + " to "
				+ EventViewUtils.toString(value.stateChangeTo));
		} else if (event.valueIsType(PropertyChangeEventHelper.type())) {
			PropertyChangeEvent value = PropertyChangeEventHelper.extract(event.getValue());
			StringBuilder sb = new StringBuilder(100);
			sb.append("Properties for ");
			sb.append(value.resource_id);
			sb.append(" have been changed: ");
			appendPropIdList(sb, value.properties);
			return sb.toString();
		} else if (event.valueIsType(PropertySetChangeEventTypeHelper.type())) {
			PropertySetChangeEventType value = PropertySetChangeEventTypeHelper.extract(event.getValue());
			StringBuilder sb = new StringBuilder(100);
			sb.append("Properties for ");
			sb.append(value.sourceName);
			sb.append(" have been changed: ");
			appendPropIdList(sb, value.properties);
			return sb.toString();
		} else if (event.valueIsType(ResourceStateChangeEventTypeHelper.type())) {
			ResourceStateChangeEventType value = ResourceStateChangeEventTypeHelper.extract(event.getValue());
			return (value.sourceName + " state changed from " + EventViewUtils.toString(value.stateChangeFrom) + " to "
				+ EventViewUtils.toString(value.stateChangeTo));
		} else if (event.valueIsType(AbnormalComponentTerminationEventTypeHelper.type())) {
			AbnormalComponentTerminationEventType value = AbnormalComponentTerminationEventTypeHelper.extract(event.getValue());
			return (value.componentId + " terminated unexpectedly");
		} else if (event.valueIsType(CF.PropertiesHelper.type())) {
			DataType[] value = CF.PropertiesHelper.extract(event.getValue());
			StringBuilder sb = new StringBuilder("Event received regarding structs: ");
			appendPropIdList(sb, value);
			return sb.toString();
		} else if (event.valueIsType(LogEventHelper.type())) {
			LogEvent value = LogEventHelper.extract(event.getValue());
			return value.msg;
		} else if (event.valueIsType(PropertyChangeEventHelper_2_0.type())) {
			PropertyChangeEvent value = PropertyChangeEventHelper_2_0.extract(event.getValue());
			StringBuilder sb = new StringBuilder(100);
			sb.append("Properties for ");
			sb.append(value.resource_id);
			sb.append(" have been changed: ");
			appendPropIdList(sb, value.properties);
			return sb.toString();
		}
		return "";
	}

	private String appendPropIdList(StringBuilder sb, DataType[] props) {
		sb.append('[');
		int len = 0;
		boolean ellipsis = false;
		for (DataType prop : props) {
			len += prop.id.length();
			if (len < 50) {
				sb.append(prop.id);
				sb.append(", ");
			} else {
				sb.append("...");
				ellipsis = true;
				break;
			}
		}
		if (!ellipsis && props.length > 0) {
			sb.setLength(sb.length() - 2);
		}
		sb.append(']');
		return sb.toString();
	}

	@Override
	public Object getBackingData(Object element, XViewerColumn xViewerColumn, int columnIndex) throws Exception {
		// If not shown, don't process any further
		if (!xViewerColumn.isShow()) {
			return "";
		}
		if (xViewerColumn.getId().equals(EventViewerFactory.TIME_COL_SS.getId()) && element instanceof Event) {
			return ((Event) element).getTimestamp();
		}
		return super.getBackingData(element, xViewerColumn, columnIndex);
	}

}
