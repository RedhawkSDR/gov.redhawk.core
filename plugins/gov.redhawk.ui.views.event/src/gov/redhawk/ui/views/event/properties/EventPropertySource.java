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
package gov.redhawk.ui.views.event.properties;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
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
import gov.redhawk.logging.ui.LogLevels;
import gov.redhawk.ui.views.event.model.Event;
import gov.redhawk.ui.views.event.utils.EventViewUtils;
import mil.jpeojtrs.sca.util.time.UTCTime;

/**
 * 
 */
public class EventPropertySource implements IPropertySource {

	// EVENT ID's
	private static final String ID_PREFIX = "gov.redhawk.ide.ui.event.";
	private static final String APPLICATION_ID = ID_PREFIX + "applicationId";
	private static final String CHANNEL = ID_PREFIX + "type.channel";
	private static final String COMPONENT_ID = ID_PREFIX + "ComponentId";
	private static final String DEVICE_ID = ID_PREFIX + "deviceId";
	private static final String EVENT_ID = ID_PREFIX + "eventId";
	private static final String PRODUCER_ID = ID_PREFIX + "producerId";
	private static final String PROPERTIES = ID_PREFIX + "properties";
	private static final String REGISTRATION_ID = ID_PREFIX + "registrationId";
	private static final String SOURCE_CATEGORY = ID_PREFIX + "sourceCategory";
	private static final String SOURCE_ID = ID_PREFIX + "sourceId";
	private static final String SOURCE_IOR = ID_PREFIX + "sourceIor";
	private static final String SOURCE_NAME = ID_PREFIX + "sourceName";
	private static final String STATE_CHANGE_CATEGORY = ID_PREFIX + "stateCategory";
	private static final String STATE_CHANGE_FROM = ID_PREFIX + "stateChangeFrom";
	private static final String STATE_CHANGE_TO = ID_PREFIX + "stateChangeTo";
	private static final String TIMESTAMP = ID_PREFIX + "timestamp";
	private static final String TIMESTAMP_STATUS = ID_PREFIX + "timestampStatus";
	private static final String TYPE = ID_PREFIX + "type";

	private Event event;

	public EventPropertySource(Event event) {
		this.event = event;
	}

	@Override
	public Object getEditableValue() {
		return event;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		List<PropertyDescriptor> propDescList = new ArrayList<PropertyDescriptor>();
		propDescList.add(new PropertyDescriptor(TYPE, "Event Type"));
		propDescList.add(new PropertyDescriptor(CHANNEL, "Event Channel"));
		propDescList.add(new PropertyDescriptor(TIMESTAMP, "Timestamp"));

		if (event.valueIsType(DomainManagementObjectAddedEventTypeHelper.type())) {
			createObjectAddedPropertyDescriptors(propDescList);
		} else if (event.valueIsType(DomainManagementObjectRemovedEventTypeHelper.type())) {
			createObjectRemovedPropertyDescriptors(propDescList);
		} else if (event.valueIsType(PropertyChangeEventHelper.type())) {
			createPropertyChangePropertyDescriptors(propDescList);
		} else if (event.valueIsType(PropertySetChangeEventTypeHelper.type())) {
			createPropertySetChangePropertyDescriptors(propDescList);
		} else if (event.valueIsType(StateChangeEventTypeHelper.type())) {
			createStateChangePropertyDescriptors(propDescList);
		} else if (event.valueIsType(ResourceStateChangeEventTypeHelper.type())) {
			createResourceStateChangePropertyDescriptors(propDescList);
		} else if (event.valueIsType(AbnormalComponentTerminationEventTypeHelper.type())) {
			createAbnormalTeminationPropertyDescriptors(propDescList);
		} else if (event.valueIsType(CF.PropertiesHelper.type())) {
			createMessageEventPropertyDescriptors(propDescList, CF.PropertiesHelper.extract(event.getValue()));
		} else if (event.valueIsType(LogEventHelper.type())) {
			createLogEventProperty(propDescList);
		} else if (event.valueIsType(PropertyChangeEventHelper_2_0.type())) {
			createPropertyChangePropertyDescriptors(propDescList);
		}

		return propDescList.toArray(new IPropertyDescriptor[0]);
	}

