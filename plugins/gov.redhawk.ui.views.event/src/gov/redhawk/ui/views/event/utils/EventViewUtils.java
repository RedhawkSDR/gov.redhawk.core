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
package gov.redhawk.ui.views.event.utils;

import org.apache.commons.lang.ArrayUtils;

import CF.DataType;
import ExtendedEvent.ResourceStateChangeType;
import StandardEvent.SourceCategoryType;
import StandardEvent.StateChangeCategoryType;
import StandardEvent.StateChangeType;
import mil.jpeojtrs.sca.util.AnyUtils;

public class EventViewUtils {

	private EventViewUtils() {
	}

	public static String toString(SourceCategoryType sourceCategory) {
		switch (sourceCategory.value()) {
		case SourceCategoryType._APPLICATION:
			return "APPLICATION";
		case SourceCategoryType._APPLICATION_FACTORY:
			return "APPLICATION_FACTORY";
		case SourceCategoryType._DEVICE:
			return "DEVICE";
		case SourceCategoryType._DEVICE_MANAGER:
			return "DEVICE_MANAGER";
		case SourceCategoryType._EVENT_CHANNEL:
			return "EVENT_CHANNEL";
		case SourceCategoryType._SERVICE:
			return "SERVICE";
		default:
			return "";
		}
	}

	public static String toString(StateChangeCategoryType stateChangeCategory) {
		switch (stateChangeCategory.value()) {
		case StateChangeCategoryType._ADMINISTRATIVE_STATE_EVENT:
			return "ADMINISTRATIVE_STATE_EVENT";
		case StateChangeCategoryType._OPERATIONAL_STATE_EVENT:
			return "OPERATIONAL_STATE_EVENT";
		case StateChangeCategoryType._USAGE_STATE_EVENT:
			return "USAGE_STATE_EVENT";
		default:
			return "";
		}
	}

	public static String toString(StateChangeType stateChangeFrom) {
		switch (stateChangeFrom.value()) {
		case StateChangeType._ACTIVE:
			return "ACTIVE";
		case StateChangeType._BUSY:
			return "BUSY";
		case StateChangeType._DISABLED:
			return "DISABLED";
		case StateChangeType._ENABLED:
			return "ENABLED";
		case StateChangeType._IDLE:
			return "IDLE";
		case StateChangeType._LOCKED:
			return "LOCKED";
		case StateChangeType._SHUTTING_DOWN:
			return "SHUTTING_DOWN";
		case StateChangeType._UNLOCKED:
			return "UNLOCKED";
		default:
			return "";
		}
	}

	public static String toString(ResourceStateChangeType stateChangeFrom) {
		switch (stateChangeFrom.value()) {
		case ResourceStateChangeType._STARTED:
			return "STARTED";
		case ResourceStateChangeType._STOPPED:
			return "STOPPED";
		default:
			return "";
		}
	}

	public static String toString(DataType[] properties) {
		StringBuilder retVal = new StringBuilder();
		for (DataType t : properties) {
			retVal.append(t.id);
			retVal.append(" = ");
			Object value = AnyUtils.convertAny(t.value);
			if (value instanceof DataType[]) {
				retVal.append("{\n");
				retVal.append(toString((DataType[]) value));
				retVal.append("}");
			} else if (value.getClass().isArray()) {
				retVal.append(ArrayUtils.toString(value));
			} else {
				retVal.append(value);
			}
			retVal.append("\n");
		}
		return retVal.toString();
	}

}
