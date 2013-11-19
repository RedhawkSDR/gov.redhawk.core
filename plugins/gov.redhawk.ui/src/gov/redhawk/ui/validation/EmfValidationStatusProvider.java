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
package gov.redhawk.ui.validation;

import gov.redhawk.ui.RedhawkUiActivator;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.ObservableTracker;
import org.eclipse.core.databinding.observable.Observables;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.ModelValidationService;

/**
 * 
 */
public class EmfValidationStatusProvider extends MultiValidator {

	private final Diagnostician diagnostician;
	private final EObject eObject;
	private final DataBindingContext dataBindingContext;
	private final AdapterFactory adapterFactory;
	private final IObservableValue validationStatus;
	private IObservableValue unmodifiableValidationStatus;

	/**
	 * 
	 */
	public EmfValidationStatusProvider(final EObject objectToValidate, final DataBindingContext context,
	        final AdapterFactory factory) {
		this.eObject = objectToValidate;
		this.validationStatus = new WritableValue(Realm.getDefault(), ValidationStatus.ok(), IStatus.class);
		this.eObject.eAdapters().add(new EContentAdapter() {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public void notifyChanged(final Notification notification) {
				super.notifyChanged(notification);
				ObservableTracker.runAndMonitor(new Runnable() {
					@Override
					public void run() {
						try {
							IStatus status = validate();
							if (status == null) {
								status = ValidationStatus.ok();
							}
							EmfValidationStatusProvider.this.validationStatus.setValue(status);
						} catch (final RuntimeException e) { // SUPPRESS CHECKSTYLE Logged Catch all exception
							// Usually an NPE as dependencies are
							// initialized
							EmfValidationStatusProvider.this.validationStatus.setValue(ValidationStatus.error(
							        e.getMessage(), e));
						}
					}
				}, null, null);
			}
		});
		this.dataBindingContext = context;
		this.adapterFactory = factory;

		// Subclass the default Diagnostician to customize the String
		// representation of each validated EObject
		this.diagnostician = new Diagnostician() {
			@Override
			public String getObjectLabel(final EObject eObject) {
				if (!eObject.eIsProxy()) {
					final IItemLabelProvider itemLabelProvider = (IItemLabelProvider) getAdapterFactory().adapt(
					        eObject, IItemLabelProvider.class);
					if (itemLabelProvider != null) {
						return itemLabelProvider.getText(eObject);
					}
				}

				return super.getObjectLabel(eObject);
			}

			@Override
			protected boolean doValidateContents(final EObject eObject, final DiagnosticChain diagnostics,
			        final Map<Object, Object> context) {

				final Resource eContainerResource = eObject.eResource();
				final List<EObject> eContents = eObject.eContents();
				if (!eContents.isEmpty()) {
					boolean result = true;
					for (final Iterator<EObject> i = eContents.iterator(); i.hasNext()
					        && (result || diagnostics != null);) {
						final EObject child = i.next();
						// in case of cross resource containment,
						// avoid to validate a child which are not in the
						// container resource
						final Resource eChildResource = child.eResource();
						if (eContainerResource != null && eChildResource == eContainerResource) {
							result &= validate(child, diagnostics, context);
						}
					}
					return result;
				}
				return true;
			}
		};
	}

	/**
	 * @return
	 */
	protected AdapterFactory getAdapterFactory() {
		return this.adapterFactory;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected IStatus validate() {
		final IBatchValidator batchValidator = ModelValidationService.getInstance().newValidator(EvaluationMode.BATCH);
		batchValidator.setIncludeLiveConstraints(true);
		batchValidator.setReportSuccesses(false);
		IStatus status;
		final Diagnostic diag = this.diagnostician.validate(this.eObject);
		status = analyzeDiagnostic(this.dataBindingContext, diag);

		if (status.isOK()) {
			status = batchValidator.validate(this.eObject);
		}

		return status;
	}

	private IStatus analyzeDiagnostic(final DataBindingContext dbc, final Diagnostic diagnostic) {
		String message = "";
		if (!diagnostic.getChildren().isEmpty()) {
			message = diagnostic.getChildren().get(0).getMessage();
		}
		return new Status(diagnostic.getSeverity(), RedhawkUiActivator.getPluginId(), message, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IObservableValue getValidationStatus() {
		if (this.unmodifiableValidationStatus == null) {
			myRevalidate();
			this.unmodifiableValidationStatus = Observables.unmodifiableObservableValue(this.validationStatus);
		}
		return this.unmodifiableValidationStatus;
	}

	private void myRevalidate() {
		ObservableTracker.runAndMonitor(new Runnable() {
			@Override
			public void run() {
				try {
					IStatus status = validate();
					if (status == null) {
						status = ValidationStatus.ok();
					}
					EmfValidationStatusProvider.this.validationStatus.setValue(status);
				} catch (final RuntimeException e) { // SUPPRESS CHECKSTYLE Logged Catch all exception
					// Usually an NPE as dependencies are initialized
					EmfValidationStatusProvider.this.validationStatus.setValue(ValidationStatus.error(e.getMessage(), e));
				}
			}
		}, null, null);
	}

}
