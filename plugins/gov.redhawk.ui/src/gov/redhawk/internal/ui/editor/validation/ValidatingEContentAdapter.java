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
/**
 * Copyright (c) 2009, 2010 Anyware Technologies and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Anyware Technologies - initial API and implementation
 *     Sebastien Moran <smoran@sierrawireless.com> - bug 292926
 *
 * $Id: ValidatingEContentAdapter.java,v 1.14 2010/02/10 10:54:50 bcabe Exp $
 */
package gov.redhawk.internal.ui.editor.validation;

import gov.redhawk.ui.RedhawkUiActivator;
import gov.redhawk.ui.editor.FormOutlinePage;
import gov.redhawk.ui.editor.SCAFormEditor;
import gov.redhawk.ui.validation.ValidatingService;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.ui.forms.IMessageManager;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.progress.WorkbenchJob;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

public class ValidatingEContentAdapter extends EContentAdapter {
	private final SCAFormEditor formEditor;
	private final DataBindingContext dataBindingContext;
	private final Diagnostician diagnostician;

	private final ValidatingService validatingService = new ValidatingService34();
	private final Notifier root;

	// Ensure that the update is performed within the UI Thread. See Bug
	// #292926
	private final WorkbenchJob updateManagerJob = new WorkbenchJob("Update Message Manager") {

		{
			setPriority(Job.INTERACTIVE);
			setUser(false);
			setSystem(true);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean shouldRun() {
			return super.shouldRun() && !ValidatingEContentAdapter.this.formEditor.isDisposed();
		}

		@Override
		public IStatus runInUIThread(final IProgressMonitor monitor) {
			final IFormPage activePageInstance = ValidatingEContentAdapter.this.formEditor.getActivePageInstance();
			if (activePageInstance == null || activePageInstance.getManagedForm() == null) {
				return Status.OK_STATUS;
			}

			final IMessageManager messageManager = activePageInstance.getManagedForm().getMessageManager();
			if (!ValidatingEContentAdapter.this.formEditor.isDisposed()) {
				messageManager.update();
				//Force a viewer refresh so all labels get updated properly
				if (ValidatingEContentAdapter.this.formEditor.getViewer() != null) {
					ValidatingEContentAdapter.this.formEditor.getViewer().refresh();
				}
				IContentOutlinePage outline = ValidatingEContentAdapter.this.formEditor.getFormOutline();
				if (outline != null && outline instanceof FormOutlinePage) {
					((FormOutlinePage) outline).refresh();
				}
			}
			return Status.OK_STATUS;
		}

	};

	public ValidatingEContentAdapter(final SCAFormEditor formEditor, final Notifier root) {
		this.formEditor = formEditor;
		this.dataBindingContext = formEditor.getDataBindingContext();
		this.root = root;

		// Subclass the default Diagnostician to customize the String
		// representation of each validated EObject
		this.diagnostician = new Diagnostician() {
			@Override
			public String getObjectLabel(final EObject eObject) {
				if (!eObject.eIsProxy()) {
					final IItemLabelProvider itemLabelProvider = (IItemLabelProvider) ValidatingEContentAdapter.this.formEditor.getAdapterFactory().adapt(
					        eObject, IItemLabelProvider.class);
					if (itemLabelProvider != null) {
						return itemLabelProvider.getText(eObject);
					}
				}

				return super.getObjectLabel(eObject);
			}

			@Override
			protected boolean doValidateContents(final EObject eObject, final DiagnosticChain diagnostics, final Map<Object, Object> context) {

				final Resource eContainerResource = eObject.eResource();
				final List<EObject> eContents = eObject.eContents();
				if (!eContents.isEmpty()) {
					boolean result = true;
					for (final Iterator<EObject> i = eContents.iterator(); i.hasNext() && (result || diagnostics != null);) {
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

	@Override
	public void notifyChanged(final Notification notification) {
		super.notifyChanged(notification);
		if (!NotificationFilter.READ.matches(notification)) {
			validate();
		}

	}

	public void validate() {
		final IFormPage activePageInstance = this.formEditor.getActivePageInstance();
		if (activePageInstance == null || activePageInstance.getManagedForm() == null) {
			return;
		}

		final IMessageManager messageManager = activePageInstance.getManagedForm().getMessageManager();
		messageManager.removeAllMessages();
		messageManager.setAutoUpdate(false);

		final BasicDiagnostic diagnostics = new BasicDiagnostic();
		if (this.root instanceof ResourceSet) {
			validateResourceSet((ResourceSet) this.root, messageManager, diagnostics);
		} else if (this.root instanceof Resource) {
			validateResource((Resource) this.root, messageManager, diagnostics);
		} else if (this.root instanceof EObject) {
			validatorEObject((EObject) this.root, messageManager, diagnostics);
		}

		for (final Diagnostic diagnostic : diagnostics.getChildren()) {
			getValidatorService().analyzeDiagnostic(this.dataBindingContext, diagnostic, messageManager);
		}

		this.updateManagerJob.schedule();
	}

	/**
	 * @param root2
	 */
	private void validatorEObject(final EObject obj, final IMessageManager messageManager, final DiagnosticChain diagnostic) {
		validate(obj, diagnostic);
	}

	/**
	 * @param root2
	 */
	private void validateResource(final Resource resource, final IMessageManager messageManager, final DiagnosticChain diagnostic) {
		if (this.formEditor.isPersisted(resource)) {
			for (final EObject obj : resource.getContents()) {
				if (obj != null) {
					validatorEObject(obj, messageManager, diagnostic);
				}
			}
		}
	}

	/**
	 * @param root2
	 * @param messageManager
	 */
	private void validateResourceSet(final ResourceSet resourceSet, final IMessageManager messageManager, final DiagnosticChain diagnosticChain) {
		for (final Object o : resourceSet.getResources().toArray()) {
			final Resource resource = (Resource) o;
			validateResource(resource, messageManager, diagnosticChain);
		}
	}

	public void validate(final EObject obj, final DiagnosticChain diagnostic) {
		EditingDomain editingDomain = formEditor.getEditingDomain();
		if (editingDomain instanceof TransactionalEditingDomain) {
			TransactionalEditingDomain ted = (TransactionalEditingDomain) editingDomain;
			try {
	            ted.runExclusive(new Runnable() {

					@Override
					public void run() {
						SafeRunnable.run(new ISafeRunnable() {
							
							@Override
							public void run() throws Exception {
								diagnostician.validate(obj, diagnostic);	
							}
							
							@Override
							public void handleException(Throwable exception) {
								diagnostic.add(BasicDiagnostic.toDiagnostic(new Status(Status.ERROR, RedhawkUiActivator.getPluginId(), "FATAL Diagnostic exception on: " + obj, exception)));
							}
						});
                    }
	            	
	            });
            } catch (InterruptedException e) {
	            // PASS
            }
		} else {
			SafeRunnable.run(new ISafeRunnable() {
				
				@Override
				public void run() throws Exception {
					diagnostician.validate(obj, diagnostic);	
				}
				
				@Override
				public void handleException(Throwable exception) {
					diagnostic.add(BasicDiagnostic.toDiagnostic(new Status(Status.ERROR, RedhawkUiActivator.getPluginId(), "FATAL Diagnostic exception on: " + obj, exception)));
				}
			});
		}
	}

	private ValidatingService getValidatorService() {
		return this.validatingService;
	}

}
