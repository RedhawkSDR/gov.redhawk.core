package gov.redhawk.sca.compatibility;

import java.io.File;

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

	@Override
	public void initializeSettingStore(Object context) {
		throw new UnsupportedOperationException("This method is used in RAP only");
	}

	@Override
	public File getSettingStoreWorkDir() {
		throw new UnsupportedOperationException("This method is used in RAP only");
	}

}
