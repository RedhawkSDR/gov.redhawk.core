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

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * 
 */
public class StructSequenceSimplePropertyComposite extends SimplePropertyComposite {

	/**
	 * @param parent
	 * @param style
	 * @param toolkit
	 */
	public StructSequenceSimplePropertyComposite(final Composite parent, final int style, final FormToolkit toolkit) {
		super(parent, style, toolkit);
		
	}
	
	@Override
	protected void createControls(Composite parent , FormToolkit toolkit) {
		parent.setLayout(FormLayoutFactory.createSectionClientGridLayout(false, NUM_COLUMNS));

		createIDEntryField(toolkit, parent);
		createNameEntryField(toolkit, parent);
		createTypeViewer(parent, toolkit);
		createValueEntry(parent, toolkit);
		createUnitsEntry(parent, toolkit);
		createEnumerationsViewer(parent, toolkit);
		createRange(parent, toolkit);
		createDescription(parent, toolkit);

		toolkit.paintBordersFor(parent);
		
		ArrayList<Control> tabList = new ArrayList<Control>();
		
		tabList.add(getIdEntry().getText());
		tabList.add(getNameEntry().getText());
		tabList.add(getTypeViewer().getControl());
		tabList.add(getValueEntry().getText());
		tabList.add(getUnitsEntry().getText());
		tabList.add(getEnumerationViewer().getControl().getParent());
		tabList.add(getRangeButton());
		tabList.add(getMinText().getText().getParent());
		tabList.add(getMaxText().getText().getParent());
		tabList.add(getDescriptionText());
		
		parent.setTabList(tabList.toArray(new Control[tabList.size()]));
	}
	
	@Override
	protected FormEntry createValueEntry(Composite parent, FormToolkit toolkit) {
	    FormEntry retVal = super.createValueEntry(parent, toolkit);
	    retVal.setLabel("Default Value:");
	    return retVal;
	}
}
