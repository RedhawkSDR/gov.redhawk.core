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

 // BEGIN GENERATED CODE
package gov.redhawk.model.sca;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Domain Connection State</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see gov.redhawk.model.sca.ScaPackage#getDomainConnectionState()
 * @model extendedMetaData="name='DomainConnectionState'"
 * @generated
 */
public enum DomainConnectionState implements Enumerator {
	/**
	 * The '<em><b>Disconnected</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DISCONNECTED_VALUE
	 * @generated
	 * @ordered
	 */
	DISCONNECTED(1, "disconnected", "disconnected"), /**
	 * The '<em><b>Connecting</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONNECTING_VALUE
	 * @generated
	 * @ordered
	 */
	CONNECTING(2, "connecting", "connecting"), /**
	 * The '<em><b>Connected</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONNECTED_VALUE
	 * @generated
	 * @ordered
	 */
	CONNECTED(3, "connected", "connected"), /**
	 * The '<em><b>Disconnecting</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DISCONNECTING_VALUE
	 * @generated
	 * @ordered
	 */
	DISCONNECTING(4, "disconnecting", "disconnecting"), /**
	 * The '<em><b>Failed</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FAILED_VALUE
	 * @generated
	 * @ordered
	 */
	FAILED(-1, "failed", "failed");

/**
	 * The '<em><b>Disconnected</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Disconnected</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DISCONNECTED
	 * @model name="disconnected"
	 * @generated
	 * @ordered
	 */
	public static final int DISCONNECTED_VALUE = 1;
/**
	 * The '<em><b>Connecting</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Connecting</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONNECTING
	 * @model name="connecting"
	 * @generated
	 * @ordered
	 */
	public static final int CONNECTING_VALUE = 2;
/**
	 * The '<em><b>Connected</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Connected</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONNECTED
	 * @model name="connected"
	 * @generated
	 * @ordered
	 */
	public static final int CONNECTED_VALUE = 3;
/**
	 * The '<em><b>Disconnecting</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Disconnecting</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DISCONNECTING
	 * @model name="disconnecting"
	 * @generated
	 * @ordered
	 */
	public static final int DISCONNECTING_VALUE = 4;
/**
	 * The '<em><b>Failed</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Failed</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FAILED
	 * @model name="failed"
	 * @generated
	 * @ordered
	 */
	public static final int FAILED_VALUE = -1;
/**
	 * An array of all the '<em><b>Domain Connection State</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final DomainConnectionState[] VALUES_ARRAY =
		new DomainConnectionState[] {
			DISCONNECTED,
			CONNECTING,
			CONNECTED,
			DISCONNECTING,
			FAILED,
		};
/**
	 * A public read-only list of all the '<em><b>Domain Connection State</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<DomainConnectionState> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

/**
	 * Returns the '<em><b>Domain Connection State</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DomainConnectionState get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DomainConnectionState result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

/**
	 * Returns the '<em><b>Domain Connection State</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DomainConnectionState getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DomainConnectionState result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

/**
	 * Returns the '<em><b>Domain Connection State</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DomainConnectionState get(int value) {
		switch (value) {
			case DISCONNECTED_VALUE: return DISCONNECTED;
			case CONNECTING_VALUE: return CONNECTING;
			case CONNECTED_VALUE: return CONNECTED;
			case DISCONNECTING_VALUE: return DISCONNECTING;
			case FAILED_VALUE: return FAILED;
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
	private DomainConnectionState(int value, String name, String literal) {
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

} //DomainConnectionState
