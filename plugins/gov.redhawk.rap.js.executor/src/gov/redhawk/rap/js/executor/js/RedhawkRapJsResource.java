package gov.redhawk.rap.js.executor.js;

import org.eclipse.rwt.resources.IResource;
import org.eclipse.rwt.resources.IResourceManager.RegisterOptions;

/**
 * Javascript that is loaded to assist with RAP/OWF integration.
 */
public class RedhawkRapJsResource implements IResource {
	private static final String CLIENT_LIBRARY_VARIANT = "org.eclipse.rwt.clientLibraryVariant";
	private static final String DEBUG_CLIENT_LIBRARY_VARIANT = "DEBUG";
	
	@Override
	public String getLocation() {
		return "gov/redhawk/rap/js/executor/js/rh-rap-debug.js";
	}
	
	@Override
	public ClassLoader getLoader() {
		return this.getClass().getClassLoader();
	}

	@Override
	public String getCharset() {
		return "UTF-8";
	}

	@Override
	public RegisterOptions getOptions() {
		if (isDebugVariant()) {
			return RegisterOptions.VERSION;
		} else {
			return RegisterOptions.VERSION_AND_COMPRESS;
		}
	}

	@Override
	public boolean isJSLibrary() {
		return true;
	}

	@Override
	public boolean isExternal() {
		return false;
	}
	
	private static boolean isDebugVariant() {
	    String libraryVariant = System.getProperty( CLIENT_LIBRARY_VARIANT );
	    return DEBUG_CLIENT_LIBRARY_VARIANT.equals( libraryVariant );
	}

}
