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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.impl.InternalTransaction;
import org.eclipse.emf.transaction.impl.TransactionChangeRecorder;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.emf.transaction.internal.EMFTransactionPlugin;
import org.eclipse.emf.transaction.internal.EMFTransactionStatusCodes;
import org.eclipse.emf.transaction.internal.Tracing;
import org.eclipse.emf.transaction.internal.l10n.Messages;

/**
 * @since 14.0
 * 
 */
public class ScaTransactionalEditingDomain extends TransactionalEditingDomainImpl {

	public ScaTransactionalEditingDomain(AdapterFactory adapterFactory, ResourceSet resourceSet) {
		super(adapterFactory, resourceSet);
	}

	public ScaTransactionalEditingDomain(AdapterFactory adapterFactory, TransactionalCommandStack stack, ResourceSet resourceSet) {
		super(adapterFactory, stack, resourceSet);
	}

	public ScaTransactionalEditingDomain(AdapterFactory adapterFactory, TransactionalCommandStack stack) {
		super(adapterFactory, stack);
	}

	public ScaTransactionalEditingDomain(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	protected TransactionChangeRecorder createChangeRecorder(ResourceSet rset) {
		return new TransactionChangeRecorder(this, rset) {
			@Override
			protected void assertWriting() {
				InternalTransaction tx = getEditingDomain().getActiveTransaction();
				if ((tx == null) || tx.isReadOnly() || (tx.getOwner() != Thread.currentThread())) {
					if (tx != null) {
						tx.abort(new Status(IStatus.ERROR, EMFTransactionPlugin.getPluginId(), EMFTransactionStatusCodes.CONCURRENT_WRITE,
							Messages.concurrentWrite, null));
					}

					IllegalStateException ise = new IllegalStateException(Messages.noWriteTx);

					Tracing.throwing(TransactionChangeRecorder.class, "assertWriting", ise); //$NON-NLS-1$

					throw ise;
				}
			}
		};
	}
}
