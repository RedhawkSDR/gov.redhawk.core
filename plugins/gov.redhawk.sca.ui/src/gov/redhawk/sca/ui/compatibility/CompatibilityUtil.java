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
<<<<<<< HEAD
=======
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.internal.PartSite;
>>>>>>> Update code for obtaining user DN from cert in RAP. (refs #836)

/**
 * @since 9.0
 */
public class CompatibilityUtil {

	private CompatibilityUtil() {
		//Prevent instantiation
	}

	public static void setFontDataStyle(FontData fontData, int style) {
		throw new UnsupportedOperationException();
	}

	public static void disableComboWheelScrollSelect(ComboViewer viewer) {
		throw new UnsupportedOperationException();
	}
<<<<<<< HEAD

	/**
     * @since 9.1
     */
=======
	
>>>>>>> Update code for obtaining user DN from cert in RAP. (refs #836)
	public static Principal getUserPrincipal(Display display) {
		// Throw exception if RAP implementation not present. There is no RCP implementation, so just return null
		if (SWT.getPlatform().startsWith("rap")) {
			throw new UnsupportedOperationException();
		} else {
			return null;
		}
	}
}
