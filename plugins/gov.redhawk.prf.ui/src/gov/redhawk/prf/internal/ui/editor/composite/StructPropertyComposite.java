/**
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.prf.internal.ui.editor.composite;

import gov.redhawk.common.ui.editor.FormLayoutFactory;

import java.util.ArrayList;
import java.util.List;

import mil.jpeojtrs.sca.prf.Struct;
import mil.jpeojtrs.sca.prf.StructSequence;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * 
 */
public class StructPropertyComposite extends BasicStructPropertyComposite {

	private boolean hiddenControls;
	private ArrayList<Control> hideableControls;

	/**
	 * @param parent
	 * @param style
	 * @param toolkit
	 */
	public StructPropertyComposite(final Composite parent, final int style, final FormToolkit toolkit) {
		super(parent, style, toolkit);

		setLayout(FormLayoutFactory.createSectionClientGridLayout(false, AbstractPropertyComposite.NUM_COLUMNS));
		setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		createIDEntryField(toolkit, this);

		createNameEntryField(toolkit, this);

		createConfigurationKindViewer(this, toolkit);

		createMode(this, toolkit);

		createDescription(this, toolkit);

		toolkit.paintBordersFor(this);
	}

	@Override
	public void createTabList() {
		this.tabList.clear();
		this.tabList.add(getIdEntry().getText());
		this.tabList.add(getConfigurationKindViewer().getControl());
		this.tabList.add(getModeViewer().getControl());
		this.tabList.add(getDescriptionText());
	}

	private List<Control> getHideableControls() {
		if (hideableControls == null) {
			hideableControls = new ArrayList<Control>();
			hideableControls.add(this.getModeLabel());
			hideableControls.add(this.getModeViewer().getControl());
			hideableControls.add(this.getKindLabel());
			hideableControls.add(this.getConfigurationKindViewer().getControl());
		}
		return hideableControls;
	}

	/**
	 * Hides or shows controls based on the current state and the parent of the struct.
	 */
	public void updateHiddenControls(Struct struct) {
		//If the viewers are hidden and this simple doesn't belong to a struct, show the viewers
		if (this.hiddenControls && !(struct.eContainer() instanceof StructSequence)) {
			for (Control control : getHideableControls()) {
				((GridData) control.getLayoutData()).exclude = false;
				control.setVisible(true);
			}
			this.hiddenControls = false;
		} else if (!this.hiddenControls && struct.eContainer() instanceof StructSequence) {
			for (Control control : getHideableControls()) {
				((GridData) control.getLayoutData()).exclude = true;
				control.setVisible(false);
			}
			this.hiddenControls = true;
		}
		this.layout();
		// If you don't recompute size with the correct width the section get's resized to be narrow
		this.getParent().setSize(this.getParent().computeSize(this.getParent().getSize().x, SWT.DEFAULT, true));
		this.getParent().layout();
	}

}
