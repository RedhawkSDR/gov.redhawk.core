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
package gov.redhawk.scd.internal.ui.editor;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IDetailsPageProvider;
import org.eclipse.ui.forms.IManagedForm;

import gov.redhawk.scd.internal.ui.editor.detailspart.PortDetailsPage;
import gov.redhawk.scd.ui.editor.page.PortsFormPage;
import gov.redhawk.ui.editor.SCAMasterDetailsBlock;
import gov.redhawk.ui.editor.ScaSection;
import mil.jpeojtrs.sca.scd.AbstractPort;

public class PortsBlock extends SCAMasterDetailsBlock {

	private PortsSection fSection;

	public PortsBlock(PortsFormPage page) {
		super(page);
	}

	@Override
	protected ScaSection createMasterSection(IManagedForm managedForm, Composite parent) {
		this.fSection = new PortsSection(this, parent);
		return this.fSection;
	}

	@Override
	protected void registerPages(DetailsPart detailsPart) {
		detailsPart.registerPage(AbstractPort.class, new PortDetailsPage(getPage()));
		detailsPart.setPageProvider(new IDetailsPageProvider() {
			
			@Override
			public Object getPageKey(Object object) {
				return AbstractPort.class;
			}
			
			@Override
			public IDetailsPage getPage(Object key) {
				return null;
			}
		});
	}

	public PortsSection getSection() {
		return this.fSection;
	}
}
