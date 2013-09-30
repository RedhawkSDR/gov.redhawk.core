package gov.redhawk.sca.rap.internal;

import gov.redhawk.entrypoint.scaExplorer.EntrypointActivator;
import gov.redhawk.sca.ScaPlugin;
import gov.redhawk.sca.compatibility.ICompatibilityUtil;

import java.io.File;
import java.lang.reflect.Field;

import org.eclipse.core.runtime.Status;
import org.eclipse.rwt.RWT;
import org.eclipse.rwt.service.FileSettingStore;
import org.eclipse.rwt.service.ISettingStore;
import org.eclipse.rwt.service.SettingStoreException;
import org.eclipse.swt.widgets.Display;

public class CompatibilityUtil implements ICompatibilityUtil {

	private static final String FIELD_WORKDIR = "workDir";

	public String getUserSpecificPath(Object context) {
		if (Boolean.valueOf(System.getProperty(EntrypointActivator.PROP_SHARED_DOMAINS))) {
			return "SHARED";
		}
		//		if (context == null || !(context instanceof Display)) {
		//			context = Display.getCurrent();
		//			if (context == null) {
		//				return "";
		//			}
		//		}
		//		Principal user = RapInit.getUserPrincipal((Display) context);
		//		if (user != null) {
		//			String dn = user.getName();
		//			MessageDigest hashSum = null;
		//			try {
		//				hashSum = MessageDigest.getInstance("SHA-256");
		//			} catch (NoSuchAlgorithmException e) {
		//				return "";
		//			}
		//			return new String(hashSum.digest(dn.getBytes()));
		//		}
		//		return "";

		//BEGIN TEMP CODE
		/* return user agent instead of DN, for dev testing */
		Display display = (Display) context;
		final String[] result = new String[1];
		final boolean[] waitForResult = {true};
		RWT.requestThreadExec(new Runnable() {

			@Override
			public void run() {
				String agent = RWT.getRequest().getHeader("User-Agent");
				if (agent.contains("Firefox")) {
					result[0] = "Firefox";
				} else if (agent.contains("Chrome")) {
					result[0] = "Chrome";
				} else {
					result[0] = "unknown";
				}
				waitForResult[0] = false;
			}

		});

		while (waitForResult[0] && display != null) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		return result[0];
		//END TEMP CODE
	}

	@Override
	public void initializeSettingStore(Object context) {
		ISettingStore store =  RWT.getSettingStore();
		try {
			store.loadById(getUserSpecificPath(context));
		} catch (SettingStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public File getSettingStoreWorkDir() {
		ISettingStore store = RWT.getSettingStore();
		if (store instanceof FileSettingStore) {
			Class<?> clazz = FileSettingStore.class;
			try {
				Field f = clazz.getDeclaredField(FIELD_WORKDIR);
				f.setAccessible(true);
				Object file = f.get(store);
				if (file instanceof File) {
					return ((File) file);
				}
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				ScaPlugin.getDefault().getLog().log(
						new Status(Status.ERROR, ScaPlugin.PLUGIN_ID, "Unable to determine SettingStore work directory", e));
			}
		}
		return null;
	}
}
