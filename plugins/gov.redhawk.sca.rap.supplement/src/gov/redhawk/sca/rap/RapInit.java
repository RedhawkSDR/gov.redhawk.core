/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.sca.rap;

import java.security.Principal;

import java.security.cert.X509Certificate;
import javax.servlet.http.HttpServletRequest;

import org.eclipse.rwt.RWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorSite;

public class RapInit {
	
	private RapInit() {
		
	}

	/**
	 *
	 * @deprecated Since 1.1 use {@link #getUserPrincipal(Display)} instead.
	 */
	@Deprecated
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

	/** @since 1.1 */
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
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					//PASS
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
					X509Certificate[] certs = (X509Certificate[]) RWT.getRequest().getAttribute("javax.servlet.request.X509Certificate");
					if (certs != null && certs.length > 0) {
						user[0] = certs[0].getSubjectDN();
					}
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
