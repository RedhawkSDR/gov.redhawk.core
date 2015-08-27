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
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.impl.listeners.DisposableObjectContainerListener;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Domain Manager Registry</b></em>'.
 * 
 * @since 12.0
 *        <!-- end-user-doc -->
 *        <p>
 *        The following features are implemented:
 *        </p>
 *        <ul>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaDomainManagerRegistryImpl#isDisposed <em>Disposed</em>}</li>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaDomainManagerRegistryImpl#getDomains <em>Domains</em>}</li>
 *        </ul>
 *
 * @generated
 */
public class ScaDomainManagerRegistryImpl extends EObjectImpl implements ScaDomainManagerRegistry {
	/**
	 * The default value of the '{@link #isDisposed() <em>Disposed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #isDisposed()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DISPOSED_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isDisposed() <em>Disposed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #isDisposed()
	 * @generated
	 * @ordered
	 */
	protected boolean disposed = DISPOSED_EDEFAULT;
	/**
	 * The cached value of the '{@link #getDomains() <em>Domains</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getDomains()
	 * @generated
	 * @ordered
	 */
	protected EList<ScaDomainManager> domains;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected ScaDomainManagerRegistryImpl() {
		// END GENERATED CODE
		super();
		eAdapters().add(new DisposableObjectContainerListener());
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScaPackage.Literals.SCA_DOMAIN_MANAGER_REGISTRY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isDisposed() {
		return disposed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<ScaDomainManager> getDomains() {
		if (domains == null) {
			domains = new EObjectContainmentEList.Resolving<ScaDomainManager>(ScaDomainManager.class, this, ScaPackage.SCA_DOMAIN_MANAGER_REGISTRY__DOMAINS);
		}
		return domains;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public ScaDomainManager findDomain(final String domainName) {
		// END GENERATED CODE
		ScaDomainManager retVal = null;
		if (eResource() != null) {
			final EObject obj = eResource().getEObject(domainName);
			if (obj instanceof ScaDomainManager) {
				retVal = (ScaDomainManager) obj;
			}
		}

		if (retVal == null) {
			for (final ScaDomainManager domain : getDomains()) {
				if (domain.getLocalName() != null) {
					if (domain.getLocalName().equals(domainName)) {
						retVal = domain;
						break;
					}
				} else {
					if (domain.getLabel().equals(domainName)) {
						retVal = domain;
						break;
					}
				}
			}
		}

		return retVal;
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void dispose() {
		// END GENERATED CODE
		if (disposed) {
			return;
		}
		for (ScaDomainManager domMgr : getDomains()) {
			domMgr.dispose();
		}
		getDomains().clear();
		boolean oldDisposed = disposed;
		disposed = true;
		eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_DOMAIN_MANAGER_REGISTRY__DISPOSED, oldDisposed, disposed));
		// BEGIN GENERATED CODE
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
		case ScaPackage.SCA_DOMAIN_MANAGER_REGISTRY__DOMAINS:
			return ((InternalEList< ? >) getDomains()).basicRemove(otherEnd, msgs);
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
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ScaPackage.SCA_DOMAIN_MANAGER_REGISTRY__DISPOSED:
			return isDisposed();
		case ScaPackage.SCA_DOMAIN_MANAGER_REGISTRY__DOMAINS:
			return getDomains();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ScaPackage.SCA_DOMAIN_MANAGER_REGISTRY__DOMAINS:
			getDomains().clear();
			getDomains().addAll((Collection< ? extends ScaDomainManager>) newValue);
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
		case ScaPackage.SCA_DOMAIN_MANAGER_REGISTRY__DOMAINS:
			getDomains().clear();
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
		case ScaPackage.SCA_DOMAIN_MANAGER_REGISTRY__DISPOSED:
			return disposed != DISPOSED_EDEFAULT;
		case ScaPackage.SCA_DOMAIN_MANAGER_REGISTRY__DOMAINS:
			return domains != null && !domains.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (disposed: ");
		result.append(disposed);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 20.0
	 *        <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public ScaDomainManager createDomain(String localName, final String domainName, final boolean autoConnect, Map<String, String> connectionProperties) {
		// END GENERATED CODE
		final ScaDomainManager newDomain = ScaFactory.eINSTANCE.createScaDomainManager();

		newDomain.setLocalName(localName);
		newDomain.setName(domainName);
		newDomain.setAutoConnect(autoConnect);
		if (connectionProperties == null) {
			connectionProperties = Collections.emptyMap();
		}
		newDomain.getConnectionProperties().putAll(connectionProperties);
		getDomains().add(newDomain);
		Assert.isNotNull(newDomain.eResource(), "Domain not in a resource!");
		Assert.isNotNull(newDomain.eResource().getResourceSet(), "Domain not in a resource set!");
		return newDomain;
		// BEGIN GENERATED CODE
	}

} // ScaDomainManagerRegistryImpl
