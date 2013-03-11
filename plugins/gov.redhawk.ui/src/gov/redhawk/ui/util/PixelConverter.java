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
/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package gov.redhawk.ui.util;

// CHECKSTYLE:OFF
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Control;

//CHECKSTYLE:ON
/**
 * The Class PixelConverter.
 */
public class PixelConverter {

	private final FontMetrics fFontMetrics;

	/**
	 * Instantiates a new pixel converter.
	 * 
	 * @param control the control
	 */
	public PixelConverter(final Control control) {
		final GC gc = new GC(control);
		gc.setFont(control.getFont());
		this.fFontMetrics = gc.getFontMetrics();
		gc.dispose();
	}

	/**
	 * Convert height in chars to pixels.
	 * 
	 * @param chars the chars
	 * @return the int
	 * @see DialogPage#convertHeightInCharsToPixels
	 */
	public int convertHeightInCharsToPixels(final int chars) {
		return Dialog.convertHeightInCharsToPixels(this.fFontMetrics, chars);
	}

	/**
	 * Convert horizontal dl us to pixels.
	 * 
	 * @param dlus the dlus
	 * @return the int
	 * @see DialogPage#convertHorizontalDLUsToPixels
	 */
	public int convertHorizontalDLUsToPixels(final int dlus) {
		return Dialog.convertHorizontalDLUsToPixels(this.fFontMetrics, dlus);
	}

	/**
	 * Convert vertical dl us to pixels.
	 * 
	 * @param dlus the dlus
	 * @return the int
	 * @see DialogPage#convertVerticalDLUsToPixels
	 */
	public int convertVerticalDLUsToPixels(final int dlus) {
		return Dialog.convertVerticalDLUsToPixels(this.fFontMetrics, dlus);
	}

	/**
	 * Convert width in chars to pixels.
	 * 
	 * @param chars the chars
	 * @return the int
	 * @see DialogPage#convertWidthInCharsToPixels
	 */
	public int convertWidthInCharsToPixels(final int chars) {
		return Dialog.convertWidthInCharsToPixels(this.fFontMetrics, chars);
	}

}
