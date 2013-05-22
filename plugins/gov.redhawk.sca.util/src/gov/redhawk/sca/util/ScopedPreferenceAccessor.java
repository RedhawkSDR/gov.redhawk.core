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
package gov.redhawk.sca.util;

import java.io.IOException;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.osgi.service.prefs.BackingStoreException;

/**
 * The ScopedPreferenceStore is an IPreferenceStore that uses the scopes
 * provided in org.eclipse.core.runtime.preferences.
 * <p>
 * A ScopedPreferenceStore does the lookup of a preference based on it's search
 * scopes and sets the value of the preference based on its store scope.
 * </p>
 * <p>
 * The default scope is always included in the search scopes when searching for
 * preference values.
 * </p>
 * 
 * @see org.eclipse.core.runtime.preferences
 * @since 1.3
 */
public class ScopedPreferenceAccessor implements IPreferenceAccessor {

	/**
	 * The storeContext is the context where values will stored with the
	 * setValue methods. If there are no searchContexts this will be the search
	 * context. (along with the "default" context)
	 */
	private final IScopeContext storeContext;

	/**
	 * The searchContext is the array of contexts that will be used by the get
	 * methods for searching for values.
	 */
	private IScopeContext[] searchContexts;

	/**
	 * A boolean to indicate the property changes should not be propagated.
	 */
    private boolean silentRunning = false;

	/**
	 * The default context is the context where getDefault and setDefault
	 * methods will search. This context is also used in the search.
	 */
	private final IScopeContext defaultContext = new DefaultScope();

	/**
	 * The nodeQualifer is the string used to look up the node in the contexts.
	 */
	private final String nodeQualifier;

	/**
	 * The defaultQualifier is the string used to look up the default node.
	 */
	private String defaultQualifier;

	/**
	 * Boolean value indicating whether or not this store has changes to be
	 * saved.
	 */
	private boolean dirty;

	/**
	 * Create a new instance of the receiver. Store the values in context in the
	 * node looked up by qualifier. <strong>NOTE:</strong> Any instance of
	 * ScopedPreferenceStore should call
	 * 
	 * @param context
	 *            the scope to store to
	 * @param qualifier
	 *            the qualifier used to look up the preference node
	 * @param defaultQualifierPath
	 *            the qualifier used when looking up the defaults
	 */
	public ScopedPreferenceAccessor(final IScopeContext context, final String qualifier, final String defaultQualifierPath) {
		this(context, qualifier);
		this.defaultQualifier = defaultQualifierPath;
	}

	/**
	 * Create a new instance of the receiver. Store the values in context in the
	 * node looked up by qualifier.
	 * 
	 * @param context
	 *            the scope to store to
	 * @param qualifier
	 *            the qualifer used to look up the preference node
	 */
	public ScopedPreferenceAccessor(final IScopeContext context, final String qualifier) {
		this.storeContext = context;
		this.nodeQualifier = qualifier;
		this.defaultQualifier = qualifier;
	}

	/**
	 * Does its best at determining the default value for the given key. Checks
	 * the given object's type and then looks in the list of defaults to see if
	 * a value exists. If not or if there is a problem converting the value, the
	 * default default value for that type is returned.
	 * 
	 * @param key
	 *            the key to search
	 * @param obj
	 *            the object who default we are looking for
	 * @return Object or <code>null</code>
	 */
	Object getDefault(final String key, final Object obj) {
		final IEclipsePreferences defaults = getDefaultPreferences();
		if (obj instanceof String) {
			return defaults.get(key, IPreferenceAccessor.STRING_DEFAULT_DEFAULT);
		} else if (obj instanceof Integer) {
			return Integer.valueOf(defaults.getInt(key, IPreferenceAccessor.INT_DEFAULT_DEFAULT));
		} else if (obj instanceof Double) {
			return new Double(defaults.getDouble(key, IPreferenceAccessor.DOUBLE_DEFAULT_DEFAULT));
		} else if (obj instanceof Float) {
			return new Float(defaults.getFloat(key, IPreferenceAccessor.FLOAT_DEFAULT_DEFAULT));
		} else if (obj instanceof Long) {
			return Long.valueOf(defaults.getLong(key, IPreferenceAccessor.LONG_DEFAULT_DEFAULT));
		} else if (obj instanceof Boolean) {
			return defaults.getBoolean(key, IPreferenceAccessor.BOOLEAN_DEFAULT_DEFAULT) ? Boolean.TRUE : Boolean.FALSE; //SUPPRESS CHECKSTYLE Inline
		} else {
			return null;
		}
	}

