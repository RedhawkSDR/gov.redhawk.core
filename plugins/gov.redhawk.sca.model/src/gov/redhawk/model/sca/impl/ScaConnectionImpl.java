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
package gov.redhawk.model.sca.impl;

import gov.redhawk.model.sca.ScaConnection;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaUsesPort;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connection</b></em>'.
 * 
 * @since 14.0
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link gov.redhawk.model.sca.impl.ScaConnectionImpl#getId <em>Id</em>}</li>
 * <li>{@link gov.redhawk.model.sca.impl.ScaConnectionImpl#getTargetPort <em>Target Port</em>}</li>
 * <li>{@link gov.redhawk.model.sca.impl.ScaConnectionImpl#getPort <em>Port</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ScaConnectionImpl extends EObjectImpl implements ScaConnection {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 21.1
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;
	/**
	 * The default value of the '{@link #getTargetPort() <em>Target Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 21.1
	 * <!-- end-user-doc -->
	 * @see #getTargetPort()
	 * @generated
	 * @ordered
	 */
	protected static final org.omg.CORBA.Object TARGET_PORT_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getTargetPort() <em>Target Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 21.1
	 * <!-- end-user-doc -->
	 * @see #getTargetPort()
	 * @generated
	 * @ordered
	 */
	protected org.omg.CORBA.Object targetPort = TARGET_PORT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScaConnectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScaPackage.Literals.SCA_CONNECTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 21.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_CONNECTION__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 21.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.omg.CORBA.Object getTargetPort() {
		return targetPort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 21.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetPort(org.omg.CORBA.Object newTargetPort) {
		org.omg.CORBA.Object oldTargetPort = targetPort;
		targetPort = newTargetPort;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_CONNECTION__TARGET_PORT, oldTargetPort, targetPort));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScaUsesPort getPort() {
		if (eContainerFeatureID() != ScaPackage.SCA_CONNECTION__PORT)
			return null;
		return (ScaUsesPort) eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaUsesPort basicGetPort() {
		if (eContainerFeatureID() != ScaPackage.SCA_CONNECTION__PORT)
			return null;
		return (ScaUsesPort) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPort(ScaUsesPort newPort, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newPort, ScaPackage.SCA_CONNECTION__PORT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPort(ScaUsesPort newPort) {
		if (newPort != eInternalContainer() || (eContainerFeatureID() != ScaPackage.SCA_CONNECTION__PORT && newPort != null)) {
			if (EcoreUtil.isAncestor(this, newPort))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPort != null)
				msgs = ((InternalEObject) newPort).eInverseAdd(this, ScaPackage.SCA_USES_PORT__CONNECTIONS, ScaUsesPort.class, msgs);
			msgs = basicSetPort(newPort, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_CONNECTION__PORT, newPort, newPort));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ScaPackage.SCA_CONNECTION__PORT:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetPort((ScaUsesPort) otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ScaPackage.SCA_CONNECTION__PORT:
			return basicSetPort(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
		case ScaPackage.SCA_CONNECTION__PORT:
			return eInternalContainer().eInverseRemove(this, ScaPackage.SCA_USES_PORT__CONNECTIONS, ScaUsesPort.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ScaPackage.SCA_CONNECTION__ID:
			return getId();
		case ScaPackage.SCA_CONNECTION__TARGET_PORT:
			return getTargetPort();
		case ScaPackage.SCA_CONNECTION__PORT:
			if (resolve)
				return getPort();
			return basicGetPort();
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
		case ScaPackage.SCA_CONNECTION__ID:
			setId((String) newValue);
			return;
		case ScaPackage.SCA_CONNECTION__TARGET_PORT:
			setTargetPort((org.omg.CORBA.Object) newValue);
			return;
		case ScaPackage.SCA_CONNECTION__PORT:
			setPort((ScaUsesPort) newValue);
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
		case ScaPackage.SCA_CONNECTION__ID:
			setId(ID_EDEFAULT);
			return;
		case ScaPackage.SCA_CONNECTION__TARGET_PORT:
			setTargetPort(TARGET_PORT_EDEFAULT);
			return;
		case ScaPackage.SCA_CONNECTION__PORT:
			setPort((ScaUsesPort) null);
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
		case ScaPackage.SCA_CONNECTION__ID:
			return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		case ScaPackage.SCA_CONNECTION__TARGET_PORT:
			return TARGET_PORT_EDEFAULT == null ? targetPort != null : !TARGET_PORT_EDEFAULT.equals(targetPort);
		case ScaPackage.SCA_CONNECTION__PORT:
			return basicGetPort() != null;
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
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (id: ");
		result.append(id);
		result.append(", targetPort: ");
		result.append(targetPort);
		result.append(')');
		return result.toString();
	}

} // ScaConnectionImpl
