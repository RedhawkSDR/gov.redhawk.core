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
package gov.redhawk.sca.ui;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.navigator.ICommonLabelProvider;

/**
 * Provides an interface version of CellLabelProvider to be used to provide tooltips to the SCA Navigator Viewer
 * 
 * @since 8.0
 * @see org.eclipse.jface.viewers.CellLabelProvider
 * @see gov.redhawk.sca.internal.ui.views.ScaCommonViewer
 */
public interface ITooltipProvider extends ICommonLabelProvider {
	/**
	 * Get the image displayed in the tool tip for object.
	 * 
	 * <p>
	 * <b>If {@link #getToolTipText(Object)} and
	 * {@link #getToolTipImage(Object)} both return <code>null</code> the
	 * control is set back to standard behavior</b>
	 * </p>
	 * 
	 * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipImage(Object)
	 * 
	 * @param object
	 *            the element for which the tool tip is shown
	 * @return {@link Image} or <code>null</code> if there is not image.
	 */

	public Image getToolTipImage(Object object);

	/**
	 * Get the text displayed in the tool tip for object.
	 * 
	 * <p>
	 * <b>If {@link #getToolTipText(Object)} and
	 * {@link #getToolTipImage(Object)} both return <code>null</code> the
	 * control is set back to standard behavior</b>
	 * </p>
	 * 
	 * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipText(Object)
	 * 
	 * @param element
	 *            the element for which the tool tip is shown
	 * @return the {@link String} or <code>null</code> if there is not text to
	 *         display
	 */
	public String getToolTipText(Object element);

	/**
	 * Return the background color used for the tool tip
	 * 
	 * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipBackgroundColor(Object)
	 * 
	 * @param object
	 *            the {@link Object} for which the tool tip is shown
	 * 
	 * @return the {@link Color} used or <code>null</code> if you want to use
	 *         the default color {@link org.eclipse.swt.SWT#COLOR_INFO_BACKGROUND}
	 * @see org.eclipse.swt.SWT#COLOR_INFO_BACKGROUND
	 */
	public Color getToolTipBackgroundColor(Object object);

	/**
	 * The foreground color used to display the the text in the tool tip
	 * 
	 * @param object
	 *            the {@link Object} for which the tool tip is shown
	 * @return the {@link Color} used or <code>null</code> if you want to use
	 *         the default color {@link org.eclipse.swt.SWT#COLOR_INFO_FOREGROUND}
	 * @see org.eclipse.swt.SWT#COLOR_INFO_FOREGROUND
	 * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipForegroundColor(Object)
	 */
	public Color getToolTipForegroundColor(Object object);

	/**
	 * Get the {@link Font} used to display the tool tip
	 * 
	 * @param object
	 *            the element for which the tool tip is shown
	 * @return {@link Font} or <code>null</code> if the default font is to be
	 *         used.
	 * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipFont(Object)
	 */
	public Font getToolTipFont(Object object);

	/**
	 * Return the amount of pixels in x and y direction you want the tool tip to
	 * pop up from the mouse pointer. The default shift is 10px right and 0px
	 * below your mouse cursor. Be aware of the fact that you should at least
	 * position the tool tip 1px right to your mouse cursor else click events
	 * may not get propagated properly.
	 * 
	 * @param object
	 *            the element for which the tool tip is shown
	 * @return {@link Point} to shift of the tool tip or <code>null</code> if the
	 *         default shift should be used.
	 * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipShift(Object)
	 */
	public Point getToolTipShift(Object object);

	/**
	 * Return whether or not to use the native tool tip. If you switch to native
	 * tool tips only the value from {@link #getToolTipText(Object)} is used all
	 * other features from custom tool tips are not supported.
	 * 
	 * <p>
	 * To reset the control to native behavior you should return
	 * <code>true</code> from this method and <code>null</code> from
	 * {@link #getToolTipText(Object)} or <code>null</code> from
	 * {@link #getToolTipText(Object)} and {@link #getToolTipImage(Object)} at
	 * the same time
	 * </p>
	 * 
	 * @param object
	 *            the {@link Object} for which the tool tip is shown
	 * @return <code>true</code> if native tool tips should be used
	 * 
	 * @see org.eclipse.jface.viewers.CellLabelProvider#useNativeToolTip(Object)
	 */
	public boolean useNativeToolTip(Object object);

	/**
	 * The time in milliseconds the tool tip is shown for.
	 * 
	 * @param object
	 *            the {@link Object} for which the tool tip is shown
	 * @return time in milliseconds the tool tip is shown for
	 * 
	 * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipTimeDisplayed(Object)
	 */
	public int getToolTipTimeDisplayed(Object object);

	/**
	 * The time in milliseconds until the tool tip is displayed.
	 * 
	 * @param object
	 *            the {@link Object} for which the tool tip is shown
	 * @return time in milliseconds until the tool tip is displayed
	 * 
	 * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipDisplayDelayTime(Object)
	 */
	public int getToolTipDisplayDelayTime(Object object);

	/**
	 * The {@link org.eclipse.swt.SWT} style used to create the {@link org.eclipse.swt.custom.CLabel} (see there for
	 * supported styles). By default {@link org.eclipse.swt.SWT#SHADOW_NONE} is used.
	 * 
	 * @param object
	 *            the element for which the tool tip is shown
	 * @return the style used to create the label
	 * @see org.eclipse.swt.custom.CLabel
	 * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipStyle(Object)
	 */
	public int getToolTipStyle(Object object);
}