	/**
	 * Return the IEclipsePreferences node associated with this store.
	 * 
	 * @return the preference node for this store
	 */
	IEclipsePreferences getStorePreferences() {
		return this.storeContext.getNode(this.nodeQualifier);
	}

	/**
	 * Return the default IEclipsePreferences for this store.
	 * 
	 * @return this store's default preference node
	 */
	private IEclipsePreferences getDefaultPreferences() {
		return this.defaultContext.getNode(this.defaultQualifier);
	}

	/**
	 * Return the preference path to search preferences on. This is the list of
	 * preference nodes based on the scope contexts for this store. If there are
	 * no search contexts set, then return this store's context.
	 * <p>
	 * Whether or not the default context should be included in the resulting
	 * list is specified by the <code>includeDefault</code> parameter.
	 * </p>
	 * 
	 * @param includeDefault
	 *            <code>true</code> if the default context should be included
	 *            and <code>false</code> otherwise
	 * @return IEclipsePreferences[]
	 * @since 3.4 public, was added in 3.1 as private method
	 */
	public IEclipsePreferences[] getPreferenceNodes(final boolean includeDefault) {
		// if the user didn't specify a search order, then return the scope that
		// this store was created on. (and optionally the default)
		if (this.searchContexts == null) {
			if (includeDefault) {
				return new IEclipsePreferences[] { getStorePreferences(), getDefaultPreferences() };
			}
			return new IEclipsePreferences[] { getStorePreferences() };
		}
		// otherwise the user specified a search order so return the appropriate
		// nodes based on it
		int length = this.searchContexts.length;
		if (includeDefault) {
			length++;
		}
		final IEclipsePreferences[] preferences = new IEclipsePreferences[length];
		for (int i = 0; i < this.searchContexts.length; i++) {
			preferences[i] = this.searchContexts[i].getNode(this.nodeQualifier);
		}
		if (includeDefault) {
			preferences[length - 1] = getDefaultPreferences();
		}
		return preferences;
	}

