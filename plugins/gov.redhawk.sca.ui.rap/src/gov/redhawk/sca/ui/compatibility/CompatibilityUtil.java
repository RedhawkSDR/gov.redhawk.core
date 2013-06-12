/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.sca.ui.compatibility;

import gov.redhawk.sca.rap.RapInit;

import java.security.Principal;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.rwt.lifecycle.UICallBack;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;

public class CompatibilityUtil {

	private CompatibilityUtil() {
		//Prevent instantiation
	}
	
	public static void setFontDataStyle(FontData fontData, int style) {
		// Cannot set style on font data in RAP
	}
	
	public static void disableComboWheelScrollSelect(ComboViewer viewer) {
		// Does not support mouse wheel do nothing
	}
	
	public static Principal getUserPrincipal(Display display) {
		return RapInit.getUserPrincipal(display);
	}
	
	public static void runinFakeUIContext(Display display, Runnable runnable) {
		UICallBack.runNonUIThreadWithFakeContext(display, runnable);
	}
	
	public static void activateUIConnection(String id) {
		UICallBack.activate(id);
	}
	
	public static void deactivateUIConnection(String id) {
		UICallBack.deactivate(id);
	}
}
