package gov.redhawk.sca.ui;

import gov.redhawk.sca.ScaPlugin;
import gov.redhawk.sca.util.ScopedPreferenceAccessor;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IPreferenceNodeVisitor;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.jface.preference.PreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.widgets.Display;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

/**
 * @since 9.2
 */
public class UserSpecificScopeContext implements IScopeContext {

	protected static final String STRING_DEFAULT = null;
	private Display display;
	private PreferenceStore prefsStore;

	public UserSpecificScopeContext(Display display) {
		this.display = display;
		File dir = new File(getUserSpecificLocation());
		File file = new File(getUserSpecificLocation() + File.separator + "user-prefs");
		try {
			dir.mkdirs();
			boolean created = file.createNewFile();
			System.err.println("Prefs file created: " + created);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.prefsStore = new PreferenceStore(getUserSpecificLocation() + File.separator + "user-prefs");
		try {
			this.prefsStore.load();
		} catch (IOException e) {
			ScaUiPlugin.logError("Unable to load user-specific preference value", e);
		}
		System.err.println("Prefs Store Lcoation: " + getUserSpecificLocation() + File.separator + "user-prefs");
	}

	@Override
	public String getName() {
		return "User-Specific Scope Context";
	}
	
	IEclipsePreferences prefs = null;

	@Override
	public IEclipsePreferences getNode(String qualifier) {
		prefs = new IEclipsePreferences() {

			private Map<IPreferenceChangeListener, IPropertyChangeListener> listenerMap = new HashMap<IPreferenceChangeListener, IPropertyChangeListener>();

			@Override
			public void put(String key, String value) {
				UserSpecificScopeContext.this.prefsStore.putValue(key, value);
				try {
					UserSpecificScopeContext.this.prefsStore.save();
				} catch (IOException e) {
					ScaUiPlugin.logError("Unable to save user-specific preference", e);
				}
			}

			@Override
			public String get(String key, String def) {
				if (UserSpecificScopeContext.this.prefsStore.contains(key)) {
					return UserSpecificScopeContext.this.prefsStore.getString(key); 
				}
				return UserSpecificScopeContext.this.prefsStore.getDefaultString(key);
			}

			@Override
			public void remove(String key) {
				UserSpecificScopeContext.this.prefsStore.setToDefault(key);
				try {
					UserSpecificScopeContext.this.prefsStore.save();
				} catch (IOException e) {
					ScaUiPlugin.logError("Unable to save user-specific preference", e);
				}
			}

			@Override
			public void clear() throws BackingStoreException {
				String[] keys = UserSpecificScopeContext.this.prefsStore.preferenceNames();
				for (String key : keys) {
					UserSpecificScopeContext.this.prefsStore.setToDefault(key);
				}
				try {
					UserSpecificScopeContext.this.prefsStore.save();
				} catch (IOException e) {
					ScaUiPlugin.logError("Unable to save user-specific preference", e);
				}
			}

			@Override
			public void putInt(String key, int value) {
				UserSpecificScopeContext.this.prefsStore.setValue(key, value);
				try {
					UserSpecificScopeContext.this.prefsStore.save();
				} catch (IOException e) {
					ScaUiPlugin.logError("Unable to save user-specific preference", e);
				}
			}

			@Override
			public int getInt(String key, int def) {
				if (UserSpecificScopeContext.this.prefsStore.contains(key)) {
					return UserSpecificScopeContext.this.prefsStore.getInt(key); 
				}
				return UserSpecificScopeContext.this.prefsStore.getDefaultInt(key);
			}

			@Override
			public void putLong(String key, long value) {
				UserSpecificScopeContext.this.prefsStore.setValue(key, value);
				try {
					UserSpecificScopeContext.this.prefsStore.save();
				} catch (IOException e) {
					ScaUiPlugin.logError("Unable to save user-specific preference", e);
				}
			}

			@Override
			public long getLong(String key, long def) {
				if (UserSpecificScopeContext.this.prefsStore.contains(key)) {
					return UserSpecificScopeContext.this.prefsStore.getLong(key);
				}
				return UserSpecificScopeContext.this.prefsStore.getDefaultLong(key);
			}

			@Override
			public void putBoolean(String key, boolean value) {
				UserSpecificScopeContext.this.prefsStore.setValue(key, value);
				try {
					UserSpecificScopeContext.this.prefsStore.save();
				} catch (IOException e) {
					ScaUiPlugin.logError("Unable to save user-specific preference", e);
				}
			}

			@Override
			public boolean getBoolean(String key, boolean def) {
				if (UserSpecificScopeContext.this.prefsStore.contains(key)) {
					return UserSpecificScopeContext.this.prefsStore.getBoolean(key);
				}
				return UserSpecificScopeContext.this.prefsStore.getDefaultBoolean(key);
			}

			@Override
			public void putFloat(String key, float value) {
				UserSpecificScopeContext.this.prefsStore.setValue(key, value);
				try {
					UserSpecificScopeContext.this.prefsStore.save();
				} catch (IOException e) {
					ScaUiPlugin.logError("Unable to save user-specific preference", e);
				}
			}

			@Override
			public float getFloat(String key, float def) {
				if (UserSpecificScopeContext.this.prefsStore.contains(key)) {
					return UserSpecificScopeContext.this.prefsStore.getFloat(key);
				}
				return UserSpecificScopeContext.this.prefsStore.getDefaultFloat(key);
			}

			@Override
			public void putDouble(String key, double value) {
				UserSpecificScopeContext.this.prefsStore.setValue(key, value);
				try {
					UserSpecificScopeContext.this.prefsStore.save();
				} catch (IOException e) {
					ScaUiPlugin.logError("Unable to save user-specific preference", e);
				}
			}

			@Override
			public double getDouble(String key, double def) {
				if (UserSpecificScopeContext.this.prefsStore.contains(key)) {
					return UserSpecificScopeContext.this.prefsStore.getDouble(key);
				}
				return UserSpecificScopeContext.this.prefsStore.getDefaultDouble(key);
			}

			@Override
			public void putByteArray(String key, byte[] value) {
				throw new UnsupportedOperationException();
			}

			@Override
			public byte[] getByteArray(String key, byte[] def) {
				throw new UnsupportedOperationException();
			}

			@Override
			public String[] keys() throws BackingStoreException {
				return UserSpecificScopeContext.this.prefsStore.preferenceNames();
			}

			@Override
			public String[] childrenNames() throws BackingStoreException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Preferences parent() {
				// TODO Auto-generated method stub
				return getNode("/");
			}

			@Override
			public boolean nodeExists(String pathName)
					throws BackingStoreException {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public String name() {
				return UserSpecificScopeContext.this.getName();
			}

			@Override
			public String absolutePath() {
				return "/";
			}

			@Override
			public void flush() throws BackingStoreException {
				try {
					UserSpecificScopeContext.this.prefsStore.save();
				} catch (IOException e) {
					ScaUiPlugin.logError("Unable to save user-specific preference value", e);
				}
			}

			@Override
			public void sync() throws BackingStoreException {
				try {
					UserSpecificScopeContext.this.prefsStore.load();
				} catch (IOException e) {
					ScaUiPlugin.logError("Unable to load user-specific preference value", e);
				}
			}

			@Override
			public void addNodeChangeListener(INodeChangeListener listener) {
				//PASS
			}

			@Override
			public void removeNodeChangeListener(INodeChangeListener listener) {
				//PASS

			}

			@Override
			public void addPreferenceChangeListener(final IPreferenceChangeListener listener) {
				IPropertyChangeListener prefsListener = new IPropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent event) {
						PreferenceChangeEvent prefsEvent = new PreferenceChangeEvent(prefs, event.getProperty(), 
								event.getOldValue(), event.getNewValue());
						listener.preferenceChange(prefsEvent);
					}
				};
				this.listenerMap.put(listener, prefsListener);
				UserSpecificScopeContext.this.prefsStore.addPropertyChangeListener(prefsListener);
			}

			@Override
			public void removePreferenceChangeListener(final IPreferenceChangeListener listener) {
				UserSpecificScopeContext.this.prefsStore.removePropertyChangeListener(this.listenerMap.get(listener));
			}

			@Override
			public void removeNode() throws BackingStoreException {
				// TODO Auto-generated method stub

			}

			@Override
			public Preferences node(String path) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void accept(IPreferenceNodeVisitor visitor) throws BackingStoreException {
				// TODO Auto-generated method stub

			}

		};
		return prefs;
	}

	@Override
	public IPath getLocation() {
		return new Path( getUserSpecificLocation());
	}

	private String getUserSpecificLocation() {
		final URI fileUri = ScaUiPlugin.getDefault().getStateLocation().append(ScaPlugin.getDefault().getCompatibilityUtil().getUserSpecificPath(this.display)).toFile().toURI();
		final org.eclipse.emf.common.util.URI uri = org.eclipse.emf.common.util.URI.createURI(fileUri.toString());
		return fileUri.toString().replace("file:", "");
	}

}
