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

import java.math.BigInteger;

import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.util.math.ComplexBoolean;
import mil.jpeojtrs.sca.util.math.ComplexByte;
import mil.jpeojtrs.sca.util.math.ComplexDouble;
import mil.jpeojtrs.sca.util.math.ComplexFloat;
import mil.jpeojtrs.sca.util.math.ComplexLong;
import mil.jpeojtrs.sca.util.math.ComplexLongLong;
import mil.jpeojtrs.sca.util.math.ComplexShort;
import mil.jpeojtrs.sca.util.math.ComplexUByte;
import mil.jpeojtrs.sca.util.math.ComplexULong;
import mil.jpeojtrs.sca.util.math.ComplexULongLong;
import mil.jpeojtrs.sca.util.math.ComplexUShort;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EObjectObservableValue;
import org.eclipse.emf.databinding.edit.EditingDomainEObjectObservableValue;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;

public class SCAObservables {

	private SCAObservables() {
		//Prevent instantiation
	}
	
	public static IObservableValue< ? > observeSimpleProperty(final ScaComponent component, final String id) {
		TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(component);
		return observeSimpleProperty(domain, component, id);
		
	}
	
	/**
	 * @since 1.2
	 */
	public static IObservableValue< ? > observeSimpleProperty(EditingDomain domain, final ScaComponent component, final String id) {
		if (component != null && !component.isDisposed() && id != null) {
			final ScaSimpleProperty simple = (ScaSimpleProperty) component.getProperty(id);
			return SCAObservables.observeSimpleProperty(simple);
		}

		return null;
	}
	
	/**
	 * @since 1.2
	 */
	public static IObservableValue< ? > observeSimpleProperty(EditingDomain domain, final ScaSimpleProperty simple) {
		if (simple != null) {
			final EDataType type;

			if (simple.getDefinition() == null) {
				type = SCAObservables.toType(simple.getValue());
			} else {
				type = simple.getDefinition().getType().toEDataType(simple.getDefinition().isComplex());
			}
			final EAttribute attribute;
			if (type != null) {
				attribute = EcoreUtil.copy(ScaPackage.Literals.SCA_SIMPLE_PROPERTY__VALUE);
				attribute.setEType(type);
			} else {
				attribute = ScaPackage.Literals.SCA_SIMPLE_PROPERTY__VALUE;
			}
			if (domain != null) {
			return new EditingDomainEObjectObservableValue(domain, simple, ScaPackage.Literals.SCA_SIMPLE_PROPERTY__VALUE) {

				@Override
				public Object getValueType() {
					return attribute;
				}
			};
			} else {
				return new EObjectObservableValue(simple, ScaPackage.Literals.SCA_SIMPLE_PROPERTY__VALUE) {

					@Override
					public Object getValueType() {
						return attribute;
					}
				};	
			}
		}
		return null;
	}

	public static IObservableValue< ? > observeSimpleProperty(final ScaSimpleProperty simple) {
		TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(simple);
		return observeSimpleProperty(domain, simple);
	}

	/**
	 * @since 1.2
	 */
	public static EDataType toType(Object obj) {
		EDataType type;
		if (obj instanceof Integer) {
			type = EcorePackage.Literals.EINTEGER_OBJECT;
		} else if (obj instanceof Boolean) {
			type = EcorePackage.Literals.EBOOLEAN_OBJECT;
		} else if (obj instanceof BigInteger) {
			type = EcorePackage.Literals.EBIG_INTEGER;
		} else if (obj instanceof Character) {
			type = EcorePackage.Literals.ECHARACTER_OBJECT;
		} else if (obj instanceof Short) {
			type = EcorePackage.Literals.ESHORT_OBJECT;
		} else if (obj instanceof Long) {
			type = EcorePackage.Literals.ELONG_OBJECT;
		} else if (obj instanceof Float) {
			type = EcorePackage.Literals.EFLOAT_OBJECT;
		} else if (obj instanceof Double) {
			type = EcorePackage.Literals.EDOUBLE_OBJECT;
		} else if (obj instanceof String) {
			type = EcorePackage.Literals.ESTRING;
		} else if (obj instanceof Byte) {
			type = EcorePackage.Literals.EBYTE;
		} else if (obj instanceof ComplexBoolean) {
			type = PrfPackage.Literals.COMPLEX_BOOLEAN;
		} else if (obj instanceof ComplexByte) {
			type = PrfPackage.Literals.COMPLEX_BYTE;
		} else if (obj instanceof ComplexDouble) {
			type = PrfPackage.Literals.COMPLEX_DOUBLE;
		} else if (obj instanceof ComplexFloat) {
			type = PrfPackage.Literals.COMPLEX_FLOAT;
		} else if (obj instanceof ComplexLong) {
			type = PrfPackage.Literals.COMPLEX_LONG;
		} else if (obj instanceof ComplexLongLong) {
			type = PrfPackage.Literals.COMPLEX_LONG_LONG;
		} else if (obj instanceof ComplexUByte) {
			type = PrfPackage.Literals.COMPLEX_UBYTE;
		} else if (obj instanceof ComplexShort) {
			type = PrfPackage.Literals.COMPLEX_SHORT;
		} else if (obj instanceof ComplexULong) {
			type = PrfPackage.Literals.COMPLEX_ULONG;
		} else if (obj instanceof ComplexULongLong) {
			type = PrfPackage.Literals.COMPLEX_ULONG_LONG;
		} else if (obj instanceof ComplexUShort) {
			type = PrfPackage.Literals.COMPLEX_USHORT;
		} else {
			type = null;
		}
		return type;
	}
}
