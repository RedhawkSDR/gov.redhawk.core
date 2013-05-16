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

import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaProvidesPort;
import mil.jpeojtrs.sca.scd.Provides;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Provides Port</b></em>'.
 * @since 12.0
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class ScaProvidesPortImpl extends ScaPortImpl<Provides, org.omg.CORBA.Object> implements ScaProvidesPort {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScaProvidesPortImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScaPackage.Literals.SCA_PROVIDES_PORT;
	}

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 8.0
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated NOT
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = "provides";

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getType() {
		// END GENERATED CODE
		return ScaProvidesPortImpl.TYPE_EDEFAULT;
		// BEGIN GENERATED CODE
	}

	@Override
	protected org.omg.CORBA.Object narrow(final org.omg.CORBA.Object obj) {
		// We don't know what to narrow to so do nothing
		return obj;
	}
	
	@Override
	protected Class<org.omg.CORBA.Object> getCorbaType() {
	    return org.omg.CORBA.Object.class;
	}

	/**
     * @since 14.0
     */
	@Override
    protected void internalFetchChildren(IProgressMonitor monitor) throws InterruptedException {
	    // Do nothing
	    
    }
	
	/**
     * @since 14.0
     */
	public boolean mayHaveChildren() {
	    return false;
    }

} //ScaProvidesPortImpl
