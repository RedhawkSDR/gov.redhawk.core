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
package gov.redhawk.sca.model.internal;

import gov.redhawk.model.sca.CorbaObjWrapper;
import gov.redhawk.model.sca.ICorbaObjectDescriptorAdapter;

import org.eclipse.core.runtime.IAdapterFactory;

public class CorbaObjWrapperAdapterFactory implements IAdapterFactory {
	private static final Class< ? >[] LIST = new Class[] { ICorbaObjectDescriptorAdapter.class };

	private static final ICorbaObjectDescriptorAdapter ADAPTER = new ICorbaObjectDescriptorAdapter() {

		public String getIOR(final Object contents) {
			final CorbaObjWrapper< ? > obj = (CorbaObjWrapper< ? >) contents;
			return obj.getIor();
		}

		public boolean supports(final Object contents, final String repId) {
			final CorbaObjWrapper< ? > obj = (CorbaObjWrapper< ? >) contents;
			return obj._is_a(repId);
		}

	};

	public Object getAdapter(final Object adaptableObject, @SuppressWarnings("rawtypes") final Class adapterType) {
		if (adaptableObject instanceof CorbaObjWrapper< ? >) {
			if (adapterType == ICorbaObjectDescriptorAdapter.class) {
				return CorbaObjWrapperAdapterFactory.ADAPTER;
			}
		}
		return null;
	}

	public Class< ? >[] getAdapterList() {
		return CorbaObjWrapperAdapterFactory.LIST;
	}

}
