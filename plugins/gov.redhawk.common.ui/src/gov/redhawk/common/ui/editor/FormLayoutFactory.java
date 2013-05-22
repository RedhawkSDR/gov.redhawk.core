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
/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package gov.redhawk.common.ui.editor;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

/**
 * FormLayoutFactory.
 * @since 3.0
 */
public final class FormLayoutFactory {

	// Used in place of 0. If 0 is used, widget borders will appear clipped
	// on some platforms (e.g. Windows XP Classic Theme).
	// Form tool kit requires parent composites containing the widget to have
	// at least 1 pixel border margins in order to paint the flat borders.
	// The form toolkit paints flat borders on a given widget when native
	// borders are not painted by SWT. See FormToolkit#paintBordersFor()
	/** The Constant DEFAULT_CLEAR_MARGIN. */
	public static final int DEFAULT_CLEAR_MARGIN = 2;

	// Required to allow space for field decorations
	/** The Constant CONTROL_HORIZONTAL_INDENT. */
	public static final int CONTROL_HORIZONTAL_INDENT = 3;

	// UI Forms Standards

	// FORM BODY
	/** The Constant FORM_BODY_MARGIN_TOP. */
	public static final int FORM_BODY_MARGIN_TOP = 12;

	/** The Constant FORM_BODY_MARGIN_BOTTOM. */
	public static final int FORM_BODY_MARGIN_BOTTOM = 12;

	/** The Constant FORM_BODY_MARGIN_LEFT. */
	public static final int FORM_BODY_MARGIN_LEFT = 6;

	/** The Constant FORM_BODY_MARGIN_RIGHT. */
	public static final int FORM_BODY_MARGIN_RIGHT = 6;

	/** The Constant FORM_BODY_HORIZONTAL_SPACING. */
	public static final int FORM_BODY_HORIZONTAL_SPACING = 20;
	// Should be 20; but, we minus 3 because the section automatically pads the
	// bottom margin by that amount
	/** The Constant FORM_BODY_VERTICAL_SPACING. */
	public static final int FORM_BODY_VERTICAL_SPACING = 17;

	/** The Constant FORM_BODY_MARGIN_HEIGHT. */
	public static final int FORM_BODY_MARGIN_HEIGHT = 0;

	/** The Constant FORM_BODY_MARGIN_WIDTH. */
	public static final int FORM_BODY_MARGIN_WIDTH = 0;

	// SECTION CLIENT
	/** The Constant SECTION_CLIENT_MARGIN_TOP. */
	public static final int SECTION_CLIENT_MARGIN_TOP = 5;

	/** The Constant SECTION_CLIENT_MARGIN_BOTTOM. */
	public static final int SECTION_CLIENT_MARGIN_BOTTOM = 5;
	// Should be 6; but, we minus 4 because the section automatically pads the
	// left margin by that amount
	/** The Constant SECTION_CLIENT_MARGIN_LEFT. */
	public static final int SECTION_CLIENT_MARGIN_LEFT = 2;
	// Should be 6; but, we minus 4 because the section automatically pads the
	// right margin by that amount
	/** The Constant SECTION_CLIENT_MARGIN_RIGHT. */
	public static final int SECTION_CLIENT_MARGIN_RIGHT = 2;

	/** The Constant SECTION_CLIENT_HORIZONTAL_SPACING. */
	public static final int SECTION_CLIENT_HORIZONTAL_SPACING = 5;

	/** The Constant SECTION_CLIENT_VERTICAL_SPACING. */
	public static final int SECTION_CLIENT_VERTICAL_SPACING = 5;

	/** The Constant SECTION_CLIENT_MARGIN_HEIGHT. */
	public static final int SECTION_CLIENT_MARGIN_HEIGHT = 0;

	/** The Constant SECTION_CLIENT_MARGIN_WIDTH. */
	public static final int SECTION_CLIENT_MARGIN_WIDTH = 0;

	/** The Constant SECTION_HEADER_VERTICAL_SPACING. */
	public static final int SECTION_HEADER_VERTICAL_SPACING = 6;

	// CLEAR
	/** The Constant CLEAR_MARGIN_TOP. */
	public static final int CLEAR_MARGIN_TOP = FormLayoutFactory.DEFAULT_CLEAR_MARGIN;

	/** The Constant CLEAR_MARGIN_BOTTOM. */
	public static final int CLEAR_MARGIN_BOTTOM = FormLayoutFactory.DEFAULT_CLEAR_MARGIN;

