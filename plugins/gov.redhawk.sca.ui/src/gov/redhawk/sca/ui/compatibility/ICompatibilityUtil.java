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
	/**
	 * Return the Principal for the logged in user (RAP). Returns null in RCP
	 * or in RAP if the user does not present a valid and trusted certificate.
	 * @param display the current Display
	 * @return the User Principal or null
	 */
	public Principal getUserPrincipal(Display display);

	/**
	 * Execute runnable in a fake UI context (RAP) or in the current thread (RCP).
	 * In RAP, this is used to enable background (i.e. non-UI) threads to access items
	 * that are only available in the UI context (e.g. objects stored in the SessionStore
	 * (RAP 1.x) or UISession (RAP 2.x)).
	 * 
	 * @since 9.1
	 * @param display the current Display
	 * @param runnable the Runnable instance to be executed
	 */
	public void runInFakeUIContext(Display display, Runnable runnable);

	/**
	 * Execute runnable in the Request context (RAP) or in the current thread (RCP).
	 * In RAP, this is required for code that needs to access the current HTTP Request object.
	 * 
	 * @since 9.3
	 * @param runnable the Runnable instance to be executed
	 */
	public void executeOnRequestThread(Runnable runnable);

	/**
	 * Used in RAP to ensure UI Updates occur during background thread execution, until
	 * {@Link #deactivateUIConnection(String)} is invoked. Has no effect in RCP.
	 * @since 9.1
	 * @param id an arbitrary ID used to associate invocations of {@Link #activateUIConnection(String)} and
	 * {@Link #deactivateUIConnection(String)}
	 */
	public void activateUIConnection(String id);

	/**
	 * Used in RAP to stop UI Updates that were occurring during background thread execution
	 * as a result of invoking {@Link #activateUIConnection(String)}. Has no effect in RCP.
	 * @since 9.1
	 * @param id an arbitrary ID used to associate invocations of {@Link #activateUIConnection(String)} and
	 * {@Link #deactivateUIConnection(String)}
	 */
	public void deactivateUIConnection(String id);

}
