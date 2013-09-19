package gov.redhawk.sca.compatibility;

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
}
