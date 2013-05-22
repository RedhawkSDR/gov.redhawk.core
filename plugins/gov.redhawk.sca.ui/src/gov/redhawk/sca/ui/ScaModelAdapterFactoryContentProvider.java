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
package gov.redhawk.sca.ui;

import gov.redhawk.sca.ScaPlugin;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.ui.provider.TransactionalAdapterFactoryContentProvider;

/**
 * @since 7.0
 *
 *
 */
public class ScaModelAdapterFactoryContentProvider extends TransactionalAdapterFactoryContentProvider {

	/**
	 * @since 7.0
	 */
	public ScaModelAdapterFactoryContentProvider(final AdapterFactory adapterFactory) {
		super(TransactionalEditingDomain.Registry.INSTANCE.getEditingDomain(ScaPlugin.EDITING_DOMAIN_ID), adapterFactory);
	}
}