	private void createObjectAddedPropertyDescriptors(List<PropertyDescriptor> propDescList) {
		createObjectRemovedPropertyDescriptors(propDescList);
		propDescList.add(new PropertyDescriptor(SOURCE_IOR, "Source IOR"));
	}

	private void createObjectRemovedPropertyDescriptors(List<PropertyDescriptor> propDescList) {
		propDescList.add(new PropertyDescriptor(PRODUCER_ID, "Producer ID"));
		propDescList.add(new PropertyDescriptor(SOURCE_CATEGORY, "Source Category"));
		propDescList.add(new PropertyDescriptor(SOURCE_ID, "Source ID"));
		propDescList.add(new PropertyDescriptor(SOURCE_NAME, "Source Name"));
	}

	private void createPropertyChangePropertyDescriptors(List<PropertyDescriptor> propDescList) {
		propDescList.add(new PropertyDescriptor(EVENT_ID, "Event ID"));
		propDescList.add(new PropertyDescriptor(REGISTRATION_ID, "Registration ID"));
		propDescList.add(new PropertyDescriptor(SOURCE_ID, "Source ID"));
		propDescList.add(new PropertyDescriptor(PROPERTIES, "Properties"));
		propDescList.add(new PropertyDescriptor(TIMESTAMP_STATUS, "Timestamp status"));
	}

	private void createPropertySetChangePropertyDescriptors(List<PropertyDescriptor> propDescList) {
		propDescList.add(new PropertyDescriptor(SOURCE_ID, "Source ID"));
		propDescList.add(new PropertyDescriptor(SOURCE_NAME, "Source Name"));
		propDescList.add(new PropertyDescriptor(PROPERTIES, "Properties"));
	}

	private void createStateChangePropertyDescriptors(List<PropertyDescriptor> propDescList) {
		propDescList.add(new PropertyDescriptor(PRODUCER_ID, "Producer ID"));
		propDescList.add(new PropertyDescriptor(SOURCE_ID, "Source ID"));
		propDescList.add(new PropertyDescriptor(STATE_CHANGE_FROM, "State Change From"));
		propDescList.add(new PropertyDescriptor(STATE_CHANGE_TO, "State Change To"));
		propDescList.add(new PropertyDescriptor(STATE_CHANGE_CATEGORY, "State Category"));
	}

	private void createResourceStateChangePropertyDescriptors(List<PropertyDescriptor> propDescList) {
		propDescList.add(new PropertyDescriptor(SOURCE_ID, "Source ID"));
		propDescList.add(new PropertyDescriptor(SOURCE_NAME, "Source Name"));
		propDescList.add(new PropertyDescriptor(STATE_CHANGE_FROM, "State Change From"));
		propDescList.add(new PropertyDescriptor(STATE_CHANGE_TO, "State Change To"));
	}

	private void createAbnormalTeminationPropertyDescriptors(List<PropertyDescriptor> propDescList) {
		propDescList.add(new PropertyDescriptor(DEVICE_ID, "Device ID"));
		propDescList.add(new PropertyDescriptor(COMPONENT_ID, "Component ID"));
		propDescList.add(new PropertyDescriptor(APPLICATION_ID, "Application ID"));
	}

	private void createMessageEventPropertyDescriptors(List<PropertyDescriptor> propDescList, DataType[] dataTypes) {
		// Message ports should only be passed as a single struct. Following that assumption here.
		if (dataTypes == null || dataTypes.length < 1 || dataTypes[0] == null) {
			return;
		}
		for (int i = 0; i < dataTypes.length; i++) {
			DataType struct = dataTypes[i];
			String structId = String.format("%s[%d]", struct.id, i);
			PropertyDescriptor structDescriptor = new PropertyDescriptor(structId, structId);
			structDescriptor.setLabelProvider(new LabelProvider() {
				@Override
				public String getText(Object element) {
					// Don't display a direct value for a struct. Child nodes will display all details.
					return "";
				}
			});
			propDescList.add(structDescriptor);
		}
	}

	private void createLogEventProperty(List<PropertyDescriptor> propDescList) {
		propDescList.add(new PropertyDescriptor(PRODUCER_ID, "Producer ID"));
		propDescList.add(new PropertyDescriptor(ID_PREFIX + "producerName", "Producer Name"));
		propDescList.add(new PropertyDescriptor(ID_PREFIX + "producerNameFqn", "Producer Name FQN"));
		propDescList.add(new PropertyDescriptor(ID_PREFIX + "logTimestamp", "Log Event Timestamp"));
		propDescList.add(new PropertyDescriptor(ID_PREFIX + "logLevel", "Log Level"));
		propDescList.add(new PropertyDescriptor(ID_PREFIX + "message", "Message"));
	}

