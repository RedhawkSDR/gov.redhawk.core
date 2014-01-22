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
package gov.redhawk.model.sca.util;

import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaService;
import gov.redhawk.model.sca.ScaWaveform;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventConstants;

/**
 * @since 19.0
 * 
 */
public class RedhawkEvents {

	public static final String TOPIC_BASE = "gov/redhawk"; //$NON-NLS-1$

	public static final String MODEL_TOPIC_BASE = TOPIC_BASE + "/model"; //$NON-NLS-1$
	
	public static final String UI_TOPIC_BASE = TOPIC_BASE + "/ui"; //$NON-NLS-1$
	
	private RedhawkEvents() { };

	public static interface EventTags {
		/**
		 * The element that caused the event to be published
		 */
		public static final String ELEMENT = "ChangedElement"; //$NON-NLS-1$

		/**
		 * Type of the waveform that contains this element
		 */
		public static final String WAVEFORM_TYPE = "waveformType";

		/**
		 * Name of the waveform that contains this element
		 */
		public static final String WAVEFORM_NAME = "waveformName";

		/**
		 * Instance ID of the waveform that contains this element
		 */
		public static final String WAVEFORM_INSTANCE = "waveformInstance";

		/**
		 * Type of the component that contains this element
		 */
		public static final String COMPONENT_TYPE = "componentType";

		/**
		 * Name of the component within the waveform that contains this element
		 */
		public static final String COMPONENT_NAME = "componentName";

		/**
		 * Instance ID of the component contains this element
		 */
		public static final String COMPONENT_INSTANCE = "componentInstance";

		/**
		 * Name of the domain that contains this element
		 */
		public static final String DOMAIN_NAME = "domain";

		/**
		 * Type of the device that contains this element
		 */
		public static final String DEVICE_TYPE = "deviceType";

		/**
		 * Name of the device that contains this element
		 */
		public static final String DEVICE_NAME = "deviceName";

		/**
		 * Instance of the device that contains this element
		 */
		public static final String DEVICE_INSTANCE = "deviceInstance";

		/**
		 * TYPE of the service that contains this element
		 */
		public static final String SERVICE_TYPE = "serviceType";

		/**
		 * Name of the service within the node contains this element
		 */
		public static final String SERVICE_NAME = "serviceName";

		/**
		 * Type of the node that contains this element
		 */
		public static final String NODE_TYPE = "nodeType";

		/**
		 * Instance of the node that contains this element
		 */
		public static final String NODE_INSTANCE = "nodeInstance";

		/**
		 * ID of the property that contains this element
		 */
		public static final String PROPERTY_ID = "propertyID";

		/**
		 * ID of the property that contains this element
		 */
		public static final String PROPERTY_FULL_ID = "propertyFullID";

		/**
		 * Name of the property that contains this element
		 */
		public static final String PROPERTY_NAME = "propertyName";

		/**
		 * Name of the port that contains this element
		 */
		public static final String PORT_NAME = "portName";
	}

	public static interface Model {
		/**
		 * The event type @see EventTypes
		 */
		public static final String TYPE = "EventType"; //$NON-NLS-1$
		/**
		 * The attribute name
		 */
		public static final String ATTNAME = "AttName"; //$NON-NLS-1$
		/**
		 * The old value
		 */
		public static final String OLD_VALUE = "OldValue"; //$NON-NLS-1$
		/**
		 * The new value
		 */
		public static final String NEW_VALUE = "NewValue"; //$NON-NLS-1$

		/**
		 * The original notification value
		 */
		public static final String NOTIFICATION = "Notification"; //$NON-NLS-1$

	}

	public static Map<String, Object> createMap(EObject object, String topic) {
		Map<String, Object> retval = createMap(object);
		retval.put(EventConstants.EVENT_TOPIC, topic);
		return retval;
	}

