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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * 
 */
public class SimplePropertyComposite extends BasicSimplePropertyComposite {

	private FormEntry valueEntry;

	/**
	 * @param parent
	 * @param style
	 * @param toolkit
	 */
	public SimplePropertyComposite(final Composite parent, final int style, final FormToolkit toolkit) {
		super(parent, style, toolkit);

		createControls(this, toolkit);		
	}
	
	protected void createControls(Composite parent, FormToolkit toolkit) {
		parent.setLayout(FormLayoutFactory.createSectionClientGridLayout(false, NUM_COLUMNS));
		
		createIDEntryField(toolkit, parent);
		createNameEntryField(toolkit, parent);
		createTypeViewer(parent, toolkit);
		createValueEntry(parent, toolkit);
		createUnitsEntry(parent, toolkit);
		createMessage(parent, toolkit);
		createKindViewer(parent, toolkit);
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
		tabList.add(getMessageButton());
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

	protected FormEntry createValueEntry(final Composite parent, final FormToolkit toolkit) {
		// Value
		FormEntry retVal = new FormEntry(parent, toolkit, "Value:" , SWT.SINGLE);
		retVal.getText().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
		retVal.getText().setToolTipText("For empty string enter a value of \"\"");
		this.valueEntry = retVal;
		return retVal;
	}

	/**
	 * @return the valueText
	 */
	public FormEntry getValueEntry() {
		return this.valueEntry;
	}
	
	@Override
	public void setEditable(final boolean canEdit) {
		if (this.valueEntry != null) {
			this.valueEntry.setEditable(canEdit);
		}
		super.setEditable(canEdit);
	}


}
