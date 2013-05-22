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


package gov.redhawk.sca.waveform.controlpanel.propertyEditors;

import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaSimpleSequenceProperty;
import gov.redhawk.sca.waveform.controlpanel.WaveformControlPanelPlugin;
import mil.jpeojtrs.sca.prf.PropertyValueType;

public class PropertyEditorFactory {

	private PropertyEditorFactory() { }

	public static PropertyEditor getPropertyEditor(ScaAbstractProperty<?> prop) {
		String name = prop.getName();
		if (name == null) {
			name = prop.getId();
		}
		if (name == null) {
			name = "(Invalid Property)";
		}
		PropertyValueType type = null;
		if (prop instanceof ScaSimpleProperty) {
			ScaSimpleProperty simpleProp = (ScaSimpleProperty) prop;
			Object value = simpleProp.getValue();
			if (value != null) {
				type = simpleProp.getDefinition().getType();
				
				switch (type) {
				//extraneous type checking due to bug in framework -- FIXED
//				case LONG:
//				case ULONG:
//					if (value instanceof Integer) {
//						return new IntegerPropertyEditor<Integer>(name, (Integer) value, simpleProp);
//					}
//					return new IntegerPropertyEditor<Integer>(name, ((Long) value).intValue(), simpleProp);
//				case USHORT:
//				case SHORT:
//					if (value instanceof Short) {
//						return new IntegerPropertyEditor<Short>(name, (Short) value, simpleProp);
//					}
//					return new IntegerPropertyEditor<Short>(name, ((Integer) value).shortValue(), simpleProp);
//				case LONGLONG:
//					if (value instanceof Long) {
//						return new IntegerPropertyEditor<Long>(name, (Long) value, simpleProp);
//					}
//					return new IntegerPropertyEditor<Long>(name, ((Integer) value).longValue(), simpleProp);
//				case OCTET:
//					Long oValue = ((Byte) value).longValue();
//					return new IntegerPropertyEditor<Long>(name,  oValue, simpleProp);
//				case BOOLEAN:
//					return new BooleanPropertyEditor(name, (Boolean) value, simpleProp);
//				case FLOAT:
//					if (value instanceof Float) {
//						return new DecimalPropertyEditor<Float>(name, (Float) value, simpleProp);
//					}
//					return new DecimalPropertyEditor<Float>(name, ((Double) value).floatValue(), simpleProp);
//				case DOUBLE:
//					if (value instanceof Float) {
//						return new DecimalPropertyEditor<Double>(name, ((Float) value).doubleValue(), simpleProp);
//					}
//					return new DecimalPropertyEditor<Double>(name, (Double) value, simpleProp);
//				case CHAR:
//				case STRING:
//					return new StringPropertyEditor(name, String.valueOf(value), simpleProp);
				case LONG:
				case ULONG:
					return new IntegerPropertyEditor<Integer>(name,  (Integer) value, simpleProp);
				case SHORT:
				case USHORT:
					return new IntegerPropertyEditor<Short>(name,  (Short) value, simpleProp);
				case LONGLONG:
					return new IntegerPropertyEditor<Long>(name,  (Long) value, simpleProp);
				case FLOAT:
					return new DecimalPropertyEditor<Float>(name, (Float) value, simpleProp);
				case DOUBLE:
					return new DecimalPropertyEditor<Double>(name, (Double) value, simpleProp);
				case OCTET:
					Long oValue = ((Byte) value).longValue();
					return new IntegerPropertyEditor<Long>(name,  oValue, simpleProp);
				case BOOLEAN:
					return new BooleanPropertyEditor(name, (Boolean) value, simpleProp);
				case CHAR:
				case STRING:
					return new StringPropertyEditor(name, String.valueOf(value), simpleProp);
				default:
					WaveformControlPanelPlugin.logWarning("Cannot render property name-value pair. Unknown TypeCode: " + type);
					return new StringPropertyEditor(name, value.toString(), prop);
				}
			} else if (prop instanceof ScaSimpleSequenceProperty) {
				return null;
			}
		}
		return null;	
	}
}
