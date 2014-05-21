/*******************************************************************************
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.internal.ui.preferences;

import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 *
 */
public class ReadOnlyStringFieldEditor extends StringFieldEditor {

	private Color textColor;

	public ReadOnlyStringFieldEditor(String name, String labelText, Composite parent) {
		super(name, labelText, parent);
		initColors();
	}

	public ReadOnlyStringFieldEditor(String name, String labelText, int width, Composite parent) {
		super(name, labelText, width, parent);
		initColors();
	}

	public ReadOnlyStringFieldEditor(String name, String labelText, int width, int strategy, Composite parent) {
		super(name, labelText, width, strategy, parent);
		initColors();
	}

	@Override
	public Text getTextControl(Composite parent) {
		Text retVal = super.getTextControl(parent);
		retVal.setEditable(false);
		return retVal;
	}

	@Override
	public Label getLabelControl(Composite parent) {
		Label retVal = super.getLabelControl(parent);
		retVal.setEnabled(false);
		return retVal;
	}

	private void initColors() {
		ColorRegistry colorRegistry = JFaceResources.getColorRegistry();
		textColor = colorRegistry.get("DarkGray");
		if (textColor == null) {
			if (!colorRegistry.hasValueFor("DarkGray")) {
				colorRegistry.put("DarkGray", new RGB(0x69, 0x69, 0x69));
			}
			textColor = colorRegistry.get("DarkGray");
		}
		getTextControl().setForeground(textColor);
	}

	@Override
	public void loadDefault() {
		// ignore for read only field
	}

	@Override
	public void store() {
		// ignore for read only field
	}
}
