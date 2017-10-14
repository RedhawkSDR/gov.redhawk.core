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

import gov.redhawk.common.ui.editor.FormLayoutFactory;
import gov.redhawk.prf.internal.ui.editor.PropertiesSection;
import gov.redhawk.prf.internal.ui.editor.composite.SimplePropertyComposite;
import gov.redhawk.ui.editor.SCAFormEditor;
import gov.redhawk.ui.util.EMFEmptyStringToNullUpdateValueStrategy;
import gov.redhawk.ui.util.EmptyStringValueToStringConverter;
import gov.redhawk.ui.util.FalseToNullValueConverter;
import gov.redhawk.ui.util.StringToEmptyStringValueConverter;

import java.util.List;

import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.PropertyConfigurationType;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.emf.databinding.EMFUpdateValueStrategy;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.databinding.edit.IEMFEditValueProperty;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

public class SimplePropertyDetailsPage extends BasicSimplePropertyDetailsPage {

	/**
	 * Instantiates a new simple property details page.
	 * 
	 * @param section the section
	 */
	public SimplePropertyDetailsPage(final PropertiesSection section) {
		super(section);
	}

	@Override
	protected List<Binding> bind(final DataBindingContext dataBindingContext, final EObject input) {
		final List<Binding> retVal = super.bind(dataBindingContext, input);

		final EditingDomain domain = getEditingDomain();

		// Value
		if (getComposite().getValueEntry() != null) {
			EMFEmptyStringToNullUpdateValueStrategy targetToModel = new EMFEmptyStringToNullUpdateValueStrategy();
			targetToModel.setConverter(new EmptyStringValueToStringConverter());
			EMFUpdateValueStrategy modelToTarget = new EMFUpdateValueStrategy();
			modelToTarget.setConverter(new StringToEmptyStringValueConverter());
			retVal.add(dataBindingContext.bindValue(
				WidgetProperties.text(SWT.Modify).observeDelayed(SCAFormEditor.getFieldBindingDelay(), getComposite().getValueEntry().getText()),
				EMFEditObservables.observeValue(domain, input, PrfPackage.Literals.SIMPLE__VALUE), targetToModel, modelToTarget));
		}

		// Command line
		if (getComposite().getCommandLineCheckbox() != null) {
			EMFUpdateValueStrategy targetToModel = new EMFUpdateValueStrategy();
			targetToModel.setConverter(new FalseToNullValueConverter());
			EMFUpdateValueStrategy modelToTarget = new EMFUpdateValueStrategy();
			modelToTarget.setConverter(new Converter(Boolean.class, Boolean.class) {

				@Override
				public Object convert(Object fromObject) {
					updateCommandLineEnabled();

					if (fromObject == null) {
						return false;
					}
					return (Boolean) fromObject == true;

				}

			});
			retVal.add(dataBindingContext.bindValue(WidgetProperties.selection().observe(getComposite().getCommandLineCheckbox()),
				EMFEditObservables.observeValue(domain, input, PrfPackage.Literals.SIMPLE__COMMANDLINE), targetToModel, modelToTarget));

			// Tie the enabled state of the checkbox to the current kind selection
			getComposite().getKindViewer().addSelectionChangedListener(new ISelectionChangedListener() {
				@Override
				public void selectionChanged(SelectionChangedEvent event) {
					updateCommandLineEnabled();
				}
			});
		}

		// Enumerations
		if (getComposite().getEnumerationViewer() != null) {
			final IEMFEditValueProperty enumProperty = EMFEditProperties.value(getEditingDomain(), FeaturePath.fromList(PrfPackage.Literals.SIMPLE__ENUMERATIONS));
			@SuppressWarnings({ "rawtypes", "unchecked" })
			IObservableValue modelObservableValue = enumProperty.observe(input);
			retVal.add(dataBindingContext.bindValue(ViewersObservables.observeInput(getComposite().getEnumerationViewer()), modelObservableValue));
		}

		return retVal;
	}

	protected void updateCommandLineEnabled() {
		// Only enable the commandline checkbox if "property" is selected for the kind
		IStructuredSelection selection = (IStructuredSelection) getComposite().getKindViewer().getSelection();
		boolean enabled = PropertyConfigurationType.PROPERTY.equals(selection.getFirstElement());
		getComposite().getCommandLineCheckbox().setEnabled(enabled);
	}

	@Override
	protected SimplePropertyComposite getComposite() {
		return (SimplePropertyComposite) super.getComposite();
	}

	@Override
	protected SimplePropertyComposite createSection(final Composite parent, final FormToolkit toolkit) {
		final Section newSection = toolkit.createSection(parent, Section.EXPANDED | Section.TITLE_BAR);
		newSection.clientVerticalSpacing = FormLayoutFactory.SECTION_HEADER_VERTICAL_SPACING;
		newSection.setText("Simple Property");
		newSection.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		newSection.setExpanded(true);

		// Align the master and details section headers (misalignment caused
		// by section toolbar icons)
		getPage().alignSectionHeaders(getSection().getSection(), newSection);

		SimplePropertyComposite retVal = createComposite(newSection, toolkit);
		newSection.setClient(retVal);

		return retVal;
	}

	public SimplePropertyComposite createComposite(Composite parent, FormToolkit toolkit) {
		SimplePropertyComposite retVal = new SimplePropertyComposite(parent, SWT.NONE, toolkit);
		toolkit.adapt(retVal);
		return retVal;
	}
}
