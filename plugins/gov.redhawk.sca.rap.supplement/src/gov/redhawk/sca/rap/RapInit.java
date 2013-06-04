package gov.redhawk.sca.rap;

import java.security.Principal;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.rwt.RWT;
import org.eclipse.rwt.service.ISessionStore;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorSite;

public class RapInit {
	
	/**
	 * 
	 * @deprecated Use {@link #getUserPrincipal(Display)} instead.
	 */
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
	
	public static Principal getUserPrincipal(final Display display) {
		final Principal[] user = new Principal[1];
		final Boolean[] done = new Boolean[1];
		done[0] = false;
		if (Display.getCurrent() == null) {
			display.asyncExec(new Runnable() {

				@Override
				public void run() {
					user[0] = findUser(display);
					done[0] = true;
				}
				
			});
			
			while (!done[0]) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
			return user[0];
		} else {
			return findUser(display);
		}
	}
	
	private static Principal findUser(Display display) {
		final Boolean[] done = new Boolean[1];
		done[0] = false;
		final Principal[] user = new Principal[1];
		RWT.requestThreadExec(new Runnable() {

			public void run() {
				if (RWT.getRequest() != null) {
					user[0] = RWT.getRequest().getUserPrincipal();
				}
				done[0] = true;
			}
			
		});
		
		while (!done[0]) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return user[0];
	}

}
