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

import gov.redhawk.ui.port.nxmblocks.PlotNxmBlock;
import gov.redhawk.ui.port.nxmplot.INxmBlock;
import gov.redhawk.ui.port.nxmplot.PlotPageBook2;

import java.util.List;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * 
 */
public class SourcePreferencePage extends PreferencePage {

	private PlotPageBook2 pageBook;
	private List<INxmBlock> sourceBlocks;

	public SourcePreferencePage(String label, PlotPageBook2 pageBook, List<INxmBlock> sourceBlocks) {
		super(label);
		setDescription("Modify how this particular source is being plotting.");
		this.sourceBlocks = sourceBlocks;
		this.pageBook = pageBook;
	}

	@Override
	protected Control createContents(Composite parent) {
		Composite main = new Composite(parent, SWT.None);
		main.setLayout(new GridLayout(3, false));

		Button hideButton = new Button(main, SWT.PUSH);
		hideButton.setText("Hide");
		hideButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PlotNxmBlock plotBlock = (PlotNxmBlock) sourceBlocks.get(sourceBlocks.size() - 1);
				plotBlock.hide();
			}
		});

		Button showButton = new Button(main, SWT.PUSH);
		showButton.setText("Show");
		showButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PlotNxmBlock plotBlock = (PlotNxmBlock) sourceBlocks.get(sourceBlocks.size() - 1);
				plotBlock.show();
			}
		});

		return main;
	}

}
