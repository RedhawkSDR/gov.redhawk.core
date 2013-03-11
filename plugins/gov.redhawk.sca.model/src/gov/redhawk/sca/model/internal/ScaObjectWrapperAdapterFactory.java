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

import gov.redhawk.model.sca.IScaObjectIdentifierAdapter;
import gov.redhawk.model.sca.ProfileObjectWrapper;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * 
 */
public class ScaObjectWrapperAdapterFactory implements IAdapterFactory {
	private static final Class< ? >[] LIST = new Class[] { IScaObjectIdentifierAdapter.class };

	private static final IScaObjectIdentifierAdapter ADAPTER = new IScaObjectIdentifierAdapter() {

		public String getIdentifier(final Object obj) {
			final ProfileObjectWrapper< ? > wrapper = (ProfileObjectWrapper< ? >) obj;
			final EObject eObj = (EObject) wrapper.getProfileObj();
			return EcoreUtil.getID(eObj);
		}

		public EObject getScaEObject(final Object obj) {
			final ProfileObjectWrapper< ? > wrapper = (ProfileObjectWrapper< ? >) obj;
			final EObject eObj = (EObject) wrapper.getProfileObj();
			return eObj;
		}

	};

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	public Object getAdapter(final Object adaptableObject, final Class adapterType) {
		if (adaptableObject instanceof ProfileObjectWrapper< ? >) {
			final ProfileObjectWrapper< ? > wrapper = (ProfileObjectWrapper< ? >) adaptableObject;
			if (!(wrapper.getProfileObj() instanceof EObject)) {
				return null;
			}
			if (adapterType == IScaObjectIdentifierAdapter.class) {
				return ScaObjectWrapperAdapterFactory.ADAPTER;
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public Class< ? >[] getAdapterList() {
		return ScaObjectWrapperAdapterFactory.LIST;
	}

}
