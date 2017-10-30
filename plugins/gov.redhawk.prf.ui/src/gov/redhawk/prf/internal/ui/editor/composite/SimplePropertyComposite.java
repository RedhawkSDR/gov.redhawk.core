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
import gov.redhawk.common.ui.parts.FormEntry;

import java.util.ArrayList;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * 
 */
public class SimplePropertyComposite extends BasicSimplePropertyComposite {

	private FormEntry valueEntry;
	private Button commandLineCheckbox;

	public SimplePropertyComposite(final Composite parent, final int style, final FormToolkit toolkit) {
		super(parent, style, toolkit);

		createControls(this, toolkit);
	}
	
	protected void createControls(Composite parent, FormToolkit toolkit) {
		parent.setLayout(FormLayoutFactory.createSectionClientGridLayout(false, NUM_COLUMNS));
		
		createNameEntryField(toolkit, parent);
		createIDEntryField(toolkit, parent);
		createTypeViewer(parent, toolkit);
		createValueEntry(parent, toolkit);
		createUnitsEntry(parent, toolkit);
		createKindViewer(parent, toolkit);
		// Adjust the kind viewer to only span 1 column to make room for the command line checkbox
		getKindViewer().getControl().setLayoutData(GridDataFactory.fillDefaults().span(1, 1).grab(true, false).create());
		createCommandLineCheckbox(parent, toolkit);
		createModeViewer(parent, toolkit);
		createActionViewer(parent, toolkit);
		createEnumerationsViewer(parent, toolkit);
		createRange(parent, toolkit);
		createDescription(parent, toolkit);
		
		ArrayList<Control> tabList = new ArrayList<Control>();
		tabList.add(getIdEntry().getText());
		tabList.add(getNameEntry().getText());
		tabList.add(getTypeViewer().getControl());
		tabList.add(getValueEntry().getText());
		tabList.add(getUnitsEntry().getText());
		tabList.add(getKindViewer().getControl());
		tabList.add(getModeViewer().getControl());
		tabList.add(getActionViewer().getControl());
		tabList.add(getEnumerationViewer().getControl().getParent());
		tabList.add(getRangeButton());
		tabList.add(getMinText().getText().getParent());
		tabList.add(getMaxText().getText().getParent());
		tabList.add(getDescriptionText());
		
		parent.setTabList(tabList.toArray(new Control[tabList.size()]));

		toolkit.paintBordersFor(parent);
	}

	protected void createCommandLineCheckbox(Composite parent, FormToolkit toolkit) {
		this.commandLineCheckbox = toolkit.createButton(parent, "Pass on command line", SWT.CHECK);
		this.commandLineCheckbox.setToolTipText("Indicates that the property's initial value at launch should be passed to the resource on the command line");
		this.commandLineCheckbox.setLayoutData(GridDataFactory.fillDefaults().span(1, 1).grab(false, false).create());
	}

	protected FormEntry createValueEntry(final Composite parent, final FormToolkit toolkit) {
		// Value
		FormEntry retVal = new FormEntry(parent, toolkit, "Value:" , SWT.SINGLE);
		retVal.getText().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
		retVal.getText().setToolTipText("For empty string enter a value of \"\"");
		this.valueEntry = retVal;
		return retVal;
	}

	public FormEntry getValueEntry() {
		return this.valueEntry;
	}

	public Button getCommandLineCheckbox() {
		return this.commandLineCheckbox;
	}

	@Override
	public void setEditable(final boolean canEdit) {
		super.setEditable(canEdit);

		if (this.valueEntry != null) {
			this.valueEntry.setEditable(canEdit);
		}
		if (this.commandLineCheckbox != null) {
			this.commandLineCheckbox.setEnabled(canEdit);
		}
		super.setEditable(canEdit);
	}
}
