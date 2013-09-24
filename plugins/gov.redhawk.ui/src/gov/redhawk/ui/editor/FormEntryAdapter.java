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
 * Copyright (c) 2003, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package gov.redhawk.ui.editor;

import gov.redhawk.common.ui.parts.FormEntry;
import gov.redhawk.common.ui.parts.IFormEntryListener;

import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.forms.events.HyperlinkEvent;

/**
 * The Class FormEntryAdapter.
 */
public class FormEntryAdapter implements IFormEntryListener {

	// CHECKSTYLE:OFF
	/** The action bars. */
	protected IActionBars actionBars;

	// CHECKSTYLE:ON

	/**
	 * Instantiates a new form entry adapter.
	 * 
	 * @param contextPart the context part
	 */
	public FormEntryAdapter() {
		this(null);
	}

	/**
	 * Instantiates a new form entry adapter.
	 * 
	 * @param contextPart the context part
	 * @param actionBars the action bars
	 */
	public FormEntryAdapter(final IActionBars actionBars) {
		this.actionBars = actionBars;
	}

	/**
	 * {@inheritDoc}
	 * @since 6.0
	 */
	@Override
	public void buttonSelected(final FormEntry entry) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void linkEntered(final HyperlinkEvent e) {
		if (this.actionBars == null) {
			return;
		}
		final IStatusLineManager mng = this.actionBars.getStatusLineManager();
		mng.setMessage(e.getLabel());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void linkExited(final HyperlinkEvent e) {
		if (this.actionBars == null) {
			return;
		}
		final IStatusLineManager mng = this.actionBars.getStatusLineManager();
		mng.setMessage(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void linkActivated(final HyperlinkEvent e) {
	}

	/**
	 * {@inheritDoc}
	 * @since 6.0
	 */
	public void selectionChanged(final FormEntry entry) {

	}
}