	/** The Constant CLEAR_MARGIN_LEFT. */
	public static final int CLEAR_MARGIN_LEFT = FormLayoutFactory.DEFAULT_CLEAR_MARGIN;

	/** The Constant CLEAR_MARGIN_RIGHT. */
	public static final int CLEAR_MARGIN_RIGHT = FormLayoutFactory.DEFAULT_CLEAR_MARGIN;

	/** The Constant CLEAR_HORIZONTAL_SPACING. */
	public static final int CLEAR_HORIZONTAL_SPACING = 0;

	/** The Constant CLEAR_VERTICAL_SPACING. */
	public static final int CLEAR_VERTICAL_SPACING = 0;

	/** The Constant CLEAR_MARGIN_HEIGHT. */
	public static final int CLEAR_MARGIN_HEIGHT = 0;

	/** The Constant CLEAR_MARGIN_WIDTH. */
	public static final int CLEAR_MARGIN_WIDTH = 0;

	// FORM PANE
	/** The Constant FORM_PANE_MARGIN_TOP. */
	public static final int FORM_PANE_MARGIN_TOP = 0;

	/** The Constant FORM_PANE_MARGIN_BOTTOM. */
	public static final int FORM_PANE_MARGIN_BOTTOM = 0;

	/** The Constant FORM_PANE_MARGIN_LEFT. */
	public static final int FORM_PANE_MARGIN_LEFT = 0;

	/** The Constant FORM_PANE_MARGIN_RIGHT. */
	public static final int FORM_PANE_MARGIN_RIGHT = 0;

	/** The Constant FORM_PANE_HORIZONTAL_SPACING. */
	public static final int FORM_PANE_HORIZONTAL_SPACING = FormLayoutFactory.FORM_BODY_HORIZONTAL_SPACING;

	/** The Constant FORM_PANE_VERTICAL_SPACING. */
	public static final int FORM_PANE_VERTICAL_SPACING = FormLayoutFactory.FORM_BODY_VERTICAL_SPACING;

	/** The Constant FORM_PANE_MARGIN_HEIGHT. */
	public static final int FORM_PANE_MARGIN_HEIGHT = 0;

	/** The Constant FORM_PANE_MARGIN_WIDTH. */
	public static final int FORM_PANE_MARGIN_WIDTH = 0;

	// MASTER DETAILS
	/** The Constant MASTER_DETAILS_MARGIN_TOP. */
	public static final int MASTER_DETAILS_MARGIN_TOP = 0;

	/** The Constant MASTER_DETAILS_MARGIN_BOTTOM. */
	public static final int MASTER_DETAILS_MARGIN_BOTTOM = 0;
	// Used only by masters part. Details part margin dynamically calculated
	/** The Constant MASTER_DETAILS_MARGIN_LEFT. */
	public static final int MASTER_DETAILS_MARGIN_LEFT = 0;
	// Used only by details part. Masters part margin dynamically calcualated
	/** The Constant MASTER_DETAILS_MARGIN_RIGHT. */
	public static final int MASTER_DETAILS_MARGIN_RIGHT = 1;

	/** The Constant MASTER_DETAILS_HORIZONTAL_SPACING. */
	public static final int MASTER_DETAILS_HORIZONTAL_SPACING = FormLayoutFactory.FORM_BODY_HORIZONTAL_SPACING;

	/** The Constant MASTER_DETAILS_VERTICAL_SPACING. */
	public static final int MASTER_DETAILS_VERTICAL_SPACING = FormLayoutFactory.FORM_BODY_VERTICAL_SPACING;

	/** The Constant MASTER_DETAILS_MARGIN_HEIGHT. */
	public static final int MASTER_DETAILS_MARGIN_HEIGHT = 0;

	/** The Constant MASTER_DETAILS_MARGIN_WIDTH. */
	public static final int MASTER_DETAILS_MARGIN_WIDTH = 0;

	/**
	 * Instantiates a new form layout factory.
	 */
	private FormLayoutFactory() {
		// NO-OP
	}

