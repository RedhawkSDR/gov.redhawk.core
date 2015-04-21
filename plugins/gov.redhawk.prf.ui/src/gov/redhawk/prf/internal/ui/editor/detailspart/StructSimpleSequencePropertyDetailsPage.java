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
import gov.redhawk.prf.internal.ui.editor.composite.StructSimpleSequencePropertyComposite;

import org.eclipse.swt.SWT;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

public class StructSimpleSequencePropertyDetailsPage extends SimpleSequencePropertyDetailsPage {

	/**
	 * @param section - property section for simple sequences contained within a struct
	 */
	public StructSimpleSequencePropertyDetailsPage(PropertiesSection section) {
		super(section);
	}

	@Override
	public StructSimpleSequencePropertyComposite getComposite() {
		return (StructSimpleSequencePropertyComposite) super.getComposite();
	}

	@Override
	public StructSimpleSequencePropertyComposite createComposite(Section parent, FormToolkit toolkit) {
		StructSimpleSequencePropertyComposite retVal = new StructSimpleSequencePropertyComposite(parent, SWT.NONE, toolkit);
		toolkit.adapt(retVal);
		return retVal;
	}

}
