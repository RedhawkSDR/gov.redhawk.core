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
package gov.redhawk.ui.port.nxmblocks;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Combo;

/**
 * @since 4.3 (package-private for now)
 */
class SelectAllTextComboTextListener implements ISelectionChangedListener {
	private final Combo combo;

	SelectAllTextComboTextListener(Combo combo) {
		this.combo = combo;
	}

	public void selectionChanged(final SelectionChangedEvent event) {
		final String text = this.combo.getText();
		final int textLen = (text == null) ? 0 : text.length(); // SUPPRESS CHECKSTYLE AvoidInline
		this.combo.setSelection(new Point(0, textLen)); // select text from combo selection
	}
}