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
package gov.redhawk.sca.util;

import java.beans.PropertyChangeListener;

/**
 * @since 3.4
 * 
 */
public class PropertyChangeSupport extends java.beans.PropertyChangeSupport {

	public PropertyChangeSupport(Object sourceBean) {
		super(sourceBean);
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		if (listener == null) {
			return;
		}
		if (contains(null, listener)) {
			return;
		}
		super.addPropertyChangeListener(listener);
	}

	private boolean contains(String propertyName, PropertyChangeListener listener) {
		PropertyChangeListener[] listeners;
		if (propertyName == null) {
			listeners = getPropertyChangeListeners();
		} else {
			listeners = getPropertyChangeListeners(propertyName);
		}
		for (PropertyChangeListener p : listeners) {
			if (p == listener) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		if (listener == null) {
			return;
		}
		if (contains(null, listener)) {
			return;
		}
		super.addPropertyChangeListener(propertyName, listener);
	}
}
