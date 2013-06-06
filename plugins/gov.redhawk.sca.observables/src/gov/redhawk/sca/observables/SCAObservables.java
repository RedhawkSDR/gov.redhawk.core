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
package gov.redhawk.sca.observables;

import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.sca.ScaPlugin;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.edit.EditingDomainEObjectObservableValue;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.impl.EAttributeImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

public class SCAObservables {

	private static final TransactionalEditingDomain EDITING_DOMAIN = TransactionalEditingDomain.Registry.INSTANCE.getEditingDomain(ScaPlugin.EDITING_DOMAIN_ID);

	private SCAObservables() {
		//Prevent instantiation
	}

	public static IObservableValue observeSimpleProperty(final ScaComponent component, final String id) {
		if (component != null && !component.isDisposed() && id != null) {
			final ScaSimpleProperty simple = (ScaSimpleProperty) component.getProperty(id);
			return SCAObservables.observeSimpleProperty(simple);
		}

		return null;
	}

	public static IObservableValue observeSimpleProperty(final ScaSimpleProperty simple) {
		if (simple != null) {
			final EDataType type = simple.getDefinition().getType().toEDataType(simple.getDefinition().isComplex());

			final EAttributeImpl attribute = (EAttributeImpl) EcoreUtil.copy(ScaPackage.Literals.SCA_SIMPLE_PROPERTY__VALUE);
			attribute.setEType(type);

			return new EditingDomainEObjectObservableValue(SCAObservables.EDITING_DOMAIN, simple, ScaPackage.Literals.SCA_SIMPLE_PROPERTY__VALUE) {

				@Override
				public Object getValueType() {
					return attribute;
				}
			};
		}
		return null;
	}

}
