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
package gov.redhawk.model.sca.util;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;

/**
 * @since 14.0
 */
public class ScaTransactionEditingDomainFactory extends TransactionalEditingDomainImpl.FactoryImpl {
	public static final ScaTransactionEditingDomainFactory INSTANCE = new ScaTransactionEditingDomainFactory();

	// Documentation copied from the inherited specification
	@Override
	public synchronized TransactionalEditingDomain createEditingDomain() {
		TransactionalEditingDomain retVal = new ScaTransactionalEditingDomain(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE), new ScaTransactionalCommandStack());

		mapResourceSet(retVal);
		setupEditingDomain((TransactionalEditingDomainImpl) retVal);
		return retVal;
	}

	private static void setupEditingDomain(TransactionalEditingDomainImpl domain) {
		final Map<Object, Object> myOptions = new HashMap<Object, Object>(domain.getDefaultTransactionOptions());
		myOptions.put(Transaction.OPTION_NO_VALIDATION, Boolean.TRUE);
		myOptions.put(Transaction.OPTION_NO_UNDO, Boolean.TRUE);
		((TransactionalEditingDomainImpl) domain).setDefaultTransactionOptions(myOptions);
	}

	// Documentation copied from the inherited specification
	@Override
	public synchronized TransactionalEditingDomain createEditingDomain(ResourceSet rset) {
		TransactionalEditingDomain result = new ScaTransactionalEditingDomain(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));

		mapResourceSet(result);
		setupEditingDomain((TransactionalEditingDomainImpl) result);
		return result;
	}
}
