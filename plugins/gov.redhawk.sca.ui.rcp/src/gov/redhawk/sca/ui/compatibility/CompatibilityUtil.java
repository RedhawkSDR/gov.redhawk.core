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
package gov.redhawk.sca.ui.compatibility;


import java.security.Principal;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * @since 1.1
 */
public class CompatibilityUtil {

	private CompatibilityUtil() {
		//Prevent instantiation
	}

	public static void setFontDataStyle(FontData fontData, int style) {
		fontData.setStyle(style);
	}
	
	public static void disableComboWheelScrollSelect(ComboViewer viewer) {
		viewer.getCombo().addListener(SWT.MouseVerticalWheel, new Listener() {

			public void handleEvent(Event event) {
				// Disable Mouse Wheel Combo Box Control
				event.doit = false;
			}

		});
	}
	
	public static Principal getUserPrincipal(Display display) {
		return null;
	}
	
	/**
	 * @since 1.2
	 * @param display
	 * @param runnable
	 */
	public static void runinFakeUIContext(Display display, Runnable runnable) {
		//No RAP implementation
	}
	
	/**
	 * @since 1.2
	 * @param display
	 * @param runnable
	 */
	public static void activateUIConnection(String id) {
		//No RAP implementation
	}
	
	public static void deactivateUIConnection(String id) {
		//No RAP implementation
	}
}