	@Override
	public Object getPropertyValue(Object id) {
		// Common to all events
		switch ((String) id) {
		case TYPE:
			try {
				return event.getValue().type().id();
			} catch (BadKind e) {
				return "UNKNOWN TYPE";
			}
		case CHANNEL:
			return event.getChannel();
		default:
		}

		// PropertyChangeEvent
		if (event.valueIsType(PropertyChangeEventHelper.type())) {
			return getPropertyValue((String) id, PropertyChangeEventHelper.extract(event.getValue()));
		} else if (event.valueIsType(PropertyChangeEventHelper_2_0.type())) {
			PropertyChangeEvent eventObject = PropertyChangeEventHelper_2_0.extract(event.getValue());
			eventObject.timestamp = new CF.UTCTime((short) 0, event.getTimestamp().getTime() / 1000, event.getTimestamp().getTime() % 1000);
			return getPropertyValue((String) id, eventObject);
		}

		// Common to all *remaining* events
		if (TIMESTAMP.equals(id)) {
			return event.getTimestamp().toString();
		}

		// Properties vary per the event type
		if (event.valueIsType(DomainManagementObjectAddedEventTypeHelper.type())) {
			return getPropertyValue((String) id, DomainManagementObjectAddedEventTypeHelper.extract(event.getValue()));
		} else if (event.valueIsType(DomainManagementObjectRemovedEventTypeHelper.type())) {
			return getPropertyValue((String) id, DomainManagementObjectRemovedEventTypeHelper.extract(event.getValue()));
		} else if (event.valueIsType(PropertySetChangeEventTypeHelper.type())) {
			return getPropertyValue((String) id, PropertySetChangeEventTypeHelper.extract(event.getValue()));
		} else if (event.valueIsType(StateChangeEventTypeHelper.type())) {
			return getPropertyValue((String) id, StateChangeEventTypeHelper.extract(event.getValue()));
		} else if (event.valueIsType(ResourceStateChangeEventTypeHelper.type())) {
			return getPropertyValue((String) id, ResourceStateChangeEventTypeHelper.extract(event.getValue()));
		} else if (event.valueIsType(AbnormalComponentTerminationEventTypeHelper.type())) {
			return getPropertyValue((String) id, AbnormalComponentTerminationEventTypeHelper.extract(event.getValue()));
		} else if (event.valueIsType(CF.PropertiesHelper.type())) {
			return getPropertyValue((String) id, CF.PropertiesHelper.extract(event.getValue()));
		} else if (event.valueIsType(LogEventHelper.type())) {
			return getPropertyValue((String) id, LogEventHelper.extract(event.getValue()));
		}

		return "";
	}

	private String getPropertyValue(String id, DomainManagementObjectAddedEventType event) {
		switch (id) {
		case PRODUCER_ID:
			return (event.producerId != null) ? event.producerId : "";
		case SOURCE_CATEGORY:
			return (event.sourceCategory != null) ? EventViewUtils.toString(event.sourceCategory) : "";
		case SOURCE_ID:
			return (event.sourceId != null) ? event.sourceId : "";
		case SOURCE_IOR:
			return (event.sourceIOR != null) ? event.sourceIOR.toString() : "";
		case SOURCE_NAME:
			return (event.sourceName != null) ? event.sourceName : "";
		default:
			return "";
		}
	}

	private String getPropertyValue(String id, DomainManagementObjectRemovedEventType event) {
		switch (id) {
		case PRODUCER_ID:
			return (event.producerId != null) ? event.producerId : "";
		case SOURCE_CATEGORY:
			return (event.sourceCategory != null) ? EventViewUtils.toString(event.sourceCategory) : "";
		case SOURCE_ID:
			return (event.sourceId != null) ? event.sourceId : "";
		case SOURCE_NAME:
			return (event.sourceName != null) ? event.sourceName : "";
		default:
			return "";
		}
	}

