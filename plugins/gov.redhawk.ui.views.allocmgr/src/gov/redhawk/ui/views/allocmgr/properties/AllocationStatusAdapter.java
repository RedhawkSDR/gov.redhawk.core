/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.ui.views.allocmgr.properties;

import org.eclipse.core.runtime.IAdapterFactory;

import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaPropertyContainer;
import gov.redhawk.ui.views.allocmgr.AllocationStatus;
import mil.jpeojtrs.sca.prf.AbstractProperty;
import mil.jpeojtrs.sca.util.AnyUtils;

/**
 * Adapts the properties of an {@link AllocationStatus} to an {@link ScaPropertyContainer} so it can be easily
 * displayed in the properties view by the existing property tab source.
 */
public class AllocationStatusAdapter implements IAdapterFactory {

	@Override
	public < T > T getAdapter(Object adaptableObject, Class<T> adapterType) {
		if (adaptableObject instanceof AllocationStatus) {
			AllocationStatus allocStatus = (AllocationStatus) adaptableObject;
			ScaComponent propContainer = ScaFactory.eINSTANCE.createScaComponent();
			for (CF.DataType dt : allocStatus.getAllocationProps()) {
				ScaAbstractProperty< ? extends AbstractProperty> prop;
				if (AnyUtils.isStruct(dt)) {
					prop = ScaFactory.eINSTANCE.createScaStructProperty();
				} else if (AnyUtils.isSimple(dt)) {
					prop = ScaFactory.eINSTANCE.createScaSimpleProperty();
				} else if (AnyUtils.isSequence(dt)) {
					prop = ScaFactory.eINSTANCE.createScaSimpleSequenceProperty();
				} else if (AnyUtils.isStructSequence(dt)) {
					prop = ScaFactory.eINSTANCE.createScaStructSequenceProperty();
				} else {
					continue;
				}
				prop.setId(dt.id);
				prop.fromAny(dt.value);
				propContainer.getProperties().add(prop);
			}
			return adapterType.cast(propContainer);
		}
		return null;
	}

	@Override
	public Class< ? >[] getAdapterList() {
		return new Class[] { ScaPropertyContainer.class };
	}

}
