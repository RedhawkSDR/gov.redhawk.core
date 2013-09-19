package gov.redhawk.sca.rap.internal;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.core.runtime.Status;
import org.eclipse.rwt.RWT;
import org.eclipse.rwt.service.ISettingStore;
import org.eclipse.rwt.service.SettingStoreException;
import org.eclipse.swt.widgets.Display;

import gov.redhawk.sca.ScaPlugin;
import gov.redhawk.sca.compatibility.ICompatibilityUtil;
import gov.redhawk.sca.rap.RapInit;

public class CompatibilityUtil implements ICompatibilityUtil {

	private static final String KEY_SHARED_DOMAINS = "gov.redhawk.sca.sharedDomains";

	public String getUserSpecificPath(Object context) {
		if (Boolean.valueOf(System.getProperty(KEY_SHARED_DOMAINS))) {
			return "";
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

		while (waitForResult[0]) {
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
			store.loadById(ScaPlugin.getDefault().getCompatibilityUtil().getUserSpecificPath(context));
		} catch (SettingStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
