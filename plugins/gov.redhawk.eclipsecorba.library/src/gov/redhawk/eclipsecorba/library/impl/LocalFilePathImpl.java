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
import gov.redhawk.eclipsecorba.library.LocalFilePath;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Local File Path</b></em>'.
 * @since 4.0
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.library.impl.LocalFilePathImpl#getLocalPaths <em>Local Paths</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LocalFilePathImpl extends PathImpl implements LocalFilePath {
	/**
	 * The cached value of the '{@link #getLocalPaths() <em>Local Paths</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocalPaths()
	 * @generated
	 * @ordered
	 */
	protected EList<IPath> localPaths;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LocalFilePathImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LibraryPackage.Literals.LOCAL_FILE_PATH;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IPath> getLocalPaths() {
		if (localPaths == null) {
			localPaths = new EDataTypeUniqueEList<IPath>(IPath.class, this, LibraryPackage.LOCAL_FILE_PATH__LOCAL_PATHS);
		}
		return localPaths;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LibraryPackage.LOCAL_FILE_PATH__LOCAL_PATHS:
				return getLocalPaths();
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
			case LibraryPackage.LOCAL_FILE_PATH__LOCAL_PATHS:
				getLocalPaths().clear();
				getLocalPaths().addAll((Collection<? extends IPath>)newValue);
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
			case LibraryPackage.LOCAL_FILE_PATH__LOCAL_PATHS:
				getLocalPaths().clear();
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
			case LibraryPackage.LOCAL_FILE_PATH__LOCAL_PATHS:
				return localPaths != null && !localPaths.isEmpty();
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
		result.append(" (localPaths: ");
		result.append(localPaths);
		result.append(')');
		return result.toString();
	}

	{
		// END GENERATED CODE
		eAdapters().add(new AdapterImpl() {
			@SuppressWarnings("unchecked")
			@Override
			public void notifyChanged(final Notification msg) {
				IPath value = null;
				List<IPath> list = null;
				switch (msg.getFeatureID(LocalFilePath.class)) {
				case LibraryPackage.LOCAL_FILE_PATH__LOCAL_PATHS:
					switch (msg.getEventType()) {
					case Notification.ADD:
						value = (IPath) msg.getNewValue();
						getDerivedPath().add(URI.createURI(value.toFile().toURI().toString()));
						break;
					case Notification.ADD_MANY:
						list = (List<IPath>) msg.getNewValue();
						for (final IPath newValue : list) {
							getDerivedPath().add(URI.createURI(newValue.toFile().toURI().toString()));
						}
						break;
					case Notification.REMOVE:
						value = (IPath) msg.getOldValue();
						getDerivedPath().remove(URI.createURI(value.toFile().toURI().toString()));
						break;
					case Notification.REMOVE_MANY:
						list = (List<IPath>) msg.getNewValue();
						for (final IPath oldValue : list) {
							getDerivedPath().remove(URI.createURI(oldValue.toFile().toURI().toString()));
						}
						break;
					default:
						break;
					}
					break;
				default:
					break;
				}
			}
		});
		// BEGIN GENERATED CODE
	}

} //LocalFilePathImpl
