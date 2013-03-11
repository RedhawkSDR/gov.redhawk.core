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
package gov.redhawk.ui.parts;

import gov.redhawk.common.ui.parts.FormEntry;
import gov.redhawk.ui.editor.SCAFormEditor;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;

/**
 * 
 */
public final class FormEntryBindingFactory {
	private FormEntryBindingFactory() {

	}

	/**
	 * @since 6.0
	 */
	public static Binding bind(final DataBindingContext context, final FormEntry entry, final EditingDomain domain, final EStructuralFeature feature,
	        final EObject input, final UpdateValueStrategy targetToModel, final UpdateValueStrategy modelToTarget) {
		return context.bindValue(WidgetProperties.text(SWT.Modify).observeDelayed(SCAFormEditor.getFieldBindingDelay(), entry.getText()),
		        EMFEditObservables.observeValue(domain, input, feature),
		        targetToModel,
		        modelToTarget);

	}
}
