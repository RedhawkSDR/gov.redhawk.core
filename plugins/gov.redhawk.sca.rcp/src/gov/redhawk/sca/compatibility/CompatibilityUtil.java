package gov.redhawk.sca.compatibility;

import java.io.File;

/**
 * @since 6.1
 */
public class CompatibilityUtil implements ICompatibilityUtil {

	
	/* (non-Javadoc)
	 * @see gov.redhawk.sca.compatibility.ICompatibilityUtil#getUserSpecificPath(java.lang.Object)
	 */
	@Override
	public String getUserSpecificPath(Object context) {
		return "";
	}

	/* (non-Javadoc)
	 * @see gov.redhawk.sca.compatibility.ICompatibilityUtil#initializeSettingStore(java.lang.Object)
	 */
	@Override
	public void initializeSettingStore(Object context) {
		throw new UnsupportedOperationException("This method is used in RAP only");
	}

	/* (non-Javadoc)
	 * @see gov.redhawk.sca.compatibility.ICompatibilityUtil#getSettingStoreWorkDir()
	 */
	@Override
	public File getSettingStoreWorkDir() {
		throw new UnsupportedOperationException("This method is used in RAP only");
	}

}