	/**
	 * For form bodies.
	 * 
	 * @param makeColumnsEqualWidth the make columns equal width
	 * @param numColumns the num columns
	 * @return the grid layout
	 */
	public static GridLayout createFormGridLayout(final boolean makeColumnsEqualWidth, final int numColumns) {
		final GridLayout layout = new GridLayout();

		layout.marginHeight = FormLayoutFactory.FORM_BODY_MARGIN_HEIGHT;
		layout.marginWidth = FormLayoutFactory.FORM_BODY_MARGIN_WIDTH;

		layout.marginTop = FormLayoutFactory.FORM_BODY_MARGIN_TOP;
		layout.marginBottom = FormLayoutFactory.FORM_BODY_MARGIN_BOTTOM;
		layout.marginLeft = FormLayoutFactory.FORM_BODY_MARGIN_LEFT;
		layout.marginRight = FormLayoutFactory.FORM_BODY_MARGIN_RIGHT;

		layout.horizontalSpacing = FormLayoutFactory.FORM_BODY_HORIZONTAL_SPACING;
		layout.verticalSpacing = FormLayoutFactory.FORM_BODY_VERTICAL_SPACING;

		layout.makeColumnsEqualWidth = makeColumnsEqualWidth;
		layout.numColumns = numColumns;

		return layout;
	}

	/**
	 * For miscellaneous grouping composites. For sections (as a whole - header
	 * plus client).
	 * 
	 * @param makeColumnsEqualWidth the make columns equal width
	 * @param numColumns the num columns
	 * @return the grid layout
	 */
	public static GridLayout createClearGridLayout(final boolean makeColumnsEqualWidth, final int numColumns) {
		final GridLayout layout = new GridLayout();

		layout.marginHeight = FormLayoutFactory.CLEAR_MARGIN_HEIGHT;
		layout.marginWidth = FormLayoutFactory.CLEAR_MARGIN_WIDTH;

		layout.marginTop = FormLayoutFactory.CLEAR_MARGIN_TOP;
		layout.marginBottom = FormLayoutFactory.CLEAR_MARGIN_BOTTOM;
		layout.marginLeft = FormLayoutFactory.CLEAR_MARGIN_LEFT;
		layout.marginRight = FormLayoutFactory.CLEAR_MARGIN_RIGHT;

		layout.horizontalSpacing = FormLayoutFactory.CLEAR_HORIZONTAL_SPACING;
		layout.verticalSpacing = FormLayoutFactory.CLEAR_VERTICAL_SPACING;

		layout.makeColumnsEqualWidth = makeColumnsEqualWidth;
		layout.numColumns = numColumns;

		return layout;
	}

	/**
	 * For form bodies.
	 * 
	 * @param makeColumnsEqualWidth the make columns equal width
	 * @param numColumns the num columns
	 * @return the table wrap layout
	 */
	public static TableWrapLayout createFormTableWrapLayout(final boolean makeColumnsEqualWidth, final int numColumns) {
		final TableWrapLayout layout = new TableWrapLayout();

		layout.topMargin = FormLayoutFactory.FORM_BODY_MARGIN_TOP;
		layout.bottomMargin = FormLayoutFactory.FORM_BODY_MARGIN_BOTTOM;
		layout.leftMargin = FormLayoutFactory.FORM_BODY_MARGIN_LEFT;
		layout.rightMargin = FormLayoutFactory.FORM_BODY_MARGIN_RIGHT;

		layout.horizontalSpacing = FormLayoutFactory.FORM_BODY_HORIZONTAL_SPACING;
		layout.verticalSpacing = FormLayoutFactory.FORM_BODY_VERTICAL_SPACING;

		layout.makeColumnsEqualWidth = makeColumnsEqualWidth;
		layout.numColumns = numColumns;

		return layout;
	}

	/**
	 * For composites used to group sections in left and right panes.
	 * 
	 * @param makeColumnsEqualWidth the make columns equal width
	 * @param numColumns the num columns
	 * @return the table wrap layout
	 */
	public static TableWrapLayout createFormPaneTableWrapLayout(final boolean makeColumnsEqualWidth, final int numColumns) {
		final TableWrapLayout layout = new TableWrapLayout();

		layout.topMargin = FormLayoutFactory.FORM_PANE_MARGIN_TOP;
		layout.bottomMargin = FormLayoutFactory.FORM_PANE_MARGIN_BOTTOM;
		layout.leftMargin = FormLayoutFactory.FORM_PANE_MARGIN_LEFT;
		layout.rightMargin = FormLayoutFactory.FORM_PANE_MARGIN_RIGHT;

		layout.horizontalSpacing = FormLayoutFactory.FORM_PANE_HORIZONTAL_SPACING;
		layout.verticalSpacing = FormLayoutFactory.FORM_PANE_VERTICAL_SPACING;

		layout.makeColumnsEqualWidth = makeColumnsEqualWidth;
		layout.numColumns = numColumns;

		return layout;
	}

