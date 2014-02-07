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

import gov.redhawk.ui.port.nxmplot.PlotPageBook2;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * 
 */
public class SourcePreferencePage extends PreferencePage {

	public SourcePreferencePage(String label, PlotPageBook2 pageBook) {
		super(label);
		setDescription("Modify how this particular source is being plotting.");
	}

	@Override
	protected Control createContents(Composite parent) {
		Composite main = new Composite(parent, SWT.None);
		main.setLayout(new GridLayout(3, false));

		Button hideButton = new Button(main, SWT.PUSH);
		hideButton.setText("Hide");

		Button showButton = new Button(main, SWT.PUSH);
		showButton.setText("Show");

		Button removeButton = new Button(main, SWT.PUSH);
		removeButton.setText("Remove");

		return main;
	}

}
