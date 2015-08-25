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
package gov.redhawk.internal.ui.editor.validation;

import gov.redhawk.ui.validation.ValidatingService;

import java.util.List;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.databinding.EObjectObservableValue;
import org.eclipse.emf.databinding.IEMFObservable;
import org.eclipse.jface.databinding.swt.ISWTObservable;
import org.eclipse.jface.databinding.viewers.IViewerObservable;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.forms.IMessageManager;

public class ValidatingService34 implements ValidatingService {

	@Override
	public void analyzeDiagnostic(final DataBindingContext dataBindingContext, final Diagnostic diagnostic, final IMessageManager messageManager) {
		IObservableList bindingList = dataBindingContext.getBindings();
		bindingList.getRealm().exec(new Runnable() {

			@Override
			public void run() {
				boolean atLeastOneErroneousBinding = false;
				for (final Object o : dataBindingContext.getBindings()) {
					final Binding binding = (Binding) o;
					Object observed = null;
					Object valueType = null;
					final IObservable model = binding.getModel();
					final IObservable target = binding.getTarget();

					IObservable viewObservable = null;
					if (target instanceof ISWTObservable || target instanceof IViewerObservable) {
						viewObservable = target;
					} else if (model instanceof ISWTObservable || model instanceof IViewerObservable) {
						viewObservable = model;
					}
					Control control = getBoundControl(viewObservable);

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

					if (observed != null && valueType != null && control != null) {
						if (checkBindingFor34(observed, valueType, viewObservable, control, diagnostic, messageManager)) {
							atLeastOneErroneousBinding = true;
						}
					}
				}
				if (!atLeastOneErroneousBinding) {
					// add an error message anyways
					messageManager.addMessage(diagnostic, diagnostic.getMessage(), null, ValidatingService.KEY_MAP.getMessageProviderKey(diagnostic.getSeverity()));
				}
			}
			
		});
		
	}

	private Control getBoundControl(IObservable observable) {
		if (observable instanceof ISWTObservable) {
			Widget widget = ((ISWTObservable) observable).getWidget();
			if (widget instanceof Control && !(widget instanceof Button)) {
				return (Control) widget;
			}
		} else if (observable instanceof IViewerObservable) {
			return ((IViewerObservable) observable).getViewer().getControl();
		}
		return null;
	}

	private boolean checkBindingFor34(Object observed, Object valueType, IObservable observable, final Control control, final Diagnostic diagnostic,
	        final IMessageManager messageManager) {
		final List< ? > diagnosticData = diagnostic.getData();
		if (diagnosticData.size() >= 2) {
			if (diagnosticData.get(0) == observed) {
				if (diagnosticData.get(1) == valueType) {
					messageManager.addMessage(observable, diagnostic.getMessage(), null,
						ValidatingService.KEY_MAP.getMessageProviderKey(diagnostic.getSeverity()), control);
					return true;
				}
			}
		}
		return false;
	}
}
