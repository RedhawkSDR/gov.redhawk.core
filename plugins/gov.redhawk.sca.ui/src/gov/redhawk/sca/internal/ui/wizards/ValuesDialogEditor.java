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
package gov.redhawk.sca.internal.ui.wizards;

import java.util.Arrays;

import mil.jpeojtrs.sca.prf.PropertyValueType;

import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * 
 */
public class ValuesDialogEditor extends DialogCellEditor {

	private final PropertyValueType type;

	/**
	 * @param parent
	 */
	public ValuesDialogEditor(final Composite parent, final PropertyValueType type) {
		super(parent);
		this.type = type;
	}

	@Override
	protected void updateContents(final Object value) {
		if (getDefaultLabel() == null) {
			return;
		}

		String text = ""; //$NON-NLS-1$
		if (value != null && value instanceof Object[]) {
			text = Arrays.toString((Object[]) value);
		}
		getDefaultLabel().setText(text);
	}

	@Override
	protected void doSetValue(Object value) {
		if (value instanceof String && ((String) value).startsWith("[") && ((String) value).endsWith("]")) {
			final String val = (String) value;
			value = val.substring(1, val.length() - 1).split(", ");
		}

		super.doSetValue(value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Object openDialogBox(final Control cellEditorWindow) {
		final ValuesWizard wizard = new ValuesWizard(this.type);
		wizard.setInput((String[]) getValue());
		final WizardDialog dialog = new WizardDialog(cellEditorWindow.getShell(), wizard);
		if (dialog.open() == Window.OK) {
			return wizard.getValues();
		}
		return null;
	}

}
