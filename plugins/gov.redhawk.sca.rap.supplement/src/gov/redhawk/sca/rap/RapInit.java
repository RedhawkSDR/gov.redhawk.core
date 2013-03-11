package gov.redhawk.sca.rap;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.rwt.RWT;
import org.eclipse.ui.IEditorSite;

public class RapInit {
	
	public static void init(final IEditorSite site) {
		final HttpServletRequest req = RWT.getRequest();
		RWT.requestThreadExec(new Runnable() {

			public void run() {
				Principal user = null;
				if (RWT.getRequest() != null) {
					user = RWT.getRequest().getUserPrincipal();
				}
				if (site != null) {
					if (user != null) {
						site.getActionBars().getStatusLineManager().setMessage(user.getName());
					} else {
						site.getActionBars().getStatusLineManager().setMessage("Unknown user for SSL Session: "
								+ req.getAttribute("javax.servlet.request.ssl_session"));
					}
				}
			}
			
		});
	}

}
