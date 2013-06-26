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
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;

/**
 * @since 9.1
 * @noimplement This interface is not intended to be implemented by clients.
 */
public interface ICompatibilityUtil {
	
	public void setFontDataStyle(FontData fontData, int style);

	public void disableComboWheelScrollSelect(ComboViewer viewer);

	/**
     * @since 9.1
     */
	public Principal getUserPrincipal(Display display);
	
	/**
     * @since 9.1
     */
	public void runInFakeUIContext(Display display, Runnable runnable);
	
	/**
     * @since 9.1
     */
	public void activateUIConnection(String id);
	
	/**
     * @since 9.1
     */
	public void deactivateUIConnection(String id);

}
