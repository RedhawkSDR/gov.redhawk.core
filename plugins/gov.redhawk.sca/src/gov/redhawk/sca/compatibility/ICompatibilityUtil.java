package gov.redhawk.sca.compatibility;

import java.io.File;

/**
 * @since 6.1
 */
public interface ICompatibilityUtil {

	/**
	 * Returns a String that is uniquely associated with the current user
	 * (RAP) or an empty String (RCP), to be used as a path segment while
	 * persisting preferences.
	 * @param context
	 * 					The current Display (meaningful for RAP only), used
	 * to obtain a user-specific context.
	 * @return
	 * 			A String uniquely associated with the current user (RAP), or
	 * an empty String.
	 */
	public String getUserSpecificPath(Object context);
	
	/**
	 * Initialize the setting store. Used only in RAP, to initialize the
	 * SettingStore with preference values scoped and persisted for the
	 * current user.
	 * 
	 * @param context
	 *            the current Display
	 */
	public void initializeSettingStore(Object context);
	
	/**
	 * Gets the RAP SettingStore work directory.
	 * 
	 * @return 
	 * 			the directory in which the configured SettingStore is storing
	 * user-specific preferences
	 */
	public File getSettingStoreWorkDir();
}
