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
package gov.redhawk.ui.views.event.model;

import java.util.Date;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.views.properties.IPropertySource;
import org.omg.CORBA.Any;
import org.omg.CORBA.TypeCode;

import gov.redhawk.ui.views.event.properties.EventPropertySource;

/**
 * 
 */
public class Event implements IAdaptable {

	private Any value;
	private String channel;
	private Date timestamp;

	/**
	 * 
	 */
	public Event(Any value, String channel, Date timestamp) {
		this.value = value;
		this.channel = channel;
		this.timestamp = timestamp;
	}

	public Any getValue() {
		return value;
	}

	public String getChannel() {
		return channel;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public boolean valueIsType(TypeCode type) {
		return value.type().equal(type);
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EventPropertySource getAdapter(Class adapter) {
		if (adapter == IPropertySource.class) {
			return new EventPropertySource(this);
		}
		return null;
	}

}
