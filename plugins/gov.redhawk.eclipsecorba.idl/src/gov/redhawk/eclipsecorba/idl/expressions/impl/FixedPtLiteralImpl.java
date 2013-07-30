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
package gov.redhawk.eclipsecorba.idl.expressions.impl;

import gov.redhawk.eclipsecorba.idl.expressions.ExpressionsPackage;
import gov.redhawk.eclipsecorba.idl.expressions.FixedPtLiteral;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Fixed Pt Literal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.expressions.impl.FixedPtLiteralImpl#getIntegerPart <em>Integer Part</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.expressions.impl.FixedPtLiteralImpl#getDecimalPart <em>Decimal Part</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.expressions.impl.FixedPtLiteralImpl#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FixedPtLiteralImpl extends ExpressionImpl implements FixedPtLiteral {
	/**
	 * The default value of the '{@link #getIntegerPart() <em>Integer Part</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIntegerPart()
	 * @generated
	 * @ordered
	 */
	protected static final int INTEGER_PART_EDEFAULT = 0;
	/**
	 * The cached value of the '{@link #getIntegerPart() <em>Integer Part</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIntegerPart()
	 * @generated
	 * @ordered
	 */
	protected int integerPart = INTEGER_PART_EDEFAULT;
	/**
	 * The default value of the '{@link #getDecimalPart() <em>Decimal Part</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDecimalPart()
	 * @generated
	 * @ordered
	 */
	protected static final int DECIMAL_PART_EDEFAULT = 0;
	/**
	 * The cached value of the '{@link #getDecimalPart() <em>Decimal Part</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDecimalPart()
	 * @generated
	 * @ordered
	 */
	protected int decimalPart = DECIMAL_PART_EDEFAULT;
	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FixedPtLiteralImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExpressionsPackage.Literals.FIXED_PT_LITERAL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getIntegerPart() {
		return integerPart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIntegerPart(int newIntegerPart) {
		int oldIntegerPart = integerPart;
		integerPart = newIntegerPart;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExpressionsPackage.FIXED_PT_LITERAL__INTEGER_PART, oldIntegerPart, integerPart));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getDecimalPart() {
		return decimalPart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDecimalPart(int newDecimalPart) {
		int oldDecimalPart = decimalPart;
		decimalPart = newDecimalPart;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExpressionsPackage.FIXED_PT_LITERAL__DECIMAL_PART, oldDecimalPart, decimalPart));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getValue() {
		return getIntegerPart() + "." + getDecimalPart();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setValue(String newValue) {
		String[] split = newValue.split("\\.");
		try {
			setIntegerPart(Integer.valueOf(split[0]));
		} catch (NumberFormatException e) {
			setIntegerPart(0);
		}
		try {
			if (split.length > 1) {
				setDecimalPart(Integer.valueOf(split[1]));
			} else {
				setDecimalPart(0);
			}
		} catch (NumberFormatException e) {
			setDecimalPart(0);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ExpressionsPackage.FIXED_PT_LITERAL__INTEGER_PART:
				return getIntegerPart();
			case ExpressionsPackage.FIXED_PT_LITERAL__DECIMAL_PART:
				return getDecimalPart();
			case ExpressionsPackage.FIXED_PT_LITERAL__VALUE:
				return getValue();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ExpressionsPackage.FIXED_PT_LITERAL__INTEGER_PART:
				setIntegerPart((Integer)newValue);
				return;
			case ExpressionsPackage.FIXED_PT_LITERAL__DECIMAL_PART:
				setDecimalPart((Integer)newValue);
				return;
			case ExpressionsPackage.FIXED_PT_LITERAL__VALUE:
				setValue((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ExpressionsPackage.FIXED_PT_LITERAL__INTEGER_PART:
				setIntegerPart(INTEGER_PART_EDEFAULT);
				return;
			case ExpressionsPackage.FIXED_PT_LITERAL__DECIMAL_PART:
				setDecimalPart(DECIMAL_PART_EDEFAULT);
				return;
			case ExpressionsPackage.FIXED_PT_LITERAL__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ExpressionsPackage.FIXED_PT_LITERAL__INTEGER_PART:
				return integerPart != INTEGER_PART_EDEFAULT;
			case ExpressionsPackage.FIXED_PT_LITERAL__DECIMAL_PART:
				return decimalPart != DECIMAL_PART_EDEFAULT;
			case ExpressionsPackage.FIXED_PT_LITERAL__VALUE:
				return VALUE_EDEFAULT == null ? getValue() != null : !VALUE_EDEFAULT.equals(getValue());
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (integerPart: ");
		result.append(integerPart);
		result.append(", decimalPart: ");
		result.append(decimalPart);
		result.append(')');
		return result.toString();
	}

} //FixedPtLiteralImpl
