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

import org.eclipse.rwt.SessionSingletonBase;
import org.eclipse.swt.widgets.Display;

/**
 * @since 1.2
 */
public class DisplayHolder extends SessionSingletonBase {

	private Display display;
	
	//force singleton
	private DisplayHolder(){}

	public static DisplayHolder getInstance() {
		return SessionSingletonBase.getInstance(DisplayHolder.class);
	}
	
	public void setDisplay(Display display) {
		this.display = display;
	}
	
	public Display getDisplay() {
		return this.display;
	}
}
