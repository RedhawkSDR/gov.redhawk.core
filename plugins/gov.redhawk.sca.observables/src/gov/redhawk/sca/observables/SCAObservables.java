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
import mil.jpeojtrs.sca.util.math.ComplexOctet;
import mil.jpeojtrs.sca.util.math.ComplexShort;
import mil.jpeojtrs.sca.util.math.ComplexUByte;
import mil.jpeojtrs.sca.util.math.ComplexULong;
import mil.jpeojtrs.sca.util.math.ComplexULongLong;
import mil.jpeojtrs.sca.util.math.ComplexUShort;
import mil.jpeojtrs.sca.util.time.UTCTime;

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
	@SuppressWarnings("deprecation")
	public static EDataType toType(Object obj) {
		if (obj instanceof Integer) {
			return EcorePackage.Literals.EINTEGER_OBJECT;
		} else if (obj instanceof Boolean) {
			return EcorePackage.Literals.EBOOLEAN_OBJECT;
		} else if (obj instanceof BigInteger) {
			return EcorePackage.Literals.EBIG_INTEGER;
		} else if (obj instanceof Character) {
			return EcorePackage.Literals.ECHARACTER_OBJECT;
		} else if (obj instanceof Short) {
			return EcorePackage.Literals.ESHORT_OBJECT;
		} else if (obj instanceof Long) {
			return EcorePackage.Literals.ELONG_OBJECT;
		} else if (obj instanceof Float) {
			return EcorePackage.Literals.EFLOAT_OBJECT;
		} else if (obj instanceof Double) {
			return EcorePackage.Literals.EDOUBLE_OBJECT;
		} else if (obj instanceof String) {
			return EcorePackage.Literals.ESTRING;
		} else if (obj instanceof Byte) {
			return EcorePackage.Literals.EBYTE;
		} else if (obj instanceof ComplexBoolean) {
			return PrfPackage.Literals.COMPLEX_BOOLEAN;
		} else if (obj instanceof ComplexByte) {
			return PrfPackage.Literals.COMPLEX_BYTE;
		} else if (obj instanceof ComplexDouble) {
			return PrfPackage.Literals.COMPLEX_DOUBLE;
		} else if (obj instanceof ComplexFloat) {
			return PrfPackage.Literals.COMPLEX_FLOAT;
		} else if (obj instanceof ComplexLong) {
			return PrfPackage.Literals.COMPLEX_LONG;
		} else if (obj instanceof ComplexLongLong) {
			return PrfPackage.Literals.COMPLEX_LONG_LONG;
		} else if (obj instanceof ComplexOctet) {
			return PrfPackage.Literals.COMPLEX_OCTET;
		} else if (obj instanceof ComplexUByte) {
			return PrfPackage.Literals.COMPLEX_UBYTE;
		} else if (obj instanceof ComplexShort) {
			return PrfPackage.Literals.COMPLEX_SHORT;
		} else if (obj instanceof ComplexULong) {
			return PrfPackage.Literals.COMPLEX_ULONG;
		} else if (obj instanceof ComplexULongLong) {
			return PrfPackage.Literals.COMPLEX_ULONG_LONG;
		} else if (obj instanceof ComplexUShort) {
			return PrfPackage.Literals.COMPLEX_USHORT;
		} else if (obj instanceof UTCTime) {
			return PrfPackage.Literals.UTC_TIME;
		} else {
			return null;
		}
	}
}
