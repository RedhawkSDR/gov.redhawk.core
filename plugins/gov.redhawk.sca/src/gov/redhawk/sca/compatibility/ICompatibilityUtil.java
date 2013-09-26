package gov.redhawk.sca.compatibility;

import java.io.File;

/**
 * @since 6.1
 */
public interface ICompatibilityUtil {

	public String getUserSpecificPath(Object context);
	
	/**
	 * Initialize setting store. Used only in RAP, to initialize the
	 * SettingStore with preference values scoped for the current user.
	 * 
	 * @param context
	 *            the current Display
	 */
	public void initializeSettingStore(Object context);
	
	/**
	 * Gets the RAP SettingStore work directory.
	 * 
	 * @return the directory in which the SettingStore is storing
	 * user-specific preferences
	 */
	public File getSettingStoreWorkDir();
}