	/**
	 * Set the search contexts to scopes. When searching for a value the seach
	 * will be done in the order of scope contexts and will not search the
	 * storeContext unless it is in this list.
	 * <p>
	 * If the given list is <code>null</code>, then clear this store's search
	 * contexts. This means that only this store's scope context and default
	 * scope will be used during preference value searching.
	 * </p>
	 * <p>
	 * The defaultContext will be added to the end of this list automatically
	 * and <em>MUST NOT</em> be included by the user.
	 * </p>
	 * 
	 * @param scopes
	 *            a list of scope contexts to use when searching, or
	 *            <code>null</code>
	 */
	public void setSearchContexts(final IScopeContext[] scopes) {
		this.searchContexts = scopes;
		if (scopes == null) {
			return;
		}

		// Assert that the default was not included (we automatically add it to
		// the end)
		for (int i = 0; i < scopes.length; i++) {
			if (scopes[i].equals(this.defaultContext)) {
				Assert.isTrue(false, "Do not add the default to the search contexts");
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.IPreferenceStore#contains(java.lang.String)
	 */
	public boolean contains(final String name) {
		if (name == null) {
			return false;
		}
		return (Platform.getPreferencesService().get(name, null, getPreferenceNodes(true))) != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.IPreferenceStore#getBoolean(java.lang.String)
	 */
	public boolean getBoolean(final String name) {
		final String value = internalGet(name);
		return (value == null) ? IPreferenceAccessor.BOOLEAN_DEFAULT_DEFAULT : Boolean.valueOf(value).booleanValue(); //SUPPRESS CHECKSTYLE Inline
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.IPreferenceStore#getDefaultBoolean(java.lang.String)
	 */
	public boolean getDefaultBoolean(final String name) {
		return getDefaultPreferences().getBoolean(name, IPreferenceAccessor.BOOLEAN_DEFAULT_DEFAULT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.IPreferenceStore#getDefaultDouble(java.lang.String)
	 */
	public double getDefaultDouble(final String name) {
		return getDefaultPreferences().getDouble(name, IPreferenceAccessor.DOUBLE_DEFAULT_DEFAULT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.IPreferenceStore#getDefaultFloat(java.lang.String)
	 */
	public float getDefaultFloat(final String name) {
		return getDefaultPreferences().getFloat(name, IPreferenceAccessor.FLOAT_DEFAULT_DEFAULT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.IPreferenceStore#getDefaultInt(java.lang.String)
	 */
	public int getDefaultInt(final String name) {
		return getDefaultPreferences().getInt(name, IPreferenceAccessor.INT_DEFAULT_DEFAULT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.IPreferenceStore#getDefaultLong(java.lang.String)
	 */
	public long getDefaultLong(final String name) {
		return getDefaultPreferences().getLong(name, IPreferenceAccessor.LONG_DEFAULT_DEFAULT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.IPreferenceStore#getDefaultString(java.lang.String)
	 */
	public String getDefaultString(final String name) {
		return getDefaultPreferences().get(name, IPreferenceAccessor.STRING_DEFAULT_DEFAULT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.IPreferenceStore#getDouble(java.lang.String)
	 */
	public double getDouble(final String name) {
		final String value = internalGet(name);
		if (value == null) {
			return IPreferenceAccessor.DOUBLE_DEFAULT_DEFAULT;
		}
		try {
			return Double.parseDouble(value);
		} catch (final NumberFormatException e) {
			return IPreferenceAccessor.DOUBLE_DEFAULT_DEFAULT;
		}
	}

	/**
	 * Return the string value for the specified key. Look in the nodes which
	 * are specified by this object's list of search scopes. If the value does
	 * not exist then return <code>null</code>.
	 * 
	 * @param key
	 *            the key to search with
	 * @return String or <code>null</code> if the value does not exist.
	 */
	private String internalGet(final String key) {
		return Platform.getPreferencesService().get(key, null, getPreferenceNodes(true));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.IPreferenceStore#getFloat(java.lang.String)
	 */
	public float getFloat(final String name) {
		final String value = internalGet(name);
		if (value == null) {
			return IPreferenceAccessor.FLOAT_DEFAULT_DEFAULT;
		}
		try {
			return Float.parseFloat(value);
		} catch (final NumberFormatException e) {
			return IPreferenceAccessor.FLOAT_DEFAULT_DEFAULT;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.IPreferenceStore#getInt(java.lang.String)
	 */
	public int getInt(final String name) {
		final String value = internalGet(name);
		if (value == null) {
			return IPreferenceAccessor.INT_DEFAULT_DEFAULT;
		}
		try {
			return Integer.parseInt(value);
		} catch (final NumberFormatException e) {
			return IPreferenceAccessor.INT_DEFAULT_DEFAULT;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.IPreferenceStore#getLong(java.lang.String)
	 */
	public long getLong(final String name) {
		final String value = internalGet(name);
		if (value == null) {
			return IPreferenceAccessor.LONG_DEFAULT_DEFAULT;
		}
		try {
			return Long.parseLong(value);
		} catch (final NumberFormatException e) {
			return IPreferenceAccessor.LONG_DEFAULT_DEFAULT;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.IPreferenceStore#getString(java.lang.String)
	 */
	public String getString(final String name) {
		final String value = internalGet(name);
		return (value == null) ? IPreferenceAccessor.STRING_DEFAULT_DEFAULT : value; //SUPPRESS CHECKSTYLE Inline
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.IPreferenceStore#isDefault(java.lang.String)
	 */
	public boolean isDefault(final String name) {
		if (name == null) {
			return false;
		}
		return (Platform.getPreferencesService().get(name, null, getPreferenceNodes(false))) == null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.IPreferenceStore#needsSaving()
	 */
	public boolean needsSaving() {
		return this.dirty;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.IPreferenceStore#putValue(java.lang.String,
	 *      java.lang.String)
	 */
	public void putValue(final String name, final String value) {
		try {
			// Do not notify listeners
			this.silentRunning = true;
			getStorePreferences().put(name, value);
		} finally {
			// Be sure that an exception does not stop property updates
			this.silentRunning = false;
			this.dirty = true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.IPreferenceStore#setDefault(java.lang.String,
	 *      double)
	 */
	public void setDefault(final String name, final double value) {
		getDefaultPreferences().putDouble(name, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.IPreferenceStore#setDefault(java.lang.String,
	 *      float)
	 */
	public void setDefault(final String name, final float value) {
		getDefaultPreferences().putFloat(name, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.IPreferenceStore#setDefault(java.lang.String,
	 *      int)
	 */
	public void setDefault(final String name, final int value) {
		getDefaultPreferences().putInt(name, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.IPreferenceStore#setDefault(java.lang.String,
	 *      long)
	 */
	public void setDefault(final String name, final long value) {
		getDefaultPreferences().putLong(name, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.IPreferenceStore#setDefault(java.lang.String,
	 *      java.lang.String)
	 */
	public void setDefault(final String name, final String defaultObject) {
		getDefaultPreferences().put(name, defaultObject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.IPreferenceStore#setDefault(java.lang.String,
	 *      boolean)
	 */
	public void setDefault(final String name, final boolean value) {
		getDefaultPreferences().putBoolean(name, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.IPreferenceStore#setToDefault(java.lang.String)
	 */
	public void setToDefault(final String name) {

		final String oldValue = getString(name);
		final String defaultValue = getDefaultString(name);
		try {
			this.silentRunning = true; // Turn off updates from the store
			// removing a non-existing preference is a no-op so call the Core
			// API directly
			getStorePreferences().remove(name);
			if (!oldValue.equals(defaultValue)) {
				this.dirty = true;
				//				firePropertyChangeEvent(name, oldValue, defaultValue);
			}

		} finally {
			this.silentRunning = false; // Restart listening to preferences
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.IPreferenceStore#setValue(java.lang.String,
	 *      double)
	 */
	public void setValue(final String name, final double value) {
		final double oldValue = getDouble(name);
		if (oldValue == value) {
			return;
		}
		try {
			this.silentRunning = true; // Turn off updates from the store
			if (getDefaultDouble(name) == value) {
				getStorePreferences().remove(name);
			} else {
				getStorePreferences().putDouble(name, value);
			}
			this.dirty = true;
			//			firePropertyChangeEvent(name, new Double(oldValue), new Double(value));
		} finally {
			this.silentRunning = false; // Restart listening to preferences
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.IPreferenceStore#setValue(java.lang.String,
	 *      float)
	 */
	public void setValue(final String name, final float value) {
		final float oldValue = getFloat(name);
		if (oldValue == value) {
			return;
		}
		try {
			this.silentRunning = true; // Turn off updates from the store
			if (getDefaultFloat(name) == value) {
				getStorePreferences().remove(name);
			} else {
				getStorePreferences().putFloat(name, value);
			}
			this.dirty = true;
			//			firePropertyChangeEvent(name, new Float(oldValue), new Float(value));
		} finally {
			this.silentRunning = false; // Restart listening to preferences
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.IPreferenceStore#setValue(java.lang.String,
	 *      int)
	 */
	public void setValue(final String name, final int value) {
		final int oldValue = getInt(name);
		if (oldValue == value) {
			return;
		}
		try {
			this.silentRunning = true; // Turn off updates from the store
			if (getDefaultInt(name) == value) {
				getStorePreferences().remove(name);
			} else {
				getStorePreferences().putInt(name, value);
			}
			this.dirty = true;
			//			firePropertyChangeEvent(name, new Integer(oldValue), new Integer(value));
		} finally {
			this.silentRunning = false; // Restart listening to preferences
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.IPreferenceStore#setValue(java.lang.String,
	 *      long)
	 */
	public void setValue(final String name, final long value) {
		final long oldValue = getLong(name);
		if (oldValue == value) {
			return;
		}
		try {
			this.silentRunning = true; // Turn off updates from the store
			if (getDefaultLong(name) == value) {
				getStorePreferences().remove(name);
			} else {
				getStorePreferences().putLong(name, value);
			}
			this.dirty = true;
			//			firePropertyChangeEvent(name, new Long(oldValue), new Long(value));
		} finally {
			this.silentRunning = false; // Restart listening to preferences
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.IPreferenceStore#setValue(java.lang.String,
	 *      java.lang.String)
	 */
	public void setValue(final String name, final String value) {
		// Do not turn on silent running here as Strings are propagated
		if (getDefaultString(name).equals(value)) {
			getStorePreferences().remove(name);
		} else {
			getStorePreferences().put(name, value);
		}
		this.dirty = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.IPreferenceStore#setValue(java.lang.String,
	 *      boolean)
	 */
	public void setValue(final String name, final boolean value) {
		final boolean oldValue = getBoolean(name);
		if (oldValue == value) {
			return;
		}
		try {
			this.silentRunning = true; // Turn off updates from the store
			if (getDefaultBoolean(name) == value) {
				getStorePreferences().remove(name);
			} else {
				getStorePreferences().putBoolean(name, value);
			}
			this.dirty = true;
			//			firePropertyChangeEvent(name, oldValue ? Boolean.TRUE : Boolean.FALSE, value ? Boolean.TRUE : Boolean.FALSE);
		} finally {
			this.silentRunning = false; // Restart listening to preferences
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.IPersistentPreferenceStore#save()
	 */
	public void save() throws IOException {
		try {
			getStorePreferences().flush();
			this.dirty = false;
		} catch (final BackingStoreException e) {
			IOException ex = new IOException(e.getMessage());
			ex.initCause(e);
			throw ex;
		}

	}

	public void addPreferenceChangeListener(final IPreferenceChangeListener listener) {
		getStorePreferences().addPreferenceChangeListener(listener);
	}

	public void removePreferenceChangeListener(final IPreferenceChangeListener listener) {
		getStorePreferences().removePreferenceChangeListener(listener);
	}
}
