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
import gov.redhawk.prf.internal.ui.dialogs.SimpleRefSelectorDialog;
import gov.redhawk.prf.internal.ui.editor.PropertiesSection;
import gov.redhawk.prf.internal.ui.editor.composite.SimpleRefComposite;
import gov.redhawk.ui.editor.ScaDetails;
import gov.redhawk.ui.editor.ScaFormPage;
import gov.redhawk.ui.parts.FormEntryBindingFactory;
import gov.redhawk.ui.util.EMFEmptyStringToNullUpdateValueStrategy;
import gov.redhawk.ui.util.SCAEditorUtil;

import java.util.ArrayList;
import java.util.List;

import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleRef;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

/**
 * 
 */
public class SimpleRefDetailsPage extends ScaDetails {

	private final PropertiesSection fSection;
	private SimpleRefComposite client;
	private SimpleRef input;

	/**
	 * The Constructor.
	 * 
	 * @param fSection the f section
	 */
	public SimpleRefDetailsPage(final PropertiesSection fSection) {
		super(fSection.getPage());
		this.fSection = fSection;
	}

	/**
	 * Creates the property ref section.
	 * 
	 * @param toolkit the toolkit
	 * @param parent the parent
	 */
	private void createPropertyRefSection(final FormToolkit toolkit, final Composite parent) {
		final Section section = toolkit.createSection(parent, Section.DESCRIPTION | ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE
		        | ExpandableComposite.EXPANDED);
		section.clientVerticalSpacing = FormLayoutFactory.SECTION_HEADER_VERTICAL_SPACING;
		section.setText("Simple Ref");
		section.setDescription("The 'simle' element is used to indicate " + "a unique refid attribute that references a simple allocation property,"
		        + " defined in the package, and a property value attribute used by the domain "
		        + "Management function to perform the dependency check. This 'refid' is a DCE UUID.");

		section.setLayout(FormLayoutFactory.createClearGridLayout(false, 1));
		section.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING));

		// Align the master and details section headers (misalignment caused
		// by section toolbar icons)
		getPage().alignSectionHeaders(this.fSection.getSection(), section);

		this.client = new SimpleRefComposite(section, SWT.None, toolkit);
		this.client.getIdEntry().getButton().addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(final SelectionEvent e) {

				final SimpleRefSelectorDialog dialog = new SimpleRefSelectorDialog(getPage().getSite().getShell(), SimpleRefDetailsPage.this.input);
				final int result = dialog.open();
				if (result == Window.OK) {
					final Simple sel = (Simple) dialog.getFirstResult();
					if (sel != null) {
						SimpleRefDetailsPage.this.client.getIdEntry().getText().setText(sel.getId());
					}
				}
			}
		});
		section.setClient(this.client);
		toolkit.adapt(this.client);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ScaFormPage getPage() {
		return this.fSection.getPage();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<Binding> bind(final DataBindingContext dataBindingContext, final EObject input) {
		final List<Binding> retVal = new ArrayList<Binding>();
		this.input = (SimpleRef) input;
		retVal.add(FormEntryBindingFactory.bind(dataBindingContext, this.client.getIdEntry(), getEditingDomain(), PrfPackage.Literals.ABSTRACT_PROPERTY_REF__REF_ID,
		        input, new EMFEmptyStringToNullUpdateValueStrategy(), null));
		retVal.add(FormEntryBindingFactory.bind(dataBindingContext, this.client.getValueEntry(), getEditingDomain(), PrfPackage.Literals.SIMPLE_REF__VALUE,
		        input, null, null));
		this.client.setEditable(SCAEditorUtil.isEditableResource(getPage(), input.eResource()));
		return retVal;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void createSpecificContent(final Composite parent) {
		final FormToolkit toolkit = getManagedForm().getToolkit();
		createPropertyRefSection(toolkit, parent);
	}
}
