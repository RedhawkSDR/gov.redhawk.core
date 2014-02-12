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
package gov.redhawk.ui.port.nxmplot.preferences;

import gov.redhawk.ui.port.nxmplot.PlotActivator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceStore;
import org.eclipse.jface.util.PropertyChangeEvent;

/**
 * @since 4.4
 */
public class Preference< T > {
	private final String name;
	private final Class<T> type;
	private final T defaultValue;

	public Preference(String name, T defaultValue) {
		super();
		Assert.isNotNull(defaultValue);
		this.name = name;
		this.defaultValue = defaultValue;
		this.type = (Class<T>) this.defaultValue.getClass();
	}

	public String getName() {
		return name;
	}

	public Class<T> getType() {
		return type;
	}

	public T getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(IPreferenceStore store, T value) {
		if (value != null) {
			if (value instanceof Boolean) {
				store.setDefault(name, (Boolean) value);
			} else if (value instanceof Double) {
				store.setDefault(name, (Double) value);
			} else if (value instanceof Float) {
				store.setDefault(name, (Float) value);
			} else if (value instanceof Integer) {
				store.setDefault(name, (Integer) value);
			} else if (value instanceof Long) {
				store.setDefault(name, (Long) value);
			} else if (value instanceof String) {
				store.setDefault(name, (String) value);
			} else {
				throw new IllegalStateException("Unsupported preference type " + type.getCanonicalName());
			}
		} else {
			store.setToDefault(name);
		}
	}

	public void setDefaultValue(IPreferenceStore store) {
		if (defaultValue != null) {
			if (defaultValue instanceof Boolean) {
				store.setDefault(name, (Boolean) defaultValue);
			} else if (defaultValue instanceof Double) {
				store.setDefault(name, (Double) defaultValue);
			} else if (defaultValue instanceof Float) {
				store.setDefault(name, (Float) defaultValue);
			} else if (defaultValue instanceof Integer) {
				store.setDefault(name, (Integer) defaultValue);
			} else if (defaultValue instanceof Long) {
				store.setDefault(name, (Long) defaultValue);
			} else if (defaultValue instanceof String) {
				store.setDefault(name, (String) defaultValue);
			} else {
				throw new IllegalStateException("Unsupported preference type " + type.getCanonicalName());
			}
		} else {
			store.setToDefault(name);
		}
	}

	public void setValue(IPreferenceStore store, T newValue) {
		if (newValue != null) {
			type.cast(newValue);
			if (newValue instanceof Boolean) {
				store.setValue(name, (Boolean) newValue);
			} else if (newValue instanceof Double) {
				store.setValue(name, (Double) newValue);
			} else if (newValue instanceof Float) {
				store.setValue(name, (Float) newValue);
			} else if (newValue instanceof Integer) {
				store.setValue(name, (Integer) newValue);
			} else if (newValue instanceof Long) {
				store.setValue(name, (Long) newValue);
			} else if (newValue instanceof String) {
				store.setValue(name, (String) newValue);
			} else {
				throw new IllegalStateException("Unsupported preference type " + type.getCanonicalName());
			}
		} else {
			store.setToDefault(name);
		}
	}

	public T getValue(IPreferenceStore store) {
		if (store != null) {
			if (type == Boolean.class) {
				return type.cast(store.getBoolean(name));
			} else if (type == Double.class) {
				return type.cast(store.getDouble(name));
			} else if (type == Float.class) {
				return type.cast(store.getFloat(name));
			} else if (type == Integer.class) {
				return type.cast(store.getInt(name));
			} else if (type == Long.class) {
				return type.cast(store.getLong(name));
			} else if (type == String.class) {
				return type.cast(store.getString(name));
			} else {
				throw new IllegalStateException("Unsupported preference type " + type.getCanonicalName());
			}
		} else {
			return null;
		}
	}

	public T getDefaultValue(IPreferenceStore store) {
		if (store != null) {
			if (type == Boolean.class) {
				return type.cast(store.getDefaultBoolean(name));
			} else if (type == Double.class) {
				return type.cast(store.getDefaultDouble(name));
			} else if (type == Float.class) {
				return type.cast(store.getDefaultFloat(name));
			} else if (type == Integer.class) {
				return type.cast(store.getDefaultInt(name));
			} else if (type == Long.class) {
				return type.cast(store.getDefaultLong(name));
			} else if (type == String.class) {
				return type.cast(store.getDefaultString(name));
			} else {
				throw new IllegalStateException("Unsupported preference type " + type.getCanonicalName());
			}
		} else {
			return null;
		}
	}

	public boolean isEvent(PropertyChangeEvent event) {
		if (name.equals(event.getProperty())) {
			return true;
		}
		return false;
	}

	public void setToDefault(IPreferenceStore preferences) {
		preferences.setToDefault(getName());
	}

	public boolean isDefault(IPreferenceStore store) {
		return store.isDefault(getName());
	}

	public static List<Preference< ? >> gettAllPreferencesFor(Class< ? > c) {
		Field[] fields = c.getFields();
		List<Preference< ? >> retVal = new ArrayList<Preference< ? >>(fields.length);
		for (int i = 0; i < fields.length; i++) {
			try {
				Object value = fields[i].get(null);
				if (value instanceof Preference< ? >) {
					retVal.add((Preference< ? >) value);
				}
			} catch (IllegalArgumentException e) {
				// PASS
			} catch (IllegalAccessException e) {
				// PASS
			}
		}
		return Collections.unmodifiableList(retVal);
	}

	public static IPreferenceStore initStoreFromWorkbench(List<Preference< ? >> prefs, IPreferenceStore newStore) {
		if (newStore == null) {
			newStore = new PreferenceStore();
		}

		IPreferenceStore workbenchStore = PlotActivator.getDefault().getPreferenceStore();
		for (Preference p : prefs) {
			p.setDefaultValue(newStore, p.getDefaultValue(workbenchStore));
			p.setValue(newStore, p.getValue(workbenchStore));
		}

		return newStore;
	}

}
