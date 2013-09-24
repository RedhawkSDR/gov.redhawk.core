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
package gov.redhawk.eclipsecorba.library.impl;

import gov.redhawk.eclipsecorba.library.LibraryPackage;
import gov.redhawk.eclipsecorba.library.URIPathSet;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>URI Path Set</b></em>'.
 *  @since 4.0
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.library.impl.URIPathSetImpl#getDirs <em>Dirs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class URIPathSetImpl extends PathImpl implements URIPathSet {
	/**
	 * The cached value of the '{@link #getDirs() <em>Dirs</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDirs()
	 * @generated
	 * @ordered
	 */
	protected EList<URI> dirs;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected URIPathSetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LibraryPackage.Literals.URI_PATH_SET;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<URI> getDirs() {
		if (dirs == null) {
			dirs = new EDataTypeUniqueEList<URI>(URI.class, this, LibraryPackage.URI_PATH_SET__DIRS);
		}
		return dirs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LibraryPackage.URI_PATH_SET__DIRS:
				return getDirs();
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
			case LibraryPackage.URI_PATH_SET__DIRS:
				getDirs().clear();
				getDirs().addAll((Collection<? extends URI>)newValue);
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
			case LibraryPackage.URI_PATH_SET__DIRS:
				getDirs().clear();
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
			case LibraryPackage.URI_PATH_SET__DIRS:
				return dirs != null && !dirs.isEmpty();
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
		result.append(" (dirs: ");
		result.append(dirs);
		result.append(')');
		return result.toString();
	}

	{
		// END GENERATED CODE
		eAdapters().add(new AdapterImpl() {
			@Override
			public void notifyChanged(final Notification msg) {
				switch (msg.getFeatureID(URIPathSet.class)) {
				case LibraryPackage.URI_PATH_SET__DIRS:
					if (eNotificationRequired()) {
						eNotify(new ENotificationImpl(URIPathSetImpl.this, msg.getEventType(), LibraryPackage.URI_PATH_SET__DERIVED_PATH, msg.getOldValue(),
							msg.getNewValue()));
					}
					break;
				default:
					break;
				}
			}
		});
		// BEGIN GENERATED CODE
	}

	@Override
	public EList<URI> getDerivedPath() {
		// END GENERATED CODE
		return getDirs();
		// BEGIN GENERATED CODE
	}

} //URIPathSetImpl
