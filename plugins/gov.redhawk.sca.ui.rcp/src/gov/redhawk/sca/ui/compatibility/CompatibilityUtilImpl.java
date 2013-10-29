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
 * @since 2.0
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
public class CompatibilityUtilImpl implements ICompatibilityUtil {

	/* (non-Javadoc)
	 * @see gov.redhawk.sca.ui.compatibility.ICompatibilityUtil#setFontDataStyle(org.eclipse.swt.graphics.FontData, int)
	 */
	@Override
	public void setFontDataStyle(FontData fontData, int style) {
		fontData.setStyle(style);
	}
	
	/* (non-Javadoc)
	 * @see gov.redhawk.sca.ui.compatibility.ICompatibilityUtil#disableComboWheelScrollSelect(org.eclipse.jface.viewers.ComboViewer)
	 */
	@Override
	public void disableComboWheelScrollSelect(ComboViewer viewer) {
		viewer.getCombo().addListener(SWT.MouseVerticalWheel, new Listener() {

			@Override
			public void handleEvent(Event event) {
				// Disable Mouse Wheel Combo Box Control
				event.doit = false;
			}

		});
	}
	

	/* (non-Javadoc)
	 * @see gov.redhawk.sca.ui.compatibility.ICompatibilityUtil#getUserPrincipal(org.eclipse.swt.widgets.Display)
	 */
	@Override
	/**
	 * @since 9.1
	 */
	public Principal getUserPrincipal(Display display) {
		throw new UnsupportedOperationException("This method is used in RAP only");
	}
	
	/**
	 * @since 1.2
	 */
	/* (non-Javadoc)
	 * @see gov.redhawk.sca.ui.compatibility.ICompatibilityUtil#runInFakeUIContext(org.eclipse.swt.widgets.Display, java.lang.Runnable)
	 */
	@Override
	public void runInFakeUIContext(Display display, Runnable runnable) {
		//PASS
		//No RCP implementation needed
	}
	
	/**
	 * @since 1.2
	 */
	/* (non-Javadoc)
	 * @see gov.redhawk.sca.ui.compatibility.ICompatibilityUtil#activateUIConnection(java.lang.String)
	 */
	@Override
	public void activateUIConnection(String id) {
		//PASS
		//No RCP implementation needed
	}
	
	/* (non-Javadoc)
	 * @see gov.redhawk.sca.ui.compatibility.ICompatibilityUtil#deactivateUIConnection(java.lang.String)
	 */
	@Override
	public void deactivateUIConnection(String id) {
		//PASS
		//No RCP implementation needed
	}

	/* (non-Javadoc)
	 * @see gov.redhawk.sca.ui.compatibility.ICompatibilityUtil#executeOnRequestThread(java.lang.Runnable)
	 */
	@Override
	public void executeOnRequestThread(Runnable runnable) {
		//Run on Request Thread only in RAP
		runnable.run();
	}
}
