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
package gov.redhawk.prf.internal.ui.editor.detailspart;

import gov.redhawk.prf.internal.ui.editor.PropertiesSection;
import gov.redhawk.prf.internal.ui.editor.composite.StructSequenceSimplePropertyComposite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * The Class SimplePropertyDetailsPage.
 */
public class StructSequenceSimplePropertyDetailsPage extends SimplePropertyDetailsPage {

	/**
	 * Instantiates a new simple property details page.
	 * 
	 * @param section the section
	 */
	public StructSequenceSimplePropertyDetailsPage(final PropertiesSection section) {
		super(section);
	}
	
	@Override
	protected StructSequenceSimplePropertyComposite getComposite() {
	    return (StructSequenceSimplePropertyComposite) super.getComposite();
	}

	
	public StructSequenceSimplePropertyComposite createComposite(Composite parent, FormToolkit toolkit) {
		StructSequenceSimplePropertyComposite retVal = new StructSequenceSimplePropertyComposite(parent, SWT.NONE, toolkit);
		toolkit.adapt(retVal);
		return retVal;
	}

	

	

}
