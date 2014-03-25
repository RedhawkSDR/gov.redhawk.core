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
package gov.redhawk.bulkio.ui.internal;

import gov.redhawk.bulkio.ui.BulkIOUIActivator;
import gov.redhawk.bulkio.ui.BulkioDataTypes;

import java.util.Arrays;
import java.util.Date;

import mil.jpeojtrs.sca.util.AnyUtils;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import CF.DataType;

public class SriDataViewLabelProvider implements ITableLabelProvider {

	@Override
	public void addListener(ILabelProviderListener listener) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
	}

	@Override
	@Nullable
	public Image getColumnImage(Object element, int columnIndex) {
		Image image;
		if (columnIndex == 0) {
			if (element instanceof SriDataTypeWrapper) {
				DataType dt = ((SriDataTypeWrapper) element).getDataType();
				Object value = AnyUtils.convertAny(dt.value);
				if (value instanceof Object[]) {
					if (value instanceof DataType[]) {
						image = AbstractUIPlugin.imageDescriptorFromPlugin(BulkIOUIActivator.PLUGIN_ID, "icons/Struct.gif").createImage();
						return image;
					} else {
						Object[] values = (Object[]) value;
						if (values.length > 0 && values[0] instanceof DataType[]) {
							image = AbstractUIPlugin.imageDescriptorFromPlugin(BulkIOUIActivator.PLUGIN_ID, "icons/StructSequence.gif").createImage();
							return image;
						} else {
							image = AbstractUIPlugin.imageDescriptorFromPlugin(BulkIOUIActivator.PLUGIN_ID, "icons/SimpleSequence.gif").createImage();
							return image;
						}
					}
				} else {
					image = AbstractUIPlugin.imageDescriptorFromPlugin(BulkIOUIActivator.PLUGIN_ID, "icons/Simple.gif").createImage();
					return image;
				}
			}
			if (element instanceof StructSequenceHelper) {
				StructSequenceHelper helper = (StructSequenceHelper) element;
				if (helper.isArray()) {
					image = AbstractUIPlugin.imageDescriptorFromPlugin(BulkIOUIActivator.PLUGIN_ID, "icons/Struct.gif").createImage();
				} else {
					image = AbstractUIPlugin.imageDescriptorFromPlugin(BulkIOUIActivator.PLUGIN_ID, "icons/Simple.gif").createImage();
				}
				return image;
			}
			if (element instanceof DataType) {
				image = AbstractUIPlugin.imageDescriptorFromPlugin(BulkIOUIActivator.PLUGIN_ID, "icons/Simple.gif").createImage();
				return image;
			}
		}
		return null;
	}

	@Override
	@NonNull
	public String getColumnText(Object element, int columnIndex) {
		if (element instanceof SriBuilder) {
			SriBuilder sri = (SriBuilder) element;
			switch (columnIndex) {
			case 0:
				return sri.getName();
			case 1:
				if ("keywords: ".equals(sri.getName())) {
					return "";
				} else if ("mode: ".equals(sri.getName())) {
					Short value = (Short) sri.getValue();
					if (value == 0) {
						return "[" + String.valueOf(sri.getValue()) + "] scalar";
					} else if (value == 1) {
						return "[" + String.valueOf(sri.getValue()) + "] complex";
					}
				} else if ("xunits: ".equals(sri.getName()) || "yunits: ".equals(sri.getName())) {
					switch ((Short) sri.getValue()) {
					case 0:
						return "[" + String.valueOf(sri.getValue()) + "] " + BulkioDataTypes.UNITS_NONE;
					case 1:
						return "[" + String.valueOf(sri.getValue()) + "] " + BulkioDataTypes.UNITS_TIME;
					case 2:
						return "[" + String.valueOf(sri.getValue()) + "] " + BulkioDataTypes.UNITS_DELAY;
					case 3:
						return "[" + String.valueOf(sri.getValue()) + "] " + BulkioDataTypes.UNITS_FREQUENCY;
					case 4:
						return "[" + String.valueOf(sri.getValue()) + "] " + BulkioDataTypes.UNITS_TIMECODE;
					case 5:
						return "[" + String.valueOf(sri.getValue()) + "] " + BulkioDataTypes.DISTANCE;
					case 6:
						return "[" + String.valueOf(sri.getValue()) + "] " + BulkioDataTypes.UNITS_VELOCITY;
					case 7:
						return "[" + String.valueOf(sri.getValue()) + "] " + BulkioDataTypes.UNITS_ACCELERATION;
					case 8:
						return "[" + String.valueOf(sri.getValue()) + "] " + BulkioDataTypes.UNITS_JERK;
					case 9:
						return "[" + String.valueOf(sri.getValue()) + "] " + BulkioDataTypes.UNITS_DOPPLER;
					case 10:
						return "[" + String.valueOf(sri.getValue()) + "] " + BulkioDataTypes.UNITS_DOPPLERRATE;
					case 11:
						return "[" + String.valueOf(sri.getValue()) + "] " + BulkioDataTypes.UNITS_ENERGY;
					case 12:
						return "[" + String.valueOf(sri.getValue()) + "] " + BulkioDataTypes.UNITS_POWER;
					case 13:
						return "[" + String.valueOf(sri.getValue()) + "] " + BulkioDataTypes.UNITS_MASS;
					default:
						return sri.getValue().toString();
					}
				}

				// Default behavior for populated rows
				if (sri.getValue() != null) {
					if (sri.getValue() instanceof Date) {
						return BulkIOUIActivator.toISO8601TimeStr((Date) sri.getValue());
					} else {
						return sri.getValue().toString();
					}
				} else {
					return "";
				}
			default:
				return "";
			}
		} else if (element instanceof SriDataTypeWrapper) {
			DataType dt = ((SriDataTypeWrapper) element).getDataType();
			switch (columnIndex) {
			case 0:
				return dt.id;
			case 1:
				Object value = AnyUtils.convertAny(dt.value);
				if (value instanceof Object[]) {
					if (value instanceof DataType[]) {
						return "";
					} else {
						Object[] values = (Object[]) value;
						if (values.length > 0 && values[0] instanceof DataType[]) {
							return "";
						} else {
							return Arrays.toString(values);
						}
					}
				} else {
					return String.valueOf(value);
				}
			default:
				return "";
			}
		} else if (element instanceof StructSequenceHelper) {
			StructSequenceHelper helper = (StructSequenceHelper) element;
			switch (columnIndex) {
			case 0:
				return helper.getId();
			case 1:
				if (helper.isArray()) {
					return "";
				}
				return String.valueOf(helper.getValue());
			default:
				return "";
			}
		} else if (element instanceof DataType) {
			DataType dt = (DataType) element;
			switch (columnIndex) {
			case 0:
				return dt.id;
			case 1:
				return String.valueOf(dt.value);
			default:
				return "";
			}
		} else {
			return "";
		}
	}
}