	/**
	 * For composites used to group sections in left and right panes.
	 * 
	 * @param makeColumnsEqualWidth the make columns equal width
	 * @param numColumns the num columns
	 * @return the grid layout
	 */
	public static GridLayout createFormPaneGridLayout(final boolean makeColumnsEqualWidth, final int numColumns) {
		final GridLayout layout = new GridLayout();

		layout.marginHeight = FormLayoutFactory.FORM_PANE_MARGIN_HEIGHT;
		layout.marginWidth = FormLayoutFactory.FORM_PANE_MARGIN_WIDTH;

		layout.marginTop = FormLayoutFactory.FORM_PANE_MARGIN_TOP;
		layout.marginBottom = FormLayoutFactory.FORM_PANE_MARGIN_BOTTOM;
		layout.marginLeft = FormLayoutFactory.FORM_PANE_MARGIN_LEFT;
		layout.marginRight = FormLayoutFactory.FORM_PANE_MARGIN_RIGHT;

		layout.horizontalSpacing = FormLayoutFactory.FORM_PANE_HORIZONTAL_SPACING;
		layout.verticalSpacing = FormLayoutFactory.FORM_PANE_VERTICAL_SPACING;

		layout.makeColumnsEqualWidth = makeColumnsEqualWidth;
		layout.numColumns = numColumns;

		return layout;
	}

	/**
	 * For miscellaneous grouping composites. For sections (as a whole - header
	 * plus client).
	 * 
	 * @param makeColumnsEqualWidth the make columns equal width
	 * @param numColumns the num columns
	 * @return the table wrap layout
	 */
	public static TableWrapLayout createClearTableWrapLayout(final boolean makeColumnsEqualWidth, final int numColumns) {
		final TableWrapLayout layout = new TableWrapLayout();

		layout.topMargin = FormLayoutFactory.CLEAR_MARGIN_TOP;
		layout.bottomMargin = FormLayoutFactory.CLEAR_MARGIN_BOTTOM;
		layout.leftMargin = FormLayoutFactory.CLEAR_MARGIN_LEFT;
		layout.rightMargin = FormLayoutFactory.CLEAR_MARGIN_RIGHT;

		layout.horizontalSpacing = FormLayoutFactory.CLEAR_HORIZONTAL_SPACING;
		layout.verticalSpacing = FormLayoutFactory.CLEAR_VERTICAL_SPACING;

		layout.makeColumnsEqualWidth = makeColumnsEqualWidth;
		layout.numColumns = numColumns;

		return layout;
	}

	/**
	 * For master sections belonging to a master details block.
	 * 
	 * @param makeColumnsEqualWidth the make columns equal width
	 * @param numColumns the num columns
	 * @return the grid layout
	 */
	public static GridLayout createMasterGridLayout(final boolean makeColumnsEqualWidth, final int numColumns) {
		final GridLayout layout = new GridLayout();

		layout.marginHeight = FormLayoutFactory.MASTER_DETAILS_MARGIN_HEIGHT;
		layout.marginWidth = FormLayoutFactory.MASTER_DETAILS_MARGIN_WIDTH;

		layout.marginTop = FormLayoutFactory.MASTER_DETAILS_MARGIN_TOP;
		layout.marginBottom = FormLayoutFactory.MASTER_DETAILS_MARGIN_BOTTOM;
		layout.marginLeft = FormLayoutFactory.MASTER_DETAILS_MARGIN_LEFT;
		// Cannot set layout on a sash form.
		// In order to replicate the horizontal spacing between sections,
		// divide the amount by 2 and set the master section right margin to
		// half the amount and set the left details section margin to half
		// the amount. The default sash width is currently set at 3.
		// Minus 1 pixel from each half. Use the 1 left over pixel to separate
		// the details section from the vertical scollbar.
		int marginRight = FormLayoutFactory.MASTER_DETAILS_HORIZONTAL_SPACING;
		if (marginRight > 0) {
			marginRight = marginRight / 2;
			if (marginRight > 0) {
				marginRight--;
			}
		}
		layout.marginRight = marginRight;

		layout.horizontalSpacing = FormLayoutFactory.MASTER_DETAILS_HORIZONTAL_SPACING;
		layout.verticalSpacing = FormLayoutFactory.MASTER_DETAILS_VERTICAL_SPACING;

		layout.makeColumnsEqualWidth = makeColumnsEqualWidth;
		layout.numColumns = numColumns;

		return layout;
	}