	private String getPropertyValue(String id, PropertyChangeEvent event) {
		switch (id) {
		case EVENT_ID:
			return (event.evt_id != null) ? event.evt_id : "";
		case REGISTRATION_ID:
			return (event.reg_id != null) ? event.reg_id : "";
		case SOURCE_ID:
			return (event.resource_id != null) ? event.resource_id : "";
		case PROPERTIES:
			return (event.properties != null) ? EventViewUtils.toString(event.properties) : "";
		case TIMESTAMP:
			return (event.timestamp != null) ? new UTCTime(event.timestamp).toString() : "";
		case TIMESTAMP_STATUS:
			if (event.timestamp == null) {
				return "";
			}
			switch (event.timestamp.tcstatus) {
			case 0:
				return "0 (invalid)";
			case 1:
				return "1 (valid)";
			default:
				return Short.toString(event.timestamp.tcstatus);
			}
		default:
			return "";
		}
	}

	private String getPropertyValue(String id, PropertySetChangeEventType event) {
		switch (id) {
		case SOURCE_ID:
			return (event.sourceId != null) ? event.sourceId : "";
		case SOURCE_NAME:
			return (event.sourceName != null) ? event.sourceName : "";
		case PROPERTIES:
			return (event.properties != null) ? EventViewUtils.toString(event.properties) : "";
		default:
			return "";
		}
	}

	private String getPropertyValue(String id, StateChangeEventType event) {
		switch (id) {
		case PRODUCER_ID:
			return (event.producerId != null) ? event.producerId : "";
		case SOURCE_ID:
			return (event.sourceId != null) ? event.sourceId : "";
		case STATE_CHANGE_CATEGORY:
			return (event.stateChangeCategory != null) ? EventViewUtils.toString(event.stateChangeCategory) : "";
		case STATE_CHANGE_FROM:
			return (event.stateChangeFrom != null) ? EventViewUtils.toString(event.stateChangeFrom) : "";
		case STATE_CHANGE_TO:
			return (event.stateChangeTo != null) ? EventViewUtils.toString(event.stateChangeTo) : "";
		default:
			return "";
		}
	}

	private String getPropertyValue(String id, ResourceStateChangeEventType event) {
		switch (id) {
		case SOURCE_ID:
			return (event.sourceId != null) ? event.sourceId : "";
		case SOURCE_NAME:
			return (event.sourceName != null) ? event.sourceName : "";
		case STATE_CHANGE_FROM:
			return (event.stateChangeFrom != null) ? EventViewUtils.toString(event.stateChangeFrom) : "";
		case STATE_CHANGE_TO:
			return (event.stateChangeTo != null) ? EventViewUtils.toString(event.stateChangeTo) : "";
		default:
			return "";
		}
	}

	private String getPropertyValue(String id, AbnormalComponentTerminationEventType event) {
		switch (id) {
		case DEVICE_ID:
			return (event.deviceId != null) ? event.deviceId : "";
		case COMPONENT_ID:
			return (event.componentId != null) ? event.componentId : "";
		case APPLICATION_ID:
			return (event.applicationId != null) ? event.applicationId : "";
		default:
			return "";
		}
	}

	private Object getPropertyValue(String id, DataType[] dataTypes) {
		for (int i = 0; i < dataTypes.length; i++) {
			DataType struct = dataTypes[i];
			String structId = String.format("%s[%d]", struct.id, i);
			if (id.equals(structId)) {
				return new EventStructPropertySource(struct);
			}
		}
		return "";
	}

	private Object getPropertyValue(String id, LogEvent event) {
		switch (id) {
		case PRODUCER_ID:
			return (event.producerId != null) ? event.producerId : "";
		case ID_PREFIX + "producerName":
			return (event.producerName != null) ? event.producerName : "";
		case ID_PREFIX + "producerNameFqn":
			return (event.producerName_fqn != null) ? event.producerName_fqn : "";
		case ID_PREFIX + "logTimestamp":
			Date date = new Date(event.timeStamp);
			return date.toString();
		case ID_PREFIX + "logLevel":
			return LogLevels.intToLogLevel(event.level).getLabel();
		case ID_PREFIX + "message":
			return (event.msg != null) ? event.msg : "";
		default:
			return "";
		}
	}

	@Override
	public boolean isPropertySet(Object id) {
		return true;
	}

	@Override
	public void resetPropertyValue(Object id) {
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
	}
}
