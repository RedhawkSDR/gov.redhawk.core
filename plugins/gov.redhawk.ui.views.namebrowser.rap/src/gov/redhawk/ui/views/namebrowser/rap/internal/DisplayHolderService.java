package gov.redhawk.ui.views.namebrowser.rap.internal;
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

import org.eclipse.rwt.lifecycle.UICallBack;
import org.eclipse.swt.widgets.Display;

import gov.redhawk.ui.views.namebrowser.IDisplayHolder;

/**
 * This class is used to maintain a reference to the Display used
 * for each UI Session, using the session singleton instance
 * of {@link #DisplayHolder}.
 *
 */

public class DisplayHolderService implements IDisplayHolder {


	@Override
	public void setSessionDisplay(final Display display) {
		UICallBack.runNonUIThreadWithFakeContext(display, new Runnable() {

			@Override
			public void run() {
				DisplayHolder displayHolder = DisplayHolder.getInstance();
				displayHolder.setDisplay(display);
			}
			
		});
	}

	@Override
	public Display getSessionDisplay(Display display) {
		final boolean[] done = {false};
		final Display[] result = new Display[1];
		UICallBack.runNonUIThreadWithFakeContext(display, new Runnable() {

			@Override
			public void run() {
				DisplayHolder displayHolder = DisplayHolder.getInstance();
				result[0] = displayHolder.getDisplay();
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
		
		return result[0];
	}

}
