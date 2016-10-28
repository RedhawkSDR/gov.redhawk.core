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

import org.eclipse.jface.viewers.BaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.graphics.Image;
import org.omg.CORBA.TypeCodePackage.BadKind;

import CF.DataType;
import gov.redhawk.ui.views.event.model.Event;

public class EventDetailsLabelProvider extends BaseLabelProvider implements ILabelProvider {

	@Override
	public Image getImage(Object element) {
		return null;
	}

	@Override
	public String getText(Object element) {
		if (element instanceof TreeSelection && ((TreeSelection) element).getFirstElement() instanceof Event) {
			Event event = (Event) ((TreeSelection) element).getFirstElement();
			if (event.valueIsType(CF.PropertiesHelper.type())) {
				DataType[] value = CF.PropertiesHelper.extract(event.getValue());
				if (value != null && value.length > 0) {
					return "Message Event: " + value[0].id;
				}
			} else {
				try {
					return event.getValue().type().name();
				} catch (BadKind e) {
					return "Event Viewer - Property Details";
				}
			}
		}

		return "Event Viewer - Property Details";
	}

}
