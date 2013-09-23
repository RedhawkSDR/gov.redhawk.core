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
package gov.redhawk.model.sca.services;

import gov.redhawk.model.sca.CorbaObjWrapper;
import gov.redhawk.model.sca.Properties;
import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaFileStore;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

/**
 * @since 14.0
 * 
 */
public abstract class AbstractScaObjectLocator implements IScaObjectLocator {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public < T extends CorbaObjWrapper< ? >> T findEObject(Class<T> type, String ior) {
		if (ior == null || type == null) {
			return null;
		}
		final TreeIterator<EObject> iterator = getContentIterator(type, ior);
		while (iterator.hasNext()) {
			EObject obj = iterator.next();
			if (type.isInstance(obj)) {
				final T wrapper = type.cast(obj);
				if (ior.equals(wrapper.getIor())) {
					return wrapper;
				}
			}
			if (shouldPrune(obj)) {
				iterator.prune();
			}
		}
		return null;
	}
	
	protected boolean shouldPrune(EObject obj) {
		if (obj instanceof ScaAbstractProperty< ? > || obj instanceof ScaFileStore || obj instanceof Properties) {
			return true;
		}
		return false;
    }

	protected abstract TreeIterator<EObject> getContentIterator(Class<? extends CorbaObjWrapper<?>> type, String ior);

}
