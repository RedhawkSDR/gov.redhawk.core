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
package gov.redhawk.model.sca;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;

import CF.PropertySetOperations;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Property Container</b></em>'.
 * @since 11.0
 * @noimplement This interface is not intended to be implemented by clients.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link gov.redhawk.model.sca.ScaPropertyContainer#getProperties <em>Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.model.sca.ScaPackage#getScaPropertyContainer()
 * @model abstract="true" superTypes=
 * "gov.redhawk.model.sca.CorbaObjWrapper<P> gov.redhawk.model.sca.ProfileObjectWrapper<E> mil.jpeojtrs.sca.cf.PropertySetOperations"
 * PBounds="gov.redhawk.model.sca.Object" EBounds="org.eclipse.emf.ecore.EJavaObject"
 * extendedMetaData="name='ScaPropertyContainer' kind='elementOnly'"
 * @generated
 */
public interface ScaPropertyContainer< P extends org.omg.CORBA.Object, E extends Object > extends CorbaObjWrapper<P>, ProfileObjectWrapper<E>,
		PropertySetOperations {
	/**
	 * Returns the value of the '<em><b>Properties</b></em>' containment reference list.
	 * The list contents are of type {@link gov.redhawk.model.sca.ScaAbstractProperty}&lt;?>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Properties</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Properties</em>' containment reference list.
	 * @see #isSetProperties()
	 * @see #unsetProperties()
	 * @see gov.redhawk.model.sca.ScaPackage#getScaPropertyContainer_Properties()
	 * @model containment="true" unsettable="true" transient="true"
	 * extendedMetaData="kind='element' name='properties'"
	 * @generated
	 */
	EList<ScaAbstractProperty< ? >> getProperties();

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaPropertyContainer#getProperties <em>Properties</em>}'
	 * containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetProperties()
	 * @see #getProperties()
	 * @generated
	 */
	void unsetProperties();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaPropertyContainer#getProperties
	 * <em>Properties</em>}' containment reference list is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Properties</em>' containment reference list is set.
	 * @see #unsetProperties()
	 * @see #getProperties()
	 * @generated
	 */
	boolean isSetProperties();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	EList<ScaAbstractProperty< ? >> fetchProperties(IProgressMonitor monitor);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	ScaAbstractProperty< ? > getProperty(String identifier);

	/**
	 * @deprecated Use {@link #fetchProperties(IProgressMonitor)} instead
	 * @param monitor
	 */
	@Deprecated
	void fetchPropertyValues(IProgressMonitor monitor) throws InterruptedException;

} // ScaPropertyContainer
