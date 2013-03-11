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

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Caret;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;

/**
 * Utility class to simplify access to some SWT resources.
 */
public final class SWTUtil {

	/** A layout factory useful for create table entries within forms. */
	public static final GridLayoutFactory TABLE_ENTRY_LAYOUT_FACTORY = GridLayoutFactory.fillDefaults().margins(0, 0).numColumns(
	        2);

	/**
	 * Instantiates a new sWT util.
	 */
	private SWTUtil() {

	}

	/**
	 * Returns the standard display to be used. The method first checks, if the
	 * thread calling this method has an associated disaply. If so, this display
	 * is returned. Otherwise the method returns the default display.
	 * 
	 * @return the standard display
	 */
	public static Display getStandardDisplay() {
		Display display;
		display = Display.getCurrent();
		if (display == null) {
			display = Display.getDefault();
		}
		return display;
	}

	/**
	 * Returns the shell for the given widget. If the widget doesn't represent a
	 * SWT object that manage a shell, <code>null</code> is returned.
	 * 
	 * @param widget the widget
	 * @return the shell for the given widget
	 */
	public static Shell getShell(final Widget widget) {
		if (widget instanceof Control) {
			return ((Control) widget).getShell();
		}
		if (widget instanceof Caret) {
			return ((Caret) widget).getParent().getShell();
		}
		if (widget instanceof DragSource) {
			return ((DragSource) widget).getControl().getShell();
		}
		if (widget instanceof DropTarget) {
			return ((DropTarget) widget).getControl().getShell();
		}
		if (widget instanceof Menu) {
			return ((Menu) widget).getParent().getShell();
		}
		if (widget instanceof ScrollBar) {
			return ((ScrollBar) widget).getParent().getShell();
		}

		return null;
	}

	/**
	 * Returns a width hint for a button control.
	 * 
	 * @param button the button
	 * @return the button width hint
	 */
	public static int getButtonWidthHint(final Button button) {
		if (button.getFont().equals(JFaceResources.getDefaultFont())) {
			button.setFont(JFaceResources.getDialogFont());
		}
		final PixelConverter converter = new PixelConverter(button);
		final int widthHint = converter.convertHorizontalDLUsToPixels(IDialogConstants.BUTTON_WIDTH);
		return Math.max(widthHint, button.computeSize(SWT.DEFAULT, SWT.DEFAULT, true).x);
	}

	/**
	 * Sets width and height hint for the button control. <b>Note:</b> This is a
	 * NOP if the button's layout data is not an instance of
	 * <code>GridData</code>.
	 * 
	 * @param button the button
	 */
	public static void setButtonDimensionHint(final Button button) {
		Dialog.applyDialogFont(button);
		Assert.isNotNull(button);
		final Object gd = button.getLayoutData();
		if (gd instanceof GridData) {
			((GridData) gd).widthHint = SWTUtil.getButtonWidthHint(button);
		}
	}

	/**
	 * Sets the dialog size.
	 * 
	 * @param dialog the dialog
	 * @param width the width
	 * @param height the height
	 */
	public static void setDialogSize(final Dialog dialog, int width, int height) {
		final Point computedSize = dialog.getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT);
		width = Math.max(computedSize.x, width);
		height = Math.max(computedSize.y, height);
		dialog.getShell().setSize(width, height);
	}
}