	/**
	 * For details sections belonging to a master details block.
	 * 
	 * @param makeColumnsEqualWidth the make columns equal width
	 * @param numColumns the num columns
	 * @return the grid layout
	 */
	public static GridLayout createDetailsGridLayout(final boolean makeColumnsEqualWidth, final int numColumns) {
		final GridLayout layout = new GridLayout();

		layout.marginHeight = FormLayoutFactory.MASTER_DETAILS_MARGIN_HEIGHT;
		layout.marginWidth = FormLayoutFactory.MASTER_DETAILS_MARGIN_WIDTH;

		layout.marginTop = FormLayoutFactory.MASTER_DETAILS_MARGIN_TOP;
		layout.marginBottom = FormLayoutFactory.MASTER_DETAILS_MARGIN_BOTTOM;
		// Cannot set layout on a sash form.
		// In order to replicate the horizontal spacing between sections,
		// divide the amount by 2 and set the master section right margin to
		// half the amount and set the left details section margin to half
		// the amount. The default sash width is currently set at 3.
		// Minus 1 pixel from each half. Use the 1 left over pixel to separate
		// the details section from the vertical scollbar.
		int marginLeft = FormLayoutFactory.MASTER_DETAILS_HORIZONTAL_SPACING;
		if (marginLeft > 0) {
			marginLeft = marginLeft / 2;
			if (marginLeft > 0) {
				marginLeft--;
			}
		}
		layout.marginLeft = marginLeft;
		layout.marginRight = FormLayoutFactory.MASTER_DETAILS_MARGIN_RIGHT;

		layout.horizontalSpacing = FormLayoutFactory.MASTER_DETAILS_HORIZONTAL_SPACING;
		layout.verticalSpacing = FormLayoutFactory.MASTER_DETAILS_VERTICAL_SPACING;

		layout.makeColumnsEqualWidth = makeColumnsEqualWidth;
		layout.numColumns = numColumns;

		return layout;
	}

	/**
	 * For composites set as section clients. For composites containg form text.
	 * 
	 * @param makeColumnsEqualWidth the make columns equal width
	 * @param numColumns the num columns
	 * @return the grid layout
	 */
	public static GridLayout createSectionClientGridLayout(final boolean makeColumnsEqualWidth, final int numColumns) {
		final GridLayout layout = new GridLayout();

		layout.marginHeight = FormLayoutFactory.SECTION_CLIENT_MARGIN_HEIGHT;
		layout.marginWidth = FormLayoutFactory.SECTION_CLIENT_MARGIN_WIDTH;

		layout.marginTop = FormLayoutFactory.SECTION_CLIENT_MARGIN_TOP;
		layout.marginBottom = FormLayoutFactory.SECTION_CLIENT_MARGIN_BOTTOM;
		layout.marginLeft = FormLayoutFactory.SECTION_CLIENT_MARGIN_LEFT;
		layout.marginRight = FormLayoutFactory.SECTION_CLIENT_MARGIN_RIGHT;

		layout.horizontalSpacing = FormLayoutFactory.SECTION_CLIENT_HORIZONTAL_SPACING;
		layout.verticalSpacing = FormLayoutFactory.SECTION_CLIENT_VERTICAL_SPACING;

		layout.makeColumnsEqualWidth = makeColumnsEqualWidth;
		layout.numColumns = numColumns;

		return layout;
	}

	/**
	 * For composites set as section clients. For composites containg form text.
	 * 
	 * @param makeColumnsEqualWidth the make columns equal width
	 * @param numColumns the num columns
	 * @return the table wrap layout
	 */
	public static TableWrapLayout createSectionClientTableWrapLayout(final boolean makeColumnsEqualWidth, final int numColumns) {
		final TableWrapLayout layout = new TableWrapLayout();

		layout.topMargin = FormLayoutFactory.SECTION_CLIENT_MARGIN_TOP;
		layout.bottomMargin = FormLayoutFactory.SECTION_CLIENT_MARGIN_BOTTOM;
		layout.leftMargin = FormLayoutFactory.SECTION_CLIENT_MARGIN_LEFT;
		layout.rightMargin = FormLayoutFactory.SECTION_CLIENT_MARGIN_RIGHT;

		layout.horizontalSpacing = FormLayoutFactory.SECTION_CLIENT_HORIZONTAL_SPACING;
		layout.verticalSpacing = FormLayoutFactory.SECTION_CLIENT_VERTICAL_SPACING;

		layout.makeColumnsEqualWidth = makeColumnsEqualWidth;
		layout.numColumns = numColumns;

		return layout;
	}

	/**
	 * Debug method. MAGENTA = 11 CYAN = 13 GREEN = 5
	 * 
	 * @param container the container
	 * @param color the color
	 */
	public static void visualizeLayoutArea(final Composite container, final int color) {
		container.setBackground(Display.getCurrent().getSystemColor(color));
	}
}
