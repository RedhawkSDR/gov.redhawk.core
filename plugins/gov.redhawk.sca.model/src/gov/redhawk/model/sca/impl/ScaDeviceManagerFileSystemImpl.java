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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaDeviceManagerFileSystem;
import gov.redhawk.model.sca.ScaPackage;
import mil.jpeojtrs.sca.util.QueryParser;
import mil.jpeojtrs.sca.util.ScaFileSystemConstants;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import CF.FileSystem;
import CF.FileSystemHelper;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Device Manager File System</b></em>'.
 * 
 * @since 12.0
 *        <!-- end-user-doc -->
 *        <p>
 *        The following features are implemented:
 *        <ul>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaDeviceManagerFileSystemImpl#getDeviceManager <em>Device Manager</em>}
 *        </li>
 *        </ul>
 *        </p>
 *
 * @generated
 */
public class ScaDeviceManagerFileSystemImpl extends ScaFileSystemImpl<FileSystem> implements ScaDeviceManagerFileSystem {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ScaDeviceManagerFileSystemImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScaPackage.Literals.SCA_DEVICE_MANAGER_FILE_SYSTEM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ScaDeviceManager getDeviceManager() {
		if (eContainerFeatureID() != ScaPackage.SCA_DEVICE_MANAGER_FILE_SYSTEM__DEVICE_MANAGER)
			return null;
		return (ScaDeviceManager) eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ScaDeviceManager basicGetDeviceManager() {
		if (eContainerFeatureID() != ScaPackage.SCA_DEVICE_MANAGER_FILE_SYSTEM__DEVICE_MANAGER)
			return null;
		return (ScaDeviceManager) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetDeviceManager(ScaDeviceManager newDeviceManager, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newDeviceManager, ScaPackage.SCA_DEVICE_MANAGER_FILE_SYSTEM__DEVICE_MANAGER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setDeviceManager(ScaDeviceManager newDeviceManager) {
		if (newDeviceManager != eInternalContainer()
			|| (eContainerFeatureID() != ScaPackage.SCA_DEVICE_MANAGER_FILE_SYSTEM__DEVICE_MANAGER && newDeviceManager != null)) {
			if (EcoreUtil.isAncestor(this, newDeviceManager))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newDeviceManager != null)
				msgs = ((InternalEObject) newDeviceManager).eInverseAdd(this, ScaPackage.SCA_DEVICE_MANAGER__FILE_SYSTEM, ScaDeviceManager.class, msgs);
			msgs = basicSetDeviceManager(newDeviceManager, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_DEVICE_MANAGER_FILE_SYSTEM__DEVICE_MANAGER, newDeviceManager, newDeviceManager));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ScaPackage.SCA_DEVICE_MANAGER_FILE_SYSTEM__DEVICE_MANAGER:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetDeviceManager((ScaDeviceManager) otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ScaPackage.SCA_DEVICE_MANAGER_FILE_SYSTEM__DEVICE_MANAGER:
			return basicSetDeviceManager(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
		case ScaPackage.SCA_DEVICE_MANAGER_FILE_SYSTEM__DEVICE_MANAGER:
			return eInternalContainer().eInverseRemove(this, ScaPackage.SCA_DEVICE_MANAGER__FILE_SYSTEM, ScaDeviceManager.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ScaPackage.SCA_DEVICE_MANAGER_FILE_SYSTEM__DEVICE_MANAGER:
			if (resolve)
				return getDeviceManager();
			return basicGetDeviceManager();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ScaPackage.SCA_DEVICE_MANAGER_FILE_SYSTEM__DEVICE_MANAGER:
			setDeviceManager((ScaDeviceManager) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case ScaPackage.SCA_DEVICE_MANAGER_FILE_SYSTEM__DEVICE_MANAGER:
			setDeviceManager((ScaDeviceManager) null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case ScaPackage.SCA_DEVICE_MANAGER_FILE_SYSTEM__DEVICE_MANAGER:
			return basicGetDeviceManager() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * @since 14.0
	 */
	@Override
	protected FileSystem narrow(final org.omg.CORBA.Object obj) {
		// END GENERATED CODE
		return FileSystemHelper.narrow(obj);
		// BEGIN GENERATED CODE
	}

	@Override
	protected Class< ? extends FileSystem> getCorbaType() {
		return FileSystem.class;
	}

	@Override
	public void setCorbaObj(org.omg.CORBA.Object newCorbaObj) {
		if (newCorbaObj instanceof FileSystem) {
			super.setCorbaObj(newCorbaObj);
			setObj((FileSystem) newCorbaObj);
		} else {
			super.setCorbaObj(newCorbaObj);
		}
	}

	@Override
	protected URI createFileSystemURI() {
		ScaDeviceManager devMgr = getDeviceManager();
		String devMgrName = null;
		String domMgrName = null;
		if (devMgr != null) {
			if (devMgr.getDomMgr() != null) {
				domMgrName = devMgr.getDomMgr().getLabel();
			}
			devMgrName = devMgr.getLabel();
		}

		try {
			return createFileSystemURI(ior, domMgrName, devMgrName);
		} catch (final URISyntaxException e) {
			return null;
		}
	}

	/**
	 * @since 19.0
	 * @param ior
	 * @param dmName
	 * @return
	 * @throws URISyntaxException
	 */
	protected static URI createFileSystemURI(String ior, String dmName, String devName) throws URISyntaxException {
		if (ior == null) {
			return null;
		}
		final Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put(ScaFileSystemConstants.QUERY_PARAM_FS, ior);
		queryParams.put(ScaFileSystemConstants.QUERY_PARAM_DOMAIN_NAME, dmName);
		queryParams.put(ScaFileSystemConstants.QUERY_PARAM_DEVICE_MGR_NAME, devName);
		return new URI(ScaFileSystemConstants.SCHEME + "://?" + QueryParser.createQuery(queryParams));
	}

} // ScaDeviceManagerFileSystemImpl
