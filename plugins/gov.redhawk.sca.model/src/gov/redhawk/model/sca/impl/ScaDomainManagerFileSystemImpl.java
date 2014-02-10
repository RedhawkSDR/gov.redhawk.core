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

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaDomainManagerFileSystem;
import gov.redhawk.model.sca.ScaPackage;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import mil.jpeojtrs.sca.util.QueryParser;
import mil.jpeojtrs.sca.util.ScaFileSystemConstants;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import CF.FileManager;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Domain Manager File System</b></em>'.
 *  @since 12.0
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaDomainManagerFileSystemImpl#getDomMgr <em>Dom Mgr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScaDomainManagerFileSystemImpl extends ScaFileManagerImpl implements ScaDomainManagerFileSystem {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScaDomainManagerFileSystemImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScaPackage.Literals.SCA_DOMAIN_MANAGER_FILE_SYSTEM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaDomainManager getDomMgr() {
		if (eContainerFeatureID() != ScaPackage.SCA_DOMAIN_MANAGER_FILE_SYSTEM__DOM_MGR) return null;
		return (ScaDomainManager)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaDomainManager basicGetDomMgr() {
		if (eContainerFeatureID() != ScaPackage.SCA_DOMAIN_MANAGER_FILE_SYSTEM__DOM_MGR) return null;
		return (ScaDomainManager)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDomMgr(ScaDomainManager newDomMgr, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newDomMgr, ScaPackage.SCA_DOMAIN_MANAGER_FILE_SYSTEM__DOM_MGR, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setDomMgr(ScaDomainManager newDomMgr) {
		setDomMgrGen(newDomMgr);
		setFileSystemURI(createFileSystemURI());
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDomMgrGen(ScaDomainManager newDomMgr) {
		if (newDomMgr != eInternalContainer() || (eContainerFeatureID() != ScaPackage.SCA_DOMAIN_MANAGER_FILE_SYSTEM__DOM_MGR && newDomMgr != null)) {
			if (EcoreUtil.isAncestor(this, newDomMgr))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newDomMgr != null)
				msgs = ((InternalEObject)newDomMgr).eInverseAdd(this, ScaPackage.SCA_DOMAIN_MANAGER__FILE_MANAGER, ScaDomainManager.class, msgs);
			msgs = basicSetDomMgr(newDomMgr, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_DOMAIN_MANAGER_FILE_SYSTEM__DOM_MGR, newDomMgr, newDomMgr));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ScaPackage.SCA_DOMAIN_MANAGER_FILE_SYSTEM__DOM_MGR:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetDomMgr((ScaDomainManager)otherEnd, msgs);
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
			case ScaPackage.SCA_DOMAIN_MANAGER_FILE_SYSTEM__DOM_MGR:
				return basicSetDomMgr(null, msgs);
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
			case ScaPackage.SCA_DOMAIN_MANAGER_FILE_SYSTEM__DOM_MGR:
				return eInternalContainer().eInverseRemove(this, ScaPackage.SCA_DOMAIN_MANAGER__FILE_MANAGER, ScaDomainManager.class, msgs);
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
			case ScaPackage.SCA_DOMAIN_MANAGER_FILE_SYSTEM__DOM_MGR:
				if (resolve) return getDomMgr();
				return basicGetDomMgr();
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
			case ScaPackage.SCA_DOMAIN_MANAGER_FILE_SYSTEM__DOM_MGR:
				setDomMgr((ScaDomainManager)newValue);
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
			case ScaPackage.SCA_DOMAIN_MANAGER_FILE_SYSTEM__DOM_MGR:
				setDomMgr((ScaDomainManager)null);
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
			case ScaPackage.SCA_DOMAIN_MANAGER_FILE_SYSTEM__DOM_MGR:
				return basicGetDomMgr() != null;
		}
		return super.eIsSet(featureID);
	}

	@Override
	protected URI createFileSystemURI() {
		ScaDomainManager localDomMgr = getDomMgr();
		String dmName = null;
		if (localDomMgr != null) {
			dmName = localDomMgr.getName();
		}
		try {
			return createFileSystemURI(ior, dmName);
		} catch (final URISyntaxException e) {
			return null;
		}
	}
	
	/**
	 * @since 14.0
	 * @param ior
	 * @param dmName
	 * @return
	 * @throws URISyntaxException
	 */
	protected static URI createFileSystemURI(String ior, String dmName) throws URISyntaxException {
		if (ior == null || dmName == null) {
			return null;
		}
		final Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put(ScaFileSystemConstants.QUERY_PARAM_FS, ior);
		queryParams.put(ScaFileSystemConstants.QUERY_PARAM_DOMAIN_NAME, dmName);
		return new URI(ScaFileSystemConstants.SCHEME + "://?" + QueryParser.createQuery(queryParams));
	}
	
	
	@Override
	public void setCorbaObj(org.omg.CORBA.Object newCorbaObj) {
		if (newCorbaObj instanceof FileManager) {
			super.setCorbaObj(newCorbaObj);
			setObj((FileManager) newCorbaObj);
		} else {
			super.setCorbaObj(newCorbaObj);
		}
	}

} //ScaDomainManagerFileSystemImpl
