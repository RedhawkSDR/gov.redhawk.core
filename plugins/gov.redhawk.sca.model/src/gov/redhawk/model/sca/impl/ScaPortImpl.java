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

import gov.redhawk.model.sca.ScaAbstractComponent;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaPortContainer;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import mil.jpeojtrs.sca.scd.AbstractPort;

import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.omg.CORBA.SystemException;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Port</b></em>'.
 * @since 12.0
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaPortImpl#getName <em>Name</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaPortImpl#getProfileObj <em>Profile Obj</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaPortImpl#getRepid <em>Repid</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaPortImpl#getPortContainer <em>Port Container</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ScaPortImpl<P extends AbstractPort, P2 extends org.omg.CORBA.Object> extends CorbaObjWrapperImpl<P2> implements ScaPort<P, P2> {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 18.0
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 18.0
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;
	/**
	 * The cached value of the '{@link #getProfileObj() <em>Profile Obj</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProfileObj()
	 * @generated
	 * @ordered
	 */
	protected P profileObj;
	/**
	 * The default value of the '{@link #getRepid() <em>Repid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepid()
	 * @generated
	 * @ordered
	 */
	protected static final String REPID_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScaPortImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScaPackage.Literals.SCA_PORT;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * @since 18.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNameGen() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 18.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_PORT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	public P getProfileObj() {
		if (profileObj != null && profileObj.eIsProxy()) {
			InternalEObject oldProfileObj = (InternalEObject)profileObj;
			profileObj = (P)eResolveProxy(oldProfileObj);
			if (profileObj != oldProfileObj) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ScaPackage.SCA_PORT__PROFILE_OBJ, oldProfileObj, profileObj));
			}
		}
		return profileObj;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public P basicGetProfileObj() {
		return profileObj;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setProfileObj(P newProfileObj) {
		P oldProfileObj = profileObj;
		profileObj = newProfileObj;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_PORT__PROFILE_OBJ, oldProfileObj, profileObj));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getRepid() {
		// END GENERATED CODE
		if (getProfileObj() != null) {
			return getProfileObj().getRepID();
		} else {
			return null;
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScaPortContainer getPortContainer() {
		if (eContainerFeatureID() != ScaPackage.SCA_PORT__PORT_CONTAINER) return null;
		return (ScaPortContainer)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaPortContainer basicGetPortContainer() {
		if (eContainerFeatureID() != ScaPackage.SCA_PORT__PORT_CONTAINER) return null;
		return (ScaPortContainer)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPortContainer(ScaPortContainer newPortContainer, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newPortContainer, ScaPackage.SCA_PORT__PORT_CONTAINER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPortContainer(ScaPortContainer newPortContainer) {
		if (newPortContainer != eInternalContainer() || (eContainerFeatureID() != ScaPackage.SCA_PORT__PORT_CONTAINER && newPortContainer != null)) {
			if (EcoreUtil.isAncestor(this, newPortContainer))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPortContainer != null)
				msgs = ((InternalEObject)newPortContainer).eInverseAdd(this, ScaPackage.SCA_PORT_CONTAINER__PORTS, ScaPortContainer.class, msgs);
			msgs = basicSetPortContainer(newPortContainer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_PORT__PORT_CONTAINER, newPortContainer, newPortContainer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ScaPackage.SCA_PORT__PORT_CONTAINER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetPortContainer((ScaPortContainer)otherEnd, msgs);
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
			case ScaPackage.SCA_PORT__PORT_CONTAINER:
				return basicSetPortContainer(null, msgs);
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
			case ScaPackage.SCA_PORT__PORT_CONTAINER:
				return eInternalContainer().eInverseRemove(this, ScaPackage.SCA_PORT_CONTAINER__PORTS, ScaPortContainer.class, msgs);
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
			case ScaPackage.SCA_PORT__NAME:
				return getName();
			case ScaPackage.SCA_PORT__PROFILE_OBJ:
				if (resolve) return getProfileObj();
				return basicGetProfileObj();
			case ScaPackage.SCA_PORT__REPID:
				return getRepid();
			case ScaPackage.SCA_PORT__PORT_CONTAINER:
				if (resolve) return getPortContainer();
				return basicGetPortContainer();
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
			case ScaPackage.SCA_PORT__NAME:
				setName((String)newValue);
				return;
			case ScaPackage.SCA_PORT__PROFILE_OBJ:
				setProfileObj((P)newValue);
				return;
			case ScaPackage.SCA_PORT__PORT_CONTAINER:
				setPortContainer((ScaPortContainer)newValue);
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
			case ScaPackage.SCA_PORT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ScaPackage.SCA_PORT__PROFILE_OBJ:
				setProfileObj((P)null);
				return;
			case ScaPackage.SCA_PORT__PORT_CONTAINER:
				setPortContainer((ScaPortContainer)null);
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
			case ScaPackage.SCA_PORT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ScaPackage.SCA_PORT__PROFILE_OBJ:
				return profileObj != null;
			case ScaPackage.SCA_PORT__REPID:
				return REPID_EDEFAULT == null ? getRepid() != null : !REPID_EDEFAULT.equals(getRepid());
			case ScaPackage.SCA_PORT__PORT_CONTAINER:
				return basicGetPortContainer() != null;
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
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * @deprecated
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Deprecated
	public ScaAbstractComponent<?> getComponent() {
		// END GENERATED CODE
		if (eContainer() instanceof ScaAbstractComponent< ? >) {
			return (ScaAbstractComponent< ? >) eContainer();
		}
		return null;
		// BEGIN GENERATED CODE
	}

	@Override
	public void dispose() {	
	    super.dispose();
	    setProfileObj(null);
	}

	@Override
	protected void notifyChanged(Notification msg) {
		// END GENERATED CODE
		super.notifyChanged(msg);
		if (!msg.isTouch()) {
			switch (msg.getFeatureID(ScaPort.class)) {
			case ScaPackage.SCA_PORT__PROFILE_OBJ:
				eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_PORT__NAME, null, getName()));
				eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_PORT__REPID, null, getRepid()));
				break;
			default:
				break;
			}
		}
		// BEGIN GENERATED CODE
	}
	
	@Override
	public boolean exists() {
		if (getCorbaObj() == null) {
			ScaModelCommand.execute(this, new ScaModelCommand() {
				
				@Override
				public void execute() {
					setStatus(ScaPackage.Literals.CORBA_OBJ_WRAPPER__OBJ, new Status(Status.ERROR, ScaModelPlugin.ID, "Unable to find port object", null));	
				}
			});
			return true;
		}
		try {
			if (getCorbaObj()._non_existent()) {
				ScaModelCommand.execute(this, new ScaModelCommand() {
					
					@Override
					public void execute() {
						setStatus(ScaPackage.Literals.CORBA_OBJ_WRAPPER__OBJ, new Status(Status.ERROR, ScaModelPlugin.ID, "Non existent port object", null));	
					}
				});
			}
		} catch (final SystemException e) {
			ScaModelCommand.execute(this, new ScaModelCommand() {
				
				@Override
				public void execute() {
					setStatus(ScaPackage.Literals.CORBA_OBJ_WRAPPER__OBJ, new Status(Status.ERROR, ScaModelPlugin.ID, "Exception contacting port", e));	
				}
			});
		}
		return true;
	}

} //ScaPortImpl
