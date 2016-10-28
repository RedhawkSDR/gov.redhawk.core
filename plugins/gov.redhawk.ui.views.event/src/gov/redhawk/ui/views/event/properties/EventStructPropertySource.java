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
import java.util.Arrays;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;

import CF.DataType;
import mil.jpeojtrs.sca.util.AnyUtils;

/**
 * 
 */
public class EventStructPropertySource implements IPropertySource {

	private DataType struct;

	public EventStructPropertySource(DataType struct) {
		this.struct = struct;
	}
	
	@Override
	public Object getEditableValue() {
		return struct;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		List<PropertyDescriptor> propDescList = new ArrayList<PropertyDescriptor>();
		if (struct != null) {
			DataType[] fields = CF.PropertiesHelper.extract(struct.value);
			for (DataType field : fields) {
				propDescList.add(new PropertyDescriptor(field.id, field.id));
			}
		}
		return propDescList.toArray(new IPropertyDescriptor[0]);
	}

	@Override
	public Object getPropertyValue(Object id) {
		DataType[] fields = CF.PropertiesHelper.extract(struct.value);
		for (DataType field : fields) {
			if (id.equals(field.id)) {
				if (AnyUtils.isSimple(field)) {
					return field.value.toString();
				}
				if (AnyUtils.isSequence(field)) {
					return Arrays.toString((Object[]) AnyUtils.convertAny(field.value));
				}

				return field.value.toString();
			}
		}
		return new Object();
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
