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
 * This class is meant as an extension mechanism to single source RCP / RAP applications
 * @since 9.1
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
public class CompatibilityUtilImpl implements ICompatibilityUtil {

	@Override
	public void setFontDataStyle(FontData fontData, int style) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void disableComboWheelScrollSelect(ComboViewer viewer) {
		throw new UnsupportedOperationException();

	}

	@Override
	public Principal getUserPrincipal(Display display) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void runInFakeUIContext(Display display, Runnable runnable) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @since 9.3
	 */
	@Override
	public void executeOnRequestThread(Runnable runnable) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void activateUIConnection(String id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deactivateUIConnection(String id) {
		throw new UnsupportedOperationException();

	}

}
