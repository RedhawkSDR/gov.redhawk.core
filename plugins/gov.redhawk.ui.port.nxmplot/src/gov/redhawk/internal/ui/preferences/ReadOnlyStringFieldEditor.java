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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * 
 */
public class ReadOnlyStringFieldEditor extends StringFieldEditor {

	public ReadOnlyStringFieldEditor(String name, String labelText, Composite parent) {
		super(name, labelText, parent);
	}

	public ReadOnlyStringFieldEditor(String name, String labelText, int width, Composite parent) {
		super(name, labelText, width, parent);
	}

	public ReadOnlyStringFieldEditor(String name, String labelText, int width, int strategy, Composite parent) {
		super(name, labelText, width, strategy, parent);
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

}
