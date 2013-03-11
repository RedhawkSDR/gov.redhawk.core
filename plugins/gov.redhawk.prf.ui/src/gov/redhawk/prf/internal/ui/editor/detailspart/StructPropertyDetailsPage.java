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
package gov.redhawk.prf.internal.ui.editor.detailspart;

import gov.redhawk.common.ui.editor.FormLayoutFactory;
import gov.redhawk.prf.internal.ui.editor.PropertiesSection;
import gov.redhawk.prf.internal.ui.editor.composite.BasicStructPropertyComposite;
import gov.redhawk.prf.internal.ui.editor.composite.StructPropertyComposite;

import java.util.List;

import mil.jpeojtrs.sca.prf.Struct;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

/**
 * The Class SimplePropertyDetailsPage.
 */
public class StructPropertyDetailsPage extends BasicStructPropertyDetailsPage {

	private StructPropertyComposite structComposite;

	/**
	 * Instantiates a new simple property details page.
	 * 
	 * @param section the section
	 */
	public StructPropertyDetailsPage(final PropertiesSection section) {
		super(section);
	}

	/**
	 * @param parent
	 * @param toolkit
	 */
	@Override
	protected BasicStructPropertyComposite createSection(final Composite parent, final FormToolkit toolkit) {
		final Section newSection = toolkit.createSection(parent, Section.DESCRIPTION | ExpandableComposite.TITLE_BAR);
		newSection.clientVerticalSpacing = FormLayoutFactory.SECTION_HEADER_VERTICAL_SPACING;
		newSection.setText("Struct Property");
		newSection.setDescription("");
		newSection.setLayout(FormLayoutFactory.createClearGridLayout(false, 1));
		newSection.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING));

		// Align the master and details section headers (misalignment caused
		// by section toolbar icons)
		getPage().alignSectionHeaders(getSection().getSection(), newSection);

		this.structComposite = new StructPropertyComposite(newSection, SWT.NONE, toolkit);
		toolkit.adapt(this.structComposite);
		newSection.setClient(this.structComposite);
		return this.structComposite;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<Binding> bind(final DataBindingContext dataBindingContext, final EObject input) {
		this.structComposite.updateHiddenControls((Struct) input);
		final List<Binding> retVal = super.bind(dataBindingContext, input);
		return retVal;
	}
}
