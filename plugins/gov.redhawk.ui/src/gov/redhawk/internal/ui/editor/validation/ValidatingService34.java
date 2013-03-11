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
package gov.redhawk.internal.ui.editor.validation;

import gov.redhawk.ui.validation.ValidatingService;

import java.util.List;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.databinding.EObjectObservableValue;
import org.eclipse.emf.databinding.IEMFObservable;
import org.eclipse.jface.databinding.swt.ISWTObservable;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.forms.IMessageManager;

public class ValidatingService34 implements ValidatingService {

	public void analyzeDiagnostic(final DataBindingContext dataBindingContext, final Diagnostic diagnostic, final IMessageManager messageManager) {
		boolean atLeastOneErroneousBinding = false;
		for (final Object o : dataBindingContext.getBindings()) {
			final Binding binding = (Binding) o;
			Object observed = null;
			Object valueType = null;
			ISWTObservable swtObservable = null;
			final IObservable model = binding.getModel();
			final IObservable target = binding.getTarget();

			if (model instanceof ISWTObservable) {
				swtObservable = (ISWTObservable) model;
			} else if (target instanceof ISWTObservable) {
				swtObservable = (ISWTObservable) target;
			}

			if (model instanceof IEMFObservable) {
				IEMFObservable observable = (IEMFObservable) model;
				observed = observable.getObserved();
				valueType = observable.getStructuralFeature();
			} else if (target instanceof IEMFObservable) {
				IEMFObservable observable = (IEMFObservable) target;
				observed = observable.getObserved();
				valueType = observable.getStructuralFeature();
			} else if (model instanceof EObjectObservableValue) {
				EObjectObservableValue emfObservable = (EObjectObservableValue) model;
				observed = emfObservable.getObserved();
				valueType = emfObservable.getValueType();
			} else if (target instanceof EObjectObservableValue) {
				EObjectObservableValue emfObservable = (EObjectObservableValue) target;
				observed = emfObservable.getObserved();
				valueType = emfObservable.getValueType();
			}

			if (observed != null && valueType != null && swtObservable != null) {
				if (checkBindingFor34(observed, valueType, swtObservable, diagnostic, messageManager)) {
					atLeastOneErroneousBinding = true;
				}
			}
		}
		if (!atLeastOneErroneousBinding) {
			// add an error message anyways
			messageManager.addMessage(diagnostic, diagnostic.getMessage(), null, ValidatingService.KEY_MAP.getMessageProviderKey(diagnostic.getSeverity()));
		}
	}

	private boolean checkBindingFor34(Object observed, Object valueType, final ISWTObservable swtObservable, final Diagnostic diagnostic,
	        final IMessageManager messageManager) {
		final List< ? > diagnosticData = diagnostic.getData();
		if (diagnosticData.size() >= 2) {
			if (diagnosticData.get(0) == observed) {
				if (diagnosticData.get(1) == valueType) {
					Widget widget = swtObservable.getWidget();
					if (widget instanceof Control && !(widget instanceof Button)) {
						final Control control = (Control) widget;
						messageManager.addMessage(swtObservable, diagnostic.getMessage(), null,
						        ValidatingService.KEY_MAP.getMessageProviderKey(diagnostic.getSeverity()), control);
						return true;
					}
				}
			}
		}
		return false;
	}
}
