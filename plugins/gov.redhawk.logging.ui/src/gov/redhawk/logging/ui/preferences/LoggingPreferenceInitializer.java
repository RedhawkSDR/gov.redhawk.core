package gov.redhawk.logging.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import gov.redhawk.logging.ui.LoggingUiPlugin;

public class LoggingPreferenceInitializer extends AbstractPreferenceInitializer {
	public static final String SHOW_LOG_CONFIG_WARNING = "showLogConfigWarning";

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = LoggingUiPlugin.getDefault().getPreferenceStore();
		
		store.setDefault(LoggingPreferenceInitializer.SHOW_LOG_CONFIG_WARNING, true);
	}

}
