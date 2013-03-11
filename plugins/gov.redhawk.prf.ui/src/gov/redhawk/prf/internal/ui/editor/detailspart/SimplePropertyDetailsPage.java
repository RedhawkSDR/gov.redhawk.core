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
import gov.redhawk.prf.internal.ui.editor.composite.BasicSimplePropertyComposite;
import gov.redhawk.prf.internal.ui.editor.composite.SimplePropertyComposite;
import gov.redhawk.prf.ui.wizard.EnumerationWizard;
import gov.redhawk.ui.editor.SCAFormEditor;
import gov.redhawk.ui.util.EMFEmptyStringToNullUpdateValueStrategy;

import java.util.Collections;
import java.util.List;

import mil.jpeojtrs.sca.prf.Enumeration;
import mil.jpeojtrs.sca.prf.Enumerations;
import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.Simple;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.databinding.edit.IEMFEditValueProperty;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.ReplaceCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

/**
 * The Class SimplePropertyDetailsPage.
 */
public class SimplePropertyDetailsPage extends BasicSimplePropertyDetailsPage {

	private Simple input;
	private SimplePropertyComposite composite;

	/**
	 * Instantiates a new simple property details page.
	 * 
	 * @param section the section
	 */
	public SimplePropertyDetailsPage(final PropertiesSection section) {
		super(section);
	}

	/**
	 * 
	 */
	@Override
	protected void addListeners() {
		super.addListeners();
		this.composite.getAddEnumButton().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				handleAddEnum();
			}
		});
		this.composite.getEditEnumButton().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				handleEditEnumeration();
			}
		});
		this.composite.getRemoveEnumButton().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				handleRemoveEnumeration();
			}
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<Binding> bind(final DataBindingContext dataBindingContext, final EObject input) {
		final List<Binding> retVal = super.bind(dataBindingContext, input);

		final EditingDomain domain = getEditingDomain();
		this.input = (Simple) input;

		this.composite.updateHiddenControls(this.input);
		this.composite.createTabList();

		// Value
		retVal.add(dataBindingContext.bindValue(
		        WidgetProperties.text(SWT.Modify).observeDelayed(SCAFormEditor.getFieldBindingDelay(), this.composite.getValueText()),
		        EMFEditObservables.observeValue(domain, input, PrfPackage.Literals.SIMPLE__VALUE), new EMFEmptyStringToNullUpdateValueStrategy(), null));

		//Enumerations
		final IEMFEditValueProperty enumProperty = EMFEditProperties.value(getEditingDomain(), FeaturePath.fromList(PrfPackage.Literals.SIMPLE__ENUMERATIONS));
		retVal.add(dataBindingContext.bindValue(ViewersObservables.observeInput(this.composite.getEnumerationViewer()), enumProperty.observe(input)));

		return retVal;
	}

	/**
	 * @param parent
	 * @param toolkit
	 */
	@Override
	protected BasicSimplePropertyComposite createSection(final Composite parent, final FormToolkit toolkit) {
		final Section newSection = toolkit.createSection(parent, Section.DESCRIPTION | ExpandableComposite.TITLE_BAR);
		newSection.clientVerticalSpacing = FormLayoutFactory.SECTION_HEADER_VERTICAL_SPACING;
		newSection.setText("Simple Property");
		newSection.setDescription("");
		newSection.setLayout(FormLayoutFactory.createClearGridLayout(false, 1));
		newSection.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.TOP).grab(true, true).create());

		// Align the master and details section headers (misalignment caused
		// by section toolbar icons)
		getPage().alignSectionHeaders(getSection().getSection(), newSection);

		this.composite = new SimplePropertyComposite(newSection, SWT.NONE, toolkit);
		toolkit.adapt(this.composite);
		newSection.setClient(this.composite);
		return this.composite;
	}

	private Enumeration getEnumerationViewerSelection() {
		return (Enumeration) ((IStructuredSelection) ((SimplePropertyComposite) getComposite()).getEnumerationViewer().getSelection()).getFirstElement();
	}

	public void handleAddEnum() {
		final EnumerationWizard wizard = new EnumerationWizard();

		final WizardDialog dialog = new WizardDialog(getPage().getSite().getShell(), wizard);

		if (dialog.open() == Window.OK) {
			final Enumeration enumeration = wizard.getEnumeration();
			if (enumeration != null) {
				Command command = null;
				Enumerations enums = this.input.getEnumerations();
				if (enums == null) {
					enums = PrfFactory.eINSTANCE.createEnumerations();
					enums.getEnumeration().add(enumeration);
					command = SetCommand.create(getEditingDomain(), this.input, PrfPackage.Literals.SIMPLE__ENUMERATIONS, enums);
				} else {
					command = AddCommand.create(getEditingDomain(), enums, PrfPackage.Literals.ENUMERATIONS__ENUMERATION, enumeration);
				}
				execute(command);
			}
		}
	}

	/**
	 * Handle edit enumeration.
	 */
	protected void handleEditEnumeration() {
		final EnumerationWizard wizard = new EnumerationWizard();
		final Enumeration enumeration = getEnumerationViewerSelection();

		wizard.setEnumeration(enumeration);

		final WizardDialog dialog = new WizardDialog(getPage().getSite().getShell(), wizard);

		if (dialog.open() == Window.OK) {
			final Command command = ReplaceCommand.create(getEditingDomain(), this.input.getEnumerations(), PrfPackage.Literals.ENUMERATIONS__ENUMERATION,
			        enumeration, Collections.singleton(wizard.getEnumeration()));
			execute(command);
		}
	}

	/**
	 * Handle enumeration removed.
	 */
	protected void handleRemoveEnumeration() {
		Command command = null;
		if (this.input.getEnumerations() != null) {
			command = RemoveCommand.create(getEditingDomain(), this.input.getEnumerations(), PrfPackage.Literals.ENUMERATIONS__ENUMERATION,
			        getEnumerationViewerSelection());
			if (this.input.getEnumerations().getEnumeration().size() - 1 == 0) {
				command = SetCommand.create(getEditingDomain(), this.input, PrfPackage.Literals.SIMPLE__ENUMERATIONS, null);
			}
		}
		if (command != null && command.canExecute()) {
			execute(command);
		}
	}

}
