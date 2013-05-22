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

 // BEGIN GENERATED CODE
package gov.redhawk.eclipsecorba.idl.expressions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Unary Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see gov.redhawk.eclipsecorba.idl.expressions.ExpressionsPackage#getUnaryType()
 * @model
 * @generated
 */
public enum UnaryType implements Enumerator {
	/**
	 * The '<em><b>NEGATIVE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NEGATIVE_VALUE
	 * @generated
	 * @ordered
	 */
	NEGATIVE(0, "NEGATIVE", "-"), /**
	 * The '<em><b>POSITIVE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #POSITIVE_VALUE
	 * @generated
	 * @ordered
	 */
	POSITIVE(1, "POSITIVE", "+"), /**
	 * The '<em><b>TILDE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TILDE_VALUE
	 * @generated
	 * @ordered
	 */
	TILDE(2, "TILDE", "~");

/**
	 * The '<em><b>NEGATIVE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NEGATIVE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NEGATIVE
	 * @model literal="-"
	 * @generated
	 * @ordered
	 */
	public static final int NEGATIVE_VALUE = 0;
/**
	 * The '<em><b>POSITIVE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>POSITIVE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #POSITIVE
	 * @model literal="+"
	 * @generated
	 * @ordered
	 */
	public static final int POSITIVE_VALUE = 1;
/**
	 * The '<em><b>TILDE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TILDE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TILDE
	 * @model literal="~"
	 * @generated
	 * @ordered
	 */
	public static final int TILDE_VALUE = 2;
/**
	 * An array of all the '<em><b>Unary Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final UnaryType[] VALUES_ARRAY =
		new UnaryType[] {
			NEGATIVE,
			POSITIVE,
			TILDE,
		};
/**
	 * A public read-only list of all the '<em><b>Unary Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<UnaryType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

/**
	 * Returns the '<em><b>Unary Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static UnaryType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			UnaryType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

/**
	 * Returns the '<em><b>Unary Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static UnaryType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			UnaryType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

/**
	 * Returns the '<em><b>Unary Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static UnaryType get(int value) {
		switch (value) {
			case NEGATIVE_VALUE: return NEGATIVE;
			case POSITIVE_VALUE: return POSITIVE;
			case TILDE_VALUE: return TILDE;
		}
		return null;
	}

/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;
/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;
/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private UnaryType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //UnaryType
