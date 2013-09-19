package gov.redhawk.sca.ui.internal;

import gov.redhawk.sca.ScaPlugin;
import gov.redhawk.sca.ui.ScaUiPlugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;

import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.rwt.service.ISettingStore;
import org.eclipse.rwt.service.SettingStoreEvent;
import org.eclipse.rwt.service.SettingStoreException;
import org.eclipse.rwt.service.SettingStoreListener;

public class ScaSettingStore implements ISettingStore {

	Properties props = new Properties();
	private String filePath;
	private String userId;
	private ListenerList listeners = new ListenerList();
	private boolean silentRunning;

	@Override
	public String getAttribute(String name) {
		try {
			//Probably not necessary
			this.silentRunning = true;
			loadProps();
			this.silentRunning = false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return props.getProperty(name);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		return Collections.enumeration(props.stringPropertyNames());
	}

	@Override
	public void setAttribute(String name, String value)
			throws SettingStoreException {
		SettingStoreEvent event = new SettingStoreEvent(this, name, props.getProperty(name), value);
		if (value != null) {
			props.setProperty(name, value);
		} else {
			props.remove(name);
		}
		fireSettingStoreEvent(event);
		try {
			saveProps();
		} catch (IOException e) {
			throw new SettingStoreException("Failed to save SettingStore", e);
		}
	}

	@Override
	public void removeAttribute(String name) throws SettingStoreException {
		SettingStoreEvent event = new SettingStoreEvent(this, name, props.getProperty(name), null);
		props.remove(name);
		fireSettingStoreEvent(event);
	}

	@Override
	public void loadById(String id) throws SettingStoreException {
		this.userId = id;
		String path = getPropsFilePath(id);
		File propsFile = new File(path + File.separator + "user-prefs.prop");
		try {
			System.err.println("CREATE FILE: " + propsFile.getAbsolutePath());
			propsFile.createNewFile();
		} catch (IOException e) {
			throw new SettingStoreException("Failed to create Properties file for SettingStore", e);
		}
		this.filePath = propsFile.getAbsolutePath();
		try {
			loadProps();
		} catch (IOException e) {
			throw new SettingStoreException("Failed to load SettingStore", e);
		}
	}

	private String getPropsFilePath(String id) {
		final File file = new File(ScaPlugin.getDefault().getStateLocation()
				+ File.separator + id);
		file.mkdirs();
		return file.getAbsolutePath();
	}

	private void loadProps() throws IOException, FileNotFoundException {
		Enumeration<String> names = getAttributeNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			String value = props.getProperty(name);
			props.remove(name);
			if (!silentRunning) {
				SettingStoreEvent event = new SettingStoreEvent(this, name, value, null);
				fireSettingStoreEvent(event);
			}
		}
		props.load(new FileInputStream(this.filePath));
		names = getAttributeNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			String value = props.getProperty(name);
			if (!silentRunning) {
				SettingStoreEvent event = new SettingStoreEvent(this, name, null, value);
				fireSettingStoreEvent(event);
			}
		}
	}

	private void saveProps() throws FileNotFoundException, IOException {
		props.store(new FileOutputStream(this.filePath), "");
	}

	@Override
	public String getId() {
		return this.userId;
	}

	protected void fireSettingStoreEvent(final SettingStoreEvent event) {
		if (equals(event.getOldValue(), event.getNewValue())) {
			return;
		}
		for (final Object obj : listeners.getListeners()) {
			SafeRunner.run(new ISafeRunnable() {

				@Override
				public void run() throws Exception {
					((SettingStoreListener)obj).settingChanged(event);
				}

				@Override
				public void handleException(Throwable exception) {

				}
			});
		}
	}

	private boolean equals(String oldValue, String newValue) {
		if (oldValue == newValue) {
			return true;
		} else if (oldValue != null) {
			return oldValue.equals(newValue);
		} else {
			return false;
		}
	}

	@Override
	public void addSettingStoreListener(SettingStoreListener listener) {
		this.listeners.add(listener);
	}

	@Override
	public void removeSettingStoreListener(SettingStoreListener listener) {
		this.listeners.remove(listener);
	}



}
