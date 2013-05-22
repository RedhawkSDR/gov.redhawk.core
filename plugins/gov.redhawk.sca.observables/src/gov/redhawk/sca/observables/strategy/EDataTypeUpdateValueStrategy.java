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
package gov.redhawk.sca.observables.strategy;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.emf.databinding.EMFUpdateValueStrategy;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;

public class EDataTypeUpdateValueStrategy extends EMFUpdateValueStrategy {

	private EDataType type;

	public EDataTypeUpdateValueStrategy(EDataType type) {
		this.type = type;
	}

	@Override
	protected IConverter createConverter(Object fromType, Object toType) {
		if (fromType == String.class) {
			if (toType instanceof EAttribute) {
				final EAttribute eAttribute = (EAttribute) toType;
				final EDataType eDataType = this.type;
				final EFactory eFactory = eDataType.getEPackage().getEFactoryInstance();
				return new Converter(fromType, toType) {
					public Object convert(Object fromObject) {
						String value = (fromObject == null) ? null : fromObject.toString();
						if (eAttribute.isMany()) {
							List<Object> result = new ArrayList<Object>();
							if (value != null) {
								for (String element : value.split(" ")) {
									result.add(eFactory.createFromString(eDataType, element));
								}
							}
							return result;
						} else {
							return eFactory.createFromString(eDataType, value);
						}
					}
				};
			}
		} else if (toType == String.class) {
			if (fromType instanceof EAttribute) {
				final EAttribute eAttribute = (EAttribute) fromType;
				final EDataType eDataType = this.type;
				final EFactory eFactory = eDataType.getEPackage().getEFactoryInstance();
				return new Converter(fromType, toType) {
					public Object convert(Object fromObject) {
						if (eAttribute.isMany()) {
							StringBuilder result = new StringBuilder();
							for (Object value : (List< ? >) fromObject) {
								if (result.length() == 0) {
									result.append(' ');
								}
								result.append(eFactory.convertToString(eDataType, value));
							}
							return result.toString();
						} else {
							return eFactory.convertToString(eDataType, fromObject);
						}
					}
				};
			}
		}
		return super.createConverter(fromType, toType);
	}
}
