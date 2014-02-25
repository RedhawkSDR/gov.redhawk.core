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

import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 *
 */
public class OverridableIntegerFieldEditor extends IntegerFieldEditor {

	private String overridePref;
	private boolean override;
	private ModifyListener listener = new ModifyListener() {

		@Override
		public void modifyText(ModifyEvent e) {
			if (getTextControl().getText().isEmpty() || "AUTO".equalsIgnoreCase(getTextControl().getText())) {
				override = false;
			} else {
				override = true;
			}
			updateTextFieldColor();
		}
	};
	private Color defaultForeground;
	private Color disabledForeground;

	public OverridableIntegerFieldEditor(String name, String nameOverride, String labelText, Composite parent) {
		super(name, labelText, parent);
		this.overridePref = nameOverride;
		final Text control = getTextControl();
		control.addModifyListener(listener);
		control.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (!override) {
					control.setText("AUTO");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (!override) {
					control.setText("");
				}
			}
		});
		setEmptyStringAllowed(true);
		initColors(control);
		updateTextFieldColor();
	}

	public void setToolTipText(String val) {
		Text text = getTextControl();

		if (text != null) {
			text.setToolTipText(val);
		}
	}

	private void initColors(Text retVal) {
		defaultForeground = retVal.getForeground();
		ColorRegistry colorRegistry = JFaceResources.getColorRegistry();
		disabledForeground = colorRegistry.get("DarkGray");
		if (disabledForeground == null) {
			if (!colorRegistry.hasValueFor("DarkGray")) {
				colorRegistry.put("DarkGray", new RGB(0x69, 0x69, 0x69));
			}
			disabledForeground = colorRegistry.get("DarkGray");
		}
	}

	@Override
	protected boolean checkState() {
		Text text = getTextControl();

		if (text == null) {
			return false;
		}

		if ("AUTO".equals(text.getText())) {
			return true;
		} else if ("".equals(text.getText())) {
			return true;
		}
		return super.checkState();
	}

	@Override
	protected void doLoad() {
		this.override = getPreferenceStore().getBoolean(overridePref);
		Text text = getTextControl();
		if (text != null) {
			if (override) {
				int value = getPreferenceStore().getInt(getPreferenceName());
				text.setText("" + value); //$NON-NLS-1$
				oldValue = "" + value; //$NON-NLS-1$
			} else {
				text.setText("AUTO");
				oldValue = "AUTO"; //$NON-NLS-1$
			}
		}
	}

	private void updateTextFieldColor() {
		Text control = getTextControl();
		if (control == null) {
			return;
		}
		if (!this.override) {
			getTextControl().setForeground(disabledForeground);
		} else {
			getTextControl().setForeground(defaultForeground);
		}
	}

	@Override
	protected void doLoadDefault() {
		this.override = getPreferenceStore().getDefaultBoolean(overridePref);
		Text text = getTextControl();
		if (text != null) {
			if (override) {
				int value = getPreferenceStore().getDefaultInt(getPreferenceName());
				text.setText("" + value); //$NON-NLS-1$
			} else {
				text.setText("AUTO"); //$NON-NLS-1$
			}
		}
		valueChanged();
	}

	@Override
	protected void doStore() {
		Text text = getTextControl();
		if (text != null) {
			if (override) {
				Integer i = new Integer(text.getText());
				getPreferenceStore().setValue(getPreferenceName(), i.intValue());
				getPreferenceStore().setValue(overridePref, true);
			} else {
				getPreferenceStore().setValue(overridePref, false);
				getPreferenceStore().setToDefault(getPreferenceName());
			}
		}
	}
}
