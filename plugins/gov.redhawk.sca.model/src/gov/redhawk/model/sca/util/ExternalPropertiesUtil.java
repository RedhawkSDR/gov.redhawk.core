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
package gov.redhawk.model.sca.util;

import java.util.HashMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap.ValueListIterator;

import mil.jpeojtrs.sca.partitioning.ComponentProperties;
import mil.jpeojtrs.sca.prf.AbstractProperty;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleRef;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.SimpleSequenceRef;
import mil.jpeojtrs.sca.prf.Struct;
import mil.jpeojtrs.sca.prf.StructRef;
import mil.jpeojtrs.sca.prf.StructSequence;
import mil.jpeojtrs.sca.prf.StructSequenceRef;
import mil.jpeojtrs.sca.prf.StructValue;

/**
 * @since 20.0
 */
public class ExternalPropertiesUtil {

	private ExternalPropertiesUtil() {
	}

	public static HashMap<String, EObject> getOverriddenProperties(ComponentProperties componentProps) {
		HashMap<String, EObject> propertyMap = new HashMap<String, EObject>();
		ValueListIterator<Object> valueListIterator = componentProps.getProperties().valueListIterator();
		while (valueListIterator.hasNext()) {
			EObject property = (EObject) valueListIterator.next();
			switch (property.eClass().getClassifierID()) {
			case PrfPackage.SIMPLE_REF:
				SimpleRef simpleRef = (SimpleRef) property;
				propertyMap.put(simpleRef.getRefID(), simpleRef);
				break;
			case PrfPackage.SIMPLE_SEQUENCE_REF:
				SimpleSequenceRef simpleSeqRef = (SimpleSequenceRef) property;
				propertyMap.put(simpleSeqRef.getRefID(), simpleSeqRef);
				break;
			case PrfPackage.STRUCT_REF:
				StructRef structRef = (StructRef) property;
				propertyMap.put(structRef.getRefID(), structRef);
				break;
			case PrfPackage.STRUCT_SEQUENCE_REF:
				StructSequenceRef structSeqRef = (StructSequenceRef) property;
				propertyMap.put(structSeqRef.getRefID(), structSeqRef);
				break;
			default:
				break;
			}
		}
		return propertyMap;
	}

	public static AbstractProperty copyProperty(AbstractProperty property, HashMap<String, EObject> overriddenPropsMap) {
		AbstractProperty newProperty = EcoreUtil.copy(property);
		EObject propertyRef = overriddenPropsMap.get(property.getId());
		switch (propertyRef.eClass().getClassifierID()) {
		case PrfPackage.SIMPLE_REF:
			return copySimple((SimpleRef) propertyRef, (Simple) newProperty);
		case PrfPackage.SIMPLE_SEQUENCE_REF:
			return copySimpleSequence((SimpleSequenceRef) propertyRef, (SimpleSequence) newProperty);
		case PrfPackage.STRUCT_REF:
			return copyStruct((StructRef) propertyRef, (Struct) newProperty);
		case PrfPackage.STRUCT_SEQUENCE_REF:
			return copyStructSequence((StructSequenceRef) propertyRef, (StructSequence) newProperty);
		default:
			throw new RuntimeException("Unknopwn property type");
		}
	}

	private static Simple copySimple(SimpleRef simpleRef, Simple newSimple) {
		newSimple.setValue(simpleRef.getValue());
		return newSimple;
	}

	private static SimpleSequence copySimpleSequence(SimpleSequenceRef simpleSeqRef, SimpleSequence newSimpleSeq) {
		newSimpleSeq.setValues(simpleSeqRef.getValues());
		return newSimpleSeq;
	}

	private static Struct copyStruct(StructRef structRef, Struct newStruct) {
		// Copy over the simples
		out: for (SimpleRef simpleRef : structRef.getSimpleRef()) {
			for (Simple simple : newStruct.getSimple()) {
				if (simple.getId().equals(simpleRef.getRefID())) {
					copySimple(simpleRef, simple);
					continue out;
				}
			}
		}

		// Copy over the simple sequences
		out: for (SimpleSequenceRef simpleSeqRef : structRef.getSimpleSequenceRef()) {
			for (SimpleSequence simpleSeq : newStruct.getSimpleSequence()) {
				if (simpleSeq.getId().equals(simpleSeqRef.getRefID())) {
					copySimpleSequence(simpleSeqRef, simpleSeq);
					continue out;
				}
			}
		}

		return newStruct;
	}

	private static StructSequence copyStructSequence(StructSequenceRef structSeqRef, StructSequence newStructSequence) {
		newStructSequence.getStructValue().clear();

		// Copy over the struct values
		for (StructValue structValue : structSeqRef.getStructValue()) {
			newStructSequence.getStructValue().add((StructValue) EcoreUtil.copy(structValue));
		}
		return newStructSequence;
	}
}
