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
/**
 * Copyright (c) 2009 Anyware Technologies and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Anyware Technologies - initial API and implementation
 *
 * $Id: EmfValidatorLabelDecorator.java,v 1.1 2009/07/18 13:22:34 bcabe Exp $
 */
package gov.redhawk.internal.ui.editor.validation;

import gov.redhawk.ui.RedhawkUiActivator;
import gov.redhawk.ui.editor.IEmfFormsImages;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.IWrapperItemProvider;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;

public class EmfValidatorLabelDecorator extends LabelProvider implements ILightweightLabelDecorator {

	/**
	 * @see org.eclipse.jface.viewers.ILightweightLabelDecorator#decorate(java.lang.Object,
	 *      org.eclipse.jface.viewers.IDecoration)
	 */
	public void decorate(final Object element, final IDecoration decoration) {
		if (!(element instanceof EObject || element instanceof FeatureMap.Entry || element instanceof IWrapperItemProvider)) {
			return;
		}

		EObject object = null;
		if (element instanceof EObject) {
			object = (EObject) element;
		} else {
			final Object unwrapped = AdapterFactoryEditingDomain.unwrap(element);
			if (unwrapped instanceof EObject) {
				object = (EObject) unwrapped;
			}
		}

		// Skip non local resources
		if (object == null || object.eResource() == null || !object.eResource().getURI().isPlatform()) {
			return;
		}
		final Diagnostic validate;
		final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(object);
		if (editingDomain != null) {
			Diagnostic tmp = null;
			final EObject diagObj = object;
			try {
				tmp = TransactionUtil.runExclusive(editingDomain, new RunnableWithResult.Impl<Diagnostic>() {

					public void run() {
						setResult(Diagnostician.INSTANCE.validate(diagObj));
					}

				});
			} catch (final InterruptedException e) {
				// PASS
			}
			validate = tmp;
		} else {
			validate = Diagnostician.INSTANCE.validate(object);

		}
		if (validate != null) {
			if (validate.getSeverity() == Diagnostic.ERROR) {
				decoration.addOverlay(getErrorImageDescriptor());
			} else if (validate.getSeverity() == Diagnostic.WARNING) {
				decoration.addOverlay(getWarningImageDescriptor());
			}
		}
	}

	/**
	 * The image descriptor of the error decoration
	 * 
	 * @return the ImageDescriptor of the error decoration
	 */
	protected ImageDescriptor getErrorImageDescriptor() {
		return RedhawkUiActivator.getImageDescriptor(IEmfFormsImages.ERROR_DECORATOR);
	}

	/**
	 * The image descriptor of the error decoration
	 * 
	 * @return the ImageDescriptor of the error decoration
	 */
	protected ImageDescriptor getWarningImageDescriptor() {
		return RedhawkUiActivator.getImageDescriptor(IEmfFormsImages.WARNING_DECORATOR);
	}

}
