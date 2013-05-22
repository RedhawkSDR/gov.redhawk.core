/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.sca.observables.strategy;

import gov.redhawk.model.sca.ScaSimpleProperty;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.StringToNumberConverter;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EcorePackage;

public class ScaNumberUpdateValueStrategy extends UpdateValueStrategy {
	private static class NumberValidator implements IValidator {
		private final EDataType dataType;

		public NumberValidator(EDataType dataType) {
			this.dataType = dataType;
		}

		public IStatus validate(Object value) {
			String s = String.valueOf(value);
			try {
				switch (dataType.getClassifierID()) {
				case EcorePackage.EDOUBLE:
				case EcorePackage.EDOUBLE_OBJECT:
					Double.parseDouble(s);
					break;
				case EcorePackage.EFLOAT:
				case EcorePackage.EFLOAT_OBJECT:
					Float.parseFloat(s);
					break;
				case EcorePackage.EINTEGER_OBJECT:
				case EcorePackage.EINT:
					Integer.parseInt(s);
					break;
				case EcorePackage.ELONG:
				case EcorePackage.ELONG_OBJECT:
					Long.parseLong(s);
					break;
				case EcorePackage.ESHORT:
				case EcorePackage.ESHORT_OBJECT:
					Short.parseShort(s);
					break;
				case EcorePackage.EBYTE:
				case EcorePackage.EBYTE_OBJECT:
					Byte.parseByte(s);
					break;
				case EcorePackage.EBIG_INTEGER:
					new BigInteger(s);
					break;
				case EcorePackage.EBIG_DECIMAL:
					new BigDecimal(s);
					break;
				default:
					throw new IllegalArgumentException("Unknown number format: " + dataType.getInstanceClassName());
				}
			} catch (NumberFormatException e) {
				return ValidationStatus.error("Non-number detected.", e);
			}
			return ValidationStatus.ok();
		}

	};

	public ScaNumberUpdateValueStrategy(ScaSimpleProperty prop) {
		NumberValidator validator = new NumberValidator(prop.getDefinition().getType().toDataType());
		this.beforeSetValidator = validator;
		this.afterGetValidator = validator;
		switch (prop.getDefinition().getType().toDataType().getClassifierID()) {
		case EcorePackage.EDOUBLE:
		case EcorePackage.EDOUBLE_OBJECT:
			setConverter(StringToNumberConverter.toFloat(true));
			break;
		case EcorePackage.EFLOAT:
		case EcorePackage.EFLOAT_OBJECT:
			setConverter(StringToNumberConverter.toDouble(true));
			break;
		case EcorePackage.EINTEGER_OBJECT:
		case EcorePackage.EINT:
			setConverter(StringToNumberConverter.toInteger(true));
			break;
		case EcorePackage.ELONG:
		case EcorePackage.ELONG_OBJECT:
			setConverter(StringToNumberConverter.toLong(true));
			break;
		case EcorePackage.ESHORT:
		case EcorePackage.ESHORT_OBJECT:
			setConverter(StringToNumberConverter.toShort(true));
			break;
		case EcorePackage.EBYTE:
		case EcorePackage.EBYTE_OBJECT:
			setConverter(StringToNumberConverter.toByte(true));
			break;
		case EcorePackage.EBIG_INTEGER:
			setConverter(StringToNumberConverter.toBigInteger());
			break;
		case EcorePackage.EBIG_DECIMAL:
			setConverter(StringToNumberConverter.toBigDecimal());
			break;
		default:
			throw new IllegalArgumentException("Unknown number format: " + prop.getDefinition().getType().toDataType().getInstanceClassName());
		}
	}
}
