/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */

 // BEGIN GENERATED CODE
package gov.redhawk.eclipsecorba.idl.impl;

import gov.redhawk.eclipsecorba.idl.Element;
import gov.redhawk.eclipsecorba.idl.FileRegion;
import gov.redhawk.eclipsecorba.idl.IdlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element</b></em>'.
 * @since 6.0
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.impl.ElementImpl#getStartLine <em>Start Line</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.impl.ElementImpl#getStartColumn <em>Start Column</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.impl.ElementImpl#getEndLine <em>End Line</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.impl.ElementImpl#getEndColumn <em>End Column</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ElementImpl extends IdentifiableImpl implements Element {

	/**
	 * The default value of the '{@link #getStartLine() <em>Start Line</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartLine()
	 * @generated
	 * @ordered
	 */
	protected static final int START_LINE_EDEFAULT = 0;
	/**
	 * The cached value of the '{@link #getStartLine() <em>Start Line</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartLine()
	 * @generated
	 * @ordered
	 */
	protected int startLine = START_LINE_EDEFAULT;
	/**
	 * The default value of the '{@link #getStartColumn() <em>Start Column</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartColumn()
	 * @generated
	 * @ordered
	 */
	protected static final int START_COLUMN_EDEFAULT = 0;
	/**
	 * The cached value of the '{@link #getStartColumn() <em>Start Column</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartColumn()
	 * @generated
	 * @ordered
	 */
	protected int startColumn = START_COLUMN_EDEFAULT;
	/**
	 * The default value of the '{@link #getEndLine() <em>End Line</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndLine()
	 * @generated
	 * @ordered
	 */
	protected static final int END_LINE_EDEFAULT = 0;
	/**
	 * The cached value of the '{@link #getEndLine() <em>End Line</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndLine()
	 * @generated
	 * @ordered
	 */
	protected int endLine = END_LINE_EDEFAULT;
	/**
	 * The default value of the '{@link #getEndColumn() <em>End Column</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndColumn()
	 * @generated
	 * @ordered
	 */
	protected static final int END_COLUMN_EDEFAULT = 0;
	/**
	 * The cached value of the '{@link #getEndColumn() <em>End Column</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndColumn()
	 * @generated
	 * @ordered
	 */
	protected int endColumn = END_COLUMN_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IdlPackage.Literals.ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getStartLine() {
		return startLine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartLine(int newStartLine) {
		int oldStartLine = startLine;
		startLine = newStartLine;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdlPackage.ELEMENT__START_LINE, oldStartLine, startLine));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getStartColumn() {
		return startColumn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartColumn(int newStartColumn) {
		int oldStartColumn = startColumn;
		startColumn = newStartColumn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdlPackage.ELEMENT__START_COLUMN, oldStartColumn, startColumn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getEndLine() {
		return endLine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEndLine(int newEndLine) {
		int oldEndLine = endLine;
		endLine = newEndLine;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdlPackage.ELEMENT__END_LINE, oldEndLine, endLine));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getEndColumn() {
		return endColumn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEndColumn(int newEndColumn) {
		int oldEndColumn = endColumn;
		endColumn = newEndColumn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdlPackage.ELEMENT__END_COLUMN, oldEndColumn, endColumn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IdlPackage.ELEMENT__START_LINE:
				return getStartLine();
			case IdlPackage.ELEMENT__START_COLUMN:
				return getStartColumn();
			case IdlPackage.ELEMENT__END_LINE:
				return getEndLine();
			case IdlPackage.ELEMENT__END_COLUMN:
				return getEndColumn();
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
			case IdlPackage.ELEMENT__START_LINE:
				setStartLine((Integer)newValue);
				return;
			case IdlPackage.ELEMENT__START_COLUMN:
				setStartColumn((Integer)newValue);
				return;
			case IdlPackage.ELEMENT__END_LINE:
				setEndLine((Integer)newValue);
				return;
			case IdlPackage.ELEMENT__END_COLUMN:
				setEndColumn((Integer)newValue);
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
			case IdlPackage.ELEMENT__START_LINE:
				setStartLine(START_LINE_EDEFAULT);
				return;
			case IdlPackage.ELEMENT__START_COLUMN:
				setStartColumn(START_COLUMN_EDEFAULT);
				return;
			case IdlPackage.ELEMENT__END_LINE:
				setEndLine(END_LINE_EDEFAULT);
				return;
			case IdlPackage.ELEMENT__END_COLUMN:
				setEndColumn(END_COLUMN_EDEFAULT);
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
			case IdlPackage.ELEMENT__START_LINE:
				return startLine != START_LINE_EDEFAULT;
			case IdlPackage.ELEMENT__START_COLUMN:
				return startColumn != START_COLUMN_EDEFAULT;
			case IdlPackage.ELEMENT__END_LINE:
				return endLine != END_LINE_EDEFAULT;
			case IdlPackage.ELEMENT__END_COLUMN:
				return endColumn != END_COLUMN_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == FileRegion.class) {
			switch (derivedFeatureID) {
				case IdlPackage.ELEMENT__START_LINE: return IdlPackage.FILE_REGION__START_LINE;
				case IdlPackage.ELEMENT__START_COLUMN: return IdlPackage.FILE_REGION__START_COLUMN;
				case IdlPackage.ELEMENT__END_LINE: return IdlPackage.FILE_REGION__END_LINE;
				case IdlPackage.ELEMENT__END_COLUMN: return IdlPackage.FILE_REGION__END_COLUMN;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == FileRegion.class) {
			switch (baseFeatureID) {
				case IdlPackage.FILE_REGION__START_LINE: return IdlPackage.ELEMENT__START_LINE;
				case IdlPackage.FILE_REGION__START_COLUMN: return IdlPackage.ELEMENT__START_COLUMN;
				case IdlPackage.FILE_REGION__END_LINE: return IdlPackage.ELEMENT__END_LINE;
				case IdlPackage.FILE_REGION__END_COLUMN: return IdlPackage.ELEMENT__END_COLUMN;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (startLine: ");
		result.append(startLine);
		result.append(", startColumn: ");
		result.append(startColumn);
		result.append(", endLine: ");
		result.append(endLine);
		result.append(", endColumn: ");
		result.append(endColumn);
		result.append(')');
		return result.toString();
	}

} //ElementImpl
