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
import gov.redhawk.prf.internal.ui.editor.composite.StructSequencePropertyComposite;
import gov.redhawk.prf.internal.ui.handlers.PropertyHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.Properties;
import mil.jpeojtrs.sca.prf.SimpleRef;
import mil.jpeojtrs.sca.prf.StructSequence;
import mil.jpeojtrs.sca.prf.StructValue;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.emf.databinding.IEMFListProperty;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
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
public class StructSequencePropertyDetailsPage extends BasicStructPropertyDetailsPage {

	private StructSequencePropertyComposite structSequenceComposite;
	private StructSequence sequence;

	/**
	 * Instantiates a new simple property details page.
	 * 
	 * @param section the section
	 */
	public StructSequencePropertyDetailsPage(final PropertiesSection section) {
		super(section);
	}

	/**
	 * @param parent
	 * @param toolkit
	 */
	@Override
	protected BasicStructPropertyComposite createSection(final Composite parent, final FormToolkit toolkit) {
		final Section newSection = toolkit.createSection(parent,  Section.EXPANDED | ExpandableComposite.TITLE_BAR);
		newSection.clientVerticalSpacing = FormLayoutFactory.SECTION_HEADER_VERTICAL_SPACING;
		newSection.setText("Struct Sequence Property");
		newSection.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

		// Align the master and details section headers (misalignment caused
		// by section toolbar icons)
		getPage().alignSectionHeaders(getSection().getSection(), newSection);

		this.structSequenceComposite = new StructSequencePropertyComposite(newSection, SWT.NONE, toolkit);
		toolkit.adapt(this.structSequenceComposite);
		newSection.setClient(this.structSequenceComposite);
		return this.structSequenceComposite;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void addListeners() {
		super.addListeners();

		/**
		 * Add a new StructValue.
		 */
		this.structSequenceComposite.getAddButton().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				PropertyHandler.addStructValue(getAdapterFactory(), getEditingDomain(), StructSequencePropertyDetailsPage.this.sequence);
			}
		});

		/**
		 * Remove the selected StructValues from the sequence.
		 */
		this.structSequenceComposite.getRemoveButton().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				final List<Object> removeStructVals = new ArrayList<Object>();
				for (final Object obj : getSelection()) {
					if (obj instanceof StructValue) {
						removeStructVals.add(obj);
					}
				}
				PropertyHandler.removeProperty(getAdapterFactory(), getEditingDomain(),
				        (Properties) StructSequencePropertyDetailsPage.this.sequence.eContainer(), removeStructVals);
				StructSequencePropertyDetailsPage.this.structSequenceComposite.getStructValueViewer().refresh();
			}
		});

		/**
		 * Enable remove button if a StructValue is selected.  Update the page selection.
		 */
		this.structSequenceComposite.getStructValueViewer().addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 */
			public void selectionChanged(final SelectionChangedEvent event) {
				final List<Object> selection = getSelection();
				boolean remove = !selection.isEmpty();
				if (remove) {
					for (final Object obj : getSelection()) {
						if (obj instanceof SimpleRef) {
							remove = false;
							break;
						}
					}
				}
				StructSequencePropertyDetailsPage.this.structSequenceComposite.getRemoveButton().setEnabled(remove);
				getPage().setSelection(event.getSelection());
			}
		});
	}

	/**
	 * Gets the current selection from the viewer.
	 * 
	 * @return the list of selected objects
	 */
	private List<Object> getSelection() {
		return Arrays.asList(((IStructuredSelection) this.structSequenceComposite.getStructValueViewer().getSelection()).toArray());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<Binding> bind(final DataBindingContext context, final EObject input) {
		final List<Binding> bindings = super.bind(context, input);
		this.sequence = (StructSequence) input;

		this.structSequenceComposite.getStructValueViewer().setInput(this.sequence);
		final IObservableValue target = SWTObservables.observeEnabled(this.structSequenceComposite.getAddButton());

		final IEMFListProperty property = EMFProperties.list(FeaturePath.fromList(PrfPackage.Literals.STRUCT_SEQUENCE__STRUCT,
		        PrfPackage.Literals.STRUCT__SIMPLE));
		final IObservableList list = property.observe(this.sequence);

		final IObservableValue value = new ComputedValue() {
			@Override
			protected Object calculate() {
				return list.isEmpty();
			}
		};
		//Only enable the button if the list isn't empty
		bindings.add(context.bindValue(target, value, null, new UpdateValueStrategy() {
			@Override
			public Object convert(final Object value) {
				return !((Boolean) value);

			}
		}));
		return bindings;
	}
}