	public static Map<String, Object> createMap(EObject object) {
		final Map<String, Object> retVal = new HashMap<String, Object>();
		retVal.put(EventTags.ELEMENT, object);
		for (EObject obj = object; obj != null; obj = obj.eContainer()) {
			if (obj instanceof ScaAbstractProperty< ? >) {
				retVal.put(EventTags.PROPERTY_ID, ((ScaAbstractProperty< ? >) obj).getId());
				retVal.put(EventTags.PROPERTY_NAME, ((ScaAbstractProperty< ? >) obj).getName());
				List<ScaAbstractProperty< ? >> path = new ArrayList<ScaAbstractProperty< ? >>();
				path.add((ScaAbstractProperty< ? >) obj);
				for (ScaAbstractProperty< ? > prop = (ScaAbstractProperty< ? >) obj; prop.eContainer() instanceof ScaAbstractProperty< ? >; prop = (ScaAbstractProperty< ? >) prop.eContainer()) {
					path.add(prop);
				}
				Collections.reverse(path);

				StringBuilder fullPropId = new StringBuilder();
				for (ScaAbstractProperty< ? > prop : path) {
					fullPropId.append(prop.getId());
					fullPropId.append(".");
				}
				fullPropId.setLength(fullPropId.length() - 1);
				retVal.put(EventTags.PROPERTY_FULL_ID, fullPropId.toString());
				obj = path.get(0);
			} else if (obj instanceof ScaComponent) {
				retVal.put(EventTags.COMPONENT_INSTANCE, ((ScaComponent) obj).getIdentifier());
				retVal.put(EventTags.COMPONENT_NAME, ((ScaComponent) obj).getName());
				if (((ScaComponent) obj).getProfileObj() != null) {
					retVal.put(EventTags.COMPONENT_TYPE, ((ScaComponent) obj).getProfileObj().getId());
				}
			} else if (obj instanceof ScaWaveform) {
				retVal.put(EventTags.WAVEFORM_INSTANCE, ((ScaWaveform) obj).getIdentifier());
				retVal.put(EventTags.WAVEFORM_NAME, ((ScaWaveform) obj).getName());
				if (((ScaWaveform) obj).getProfileObj() != null) {
					retVal.put(EventTags.WAVEFORM_TYPE, ((ScaWaveform) obj).getProfileObj().getId());
				}
			} else if (obj instanceof ScaDomainManager) {
				retVal.put(EventTags.DOMAIN_NAME, ((ScaDomainManager) obj).getName());
			} else if (obj instanceof ScaService) {
				retVal.put(EventTags.SERVICE_NAME, ((ScaService) obj).getName());
				if (((ScaService) obj).getProfileObj() != null) {
					retVal.put(EventTags.SERVICE_TYPE, ((ScaService) obj).getProfileObj().getId());
				}
			} else if (obj instanceof ScaDevice< ? >) {
				retVal.put(EventTags.DEVICE_INSTANCE, ((ScaDevice< ? >) obj).getIdentifier());
				retVal.put(EventTags.DEVICE_NAME, ((ScaDevice< ? >) obj).getLabel());
				if (((ScaDevice< ? >) obj).getProfileObj() != null) {
					retVal.put(EventTags.DEVICE_TYPE, ((ScaDevice< ? >) obj).getProfileObj().getId());
				}
			} else if (obj instanceof ScaDeviceManager) {
				retVal.put(EventTags.NODE_INSTANCE, ((ScaDeviceManager) obj).getIdentifier());
				if (((ScaDeviceManager) obj).getProfileObj() != null) {
					retVal.put(EventTags.NODE_TYPE, ((ScaDeviceManager) obj).getProfileObj().getId());
				}
			} else if (obj instanceof ScaPort< ? , ? >) {
				retVal.put(EventTags.PORT_NAME, ((ScaPort< ? , ? >) obj).getName());
			}
		}
		return retVal;
	}

	/**
	 * Publish the topic with the provided arguments to the global event bus. argMap MUST contain an
	 * EventTags.ELEMENT argument that is an MUIElement. the contained MUIElement will be used to
	 * determine the event bus to publish to.
	 * 
	 * @param topic
	 *            to broadcast
	 * @param argMap
	 *            arguments map with a minimum of a changedElement
	 * @return true if the event is published correctly, false otherwise
	 */
	public static boolean publishEvent(String topic, Map<String, Object> argMap) {
		if (topic == null || topic.length() == 0 || argMap == null) {
			return false;
		}

		Object element = argMap.get(EventTags.ELEMENT);
		if (element == null || !(element instanceof EObject)) {
			return false;
		}
		EventAdmin eventAdmin = ScaModelPlugin.getDefault().getEventAdmin();
		eventAdmin.postEvent(new Event(topic, argMap));
		return true;
	}

}
