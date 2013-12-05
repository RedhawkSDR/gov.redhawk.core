/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.ui.port.nxmblocks;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Combo;

/** 
 * Listener to select the text in Combo widget when it's selection changes.
 * @since 4.3 (package-private for now)
 */
class SelectAllTextComboTextListener implements ISelectionChangedListener {
	private final Combo combo;

	SelectAllTextComboTextListener(@NonNull Combo combo) {
		this.combo = combo;
	}

	public void selectionChanged(final SelectionChangedEvent event) {
		String text = this.combo.getText();
		int textLen = (text == null) ? 0 : text.length(); // SUPPRESS CHECKSTYLE AvoidInline
		this.combo.setSelection(new Point(0, textLen)); // select text from combo selection
	}
}
