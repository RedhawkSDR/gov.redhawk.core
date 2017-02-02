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
package gov.redhawk.ui.parts;

import gov.redhawk.common.ui.parts.FormEntry;
import gov.redhawk.ui.editor.SCAFormEditor;
import gov.redhawk.ui.util.EMFEmptyStringToNullUpdateValueStrategy;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;

/**
 * The Class EMFFormEntryAdapter.
 */
public class FormEntryBinder {
	private final EditingDomain domain;
	private final EStructuralFeature feature;
	private final FormEntry entry;
	private final IObservableValue< ? > input;
	private Binding binding;
	private final DataBindingContext context;

	/**
	 * Instantiates a new eMF form entry adapter.
	 * 
	 * @param domain the domain
	 * @param feature the feature
	 * @param entry the entry
	 * @param inputProvider the input provider
	 * @param context the context
	 * @param form the form
	 * @since 6.0
	 */
	public FormEntryBinder(final EditingDomain domain, final EStructuralFeature feature, final FormEntry entry, final IObservableValue< ? > input,
	        final DataBindingContext context) {
		this.domain = domain;
		this.feature = feature;
		this.entry = entry;
		this.input = input;
		this.context = context;
	}

	/**
	 * Rebind.
	 */
	public void rebind() {
		if (this.binding != null) {
			this.binding.dispose();
			this.binding = null;
		}

		final EObject eobj = (EObject) this.input.getValue();
		if (eobj != null) {
			this.binding = this.context.bindValue(WidgetProperties.text(SWT.Modify).observeDelayed(SCAFormEditor.getFieldBindingDelay(), this.entry.getText()),
			        EMFEditObservables.observeValue(this.domain, eobj, this.feature),
			        createTargetToModelStrategy(),
			        createModelToTargetStrategy());
		}
	}

	/**
	 * Gets the target to model strategy.
	 * 
	 * @return the target to model strategy
	 */
	protected UpdateValueStrategy createTargetToModelStrategy() {
		return new EMFEmptyStringToNullUpdateValueStrategy();
	}

	/**
	 * Gets the model to target strategy.
	 * 
	 * @return the model to target strategy
	 */
	protected UpdateValueStrategy createModelToTargetStrategy() {
		return null;
	}

}
