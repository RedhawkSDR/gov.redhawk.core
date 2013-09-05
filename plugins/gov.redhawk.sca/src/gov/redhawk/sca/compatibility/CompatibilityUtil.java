package gov.redhawk.sca.compatibility;

/**
 * @since 6.1
 */
public class CompatibilityUtil implements ICompatibilityUtil {

	
	/**
	 * RAP implementation returns a path component specific to the authenticated
	 * user. RCP implementation returns an empty string.
	 */
	@Override
	public String getUserSpecificPath(Object context) {
		return "";
	}

}
