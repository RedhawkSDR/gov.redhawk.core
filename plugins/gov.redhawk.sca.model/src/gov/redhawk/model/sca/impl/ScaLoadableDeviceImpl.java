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

import gov.redhawk.model.sca.ScaLoadableDevice;
import gov.redhawk.model.sca.ScaPackage;

import org.eclipse.emf.ecore.EClass;

import CF.FileSystem;
import CF.InvalidFileName;
import CF.LoadableDevice;
import CF.LoadableDeviceHelper;
import CF.DevicePackage.InvalidState;
import CF.LoadableDevicePackage.InvalidLoadKind;
import CF.LoadableDevicePackage.LoadFail;
import CF.LoadableDevicePackage.LoadType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Loadable Device</b></em>'.
 * 
 * @since 12.0
 *        <!-- end-user-doc -->
 *        <p>
 *        </p>
 *
 * @generated
 */
public class ScaLoadableDeviceImpl< L extends LoadableDevice > extends ScaDeviceImpl<L> implements ScaLoadableDevice<L> {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	protected ScaLoadableDeviceImpl() {
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
		return ScaPackage.Literals.SCA_LOADABLE_DEVICE;
	}

	/**
	 * @since 14.0
	 */
	@Override
	public void load(final FileSystem fs, final String fileName, final LoadType loadKind) throws InvalidState, InvalidLoadKind, InvalidFileName, LoadFail {
		// END GENERATED CODE
		L device = fetchNarrowedObject(null);
		if (device == null) {
			throw new IllegalStateException();
		}
		device.load(fs, fileName, loadKind);
		// BEGIN GENERATED CODE
	}

	@Override
	public void unload(final String fileName) throws InvalidState, InvalidFileName {
		// END GENERATED CODE
		L device = fetchNarrowedObject(null);
		if (device == null) {
			throw new IllegalStateException();
		}
		device.unload(fileName);
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 14.0 {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected L narrow(final org.omg.CORBA.Object obj) {
		// END GENERATED CODE
		return (L) LoadableDeviceHelper.narrow(obj);
		// BEGIN GENERATED CODE
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Class<L> getCorbaType() {
		return (Class<L>) LoadableDevice.class;
	}

} // ScaLoadableDeviceImpl
