/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package gov.redhawk.sca.model.internal;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap.ValueListIterator;

import mil.jpeojtrs.sca.partitioning.ComponentProperties;
import mil.jpeojtrs.sca.prf.AbstractProperty;
import mil.jpeojtrs.sca.prf.AbstractPropertyRef;
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

public class ExternalPropertiesUtil {

	private ExternalPropertiesUtil() {
	}

	public static HashMap<String, EObject> getOverriddenProperties(ComponentProperties componentProps) {
		HashMap<String, EObject> propertyMap = new HashMap<String, EObject>();
		if (componentProps == null) {
			return propertyMap;
		}
		ValueListIterator<Object> valueListIterator = componentProps.getProperties().valueListIterator();
		while (valueListIterator.hasNext()) {
			AbstractPropertyRef< ? > propertyRef = (AbstractPropertyRef< ? >) valueListIterator.next();
			propertyMap.put(propertyRef.getRefID(), propertyRef);
		}
		return propertyMap;
	}

	public static AbstractProperty copyProperty(AbstractProperty property, Map<String, EObject> overriddenPropsMap) {
		AbstractProperty newProperty = EcoreUtil.copy(property);
		EObject propertyRef = overriddenPropsMap.get(property.getId());
		if (propertyRef == null) {
			return newProperty;
		}
		switch (propertyRef.eClass().getClassifierID()) {
		case PrfPackage.SIMPLE_REF:
			if (!(newProperty instanceof Simple)) {
				return newProperty;
			}
			return copySimple((SimpleRef) propertyRef, (Simple) newProperty);
		case PrfPackage.SIMPLE_SEQUENCE_REF:
			if (!(newProperty instanceof SimpleSequence)) {
				return newProperty;
			}
			return copySimpleSequence((SimpleSequenceRef) propertyRef, (SimpleSequence) newProperty);
		case PrfPackage.STRUCT_REF:
			if (!(newProperty instanceof Struct)) {
				return newProperty;
			}
			return copyStruct((StructRef) propertyRef, (Struct) newProperty);
		case PrfPackage.STRUCT_SEQUENCE_REF:
			if (!(newProperty instanceof StructSequence)) {
				return newProperty;
			}
			return copyStructSequence((StructSequenceRef) propertyRef, (StructSequence) newProperty);
		default:
			throw new RuntimeException("Unknown property type");
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
