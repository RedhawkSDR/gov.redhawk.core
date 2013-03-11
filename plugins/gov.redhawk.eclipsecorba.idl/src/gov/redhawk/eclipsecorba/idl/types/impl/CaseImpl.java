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
package gov.redhawk.eclipsecorba.idl.types.impl;

import gov.redhawk.eclipsecorba.idl.impl.FileRegionImpl;
import gov.redhawk.eclipsecorba.idl.types.Case;
import gov.redhawk.eclipsecorba.idl.types.CaseLabel;
import gov.redhawk.eclipsecorba.idl.types.ElementSpec;
import gov.redhawk.eclipsecorba.idl.types.TypesPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Case</b></em>'.
 * @since 6.0
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.types.impl.CaseImpl#getLabels <em>Labels</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.types.impl.CaseImpl#getSpec <em>Spec</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CaseImpl extends FileRegionImpl implements Case {

	/**
	 * The cached value of the '{@link #getLabels() <em>Labels</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabels()
	 * @generated
	 * @ordered
	 */
	protected EList<CaseLabel> labels;
	/**
	 * The cached value of the '{@link #getSpec() <em>Spec</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpec()
	 * @generated
	 * @ordered
	 */
	protected ElementSpec spec;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CaseImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypesPackage.Literals.CASE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CaseLabel> getLabels() {
		if (labels == null) {
			labels = new EObjectContainmentEList<CaseLabel>(CaseLabel.class, this, TypesPackage.CASE__LABELS);
		}
		return labels;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ElementSpec getSpec() {
		return spec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSpec(ElementSpec newSpec, NotificationChain msgs) {
		ElementSpec oldSpec = spec;
		spec = newSpec;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TypesPackage.CASE__SPEC, oldSpec, newSpec);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpec(ElementSpec newSpec) {
		if (newSpec != spec) {
			NotificationChain msgs = null;
			if (spec != null)
				msgs = ((InternalEObject)spec).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TypesPackage.CASE__SPEC, null, msgs);
			if (newSpec != null)
				msgs = ((InternalEObject)newSpec).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TypesPackage.CASE__SPEC, null, msgs);
			msgs = basicSetSpec(newSpec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.CASE__SPEC, newSpec, newSpec));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TypesPackage.CASE__LABELS:
				return ((InternalEList<?>)getLabels()).basicRemove(otherEnd, msgs);
			case TypesPackage.CASE__SPEC:
				return basicSetSpec(null, msgs);
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
			case TypesPackage.CASE__LABELS:
				return getLabels();
			case TypesPackage.CASE__SPEC:
				return getSpec();
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
			case TypesPackage.CASE__LABELS:
				getLabels().clear();
				getLabels().addAll((Collection<? extends CaseLabel>)newValue);
				return;
			case TypesPackage.CASE__SPEC:
				setSpec((ElementSpec)newValue);
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
			case TypesPackage.CASE__LABELS:
				getLabels().clear();
				return;
			case TypesPackage.CASE__SPEC:
				setSpec((ElementSpec)null);
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
			case TypesPackage.CASE__LABELS:
				return labels != null && !labels.isEmpty();
			case TypesPackage.CASE__SPEC:
				return spec != null;
		}
		return super.eIsSet(featureID);
	}

} //CaseImpl
