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
import gov.redhawk.prf.internal.ui.editor.composite.BasicSimplePropertyComposite;
import gov.redhawk.prf.internal.ui.editor.composite.SimpleSequencePropertyComposite;
import gov.redhawk.validation.prf.ValidValueTypeConstraint;

import java.util.List;

import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.PropertyValueType;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.Values;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.databinding.edit.IEMFEditValueProperty;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

/**
 * The Class SimpleSequencePropertyDetailsPage.
 */
public class SimpleSequencePropertyDetailsPage extends BasicSimplePropertyDetailsPage {

	private class InputValueValidator implements IInputValidator {

		private final PropertyValueType type;
		private final Boolean complex;

		public InputValueValidator(final PropertyValueType type, Boolean complex) {
			this.type = type;
			this.complex = complex;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String isValid(final String newText) {
			String retVal = null;
			boolean isComplexFormat = ValidValueTypeConstraint.isComplexNumber(newText);
			if (complex && !isComplexFormat) {
				final StringBuilder builder = new StringBuilder();
				builder.append("The value: \"");
				builder.append(newText);
				builder.append("\"");
				builder.append(" is invalid for PropertyValueType: ");
				builder.append("complex " + this.type.toString());
				retVal = builder.toString();
			} else {
				final boolean valid = this.type.isValueOfType(newText, this.complex);
				if (!valid) {
					final StringBuilder builder = new StringBuilder();
					builder.append("The value: \"");
					builder.append(newText);
					builder.append("\"");
					builder.append(" is invalid for PropertyValueType: ");
					builder.append(this.type.toString());
					retVal = builder.toString();
				}
			}
			return retVal;
		}
	}

	private SimpleSequence input;

	private SimpleSequencePropertyComposite composite;

	/**
	 * Instantiates a new simple sequence property details page.
	 * 
	 * @param section the section
	 */
	public SimpleSequencePropertyDetailsPage(final PropertiesSection section) {
		super(section);

	}

	/**
	 * 
	 */
	@Override
	protected void addListeners() {
		super.addListeners();

		this.composite.getRemoveValueButton().addSelectionListener(new SelectionAdapter() {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public void widgetSelected(final SelectionEvent e) {
				handleRemoveValue();
			}
		});

		this.composite.getAddValueButton().addSelectionListener(new SelectionAdapter() {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public void widgetSelected(final SelectionEvent e) {
				handleAddValue(e.widget.getDisplay().getActiveShell());
			}
		});

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<Binding> bind(final DataBindingContext context, final EObject input) {
		final List<Binding> retVal = super.bind(context, input);

		this.input = (SimpleSequence) input;

		// Values
		final IEMFEditValueProperty valueProp = EMFEditProperties.value(getEditingDomain(), FeaturePath.fromList(PrfPackage.Literals.SIMPLE_SEQUENCE__VALUES));
		final TableViewer valuesViewer = this.composite.getValuesViewer();
		retVal.add(context.bindValue(ViewersObservables.observeInput(valuesViewer), valueProp.observe(input)));

		if (this.isEditable()) {
			this.composite.getValueColumn().setEditingSupport(new SimpleSequenceValueEditingSupport(this.input.getType(), this.input.isComplex(), valuesViewer));
		} else {
			this.composite.getValueColumn().setEditingSupport(null);
		}

		return retVal;
	}

	/**
	 * @param parent
	 * @param toolkit
	 */
	@Override
	protected BasicSimplePropertyComposite createSection(final Composite parent, final FormToolkit toolkit) {
		final Section newSection = toolkit.createSection(parent,  Section.EXPANDED | ExpandableComposite.TITLE_BAR);
		newSection.clientVerticalSpacing = FormLayoutFactory.SECTION_HEADER_VERTICAL_SPACING;
		newSection.setText("Simple Sequence Property");
		newSection.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

		// Align the master and details section headers (misalignment caused
		// by section toolbar icons)
		getPage().alignSectionHeaders(getSection().getSection(), newSection);

		this.composite = new SimpleSequencePropertyComposite(newSection, SWT.NONE, toolkit);
		toolkit.adapt(this.composite);
		newSection.setClient(this.composite);

		// TODO Add DND support
		//		final int dndOperations = DND.DROP_MOVE;
		//		final Transfer[] transfers = new Transfer[] { LocalTransfer.getInstance() };
		//		final TableViewer valuesViewer = this.composite.getValuesViewer();
		//		valuesViewer.addDragSupport(dndOperations, transfers, new ViewerDragAdapter(valuesViewer));
		//		valuesViewer.addDropSupport(dndOperations, transfers, new EditingDomainViewerDropAdapter(getEditingDomain(), valuesViewer));

		return this.composite;
	}

	/**
	 * @param activeShell
	 */
	protected void handleAddValue(final Shell shell) {
		final InputDialog dialog = new InputDialog(shell, "New Value", "Value:", "", new InputValueValidator(this.input.getType(), this.input.isComplex()));

		if (dialog.open() == Window.OK) {
			Values values = this.input.getValues();
			if (values == null) {
				values = PrfFactory.eINSTANCE.createValues();
				values.getValue().add(dialog.getValue());
				execute(SetCommand.create(getEditingDomain(), this.input, PrfPackage.Literals.SIMPLE_SEQUENCE__VALUES, values));
				// Values
//				this.composite.getValuesViewer().setInput(this.input.getValues());
			} else {
				final Command command = AddCommand.create(getEditingDomain(), values, PrfPackage.Literals.VALUES__VALUE, dialog.getValue());
				execute(command);
			}

			if (!this.composite.getRemoveValueButton().isEnabled()) {
				this.composite.getRemoveValueButton().setEnabled(true);
			}
		}
	}

	/**
	 * 
	 */
	protected void handleRemoveValue() {
		if (this.input.getValues() != null) {
			Command command = null;
			if (this.input.getValues().getValue() != null && this.input.getValues().getValue().size() == 1) {
				command = SetCommand.create(getEditingDomain(), this.input, PrfPackage.Literals.SIMPLE_SEQUENCE__VALUES, null);

				if (this.composite.getRemoveValueButton().isEnabled()) {
					this.composite.getRemoveValueButton().setEnabled(false);
				}
			} else {
				command = RemoveCommand
				        .create(getEditingDomain(), this.input.getValues(), PrfPackage.Literals.VALUES__VALUE, this.composite.getLastSelection());
			}
			execute(command);
		}
	}

}
