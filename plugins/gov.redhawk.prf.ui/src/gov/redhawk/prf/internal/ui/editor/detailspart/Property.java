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
package gov.redhawk.prf.internal.ui.editor.detailspart;

import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.Struct;
import mil.jpeojtrs.sca.prf.StructSequence;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Provides a common way to access {@link EStructuralFeature}s of the different property types.  
 * This allows us to write common UI code even though the different properties only inherit from {@link EObject}
 */
public enum Property {

	SIMPLE, SIMPLE_SEQUENCE, STRUCT, STRUCT_SEQUENCE;

	/**
	 * 
	 * @param property
	 * @return
	 */
	public static Property getProperty(final EObject property) {
		if (property instanceof Simple) {
			return Property.SIMPLE;
		} else if (property instanceof SimpleSequence) {
			return Property.SIMPLE_SEQUENCE;
		} else if (property instanceof Struct) {
			return Property.STRUCT;
		} else if (property instanceof StructSequence) {
			return Property.STRUCT_SEQUENCE;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Provide access to the property type.
	 * 
	 * @return an {@link EStructuralFeature} for the property type
	 */
	public EStructuralFeature getPropertyFeature() {
		switch (this) {
		case SIMPLE:
			return PrfPackage.Literals.PROPERTIES__SIMPLE;
		case SIMPLE_SEQUENCE:
			return PrfPackage.Literals.PROPERTIES__SIMPLE_SEQUENCE;
		case STRUCT:
			return PrfPackage.Literals.PROPERTIES__STRUCT;
		case STRUCT_SEQUENCE:
			return PrfPackage.Literals.PROPERTIES__STRUCT_SEQUENCE;
		default:
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * Provide access to the name.
	 * 
	 * @return an {@link EStructuralFeature} for the property name
	 */
	public EStructuralFeature getName() {
		return PrfPackage.Literals.ABSTRACT_PROPERTY__NAME;
	}

	/**
	 * Provide access to the ID.
	 * 
	 * @return an {@link EStructuralFeature} for the property ID
	 */
	public EStructuralFeature getId() {
		return PrfPackage.Literals.ABSTRACT_PROPERTY__ID;
	}

	/**
	 * Provide access to the Type.
	 * 
	 * @return an {@link EStructuralFeature} for the property Type
	 */
	public EStructuralFeature getType() {
		switch (this) {
		case SIMPLE:
			return PrfPackage.Literals.SIMPLE__TYPE;
		case SIMPLE_SEQUENCE:
			return PrfPackage.Literals.SIMPLE_SEQUENCE__TYPE;
		default:
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * Provide access to the Mode.
	 * 
	 * @return an {@link EStructuralFeature} for the property Mode
	 */
	public EStructuralFeature getMode() {
		return PrfPackage.Literals.ABSTRACT_PROPERTY__MODE;
	}

	/**
	 * Provide access to the Description.
	 * 
	 * @return an {@link EStructuralFeature} for the property Description
	 */
	public EStructuralFeature getDescription() {
		return PrfPackage.Literals.ABSTRACT_PROPERTY__DESCRIPTION;
	}

	/**
	 * Provide access to the Units.
	 * 
	 * @return an {@link EStructuralFeature} for the property Units
	 */
	public EStructuralFeature getUnits() {
		switch (this) {
		case SIMPLE:
			return PrfPackage.Literals.SIMPLE__UNITS;
		case SIMPLE_SEQUENCE:
			return PrfPackage.Literals.SIMPLE_SEQUENCE__UNITS;
		default:
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * Provide access to the Action.
	 * 
	 * @return an {@link EStructuralFeature} for the property Action
	 */
	public EStructuralFeature getAction() {
		switch (this) {
		case SIMPLE:
			return PrfPackage.Literals.SIMPLE__ACTION;
		case SIMPLE_SEQUENCE:
			return PrfPackage.Literals.SIMPLE_SEQUENCE__ACTION;
		default:
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * Provide access to the Kind.
	 * 
	 * @return an {@link EStructuralFeature} for the property Kind
	 */
	public EStructuralFeature getKind() {
		switch (this) {
		case SIMPLE:
			return PrfPackage.Literals.SIMPLE__KIND;
		case SIMPLE_SEQUENCE:
			return PrfPackage.Literals.SIMPLE_SEQUENCE__KIND;
		default:
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * Provide access to the Range.
	 * 
	 * @return an {@link EStructuralFeature} for the property {@link Range}
	 */
	public EStructuralFeature getRange() {
		switch (this) {
		case SIMPLE:
			return PrfPackage.Literals.SIMPLE__RANGE;
		case SIMPLE_SEQUENCE:
			return PrfPackage.Literals.SIMPLE_SEQUENCE__RANGE;
		default:
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * Provide access to the ConfigurationKind.
	 * 
	 * @return an {@link EStructuralFeature} for the property {@link Range}
	 */
	public EStructuralFeature getConfigurationKind() {
		switch (this) {
		case STRUCT:
			return PrfPackage.Literals.STRUCT__CONFIGURATION_KIND;
		case STRUCT_SEQUENCE:
			return PrfPackage.Literals.STRUCT_SEQUENCE__CONFIGURATION_KIND;
		default:
			throw new UnsupportedOperationException();
		}
	}
}
