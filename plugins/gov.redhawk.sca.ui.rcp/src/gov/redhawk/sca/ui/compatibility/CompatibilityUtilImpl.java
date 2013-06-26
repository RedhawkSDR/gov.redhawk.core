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
 * This class is meant as an extension mechanism to single source RCP / RAP applications
 * 
 * @since 1.2
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
public class CompatibilityUtilImpl implements ICompatibilityUtil {

	public void setFontDataStyle(FontData fontData, int style) {
		fontData.setStyle(style);
	}
	
	public void disableComboWheelScrollSelect(ComboViewer viewer) {
		viewer.getCombo().addListener(SWT.MouseVerticalWheel, new Listener() {

			public void handleEvent(Event event) {
				// Disable Mouse Wheel Combo Box Control
				event.doit = false;
			}

		});
	}
	
	public Principal getUserPrincipal(Display display) {
		return null;
	}
	
	/**
	 * @since 1.2
	 * @param display
	 * @param runnable
	 */
	public void runInFakeUIContext(Display display, Runnable runnable) {
		//No RAP implementation
	}
	
	/**
	 * @since 1.2
	 * @param display
	 * @param runnable
	 */
	public void activateUIConnection(String id) {
		//No RAP implementation
	}
	
	public void deactivateUIConnection(String id) {
		//No RAP implementation
	}
}