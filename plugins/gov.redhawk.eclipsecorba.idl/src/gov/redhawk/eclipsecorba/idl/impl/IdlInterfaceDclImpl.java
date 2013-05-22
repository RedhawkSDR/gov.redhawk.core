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
import gov.redhawk.eclipsecorba.idl.ForwardDcl;
import gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl;
import gov.redhawk.eclipsecorba.idl.IdlPackage;
import gov.redhawk.eclipsecorba.idl.IdlTypeDcl;

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
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Interface Dcl</b></em>'. 
 * @since 6.0
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.impl.IdlInterfaceDclImpl#getDefinitions <em>Definitions</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.impl.IdlInterfaceDclImpl#getBody <em>Body</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.impl.IdlInterfaceDclImpl#getInheritedInterfaces <em>Inherited Interfaces</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.impl.IdlInterfaceDclImpl#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.impl.IdlInterfaceDclImpl#isLocal <em>Local</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.impl.IdlInterfaceDclImpl#getForwardDcl <em>Forward Dcl</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IdlInterfaceDclImpl extends IdlTypeDclImpl implements IdlInterfaceDcl {
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
	 * The cached value of the '{@link #getBody() <em>Body</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBody()
	 * @generated
	 * @ordered
	 */
	protected EList<Export> body;
	/**
	 * The cached value of the '{@link #getInheritedInterfaces() <em>Inherited Interfaces</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInheritedInterfaces()
	 * @generated
	 * @ordered
	 */
	protected EList<IdlInterfaceDcl> inheritedInterfaces;
	/**
	 * The default value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAbstract()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ABSTRACT_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAbstract()
	 * @generated
	 * @ordered
	 */
	protected boolean abstract_ = ABSTRACT_EDEFAULT;
	/**
	 * The default value of the '{@link #isLocal() <em>Local</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLocal()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LOCAL_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isLocal() <em>Local</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLocal()
	 * @generated
	 * @ordered
	 */
	protected boolean local = LOCAL_EDEFAULT;
	/**
	 * The cached value of the '{@link #getForwardDcl() <em>Forward Dcl</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForwardDcl()
	 * @generated
	 * @ordered
	 */
	protected ForwardDcl forwardDcl;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IdlInterfaceDclImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IdlPackage.Literals.IDL_INTERFACE_DCL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Definition> getDefinitions() {
		if (definitions == null) {
			definitions = new EObjectContainmentEList<Definition>(Definition.class, this, IdlPackage.IDL_INTERFACE_DCL__DEFINITIONS);
		}
		return definitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Export> getBody() {
		if (body == null) {
			body = new EObjectContainmentEList<Export>(Export.class, this, IdlPackage.IDL_INTERFACE_DCL__BODY);
		}
		return body;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IdlInterfaceDcl> getInheritedInterfaces() {
		if (inheritedInterfaces == null) {
			inheritedInterfaces = new EObjectResolvingEList<IdlInterfaceDcl>(IdlInterfaceDcl.class, this, IdlPackage.IDL_INTERFACE_DCL__INHERITED_INTERFACES);
		}
		return inheritedInterfaces;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAbstract() {
		return abstract_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAbstract(boolean newAbstract) {
		boolean oldAbstract = abstract_;
		abstract_ = newAbstract;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdlPackage.IDL_INTERFACE_DCL__ABSTRACT, oldAbstract, abstract_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isLocal() {
		return local;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocal(boolean newLocal) {
		boolean oldLocal = local;
		local = newLocal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdlPackage.IDL_INTERFACE_DCL__LOCAL, oldLocal, local));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ForwardDcl getForwardDcl() {
		if (forwardDcl != null && forwardDcl.eIsProxy()) {
			InternalEObject oldForwardDcl = (InternalEObject)forwardDcl;
			forwardDcl = (ForwardDcl)eResolveProxy(oldForwardDcl);
			if (forwardDcl != oldForwardDcl) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IdlPackage.IDL_INTERFACE_DCL__FORWARD_DCL, oldForwardDcl, forwardDcl));
			}
		}
		return forwardDcl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ForwardDcl basicGetForwardDcl() {
		return forwardDcl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setForwardDcl(ForwardDcl newForwardDcl) {
		ForwardDcl oldForwardDcl = forwardDcl;
		forwardDcl = newForwardDcl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdlPackage.IDL_INTERFACE_DCL__FORWARD_DCL, oldForwardDcl, forwardDcl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 2.0 <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isInstance(final IdlTypeDcl obj) {
		// END GENERATED CODE
		if (this.equals(obj)) {
			return true;
		}

		for (final IdlInterfaceDcl dcl : this.getInheritedInterfaces()) {
			if (dcl.isInstance(obj)) {
				return true;
			}
		}

		return false;
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case IdlPackage.IDL_INTERFACE_DCL__DEFINITIONS:
				return ((InternalEList<?>)getDefinitions()).basicRemove(otherEnd, msgs);
			case IdlPackage.IDL_INTERFACE_DCL__BODY:
				return ((InternalEList<?>)getBody()).basicRemove(otherEnd, msgs);
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
			case IdlPackage.IDL_INTERFACE_DCL__DEFINITIONS:
				return getDefinitions();
			case IdlPackage.IDL_INTERFACE_DCL__BODY:
				return getBody();
			case IdlPackage.IDL_INTERFACE_DCL__INHERITED_INTERFACES:
				return getInheritedInterfaces();
			case IdlPackage.IDL_INTERFACE_DCL__ABSTRACT:
				return isAbstract();
			case IdlPackage.IDL_INTERFACE_DCL__LOCAL:
				return isLocal();
			case IdlPackage.IDL_INTERFACE_DCL__FORWARD_DCL:
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
			case IdlPackage.IDL_INTERFACE_DCL__DEFINITIONS:
				getDefinitions().clear();
				getDefinitions().addAll((Collection<? extends Definition>)newValue);
				return;
			case IdlPackage.IDL_INTERFACE_DCL__BODY:
				getBody().clear();
				getBody().addAll((Collection<? extends Export>)newValue);
				return;
			case IdlPackage.IDL_INTERFACE_DCL__INHERITED_INTERFACES:
				getInheritedInterfaces().clear();
				getInheritedInterfaces().addAll((Collection<? extends IdlInterfaceDcl>)newValue);
				return;
			case IdlPackage.IDL_INTERFACE_DCL__ABSTRACT:
				setAbstract((Boolean)newValue);
				return;
			case IdlPackage.IDL_INTERFACE_DCL__LOCAL:
				setLocal((Boolean)newValue);
				return;
			case IdlPackage.IDL_INTERFACE_DCL__FORWARD_DCL:
				setForwardDcl((ForwardDcl)newValue);
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
			case IdlPackage.IDL_INTERFACE_DCL__DEFINITIONS:
				getDefinitions().clear();
				return;
			case IdlPackage.IDL_INTERFACE_DCL__BODY:
				getBody().clear();
				return;
			case IdlPackage.IDL_INTERFACE_DCL__INHERITED_INTERFACES:
				getInheritedInterfaces().clear();
				return;
			case IdlPackage.IDL_INTERFACE_DCL__ABSTRACT:
				setAbstract(ABSTRACT_EDEFAULT);
				return;
			case IdlPackage.IDL_INTERFACE_DCL__LOCAL:
				setLocal(LOCAL_EDEFAULT);
				return;
			case IdlPackage.IDL_INTERFACE_DCL__FORWARD_DCL:
				setForwardDcl((ForwardDcl)null);
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
			case IdlPackage.IDL_INTERFACE_DCL__DEFINITIONS:
				return definitions != null && !definitions.isEmpty();
			case IdlPackage.IDL_INTERFACE_DCL__BODY:
				return body != null && !body.isEmpty();
			case IdlPackage.IDL_INTERFACE_DCL__INHERITED_INTERFACES:
				return inheritedInterfaces != null && !inheritedInterfaces.isEmpty();
			case IdlPackage.IDL_INTERFACE_DCL__ABSTRACT:
				return abstract_ != ABSTRACT_EDEFAULT;
			case IdlPackage.IDL_INTERFACE_DCL__LOCAL:
				return local != LOCAL_EDEFAULT;
			case IdlPackage.IDL_INTERFACE_DCL__FORWARD_DCL:
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
		if (baseClass == DefinitionContainer.class) {
			switch (derivedFeatureID) {
				case IdlPackage.IDL_INTERFACE_DCL__DEFINITIONS: return IdlPackage.DEFINITION_CONTAINER__DEFINITIONS;
				default: return -1;
			}
		}
		if (baseClass == ExportContainer.class) {
			switch (derivedFeatureID) {
				case IdlPackage.IDL_INTERFACE_DCL__BODY: return IdlPackage.EXPORT_CONTAINER__BODY;
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
		if (baseClass == DefinitionContainer.class) {
			switch (baseFeatureID) {
				case IdlPackage.DEFINITION_CONTAINER__DEFINITIONS: return IdlPackage.IDL_INTERFACE_DCL__DEFINITIONS;
				default: return -1;
			}
		}
		if (baseClass == ExportContainer.class) {
			switch (baseFeatureID) {
				case IdlPackage.EXPORT_CONTAINER__BODY: return IdlPackage.IDL_INTERFACE_DCL__BODY;
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
		result.append(" (abstract: ");
		result.append(abstract_);
		result.append(", local: ");
		result.append(local);
		result.append(')');
		return result.toString();
	}

} //IdlInterfaceDclImpl
