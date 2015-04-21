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
package gov.redhawk.prf.internal.ui.editor.composite;

import gov.redhawk.common.ui.editor.FormLayoutFactory;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class StructSimpleSequencePropertyComposite extends SimpleSequencePropertyComposite {

	/**
	 * @param parent
	 * @param style
	 * @param toolkit
	 */
	public StructSimpleSequencePropertyComposite(Composite parent, int style, FormToolkit toolkit) {
		super(parent, style, toolkit);
	}

	@Override
	protected void createControls(Composite parent, FormToolkit toolkit) {
		parent.setLayout(FormLayoutFactory.createSectionClientGridLayout(false, NUM_COLUMNS));

		createIDEntryField(toolkit, parent);
		createNameEntryField(toolkit, parent);
		createTypeViewer(parent, toolkit);
		createValues(parent, toolkit);
		createUnitsEntry(parent, toolkit);
		createRange(parent, toolkit);
		createOptionalCombo(parent, toolkit);
		createDescription(parent, toolkit);

		ArrayList<Control> tabList = new ArrayList<Control>();
		tabList.add(getIdEntry().getText());
		tabList.add(getNameEntry().getText());
		tabList.add(getTypeViewer().getControl());
		tabList.add(getValuesViewer().getControl().getParent());
		tabList.add(getUnitsEntry().getText());
		tabList.add(getRangeButton());
		tabList.add(getMinText().getText().getParent());
		tabList.add(getMaxText().getText().getParent());
		tabList.add(getOptionalCombo());
		tabList.add(getDescriptionText());

		parent.setTabList(tabList.toArray(new Control[tabList.size()]));

		toolkit.paintBordersFor(parent);
	}

}
