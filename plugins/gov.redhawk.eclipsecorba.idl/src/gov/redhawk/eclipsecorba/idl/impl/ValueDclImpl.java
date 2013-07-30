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
package gov.redhawk.eclipsecorba.idl.impl;

import gov.redhawk.eclipsecorba.idl.Definition;
import gov.redhawk.eclipsecorba.idl.DefinitionContainer;
import gov.redhawk.eclipsecorba.idl.Export;
import gov.redhawk.eclipsecorba.idl.ExportContainer;
import gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl;
import gov.redhawk.eclipsecorba.idl.IdlPackage;
import gov.redhawk.eclipsecorba.idl.ValueDcl;
import gov.redhawk.eclipsecorba.idl.ValueForwardDcl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Value Dcl</b></em>'.
 * @since 6.0
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.impl.ValueDclImpl#getBody <em>Body</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.impl.ValueDclImpl#getDefinitions <em>Definitions</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.impl.ValueDclImpl#getInheritedValues <em>Inherited Values</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.impl.ValueDclImpl#getSupportsInterface <em>Supports Interface</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.impl.ValueDclImpl#isCustom <em>Custom</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.impl.ValueDclImpl#getForwardDcl <em>Forward Dcl</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ValueDclImpl extends ValueTypeDclImpl implements ValueDcl {

	/**
	 * The cached value of the '{@link #getBody() <em>Body</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBody()
	 * @generated
	 * @ordered
	 */
	protected EList<Export> body;
	/**
	 * The cached value of the '{@link #getDefinitions() <em>Definitions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefinitions()
	 * @generated
	 * @ordered
	 */
	protected EList<Definition> definitions;
	/**
	 * The cached value of the '{@link #getInheritedValues() <em>Inherited Values</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInheritedValues()
	 * @generated
	 * @ordered
	 */
	protected EList<ValueDcl> inheritedValues;
	/**
	 * The cached value of the '{@link #getSupportsInterface() <em>Supports Interface</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSupportsInterface()
	 * @generated
	 * @ordered
	 */
	protected EList<IdlInterfaceDcl> supportsInterface;
	/**
	 * The default value of the '{@link #isCustom() <em>Custom</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCustom()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CUSTOM_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isCustom() <em>Custom</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCustom()
	 * @generated
	 * @ordered
	 */
	protected boolean custom = CUSTOM_EDEFAULT;
	/**
	 * The cached value of the '{@link #getForwardDcl() <em>Forward Dcl</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForwardDcl()
	 * @generated
	 * @ordered
	 */
	protected ValueForwardDcl forwardDcl;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ValueDclImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IdlPackage.Literals.VALUE_DCL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Export> getBody() {
		if (body == null) {
			body = new EObjectContainmentEList<Export>(Export.class, this, IdlPackage.VALUE_DCL__BODY);
		}
		return body;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Definition> getDefinitions() {
		if (definitions == null) {
			definitions = new EObjectContainmentEList<Definition>(Definition.class, this, IdlPackage.VALUE_DCL__DEFINITIONS);
		}
		return definitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ValueDcl> getInheritedValues() {
		if (inheritedValues == null) {
			inheritedValues = new EObjectResolvingEList<ValueDcl>(ValueDcl.class, this, IdlPackage.VALUE_DCL__INHERITED_VALUES);
		}
		return inheritedValues;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IdlInterfaceDcl> getSupportsInterface() {
		if (supportsInterface == null) {
			supportsInterface = new EObjectResolvingEList<IdlInterfaceDcl>(IdlInterfaceDcl.class, this, IdlPackage.VALUE_DCL__SUPPORTS_INTERFACE);
		}
		return supportsInterface;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCustom() {
		return custom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCustom(boolean newCustom) {
		boolean oldCustom = custom;
		custom = newCustom;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdlPackage.VALUE_DCL__CUSTOM, oldCustom, custom));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueForwardDcl getForwardDcl() {
		if (forwardDcl != null && forwardDcl.eIsProxy()) {
			InternalEObject oldForwardDcl = (InternalEObject)forwardDcl;
			forwardDcl = (ValueForwardDcl)eResolveProxy(oldForwardDcl);
			if (forwardDcl != oldForwardDcl) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IdlPackage.VALUE_DCL__FORWARD_DCL, oldForwardDcl, forwardDcl));
			}
		}
		return forwardDcl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueForwardDcl basicGetForwardDcl() {
		return forwardDcl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setForwardDcl(ValueForwardDcl newForwardDcl) {
		ValueForwardDcl oldForwardDcl = forwardDcl;
		forwardDcl = newForwardDcl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdlPackage.VALUE_DCL__FORWARD_DCL, oldForwardDcl, forwardDcl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case IdlPackage.VALUE_DCL__BODY:
				return ((InternalEList<?>)getBody()).basicRemove(otherEnd, msgs);
			case IdlPackage.VALUE_DCL__DEFINITIONS:
				return ((InternalEList<?>)getDefinitions()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IdlPackage.VALUE_DCL__BODY:
				return getBody();
			case IdlPackage.VALUE_DCL__DEFINITIONS:
				return getDefinitions();
			case IdlPackage.VALUE_DCL__INHERITED_VALUES:
				return getInheritedValues();
			case IdlPackage.VALUE_DCL__SUPPORTS_INTERFACE:
				return getSupportsInterface();
			case IdlPackage.VALUE_DCL__CUSTOM:
				return isCustom();
			case IdlPackage.VALUE_DCL__FORWARD_DCL:
				if (resolve) return getForwardDcl();
				return basicGetForwardDcl();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case IdlPackage.VALUE_DCL__BODY:
				getBody().clear();
				getBody().addAll((Collection<? extends Export>)newValue);
				return;
			case IdlPackage.VALUE_DCL__DEFINITIONS:
				getDefinitions().clear();
				getDefinitions().addAll((Collection<? extends Definition>)newValue);
				return;
			case IdlPackage.VALUE_DCL__INHERITED_VALUES:
				getInheritedValues().clear();
				getInheritedValues().addAll((Collection<? extends ValueDcl>)newValue);
				return;
			case IdlPackage.VALUE_DCL__SUPPORTS_INTERFACE:
				getSupportsInterface().clear();
				getSupportsInterface().addAll((Collection<? extends IdlInterfaceDcl>)newValue);
				return;
			case IdlPackage.VALUE_DCL__CUSTOM:
				setCustom((Boolean)newValue);
				return;
			case IdlPackage.VALUE_DCL__FORWARD_DCL:
				setForwardDcl((ValueForwardDcl)newValue);
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
			case IdlPackage.VALUE_DCL__BODY:
				getBody().clear();
				return;
			case IdlPackage.VALUE_DCL__DEFINITIONS:
				getDefinitions().clear();
				return;
			case IdlPackage.VALUE_DCL__INHERITED_VALUES:
				getInheritedValues().clear();
				return;
			case IdlPackage.VALUE_DCL__SUPPORTS_INTERFACE:
				getSupportsInterface().clear();
				return;
			case IdlPackage.VALUE_DCL__CUSTOM:
				setCustom(CUSTOM_EDEFAULT);
				return;
			case IdlPackage.VALUE_DCL__FORWARD_DCL:
				setForwardDcl((ValueForwardDcl)null);
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
			case IdlPackage.VALUE_DCL__BODY:
				return body != null && !body.isEmpty();
			case IdlPackage.VALUE_DCL__DEFINITIONS:
				return definitions != null && !definitions.isEmpty();
			case IdlPackage.VALUE_DCL__INHERITED_VALUES:
				return inheritedValues != null && !inheritedValues.isEmpty();
			case IdlPackage.VALUE_DCL__SUPPORTS_INTERFACE:
				return supportsInterface != null && !supportsInterface.isEmpty();
			case IdlPackage.VALUE_DCL__CUSTOM:
				return custom != CUSTOM_EDEFAULT;
			case IdlPackage.VALUE_DCL__FORWARD_DCL:
				return forwardDcl != null;
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
		if (baseClass == ExportContainer.class) {
			switch (derivedFeatureID) {
				case IdlPackage.VALUE_DCL__BODY: return IdlPackage.EXPORT_CONTAINER__BODY;
				default: return -1;
			}
		}
		if (baseClass == DefinitionContainer.class) {
			switch (derivedFeatureID) {
				case IdlPackage.VALUE_DCL__DEFINITIONS: return IdlPackage.DEFINITION_CONTAINER__DEFINITIONS;
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
		if (baseClass == ExportContainer.class) {
			switch (baseFeatureID) {
				case IdlPackage.EXPORT_CONTAINER__BODY: return IdlPackage.VALUE_DCL__BODY;
				default: return -1;
			}
		}
		if (baseClass == DefinitionContainer.class) {
			switch (baseFeatureID) {
				case IdlPackage.DEFINITION_CONTAINER__DEFINITIONS: return IdlPackage.VALUE_DCL__DEFINITIONS;
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
		result.append(" (custom: ");
		result.append(custom);
		result.append(')');
		return result.toString();
	}

} //ValueDclImpl
