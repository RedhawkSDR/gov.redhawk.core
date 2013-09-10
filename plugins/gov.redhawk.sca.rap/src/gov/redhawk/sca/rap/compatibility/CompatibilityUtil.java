package gov.redhawk.sca.rap.compatibility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;

import org.eclipse.rwt.RWT;
import org.eclipse.swt.widgets.Display;

import gov.redhawk.sca.compatibility.ICompatibilityUtil;
import gov.redhawk.sca.rap.RapInit;

public class CompatibilityUtil implements ICompatibilityUtil {

	public String getUserSpecificPath(Object context) {
		//		if (context == null || !(context instanceof Display)) {
		//			return "";
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
		//		} else {
		//			return "";
		//		}
		Display display = (Display) context;
		final String[] result = new String[1];
		final boolean[] waitForResult = {true};
		RWT.requestThreadExec(new Runnable() {

			@Override
			public void run() {
				result[0] = RWT.getRequest().getSession().getId();
				waitForResult[0] = false;
			}

		});
		
		while (waitForResult[0]) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
		return result[0];
	}
}
