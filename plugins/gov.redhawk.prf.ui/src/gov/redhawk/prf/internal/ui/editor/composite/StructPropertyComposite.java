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

/**
 * 
 */
public class StructPropertyComposite extends BasicStructPropertyComposite {

	public StructPropertyComposite(final Composite parent, final int style, final FormToolkit toolkit) {
		super(parent, style, toolkit);

		createControls(this, toolkit);

	}
	
	protected void createControls(Composite parent, FormToolkit toolkit) {
		parent.setLayout(FormLayoutFactory.createSectionClientGridLayout(false, AbstractPropertyComposite.NUM_COLUMNS));
		
		ArrayList<Control> tabList = new ArrayList<Control>();

		createIDEntryField(toolkit, parent);
		createNameEntryField(toolkit, parent);
		createConfigurationKindViewer(parent, toolkit);
		createModeViewer(parent, toolkit);
		createDescription(parent, toolkit);
		
		tabList.add(getIdEntry().getText());
		tabList.add(getNameEntry().getText());
		tabList.add(getConfigurationKindViewer().getControl());
		tabList.add(getModeViewer().getControl());
		tabList.add(getDescriptionText());
		
		parent.setTabList(tabList.toArray(new Control[tabList.size()]));

		toolkit.paintBordersFor(parent);
	}

}
